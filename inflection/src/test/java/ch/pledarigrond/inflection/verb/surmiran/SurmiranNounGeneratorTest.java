package ch.pledarigrond.inflection.verb.surmiran;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.noun.surmiran.SurmiranNounGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SurmiranNounGeneratorTest {

    private static SurmiranNounGenerator generator;
    private InflectionDto forms;

    @BeforeAll
    public static void initialize() {
        generator = new SurmiranNounGenerator();
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("3", "scolast");
        assertEquals(forms.getNoun().getMSingular(), "scolast");
        assertEquals(forms.getNoun().getFSingular(), "scolasta");
        assertEquals(forms.getNoun().getMPlural(), "scolasts");
        assertEquals(forms.getNoun().getFPlural(), "scolastas");
    }

    @Test
    public void testRule4() {
        forms = generator.generateForms("4", "manader");
        assertEquals(forms.getNoun().getMSingular(), "manader");
        assertEquals(forms.getNoun().getFSingular(), "manadra");
        assertEquals(forms.getNoun().getMPlural(), "manaders");
        assertEquals(forms.getNoun().getFPlural(), "manadras");
    }

    @Test
    public void testRule5() {
        forms = generator.generateForms("5", "responsabel");
        assertEquals(forms.getNoun().getMSingular(), "responsabel");
        assertEquals(forms.getNoun().getFSingular(), "responsabla");
        assertEquals(forms.getNoun().getMPlural(), "responsabels");
        assertEquals(forms.getNoun().getFPlural(), "responsablas");
    }

    @Test
    public void testRule6() {
        forms = generator.generateForms("6", "apotecher");
        assertEquals(forms.getNoun().getMSingular(), "apotecher");
        assertEquals(forms.getNoun().getFSingular(), "apotecra");
        assertEquals(forms.getNoun().getMPlural(), "apotechers");
        assertEquals(forms.getNoun().getFPlural(), "apotecras");
    }

    @Test
    public void testRule7() {
        forms = generator.generateForms("7", "flevel");
        assertEquals(forms.getNoun().getMSingular(), "flevel");
        assertEquals(forms.getNoun().getFSingular(), "flevla");
        assertEquals(forms.getNoun().getMPlural(), "flevels");
        assertEquals(forms.getNoun().getFPlural(), "flevlas");
    }

    @Test
    public void testRule8() {
        forms = generator.generateForms("8", "gioven");
        assertEquals(forms.getNoun().getMSingular(), "gioven");
        assertEquals(forms.getNoun().getFSingular(), "giovna");
        assertEquals(forms.getNoun().getMPlural(), "giovens");
        assertEquals(forms.getNoun().getFPlural(), "giovnas");
    }

    @Test
    public void testRule9() {
        forms = generator.generateForms("9", "ampluia");
        assertEquals(forms.getNoun().getMSingular(), "ampluia");
        assertEquals(forms.getNoun().getFSingular(), "amplueida");
        assertEquals(forms.getNoun().getMPlural(), "ampluias");
        assertEquals(forms.getNoun().getFPlural(), "amplueidas");
    }

    @Test
    public void testRule10() {
        forms = generator.generateForms("10", "anvido");
        assertEquals(forms.getNoun().getMSingular(), "anvido");
        assertEquals(forms.getNoun().getFSingular(), "anvidada");
        assertEquals(forms.getNoun().getMPlural(), "anvidos");
        assertEquals(forms.getNoun().getFPlural(), "anvidadas");
    }

    @Test
    public void testRule11() {
        forms = generator.generateForms("11", "relaschea");
        assertEquals(forms.getNoun().getMSingular(), "relaschea");
        assertEquals(forms.getNoun().getFSingular(), "relascheda");
        assertEquals(forms.getNoun().getMPlural(), "relascheas");
        assertEquals(forms.getNoun().getFPlural(), "relaschedas");
    }

    @Test
    public void testRule12() {
        forms = generator.generateForms("12", "minoren");
        assertEquals(forms.getNoun().getMSingular(), "minoren");
        assertEquals(forms.getNoun().getFSingular(), "minorenna");
        assertEquals(forms.getNoun().getMPlural(), "minorens");
        assertEquals(forms.getNoun().getFPlural(), "minorennas");
    }

    @Test
    public void testRule13() {
        forms = generator.generateForms("13", "peir");
        assertEquals(forms.getNoun().getMSingular(), "peir");
        assertEquals(forms.getNoun().getFSingular(), null);
        assertEquals(forms.getNoun().getMPlural(), "peirs");
        assertEquals(forms.getNoun().getFPlural(), null);
        assertEquals(forms.getNoun().getPluralCollectiv(), "peira");
    }

    @Test
    public void testPluralRule2() {
        forms = generator.generateForms("1", "cass");
        assertEquals(forms.getNoun().getMSingular(), "cass");
        assertEquals(forms.getNoun().getMPlural(), "cass");
    }

    @Test
    public void testPluralRule3() {
        forms = generator.generateForms("1", "isch-tgesa");
        assertEquals(forms.getNoun().getMSingular(), "isch-tgesa");
        assertEquals(forms.getNoun().getMPlural(), "ischs-tgesa");
    }

    @Test
    public void testPluralRule4() {
        forms = generator.generateForms("2", "pale");
        assertEquals(forms.getNoun().getFSingular(), "pale");
        assertEquals(forms.getNoun().getFPlural(), "paleis");
    }

    @Test
    public void testPluralRule5() {
        forms = generator.generateForms("2", "pare");
        assertEquals(forms.getNoun().getFSingular(), "pare");
        assertEquals(forms.getNoun().getFPlural(), "pareis");
    }

    @Test
    public void testUpperCase() {
        forms = generator.generateForms("3", "Argoves");
        assertEquals(forms.getNoun().getMSingular(), "Argoves");
        assertEquals(forms.getNoun().getFSingular(), "Argovesa");
        assertEquals(forms.getNoun().getMPlural(), "Argoves");
        assertEquals(forms.getNoun().getFPlural(), "Argovesas");
    }
}
