package ch.pledarigrond.inflection.generation.vallader;

import ch.pledarigrond.inflection.generation.generic.LanguageInflection;
import ch.pledarigrond.inflection.model.InflectionResponse;
import ch.pledarigrond.inflection.model.InflectionSubType;
import ch.pledarigrond.inflection.model.InflectionType;

import java.util.List;

public class ValladerInflection implements LanguageInflection {

    @Override
    public List<InflectionSubType> getInflectionSubtypes(InflectionType inflectionType) {
        switch (inflectionType) {
            case V:
                return ValladerConjugationClasses.getVerbInflectionSubtypes();
            case NOUN:
                return ValladerNounClasses.getNounInflectionSubtypes();
            /*case ADJECTIVE:
                return PuterAdjectiveClasses.getAdjectiveInflectionSubtypes();*/
        }

        return null;
    }

    @Override
    public InflectionResponse guessInflectionSubtype(InflectionType inflectionType, String baseForm, String genus, String flex) {
        switch (inflectionType) {
            case V:
                ValladerConjugation conjugation = new ValladerConjugation();
                return conjugation.guessInflection(baseForm, genus, flex);
            case NOUN:
                ValladerNounGenerator nounGenerator = new ValladerNounGenerator();
                return nounGenerator.guessInflection(baseForm, genus, flex);
            /*case ADJECTIVE:
                PuterAdjectiveGenerator adjectiveGenerator = new PuterAdjectiveGenerator();
                return adjectiveGenerator.guessInflection(baseForm, genus, flex);*/
        }
        return null;
    }

    @Override
    public InflectionResponse generateInflection(InflectionType inflectionType, String subTypeId, String baseForm) {
        switch (inflectionType) {
            case V:
                ValladerConjugation conjugation = new ValladerConjugation();
                return conjugation.generateConjugation(subTypeId, baseForm);
            case NOUN:
                ValladerNounGenerator nounGenerator = new ValladerNounGenerator();
                return nounGenerator.generateForms(subTypeId, baseForm);
            /*case ADJECTIVE:
                PuterAdjectiveGenerator adjectiveGenerator = new PuterAdjectiveGenerator();
                return adjectiveGenerator.generateForms(subTypeId, baseForm);*/
        }
        return null;
    }
}
