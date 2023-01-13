package ch.pledarigrond.spellchecker.generator;

import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.names.entities.Name;

import java.util.HashMap;
import java.util.List;

public class SurmiranWordListGenerator extends WordListGenerator {

    public SurmiranWordListGenerator(PgEnvironment pgEnvironment, List<Name> names) {
        super(pgEnvironment, names);

        posLookupTable = new HashMap<>() {{
            put("I.", new PartOfSpeechTag[] { PartOfSpeechTag.WTF });
            put("I. Artikel bestimmt", new PartOfSpeechTag[] { PartOfSpeechTag.ART });
            put("I. Artikel unbestimmt", new PartOfSpeechTag[] { PartOfSpeechTag.ART });
            put("I. Pronomen demonstrativ", new PartOfSpeechTag[] { PartOfSpeechTag.PRON_DEMONSTRATIV });
            put("I. adverb", new PartOfSpeechTag[] { PartOfSpeechTag.ADV });
            put("I. adverb interrogativ", new PartOfSpeechTag[] { PartOfSpeechTag.ADV });
            put("I. artetgel", new PartOfSpeechTag[] { PartOfSpeechTag.ART });
            put("I. artetgel definit", new PartOfSpeechTag[] { PartOfSpeechTag.ART });
            put("I. artetgel indefinit", new PartOfSpeechTag[] { PartOfSpeechTag.ART });
            put("I. conjuncziun", new PartOfSpeechTag[] { PartOfSpeechTag.CONJ });
            put("I. impersunal", new PartOfSpeechTag[] { PartOfSpeechTag.PRON });
            put("I. interjecziun", new PartOfSpeechTag[] { PartOfSpeechTag.INTERJ });
            put("I. prep", new PartOfSpeechTag[] { PartOfSpeechTag.PREP });
            put("I. prep/adverb", new PartOfSpeechTag[] { PartOfSpeechTag.PREP, PartOfSpeechTag.ADV });
            put("I. pronom", new PartOfSpeechTag[] { PartOfSpeechTag.PRON });
            put("I. pronom demonstrativ", new PartOfSpeechTag[] { PartOfSpeechTag.PRON_DEMONSTRATIV });
            put("I. pronom demonstrativ impersunal", new PartOfSpeechTag[] { PartOfSpeechTag.PRON_DEMONSTRATIV });
            put("I. pronom demonstrativ neutr", new PartOfSpeechTag[] { PartOfSpeechTag.PRON_DEMONSTRATIV });
            put("I. pronom indefinit", new PartOfSpeechTag[] { PartOfSpeechTag.PRON_INDEFINIT });
            put("I. pronom interrogativ", new PartOfSpeechTag[] { PartOfSpeechTag.PRON_INTERROGATIV });
            put("I. pronom neutr", new PartOfSpeechTag[] { PartOfSpeechTag.PRON });
            put("I. pronom numeral", new PartOfSpeechTag[] { PartOfSpeechTag.WTF });
            put("I. pronom persuna / persunal", new PartOfSpeechTag[] { PartOfSpeechTag.PRON_PERS });
            put("II.", new PartOfSpeechTag[] { PartOfSpeechTag.WTF });
            put("II. Verb modal", new PartOfSpeechTag[] { PartOfSpeechTag.VERB });
            put("II. adj", new PartOfSpeechTag[] { PartOfSpeechTag.ADJ });
            put("II. impersunal", new PartOfSpeechTag[] { PartOfSpeechTag.PRON });
            put("II. interjecziun", new PartOfSpeechTag[] { PartOfSpeechTag.INTERJ });
            put("II. numeral", new PartOfSpeechTag[] { PartOfSpeechTag.NUM_CARDINALS });
            put("II. numeral/pronom", new PartOfSpeechTag[] { PartOfSpeechTag.PRON });
            put("II. prep", new PartOfSpeechTag[] { PartOfSpeechTag.PREP });
            put("II. prep/adverb", new PartOfSpeechTag[] { PartOfSpeechTag.PREP, PartOfSpeechTag.ADV });
            put("II. pronom demonstrativ", new PartOfSpeechTag[] { PartOfSpeechTag.PRON_DEMONSTRATIV });
            put("II. pronom indefinit", new PartOfSpeechTag[] { PartOfSpeechTag.PRON_INDEFINIT });
            put("II. pronom interrogativ", new PartOfSpeechTag[] { PartOfSpeechTag.PRON_INTERROGATIV });
            put("II. pronom possessiv", new PartOfSpeechTag[] { PartOfSpeechTag.PRON });
            put("II. pronom neutr", new PartOfSpeechTag[] { PartOfSpeechTag.PRON });
            put("II. pronom persuna / persunal", new PartOfSpeechTag[] { PartOfSpeechTag.PRON });
            put("II. pronom reflexiv", new PartOfSpeechTag[] { PartOfSpeechTag.PRON });
            put("II. pronom relativ", new PartOfSpeechTag[] { PartOfSpeechTag.PRON_RELATIV });
            put("II.  reflexiv", new PartOfSpeechTag[] { PartOfSpeechTag.VERB });
            put("II. tr", new PartOfSpeechTag[] { PartOfSpeechTag.VERB });
            put("II. tr indirect", new PartOfSpeechTag[] { PartOfSpeechTag.VERB });
            put("III.", new PartOfSpeechTag[] { PartOfSpeechTag.WTF });
            put("III. adj", new PartOfSpeechTag[] { PartOfSpeechTag.ADJ });
            put("III. Pronomen unbestimmt", new PartOfSpeechTag[] { PartOfSpeechTag.PRON });
            put("III. Pronomen/num", new PartOfSpeechTag[] { PartOfSpeechTag.PRON });
            put("III. adv", new PartOfSpeechTag[] { PartOfSpeechTag.ADV });
            put("III. adverb", new PartOfSpeechTag[] { PartOfSpeechTag.ADV });
            put("III. adverb pronom", new PartOfSpeechTag[] { PartOfSpeechTag.ADV, PartOfSpeechTag.PRON });
            put("III. conjuncziun", new PartOfSpeechTag[] { PartOfSpeechTag.CONJ });
            put("III. impersunal", new PartOfSpeechTag[] { PartOfSpeechTag.PRON });
            put("III. int", new PartOfSpeechTag[] { PartOfSpeechTag.VERB });
            put("III. int/tr", new PartOfSpeechTag[] { PartOfSpeechTag.VERB });
            put("III. interjecziun", new PartOfSpeechTag[] { PartOfSpeechTag.INTERJ });
            put("III. prep", new PartOfSpeechTag[] { PartOfSpeechTag.PREP });
            put("III. prep/conjuncziun", new PartOfSpeechTag[] { PartOfSpeechTag.PREP, PartOfSpeechTag.CONJ });
            put("III. pronom demonstrativ", new PartOfSpeechTag[] { PartOfSpeechTag.PRON_DEMONSTRATIV });
            put("III. pronom indefinit", new PartOfSpeechTag[] { PartOfSpeechTag.PRON_INDEFINIT });
            put("III. pronom neutr", new PartOfSpeechTag[] { PartOfSpeechTag.PRON });
            put("III. pronom reflexiv", new PartOfSpeechTag[] { PartOfSpeechTag.PRON });
            put("III. pronom relativ", new PartOfSpeechTag[] { PartOfSpeechTag.PRON_RELATIV });
            put("III. tr indirect", new PartOfSpeechTag[] { PartOfSpeechTag.VERB });
            put("IV.", new PartOfSpeechTag[] { PartOfSpeechTag.WTF });
            put("IV. adverb", new PartOfSpeechTag[] { PartOfSpeechTag.ADV });
            put("IV. conjuncziun", new PartOfSpeechTag[] { PartOfSpeechTag.CONJ });
            put("IV. Interjektion", new PartOfSpeechTag[] { PartOfSpeechTag.INTERJ });
            put("IV. unpersönlich", new PartOfSpeechTag[] { PartOfSpeechTag.PRON });
            put("V. interjecziun", new PartOfSpeechTag[] { PartOfSpeechTag.INTERJ });
            put("Konjunktion", new PartOfSpeechTag[] { PartOfSpeechTag.CONJ });
            put("Pronomen interrogativ", new PartOfSpeechTag[] { PartOfSpeechTag.PRON_INTERROGATIV });
            put("Pronomen neutr", new PartOfSpeechTag[] { PartOfSpeechTag.PRON });
            put("Pronomen possessiv", new PartOfSpeechTag[] { PartOfSpeechTag.PRON });
            put("Pronomen/adj", new PartOfSpeechTag[] { PartOfSpeechTag.PRON, PartOfSpeechTag.ADJ });
            put("Präposition", new PartOfSpeechTag[] { PartOfSpeechTag.PREP });
            put("adj", new PartOfSpeechTag[] { PartOfSpeechTag.ADJ });
            put("adj demonstrativ", new PartOfSpeechTag[] { PartOfSpeechTag.ADJ });
            put("adj indefinit", new PartOfSpeechTag[] { PartOfSpeechTag.ADJ });
            put("adj interrogativ", new PartOfSpeechTag[] { PartOfSpeechTag.ADJ });
            put("adj numeral", new PartOfSpeechTag[] { PartOfSpeechTag.ADJ });
            put("adj possessiv", new PartOfSpeechTag[] { PartOfSpeechTag.ADJ });
            put("adj/pronom", new PartOfSpeechTag[] { PartOfSpeechTag.ADJ, PartOfSpeechTag.PRON });
            put("adj/Pronomen", new PartOfSpeechTag[] { PartOfSpeechTag.ADJ, PartOfSpeechTag.PRON });
            put("adj/adv", new PartOfSpeechTag[] { PartOfSpeechTag.ADJ, PartOfSpeechTag.ADV });
            put("adj/adverb", new PartOfSpeechTag[] { PartOfSpeechTag.ADJ, PartOfSpeechTag.ADV });
            put("adj/num", new PartOfSpeechTag[] { PartOfSpeechTag.ADJ });
            put("adj/numeral", new PartOfSpeechTag[] { PartOfSpeechTag.ADJ });
            put("adj/pronom indefinit", new PartOfSpeechTag[] { PartOfSpeechTag.ADJ });
            put("adj/pronom interrogativ", new PartOfSpeechTag[] { PartOfSpeechTag.PRON_INTERROGATIV });
            put("adv", new PartOfSpeechTag[] { PartOfSpeechTag.ADV });
            put("adverb pronom", new PartOfSpeechTag[] { PartOfSpeechTag.ADV });
            put("adv relativ", new PartOfSpeechTag[] { PartOfSpeechTag.ADJ });
            put("adverb/conjuncziun", new PartOfSpeechTag[] { PartOfSpeechTag.ADV, PartOfSpeechTag.CONJ });
            put("adv/Konjunktion", new PartOfSpeechTag[] { PartOfSpeechTag.ADV });
            put("adv/Präposition", new PartOfSpeechTag[] { PartOfSpeechTag.ADV, PartOfSpeechTag.PREP });
            put("adverb", new PartOfSpeechTag[] { PartOfSpeechTag.ADV });
            put("adverb/prep", new PartOfSpeechTag[] { PartOfSpeechTag.ADV, PartOfSpeechTag.PREP });
            put("cj", new PartOfSpeechTag[] { PartOfSpeechTag.CONJ });
            put("conjuncziun", new PartOfSpeechTag[] { PartOfSpeechTag.CONJ });
            put("impersunal", new PartOfSpeechTag[] { PartOfSpeechTag.VERB_DEFECTIV });
            put("int", new PartOfSpeechTag[] { PartOfSpeechTag.VERB });
            put("int/impersunal", new PartOfSpeechTag[] { PartOfSpeechTag.VERB });
            put("int/reflexiv", new PartOfSpeechTag[] { PartOfSpeechTag.VERB });
            put("int/tr", new PartOfSpeechTag[] { PartOfSpeechTag.VERB });
            put("int/unpersönlich", new PartOfSpeechTag[] { PartOfSpeechTag.VERB });
            put("interj", new PartOfSpeechTag[] { PartOfSpeechTag.INTERJ });
            put("interjecziun", new PartOfSpeechTag[] { PartOfSpeechTag.INTERJ });
            put("num", new PartOfSpeechTag[] { PartOfSpeechTag.NUM_CARDINALS });
            put("numeral", new PartOfSpeechTag[] { PartOfSpeechTag.NUM_CARDINALS });
            put("num ord", new PartOfSpeechTag[] { PartOfSpeechTag.NUM_ORDINALS });
            put("part perfect/adj", new PartOfSpeechTag[] { PartOfSpeechTag.WTF });
            put("prep", new PartOfSpeechTag[] { PartOfSpeechTag.PREP });
            put("prep+artetgel", new PartOfSpeechTag[] { PartOfSpeechTag.PREP, PartOfSpeechTag.ART });
            put("prep/conjuncziun", new PartOfSpeechTag[] { PartOfSpeechTag.PREP, PartOfSpeechTag.CONJ });
            put("pron", new PartOfSpeechTag[] { PartOfSpeechTag.PRON });
            put("pronom", new PartOfSpeechTag[] { PartOfSpeechTag.PRON });
            put("pronom demonstrativ", new PartOfSpeechTag[] { PartOfSpeechTag.PRON_DEMONSTRATIV });
            put("pronom indefinit", new PartOfSpeechTag[] { PartOfSpeechTag.PRON_INDEFINIT });
            put("pronom interrogativ", new PartOfSpeechTag[] { PartOfSpeechTag.PRON_INTERROGATIV });
            put("pronom neutr", new PartOfSpeechTag[] { PartOfSpeechTag.PRON });
            put("pronom persuna / persunal", new PartOfSpeechTag[] { PartOfSpeechTag.PRON_PERS });
            put("pronom persuna / persunal indefinit", new PartOfSpeechTag[] { PartOfSpeechTag.PRON_PERS });
            put("pron Person / personal", new PartOfSpeechTag[] { PartOfSpeechTag.PRON_PERS });
            put("pronom reflexiv", new PartOfSpeechTag[] { PartOfSpeechTag.PRON });
            put("pron reflexiv", new PartOfSpeechTag[] { PartOfSpeechTag.PRON });
            put("pronom persuna / persunal 3a pl cumplemaint indirect", new PartOfSpeechTag[] { PartOfSpeechTag.PRON });
            put("pronom persuna / persunal pl", new PartOfSpeechTag[] { PartOfSpeechTag.PRON_PERS });
            put("pronom possessiv", new PartOfSpeechTag[] { PartOfSpeechTag.PRON });
            put("reflexiv", new PartOfSpeechTag[] { PartOfSpeechTag.VERB });
            put("refl", new PartOfSpeechTag[] { PartOfSpeechTag.VERB });
            put("subst", new PartOfSpeechTag[] { PartOfSpeechTag.NOUN });
            put("tr", new PartOfSpeechTag[] { PartOfSpeechTag.VERB });
            put("tr indirect", new PartOfSpeechTag[] { PartOfSpeechTag.VERB });
            put("tr indirect/int", new PartOfSpeechTag[] { PartOfSpeechTag.VERB });
            put("tr/impersunal", new PartOfSpeechTag[] { PartOfSpeechTag.VERB });
            put("tr/int", new PartOfSpeechTag[] { PartOfSpeechTag.VERB });
            put("tr/int/Verb modal", new PartOfSpeechTag[] { PartOfSpeechTag.VERB });
            put("tr/tr indirect", new PartOfSpeechTag[] { PartOfSpeechTag.VERB });
            put("tr/verb modal", new PartOfSpeechTag[] { PartOfSpeechTag.VERB });
        }};
    }

    protected String normalizeString(String input) {
        return WordListUtils.normalizeStringSurmiran(input);
    }

    protected String removePronouns(String value) {
        return WordListUtils.removePronounsSurmiran(value);
    }
}
