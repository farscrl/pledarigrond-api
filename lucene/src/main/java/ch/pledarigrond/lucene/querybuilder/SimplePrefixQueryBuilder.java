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
 * using Lucene WhiteSpaceAnalyzer (preserving all special chars, e.g. brackets)
 */
public class SimplePrefixQueryBuilder extends AbstractQueryBuilder {

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
