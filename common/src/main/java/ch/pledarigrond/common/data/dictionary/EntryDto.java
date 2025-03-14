package ch.pledarigrond.common.data.dictionary;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class EntryDto {
    private String entryId;

    private EntryVersionDto current;
    private List<EntryVersionDto> suggestions = new ArrayList<>();
    private List<EntryVersionDto> versions = new ArrayList<>();

    private PublicationStatus publicationStatus;

    private Instant createdDate;
    private Instant lastModifiedDate;
    private String createdBy;
    private String lastModifiedBy;
}