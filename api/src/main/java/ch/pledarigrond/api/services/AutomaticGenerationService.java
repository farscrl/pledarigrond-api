package ch.pledarigrond.api.services;

import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.exception.DatabaseException;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;

import java.io.IOException;
import java.io.OutputStream;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;

public interface AutomaticGenerationService {

    boolean generateNounForms(Language language);

    boolean generateAdjectiveForms(Language language);

    boolean generateVerbForms(Language language);

    boolean fixMissingIds(Language language) throws DatabaseException, UnknownHostException;

    HashMap<String, String> listWrongNextIds(Language language) throws DatabaseException;

    boolean fixWrongNextIds(Language language) throws DatabaseException, UnknownHostException;

    List<LexEntry> findEntriesWithWrongState(Language language) throws NoDatabaseAvailableException;

    boolean fixEntriesWithWrongState(Language language) throws DatabaseException, UnknownHostException;

    boolean addEncliticForms(Language language) throws DatabaseException, UnknownHostException;

    boolean changeGrammarIndications(Language language) throws DatabaseException, UnknownHostException;

    boolean changeGenusIndications(Language language) throws DatabaseException, UnknownHostException;

    boolean fixVerbPronounsRg(Language language) throws DatabaseException, UnknownHostException;

    boolean fixAutomaticDuplicationErrors(Language language) throws DatabaseException, UnknownHostException;

    boolean fixValuesWithNoAcceptedVersion(Language language) throws DatabaseException, UnknownHostException;

    boolean fixWrongParentId(Language language) throws DatabaseException, UnknownHostException;

    boolean exportRemainingWords(Language language) throws IOException, InterruptedException, DatabaseException;

    boolean exportWordsWithGrammarV(Language language) throws IOException, DatabaseException;
}
