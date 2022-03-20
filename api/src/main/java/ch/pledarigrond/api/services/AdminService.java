package ch.pledarigrond.api.services;

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

    void importDemoDatabase() throws NoDatabaseAvailableException, IndexException, InvalidEntryException, IOException;

    void importDemoDatabase(int max) throws NoDatabaseAvailableException, IndexException, InvalidEntryException, IOException;

    void dropDatabase() throws DatabaseException;

    void reloadDemoDatabase() throws DatabaseException, IndexException;

    void rebuildIndex() throws NoDatabaseAvailableException, IndexException;

    DatabaseStatistics getDatabaseStats() throws NoDatabaseAvailableException;

    IndexStatistics getIndexStats() throws NoIndexAvailableException;

    void importDatabase(HttpServletRequest request) throws IOException, InvalidEntryException, NoDatabaseAvailableException, JAXBException, XMLStreamException;

    void exportData(boolean allVersions, boolean dropKeys, ServletOutputStream out, String fileName) throws NoDatabaseAvailableException, NoSuchAlgorithmException, JAXBException, IOException;

    BackupInfos getBackupInfos();
}
