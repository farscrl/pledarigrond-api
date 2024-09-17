package ch.pledarigrond.api.services;

import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.exception.DatabaseException;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public interface ExportService {

    public ByteArrayInputStream ladinComposedVerbs(Language language, HttpServletRequest request) throws IOException, DatabaseException;
    public ByteArrayInputStream ladinConsonantsOnErrors(Language language, HttpServletRequest request) throws IOException, DatabaseException;
    public ByteArrayInputStream ladinEntriesWithCommaAndSlash(Language language, HttpServletRequest request) throws IOException, DatabaseException;
}
