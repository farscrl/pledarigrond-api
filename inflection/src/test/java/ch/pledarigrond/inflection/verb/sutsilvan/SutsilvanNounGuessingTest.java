package ch.pledarigrond.inflection.verb.sutsilvan;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.noun.sutsilvan.SutsilvanNounGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SutsilvanNounGuessingTest {

    private static SutsilvanNounGenerator generator;
    private InflectionDto forms;

    @BeforeAll
    public static void initialize() {
        generator = new SutsilvanNounGenerator();
    }

    @Test
    public void testRule3() {
        forms = generator.guessInflection("scular", null, null);
        assertEquals(forms.getInflectionSubtype(), "3");
        assertEquals(forms.getNoun().getMSingular(), "scular");
        assertEquals(forms.getNoun().getFSingular(), "sculara");
        assertEquals(forms.getNoun().getMPlural(), "sculars");
        assertEquals(forms.getNoun().getFPlural(), "scularas");
    }

    @Test
    public void testRule4() {
        forms = generator.guessInflection("antschavider",null, null);
        assertEquals(forms.getInflectionSubtype(), "4");
        assertEquals(forms.getNoun().getMSingular(), "antschavider");
        assertEquals(forms.getNoun().getFSingular(), "antschavidra");
        assertEquals(forms.getNoun().getMPlural(), "antschaviders");
        assertEquals(forms.getNoun().getFPlural(), "antschavidras");
    }

    @Test
    public void testRule5() {
        forms = generator.guessInflection("raspunsabel", null, null);
        assertEquals(forms.getInflectionSubtype(), "5");
        assertEquals(forms.getNoun().getMSingular(), "raspunsabel");
        assertEquals(forms.getNoun().getFSingular(), "raspunsabla");
        assertEquals(forms.getNoun().getMPlural(), "raspunsabels");
        assertEquals(forms.getNoun().getFPlural(), "raspunsablas");
    }

    @Test
    public void testRule6() {
        forms = generator.guessInflection("apotecer", null, null);
        assertEquals(forms.getInflectionSubtype(), "6");
        assertEquals(forms.getNoun().getMSingular(), "apotecer");
        assertEquals(forms.getNoun().getFSingular(), "apotecra");
        assertEquals(forms.getNoun().getMPlural(), "apotecers");
        assertEquals(forms.getNoun().getFPlural(), "apotecras");
    }

    @Test
    public void testRule7() {
        forms = generator.guessInflection("raspunsavel", null, null);
        assertEquals(forms.getInflectionSubtype(), "7");
        assertEquals(forms.getNoun().getMSingular(), "raspunsavel");
        assertEquals(forms.getNoun().getFSingular(), "raspunsavla");
        assertEquals(forms.getNoun().getMPlural(), "raspunsavels");
        assertEquals(forms.getNoun().getFPlural(), "raspunsavlas");
    }

    @Test
    public void testRule8() {
        forms = generator.guessInflection("giuven", null, null);
        assertEquals(forms.getInflectionSubtype(), "8");
        assertEquals(forms.getNoun().getMSingular(), "giuven");
        assertEquals(forms.getNoun().getFSingular(), "giuvna");
        assertEquals(forms.getNoun().getMPlural(), "giuvens");
        assertEquals(forms.getNoun().getFPlural(), "giuvnas");
    }

    @Test
    public void testRule9() {
        forms = generator.guessInflection("pover", null, null);
        assertEquals(forms.getInflectionSubtype(), "9");
        assertEquals(forms.getNoun().getMSingular(), "pover");
        assertEquals(forms.getNoun().getFSingular(), "povra");
        assertEquals(forms.getNoun().getMPlural(), "povers");
        assertEquals(forms.getNoun().getFPlural(), "povras");
    }

    @Test
    public void testRule10() {
        forms = generator.guessInflection("impiego", null, null);
        assertEquals(forms.getInflectionSubtype(), "10");
        assertEquals(forms.getNoun().getMSingular(), "impiego");
        assertEquals(forms.getNoun().getFSingular(), "impiegada");
        assertEquals(forms.getNoun().getMPlural(), "impiegos");
        assertEquals(forms.getNoun().getFPlural(), "impiegadas");
    }

    @Test
    public void testRule11() {
        forms = generator.guessInflection("impedieu", null, null);
        assertEquals(forms.getInflectionSubtype(), "11");
        assertEquals(forms.getNoun().getMSingular(), "impedieu");
        assertEquals(forms.getNoun().getFSingular(), "impedida");
        assertEquals(forms.getNoun().getMPlural(), "impedieus");
        assertEquals(forms.getNoun().getFPlural(), "impedidas");
    }

    @Test
    public void testRule12() {
        forms = generator.guessInflection("sgiaglieu", null, null);
        assertEquals(forms.getInflectionSubtype(), "12");
        assertEquals(forms.getNoun().getMSingular(), "sgiaglieu");
        assertEquals(forms.getNoun().getFSingular(), "sgiaglieada");
        assertEquals(forms.getNoun().getMPlural(), "sgiaglieus");
        assertEquals(forms.getNoun().getFPlural(), "sgiaglieadas");
    }


    @Test
    public void testRule13() {
        forms = generator.guessInflection("partutgieu", null, null);
        assertEquals(forms.getInflectionSubtype(), "13");
        assertEquals(forms.getNoun().getMSingular(), "partutgieu");
        assertEquals(forms.getNoun().getFSingular(), "partutgeada");
        assertEquals(forms.getNoun().getMPlural(), "partutgieus");
        assertEquals(forms.getNoun().getFPlural(), "partutgeadas");
    }


    @Test
    public void testRule14() {
        forms = generator.guessInflection("exiliieu", null, null);
        assertEquals(forms.getInflectionSubtype(), "14");
        assertEquals(forms.getNoun().getMSingular(), "exiliieu");
        assertEquals(forms.getNoun().getFSingular(), "exilieada");
        assertEquals(forms.getNoun().getMPlural(), "exiliieus");
        assertEquals(forms.getNoun().getFPlural(), "exilieadas");
    }

    @Test
    public void testBaseFormWithWhitespace() {
        forms = generator.guessInflection("isch tga", null, null);
        assertEquals(forms, null);
    }

    @Test
    public void testNormalizeBaseForm() {
        forms = generator.guessInflection("  sgiaglieu          ", null, null);
        assertEquals(forms.getInflectionSubtype(), "12");
        assertEquals(forms.getNoun().getMSingular(), "sgiaglieu");
    }

    @Test
    public void testWithEndingIndicationA() {
        forms = generator.guessInflection("client, -a", null, null);
        assertEquals(forms.getInflectionSubtype(), "3");
    }

    @Test
    public void testWithEndingIndicationDra() {
        forms = generator.guessInflection("invantader, -dra", null, null);
        assertEquals(forms.getInflectionSubtype(), "4");
    }

    @Test
    public void testWithEndingIndicationBla() {
        forms = generator.guessInflection("movibel, -bla", null, null);
        assertEquals(forms.getInflectionSubtype(), "5");
    }

    @Test
    public void testWithEndingIndicationCra() {
        forms = generator.guessInflection("scepticer, -cra", null, null);
        assertEquals(forms.getInflectionSubtype(), "6");
    }

    @Test
    public void testWithEndingIndicationVla() {
        forms = generator.guessInflection("pussevel, -vla", null, null);
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
    public void testWithEndingIndicationAda() {
        forms = generator.guessInflection("scienzio, -ada", null, null);
        assertEquals(forms.getInflectionSubtype(), "10");
    }

    @Test
    public void testWithEndingIndicationIda() {
        forms = generator.guessInflection("impedieu, -ida", null, null);
        assertEquals(forms.getInflectionSubtype(), "11");
    }
}
