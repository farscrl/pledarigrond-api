package ch.pledarigrond.inflection.generation.adjective;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.LanguageInflectionBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class LanguageAdjectiveGeneration extends LanguageInflectionBase {
    public abstract void reset();

    public abstract InflectionDto guessInflection(String baseForm, String genus, String flex);

    public abstract InflectionDto generateForms(String adjectiveClass, String baseForm);
}
