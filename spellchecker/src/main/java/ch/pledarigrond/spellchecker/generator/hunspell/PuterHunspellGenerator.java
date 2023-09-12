package ch.pledarigrond.spellchecker.generator.hunspell;

import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.names.entities.Name;
import ch.pledarigrond.spellchecker.generator.WordListUtils;
import ch.pledarigrond.spellchecker.model.HunspellList;
import ch.pledarigrond.spellchecker.model.HunspellRules;

import java.util.ArrayList;
import java.util.List;

import static ch.pledarigrond.spellchecker.model.HunspellRules.*;

public class PuterHunspellGenerator extends HunspellGenerator {
    public PuterHunspellGenerator(PgEnvironment pgEnvironment, List<Name> names) {
        super(pgEnvironment, names);
    }

    @Override
    protected String removePronouns(String value) {
        return WordListUtils.removePronounsPuter(value);
    }

    protected String normalizeString(String input) {
        return WordListUtils.normalizeStringPuter(input);
    }

    protected void extractNouns(HunspellList list, LemmaVersion lemmaVersion) {
        list.addWord(lemmaVersion.getEntryValue("baseForm"), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("mSingular"), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("fSingular"), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("mPlural"), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("fPlural"), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("pluralCollectiv"), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
    }

    protected void extractAdjectives(HunspellList list, LemmaVersion lemmaVersion) {
        list.addWord(lemmaVersion.getEntryValue("baseForm"), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("mSingular"), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("fSingular"), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("mPlural"), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("fPlural"), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("adverbialForm"), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
    }

    protected void extractPronouns(HunspellList list, LemmaVersion lemmaVersion) {
        list.addWord(lemmaVersion.getEntryValue("baseForm"), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("mSingular"), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("fSingular"), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("mPlural"), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("fPlural"), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
    }

    protected void extractVerbs(Language language, HunspellList list, LemmaVersion lemmaVersion) {
        String infinitiv = lemmaVersion.getEntryValue("infinitiv");
        if (infinitiv == null || infinitiv.equals("")) {
            infinitiv = lemmaVersion.getEntryValue("RStichwort");
        }
        list.addWord(removePronouns(infinitiv), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});

        ArrayList<String> forms = new ArrayList<>();
        addNewlines(lemmaVersion.getEntryValue("preschentsing1"), forms);
        addNewlines(lemmaVersion.getEntryValue("preschentsing2"), forms);
        addNewlines(lemmaVersion.getEntryValue("preschentsing3"), forms);
        addNewlines(lemmaVersion.getEntryValue("preschentplural1"), forms);
        addNewlines(lemmaVersion.getEntryValue("preschentplural2"), forms);
        addNewlines(lemmaVersion.getEntryValue("preschentplural3"), forms);

        addNewlines(lemmaVersion.getEntryValue("imperfectsing1"), forms);
        addNewlines(lemmaVersion.getEntryValue("imperfectsing2"), forms);
        addNewlines(lemmaVersion.getEntryValue("imperfectsing3"), forms);
        addNewlines(lemmaVersion.getEntryValue("imperfectplural1"), forms);
        addNewlines(lemmaVersion.getEntryValue("imperfectplural2"), forms);
        addNewlines(lemmaVersion.getEntryValue("imperfectplural3"), forms);

        addNewlines(lemmaVersion.getEntryValue("conjunctivsing1"), forms);
        addNewlines(lemmaVersion.getEntryValue("conjunctivsing2"), forms);
        addNewlines(lemmaVersion.getEntryValue("conjunctivsing3"), forms);
        addNewlines(lemmaVersion.getEntryValue("conjunctivplural1"), forms);
        addNewlines(lemmaVersion.getEntryValue("conjunctivplural2"), forms);
        addNewlines(lemmaVersion.getEntryValue("conjunctivplural3"), forms);

        addNewlines(lemmaVersion.getEntryValue("conjunctiv2sing1"), forms);
        addNewlines(lemmaVersion.getEntryValue("conjunctiv2sing2"), forms);
        addNewlines(lemmaVersion.getEntryValue("conjunctiv2sing3"), forms);
        addNewlines(lemmaVersion.getEntryValue("conjunctiv2plural1"), forms);
        addNewlines(lemmaVersion.getEntryValue("conjunctiv2plural2"), forms);
        addNewlines(lemmaVersion.getEntryValue("conjunctiv2plural3"), forms);

        addNewlines(lemmaVersion.getEntryValue("cundizionalsing1"), forms);
        addNewlines(lemmaVersion.getEntryValue("cundizionalsing2"), forms);
        addNewlines(lemmaVersion.getEntryValue("cundizionalsing3"), forms);
        addNewlines(lemmaVersion.getEntryValue("cundizionalplural1"), forms);
        addNewlines(lemmaVersion.getEntryValue("cundizionalplural2"), forms);
        addNewlines(lemmaVersion.getEntryValue("cundizionalplural3"), forms);

        addNewlines(lemmaVersion.getEntryValue("participperfectms"), forms);
        addNewlines(lemmaVersion.getEntryValue("participperfectfs"), forms);
        addNewlines(lemmaVersion.getEntryValue("participperfectmp"), forms);
        addNewlines(lemmaVersion.getEntryValue("participperfectfp"), forms);

        addNewlines(lemmaVersion.getEntryValue("gerundium"), forms);

        addNewlines(lemmaVersion.getEntryValue("futursing1"), forms);
        addNewlines(lemmaVersion.getEntryValue("futursing2"), forms);
        addNewlines(lemmaVersion.getEntryValue("futursing3"), forms);
        addNewlines(lemmaVersion.getEntryValue("futurplural1"), forms);
        addNewlines(lemmaVersion.getEntryValue("futurplural2"), forms);
        addNewlines(lemmaVersion.getEntryValue("futurplural3"), forms);

        addNewlines(lemmaVersion.getEntryValue("futurdubitativsing1"), forms);
        addNewlines(lemmaVersion.getEntryValue("futurdubitativsing2"), forms);
        addNewlines(lemmaVersion.getEntryValue("futurdubitativsing3"), forms);
        addNewlines(lemmaVersion.getEntryValue("futurdubitativplural1"), forms);
        addNewlines(lemmaVersion.getEntryValue("futurdubitativplural2"), forms);
        addNewlines(lemmaVersion.getEntryValue("futurdubitativplural3"), forms);

        addNewlines(lemmaVersion.getEntryValue("preschentsing1enclitic"), forms);
        addNewlines(lemmaVersion.getEntryValue("preschentsing2enclitic"), forms);
        addNewlines(lemmaVersion.getEntryValue("preschentsing3encliticm"), forms);
        addNewlines(lemmaVersion.getEntryValue("preschentsing3encliticf"), forms);
        addNewlines(lemmaVersion.getEntryValue("preschentplural1enclitic"), forms);
        addNewlines(lemmaVersion.getEntryValue("preschentplural2enclitic"), forms);
        addNewlines(lemmaVersion.getEntryValue("preschentplural3enclitic"), forms);

        addNewlines(lemmaVersion.getEntryValue("imperfectsing1enclitic"), forms);
        addNewlines(lemmaVersion.getEntryValue("imperfectsing2enclitic"), forms);
        addNewlines(lemmaVersion.getEntryValue("imperfectsing3encliticm"), forms);
        addNewlines(lemmaVersion.getEntryValue("imperfectsing3encliticf"), forms);
        addNewlines(lemmaVersion.getEntryValue("imperfectplural1enclitic"), forms);
        addNewlines(lemmaVersion.getEntryValue("imperfectplural2enclitic"), forms);
        addNewlines(lemmaVersion.getEntryValue("imperfectplural3enclitic"), forms);

        addNewlines(lemmaVersion.getEntryValue("cundizionalsing1enclitic"), forms);
        addNewlines(lemmaVersion.getEntryValue("cundizionalsing2enclitic"), forms);
        addNewlines(lemmaVersion.getEntryValue("cundizionalsing3encliticm"), forms);
        addNewlines(lemmaVersion.getEntryValue("cundizionalsing3encliticf"), forms);
        addNewlines(lemmaVersion.getEntryValue("cundizionalplural1enclitic"), forms);
        addNewlines(lemmaVersion.getEntryValue("cundizionalplural2enclitic"), forms);
        addNewlines(lemmaVersion.getEntryValue("cundizionalplural3enclitic"), forms);

        addNewlines(lemmaVersion.getEntryValue("futursing1enclitic"), forms);
        addNewlines(lemmaVersion.getEntryValue("futursing2enclitic"), forms);
        addNewlines(lemmaVersion.getEntryValue("futursing3encliticm"), forms);
        addNewlines(lemmaVersion.getEntryValue("futursing3encliticf"), forms);
        addNewlines(lemmaVersion.getEntryValue("futurplural1enclitic"), forms);
        addNewlines(lemmaVersion.getEntryValue("futurplural2enclitic"), forms);
        addNewlines(lemmaVersion.getEntryValue("futurplural3enclitic"), forms);

        addNewlines(lemmaVersion.getEntryValue("futurdubitativsing1enclitic"), forms);
        addNewlines(lemmaVersion.getEntryValue("futurdubitativsing2enclitic"), forms);
        addNewlines(lemmaVersion.getEntryValue("futurdubitativsing3encliticm"), forms);
        addNewlines(lemmaVersion.getEntryValue("futurdubitativsing3encliticf"), forms);
        addNewlines(lemmaVersion.getEntryValue("futurdubitativplural1enclitic"), forms);
        addNewlines(lemmaVersion.getEntryValue("futurdubitativplural2enclitic"), forms);
        addNewlines(lemmaVersion.getEntryValue("futurdubitativplural3enclitic"), forms);

        forms.forEach(f -> {
            list.addWord(removePronouns(f), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
        });

        forms = new ArrayList<>();
        addNewlines(lemmaVersion.getEntryValue("imperativ1"), forms);
        addNewlines(lemmaVersion.getEntryValue("imperativ2"), forms);
        addNewlines(lemmaVersion.getEntryValue("imperativ3"), forms);
        addNewlines(lemmaVersion.getEntryValue("imperativ4"), forms);
        addNewlines(lemmaVersion.getEntryValue("imperativ5"), forms);
        addNewlines(lemmaVersion.getEntryValue("imperativ6"), forms);

        forms.forEach(f -> {
            list.addWord(removePronouns(f), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
        });
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

        list.addWord(candidate, new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
    }
}
