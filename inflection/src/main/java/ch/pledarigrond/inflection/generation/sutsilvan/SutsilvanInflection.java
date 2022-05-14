package ch.pledarigrond.inflection.generation.sutsilvan;

import ch.pledarigrond.inflection.generation.generic.LanguageInflection;
import ch.pledarigrond.inflection.model.InflectionResponse;
import ch.pledarigrond.inflection.model.InflectionSubType;
import ch.pledarigrond.inflection.model.InflectionType;

import java.util.List;

public class SutsilvanInflection  implements LanguageInflection {
    @Override
    public List<InflectionSubType> getInflectionSubtypes(InflectionType inflectionType) {
        switch (inflectionType) {
            case V:
                return SutsilvanConjugationClasses.getVerbInflectionSubtypes();
        }

        return null;
    }

    @Override
    public InflectionResponse guessInflectionSubtype(InflectionType inflectionType, String baseForm, String genus, String flex) {
        return null;
    }

    @Override
    public InflectionResponse generateInflection(InflectionType inflectionType, String subTypeId, String baseForm) {
        switch (inflectionType) {
            case V:
                SutsilvanConjugation conjugation = new SutsilvanConjugation();
                return conjugation.generateConjugation(subTypeId, baseForm);
        }
        return null;
    }
}
