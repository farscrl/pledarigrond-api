package ch.pledarigrond.common.data.common.inflection;

import lombok.Data;

@Data
public class Pronoun {
    public String baseForm;

    public String mSingular;
    public String fSingular;
    public String mPlural;
    public String fPlural;
}
