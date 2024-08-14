package ch.pledarigrond.lucene.core;

import ch.pledarigrond.common.config.LuceneConfiguration;
import ch.pledarigrond.common.data.common.*;
import ch.pledarigrond.common.data.lucene.IndexStatistics;
import ch.pledarigrond.common.data.lucene.SuggestionField;
import ch.pledarigrond.common.data.user.Pagination;
import ch.pledarigrond.common.data.user.SearchCriteria;
import ch.pledarigrond.common.util.PronunciationNormalizer;
import ch.pledarigrond.lucene.IndexManager;
import ch.pledarigrond.lucene.exceptions.BrokenIndexException;
import ch.pledarigrond.lucene.exceptions.IndexException;
import ch.pledarigrond.lucene.exceptions.InvalidQueryException;
import ch.pledarigrond.lucene.exceptions.NoIndexAvailableException;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.StoredFields;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.SortField.Type;
import org.apache.lucene.search.grouping.GroupDocs;
import org.apache.lucene.search.grouping.GroupingSearch;
import org.apache.lucene.search.grouping.TopGroups;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
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

    private IndexManager indexManager;

    private final IndexCommandQueue queue;

    public LuceneIndexManager(LuceneConfiguration luceneConfiguration) throws IOException {
        this.language = luceneConfiguration.getLanguage();
        queue = IndexCommandQueue.getInstance(this.language);
        formatter = (NumberFormat) NumberFormat.getNumberInstance().clone();
        formatter.setMaximumFractionDigits(3);
        logger.info("Created new index.");
        setLuceneConfiguration(luceneConfiguration);
    }

    public void setLuceneConfiguration(LuceneConfiguration luceneConfiguration) throws IOException {
        luceneIndexFilesystem.put(this.language, new LuceneIndexFilesystem(luceneConfiguration));
        luceneIndexFilesystem.get(this.language).initialize();
        luceneIndexFilesystem.get(this.language).resetIndexDirectory();
        indexManager = IndexManager.getInstance();
    }

    public Page<LemmaVersion> query(SearchCriteria searchCriteria, Pagination pagination) throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException, InvalidTokenOffsetsException {
        long start = System.nanoTime();

        // normalize setSearchPhrase (we remove pronunciation marks)
        searchCriteria.setSearchPhrase(PronunciationNormalizer.normalizePronunciation(searchCriteria.getSearchPhrase()));

        boolean hasAdminRole = false;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            hasAdminRole = authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        }

        validatePagination(pagination, hasAdminRole);

        long s1 = System.nanoTime();
        Query query = buildQuery(searchCriteria);
        TopDocs docs;


        SortField[] fields = new SortField[3];
        fields[0] = SortField.FIELD_SCORE;
        if (searchCriteria.getSearchDirection() == SearchDirection.ROMANSH) {
            fields[1] = new SortField("RStichwort", Type.STRING);
            fields[2] = new SortField("RStichwort_sort", Type.INT);
        } else {
            fields[1] = new SortField("DStichwort", Type.STRING);
            fields[2] = new SortField("DStichwort_sort", Type.INT);
        }
        Sort sort = new Sort(fields);
        Page<LemmaVersion> result;

        int pageSize = pagination.getPageSize();
        int pageNr = pagination.getPage();
        long e1 = System.nanoTime();
        try {
            long s2 = System.nanoTime();
            docs = luceneIndexFilesystem.get(language).getSearcher().search(query, pageSize * (pageNr + 1), sort);
            long e2 = System.nanoTime();
            result = toLemmaVersionPagination(docs, pageNr, pageSize);
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
            logger.warn("Slow query: {} ms for {}", formatter.format(time), searchCriteria);
        } else if (logger.isDebugEnabled()) {
            logger.debug("Processed query in {} ms :{}", formatter.format(time), searchCriteria);
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

    private Page<LemmaVersion> toLemmaVersionPagination(TopDocs docs, int page, int pageSize) throws NoIndexAvailableException, IOException, InvalidTokenOffsetsException {
        final ArrayList<LemmaVersion> results = new ArrayList<>(pageSize);
        final ScoreDoc[] scoreDocs = docs.scoreDocs;
        IndexSearcher searcher = luceneIndexFilesystem.get(language).getSearcher();
		StoredFields storedFields = searcher.getIndexReader().storedFields();
        for (int i = page * pageSize; i < scoreDocs.length && i < page * pageSize + pageSize; i++) {

            Document doc = storedFields.document(scoreDocs[i].doc);
            LemmaVersion e = indexManager.getLemmaVersion(doc);
            results.add(e);
        }

        Pageable pageable = PageRequest.of(page, pageSize);
        return new PageImpl<>(results, pageable, docs.totalHits.value);
    }

    public Page<LemmaVersion> queryExact(String phrase, DictionaryLanguage dictionaryLanguage) throws NoIndexAvailableException, BrokenIndexException, InvalidQueryException {
        List<Query> queries;
        SortField sortField;
        if (dictionaryLanguage == DictionaryLanguage.GERMAN) {
            queries = BuilderRegistry.getInstance().getBuilder(SearchDirection.GERMAN, SearchMethod.EXACT).transform(phrase);
            sortField = new SortField("DStichwort_sort", Type.INT);
        } else {
            queries = BuilderRegistry.getInstance().getBuilder(SearchDirection.ROMANSH, SearchMethod.EXACT).transform(phrase);
            sortField = new SortField("RStichwort_sort", Type.INT);
        }
        int pageSize = 120;
        try {
            BooleanQuery.Builder qBuilder = new BooleanQuery.Builder();
            for (Query q : queries) {
                qBuilder.add(q, Occur.SHOULD);
            }
            BooleanQuery.Builder bc = new BooleanQuery.Builder();
            bc.add(qBuilder.build(), Occur.MUST);
            bc.add(new TermQuery(new Term(LemmaVersion.VERIFICATION, LemmaVersion.Verification.ACCEPTED.toString())), Occur.MUST);
            qBuilder = bc;
            TopDocs docs = luceneIndexFilesystem.get(language).getSearcher().search(qBuilder.build(), pageSize, new Sort(sortField));

            return toLemmaVersionPagination(docs, 0, pageSize);
        } catch (IOException e) {
            throw new BrokenIndexException("Broken index!", e);
        } catch (InvalidTokenOffsetsException e) {
            throw new InvalidQueryException("Highlighting failed", e);
        }
    }

    public IndexStatistics getIndexStatistics() {
        final IndexStatistics statistics = new IndexStatistics();
        try {
            queue.push(language -> {
                int all = luceneIndexFilesystem.get(language).getSearcher().getIndexReader().numDocs();
                int unverified = 0;
                int approved = 0;
                int unknown = 0;
                IndexReader reader = luceneIndexFilesystem.get(language).getSearcher().getIndexReader();
                HashMap<String, Integer> byInflectionType = new HashMap<>();
				StoredFields storedFields = reader.storedFields();

                for (int i = 0; i < all; i++) {
					Document document = storedFields.document(i);
                    String verification = document.get(LemmaVersion.VERIFICATION);
                    try {
                        if (LemmaVersion.Verification.ACCEPTED.equals(LemmaVersion.Verification.valueOf(verification))) {
                            approved++;
                        } else if (LemmaVersion.Verification.UNVERIFIED.equals(LemmaVersion.Verification.valueOf(verification))) {
                            unverified++;
                        } else {
                            unknown++;
                        }
                    } catch (Exception e) {
                        unknown++;
                    }
                    String flexType = document.get(LemmaVersion.RM_INFLECTION_TYPE);
                    if (flexType != null) {
                        Integer old = byInflectionType.get(flexType);
                        if (old == null) old = 0;
                        byInflectionType.put(flexType, old + 1);
                    }
                }
                statistics.setInflectionCount(byInflectionType);
                statistics.setNumberOfEntries(all);
                statistics.setUnverifiedEntries(unverified);
                statistics.setApprovedEntries(approved);
                statistics.setUnknown(unknown);
                statistics.setLastUpdated(luceneIndexFilesystem.get(language).getLastUpdated());
            });
            return statistics;
        } catch (Exception e) {
            return new IndexStatistics();
        }
    }

    public ArrayList<String> getSuggestionsForField(String fieldName, String searchTerm, int limit) throws NoIndexAvailableException, IOException {
        Query query = getSuggestionsQuery(fieldName, searchTerm);
        Set<String> allValues = new TreeSet<>();

        GroupingSearch groupingSearch = new GroupingSearch(fieldName);
        getGroupingResults(searchTerm, limit, query, allValues, groupingSearch);
        return getLimitedResults(limit, allValues);
    }

    // p.ex. grammar, gender
    public ArrayList<String> getSuggestionsForFieldChoice(SuggestionField suggestionField, String value, int limit) throws NoIndexAvailableException, IOException {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setSuggestions(true);
        Set<String[]> fields = Set.of();
        if (suggestionField == SuggestionField.GENDER) {
            searchCriteria.setGender(value);
            fields = Set.of(new String[]{"DGenus_na_nw_l_t-STRING", "DGenus"}, new String[]{"RGenus_na_nw_l_t-STRING", "RGenus"});
        } else if (suggestionField == SuggestionField.GRAMMAR) {
            searchCriteria.setGrammar(value);
            fields = Set.of(new String[]{"DGrammatik_na_nw_l_t-STRING", "DGrammatik"}, new String[]{"RGrammatik_na_nw_l_t-STRING", "RGrammatik"});
        }
        Query query = buildQuery(searchCriteria);
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

    public void addToIndex(final Iterator<LexEntry> iterator) throws IndexException {
        try {
            queue.push(language -> luceneIndexFilesystem.get(language).addToIndex(iterator));
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

    public void update(final LexEntry entry) throws IOException {
        try {
            queue.push(language -> {
                long start = System.currentTimeMillis();
                luceneIndexFilesystem.get(language).update(entry);
                long end = System.currentTimeMillis();
                logger.info("Index update for entry {} completed after {} ms.", entry.getId(), end - start);
            });
        } catch (Exception e) {
            throw new IOException(e);
        }

    }

    public void delete(final LexEntry entry) throws IOException {
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

    private Query buildQuery(SearchCriteria searchCriteria) {
        long prepareStart = System.nanoTime();
        BooleanQuery.Builder finalQueryBuilder = new BooleanQuery.Builder();

        if (searchCriteria.getSearchPhrase() != null && !searchCriteria.getSearchPhrase().isEmpty()) {
            List<Query> searchPhraseQueries = switch (searchCriteria.getSearchDirection()) {
                case GERMAN -> Stream.of(
                        BuilderRegistry.getInstance().getBuilder(SearchDirection.GERMAN, searchCriteria.getSearchMethod()).transform(searchCriteria.getSearchPhrase()),
                        BuilderRegistry.getInstance().getTagQueries(SearchDirection.GERMAN, searchCriteria.getSearchMethod(), searchCriteria.getSearchPhrase())
                ).flatMap(Collection::stream).collect(Collectors.toList());
                case ROMANSH -> Stream.of(
                        BuilderRegistry.getInstance().getBuilder(SearchDirection.ROMANSH, searchCriteria.getSearchMethod()).transform(searchCriteria.getSearchPhrase()),
                        BuilderRegistry.getInstance().getTagQueries(SearchDirection.ROMANSH, searchCriteria.getSearchMethod(), searchCriteria.getSearchPhrase()),
                        BuilderRegistry.getInstance().getEtymologyQueries(searchCriteria.getSearchMethod(), searchCriteria.getSearchPhrase())
                ).flatMap(Collection::stream).collect(Collectors.toList());

                case BOTH -> Stream.of(
                        BuilderRegistry.getInstance().getBuilder(SearchDirection.GERMAN, searchCriteria.getSearchMethod()).transform(searchCriteria.getSearchPhrase()),
                        BuilderRegistry.getInstance().getBuilder(SearchDirection.ROMANSH, searchCriteria.getSearchMethod()).transform(searchCriteria.getSearchPhrase()),
                        BuilderRegistry.getInstance().getTagQueries(SearchDirection.GERMAN, searchCriteria.getSearchMethod(), searchCriteria.getSearchPhrase()),
                        BuilderRegistry.getInstance().getTagQueries(SearchDirection.ROMANSH, searchCriteria.getSearchMethod(), searchCriteria.getSearchPhrase()),
                        BuilderRegistry.getInstance().getEtymologyQueries(searchCriteria.getSearchMethod(), searchCriteria.getSearchPhrase())
                ).flatMap(Collection::stream).collect(Collectors.toList());
            };
            BooleanQuery.Builder part = new BooleanQuery.Builder();
            for (Query tf : searchPhraseQueries) {
                part.add(tf, BooleanClause.Occur.SHOULD);
            }
            finalQueryBuilder.add(part.build(), BooleanClause.Occur.MUST);
        }

        if (searchCriteria.getGender() != null) {
            List<Query> genderQueries = switch (searchCriteria.getSearchDirection()) {
                case GERMAN ->
                        BuilderRegistry.getInstance().getGenderBuilder(SearchDirection.GERMAN).transform(searchCriteria.getGender());
                case ROMANSH ->
                        BuilderRegistry.getInstance().getGenderBuilder(SearchDirection.ROMANSH).transform(searchCriteria.getGender());
                case BOTH -> Stream.of(
                        BuilderRegistry.getInstance().getGenderBuilder(SearchDirection.ROMANSH).transform(searchCriteria.getGender()),
                        BuilderRegistry.getInstance().getGenderBuilder(SearchDirection.GERMAN).transform(searchCriteria.getGender())
                ).flatMap(Collection::stream).collect(Collectors.toList());
            };
            BooleanQuery.Builder part = new BooleanQuery.Builder();
            for (Query tf : genderQueries) {
                part.add(tf, BooleanClause.Occur.SHOULD);
            }
            finalQueryBuilder.add(part.build(), BooleanClause.Occur.MUST);
        }

        if (searchCriteria.getGrammar() != null) {
            List<Query> grammarQueries = switch (searchCriteria.getSearchDirection()) {
                case GERMAN ->
                        BuilderRegistry.getInstance().getGrammarBuilder(SearchDirection.GERMAN).transform(searchCriteria.getGrammar());
                case ROMANSH ->
                        BuilderRegistry.getInstance().getGrammarBuilder(SearchDirection.ROMANSH).transform(searchCriteria.getGrammar());
                case BOTH -> Stream.of(
                        BuilderRegistry.getInstance().getGrammarBuilder(SearchDirection.ROMANSH).transform(searchCriteria.getGrammar()),
                        BuilderRegistry.getInstance().getGrammarBuilder(SearchDirection.GERMAN).transform(searchCriteria.getGrammar())
                ).flatMap(Collection::stream).collect(Collectors.toList());
            };
            BooleanQuery.Builder part = new BooleanQuery.Builder();
            for (Query tf : grammarQueries) {
                part.add(tf, BooleanClause.Occur.SHOULD);
            }
            finalQueryBuilder.add(part.build(), BooleanClause.Occur.MUST);
        }

        if (searchCriteria.getSubSemantics() != null) {
            List<Query> subSemanticsQueries = switch (searchCriteria.getSearchDirection()) {
                case GERMAN ->
                        BuilderRegistry.getInstance().getSubSemanticsBuilder(SearchDirection.GERMAN).transform(searchCriteria.getSubSemantics());
                case ROMANSH ->
                        BuilderRegistry.getInstance().getSubSemanticsBuilder(SearchDirection.ROMANSH).transform(searchCriteria.getSubSemantics());
                case BOTH -> Stream.of(
                        BuilderRegistry.getInstance().getSubSemanticsBuilder(SearchDirection.ROMANSH).transform(searchCriteria.getSubSemantics()),
                        BuilderRegistry.getInstance().getSubSemanticsBuilder(SearchDirection.GERMAN).transform(searchCriteria.getSubSemantics())
                ).flatMap(Collection::stream).collect(Collectors.toList());
            };
            BooleanQuery.Builder part = new BooleanQuery.Builder();
            for (Query tf : subSemanticsQueries) {
                part.add(tf, BooleanClause.Occur.SHOULD);
            }
            finalQueryBuilder.add(part.build(), BooleanClause.Occur.MUST);
        }

        if (searchCriteria.getCategory() != null) {
            List<Query> categoryQueries = BuilderRegistry.getInstance().getCategoryBuilder().transform(searchCriteria.getCategory());
            BooleanQuery.Builder part = new BooleanQuery.Builder();
            for (Query tf : categoryQueries) {
                part.add(tf, BooleanClause.Occur.SHOULD);
            }
            finalQueryBuilder.add(part.build(), BooleanClause.Occur.MUST);
        }

        if (searchCriteria.getVerification() != null) {
            try {
                QueryParser queryParser = new QueryParser(LemmaVersion.VERIFICATION + "_analyzed", new StandardAnalyzer());
                queryParser.setAllowLeadingWildcard(true);
                finalQueryBuilder.add(queryParser.parse(searchCriteria.getVerification().toString()), BooleanClause.Occur.MUST);
            } catch (ParseException e) {
                logger.error("Failed to parse verification query", e);
            }
        }

        if (searchCriteria.getShowReviewLater() != null) {
            try {
                QueryParser queryParser = new QueryParser(LemmaVersion.REVIEW_LATER, new StandardAnalyzer());
                finalQueryBuilder.add(queryParser.parse(searchCriteria.getShowReviewLater().toString()), BooleanClause.Occur.MUST);
            } catch (ParseException e) {
                logger.error("Failed to parse review later query", e);
            }
        }

        if (searchCriteria.getOnlyAutomaticChanged()) {
            try {
                QueryParser queryParser = new QueryParser(LemmaVersion.AUTOMATIC_CHANGE, new StandardAnalyzer());
                queryParser.setAllowLeadingWildcard(true);
                finalQueryBuilder.add(queryParser.parse("*"), BooleanClause.Occur.MUST);
            } catch (ParseException e) {
                logger.error("Failed to parse automatic change query", e);
            }
        }

        if (searchCriteria.getExcludeAutomaticChanged()) {
            try {
                QueryParser queryParser = new QueryParser(LemmaVersion.FIELD_NAMES, new StandardAnalyzer());
                queryParser.setAllowLeadingWildcard(true);
                finalQueryBuilder.add(queryParser.parse("* AND -" + LemmaVersion.AUTOMATIC_CHANGE), BooleanClause.Occur.MUST);
            } catch (ParseException e) {
                logger.error("Failed to parse automatic change query", e);
            }
        }

        if (searchCriteria.getAutomaticChangesType() != null && searchCriteria.getAutomaticChangesType() != AutomaticChangesType.ALL) {
            try {
                QueryParser queryParser = new QueryParser(LemmaVersion.AUTOMATIC_CHANGE, new StandardAnalyzer());
                queryParser.setAllowLeadingWildcard(true);
                finalQueryBuilder.add(queryParser.parse(searchCriteria.getAutomaticChangesType().toString()), BooleanClause.Occur.MUST);
            } catch (ParseException e) {
                logger.error("Failed to parse automatic change query", e);
            }
        }

        // Unless a user wants to see unverified suggestions, each item returned must be verified.
        if (!searchCriteria.getSuggestions()) {
            BooleanQuery.Builder bc = new BooleanQuery.Builder();
            bc.add(finalQueryBuilder.build(), BooleanClause.Occur.MUST);
            bc.add(new TermQuery(new Term(LemmaVersion.VERIFICATION, LemmaVersion.Verification.ACCEPTED.toString())), BooleanClause.Occur.MUST);
            finalQueryBuilder = bc;
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

    private Query createSimpleQuery(List<Query> parts) {
        BooleanQuery.Builder bqBuilder = new BooleanQuery.Builder();
        for (Query part : parts) {
            bqBuilder.add(part, Occur.SHOULD);
        }
        BooleanQuery.Builder bc = new BooleanQuery.Builder();
        bc.add(bqBuilder.build(), Occur.MUST);
        bc.add(new TermQuery(new Term(LemmaVersion.VERIFICATION, LemmaVersion.Verification.ACCEPTED.toString())), Occur.MUST);
        return bc.build();
    }
}
