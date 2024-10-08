package ch.pledarigrond.api.services;

import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.lucene.IndexStatistics;
import ch.pledarigrond.common.exception.DatabaseException;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.lucene.exceptions.IndexException;
import ch.pledarigrond.lucene.exceptions.NoIndexAvailableException;
import ch.pledarigrond.mongodb.exceptions.InvalidEntryException;
import ch.pledarigrond.mongodb.model.BackupInfos;
import ch.pledarigrond.mongodb.model.DatabaseStatistics;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface AdminService {

    void dropDatabase(Language language) throws DatabaseException;

    void rebuildIndex(Language language) throws NoDatabaseAvailableException, IndexException;

    void rebuildSuggestionsIndex(Language language) throws Exception;

    DatabaseStatistics getDatabaseStats(Language language) throws NoDatabaseAvailableException;

    IndexStatistics getIndexStats(Language language) throws NoIndexAvailableException;

    void importDatabase(Language language, HttpServletRequest request) throws IOException, InvalidEntryException, NoDatabaseAvailableException, JAXBException, XMLStreamException;

    void exportData(Language language, boolean allVersions, boolean dropKeys, ServletOutputStream out, String fileName) throws NoDatabaseAvailableException, NoSuchAlgorithmException, JAXBException, IOException;

    BackupInfos getBackupInfos(Language language);

    File getBackupFile(Language language, String fileName);
}
