package ch.pledarigrond.inflection.verb.surmiran;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.noun.surmiran.SurmiranNounGenerator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class SurmiranNounGuessingTest {

    private static SurmiranNounGenerator generator;
    private InflectionDto forms;

    @BeforeClass
    public static void initialize() {
        generator = new SurmiranNounGenerator();
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
        forms = generator.guessInflection("flevel", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "7");
        Assert.assertEquals(forms.getNoun().getMSingular(), "flevel");
        Assert.assertEquals(forms.getNoun().getFSingular(), "flevla");
        Assert.assertEquals(forms.getNoun().getMPlural(), "flevels");
        Assert.assertEquals(forms.getNoun().getFPlural(), "flevlas");
    }

    @Test
    public void testRule8() {
        forms = generator.guessInflection("gioven", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "8");
        Assert.assertEquals(forms.getNoun().getMSingular(), "gioven");
        Assert.assertEquals(forms.getNoun().getFSingular(), "giovna");
        Assert.assertEquals(forms.getNoun().getMPlural(), "giovens");
        Assert.assertEquals(forms.getNoun().getFPlural(), "giovnas");
    }

    @Test
    public void testRule9() {
        forms = generator.guessInflection("ampluia", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "9");
        Assert.assertEquals(forms.getNoun().getMSingular(), "ampluia");
        Assert.assertEquals(forms.getNoun().getFSingular(), "amplueida");
        Assert.assertEquals(forms.getNoun().getMPlural(), "ampluias");
        Assert.assertEquals(forms.getNoun().getFPlural(), "amplueidas");
    }

    @Test
    public void testRule10() {
        forms = generator.guessInflection("anvido", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "10");
        Assert.assertEquals(forms.getNoun().getMSingular(), "anvido");
        Assert.assertEquals(forms.getNoun().getFSingular(), "anvidada");
        Assert.assertEquals(forms.getNoun().getMPlural(), "anvidos");
        Assert.assertEquals(forms.getNoun().getFPlural(), "anvidadas");
    }

    @Test
    public void testRule11() {
        forms = generator.guessInflection("relaschea", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "11");
        Assert.assertEquals(forms.getNoun().getMSingular(), "relaschea");
        Assert.assertEquals(forms.getNoun().getFSingular(), "relascheda");
        Assert.assertEquals(forms.getNoun().getMPlural(), "relascheas");
        Assert.assertEquals(forms.getNoun().getFPlural(), "relaschedas");
    }

    @Test
    public void testRule12() {
        forms = generator.guessInflection("minoren", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "12");
        Assert.assertEquals(forms.getNoun().getMSingular(), "minoren");
        Assert.assertEquals(forms.getNoun().getFSingular(), "minorenna");
        Assert.assertEquals(forms.getNoun().getMPlural(), "minorens");
        Assert.assertEquals(forms.getNoun().getFPlural(), "minorennas");
    }

    @Test
    public void testPluralRule4() {
        forms = generator.guessInflection("pale", "f", null);
        Assert.assertEquals(forms.getInflectionSubtype(), "2");
        Assert.assertEquals(forms.getNoun().getFSingular(), "pale");
        Assert.assertEquals(forms.getNoun().getFPlural(), "paleis");
    }

    @Test
    public void testBaseFormWithWhitespace() {
        forms = generator.guessInflection("isch tga", null, null);
        Assert.assertEquals(forms, null);
    }

    @Test
    public void testNormalizeBaseForm() {
        forms = generator.guessInflection("  minoren         ", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "12");
        Assert.assertEquals(forms.getNoun().getMSingular(), "minoren");
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
        forms = generator.guessInflection("raschunevel, -vla", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "7");
    }

    @Test
    public void testWithEndingIndicationVna() {
        forms = generator.guessInflection("gioven, -vna", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "8");
    }

    @Test
    public void testWithEndingIndicationEida() {
        forms = generator.guessInflection("crucifitgia, -eida", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "9");
    }

    @Test
    public void testWithEndingIndicationAda() {
        forms = generator.guessInflection("scienzio, -ada", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "10");
    }

    @Test
    public void testWithEndingIndicationEda() {
        forms = generator.guessInflection("donnagea, -eda", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "11");
    }
}
