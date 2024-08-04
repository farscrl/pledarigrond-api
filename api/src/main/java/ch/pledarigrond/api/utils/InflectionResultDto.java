package ch.pledarigrond.api.utils;

import ch.pledarigrond.inflection.model.InflectionResponse;
import lombok.Data;

@Data
public class InflectionResultDto {
    InflectionResponse inflectionResponse;
    boolean isCorrect;

    InflectionResultDto(InflectionResponse inflectionResponse, boolean isCorrect) {
        this.inflectionResponse = inflectionResponse;
        this.isCorrect = isCorrect;
    }
}
