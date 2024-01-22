package ch.pledarigrond.inflection.generation.sursilvan;

import ch.pledarigrond.inflection.generation.generic.LanguageInflection;
import ch.pledarigrond.inflection.model.InflectionResponse;
import ch.pledarigrond.inflection.model.InflectionSubType;
import ch.pledarigrond.inflection.model.InflectionType;

import java.util.List;

public class SursilvanInflection implements LanguageInflection {
    @Override
    public List<InflectionSubType> getInflectionSubtypes(InflectionType inflectionType) {
        switch (inflectionType) {
            case V:
                return SursilvanConjugationClasses.getVerbInflectionSubtypes();
            case NOUN:
                return null; // RumantschGrischunNounClasses.getNounInflectionSubtypes();
            case ADJECTIVE:
                return null; // RumantschGrischunAdjectiveClasses.getAdjectiveInflectionSubtypes();
        }

        return null;
    }

    @Override
    public InflectionResponse guessInflectionSubtype(InflectionType inflectionType, String baseForm, String genus, String flex) {
        switch (inflectionType) {
            case V:
                SursilvanConjugation conjugation = new SursilvanConjugation();
                return conjugation.guessInflection(baseForm, genus, flex);
            case NOUN:
                // RumantschGrischunNounGenerator nounGenerator = new RumantschGrischunNounGenerator();
                return null; // nounGenerator.guessInflection(baseForm, genus, flex);
            case ADJECTIVE:
                // RumantschGrischunAdjectiveGenerator adjectiveGenerator = new RumantschGrischunAdjectiveGenerator();
                return null; // adjectiveGenerator.guessInflection(baseForm, genus, flex);
        }
        return null;
    }

    @Override
    public InflectionResponse generateInflection(InflectionType inflectionType, String subTypeId, String baseForm) {
        switch (inflectionType) {
            case V:
                SursilvanConjugation conjugation = new SursilvanConjugation();
                return conjugation.generateConjugation(subTypeId, baseForm);
            case NOUN:
                // RumantschGrischunNounGenerator nounGenerator = new RumantschGrischunNounGenerator();
                return null; // nounGenerator.generateForms(subTypeId, baseForm);
            case ADJECTIVE:
                // RumantschGrischunAdjectiveGenerator adjectiveGenerator = new RumantschGrischunAdjectiveGenerator();
                return null; // adjectiveGenerator.generateForms(subTypeId, baseForm);
        }
        return null;
    }
}
