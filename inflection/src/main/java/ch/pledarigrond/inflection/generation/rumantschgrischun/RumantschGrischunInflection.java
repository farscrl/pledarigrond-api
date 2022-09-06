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
            case V:
                return RumantschGrischunConjugationClasses.getVerbInflectionSubtypes();
            case NOUN:
                return RumantschGrischunNounClasses.getNounInflectionSubtypes();
            case ADJECTIVE:
                return RumantschGrischunAdjectiveClasses.getAdjectiveInflectionSubtypes();
        }

        return null;
    }

    @Override
    public InflectionResponse guessInflectionSubtype(InflectionType inflectionType, String baseForm, String genus, String flex) {
        switch (inflectionType) {
            case V:
                RumantschGrischunConjugation conjugation = new RumantschGrischunConjugation();
                return conjugation.guessInflection(baseForm, genus, flex);
            case NOUN:
                RumantschGrischunNounGenerator nounGenerator = new RumantschGrischunNounGenerator();
                return nounGenerator.guessInflection(baseForm, genus, flex);
            case ADJECTIVE:
                RumantschGrischunAdjectiveGenerator adjectiveGenerator = new RumantschGrischunAdjectiveGenerator();
                return adjectiveGenerator.guessInflection(baseForm, genus, flex);
        }
        return null;
    }

    @Override
    public InflectionResponse generateInflection(InflectionType inflectionType, String subTypeId, String baseForm) {
        switch (inflectionType) {
            case V:
                RumantschGrischunConjugation conjugation = new RumantschGrischunConjugation();
                return conjugation.generateConjugation(subTypeId, baseForm);
            case NOUN:
                RumantschGrischunNounGenerator nounGenerator = new RumantschGrischunNounGenerator();
                return nounGenerator.generateForms(subTypeId, baseForm);
            case ADJECTIVE:
                RumantschGrischunAdjectiveGenerator adjectiveGenerator = new RumantschGrischunAdjectiveGenerator();
                return adjectiveGenerator.generateForms(subTypeId, baseForm);
        }
        return null;
    }
}
