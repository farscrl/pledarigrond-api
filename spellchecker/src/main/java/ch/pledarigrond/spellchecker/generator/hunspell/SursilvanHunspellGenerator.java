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
import java.util.ArrayList;
import java.util.List;

import static ch.pledarigrond.spellchecker.model.HunspellRules.SURSILVAN_PLEDS_APOSTROFAI;
import static ch.pledarigrond.spellchecker.model.HunspellRules.SURSILVAN_PRONOMS_REFLEXIVS;

public class SursilvanHunspellGenerator extends HunspellGenerator {
    public SursilvanHunspellGenerator(PgEnvironment pgEnvironment, List<String> names) throws IOException {
        super(Language.SURSILVAN, pgEnvironment, names);
    }

    @Override
    protected String removePronouns(String value) {
        return WordListUtils.removePronounsSursilvan(value);
    }

    protected String normalizeString(String input) {
        return WordNormalizer.normalizeStringSursilvan(input);
    }

    protected void extractNouns(HunspellList list, EntryVersionDto dto) {
        if (dto.getInflection().getNoun() == null) {
            return;
        }
        list.addWord(dto.getInflection().getNoun().getBaseForm(), new HunspellRules[]{SURSILVAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getNoun().getMSingular(), new HunspellRules[]{SURSILVAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getNoun().getFSingular(), new HunspellRules[]{SURSILVAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getNoun().getMPlural(), new HunspellRules[]{SURSILVAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getNoun().getFPlural(), new HunspellRules[]{SURSILVAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getNoun().getPluralCollectiv(), new HunspellRules[]{SURSILVAN_PLEDS_APOSTROFAI});
    }

    protected void extractAdjectives(HunspellList list, EntryVersionDto dto) {
        if (dto.getInflection().getAdjective() == null) {
            return;
        }
        list.addWord(dto.getInflection().getAdjective().getBaseForm(), new HunspellRules[]{SURSILVAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getAdjective().getMSingular(), new HunspellRules[]{SURSILVAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getAdjective().getFSingular(), new HunspellRules[]{SURSILVAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getAdjective().getMPlural(), new HunspellRules[]{SURSILVAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getAdjective().getFPlural(), new HunspellRules[]{SURSILVAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getAdjective().getPredicative(), new HunspellRules[]{SURSILVAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getAdjective().getAdverbialForm(), new HunspellRules[]{SURSILVAN_PLEDS_APOSTROFAI});
    }

    protected void extractPronouns(HunspellList list, EntryVersionDto dto) {
        if (dto.getInflection().getPronoun() == null) {
            return;
        }
        list.addWord(dto.getInflection().getPronoun().getBaseForm(), new HunspellRules[]{SURSILVAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getPronoun().getMSingular(), new HunspellRules[]{SURSILVAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getPronoun().getFSingular(), new HunspellRules[]{SURSILVAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getPronoun().getMPlural(), new HunspellRules[]{SURSILVAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getPronoun().getFPlural(), new HunspellRules[]{SURSILVAN_PLEDS_APOSTROFAI});
    }

    protected void extractOtherForms(HunspellList list, EntryVersionDto dto) {
        if (dto.getInflection().getOther() == null) {
            return;
        }
        list.addWord(dto.getInflection().getOther().getBaseForm(), new HunspellRules[]{SURSILVAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getOther().getOtherForm1(), new HunspellRules[]{SURSILVAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getOther().getOtherForm2(), new HunspellRules[]{SURSILVAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getOther().getOtherForm3(), new HunspellRules[]{SURSILVAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getOther().getOtherForm4(), new HunspellRules[]{SURSILVAN_PLEDS_APOSTROFAI});
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
        list.addWord(removePronouns(infinitiv), new HunspellRules[]{SURSILVAN_PLEDS_APOSTROFAI, SURSILVAN_PRONOMS_REFLEXIVS});

        ArrayList<String> forms = new ArrayList<>();
        addNewlines(v.getPreschent().getSing1(), forms);
        addNewlines(v.getPreschent().getSing2(), forms);
        addNewlines(v.getPreschent().getSing3(), forms);
        addNewlines(v.getPreschent().getPlural1(), forms);
        addNewlines(v.getPreschent().getPlural2(), forms);
        addNewlines(v.getPreschent().getPlural3(), forms);

        addNewlines(v.getImperfect().getSing1(), forms);
        addNewlines(v.getImperfect().getSing2(), forms);
        addNewlines(v.getImperfect().getSing3(), forms);
        addNewlines(v.getImperfect().getPlural1(), forms);
        addNewlines(v.getImperfect().getPlural2(), forms);
        addNewlines(v.getImperfect().getPlural3(), forms);

        addNewlines(v.getConjunctiv().getSing1(), forms);
        addNewlines(v.getConjunctiv().getSing2(), forms);
        addNewlines(v.getConjunctiv().getSing3(), forms);
        addNewlines(v.getConjunctiv().getPlural1(), forms);
        addNewlines(v.getConjunctiv().getPlural2(), forms);
        addNewlines(v.getConjunctiv().getPlural3(), forms);

        addNewlines(v.getConjunctivImperfect().getSing1(), forms);
        addNewlines(v.getConjunctivImperfect().getSing2(), forms);
        addNewlines(v.getConjunctivImperfect().getSing3(), forms);
        addNewlines(v.getConjunctivImperfect().getPlural1(), forms);
        addNewlines(v.getConjunctivImperfect().getPlural2(), forms);
        addNewlines(v.getConjunctivImperfect().getPlural3(), forms);

        addNewlines(v.getCundiziunal().getSing1(), forms);
        addNewlines(v.getCundiziunal().getSing2(), forms);
        addNewlines(v.getCundiziunal().getSing3(), forms);
        addNewlines(v.getCundiziunal().getPlural1(), forms);
        addNewlines(v.getCundiziunal().getPlural2(), forms);
        addNewlines(v.getCundiziunal().getPlural3(), forms);

        addNewlines(v.getCundiziunalIndirect().getSing1(), forms);
        addNewlines(v.getCundiziunalIndirect().getSing2(), forms);
        addNewlines(v.getCundiziunalIndirect().getSing3(), forms);
        addNewlines(v.getCundiziunalIndirect().getPlural1(), forms);
        addNewlines(v.getCundiziunalIndirect().getPlural2(), forms);
        addNewlines(v.getCundiziunalIndirect().getPlural3(), forms);

        addNewlines(v.getParticipPerfect().getMs(), forms);
        addNewlines(v.getParticipPerfect().getFs(), forms);
        addNewlines(v.getParticipPerfect().getMp(), forms);
        addNewlines(v.getParticipPerfect().getFp(), forms);
        addNewlines(v.getParticipPerfect().getMsPredicativ(), forms);

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
            list.addWord(removePronouns(f), new HunspellRules[]{SURSILVAN_PLEDS_APOSTROFAI, SURSILVAN_PRONOMS_REFLEXIVS});
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

        list.addWord(candidate, new HunspellRules[]{SURSILVAN_PLEDS_APOSTROFAI});
    }

    protected void postProcessHunspellList(HunspellList list) {
        // Do nothing
    }
}
