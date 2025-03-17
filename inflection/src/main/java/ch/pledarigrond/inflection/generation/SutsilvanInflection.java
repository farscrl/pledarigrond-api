package ch.pledarigrond.inflection.generation;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.common.data.dictionary.inflection.InflectionType;
import ch.pledarigrond.inflection.LanguageInflection;
import ch.pledarigrond.inflection.generation.adjective.sutsilvan.SutsilvanAdjectiveClasses;
import ch.pledarigrond.inflection.generation.adjective.sutsilvan.SutsilvanAdjectiveGenerator;
import ch.pledarigrond.inflection.generation.noun.sutsilvan.SutsilvanNounClasses;
import ch.pledarigrond.inflection.generation.noun.sutsilvan.SutsilvanNounGenerator;
import ch.pledarigrond.inflection.generation.verb.sutsilvan.SutsilvanConjugation;
import ch.pledarigrond.inflection.generation.verb.sutsilvan.SutsilvanConjugationClasses;
import ch.pledarigrond.inflection.model.InflectionSubType;

import java.util.List;

public class SutsilvanInflection  implements LanguageInflection {
    @Override
    public List<InflectionSubType> getInflectionSubtypes(InflectionType inflectionType) {
        switch (inflectionType) {
            case VERB:
                return SutsilvanConjugationClasses.getVerbInflectionSubtypes();
            case NOUN:
                return SutsilvanNounClasses.getNounInflectionSubtypes();
            case ADJECTIVE:
                return SutsilvanAdjectiveClasses.getAdjectiveInflectionSubtypes();
        }

        return null;
    }

    @Override
    public InflectionDto guessInflectionSubtype(InflectionType inflectionType, String baseForm, String genus, String flex) {
        switch (inflectionType) {
            case VERB:
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
    public InflectionDto generateInflection(InflectionType inflectionType, String subTypeId, String baseForm) {
        switch (inflectionType) {
            case VERB:
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
