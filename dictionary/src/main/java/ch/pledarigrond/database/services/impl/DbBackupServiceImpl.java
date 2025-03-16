package ch.pledarigrond.database.services.impl;

import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.backup.BackupInfos;
import ch.pledarigrond.common.data.backup.FileInfo;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.util.DbSelector;
import ch.pledarigrond.database.services.DbBackupService;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.DigestInputStream;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

@Service
public class DbBackupServiceImpl implements DbBackupService {
    private final Logger logger = LoggerFactory.getLogger(DbBackupServiceImpl.class);

    @Autowired
    private PgEnvironment pgEnvironment;

    @Autowired
    @Qualifier("dictionaryMongoClient")
    MongoClient mongoClient;

    private static final int BATCH_SIZE = 1000;

    @Override
    public void backupLanguage(Language language, OutputStream output, String fileName) {
        String dbName = getDbName(language);
        String collectionName = "entries";
        backupCollection(dbName, collectionName, output, fileName);
    }

    @Override
    public void restoreLanguage(Language language, InputStream input) {
        String dbName = getDbName(language);
        String collectionName = "entries";
        restoreCollection(dbName, collectionName, input);
    }

    @Override
    public void backupUsers(OutputStream output, String fileName) {
        String dbName = "users";
        String collectionName = "users";
        backupCollection(dbName, collectionName, output, fileName);
    }

    @Override
    public void restoreUsers(InputStream input) {
        String dbName = "users";
        String collectionName = "users";
        restoreCollection(dbName, collectionName, input);
    }

    @Override
    public BackupInfos getBackupInfos(Language language) {
        String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, language);
        List<FileInfo> list = new ArrayList<FileInfo>();
        File backupFile = new File(pgEnvironment.getBackupLocation() + dbName + "/");
        backupFile.mkdirs();

        List<File> backupFiles = listBackupFilesAsc(backupFile.getAbsolutePath());
        try {
            for (File file : backupFiles) {
                FileInfo fileInfo = new FileInfo();
                fileInfo.setAbsolutePath(file.getAbsolutePath());
                fileInfo.setLastModified(getLastModified(file));
                fileInfo.setName(file.getName());
                fileInfo.setParent(file.getParentFile().getAbsolutePath());
                fileInfo.setCreationDate(getCreationDate(file));
                fileInfo.setSize(getSize(file));
                list.add(fileInfo);
            }
        } catch (Exception e) {
            logger.error("error occured: ", e);
        }

        String cronExpression = DbSelector.getDbCronByLanguage(pgEnvironment, language);

        BackupInfos infos = new BackupInfos();
        infos.setInfos(list);
        infos.setBackupLocation(backupFile.getPath());
        infos.setTriggerTime(cronExpression);
        infos.setBackupNumber(pgEnvironment.getBackupNumber());

