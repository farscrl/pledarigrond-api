package ch.pledarigrond.common.data.common;

import ch.pledarigrond.common.data.common.inflection.Inflection;
import lombok.Data;

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
}
