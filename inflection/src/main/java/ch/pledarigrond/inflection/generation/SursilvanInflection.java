package ch.pledarigrond.inflection.generation;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.common.data.dictionary.inflection.InflectionType;
import ch.pledarigrond.inflection.LanguageInflection;
import ch.pledarigrond.inflection.generation.adjective.sursilvan.SursilvanAdjectiveClasses;
import ch.pledarigrond.inflection.generation.adjective.sursilvan.SursilvanAdjectiveGenerator;
import ch.pledarigrond.inflection.generation.noun.sursilvan.SursilvanNounClasses;
import ch.pledarigrond.inflection.generation.noun.sursilvan.SursilvanNounGenerator;
import ch.pledarigrond.inflection.generation.verb.sursilvan.SursilvanConjugation;
import ch.pledarigrond.inflection.generation.verb.sursilvan.SursilvanConjugationClasses;
import ch.pledarigrond.inflection.model.InflectionSubType;

import java.util.List;

public class SursilvanInflection implements LanguageInflection {
    @Override
    public List<InflectionSubType> getInflectionSubtypes(InflectionType inflectionType) {
        switch (inflectionType) {
            case VERB:
                return SursilvanConjugationClasses.getVerbInflectionSubtypes();
            case NOUN:
                return SursilvanNounClasses.getNounInflectionSubtypes();
            case ADJECTIVE:
                return SursilvanAdjectiveClasses.getAdjectiveInflectionSubtypes();
        }

        return null;
    }

    @Override
    public InflectionDto guessInflectionSubtype(InflectionType inflectionType, String baseForm, String genus, String flex) {
        switch (inflectionType) {
            case VERB:
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
    public InflectionDto generateInflection(InflectionType inflectionType, String subTypeId, String baseForm) {
        switch (inflectionType) {
            case VERB:
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
