package ch.pledarigrond.api.services;

public interface AutomaticGenerationService {

    boolean generateNounForms();

    boolean generateAdjectiveForms();

    boolean generateVerbForms();

    String getVerbListWithConjugationClass();

    boolean migrateDb();
}
