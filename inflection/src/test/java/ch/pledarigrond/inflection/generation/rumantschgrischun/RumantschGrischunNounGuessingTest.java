package ch.pledarigrond.inflection.generation.rumantschgrischun;

import ch.pledarigrond.inflection.model.InflectionResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class RumantschGrischunNounGuessingTest {

    private static RumantschGrischunNounGenerator generator;
    private InflectionResponse forms;

    @BeforeClass
    public static void initialize() {
        generator = new RumantschGrischunNounGenerator();
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
        forms = generator.guessInflection("deputà", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "7");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "deputà");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "deputada");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "deputads");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "deputadas");
    }

    @Test
    public void testRule8() {
        forms = generator.guessInflection("nar", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "8");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "nar");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "narra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "nars");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "narras");
    }

    @Test
    public void testBaseFormWithWhitespace() {
        forms = generator.guessInflection("isch chasa", null, null);
        Assert.assertEquals(forms, null);
    }

    @Test
    public void testNormalizeBaseForm() {
        forms = generator.guessInflection("  nar         ", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "8");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "nar");
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
    public void testWithEndingIndicationCra() {
        forms = generator.guessInflection("scepticher, -cra", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "6");
    }

    @Test
    public void testWithEndingIndicationVla() {
        forms = generator.guessInflection("delegà, -ada", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "7");
    }
}
