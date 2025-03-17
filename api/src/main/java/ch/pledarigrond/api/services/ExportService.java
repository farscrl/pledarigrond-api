package ch.pledarigrond.api.services;

import ch.pledarigrond.common.data.common.Language;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public interface ExportService {

    ByteArrayInputStream ladinComposedVerbs(Language language, HttpServletRequest request) throws IOException;
    ByteArrayInputStream ladinConsonantsOnErrors(Language language, HttpServletRequest request) throws IOException;
    ByteArrayInputStream ladinEntriesWithCommaAndSlash(Language language, HttpServletRequest request) throws IOException;
}
