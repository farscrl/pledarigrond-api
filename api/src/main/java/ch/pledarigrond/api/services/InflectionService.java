package ch.pledarigrond.api.services;

import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.inflection.model.InflectionResponse;
import ch.pledarigrond.inflection.model.InflectionSubType;
import ch.pledarigrond.inflection.model.InflectionType;

import java.util.List;

public interface InflectionService {

    List<InflectionSubType> getInflectionTypes(Language language, InflectionType inflectionType);

    InflectionResponse generateInflection(Language language, InflectionType inflectionType, String subTypeId, String baseForm);
}
