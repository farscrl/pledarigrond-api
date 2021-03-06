package ch.pledarigrond.inflection.generation.generic;

public abstract class LanguageInflectionBase {

    protected boolean isVocal(char ch) {
        return switch (ch) {
            case 'a', 'e', 'i', 'o', 'u', 'ä', 'ö', 'ü' -> true;
            default -> false;
        };
    }

    protected String normalizeString(String query) {
        query = query.replaceAll("^\\s+|\\s+$", "");
        return query;
    }
}
