package ch.pledarigrond.inflection.generation.generic;

import ch.pledarigrond.inflection.model.InflectionResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class LanguageAdjectiveGeneration extends LanguageInflectionBase {

    public abstract InflectionResponse guessInflection(String baseForm, String genus, String flex);

    public abstract InflectionResponse generateForms(String adjectiveClass, String baseForm);
}
