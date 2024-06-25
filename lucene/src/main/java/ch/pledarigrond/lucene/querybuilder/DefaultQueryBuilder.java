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
import ch.pledarigrond.lucene.analyzers.CaseInsensitiveWhitespaceAnalyzer;
import ch.pledarigrond.lucene.util.TokenizerHelper;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;

import java.util.Arrays;
import java.util.List;

/**
 * This query builder generates three lucene-queries from
 * a single field, to modify the sort order of
 * the results of a query: If a user searches for a term (for instance, 
 * 'car'), all exact translations should be listed before any
 * translation which contains the term among others ('car insurance', 
 * 'car crash', etc.). This is internally realized by using both
 * {@link TermQuery} and {@link PrefixQuery}, and by modifying the
 * field names which are searched by lucene.
 * <br>
 * 
 * @author sschwieb
 *
 */
public class DefaultQueryBuilder extends PgQueryBuilder {

	@Override
	protected void buildColumnToFieldsMapping() {
		registerFieldMapping("first", false, FieldType.STRING, true, false);
		registerFieldMapping("second",true, FieldType.TEXT, true, false);
		registerFieldMapping("third", false, FieldType.STRING, true, false);
	}

	@Override
	public List<Query> transform(String value) {
		value = TokenizerHelper.tokenizeString(new CaseInsensitiveWhitespaceAnalyzer(), value);
		TermQuery first = new TermQuery(new Term(super.getFieldName("first"), value));
		first.setBoost(1000f);
		//match entries where searchphrase is followed by whitespace
		TermQuery second = new TermQuery(new Term(super.getFieldName("second"), value));
		second.setBoost(100f);
		PrefixQuery fourth = new PrefixQuery(new Term(super.getFieldName("first"), value));
		fourth.setBoost(10f);
		PrefixQuery fifth = new PrefixQuery(new Term(super.getFieldName("third"), value));
		return Arrays.asList(first,second,fourth, fifth);
	}
}
