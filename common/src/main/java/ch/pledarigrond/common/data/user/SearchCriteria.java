package ch.pledarigrond.common.data.user;

import ch.pledarigrond.common.data.common.SearchDirection;
import ch.pledarigrond.common.data.common.SearchMethod;
import lombok.Data;

@Data
public class SearchCriteria {
    private String searchPhrase = "";
    private SearchDirection searchDirection = SearchDirection.BOTH;
    private SearchMethod searchMethod = SearchMethod.NORMAL;
    private Boolean highlight = false;
    private Boolean suggestions = false;
    private String genus = null;
    private String grammar = null;
}
