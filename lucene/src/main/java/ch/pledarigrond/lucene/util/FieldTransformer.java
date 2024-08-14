package ch.pledarigrond.lucene.util;

import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.data.lucene.IndexedColumn;
import ch.pledarigrond.lucene.core.FieldManager;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexableField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FieldTransformer {

    protected static final Logger logger = LoggerFactory.getLogger(FieldTransformer.class);

    public static Document getDocument(LexEntry lexEntry, LemmaVersion lemmaVersion) {
        Document doc = new Document();
        Set<Map.Entry<String, String>> entries = lemmaVersion.getLemmaValues().entrySet();
        StringBuilder allFieldsList = new StringBuilder();
        for (Map.Entry<String, String> entry : entries) {
            if (FieldManager.getInstance().getIgnored().contains(entry.getKey())) {
                continue;
            }

            List<IndexableField> fields = toField(entry.getKey(), entry.getValue());
            if (fields == null) {
                logger.warn("No mapping for field {} - field will not be indexed!", entry.getKey());
                FieldManager.getInstance().getIgnored().add(entry.getKey());
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

    public static LemmaVersion getLemmaVersion(Document document) {
        LemmaVersion lv = new LemmaVersion();
        for (String fieldName : FieldManager.getInstance().getAllColumns()) {
            IndexableField[] fields = document.getFields(fieldName);
            if (fields.length == 0) continue;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < fields.length; i++) {
                sb.append(fields[i].stringValue());
                if (i < fields.length - 1) {
                    sb.append(",");
                }
            }
            lv.putEntryValue(fieldName, sb.toString());
        }
        addPgFieldsToLemmaVersion(document, lv);
        return lv;
    }

    protected static List<IndexableField> toField(String key, String value) {
        List<IndexedColumn> indexFields = FieldManager.getInstance().getDbFieldMapping().get(key);
        if (indexFields == null || indexFields.isEmpty()) {
            return null;
        }

        List<IndexableField> fields = new ArrayList<>();
        for (IndexedColumn f : indexFields) {
            List<IndexableField> field = IndexedColumnHelper.getFields(f, value);
            if (field != null) {
                fields.addAll(field);
            }
        }
        return fields;
    }

    /**
     * Helper method to add the default pg fields of a {@link LemmaVersion} to a lucene {@link Document}.
     */
    protected static void addPgFieldsToDocument(LexEntry lexEntry, LemmaVersion version, Document document) {
        document.add(new StringField(LexEntry.ID, lexEntry.getId(), Field.Store.YES));
        document.add(new StringField(LemmaVersion.LEXENTRY_ID, lexEntry.getId(), Field.Store.YES));
        document.add(new StringField(LemmaVersion.ID, version.getInternalId() + "", Field.Store.YES));
        document.add(new StringField(LemmaVersion.VERIFICATION, version.getVerification().toString(), Field.Store.YES));
        document.add(new TextField(LemmaVersion.VERIFICATION + "_analyzed", version.getVerification().toString().toLowerCase(), Field.Store.NO));
    }

    /**
     * Helper method to add default PG fields from a lucene {@link Document} to a {@link LemmaVersion}.
     */
    protected static void addPgFieldsToLemmaVersion(Document document, LemmaVersion lemmaVersion) {
        lemmaVersion.putPgValue(LexEntry.ID, document.get(LexEntry.ID));
        lemmaVersion.putPgValue(LemmaVersion.LEXENTRY_ID, document.get(LemmaVersion.LEXENTRY_ID));
        lemmaVersion.putPgValue(LemmaVersion.ID, document.get(LemmaVersion.ID));
        lemmaVersion.putPgValue(LemmaVersion.VERIFICATION, document.get(LemmaVersion.VERIFICATION));
    }
}
