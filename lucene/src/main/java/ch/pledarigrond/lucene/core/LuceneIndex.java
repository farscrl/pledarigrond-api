/*******************************************************************************
 * Copyright 2013 Sprachliche Informationsverarbeitung, University of Cologne
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
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
import org.apache.lucene.store.NIOFSDirectory;
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
 * This class is responsible for managing the lucene index used by PG,
 * and provides all required methods to perform CRUD-like operations on it.
 * Internally, two indices are managed: All read- and query-requests are
 * executed on an in-memory-index, whereas write-requests are executed on
 * a {@link NIOFSDirectory}. Index-Changes are executed in-order with the 
 * help of a {@link IndexCommandQueue}.
 * 
 * @author sschwieb
 * @author matana
 *
 */
public class LuceneIndex {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final Language language;

	private final NumberFormat formatter;

	private final Map<Language, LuceneIndexRam> luceneIndexRam = new HashMap<>();

	private final Map<Language, LuceneIndexFilesystem> luceneIndexFilesystem = new HashMap<>();

	private IndexManager indexManager;

	private final IndexCommandQueue queue;

	public LuceneIndex(LuceneConfiguration luceneConfiguration) throws IOException {
		this.language = luceneConfiguration.getLanguage();
		queue = IndexCommandQueue.getInstance(this.language);
		formatter = (NumberFormat) NumberFormat.getNumberInstance().clone();
		formatter.setMaximumFractionDigits(3);
		logger.info("Created new index.");
		setLuceneConfiguration(luceneConfiguration);
	}

