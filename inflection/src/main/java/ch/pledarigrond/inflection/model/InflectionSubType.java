package ch.pledarigrond.inflection.model;

import lombok.Data;

@Data
public class InflectionSubType {
    public String id;
    public String name;
    public String description;
    public boolean isSubtype;

    public InflectionSubType(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public InflectionSubType(String id, String name, String description, boolean isSubtype) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isSubtype = isSubtype;
    }
}
