package ch.pledarigrond.inflection.generation;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.common.data.dictionary.inflection.InflectionType;
import ch.pledarigrond.inflection.LanguageInflection;
import ch.pledarigrond.inflection.generation.adjective.puter.PuterAdjectiveClasses;
import ch.pledarigrond.inflection.generation.adjective.puter.PuterAdjectiveGenerator;
import ch.pledarigrond.inflection.generation.noun.puter.PuterNounClasses;
import ch.pledarigrond.inflection.generation.noun.puter.PuterNounGenerator;
import ch.pledarigrond.inflection.generation.verb.puter.PuterConjugation;
import ch.pledarigrond.inflection.generation.verb.puter.PuterConjugationClasses;
import ch.pledarigrond.inflection.model.InflectionSubType;

import java.util.List;

public class PuterInflection implements LanguageInflection {

    @Override
    public List<InflectionSubType> getInflectionSubtypes(InflectionType inflectionType) {
        switch (inflectionType) {
            case VERB:
                return PuterConjugationClasses.getVerbInflectionSubtypes();
            case NOUN:
                return PuterNounClasses.getNounInflectionSubtypes();
            case ADJECTIVE:
                return PuterAdjectiveClasses.getAdjectiveInflectionSubtypes();
        }

        return null;
    }

    @Override
    public InflectionDto guessInflectionSubtype(InflectionType inflectionType, String baseForm, String genus, String flex) {
        switch (inflectionType) {
            case VERB:
                PuterConjugation conjugation = new PuterConjugation();
                return conjugation.guessInflection(baseForm, genus, flex);
            case NOUN:
                PuterNounGenerator nounGenerator = new PuterNounGenerator();
                return nounGenerator.guessInflection(baseForm, genus, flex);
            case ADJECTIVE:
                PuterAdjectiveGenerator adjectiveGenerator = new PuterAdjectiveGenerator();
                return adjectiveGenerator.guessInflection(baseForm, genus, flex);
        }
        return null;
    }

    @Override
    public InflectionDto generateInflection(InflectionType inflectionType, String subTypeId, String baseForm) {
        switch (inflectionType) {
            case VERB:
                PuterConjugation conjugation = new PuterConjugation();
                return conjugation.generateConjugation(subTypeId, baseForm);
            case NOUN:
                PuterNounGenerator nounGenerator = new PuterNounGenerator();
                return nounGenerator.generateForms(subTypeId, baseForm);
            case ADJECTIVE:
                PuterAdjectiveGenerator adjectiveGenerator = new PuterAdjectiveGenerator();
                return adjectiveGenerator.generateForms(subTypeId, baseForm);
        }
        return null;
    }
}
