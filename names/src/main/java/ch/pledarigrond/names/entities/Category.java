package ch.pledarigrond.names.entities;

import java.util.Arrays;

public enum Category {
    GEOGRAPHY("GEOGRAPHY"),
    FIRSTNAME("FIRSTNAME"),
    SURNAME("SURNAME"),
    COMPANY("COMPANY"),
    ABBREVIATION("ABBREVIATION"),
    OTHER("OTHER");

    private final String value;

    private Category(String value) {
        this.value = value;
    }

    public static Category findCategory(final String cat){
        return Arrays.stream(values()).filter(value -> value.value.equals(cat)).findFirst().orElse(null);
    }
}
