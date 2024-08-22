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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class ScheduledDumpService {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledDumpService.class);

    @Autowired
    private PgEnvironment pgEnvironment;

    public File getJsonExportFile(Language language) throws IOException {
        String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, language);
        File dir = new File(pgEnvironment.getExportLocation() + dbName + "/");
        Files.createDirectories(dir.toPath());

        if (Objects.requireNonNull(dir.listFiles()).length > 0) {
            return Objects.requireNonNull(dir.listFiles(pathname -> pathname.getName().endsWith(".zip")))[0];
        }
        return null;
    }

    @Scheduled(cron = "${pg.export.cron}")
    private void exportJSON() throws IOException, NoDatabaseAvailableException {
        exportDataForLanguage(Language.PUTER);
        exportDataForLanguage(Language.RUMANTSCHGRISCHUN);
        exportDataForLanguage(Language.SURMIRAN);
        exportDataForLanguage(Language.SURSILVAN);
        exportDataForLanguage(Language.SUTSILVAN);
        exportDataForLanguage(Language.VALLADER);
    }

    private void exportDataForLanguage(Language language) throws IOException, NoDatabaseAvailableException {
        String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, language);
        File exportDir = new File(pgEnvironment.getExportLocation() + dbName + "/");
        Files.createDirectories(exportDir.toPath());

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
        return "pledarigrond_export_json_" + DbSelector.getDbNameByLanguage(pgEnvironment, language);
    }
}
