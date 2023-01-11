package ch.pledarigrond.spellchecker.generator;

import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.inflection.generation.surmiran.SurmiranPronouns;
import ch.pledarigrond.names.entities.Name;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SurmiranSpellcheckerGenerator extends SpellcheckerGenerator{
    public SurmiranSpellcheckerGenerator(PgEnvironment pgEnvironment, List<Name> names) {
        super(pgEnvironment, names);
    }

    @Override
    protected String removePronouns(String value) {
       return WordListUtils.removePronounsSurmiran(value);
    }

    protected String normalizeString(String input) {
        return WordListUtils.normalizeStringSurmiran(input);
    }
}
