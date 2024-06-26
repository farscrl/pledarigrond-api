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
import ch.pledarigrond.lucene.util.LuceneHelper;
import ch.pledarigrond.lucene.util.TokenizerHelper;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;

import java.util.List;

/**
 * A query builder to generate 'exact' queries. Matches both upper and lowercase.
 * See {@link PgQueryBuilder} and {@link DefaultQueryBuilder} for details.
 * @author sschwieb
 *
 */
public class ExactMatchQueryBuilder extends PgQueryBuilder {

	@Override
	protected void buildColumnToFieldsMapping() {
		registerFieldMapping("first", false, FieldType.STRING, true, false);
	}

	@Override
	public List<Query> transform(String value) {
		value = TokenizerHelper.tokenizeString(LuceneHelper.newWhitespaceAnalyzer(), value);

		// match both upper and lower case:
		Query query = new TermQuery(new Term(getFieldName("first"), value.toLowerCase()));

		return List.of(query);
	}
}
