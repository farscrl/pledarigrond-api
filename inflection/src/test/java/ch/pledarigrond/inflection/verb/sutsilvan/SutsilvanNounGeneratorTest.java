package ch.pledarigrond.inflection.verb.sutsilvan;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.noun.sutsilvan.SutsilvanNounGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SutsilvanNounGeneratorTest {

    private static SutsilvanNounGenerator generator;
    private InflectionDto forms;

    @BeforeAll
    public static void initialize() {
        generator = new SutsilvanNounGenerator();
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("3", "scular");
        assertEquals("scular", forms.getNoun().getMSingular());
        assertEquals("sculara", forms.getNoun().getFSingular());
        assertEquals("sculars", forms.getNoun().getMPlural());
        assertEquals("scularas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule4() {
        forms = generator.generateForms("4", "antschavider");
        assertEquals("antschavider", forms.getNoun().getMSingular());
        assertEquals("antschavidra", forms.getNoun().getFSingular());
        assertEquals("antschaviders", forms.getNoun().getMPlural());
        assertEquals("antschavidras", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule5() {
        forms = generator.generateForms("5", "raspunsabel");
        assertEquals("raspunsabel", forms.getNoun().getMSingular());
        assertEquals("raspunsabla", forms.getNoun().getFSingular());
        assertEquals("raspunsabels", forms.getNoun().getMPlural());
        assertEquals("raspunsablas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule6() {
        forms = generator.generateForms("6", "apotecer");
        assertEquals("apotecer", forms.getNoun().getMSingular());
        assertEquals("apotecra", forms.getNoun().getFSingular());
        assertEquals("apotecers", forms.getNoun().getMPlural());
        assertEquals("apotecras", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule7() {
        forms = generator.generateForms("7", "raspunsavel");
        assertEquals("raspunsavel", forms.getNoun().getMSingular());
        assertEquals("raspunsavla", forms.getNoun().getFSingular());
        assertEquals("raspunsavels", forms.getNoun().getMPlural());
        assertEquals("raspunsavlas", forms.getNoun().getFPlural());
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
        forms = generator.generateForms("10", "impiego");
        assertEquals("impiego", forms.getNoun().getMSingular());
        assertEquals("impiegada", forms.getNoun().getFSingular());
        assertEquals("impiegos", forms.getNoun().getMPlural());
        assertEquals("impiegadas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule11() {
        forms = generator.generateForms("11", "impedieu");
        assertEquals("impedieu", forms.getNoun().getMSingular());
        assertEquals("impedida", forms.getNoun().getFSingular());
        assertEquals("impedieus", forms.getNoun().getMPlural());
        assertEquals("impedidas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule12() {
        forms = generator.generateForms("12", "sgiaglieu");
        assertEquals("sgiaglieu", forms.getNoun().getMSingular());
        assertEquals("sgiaglieada", forms.getNoun().getFSingular());
        assertEquals("sgiaglieus", forms.getNoun().getMPlural());
        assertEquals("sgiaglieadas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule13() {
        forms = generator.generateForms("13", "partutgieu");
        assertEquals("partutgieu", forms.getNoun().getMSingular());
        assertEquals("partutgeada", forms.getNoun().getFSingular());
        assertEquals("partutgieus", forms.getNoun().getMPlural());
        assertEquals("partutgeadas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule14() {
        forms = generator.generateForms("14", "exiliieu");
        assertEquals("exiliieu", forms.getNoun().getMSingular());
        assertEquals("exilieada", forms.getNoun().getFSingular());
        assertEquals("exiliieus", forms.getNoun().getMPlural());
        assertEquals("exilieadas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule15() {
        forms = generator.generateForms("15", "pér");
        assertEquals("pér", forms.getNoun().getMSingular());
        assertEquals(null, forms.getNoun().getFSingular());
        assertEquals("pérs", forms.getNoun().getMPlural());
        assertEquals(null, forms.getNoun().getFPlural());
        assertEquals("péra", forms.getNoun().getPluralCollectiv());
    }

    @Test
    public void testPluralRule2() {
        forms = generator.generateForms("1", "pass");
        assertEquals("pass", forms.getNoun().getMSingular());
        assertEquals("pass", forms.getNoun().getMPlural());
    }

    @Test
    public void testPluralRule3() {
        forms = generator.generateForms("1", "esch-tgea");
        assertEquals("esch-tgea", forms.getNoun().getMSingular());
        assertEquals("eschs-tgea", forms.getNoun().getMPlural());
    }

    @Test
    public void testPluralRule4() {
        forms = generator.generateForms("1", "cunti");
        assertEquals("cunti", forms.getNoun().getMSingular());
        assertEquals("cunteals", forms.getNoun().getMPlural());
    }
}
