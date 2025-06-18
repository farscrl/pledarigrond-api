package ch.pledarigrond.common.data.dictionary;

import ch.pledarigrond.common.data.common.Action;
import ch.pledarigrond.common.data.common.EditorRole;
import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class EntryVersionDto {
    private String versionId = UUID.randomUUID().toString();
    private String entryId;

    // info romansh
    @Size(max = 200) private String rmStichwort;
    @Size(max = 200) private String rmStichwortSort;
    @Size(max = 200) private String rmSemantik;
    @Size(max = 200) private String rmSubsemantik;
    @Size(max = 200) private String rmGrammatik;
    @Size(max = 200) private String rmGenus;
    @Size(max = 200) private String rmFlex;
    @Size(max = 200) private String rmTags;
    @Size(max = 200) private String rmRedirect;
    @Size(max = 200) private String rmEtymologie;
    @Size(max = 200) private String rmPhonetics;
    @Size(max = 200) private String rmSynonym;
    @Size(max = 200) private String rmPronunciation;
    private InflectionDto inflection;

    // info german
    @Size(max = 200) private String deStichwort;
    @Size(max = 200) private String deStichwortSort;
    @Size(max = 200) private String deSemantik;
    @Size(max = 200) private String deSubsemantik;
    @Size(max = 200) private String deGrammatik;
    @Size(max = 200) private String deGenus;
    @Size(max = 200) private String deTags;
    @Size(max = 200) private String deRedirect;

    // general info
    @Size(max = 200) private String categories;
    private List<ExampleDto> examples = new ArrayList<>();
    @Size(max = 500) private String userComment;
    @Size(max = 200) private String userEmail;

    // technical info
    private Instant timestamp;
    private Action action;

    @Size(max = 200) private String creator;
    @Size(max = 200) private String creatorIp;
    private EditorRole creatorRole;

    private boolean automaticChange = false;
}

