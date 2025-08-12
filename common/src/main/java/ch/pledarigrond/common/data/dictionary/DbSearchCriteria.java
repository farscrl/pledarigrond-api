package ch.pledarigrond.common.data.dictionary;

import ch.pledarigrond.common.data.common.EditorRole;
import ch.pledarigrond.common.data.common.SearchDirection;
import ch.pledarigrond.common.data.common.SearchMethod;
import ch.pledarigrond.common.data.dictionary.inflection.InflectionType;
import lombok.Data;

@Data
public class DbSearchCriteria {
    private static final long ONE_DAY = 1000 * 60 * 60 * 24;
    private static final long HALF_YEAR = ONE_DAY * 31 * 6;

    // filters for Entry
    private String searchPhrase = "";
    private SearchDirection searchDirection = SearchDirection.BOTH;
    private SearchMethod searchMethod = SearchMethod.NORMAL;
    private PublicationStatus state = PublicationStatus.PUBLISHED;
    private Boolean onlyAutomaticChanged = false;
    private boolean excludeAutomaticChanges = true;
    private InflectionType inflectionType = null;
    private Boolean showReviewLater = null;

    // filters for EntryVersion
    private String userOrIp; // The current text (first name, last name, email, login) used for filtering results
    private EditorRole role;
    private long startTime = System.currentTimeMillis() - HALF_YEAR;
    private long endTime;

    // sort
    private String sortColumn = "timestamp";
    private boolean sortAscending = false;
}
