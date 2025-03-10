package ch.pledarigrond.dictionary.entities;

import ch.pledarigrond.common.data.common.DictionaryVersion;
import ch.pledarigrond.common.data.common.DictionaryVersionInternal;
import ch.pledarigrond.common.data.common.PublicationStatus;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Nullable;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Document("entries")
@Data
public class DictionaryEntry {
    private String id;

    @Nullable private DictionaryVersion currentlyPublished;
    private List<DictionaryVersionInternal> suggestions = new ArrayList<>();
    private List<DictionaryVersionInternal> versions = new ArrayList<>();

    // calculated fields
    private PublicationStatus publicationStatus;

    // Auditing fields
    @CreatedDate
    private Instant createdDate;

    @LastModifiedDate
    private Instant lastModifiedDate;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
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
        if (currentlyPublished == null && !suggestions.isEmpty()) {
            return PublicationStatus.SUGGESTION;
        }
        if (currentlyPublished != null && suggestions.isEmpty()) {
            return PublicationStatus.PUBLISHED;
        }
        if (currentlyPublished != null && !suggestions.isEmpty()) {
            return PublicationStatus.MODIFIED;
        }
        return PublicationStatus.INVALID;
    }
}
