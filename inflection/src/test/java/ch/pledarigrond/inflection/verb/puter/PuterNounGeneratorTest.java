package ch.pledarigrond.inflection.verb.puter;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.noun.puter.PuterNounGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PuterNounGeneratorTest {

    private static PuterNounGenerator generator;
    private InflectionDto forms;

    @BeforeAll
    public static void initialize() {
        generator = new PuterNounGenerator();
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
        forms = generator.generateForms("2", "palüd");
        assertEquals(null, forms.getNoun().getMSingular());
        assertEquals("palüd", forms.getNoun().getFSingular());
        assertEquals(null, forms.getNoun().getMPlural());
        assertEquals("palüds", forms.getNoun().getFPlural());
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
        forms = generator.generateForms("4", "mneder");
        assertEquals("mneder", forms.getNoun().getMSingular());
        assertEquals("mnedra", forms.getNoun().getFSingular());
        assertEquals("mneders", forms.getNoun().getMPlural());
        assertEquals("mnedras", forms.getNoun().getFPlural());
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
        forms = generator.generateForms("10", "depüto");
        assertEquals("depüto", forms.getNoun().getMSingular());
        assertEquals("depüteda", forms.getNoun().getFSingular());
        assertEquals("depütos", forms.getNoun().getMPlural());
        assertEquals("depütedas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule11() {
        forms = generator.generateForms("11", "impiego");
        assertEquals("impiego", forms.getNoun().getMSingular());
        assertEquals("impiegheda", forms.getNoun().getFSingular());
        assertEquals("impiegos", forms.getNoun().getMPlural());
        assertEquals("impieghedas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule12() {
        forms = generator.generateForms("12", "piglio");
        assertEquals("piglio", forms.getNoun().getMSingular());
        assertEquals("piglieda", forms.getNoun().getFSingular());
        assertEquals("piglios", forms.getNoun().getMPlural());
        assertEquals("pigliedas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule13() {
        forms = generator.generateForms("13", "impedieu");
        assertEquals("impedieu", forms.getNoun().getMSingular());
        assertEquals("impedida", forms.getNoun().getFSingular());
        assertEquals("impedieus", forms.getNoun().getMPlural());
        assertEquals("impedidas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule14() {
        forms = generator.generateForms("14", "assaglieu");
        assertEquals("assaglieu", forms.getNoun().getMSingular());
        assertEquals("assaglida", forms.getNoun().getFSingular());
        assertEquals("assaglieus", forms.getNoun().getMPlural());
        assertEquals("assaglidas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule15() {
        forms = generator.generateForms("15", "besch");
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

        forms = generator.generateForms("1", "nes");
        assertEquals("nes", forms.getNoun().getMSingular());
        assertEquals("nes", forms.getNoun().getMPlural());
    }

    @Test
    public void testPluralRule3() {
        forms = generator.generateForms("1", "curtè");
        assertEquals("curtè", forms.getNoun().getMSingular());
        assertEquals("curtels", forms.getNoun().getMPlural());
    }

    @Test
    public void testPluralRule4() {
        forms = generator.generateForms("2", "chalur");
        assertEquals("chalur", forms.getNoun().getFSingular());
        assertEquals("chaluors", forms.getNoun().getFPlural());
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
