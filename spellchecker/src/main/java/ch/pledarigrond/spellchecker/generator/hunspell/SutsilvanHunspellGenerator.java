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

public class SutsilvanHunspellGenerator extends HunspellGenerator {
    public SutsilvanHunspellGenerator(PgEnvironment pgEnvironment, List<String> names) throws IOException {
        super(Language.SUTSILVAN, pgEnvironment, names);
    }

    @Override
    protected String removePronouns(String value) {
        return WordListUtils.removePronounsSutsilvan(value);
    }

    protected String normalizeString(String input) {
        return WordNormalizer.normalizeStringSutsilvan(input);
    }

    protected void extractNouns(HunspellList list, EntryVersionDto dto) {
        if (dto.getInflection().getNoun() == null) {
            return;
        }
        list.addWord(dto.getInflection().getNoun().getBaseForm(), new HunspellRules[]{SUTSILVAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getNoun().getMSingular(), new HunspellRules[]{SUTSILVAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getNoun().getFSingular(), new HunspellRules[]{SUTSILVAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getNoun().getMPlural(), new HunspellRules[]{SUTSILVAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getNoun().getFPlural(), new HunspellRules[]{SUTSILVAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getNoun().getPluralCollectiv(), new HunspellRules[]{SUTSILVAN_PLEDS_APOSTROFAI});
    }

    protected void extractAdjectives(HunspellList list, EntryVersionDto dto) {
        if (dto.getInflection().getAdjective() == null) {
            return;
        }
        list.addWord(dto.getInflection().getAdjective().getBaseForm(), new HunspellRules[]{SUTSILVAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getAdjective().getMSingular(), new HunspellRules[]{SUTSILVAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getAdjective().getFSingular(), new HunspellRules[]{SUTSILVAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getAdjective().getMPlural(), new HunspellRules[]{SUTSILVAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getAdjective().getFPlural(), new HunspellRules[]{SUTSILVAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getAdjective().getAdverbialForm(), new HunspellRules[]{SUTSILVAN_PLEDS_APOSTROFAI});
    }

    protected void extractPronouns(HunspellList list, EntryVersionDto dto) {
        if (dto.getInflection().getPronoun() == null) {
            return;
        }
        list.addWord(dto.getInflection().getPronoun().getBaseForm(), new HunspellRules[]{SUTSILVAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getPronoun().getMSingular(), new HunspellRules[]{SUTSILVAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getPronoun().getFSingular(), new HunspellRules[]{SUTSILVAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getPronoun().getMPlural(), new HunspellRules[]{SUTSILVAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getPronoun().getFPlural(), new HunspellRules[]{SUTSILVAN_PLEDS_APOSTROFAI});
    }

    protected void extractOtherForms(HunspellList list, EntryVersionDto dto) {
        if (dto.getInflection().getOther() == null) {
            return;
        }
        list.addWord(dto.getInflection().getOther().getBaseForm(), new HunspellRules[]{SUTSILVAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getOther().getOtherForm1(), new HunspellRules[]{SUTSILVAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getOther().getOtherForm2(), new HunspellRules[]{SUTSILVAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getOther().getOtherForm3(), new HunspellRules[]{SUTSILVAN_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getOther().getOtherForm4(), new HunspellRules[]{SUTSILVAN_PLEDS_APOSTROFAI});
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
        list.addWord(removePronouns(infinitiv), new HunspellRules[]{SUTSILVAN_PLEDS_APOSTROFAI, SUTSILVAN_PRONOMS_CUN_FURMA_INVERSIVA_DAL_PRONOM_IMPERS, SUTSILVAN_PRONOMS_REFLEXIVS});

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

        addNewlines(v.getCundizional().getSing1(), forms);
        addNewlines(v.getCundizional().getSing2(), forms);
        addNewlines(v.getCundizional().getSing3(), forms);
        addNewlines(v.getCundizional().getPlural1(), forms);
        addNewlines(v.getCundizional().getPlural2(), forms);
        addNewlines(v.getCundizional().getPlural3(), forms);

        addNewlines(v.getParticipperfect().getMs(), forms);
        addNewlines(v.getParticipperfect().getFs(), forms);
        addNewlines(v.getParticipperfect().getMp(), forms);
        addNewlines(v.getParticipperfect().getFp(), forms);

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

        addNewlines(v.getCundizionalEnclitic().getSing1(), forms);
        addNewlines(v.getCundizionalEnclitic().getSing2(), forms);
        addNewlines(v.getCundizionalEnclitic().getSing3m(), forms);
        addNewlines(v.getCundizionalEnclitic().getSing3f(), forms);
        addNewlines(v.getCundizionalEnclitic().getPlural1(), forms);
        addNewlines(v.getCundizionalEnclitic().getPlural2(), forms);
        addNewlines(v.getCundizionalEnclitic().getPlural3(), forms);

        addNewlines(v.getImperativ().getSingular(), forms);
        addNewlines(v.getImperativ().getPlural(), forms);

        forms.forEach(f -> {
            list.addWord(removePronouns(f), new HunspellRules[]{SUTSILVAN_PLEDS_APOSTROFAI, SUTSILVAN_PRONOMS_CUN_FURMA_INVERSIVA_DAL_PRONOM_IMPERS, SUTSILVAN_PRONOMS_REFLEXIVS});
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

        list.addWord(candidate, new HunspellRules[]{SUTSILVAN_PLEDS_APOSTROFAI});
    }

    protected void postProcessHunspellList(HunspellList list) {
        // Do nothing
    }
}
