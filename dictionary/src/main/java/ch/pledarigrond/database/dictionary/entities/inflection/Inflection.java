package ch.pledarigrond.database.dictionary.entities.inflection;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionType;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class Inflection implements Cloneable {
    private InflectionType inflectionType;
    private boolean reviewLater;

    @Nullable private Verb verb;
    @Nullable private Noun noun;
    @Nullable private Adjective adjective;
    @Nullable private Pronoun pronoun;
    @Nullable private Other other;

    @Override
    public Inflection clone() {
        try {
            Inflection cloned = (Inflection) super.clone();
            if (this.verb != null) cloned.verb = this.verb.clone();
            if (this.noun != null) cloned.noun = this.noun.clone();
            if (this.adjective != null) cloned.adjective = this.adjective.clone();
            if (this.pronoun != null) cloned.pronoun = this.pronoun.clone();
            if (this.other != null) cloned.other = this.other.clone();
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
