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
                return SursilvanNounClasses.getNounInflectionSubtypes();
            case ADJECTIVE:
                return SursilvanAdjectiveClasses.getAdjectiveInflectionSubtypes();
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
                SursilvanNounGenerator nounGenerator = new SursilvanNounGenerator();
                return nounGenerator.guessInflection(baseForm, genus, flex);
            case ADJECTIVE:
                SursilvanAdjectiveGenerator adjectiveGenerator = new SursilvanAdjectiveGenerator();
                return adjectiveGenerator.guessInflection(baseForm, genus, flex);
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
                SursilvanNounGenerator nounGenerator = new SursilvanNounGenerator();
                return nounGenerator.generateForms(subTypeId, baseForm);
            case ADJECTIVE:
                SursilvanAdjectiveGenerator adjectiveGenerator = new SursilvanAdjectiveGenerator();
                return adjectiveGenerator.generateForms(subTypeId, baseForm);
        }
        return null;
    }
}
