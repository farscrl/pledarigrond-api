package ch.pledarigrond.api.services;

import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.exception.DatabaseException;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;

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

    boolean fixAutomaticDuplicationErrors(Language language) throws DatabaseException, UnknownHostException;

    boolean fixValuesWithNoAcceptedVersion(Language language) throws DatabaseException, UnknownHostException;

    boolean fixWrongParentId(Language language) throws DatabaseException, UnknownHostException;

    boolean removeSubstIndicationIfGenusIsSet(Language language) throws DatabaseException, UnknownHostException;

    boolean moveConjunctivImperfectToConjunctiv2(Language language) throws DatabaseException, UnknownHostException;

    String getVerbListWithConjugationClass(Language language);

    boolean fixEncliticFormsForSurmiran(Language language) throws DatabaseException, UnknownHostException;
}
