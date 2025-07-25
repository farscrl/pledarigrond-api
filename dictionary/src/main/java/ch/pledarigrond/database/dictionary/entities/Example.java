package ch.pledarigrond.database.dictionary.entities;

import lombok.Data;

@Data
public class Example implements Cloneable {
    String rm;
    String de;

    @Override
    public Example clone() {
        try {
            return (Example) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
