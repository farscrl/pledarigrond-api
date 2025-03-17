package ch.pledarigrond.api.utils;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import lombok.Data;

@Data
public class InflectionResultDto {
    InflectionDto inflectionResponse;
    boolean isCorrect;
    Integer errorCount;

    InflectionResultDto(InflectionDto inflectionResponse, boolean isCorrect, Integer errorCount) {
        this.inflectionResponse = inflectionResponse;
        this.isCorrect = isCorrect;
        this.errorCount = errorCount;
    }
}
