package ch.pledarigrond.spellchecker.generator.hunspell;

import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.names.entities.Name;
import ch.pledarigrond.spellchecker.model.HunspellList;

import java.util.List;

public class ValladerHunspellGenerator extends HunspellGenerator {
    public ValladerHunspellGenerator(PgEnvironment pgEnvironment, List<Name> names) {
        super(pgEnvironment, names);
    }

    @Override
    protected String removePronouns(String value) {
        // TODO: implement me
        return value;
    }

    protected String normalizeString(String input) {
        // TODO: implement me
        return input;
    }

    protected void extractNouns(HunspellList list, LemmaVersion lemmaVersion) {
        // TODO: implement me
    }

    protected void extractAdjectives(HunspellList list, LemmaVersion lemmaVersion) {
        // TODO: implement me
    }

    protected void extractPronouns(HunspellList list, LemmaVersion lemmaVersion) {
        // TODO: implement me
    }

    protected void extractVerbs(Language language, HunspellList list, LemmaVersion lemmaVersion) {
        // TODO: implement me
    }

    protected void extractDefault(HunspellList list, LemmaVersion lemmaVersion) {
        // TODO: implement me
    }
}
