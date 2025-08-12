package ch.pledarigrond.common.data.user;

import ch.pledarigrond.common.data.common.SearchDirection;
import ch.pledarigrond.common.data.common.SearchMethod;
import ch.pledarigrond.common.data.common.SortBy;
import lombok.Data;

@Data
public class LuceneSearchCriteria {
    private String searchPhrase = "";
    private SearchDirection searchDirection = SearchDirection.BOTH;
    private SearchMethod searchMethod = SearchMethod.NORMAL;
    private SortBy sortBy = null;
    private Boolean highlight = false;
    private String gender = null;
    private String grammar = null;
    private String subSemantics = null;
    private String category = null;
    private Boolean excludeAutomaticChanged = false;
}
