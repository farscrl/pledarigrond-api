package ch.pledarigrond.inflection.generation.rumantschgrischun;

import ch.pledarigrond.inflection.model.InflectionResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class RumantschGrischunNounGeneratorTest {

    private static RumantschGrischunNounGenerator generator;
    private InflectionResponse forms;

    @BeforeClass
    public static void initialize() {
        generator = new RumantschGrischunNounGenerator();
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("3", "scolast");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "scolast");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "scolasta");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "scolasts");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "scolastas");
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
        forms = generator.generateForms("5", "responsabel");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "responsabel");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "responsabla");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "responsabels");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "responsablas");
    }

    @Test
    public void testRule6() {
        forms = generator.generateForms("6", "apotecher");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "apotecher");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "apotecra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "apotechers");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "apotecras");
    }

    @Test
    public void testRule7() {
        forms = generator.generateForms("7", "deputà");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "deputà");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "deputada");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "deputads");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "deputadas");
    }

    @Test
    public void testRule8() {
        forms = generator.generateForms("8", "nar");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "nar");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "narra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "nars");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "narras");
    }

    @Test
    public void testPluralRule1() {
        forms = generator.generateForms("1", "cudisch");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "cudisch");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "cudischs");

        forms = generator.generateForms("2", "dunna");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "dunna");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "dunnas");

        forms = generator.generateForms("1", "canapé");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "canapé");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "canapés");
    }

    @Test
    public void testPluralRule2() {
        forms = generator.generateForms("1", "cas");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "cas");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "cas");

        forms = generator.generateForms("1", "nas");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "nas");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "nas");
    }

    @Test
    public void testPluralRule3() {
        forms = generator.generateForms("1", "isch-chasa");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "isch-chasa");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "ischs-chasa");
    }

    @Test
    public void testPluralRule4() {
        forms = generator.generateForms("7", "deputà");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "deputà");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "deputada");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "deputads");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "deputadas");
    }

    @Test
    public void testPluralRule5() {
        forms = generator.generateForms("1", "anè");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "anè");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "anels");
    }

    @Test
    public void testPluralRule6() {
        forms = generator.generateForms("1", "vestgì");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "vestgì");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "vestgids");
    }
}
