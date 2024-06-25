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
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
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
 * 'car crash', etc).
 *
 * This is a special case of the DefaultQueryBuilder, used for the fields
 * containing additional search terms. As those additional terms are not
 * seperated by any symbol (e.g. a comma), we use whitespaces to separate them.
 *
 * This is internally realized by using both
 * {@link TermQuery} and {@link PrefixQuery}, and by modifying the
 * field names which are searched by lucene.
 * <br>
 * 
 * @author sschwieb
 *
 */
public class DefaultQueryBuilderSplittingWhitespaces extends PgQueryBuilder {

	@Override
	protected void buildColumnToFieldsMapping() {
		registerFieldMapping("first", false, FieldType.STRING, true, false);
		registerFieldMapping("second",true, FieldType.TEXT, true, false);
	}

	@Override
	public List<Query> transform(String value) {
		value = TokenizerHelper.tokenizeString(analyzer, value);
		//match single word entries
		TermQuery first = new TermQuery(new Term(super.getFieldName("first"), value));
		first.setBoost(1000f);
		//match entries where searchphrase is followed by whitespace
		TermQuery second = new TermQuery(new Term(super.getFieldName("second"), value));
		second.setBoost(100f);
		//also match prefix of StringFields ...
		PrefixQuery third = new PrefixQuery(new Term(super.getFieldName("first"), value));
		third.setBoost(10f);
		//and of (analyzed) TextFields
		PrefixQuery fourth = new PrefixQuery(new Term(super.getFieldName("second"), value));

		List<Query> toReturn = Arrays.asList(first,second,third,fourth);

		 /* Must be a TextField to handle multi-word expressions. */
		String textField = super.getFieldName("second");
		QueryParser parser = new QueryParser(LuceneHelper.CURRENT, textField, analyzer);
		try {
			//match multi-word queries
			value = value.replace(" ", " AND ");
			//append wildcard (cf. PrefixQueries)
			value += "*";
			Query query = parser.parse(value);
			toReturn = Arrays.asList(query);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return toReturn;
	}
}
