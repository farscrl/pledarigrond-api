package ch.pledarigrond.common.util;

import ch.pledarigrond.common.data.common.Language;

public class WordNormalizer {

    public static String normalizeWord(Language language, String word) {
        if (word == null) {
            return null;
        }

        word = word.trim();

        return switch (language) {
            case PUTER -> WordNormalizer.normalizeStringPuter(word);
            case RUMANTSCHGRISCHUN -> WordNormalizer.normalizeStringRumantschGrischun(word);
            case SURSILVAN -> WordNormalizer.normalizeStringSursilvan(word);
            case SUTSILVAN -> WordNormalizer.normalizeStringSutsilvan(word);
            case SURMIRAN -> WordNormalizer.normalizeStringSurmiran(word);
            case VALLADER -> WordNormalizer.normalizeStringVallader(word);
            default -> word;
        };
    }
    public static String normalizeStringPuter(String input) {
        if (input == null) {
            return null;
        }

        // Ignore cf.
        if (input.startsWith("cf. ")) {
            return null;
        }

        if (input.endsWith(" da")) {
            input = input.substring(0, input.length() - 3);
        }
        if (input.endsWith(" a")) {
            input = input.substring(0, input.length() - 2);
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
        if (input.startsWith("fer ")) {
            input = input.substring(4);
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
        return input;
    }

    public static String normalizeStringRumantschGrischun(String input) {
        if (input == null) {
            return null;
        }

        // Ignore cf.
        if (input.startsWith("cf. ")) {
            return null;
        }
        if (input.startsWith("far ")) {
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

        return input;
    }

    public static String normalizeStringSursilvan(String input) {
        // TODO: implement me
        return input;
    }

    public static String normalizeStringSurmiran(String input) {
        if (input == null) {
            return null;
        }

        // Ignore cf.
        if (input.startsWith("cf. ")) {
            return null;
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
        if (input.startsWith("far ")) {
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
        if (input.startsWith("far ")) {
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
        if (input == null) {
            return null;
        }

        // Ignore cf.
        if (input.startsWith("cf. ")) {
            return null;
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
        if (input.startsWith("far ")) {
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
