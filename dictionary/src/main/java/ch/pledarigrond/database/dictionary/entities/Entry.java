package ch.pledarigrond.database.dictionary.entities;

import ch.pledarigrond.common.data.common.Action;
import ch.pledarigrond.common.data.common.UserInfoDto;
import ch.pledarigrond.common.data.dictionary.PublicationStatus;
import ch.pledarigrond.common.exception.dictionary.InvalidDataException;
import ch.pledarigrond.common.exception.dictionary.SuggestionNotFoundException;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Nullable;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document("entries")
@Data
public class Entry {
    @Id
    private String entryId = new ObjectId().toString();

    @Nullable private EntryVersion current;
    private List<EntryVersion> suggestions = new ArrayList<>();
    private List<EntryVersion> versions = new ArrayList<>();

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
        if (publicationStatus == null) {
            publicationStatus = calculateEntryStatus();
        }

        return publicationStatus;
    }

    private PublicationStatus calculateEntryStatus() {
        if (!suggestions.isEmpty()) {
            return PublicationStatus.HAS_SUGGESTION;
        }

        if (current != null) {
            return PublicationStatus.PUBLISHED;
        }

        return PublicationStatus.INVALID;
    }

    public static Entry createEntry(EntryVersion version, boolean asSuggestion, UserInfoDto userInfo) throws InvalidDataException {
        Entry entry = new Entry();

        if (asSuggestion) {
            entry.addVersion(version, userInfo, Action.SUGGESTED_ENTRY);
        } else {
            entry.addVersion(version, userInfo, Action.CREATED_ENTRY);
        }

        return entry;
    }

    public Entry addVersion(EntryVersion version, UserInfoDto userInfo, Action action) throws InvalidDataException {
        validateVersion(version, this, userInfo, action);

        if (action == Action.SUGGESTED_ENTRY || action == Action.SUGGESTED_MODIFICATION) {
            suggestions.add(version);
        } else if (action == Action.ACCEPTED_ENTRY || action == Action.CREATED_ENTRY || action == Action.ACCEPTED_MODIFICATION || action == Action.CREATED_MODIFICATION || action == Action.CHANGED_ORDER) {
            current = version;
        } else if (action == Action.REFUSED_MODIFICATION) {
            // do nothing
        } else {
            throw new InvalidDataException("Invalid action: " + action);
        }

        // only add to versions if it is not already present
        if(versions.stream().noneMatch(v -> v.getVersionId().equals(version.getVersionId()))) {
            versions.add(version);
        }
        this.updateCalculatedFields();
        return this;
    }

    public Entry acceptVersion(String versionId, UserInfoDto userInfo) throws SuggestionNotFoundException {
        EntryVersion version = suggestions.stream().filter(v -> v.getVersionId().equals(versionId)).findFirst().orElseThrow(() -> new SuggestionNotFoundException(versionId));
        suggestions.remove(version);
        version.setTimestamp(Instant.now());
        return addVersion(version, userInfo, Action.ACCEPTED_MODIFICATION);
    }

    public Entry refuseVersion(String versionId, UserInfoDto userInfo) throws SuggestionNotFoundException {
        EntryVersion version = suggestions.stream().filter(v -> v.getVersionId().equals(versionId)).findFirst().orElseThrow(() -> new SuggestionNotFoundException(versionId));
        suggestions.remove(version);
        version.setTimestamp(Instant.now());
        return addVersion(version, userInfo, Action.REFUSED_MODIFICATION);
    }

    public Entry removeHistory() {
        versions = new ArrayList<>();

        if (current != null) {
            versions.add(current);
        }
        versions.addAll(suggestions);
        updateCalculatedFields();
        return this;
    }

    private static void validateVersion(EntryVersion version, Entry entry, UserInfoDto userInfo, Action action) {
        if (version == null) {
            throw new InvalidDataException("version is null");
        }

        if ((version.getRmStichwort() == null || version.getRmStichwort().isEmpty()) && (version.getDeStichwort() == null || version.getDeStichwort().isEmpty())) {
            throw new InvalidDataException("rmStichwort or deStichwort must be set");
        }

        version.setVersionId(UUID.randomUUID().toString());
        version.setEntryId(entry.getEntryId());
        version.setCreator(userInfo.getEmail());
        version.setCreatorIp(userInfo.getIpAddress());
        version.setCreatorRole(userInfo.getRole());
        version.setAction(action);
    }
}
