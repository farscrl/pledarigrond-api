package ch.pledarigrond.lucene.core;

import ch.pledarigrond.common.data.common.SearchDirection;
import ch.pledarigrond.common.data.common.SearchMethod;
import ch.pledarigrond.common.data.user.Pagination;
import ch.pledarigrond.common.data.user.SearchCriteria;

public class IndexTestHelpers {
    public static SearchCriteria getSearchCriteria(SearchDirection direction, SearchMethod method, String searchPhrase) {
        SearchCriteria query = new SearchCriteria();
        query.setSearchDirection(direction);
        query.setSearchMethod(method);
        query.setSearchPhrase(searchPhrase);
        return query;
    }

    public static Pagination getPagination() {
        return new Pagination();
    }
}
