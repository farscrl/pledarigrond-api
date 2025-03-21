package ch.pledarigrond.inflection.verb.vallader;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.noun.puter.PuterNounGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValladerNounGuessingTest {

    private static PuterNounGenerator generator;
    private InflectionDto forms;

    @BeforeAll
    public static void initialize() {
        generator = new PuterNounGenerator();
    }

    @Test
    public void testRule3() {
        forms = generator.guessInflection("scolar", null, null);
        assertEquals(forms.getInflectionSubtype(), "3");
        assertEquals(forms.getNoun().getMSingular(), "scolar");
        assertEquals(forms.getNoun().getFSingular(), "scolara");
    }

    @Test
    public void testRule4() {
        forms = generator.guessInflection("mneder",null, null);
        assertEquals(forms.getInflectionSubtype(), "4");
        assertEquals(forms.getNoun().getMSingular(), "mneder");
        assertEquals(forms.getNoun().getFSingular(), "mnedra");
    }

    @Test
    public void testRule5() {
        forms = generator.guessInflection("respunsabel", null, null);
        assertEquals(forms.getInflectionSubtype(), "5");
        assertEquals(forms.getNoun().getMSingular(), "respunsabel");
        assertEquals(forms.getNoun().getFSingular(), "respunsabla");
    }

    @Test
    public void testRule6() {
        forms = generator.guessInflection("crettaivel", null, null);
        assertEquals(forms.getInflectionSubtype(), "6");
        assertEquals(forms.getNoun().getMSingular(), "crettaivel");
        assertEquals(forms.getNoun().getFSingular(), "crettaivla");
    }

    @Test
    public void testRule7() {
        forms = generator.guessInflection("apoteker", null, null);
        assertEquals(forms.getInflectionSubtype(), "7");
        assertEquals(forms.getNoun().getMSingular(), "apoteker");
        assertEquals(forms.getNoun().getFSingular(), "apotecra");
    }

    @Test
    public void testRule8() {
        forms = generator.guessInflection("giuven", null, null);
        assertEquals(forms.getInflectionSubtype(), "8");
        assertEquals(forms.getNoun().getMSingular(), "giuven");
        assertEquals(forms.getNoun().getFSingular(), "giuvna");
    }

    @Test
    public void testRule9() {
        forms = generator.guessInflection("pover", null, null);
        assertEquals(forms.getInflectionSubtype(), "9");
        assertEquals(forms.getNoun().getMSingular(), "pover");
        assertEquals(forms.getNoun().getFSingular(), "povra");
    }

    @Test
    public void testRule10() {
        forms = generator.guessInflection("depüto", null, null);
        assertEquals(forms.getInflectionSubtype(), "10");
        assertEquals(forms.getNoun().getMSingular(), "depüto");
        assertEquals(forms.getNoun().getFSingular(), "depüteda");
    }

    @Test
    public void testRule11() {
        forms = generator.guessInflection("impiego", null, null);
        assertEquals(forms.getInflectionSubtype(), "11");
        assertEquals(forms.getNoun().getMSingular(), "impiego");
        assertEquals(forms.getNoun().getFSingular(), "impiegheda");
    }

    @Test
    public void testRule12() {
        forms = generator.guessInflection("piglio", null, null);
        assertEquals(forms.getInflectionSubtype(), "12");
        assertEquals(forms.getNoun().getMSingular(), "piglio");
        assertEquals(forms.getNoun().getFSingular(), "piglieda");
    }

    @Test
    public void testRule13() {
        forms = generator.guessInflection("impedieu", null, null);
        assertEquals(forms.getInflectionSubtype(), "13");
        assertEquals(forms.getNoun().getMSingular(), "impedieu");
        assertEquals(forms.getNoun().getFSingular(), "impedida");
    }

    @Test
    public void testRule14() {
        forms = generator.guessInflection("assaglieu", null, null);
        assertEquals(forms.getInflectionSubtype(), "14");
        assertEquals(forms.getNoun().getMSingular(), "assaglieu");
        assertEquals(forms.getNoun().getFSingular(), "assaglida");
    }

    @Test
    public void testBaseFormWithWhitespace() {
        forms = generator.guessInflection("porta da chesa", null, null);
        assertEquals(forms, null);
    }

    @Test
    public void testNormalizeBaseForm() {
        forms = generator.guessInflection("  scolar         ", null, null);
        assertEquals(forms.getInflectionSubtype(), "3");
        assertEquals(forms.getNoun().getMSingular(), "scolar");
    }

    @Test
    public void testWithEndingIndicationA() {
        forms = generator.guessInflection("client, -a", null, null);
        assertEquals(forms.getInflectionSubtype(), "3");
    }

    @Test
    public void testWithEndingIndicationDra() {
        forms = generator.guessInflection("cumprader, -dra", null, null);
        assertEquals(forms.getInflectionSubtype(), "4");
    }

    @Test
    public void testWithEndingIndicationBla() {
        forms = generator.guessInflection("responsabel, -bla", null, null);
        assertEquals(forms.getInflectionSubtype(), "5");
    }

    @Test
    public void testWithEndingIndicationVla() {
        forms = generator.guessInflection("crettaivel, -vla", null, null);
        assertEquals(forms.getInflectionSubtype(), "6");
    }

    @Test
    public void testWithEndingIndicationCra() {
        forms = generator.guessInflection("apoteker, -cra", null, null);
        assertEquals(forms.getInflectionSubtype(), "7");
    }

    @Test
    public void testWithEndingIndicationVna() {
        forms = generator.guessInflection("gioven, -vna", null, null);
        assertEquals(forms.getInflectionSubtype(), "8");
    }

    @Test
    public void testWithEndingIndicationVra() {
        forms = generator.guessInflection("pover, -vra", null, null);
        assertEquals(forms.getInflectionSubtype(), "9");
    }

    @Test
    public void testWithEndingIndicationEda() {
        forms = generator.guessInflection("depüto, -eda", null, null);
        assertEquals(forms.getInflectionSubtype(), "10");
    }

    @Test
    public void testWithEndingIndicationGheda() {
        forms = generator.guessInflection("impiego, -gheda", null, null);
        assertEquals(forms.getInflectionSubtype(), "11");
    }

    @Test
    public void testWithEndingIndicationGlieada() {
        forms = generator.guessInflection("piglio, -glieda", null, null);
        assertEquals(forms.getInflectionSubtype(), "12");
    }

    @Test
    public void testWithEndingIndicationIda() {
        forms = generator.guessInflection("impedieu, -ida", null, null);
        assertEquals(forms.getInflectionSubtype(), "13");
    }

    @Test
    public void testWithEndingIndicationGlida() {
        forms = generator.guessInflection("assaglieu, -glida", null, null);
        assertEquals(forms.getInflectionSubtype(), "14");
    }
}
