package ch.pledarigrond.inflection.generation.sutsilvan;

import ch.pledarigrond.inflection.model.InflectionResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

public class SutsilvanNounGuessingTest {

    private static SutsilvanNounGenerator generator;
    private InflectionResponse forms;

    @BeforeClass
    public static void initialize() {
        generator = new SutsilvanNounGenerator();
    }

    @Test
    public void testPrintOutForms() throws IOException {
        forms = generator.guessInflection("scular", null, null);
        generator.printForms(forms.getInflectionValues(), "test");
    }

    @Test
    public void testRule3() {
        forms = generator.guessInflection("scular", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "3");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "scular");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "sculara");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "sculars");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "scularas");
    }

    @Test
    public void testRule4() {
        forms = generator.guessInflection("antschavider",null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "4");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "antschavider");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "antschavidra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "antschaviders");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "antschavidras");
    }

    @Test
    public void testRule5() {
        forms = generator.guessInflection("raspunsabel", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "5");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "raspunsabel");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "raspunsabla");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "raspunsabels");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "raspunsablas");
    }

    @Test
    public void testRule6() {
        forms = generator.guessInflection("apotecer", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "6");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "apotecer");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "apotecra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "apotecers");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "apotecras");
    }

    @Test
    public void testRule7() {
        forms = generator.guessInflection("raspunsavel", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "7");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "raspunsavel");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "raspunsavla");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "raspunsavels");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "raspunsavlas");
    }

    @Test
    public void testRule8() {
        forms = generator.guessInflection("giuven", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "8");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "giuven");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "giuvna");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "giuvens");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "giuvnas");
    }

    @Test
    public void testRule9() {
        forms = generator.guessInflection("pover", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "9");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "pover");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "povra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "povers");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "povras");
    }

    @Test
    public void testRule10() {
        forms = generator.guessInflection("impiego", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "10");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "impiego");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "impiegada");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "impiegos");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "impiegadas");
    }

    @Test
    public void testRule11() {
        forms = generator.guessInflection("impedieu", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "11");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "impedieu");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "impedida");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "impedieus");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "impedidas");
    }

    @Test
    public void testRule12() {
        forms = generator.guessInflection("sgiaglieu", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "12");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "sgiaglieu");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "sgiaglieada");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "sgiaglieus");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "sgiaglieadas");
    }


    @Test
    public void testRule13() {
        forms = generator.guessInflection("partutgieu", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "13");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "partutgieu");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "partutgeada");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "partutgieus");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "partutgeadas");
    }


    @Test
    public void testRule14() {
        forms = generator.guessInflection("exiliieu", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "14");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "exiliieu");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "exilieada");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "exiliieus");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "exilieadas");
    }

    @Test
    public void testBaseFormWithWhitespace() {
        forms = generator.guessInflection("isch tga", null, null);
        Assert.assertEquals(forms, null);
    }

    @Test
    public void testNormalizeBaseForm() {
        forms = generator.guessInflection("  sgiaglieu          ", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "12");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "sgiaglieu");
    }

    @Test
    public void testWithEndingIndicationA() {
        forms = generator.guessInflection("client, -a", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "3");
    }

    @Test
    public void testWithEndingIndicationDra() {
        forms = generator.guessInflection("invantader, -dra", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "4");
    }

    @Test
    public void testWithEndingIndicationBla() {
        forms = generator.guessInflection("movibel, -bla", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "5");
    }

    @Test
    public void testWithEndingIndicationCra() {
        forms = generator.guessInflection("scepticer, -cra", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "6");
    }

    @Test
    public void testWithEndingIndicationVla() {
        forms = generator.guessInflection("pussevel, -vla", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "7");
    }

    @Test
    public void testWithEndingIndicationVna() {
        forms = generator.guessInflection("gioven, -vna", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "8");
    }

    @Test
    public void testWithEndingIndicationVra() {
        forms = generator.guessInflection("pover, -vra", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "9");
    }

    @Test
    public void testWithEndingIndicationAda() {
        forms = generator.guessInflection("scienzio, -ada", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "10");
    }

    @Test
    public void testWithEndingIndicationIda() {
        forms = generator.guessInflection("impedieu, -ida", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "11");
    }
}
