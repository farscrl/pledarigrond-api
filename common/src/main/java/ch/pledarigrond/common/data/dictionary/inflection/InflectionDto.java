package ch.pledarigrond.common.data.dictionary.inflection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InflectionDto {
    private InflectionType inflectionType;
    private boolean reviewLater;

    private VerbDto verb;
    private NounDto noun;
    private AdjectiveDto adjective;
    private PronounDto pronoun;
    private OtherDto other;
}

