package ch.pledarigrond.lucene.core;

import ch.pledarigrond.common.data.common.SearchDirection;
import ch.pledarigrond.common.data.common.SearchMethod;
import ch.pledarigrond.common.data.user.LuceneSearchCriteria;
import ch.pledarigrond.common.data.user.Pagination;

public class IndexTestHelpers {
    public static LuceneSearchCriteria getSearchCriteria(SearchDirection direction, SearchMethod method, String searchPhrase) {
        LuceneSearchCriteria query = new LuceneSearchCriteria();
        query.setSearchDirection(direction);
        query.setSearchMethod(method);
        query.setSearchPhrase(searchPhrase);
        return query;
    }

    public static Pagination getPagination() {
        return new Pagination();
    }
}
