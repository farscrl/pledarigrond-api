package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.BackupService;
import ch.pledarigrond.common.config.PgEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

@Service
public class BackupServiceImpl implements BackupService {

    private final Logger logger = LoggerFactory.getLogger(BackupServiceImpl.class);

    @Autowired
    private PgEnvironment pgEnvironment;

    @Override
    public boolean dumbDb(String db, OutputStream fileOutputStream) throws IOException, InterruptedException {
        Path basePath = Paths.get(pgEnvironment.getDumpLocation());
        Files.createDirectories(basePath);

        List<String> command = Arrays.asList(
                "mongodump",
                "--db", db,
                "--port", pgEnvironment.getDbPort(),
                "--username", pgEnvironment.getDbUsername(),
                "--authenticationDatabase", "admin",
                "--password", pgEnvironment.getDbPassword(),
                "--out", basePath.toString()
        );

        ProcessBuilder pb = new ProcessBuilder(command);

        logger.info(pb.command().toString());

        Process process = pb.start();

        List<String> results = readOutputHelper(process.getInputStream());
        List<String> err = readOutputHelper(process.getErrorStream());

        logger.info("dump result: {}", results);
        logger.info("dump errors: {}", err);

        //WAITING FOR A RETURN FROM THE PROCESS WE STARTED
        int exitCode = process.waitFor();

        if (exitCode != 0) {
            return false;
        }

        zipDirectory(basePath.toFile(), fileOutputStream);
        return true;
    }

    @Override
    public boolean restoreDb(String db, InputStream inputStream) throws IOException, InterruptedException {
        Path basePath = Paths.get(pgEnvironment.getRestoreLocation());
        Files.createDirectories(basePath);
        Path file = basePath.resolve(db + ".zip");
        Files.copy(inputStream, file, StandardCopyOption.REPLACE_EXISTING);

        unzip(file.toFile().getAbsolutePath(), basePath.toFile().getAbsolutePath());

        List<String> command = Arrays.asList(
                "mongorestore",
                "--port", pgEnvironment.getDbPort(),
                "--username", pgEnvironment.getDbUsername(),
                "--authenticationDatabase", "admin",
                "--password", pgEnvironment.getDbPassword(),
                "--dir", basePath.toString()
        );

        ProcessBuilder pb = new ProcessBuilder(command);

        logger.info(pb.command().toString());

        Process process = pb.start();

        List<String> results = readOutputHelper(process.getInputStream());
        List<String> err = readOutputHelper(process.getErrorStream());

        logger.info("restore result: {}", results);
        logger.info("restore errors: {}", err);

        //WAITING FOR A RETURN FROM THE PROCESS WE STARTED
        int exitCode = process.waitFor();

        return exitCode == 0;
    }

    private static List<String> readOutputHelper(InputStream inputStream) throws IOException {
        try (BufferedReader output = new BufferedReader(new InputStreamReader(inputStream))) {
            return output.lines()
                    .collect(Collectors.toList());
        }
    }

    /**
     * This method zips the directory
     */
    private void zipDirectory(File dir, OutputStream outputStream) {
        try {
            List<String> filesListInDir = new ArrayList<>();
            populateFilesList(dir, filesListInDir);

            ZipOutputStream zos = new ZipOutputStream(outputStream);
            for(String filePath : filesListInDir){
                logger.info("Zipping {}", filePath);
                //for ZipEntry we need to keep only relative file path, so we used substring on absolute path
                ZipEntry ze = new ZipEntry(filePath.substring(dir.getAbsolutePath().length()+1));
                zos.putNextEntry(ze);
                //read the file and write to ZipOutputStream
                FileInputStream fis = new FileInputStream(filePath);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                zos.closeEntry();
                fis.close();
            }
            zos.close();
            outputStream.close();
        } catch (IOException e) {
            logger.error("Error while zipping directory", e);
        }
    }

    /**
     * This method populates all the files in a directory to a List
     */
    private void populateFilesList(File dir, List<String> filesListInDir) throws IOException {
        File[] files = dir.listFiles();
        assert files != null;
        for(File file : files){
            if(file.isFile()) filesListInDir.add(file.getAbsolutePath());
            else populateFilesList(file, filesListInDir);
        }
    }


    private void unzip(String zipFilePath, String destDir) {
        File dir = new File(destDir);

        if(!dir.exists()) {
            boolean success = dir.mkdirs();
            if (!success) {
                logger.error("Failed to create directory: " + destDir);
                return;
            }
        }

        FileInputStream fis;
        byte[] buffer = new byte[1024];
        try {
            fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while(ze != null){
                String fileName = ze.getName();
                File newFile = new File(destDir + File.separator + fileName);
                logger.info("Unzipping to {}", newFile.getAbsolutePath());
                //create directories for sub directories in zip
                boolean success = new File(newFile.getParent()).mkdirs();
                if (!success) {
                    logger.error("Failed to create directory: " + newFile.getParent());
                    return;
                }
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                //close this ZipEntry
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            //close last ZipEntry
            zis.closeEntry();
            zis.close();
            fis.close();
        } catch (IOException e) {
            logger.error("Error while unzipping", e);
        }

    }
}
