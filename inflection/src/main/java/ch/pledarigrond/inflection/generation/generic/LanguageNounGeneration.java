package ch.pledarigrond.inflection.generation.generic;

import ch.pledarigrond.inflection.model.InflectionResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class LanguageNounGeneration extends LanguageInflectionBase {

    public abstract InflectionResponse guessInflection(String baseForm, String genus, String flex);

    public abstract InflectionResponse generateForms(String nounClass, String baseForm);

    protected String normalizeString(String query) {
        query = query.replaceAll("^\\s+|\\s+$", "");

        return query;
    }
}
