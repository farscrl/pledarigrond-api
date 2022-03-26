package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.LuceneService;
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.*;
import ch.pledarigrond.common.data.lucene.IndexStatistics;
import ch.pledarigrond.common.data.user.Pagination;
import ch.pledarigrond.common.data.user.SearchCriteria;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.lucene.core.Dictionary;
import ch.pledarigrond.lucene.exceptions.BrokenIndexException;
import ch.pledarigrond.lucene.exceptions.IndexException;
import ch.pledarigrond.lucene.exceptions.InvalidQueryException;
import ch.pledarigrond.lucene.exceptions.NoIndexAvailableException;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.flexible.core.QueryNodeException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class LuceneServiceImpl implements LuceneService {

    @Autowired
    private PgEnvironment pgEnvironment;

    private Dictionary dictionary;

    @PostConstruct
    public void initialize() throws IOException {
        dictionary = new Dictionary(pgEnvironment.getLuceneConfig());
    }

    @Override
    public QueryResult query(Language language, SearchCriteria searchCriteria, Pagination pagination, boolean removeInternalData) throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException, IOException, InvalidTokenOffsetsException {
        QueryResult result = dictionary.query(searchCriteria, pagination);
        if (removeInternalData) {
            return clean(result);
        }
        return result;
    }

    @Override
    public QueryResult queryExact(Language language, String phrase, boolean firstLanguage, boolean removeInternalData) throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException, IOException, InvalidTokenOffsetsException {
        QueryResult result = dictionary.queryExact(phrase, firstLanguage);
        if (removeInternalData) {
            return clean(result);
        }
        return result;
    }

    @Override
    public QueryResult getAllStartingWith(Language language, SearchDirection searchDirection, String prefix, int page) throws NoIndexAvailableException, BrokenIndexException, InvalidQueryException {
        QueryResult result = dictionary.getAllStartingWith(searchDirection, prefix, page);
        return clean(result);
    }

    @Override
    public IndexStatistics getIndexStatistics(Language language) {
        return dictionary.getIndexStatistics();
    }

    @Override
    public void reloadIndex(Language language) throws NoIndexAvailableException {
        dictionary.reloadIndex();
    }

    @Override
    public void dropIndex(Language language) throws IndexException {
        dictionary.dropIndex();
    }

    @Override
    public void addToIndex(Language language, Iterator<LexEntry> iterator) throws NoDatabaseAvailableException, IndexException {
        dictionary.addToIndex(iterator);
    }

    @Override
    public ArrayList<String> getSuggestionsForField(Language language, String fieldName, String query, int limit) throws NoIndexAvailableException, QueryNodeException, IOException, ParseException {
        return dictionary.getSuggestionsForField(fieldName, query, limit);
    }

    @Override
    public void update(Language language, LexEntry entry) throws IOException {
        dictionary.update(entry);
    }

    @Override
    public void delete(Language language, LexEntry entry) throws IOException {
        dictionary.delete(entry);
    }

    @Override
    public void updateAll(Language language, List<LexEntry> modified) throws IOException {
        for (LexEntry entry : modified) {
            dictionary.update(entry);
        }
    }

    @Override
    public List<String> getSuggestionsForFieldChoice(Language language, String id, String query, int limit) throws NoIndexAvailableException, QueryNodeException, IOException, ParseException {
        return dictionary.getSuggestionsForFieldChoice(id, query, limit);
    }

    private QueryResult clean(QueryResult result) {
        List<LemmaVersion> entries = result.getEntries();
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
