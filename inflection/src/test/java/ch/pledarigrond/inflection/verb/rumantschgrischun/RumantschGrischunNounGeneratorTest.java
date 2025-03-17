package ch.pledarigrond.inflection.verb.rumantschgrischun;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.noun.rumantschgrischun.RumantschGrischunNounGenerator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class RumantschGrischunNounGeneratorTest {

    private static RumantschGrischunNounGenerator generator;
    private InflectionDto forms;

    @BeforeClass
    public static void initialize() {
        generator = new RumantschGrischunNounGenerator();
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("3", "scolast");
        Assert.assertEquals(forms.getNoun().getMSingular(), "scolast");
        Assert.assertEquals(forms.getNoun().getFSingular(), "scolasta");
        Assert.assertEquals(forms.getNoun().getMPlural(), "scolasts");
        Assert.assertEquals(forms.getNoun().getFPlural(), "scolastas");
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
        forms = generator.generateForms("5", "responsabel");
        Assert.assertEquals(forms.getNoun().getMSingular(), "responsabel");
        Assert.assertEquals(forms.getNoun().getFSingular(), "responsabla");
        Assert.assertEquals(forms.getNoun().getMPlural(), "responsabels");
        Assert.assertEquals(forms.getNoun().getFPlural(), "responsablas");
    }

    @Test
    public void testRule6() {
        forms = generator.generateForms("6", "apotecher");
        Assert.assertEquals(forms.getNoun().getMSingular(), "apotecher");
        Assert.assertEquals(forms.getNoun().getFSingular(), "apotecra");
        Assert.assertEquals(forms.getNoun().getMPlural(), "apotechers");
        Assert.assertEquals(forms.getNoun().getFPlural(), "apotecras");
    }

    @Test
    public void testRule7() {
        forms = generator.generateForms("7", "deputà");
        Assert.assertEquals(forms.getNoun().getMSingular(), "deputà");
        Assert.assertEquals(forms.getNoun().getFSingular(), "deputada");
        Assert.assertEquals(forms.getNoun().getMPlural(), "deputads");
        Assert.assertEquals(forms.getNoun().getFPlural(), "deputadas");
    }

    @Test
    public void testRule8() {
        forms = generator.generateForms("8", "nar");
        Assert.assertEquals(forms.getNoun().getMSingular(), "nar");
        Assert.assertEquals(forms.getNoun().getFSingular(), "narra");
        Assert.assertEquals(forms.getNoun().getMPlural(), "nars");
        Assert.assertEquals(forms.getNoun().getFPlural(), "narras");
    }

    @Test
    public void testPluralRule1() {
        forms = generator.generateForms("1", "cudisch");
        Assert.assertEquals(forms.getNoun().getMSingular(), "cudisch");
        Assert.assertEquals(forms.getNoun().getMPlural(), "cudischs");

        forms = generator.generateForms("2", "dunna");
        Assert.assertEquals(forms.getNoun().getFSingular(), "dunna");
        Assert.assertEquals(forms.getNoun().getFPlural(), "dunnas");

        forms = generator.generateForms("1", "canapé");
        Assert.assertEquals(forms.getNoun().getMSingular(), "canapé");
        Assert.assertEquals(forms.getNoun().getMPlural(), "canapés");
    }

    @Test
    public void testPluralRule2() {
        forms = generator.generateForms("1", "cas");
        Assert.assertEquals(forms.getNoun().getMSingular(), "cas");
        Assert.assertEquals(forms.getNoun().getMPlural(), "cas");

        forms = generator.generateForms("1", "nas");
        Assert.assertEquals(forms.getNoun().getMSingular(), "nas");
        Assert.assertEquals(forms.getNoun().getMPlural(), "nas");
    }

    @Test
    public void testPluralRule3() {
        forms = generator.generateForms("1", "isch-chasa");
        Assert.assertEquals(forms.getNoun().getMSingular(), "isch-chasa");
        Assert.assertEquals(forms.getNoun().getMPlural(), "ischs-chasa");
    }

    @Test
    public void testPluralRule4() {
        forms = generator.generateForms("7", "deputà");
        Assert.assertEquals(forms.getNoun().getMSingular(), "deputà");
        Assert.assertEquals(forms.getNoun().getFSingular(), "deputada");
        Assert.assertEquals(forms.getNoun().getMPlural(), "deputads");
        Assert.assertEquals(forms.getNoun().getFPlural(), "deputadas");
    }

    @Test
    public void testPluralRule5() {
        forms = generator.generateForms("1", "anè");
        Assert.assertEquals(forms.getNoun().getMSingular(), "anè");
        Assert.assertEquals(forms.getNoun().getMPlural(), "anels");
    }

    @Test
    public void testPluralRule6() {
        forms = generator.generateForms("1", "vestgì");
        Assert.assertEquals(forms.getNoun().getMSingular(), "vestgì");
        Assert.assertEquals(forms.getNoun().getMPlural(), "vestgids");
    }
}
