package ch.pledarigrond.api.services;

import ch.pledarigrond.common.data.common.Language;

public interface AutomaticGenerationService {

    boolean generateNounForms(Language language);

    boolean generateAdjectiveForms(Language language);

    boolean generateVerbForms(Language language);
}
