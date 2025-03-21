package ch.pledarigrond.inflection.verb.rumantschgrischun;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.noun.rumantschgrischun.RumantschGrischunNounGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RumantschGrischunNounGeneratorTest {

    private static RumantschGrischunNounGenerator generator;
    private InflectionDto forms;

    @BeforeAll
    public static void initialize() {
        generator = new RumantschGrischunNounGenerator();
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
        forms = generator.generateForms("7", "deputà");
        assertEquals(forms.getNoun().getMSingular(), "deputà");
        assertEquals(forms.getNoun().getFSingular(), "deputada");
        assertEquals(forms.getNoun().getMPlural(), "deputads");
        assertEquals(forms.getNoun().getFPlural(), "deputadas");
    }

    @Test
    public void testRule8() {
        forms = generator.generateForms("8", "nar");
        assertEquals(forms.getNoun().getMSingular(), "nar");
        assertEquals(forms.getNoun().getFSingular(), "narra");
        assertEquals(forms.getNoun().getMPlural(), "nars");
        assertEquals(forms.getNoun().getFPlural(), "narras");
    }

    @Test
    public void testPluralRule1() {
        forms = generator.generateForms("1", "cudisch");
        assertEquals(forms.getNoun().getMSingular(), "cudisch");
        assertEquals(forms.getNoun().getMPlural(), "cudischs");

        forms = generator.generateForms("2", "dunna");
        assertEquals(forms.getNoun().getFSingular(), "dunna");
        assertEquals(forms.getNoun().getFPlural(), "dunnas");

        forms = generator.generateForms("1", "canapé");
        assertEquals(forms.getNoun().getMSingular(), "canapé");
        assertEquals(forms.getNoun().getMPlural(), "canapés");
    }

    @Test
    public void testPluralRule2() {
        forms = generator.generateForms("1", "cas");
        assertEquals(forms.getNoun().getMSingular(), "cas");
        assertEquals(forms.getNoun().getMPlural(), "cas");

        forms = generator.generateForms("1", "nas");
        assertEquals(forms.getNoun().getMSingular(), "nas");
        assertEquals(forms.getNoun().getMPlural(), "nas");
    }

    @Test
    public void testPluralRule3() {
        forms = generator.generateForms("1", "isch-chasa");
        assertEquals(forms.getNoun().getMSingular(), "isch-chasa");
        assertEquals(forms.getNoun().getMPlural(), "ischs-chasa");
    }

    @Test
    public void testPluralRule4() {
        forms = generator.generateForms("7", "deputà");
        assertEquals(forms.getNoun().getMSingular(), "deputà");
        assertEquals(forms.getNoun().getFSingular(), "deputada");
        assertEquals(forms.getNoun().getMPlural(), "deputads");
        assertEquals(forms.getNoun().getFPlural(), "deputadas");
    }

    @Test
    public void testPluralRule5() {
        forms = generator.generateForms("1", "anè");
        assertEquals(forms.getNoun().getMSingular(), "anè");
        assertEquals(forms.getNoun().getMPlural(), "anels");
    }

    @Test
    public void testPluralRule6() {
        forms = generator.generateForms("1", "vestgì");
        assertEquals(forms.getNoun().getMSingular(), "vestgì");
        assertEquals(forms.getNoun().getMPlural(), "vestgids");
    }
}
