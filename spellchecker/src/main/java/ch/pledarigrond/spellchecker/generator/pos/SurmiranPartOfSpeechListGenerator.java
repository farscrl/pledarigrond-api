package ch.pledarigrond.spellchecker.generator.pos;

import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.dictionary.EntryVersionDto;
import ch.pledarigrond.common.util.WordNormalizer;
import ch.pledarigrond.spellchecker.generator.WordListUtils;
import ch.pledarigrond.spellchecker.model.PartOfSpeechTag;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class SurmiranPartOfSpeechListGenerator extends PartOfSpeechListGenerator {

    public SurmiranPartOfSpeechListGenerator(PgEnvironment pgEnvironment, List<String> names) {
        super(pgEnvironment, names);

        posLookupTable = new HashMap<>() {{
            put("adjectiv", new PartOfSpeechTag[] { PartOfSpeechTag.ADJ });
            put("adjectiv / pronom", new PartOfSpeechTag[] { PartOfSpeechTag.ADJ, PartOfSpeechTag.PRON });
            put("adjectiv / pronom possessiv", new PartOfSpeechTag[] { PartOfSpeechTag.ADJ, PartOfSpeechTag.PRON });
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
            put("int / tr", new PartOfSpeechTag[] { PartOfSpeechTag.VERB });
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
            put("abreviaziun", new PartOfSpeechTag[] { PartOfSpeechTag.ABBR });
        }};
    }

    @Override
    protected String normalizeString(String input) {
        return WordNormalizer.normalizeStringSurmiran(input);
    }

    @Override
    protected String removePronouns(String value) {
        return WordListUtils.removePronounsSurmiran(value);
    }

    @Override
    protected void extractNouns(Set<String> baseForms, Set<String> inflections, EntryVersionDto dto, String RStichwort) {
        /*
        String baseForm = lemmaVersion.getEntryValue("baseForm");
        if (baseForm == null || baseForm.equals("")) {
            baseForm = RStichwort;
        }
        addFormToSet(baseForms, baseForm);
        addFormToSet(inflections, baseForm);

        addFormToSet(inflections, lemmaVersion.getEntryValue("mSingular"));
        addFormToSet(inflections, lemmaVersion.getEntryValue("fSingular"));
        addFormToSet(inflections, lemmaVersion.getEntryValue("mPlural"));
        addFormToSet(inflections, lemmaVersion.getEntryValue("fPlural"));
        addFormToSet(inflections, lemmaVersion.getEntryValue("pluralCollectiv"));
        */
    }

    @Override
    protected void extractAdjectives(Set<String> adjectiveBaseForms, Set<String> adjectiveInflections, Set<String> adverbialBaseForms, Set<String> adverbialInflections, EntryVersionDto dto, String RStichwort) {
        /*
        String baseForm = lemmaVersion.getEntryValue("baseForm");
        if (baseForm == null || baseForm.equals("")) {
            baseForm = RStichwort;
        }
        addFormToSet(adjectiveBaseForms, baseForm);
        addFormToSet(adjectiveInflections, baseForm);

        addFormToSet(adjectiveInflections, lemmaVersion.getEntryValue("mSingular"));
        addFormToSet(adjectiveInflections, lemmaVersion.getEntryValue("fSingular"));
        addFormToSet(adjectiveInflections, lemmaVersion.getEntryValue("mPlural"));
        addFormToSet(adjectiveInflections, lemmaVersion.getEntryValue("fPlural"));

        addFormToSet(adverbialBaseForms, lemmaVersion.getEntryValue("adverbialForm"));
        addFormToSet(adverbialInflections, lemmaVersion.getEntryValue("adverbialForm"));
        */
    }

    @Override
    protected void extractPronouns(Set<String> baseForms, Set<String> inflections, EntryVersionDto dto, String RStichwort) {
        /*
        String baseForm = lemmaVersion.getEntryValue("baseForm");
        if (baseForm == null || baseForm.equals("")) {
            baseForm = RStichwort;
        }
        addFormToSet(baseForms, baseForm);
        addFormToSet(inflections, baseForm);

        addFormToSet(inflections, lemmaVersion.getEntryValue("mSingular"));
        addFormToSet(inflections, lemmaVersion.getEntryValue("fSingular"));
        addFormToSet(inflections, lemmaVersion.getEntryValue("mPlural"));
        addFormToSet(inflections, lemmaVersion.getEntryValue("fPlural"));
        */
    }

    @Override
    protected void extractVerbs(Set<String> baseForms, Set<String> inflections, EntryVersionDto dto, String RStichwort) {
        /*
        String infinitiv = lemmaVersion.getEntryValue("infinitiv");
        if (infinitiv == null || infinitiv.equals("")) {
            infinitiv = RStichwort;
        }
        addFormToSet(baseForms, removePronouns(infinitiv));
        addFormToSet(inflections, removePronouns(infinitiv));

        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("preschentsing1")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("preschentsing2")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("preschentsing3")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("preschentplural1")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("preschentplural2")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("preschentplural3")));

        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("imperfectsing1")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("imperfectsing2")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("imperfectsing3")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("imperfectplural1")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("imperfectplural2")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("imperfectplural3")));

        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("conjunctivsing1")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("conjunctivsing2")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("conjunctivsing3")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("conjunctivplural1")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("conjunctivplural2")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("conjunctivplural3")));

        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("cundizionalsing1")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("cundizionalsing2")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("cundizionalsing3")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("cundizionalplural1")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("cundizionalplural2")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("cundizionalplural3")));

        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("participperfectms")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("participperfectfs")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("participperfectmp")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("participperfectfp")));

        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("imperativ1")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("imperativ2")));

        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("gerundium")));

        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("futursing1")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("futursing2")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("futursing3")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("futurplural1")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("futurplural2")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("futurplural3")));

        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("preschentsing1enclitic")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("preschentsing2enclitic")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("preschentsing3encliticm")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("preschentsing3encliticf")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("preschentplural1enclitic")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("preschentplural2enclitic")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("preschentplural3enclitic")));

        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("imperfectsing1enclitic")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("imperfectsing2enclitic")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("imperfectsing3encliticm")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("imperfectsing3encliticf")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("imperfectplural1enclitic")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("imperfectplural2enclitic")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("imperfectplural3enclitic")));

        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("cundizionalsing1enclitic")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("cundizionalsing2enclitic")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("cundizionalsing3encliticm")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("cundizionalsing3encliticf")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("cundizionalplural1enclitic")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("cundizionalplural2enclitic")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("cundizionalplural3enclitic")));

        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("futursing1enclitic")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("futursing2enclitic")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("futursing3encliticm")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("futursing3encliticf")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("futurplural1enclitic")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("futurplural2enclitic")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("futurplural3enclitic")));
        */
    }

    @Override
    protected void extractDefault(Set<String> baseForms, Set<String> inflections, EntryVersionDto dto, String RStichwort) {
        /*
        addFormToSet(baseForms, RStichwort);
        addFormToSet(inflections, RStichwort);
        */
    }
}
