package ch.pledarigrond.pronunciation.entities;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "#{T(ch.pledarigrond.pronunciation.utilities.RepositoryNamingClass).getRegistrationsCollectionName()}")
public class Registration {

    private String DStichwort;
    private String RStichwort;
    private String RSemantik;
    private String RSubsemantik;
    private String RGrammatik;
    private String RGenus;
    private String RFlex;
    private String RTags;
    private String RInflectionType;
    private String RInflectionSubtype;

}
