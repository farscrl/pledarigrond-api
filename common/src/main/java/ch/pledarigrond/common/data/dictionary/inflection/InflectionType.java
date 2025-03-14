package ch.pledarigrond.common.data.dictionary.inflection;

import lombok.Getter;

@Getter
public enum InflectionType {
    NONE("NONE"),
    VERB("V"),
    NOUN("NOUN"),
    ADJECTIVE("ADJECTIVE"),
    PRONOUN("PRONOUN"),
    OTHER("OTHER");

    private final String name;

    InflectionType(String name) {
        this.name = name;
    }
}