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
}
