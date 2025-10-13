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

import static ch.pledarigrond.spellchecker.model.HunspellRules.*;

public class SurmiranHunspellGenerator extends HunspellGenerator {
    public SurmiranHunspellGenerator(PgEnvironment pgEnvironment, List<String> names) throws IOException {
        super(Language.SURMIRAN, pgEnvironment, names);
    }

    @Override
    protected String removePronouns(String value) {
       return WordListUtils.removePronounsSurmiran(value);
    }

    protected String normalizeString(String input) {
        return WordNormalizer.normalizeStringSurmiran(input);
    }

    protected void extractNouns(HunspellList list, EntryVersionDto dto) {
        if (dto.getInflection().getNoun() == null) {
            return;
        }
        list.addWord(dto.getInflection().getNoun().getBaseForm(), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_SHORT_ADJECTIVE});
        list.addWord(dto.getInflection().getNoun().getMSingular(), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_SHORT_ADJECTIVE});
        list.addWord(dto.getInflection().getNoun().getFSingular(), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_SHORT_ADJECTIVE});
        list.addWord(dto.getInflection().getNoun().getMPlural(), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_SHORT_ADJECTIVE});
        list.addWord(dto.getInflection().getNoun().getFPlural(), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_SHORT_ADJECTIVE});
        list.addWord(dto.getInflection().getNoun().getPluralCollectiv(), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_SHORT_ADJECTIVE});
    }

    protected void extractAdjectives(HunspellList list, EntryVersionDto dto) {
        if (dto.getInflection().getAdjective() == null) {
            return;
        }
        list.addWord(dto.getInflection().getAdjective().getBaseForm(), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getAdjective().getMSingular(), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getAdjective().getFSingular(), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getAdjective().getMPlural(), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getAdjective().getFPlural(), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getAdjective().getAdverbialForm(), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI});
    }

    protected void extractPronouns(HunspellList list, EntryVersionDto dto) {
        if (dto.getInflection().getPronoun() == null) {
            return;
        }
        list.addWord(dto.getInflection().getPronoun().getBaseForm(), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getPronoun().getMSingular(), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getPronoun().getFSingular(), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getPronoun().getMPlural(), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getPronoun().getFPlural(), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI});
    }

    protected void extractOtherForms(HunspellList list, EntryVersionDto dto) {
        if (dto.getInflection().getOther() == null) {
            return;
        }
        list.addWord(dto.getInflection().getOther().getBaseForm(), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getOther().getOtherForm1(), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getOther().getOtherForm2(), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getOther().getOtherForm3(), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getOther().getOtherForm4(), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI});
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
        list.addWord(removePronouns(infinitiv), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});

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

        addNewlines(v.getPreschentEnclitic().getSing1(), forms);
        addNewlines(v.getPreschentEnclitic().getSing2(), forms);
        addNewlines(v.getPreschentEnclitic().getSing3m(), forms);
        addNewlines(v.getPreschentEnclitic().getSing3f(), forms);
        addNewlines(v.getPreschentEnclitic().getPlural1(), forms);
        addNewlines(v.getPreschentEnclitic().getPlural2(), forms);
        addNewlines(v.getPreschentEnclitic().getPlural3(), forms);

        addNewlines(v.getImperfectEnclitic().getSing1(), forms);
        addNewlines(v.getImperfectEnclitic().getSing2(), forms);
        addNewlines(v.getImperfectEnclitic().getSing3m(), forms);
        addNewlines(v.getImperfectEnclitic().getSing3f(), forms);
        addNewlines(v.getImperfectEnclitic().getPlural1(), forms);
        addNewlines(v.getImperfectEnclitic().getPlural2(), forms);
        addNewlines(v.getImperfectEnclitic().getPlural3(), forms);

        addNewlines(v.getCundiziunalEnclitic().getSing1(), forms);
        addNewlines(v.getCundiziunalEnclitic().getSing2(), forms);
        addNewlines(v.getCundiziunalEnclitic().getSing3m(), forms);
        addNewlines(v.getCundiziunalEnclitic().getSing3f(), forms);
        addNewlines(v.getCundiziunalEnclitic().getPlural1(), forms);
        addNewlines(v.getCundiziunalEnclitic().getPlural2(), forms);
        addNewlines(v.getCundiziunalEnclitic().getPlural3(), forms);

        addNewlines(v.getFuturEnclitic().getSing1(), forms);
        addNewlines(v.getFuturEnclitic().getSing2(), forms);
        addNewlines(v.getFuturEnclitic().getSing3m(), forms);
        addNewlines(v.getFuturEnclitic().getSing3f(), forms);
        addNewlines(v.getFuturEnclitic().getPlural1(), forms);
        addNewlines(v.getFuturEnclitic().getPlural2(), forms);
        addNewlines(v.getFuturEnclitic().getPlural3(), forms);

        forms.forEach(f -> {
            list.addWord(removePronouns(f), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        });

        forms = new ArrayList<>();
        addNewlines(v.getImperativ().getSingular(), forms);
        addNewlines(v.getImperativ().getPlural(), forms);

        forms.forEach(f -> {
            list.addWord(removePronouns(f), new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOM_CONGIUNT_IMPERATIV, SURMIRAN_PRONOMS_REFLEXIVS});
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

        list.addWord(candidate, new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI});
    }

    protected void postProcessHunspellList(HunspellList list) {
        // Do nothing
    }
}
