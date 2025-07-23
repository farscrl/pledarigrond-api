package ch.pledarigrond.database.dictionary.entities.inflection;

import lombok.Data;

@Data
public class Adjective {
    private String baseForm;
    private boolean irregular;
    private String inflectionSubtype;

    private String mSingular;
    private String fSingular;
    private String mPlural;
    private String fPlural;

    private String adverbialForm;

    private String predicative;
}
