package ch.pledarigrond.inflection.verb.surmiran;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.noun.surmiran.SurmiranNounGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SurmiranNounGeneratorTest {

    private static SurmiranNounGenerator generator;
    private InflectionDto forms;

    @BeforeAll
    public static void initialize() {
        generator = new SurmiranNounGenerator();
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
        forms = generator.generateForms("7", "flevel");
        assertEquals("flevel", forms.getNoun().getMSingular());
        assertEquals("flevla", forms.getNoun().getFSingular());
        assertEquals("flevels", forms.getNoun().getMPlural());
        assertEquals("flevlas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule8() {
        forms = generator.generateForms("8", "gioven");
        assertEquals("gioven", forms.getNoun().getMSingular());
        assertEquals("giovna", forms.getNoun().getFSingular());
        assertEquals("giovens", forms.getNoun().getMPlural());
        assertEquals("giovnas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule9() {
        forms = generator.generateForms("9", "ampluia");
        assertEquals("ampluia", forms.getNoun().getMSingular());
        assertEquals("amplueida", forms.getNoun().getFSingular());
        assertEquals("ampluias", forms.getNoun().getMPlural());
        assertEquals("amplueidas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule10() {
        forms = generator.generateForms("10", "anvido");
        assertEquals("anvido", forms.getNoun().getMSingular());
        assertEquals("anvidada", forms.getNoun().getFSingular());
        assertEquals("anvidos", forms.getNoun().getMPlural());
        assertEquals("anvidadas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule11() {
        forms = generator.generateForms("11", "relaschea");
        assertEquals("relaschea", forms.getNoun().getMSingular());
        assertEquals("relascheda", forms.getNoun().getFSingular());
        assertEquals("relascheas", forms.getNoun().getMPlural());
        assertEquals("relaschedas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule12() {
        forms = generator.generateForms("12", "minoren");
        assertEquals("minoren", forms.getNoun().getMSingular());
        assertEquals("minorenna", forms.getNoun().getFSingular());
        assertEquals("minorens", forms.getNoun().getMPlural());
        assertEquals("minorennas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule13() {
        forms = generator.generateForms("13", "peir");
        assertEquals("peir", forms.getNoun().getMSingular());
        assertEquals(null, forms.getNoun().getFSingular());
        assertEquals("peirs", forms.getNoun().getMPlural());
        assertEquals(null, forms.getNoun().getFPlural());
        assertEquals("peira", forms.getNoun().getPluralCollectiv());
    }

    @Test
    public void testPluralRule2() {
        forms = generator.generateForms("1", "cass");
        assertEquals("cass", forms.getNoun().getMSingular());
        assertEquals("cass", forms.getNoun().getMPlural());
    }

    @Test
    public void testPluralRule3() {
        forms = generator.generateForms("1", "isch-tgesa");
        assertEquals("isch-tgesa", forms.getNoun().getMSingular());
        assertEquals("ischs-tgesa", forms.getNoun().getMPlural());
    }

    @Test
    public void testPluralRule4() {
        forms = generator.generateForms("2", "pale");
        assertEquals("pale", forms.getNoun().getFSingular());
        assertEquals("paleis", forms.getNoun().getFPlural());
    }

    @Test
    public void testPluralRule5() {
        forms = generator.generateForms("2", "pare");
        assertEquals("pare", forms.getNoun().getFSingular());
        assertEquals("pareis", forms.getNoun().getFPlural());
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
