package ch.pledarigrond.lucene.core;

import ch.pledarigrond.common.config.LuceneConfiguration;
import ch.pledarigrond.common.data.common.*;
import ch.pledarigrond.common.data.dictionary.EntryDto;
import ch.pledarigrond.common.data.dictionary.EntryVersionDto;
import ch.pledarigrond.common.data.dictionary.inflection.InflectionType;
import ch.pledarigrond.common.data.lucene.IndexStatistics;
import ch.pledarigrond.common.data.lucene.SuggestionField;
import ch.pledarigrond.common.data.user.LuceneSearchCriteria;
import ch.pledarigrond.common.data.user.Pagination;
import ch.pledarigrond.common.util.PronunciationNormalizer;
import ch.pledarigrond.lucene.exceptions.BrokenIndexException;
import ch.pledarigrond.lucene.exceptions.IndexException;
import ch.pledarigrond.lucene.exceptions.InvalidQueryException;
import ch.pledarigrond.lucene.exceptions.NoIndexAvailableException;
import ch.pledarigrond.lucene.util.FN;
import ch.pledarigrond.lucene.util.FieldTransformer;
import ch.pledarigrond.lucene.util.IndexCommandQueue;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.StoredFields;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.SortField.Type;
import org.apache.lucene.search.grouping.GroupDocs;
import org.apache.lucene.search.grouping.GroupingSearch;
import org.apache.lucene.search.grouping.TopGroups;
import org.apache.lucene.util.BytesRef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class is responsible for managing the Lucene index utilized by PG
 * and offers all necessary methods for performing CRUD-like operations.
 * Index modifications are executed sequentially using an {@link IndexCommandQueue}.
 */
public class LuceneIndexManager {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Language language;

    private final NumberFormat formatter;

    private final Map<Language, LuceneIndexFilesystem> luceneIndexFilesystem = new HashMap<>();

    private final IndexCommandQueue queue;

    public LuceneIndexManager(LuceneConfiguration luceneConfiguration) throws IOException, NoIndexAvailableException {
        this.language = luceneConfiguration.getLanguage();
        queue = IndexCommandQueue.getInstance(this.language);
        formatter = (NumberFormat) NumberFormat.getNumberInstance().clone();
        formatter.setMaximumFractionDigits(3);
        logger.info("Created new index.");
        setLuceneConfiguration(luceneConfiguration);
    }

    public void setLuceneConfiguration(LuceneConfiguration luceneConfiguration) throws IOException, NoIndexAvailableException {
        luceneIndexFilesystem.put(this.language, new LuceneIndexFilesystem(luceneConfiguration));
        luceneIndexFilesystem.get(this.language).initialize();
        luceneIndexFilesystem.get(this.language).resetIndexDirectory();
    }

    public Page<EntryVersionDto> query(LuceneSearchCriteria luceneSearchCriteria, Pagination pagination) throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException {
        long start = System.nanoTime();

        // normalize setSearchPhrase (we remove pronunciation marks)
        luceneSearchCriteria.setSearchPhrase(PronunciationNormalizer.normalizePronunciation(luceneSearchCriteria.getSearchPhrase()));

        boolean hasAdminRole = false;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            hasAdminRole = authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        }

        validatePagination(pagination, hasAdminRole);

        long s1 = System.nanoTime();
        Query query = buildQuery(luceneSearchCriteria);
        TopDocs docs;


        SortField[] fields = new SortField[5];
        fields[0] = SortField.FIELD_SCORE;

        if (luceneSearchCriteria.getSortBy() == null) {
            if (luceneSearchCriteria.getSearchDirection() == SearchDirection.ROMANSH) {
                luceneSearchCriteria.setSortBy(SortBy.ROMANSH);
            } else {
                luceneSearchCriteria.setSortBy(SortBy.GERMAN);
            }
        }

        if (luceneSearchCriteria.getSortBy() == SortBy.ROMANSH) {
            fields[1] = new SortField(FN.rmStichwort, Type.STRING);
            fields[2] = new SortField(FN.rmStichwortSort, Type.INT);
            fields[3] = new SortField(FN.deStichwort, Type.STRING);
            fields[4] = new SortField(FN.deStichwortSort, Type.INT);
        } else {
            fields[1] = new SortField(FN.deStichwort, Type.STRING);
            fields[2] = new SortField(FN.deStichwortSort, Type.INT);
            fields[3] = new SortField(FN.rmStichwort, Type.STRING);
            fields[4] = new SortField(FN.rmStichwortSort, Type.INT);
        }
        Sort sort = new Sort(fields);
        Page<EntryVersionDto> result;

