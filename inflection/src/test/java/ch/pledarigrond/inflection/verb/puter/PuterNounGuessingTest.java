package ch.pledarigrond.inflection.verb.puter;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.noun.puter.PuterNounGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PuterNounGuessingTest {

    private static PuterNounGenerator generator;
    private InflectionDto forms;

    @BeforeAll
    public static void initialize() {
        generator = new PuterNounGenerator();
    }

    @Test
    public void testRule3() {
        forms = generator.guessInflection("scolar", null, null);
        assertEquals("3", forms.getNoun().getInflectionSubtype());
        assertEquals("scolar", forms.getNoun().getMSingular());
        assertEquals("scolara", forms.getNoun().getFSingular());
    }

    @Test
    public void testRule4() {
        forms = generator.guessInflection("mneder",null, null);
        assertEquals("4", forms.getNoun().getInflectionSubtype());
        assertEquals("mneder", forms.getNoun().getMSingular());
        assertEquals("mnedra", forms.getNoun().getFSingular());
    }

    @Test
    public void testRule5() {
        forms = generator.guessInflection("respunsabel", null, null);
        assertEquals("5", forms.getNoun().getInflectionSubtype());
        assertEquals("respunsabel", forms.getNoun().getMSingular());
        assertEquals("respunsabla", forms.getNoun().getFSingular());
    }

    @Test
    public void testRule6() {
        forms = generator.guessInflection("crettaivel", null, null);
        assertEquals("6", forms.getNoun().getInflectionSubtype());
        assertEquals("crettaivel", forms.getNoun().getMSingular());
        assertEquals("crettaivla", forms.getNoun().getFSingular());
    }

    @Test
    public void testRule7() {
        forms = generator.guessInflection("apoteker", null, null);
        assertEquals("7", forms.getNoun().getInflectionSubtype());
        assertEquals("apoteker", forms.getNoun().getMSingular());
        assertEquals("apotecra", forms.getNoun().getFSingular());
    }

    @Test
    public void testRule8() {
        forms = generator.guessInflection("giuven", null, null);
        assertEquals("8", forms.getNoun().getInflectionSubtype());
        assertEquals("giuven", forms.getNoun().getMSingular());
        assertEquals("giuvna", forms.getNoun().getFSingular());
    }

    @Test
    public void testRule9() {
        forms = generator.guessInflection("pover", null, null);
        assertEquals("9", forms.getNoun().getInflectionSubtype());
        assertEquals("pover", forms.getNoun().getMSingular());
        assertEquals("povra", forms.getNoun().getFSingular());
    }

    @Test
    public void testRule10() {
        forms = generator.guessInflection("depüto", null, null);
        assertEquals("10", forms.getNoun().getInflectionSubtype());
        assertEquals("depüto", forms.getNoun().getMSingular());
        assertEquals("depüteda", forms.getNoun().getFSingular());
    }

    @Test
    public void testRule11() {
        forms = generator.guessInflection("impiego", null, null);
        assertEquals("11", forms.getNoun().getInflectionSubtype());
        assertEquals("impiego", forms.getNoun().getMSingular());
        assertEquals("impiegheda", forms.getNoun().getFSingular());
    }

    @Test
    public void testRule12() {
        forms = generator.guessInflection("piglio", null, null);
        assertEquals("12", forms.getNoun().getInflectionSubtype());
        assertEquals("piglio", forms.getNoun().getMSingular());
        assertEquals("piglieda", forms.getNoun().getFSingular());
    }

    @Test
    public void testRule13() {
        forms = generator.guessInflection("impedieu", null, null);
        assertEquals("13", forms.getNoun().getInflectionSubtype());
        assertEquals("impedieu", forms.getNoun().getMSingular());
        assertEquals("impedida", forms.getNoun().getFSingular());
    }

    @Test
    public void testRule14() {
        forms = generator.guessInflection("assaglieu", null, null);
        assertEquals("14", forms.getNoun().getInflectionSubtype());
        assertEquals("assaglieu", forms.getNoun().getMSingular());
        assertEquals("assaglida", forms.getNoun().getFSingular());
    }

    @Test
    public void testBaseFormWithWhitespace() {
        forms = generator.guessInflection("porta da chesa", null, null);
        assertEquals(null, forms);
    }

    @Test
    public void testNormalizeBaseForm() {
        forms = generator.guessInflection("  scolar         ", null, null);
        assertEquals("3", forms.getNoun().getInflectionSubtype());
        assertEquals("scolar", forms.getNoun().getMSingular());
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
    public void testWithEndingIndicationVla() {
        forms = generator.guessInflection("crettaivel, -vla", null, null);
        assertEquals("6", forms.getNoun().getInflectionSubtype());
    }

    @Test
    public void testWithEndingIndicationCra() {
        forms = generator.guessInflection("apoteker, -cra", null, null);
        assertEquals("7", forms.getNoun().getInflectionSubtype());
    }

    @Test
    public void testWithEndingIndicationVna() {
        forms = generator.guessInflection("gioven, -vna", null, null);
        assertEquals("8", forms.getNoun().getInflectionSubtype());
    }

    @Test
    public void testWithEndingIndicationVra() {
        forms = generator.guessInflection("pover, -vra", null, null);
        assertEquals("9", forms.getNoun().getInflectionSubtype());
    }

    @Test
    public void testWithEndingIndicationEda() {
        forms = generator.guessInflection("depüto, -eda", null, null);
        assertEquals("10", forms.getNoun().getInflectionSubtype());
    }

    @Test
    public void testWithEndingIndicationGheda() {
        forms = generator.guessInflection("impiego, -gheda", null, null);
        assertEquals("11", forms.getNoun().getInflectionSubtype());
    }

    @Test
    public void testWithEndingIndicationGlieada() {
        forms = generator.guessInflection("piglio, -glieda", null, null);
        assertEquals("12", forms.getNoun().getInflectionSubtype());
    }

    @Test
    public void testWithEndingIndicationIda() {
        forms = generator.guessInflection("impedieu, -ida", null, null);
        assertEquals("13", forms.getNoun().getInflectionSubtype());
    }

    @Test
    public void testWithEndingIndicationGlida() {
        forms = generator.guessInflection("assaglieu, -glida", null, null);
        assertEquals("14", forms.getNoun().getInflectionSubtype());
    }
}
