package ch.pledarigrond.api.services;

import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.exception.DatabaseException;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;

public interface LadinCleanupService {

    public boolean removeEtymology(Language language) throws DatabaseException, UnknownHostException;

    public boolean removeEmptyWords(Language language) throws DatabaseException, UnknownHostException;

    public boolean removeOldForms(Language language) throws DatabaseException, UnknownHostException;
}
