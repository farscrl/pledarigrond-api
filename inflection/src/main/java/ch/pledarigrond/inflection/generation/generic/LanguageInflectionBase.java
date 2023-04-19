package ch.pledarigrond.inflection.generation.generic;

public abstract class LanguageInflectionBase {

    protected static boolean isVocal(char ch) {
        return switch (ch) {
            case 'a', 'e', 'i', 'o', 'u', 'ä', 'ö', 'ü' -> true;
            default -> false;
        };
    }

    protected String normalizeString(String query) {
        query = query.replaceAll("^\\s+|\\s+$", "");
        return query;
    }

    public static boolean isSingleSyllable(String word) {
        return countSyllables(word) == 1;
    }

    public static int countSyllables(String word) {
        // rules implemented according to "Grammatica Sursilvana", 1989, Arnold Spescha
        int count = 0;
        word = word.toLowerCase();

        boolean isConsonant = true;

        for (int i = 0; i < word.length(); i++) {
            boolean isNextConsonant = !isVocal(word.charAt(i));
            if (isConsonant && !isNextConsonant) {
                count++;
            }

            isConsonant = isNextConsonant;
        }
        return count;
    }
}
