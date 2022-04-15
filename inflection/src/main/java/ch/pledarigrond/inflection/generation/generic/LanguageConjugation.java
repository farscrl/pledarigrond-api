package ch.pledarigrond.inflection.generation.generic;

import ch.pledarigrond.inflection.model.InflectionResponse;
import lombok.Data;

import java.util.HashMap;

@Data
public abstract class LanguageConjugation {

    // the root of a verb
    protected String root;

    protected String ending;

    // a modified root
    protected String modRoot;

    public abstract InflectionResponse generateConjugation(String conjugationClass, String infinitiv);

    protected String removeWhitespaces(String query) {
        query = query.toLowerCase();
        query = query.replaceAll("^\\s+|\\s+$", "");
        return query;
    }

    protected boolean isVocal(char ch) {
        return switch (ch) {
            case 'a', 'e', 'i', 'o', 'u', 'ä', 'ö', 'ü' -> true;
            default -> false;
        };
    }

    public boolean endsWithDoubleConsonant(String root) {
        return root.charAt(root.length() - 1) == root.charAt(root.length() - 2);
    }

    public boolean startsWithVocal(String root) {
        return switch (root.substring(0, 1)) {
            case "a", "e", "i", "o", "u", "ä", "ö", "ü" -> true;
            default -> false;
        };
    }
}
