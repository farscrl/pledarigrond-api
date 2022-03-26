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

import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.data.common.QueryResult;
import ch.pledarigrond.common.data.common.SearchDirection;
import ch.pledarigrond.common.data.lucene.FieldType;
import ch.pledarigrond.common.data.lucene.IndexStatistics;
import ch.pledarigrond.common.data.lucene.IndexedColumn;
import ch.pledarigrond.common.data.user.Pagination;
import ch.pledarigrond.common.config.SurmiranLanguageConfig;
import ch.pledarigrond.common.data.user.SearchCriteria;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.lucene.config.IndexManagerSurmiran;
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
public class Dictionary {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final NumberFormat formatter;

	private DictionaryLoader indexProvider;

	private DictionaryCreator indexCreator;

	private LuceneConfiguration luceneConfiguration;

	private IndexManagerSurmiran indexManager;

	private HashMap<String, Type> sortTypes;

	private SimplePrefixQueryBuilder langBIndexBuilder;

	private SimplePrefixQueryBuilder langAIndexBuilder;

	private ExactMatchQueryBuilder exactMatchesLangA;

	private ExactMatchQueryBuilder exactMatchesLangB;

	private final SurmiranLanguageConfig languageConfig;

	public Dictionary(LuceneConfiguration configuration) throws IOException {
		this();
		setLuceneConfiguration(configuration);
	}

	public Dictionary() {
		formatter = (NumberFormat) NumberFormat.getNumberInstance().clone();
		formatter.setMaximumFractionDigits(3);
		logger.info("Created new index.");
		languageConfig = new SurmiranLanguageConfig();
	}
	
	public LuceneConfiguration getLuceneConfiguration() {
		return luceneConfiguration;
	}

