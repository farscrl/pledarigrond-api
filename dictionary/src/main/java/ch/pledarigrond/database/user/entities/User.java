package ch.pledarigrond.database.user.entities;

import ch.pledarigrond.common.data.common.EditorRole;
import lombok.Data;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;

    @Indexed(unique = true)
    private String email;

    private String password;

    private String firstName;
    private String lastName;

    private boolean isAdmin = false;
    private EditorRole rolePuter = EditorRole.NONE;
    private EditorRole roleRumantschGrischun = EditorRole.NONE;
    private EditorRole roleSurmiran = EditorRole.NONE;
    private EditorRole roleSursilvan = EditorRole.NONE;
    private EditorRole roleSutsilvan = EditorRole.NONE;
    private EditorRole roleVallader = EditorRole.NONE;
    private EditorRole roleNames = EditorRole.NONE;
    private EditorRole roleRegistrations = EditorRole.NONE;

    @CreatedDate
    private Instant createdDate;

    @LastModifiedDate
    private Instant lastModifiedDate;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String lastModifiedBy;
}
