package ch.pledarigrond.common.data.user;

import ch.pledarigrond.common.data.common.*;
import lombok.Data;

@Data
public class SearchCriteria {
    private String searchPhrase = "";
    private SearchDirection searchDirection = SearchDirection.BOTH;
    private SearchMethod searchMethod = SearchMethod.NORMAL;
    private SortBy sortBy = null;
    private Boolean highlight = false;
    private String gender = null;
    private String grammar = null;
    private String subSemantics = null;
    private String category = null;
    private Boolean onlyAutomaticChanged = false;
    private Boolean excludeAutomaticChanged = false;
    private LemmaVersion.Verification verification = null;
    private AutomaticChangesType automaticChangesType = AutomaticChangesType.ALL;
    private Boolean showReviewLater = null;
}
