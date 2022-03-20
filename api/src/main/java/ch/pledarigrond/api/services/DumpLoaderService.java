package ch.pledarigrond.api.services;

import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.lucene.exceptions.IndexException;
import ch.pledarigrond.mongodb.exceptions.InvalidEntryException;

import java.io.File;
import java.io.IOException;

public interface DumpLoaderService {
    void createFromSQLDump(File file, int maxEntries) throws IOException, NoDatabaseAvailableException, InvalidEntryException, IndexException;
}
