package ch.pledarigrond.api.services;

import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.exception.DatabaseException;

import java.net.UnknownHostException;

public interface AutomaticGenerationService {

    boolean generateNounForms(Language language);

    boolean generateAdjectiveForms(Language language);

    boolean generateVerbForms(Language language);

    boolean fixAutomaticDuplicationErrors(Language language) throws DatabaseException, UnknownHostException;

    boolean removeSubstIndicationIfGenusIsSet(Language language) throws DatabaseException, UnknownHostException;

    String getVerbListWithConjugationClass(Language language);

    boolean migrateDb() throws DatabaseException, UnknownHostException;
}
