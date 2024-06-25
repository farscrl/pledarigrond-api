package ch.pledarigrond.lucene.util;

import ch.pledarigrond.common.data.lucene.IndexedColumn;
import ch.pledarigrond.common.util.PronunciationNormalizer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexableField;

import java.util.ArrayList;
import java.util.List;

public class IndexedColumnHelper {
    public static List<IndexableField> getFields(IndexedColumn field, String value) {
        value = normalizeLadinPronunciationValues(field.getIndexFieldName(), value);
        if(value == null) return null;

        Analyzer analyzer = (field.isWhitespaceAnalyzer()) ? LuceneHelper.newWhitespaceAnalyzer() : LuceneHelper.newAnalyzer();
        Field.Store stored = field.isStored() ? Field.Store.YES : Field.Store.NO;

        if(field.isLowerCase()) {
            value = value.toLowerCase();
        }
        if(field.isAnalyzed()) {
            value = TokenizerHelper.tokenizeString(analyzer, value);
        }
        return switch (field.getType()) {
            case STRING -> List.of(new StringField(field.getIndexFieldName(), value, stored));
            case TEXT -> List.of(new TextField(field.getIndexFieldName(), value, stored));
            case INTEGER -> List.of(new IntField(field.getIndexFieldName(), Integer.parseInt(value), stored));
            case SEMICOLON_SEPERATED -> semicolonFields(field.getIndexFieldName(), value, stored);
        };
    }

    private static List<IndexableField> semicolonFields(String name, String value, Field.Store stored) {
        List<IndexableField> fields = new ArrayList<>();
        String[] split = value.split(";");
        for (String part : split) {
            if(!part.trim().isEmpty()) {
                fields.add(new StringField(name, part.trim(), stored));
            }
        }
        return fields;
    }

    /**
     * RStichwort in ladin (puter & vallader) contains pronunciation marks. As search should not depend
     * on those, we normalize the values for the search fields.
     * This function normalizes the values in fields with a name starting with "RStichwort_".
     *
     * @param value The field value
     * @return The normalized file value
     */
    private static String normalizeLadinPronunciationValues(String fieldName, String value) {
        if (fieldName.startsWith("RStichwort_")) {
            return PronunciationNormalizer.normalizePronunciation(value);
        }
        return value;
    }
}
