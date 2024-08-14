package ch.pledarigrond.lucene.querybuilder;

import ch.pledarigrond.common.data.lucene.FieldType;
import ch.pledarigrond.common.data.lucene.IndexedColumn;
import ch.pledarigrond.common.data.user.SearchCriteria;
import ch.pledarigrond.lucene.core.FieldManager;
import ch.pledarigrond.lucene.util.LuceneHelper;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.search.Query;

import java.util.*;

/**
 * This is the base class of all query builders, which are responsible
 * to query the dictionary.
 * Query builders are used in two different phases:
 * - During startup, they are instantiated and configured for each
 * needed configuration. Each query builder defines a set of
 * lucene fields required to perform queries, so during startup these
 * fields are used to set up the lucene index.
 * - When performing queries, the {@link FieldManager} will use the
 * information provided in a {@link SearchCriteria } to find the
 * relevant {@link AbstractQueryBuilder}, such that they can be used
 * to build the lucene query string.
 */
public abstract class AbstractQueryBuilder {

    protected final Analyzer analyzer = LuceneHelper.newAnalyzer();
    protected String column;
    private final Map<String, String> finalFieldNames = new HashMap<>();
    private final Set<IndexedColumn> columns = new HashSet<>();

    /**
     * Initialize the builder for the given column.
     *
     * @param column the column to search in
     */
    public AbstractQueryBuilder setColumn(String column) {
        this.column = column;
        buildColumnToFieldsMapping();
        return this;
    }

    /**
     * Subclasses must override this method and register the index fields they
     * require performing searches, by calling
     * {@link AbstractQueryBuilder#registerFieldMapping(String, boolean, FieldType, boolean, boolean)}
     * for each individual field.
     */
    protected abstract void buildColumnToFieldsMapping();

    public abstract List<Query> transform(String field);

    /**
     * Register a variation of the index field for the column the query builder
     * was created for.
     */
    protected void registerFieldMapping(String name, boolean analyzed, FieldType type, boolean lowercase, boolean whitespace) {
        IndexedColumn item = new IndexedColumn(column);
        item.setAnalyzed(analyzed);
        item.setLowerCase(lowercase);
        item.setStored(false);
        item.setType(type);
        item.setWhitespaceAnalyzer(whitespace);
        String destField = column + getFieldSuffix(analyzed, lowercase, whitespace, type);
        finalFieldNames.put(name, destField);
        item.setIndexFieldName(destField);
        columns.add(item);
    }

    private String getFieldSuffix(boolean analyzed, boolean lowercase, boolean whitespace, FieldType type) {
        return "_" +
                (analyzed ? "a" : "na") +
                "_" +
                (whitespace ? "w" : "nw") +
                "_" +
                (lowercase ? "l" : "nl") +
                "_" +
                "t-" + type;
    }

    public Set<IndexedColumn> getRegisteredColumns() {
        return columns;
    }

    protected String getFieldName(String registeredName) {
        return finalFieldNames.get(registeredName);
    }
}
