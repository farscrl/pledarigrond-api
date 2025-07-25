package ch.pledarigrond.inflection.verb.rumantschgrischun;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.noun.rumantschgrischun.RumantschGrischunNounGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RumantschGrischunNounGuessingTest {

    private static RumantschGrischunNounGenerator generator;
    private InflectionDto forms;

    @BeforeAll
    public static void initialize() {
        generator = new RumantschGrischunNounGenerator();
    }

    @Test
    public void testRule3() {
        forms = generator.guessInflection("scolast", null, null);
        assertEquals("3", forms.getNoun().getInflectionSubtype());
        assertEquals("scolast", forms.getNoun().getMSingular());
        assertEquals("scolasta", forms.getNoun().getFSingular());
        assertEquals("scolasts", forms.getNoun().getMPlural());
        assertEquals("scolastas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule4() {
        forms = generator.guessInflection("manader",null, null);
        assertEquals("4", forms.getNoun().getInflectionSubtype());
        assertEquals("manader", forms.getNoun().getMSingular());
        assertEquals("manadra", forms.getNoun().getFSingular());
        assertEquals("manaders", forms.getNoun().getMPlural());
        assertEquals("manadras", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule5() {
        forms = generator.guessInflection("responsabel", null, null);
        assertEquals("5", forms.getNoun().getInflectionSubtype());
        assertEquals("responsabel", forms.getNoun().getMSingular());
        assertEquals("responsabla", forms.getNoun().getFSingular());
        assertEquals("responsabels", forms.getNoun().getMPlural());
        assertEquals("responsablas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule6() {
        forms = generator.guessInflection("apotecher", null, null);
        assertEquals("6", forms.getNoun().getInflectionSubtype());
        assertEquals("apotecher", forms.getNoun().getMSingular());
        assertEquals("apotecra", forms.getNoun().getFSingular());
        assertEquals("apotechers", forms.getNoun().getMPlural());
        assertEquals("apotecras", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule7() {
        forms = generator.guessInflection("deputà", null, null);
        assertEquals("7", forms.getNoun().getInflectionSubtype());
        assertEquals("deputà", forms.getNoun().getMSingular());
        assertEquals("deputada", forms.getNoun().getFSingular());
        assertEquals("deputads", forms.getNoun().getMPlural());
        assertEquals("deputadas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule8() {
        forms = generator.guessInflection("nar", null, null);
        assertEquals("8", forms.getNoun().getInflectionSubtype());
        assertEquals("nar", forms.getNoun().getMSingular());
        assertEquals("narra", forms.getNoun().getFSingular());
        assertEquals("nars", forms.getNoun().getMPlural());
        assertEquals("narras", forms.getNoun().getFPlural());
    }

    @Test
    public void testBaseFormWithWhitespace() {
        forms = generator.guessInflection("isch chasa", null, null);
        assertEquals(null, forms);
    }

    @Test
    public void testNormalizeBaseForm() {
        forms = generator.guessInflection("  nar         ", null, null);
        assertEquals("8", forms.getNoun().getInflectionSubtype());
        assertEquals("nar", forms.getNoun().getMSingular());
    }

    @Test
    public void testWithEndingIndicationA() {
        forms = generator.guessInflection("client, -a", null, null);
        assertEquals("3", forms.getNoun().getInflectionSubtype());
    }

    @Test
    public void testWithEndingIndicationDra() {
        forms = generator.guessInflection("cumprader, -dra", null, null);
        assertEquals("4", forms.getNoun().getInflectionSubtype());
    }

    @Test
    public void testWithEndingIndicationBla() {
        forms = generator.guessInflection("responsabel, -bla", null, null);
        assertEquals("5", forms.getNoun().getInflectionSubtype());
    }

    @Test
    public void testWithEndingIndicationCra() {
        forms = generator.guessInflection("scepticher, -cra", null, null);
        assertEquals("6", forms.getNoun().getInflectionSubtype());
    }

    @Test
    public void testWithEndingIndicationVla() {
        forms = generator.guessInflection("delegà, -ada", null, null);
        assertEquals("7", forms.getNoun().getInflectionSubtype());
    }
}
