package ch.pledarigrond.lucene.querybuilder;

import ch.pledarigrond.common.data.lucene.FieldType;
import ch.pledarigrond.lucene.util.TokenizerHelper;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BoostQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * This query builder generates lucene-queries from
 * a single field, to modify the sort order of
 * the results of a query:
 * If a user searches for a term (for instance, 'car'), all exact
 * translations should be listed before any translation which contains
 * the term among others ('car insurance', 'car crash', etc.).
 * This is a special case of the DefaultQueryBuilder, used for the fields
 * containing additional search terms. As those additional terms are not
 * seperated by any symbol (e.g. a comma), we use whitespaces to separate them.
 */
public class DefaultQueryBuilderSplittingWhitespaces extends AbstractQueryBuilder {

    @Override
    protected void buildColumnToFieldsMapping() {
        registerFieldMapping("first", false, FieldType.STRING, true, false);
        registerFieldMapping("second", true, FieldType.TEXT, true, false);
    }

    @Override
    public List<Query> transform(String value) {
        value = TokenizerHelper.tokenizeString(analyzer, value);

        //match single word entries
        Query first = new TermQuery(new Term(super.getFieldName("first"), value));
        first = new BoostQuery(first, 1000f);

        //match entries where searchphrase is followed by whitespace
        Query second = new TermQuery(new Term(super.getFieldName("second"), value));
        second = new BoostQuery(second, 100f);

        //also match prefix of StringFields ...
        Query third = new PrefixQuery(new Term(super.getFieldName("first"), value));
        third = new BoostQuery(third, 10f);

        //and of (analyzed) TextFields
        Query fourth = new PrefixQuery(new Term(super.getFieldName("second"), value));

        List<Query> toReturn = Arrays.asList(first, second, third, fourth);

        /* Must be a TextField to handle multi-word expressions. */
        String textField = super.getFieldName("second");
        QueryParser parser = new QueryParser(textField, analyzer);
        try {
            //match multi-word queries
            value = value.replace(" ", " AND ");
            //append wildcard (cf. PrefixQueries)
            value += "*";
            Query query = parser.parse(value);
            toReturn = Collections.singletonList(query);
        } catch (ParseException e) {
            logger.error("Could not parse query: {}", value, e);
        }
        return toReturn;
    }
}
