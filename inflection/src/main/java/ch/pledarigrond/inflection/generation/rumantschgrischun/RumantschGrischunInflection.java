package ch.pledarigrond.inflection.generation.rumantschgrischun;

import ch.pledarigrond.inflection.generation.generic.LanguageInflection;
import ch.pledarigrond.inflection.model.InflectionResponse;
import ch.pledarigrond.inflection.model.InflectionSubType;
import ch.pledarigrond.inflection.model.InflectionType;

import java.util.List;

public class RumantschGrischunInflection  implements LanguageInflection {
    @Override
    public List<InflectionSubType> getInflectionSubtypes(InflectionType inflectionType) {
        switch (inflectionType) {
            case VERB:
                return RumantschGrischunConjugationClasses.getVerbInflectionSubtypes();
        }

        return null;
    }

    @Override
    public InflectionSubType guessInflectionSubtype(InflectionType inflectionType, String baseForm) {
        return null;
    }

    @Override
    public InflectionResponse generateInflection(InflectionType inflectionType, String subTypeId, String baseForm) {
        switch (inflectionType) {
            case VERB:
                RumantschGrischunConjugation conjugation = new RumantschGrischunConjugation();
                return conjugation.generateConjugation(subTypeId, baseForm);
        }
        return null;
    }
}