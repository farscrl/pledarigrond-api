package ch.pledarigrond.api.services;

import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.common.data.dictionary.inflection.InflectionType;
import ch.pledarigrond.inflection.model.InflectionSubType;

import java.util.List;

public interface InflectionService {

    List<InflectionSubType> getInflectionTypes(Language language, InflectionType inflectionType);

    InflectionDto guessInflection(Language language, InflectionType inflectionType, String baseForm, String genus, String flex);

    InflectionDto generateInflection(Language language, InflectionType inflectionType, String subTypeId, String baseForm);
}
