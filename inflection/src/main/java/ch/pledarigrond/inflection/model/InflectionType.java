package ch.pledarigrond.inflection.model;

public enum InflectionType {
    V("v"), // verb
    NOUN("noun"),
    ADJECTIVE("adjective"),
    PRONOUN("pronoun")
    ;

    private final String name;

    InflectionType(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}
