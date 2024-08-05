package ch.pledarigrond.api.utils;

import ch.pledarigrond.inflection.model.InflectionResponse;
import lombok.Data;

@Data
public class InflectionResultDto {
    InflectionResponse inflectionResponse;
    boolean isCorrect;
    Integer errorCount;

    InflectionResultDto(InflectionResponse inflectionResponse, boolean isCorrect, Integer errorCount) {
        this.inflectionResponse = inflectionResponse;
        this.isCorrect = isCorrect;
        this.errorCount = errorCount;
    }
}
