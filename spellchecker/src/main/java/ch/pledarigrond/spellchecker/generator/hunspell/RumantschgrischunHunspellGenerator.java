package ch.pledarigrond.spellchecker.generator.hunspell;

import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.names.entities.Name;
import ch.pledarigrond.spellchecker.generator.WordListUtils;
import ch.pledarigrond.spellchecker.model.HunspellList;
import ch.pledarigrond.spellchecker.model.HunspellRules;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import static ch.pledarigrond.spellchecker.model.HunspellRules.*;

public class RumantschgrischunHunspellGenerator extends HunspellGenerator {
    public RumantschgrischunHunspellGenerator(PgEnvironment pgEnvironment, List<Name> names) {
        super(pgEnvironment, names);
    }

    @Override
    protected String removePronouns(String value) {
        return WordListUtils.removePronounsRumantschGrischun(value);
    }

    protected String normalizeString(String input) {
        return WordListUtils.normalizeStringRumantschGrischun(input);
    }

    protected void extractNouns(HunspellList list, LemmaVersion lemmaVersion) {
        list.addWord(lemmaVersion.getEntryValue("baseForm"), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("mSingular"), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("fSingular"), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("mPlural"), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("fPlural"), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("pluralCollectiv"), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
    }

    protected void extractAdjectives(HunspellList list, LemmaVersion lemmaVersion) {
        list.addWord(lemmaVersion.getEntryValue("baseForm"), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("mSingular"), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("fSingular"), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("mPlural"), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("fPlural"), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("adverbialForm"), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
    }

    protected void extractPronouns(HunspellList list, LemmaVersion lemmaVersion) {
        list.addWord(lemmaVersion.getEntryValue("baseForm"), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("mSingular"), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("fSingular"), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("mPlural"), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("fPlural"), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
    }

    protected void extractOtherForms(HunspellList list, LemmaVersion lemmaVersion) {
        list.addWord(lemmaVersion.getEntryValue("baseForm"), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("otherForm1"), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("otherForm2"), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("otherForm3"), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("otherForm4"), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
    }

    protected void extractVerbs(HunspellList list, LemmaVersion lemmaVersion) {
        String infinitiv = lemmaVersion.getEntryValue("infinitiv");
        if (infinitiv == null || infinitiv.equals("")) {
            infinitiv = lemmaVersion.getEntryValue("RStichwort");
        }
        list.addWord(removePronouns(infinitiv), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        if (startsWithVowel(infinitiv)) {
            list.addWord(removePronouns(infinitiv), new HunspellRules[]{RUMANTSCH_GRISCHUN_PRONOMS_REFLEXIVS});
        }

        ArrayList<String> forms = new ArrayList<>();
        addNewlines(lemmaVersion.getEntryValue("preschentsing1"), forms);
        addNewlines(lemmaVersion.getEntryValue("preschentsing2"), forms);
        // addNewlines(lemmaVersion.getEntryValue("preschentsing3"), forms); // below
        addNewlines(lemmaVersion.getEntryValue("preschentplural1"), forms);
        addNewlines(lemmaVersion.getEntryValue("preschentplural2"), forms);
        addNewlines(lemmaVersion.getEntryValue("preschentplural3"), forms);

        addNewlines(lemmaVersion.getEntryValue("imperfectsing1"), forms);
        addNewlines(lemmaVersion.getEntryValue("imperfectsing2"), forms);
        // addNewlines(lemmaVersion.getEntryValue("imperfectsing3"), forms); // below
        addNewlines(lemmaVersion.getEntryValue("imperfectplural1"), forms);
        addNewlines(lemmaVersion.getEntryValue("imperfectplural2"), forms);
        addNewlines(lemmaVersion.getEntryValue("imperfectplural3"), forms);

        addNewlines(lemmaVersion.getEntryValue("conjunctivsing1"), forms);
        addNewlines(lemmaVersion.getEntryValue("conjunctivsing2"), forms);
        addNewlines(lemmaVersion.getEntryValue("conjunctivsing3"), forms);
        addNewlines(lemmaVersion.getEntryValue("conjunctivplural1"), forms);
        addNewlines(lemmaVersion.getEntryValue("conjunctivplural2"), forms);
        addNewlines(lemmaVersion.getEntryValue("conjunctivplural3"), forms);

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

        addNewlines(lemmaVersion.getEntryValue("imperativ1"), forms);
        addNewlines(lemmaVersion.getEntryValue("imperativ2"), forms);

        forms.forEach(f -> {
            String form = removePronouns(f);
            list.addWord(form, new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
            if (startsWithVowel(form)) {
                list.addWord(form, new HunspellRules[]{RUMANTSCH_GRISCHUN_PRONOMS_REFLEXIVS});
            }
        });

        forms = new ArrayList<>();
        addNewlines(lemmaVersion.getEntryValue("preschentsing3"), forms);
        addNewlines(lemmaVersion.getEntryValue("imperfectsing3"), forms);

        String finalInfinitiv = infinitiv;
        forms.forEach(f -> {
            String form = removePronouns(f);
            list.addWord(form, new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
            if (startsWithVowel(form)) {
                list.addWord(form, new HunspellRules[]{RUMANTSCH_GRISCHUN_PRONOMS_REFLEXIVS});
            }
            if (!finalInfinitiv.endsWith("ia")) {
                list.addWord(form, new HunspellRules[]{RUMANTSCH_GRISCHUN_PRONOMS_IMPRES});
            }
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

        list.addWord(candidate, new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
    }

    protected void postProcessHunspellList(HunspellList list) {
        list.applyRuleOnCondition(new CheckIfStartsWithVowel(), RUMANTSCH_GRISCHUN_NEGAZIUN);
        list.separateWordsWithSlash();
    }

    private boolean startsWithVowel(String root) {
        if (root == null || root.isEmpty()) {
            return false;
        }

        // convert è -> e
        root = Normalizer.normalize(root, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        return switch (root.toLowerCase().substring(0, 1)) {
            case "a", "e", "i", "o", "u", "h" -> true;
            default -> false;
        };
    }

    public class CheckIfStartsWithVowel implements HunspellList.CheckCondition {

        @Override
        public boolean check(String word) {
            return startsWithVowel(word);
        }
    }
}
