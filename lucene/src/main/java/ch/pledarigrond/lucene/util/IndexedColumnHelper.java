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
        // we store the lower case value for sorting, otherwise Uppercase words will be sorted before lowercase words
        fields.add(new SortedDocValuesField(name, new BytesRef(value.toLowerCase())));
        fields.add(new TextField(name, value, stored));

        if (name.equals("DStichwort")) {
            String valueDict = normalizeDictionaryEntries(value);
            if (!valueDict.isEmpty()) {
                fields.add(new StringField("DStichwort_dict", value, stored));
            }
        }
        if (name.equals("RStichwort")) {
            String valueDict = normalizeDictionaryEntries(value);
            if (!valueDict.isEmpty()) {
                fields.add(new StringField("RStichwort_dict", value, stored));
            }
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

    private static String normalizeDictionaryEntries(String value) {
        value = value.replaceAll("ạ", "a");
        value = value.replaceAll("ạ̀", "a");
        value = value.replaceAll("Ạ", "A");
        value = value.replaceAll("ẹ", "e");
        value = value.replaceAll("ẹ̀", "e");
        value = value.replaceAll("ẹ́", "e");
        value = value.replaceAll("ệ", "e");
        value = value.replaceAll("Ẹ", "E");
        value = value.replaceAll("ị", "i");
        value = value.replaceAll("ị̈", "i");
        value = value.replaceAll("Ị", "I");
        value = value.replaceAll("ọ", "o");
        value = value.replaceAll("ọ̈", "ö");
        value = value.replaceAll("ộ", "o");
        value = value.replaceAll("ọ̀", "o");
        value = value.replaceAll("Ọ", "O");
        value = value.replaceAll("Ọ̈", "Ö");
        value = value.replaceAll("ụ", "u");
        value = value.replaceAll("ụ̈", "ü");
        value = value.replaceAll("Ụ", "U");
        value = value.replaceAll("Ụ̈", "Ü");
        value = value.replaceAll("ṣ", "s");
        value = value.replaceAll("Ṣ", "S");
        return value.replaceAll("[-.,/&%*\"+$!^`?«»_]", "");
    }
}
