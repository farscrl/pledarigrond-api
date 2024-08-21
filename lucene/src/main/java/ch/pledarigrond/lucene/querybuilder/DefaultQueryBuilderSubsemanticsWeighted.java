package ch.pledarigrond.lucene.querybuilder;

import ch.pledarigrond.common.data.lucene.FieldType;
import ch.pledarigrond.lucene.analyzers.CaseInsensitiveWhitespaceAnalyzer;
import ch.pledarigrond.lucene.util.TokenizerHelper;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;

import java.util.Arrays;
import java.util.List;

/**
 * This query builder generates lucene-queries from
 * a single field, to modify the sort order of
 * the results of a query:
 * If a user searches for a term (for instance, 'car'), all exact
 * translations should be listed before any translation which contains
 * the term among others ('car insurance', 'car crash', etc.).
 * Under the terms with an exact match, entries without subsemantics
 * should be listed first, followed by entries with subsemantics but
 * not equal to vulgar words, and finally entries with subsemantics
 * equal to vulgar words.
 */
public class DefaultQueryBuilderSubsemanticsWeighted extends AbstractQueryBuilder {

    @Override
    protected void buildColumnToFieldsMapping() {
        registerFieldMapping("first", false, FieldType.STRING, true, false);
        registerFieldMapping("second", true, FieldType.TEXT, true, false);
        registerFieldMapping("third", false, FieldType.STRING, true, false);
    }

    @Override
    public List<Query> transform(String value) {
        value = TokenizerHelper.tokenizeString(new CaseInsensitiveWhitespaceAnalyzer(), value);
        String subsemanticsField = "RSubsemantik";
        if (column.equals("DStichwort")) {
            subsemanticsField = "DSubsemantik";
        }

        String[] vulgarWords = {"vulg."};

        // Entries with searchField equal to passed value and subsemantics not set
        BooleanQuery.Builder firstQueryBuilder = new BooleanQuery.Builder();
        firstQueryBuilder.add(new TermQuery(new Term(super.getFieldName("first"), value)), BooleanClause.Occur.MUST);
        firstQueryBuilder.add(new TermRangeQuery(subsemanticsField, null, null, true, true), BooleanClause.Occur.MUST_NOT);
        Query first = new BoostQuery(firstQueryBuilder.build(), 100000f);

        // Entries with searchField equal to passed value and subsemantics set but not equal to vulgar words
        BooleanQuery.Builder secondQueryBuilder = new BooleanQuery.Builder();
        secondQueryBuilder.add(new TermQuery(new Term(super.getFieldName("first"), value)), BooleanClause.Occur.MUST);
        BooleanQuery.Builder notVulgarQuery = new BooleanQuery.Builder();
        for (String vulgarWord : vulgarWords) {
            notVulgarQuery.add(new TermQuery(new Term(subsemanticsField, vulgarWord)), BooleanClause.Occur.MUST_NOT);
        }
        secondQueryBuilder.add(notVulgarQuery.build(), BooleanClause.Occur.MUST);
        Query second = new BoostQuery(secondQueryBuilder.build(), 10000f);


        // Entries with searchField equal to passed value and subsemantics equal to one of the vulgar words
        BooleanQuery.Builder thirdQueryBuilder = new BooleanQuery.Builder();
        thirdQueryBuilder.add(new TermQuery(new Term(super.getFieldName("first"), value)), BooleanClause.Occur.MUST);
        BooleanQuery.Builder vulgarQuery = new BooleanQuery.Builder();
        for (String vulgarWord : vulgarWords) {
            vulgarQuery.add(new TermQuery(new Term(subsemanticsField, vulgarWord)), BooleanClause.Occur.SHOULD);
        }
        thirdQueryBuilder.add(vulgarQuery.build(), BooleanClause.Occur.MUST);
        Query third = new BoostQuery(thirdQueryBuilder.build(), 1000f);

        // match entries where search phrase is followed by whitespace
        Query fourth = new TermQuery(new Term(super.getFieldName("second"), value));
        fourth = new BoostQuery(fourth, 100f);

        // match entries where search phrase is the first word
        Query fifth = new PrefixQuery(new Term(super.getFieldName("first"), value));
        fifth = new BoostQuery(fifth, 10f);

        // match entries where search phrase is the first word
        Query sixth = new PrefixQuery(new Term(super.getFieldName("third"), value));

        return Arrays.asList(first, second, third, fourth, fifth, sixth);
    }
}