	public void setLuceneConfiguration(LuceneConfiguration luceneConfiguration) throws IOException {
		luceneIndexRam.put(this.language, new LuceneIndexRam(luceneConfiguration));
		luceneIndexFilesystem.put(this.language, new LuceneIndexFilesystem(luceneConfiguration));
		luceneIndexFilesystem.get(this.language).initialize();
		luceneIndexFilesystem.get(this.language).resetIndexDirectory();
		indexManager = IndexManager.getInstance(this.language);
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

		Sort sort = new Sort();

		SortField[] fields = new SortField[3];
		fields[0] = SortField.FIELD_SCORE;
		if(searchCriteria.getSearchDirection() == SearchDirection.ROMANSH) {
			fields[1] = new SortField("RStichwort", Type.STRING);
			fields[2] = new SortField("RStichwort_sort", Type.STRING);
		} else {
			fields[1] = new SortField("DStichwort", Type.STRING);
			fields[2] = new SortField("DStichwort_sort", Type.STRING);
		}
		sort.setSort(fields);
		Page<LemmaVersion> result;

		int pageSize = pagination.getPageSize();
		int pageNr = pagination.getPage();
		long e1 = System.nanoTime();
		try {
			long s2 = System.nanoTime();
			docs = luceneIndexRam.get(language).getSearcher().search(query, pageSize * (pageNr + 1), sort);
			long e2 = System.nanoTime();
			result = toLemmaVersionPagination(docs, pageNr, pageSize);
			if(logger.isDebugEnabled()) {
				logger.debug("Time to build query: " + (e1-s1)/1000000 + ", Time to execute query: " + ((e2-s2)/1000000));
			}
		} catch (IOException e) {
			throw new BrokenIndexException("Failed to access index", e);
		}
		long end = System.nanoTime();
		double time = (end - start) / 1000000D;
		// Warn if query takes more than 100 ms.
		if (time > 100) {
			logger.warn("Slow query: " + formatter.format(time) + " ms for " + searchCriteria);
		} else if (logger.isDebugEnabled()) {
			logger.debug("Processed query in " + formatter.format(time) + " ms :" + searchCriteria);
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
		IndexSearcher searcher = luceneIndexRam.get(language).getSearcher();
		for (int i = page * pageSize; i < scoreDocs.length && i < page * pageSize + pageSize; i++) {
			Document doc = searcher.doc(scoreDocs[i].doc);
			LemmaVersion e = indexManager.getLemmaVersion(doc);
			results.add(e);
		}

		Pageable pageable = PageRequest.of(page, pageSize);
		return new PageImpl<>(results, pageable, docs.totalHits);
	}

	public Page<LemmaVersion> queryExact(String phrase, DictionaryLanguage dictionaryLanguage) throws NoIndexAvailableException, BrokenIndexException, InvalidQueryException {
		List<Query> queries;
		SortField sortField;
		if(dictionaryLanguage == DictionaryLanguage.GERMAN) {
			queries =  indexManager.getBuilderRegistry().getBuilder(SearchDirection.GERMAN, SearchMethod.EXACT).transform(phrase);
			sortField = new SortField("DStichwort_sort", Type.STRING);
		} else {
			queries =  indexManager.getBuilderRegistry().getBuilder(SearchDirection.ROMANSH, SearchMethod.EXACT).transform(phrase);
			sortField = new SortField("RStichwort_sort", Type.STRING);
		}
		int pageSize = 120;
		try {
			BooleanQuery.Builder qBuilder = new BooleanQuery.Builder();
			for (Query q : queries) {
				qBuilder.add(q, Occur.SHOULD);
			}
			BooleanQuery.Builder bc = new BooleanQuery.Builder();
			bc.add(qBuilder.build(), Occur.MUST);
			bc.add(new TermQuery(new Term(LemmaVersion.VERIFICATION, LemmaVersion.Verification.ACCEPTED.toString())),Occur.MUST);
			qBuilder = bc;
			TopDocs docs = luceneIndexRam.get(language).getSearcher().search(qBuilder.build(), pageSize, new Sort(sortField));

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
                int all = luceneIndexRam.get(language).getSearcher().getIndexReader().numDocs();
                int unverified = 0;
                int approved = 0;
                int unknown = 0;
                IndexReader reader = luceneIndexRam.get(language).getSearcher().getIndexReader();
                HashMap<String, Integer> byInflectionType = new HashMap<>();
                for (int i = 0; i < all; i++) {
                    Document document = reader.document(i);
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
                    if(flexType != null) {
                        Integer old = byInflectionType.get(flexType);
                        if(old == null) old = 0;
                        byInflectionType.put(flexType, old+1);
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
		groupingSearch.setGroupDocsLimit(limit);
		TopGroups<BytesRef> result = groupingSearch.search(luceneIndexRam.get(language).getSearcher(), query, 0, 10000);

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
		if(query == null) {
			return new ArrayList<>();
		}
		Set<String> allValues = new TreeSet<>();
		for (String[] field : fields) {
			GroupingSearch groupingSearch = new GroupingSearch(field[1]);
			groupingSearch.setGroupDocsLimit(limit);
			TopGroups<BytesRef> result = groupingSearch.search(luceneIndexRam.get(language).getSearcher(), query, 0, 10000);

			GroupDocs<BytesRef>[] groups = result.groups;
            for (GroupDocs<BytesRef> group : groups) {
				if (group.groupValue == null) {
					continue;
				}
                String fieldValue = group.groupValue.utf8ToString();

				String[] parts = fieldValue.split("(; ?)|(, ?)");
				for (String part : parts) {
					if (part.toLowerCase().startsWith(value.toLowerCase())) {
						allValues.add(part);
					}
				}
            }
		}
		return getLimitedResults(limit, allValues);
	}

	public void reloadIndex() throws NoIndexAvailableException {
		try {
			queue.push(language -> {
                logger.info("Reloading index...");
                luceneIndexRam.get(language).reloadIndex();
                logger.info("Index reloaded");
            });
		} catch (Exception e) {
			throw new NoIndexAvailableException(e);
		}
	}

	public void addToIndex(final Iterator<LexEntry> iterator) throws IndexException {
		try {
			queue.push(language -> {
                int added = luceneIndexFilesystem.get(language).addToIndex(iterator);
            });
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
                luceneIndexRam.get(language).update(entry);
                long end = System.currentTimeMillis();
                logger.info("Index update for entry " + entry.getId()
                        + " completed after " + (end - start) + " ms.");
            });
		} catch (Exception e) {
			throw new IOException(e);
		}
		
	}

	public void delete(final LexEntry entry) throws IOException {
		try {
			queue.push(new IndexOperation() {

				@Override
				public void execute(Language language) throws Exception {
					luceneIndexFilesystem.get(language).delete(entry);
					luceneIndexRam.get(language).delete(entry);
				}
			});
		} catch (Exception e) {
			throw new IOException(e);
		}
	}

	private Query getSuggestionsQuery(String fieldName, String value) {
		List<Query> parts = indexManager.getBuilderRegistry().getSuggestionQueries(fieldName, value);
		BooleanQuery.Builder bqBuilder = new BooleanQuery.Builder();
		for (Query part : parts) {
			bqBuilder.add(part, BooleanClause.Occur.SHOULD);
		}
		BooleanQuery.Builder bc = new BooleanQuery.Builder();
		bc.add(bqBuilder.build(), BooleanClause.Occur.MUST);
		bc.add(new TermQuery(new Term(LemmaVersion.VERIFICATION, LemmaVersion.Verification.ACCEPTED.toString())), BooleanClause.Occur.MUST);
		return bc.build();
	}

	private Query buildQuery(SearchCriteria searchCriteria) {
		long prepareStart = System.nanoTime();
		BooleanQuery.Builder finalQueryBuilder = new BooleanQuery.Builder();

		if (searchCriteria.getSearchPhrase() != null && !searchCriteria.getSearchPhrase().isEmpty()) {
			List<Query> searchPhraseQueries = switch (searchCriteria.getSearchDirection()) {
				case GERMAN -> Stream.of(
						indexManager.getBuilderRegistry().getBuilder(SearchDirection.GERMAN, searchCriteria.getSearchMethod()).transform(searchCriteria.getSearchPhrase()),
						indexManager.getBuilderRegistry().getTagQueries(SearchDirection.GERMAN, searchCriteria.getSearchMethod(), searchCriteria.getSearchPhrase())
				).flatMap(Collection::stream).collect(Collectors.toList());
				case ROMANSH -> Stream.of(
						indexManager.getBuilderRegistry().getBuilder(SearchDirection.ROMANSH, searchCriteria.getSearchMethod()).transform(searchCriteria.getSearchPhrase()),
						indexManager.getBuilderRegistry().getTagQueries(SearchDirection.ROMANSH, searchCriteria.getSearchMethod(), searchCriteria.getSearchPhrase()),
						indexManager.getBuilderRegistry().getEtymologyQueries(searchCriteria.getSearchMethod(), searchCriteria.getSearchPhrase())
				).flatMap(Collection::stream).collect(Collectors.toList());

				case BOTH -> Stream.of(
						indexManager.getBuilderRegistry().getBuilder(SearchDirection.GERMAN, searchCriteria.getSearchMethod()).transform(searchCriteria.getSearchPhrase()),
						indexManager.getBuilderRegistry().getBuilder(SearchDirection.ROMANSH, searchCriteria.getSearchMethod()).transform(searchCriteria.getSearchPhrase()),
						indexManager.getBuilderRegistry().getTagQueries(SearchDirection.GERMAN, searchCriteria.getSearchMethod(), searchCriteria.getSearchPhrase()),
						indexManager.getBuilderRegistry().getTagQueries(SearchDirection.ROMANSH, searchCriteria.getSearchMethod(), searchCriteria.getSearchPhrase()),
						indexManager.getBuilderRegistry().getEtymologyQueries(searchCriteria.getSearchMethod(), searchCriteria.getSearchPhrase())
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
				case GERMAN -> indexManager.getBuilderRegistry().getGenderBuilder(SearchDirection.GERMAN).transform(searchCriteria.getGender());
				case ROMANSH -> indexManager.getBuilderRegistry().getGenderBuilder(SearchDirection.ROMANSH).transform(searchCriteria.getGender());
				case BOTH -> Stream.of(
						indexManager.getBuilderRegistry().getGenderBuilder(SearchDirection.ROMANSH).transform(searchCriteria.getGender()),
						indexManager.getBuilderRegistry().getGenderBuilder(SearchDirection.GERMAN).transform(searchCriteria.getGender())
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
				case GERMAN -> indexManager.getBuilderRegistry().getGrammarBuilder(SearchDirection.GERMAN).transform(searchCriteria.getGrammar());
				case ROMANSH -> indexManager.getBuilderRegistry().getGrammarBuilder(SearchDirection.ROMANSH).transform(searchCriteria.getGrammar());
				case BOTH -> Stream.of(
						indexManager.getBuilderRegistry().getGrammarBuilder(SearchDirection.ROMANSH).transform(searchCriteria.getGrammar()),
						indexManager.getBuilderRegistry().getGrammarBuilder(SearchDirection.GERMAN).transform(searchCriteria.getGrammar())
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
				case GERMAN -> indexManager.getBuilderRegistry().getSubSemanticsBuilder(SearchDirection.GERMAN).transform(searchCriteria.getSubSemantics());
				case ROMANSH -> indexManager.getBuilderRegistry().getSubSemanticsBuilder(SearchDirection.ROMANSH).transform(searchCriteria.getSubSemantics());
				case BOTH -> Stream.of(
						indexManager.getBuilderRegistry().getSubSemanticsBuilder(SearchDirection.ROMANSH).transform(searchCriteria.getSubSemantics()),
						indexManager.getBuilderRegistry().getSubSemanticsBuilder(SearchDirection.GERMAN).transform(searchCriteria.getSubSemantics())
				).flatMap(Collection::stream).collect(Collectors.toList());
			};
			BooleanQuery.Builder part = new BooleanQuery.Builder();
			for (Query tf : subSemanticsQueries) {
				part.add(tf, BooleanClause.Occur.SHOULD);
			}
			finalQueryBuilder.add(part.build(), BooleanClause.Occur.MUST);
		}

		if (searchCriteria.getCategory() != null) {
			List<Query> categoryQueries = indexManager.getBuilderRegistry().getCategoryBuilder().transform(searchCriteria.getCategory());
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
				e.printStackTrace();
			}
		}

		if (searchCriteria.getShowReviewLater() != null) {
			try {
				QueryParser queryParser = new QueryParser(LemmaVersion.REVIEW_LATER, new StandardAnalyzer());
				finalQueryBuilder.add(queryParser.parse(searchCriteria.getShowReviewLater().toString()), BooleanClause.Occur.MUST);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if (searchCriteria.getOnlyAutomaticChanged()) {
			try {
				QueryParser queryParser = new QueryParser(LemmaVersion.AUTOMATIC_CHANGE, new StandardAnalyzer());
				queryParser.setAllowLeadingWildcard(true);
				finalQueryBuilder.add(queryParser.parse("*"), BooleanClause.Occur.MUST);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if (searchCriteria.getExcludeAutomaticChanged()) {
			try {
				QueryParser queryParser = new QueryParser(LemmaVersion.FIELD_NAMES, new StandardAnalyzer());
				queryParser.setAllowLeadingWildcard(true);
				finalQueryBuilder.add(queryParser.parse("* AND -" + LemmaVersion.AUTOMATIC_CHANGE), BooleanClause.Occur.MUST);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if (searchCriteria.getAutomaticChangesType() != null && searchCriteria.getAutomaticChangesType() != AutomaticChangesType.ALL) {
			try {
				QueryParser queryParser = new QueryParser(LemmaVersion.AUTOMATIC_CHANGE, new StandardAnalyzer());
				queryParser.setAllowLeadingWildcard(true);
				finalQueryBuilder.add(queryParser.parse(searchCriteria.getAutomaticChangesType().toString()), BooleanClause.Occur.MUST);
			} catch (ParseException e) {
				e.printStackTrace();
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
		if(logger.isDebugEnabled()) {
			logger.debug("Final query: " + finalQueryBuilder + " created in " + ((prepareEnd-prepareStart)/1000000D) + " ms.");
		}

		return finalQueryBuilder.build();
	}

	private Query buildStartsWithQuery(SearchDirection searchDirection, String prefix) {
		List<Query> queries = indexManager.getBuilderRegistry().getStartsWithBuilder(searchDirection).transform(prefix);
		BooleanQuery.Builder qBuilder = new BooleanQuery.Builder();
		for (Query q : queries) {
			qBuilder.add(q, BooleanClause.Occur.SHOULD);
		}
		BooleanQuery.Builder bc = new BooleanQuery.Builder();
		bc.add(qBuilder.build(), BooleanClause.Occur.MUST);
		bc.add(new TermQuery(new Term(LemmaVersion.VERIFICATION, LemmaVersion.Verification.ACCEPTED.toString())), BooleanClause.Occur.MUST);
		return bc.build();
	}

	private ArrayList<String> getLimitedResults(int limit, Set<String> allValues) {
		ArrayList<String> results = new ArrayList<>(allValues);
		if(!results.isEmpty()) {
			List<String> resultList = results.subList(0, Math.min(results.size(), limit));
			return new ArrayList<>(resultList);
		} else {
			return results;
		}
	}
}
