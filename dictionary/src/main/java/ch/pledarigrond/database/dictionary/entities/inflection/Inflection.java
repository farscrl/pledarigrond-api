package ch.pledarigrond.database.dictionary.entities.inflection;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionType;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class Inflection {
    private InflectionType inflectionType;
    private boolean reviewLater;

    @Nullable private Verb verb;
    @Nullable private Noun noun;
    @Nullable private Adjective adjective;
    @Nullable private Pronoun pronoun;
    @Nullable private Other other;
}
