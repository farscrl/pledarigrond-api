package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.DumpLoaderService;
import ch.pledarigrond.api.services.LuceneService;
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.data.common.Role;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.common.util.DbSelector;
import ch.pledarigrond.lucene.exceptions.IndexException;
import ch.pledarigrond.mongodb.core.Converter;
import ch.pledarigrond.mongodb.core.Database;
import ch.pledarigrond.mongodb.exceptions.InvalidEntryException;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Service
public class DumpLoaderServiceImpl implements DumpLoaderService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LuceneService luceneService;

    @Autowired
    private PgEnvironment pgEnvironment;

    /*@Autowired
    private PGAutenticationProvider authProvider;*/ // TODO: re-add me

    @Override
    public void createFromSQLDump(Language language, File file, int maxEntries) throws IOException, NoDatabaseAvailableException, InvalidEntryException, IndexException {

        if(!file.exists()) {
            logger.info("No data to import - file " + file + " does not exist.");
            return;
        }

        BufferedReader br;
        ZipFile zipFile = null;
        if(file.getName().endsWith(".zip")) {
            logger.info("Trying to read data from zip file=" + file.getName());
            zipFile = new ZipFile(file);
            String entryName = file.getName().replaceAll(".zip", "");
            ZipEntry entry = zipFile.getEntry(entryName);
            if(entry == null) {
                logger.info("No file named " + entryName + " found in zip file - skipping import");
                zipFile.close();
                return;
            }
            br = new BufferedReader(new InputStreamReader(zipFile.getInputStream(entry), "UTF-8"));
        } else {
            logger.info("Trying to read data from file " + file);
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
        }

        String line = br.readLine();
        String[] keys = line.split("\t",-1);
        Database db = Database.getInstance(DbSelector.getDbNameByLanguage(pgEnvironment, language)); // TODO: implement for all idioms
        List<Document> entries = new ArrayList();
        int counter = 0;
        //String userId = authProvider.getCurrentUserId();
        String userId = "Import"; // TODO: read correct user ID
        while((line = br.readLine()) != null) {
            String[] values = line.split("\t",-1);
            if(values.length != keys.length) {
                logger.warn("Ignoring entry: Attribute mismatch (" + values.length + " entries found, " + keys.length + " entries expected) in line " + line);
                continue;
            }
            LemmaVersion version = new LemmaVersion();
            for(int i = 0; i < keys.length; i++) {
                String value = values[i].trim();
                String key = keys[i].trim();
                if(value.length() == 0) continue;
                if(key.length() == 0) continue;
                version.setValue(key, value);
            }
            LexEntry entry = new LexEntry(version);
            entry.setCurrent(version);
            entry.getCurrent().setStatus(LemmaVersion.Status.NEW_ENTRY);
            entry.getCurrent().setVerification(LemmaVersion.Verification.ACCEPTED);
            long timestamp = System.currentTimeMillis();
            String embeddedTimeStamp = version.getEntryValue(LemmaVersion.TIMESTAMP);
            if(embeddedTimeStamp != null) {
                timestamp = Long.parseLong(embeddedTimeStamp);
                version.removeEntryValue(LemmaVersion.TIMESTAMP);
            }
            entry.getCurrent().setUserId(userId);
            entry.getCurrent().setTimestamp(timestamp);
            entry.getCurrent().setCreatorRole(Role.ADMIN_5);
            entries.add(new Document(Converter.convertLexEntry(entry)));
            if(entries.size() == 10000) {
                db.insertBatch(entries);
                entries.clear();
            }
            counter++;
            if (counter == maxEntries) {
                logger.warn("Skipping db creation, as max entries is "
                        + maxEntries);
                break;
            }
        }
        db.insertBatch(entries);
        entries.clear();
        Iterator<LexEntry> iterator = db.getEntries();
        luceneService.dropIndex(language);
        luceneService.addToIndex(language, iterator);
        logger.info("Index has been created, swapping to RAM...");
        luceneService.reloadIndex(language);
        logger.info("RAM-Index updated.");
        br.close();
        if(zipFile != null) {
            zipFile.close();
        }
        logger.info("Dataloader initialized.");
    }
}
