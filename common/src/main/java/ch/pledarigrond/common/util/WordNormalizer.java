package ch.pledarigrond.common.util;

import ch.pledarigrond.common.data.common.Language;

/**
 * This class provides methods to normalize words in different idioms. This means, frequent prefixes and suffixes are
 * removed from the words.
 * Depending on the usage of the method, the normalization can be more or less strict: e.g. for pure spell checking,
 * the meaning of the word is irrelevant, so the normalization also change the meaning. For other use cases, the meaning
 * should not be changed, so the normalization is stricter.
 */
public class WordNormalizer {

    public static String normalizeWord(Language language, String word) {
        return normalizeWord(language, word, true);
    }

    public static String normalizeWord(Language language, String word, boolean allowChangeOfMeaning) {
        if (word == null) {
            return null;
        }

        word = word.trim();

        return switch (language) {
            case PUTER -> WordNormalizer.normalizeStringPuter(word, allowChangeOfMeaning);
            case RUMANTSCHGRISCHUN -> WordNormalizer.normalizeStringRumantschGrischun(word, allowChangeOfMeaning);
            case SURSILVAN -> WordNormalizer.normalizeStringSursilvan(word, allowChangeOfMeaning);
            case SUTSILVAN -> WordNormalizer.normalizeStringSutsilvan(word, allowChangeOfMeaning);
            case SURMIRAN -> WordNormalizer.normalizeStringSurmiran(word, allowChangeOfMeaning);
            case VALLADER -> WordNormalizer.normalizeStringVallader(word, allowChangeOfMeaning);
            default -> word;
        };
    }

    public static String normalizeStringPuter(String input) {
        return normalizeStringPuter(input, true);
    }

    public static String normalizeStringPuter(String input, boolean allowChangeOfMeaning) {
        if (input == null) {
            return null;
        }

        // Ignore cf.
        if (input.startsWith("cf. ")) {
            return null;
        }

        if (input.endsWith(" ...")) {
            input = input.substring(0, input.length() - 4);
        }
        if (input.startsWith("... ")) {
            input = input.substring(4);
        }
        if (input.endsWith(" a qchs / qchn")) {
            input = input.substring(0, input.length() - 14);
        }
        if (input.endsWith(" da qchs / qchn")) {
            input = input.substring(0, input.length() - 15);
        }
        if (input.endsWith(" sün qchs / qchn")) {
            input = input.substring(0, input.length() - 16);
        }
        if (input.endsWith(" per qchs / qchn")) {
            input = input.substring(0, input.length() - 16);
        }
        if (input.endsWith(" qchs / qchn")) {
            input = input.substring(0, input.length() - 12);
        }
        if (input.endsWith(" qchs/a qchn")) {
            input = input.substring(0, input.length() - 12);
        }
        if (input.endsWith("da qchn")) {
            input = input.substring(0, input.length() - 7);
        }
        if (input.endsWith("qchs da qchn")) {
            input = input.substring(0, input.length() - 12);
        }
        if (input.endsWith(" cun qchn")) {
            input = input.substring(0, input.length() - 9);
        }
        if (input.endsWith(" sün qchn")) {
            input = input.substring(0, input.length() - 9);
        }
        if (input.endsWith(" tar qchn")) {
            input = input.substring(0, input.length() - 9);
        }
        if (input.endsWith(" sü per qchn")) {
            input = input.substring(0, input.length() - 12);
        }
        if (input.endsWith(" our")) {
            input = input.substring(0, input.length() - 4);
        }
        if (input.endsWith(" qchn / qchs")) {
            input = input.substring(0, input.length() - 12);
        }
        if (input.endsWith(" qchs / a qchn")) {
            input = input.substring(0, input.length() - 14);
        }
        if (input.endsWith(" qchs")) {
            input = input.substring(0, input.length() - 5);
        }
        if (input.endsWith(" a qchn")) {
            input = input.substring(0, input.length() - 7);
        }
        if (input.endsWith("!")) {
            input = input.substring(0, input.length() - 1);
        }
        if (input.endsWith("?")) {
            input = input.substring(0, input.length() - 1);
        }
        if (input.endsWith(" da")) {
            input = input.substring(0, input.length() - 3);
        }
        if (input.endsWith(" a")) {
            input = input.substring(0, input.length() - 2);
        }
        if (input.endsWith(", -a")) {
            input = input.substring(0, input.length() - 4);
        }

        if (input.startsWith("fer ") && allowChangeOfMeaning) {
            input = input.substring(4);
        }
        if (input.startsWith("as ") && allowChangeOfMeaning) {
            input = input.substring(3);
        }
        if (input.startsWith("l'")) {
            input = input.substring(2);
        }
        if (input.startsWith("la ")) {
            input = input.substring(3);
        }
        if (input.startsWith("il ")) {
            input = input.substring(3);
        }
        if (input.startsWith("ün ")) {
            input = input.substring(3);
        }
        if (input.startsWith("ün'")) {
            input = input.substring(3);
        }
        if (input.startsWith("üna ")) {
            input = input.substring(4);
        }
        if (input.startsWith("a/que ")) {
            input = input.substring(6);
        }
        if (input.startsWith("as ") && allowChangeOfMeaning) {
            input = input.substring(3);
        }

        return input;
    }

