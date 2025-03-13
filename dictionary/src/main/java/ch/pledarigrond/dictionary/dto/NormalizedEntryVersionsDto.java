package ch.pledarigrond.dictionary.dto;

import ch.pledarigrond.common.data.common.EntryVersionInternal;
import ch.pledarigrond.common.data.common.PublicationStatus;
import lombok.Data;

@Data
public class NormalizedEntryVersionsDto {
    private String entryId;
    private PublicationStatus publicationStatus;

    private EntryVersionInternal version;
}
