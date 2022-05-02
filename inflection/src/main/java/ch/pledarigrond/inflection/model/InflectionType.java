package ch.pledarigrond.inflection.model;

public enum InflectionType {
    VERB("v"),
    NOUN("noun")
    ;

    private final String name;

    InflectionType(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}
