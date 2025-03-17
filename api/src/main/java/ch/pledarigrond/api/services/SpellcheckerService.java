package ch.pledarigrond.api.services;

import ch.pledarigrond.common.data.common.Language;

import java.io.File;
import java.io.IOException;

public interface SpellcheckerService {
    File exportHunspell(Language language) throws IOException;
    void generateAndCommit() throws IOException;
    File exportMsWordlist(Language language) throws IOException;
}
