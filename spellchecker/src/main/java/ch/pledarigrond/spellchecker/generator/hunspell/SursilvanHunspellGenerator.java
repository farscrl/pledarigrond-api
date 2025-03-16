package ch.pledarigrond.spellchecker.generator.hunspell;

import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.dictionary.EntryVersionDto;
import ch.pledarigrond.spellchecker.model.HunspellList;

import java.io.IOException;
import java.util.List;

public class SursilvanHunspellGenerator extends HunspellGenerator {
    public SursilvanHunspellGenerator(PgEnvironment pgEnvironment, List<String> names) throws IOException {
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

    protected void extractNouns(HunspellList list, EntryVersionDto dto) {
        // TODO: implement me
    }

    protected void extractAdjectives(HunspellList list, EntryVersionDto dto) {
        // TODO: implement me
    }

    protected void extractPronouns(HunspellList list, EntryVersionDto dto) {
        // TODO: implement me
    }

    protected void extractOtherForms(HunspellList list, EntryVersionDto dto) {
        // TODO: implement me
    }

    protected void extractVerbs(HunspellList list, EntryVersionDto dto) {
        // TODO: implement me
    }

    protected void extractDefault(HunspellList list, EntryVersionDto dto) {
        // TODO: implement me
    }

    protected void postProcessHunspellList(HunspellList list) {
        // Do nothing
    }
}
