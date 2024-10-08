package ch.pledarigrond.lucene.querybuilder;

import ch.pledarigrond.common.data.lucene.FieldType;
import ch.pledarigrond.lucene.util.TokenizerHelper;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BoostQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.RegexpQuery;
import org.apache.lucene.search.TermQuery;

import java.util.Arrays;
import java.util.List;

/**
 * A query builder for 'suffix' queries.
 */
public class SuffixQueryBuilder extends AbstractQueryBuilder {

    @Override
    protected void buildColumnToFieldsMapping() {
        registerFieldMapping("first", false, FieldType.STRING, true, false);
        registerFieldMapping("second", true, FieldType.STRING, false, false);
    }

    @Override
    public List<Query> transform(String value) {
        value = TokenizerHelper.tokenizeString(analyzer, value);

        // exact match is on top
        Query q1 = new TermQuery(new Term(getFieldName("first"), value));
        q1 = new BoostQuery(q1, 1000f);

        // all words ending with the given value
        Query q2 = new RegexpQuery(new Term(getFieldName("second"), ".*" + value));

        return Arrays.asList(q1, q2);
    }
}
