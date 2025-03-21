package ch.pledarigrond.inflection.verb.rumantschgrischun;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.noun.rumantschgrischun.RumantschGrischunNounGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RumantschGrischunNounGuessingTest {

    private static RumantschGrischunNounGenerator generator;
    private InflectionDto forms;

    @BeforeAll
    public static void initialize() {
        generator = new RumantschGrischunNounGenerator();
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
        forms = generator.guessInflection("deputà", null, null);
        assertEquals(forms.getInflectionSubtype(), "7");
        assertEquals(forms.getNoun().getMSingular(), "deputà");
        assertEquals(forms.getNoun().getFSingular(), "deputada");
        assertEquals(forms.getNoun().getMPlural(), "deputads");
        assertEquals(forms.getNoun().getFPlural(), "deputadas");
    }

    @Test
    public void testRule8() {
        forms = generator.guessInflection("nar", null, null);
        assertEquals(forms.getInflectionSubtype(), "8");
        assertEquals(forms.getNoun().getMSingular(), "nar");
        assertEquals(forms.getNoun().getFSingular(), "narra");
        assertEquals(forms.getNoun().getMPlural(), "nars");
        assertEquals(forms.getNoun().getFPlural(), "narras");
    }

    @Test
    public void testBaseFormWithWhitespace() {
        forms = generator.guessInflection("isch chasa", null, null);
        assertEquals(forms, null);
    }

    @Test
    public void testNormalizeBaseForm() {
        forms = generator.guessInflection("  nar         ", null, null);
        assertEquals(forms.getInflectionSubtype(), "8");
        assertEquals(forms.getNoun().getMSingular(), "nar");
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
        forms = generator.guessInflection("delegà, -ada", null, null);
        assertEquals(forms.getInflectionSubtype(), "7");
    }
}
