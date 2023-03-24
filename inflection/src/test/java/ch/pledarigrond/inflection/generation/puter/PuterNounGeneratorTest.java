package ch.pledarigrond.inflection.generation.puter;

import ch.pledarigrond.inflection.model.InflectionResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

public class PuterNounGeneratorTest {

    private static PuterNounGenerator generator;
    private InflectionResponse forms;

    @BeforeClass
    public static void initialize() {
        generator = new PuterNounGenerator();
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
        forms = generator.generateForms("2", "palüd");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), null);
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "palüd");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), null);
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "palüds");
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
        forms = generator.generateForms("4", "mneder");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "mneder");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "mnedra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "mneders");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "mnedras");
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
        forms = generator.generateForms("10", "depüto");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "depüto");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "depüteda");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "depütos");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "depütedas");
    }

    @Test
    public void testRule11() {
        forms = generator.generateForms("11", "impiego");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "impiego");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "impiegheda");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "impiegos");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "impieghedas");
    }

    @Test
    public void testRule12() {
        forms = generator.generateForms("12", "piglio");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "piglio");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "piglieda");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "piglios");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "pigliedas");
    }

    @Test
    public void testRule13() {
        forms = generator.generateForms("13", "impedieu");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "impedieu");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "impedida");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "impedieus");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "impedidas");
    }

    @Test
    public void testRule14() {
        forms = generator.generateForms("14", "assaglieu");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "assaglieu");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "assaglida");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "assaglieus");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "assaglidas");
    }

    @Test
    public void testRule15() {
        forms = generator.generateForms("15", "besch");
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

        forms = generator.generateForms("1", "nes");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "nes");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "nes");
    }

    @Test
    public void testPluralRule3() {
        forms = generator.generateForms("1", "curtè");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "curtè");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "curtels");
    }

    @Test
    public void testPluralRule4() {
        forms = generator.generateForms("2", "chalur");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "chalur");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "chaluors");
    }

    @Test
    public void testPluralRule5() {
        forms = generator.generateForms("1", "porta-chesa");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "porta-chesa");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "portas-chesa");
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
