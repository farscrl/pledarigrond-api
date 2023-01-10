package ch.pledarigrond.spellchecker.generator;

import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.names.entities.Name;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SurmiranWordListGenerator extends WordListGenerator {

    public SurmiranWordListGenerator(PgEnvironment pgEnvironment, List<Name> names) {
        super(pgEnvironment, names);

        posLookupTable = new HashMap<>() {{
            put("$", PartOfSpeechTag.WTF);
            put("2.", PartOfSpeechTag.WTF);
            put("3.", PartOfSpeechTag.WTF);
            put("I.", PartOfSpeechTag.WTF);
            put("I. Artikel bestimmt", PartOfSpeechTag.ART);
            put("I. Artikel unbestimmt", PartOfSpeechTag.ART);
            put("I. Pronomen demonstrativ", PartOfSpeechTag.PRON_DEMONSTRATIV);
            put("I. adi", PartOfSpeechTag.ADJ);
            put("I. adverb/conjuncziun", PartOfSpeechTag.ADV);
            put("I. adverb/pronom indefinit", PartOfSpeechTag.ADV);
            put("I. artetgel definit", PartOfSpeechTag.ART);
            put("I. num", PartOfSpeechTag.NOUN);
            put("I. num/Pronomen unbestimmt", PartOfSpeechTag.WTF);
            put("II. Verb modal", PartOfSpeechTag.WTF);
            put("II. adj", PartOfSpeechTag.WTF);
            put("II. numeral/pronom", PartOfSpeechTag.WTF);
            put("II. prep", PartOfSpeechTag.PREP);
            put("II. prep/adverb", PartOfSpeechTag.PREP);
            put("II. pronom neutr", PartOfSpeechTag.WTF);
            put("II. pronom persuna / persunal", PartOfSpeechTag.WTF);
            put("II. pronom reflexiv", PartOfSpeechTag.WTF);
            put("II. pronom relativ", PartOfSpeechTag.WTF);
            put("II. tr indirect", PartOfSpeechTag.WTF);
            put("III.", PartOfSpeechTag.WTF);
            put("III. Pronomen unbestimmt", PartOfSpeechTag.WTF);
            put("III. Pronomen/num", PartOfSpeechTag.WTF);
            put("III. adv", PartOfSpeechTag.WTF);
            put("III. conjuncziun", PartOfSpeechTag.WTF);
            put("III. impersunal", PartOfSpeechTag.WTF);
            put("III. int/tr", PartOfSpeechTag.WTF);
            put("III. interjecziun", PartOfSpeechTag.WTF);
            put("III. pronom demonstrativ", PartOfSpeechTag.WTF);
            put("IV.", PartOfSpeechTag.WTF);
            put("IV. Interjektion", PartOfSpeechTag.WTF);
            put("IV. unpersönlich", PartOfSpeechTag.WTF);
            put("Interjektion/adv", PartOfSpeechTag.WTF);
            put("Konjunktion", PartOfSpeechTag.CONJ);
            put("Pronomen interrogativ", PartOfSpeechTag.PRON_INTERROGATIV);
            put("Pronomen neutr", PartOfSpeechTag.PRON);
            put("Pronomen possessiv", PartOfSpeechTag.PRON);
            put("Pronomen rcp", PartOfSpeechTag.PRON);
            put("Pronomen/adj", PartOfSpeechTag.PRON);
            put("Präposition", PartOfSpeechTag.PREP);
            put("Präposition/Konjunktion", PartOfSpeechTag.PREP);
            put("Präposition/adv", PartOfSpeechTag.PREP);
            put("V.", PartOfSpeechTag.WTF);
            put("`", PartOfSpeechTag.WTF);
            put("adi", PartOfSpeechTag.ADJ);
            put("adj", PartOfSpeechTag.ADJ);
            put("adj numeral", PartOfSpeechTag.ADJ);
            put("adj/Pronomen", PartOfSpeechTag.ADJ);
            put("adj/adv", PartOfSpeechTag.ADJ);
            put("adj/adverb", PartOfSpeechTag.ADJ);
            put("adj/num", PartOfSpeechTag.ADJ);
            put("adj/numeral", PartOfSpeechTag.ADJ);
            put("adj/pronom indefinit", PartOfSpeechTag.ADJ);
            put("adj/pronom interrogativ", PartOfSpeechTag.ADJ);
            put("adv", PartOfSpeechTag.ADJ);
            put("adv Pronomen/Konjunktion", PartOfSpeechTag.ADJ);
            put("adv relativ", PartOfSpeechTag.ADJ);
            put("adv/Interjektion", PartOfSpeechTag.ADJ);
            put("adv/Konjunktion", PartOfSpeechTag.ADJ);
            put("adv/Präposition", PartOfSpeechTag.ADJ);
            put("adverb", PartOfSpeechTag.ADV);
            put("adverb/prep", PartOfSpeechTag.ADV);
            put("cj", PartOfSpeechTag.CONJ);
            put("int", PartOfSpeechTag.INTERJ);
            put("int/impersunal", PartOfSpeechTag.VERB);
            put("int/reflexiv", PartOfSpeechTag.VERB);
            put("int/tr", PartOfSpeechTag.VERB);
            put("int/unpersönlich", PartOfSpeechTag.VERB);
            put("interj", PartOfSpeechTag.INTERJ);
            put("num", PartOfSpeechTag.NUM_CARDINALS);
            put("num ord", PartOfSpeechTag.NUM_ORDINALS);
            put("part perfect", PartOfSpeechTag.WTF);
            put("part perfect/adj", PartOfSpeechTag.WTF);
            put("prefix", PartOfSpeechTag.WTF);
            put("prep", PartOfSpeechTag.PREP);
            put("prep+artetgel", PartOfSpeechTag.PREP);
            put("prep/conjuncziun", PartOfSpeechTag.PREP);
            put("pron", PartOfSpeechTag.PRON);
            put("pron Person / personal", PartOfSpeechTag.PRON_PERS);
            put("pron reflexiv", PartOfSpeechTag.PRON);
            put("pronom persuna / persunal 3a pl cumplemaint indirect", PartOfSpeechTag.PRON);
            put("pronom persuna / persunal pl", PartOfSpeechTag.PRON_PERS);
            put("pronom possessiv", PartOfSpeechTag.PRON);
            put("refl", PartOfSpeechTag.VERB);
            put("subst", PartOfSpeechTag.NOUN);
            put("tr", PartOfSpeechTag.VERB);
            put("tr indirect/int", PartOfSpeechTag.VERB);
            put("tr/impersunal", PartOfSpeechTag.VERB);
            put("tr/int", PartOfSpeechTag.VERB);
            put("tr/int/Verb modal", PartOfSpeechTag.VERB);
            put("tr/tr indirect", PartOfSpeechTag.VERB);
            put("tr/verb modal", PartOfSpeechTag.VERB);
        }};
    }

    protected String normalizeString(String input) {
        return WordListUtils.normalizeStringSurmiran(input);
    }
}
