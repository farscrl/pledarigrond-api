package ch.pledarigrond.inflection.generation.surmiran;

import ch.pledarigrond.inflection.model.InflectionResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

public class SurmiranAdjectiveGeneratorTest {

    private static SurmiranAdjectiveGenerator generator;
    private InflectionResponse forms;

    @BeforeClass
    public static void initialize() {
        generator = new SurmiranAdjectiveGenerator();
    }

    @Test
    public void testRule1() {
        forms = generator.generateForms("1", "puril");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "puril");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "purila");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "purils");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "purilas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "purilamaintg");
    }

    @Test
    public void testRule2() {
        forms = generator.generateForms("2", "veder");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "veder");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "vedra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "veders");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "vedras");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "vedramaintg");
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("3", "admirabel");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "admirabel");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "admirabla");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "admirabels");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "admirablas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "admirablamaintg");
    }

    @Test
    public void testRule4() {
        forms = generator.generateForms("4", "zacher");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "zacher");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "zacra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "zachers");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "zacras");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "zacramaintg");
    }

    @Test
    public void testRule5() {
        forms = generator.generateForms("5", "capavel");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "capavel");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "capavla");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "capavels");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "capavlas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "capavlamaintg");
    }

    @Test
    public void testRule6() {
        forms = generator.generateForms("6", "gioven");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "gioven");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "giovna");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "giovens");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "giovnas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "giovnamaintg");
    }

    @Test
    public void testRule7() {
        forms = generator.generateForms("7", "distinguia");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "distinguia");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "distingueida");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "distinguias");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "distingueidas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "distingueidamaintg");
    }

    @Test
    public void testRule8() {
        forms = generator.generateForms("8", "attempo");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "attempo");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "attempada");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "attempos");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "attempadas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "attempadamaintg");
    }

    @Test
    public void testRule9() {
        forms = generator.generateForms("9", "schlaschea");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "schlaschea");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "schlascheda");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "schlascheas");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "schlaschedas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "schlaschedamaintg");
    }

    @Test
    public void testRule10() {
        forms = generator.generateForms("10", "tschuf");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "tschuf");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "tschuffa");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "tschufs");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "tschuffas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "tschuffamaintg");
    }
}