    public static String normalizeStringRumantschGrischun(String input) {
        return normalizeStringRumantschGrischun(input, true);
    }

    public static String normalizeStringRumantschGrischun(String input, boolean allowChangeOfMeaning) {
        if (input == null) {
            return null;
        }

        // Ignore cf.
        if (input.startsWith("cf. ")) {
            return null;
        }
        if (input.startsWith("far ") && allowChangeOfMeaning) {
            input = input.substring(4);
        }
        if (input.startsWith("l'")) {
            input = input.substring(2);
        }
        if (input.startsWith("la ")) {
            input = input.substring(3);
        }
        if (input.startsWith("in ")) {
            input = input.substring(3);
        }
        if (input.startsWith("in'")) {
            input = input.substring(3);
        }
        if (input.startsWith("ina ")) {
            input = input.substring(4);
        }

        if (input.endsWith(" da")) {
            input = input.substring(0, input.length() - 3);
        }
        if (input.endsWith(" che")) {
            input = input.substring(0, input.length() - 4);
        }
        if (input.endsWith(" a")) {
            input = input.substring(0, input.length() - 2);
        }
        if (input.endsWith("!")) {
            input = input.substring(0, input.length() - 1);
        }
        if (input.endsWith("?")) {
            input = input.substring(0, input.length() - 1);
        }
        if (input.endsWith(" (a)")) {
            input = input.substring(0, input.length() - 4);
        }
        if (input.endsWith("(a)")) {
            input = input.substring(0, input.length() - 3);
        }
        if (input.endsWith(" (da)")) {
            input = input.substring(0, input.length() - 5);
        }
        if (input.endsWith("(da)")) {
            input = input.substring(0, input.length() - 4);
        }
        if (input.endsWith(" (E)")) {
            input = input.substring(0, input.length() - 4);
        }
        if (input.endsWith(" (cun)")) {
            input = input.substring(0, input.length() - 6);
        }

        return input;
    }

    public static String normalizeStringSursilvan(String input) {
        return normalizeStringSursilvan(input, true);
    }

    public static String normalizeStringSursilvan(String input, boolean allowChangeOfMeaning) {
        // TODO: implement me
        return input;
    }

    public static String normalizeStringSurmiran(String input) {
        return normalizeStringSurmiran(input, true);
    }

    public static String normalizeStringSurmiran(String input, boolean allowChangeOfMeaning) {
        if (input == null) {
            return null;
        }

        // Ignore cf.
        if (input.startsWith("cf. ")) {
            return null;
        }

        if (input.endsWith(", -a")) {
            input = input.substring(0, input.length() - 4);
        }
        if (input.endsWith(", -ada")) {
            input = input.substring(0, input.length() - 6);
        }
        if (input.endsWith(", -eida")) {
            input = input.substring(0, input.length() - 7);
        }
        if (input.endsWith(", -bla")) {
            input = input.substring(0, input.length() - 6);
        }
        if (input.endsWith(", -dra")) {
            input = input.substring(0, input.length() - 6);
        }
        if (input.endsWith(" or")) {
            input = input.substring(0, input.length() - 3);
        }
        if (input.endsWith(" da")) {
            input = input.substring(0, input.length() - 3);
        }
        if (input.endsWith(" a")) {
            input = input.substring(0, input.length() - 2);
        }
        if (input.endsWith("!")) {
            input = input.substring(0, input.length() - 1);
        }
        if (input.endsWith("?")) {
            input = input.substring(0, input.length() - 1);
        }
        if (input.startsWith("far ") && allowChangeOfMeaning) {
            input = input.substring(4);
        }
        if (input.startsWith("l'")) {
            input = input.substring(2);
        }
        if (input.startsWith("la ")) {
            input = input.substring(3);
        }
        if (input.startsWith("en ")) {
            input = input.substring(3);
        }
        if (input.startsWith("en'")) {
            input = input.substring(3);
        }
        if (input.startsWith("ena ")) {
            input = input.substring(4);
        }
        if (input.startsWith("egn ")) {
            input = input.substring(4);
        }
        if (input.startsWith("egna ")) {
            input = input.substring(5);
        }
        return input;
    }

