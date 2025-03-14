package ch.pledarigrond.dictionary.entities.inflection;

import lombok.Data;

@Data
public class Adjective {
    public String baseForm;

    public String mSingular;
    public String fSingular;
    public String mPlural;
    public String fPlural;

    public String adverbialForm;
}
