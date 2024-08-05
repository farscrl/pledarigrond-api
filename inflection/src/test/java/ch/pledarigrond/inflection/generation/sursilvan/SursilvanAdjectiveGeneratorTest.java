package ch.pledarigrond.inflection.generation.sursilvan;

import ch.pledarigrond.inflection.model.InflectionResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class SursilvanAdjectiveGeneratorTest {

    private static SursilvanAdjectiveGenerator generator;
    private InflectionResponse forms;

    @BeforeClass
    public static void initialize() {
        generator = new SursilvanAdjectiveGenerator();
    }

    @Test
    public void testRule1() {
        forms = generator.generateForms("1", "verd");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "verd");
        Assert.assertEquals(forms.getInflectionValues().get("predicative"), "verds");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "verda");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "verds");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "verdas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "verdamein");
    }

    @Test
    public void testRule2() {
        forms = generator.generateForms("2", "ventireivel");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "ventireivel");
        Assert.assertEquals(forms.getInflectionValues().get("predicative"), "ventireivels");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "ventireivla");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "ventireivels");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "ventireivlas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "ventireivlamein");
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("3", "viscal");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "viscal");
        Assert.assertEquals(forms.getInflectionValues().get("predicative"), "viscals");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "viscla");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "viscals");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "visclas");
        // Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "visclamein");
    }

    @Test
    public void testRule4() {
        forms = generator.generateForms("4", "giuven");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "giuven");
        Assert.assertEquals(forms.getInflectionValues().get("predicative"), "giuvens");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "giuvna");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "giuvens");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "giuvnas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "giuvnamein");
    }

    @Test
    public void testRule6() {
        forms = generator.generateForms("6", "pauper");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "pauper");
        Assert.assertEquals(forms.getInflectionValues().get("predicative"), "paupers");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "paupra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "paupers");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "paupras");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "paupramein");
    }

    @Test
    public void testRule7() {
        forms = generator.generateForms("7", "bugliu");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "bugliu");
        Assert.assertEquals(forms.getInflectionValues().get("predicative"), "buglius");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "buglida");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "buglius");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "buglidas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "buglidamein");
    }

    @Test
    public void testRule8() {
        forms = generator.generateForms("8", "barsau");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "barsau");
        Assert.assertEquals(forms.getInflectionValues().get("predicative"), "barsaus");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "barsada");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "barsaus");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "barsadas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "barsadamein");
    }

    @Test
    public void testRule10() {
        forms = generator.generateForms("10", "tschuf");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "tschuf");
        Assert.assertEquals(forms.getInflectionValues().get("predicative"), "tschufs");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "tschuffa");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "tschufs");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "tschuffas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "tschuffamein");
    }
}
