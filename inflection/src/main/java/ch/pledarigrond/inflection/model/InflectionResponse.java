package ch.pledarigrond.inflection.model;

import lombok.Data;

import java.util.HashMap;

@Data
public class InflectionResponse {
    private InflectionSubType inflectionSubType;
    private HashMap<String, String> inflectionValues;

    public InflectionResponse(InflectionSubType inflectionSubType, HashMap<String, String> inflectionValues) {
        this.inflectionSubType = inflectionSubType;
        this.inflectionValues = inflectionValues;
    }
}
