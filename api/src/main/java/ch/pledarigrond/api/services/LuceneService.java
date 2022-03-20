package ch.pledarigrond.api.services;

import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.data.common.QueryResult;
import ch.pledarigrond.common.data.common.SearchDirection;
import ch.pledarigrond.common.data.lucene.IndexStatistics;
import ch.pledarigrond.common.data.user.Pagination;
import ch.pledarigrond.common.data.user.SearchCriteria;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.lucene.exceptions.BrokenIndexException;
import ch.pledarigrond.lucene.exceptions.IndexException;
import ch.pledarigrond.lucene.exceptions.InvalidQueryException;
import ch.pledarigrond.lucene.exceptions.NoIndexAvailableException;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.flexible.core.QueryNodeException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public interface LuceneService {
    QueryResult query(SearchCriteria searchCriteria, Pagination pagination, boolean removeInternalData) throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException, IOException, InvalidTokenOffsetsException;

    QueryResult queryExact(String phrase, boolean firstLanguage, boolean removeInternalData) throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException, IOException, InvalidTokenOffsetsException;

    QueryResult getAllStartingWith(SearchDirection searchDirection, String prefix, int page) throws NoIndexAvailableException, BrokenIndexException, InvalidQueryException;

    IndexStatistics getIndexStatistics();

    void reloadIndex() throws NoIndexAvailableException;

    void dropIndex() throws IndexException;

    void addToIndex(Iterator<LexEntry> iterator) throws NoDatabaseAvailableException, IndexException;

    ArrayList<String> getSuggestionsForField(String fieldName, String query, int limit) throws NoIndexAvailableException, QueryNodeException, IOException, ParseException;

    void update(LexEntry entry) throws IOException;

    void delete(LexEntry entry) throws IOException;

    void updateAll(List<LexEntry> modified) throws IOException;

    List<String> getSuggestionsForFieldChoice(String id, String query, int limit) throws NoIndexAvailableException, QueryNodeException, IOException, ParseException;
}
