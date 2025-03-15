package ch.pledarigrond.dictionary.entities;

import ch.pledarigrond.common.data.common.EditorRole;
import ch.pledarigrond.common.data.dictionary.VersionStatus;
import ch.pledarigrond.dictionary.entities.inflection.Inflection;
import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class EntryVersion {

    private String versionId = UUID.randomUUID().toString();

    // info romansh
    private String rmStichwort;
    private String rmStichwortSort;
    private String rmSemantik;
    private String rmSubsemantik;
    private String rmGrammatik;
    private String rmGenus;
    private String rmFlex;
    private String rmTags;
    private String rmRedirect;
    private String rmEtymologie;
    private String rmPhonetics;
    private String rmSynonym;
    private String rmPronunciation;
    private Inflection inflection;

    // info german
    private String deStichwort;
    private String deStichwortSort;
    private String deSemantik;
    private String deSubsemantik;
    private String deGrammatik;
    private String deGenus;
    private String deTags;
    private String deRedirect;

    // general info
    private String categories;
    private List<Example> examples = new ArrayList<>();
    private String userComment;
    private String userEmail;

    // technical info
    private Instant timestamp = Instant.now();
    private VersionStatus versionStatus = VersionStatus.UNVERIFIED;

    private String creator;
    private String creatorIp;
    private EditorRole creatorRole;
    private String verifier;

    private boolean automaticChange = false;
}
