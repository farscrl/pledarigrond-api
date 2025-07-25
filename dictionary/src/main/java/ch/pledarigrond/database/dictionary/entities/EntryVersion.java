package ch.pledarigrond.database.dictionary.entities;

import ch.pledarigrond.common.data.common.Action;
import ch.pledarigrond.common.data.common.EditorRole;
import ch.pledarigrond.database.dictionary.entities.inflection.Inflection;
import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
public class EntryVersion implements Cloneable {
    private String versionId = UUID.randomUUID().toString();
    private String entryId;

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
    private String deFlex;
    private String deTags;
    private String deRedirect;

    // general info
    private String categories;
    private List<Example> examples = new ArrayList<>();

    // metadata
    private String userComment;
    private String userEmail;

    // technical info
    private Instant timestamp = Instant.now();
    private Action action;
    private String creator;
    private String creatorIp;
    private EditorRole creatorRole;

    private boolean automaticChange = false;

    private UnusedData unusedData = new UnusedData();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntryVersion version = (EntryVersion) o;
        return Objects.equals(versionId, version.versionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(versionId);
    }

    @Override
    public EntryVersion clone() {
        try {
            EntryVersion cloned = (EntryVersion) super.clone();
            // Deep copy mutable fields
            if (this.examples != null) {
                cloned.examples = new ArrayList<>();
                for (Example ex : this.examples) {
                    cloned.examples.add(ex == null ? null : ex.clone());
                }
            }
            if (this.inflection != null) {
                cloned.inflection = this.inflection.clone();
            }
            if (this.unusedData != null) {
                cloned.unusedData = this.unusedData.clone();
            }
            // Other fields are either immutable or primitives
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
