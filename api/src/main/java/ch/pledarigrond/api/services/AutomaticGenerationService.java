package ch.pledarigrond.api.services;

import java.io.IOException;

public interface AutomaticGenerationService {

    boolean generateNounForms();

    boolean generateAdjectiveForms();

    boolean generateVerbForms();

    String getVerbListWithConjugationClass();

    boolean migrateDb() throws IOException;
}
