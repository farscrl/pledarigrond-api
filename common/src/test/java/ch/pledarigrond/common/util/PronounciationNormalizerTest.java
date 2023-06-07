package ch.pledarigrond.common.util;

import org.junit.Assert;
import org.junit.Test;

public class PronounciationNormalizerTest {
    @Test
    public void testRule1() {
        Assert.assertEquals("chantar", PronunciationNormalizer.normalizePronunciation("chantạr"));
        Assert.assertEquals("avers", PronunciationNormalizer.normalizePronunciation("avẹrs"));
        Assert.assertEquals("avemaria", PronunciationNormalizer.normalizePronunciation("avemarịa"));
        Assert.assertEquals("aviöl", PronunciationNormalizer.normalizePronunciation("aviọ̈l"));
        Assert.assertEquals("üna", PronunciationNormalizer.normalizePronunciation("ụ̈na"));
        Assert.assertEquals("sfrignöz", PronunciationNormalizer.normalizePronunciation("sfrignọ̈z"));
        Assert.assertEquals("avisar", PronunciationNormalizer.normalizePronunciation("aviṣạr"));
    }
}
