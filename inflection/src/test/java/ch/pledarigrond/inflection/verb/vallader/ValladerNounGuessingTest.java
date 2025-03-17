package ch.pledarigrond.inflection.verb.vallader;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.noun.puter.PuterNounGenerator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ValladerNounGuessingTest {

    private static PuterNounGenerator generator;
    private InflectionDto forms;

    @BeforeClass
    public static void initialize() {
        generator = new PuterNounGenerator();
    }

    @Test
    public void testRule3() {
        forms = generator.guessInflection("scolar", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "3");
        Assert.assertEquals(forms.getNoun().getMSingular(), "scolar");
        Assert.assertEquals(forms.getNoun().getFSingular(), "scolara");
    }

    @Test
    public void testRule4() {
        forms = generator.guessInflection("mneder",null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "4");
        Assert.assertEquals(forms.getNoun().getMSingular(), "mneder");
        Assert.assertEquals(forms.getNoun().getFSingular(), "mnedra");
    }

    @Test
    public void testRule5() {
        forms = generator.guessInflection("respunsabel", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "5");
        Assert.assertEquals(forms.getNoun().getMSingular(), "respunsabel");
        Assert.assertEquals(forms.getNoun().getFSingular(), "respunsabla");
    }

    @Test
    public void testRule6() {
        forms = generator.guessInflection("crettaivel", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "6");
        Assert.assertEquals(forms.getNoun().getMSingular(), "crettaivel");
        Assert.assertEquals(forms.getNoun().getFSingular(), "crettaivla");
    }

    @Test
    public void testRule7() {
        forms = generator.guessInflection("apoteker", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "7");
        Assert.assertEquals(forms.getNoun().getMSingular(), "apoteker");
        Assert.assertEquals(forms.getNoun().getFSingular(), "apotecra");
    }

    @Test
    public void testRule8() {
        forms = generator.guessInflection("giuven", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "8");
        Assert.assertEquals(forms.getNoun().getMSingular(), "giuven");
        Assert.assertEquals(forms.getNoun().getFSingular(), "giuvna");
    }

    @Test
    public void testRule9() {
        forms = generator.guessInflection("pover", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "9");
        Assert.assertEquals(forms.getNoun().getMSingular(), "pover");
        Assert.assertEquals(forms.getNoun().getFSingular(), "povra");
    }

    @Test
    public void testRule10() {
        forms = generator.guessInflection("depüto", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "10");
        Assert.assertEquals(forms.getNoun().getMSingular(), "depüto");
        Assert.assertEquals(forms.getNoun().getFSingular(), "depüteda");
    }

    @Test
    public void testRule11() {
        forms = generator.guessInflection("impiego", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "11");
        Assert.assertEquals(forms.getNoun().getMSingular(), "impiego");
        Assert.assertEquals(forms.getNoun().getFSingular(), "impiegheda");
    }

    @Test
    public void testRule12() {
        forms = generator.guessInflection("piglio", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "12");
        Assert.assertEquals(forms.getNoun().getMSingular(), "piglio");
        Assert.assertEquals(forms.getNoun().getFSingular(), "piglieda");
    }

    @Test
    public void testRule13() {
        forms = generator.guessInflection("impedieu", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "13");
        Assert.assertEquals(forms.getNoun().getMSingular(), "impedieu");
        Assert.assertEquals(forms.getNoun().getFSingular(), "impedida");
    }

    @Test
    public void testRule14() {
        forms = generator.guessInflection("assaglieu", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "14");
        Assert.assertEquals(forms.getNoun().getMSingular(), "assaglieu");
        Assert.assertEquals(forms.getNoun().getFSingular(), "assaglida");
    }

    @Test
    public void testBaseFormWithWhitespace() {
        forms = generator.guessInflection("porta da chesa", null, null);
        Assert.assertEquals(forms, null);
    }

    @Test
    public void testNormalizeBaseForm() {
        forms = generator.guessInflection("  scolar         ", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "3");
        Assert.assertEquals(forms.getNoun().getMSingular(), "scolar");
    }

    @Test
    public void testWithEndingIndicationA() {
        forms = generator.guessInflection("client, -a", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "3");
    }

    @Test
    public void testWithEndingIndicationDra() {
        forms = generator.guessInflection("cumprader, -dra", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "4");
    }

    @Test
    public void testWithEndingIndicationBla() {
        forms = generator.guessInflection("responsabel, -bla", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "5");
    }

    @Test
    public void testWithEndingIndicationVla() {
        forms = generator.guessInflection("crettaivel, -vla", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "6");
    }

    @Test
    public void testWithEndingIndicationCra() {
        forms = generator.guessInflection("apoteker, -cra", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "7");
    }

    @Test
    public void testWithEndingIndicationVna() {
        forms = generator.guessInflection("gioven, -vna", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "8");
    }

    @Test
    public void testWithEndingIndicationVra() {
        forms = generator.guessInflection("pover, -vra", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "9");
    }

    @Test
    public void testWithEndingIndicationEda() {
        forms = generator.guessInflection("depüto, -eda", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "10");
    }

    @Test
    public void testWithEndingIndicationGheda() {
        forms = generator.guessInflection("impiego, -gheda", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "11");
    }

    @Test
    public void testWithEndingIndicationGlieada() {
        forms = generator.guessInflection("piglio, -glieda", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "12");
    }

    @Test
    public void testWithEndingIndicationIda() {
        forms = generator.guessInflection("impedieu, -ida", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "13");
    }

    @Test
    public void testWithEndingIndicationGlida() {
        forms = generator.guessInflection("assaglieu, -glida", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "14");
    }
}
