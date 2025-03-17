package ch.pledarigrond.inflection.verb.rumantschgrischun;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.noun.rumantschgrischun.RumantschGrischunNounGenerator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class RumantschGrischunNounGuessingTest {

    private static RumantschGrischunNounGenerator generator;
    private InflectionDto forms;

    @BeforeClass
    public static void initialize() {
        generator = new RumantschGrischunNounGenerator();
    }

    @Test
    public void testRule3() {
        forms = generator.guessInflection("scolast", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "3");
        Assert.assertEquals(forms.getNoun().getMSingular(), "scolast");
        Assert.assertEquals(forms.getNoun().getFSingular(), "scolasta");
        Assert.assertEquals(forms.getNoun().getMPlural(), "scolasts");
        Assert.assertEquals(forms.getNoun().getFPlural(), "scolastas");
    }

    @Test
    public void testRule4() {
        forms = generator.guessInflection("manader",null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "4");
        Assert.assertEquals(forms.getNoun().getMSingular(), "manader");
        Assert.assertEquals(forms.getNoun().getFSingular(), "manadra");
        Assert.assertEquals(forms.getNoun().getMPlural(), "manaders");
        Assert.assertEquals(forms.getNoun().getFPlural(), "manadras");
    }

    @Test
    public void testRule5() {
        forms = generator.guessInflection("responsabel", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "5");
        Assert.assertEquals(forms.getNoun().getMSingular(), "responsabel");
        Assert.assertEquals(forms.getNoun().getFSingular(), "responsabla");
        Assert.assertEquals(forms.getNoun().getMPlural(), "responsabels");
        Assert.assertEquals(forms.getNoun().getFPlural(), "responsablas");
    }

    @Test
    public void testRule6() {
        forms = generator.guessInflection("apotecher", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "6");
        Assert.assertEquals(forms.getNoun().getMSingular(), "apotecher");
        Assert.assertEquals(forms.getNoun().getFSingular(), "apotecra");
        Assert.assertEquals(forms.getNoun().getMPlural(), "apotechers");
        Assert.assertEquals(forms.getNoun().getFPlural(), "apotecras");
    }

    @Test
    public void testRule7() {
        forms = generator.guessInflection("deputà", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "7");
        Assert.assertEquals(forms.getNoun().getMSingular(), "deputà");
        Assert.assertEquals(forms.getNoun().getFSingular(), "deputada");
        Assert.assertEquals(forms.getNoun().getMPlural(), "deputads");
        Assert.assertEquals(forms.getNoun().getFPlural(), "deputadas");
    }

    @Test
    public void testRule8() {
        forms = generator.guessInflection("nar", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "8");
        Assert.assertEquals(forms.getNoun().getMSingular(), "nar");
        Assert.assertEquals(forms.getNoun().getFSingular(), "narra");
        Assert.assertEquals(forms.getNoun().getMPlural(), "nars");
        Assert.assertEquals(forms.getNoun().getFPlural(), "narras");
    }

    @Test
    public void testBaseFormWithWhitespace() {
        forms = generator.guessInflection("isch chasa", null, null);
        Assert.assertEquals(forms, null);
    }

    @Test
    public void testNormalizeBaseForm() {
        forms = generator.guessInflection("  nar         ", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "8");
        Assert.assertEquals(forms.getNoun().getMSingular(), "nar");
    }

    @Test
    public void testWithEndingIndicationA() {
        forms = generator.guessInflection("client, -a", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "3");
    }

    @Test
    public void testWithEndingIndicationDra() {
        forms = generator.guessInflection("cumprader, -dra", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "4");
    }

    @Test
    public void testWithEndingIndicationBla() {
        forms = generator.guessInflection("responsabel, -bla", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "5");
    }

    @Test
    public void testWithEndingIndicationCra() {
        forms = generator.guessInflection("scepticher, -cra", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "6");
    }

    @Test
    public void testWithEndingIndicationVla() {
        forms = generator.guessInflection("delegà, -ada", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "7");
    }
}
