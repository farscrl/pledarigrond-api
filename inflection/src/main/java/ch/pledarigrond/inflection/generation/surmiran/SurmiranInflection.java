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
        }

        return null;
    }

    @Override
    public InflectionSubType guessInflectionSubtype(InflectionType inflectionType, String baseForm) {
        // TODO: implement me
        return null;
    }

    @Override
    public InflectionResponse generateInflection(InflectionType inflectionType, String subTypeId, String baseForm) {
        switch (inflectionType) {
            case VERB:
                SurmiranConjugation conjugation = new SurmiranConjugation();
                return conjugation.generateConjugation(subTypeId, baseForm);
        }
        return null;
    }
}
