package ch.pledarigrond.common.util;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PronounciationNormalizerTest {
    @Test
    public void testRule1() {
        assertEquals("chantar", PronunciationNormalizer.normalizePronunciation("chantạr"));
        assertEquals("avers", PronunciationNormalizer.normalizePronunciation("avẹrs"));
        assertEquals("avemaria", PronunciationNormalizer.normalizePronunciation("avemarịa"));
        assertEquals("aviöl", PronunciationNormalizer.normalizePronunciation("aviọ̈l"));
        assertEquals("üna", PronunciationNormalizer.normalizePronunciation("ụ̈na"));
        assertEquals("sfrignöz", PronunciationNormalizer.normalizePronunciation("sfrignọ̈z"));
        assertEquals("avisar", PronunciationNormalizer.normalizePronunciation("aviṣạr"));
    }
}
