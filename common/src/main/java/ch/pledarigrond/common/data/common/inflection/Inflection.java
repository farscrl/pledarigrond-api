package ch.pledarigrond.common.data.common.inflection;

import lombok.Getter;
import org.springframework.lang.Nullable;

public class Inflection {
    private InflectionType inflectionType;
    private String inflectionSubtype;

    @Nullable private Verb verb;
    @Nullable private Noun noun;
    @Nullable private Adjective adjective;
    @Nullable private Pronoun pronoun;
    @Nullable private Other other;

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
}
