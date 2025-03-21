package ch.pledarigrond.database.dictionary.entities.inflection;

import lombok.Data;

@Data
public class Pronoun {
    private String baseForm;

    private String mSingular;
    private String fSingular;
    private String mPlural;
    private String fPlural;
}
