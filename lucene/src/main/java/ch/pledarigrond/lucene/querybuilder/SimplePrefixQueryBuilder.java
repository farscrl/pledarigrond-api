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
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;

import java.util.List;

/**
 * This query builder generates a basic PrefixQuery for use in field-suggestions,
 * using Lucene's WhiteSpaceAnalyzer (preserving all special chars, e.g. brackets)
 * <br>
 * <strong>Note: The transformation of the field names is proprietary.</strong>
 * This means, that using this query builder requires a lucene index which
 * defines fields with special naming conventions. 
 * 
 * @author claesn
 *
 */
public class SimplePrefixQueryBuilder extends PgQueryBuilder {

	@Override
	protected void buildColumnToFieldsMapping() {
		registerFieldMapping("first", false, FieldType.STRING, true, false);
	}

	@Override
	public List<Query> transform(String value) {
		value = TokenizerHelper.tokenizeString(LuceneHelper.newWhitespaceAnalyzer(), value);
		Query query = new PrefixQuery(new Term(getFieldName("first"), value.toLowerCase()));
		return List.of(query);
	}
}
