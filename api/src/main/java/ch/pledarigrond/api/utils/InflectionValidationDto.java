package ch.pledarigrond.api.utils;

import ch.pledarigrond.inflection.model.InflectionResponse;
import lombok.Data;

@Data
public class InflectionValidationDto {
    InflectionResponse inflectionResponse;
    int nbrWrongForms;

    InflectionValidationDto(InflectionResponse inflectionResponse) {
        this.inflectionResponse = inflectionResponse;
    }
}
