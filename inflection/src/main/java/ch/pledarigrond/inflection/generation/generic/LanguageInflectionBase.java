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
        int numberVowels = 0;
        int lastVowelPosition = -1;

        for (int i = 0; i < word.length() ; i++) {
            if(isVocal(word.charAt(i))) {
                numberVowels++;
                if (numberVowels > 2) {
                    return false;
                }
                if (numberVowels > 1 && i - lastVowelPosition > 1) {
                    return false;
                }
                lastVowelPosition = i;
            }
        }
        return true;
    }
}
