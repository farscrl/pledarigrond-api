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
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface AdminService {

    void importDemoDatabase(Language language) throws NoDatabaseAvailableException, IndexException, InvalidEntryException, IOException;

    void dropDatabase(Language language) throws DatabaseException;

    void reloadDemoDatabase(Language language) throws DatabaseException, IndexException, IOException;

    void rebuildIndex(Language language) throws NoDatabaseAvailableException, IndexException;

    DatabaseStatistics getDatabaseStats(Language language) throws NoDatabaseAvailableException;

    IndexStatistics getIndexStats(Language language) throws NoIndexAvailableException;

    void importDatabase(Language language, HttpServletRequest request) throws IOException, InvalidEntryException, NoDatabaseAvailableException, JAXBException, XMLStreamException;

    void exportData(Language language, boolean allVersions, boolean dropKeys, ServletOutputStream out, String fileName) throws NoDatabaseAvailableException, NoSuchAlgorithmException, JAXBException, IOException;

    BackupInfos getBackupInfos(Language language);
}
