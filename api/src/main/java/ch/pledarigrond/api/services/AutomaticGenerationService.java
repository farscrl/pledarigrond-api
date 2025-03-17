package ch.pledarigrond.api.services;

import ch.pledarigrond.common.exception.DatabaseException;

import java.net.UnknownHostException;

public interface AutomaticGenerationService {

    boolean generateNounForms();

    boolean generateAdjectiveForms();

    boolean generateVerbForms();

    String getVerbListWithConjugationClass();

    boolean migrateDb() throws DatabaseException, UnknownHostException;
}
