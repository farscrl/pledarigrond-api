package ch.pledarigrond.inflection.verb.surmiran;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.noun.surmiran.SurmiranNounGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SurmiranNounGuessingTest {

    private static SurmiranNounGenerator generator;
    private InflectionDto forms;

    @BeforeAll
    public static void initialize() {
        generator = new SurmiranNounGenerator();
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
        forms = generator.guessInflection("flevel", null, null);
        assertEquals("7", forms.getNoun().getInflectionSubtype());
        assertEquals("flevel", forms.getNoun().getMSingular());
        assertEquals("flevla", forms.getNoun().getFSingular());
        assertEquals("flevels", forms.getNoun().getMPlural());
        assertEquals("flevlas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule8() {
        forms = generator.guessInflection("gioven", null, null);
        assertEquals("8", forms.getNoun().getInflectionSubtype());
        assertEquals("gioven", forms.getNoun().getMSingular());
        assertEquals("giovna", forms.getNoun().getFSingular());
        assertEquals("giovens", forms.getNoun().getMPlural());
        assertEquals("giovnas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule9() {
        forms = generator.guessInflection("ampluia", null, null);
        assertEquals("9", forms.getNoun().getInflectionSubtype());
        assertEquals("ampluia", forms.getNoun().getMSingular());
        assertEquals("amplueida", forms.getNoun().getFSingular());
        assertEquals("ampluias", forms.getNoun().getMPlural());
        assertEquals("amplueidas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule10() {
        forms = generator.guessInflection("anvido", null, null);
        assertEquals("10", forms.getNoun().getInflectionSubtype());
        assertEquals("anvido", forms.getNoun().getMSingular());
        assertEquals("anvidada", forms.getNoun().getFSingular());
        assertEquals("anvidos", forms.getNoun().getMPlural());
        assertEquals("anvidadas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule11() {
        forms = generator.guessInflection("relaschea", null, null);
        assertEquals("11", forms.getNoun().getInflectionSubtype());
        assertEquals("relaschea", forms.getNoun().getMSingular());
        assertEquals("relascheda", forms.getNoun().getFSingular());
        assertEquals("relascheas", forms.getNoun().getMPlural());
        assertEquals("relaschedas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule12() {
        forms = generator.guessInflection("minoren", null, null);
        assertEquals("12", forms.getNoun().getInflectionSubtype());
        assertEquals("minoren", forms.getNoun().getMSingular());
        assertEquals("minorenna", forms.getNoun().getFSingular());
        assertEquals("minorens", forms.getNoun().getMPlural());
        assertEquals("minorennas", forms.getNoun().getFPlural());
    }

    @Test
    public void testPluralRule4() {
        forms = generator.guessInflection("pale", "f", null);
        assertEquals("2", forms.getNoun().getInflectionSubtype());
        assertEquals("pale", forms.getNoun().getFSingular());
        assertEquals("paleis", forms.getNoun().getFPlural());
    }

    @Test
    public void testBaseFormWithWhitespace() {
        forms = generator.guessInflection("isch tga", null, null);
        assertEquals(null, forms);
    }

    @Test
    public void testNormalizeBaseForm() {
        forms = generator.guessInflection("  minoren         ", null, null);
        assertEquals("12", forms.getNoun().getInflectionSubtype());
        assertEquals("minoren", forms.getNoun().getMSingular());
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
        forms = generator.guessInflection("raschunevel, -vla", null, null);
        assertEquals("7", forms.getNoun().getInflectionSubtype());
    }

    @Test
    public void testWithEndingIndicationVna() {
        forms = generator.guessInflection("gioven, -vna", null, null);
        assertEquals("8", forms.getNoun().getInflectionSubtype());
    }

    @Test
    public void testWithEndingIndicationEida() {
        forms = generator.guessInflection("crucifitgia, -eida", null, null);
        assertEquals("9", forms.getNoun().getInflectionSubtype());
    }

    @Test
    public void testWithEndingIndicationAda() {
        forms = generator.guessInflection("scienzio, -ada", null, null);
        assertEquals("10", forms.getNoun().getInflectionSubtype());
    }

    @Test
    public void testWithEndingIndicationEda() {
        forms = generator.guessInflection("donnagea, -eda", null, null);
        assertEquals("11", forms.getNoun().getInflectionSubtype());
    }
}
