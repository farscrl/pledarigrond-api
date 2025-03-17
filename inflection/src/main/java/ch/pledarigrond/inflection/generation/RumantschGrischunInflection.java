package ch.pledarigrond.inflection.generation;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.common.data.dictionary.inflection.InflectionType;
import ch.pledarigrond.inflection.LanguageInflection;
import ch.pledarigrond.inflection.generation.adjective.rumantschgrischun.RumantschGrischunAdjectiveClasses;
import ch.pledarigrond.inflection.generation.adjective.rumantschgrischun.RumantschGrischunAdjectiveGenerator;
import ch.pledarigrond.inflection.generation.noun.rumantschgrischun.RumantschGrischunNounClasses;
import ch.pledarigrond.inflection.generation.noun.rumantschgrischun.RumantschGrischunNounGenerator;
import ch.pledarigrond.inflection.generation.verb.rumantschgrischun.RumantschGrischunConjugation;
import ch.pledarigrond.inflection.generation.verb.rumantschgrischun.RumantschGrischunConjugationClasses;
import ch.pledarigrond.inflection.model.InflectionSubType;

import java.util.List;

public class RumantschGrischunInflection  implements LanguageInflection {
    @Override
    public List<InflectionSubType> getInflectionSubtypes(InflectionType inflectionType) {
        switch (inflectionType) {
            case VERB:
                return RumantschGrischunConjugationClasses.getVerbInflectionSubtypes();
            case NOUN:
                return RumantschGrischunNounClasses.getNounInflectionSubtypes();
            case ADJECTIVE:
                return RumantschGrischunAdjectiveClasses.getAdjectiveInflectionSubtypes();
        }

        return null;
    }

    @Override
    public InflectionDto guessInflectionSubtype(InflectionType inflectionType, String baseForm, String genus, String flex) {
        switch (inflectionType) {
            case VERB:
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
    public InflectionDto generateInflection(InflectionType inflectionType, String subTypeId, String baseForm) {
        switch (inflectionType) {
            case VERB:
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
