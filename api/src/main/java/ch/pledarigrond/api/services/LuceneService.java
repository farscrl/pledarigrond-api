package ch.pledarigrond.api.services;

import ch.pledarigrond.common.data.common.Language;
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
    /**
     * Queries the index and returns the result in form of a {@link QueryResult}.
     */
    QueryResult query(Language language, SearchCriteria searchCriteria, Pagination pagination, boolean removeInternalData) throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException, IOException, InvalidTokenOffsetsException;

    QueryResult queryExact(Language language, String phrase, boolean firstLanguage, boolean removeInternalData) throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException, IOException, InvalidTokenOffsetsException;

    QueryResult getAllStartingWith(Language language, SearchDirection searchDirection, String prefix, int page) throws NoIndexAvailableException, BrokenIndexException, InvalidQueryException;

    IndexStatistics getIndexStatistics(Language language);

    void reloadIndex(Language language) throws NoIndexAvailableException;

    void dropIndex(Language language) throws IndexException;

    void addToIndex(Language language, Iterator<LexEntry> iterator) throws NoDatabaseAvailableException, IndexException;

    ArrayList<String> getSuggestionsForField(Language language, String fieldName, String query, int limit) throws NoIndexAvailableException, QueryNodeException, IOException, ParseException;

    void update(Language language, LexEntry entry) throws IOException;

    void delete(Language language, LexEntry entry) throws IOException;

    void updateAll(Language language, List<LexEntry> modified) throws IOException;

    List<String> getSuggestionsForFieldChoice(Language language, String id, String query, int limit) throws NoIndexAvailableException, QueryNodeException, IOException, ParseException;
}
