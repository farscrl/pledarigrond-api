package ch.pledarigrond.inflection.generation.generic;

import org.junit.Test;

import static org.junit.Assert.*;

public class SyllableTest {

    @Test
    public void countSyllables() {
        assertEquals(LanguageInflectionBase.countSyllables("utschi"), 2);
        assertEquals(LanguageInflectionBase.countSyllables("rosa"), 2);
        assertEquals(LanguageInflectionBase.countSyllables("leger"), 2);
        assertEquals(LanguageInflectionBase.countSyllables("calar"), 2);
        assertEquals(LanguageInflectionBase.countSyllables("combra"), 2);
        assertEquals(LanguageInflectionBase.countSyllables("plonta"), 2);
        assertEquals(LanguageInflectionBase.countSyllables("emprender"), 3);
    }

    @Test
    public void checkSingleSyllable() {
        assertTrue(LanguageInflectionBase.isSingleSyllable("plai"));
        assertTrue(LanguageInflectionBase.isSingleSyllable("fain"));
        assertFalse(LanguageInflectionBase.isSingleSyllable("tomata"));
    }
}
