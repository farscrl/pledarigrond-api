package ch.pledarigrond.inflection.verb.sutsilvan;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.noun.sutsilvan.SutsilvanNounGenerator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class SutsilvanNounGuessingTest {

    private static SutsilvanNounGenerator generator;
    private InflectionDto forms;

    @BeforeClass
    public static void initialize() {
        generator = new SutsilvanNounGenerator();
    }

    @Test
    public void testRule3() {
        forms = generator.guessInflection("scular", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "3");
        Assert.assertEquals(forms.getNoun().getMSingular(), "scular");
        Assert.assertEquals(forms.getNoun().getFSingular(), "sculara");
        Assert.assertEquals(forms.getNoun().getMPlural(), "sculars");
        Assert.assertEquals(forms.getNoun().getFPlural(), "scularas");
    }

    @Test
    public void testRule4() {
        forms = generator.guessInflection("antschavider",null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "4");
        Assert.assertEquals(forms.getNoun().getMSingular(), "antschavider");
        Assert.assertEquals(forms.getNoun().getFSingular(), "antschavidra");
        Assert.assertEquals(forms.getNoun().getMPlural(), "antschaviders");
        Assert.assertEquals(forms.getNoun().getFPlural(), "antschavidras");
    }

    @Test
    public void testRule5() {
        forms = generator.guessInflection("raspunsabel", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "5");
        Assert.assertEquals(forms.getNoun().getMSingular(), "raspunsabel");
        Assert.assertEquals(forms.getNoun().getFSingular(), "raspunsabla");
        Assert.assertEquals(forms.getNoun().getMPlural(), "raspunsabels");
        Assert.assertEquals(forms.getNoun().getFPlural(), "raspunsablas");
    }

    @Test
    public void testRule6() {
        forms = generator.guessInflection("apotecer", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "6");
        Assert.assertEquals(forms.getNoun().getMSingular(), "apotecer");
        Assert.assertEquals(forms.getNoun().getFSingular(), "apotecra");
        Assert.assertEquals(forms.getNoun().getMPlural(), "apotecers");
        Assert.assertEquals(forms.getNoun().getFPlural(), "apotecras");
    }

    @Test
    public void testRule7() {
        forms = generator.guessInflection("raspunsavel", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "7");
        Assert.assertEquals(forms.getNoun().getMSingular(), "raspunsavel");
        Assert.assertEquals(forms.getNoun().getFSingular(), "raspunsavla");
        Assert.assertEquals(forms.getNoun().getMPlural(), "raspunsavels");
        Assert.assertEquals(forms.getNoun().getFPlural(), "raspunsavlas");
    }

    @Test
    public void testRule8() {
        forms = generator.guessInflection("giuven", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "8");
        Assert.assertEquals(forms.getNoun().getMSingular(), "giuven");
        Assert.assertEquals(forms.getNoun().getFSingular(), "giuvna");
        Assert.assertEquals(forms.getNoun().getMPlural(), "giuvens");
        Assert.assertEquals(forms.getNoun().getFPlural(), "giuvnas");
    }

    @Test
    public void testRule9() {
        forms = generator.guessInflection("pover", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "9");
        Assert.assertEquals(forms.getNoun().getMSingular(), "pover");
        Assert.assertEquals(forms.getNoun().getFSingular(), "povra");
        Assert.assertEquals(forms.getNoun().getMPlural(), "povers");
        Assert.assertEquals(forms.getNoun().getFPlural(), "povras");
    }

    @Test
    public void testRule10() {
        forms = generator.guessInflection("impiego", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "10");
        Assert.assertEquals(forms.getNoun().getMSingular(), "impiego");
        Assert.assertEquals(forms.getNoun().getFSingular(), "impiegada");
        Assert.assertEquals(forms.getNoun().getMPlural(), "impiegos");
        Assert.assertEquals(forms.getNoun().getFPlural(), "impiegadas");
    }

    @Test
    public void testRule11() {
        forms = generator.guessInflection("impedieu", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "11");
        Assert.assertEquals(forms.getNoun().getMSingular(), "impedieu");
        Assert.assertEquals(forms.getNoun().getFSingular(), "impedida");
        Assert.assertEquals(forms.getNoun().getMPlural(), "impedieus");
        Assert.assertEquals(forms.getNoun().getFPlural(), "impedidas");
    }

    @Test
    public void testRule12() {
        forms = generator.guessInflection("sgiaglieu", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "12");
        Assert.assertEquals(forms.getNoun().getMSingular(), "sgiaglieu");
        Assert.assertEquals(forms.getNoun().getFSingular(), "sgiaglieada");
        Assert.assertEquals(forms.getNoun().getMPlural(), "sgiaglieus");
        Assert.assertEquals(forms.getNoun().getFPlural(), "sgiaglieadas");
    }


    @Test
    public void testRule13() {
        forms = generator.guessInflection("partutgieu", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "13");
        Assert.assertEquals(forms.getNoun().getMSingular(), "partutgieu");
        Assert.assertEquals(forms.getNoun().getFSingular(), "partutgeada");
        Assert.assertEquals(forms.getNoun().getMPlural(), "partutgieus");
        Assert.assertEquals(forms.getNoun().getFPlural(), "partutgeadas");
    }


    @Test
    public void testRule14() {
        forms = generator.guessInflection("exiliieu", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "14");
        Assert.assertEquals(forms.getNoun().getMSingular(), "exiliieu");
        Assert.assertEquals(forms.getNoun().getFSingular(), "exilieada");
        Assert.assertEquals(forms.getNoun().getMPlural(), "exiliieus");
        Assert.assertEquals(forms.getNoun().getFPlural(), "exilieadas");
    }

    @Test
    public void testBaseFormWithWhitespace() {
        forms = generator.guessInflection("isch tga", null, null);
        Assert.assertEquals(forms, null);
    }

    @Test
    public void testNormalizeBaseForm() {
        forms = generator.guessInflection("  sgiaglieu          ", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "12");
        Assert.assertEquals(forms.getNoun().getMSingular(), "sgiaglieu");
    }

    @Test
    public void testWithEndingIndicationA() {
        forms = generator.guessInflection("client, -a", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "3");
    }

    @Test
    public void testWithEndingIndicationDra() {
        forms = generator.guessInflection("invantader, -dra", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "4");
    }

    @Test
    public void testWithEndingIndicationBla() {
        forms = generator.guessInflection("movibel, -bla", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "5");
    }

    @Test
    public void testWithEndingIndicationCra() {
        forms = generator.guessInflection("scepticer, -cra", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "6");
    }

    @Test
    public void testWithEndingIndicationVla() {
        forms = generator.guessInflection("pussevel, -vla", null, null);
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
    public void testWithEndingIndicationAda() {
        forms = generator.guessInflection("scienzio, -ada", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "10");
    }

    @Test
    public void testWithEndingIndicationIda() {
        forms = generator.guessInflection("impedieu, -ida", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "11");
    }
}
