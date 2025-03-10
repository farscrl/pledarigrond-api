package ch.pledarigrond.dictionary.entities;

import ch.pledarigrond.common.data.common.DictionaryVersionInternal;
import ch.pledarigrond.common.data.common.PublicationStatus;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Nullable;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Document("entries")
@Data
public class DictionaryEntry {
    private String id;

    @Nullable private DictionaryVersionInternal current;
    private List<DictionaryVersionInternal> suggestions = new ArrayList<>();
    private List<DictionaryVersionInternal> versions = new ArrayList<>();

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

    public void updateCalculatedEventFields() {
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
