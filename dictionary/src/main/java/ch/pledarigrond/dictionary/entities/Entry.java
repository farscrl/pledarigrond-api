package ch.pledarigrond.dictionary.entities;

import ch.pledarigrond.common.data.common.EntryVersionInternal;
import ch.pledarigrond.common.data.common.PublicationStatus;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Nullable;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Document("entries")
@Data
public class Entry {
    @Id
    private String entryId;

    @Nullable private EntryVersionInternal current;
    private List<EntryVersionInternal> suggestions = new ArrayList<>();
    private List<EntryVersionInternal> versions = new ArrayList<>();

    // calculated fields
    private PublicationStatus publicationStatus;

    // Auditing fields
    // @CreatedDate TODO: re-enable
    private Instant createdDate;

    // @LastModifiedDate TODO: re-enable
    private Instant lastModifiedDate;

    // @CreatedBy TODO: re-enable
    private String createdBy;

    // @LastModifiedBy TODO: re-enable
    private String lastModifiedBy;

    public void updateCalculatedFields() {
        this.publicationStatus = calculateEntryStatus();
    }

    public PublicationStatus getPublicationStatus() {
        if (publicationStatus != null) {
            return publicationStatus;
        }

        return calculateEntryStatus();
    }

    private PublicationStatus calculateEntryStatus() {
        if (current == null && !suggestions.isEmpty()) {
            return PublicationStatus.SUGGESTION;
        }
        if (current != null && suggestions.isEmpty()) {
            return PublicationStatus.PUBLISHED;
        }
        if (current != null && !suggestions.isEmpty()) {
            return PublicationStatus.MODIFIED;
        }
        return PublicationStatus.INVALID;
    }
}
