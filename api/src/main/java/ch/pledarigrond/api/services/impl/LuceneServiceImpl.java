package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.LuceneService;
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.*;
import ch.pledarigrond.common.data.lucene.IndexStatistics;
import ch.pledarigrond.common.data.lucene.SuggestionField;
import ch.pledarigrond.common.data.user.Pagination;
import ch.pledarigrond.common.data.user.SearchCriteria;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.lucene.core.LuceneIndex;
import ch.pledarigrond.lucene.exceptions.BrokenIndexException;
import ch.pledarigrond.lucene.exceptions.IndexException;
import ch.pledarigrond.lucene.exceptions.InvalidQueryException;
import ch.pledarigrond.lucene.exceptions.NoIndexAvailableException;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.flexible.core.QueryNodeException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Service
public class LuceneServiceImpl implements LuceneService {

    @Autowired
    private PgEnvironment pgEnvironment;

    private final Map<Language, LuceneIndex> luceneIndexMap = new HashMap<>();

    @PostConstruct
    public void initialize() throws IOException {
        luceneIndexMap.put(Language.PUTER, new LuceneIndex(pgEnvironment.getLuceneConfigPuter()));
        luceneIndexMap.put(Language.RUMANTSCHGRISCHUN, new LuceneIndex(pgEnvironment.getLuceneConfigRumantschgrischun()));
        luceneIndexMap.put(Language.SURMIRAN, new LuceneIndex(pgEnvironment.getLuceneConfigSurmiran()));
        luceneIndexMap.put(Language.SURSILVAN, new LuceneIndex(pgEnvironment.getLuceneConfigSursilvan()));
        luceneIndexMap.put(Language.SUTSILVAN, new LuceneIndex(pgEnvironment.getLuceneConfigSutsilvan()));
        luceneIndexMap.put(Language.VALLADER, new LuceneIndex(pgEnvironment.getLuceneConfigVallader()));
    }

    @Override
    public Page<LemmaVersion> query(Language language, SearchCriteria searchCriteria, Pagination pagination, boolean removeInternalData) throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException, IOException, InvalidTokenOffsetsException {
        Page<LemmaVersion> result = luceneIndexMap.get(language).query(searchCriteria, pagination);
        if (removeInternalData) {
            return clean(result);
        }
        return result;
    }

    @Override
    public Page<LemmaVersion> queryExact(Language language, String phrase, DictionaryLanguage dictionaryLanguage, boolean removeInternalData) throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException, IOException, InvalidTokenOffsetsException {
        Page<LemmaVersion> result = luceneIndexMap.get(language).queryExact(phrase, dictionaryLanguage);
        if (removeInternalData) {
            return clean(result);
        }
        return result;
    }

    @Override
    public Page<LemmaVersion> getAllStartingWith(Language language, SearchDirection searchDirection, String prefix, int page) throws NoIndexAvailableException, BrokenIndexException, InvalidQueryException {
        Page<LemmaVersion> result = luceneIndexMap.get(language).getAllStartingWith(searchDirection, prefix, page);
        return clean(result);
    }

    @Override
    public IndexStatistics getIndexStatistics(Language language) {
        return luceneIndexMap.get(language).getIndexStatistics();
    }

    @Override
    public void reloadIndex(Language language) throws NoIndexAvailableException {
        luceneIndexMap.get(language).reloadIndex();
    }

    @Override
    public void dropIndex(Language language) throws IndexException {
        luceneIndexMap.get(language).dropIndex();
    }

    @Override
    public void addToIndex(Language language, Iterator<LexEntry> iterator) throws NoDatabaseAvailableException, IndexException {
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
    public List<String> getSuggestionsForField(Language language, String field, String searchTerm, int limit) throws NoIndexAvailableException, IOException, QueryNodeException, ParseException {
        return luceneIndexMap.get(language).getSuggestionsForField(field, searchTerm, limit);
    }

    @Override
    public List<String> getSuggestionsForFieldChoice(Language language, SuggestionField suggestionField, String query, int limit) throws NoIndexAvailableException, IOException {
        return luceneIndexMap.get(language).getSuggestionsForFieldChoice(suggestionField, query, limit);
    }

    private Page<LemmaVersion> clean(Page<LemmaVersion> result) {
        List<LemmaVersion> entries = result.getContent();
        for (LemmaVersion lemma : entries) {
            clean(lemma);
        }
        return result;
    }

    private LemmaVersion clean(LemmaVersion lemma) {
        lemma.getMaalrValues().keySet().retainAll(LemmaVersion.PUBLIC_MAALR_KEYS);
        lemma.getEntryValues().remove(LemmaVersion.COMMENT);
        lemma.getEntryValues().remove(LemmaVersion.EMAIL);
        return lemma;
    }
}
