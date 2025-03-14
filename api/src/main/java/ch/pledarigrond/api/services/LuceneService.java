package ch.pledarigrond.api.services;

import ch.pledarigrond.common.data.common.DictionaryLanguage;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.SearchDirection;
import ch.pledarigrond.common.data.dictionary.EntryDto;
import ch.pledarigrond.common.data.dictionary.EntryVersionDto;
import ch.pledarigrond.common.data.lucene.IndexStatistics;
import ch.pledarigrond.common.data.lucene.SuggestionField;
import ch.pledarigrond.common.data.user.Pagination;
import ch.pledarigrond.common.data.user.SearchCriteria;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.lucene.exceptions.BrokenIndexException;
import ch.pledarigrond.lucene.exceptions.IndexException;
import ch.pledarigrond.lucene.exceptions.InvalidQueryException;
import ch.pledarigrond.lucene.exceptions.NoIndexAvailableException;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public interface LuceneService {

    Page<EntryVersionDto> query(SearchCriteria searchCriteria, Pagination pagination, boolean removeInternalData) throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException, IOException;

    Page<EntryVersionDto> searchExactMatches(String phrase, DictionaryLanguage dictionaryLanguage, boolean removeInternalData) throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException, IOException;

    IndexStatistics getIndexStatistics();

    void dropIndex() throws IndexException;

    void addToIndex(Stream<EntryDto> stream) throws NoDatabaseAvailableException, IndexException;

    void update(EntryDto entry) throws IOException;

    void delete(EntryDto entry) throws IOException;

    void updateAll(List<EntryDto> modified) throws IOException;

    List<String> getSuggestionsForField(String field, String searchTerm, int limit) throws NoIndexAvailableException, IOException;

    List<String> getSuggestionsForFieldChoice(SuggestionField suggestionField, String query, int limit) throws NoIndexAvailableException, IOException;

    void regenerateSuggestionIndex(Language language) throws Exception;

    String[] getSuggestionForWord(String word, int numSuggestions, SearchDirection searchDirection) throws Exception;
}