	public void setLuceneConfiguration(LuceneConfiguration luceneConfiguration)
			throws IOException {
		this.luceneConfiguration = luceneConfiguration;
		indexProvider = new DictionaryLoader();
		indexProvider.setLuceneConfiguration(luceneConfiguration);
		indexCreator = new DictionaryCreator();
		indexCreator.setLuceneConfiguration(luceneConfiguration);
		indexCreator.initialize();
		indexCreator.resetIndexDirectory();
		indexManager = IndexManagerSurmiran.getInstance();
		Set<IndexedColumn> columns = indexManager.getFinalColumnSet();
		sortTypes = new HashMap<>();
		for (IndexedColumn item : columns) {
			sortTypes.put(item.getIndexFieldName(), getType(item.getType()));
		}
		// Create query builder for static dictionary pages
		langAIndexBuilder = new SimplePrefixQueryBuilder();
		langAIndexBuilder.setColumn("DStichwort");
		langBIndexBuilder = new SimplePrefixQueryBuilder();
		langBIndexBuilder.setColumn("DStichwort"); // TODO: Should this be "RStichwort"?
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
			docs = indexProvider.getSearcher().search(query, pageSize * (pageNr + 1), sort);
			long e2 = System.nanoTime();
			result = toQueryResult(docs, pageSize * pageNr, pageSize);
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
		IndexSearcher searcher = indexProvider.getSearcher();
		for (int i = startIndex; i < scoreDocs.length
				&& i < startIndex + pageSize; i++) {
			Document doc = searcher.doc(scoreDocs[i].doc);
			LemmaVersion e = indexManager.getLemmaVersion(doc);
			results.add(e);
		}
		return new QueryResult(results, docs.totalHits, pageSize);
	}

	
	public QueryResult queryExact(String phrase, boolean firstLanguage) throws NoIndexAvailableException, BrokenIndexException, InvalidQueryException {
		String sortField = null;
		List<Query> queries = null;
		sortField = firstLanguage ? "DStichwort_sort" : "RStichwort_sort";
		if(firstLanguage) {
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
			TopDocs docs = indexProvider.getSearcher().search(query, null, pageSize, new Sort(new SortField(sortField, Type.STRING)));

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
			TopDocs docs = indexProvider.getSearcher().search(query,
					new DuplicateFilter(field), Integer.MAX_VALUE,
					new Sort(new SortField(sortField, Type.STRING)));
			return toQueryResult(docs, page * pageSize, pageSize);
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
				public void execute() throws Exception {
					int all = indexProvider.getSearcher().getIndexReader().numDocs();
					int unverified = 0;
					int approved = 0;
					int unknown = 0;
					IndexReader reader = indexProvider.getSearcher().getIndexReader();
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
						String overlayA = document.get(LemmaVersion.OVERLAY_LANG1);
						if(overlayA != null) {
							Integer old = byCategory.get(overlayA);
							if(old == null) old = 0;
							byCategory.put(overlayA, old+1);
						}
						String overlayB = document.get(LemmaVersion.OVERLAY_LANG2);
						if(overlayB != null) {
							Integer old = byCategory.get(overlayB);
							if(old == null) old = 0;
							byCategory.put(overlayB, old+1);
						}
						
					}
					statistics.setOverlayCount(byCategory);
					statistics.setNumberOfEntries(all);
					statistics.setUnverifiedEntries(unverified);
					statistics.setApprovedEntries(approved);
					statistics.setUnknown(unknown);
					statistics.setLastUpdated(indexCreator.getLastUpdated());
				}
			});
			return statistics;
		} catch (Exception e) {
			return new IndexStatistics();
		}
	}
	
	public ArrayList<String> getSuggestionsForField(String fieldName, String value, int limit) throws QueryNodeException, NoIndexAvailableException, IOException, ParseException {
		Query query = indexManager.getSuggestionsQuery(fieldName, value);
		if(query == null) {
			return new ArrayList<String>();
		}
		ArrayList<String> results = new ArrayList<String>();
		Set<String> allValues = new TreeSet<String>();
		ArrayList<String> fields = new ArrayList<String>();
		fields.add(fieldName);
		for (String field : fields) {
			TopDocs docs = indexProvider.getSearcher().search(query, new DuplicateFilter(field), Integer.MAX_VALUE);
			ScoreDoc[] scoreDocs = docs.scoreDocs;
			for (int i = 0; i < scoreDocs.length; i++) {
				Document doc = indexProvider.getSearcher().doc(scoreDocs[i].doc);
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
		results.addAll(allValues);
		if(results.size() > 0) {
			List<String> resultList = results.subList(0, Math.min(results.size(), limit));//restrict length to 'limit'
			return new ArrayList<String>(resultList);
		} else {
			return results;
		}
	}
	
	public ArrayList<String> getSuggestionsForFieldChoice(String fieldName, String value, int limit) throws QueryNodeException, NoIndexAvailableException, IOException, ParseException {
		/*MaalrQuery maalrQuery = new MaalrQuery();
		maalrQuery.setQueryValue(fieldName, value);
		Query query = indexManager.buildQuery(maalrQuery);
		if(query == null) {
			return new ArrayList<String>();
		}
		ArrayList<String> results = new ArrayList<String>();
		Set<String> allValues = new TreeSet<String>();
		Set<String> fields = indexManager.getFieldNames(fieldName);
		for (String field : fields) {
			TopDocs docs = indexProvider.getSearcher().search(query, new DuplicateFilter(field), Integer.MAX_VALUE);
			ScoreDoc[] scoreDocs = docs.scoreDocs;
			for (int i = 0; i < scoreDocs.length; i++) {
				Document doc = indexProvider.getSearcher().doc(scoreDocs[i].doc);
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
		results.addAll(allValues);
		if(results.size() > 0) {
			List<String> resultList = results.subList(0, Math.min(results.size(), limit));//restrict length to 'limit'
			return new ArrayList<String>(resultList);
		} else {
			return results;
		}*/
		return new ArrayList<String>();
	}

	private IndexCommandQueue queue = IndexCommandQueue.getInstance();

	public void reloadIndex() throws NoIndexAvailableException {
		try {
			queue.push(new IndexOperation() {
				
				@Override
				public void execute() throws NoIndexAvailableException {
					logger.info("Reloading index...");
					indexProvider.reloadIndex();
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
				public void execute() throws Exception {
					int added = indexCreator.addToIndex(iterator);
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
				public void execute() throws Exception {
					indexCreator.dropIndex();
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
				public void execute() throws Exception {
					long start = System.currentTimeMillis();
					indexCreator.update(entry);
					indexProvider.update(entry);
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
				public void execute() throws Exception {
					indexCreator.delete(entry);
					indexProvider.delete(entry);
				}
			});
		} catch (Exception e) {
			throw new IOException(e);
		}
	}

}
