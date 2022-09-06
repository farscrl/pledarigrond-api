package ch.pledarigrond.inflection.generation.rumantschgrischun;

import ch.pledarigrond.inflection.model.InflectionResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class RumantschGrischunAdjectiveGeneratorTest {

    private static RumantschGrischunAdjectiveGenerator generator;
    private InflectionResponse forms;

    @BeforeClass
    public static void initialize() {
        generator = new RumantschGrischunAdjectiveGenerator();
    }

    @Test
    public void testRule1() {
        forms = generator.generateForms("1", "puril");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "puril");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "purila");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "purils");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "purilas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "purilmain");
    }

    @Test
    public void testRule2() {
        forms = generator.generateForms("2", "veder");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "veder");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "vedra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "veders");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "vedras");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "vedramain");
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("3", "admirabel");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "admirabel");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "admirabla");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "admirabels");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "admirablas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "admirablamain");
    }

    @Test
    public void testRule4() {
        forms = generator.generateForms("4", "mediocher");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "mediocher");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "mediocra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "mediochers");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "mediocras");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "mediocramain");
    }

    @Test
    public void testRule5() {
        forms = generator.generateForms("5", "amiaivel");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "amiaivel");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "amiaivla");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "amiaivels");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "amiaivlas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "amiaivlamain");
    }

    @Test
    public void testRule6() {
        forms = generator.generateForms("6", "giuven");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "giuven");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "giuvna");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "giuvens");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "giuvnas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "giuvnamain");
    }

    @Test
    public void testRule7() {
        forms = generator.generateForms("7", "isolà");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "isolà");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "isolada");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "isolads");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "isoladas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "isoladamain");
    }

    @Test
    public void testRule8() {
        forms = generator.generateForms("8", "sclerì");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "sclerì");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "sclerida");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "sclerids");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "scleridas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "scleridamain");
    }

    @Test
    public void testRule9() {
        forms = generator.generateForms("9", "tschuf");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "tschuf");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "tschuffa");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "tschufs");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "tschuffas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "tschuffamain");

        forms = generator.generateForms("9", "rut");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "rut");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "rutta");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "ruts");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "ruttas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "ruttamain");
    }
}
