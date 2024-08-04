package ch.pledarigrond.api.utils;

import ch.pledarigrond.api.dtos.VerbDto;
import ch.pledarigrond.api.services.InflectionService;
import ch.pledarigrond.api.services.SursilvanVerbService;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.inflection.model.InflectionResponse;
import ch.pledarigrond.inflection.model.InflectionType;
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
            InflectionResponse inflectionResponse = inflectionService.guessInflection(Language.SURSILVAN, InflectionType.V, baseForm, null, null);
            return new InflectionResultDto(inflectionResponse, false);
        }

        generateAllInflections();
        if (allInflections.isEmpty()) {
            InflectionResponse inflectionResponse = inflectionService.guessInflection(Language.SURSILVAN, InflectionType.V, baseForm, null, null);
            return new InflectionResultDto(inflectionResponse, false);
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
                InflectionResponse inflectionResponse = inflectionService.generateInflection(Language.SURSILVAN, InflectionType.V, constant, baseForm);
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

    private int validateInflection(InflectionResponse inflectionResponse) {
        int nbrWrongForms = 0;

        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("preschentsing1"), referenceVerb.getPreschentsing1());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("preschentsing1"), referenceVerb.getPreschentsing1());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("preschentsing2"), referenceVerb.getPreschentsing2());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("preschentsing3"), referenceVerb.getPreschentsing3());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("preschentplural1"), referenceVerb.getPreschentplural1());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("preschentplural2"), referenceVerb.getPreschentplural2());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("preschentplural3"), referenceVerb.getPreschentplural3());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("imperfectsing1"), referenceVerb.getImperfectsing1());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("imperfectsing2"), referenceVerb.getImperfectsing2());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("imperfectsing3"), referenceVerb.getImperfectsing3());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("imperfectplural1"), referenceVerb.getImperfectplural1());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("imperfectplural2"), referenceVerb.getImperfectplural2());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("imperfectplural3"), referenceVerb.getImperfectplural3());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("conjunctivsing1"), referenceVerb.getConjunctivsing1());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("conjunctivsing2"), referenceVerb.getConjunctivsing2());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("conjunctivsing3"), referenceVerb.getConjunctivsing3());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("conjunctivplural1"), referenceVerb.getConjunctivplural1());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("conjunctivplural2"), referenceVerb.getConjunctivplural2());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("conjunctivplural3"), referenceVerb.getConjunctivplural3());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("conjunctiv2sing1"), referenceVerb.getConjunctivimpsing1());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("conjunctiv2sing2"), referenceVerb.getConjunctivimpsing2());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("conjunctiv2sing3"), referenceVerb.getConjunctivimpsing3());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("conjunctiv2plural1"), referenceVerb.getConjunctivimpplural1());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("conjunctiv2plural2"), referenceVerb.getConjunctivimpplural2());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("conjunctiv2plural3"), referenceVerb.getConjunctivimpplural3());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("cundizionalsing1"), referenceVerb.getCundizionalsing1());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("cundizionalsing2"), referenceVerb.getCundizionalsing2());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("cundizionalsing3"), referenceVerb.getCundizionalsing3());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("cundizionalplural1"), referenceVerb.getCundizionalplural1());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("cundizionalplural2"), referenceVerb.getCundizionalplural2());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("cundizionalplural3"), referenceVerb.getCundizionalplural3());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("cundizionalindirectsing1"), referenceVerb.getCundizionalindsing1());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("cundizionalindirectsing2"), referenceVerb.getCundizionalindsing2());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("cundizionalindirectsing3"), referenceVerb.getCundizionalindsing3());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("cundizionalindirectplural1"), referenceVerb.getCundizionalindplural1());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("cundizionalindirectplural2"), referenceVerb.getCundizionalindplural2());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("cundizionalindirectplural3"), referenceVerb.getCundizionalindplural3());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("participperfectmspredicativ"), referenceVerb.getParticipperfectms());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("participperfectfs"), referenceVerb.getParticipperfectfs());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("participperfectmp"), referenceVerb.getParticipperfectmp());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("participperfectfp"), referenceVerb.getParticipperfectfp());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("imperativ1"), referenceVerb.getImperativ1());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("imperativ2"), referenceVerb.getImperativ2());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("gerundium"), referenceVerb.getGerundium());
        nbrWrongForms += getWrongness(inflectionResponse.getInflectionValues().get("infinitiv"), referenceVerb.getInfinitiv());

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
            return new InflectionResultDto(minEntry.getValue().getInflectionResponse(), true);
        } else {
            return new InflectionResultDto(minEntry.getValue().getInflectionResponse(), false);
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
