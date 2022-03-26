package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.AdminService;
import ch.pledarigrond.api.services.DumpLoaderService;
import ch.pledarigrond.api.services.LuceneService;
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.data.lucene.IndexStatistics;
import ch.pledarigrond.common.exception.DatabaseException;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.common.util.DbSelector;
import ch.pledarigrond.lucene.exceptions.IndexException;
import ch.pledarigrond.mongodb.core.Database;
import ch.pledarigrond.mongodb.exceptions.InvalidEntryException;
import ch.pledarigrond.mongodb.model.BackupInfos;
import ch.pledarigrond.mongodb.model.DatabaseStatistics;
import ch.pledarigrond.mongodb.model.DictionaryStatistics;
import ch.pledarigrond.mongodb.util.backup.BackupInfoHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private PgEnvironment pgEnvironment;

    @Autowired
    private LuceneService luceneService;

    @Autowired
    private DumpLoaderService dumpLoaderService;

    @Qualifier("backupInfoHelper")
    @Autowired
    private BackupInfoHelper backupInfoHelper;

    private final Logger logger = LoggerFactory.getLogger(AdminService.class);

    public void importDemoDatabase(Language language) throws NoDatabaseAvailableException, IndexException, InvalidEntryException, IOException {
        dumpLoaderService.createFromSQLDump(language, getDemoFileByLanguage(language), -1);
        rebuildIndex(language);
    }

    public void importDemoDatabase(Language language, int max) throws NoDatabaseAvailableException, IndexException, InvalidEntryException, IOException {
        dumpLoaderService.createFromSQLDump(language, getDemoFileByLanguage(language), max);
        rebuildIndex(language);
    }

    @Override
    public void dropDatabase(Language language) throws DatabaseException {
        Database.getInstance(DbSelector.getDbNameByLanguage(pgEnvironment, language)).deleteAllEntries();
        boolean empty = Database.getInstance("surmiran").isEmpty();
        if (!empty) {
            throw new DatabaseException("The database has been dropped but is still not empty, which is weird.");
        }
    }

    @Override
    public void reloadDemoDatabase(Language language) throws DatabaseException, IndexException {
        dropDatabase(language);
        try {
            dumpLoaderService.createFromSQLDump(language, pgEnvironment.getDemoDataSurmiranFile(), -1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rebuildIndex(Language language) throws NoDatabaseAvailableException, IndexException {
        logger.info("Rebuilding index...");
        Database db = Database.getInstance(DbSelector.getDbNameByLanguage(pgEnvironment, language));
        Iterator<LexEntry> iterator = db.getEntries();
        luceneService.dropIndex(language);
        luceneService.addToIndex(language, iterator);
        logger.info("Index has been created, swapping to RAM...");
        luceneService.reloadIndex(language);
        logger.info("RAM-Index updated!");
    }

    @Override
    public DatabaseStatistics getDatabaseStats(Language language) throws NoDatabaseAvailableException {
        DatabaseStatistics statistics = Database.getInstance(DbSelector.getDbNameByLanguage(pgEnvironment, language)).getStatistics();
        logger.info(statistics.toString());
        return statistics;
    }

    @Override
    public IndexStatistics getIndexStats(Language language) {
        IndexStatistics statistics = luceneService.getIndexStatistics(language);
        DictionaryStatistics.initialize(statistics.getUnverifiedEntries(), statistics.getApprovedEntries(), statistics.getLastUpdated(), statistics.getOverlayCount());
        return statistics;
    }

    public void importDatabase(HttpServletRequest request) throws IOException, InvalidEntryException, NoDatabaseAvailableException, JAXBException, XMLStreamException {
        DefaultMultipartHttpServletRequest dmhsRequest = (DefaultMultipartHttpServletRequest) request;
        MultipartFile multipartFile = (MultipartFile) dmhsRequest.getFile("file");
        InputStream in = multipartFile.getInputStream();
        logger.info("Importing from XML file... {}", multipartFile.getName());
        Database.getInstance("surmiran").importData(in);
    }

    @Override
    public void exportData(Language language, boolean allVersions, boolean dropKeys, ServletOutputStream out, String fileName) throws NoDatabaseAvailableException, NoSuchAlgorithmException, JAXBException, IOException {
        Database.getInstance(DbSelector.getDbNameByLanguage(pgEnvironment, language))
                .exportData(allVersions, dropKeys, out, fileName);
    }

    public BackupInfos getBackupInfos(Language language) {
        return backupInfoHelper.getBackupInfos(language);
    }

    private File getDemoFileByLanguage(Language language) {
        // TODO: implement, if more demo-files are available
        return pgEnvironment.getDemoDataSurmiranFile();
    }
}
