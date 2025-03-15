package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.AdminService;
import ch.pledarigrond.api.services.LuceneService;
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.backup.BackupInfos;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.lucene.IndexStatistics;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.common.util.DbSelector;
import ch.pledarigrond.dictionary.services.DbBackupService;
import ch.pledarigrond.dictionary.services.DictionaryService;
import ch.pledarigrond.lucene.exceptions.IndexException;
import ch.pledarigrond.mongodb.core.Database;
import ch.pledarigrond.mongodb.model.DatabaseStatistics;
import ch.pledarigrond.mongodb.model.DictionaryStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private PgEnvironment pgEnvironment;

    @Autowired
    private LuceneService luceneService;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private DbBackupService dbBackupService;

    private final Logger logger = LoggerFactory.getLogger(AdminService.class);

    @Override
    public void dropDatabase(Language language) {
        logger.info("Dropping database for language: {}", language);
        dictionaryService.deleteAllEntries();
        logger.info("Dropped database for language: {}", language);
    }

    @Override
    public void rebuildIndex(Language language) throws IndexException {
        logger.info("Rebuilding index...");
        luceneService.dropIndex();
        luceneService.addToIndex(dictionaryService.getStreamForEntries());
        logger.info("Index has been created");
    }

    @Override
    public void rebuildSuggestionsIndex(Language language) throws Exception {
        logger.info("Rebuilding suggestions index...");
        luceneService.regenerateSuggestionIndex(language);
        logger.info("Suggestions index has been rebuild");
    }

    @Override
    public DatabaseStatistics getDatabaseStats(Language language) throws NoDatabaseAvailableException {
        DatabaseStatistics statistics = Database.getInstance(DbSelector.getDbNameByLanguage(pgEnvironment, language)).getStatistics();
        logger.info(statistics.toString());
        return statistics;
    }

    @Override
    public IndexStatistics getIndexStats(Language language) {
        IndexStatistics statistics = luceneService.getIndexStatistics();
        DictionaryStatistics.initialize(statistics.getUnverifiedEntries(), statistics.getApprovedEntries(), statistics.getLastUpdated(), statistics.getInflectionCount());
        return statistics;
    }

    @Override
    public void importDatabase(Language language, HttpServletRequest request) throws IOException {
        StandardMultipartHttpServletRequest dmhsRequest = (StandardMultipartHttpServletRequest) request;
        MultipartFile multipartFile = (MultipartFile) dmhsRequest.getFile("file");
        InputStream in = multipartFile.getInputStream();
        logger.info("Importing from backup file... {}", multipartFile.getName());
        dbBackupService.restore(language, in);
    }

    @Override
    public void exportData(Language language, ServletOutputStream out, String fileName) {
        dbBackupService.backup(language, out, fileName);
    }

    @Override
    public BackupInfos getBackupInfos(Language language) {
        return dbBackupService.getBackupInfos(language);
    }

    @Override
    public File getBackupFile(Language language, String fileName) {
        return dbBackupService.getBackupFile(language, fileName);
    }
}
