package ch.pledarigrond.database.dictionary.entities.inflection;

import lombok.Data;

@Data
public class Adjective implements Cloneable {
    private String baseForm;
    private boolean irregular;
    private String inflectionSubtype;

    private String mSingular;
    private String fSingular;
    private String mPlural;
    private String fPlural;

    private String adverbialForm;

    private String predicative;

    @Override
    public Adjective clone() {
        try {
            return (Adjective) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
