package ch.pledarigrond.database.dictionary.entities.inflection;

import lombok.Data;

@Data
public class Noun {
    private String baseForm;
    private boolean irregular;

    private String mSingular;
    private String fSingular;
    private String mPlural;
    private String fPlural;

    private String pluralCollectiv;
}
