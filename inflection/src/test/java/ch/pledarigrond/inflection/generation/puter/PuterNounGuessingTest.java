package ch.pledarigrond.inflection.generation.puter;

import ch.pledarigrond.inflection.model.InflectionResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

public class PuterNounGuessingTest {

    private static PuterNounGenerator generator;
    private InflectionResponse forms;

    @BeforeClass
    public static void initialize() {
        generator = new PuterNounGenerator();
    }

    @Test
    public void testRule3() {
        forms = generator.guessInflection("scolar", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "3");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "scolar");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "scolara");
    }

    @Test
    public void testRule4() {
        forms = generator.guessInflection("mneder",null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "4");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "mneder");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "mnedra");
    }

    @Test
    public void testRule5() {
        forms = generator.guessInflection("respunsabel", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "5");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "respunsabel");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "respunsabla");
    }

    @Test
    public void testRule6() {
        forms = generator.guessInflection("crettaivel", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "6");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "crettaivel");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "crettaivla");
    }

    @Test
    public void testRule7() {
        forms = generator.guessInflection("apoteker", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "7");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "apoteker");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "apotecra");
    }

    @Test
    public void testRule8() {
        forms = generator.guessInflection("giuven", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "8");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "giuven");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "giuvna");
    }

    @Test
    public void testRule9() {
        forms = generator.guessInflection("pover", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "9");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "pover");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "povra");
    }

    @Test
    public void testRule10() {
        forms = generator.guessInflection("depüto", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "10");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "depüto");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "depüteda");
    }

    @Test
    public void testRule11() {
        forms = generator.guessInflection("impiego", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "11");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "impiego");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "impiegheda");
    }

    @Test
    public void testRule12() {
        forms = generator.guessInflection("piglio", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "12");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "piglio");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "piglieda");
    }

    @Test
    public void testRule13() {
        forms = generator.guessInflection("impedieu", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "13");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "impedieu");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "impedida");
    }

    @Test
    public void testRule14() {
        forms = generator.guessInflection("assaglieu", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "14");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "assaglieu");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "assaglida");
    }

    @Test
    public void testBaseFormWithWhitespace() {
        forms = generator.guessInflection("porta da chesa", null, null);
        Assert.assertEquals(forms, null);
    }

    @Test
    public void testNormalizeBaseForm() {
        forms = generator.guessInflection("  scolar         ", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "3");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "scolar");
    }

    @Test
    public void testWithEndingIndicationA() {
        forms = generator.guessInflection("client, -a", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "3");
    }

    @Test
    public void testWithEndingIndicationDra() {
        forms = generator.guessInflection("cumprader, -dra", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "4");
    }

    @Test
    public void testWithEndingIndicationBla() {
        forms = generator.guessInflection("responsabel, -bla", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "5");
    }

    @Test
    public void testWithEndingIndicationVla() {
        forms = generator.guessInflection("crettaivel, -vla", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "6");
    }

    @Test
    public void testWithEndingIndicationCra() {
        forms = generator.guessInflection("apoteker, -cra", null, null);
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
    public void testWithEndingIndicationEda() {
        forms = generator.guessInflection("depüto, -eda", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "10");
    }

    @Test
    public void testWithEndingIndicationGheda() {
        forms = generator.guessInflection("impiego, -gheda", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "11");
    }

    @Test
    public void testWithEndingIndicationGlieada() {
        forms = generator.guessInflection("piglio, -glieda", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "12");
    }

    @Test
    public void testWithEndingIndicationIda() {
        forms = generator.guessInflection("impedieu, -ida", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "13");
    }

    @Test
    public void testWithEndingIndicationGlida() {
        forms = generator.guessInflection("assaglieu, -glida", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "14");
    }
}
