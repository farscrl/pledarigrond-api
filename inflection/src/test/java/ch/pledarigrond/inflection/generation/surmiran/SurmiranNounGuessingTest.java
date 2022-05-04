package ch.pledarigrond.inflection.generation.surmiran;

import ch.pledarigrond.inflection.model.InflectionResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

public class SurmiranNounGuessingTest {

    private static SurmiranNounGenerator generator;
    private InflectionResponse forms;

    @BeforeClass
    public static void initialize() {
        generator = new SurmiranNounGenerator();
    }

    @Test
    public void testPrintOutForms() throws IOException {
        forms = generator.guessInflection("scolast", null, null);
        generator.printForms(forms.getInflectionValues(), "test");
    }

    @Test
    public void testRule3() {
        forms = generator.guessInflection("scolast", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "3");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "scolast");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "scolasta");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "scolasts");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "scolastas");
    }

    @Test
    public void testRule4() {
        forms = generator.guessInflection("manader",null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "4");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "manader");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "manadra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "manaders");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "manadras");
    }

    @Test
    public void testRule5() {
        forms = generator.guessInflection("responsabel", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "5");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "responsabel");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "responsabla");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "responsabels");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "responsablas");
    }

    @Test
    public void testRule6() {
        forms = generator.guessInflection("apotecher", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "6");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "apotecher");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "apotecra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "apotechers");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "apotecras");
    }

    @Test
    public void testRule7() {
        forms = generator.guessInflection("flevel", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "7");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "flevel");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "flevla");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "flevels");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "flevlas");
    }

    @Test
    public void testRule8() {
        forms = generator.guessInflection("gioven", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "8");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "gioven");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "giovna");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "giovens");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "giovnas");
    }

    @Test
    public void testRule9() {
        forms = generator.guessInflection("ampluia", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "9");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "ampluia");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "amplueida");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "ampluias");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "amplueidas");
    }

    @Test
    public void testRule10() {
        forms = generator.guessInflection("anvido", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "10");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "anvido");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "anvidada");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "anvidos");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "anvidadas");
    }

    @Test
    public void testRule11() {
        forms = generator.guessInflection("relaschea", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "11");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "relaschea");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "relascheda");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "relascheas");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "relaschedas");
    }

    @Test
    public void testRule12() {
        forms = generator.guessInflection("minoren", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "12");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "minoren");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "minorenna");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "minorens");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "minorennas");
    }

    @Test
    public void testPluralRule4() {
        forms = generator.guessInflection("pale", "f", null);
        Assert.assertEquals(forms.getInflectionSubType().id, "2");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "pale");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "paleis");
    }

    @Test
    public void testBaseFormWithWhitespace() {
        forms = generator.guessInflection("isch tga", null, null);
        Assert.assertEquals(forms, null);
    }

    @Test
    public void testNormalizeBaseForm() {
        forms = generator.guessInflection("  minoren         ", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "12");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "minoren");
    }
}
