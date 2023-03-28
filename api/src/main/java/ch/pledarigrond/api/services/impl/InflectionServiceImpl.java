package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.InflectionService;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.inflection.generation.puter.PuterInflection;
import ch.pledarigrond.inflection.generation.rumantschgrischun.RumantschGrischunInflection;
import ch.pledarigrond.inflection.generation.surmiran.SurmiranInflection;
import ch.pledarigrond.inflection.generation.generic.LanguageInflection;
import ch.pledarigrond.inflection.generation.sutsilvan.SutsilvanInflection;
import ch.pledarigrond.inflection.generation.vallader.ValladerInflection;
import ch.pledarigrond.inflection.model.InflectionResponse;
import ch.pledarigrond.inflection.model.InflectionSubType;
import ch.pledarigrond.inflection.model.InflectionType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class InflectionServiceImpl implements InflectionService {

    private final HashMap<Language, LanguageInflection> inflectionMap = new HashMap<>();

    public List<InflectionSubType> getInflectionTypes(Language language, InflectionType inflectionType) {
        LanguageInflection inflection = getLanguageInflection(language);
        return inflection.getInflectionSubtypes(inflectionType);
    }

    public InflectionResponse guessInflection(Language language, InflectionType inflectionType, String baseForm, String genus, String flex) {
        LanguageInflection inflection = getLanguageInflection(language);
        return inflection.guessInflectionSubtype(inflectionType, baseForm, genus, flex);
    }

    public InflectionResponse generateInflection(Language language, InflectionType inflectionType, String subTypeId, String baseForm) {
        LanguageInflection inflection = getLanguageInflection(language);
        return inflection.generateInflection(inflectionType, subTypeId, baseForm);
    }

    private LanguageInflection getLanguageInflection(Language language) {
        if (inflectionMap.containsKey(language)) {
            return inflectionMap.get(language);
        }

        LanguageInflection inflection;
        switch (language) {
            case RUMANTSCHGRISCHUN:
                inflection = new RumantschGrischunInflection();
                break;
            case SURMIRAN:
                inflection = new SurmiranInflection();
                break;
            case SUTSILVAN:
                inflection = new SutsilvanInflection();
                break;
            case PUTER:
                inflection = new PuterInflection();
                break;
            case VALLADER:
                inflection = new ValladerInflection();
                break;
            default:
                inflection = null;
        }

        inflectionMap.put(language, inflection);
        return inflection;
    }
}
