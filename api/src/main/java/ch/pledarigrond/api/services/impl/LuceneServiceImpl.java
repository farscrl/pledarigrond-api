package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.LuceneService;
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.*;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class LuceneServiceImpl implements LuceneService {

    @Autowired
    private PgEnvironment pgEnvironment;

    private final Map<Language, LuceneIndexManager> luceneIndexMap = new HashMap<>();

    private final Map<Language, SuggestionsIndex> luceneSuggestionsIndexMap = new HashMap<>();

    @PostConstruct
    public void initialize() throws IOException {
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
    public Page<LemmaVersion> query(Language language, SearchCriteria searchCriteria, Pagination pagination, boolean removeInternalData) throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException {
        Page<LemmaVersion> result = luceneIndexMap.get(language).query(searchCriteria, pagination);
        if (removeInternalData) {
            return clean(result);
        }
        return result;
    }

    @Override
    public Page<LemmaVersion> searchExactMatches(Language language, String phrase, DictionaryLanguage dictionaryLanguage, boolean removeInternalData) throws NoIndexAvailableException, BrokenIndexException {
        Page<LemmaVersion> result = luceneIndexMap.get(language).searchExactMatches(phrase, dictionaryLanguage);
        if (removeInternalData) {
            return clean(result);
        }
        return result;
    }

    @Override
    public IndexStatistics getIndexStatistics(Language language) {
        return luceneIndexMap.get(language).getIndexStatistics();
    }

    @Override
    public void dropIndex(Language language) throws IndexException {
        luceneIndexMap.get(language).dropIndex();
    }

    @Override
    public void addToIndex(Language language, Iterator<LexEntry> iterator) throws IndexException {
        luceneIndexMap.get(language).addToIndex(iterator);
    }

    @Override
    public void update(Language language, LexEntry entry) throws IOException {
        luceneIndexMap.get(language).update(entry);
    }

    @Override
    public void delete(Language language, LexEntry entry) throws IOException {
        luceneIndexMap.get(language).delete(entry);
    }

    @Override
    public void updateAll(Language language, List<LexEntry> modified) throws IOException {
        for (LexEntry entry : modified) {
            luceneIndexMap.get(language).update(entry);
        }
    }

    @Override
    public List<String> getSuggestionsForField(Language language, String field, String searchTerm, int limit) throws NoIndexAvailableException, IOException {
        return luceneIndexMap.get(language).getSuggestionsForField(field, searchTerm, limit);
    }

    @Override
    public List<String> getSuggestionsForFieldChoice(Language language, SuggestionField suggestionField, String query, int limit) throws NoIndexAvailableException, IOException {
        return luceneIndexMap.get(language).getSuggestionsForFieldChoice(suggestionField, query, limit);
    }

    @Override
    public void regenerateSuggestionIndex(Language language) throws Exception {
        luceneSuggestionsIndexMap.get(language).reIndex();
    }

    @Override
    public String[] getSuggestionForWord(Language language, String word, int numSuggestions, SearchDirection searchDirection) throws Exception {
        return luceneSuggestionsIndexMap.get(language).suggestSimilar(word, numSuggestions, searchDirection);
    }

    private Page<LemmaVersion> clean(Page<LemmaVersion> result) {
        List<LemmaVersion> entries = result.getContent();
        for (LemmaVersion lemma : entries) {
            clean(lemma);
        }
        return result;
    }

    private void clean(LemmaVersion lemma) {
        lemma.getPgValues().keySet().retainAll(LemmaVersion.PUBLIC_PG_KEYS);
        lemma.getLemmaValues().remove(LemmaVersion.COMMENT);
        lemma.getLemmaValues().remove(LemmaVersion.EMAIL);
        lemma.getLemmaValues().remove(LemmaVersion.IP_ADDRESS);
    }
}
