package ch.pledarigrond.api.services;

import ch.pledarigrond.common.data.common.*;
import ch.pledarigrond.common.data.lucene.IndexStatistics;
import ch.pledarigrond.common.data.lucene.SuggestionField;
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
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public interface LuceneService {

    Page<LemmaVersion> query(Language language, SearchCriteria searchCriteria, Pagination pagination, boolean removeInternalData) throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException, IOException, InvalidTokenOffsetsException;

    Page<LemmaVersion> queryExact(Language language, String phrase, DictionaryLanguage dictionaryLanguage, boolean removeInternalData) throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException, IOException, InvalidTokenOffsetsException;

    IndexStatistics getIndexStatistics(Language language);

    void dropIndex(Language language) throws IndexException;

    void addToIndex(Language language, Iterator<LexEntry> iterator) throws NoDatabaseAvailableException, IndexException;

    void update(Language language, LexEntry entry) throws IOException;

    void delete(Language language, LexEntry entry) throws IOException;

    void updateAll(Language language, List<LexEntry> modified) throws IOException;

    List<String> getSuggestionsForField(Language language, String field, String searchTerm, int limit) throws NoIndexAvailableException, IOException, QueryNodeException, ParseException;

    List<String> getSuggestionsForFieldChoice(Language language, SuggestionField suggestionField, String query, int limit) throws NoIndexAvailableException, QueryNodeException, IOException, ParseException;

    void regenerateSuggestionIndex(Language language) throws Exception;

    String[] getSuggestionForWord(Language language, String word, int numSuggestions, SearchDirection searchDirection) throws Exception;
}