        return infos;
    }

    @Override
    public File getBackupFile(Language language, String fileName) {
        fileName = fileName.replace("/", "").replace("..", "");
        String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, language);
        return new File(pgEnvironment.getBackupLocation() + dbName + "/" + fileName);
    }

    private void backupCollection(String dbName, String collectionName, OutputStream output, String fileName) {
        MongoDatabase database = mongoClient.getDatabase(dbName);
        MongoCollection<Document> collection = database.getCollection(collectionName);

        try (ZipOutputStream zos = new ZipOutputStream(output)) {
            // --- JSON backup entry ---
            ZipEntry jsonEntry = new ZipEntry(fileName + ".json");
            zos.putNextEntry(jsonEntry);

            // Create a MessageDigest to compute the MD5 checksum while writing
            MessageDigest md = MessageDigest.getInstance("MD5");
            // Wrap the ZipOutputStream with a DigestOutputStream so that every byte written updates the MD5 digest.
            DigestOutputStream dos = new DigestOutputStream(zos, md);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(dos, StandardCharsets.UTF_8));

            // Stream the documents one by one
            try (MongoCursor<Document> cursor = collection.find().iterator()) {
                while (cursor.hasNext()) {
                    Document doc = cursor.next();
                    writer.write(doc.toJson());
                    writer.newLine();
                }
            }
            writer.flush();
            zos.closeEntry(); // finish writing the JSON entry

            // Compute the MD5 checksum in hexadecimal format.
            byte[] digestBytes = md.digest();
            StringBuilder md5HexBuilder = new StringBuilder();
            for (byte b : digestBytes) {
                md5HexBuilder.append(String.format("%02x", b));
            }
            String md5Hex = md5HexBuilder.toString();

            // --- infos.txt entry ---
            String infoContent = "DB Name: " + dbName + "\n" +
                    "Collection Name: " + collectionName + "\n" +
                    "Backup Time: " + LocalDateTime.now().toString() + "\n" +
                    "MD5: " + md5Hex + "\n";

            ZipEntry infoEntry = new ZipEntry("infos.txt");
            zos.putNextEntry(infoEntry);
            zos.write(infoContent.getBytes(StandardCharsets.UTF_8));
            zos.closeEntry();

            zos.finish();
            logger.info("Backup completed and saved as: {}", fileName);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error during backup: {}", e.getMessage());
        }
    }

    private void restoreCollection(String dbName, String collectionName, InputStream input) {

        MongoDatabase database = mongoClient.getDatabase(dbName);
        MongoCollection<Document> collection = database.getCollection(collectionName);

        String expectedMd5 = null;
        File tempJsonFile = null;

        // First, open the zip file and extract the infos.txt and the JSON backup file.
        try (ZipInputStream zis = new ZipInputStream(input)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if ("infos.txt".equals(entry.getName())) {
                    // Read infos.txt to extract the expected MD5 checksum.
                    BufferedReader reader = new BufferedReader(new InputStreamReader(zis, StandardCharsets.UTF_8));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.startsWith("MD5:")) {
                            expectedMd5 = line.substring("MD5:".length()).trim();
                        }
                    }
                } else if (entry.getName().endsWith(".json")) {
                    // Save the JSON backup entry to a temporary file.
                    tempJsonFile = File.createTempFile(collectionName, ".json");
                    try (FileOutputStream fos = new FileOutputStream(tempJsonFile)) {
                        byte[] buffer = new byte[4096];
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }
                }
                zis.closeEntry();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error reading zip file: {}", e.getMessage());
            return;
        }

        if (tempJsonFile == null) {
            logger.error("No JSON backup file found in zip.");
            return;
        }
        if (expectedMd5 == null) {
            logger.error("No MD5 checksum found in infos.txt.");
            return;
        }

        // Compute the MD5 checksum of the extracted JSON file.
        String computedMd5;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            try (InputStream is = new FileInputStream(tempJsonFile);
                 DigestInputStream dis = new DigestInputStream(is, md)) {
                byte[] buffer = new byte[4096];
                while (dis.read(buffer) != -1) {
                    // Just reading to update the digest.
                }
            }
            byte[] digestBytes = md.digest();
            StringBuilder md5HexBuilder = new StringBuilder();
            for (byte b : digestBytes) {
                md5HexBuilder.append(String.format("%02x", b));
            }
            computedMd5 = md5HexBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error computing MD5 checksum: {}", e.getMessage());
            return;
        }

        // Compare the computed MD5 checksum with the expected value.
        if (!computedMd5.equals(expectedMd5)) {
            logger.error("MD5 checksum mismatch. Expected: {}, Computed: {}", expectedMd5, computedMd5);
            tempJsonFile.delete();
            return;
        }

        // MD5 matches. Now stream the JSON file line by line and insert documents into MongoDB.

        try (BufferedReader reader = new BufferedReader(new FileReader(tempJsonFile))) {
            List<Document> batch = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Document doc = Document.parse(line);
                    batch.add(doc);
                    if (batch.size() >= BATCH_SIZE) {
                        collection.insertMany(batch);
                        batch.clear();
                    }
                }
            }
            if (!batch.isEmpty()) {
                collection.insertMany(batch);
            }
            logger.info("Restore completed for collection: {}", collectionName);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error during restore: {}", e.getMessage());
        } finally {
            // Clean up the temporary file.
            tempJsonFile.delete();
        }
    }

    private String getSize(File file) {
        return new DecimalFormat("##.##").format((file.length() / 1024) / 1024);
    }

    private String getCreationDate(File file) {
        return new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss")
                .format(new Date(file.lastModified()));
    }

    private String getLastModified(File file) {
        return new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss")
                .format(new Date(file.lastModified()));
    }

    private String getDbName(Language language) {
        return switch (language) {
            case PUTER -> "dict_puter";
            case RUMANTSCHGRISCHUN -> "dict_rumantschgrischun";
            case SURMIRAN -> "dict_surmiran";
            case SURSILVAN -> "dict_sursilvan";
            case SUTSILVAN -> "dict_sutsilvan";
            case VALLADER -> "dict_vallader";
            default -> throw new IllegalStateException("Unexpected value: " + language);
        };
    }

    private List<File> listBackupFilesAsc(String backupDir) {
        File backupFolder = new File(backupDir);
        backupFolder.mkdirs();
        File[] files = backupFolder.listFiles(filter());
        if (files == null) {
            return new ArrayList<File>();
        }
        List<File> backupFiles = Arrays.asList(files);

        Collections.sort(backupFiles, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                Date d1 = new Date(o1.lastModified());
                Date d2 = new Date(o2.lastModified());
                return d2.compareTo(d1);
            }
        });
        return backupFiles;
    }

    private FileFilter filter() {
        return new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.exists() && f.getName().endsWith(".zip")
                        && f.canRead() && !f.isHidden();
            }
        };
    }
}
