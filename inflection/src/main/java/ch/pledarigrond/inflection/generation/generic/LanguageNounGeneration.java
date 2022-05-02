package ch.pledarigrond.inflection.generation.generic;

import ch.pledarigrond.inflection.model.InflectionResponse;
import lombok.Data;

@Data
public abstract class LanguageNounGeneration {

    public abstract InflectionResponse generateForms(String nounClass, String baseForm);

    protected String normalizeString(String query) {

        query = query.toLowerCase();
        query = query.replaceAll("^\\s+|\\s+$", "");

        return query;
    }
}
