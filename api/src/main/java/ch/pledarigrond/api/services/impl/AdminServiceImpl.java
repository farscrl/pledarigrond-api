package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.AdminService;
import ch.pledarigrond.api.services.DumpLoaderService;
import ch.pledarigrond.api.services.LuceneService;
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.data.lucene.IndexStatistics;
import ch.pledarigrond.common.exception.DatabaseException;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.lucene.exceptions.IndexException;
import ch.pledarigrond.lucene.exceptions.NoIndexAvailableException;
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
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.zip.ZipException;

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

    private Logger logger = LoggerFactory.getLogger(AdminService.class);

    public void importDemoDatabase() throws NoDatabaseAvailableException, IndexException, InvalidEntryException, IOException {
        dumpLoaderService.createFromSQLDump(pgEnvironment.getDemoDataSurmiranFile(), -1);
        rebuildIndex();
    }

    public void importDemoDatabase(int max) throws NoDatabaseAvailableException, IndexException, InvalidEntryException, IOException {
        dumpLoaderService.createFromSQLDump(pgEnvironment.getDemoDataSurmiranFile(), max);
        rebuildIndex();
    }

    public void dropDatabase() throws DatabaseException {
        Database.getInstance("surmiran").deleteAllEntries();
        boolean empty = Database.getInstance("surmiran").isEmpty();
        if (!empty) {
            throw new DatabaseException("The database has been dropped but is still not empty, which is weird.");
        }
    }

    public void reloadDemoDatabase() throws DatabaseException, IndexException {
        dropDatabase();
        try {
            dumpLoaderService.createFromSQLDump(pgEnvironment.getDemoDataSurmiranFile(), -1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rebuildIndex() throws NoDatabaseAvailableException, IndexException {
        logger.info("Rebuilding index...");
        Database db = Database.getInstance("surmiran");
        Iterator<LexEntry> iterator = db.getEntries();
        luceneService.dropIndex();
        luceneService.addToIndex(iterator);
        logger.info("Index has been created, swapping to RAM...");
        luceneService.reloadIndex();
        logger.info("RAM-Index updated!");
    }

    public DatabaseStatistics getDatabaseStats() throws NoDatabaseAvailableException {
        DatabaseStatistics statistics = Database.getInstance("surmiran").getStatistics();
        logger.info(statistics.toString());
        return statistics;
    }

    public IndexStatistics getIndexStats() throws NoIndexAvailableException {
        IndexStatistics statistics = luceneService.getIndexStatistics();
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

    public void exportData(boolean allVersions, boolean dropKeys, ServletOutputStream out, String fileName) throws NoDatabaseAvailableException, NoSuchAlgorithmException, JAXBException, IOException {
        Database.getInstance("surmiran").exportData(allVersions, dropKeys, out, fileName);
    }

    public BackupInfos getBackupInfos() {
        return backupInfoHelper.getBackupInfos();
    }
}
