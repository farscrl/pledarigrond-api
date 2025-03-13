package ch.pledarigrond.common.data.common.inflection;

import lombok.Data;

@Data
public class Noun {
    public String baseForm;

    public String mSingular;
    public String fSingular;
    public String mPlural;
    public String fPlural;

    public String pluralCollectiv;
}
