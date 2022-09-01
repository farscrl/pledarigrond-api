package ch.pledarigrond.names.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Name {

    @Id
    private String id;

    @Indexed
    private String nameRumantschGrischun;

    @Indexed
    private String nameGerman;

    @Indexed
    private Category category;

    @Indexed
    private String nameSursilvan;

    @Indexed
    private String nameSutsilvan;

    @Indexed
    private String nameSurmiran;

    @Indexed
    private String namePuter;

    @Indexed
    private String nameVallader;

    public Name(String id, String nameRumantschGrischun, String nameGerman, Category category, String nameSursilvan, String nameSutsilvan, String nameSurmiran, String namePuter, String nameVallader) {
        this.id = id;
        this.nameRumantschGrischun = nameRumantschGrischun;
        this.nameGerman = nameGerman;
        this.category = category;
        this.nameSursilvan = nameSursilvan;
        this.nameSutsilvan = nameSutsilvan;
        this.nameSurmiran = nameSurmiran;
        this.namePuter = namePuter;
        this.nameVallader = nameVallader;
    }

    @Override
    public String toString() {
        return nameRumantschGrischun + " (" + category + ")";
    }
}
