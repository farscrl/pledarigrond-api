package ch.pledarigrond.inflection.verb.vallader;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.noun.vallader.ValladerNounGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValladerNounGeneratorTest {

    private static ValladerNounGenerator generator;
    private InflectionDto forms;

    @BeforeAll
    public static void initialize() {
        generator = new ValladerNounGenerator();
    }

    @Test
    public void testRule1() {
        forms = generator.generateForms("1", "cas");
        assertEquals("cas", forms.getNoun().getMSingular());
        assertEquals(null, forms.getNoun().getFSingular());
        assertEquals("cas", forms.getNoun().getMPlural());
        assertEquals(null, forms.getNoun().getFPlural());
    }

    @Test
    public void testRule2() {
        forms = generator.generateForms("2", "palü");
        assertEquals(null, forms.getNoun().getMSingular());
        assertEquals("palü", forms.getNoun().getFSingular());
        assertEquals(null, forms.getNoun().getMPlural());
        assertEquals("palüts", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("3", "scolar");
        assertEquals("scolar", forms.getNoun().getMSingular());
        assertEquals("scolara", forms.getNoun().getFSingular());
        assertEquals("scolars", forms.getNoun().getMPlural());
        assertEquals("scolaras", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule4() {
        forms = generator.generateForms("4", "manader");
        assertEquals("manader", forms.getNoun().getMSingular());
        assertEquals("manadra", forms.getNoun().getFSingular());
        assertEquals("manaders", forms.getNoun().getMPlural());
        assertEquals("manadras", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule5() {
        forms = generator.generateForms("5", "respunsabel");
        assertEquals("respunsabel", forms.getNoun().getMSingular());
        assertEquals("respunsabla", forms.getNoun().getFSingular());
        assertEquals("respunsabels", forms.getNoun().getMPlural());
        assertEquals("respunsablas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule6() {
        forms = generator.generateForms("6", "crettaivel");
        assertEquals("crettaivel", forms.getNoun().getMSingular());
        assertEquals("crettaivla", forms.getNoun().getFSingular());
        assertEquals("crettaivels", forms.getNoun().getMPlural());
        assertEquals("crettaivlas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule7() {
        forms = generator.generateForms("7", "apoteker");
        assertEquals("apoteker", forms.getNoun().getMSingular());
        assertEquals("apotecra", forms.getNoun().getFSingular());
        assertEquals("apotekers", forms.getNoun().getMPlural());
        assertEquals("apotecras", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule8() {
        forms = generator.generateForms("8", "giuven");
        assertEquals("giuven", forms.getNoun().getMSingular());
        assertEquals("giuvna", forms.getNoun().getFSingular());
        assertEquals("giuvens", forms.getNoun().getMPlural());
        assertEquals("giuvnas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule9() {
        forms = generator.generateForms("9", "pover");
        assertEquals("pover", forms.getNoun().getMSingular());
        assertEquals("povra", forms.getNoun().getFSingular());
        assertEquals("povers", forms.getNoun().getMPlural());
        assertEquals("povras", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule10() {
        forms = generator.generateForms("10", "deputà");
        assertEquals("deputà", forms.getNoun().getMSingular());
        assertEquals("deputada", forms.getNoun().getFSingular());
        assertEquals("deputats", forms.getNoun().getMPlural());
        assertEquals("deputadas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule11() {
        forms = generator.generateForms("11", "vendü");
        assertEquals("vendü", forms.getNoun().getMSingular());
        assertEquals("vendüda", forms.getNoun().getFSingular());
        assertEquals("vendüts", forms.getNoun().getMPlural());
        assertEquals("vendüdas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule12() {
        forms = generator.generateForms("12", "impedi");
        assertEquals("impedi", forms.getNoun().getMSingular());
        assertEquals("impedida", forms.getNoun().getFSingular());
        assertEquals("impedits", forms.getNoun().getMPlural());
        assertEquals("impedidas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule13() {
        forms = generator.generateForms("13", "besch");
        assertEquals("besch", forms.getNoun().getMSingular());
        assertEquals(null, forms.getNoun().getFSingular());
        assertEquals("beschs", forms.getNoun().getMPlural());
        assertEquals(null, forms.getNoun().getFPlural());
        assertEquals("bescha", forms.getNoun().getPluralCollectiv());
    }

    @Test
    public void testPluralRule2() {
        forms = generator.generateForms("1", "pass");
        assertEquals("pass", forms.getNoun().getMSingular());
        assertEquals("pass", forms.getNoun().getMPlural());

        forms = generator.generateForms("1", "nas");
        assertEquals("nas", forms.getNoun().getMSingular());
        assertEquals("nas", forms.getNoun().getMPlural());
    }

    @Test
    public void testPluralRule3() {
        forms = generator.generateForms("2", "sudà");
        assertEquals("sudà", forms.getNoun().getFSingular());
        assertEquals("sudats", forms.getNoun().getFPlural());
    }

    @Test
    public void testPluralRule4() {
        forms = generator.generateForms("1", "curtè");
        assertEquals("curtè", forms.getNoun().getMSingular());
        assertEquals("curtels", forms.getNoun().getMPlural());
    }

    @Test
    public void testPluralRule5() {
        forms = generator.generateForms("2", "vesti");
        assertEquals("vesti", forms.getNoun().getFSingular());
        assertEquals("vestits", forms.getNoun().getFPlural());
    }

    @Test
    public void testPluralRule6() {
        forms = generator.generateForms("1", "creschü");
        assertEquals("creschü", forms.getNoun().getMSingular());
        assertEquals("creschüts", forms.getNoun().getMPlural());
    }

    @Test
    public void testUpperCase() {
        forms = generator.generateForms("3", "Argoves");
        assertEquals("Argoves", forms.getNoun().getMSingular());
        assertEquals("Argovesa", forms.getNoun().getFSingular());
        assertEquals("Argoves", forms.getNoun().getMPlural());
        assertEquals("Argovesas", forms.getNoun().getFPlural());
    }
}
