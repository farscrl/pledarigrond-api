package ch.pledarigrond.lucene.util;

import ch.pledarigrond.common.data.lucene.IndexedColumn;
import ch.pledarigrond.common.util.PronunciationNormalizer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.util.BytesRef;

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
            case STRING_SORTED -> stringSorted(field.getIndexFieldName(), value, stored);
            case INTEGER_SORTED -> integerSorted(field.getIndexFieldName(), value, stored);
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

    private static List<IndexableField> stringSorted(String name, String value, Field.Store stored) {
        List<IndexableField> fields = new ArrayList<>();
        fields.add(new SortedDocValuesField(name, new BytesRef(value)));
        if (stored == Field.Store.YES) {
            fields.add(new StoredField(name, value));
        }
        return fields;
    }

    private static List<IndexableField> integerSorted(String name, String value, Field.Store stored) {
        // fields copied from old filemaker solutions contain strings and not integers.
        // all fields are set to 0 (only in the lucene index) if the value is not an integer.
        int val = 0;
        try {
            val = Integer.parseInt(value);
        } catch(NumberFormatException e){
            //ignore
        }

        List<IndexableField> fields = new ArrayList<>();
        fields.add(new NumericDocValuesField(name, val));
        if (stored == Field.Store.YES) {
            fields.add(new StoredField(name, val));
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