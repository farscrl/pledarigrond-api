package ch.pledarigrond.common.data.dictionary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DuplicateGroupDto {
    private DuplicateKey duplicateKey;
    private List<EntryVersionDto> duplicateVersions;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DuplicateKey {
        private String rmStichwort;
        private String deStichwort;
        private String rmGenus;
        private String deGenus;
        private String rmGrammatik;
        private String deGrammatik;
        private String rmSubsemantik;
        private String deSubsemantik;
    }
}
