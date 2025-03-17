package ch.pledarigrond.inflection;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.common.data.dictionary.inflection.InflectionType;
import ch.pledarigrond.inflection.model.InflectionSubType;

import java.util.List;

public interface LanguageInflection {
    List<InflectionSubType> getInflectionSubtypes(InflectionType inflectionType);

    InflectionDto guessInflectionSubtype(InflectionType inflectionType, String baseForm, String genus, String flex);

    InflectionDto generateInflection(InflectionType inflectionType, String subTypeId, String baseForm);
}
