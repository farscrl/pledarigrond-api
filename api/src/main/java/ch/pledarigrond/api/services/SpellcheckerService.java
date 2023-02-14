package ch.pledarigrond.api.services;

import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;

import java.io.File;
import java.io.IOException;

public interface SpellcheckerService {
    File exportHunspell(Language language) throws NoDatabaseAvailableException, IOException;
    File exportMsWordlist(Language language) throws NoDatabaseAvailableException, IOException;
}
