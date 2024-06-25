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
package ch.pledarigrond.lucene.querybuilder;

import ch.pledarigrond.common.data.lucene.FieldType;
import ch.pledarigrond.common.data.lucene.IndexedColumn;
import ch.pledarigrond.common.data.user.SearchCriteria;
import ch.pledarigrond.lucene.IndexManager;
import ch.pledarigrond.lucene.util.LuceneHelper;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.search.Query;

import java.util.*;

/**
 * This is the base class of all query builders, which are responsible
 * to query the dictionary. Query builders are used in two different
 * phases: During startup, they are instantiated and configured for each
 * needed configuration. Each query builder defines a set of
 * lucene fields required to perform queries, so during startup these
 * fields are used to set up the lucene index. When performing queries,
 * the {@link IndexManager} will use the information provided in
 * a {@link SearchCriteria } to find the relevant {@link PgQueryBuilder},
 * such that they can be used to build the lucene query string.
 * 
 * @author sschwieb
 *
 */
public abstract class PgQueryBuilder {
	
	protected final Analyzer analyzer = LuceneHelper.newAnalyzer();
	protected String column;
	private final Map<String, String> finalFieldNames = new HashMap<String, String>();
	private final Set<IndexedColumn> columns = new HashSet<IndexedColumn>();
	
	/**
	 * Initialize the builder for the given column.
	 * @param column the column to search in
	 */
	public PgQueryBuilder setColumn(String column) {
		this.column = column;
		buildColumnToFieldsMapping();
		return this;
	}
	
	/**
	 * Subclasses must override this method and register the index fields they
	 * require performing searches, by calling {@link PgQueryBuilder#registerFieldMapping(String, boolean, FieldType, boolean, boolean)}
	 * for each individual field.
	 */
	protected abstract void buildColumnToFieldsMapping();

	public abstract List<Query> transform(String field);
	
	/**
	 * Register a variation of the index field for the column the query builder
	 * was created for.
	 * @param name A symbolic name to lookup the field during querying
	 * @param analyzed lucene-specific: Whether or not the term should be analyzed
	 * @param type The type of the field
	 * @param lowercase Whether or not 
	 *
	 * @see LuceneHelper
	 */
	protected void registerFieldMapping(String name, boolean analyzed, FieldType type, boolean lowercase, boolean whitespace) {
		IndexedColumn item = new IndexedColumn(column);
		item.setAnalyzed(analyzed);
		item.setLowerCase(lowercase);
		item.setStored(false);
		item.setType(type);
		item.setWhitespaceAnalyzer(whitespace);
		String destField = column + getFieldSuffix(analyzed, lowercase, whitespace, type);
		finalFieldNames.put(name, destField);
		item.setIndexFieldName(destField);
		columns.add(item);
	}
	
	private String getFieldSuffix(boolean analyzed, boolean lowercase, boolean whitespace, FieldType type) {
		return "_" +
				(analyzed ? "a" : "na") +
				"_" +
				(whitespace ? "w" : "nw") +
				"_" +
				(lowercase ? "l" : "nl") +
				"_" +
				"t-" + type;
	}

	/**
	 * Returns all {@link IndexedColumn}s required by the 
	 * query builder.
	 * @return
	 */
	public Set<IndexedColumn> getRegisteredColumns() {
		return columns;
	}

	/**
	 * Returns the field name with the given symbolic name
	 * @param registeredName
	 * @return
	 */
	protected String getFieldName(String registeredName) {
		return finalFieldNames.get(registeredName);
	}

}
