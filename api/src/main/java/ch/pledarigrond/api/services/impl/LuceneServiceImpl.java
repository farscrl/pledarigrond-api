package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.LuceneService;
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.data.common.QueryResult;
import ch.pledarigrond.common.data.common.SearchDirection;
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

    /**
     * Queries the index and returns the result in form of a {@link QueryResult}.
     */
    public QueryResult query(SearchCriteria searchCriteria, Pagination pagination, boolean removeInternalData) throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException, IOException, InvalidTokenOffsetsException {
        QueryResult result = dictionary.query(searchCriteria, pagination);
        if(removeInternalData) return clean(result);
        return result;
    }

    public QueryResult queryExact(String phrase, boolean firstLanguage, boolean removeInternalData) throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException, IOException, InvalidTokenOffsetsException {
        QueryResult result = dictionary.queryExact(phrase, firstLanguage);
        if(removeInternalData) return clean(result);
        return result;
    }

    public QueryResult getAllStartingWith(SearchDirection searchDirection, String prefix, int page) throws NoIndexAvailableException, BrokenIndexException, InvalidQueryException {
        QueryResult result = dictionary.getAllStartingWith(searchDirection, prefix, page);
        return clean(result);
    }

    public IndexStatistics getIndexStatistics() {
        return dictionary.getIndexStatistics();
    }

    public void reloadIndex() throws NoIndexAvailableException {
        dictionary.reloadIndex();
    }

    public void dropIndex() throws IndexException {
        dictionary.dropIndex();
    }

    public void addToIndex(Iterator<LexEntry> iterator) throws NoDatabaseAvailableException, IndexException {
        dictionary.addToIndex(iterator);
    }

    public ArrayList<String> getSuggestionsForField(String fieldName, String query, int limit) throws NoIndexAvailableException, QueryNodeException, IOException, ParseException {
        return dictionary.getSuggestionsForField(fieldName, query, limit);
    }

    public void update(LexEntry entry) throws IOException {
        dictionary.update(entry);
    }

    public void delete(LexEntry entry) throws IOException {
        dictionary.delete(entry);
    }

    public void updateAll(List<LexEntry> modified) throws IOException {
        for (LexEntry entry : modified) {
            dictionary.update(entry);
        }
    }

    public List<String> getSuggestionsForFieldChoice(String id, String query, int limit) throws NoIndexAvailableException, QueryNodeException, IOException, ParseException {
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
