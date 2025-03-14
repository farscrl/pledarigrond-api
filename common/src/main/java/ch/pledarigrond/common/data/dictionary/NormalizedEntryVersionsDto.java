package ch.pledarigrond.common.data.dictionary;

import lombok.Data;

@Data
public class NormalizedEntryVersionsDto {
    private String entryId;
    private PublicationStatus publicationStatus;

    private EntryVersionDto version;
}
