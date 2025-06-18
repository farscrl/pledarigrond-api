package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.LuceneService;
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.DictionaryLanguage;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.RequestContext;
import ch.pledarigrond.common.data.common.SearchDirection;
import ch.pledarigrond.common.data.dictionary.EntryDto;
import ch.pledarigrond.common.data.dictionary.EntryVersionDto;
import ch.pledarigrond.common.data.lucene.IndexStatistics;
import ch.pledarigrond.common.data.lucene.SuggestionField;
import ch.pledarigrond.common.data.user.Pagination;
import ch.pledarigrond.common.data.user.SearchCriteria;
import ch.pledarigrond.lucene.core.LuceneIndexManager;
import ch.pledarigrond.lucene.exceptions.BrokenIndexException;
import ch.pledarigrond.lucene.exceptions.IndexException;
import ch.pledarigrond.lucene.exceptions.InvalidQueryException;
import ch.pledarigrond.lucene.exceptions.NoIndexAvailableException;
import ch.pledarigrond.lucene.suggestions.SuggestionsIndex;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class LuceneServiceImpl implements LuceneService {

    @Autowired
    private PgEnvironment pgEnvironment;

    private final Map<Language, LuceneIndexManager> luceneIndexMap = new HashMap<>();

    private final Map<Language, SuggestionsIndex> luceneSuggestionsIndexMap = new HashMap<>();

    @PostConstruct
    public void initialize() throws IOException, NoIndexAvailableException {
        luceneIndexMap.put(Language.PUTER, new LuceneIndexManager(pgEnvironment.getLuceneConfigPuter()));
        luceneIndexMap.put(Language.RUMANTSCHGRISCHUN, new LuceneIndexManager(pgEnvironment.getLuceneConfigRumantschgrischun()));
        luceneIndexMap.put(Language.SURMIRAN, new LuceneIndexManager(pgEnvironment.getLuceneConfigSurmiran()));
        luceneIndexMap.put(Language.SURSILVAN, new LuceneIndexManager(pgEnvironment.getLuceneConfigSursilvan()));
        luceneIndexMap.put(Language.SUTSILVAN, new LuceneIndexManager(pgEnvironment.getLuceneConfigSutsilvan()));
        luceneIndexMap.put(Language.VALLADER, new LuceneIndexManager(pgEnvironment.getLuceneConfigVallader()));

        luceneSuggestionsIndexMap.put(Language.PUTER, new SuggestionsIndex(pgEnvironment.getLuceneConfigPuter()));
        luceneSuggestionsIndexMap.put(Language.RUMANTSCHGRISCHUN, new SuggestionsIndex(pgEnvironment.getLuceneConfigRumantschgrischun()));
        luceneSuggestionsIndexMap.put(Language.SURMIRAN, new SuggestionsIndex(pgEnvironment.getLuceneConfigSurmiran()));
        luceneSuggestionsIndexMap.put(Language.SURSILVAN, new SuggestionsIndex(pgEnvironment.getLuceneConfigSursilvan()));
        luceneSuggestionsIndexMap.put(Language.SUTSILVAN, new SuggestionsIndex(pgEnvironment.getLuceneConfigSutsilvan()));
        luceneSuggestionsIndexMap.put(Language.VALLADER, new SuggestionsIndex(pgEnvironment.getLuceneConfigVallader()));
    }

    @Override
    public Page<EntryVersionDto> query(SearchCriteria searchCriteria, Pagination pagination, boolean removeInternalData) throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException {
        Page<EntryVersionDto> result = luceneIndexMap.get(RequestContext.getLanguage()).query(searchCriteria, pagination);
        if (removeInternalData) {
            return clean(result);
        }
        return result;
    }

    @Override
    public Page<EntryVersionDto> searchExactMatches(String phrase, DictionaryLanguage dictionaryLanguage, boolean removeInternalData) throws NoIndexAvailableException, BrokenIndexException {
        Page<EntryVersionDto> result = luceneIndexMap.get(RequestContext.getLanguage()).searchExactMatches(phrase, dictionaryLanguage);
        if (removeInternalData) {
            return clean(result);
        }
        return result;
    }

    @Override
    public IndexStatistics getIndexStatistics() {
        return luceneIndexMap.get(RequestContext.getLanguage()).getIndexStatistics();
    }

    @Override
    public void dropIndex() throws IndexException {
        luceneIndexMap.get(RequestContext.getLanguage()).dropIndex();
    }

    @Override
    public void addToIndex(Stream<EntryDto> stream) throws IndexException {
        luceneIndexMap.get(RequestContext.getLanguage()).addToIndex(stream);
    }

    @Override
    public void update(EntryDto entry) throws IOException {
        luceneIndexMap.get(RequestContext.getLanguage()).update(entry);
    }

    @Override
    public void delete(EntryDto entry) throws IOException {
        luceneIndexMap.get(RequestContext.getLanguage()).delete(entry);
    }

    @Override
    public void updateAll(List<EntryDto> modified) throws IOException {
        for (EntryDto entry : modified) {
            luceneIndexMap.get(RequestContext.getLanguage()).update(entry);
        }
    }

    @Override
    public List<String> getSuggestionsForField(String field, String searchTerm, int limit) throws NoIndexAvailableException, IOException {
        return luceneIndexMap.get(RequestContext.getLanguage()).getSuggestionsForField(field, searchTerm, limit);
    }

    @Override
    public List<String> getSuggestionsForFieldChoice(SuggestionField suggestionField, String query, int limit) throws NoIndexAvailableException, IOException {
        return luceneIndexMap.get(RequestContext.getLanguage()).getSuggestionsForFieldChoice(suggestionField, query, limit);
    }

    @Override
    public void regenerateSuggestionIndex(Language language) throws Exception {
        luceneSuggestionsIndexMap.get(language).reIndex();
    }

    @Override
    public String[] getSuggestionForWord(String word, int numSuggestions, SearchDirection searchDirection) throws Exception {
        return luceneSuggestionsIndexMap.get(RequestContext.getLanguage()).suggestSimilar(word, numSuggestions, searchDirection);
    }

    private Page<EntryVersionDto> clean(Page<EntryVersionDto> result) {
        List<EntryVersionDto> entries = result.getContent();
        for (EntryVersionDto lemma : entries) {
            clean(lemma);
        }
        return result;
    }

    private void clean(EntryVersionDto lemma) {
        lemma.setUserComment("");
        lemma.setUserEmail("");
        lemma.setCreatorIp("");
    }
}
