package ch.pledarigrond.spellchecker.generator.pos;

import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.LemmaVersion;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class PuterPartOfSpeechListGenerator extends PartOfSpeechListGenerator {

    public PuterPartOfSpeechListGenerator(PgEnvironment pgEnvironment, List<String> names) {
        super(pgEnvironment, names);

        posLookupTable = new HashMap<>() {{
            // TODO: implement me
        }};
    }

    @Override
    protected String normalizeString(String input) {
        // TODO: implement me
        return input;
    }

    @Override
    protected String removePronouns(String value) {
        // TODO: implement me
        return value;
    }

    @Override
    protected void extractNouns(Set<String> baseForms, Set<String> inflections, LemmaVersion lemmaVersion, String RStichwort) {
        // TODO: implement me
    }


    @Override
    protected void extractAdjectives(Set<String> adjectiveBaseForms, Set<String> adjectiveInflections, Set<String> adverbialBaseForms, Set<String> adverbialInflections, LemmaVersion lemmaVersion, String RStichwort) {
        // TODO: implement me
    }

    @Override
    protected void extractPronouns(Set<String> baseForms, Set<String> inflections, LemmaVersion lemmaVersion, String RStichwort) {
        // TODO: implement me
    }

    @Override
    protected void extractVerbs(Set<String> baseForms, Set<String> inflections, LemmaVersion lemmaVersion, String RStichwort) {
        // TODO: implement me
    }

    @Override
    protected void extractDefault(Set<String> baseForms, Set<String> inflections, LemmaVersion lemmaVersion, String RStichwort) {
        // TODO: implement me
    }
}
