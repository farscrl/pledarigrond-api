package ch.pledarigrond.lucene.config;

import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.data.lucene.IndexedColumn;
import ch.pledarigrond.lucene.core.BuilderRegistry;
import ch.pledarigrond.lucene.util.DatabaseFields;
import ch.pledarigrond.lucene.util.IndexedColumnHelper;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexableField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class IndexManager {

    protected static final Logger logger = LoggerFactory.getLogger(IndexManager.class);

    protected String[] allColumns;

    /**
     * On db field is sometimes mapped to multiple index fields. this map
     * contains the mapping from db field to index fields.
     */
    protected Map<String, List<IndexedColumn>> dbFieldMapping = new HashMap<>();

    protected BuilderRegistry builderRegistry = new BuilderRegistry();

    /**
     * Contains all field names which are ignored when generating the index.
     * A field is ignored if it has not been set up in the indexConfiguration-
     * section of the search configuration. For each ignored field, a warning
     * message will be logged.
     */
    private final Set<String> ignored = new HashSet<>();

    private static IndexManager indexManagerInstance;

    /**
     * Returns the singleton instance of this class.
     * @return IndexManager
     */
    public static synchronized IndexManager getInstance() {
        if(indexManagerInstance == null) {
            try {
                indexManagerInstance = new IndexManager();
            } catch (Exception e) {
                logger.error("Failed to initialize IndexManager", e);
                throw new RuntimeException(e);
            }
        }
        return indexManagerInstance;
    }

    public IndexManager() {
        logger.info("Generate list of all index-fields");
        Set<IndexedColumn> finalColumnSet = new TreeSet<>(Comparator.comparing(IndexedColumn::getIndexFieldName));

        finalColumnSet.addAll(DatabaseFields.getDatabaseColumns());
        extractListOfAllColumns(finalColumnSet);

        // add fields for sorting used by query builders
        finalColumnSet.addAll(builderRegistry.getAllRegisteredColumns());

        // Got all lucene fields, now create field factories to ensure that they will
        // be created and filled when a new entry is inserted.
        logger.info("Field analysis completed, index will contain the following fields:");


        for (IndexedColumn item : finalColumnSet) {
            logger.info("   " + item.toString());
            List<IndexedColumn> fields = dbFieldMapping.computeIfAbsent(item.getSourceColumnName(), k -> new ArrayList<>());
            fields.add(item);
        }
    }

    public static IndexManager getInstance(Language language) {
        return switch (language) {
            case PUTER -> IndexManager.getInstance();
            case RUMANTSCHGRISCHUN -> IndexManager.getInstance();
            case SURMIRAN -> IndexManager.getInstance();
            case SURSILVAN -> IndexManager.getInstance();
            case SUTSILVAN -> IndexManager.getInstance();
            case VALLADER -> IndexManager.getInstance();
        };
    }

    public BuilderRegistry getBuilderRegistry() {
        return builderRegistry;
    }





    public Document getDocument(LexEntry lexEntry, LemmaVersion lemmaVersion) {
        Document doc = new Document();
        Set<Map.Entry<String, String>> entries = lemmaVersion.getLemmaValues().entrySet();
        StringBuilder allFieldsList = new StringBuilder();
        for (Map.Entry<String, String> entry : entries) {
            if(ignored.contains(entry.getKey())) continue;
            List<IndexableField> fields = toField(entry.getKey(), entry.getValue());
            if(fields == null) {
                logger.warn("No mapping for field " + entry.getKey() + " - field will not be indexed!");
                ignored.add(entry.getKey());
                continue;
            }
            for (IndexableField field : fields) {
                doc.add(field);
            }
            allFieldsList.append(entry.getKey());
            allFieldsList.append(' ');
        }
        List<IndexableField> fields = toField(LemmaVersion.FIELD_NAMES, allFieldsList.toString());
        for (IndexableField field : fields) {
            doc.add(field);
        }
        addPgFieldsToDocument(lexEntry, lemmaVersion, doc);
        return doc;
    }

    public LemmaVersion getLemmaVersion(Document document) {
        LemmaVersion lv = new LemmaVersion();
        for (String fieldName : allColumns) {
            IndexableField[] fields = document.getFields(fieldName);
            if(fields.length == 0) continue;
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < fields.length; i++) {
                sb.append(fields[i].stringValue());
                if(i < fields.length-1) {
                    sb.append(",");
                }
            }
            lv.putEntryValue(fieldName, sb.toString());
        }
        addPgFieldsToLemmaVersion(document, lv);
        return lv;
    }

    protected void extractListOfAllColumns(Set<IndexedColumn> finalColumnSet) {
        Set<String> columnNames = new TreeSet<>();
        for (IndexedColumn item : finalColumnSet) {
            columnNames.add(item.getSourceColumnName());
        }

        allColumns = columnNames.toArray(new String[0]);
    }

    protected List<IndexableField> toField(String key, String value) {
        List<IndexedColumn> indexFields = dbFieldMapping.get(key);
        if(indexFields == null || indexFields.isEmpty()) {
            return null;
        }

        List<IndexableField> fields = new ArrayList<IndexableField>();
        for (IndexedColumn f : indexFields) {
            List<IndexableField> field = IndexedColumnHelper.getFields(f, value);
            if(field != null) {
                fields.addAll(field);
            }
        }
        return fields;
    }

    /**
     * Helper method to add the default pg fields of a {@link LemmaVersion} to a lucene {@link Document}.
     * @param lexEntry
     * @param version
     * @param document
     */
    protected void addPgFieldsToDocument(LexEntry lexEntry, LemmaVersion version, Document document) {
        document.add(new StringField(LexEntry.ID, lexEntry.getId(), Field.Store.YES));
        document.add(new StringField(LemmaVersion.LEXENTRY_ID, lexEntry.getId(), Field.Store.YES));
        document.add(new StringField(LemmaVersion.ID, version.getInternalId()+"", Field.Store.YES));
        document.add(new StringField(LemmaVersion.VERIFICATION, version.getVerification().toString(), Field.Store.YES));
        document.add(new TextField(LemmaVersion.VERIFICATION + "_analyzed", version.getVerification().toString().toLowerCase(), Field.Store.NO));
        if(version.getEntryValue(LemmaVersion.RM_INFLECTION_TYPE) != null) {
            document.add(new StringField(LemmaVersion.RM_INFLECTION_TYPE, version.getEntryValue(LemmaVersion.RM_INFLECTION_TYPE), Field.Store.YES));
        }
        if(version.getEntryValue(LemmaVersion.RM_INFLECTION_SUBTYPE) != null) {
            document.add(new StringField(LemmaVersion.RM_INFLECTION_SUBTYPE, version.getEntryValue(LemmaVersion.RM_INFLECTION_SUBTYPE), Field.Store.YES));
        }
        if (version.getPgValues().get(LemmaVersion.AUTOMATIC_CHANGE) != null) {
            document.add(new StringField(LemmaVersion.AUTOMATIC_CHANGE, version.getPgValues().get(LemmaVersion.AUTOMATIC_CHANGE).toLowerCase(), Field.Store.YES));
        }
        if (version.getPgValues().get(LemmaVersion.REVIEW_LATER) != null) {
            document.add(new StringField(LemmaVersion.REVIEW_LATER, version.getPgValues().get(LemmaVersion.REVIEW_LATER).toLowerCase(), Field.Store.YES));
        }
    }

    /**
     * Helper method to add default PG fields from a lucene {@link Document} to a {@link LemmaVersion}.
     * @param document
     * @param lemmaVersion
     */
    protected void addPgFieldsToLemmaVersion(Document document, LemmaVersion lemmaVersion) {
        lemmaVersion.putPgValue(LexEntry.ID, document.get(LexEntry.ID));
        lemmaVersion.putPgValue(LemmaVersion.LEXENTRY_ID, document.get(LemmaVersion.LEXENTRY_ID));
        lemmaVersion.putPgValue(LemmaVersion.ID, document.get(LemmaVersion.ID));
        lemmaVersion.putPgValue(LemmaVersion.VERIFICATION, document.get(LemmaVersion.VERIFICATION));
        lemmaVersion.putEntryValue(LemmaVersion.RM_INFLECTION_TYPE, document.get(LemmaVersion.RM_INFLECTION_TYPE));
        lemmaVersion.putEntryValue(LemmaVersion.RM_INFLECTION_SUBTYPE, document.get(LemmaVersion.RM_INFLECTION_SUBTYPE));
    }
}
