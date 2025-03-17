package ch.pledarigrond.inflection.generation;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.common.data.dictionary.inflection.InflectionType;
import ch.pledarigrond.inflection.LanguageInflection;
import ch.pledarigrond.inflection.generation.adjective.surmiran.SurmiranAdjectiveClasses;
import ch.pledarigrond.inflection.generation.adjective.surmiran.SurmiranAdjectiveGenerator;
import ch.pledarigrond.inflection.generation.noun.surmiran.SurmiranNounClasses;
import ch.pledarigrond.inflection.generation.noun.surmiran.SurmiranNounGenerator;
import ch.pledarigrond.inflection.generation.verb.surmiran.SurmiranConjugation;
import ch.pledarigrond.inflection.generation.verb.surmiran.SurmiranConjugationClasses;
import ch.pledarigrond.inflection.model.InflectionSubType;

import java.util.List;

public class SurmiranInflection implements LanguageInflection {

    @Override
    public List<InflectionSubType> getInflectionSubtypes(InflectionType inflectionType) {
        switch (inflectionType) {
            case VERB:
                return SurmiranConjugationClasses.getVerbInflectionSubtypes();
            case NOUN:
                return SurmiranNounClasses.getNounInflectionSubtypes();
            case ADJECTIVE:
                return SurmiranAdjectiveClasses.getAdjectiveInflectionSubtypes();
        }

        return null;
    }

    @Override
    public InflectionDto guessInflectionSubtype(InflectionType inflectionType, String baseForm, String genus, String flex) {
        switch (inflectionType) {
            case VERB:
                SurmiranConjugation conjugation = new SurmiranConjugation();
                return conjugation.guessInflection(baseForm, genus, flex);
            case NOUN:
                SurmiranNounGenerator nounGenerator = new SurmiranNounGenerator();
                return nounGenerator.guessInflection(baseForm, genus, flex);
            case ADJECTIVE:
                SurmiranAdjectiveGenerator adjectiveGenerator = new SurmiranAdjectiveGenerator();
                return adjectiveGenerator.guessInflection(baseForm, genus, flex);
        }
        return null;
    }

    @Override
    public InflectionDto generateInflection(InflectionType inflectionType, String subTypeId, String baseForm) {
        switch (inflectionType) {
            case VERB:
                SurmiranConjugation conjugation = new SurmiranConjugation();
                return conjugation.generateConjugation(subTypeId, baseForm);
            case NOUN:
                SurmiranNounGenerator nounGenerator = new SurmiranNounGenerator();
                return nounGenerator.generateForms(subTypeId, baseForm);
            case ADJECTIVE:
                SurmiranAdjectiveGenerator adjectiveGenerator = new SurmiranAdjectiveGenerator();
                return adjectiveGenerator.generateForms(subTypeId, baseForm);
        }
        return null;
    }
}
