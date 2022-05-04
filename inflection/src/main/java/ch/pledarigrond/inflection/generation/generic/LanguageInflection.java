package ch.pledarigrond.inflection.generation.generic;

import ch.pledarigrond.inflection.model.InflectionResponse;
import ch.pledarigrond.inflection.model.InflectionSubType;
import ch.pledarigrond.inflection.model.InflectionType;

import java.util.HashMap;
import java.util.List;

public interface LanguageInflection {
    List<InflectionSubType> getInflectionSubtypes(InflectionType inflectionType);

    InflectionResponse guessInflectionSubtype(InflectionType inflectionType, String baseForm, String genus, String flex);

    InflectionResponse generateInflection(InflectionType inflectionType, String subTypeId, String baseForm);
}
