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

import ch.pledarigrond.common.data.common.*;
import ch.pledarigrond.common.data.lucene.FieldType;
import ch.pledarigrond.common.data.lucene.IndexStatistics;
import ch.pledarigrond.common.data.lucene.IndexedColumn;
import ch.pledarigrond.common.data.lucene.SuggestionField;
import ch.pledarigrond.common.data.user.Pagination;
import ch.pledarigrond.common.data.user.SearchCriteria;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.lucene.config.IndexManager;
import ch.pledarigrond.lucene.config.querybuilder.modifier.ExactMatchQueryBuilder;
import ch.pledarigrond.lucene.config.querybuilder.modifier.SimplePrefixQueryBuilder;
import ch.pledarigrond.lucene.exceptions.BrokenIndexException;
import ch.pledarigrond.lucene.exceptions.IndexException;
import ch.pledarigrond.lucene.exceptions.InvalidQueryException;
import ch.pledarigrond.lucene.exceptions.NoIndexAvailableException;
import ch.pledarigrond.common.config.LuceneConfiguration;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.flexible.core.QueryNodeException;
import org.apache.lucene.sandbox.queries.DuplicateFilter;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.*;
import org.apache.lucene.search.SortField.Type;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.store.NIOFSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.*;

/**
 * This class is responsible for managing the lucene index used by maalr,
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

	private HashMap<String, Type> sortTypes;

	private SimplePrefixQueryBuilder langBIndexBuilder;

	private SimplePrefixQueryBuilder langAIndexBuilder;

	private ExactMatchQueryBuilder exactMatchesLangA;

	private ExactMatchQueryBuilder exactMatchesLangB;

	private final IndexCommandQueue queue;

	public LuceneIndex(LuceneConfiguration luceneConfiguration) throws IOException {
		this.language = luceneConfiguration.getLanguage();
		queue = IndexCommandQueue.getInstance(this.language);
		formatter = (NumberFormat) NumberFormat.getNumberInstance().clone();
		formatter.setMaximumFractionDigits(3);
		logger.info("Created new index.");
		setLuceneConfiguration(luceneConfiguration);
	}

	public void setLuceneConfiguration(LuceneConfiguration luceneConfiguration)
			throws IOException {
		//this.luceneConfiguration = luceneConfiguration;
		luceneIndexRam.put(this.language, new LuceneIndexRam(luceneConfiguration));
		luceneIndexFilesystem.put(this.language, new LuceneIndexFilesystem(luceneConfiguration));
		luceneIndexFilesystem.get(this.language).initialize();
		luceneIndexFilesystem.get(this.language).resetIndexDirectory();
		indexManager = IndexManager.getInstance(this.language);
		Set<IndexedColumn> columns = indexManager.getFinalColumnSet();
		sortTypes = new HashMap<>();
		for (IndexedColumn item : columns) {
			sortTypes.put(item.getIndexFieldName(), getType(item.getType()));
		}
		// Create query builder for static dictionary pages
		langAIndexBuilder = new SimplePrefixQueryBuilder();
		langAIndexBuilder.setColumn("DStichwort");
		langBIndexBuilder = new SimplePrefixQueryBuilder();
		langBIndexBuilder.setColumn("RStichwort");
		exactMatchesLangA = new ExactMatchQueryBuilder();
		exactMatchesLangA.setColumn("DStichwort");
		exactMatchesLangB = new ExactMatchQueryBuilder();
		exactMatchesLangB.setColumn("RStichwort");
	}

	private Type getType(FieldType type) {
		if (type == null) {
			return Type.STRING;
		}
		switch(type) {
		case INTEGER: return Type.INT;
		default: return Type.STRING;
		}
	}


	
	public QueryResult query(SearchCriteria searchCriteria, Pagination pagination) throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException, InvalidTokenOffsetsException {
		long start = System.nanoTime();
		validatePagination(pagination);

		long s1 = System.nanoTime();
		Query query = indexManager.buildQuery(searchCriteria);
		TopDocs docs = null;

		Sort sort = new Sort();
		String[] items = null;
		if(searchCriteria.getSearchDirection() == SearchDirection.ROMANSH) {
			items = new String[] { "RStichwort", "RStichwort_sort"};
		} else {
			items = new String[] { "DStichwort", "DStichwort_sort"};
		}
		SortField[] fields = new SortField[items.length+1];
		fields[0] = SortField.FIELD_SCORE;
		for(int i = 0; i < items.length; i++) {
			String item = items[i];
			fields[i+1] = new SortField(item, sortTypes.get(item));
		}
		sort.setSort(fields);
		QueryResult result = null;

		int pageSize = pagination.getPageSize();
		int pageNr = pagination.getPage();
		long e1 = System.nanoTime();
		try {
			long s2 = System.nanoTime();
			docs = luceneIndexRam.get(language).getSearcher().search(query, pageSize * pageNr, sort);
			long e2 = System.nanoTime();
			result = toQueryResult(docs, pageSize * (pageNr - 1), pageSize);
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

	private void validatePagination(Pagination pagination) {
		if (pagination.getPage() < 0) {
			pagination.setPage(0);
		}
		if (pagination.getPageSize() > 200) {
			pagination.setPageSize(200);
		}
		if (pagination.getPageSize() < 1) {
			pagination.setPageSize(1);
		}
	}
	
	private QueryResult toQueryResult(TopDocs docs, int startIndex, int pageSize) throws NoIndexAvailableException, IOException, InvalidTokenOffsetsException {
		final ArrayList<LemmaVersion> results = new ArrayList<LemmaVersion>(pageSize);
		final ScoreDoc[] scoreDocs = docs.scoreDocs;
		IndexSearcher searcher = luceneIndexRam.get(language).getSearcher();
		for (int i = startIndex; i < scoreDocs.length
				&& i < startIndex + pageSize; i++) {
			Document doc = searcher.doc(scoreDocs[i].doc);
			LemmaVersion e = indexManager.getLemmaVersion(doc);
			results.add(e);
		}

		int currentPage = 1;
		if (startIndex != 0) {
			currentPage = (startIndex / pageSize) + 1;
		}

		return new QueryResult(results, docs.totalHits, pageSize, currentPage);
	}

	
	public QueryResult queryExact(String phrase, DictionaryLanguage dictionaryLanguage) throws NoIndexAvailableException, BrokenIndexException, InvalidQueryException {
		String sortField = null;
		List<Query> queries = null;
		sortField = dictionaryLanguage == DictionaryLanguage.GERMAN ? "DStichwort_sort" : "RStichwort_sort";
		if(dictionaryLanguage == DictionaryLanguage.GERMAN) {
			queries = exactMatchesLangA.transform(phrase);
		} else {
			queries = exactMatchesLangB.transform(phrase);
		}
		int pageSize = 120;
		try {
			BooleanQuery query = new BooleanQuery(true);
			for (Query q : queries) {
				query.add(q, Occur.SHOULD);
			}
			BooleanQuery bc = new BooleanQuery();
			bc.add(query, Occur.MUST);
			bc.add(new TermQuery(new Term(LemmaVersion.VERIFICATION, LemmaVersion.Verification.ACCEPTED.toString())),Occur.MUST);
			query = bc;
			TopDocs docs = luceneIndexRam.get(language).getSearcher().search(query, null, pageSize, new Sort(new SortField(sortField, Type.STRING)));

			return toQueryResult(docs, 0, pageSize);
		} catch (IOException e) {
			throw new BrokenIndexException("Broken index!", e);
		} catch (InvalidTokenOffsetsException e) {
			throw new InvalidQueryException("Highlighting failed", e);
		}
	}

	public QueryResult getAllStartingWith(SearchDirection searchDirection, String prefix, int page) throws NoIndexAvailableException, BrokenIndexException, InvalidQueryException {
		String field = null;
		String sortField = null;
		List<Query> queries = null;
		field = "RStichwort";
		boolean firstLanguage = false;
		if (searchDirection == SearchDirection.GERMAN) {
			field = "DStichwort";
			firstLanguage = true;
		}

		if(firstLanguage) {
			queries = langAIndexBuilder.transform(prefix);
			sortField = langAIndexBuilder.getIndexSortField();
		} else {
			queries = langBIndexBuilder.transform(prefix);
			sortField = langBIndexBuilder.getIndexSortField();
		}
		int pageSize = 120;
		try {
			BooleanQuery query = new BooleanQuery(true);
			for (Query q : queries) {
				query.add(q, Occur.SHOULD);
			}
			BooleanQuery bc = new BooleanQuery();
			bc.add(query, Occur.MUST);
			bc.add(new TermQuery(new Term(LemmaVersion.VERIFICATION, LemmaVersion.Verification.ACCEPTED.toString())),Occur.MUST);
			query = bc;
			TopDocs docs = luceneIndexRam.get(language).getSearcher().search(query,
					new DuplicateFilter(field), Integer.MAX_VALUE,
					new Sort(new SortField(sortField, Type.STRING)));
			return toQueryResult(docs, (page - 1) * pageSize, pageSize);
		} catch (IOException e) {
			throw new BrokenIndexException("Broken index!", e);
		} catch (InvalidTokenOffsetsException e) {
			throw new InvalidQueryException("Highlighting failed", e);
		}
	}

	public IndexStatistics getIndexStatistics() {
		final IndexStatistics statistics = new IndexStatistics();
		try {
			queue.push(new IndexOperation() {

				@Override
				public void execute(Language language) throws Exception {
					int all = luceneIndexRam.get(language).getSearcher().getIndexReader().numDocs();
					int unverified = 0;
					int approved = 0;
					int unknown = 0;
					IndexReader reader = luceneIndexRam.get(language).getSearcher().getIndexReader();
					HashMap<String, Integer> byCategory = new HashMap<String, Integer>();
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
						String flexType = document.get(LemmaVersion.RM_FLEX_TYPE);
						if(flexType != null) {
							Integer old = byCategory.get(flexType);
							if(old == null) old = 0;
							byCategory.put(flexType, old+1);
						}
					}
					statistics.setOverlayCount(byCategory);
					statistics.setNumberOfEntries(all);
					statistics.setUnverifiedEntries(unverified);
					statistics.setApprovedEntries(approved);
					statistics.setUnknown(unknown);
					statistics.setLastUpdated(luceneIndexFilesystem.get(language).getLastUpdated());
				}
			});
			return statistics;
		} catch (Exception e) {
			return new IndexStatistics();
		}
	}

	public ArrayList<String> getSuggestionsForField(String fieldName, String searchTerm, int limit) throws QueryNodeException, NoIndexAvailableException, IOException, ParseException {
		Query query = indexManager.getSuggestionsQuery(fieldName, searchTerm);
		if(query == null) {
			return new ArrayList<>();
		}
		ArrayList<String> results = new ArrayList<>();
		Set<String> allValues = new TreeSet<>();
		ArrayList<String> fields = new ArrayList<>();
		fields.add(fieldName);
		for (String field : fields) {
			TopDocs docs = luceneIndexRam.get(language).getSearcher().search(query, new DuplicateFilter(field), Integer.MAX_VALUE);
			ScoreDoc[] scoreDocs = docs.scoreDocs;
			for (int i = 0; i < scoreDocs.length; i++) {
				Document doc = luceneIndexRam.get(language).getSearcher().doc(scoreDocs[i].doc);
				IndexableField[] indexableFields = doc.getFields(field);
				// FIXME: Don't split always - instead, implement MaalrFieldType.CSV!
				for (IndexableField indexedField : indexableFields) {
					String[] parts = indexedField.stringValue().split(", ");//TODO: FieldType.CSV has no effect
					for (String part : parts) {
						if(part.toLowerCase().startsWith(searchTerm.toLowerCase())) {
							allValues.add(part);
						}
					}
				}
			}
		}
		results.addAll(allValues);
		if(results.size() > 0) {
			List<String> resultList = results.subList(0, Math.min(results.size(), limit)); //restrict length to 'limit'
			return new ArrayList<>(resultList);
		} else {
			return results;
		}
	}

	// p.ex. grammar, gender
	public ArrayList<String> getSuggestionsForFieldChoice(SuggestionField suggestionField, String value, int limit) throws NoIndexAvailableException, IOException {
		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setSuggestions(true);
		Set<String> fields = Set.of();
		if (suggestionField == SuggestionField.GENDER) {
			searchCriteria.setGender(value);
			fields = Set.of("DGenus", "RGenus");
		} else if (suggestionField == SuggestionField.GRAMMAR) {
			searchCriteria.setGrammar(value);
			fields = Set.of("DGrammatik", "RGrammatik");
		}
		Query query = indexManager.buildQuery(searchCriteria);
		if(query == null) {
			return new ArrayList<>();
		}
		Set<String> allValues = new TreeSet<>();
		for (String field : fields) {
			TopDocs docs = luceneIndexRam.get(language).getSearcher().search(query, new DuplicateFilter(field), Integer.MAX_VALUE);
			ScoreDoc[] scoreDocs = docs.scoreDocs;
			for (int i = 0; i < scoreDocs.length; i++) {
				Document doc = luceneIndexRam.get(language).getSearcher().doc(scoreDocs[i].doc);
				IndexableField[] indexableFields = doc.getFields(field);
				// FIXME: Don't split always - instead, implement MaalrFieldType.CSV!
				for (IndexableField indexedField : indexableFields) {
					String[] parts = indexedField.stringValue().split(", ");//TODO: FieldType.CSV has no effect
					for (String part : parts) {
						if(part.toLowerCase().startsWith(value.toLowerCase())) {
							allValues.add(part);
						}
					}
				}
			}
		}
		ArrayList<String> results = new ArrayList<>(allValues);
		if(results.size() > 0) {
			List<String> resultList = results.subList(0, Math.min(results.size(), limit)); //restrict length to 'limit'
			return new ArrayList<>(resultList);
		} else {
			return results;
		}
	}

	public void reloadIndex() throws NoIndexAvailableException {
		try {
			queue.push(new IndexOperation() {
				
				@Override
				public void execute(Language language) throws NoIndexAvailableException {
					logger.info("Reloading index...");
					luceneIndexRam.get(language).reloadIndex();
					logger.info("Index reloaded");
				}
			});
		} catch (Exception e) {
			throw new NoIndexAvailableException(e);
		}
	}

	public void addToIndex(final Iterator<LexEntry> iterator) throws NoDatabaseAvailableException, IndexException {
		try {
			queue.push(new IndexOperation() {
				
				@Override
				public void execute(Language language) throws Exception {
					int added = luceneIndexFilesystem.get(language).addToIndex(iterator);
				}
			});
		} catch (Exception e) {
			throw new IndexException(e);
		}
	}

	public void dropIndex() throws IndexException {
		try {
			queue.push(new IndexOperation() {

				@Override
				public void execute(Language language) throws Exception {
					luceneIndexFilesystem.get(language).dropIndex();
				}
			});
		} catch (Exception e) {
			throw new IndexException(e);
		}
	}
	
	public void update(final LexEntry entry) throws IOException {
		try {
			queue.push(new IndexOperation() {
				
				@Override
				public void execute(Language language) throws Exception {
					long start = System.currentTimeMillis();
					luceneIndexFilesystem.get(language).update(entry);
					luceneIndexRam.get(language).update(entry);
					long end = System.currentTimeMillis();
					logger.info("Index update for entry " + entry.getId()
							+ " completed after " + (end - start) + " ms.");
				}
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

}
