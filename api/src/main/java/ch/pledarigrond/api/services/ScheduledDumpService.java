package ch.pledarigrond.api.services;

import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.dictionary.EntryDto;
import ch.pledarigrond.common.data.dictionary.EntryVersionDto;
import ch.pledarigrond.common.util.DbSelector;
import ch.pledarigrond.database.services.DictionaryService;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.util.Objects;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class ScheduledDumpService {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledDumpService.class);

    @Autowired
    private PgEnvironment pgEnvironment;
    @Autowired
    private DictionaryService dictionaryService;

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
    private void exportJSON() throws IOException {
        exportDataForLanguage(Language.PUTER);
        exportDataForLanguage(Language.RUMANTSCHGRISCHUN);
        exportDataForLanguage(Language.SURMIRAN);
        exportDataForLanguage(Language.SURSILVAN);
        exportDataForLanguage(Language.SUTSILVAN);
        exportDataForLanguage(Language.VALLADER);
    }

    private void exportDataForLanguage(Language language) throws IOException {
        logger.info("Exporting data for language {}", language);
        String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, language);
        File exportDir = new File(pgEnvironment.getExportLocation() + dbName + "/");
        Files.createDirectories(exportDir.toPath());

        String fileName = getExportFileName(language);
        File file = new File(exportDir, fileName + ".zip");
        FileOutputStream fos = new FileOutputStream(file, false);
        exportDataJson(fos, fileName, dictionaryService.getStreamForEntries());
        logger.info("Exported data for language {}", language);
    }

    private void exportDataJson(OutputStream outputStream, String fileName, Stream<EntryDto> stream) throws IOException {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        ZipOutputStream zipOutputStream = new ZipOutputStream(bufferedOutputStream);
        ZipEntry zipEntry = new ZipEntry(fileName + ".json");
        zipOutputStream.putNextEntry(zipEntry);

        JsonFactory jsonFactory = new JsonFactory();
        try (JsonGenerator jsonGenerator = jsonFactory.createGenerator(zipOutputStream)) {
            jsonGenerator.writeStartArray();

            ObjectMapper mapper = new ObjectMapper();

            stream.forEach(entry -> {
                try {
                    EntryVersionDto current = entry.getCurrent();
                    current.setUserComment(null);
                    current.setUserEmail(null);
                    current.setCreator(null);
                    current.setCreatorIp(null);
                    current.setCreatorRole(null);
                    current.setAction(null);
                    mapper.writeValue(jsonGenerator, current);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            jsonGenerator.writeEndArray();
        }

        zipOutputStream.closeEntry();
        zipOutputStream.close();
        bufferedOutputStream.close();
    }

    private String getExportFileName(Language language) {
        return "pledarigrond_export_json_" + DbSelector.getDbNameByLanguage(pgEnvironment, language);
    }
}
