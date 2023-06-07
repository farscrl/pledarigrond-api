package ch.pledarigrond.common.util;

import java.text.Normalizer;

public class PronunciationNormalizer {
    public static String normalizePronunciation(String value) {
        value = Normalizer.normalize(value, Normalizer.Form.NFKD);

        String pointBelowDiacriticalMark = "̣";
        value = value.replaceAll(pointBelowDiacriticalMark, "");
        return Normalizer.normalize(value, Normalizer.Form.NFC);
    }
}
