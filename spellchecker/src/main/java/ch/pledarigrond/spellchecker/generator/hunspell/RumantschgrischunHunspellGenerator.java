package ch.pledarigrond.spellchecker.generator.hunspell;

import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.dictionary.EntryVersionDto;
import ch.pledarigrond.common.data.dictionary.inflection.VerbDto;
import ch.pledarigrond.common.util.WordNormalizer;
import ch.pledarigrond.spellchecker.generator.WordListUtils;
import ch.pledarigrond.spellchecker.model.HunspellList;
import ch.pledarigrond.spellchecker.model.HunspellRules;

import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import static ch.pledarigrond.spellchecker.model.HunspellRules.*;

public class RumantschgrischunHunspellGenerator extends HunspellGenerator {
    public RumantschgrischunHunspellGenerator(PgEnvironment pgEnvironment, List<String> names) throws IOException {
        super(Language.RUMANTSCHGRISCHUN, pgEnvironment, names);
    }

    @Override
    protected String removePronouns(String value) {
        return WordListUtils.removePronounsRumantschGrischun(value);
    }

    protected String normalizeString(String input) {
        return WordNormalizer.normalizeStringRumantschGrischun(input);
    }

    protected void extractNouns(HunspellList list, EntryVersionDto dto) {
        if (dto.getInflection().getNoun() == null) {
            return;
        }
        list.addWord(dto.getInflection().getNoun().getBaseForm(), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getNoun().getMSingular(), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getNoun().getFSingular(), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getNoun().getMPlural(), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getNoun().getFPlural(), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getNoun().getPluralCollectiv(), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
    }

    protected void extractAdjectives(HunspellList list, EntryVersionDto dto) {
        if (dto.getInflection().getAdjective() == null) {
            return;
        }
        list.addWord(dto.getInflection().getAdjective().getBaseForm(), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getAdjective().getMSingular(), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getAdjective().getFSingular(), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getAdjective().getMPlural(), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getAdjective().getFPlural(), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getAdjective().getAdverbialForm(), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
    }

    protected void extractPronouns(HunspellList list, EntryVersionDto dto) {
        if (dto.getInflection().getPronoun() == null) {
            return;
        }
        list.addWord(dto.getInflection().getPronoun().getBaseForm(), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getPronoun().getMSingular(), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getPronoun().getFSingular(), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getPronoun().getMPlural(), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getPronoun().getFPlural(), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
    }

    protected void extractOtherForms(HunspellList list, EntryVersionDto dto) {
        if (dto.getInflection().getOther() == null) {
            return;
        }
        list.addWord(dto.getInflection().getOther().getBaseForm(), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getOther().getOtherForm1(), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getOther().getOtherForm2(), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getOther().getOtherForm3(), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getOther().getOtherForm4(), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
    }

    protected void extractVerbs(HunspellList list, EntryVersionDto dto) {
        if (dto.getInflection().getVerb() == null) {
            return;
        }

        VerbDto v = dto.getInflection().getVerb();

        String infinitiv = v.getInfinitiv();
        if (infinitiv == null || infinitiv.isEmpty()) {
            infinitiv = dto.getRmStichwort();
        }
        list.addWord(removePronouns(infinitiv), new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
        if (startsWithVowel(infinitiv)) {
            list.addWord(removePronouns(infinitiv), new HunspellRules[]{RUMANTSCH_GRISCHUN_PRONOMS_REFLEXIVS});
        }

        ArrayList<String> forms = new ArrayList<>();
        addNewlines(v.getPreschent().getSing1(), forms);
        addNewlines(v.getPreschent().getSing2(), forms);
        // addNewlines(v.getPreschent().getSing3(), forms); // below
        addNewlines(v.getPreschent().getPlural1(), forms);
        addNewlines(v.getPreschent().getPlural2(), forms);
        addNewlines(v.getPreschent().getPlural3(), forms);

        addNewlines(v.getImperfect().getSing1(), forms);
        addNewlines(v.getImperfect().getSing2(), forms);
        // addNewlines(v.getImperfect().getSing3(), forms); // below
        addNewlines(v.getImperfect().getPlural1(), forms);
        addNewlines(v.getImperfect().getPlural2(), forms);
        addNewlines(v.getImperfect().getPlural3(), forms);

        addNewlines(v.getConjunctiv().getSing1(), forms);
        addNewlines(v.getConjunctiv().getSing2(), forms);
        addNewlines(v.getConjunctiv().getSing3(), forms);
        addNewlines(v.getConjunctiv().getPlural1(), forms);
        addNewlines(v.getConjunctiv().getPlural2(), forms);
        addNewlines(v.getConjunctiv().getPlural3(), forms);

        addNewlines(v.getCundiziunal().getSing1(), forms);
        addNewlines(v.getCundiziunal().getSing2(), forms);
        addNewlines(v.getCundiziunal().getSing3(), forms);
        addNewlines(v.getCundiziunal().getPlural1(), forms);
        addNewlines(v.getCundiziunal().getPlural2(), forms);
        addNewlines(v.getCundiziunal().getPlural3(), forms);

        addNewlines(v.getParticipPerfect().getMs(), forms);
        addNewlines(v.getParticipPerfect().getFs(), forms);
        addNewlines(v.getParticipPerfect().getMp(), forms);
        addNewlines(v.getParticipPerfect().getFp(), forms);

        addNewlines(v.getGerundium(), forms);

        addNewlines(v.getFutur().getSing1(), forms);
        addNewlines(v.getFutur().getSing2(), forms);
        addNewlines(v.getFutur().getSing3(), forms);
        addNewlines(v.getFutur().getPlural1(), forms);
        addNewlines(v.getFutur().getPlural2(), forms);
        addNewlines(v.getFutur().getPlural3(), forms);

        addNewlines(v.getImperativ().getSingular(), forms);
        addNewlines(v.getImperativ().getPlural(), forms);

        forms.forEach(f -> {
            String form = removePronouns(f);
            list.addWord(form, new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
            if (startsWithVowel(form)) {
                list.addWord(form, new HunspellRules[]{RUMANTSCH_GRISCHUN_PRONOMS_REFLEXIVS});
            }
        });

        forms = new ArrayList<>();
        addNewlines(v.getPreschent().getSing3(), forms);

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

        forms = new ArrayList<>();
        addNewlines(v.getImperfect().getSing3(), forms);

        forms.forEach(f -> {
            String form = removePronouns(f);
            list.addWord(form, new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
            if (startsWithVowel(form)) {
                list.addWord(form, new HunspellRules[]{RUMANTSCH_GRISCHUN_PRONOMS_REFLEXIVS});
            }
            list.addWord(form, new HunspellRules[]{RUMANTSCH_GRISCHUN_PRONOMS_IMPRES});
        });
    }

    protected void extractDefault(HunspellList list, EntryVersionDto dto) {
        String candidate = dto.getRmStichwort();

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
