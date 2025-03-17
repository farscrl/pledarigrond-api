package ch.pledarigrond.inflection.verb.surmiran;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.noun.surmiran.SurmiranNounGenerator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class SurmiranNounGeneratorTest {

    private static SurmiranNounGenerator generator;
    private InflectionDto forms;

    @BeforeClass
    public static void initialize() {
        generator = new SurmiranNounGenerator();
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
        forms = generator.generateForms("7", "flevel");
        Assert.assertEquals(forms.getNoun().getMSingular(), "flevel");
        Assert.assertEquals(forms.getNoun().getFSingular(), "flevla");
        Assert.assertEquals(forms.getNoun().getMPlural(), "flevels");
        Assert.assertEquals(forms.getNoun().getFPlural(), "flevlas");
    }

    @Test
    public void testRule8() {
        forms = generator.generateForms("8", "gioven");
        Assert.assertEquals(forms.getNoun().getMSingular(), "gioven");
        Assert.assertEquals(forms.getNoun().getFSingular(), "giovna");
        Assert.assertEquals(forms.getNoun().getMPlural(), "giovens");
        Assert.assertEquals(forms.getNoun().getFPlural(), "giovnas");
    }

    @Test
    public void testRule9() {
        forms = generator.generateForms("9", "ampluia");
        Assert.assertEquals(forms.getNoun().getMSingular(), "ampluia");
        Assert.assertEquals(forms.getNoun().getFSingular(), "amplueida");
        Assert.assertEquals(forms.getNoun().getMPlural(), "ampluias");
        Assert.assertEquals(forms.getNoun().getFPlural(), "amplueidas");
    }

    @Test
    public void testRule10() {
        forms = generator.generateForms("10", "anvido");
        Assert.assertEquals(forms.getNoun().getMSingular(), "anvido");
        Assert.assertEquals(forms.getNoun().getFSingular(), "anvidada");
        Assert.assertEquals(forms.getNoun().getMPlural(), "anvidos");
        Assert.assertEquals(forms.getNoun().getFPlural(), "anvidadas");
    }

    @Test
    public void testRule11() {
        forms = generator.generateForms("11", "relaschea");
        Assert.assertEquals(forms.getNoun().getMSingular(), "relaschea");
        Assert.assertEquals(forms.getNoun().getFSingular(), "relascheda");
        Assert.assertEquals(forms.getNoun().getMPlural(), "relascheas");
        Assert.assertEquals(forms.getNoun().getFPlural(), "relaschedas");
    }

    @Test
    public void testRule12() {
        forms = generator.generateForms("12", "minoren");
        Assert.assertEquals(forms.getNoun().getMSingular(), "minoren");
        Assert.assertEquals(forms.getNoun().getFSingular(), "minorenna");
        Assert.assertEquals(forms.getNoun().getMPlural(), "minorens");
        Assert.assertEquals(forms.getNoun().getFPlural(), "minorennas");
    }

    @Test
    public void testRule13() {
        forms = generator.generateForms("13", "peir");
        Assert.assertEquals(forms.getNoun().getMSingular(), "peir");
        Assert.assertEquals(forms.getNoun().getFSingular(), null);
        Assert.assertEquals(forms.getNoun().getMPlural(), "peirs");
        Assert.assertEquals(forms.getNoun().getFPlural(), null);
        Assert.assertEquals(forms.getNoun().getPluralCollectiv(), "peira");
    }

    @Test
    public void testPluralRule2() {
        forms = generator.generateForms("1", "cass");
        Assert.assertEquals(forms.getNoun().getMSingular(), "cass");
        Assert.assertEquals(forms.getNoun().getMPlural(), "cass");
    }

    @Test
    public void testPluralRule3() {
        forms = generator.generateForms("1", "isch-tgesa");
        Assert.assertEquals(forms.getNoun().getMSingular(), "isch-tgesa");
        Assert.assertEquals(forms.getNoun().getMPlural(), "ischs-tgesa");
    }

    @Test
    public void testPluralRule4() {
        forms = generator.generateForms("2", "pale");
        Assert.assertEquals(forms.getNoun().getFSingular(), "pale");
        Assert.assertEquals(forms.getNoun().getFPlural(), "paleis");
    }

    @Test
    public void testPluralRule5() {
        forms = generator.generateForms("2", "pare");
        Assert.assertEquals(forms.getNoun().getFSingular(), "pare");
        Assert.assertEquals(forms.getNoun().getFPlural(), "pareis");
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
