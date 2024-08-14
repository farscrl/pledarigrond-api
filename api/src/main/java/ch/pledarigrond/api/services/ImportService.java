package ch.pledarigrond.api.services;

import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.mongodb.exceptions.InvalidEntryException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface ImportService {

    boolean importXlsSursilvan(Language language, HttpServletRequest request) throws IOException, InvalidEntryException, NoDatabaseAvailableException;
}
