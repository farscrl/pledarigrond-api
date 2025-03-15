package ch.pledarigrond.api.services;

import ch.pledarigrond.common.data.backup.BackupInfos;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.dictionary.DictionaryStatisticsDto;
import ch.pledarigrond.common.data.lucene.IndexStatistics;
import ch.pledarigrond.lucene.exceptions.IndexException;
import ch.pledarigrond.lucene.exceptions.NoIndexAvailableException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

public interface AdminService {

    void dropDatabase(Language language);

    void rebuildIndex(Language language) throws IndexException;

    void rebuildSuggestionsIndex(Language language) throws Exception;

    DictionaryStatisticsDto getDatabaseStats(Language language);

    IndexStatistics getIndexStats(Language language) throws NoIndexAvailableException;

    void importDatabase(Language language, HttpServletRequest request) throws IOException;

    void exportData(Language language, ServletOutputStream out, String fileName);

    BackupInfos getBackupInfos(Language language);

    File getBackupFile(Language language, String fileName);
}
