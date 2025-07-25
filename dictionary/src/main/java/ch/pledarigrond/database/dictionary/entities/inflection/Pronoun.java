package ch.pledarigrond.database.dictionary.entities.inflection;

import lombok.Data;

@Data
public class Pronoun implements Cloneable {
    private String baseForm;

    private String mSingular;
    private String fSingular;
    private String mPlural;
    private String fPlural;

    @Override
    public Pronoun clone() {
        try {
            return (Pronoun) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
