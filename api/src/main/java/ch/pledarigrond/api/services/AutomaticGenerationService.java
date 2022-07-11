package ch.pledarigrond.api.services;

import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.exception.DatabaseException;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;

import java.net.UnknownHostException;

public interface AutomaticGenerationService {

    boolean generateNounForms(Language language);

    boolean generateAdjectiveForms(Language language);

    boolean generateVerbForms(Language language);

    boolean fixMissingIds(Language language) throws DatabaseException, UnknownHostException;
}
