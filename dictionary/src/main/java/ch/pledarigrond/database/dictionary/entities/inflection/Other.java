package ch.pledarigrond.database.dictionary.entities.inflection;

import lombok.Data;

@Data
public class Other implements Cloneable {
    public String baseForm;

    public String otherForm1;
    public String otherForm2;
    public String otherForm3;
    public String otherForm4;

    @Override
    public Other clone() {
        try {
            return (Other) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