    public static String normalizeStringSutsilvan(String input) {
        return normalizeStringSutsilvan(input, true);
    }

    public static String normalizeStringSutsilvan(String input, boolean allowChangeOfMeaning) {
        if (input == null) {
            return null;
        }

        // Ignore cf.
        if (input.startsWith("cf. ")) {
            return null;
        }

        if (input.endsWith(" ca")) {
            input = input.substring(0, input.length() - 3);
        }
        if (input.endsWith(" da")) {
            input = input.substring(0, input.length() - 3);
        }
        if (input.endsWith(" a")) {
            input = input.substring(0, input.length() - 2);
        }
        if (input.endsWith("!")) {
            input = input.substring(0, input.length() - 1);
        }
        if (input.endsWith("?")) {
            input = input.substring(0, input.length() - 1);
        }
        if (input.endsWith(" anzatgi")) {
            input = input.substring(0, input.length() - 8);
        }
        if (input.endsWith(" par anzatgi")) {
            input = input.substring(0, input.length() - 12);
        }
        if (input.endsWith(" ad anzatgi")) {
            input = input.substring(0, input.length() - 11);
        }
        if (input.startsWith("far ") && allowChangeOfMeaning) {
            input = input.substring(4);
        }
        if (input.startsWith("l'")) {
            input = input.substring(2);
        }
        if (input.startsWith("la ")) {
            input = input.substring(3);
        }
        if (input.startsWith("en ")) {
            input = input.substring(3);
        }
        if (input.startsWith("en'")) {
            input = input.substring(3);
        }
        if (input.startsWith("ena ")) {
            input = input.substring(4);
        }
        if (input.startsWith("egn ")) {
            input = input.substring(4);
        }
        if (input.startsWith("egna ")) {
            input = input.substring(5);
        }
        return input;
    }

    public static String normalizeStringVallader(String input) {
        return normalizeStringVallader(input, true);
    }

    public static String normalizeStringVallader(String input, boolean allowChangeOfMeaning) {
        if (input == null) {
            return null;
        }

        // Ignore cf.
        if (input.startsWith("cf. ")) {
            return null;
        }

        if (input.endsWith(", ...")) {
            input = input.substring(0, input.length() - 5);
        }
        if (input.endsWith(" ...")) {
            input = input.substring(0, input.length() - 4);
        }
        if (input.endsWith(" per qchs / qchn")) {
            input = input.substring(0, input.length() - 16);
        }
        if (input.endsWith(" a qchs / a qchn")) {
            input = input.substring(0, input.length() - 16);
        }
        if (input.endsWith(" qchs / a qchn")) {
            input = input.substring(0, input.length() - 14);
        }
        if (input.endsWith(" a qchn")) {
            input = input.substring(0, input.length() - 7);
        }
        if (input.endsWith(" da qchs / da qchn")) {
            input = input.substring(0, input.length() - 18);
        }
        if (input.endsWith(" qchs da qchn")) {
            input = input.substring(0, input.length() - 13);
        }
        if (input.endsWith(" da qchs")) {
            input = input.substring(0, input.length() - 8);
        }
        if (input.endsWith(" da qchs / da qchn")) {
            input = input.substring(0, input.length() - 18);
        }
        if (input.endsWith(" a qchs")) {
            input = input.substring(0, input.length() - 7);
        }
        if (input.endsWith(" cun qchn")) {
            input = input.substring(0, input.length() - 9);
        }
        if (input.endsWith(" qchs")) {
            input = input.substring(0, input.length() - 5);
        }
        if (input.endsWith(" da")) {
            input = input.substring(0, input.length() - 3);
        }
        if (input.endsWith(" a")) {
            input = input.substring(0, input.length() - 2);
        }
        if (input.endsWith("!")) {
            input = input.substring(0, input.length() - 1);
        }
        if (input.endsWith("?")) {
            input = input.substring(0, input.length() - 1);
        }
        if (input.startsWith("far ") && allowChangeOfMeaning) {
            input = input.substring(4);
        }
        if (input.startsWith("l'")) {
            input = input.substring(2);
        }
        if (input.startsWith("il ")) {
            input = input.substring(3);
        }
        if (input.startsWith("la ")) {
            input = input.substring(3);
        }
        if (input.startsWith("el/ella ")) {
            input = input.substring(8);
        }
        if (input.startsWith("ün ")) {
            input = input.substring(3);
        }
        if (input.startsWith("ün'")) {
            input = input.substring(3);
        }
        if (input.startsWith("üna ")) {
            input = input.substring(4);
        }
        if (input.startsWith("egn ")) {
            input = input.substring(4);
        }
        if (input.startsWith("egna ")) {
            input = input.substring(5);
        }
        return input;
    }
}
