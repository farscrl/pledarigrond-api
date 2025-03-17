package ch.pledarigrond.inflection.verb.vallader;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.noun.vallader.ValladerNounGenerator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ValladerNounGeneratorTest {

    private static ValladerNounGenerator generator;
    private InflectionDto forms;

    @BeforeClass
    public static void initialize() {
        generator = new ValladerNounGenerator();
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
        forms = generator.generateForms("2", "palü");
        Assert.assertEquals(forms.getNoun().getMSingular(), null);
        Assert.assertEquals(forms.getNoun().getFSingular(), "palü");
        Assert.assertEquals(forms.getNoun().getMPlural(), null);
        Assert.assertEquals(forms.getNoun().getFPlural(), "palüts");
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
        forms = generator.generateForms("4", "manader");
        Assert.assertEquals(forms.getNoun().getMSingular(), "manader");
        Assert.assertEquals(forms.getNoun().getFSingular(), "manadra");
        Assert.assertEquals(forms.getNoun().getMPlural(), "manaders");
        Assert.assertEquals(forms.getNoun().getFPlural(), "manadras");
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
        forms = generator.generateForms("10", "deputà");
        Assert.assertEquals(forms.getNoun().getMSingular(), "deputà");
        Assert.assertEquals(forms.getNoun().getFSingular(), "deputada");
        Assert.assertEquals(forms.getNoun().getMPlural(), "deputats");
        Assert.assertEquals(forms.getNoun().getFPlural(), "deputadas");
    }

    @Test
    public void testRule11() {
        forms = generator.generateForms("11", "vendü");
        Assert.assertEquals(forms.getNoun().getMSingular(), "vendü");
        Assert.assertEquals(forms.getNoun().getFSingular(), "vendüda");
        Assert.assertEquals(forms.getNoun().getMPlural(), "vendüts");
        Assert.assertEquals(forms.getNoun().getFPlural(), "vendüdas");
    }

    @Test
    public void testRule12() {
        forms = generator.generateForms("12", "impedi");
        Assert.assertEquals(forms.getNoun().getMSingular(), "impedi");
        Assert.assertEquals(forms.getNoun().getFSingular(), "impedida");
        Assert.assertEquals(forms.getNoun().getMPlural(), "impedits");
        Assert.assertEquals(forms.getNoun().getFPlural(), "impedidas");
    }

    @Test
    public void testRule13() {
        forms = generator.generateForms("13", "besch");
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

        forms = generator.generateForms("1", "nas");
        Assert.assertEquals(forms.getNoun().getMSingular(), "nas");
        Assert.assertEquals(forms.getNoun().getMPlural(), "nas");
    }

    @Test
    public void testPluralRule3() {
        forms = generator.generateForms("2", "sudà");
        Assert.assertEquals(forms.getNoun().getFSingular(), "sudà");
        Assert.assertEquals(forms.getNoun().getFPlural(), "sudats");
    }

    @Test
    public void testPluralRule4() {
        forms = generator.generateForms("1", "curtè");
        Assert.assertEquals(forms.getNoun().getMSingular(), "curtè");
        Assert.assertEquals(forms.getNoun().getMPlural(), "curtels");
    }

    @Test
    public void testPluralRule5() {
        forms = generator.generateForms("2", "vesti");
        Assert.assertEquals(forms.getNoun().getFSingular(), "vesti");
        Assert.assertEquals(forms.getNoun().getFPlural(), "vestits");
    }

    @Test
    public void testPluralRule6() {
        forms = generator.generateForms("1", "creschü");
        Assert.assertEquals(forms.getNoun().getMSingular(), "creschü");
        Assert.assertEquals(forms.getNoun().getMPlural(), "creschüts");
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
