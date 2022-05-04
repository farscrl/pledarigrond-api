package ch.pledarigrond.inflection.generation.surmiran;

import ch.pledarigrond.inflection.generation.generic.LanguageInflection;
import ch.pledarigrond.inflection.model.InflectionResponse;
import ch.pledarigrond.inflection.model.InflectionSubType;
import ch.pledarigrond.inflection.model.InflectionType;

import java.util.HashMap;
import java.util.List;

public class SurmiranInflection implements LanguageInflection {

    @Override
    public List<InflectionSubType> getInflectionSubtypes(InflectionType inflectionType) {
        switch (inflectionType) {
            case VERB:
                return SurmiranConjugationClasses.getVerbInflectionSubtypes();
            case NOUN:
                return SurmiranNounClasses.getNounInflectionSubtypes();
        }

        return null;
    }

    @Override
    public InflectionResponse guessInflectionSubtype(InflectionType inflectionType, String baseForm, String genus, String flex) {
        switch (inflectionType) {
            case VERB:
                SurmiranConjugation conjugation = new SurmiranConjugation();
                return conjugation.guessInflection(baseForm, genus, flex);

            case NOUN:
                SurmiranNounGenerator nounGenerator = new SurmiranNounGenerator();
                return nounGenerator.guessInflection(baseForm, genus, flex);
        }
        return null;
    }

    @Override
    public InflectionResponse generateInflection(InflectionType inflectionType, String subTypeId, String baseForm) {
        switch (inflectionType) {
            case VERB:
                SurmiranConjugation conjugation = new SurmiranConjugation();
                return conjugation.generateConjugation(subTypeId, baseForm);

            case NOUN:
                SurmiranNounGenerator nounGenerator = new SurmiranNounGenerator();
                return nounGenerator.generateForms(subTypeId, baseForm);
        }
        return null;
    }
}
