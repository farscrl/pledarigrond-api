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
package ch.pledarigrond.lucene.config.querybuilder.modifier;

import ch.pledarigrond.common.data.lucene.FieldType;
import ch.pledarigrond.lucene.config.querybuilder.PgQueryBuilder;
import ch.pledarigrond.lucene.util.TokenizerHelper;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.RegexpQuery;
import org.apache.lucene.search.TermQuery;

import java.util.Arrays;
import java.util.List;

/**
 * This query builder generates three lucene-queries from
 * a single {@link MaalrField}, to modify the sort order of
 * the results of a query: If a user searches for a term (for instance, 
 * 'car'), all exact translations should be listed before any
 * translation which contains the term among others ('car insurance', 
 * 'car crash', etc). This is internally realized by using both
 * {@link TermQuery} and {@link PrefixQuery}, and by modifying the
 * field names which are searched by lucene.
 * <br>
 * <strong>Note: The transformation of the field names is proprietary.</strong>
 * This means, that using this query builder requires a lucene index which
 * defines fields with special naming conventions. 
 * 
 * @author sschwieb
 *
 */
public class DefaultSurmiranQueryBuilder extends PgQueryBuilder {
	

	@Override
	protected void buildColumnToFieldsMapping() {
		registerFieldMapping("first", false, FieldType.STRING, true, false);
		registerFieldMapping("second",true, FieldType.STRING, false, false);
		registerFieldMapping("third", false, FieldType.STRING, true, false);
	}



	@Override
	public List<Query> transform(String value) {
		value = TokenizerHelper.tokenizeString(analyzer, value);
		TermQuery first = new TermQuery(new Term(super.getFieldName("first"), value));
		first.setBoost(1000f);
		//match entries where searchphrase is followed by whitespace
		RegexpQuery second = new RegexpQuery(new Term(super.getFieldName("second"), value+"( .*)"));
		second.setBoost(100f);
		//match entries where searchphrase is either preceded or surrounded by whitespace
		RegexpQuery third = new RegexpQuery(new Term(super.getFieldName("second"), "(.* )"+value+"( .*)?"));
		third.setBoost(10f);
		PrefixQuery fourth = new PrefixQuery(new Term(super.getFieldName("first"), value));
//		fourth.setBoost(10f);
		PrefixQuery fifth = new PrefixQuery(new Term(super.getFieldName("third"), value));
//		fifth.setBoost(10f);
		return Arrays.asList(first,second,third, fourth, fifth);
	}
	
	

}
