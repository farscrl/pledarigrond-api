package ch.pledarigrond.api.services;

import ch.pledarigrond.common.data.dictionary.DuplicateGroupDto;

import java.util.List;

public interface AutomaticGenerationService {

    boolean generateNounForms();

    boolean generateAdjectiveForms();

    boolean generateVerbForms();

    String getVerbListWithConjugationClass();

    List<DuplicateGroupDto> deleteExactDuplicates(boolean execute);

    int setAuxiliarForTransitiveVerbs();

    int fixFuturFormsAdSe();
}
