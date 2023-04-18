package ch.pledarigrond.inflection.generation.vallader;

import ch.pledarigrond.inflection.model.InflectionResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ValladerNounGeneratorTest {

    private static ValladerNounGenerator generator;
    private InflectionResponse forms;

    @BeforeClass
    public static void initialize() {
        generator = new ValladerNounGenerator();
    }

    @Test
    public void testRule1() {
        forms = generator.generateForms("1", "cas");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "cas");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), null);
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "cas");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), null);
    }

    @Test
    public void testRule2() {
        forms = generator.generateForms("2", "palü");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), null);
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "palü");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), null);
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "palüts");
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("3", "scolar");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "scolar");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "scolara");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "scolars");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "scolaras");
    }

    @Test
    public void testRule4() {
        forms = generator.generateForms("4", "manader");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "manader");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "manadra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "manaders");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "manadras");
    }

    @Test
    public void testRule5() {
        forms = generator.generateForms("5", "respunsabel");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "respunsabel");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "respunsabla");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "respunsabels");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "respunsablas");
    }

    @Test
    public void testRule6() {
        forms = generator.generateForms("6", "crettaivel");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "crettaivel");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "crettaivla");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "crettaivels");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "crettaivlas");
    }

    @Test
    public void testRule7() {
        forms = generator.generateForms("7", "apoteker");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "apoteker");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "apotecra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "apotekers");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "apotecras");
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
        forms = generator.generateForms("10", "deputà");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "deputà");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "deputada");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "deputats");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "deputadas");
    }

    @Test
    public void testRule11() {
        forms = generator.generateForms("11", "vendü");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "vendü");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "vendüda");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "vendüts");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "vendüdas");
    }

    @Test
    public void testRule12() {
        forms = generator.generateForms("12", "impedi");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "impedi");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "impedida");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "impedits");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "impedidas");
    }

    @Test
    public void testRule13() {
        forms = generator.generateForms("13", "besch");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "besch");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), null);
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "beschs");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), null);
        Assert.assertEquals(forms.getInflectionValues().get("pluralCollectiv"), "bescha");
    }

    @Test
    public void testPluralRule2() {
        forms = generator.generateForms("1", "pass");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "pass");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "pass");

        forms = generator.generateForms("1", "nas");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "nas");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "nas");
    }

    @Test
    public void testPluralRule3() {
        forms = generator.generateForms("2", "sudà");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "sudà");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "sudats");
    }

    @Test
    public void testPluralRule4() {
        forms = generator.generateForms("1", "curtè");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "curtè");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "curtels");
    }

    @Test
    public void testPluralRule5() {
        forms = generator.generateForms("2", "vesti");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "vesti");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "vestits");
    }

    @Test
    public void testPluralRule6() {
        forms = generator.generateForms("1", "creschü");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "creschü");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "creschüts");
    }

    @Test
    public void testUpperCase() {
        forms = generator.generateForms("3", "Argoves");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "Argoves");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "Argovesa");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "Argoves");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "Argovesas");
    }
}
