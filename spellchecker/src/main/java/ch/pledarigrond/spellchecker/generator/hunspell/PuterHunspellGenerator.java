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
import java.util.regex.Pattern;

import static ch.pledarigrond.spellchecker.model.HunspellRules.*;

public class PuterHunspellGenerator extends HunspellGenerator {
    public PuterHunspellGenerator(PgEnvironment pgEnvironment, List<String> names) throws IOException {
        super(Language.PUTER, pgEnvironment, names);
    }

    @Override
    protected String removePronouns(String value) {
        return WordListUtils.removePronounsPuter(value);
    }

    protected String normalizeString(String input) {
        return WordNormalizer.normalizeStringPuter(input);
    }

    protected void extractNouns(HunspellList list, EntryVersionDto dto) {
        if (dto.getInflection().getNoun() == null) {
            return;
        }
        list.addWord(dto.getInflection().getNoun().getBaseForm(), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getNoun().getMSingular(), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getNoun().getFSingular(), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getNoun().getMPlural(), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getNoun().getFPlural(), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getNoun().getPluralCollectiv(), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
    }

    protected void extractAdjectives(HunspellList list, EntryVersionDto dto) {
        if (dto.getInflection().getAdjective() == null) {
            return;
        }
        list.addWord(dto.getInflection().getAdjective().getBaseForm(), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getAdjective().getMSingular(), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getAdjective().getFSingular(), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getAdjective().getMPlural(), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getAdjective().getFPlural(), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getAdjective().getAdverbialForm(), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
    }

    protected void extractPronouns(HunspellList list, EntryVersionDto dto) {
        if (dto.getInflection().getPronoun() == null) {
            return;
        }
        list.addWord(dto.getInflection().getPronoun().getBaseForm(), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getPronoun().getMSingular(), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getPronoun().getFSingular(), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getPronoun().getMPlural(), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getPronoun().getFPlural(), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
    }

    protected void extractOtherForms(HunspellList list, EntryVersionDto dto) {
        if (dto.getInflection().getOther() == null) {
            return;
        }
        list.addWord(dto.getInflection().getOther().getBaseForm(), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getOther().getOtherForm1(), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getOther().getOtherForm2(), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getOther().getOtherForm3(), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
        list.addWord(dto.getInflection().getOther().getOtherForm4(), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
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
        list.addWord(removePronouns(infinitiv), new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
        if (startsWithVowel(infinitiv)) {
            list.addWord(removePronouns(infinitiv), new HunspellRules[]{PUTER_PRONOMS_REFLEXIVS});
        }

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

        addNewlines(v.getConjunctiv2().getSing1(), forms);
        addNewlines(v.getConjunctiv2().getSing2(), forms);
        addNewlines(v.getConjunctiv2().getSing3(), forms);
        addNewlines(v.getConjunctiv2().getPlural1(), forms);
        addNewlines(v.getConjunctiv2().getPlural2(), forms);
        addNewlines(v.getConjunctiv2().getPlural3(), forms);

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

        addNewlines(v.getFuturdubitativ().getSing1(), forms);
        addNewlines(v.getFuturdubitativ().getSing2(), forms);
        addNewlines(v.getFuturdubitativ().getSing3(), forms);
        addNewlines(v.getFuturdubitativ().getPlural1(), forms);
        addNewlines(v.getFuturdubitativ().getPlural2(), forms);
        addNewlines(v.getFuturdubitativ().getPlural3(), forms);

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

        addNewlines(v.getFuturEnclitic().getSing1(), forms);
        addNewlines(v.getFuturEnclitic().getSing2(), forms);
        addNewlines(v.getFuturEnclitic().getSing3m(), forms);
        addNewlines(v.getFuturEnclitic().getSing3f(), forms);
        addNewlines(v.getFuturEnclitic().getPlural1(), forms);
        addNewlines(v.getFuturEnclitic().getPlural2(), forms);
        addNewlines(v.getFuturEnclitic().getPlural3(), forms);

        addNewlines(v.getFuturdubitativEnclitic().getSing1(), forms);
        addNewlines(v.getFuturdubitativEnclitic().getSing2(), forms);
        addNewlines(v.getFuturdubitativEnclitic().getSing3m(), forms);
        addNewlines(v.getFuturdubitativEnclitic().getSing3f(), forms);
        addNewlines(v.getFuturdubitativEnclitic().getPlural1(), forms);
        addNewlines(v.getFuturdubitativEnclitic().getPlural2(), forms);
        addNewlines(v.getFuturdubitativEnclitic().getPlural3(), forms);

        forms.forEach(f -> {
            String form = removePronouns(f);
            list.addWord(form, new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
            if (startsWithVowel(form)) {
                list.addWord(form, new HunspellRules[]{PUTER_PRONOMS_REFLEXIVS});
            }
        });

        forms = new ArrayList<>();
        addNewlines(v.getImperativ().getSingular(), forms);
        addNewlines(v.getImperativ().getPlural(), forms);
        addNewlines(v.getImperativ().getForm3(), forms);
        addNewlines(v.getImperativ().getForm4(), forms);
        addNewlines(v.getImperativ().getForm5(), forms);
        addNewlines(v.getImperativ().getForm6(), forms);

        forms.forEach(f -> {
            String form = removePronouns(f);
            list.addWord(form, new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
            if (startsWithVowel(form)) {
                list.addWord(form, new HunspellRules[]{PUTER_PRONOMS_REFLEXIVS});
            }
            list.addWord(removePronouns(f), new HunspellRules[]{PUTER_PRONOM_REFLEXIV_IMPERATIV_AFFIRMATIV, PUTER_PRONOMS_OBJECTS_IMPERATIV_AFFIRMATIV});
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

        // add comma seperated words, like 's-chet, s-chetta'
        if (candidate.length() > 4 && Pattern.matches("^[^,]+, [^,]+$", candidate)) {
            String[] parts = candidate.split(", ");
            list.addWord(parts[0], new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
            list.addWord(parts[1], new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
            return;
        }

        list.addWord(candidate, new HunspellRules[]{PUTER_PLEDS_APOSTROFAI});
    }

    protected void postProcessHunspellList(HunspellList list) {
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
}
