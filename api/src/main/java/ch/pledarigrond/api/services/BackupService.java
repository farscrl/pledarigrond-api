package ch.pledarigrond.api.services;

import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.mongodb.exceptions.InvalidEntryException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.*;

public interface BackupService {

    boolean dumbDb(String db, OutputStream fileOutputStream) throws IOException, InterruptedException;
    boolean restoreDb(String db, InputStream inputStream) throws IOException, InterruptedException;
}
