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
 */
public class ExactMatchQueryBuilder extends AbstractQueryBuilder {

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
