package ch.pledarigrond.spellchecker.generator.hunspell;

import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.spellchecker.model.HunspellList;

import java.util.List;

public class SursilvanHunspellGenerator extends HunspellGenerator {
    public SursilvanHunspellGenerator(PgEnvironment pgEnvironment, List<String> names) {
        super(Language.SURSILVAN, pgEnvironment, names);
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

    protected void extractOtherForms(HunspellList list, LemmaVersion lemmaVersion) {
        // TODO: implement me
    }

    protected void extractVerbs(HunspellList list, LemmaVersion lemmaVersion) {
        // TODO: implement me
    }

    protected void extractDefault(HunspellList list, LemmaVersion lemmaVersion) {
        // TODO: implement me
    }

    protected void postProcessHunspellList(HunspellList list) {
        // Do nothing
    }
}
