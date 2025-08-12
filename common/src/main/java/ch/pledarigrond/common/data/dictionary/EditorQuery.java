package ch.pledarigrond.common.data.dictionary;

import ch.pledarigrond.common.data.common.EditorRole;
import lombok.Data;

@Data
public class EditorQuery {
    private static final long ONE_DAY = 1000 * 60 * 60 * 24;
    private static final long HALF_YEAR = ONE_DAY * 31 * 6;

    // filters for Entry
    private PublicationStatus state = PublicationStatus.PUBLISHED;
    private boolean excludeAutomaticChanges = true;

    // filters for EntryVersion
    private String userOrIp; // The current text (first name, last name, email, login) used for filtering results
    private EditorRole role;
    private long startTime = System.currentTimeMillis() - HALF_YEAR;
    private long endTime;

    // sort
    private String sortColumn = "timestamp";
    private boolean sortAscending = false;
}
