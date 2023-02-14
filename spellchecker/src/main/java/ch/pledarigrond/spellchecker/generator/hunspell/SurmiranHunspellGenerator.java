package ch.pledarigrond.spellchecker.generator.hunspell;

import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.names.entities.Name;
import ch.pledarigrond.spellchecker.model.HunspellList;
import ch.pledarigrond.spellchecker.generator.WordListUtils;
import ch.pledarigrond.spellchecker.model.HunspellRules;
import java.util.List;

import static ch.pledarigrond.spellchecker.model.HunspellRules.*;

public class SurmiranHunspellGenerator extends HunspellGenerator {
    public SurmiranHunspellGenerator(PgEnvironment pgEnvironment, List<Name> names) {
        super(pgEnvironment, names);
    }

    @Override
    protected String removePronouns(String value) {
       return WordListUtils.removePronounsSurmiran(value);
    }

    protected String normalizeString(String input) {
        return WordListUtils.normalizeStringSurmiran(input);
    }

    protected void extractNouns(HunspellList list, LemmaVersion lemmaVersion) {
        list.addWord(lemmaVersion.getEntryValue("baseForm"), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_SHORT_ADJECTIVE});
        list.addWord(lemmaVersion.getEntryValue("mSingular"), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_SHORT_ADJECTIVE});
        list.addWord(lemmaVersion.getEntryValue("fSingular"), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_SHORT_ADJECTIVE});
        list.addWord(lemmaVersion.getEntryValue("mPlural"), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_SHORT_ADJECTIVE});
        list.addWord(lemmaVersion.getEntryValue("fPlural"), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_SHORT_ADJECTIVE});
        list.addWord(lemmaVersion.getEntryValue("pluralCollectiv"), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_SHORT_ADJECTIVE});
    }

    protected void extractAdjectives(HunspellList list, LemmaVersion lemmaVersion) {
        list.addWord(lemmaVersion.getEntryValue("baseForm"), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("mSingular"), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("fSingular"), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("mPlural"), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("fPlural"), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("adverbialForm"), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI});
    }

    protected void extractPronouns(HunspellList list, LemmaVersion lemmaVersion) {
        list.addWord(lemmaVersion.getEntryValue("baseForm"), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("mSingular"), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("fSingular"), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("mPlural"), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("fPlural"), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI});
    }

    protected void extractVerbs(Language language, HunspellList list, LemmaVersion lemmaVersion) {
        String infinitiv = lemmaVersion.getEntryValue("infinitiv");
        if (infinitiv == null || infinitiv.equals("")) {
            infinitiv = lemmaVersion.getEntryValue("RStichwort");
        }
        list.addWord(removePronouns(infinitiv), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI});

        list.addWord(removePronouns(lemmaVersion.getEntryValue("preschentsing1")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("preschentsing2")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("preschentsing3")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("preschentplural1")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("preschentplural2")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("preschentplural3")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});

        list.addWord(removePronouns(lemmaVersion.getEntryValue("imperfectsing1")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("imperfectsing2")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("imperfectsing3")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("imperfectplural1")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("imperfectplural2")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("imperfectplural3")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});

        list.addWord(removePronouns(lemmaVersion.getEntryValue("conjunctivsing1")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("conjunctivsing2")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("conjunctivsing3")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("conjunctivplural1")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("conjunctivplural2")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("conjunctivplural3")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});

        list.addWord(removePronouns(lemmaVersion.getEntryValue("cundizionalsing1")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("cundizionalsing2")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("cundizionalsing3")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("cundizionalplural1")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("cundizionalplural2")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("cundizionalplural3")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});

        list.addWord(removePronouns(lemmaVersion.getEntryValue("participperfectms")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("participperfectfs")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("participperfectmp")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("participperfectfp")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});

        list.addWord(removePronouns(lemmaVersion.getEntryValue("imperativ1")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOM_CONGIUNT_IMPERATIV, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("imperativ2")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOM_CONGIUNT_IMPERATIV, SURMIRAN_PRONOMS_REFLEXIVS});

        list.addWord(removePronouns(lemmaVersion.getEntryValue("gerundium")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});

        list.addWord(removePronouns(lemmaVersion.getEntryValue("futursing1")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("futursing2")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("futursing3")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("futurplural1")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("futurplural2")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("futurplural3")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});

        list.addWord(removePronouns(lemmaVersion.getEntryValue("preschentsing1enclitic")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("preschentsing2enclitic")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("preschentsing3encliticm")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("preschentsing3encliticf")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("preschentplural1enclitic")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("preschentplural2enclitic")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("preschentplural3enclitic")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});

        list.addWord(removePronouns(lemmaVersion.getEntryValue("imperfectsing1enclitic")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("imperfectsing2enclitic")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("imperfectsing3encliticm")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("imperfectsing3encliticf")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("imperfectplural1enclitic")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("imperfectplural2enclitic")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("imperfectplural3enclitic")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});

        list.addWord(removePronouns(lemmaVersion.getEntryValue("cundizionalsing1enclitic")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("cundizionalsing2enclitic")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("cundizionalsing3encliticm")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("cundizionalsing3encliticf")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("cundizionalplural1enclitic")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("cundizionalplural2enclitic")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("cundizionalplural3enclitic")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});

        list.addWord(removePronouns(lemmaVersion.getEntryValue("futursing1enclitic")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("futursing2enclitic")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("futursing3encliticm")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("futursing3encliticf")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("futurplural1enclitic")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("futurplural2enclitic")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(lemmaVersion.getEntryValue("futurplural3enclitic")), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
    }

    protected void extractDefault(HunspellList list, LemmaVersion lemmaVersion) {
        String candidate = lemmaVersion.getEntryValue("RStichwort");

        if (candidate == null) {
            return;
        }

        candidate = normalizeString(candidate);

        if (candidate == null) {
            return;
        }

        list.addWord(candidate, new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI});
    }
}
