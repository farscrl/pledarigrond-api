package ch.pledarigrond.pronunciation.entities;

import ch.pledarigrond.pronunciation.dto.RegistrationStatus;
import lombok.Data;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "#{T(ch.pledarigrond.pronunciation.utilities.RepositoryNamingClass).getRegistrationsCollectionName()}")
public class Registration {
    @Id
    private String id;

    private RegistrationStatus status = RegistrationStatus.TODO;

    private String deStichwort;
    private String rmStichwort;
    private String rmSemantik;
    private String rmSubsemantik;
    private String rmGrammatik;
    private String rmGenus;
    private String rmFlex;
    private String rmTags;
    private String rmInflectionType;
    private String rmInflectionSubtype;

    private String speakerComment;
    private String reviewerComment;

    private List<String> lemmaIds = new ArrayList<>();

    @CreatedDate
    private Instant createdDate;

    @LastModifiedDate
    private Instant lastModifiedDate;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String lastModifiedBy;
}
