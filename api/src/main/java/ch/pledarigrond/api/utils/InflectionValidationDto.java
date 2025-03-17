package ch.pledarigrond.api.utils;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import lombok.Data;

@Data
public class InflectionValidationDto {
    InflectionDto inflectionResponse;
    int nbrWrongForms;

    InflectionValidationDto(InflectionDto inflectionResponse) {
        this.inflectionResponse = inflectionResponse;
    }
}
