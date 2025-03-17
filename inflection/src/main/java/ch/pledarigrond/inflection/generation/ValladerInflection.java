package ch.pledarigrond.inflection.generation;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.common.data.dictionary.inflection.InflectionType;
import ch.pledarigrond.inflection.LanguageInflection;
import ch.pledarigrond.inflection.generation.adjective.vallader.ValladerAdjectiveClasses;
import ch.pledarigrond.inflection.generation.adjective.vallader.ValladerAdjectiveGenerator;
import ch.pledarigrond.inflection.generation.noun.vallader.ValladerNounClasses;
import ch.pledarigrond.inflection.generation.noun.vallader.ValladerNounGenerator;
import ch.pledarigrond.inflection.generation.verb.vallader.ValladerConjugation;
import ch.pledarigrond.inflection.generation.verb.vallader.ValladerConjugationClasses;
import ch.pledarigrond.inflection.model.InflectionSubType;

import java.util.List;

public class ValladerInflection implements LanguageInflection {

    @Override
    public List<InflectionSubType> getInflectionSubtypes(InflectionType inflectionType) {
        switch (inflectionType) {
            case VERB:
                return ValladerConjugationClasses.getVerbInflectionSubtypes();
            case NOUN:
                return ValladerNounClasses.getNounInflectionSubtypes();
            case ADJECTIVE:
                return ValladerAdjectiveClasses.getAdjectiveInflectionSubtypes();
        }

        return null;
    }

    @Override
    public InflectionDto guessInflectionSubtype(InflectionType inflectionType, String baseForm, String genus, String flex) {
        switch (inflectionType) {
            case VERB:
                ValladerConjugation conjugation = new ValladerConjugation();
                return conjugation.guessInflection(baseForm, genus, flex);
            case NOUN:
                ValladerNounGenerator nounGenerator = new ValladerNounGenerator();
                return nounGenerator.guessInflection(baseForm, genus, flex);
            case ADJECTIVE:
                ValladerAdjectiveGenerator adjectiveGenerator = new ValladerAdjectiveGenerator();
                return adjectiveGenerator.guessInflection(baseForm, genus, flex);
        }
        return null;
    }

    @Override
    public InflectionDto generateInflection(InflectionType inflectionType, String subTypeId, String baseForm) {
        switch (inflectionType) {
            case VERB:
                ValladerConjugation conjugation = new ValladerConjugation();
                return conjugation.generateConjugation(subTypeId, baseForm);
            case NOUN:
                ValladerNounGenerator nounGenerator = new ValladerNounGenerator();
                return nounGenerator.generateForms(subTypeId, baseForm);
            case ADJECTIVE:
                ValladerAdjectiveGenerator adjectiveGenerator = new ValladerAdjectiveGenerator();
                return adjectiveGenerator.generateForms(subTypeId, baseForm);
        }
        return null;
    }
}
