package ch.pledarigrond.inflection.verb.rumantschgrischun;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.noun.rumantschgrischun.RumantschGrischunNounGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RumantschGrischunNounGeneratorTest {

    private static RumantschGrischunNounGenerator generator;
    private InflectionDto forms;

    @BeforeAll
    public static void initialize() {
        generator = new RumantschGrischunNounGenerator();
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("3", "scolast");
        assertEquals("scolast", forms.getNoun().getMSingular());
        assertEquals("scolasta", forms.getNoun().getFSingular());
        assertEquals("scolasts", forms.getNoun().getMPlural());
        assertEquals("scolastas", forms.getNoun().getFPlural());
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
        forms = generator.generateForms("5", "responsabel");
        assertEquals("responsabel", forms.getNoun().getMSingular());
        assertEquals("responsabla", forms.getNoun().getFSingular());
        assertEquals("responsabels", forms.getNoun().getMPlural());
        assertEquals("responsablas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule6() {
        forms = generator.generateForms("6", "apotecher");
        assertEquals("apotecher", forms.getNoun().getMSingular());
        assertEquals("apotecra", forms.getNoun().getFSingular());
        assertEquals("apotechers", forms.getNoun().getMPlural());
        assertEquals("apotecras", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule7() {
        forms = generator.generateForms("7", "deputà");
        assertEquals("deputà", forms.getNoun().getMSingular());
        assertEquals("deputada", forms.getNoun().getFSingular());
        assertEquals("deputads", forms.getNoun().getMPlural());
        assertEquals("deputadas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule8() {
        forms = generator.generateForms("8", "nar");
        assertEquals("nar", forms.getNoun().getMSingular());
        assertEquals("narra", forms.getNoun().getFSingular());
        assertEquals("nars", forms.getNoun().getMPlural());
        assertEquals("narras", forms.getNoun().getFPlural());
    }

    @Test
    public void testPluralRule1() {
        forms = generator.generateForms("1", "cudisch");
        assertEquals("cudisch", forms.getNoun().getMSingular());
        assertEquals("cudischs", forms.getNoun().getMPlural());

        forms = generator.generateForms("2", "dunna");
        assertEquals("dunna", forms.getNoun().getFSingular());
        assertEquals("dunnas", forms.getNoun().getFPlural());

        forms = generator.generateForms("1", "canapé");
        assertEquals("canapé", forms.getNoun().getMSingular());
        assertEquals("canapés", forms.getNoun().getMPlural());
    }

    @Test
    public void testPluralRule2() {
        forms = generator.generateForms("1", "cas");
        assertEquals("cas", forms.getNoun().getMSingular());
        assertEquals("cas", forms.getNoun().getMPlural());

        forms = generator.generateForms("1", "nas");
        assertEquals("nas", forms.getNoun().getMSingular());
        assertEquals("nas", forms.getNoun().getMPlural());
    }

    @Test
    public void testPluralRule3() {
        forms = generator.generateForms("1", "isch-chasa");
        assertEquals("isch-chasa", forms.getNoun().getMSingular());
        assertEquals("ischs-chasa", forms.getNoun().getMPlural());
    }

    @Test
    public void testPluralRule4() {
        forms = generator.generateForms("7", "deputà");
        assertEquals("deputà", forms.getNoun().getMSingular());
        assertEquals("deputada", forms.getNoun().getFSingular());
        assertEquals("deputads", forms.getNoun().getMPlural());
        assertEquals("deputadas", forms.getNoun().getFPlural());
    }

    @Test
    public void testPluralRule5() {
        forms = generator.generateForms("1", "anè");
        assertEquals("anè", forms.getNoun().getMSingular());
        assertEquals("anels", forms.getNoun().getMPlural());
    }

    @Test
    public void testPluralRule6() {
        forms = generator.generateForms("1", "vestgì");
        assertEquals("vestgì", forms.getNoun().getMSingular());
        assertEquals("vestgids", forms.getNoun().getMPlural());
    }
}
