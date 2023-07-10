package ch.pledarigrond.api.services;

import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.data.common.LightLemmaVersion;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.common.util.DbSelector;
import ch.pledarigrond.mongodb.core.Converter;
import ch.pledarigrond.mongodb.core.Database;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class ScheduledDumpService {

    @Autowired
    private PgEnvironment pgEnvironment;

    public File getJsonExportFile(Language language) {
        String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, language);
        File dir = new File(pgEnvironment.getExportLocation() + dbName + "/");
        dir.mkdirs();

        if (dir.listFiles().length > 0) {
            File file = dir.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.getName().endsWith(".zip");
                }
            })[0];

            return file;
        }
        return null;
    }

    @Scheduled(cron = "${pg.export.cron}")
    private void exportJSON() throws IOException, NoDatabaseAvailableException, NoSuchAlgorithmException {
        exportDataForLanguage(Language.PUTER);
        exportDataForLanguage(Language.RUMANTSCHGRISCHUN);
        exportDataForLanguage(Language.SURMIRAN);
        exportDataForLanguage(Language.SURSILVAN);
        exportDataForLanguage(Language.SUTSILVAN);
        exportDataForLanguage(Language.VALLADER);
    }

    private void exportDataForLanguage(Language language) throws IOException, NoDatabaseAvailableException, NoSuchAlgorithmException {
        String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, language);
        File exportDir = new File(pgEnvironment.getExportLocation() + dbName + "/");
        exportDir.mkdirs();

        String fileName = getExportFileName(language);
        File file = new File(exportDir, fileName + ".zip");
        FileOutputStream fos = new FileOutputStream(file, false);
        exportDataJson(fos, fileName, Database.getInstance(dbName).getAll());
    }

    private void exportDataJson(OutputStream outputStream, String fileName, MongoCursor<Document> cursor) throws IOException {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        ZipOutputStream zipOutputStream = new ZipOutputStream(bufferedOutputStream);
        ZipEntry zipEntry = new ZipEntry(fileName + ".json");
        zipOutputStream.putNextEntry(zipEntry);

        JSONArray entries = new JSONArray();
        while (cursor.hasNext()) {
            DBObject object = new BasicDBObject(cursor.next());
            LexEntry entry = Converter.convertToLexEntry(object);
            LemmaVersion copy = entry.getCurrent();
            if (copy != null) {
                LightLemmaVersion lemmaVersion = new LightLemmaVersion(copy);
                entries.put(lemmaVersion.getEntryValues());
            }
        }

        String jsonData = entries.toString(1);
        byte[] bytes = jsonData.getBytes();
        for (byte b : bytes) {
            zipOutputStream.write(b);
        }

        zipOutputStream.closeEntry();
        zipOutputStream.close();
    }

    private String getExportFileName(Language language) {
        return  "pledarigrond_export_json_" + DbSelector.getDbNameByLanguage(pgEnvironment, language);
    }
}
