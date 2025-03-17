package ch.pledarigrond.inflection.verb.puter;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.noun.puter.PuterNounGenerator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class PuterNounGeneratorTest {

    private static PuterNounGenerator generator;
    private InflectionDto forms;

    @BeforeClass
    public static void initialize() {
        generator = new PuterNounGenerator();
    }

    @Test
    public void testRule1() {
        forms = generator.generateForms("1", "cas");
        Assert.assertEquals(forms.getNoun().getMSingular(), "cas");
        Assert.assertEquals(forms.getNoun().getFSingular(), null);
        Assert.assertEquals(forms.getNoun().getMPlural(), "cas");
        Assert.assertEquals(forms.getNoun().getFPlural(), null);
    }

    @Test
    public void testRule2() {
        forms = generator.generateForms("2", "palüd");
        Assert.assertEquals(forms.getNoun().getMSingular(), null);
        Assert.assertEquals(forms.getNoun().getFSingular(), "palüd");
        Assert.assertEquals(forms.getNoun().getMPlural(), null);
        Assert.assertEquals(forms.getNoun().getFPlural(), "palüds");
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("3", "scolar");
        Assert.assertEquals(forms.getNoun().getMSingular(), "scolar");
        Assert.assertEquals(forms.getNoun().getFSingular(), "scolara");
        Assert.assertEquals(forms.getNoun().getMPlural(), "scolars");
        Assert.assertEquals(forms.getNoun().getFPlural(), "scolaras");
    }

    @Test
    public void testRule4() {
        forms = generator.generateForms("4", "mneder");
        Assert.assertEquals(forms.getNoun().getMSingular(), "mneder");
        Assert.assertEquals(forms.getNoun().getFSingular(), "mnedra");
        Assert.assertEquals(forms.getNoun().getMPlural(), "mneders");
        Assert.assertEquals(forms.getNoun().getFPlural(), "mnedras");
    }

    @Test
    public void testRule5() {
        forms = generator.generateForms("5", "respunsabel");
        Assert.assertEquals(forms.getNoun().getMSingular(), "respunsabel");
        Assert.assertEquals(forms.getNoun().getFSingular(), "respunsabla");
        Assert.assertEquals(forms.getNoun().getMPlural(), "respunsabels");
        Assert.assertEquals(forms.getNoun().getFPlural(), "respunsablas");
    }

    @Test
    public void testRule6() {
        forms = generator.generateForms("6", "crettaivel");
        Assert.assertEquals(forms.getNoun().getMSingular(), "crettaivel");
        Assert.assertEquals(forms.getNoun().getFSingular(), "crettaivla");
        Assert.assertEquals(forms.getNoun().getMPlural(), "crettaivels");
        Assert.assertEquals(forms.getNoun().getFPlural(), "crettaivlas");
    }

    @Test
    public void testRule7() {
        forms = generator.generateForms("7", "apoteker");
        Assert.assertEquals(forms.getNoun().getMSingular(), "apoteker");
        Assert.assertEquals(forms.getNoun().getFSingular(), "apotecra");
        Assert.assertEquals(forms.getNoun().getMPlural(), "apotekers");
        Assert.assertEquals(forms.getNoun().getFPlural(), "apotecras");
    }

    @Test
    public void testRule8() {
        forms = generator.generateForms("8", "giuven");
        Assert.assertEquals(forms.getNoun().getMSingular(), "giuven");
        Assert.assertEquals(forms.getNoun().getFSingular(), "giuvna");
        Assert.assertEquals(forms.getNoun().getMPlural(), "giuvens");
        Assert.assertEquals(forms.getNoun().getFPlural(), "giuvnas");
    }

    @Test
    public void testRule9() {
        forms = generator.generateForms("9", "pover");
        Assert.assertEquals(forms.getNoun().getMSingular(), "pover");
        Assert.assertEquals(forms.getNoun().getFSingular(), "povra");
        Assert.assertEquals(forms.getNoun().getMPlural(), "povers");
        Assert.assertEquals(forms.getNoun().getFPlural(), "povras");
    }

    @Test
    public void testRule10() {
        forms = generator.generateForms("10", "depüto");
        Assert.assertEquals(forms.getNoun().getMSingular(), "depüto");
        Assert.assertEquals(forms.getNoun().getFSingular(), "depüteda");
        Assert.assertEquals(forms.getNoun().getMPlural(), "depütos");
        Assert.assertEquals(forms.getNoun().getFPlural(), "depütedas");
    }

    @Test
    public void testRule11() {
        forms = generator.generateForms("11", "impiego");
        Assert.assertEquals(forms.getNoun().getMSingular(), "impiego");
        Assert.assertEquals(forms.getNoun().getFSingular(), "impiegheda");
        Assert.assertEquals(forms.getNoun().getMPlural(), "impiegos");
        Assert.assertEquals(forms.getNoun().getFPlural(), "impieghedas");
    }

    @Test
    public void testRule12() {
        forms = generator.generateForms("12", "piglio");
        Assert.assertEquals(forms.getNoun().getMSingular(), "piglio");
        Assert.assertEquals(forms.getNoun().getFSingular(), "piglieda");
        Assert.assertEquals(forms.getNoun().getMPlural(), "piglios");
        Assert.assertEquals(forms.getNoun().getFPlural(), "pigliedas");
    }

    @Test
    public void testRule13() {
        forms = generator.generateForms("13", "impedieu");
        Assert.assertEquals(forms.getNoun().getMSingular(), "impedieu");
        Assert.assertEquals(forms.getNoun().getFSingular(), "impedida");
        Assert.assertEquals(forms.getNoun().getMPlural(), "impedieus");
        Assert.assertEquals(forms.getNoun().getFPlural(), "impedidas");
    }

    @Test
    public void testRule14() {
        forms = generator.generateForms("14", "assaglieu");
        Assert.assertEquals(forms.getNoun().getMSingular(), "assaglieu");
        Assert.assertEquals(forms.getNoun().getFSingular(), "assaglida");
        Assert.assertEquals(forms.getNoun().getMPlural(), "assaglieus");
        Assert.assertEquals(forms.getNoun().getFPlural(), "assaglidas");
    }

    @Test
    public void testRule15() {
        forms = generator.generateForms("15", "besch");
        Assert.assertEquals(forms.getNoun().getMSingular(), "besch");
        Assert.assertEquals(forms.getNoun().getFSingular(), null);
        Assert.assertEquals(forms.getNoun().getMPlural(), "beschs");
        Assert.assertEquals(forms.getNoun().getFPlural(), null);
        Assert.assertEquals(forms.getNoun().getPluralCollectiv(), "bescha");
    }

    @Test
    public void testPluralRule2() {
        forms = generator.generateForms("1", "pass");
        Assert.assertEquals(forms.getNoun().getMSingular(), "pass");
        Assert.assertEquals(forms.getNoun().getMPlural(), "pass");

        forms = generator.generateForms("1", "nes");
        Assert.assertEquals(forms.getNoun().getMSingular(), "nes");
        Assert.assertEquals(forms.getNoun().getMPlural(), "nes");
    }

    @Test
    public void testPluralRule3() {
        forms = generator.generateForms("1", "curtè");
        Assert.assertEquals(forms.getNoun().getMSingular(), "curtè");
        Assert.assertEquals(forms.getNoun().getMPlural(), "curtels");
    }

    @Test
    public void testPluralRule4() {
        forms = generator.generateForms("2", "chalur");
        Assert.assertEquals(forms.getNoun().getFSingular(), "chalur");
        Assert.assertEquals(forms.getNoun().getFPlural(), "chaluors");
    }

    @Test
    public void testUpperCase() {
        forms = generator.generateForms("3", "Argoves");
        Assert.assertEquals(forms.getNoun().getMSingular(), "Argoves");
        Assert.assertEquals(forms.getNoun().getFSingular(), "Argovesa");
        Assert.assertEquals(forms.getNoun().getMPlural(), "Argoves");
        Assert.assertEquals(forms.getNoun().getFPlural(), "Argovesas");
    }
}
