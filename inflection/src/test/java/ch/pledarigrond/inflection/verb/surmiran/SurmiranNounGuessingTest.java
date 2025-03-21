package ch.pledarigrond.inflection.verb.surmiran;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.noun.surmiran.SurmiranNounGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SurmiranNounGuessingTest {

    private static SurmiranNounGenerator generator;
    private InflectionDto forms;

    @BeforeAll
    public static void initialize() {
        generator = new SurmiranNounGenerator();
    }

    @Test
    public void testRule3() {
        forms = generator.guessInflection("scolast", null, null);
        assertEquals(forms.getInflectionSubtype(), "3");
        assertEquals(forms.getNoun().getMSingular(), "scolast");
        assertEquals(forms.getNoun().getFSingular(), "scolasta");
        assertEquals(forms.getNoun().getMPlural(), "scolasts");
        assertEquals(forms.getNoun().getFPlural(), "scolastas");
    }

    @Test
    public void testRule4() {
        forms = generator.guessInflection("manader",null, null);
        assertEquals(forms.getInflectionSubtype(), "4");
        assertEquals(forms.getNoun().getMSingular(), "manader");
        assertEquals(forms.getNoun().getFSingular(), "manadra");
        assertEquals(forms.getNoun().getMPlural(), "manaders");
        assertEquals(forms.getNoun().getFPlural(), "manadras");
    }

    @Test
    public void testRule5() {
        forms = generator.guessInflection("responsabel", null, null);
        assertEquals(forms.getInflectionSubtype(), "5");
        assertEquals(forms.getNoun().getMSingular(), "responsabel");
        assertEquals(forms.getNoun().getFSingular(), "responsabla");
        assertEquals(forms.getNoun().getMPlural(), "responsabels");
        assertEquals(forms.getNoun().getFPlural(), "responsablas");
    }

    @Test
    public void testRule6() {
        forms = generator.guessInflection("apotecher", null, null);
        assertEquals(forms.getInflectionSubtype(), "6");
        assertEquals(forms.getNoun().getMSingular(), "apotecher");
        assertEquals(forms.getNoun().getFSingular(), "apotecra");
        assertEquals(forms.getNoun().getMPlural(), "apotechers");
        assertEquals(forms.getNoun().getFPlural(), "apotecras");
    }

    @Test
    public void testRule7() {
        forms = generator.guessInflection("flevel", null, null);
        assertEquals(forms.getInflectionSubtype(), "7");
        assertEquals(forms.getNoun().getMSingular(), "flevel");
        assertEquals(forms.getNoun().getFSingular(), "flevla");
        assertEquals(forms.getNoun().getMPlural(), "flevels");
        assertEquals(forms.getNoun().getFPlural(), "flevlas");
    }

    @Test
    public void testRule8() {
        forms = generator.guessInflection("gioven", null, null);
        assertEquals(forms.getInflectionSubtype(), "8");
        assertEquals(forms.getNoun().getMSingular(), "gioven");
        assertEquals(forms.getNoun().getFSingular(), "giovna");
        assertEquals(forms.getNoun().getMPlural(), "giovens");
        assertEquals(forms.getNoun().getFPlural(), "giovnas");
    }

    @Test
    public void testRule9() {
        forms = generator.guessInflection("ampluia", null, null);
        assertEquals(forms.getInflectionSubtype(), "9");
        assertEquals(forms.getNoun().getMSingular(), "ampluia");
        assertEquals(forms.getNoun().getFSingular(), "amplueida");
        assertEquals(forms.getNoun().getMPlural(), "ampluias");
        assertEquals(forms.getNoun().getFPlural(), "amplueidas");
    }

    @Test
    public void testRule10() {
        forms = generator.guessInflection("anvido", null, null);
        assertEquals(forms.getInflectionSubtype(), "10");
        assertEquals(forms.getNoun().getMSingular(), "anvido");
        assertEquals(forms.getNoun().getFSingular(), "anvidada");
        assertEquals(forms.getNoun().getMPlural(), "anvidos");
        assertEquals(forms.getNoun().getFPlural(), "anvidadas");
    }

    @Test
    public void testRule11() {
        forms = generator.guessInflection("relaschea", null, null);
        assertEquals(forms.getInflectionSubtype(), "11");
        assertEquals(forms.getNoun().getMSingular(), "relaschea");
        assertEquals(forms.getNoun().getFSingular(), "relascheda");
        assertEquals(forms.getNoun().getMPlural(), "relascheas");
        assertEquals(forms.getNoun().getFPlural(), "relaschedas");
    }

    @Test
    public void testRule12() {
        forms = generator.guessInflection("minoren", null, null);
        assertEquals(forms.getInflectionSubtype(), "12");
        assertEquals(forms.getNoun().getMSingular(), "minoren");
        assertEquals(forms.getNoun().getFSingular(), "minorenna");
        assertEquals(forms.getNoun().getMPlural(), "minorens");
        assertEquals(forms.getNoun().getFPlural(), "minorennas");
    }

    @Test
    public void testPluralRule4() {
        forms = generator.guessInflection("pale", "f", null);
        assertEquals(forms.getInflectionSubtype(), "2");
        assertEquals(forms.getNoun().getFSingular(), "pale");
        assertEquals(forms.getNoun().getFPlural(), "paleis");
    }

    @Test
    public void testBaseFormWithWhitespace() {
        forms = generator.guessInflection("isch tga", null, null);
        assertEquals(forms, null);
    }

    @Test
    public void testNormalizeBaseForm() {
        forms = generator.guessInflection("  minoren         ", null, null);
        assertEquals(forms.getInflectionSubtype(), "12");
        assertEquals(forms.getNoun().getMSingular(), "minoren");
    }

    @Test
    public void testWithEndingIndicationA() {
        forms = generator.guessInflection("client, -a", null, null);
        assertEquals(forms.getInflectionSubtype(), "3");
    }

    @Test
    public void testWithEndingIndicationDra() {
        forms = generator.guessInflection("cumprader, -dra", null, null);
        assertEquals(forms.getInflectionSubtype(), "4");
    }

    @Test
    public void testWithEndingIndicationBla() {
        forms = generator.guessInflection("responsabel, -bla", null, null);
        assertEquals(forms.getInflectionSubtype(), "5");
    }

    @Test
    public void testWithEndingIndicationCra() {
        forms = generator.guessInflection("scepticher, -cra", null, null);
        assertEquals(forms.getInflectionSubtype(), "6");
    }

    @Test
    public void testWithEndingIndicationVla() {
        forms = generator.guessInflection("raschunevel, -vla", null, null);
        assertEquals(forms.getInflectionSubtype(), "7");
    }

    @Test
    public void testWithEndingIndicationVna() {
        forms = generator.guessInflection("gioven, -vna", null, null);
        assertEquals(forms.getInflectionSubtype(), "8");
    }

    @Test
    public void testWithEndingIndicationEida() {
        forms = generator.guessInflection("crucifitgia, -eida", null, null);
        assertEquals(forms.getInflectionSubtype(), "9");
    }

    @Test
    public void testWithEndingIndicationAda() {
        forms = generator.guessInflection("scienzio, -ada", null, null);
        assertEquals(forms.getInflectionSubtype(), "10");
    }

    @Test
    public void testWithEndingIndicationEda() {
        forms = generator.guessInflection("donnagea, -eda", null, null);
        assertEquals(forms.getInflectionSubtype(), "11");
    }
}
