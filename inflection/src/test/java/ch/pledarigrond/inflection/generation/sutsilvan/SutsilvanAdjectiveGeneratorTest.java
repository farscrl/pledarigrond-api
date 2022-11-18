package ch.pledarigrond.inflection.generation.sutsilvan;

import ch.pledarigrond.inflection.model.InflectionResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class SutsilvanAdjectiveGeneratorTest {

    private static SutsilvanAdjectiveGenerator generator;
    private InflectionResponse forms;

    @BeforeClass
    public static void initialize() {
        generator = new SutsilvanAdjectiveGenerator();
    }

    @Test
    public void testRule1() {
        forms = generator.generateForms("1", "puril");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "puril");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "purila");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "purils");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "purilas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "purilameing");
    }

    @Test
    public void testRule2() {
        forms = generator.generateForms("2", "veader");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "veader");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "veadra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "veaders");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "veadras");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "veadrameing");
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("3", "admirabel");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "admirabel");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "admirabla");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "admirabels");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "admirablas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "admirablameing");
    }

    @Test
    public void testRule4() {
        forms = generator.generateForms("4", "legher");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "legher");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "legra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "leghers");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "legras");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "legrameing");
    }

    @Test
    public void testRule5() {
        forms = generator.generateForms("5", "vantirevel");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "vantirevel");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "vantirevla");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "vantirevels");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "vantirevlas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "vantirevlameing");
    }

    @Test
    public void testRule6() {
        forms = generator.generateForms("6", "giuven");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "giuven");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "giuvna");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "giuvens");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "giuvnas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "giuvnameing");
    }

    @Test
    public void testRule7() {
        forms = generator.generateForms("7", "surprieu");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "surprieu");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "surprida");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "surprieus");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "surpridas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "surpridameing");
    }

    @Test
    public void testRule8() {
        forms = generator.generateForms("8", "ocupo");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "ocupo");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "ocupada");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "ocupos");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "ocupadas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "ocupadameing");
    }

    @Test
    public void testRule9() {
        forms = generator.generateForms("9", "vescel");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "vescel");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "vescla");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "vescels");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "vesclas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "vesclameing");
    }

    @Test
    public void testRule10() {
        forms = generator.generateForms("10", "pover");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "pover");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "povra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "povers");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "povras");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "povrameing");
    }

    @Test
    public void testRule11() {
        forms = generator.generateForms("11", "orfen");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "orfen");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "orfna");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "orfens");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "orfnas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "orfnameing");
    }

    @Test
    public void testRule12() {
        forms = generator.generateForms("12", "detaglieu");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "detaglieu");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "detaglieada");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "detaglieus");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "detaglieadas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "detaglieadameing");
    }

    @Test
    public void testRule13() {
        forms = generator.generateForms("13", "partutgieu");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "partutgieu");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "partutgeada");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "partutgieus");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "partutgeadas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "partutgeadameing");
    }
}
