package ch.pledarigrond.lucene.querybuilder;

import ch.pledarigrond.common.data.lucene.FieldType;
import ch.pledarigrond.lucene.analyzers.CaseInsensitiveWhitespaceAnalyzer;
import ch.pledarigrond.lucene.util.TokenizerHelper;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BoostQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;

import java.util.Arrays;
import java.util.List;

/**
 * This query builder generates lucene-queries from
 * a single field, to modify the sort order of
 * the results of a query:
 * If a user searches for a term (for instance, 'car'), all exact
 * translations should be listed before any translation which contains
 * the term among others ('car insurance', 'car crash', etc.). This is
 * realized by using both {@link TermQuery} and {@link PrefixQuery},
 * and by modifying the field names which are searched by lucene.
 */
public class DefaultQueryBuilder extends AbstractQueryBuilder {

    @Override
    protected void buildColumnToFieldsMapping() {
        registerFieldMapping("first", false, FieldType.STRING, true, false);
        registerFieldMapping("second", true, FieldType.TEXT, true, false);
        registerFieldMapping("third", false, FieldType.STRING, true, false);
    }

    @Override
    public List<Query> transform(String value) {
        value = TokenizerHelper.tokenizeString(new CaseInsensitiveWhitespaceAnalyzer(), value);

        Query first = new TermQuery(new Term(super.getFieldName("first"), value));
        first = new BoostQuery(first, 1000f);

        //match entries where search phrase is followed by whitespace
        Query second = new TermQuery(new Term(super.getFieldName("second"), value));
        second = new BoostQuery(second, 100f);

        Query fourth = new PrefixQuery(new Term(super.getFieldName("first"), value));
        fourth = new BoostQuery(fourth, 10f);

        Query fifth = new PrefixQuery(new Term(super.getFieldName("third"), value));

        return Arrays.asList(first, second, fourth, fifth);
    }
}
