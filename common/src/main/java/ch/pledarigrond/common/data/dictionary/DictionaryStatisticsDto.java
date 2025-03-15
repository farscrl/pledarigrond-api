package ch.pledarigrond.common.data.dictionary;

import lombok.Data;

@Data
public class DictionaryStatisticsDto {
    private long numberOfEntries;

    private long numberOfVersions;

    private long numberOfSuggestions;

    private long numberOfApproved;
}
