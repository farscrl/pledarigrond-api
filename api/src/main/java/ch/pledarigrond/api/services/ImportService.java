package ch.pledarigrond.api.services;

import ch.pledarigrond.common.data.common.Language;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface ImportService {
    boolean importXlsSursilvan(Language language, HttpServletRequest request) throws IOException;

    boolean importZipSursilvan(Language language, HttpServletRequest request) throws IOException;
}
