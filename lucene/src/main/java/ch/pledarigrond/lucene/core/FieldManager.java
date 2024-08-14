package ch.pledarigrond.lucene.core;

import ch.pledarigrond.common.data.lucene.IndexedColumn;
import ch.pledarigrond.lucene.util.DatabaseFields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class FieldManager {

    protected static final Logger logger = LoggerFactory.getLogger(FieldManager.class);

    /**
     * On db field is sometimes mapped to multiple index fields. this map
     * contains the mapping from db field to index fields.
     */
    protected Map<String, List<IndexedColumn>> dbFieldMapping = new HashMap<>();

    /**
     * Contains all field names which are ignored when generating the index.
     * A field is ignored if it has not been set up in the indexConfiguration-
     * section of the search configuration. For each ignored field, a warning
     * message will be logged.
     */
    private final Set<String> ignored = new HashSet<>();

    /**
     * Contains all fields which are used in the index. It is used to retrieve
     * the values from the index when a search result is returned.
     */
    protected String[] allColumns;

    private static FieldManager fieldManagerInstance;

    /**
     * Returns the singleton instance of this class.
     */
    public static synchronized FieldManager getInstance() {
        if (fieldManagerInstance == null) {
            try {
                fieldManagerInstance = new FieldManager();
            } catch (Exception e) {
                logger.error("Failed to initialize IndexManager", e);
                throw new RuntimeException(e);
            }
        }
        return fieldManagerInstance;
    }

    public FieldManager() {
        logger.info("Generate list of all index-fields");
        Set<IndexedColumn> finalColumnSet = new TreeSet<>(Comparator.comparing(IndexedColumn::getIndexFieldName));

        finalColumnSet.addAll(DatabaseFields.getDatabaseColumns());
        allColumns = extractListOfAllColumns(finalColumnSet);

        // add fields for sorting used by query builders
        finalColumnSet.addAll(BuilderRegistry.getInstance().getAllRegisteredColumns());

        // Got all lucene fields, now create field factories to ensure that they will
        // be created and filled when a new entry is inserted.
        logger.info("Field analysis completed, index will contain the following fields:");
        for (IndexedColumn item : finalColumnSet) {
            logger.info("   {}", item.toString());

            // adding items to the dbFieldMapping
            List<IndexedColumn> fields = dbFieldMapping.computeIfAbsent(item.getSourceColumnName(), k -> new ArrayList<>());
            fields.add(item);
        }
    }

    public String[] getAllColumns() {
        return allColumns;
    }

    public Map<String, List<IndexedColumn>> getDbFieldMapping() {
        return dbFieldMapping;
    }

    public Set<String> getIgnored() {
        return ignored;
    }

    protected String[] extractListOfAllColumns(Set<IndexedColumn> finalColumnSet) {
        Set<String> columnNames = new TreeSet<>();
        for (IndexedColumn item : finalColumnSet) {
            columnNames.add(item.getSourceColumnName());
        }

        return columnNames.toArray(new String[0]);
    }
}
