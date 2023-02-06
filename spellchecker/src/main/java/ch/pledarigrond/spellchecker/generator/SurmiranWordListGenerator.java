package ch.pledarigrond.spellchecker.generator;

import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.names.entities.Name;

import java.util.HashMap;
import java.util.List;

public class SurmiranWordListGenerator extends WordListGenerator {

    public SurmiranWordListGenerator(PgEnvironment pgEnvironment, List<Name> names) {
        super(pgEnvironment, names);

        posLookupTable = new HashMap<>() {{
            put("adjectiv", new PartOfSpeechTag[] { PartOfSpeechTag.ADJ });
            put("adjectiv / pronom", new PartOfSpeechTag[] { PartOfSpeechTag.ADJ, PartOfSpeechTag.PRON });
            put("adjectiv / adverb", new PartOfSpeechTag[] { PartOfSpeechTag.ADJ, PartOfSpeechTag.ADV });
            put("adverb", new PartOfSpeechTag[] { PartOfSpeechTag.ADV });
            put("adverb / pronom", new PartOfSpeechTag[] { PartOfSpeechTag.ADV, PartOfSpeechTag.PRON });
            put("artetgel", new PartOfSpeechTag[] { PartOfSpeechTag.ART });
            put("artetgel definit", new PartOfSpeechTag[] { PartOfSpeechTag.ART });
            put("artetgel indefinit", new PartOfSpeechTag[] { PartOfSpeechTag.ART });
            put("conjuncziun", new PartOfSpeechTag[] { PartOfSpeechTag.CONJ });
            put("int", new PartOfSpeechTag[] { PartOfSpeechTag.VERB });
            put("int/impersunal", new PartOfSpeechTag[] { PartOfSpeechTag.VERB });
            put("int/tr", new PartOfSpeechTag[] { PartOfSpeechTag.VERB });
            put("interjecziun", new PartOfSpeechTag[] { PartOfSpeechTag.INTERJ });
            put("nomen", new PartOfSpeechTag[] { PartOfSpeechTag.NOUN });
            put("numeral", new PartOfSpeechTag[] { PartOfSpeechTag.NUM_CARDINALS });
            put("preposiziun", new PartOfSpeechTag[] { PartOfSpeechTag.PREP });
            put("preposiziun / adverb", new PartOfSpeechTag[] { PartOfSpeechTag.PREP, PartOfSpeechTag.ADV });
            put("preposiziun / artetgel", new PartOfSpeechTag[] { PartOfSpeechTag.PREP, PartOfSpeechTag.ART });
            put("preposiziun / conjuncziun", new PartOfSpeechTag[] { PartOfSpeechTag.PREP, PartOfSpeechTag.CONJ });
            put("pronom", new PartOfSpeechTag[] { PartOfSpeechTag.PRON });
            put("pronom demonstrativ", new PartOfSpeechTag[] { PartOfSpeechTag.PRON_DEMONSTRATIV });
            put("pronom indefinit", new PartOfSpeechTag[] { PartOfSpeechTag.PRON_INDEFINIT });
            put("pronom interrogativ", new PartOfSpeechTag[] { PartOfSpeechTag.PRON_INTERROGATIV });
            put("pronom persunal", new PartOfSpeechTag[] { PartOfSpeechTag.PRON_PERS });
            put("pronom relativ", new PartOfSpeechTag[] { PartOfSpeechTag.PRON });
            put("reflexiv", new PartOfSpeechTag[] { PartOfSpeechTag.VERB });
            put("tr", new PartOfSpeechTag[] { PartOfSpeechTag.VERB });
            put("tr / int", new PartOfSpeechTag[] { PartOfSpeechTag.VERB });
            put("tr indirect", new PartOfSpeechTag[] { PartOfSpeechTag.VERB });
            put("transitiv indirect", new PartOfSpeechTag[] { PartOfSpeechTag.VERB });
            put("verb", new PartOfSpeechTag[] { PartOfSpeechTag.VERB });
            put("verb modal", new PartOfSpeechTag[] { PartOfSpeechTag.VERB });
            put("verb reflexiv", new PartOfSpeechTag[] { PartOfSpeechTag.VERB });
        }};
    }

    protected String normalizeString(String input) {
        return WordListUtils.normalizeStringSurmiran(input);
    }

    protected String removePronouns(String value) {
        return WordListUtils.removePronounsSurmiran(value);
    }
}
