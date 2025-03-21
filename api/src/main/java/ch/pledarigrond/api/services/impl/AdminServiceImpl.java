package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.AdminService;
import ch.pledarigrond.api.services.LuceneService;
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.backup.BackupInfos;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.dictionary.DictionaryStatisticsDto;
import ch.pledarigrond.common.data.lucene.IndexStatistics;
import ch.pledarigrond.database.services.DbBackupService;
import ch.pledarigrond.database.services.DictionaryService;
import ch.pledarigrond.lucene.exceptions.IndexException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
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
    public DictionaryStatisticsDto getDatabaseStats(Language language) {
        return dictionaryService.getStatistics();
    }

    @Override
    public IndexStatistics getIndexStats(Language language) {
        return luceneService.getIndexStatistics();
    }

    @Override
    public void importDatabase(Language language, HttpServletRequest request) throws IOException {
        StandardMultipartHttpServletRequest dmhsRequest = (StandardMultipartHttpServletRequest) request;
        MultipartFile multipartFile = (MultipartFile) dmhsRequest.getFile("file");
        InputStream in = multipartFile.getInputStream();
        logger.info("Importing from backup file... {}", multipartFile.getName());
        dbBackupService.restoreLanguage(language, in);
    }

    @Override
    public void exportData(Language language, ServletOutputStream out, String fileName) {
        dbBackupService.backupLanguage(language, out, fileName);
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
