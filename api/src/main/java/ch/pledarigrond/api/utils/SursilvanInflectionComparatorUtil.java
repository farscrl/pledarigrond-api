package ch.pledarigrond.api.utils;

import ch.pledarigrond.api.dtos.VerbDto;
import ch.pledarigrond.api.services.InflectionService;
import ch.pledarigrond.api.services.SursilvanVerbService;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.common.data.dictionary.inflection.InflectionType;
import ch.pledarigrond.inflection.utils.PronounRemover;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SursilvanInflectionComparatorUtil {

    @Autowired
    private SursilvanVerbService sursilvanVerbService;

    @Autowired
    private InflectionService inflectionService;

    public static final List<String> VERBS_ON_AR = List.of("1", "6", "1a", "1b", "1c", "1d", "1e", "1f", "1g", "1h", "1i", "1j", "1k", "1l", "1m", "1n", "1o", "1p", "1q", "1r", "1s", "1t", "1u", "1v", "1w", "1x", "1y", "1z", "1aa", "1ab", "1ac", "1ad", "1ae", "1af", "1ag", "1ah", "1ai");

    public static final List<String> VERBS_ON_ER = List.of("2", "2a", "3", "3a", "3b", "3c", "3d", "3e", "3f");

    public static final List<String> VERBS_ON_IR = List.of("4", "5", "4a", "4b", "4c", "4d", "4e", "4f");

    private String baseForm;
    private final Map<String, InflectionValidationDto> allInflections = new HashMap<>();
    private VerbDto referenceVerb;
    private PronounRemover pronounRemover = new PronounRemover();

    public InflectionResultDto getInflection(String baseForm) {
        this.baseForm = baseForm;
        allInflections.clear();
        referenceVerb = sursilvanVerbService.getVerb(baseForm);

        if (referenceVerb == null) {
            InflectionDto inflectionResponse = inflectionService.guessInflection(Language.SURSILVAN, InflectionType.VERB, baseForm, null, null);
            return new InflectionResultDto(inflectionResponse, false, null);
        }

        generateAllInflections();
        if (allInflections.isEmpty()) {
            InflectionDto inflectionResponse = inflectionService.guessInflection(Language.SURSILVAN, InflectionType.VERB, baseForm, null, null);
            return new InflectionResultDto(inflectionResponse, false, null);
        }

        validateInflections();
        return findBestResult();
    }

    private void generateAllInflections() {
        if (baseForm.length() < 3) {
            return;
        }

        String lastTwo = baseForm.substring(baseForm.length() - 2);

        List<String> possibleInflections = new ArrayList<>();
        if (lastTwo.equals("ar")) {
            possibleInflections = VERBS_ON_AR;
        }

        if (lastTwo.equals("er")) {
            possibleInflections = VERBS_ON_ER;
        }

        if (lastTwo.equals("ir")) {
            possibleInflections = VERBS_ON_IR;
        }

        for (String constant : possibleInflections) {
            try {
                InflectionDto inflectionResponse = inflectionService.generateInflection(Language.SURSILVAN, InflectionType.VERB, constant, baseForm);
                if (inflectionResponse != null) {
                    this.allInflections.put(constant, new InflectionValidationDto(inflectionResponse));
                }
            } catch (StringIndexOutOfBoundsException | NullPointerException ex) {
                // do nothing
            }
        }
    }

    private void validateInflections() {
        for (Map.Entry<String, InflectionValidationDto> entry : allInflections.entrySet()) {
            InflectionValidationDto inflectionResultDto = entry.getValue();
            inflectionResultDto.setNbrWrongForms(validateInflection(inflectionResultDto.getInflectionResponse()));
        }
    }

    private int validateInflection(InflectionDto inflection) {
        int nbrWrongForms = 0;

        nbrWrongForms += getWrongness(inflection.getVerb().getPreschent().getSing1(), referenceVerb.getPreschentsing1());
        nbrWrongForms += getWrongness(inflection.getVerb().getPreschent().getSing2(), referenceVerb.getPreschentsing2());
        nbrWrongForms += getWrongness(inflection.getVerb().getPreschent().getSing3(), referenceVerb.getPreschentsing3());
        nbrWrongForms += getWrongness(inflection.getVerb().getPreschent().getPlural1(), referenceVerb.getPreschentplural1());
        nbrWrongForms += getWrongness(inflection.getVerb().getPreschent().getPlural2(), referenceVerb.getPreschentplural2());
        nbrWrongForms += getWrongness(inflection.getVerb().getPreschent().getPlural3(), referenceVerb.getPreschentplural3());
        nbrWrongForms += getWrongness(inflection.getVerb().getImperfect().getSing1(), referenceVerb.getImperfectsing1());
        nbrWrongForms += getWrongness(inflection.getVerb().getImperfect().getSing2(), referenceVerb.getImperfectsing2());
        nbrWrongForms += getWrongness(inflection.getVerb().getImperfect().getSing3(), referenceVerb.getImperfectsing3());
        nbrWrongForms += getWrongness(inflection.getVerb().getImperfect().getPlural1(), referenceVerb.getImperfectplural1());
        nbrWrongForms += getWrongness(inflection.getVerb().getImperfect().getPlural2(), referenceVerb.getImperfectplural2());
        nbrWrongForms += getWrongness(inflection.getVerb().getImperfect().getPlural3(), referenceVerb.getImperfectplural3());
        nbrWrongForms += getWrongness(inflection.getVerb().getConjunctiv().getSing1(), referenceVerb.getConjunctivsing1());
        nbrWrongForms += getWrongness(inflection.getVerb().getConjunctiv().getSing2(), referenceVerb.getConjunctivsing2());
        nbrWrongForms += getWrongness(inflection.getVerb().getConjunctiv().getSing3(), referenceVerb.getConjunctivsing3());
        nbrWrongForms += getWrongness(inflection.getVerb().getConjunctiv().getPlural1(), referenceVerb.getConjunctivplural1());
        nbrWrongForms += getWrongness(inflection.getVerb().getConjunctiv().getPlural2(), referenceVerb.getConjunctivplural2());
        nbrWrongForms += getWrongness(inflection.getVerb().getConjunctiv().getPlural3(), referenceVerb.getConjunctivplural3());
        nbrWrongForms += getWrongness(inflection.getVerb().getConjunctiv2().getSing1(), referenceVerb.getConjunctivimpsing1());
        nbrWrongForms += getWrongness(inflection.getVerb().getConjunctiv2().getSing2(), referenceVerb.getConjunctivimpsing2());
        nbrWrongForms += getWrongness(inflection.getVerb().getConjunctiv2().getSing3(), referenceVerb.getConjunctivimpsing3());
        nbrWrongForms += getWrongness(inflection.getVerb().getConjunctiv2().getPlural1(), referenceVerb.getConjunctivimpplural1());
        nbrWrongForms += getWrongness(inflection.getVerb().getConjunctiv2().getPlural2(), referenceVerb.getConjunctivimpplural2());
        nbrWrongForms += getWrongness(inflection.getVerb().getConjunctiv2().getPlural3(), referenceVerb.getConjunctivimpplural3());
        nbrWrongForms += getWrongness(inflection.getVerb().getCundiziunal().getSing1(), referenceVerb.getCundizionalsing1());
        nbrWrongForms += getWrongness(inflection.getVerb().getCundiziunal().getSing2(), referenceVerb.getCundizionalsing2());
        nbrWrongForms += getWrongness(inflection.getVerb().getCundiziunal().getSing3(), referenceVerb.getCundizionalsing3());
        nbrWrongForms += getWrongness(inflection.getVerb().getCundiziunal().getPlural1(), referenceVerb.getCundizionalplural1());
        nbrWrongForms += getWrongness(inflection.getVerb().getCundiziunal().getPlural2(), referenceVerb.getCundizionalplural2());
        nbrWrongForms += getWrongness(inflection.getVerb().getCundiziunal().getPlural3(), referenceVerb.getCundizionalplural3());
        nbrWrongForms += getWrongness(inflection.getVerb().getCundiziunalIndirect().getSing1(), referenceVerb.getCundizionalindsing1());
        nbrWrongForms += getWrongness(inflection.getVerb().getCundiziunalIndirect().getSing2(), referenceVerb.getCundizionalindsing2());
        nbrWrongForms += getWrongness(inflection.getVerb().getCundiziunalIndirect().getSing3(), referenceVerb.getCundizionalindsing3());
        nbrWrongForms += getWrongness(inflection.getVerb().getCundiziunalIndirect().getPlural1(), referenceVerb.getCundizionalindplural1());
        nbrWrongForms += getWrongness(inflection.getVerb().getCundiziunalIndirect().getPlural2(), referenceVerb.getCundizionalindplural2());
        nbrWrongForms += getWrongness(inflection.getVerb().getCundiziunalIndirect().getPlural3(), referenceVerb.getCundizionalindplural3());
        nbrWrongForms += getWrongness(inflection.getVerb().getParticipPerfect().getMsPredicativ(), referenceVerb.getParticipperfectms());
        nbrWrongForms += getWrongness(inflection.getVerb().getParticipPerfect().getFs(), referenceVerb.getParticipperfectfs());
        nbrWrongForms += getWrongness(inflection.getVerb().getParticipPerfect().getMp(), referenceVerb.getParticipperfectmp());
        nbrWrongForms += getWrongness(inflection.getVerb().getParticipPerfect().getFp(), referenceVerb.getParticipperfectfp());
        nbrWrongForms += getWrongness(inflection.getVerb().getImperativ().getSingular(), referenceVerb.getImperativ1());
        nbrWrongForms += getWrongness(inflection.getVerb().getImperativ().getPlural(), referenceVerb.getImperativ2());
        nbrWrongForms += getWrongness(inflection.getVerb().getGerundium(), referenceVerb.getGerundium());
        nbrWrongForms += getWrongness(inflection.getVerb().getInfinitiv(), referenceVerb.getInfinitiv());

        return nbrWrongForms;
    }

    private int getWrongness(String generated, String reference) {
        if (reference == null && generated == null) {
            return 0;
        }
        if (reference == null || generated == null) {
            return 1;
        }

        String[] referenceLines = reference.split("\n");
        String[] generatedLines = generated.split("\n");

        if (referenceLines.length != generatedLines.length) {
            return 1;
        }

        for (int i = 0; i < referenceLines.length; i++) {
            generatedLines[i] = removeExclamationAndTrim(pronounRemover.removePronouns(Language.SURSILVAN, generatedLines[i]));
            referenceLines[i] = removeExclamationAndTrim(pronounRemover.removePronouns(Language.SURSILVAN, referenceLines[i]));
            if (generatedLines[i].startsWith("null")) {
                // entries starting with null are actually invalid and should not be returned, thus set the error factor very high
                return 100;
            }
            if (!Objects.equals(referenceLines[i], generatedLines[i])) {
                return 1;
            }
        }

        return 0;
    }

    private InflectionResultDto findBestResult() {
        Map.Entry<String, InflectionValidationDto> minEntry = null;
        for (Map.Entry<String, InflectionValidationDto> entry : allInflections.entrySet()) {
            if (minEntry == null || entry.getValue().getNbrWrongForms() < minEntry.getValue().getNbrWrongForms()) {
                minEntry = entry;
            }
        }

        assert minEntry != null;
        if (minEntry.getValue().getNbrWrongForms() == 0) {
            return new InflectionResultDto(minEntry.getValue().getInflectionResponse(), true, minEntry.getValue().nbrWrongForms);
        } else {
            return new InflectionResultDto(minEntry.getValue().getInflectionResponse(), false, minEntry.getValue().nbrWrongForms);
        }
    }

    private static String removeExclamationAndTrim(String str) {
        if (str == null) {
            return null;
        }

        str = str.replaceAll("^!+", "").replaceAll("!+$", "").trim();
        return str;
    }
}
