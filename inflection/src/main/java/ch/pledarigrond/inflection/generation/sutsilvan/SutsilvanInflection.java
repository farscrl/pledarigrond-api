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
            case NOUN:
                return SutsilvanNounClasses.getNounInflectionSubtypes();
            case ADJECTIVE:
                return SutsilvanAdjectiveClasses.getAdjectiveInflectionSubtypes();
        }

        return null;
    }

    @Override
    public InflectionResponse guessInflectionSubtype(InflectionType inflectionType, String baseForm, String genus, String flex) {
        switch (inflectionType) {
            case V:
                SutsilvanConjugation conjugation = new SutsilvanConjugation();
                return conjugation.guessInflection(baseForm, genus, flex);
            case NOUN:
                SutsilvanNounGenerator nounGenerator = new SutsilvanNounGenerator();
                return nounGenerator.guessInflection(baseForm, genus, flex);
            case ADJECTIVE:
                SutsilvanAdjectiveGenerator adjectiveGenerator = new SutsilvanAdjectiveGenerator();
                return adjectiveGenerator.guessInflection(baseForm, genus, flex);
        }
        return null;
    }

    @Override
    public InflectionResponse generateInflection(InflectionType inflectionType, String subTypeId, String baseForm) {
        switch (inflectionType) {
            case V:
                SutsilvanConjugation conjugation = new SutsilvanConjugation();
                return conjugation.generateConjugation(subTypeId, baseForm);
            case NOUN:
                SutsilvanNounGenerator nounGenerator = new SutsilvanNounGenerator();
                return nounGenerator.generateForms(subTypeId, baseForm);
            case ADJECTIVE:
                SutsilvanAdjectiveGenerator adjectiveGenerator = new SutsilvanAdjectiveGenerator();
                return adjectiveGenerator.generateForms(subTypeId, baseForm);
        }
        return null;
    }
}
