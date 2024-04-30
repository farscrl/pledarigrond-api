package ch.pledarigrond.pronunciation.entities;

import ch.pledarigrond.pronunciation.dto.RegistrationStatus;
import lombok.Data;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document(collection = "#{T(ch.pledarigrond.pronunciation.utilities.RepositoryNamingClass).getRegistrationsCollectionName()}")
public class Registration {
    @Id
    private String id;

    private RegistrationStatus status = RegistrationStatus.TODO;

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

    @CreatedDate
    private Instant createdDate;

    @LastModifiedDate
    private Instant lastModifiedDate;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String lastModifiedBy;
}
