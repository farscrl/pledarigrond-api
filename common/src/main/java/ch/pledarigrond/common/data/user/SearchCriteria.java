package ch.pledarigrond.common.data.user;

import ch.pledarigrond.common.data.common.SearchDirection;
import ch.pledarigrond.common.data.common.SearchMethod;
import lombok.Data;

@Data
public class SearchCriteria {
    public String searchPhrase = "";
    public SearchDirection searchDirection = SearchDirection.BOTH;
    public SearchMethod searchMethod = SearchMethod.NORMAL;
    public Boolean highlight = false;
    public Boolean suggestions = false;
}