        int pageSize = pagination.getPageSize();
        int pageNr = pagination.getPage();
        long e1 = System.nanoTime();
        try {
            long s2 = System.nanoTime();
            docs = luceneIndexFilesystem.get(language).getSearcher().search(query, pageSize * (pageNr + 1), sort);
            long e2 = System.nanoTime();
            result = toEntryVersionPagination(docs, pageNr, pageSize);
            if (logger.isDebugEnabled()) {
                logger.debug("Time to build query: {}, Time to execute query: {}", (e1 - s1) / 1000000, (e2 - s2) / 1000000);
            }
        } catch (IOException e) {
            throw new BrokenIndexException("Failed to access index", e);
        }
        long end = System.nanoTime();
        double time = (end - start) / 1000000D;
        // Warn if query takes more than 100 ms.
        if (time > 100) {
            logger.info("Slow query: {} ms for {}", formatter.format(time), luceneSearchCriteria);
        } else if (logger.isDebugEnabled()) {
            logger.debug("Processed query in {} ms :{}", formatter.format(time), luceneSearchCriteria);
        }
        return result;
    }

    private void validatePagination(Pagination pagination, boolean hasAdminRole) {
        if (pagination.getPage() < 0) {
            pagination.setPage(0);
        }
        if (!hasAdminRole) {
            if (pagination.getPageSize() > 200) {
                pagination.setPageSize(200);
            }
        }
        if (pagination.getPageSize() < 1) {
            pagination.setPageSize(1);
        }
    }

    private Page<EntryVersionDto> toEntryVersionPagination(TopDocs docs, int page, int pageSize) throws NoIndexAvailableException, IOException {
        final ArrayList<EntryVersionDto> results = new ArrayList<>(pageSize);
        final ScoreDoc[] scoreDocs = docs.scoreDocs;
        IndexSearcher searcher = luceneIndexFilesystem.get(language).getSearcher();
		StoredFields storedFields = searcher.getIndexReader().storedFields();
        for (int i = page * pageSize; i < scoreDocs.length && i < page * pageSize + pageSize; i++) {

            Document doc = storedFields.document(scoreDocs[i].doc);
            EntryVersionDto e = FieldTransformer.getEntryVersion(language, doc);
            results.add(e);
        }

        Pageable pageable = PageRequest.of(page, pageSize);
        return new PageImpl<>(results, pageable, docs.totalHits.value);
    }

    public Page<EntryVersionDto> searchExactMatches(String phrase, DictionaryLanguage dictionaryLanguage) throws NoIndexAvailableException, BrokenIndexException {
        List<Query> queries;
        SortField sortField;
        if (dictionaryLanguage == DictionaryLanguage.GERMAN) {
            queries = BuilderRegistry.getInstance().getBuilder(SearchDirection.GERMAN, SearchMethod.EXACT).transform(phrase);
            sortField = new SortField(FN.deStichwortSort, Type.INT);
        } else {
            queries = BuilderRegistry.getInstance().getBuilder(SearchDirection.ROMANSH, SearchMethod.EXACT).transform(phrase);
            sortField = new SortField(FN.rmStichwortSort, Type.INT);
        }
        int pageSize = 120;
        try {
            BooleanQuery.Builder qBuilder = new BooleanQuery.Builder();
            for (Query q : queries) {
                qBuilder.add(q, Occur.SHOULD);
            }
            TopDocs docs = luceneIndexFilesystem.get(language).getSearcher().search(qBuilder.build(), pageSize, new Sort(sortField));

            return toEntryVersionPagination(docs, 0, pageSize);
        } catch (IOException e) {
            throw new BrokenIndexException("Broken index!", e);
        }
    }

    public IndexStatistics getIndexStatistics() {
        final IndexStatistics statistics = new IndexStatistics();
        try {
            queue.push(language -> {
                IndexReader reader = luceneIndexFilesystem.get(language).getSearcher().getIndexReader();
                IndexSearcher searcher = new IndexSearcher(reader);

                // Find total number of entries
                statistics.setNumberOfEntries(reader.numDocs());

                // Find last updated
                statistics.setLastUpdated(luceneIndexFilesystem.get(language).getLastUpdated());

                // Find inflection count
                HashMap<String, Integer> inflectionCount = new HashMap<>();
                String[] inflectionTypes = new String[]{InflectionType.NOUN.name(), InflectionType.VERB.name(), InflectionType.ADJECTIVE.name(), InflectionType.PRONOUN.name(), InflectionType.OTHER.name()};
                for (String inflectionType : inflectionTypes) {
                    Term term = new Term(FN.inflectionType, inflectionType);
                    Query query = new TermQuery(term);
                    TopDocs results = searcher.search(query, Integer.MAX_VALUE);
                    inflectionCount.put(inflectionType, (int) results.totalHits.value);
                }
                statistics.setInflectionCount(inflectionCount);
            });
            return statistics;
        } catch (Exception e) {
            return new IndexStatistics();
        }
    }

    public ArrayList<String> getSuggestionsForField(String fieldName, String searchTerm, int limit) throws NoIndexAvailableException, IOException {
        Query query = getSuggestionsQuery(fieldName, searchTerm);
        Set<String> allValues = new TreeSet<>();

        getSortedResults(searchTerm, limit, query, allValues, fieldName);
        return getLimitedResults(limit, allValues);
    }

    // p.ex. grammar, gender
    public ArrayList<String> getSuggestionsForFieldChoice(SuggestionField suggestionField, String value, int limit) throws NoIndexAvailableException, IOException {
        LuceneSearchCriteria luceneSearchCriteria = new LuceneSearchCriteria();
        Set<String[]> fields = Set.of();
        if (suggestionField == SuggestionField.GENDER) {
            luceneSearchCriteria.setGender(value);
            fields = Set.of(new String[]{"deGenus_na_nw_l_t-STRING", FN.deGenus}, new String[]{"rmGenus_na_nw_l_t-STRING", FN.rmGenus});
        } else if (suggestionField == SuggestionField.GRAMMAR) {
            luceneSearchCriteria.setGrammar(value);
            fields = Set.of(new String[]{"deGrammatik_na_nw_l_t-STRING", FN.deGrammatik}, new String[]{"rmGrammatik_na_nw_l_t-STRING", FN.rmGrammatik});
        }
        Query query = buildQuery(luceneSearchCriteria);
        if (query == null) {
            return new ArrayList<>();
        }
        Set<String> allValues = new TreeSet<>();
        for (String[] field : fields) {
            GroupingSearch groupingSearch = new GroupingSearch(field[1]);
            getGroupingResults(value, limit, query, allValues, groupingSearch);
        }
        return getLimitedResults(limit, allValues);
    }

    public void addToIndex(final Stream<EntryDto> stream) throws IndexException {
        try {
            queue.push(language -> luceneIndexFilesystem.get(language).addToIndex(stream));
        } catch (Exception e) {
            throw new IndexException(e);
        }
    }

    public void dropIndex() throws IndexException {
        try {
            queue.push(language -> luceneIndexFilesystem.get(language).dropIndex());
        } catch (Exception e) {
            throw new IndexException(e);
        }
    }

    public void update(final EntryDto entry) throws IOException {
        try {
            queue.push(language -> {
                long start = System.currentTimeMillis();
                luceneIndexFilesystem.get(language).update(entry);
                long end = System.currentTimeMillis();
                logger.info("Index update for entry {} completed after {} ms.", entry.getEntryId(), end - start);
            });
        } catch (Exception e) {
            throw new IOException(e);
        }

    }

    public void delete(final EntryDto entry) throws IOException {
        try {
            queue.push(language -> luceneIndexFilesystem.get(language).delete(entry));
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    private Query getSuggestionsQuery(String fieldName, String value) {
        List<Query> parts = BuilderRegistry.getInstance().getSuggestionQueries(fieldName, value);
        return createSimpleQuery(parts);
    }

    private Query buildQuery(LuceneSearchCriteria luceneSearchCriteria) {
        long prepareStart = System.nanoTime();
        BooleanQuery.Builder finalQueryBuilder = new BooleanQuery.Builder();

        if (luceneSearchCriteria.getSearchPhrase() != null && !luceneSearchCriteria.getSearchPhrase().isEmpty()) {
            List<Query> searchPhraseQueries = switch (luceneSearchCriteria.getSearchDirection()) {
                case GERMAN -> Stream.of(
                        BuilderRegistry.getInstance().getBuilder(SearchDirection.GERMAN, luceneSearchCriteria.getSearchMethod()).transform(luceneSearchCriteria.getSearchPhrase()),
                        BuilderRegistry.getInstance().getTagQueries(SearchDirection.GERMAN, luceneSearchCriteria.getSearchMethod(), luceneSearchCriteria.getSearchPhrase())
                ).flatMap(Collection::stream).collect(Collectors.toList());
                case ROMANSH -> Stream.of(
                        BuilderRegistry.getInstance().getBuilder(SearchDirection.ROMANSH, luceneSearchCriteria.getSearchMethod()).transform(luceneSearchCriteria.getSearchPhrase()),
                        BuilderRegistry.getInstance().getTagQueries(SearchDirection.ROMANSH, luceneSearchCriteria.getSearchMethod(), luceneSearchCriteria.getSearchPhrase()),
                        BuilderRegistry.getInstance().getEtymologyQueries(luceneSearchCriteria.getSearchMethod(), luceneSearchCriteria.getSearchPhrase())
                ).flatMap(Collection::stream).collect(Collectors.toList());

                case BOTH -> Stream.of(
                        BuilderRegistry.getInstance().getBuilder(SearchDirection.GERMAN, luceneSearchCriteria.getSearchMethod()).transform(luceneSearchCriteria.getSearchPhrase()),
                        BuilderRegistry.getInstance().getBuilder(SearchDirection.ROMANSH, luceneSearchCriteria.getSearchMethod()).transform(luceneSearchCriteria.getSearchPhrase()),
                        BuilderRegistry.getInstance().getTagQueries(SearchDirection.GERMAN, luceneSearchCriteria.getSearchMethod(), luceneSearchCriteria.getSearchPhrase()),
                        BuilderRegistry.getInstance().getTagQueries(SearchDirection.ROMANSH, luceneSearchCriteria.getSearchMethod(), luceneSearchCriteria.getSearchPhrase()),
                        BuilderRegistry.getInstance().getEtymologyQueries(luceneSearchCriteria.getSearchMethod(), luceneSearchCriteria.getSearchPhrase())
                ).flatMap(Collection::stream).collect(Collectors.toList());
            };
            BooleanQuery.Builder part = new BooleanQuery.Builder();
            for (Query tf : searchPhraseQueries) {
                part.add(tf, BooleanClause.Occur.SHOULD);
            }
            finalQueryBuilder.add(part.build(), BooleanClause.Occur.MUST);
        }

        if (luceneSearchCriteria.getGender() != null) {
            List<Query> genderQueries = switch (luceneSearchCriteria.getSearchDirection()) {
                case GERMAN ->
                        BuilderRegistry.getInstance().getGenderBuilder(SearchDirection.GERMAN).transform(luceneSearchCriteria.getGender());
                case ROMANSH ->
                        BuilderRegistry.getInstance().getGenderBuilder(SearchDirection.ROMANSH).transform(luceneSearchCriteria.getGender());
                case BOTH -> Stream.of(
                        BuilderRegistry.getInstance().getGenderBuilder(SearchDirection.ROMANSH).transform(luceneSearchCriteria.getGender()),
                        BuilderRegistry.getInstance().getGenderBuilder(SearchDirection.GERMAN).transform(luceneSearchCriteria.getGender())
                ).flatMap(Collection::stream).collect(Collectors.toList());
            };
            BooleanQuery.Builder part = new BooleanQuery.Builder();
            for (Query tf : genderQueries) {
                part.add(tf, BooleanClause.Occur.SHOULD);
            }
            finalQueryBuilder.add(part.build(), BooleanClause.Occur.MUST);
        }

        if (luceneSearchCriteria.getGrammar() != null) {
            List<Query> grammarQueries = switch (luceneSearchCriteria.getSearchDirection()) {
                case GERMAN ->
                        BuilderRegistry.getInstance().getGrammarBuilder(SearchDirection.GERMAN).transform(luceneSearchCriteria.getGrammar());
                case ROMANSH ->
                        BuilderRegistry.getInstance().getGrammarBuilder(SearchDirection.ROMANSH).transform(luceneSearchCriteria.getGrammar());
                case BOTH -> Stream.of(
                        BuilderRegistry.getInstance().getGrammarBuilder(SearchDirection.ROMANSH).transform(luceneSearchCriteria.getGrammar()),
                        BuilderRegistry.getInstance().getGrammarBuilder(SearchDirection.GERMAN).transform(luceneSearchCriteria.getGrammar())
                ).flatMap(Collection::stream).collect(Collectors.toList());
            };
            BooleanQuery.Builder part = new BooleanQuery.Builder();
            for (Query tf : grammarQueries) {
                part.add(tf, BooleanClause.Occur.SHOULD);
            }
            finalQueryBuilder.add(part.build(), BooleanClause.Occur.MUST);
        }

        if (luceneSearchCriteria.getSubSemantics() != null) {
            List<Query> subSemanticsQueries = switch (luceneSearchCriteria.getSearchDirection()) {
                case GERMAN ->
                        BuilderRegistry.getInstance().getSubSemanticsBuilder(SearchDirection.GERMAN).transform(luceneSearchCriteria.getSubSemantics());
                case ROMANSH ->
                        BuilderRegistry.getInstance().getSubSemanticsBuilder(SearchDirection.ROMANSH).transform(luceneSearchCriteria.getSubSemantics());
                case BOTH -> Stream.of(
                        BuilderRegistry.getInstance().getSubSemanticsBuilder(SearchDirection.ROMANSH).transform(luceneSearchCriteria.getSubSemantics()),
                        BuilderRegistry.getInstance().getSubSemanticsBuilder(SearchDirection.GERMAN).transform(luceneSearchCriteria.getSubSemantics())
                ).flatMap(Collection::stream).collect(Collectors.toList());
            };
            BooleanQuery.Builder part = new BooleanQuery.Builder();
            for (Query tf : subSemanticsQueries) {
                part.add(tf, BooleanClause.Occur.SHOULD);
            }
            finalQueryBuilder.add(part.build(), BooleanClause.Occur.MUST);
        }

        if (luceneSearchCriteria.getCategory() != null) {
            List<Query> categoryQueries = BuilderRegistry.getInstance().getCategoryBuilder().transform(luceneSearchCriteria.getCategory());
            BooleanQuery.Builder part = new BooleanQuery.Builder();
            for (Query tf : categoryQueries) {
                part.add(tf, BooleanClause.Occur.SHOULD);
            }
            finalQueryBuilder.add(part.build(), BooleanClause.Occur.MUST);
        }

        long prepareEnd = System.nanoTime();
        if (logger.isDebugEnabled()) {
            logger.debug("Final query: {} created in {} ms.", finalQueryBuilder, (prepareEnd - prepareStart) / 1000000D);
        }

        return finalQueryBuilder.build();
    }

    private Query buildStartsWithQuery(SearchDirection searchDirection, String prefix) {
        List<Query> queries = BuilderRegistry.getInstance().getStartsWithBuilder(searchDirection).transform(prefix);
        return createSimpleQuery(queries);
    }

    private ArrayList<String> getLimitedResults(int limit, Set<String> allValues) {
        ArrayList<String> results = new ArrayList<>(allValues);
        if (!results.isEmpty()) {
            List<String> resultList = results.subList(0, Math.min(results.size(), limit));
            return new ArrayList<>(resultList);
        } else {
            return results;
        }
    }

    private void getGroupingResults(String searchTerm, int limit, Query query, Set<String> allValues, GroupingSearch groupingSearch) throws IOException, NoIndexAvailableException {
        groupingSearch.setGroupDocsLimit(limit);
        TopGroups<BytesRef> result = groupingSearch.search(luceneIndexFilesystem.get(language).getSearcher(), query, 0, 10000);

        GroupDocs<BytesRef>[] groups = result.groups;
        for (GroupDocs<BytesRef> group : groups) {
            if (group.groupValue == null) {
                continue;
            }
            String fieldValue = group.groupValue.utf8ToString();
            String[] parts = fieldValue.split("(; ?)|(, ?)");
            for (String part : parts) {
                if (part.toLowerCase().startsWith(searchTerm.toLowerCase())) {
                    allValues.add(part);
                }
            }
        }
    }

    private void getSortedResults(String searchTerm, int limit, Query query, Set<String> allValues, String field) throws IOException, NoIndexAvailableException {
        TopDocs topDocs = luceneIndexFilesystem.get(language).getSearcher().search(query, limit);

        StoredFields storedFields = luceneIndexFilesystem.get(language).getSearcher().getIndexReader().storedFields();
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            Document document = storedFields.document(scoreDoc.doc);

            String fieldValue = document.get(field);
            if (fieldValue != null) {
                String[] parts = fieldValue.split("(; ?)|(, ?)");
                for (String part : parts) {
                    if (part.toLowerCase().startsWith(searchTerm.toLowerCase())) {
                        allValues.add(part);
                    }
                }
            }
        }
    }

    private Query createSimpleQuery(List<Query> parts) {
        BooleanQuery.Builder bqBuilder = new BooleanQuery.Builder();
        for (Query part : parts) {
            bqBuilder.add(part, Occur.SHOULD);
        }
        return bqBuilder.build();
    }
}
