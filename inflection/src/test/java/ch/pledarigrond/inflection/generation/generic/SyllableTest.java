package ch.pledarigrond.inflection.generation.generic;

import ch.pledarigrond.inflection.generation.surmiran.SurmiranConjugation;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SyllableTest {

    @Test
    public void demoTestMethod() {
        SurmiranConjugation sc = new SurmiranConjugation();
        assertTrue(sc.isSingleSyllable("plai"));
        assertTrue(sc.isSingleSyllable("fain"));
        assertFalse(sc.isSingleSyllable("tomata"));
    }
}
