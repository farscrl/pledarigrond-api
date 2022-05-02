package ch.pledarigrond.inflection.generation.surmiran;

import ch.pledarigrond.inflection.model.InflectionResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

public class SurmiranNounGeneratorTest {

    private static SurmiranNounGenerator generator;
    private InflectionResponse forms;

    @BeforeClass
    public static void initialize() {
        generator = new SurmiranNounGenerator();
    }

    @Test
    public void testPrintOutForms() throws IOException {
        forms = generator.generateForms("scolast", "3");
        generator.printForms(forms.getInflectionValues(), "test");
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("scolast", "3");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "scolast");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "scolasta");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "scolasts");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "scolastas");
    }

    @Test
    public void testRule4() {
        forms = generator.generateForms("manader", "4");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "manader");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "manadra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "manaders");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "manadras");
    }

    @Test
    public void testRule5() {
        forms = generator.generateForms("responsabel", "5");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "responsabel");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "responsabla");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "responsabels");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "responsablas");
    }

    @Test
    public void testRule6() {
        forms = generator.generateForms("apotecher", "6");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "apotecher");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "apotecra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "apotechers");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "apotecras");
    }

    @Test
    public void testRule7() {
        forms = generator.generateForms("flevel", "7");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "flevel");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "flevla");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "flevels");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "flevlas");
    }

    @Test
    public void testRule8() {
        forms = generator.generateForms("gioven", "8");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "gioven");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "giovna");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "giovens");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "giovnas");
    }

    @Test
    public void testRule9() {
        forms = generator.generateForms("ampluia", "9");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "ampluia");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "amplueida");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "ampluias");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "amplueidas");
    }

    @Test
    public void testRule10() {
        forms = generator.generateForms("anvido", "10");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "anvido");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "anvidada");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "anvidos");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "anvidadas");
    }

    @Test
    public void testRule11() {
        forms = generator.generateForms("relaschea", "11");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "relaschea");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "relascheda");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "relascheas");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "relaschedas");
    }

    @Test
    public void testRule12() {
        forms = generator.generateForms("minoren", "12");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "minoren");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "minorenna");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "minorens");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "minorennas");
    }

    @Test
    public void testPluralRule2() {
        forms = generator.generateForms("cass", "1");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "cass");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "cass");
    }

    @Test
    public void testPluralRule3() {
        forms = generator.generateForms("isch-tgesa", "1");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "isch-tgesa");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "ischs-tgesa");
    }

    @Test
    public void testPluralRule4() {
        forms = generator.generateForms("pale", "2");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "pale");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "paleis");
    }

    @Test
    public void testPluralRule5() {
        forms = generator.generateForms("pare", "2");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "pare");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "pareis");
    }
}
