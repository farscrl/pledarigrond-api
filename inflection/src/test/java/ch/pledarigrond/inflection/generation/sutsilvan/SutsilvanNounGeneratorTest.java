package ch.pledarigrond.inflection.generation.sutsilvan;

import ch.pledarigrond.inflection.model.InflectionResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

public class SutsilvanNounGeneratorTest {

    private static SutsilvanNounGenerator generator;
    private InflectionResponse forms;

    @BeforeClass
    public static void initialize() {
        generator = new SutsilvanNounGenerator();
    }

    @Test
    public void testPrintOutForms() throws IOException {
        forms = generator.generateForms("3", "scular");
        generator.printForms(forms.getInflectionValues(), "test");
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("3", "scular");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "scular");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "sculara");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "sculars");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "scularas");
    }

    @Test
    public void testRule4() {
        forms = generator.generateForms("4", "antschavider");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "antschavider");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "antschavidra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "antschaviders");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "antschavidras");
    }

    @Test
    public void testRule5() {
        forms = generator.generateForms("5", "raspunsabel");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "raspunsabel");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "raspunsabla");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "raspunsabels");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "raspunsablas");
    }

    @Test
    public void testRule6() {
        forms = generator.generateForms("6", "apotecer");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "apotecer");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "apotecra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "apotecers");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "apotecras");
    }

    @Test
    public void testRule7() {
        forms = generator.generateForms("7", "raspunsavel");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "raspunsavel");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "raspunsavla");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "raspunsavels");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "raspunsavlas");
    }

    @Test
    public void testRule8() {
        forms = generator.generateForms("8", "giuven");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "giuven");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "giuvna");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "giuvens");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "giuvnas");
    }

    @Test
    public void testRule9() {
        forms = generator.generateForms("9", "pover");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "pover");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "povra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "povers");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "povras");
    }

    @Test
    public void testRule10() {
        forms = generator.generateForms("10", "impiego");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "impiego");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "impiegada");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "impiegos");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "impiegadas");
    }

    @Test
    public void testRule11() {
        forms = generator.generateForms("11", "impedieu");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "impedieu");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "impedida");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "impedieus");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "impedidas");
    }

    @Test
    public void testRule12() {
        forms = generator.generateForms("12", "sgiaglieu");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "sgiaglieu");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "sgiaglieada");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "sgiaglieus");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "sgiaglieadas");
    }

    @Test
    public void testRule13() {
        forms = generator.generateForms("13", "partutgieu");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "partutgieu");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "partutgeada");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "partutgieus");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "partutgeadas");
    }

    @Test
    public void testRule14() {
        forms = generator.generateForms("14", "exiliieu");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "exiliieu");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "exilieada");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "exiliieus");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "exilieadas");
    }

    @Test
    public void testRule15() {
        forms = generator.generateForms("15", "pér");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "pér");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), null);
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "pérs");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), null);
        Assert.assertEquals(forms.getInflectionValues().get("pluralCollectiv"), "péra");
    }

    @Test
    public void testPluralRule2() {
        forms = generator.generateForms("1", "pass");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "pass");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "pass");
    }

    @Test
    public void testPluralRule3() {
        forms = generator.generateForms("1", "esch-tgea");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "esch-tgea");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "eschs-tgea");
    }

    @Test
    public void testPluralRule4() {
        forms = generator.generateForms("1", "cunti");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "cunti");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "cunteals");
    }
}
