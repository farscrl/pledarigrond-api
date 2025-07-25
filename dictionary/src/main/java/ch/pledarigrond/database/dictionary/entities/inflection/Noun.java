package ch.pledarigrond.database.dictionary.entities.inflection;

import lombok.Data;

@Data
public class Noun implements Cloneable {
    private String baseForm;
    private boolean irregular;
    private String inflectionSubtype;

    private String mSingular;
    private String fSingular;
    private String mPlural;
    private String fPlural;

    private String pluralCollectiv;

    @Override
    public Noun clone() {
        try {
            return (Noun) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
