package ch.pledarigrond.inflection.verb.puter;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.noun.puter.PuterNounGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PuterNounGeneratorTest {

    private static PuterNounGenerator generator;
    private InflectionDto forms;

    @BeforeAll
    public static void initialize() {
        generator = new PuterNounGenerator();
    }

    @Test
    public void testRule1() {
        forms = generator.generateForms("1", "cas");
        assertEquals(forms.getNoun().getMSingular(), "cas");
        assertEquals(forms.getNoun().getFSingular(), null);
        assertEquals(forms.getNoun().getMPlural(), "cas");
        assertEquals(forms.getNoun().getFPlural(), null);
    }

    @Test
    public void testRule2() {
        forms = generator.generateForms("2", "palüd");
        assertEquals(forms.getNoun().getMSingular(), null);
        assertEquals(forms.getNoun().getFSingular(), "palüd");
        assertEquals(forms.getNoun().getMPlural(), null);
        assertEquals(forms.getNoun().getFPlural(), "palüds");
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("3", "scolar");
        assertEquals(forms.getNoun().getMSingular(), "scolar");
        assertEquals(forms.getNoun().getFSingular(), "scolara");
        assertEquals(forms.getNoun().getMPlural(), "scolars");
        assertEquals(forms.getNoun().getFPlural(), "scolaras");
    }

    @Test
    public void testRule4() {
        forms = generator.generateForms("4", "mneder");
        assertEquals(forms.getNoun().getMSingular(), "mneder");
        assertEquals(forms.getNoun().getFSingular(), "mnedra");
        assertEquals(forms.getNoun().getMPlural(), "mneders");
        assertEquals(forms.getNoun().getFPlural(), "mnedras");
    }

    @Test
    public void testRule5() {
        forms = generator.generateForms("5", "respunsabel");
        assertEquals(forms.getNoun().getMSingular(), "respunsabel");
        assertEquals(forms.getNoun().getFSingular(), "respunsabla");
        assertEquals(forms.getNoun().getMPlural(), "respunsabels");
        assertEquals(forms.getNoun().getFPlural(), "respunsablas");
    }

    @Test
    public void testRule6() {
        forms = generator.generateForms("6", "crettaivel");
        assertEquals(forms.getNoun().getMSingular(), "crettaivel");
        assertEquals(forms.getNoun().getFSingular(), "crettaivla");
        assertEquals(forms.getNoun().getMPlural(), "crettaivels");
        assertEquals(forms.getNoun().getFPlural(), "crettaivlas");
    }

    @Test
    public void testRule7() {
        forms = generator.generateForms("7", "apoteker");
        assertEquals(forms.getNoun().getMSingular(), "apoteker");
        assertEquals(forms.getNoun().getFSingular(), "apotecra");
        assertEquals(forms.getNoun().getMPlural(), "apotekers");
        assertEquals(forms.getNoun().getFPlural(), "apotecras");
    }

    @Test
    public void testRule8() {
        forms = generator.generateForms("8", "giuven");
        assertEquals(forms.getNoun().getMSingular(), "giuven");
        assertEquals(forms.getNoun().getFSingular(), "giuvna");
        assertEquals(forms.getNoun().getMPlural(), "giuvens");
        assertEquals(forms.getNoun().getFPlural(), "giuvnas");
    }

    @Test
    public void testRule9() {
        forms = generator.generateForms("9", "pover");
        assertEquals(forms.getNoun().getMSingular(), "pover");
        assertEquals(forms.getNoun().getFSingular(), "povra");
        assertEquals(forms.getNoun().getMPlural(), "povers");
        assertEquals(forms.getNoun().getFPlural(), "povras");
    }

    @Test
    public void testRule10() {
        forms = generator.generateForms("10", "depüto");
        assertEquals(forms.getNoun().getMSingular(), "depüto");
        assertEquals(forms.getNoun().getFSingular(), "depüteda");
        assertEquals(forms.getNoun().getMPlural(), "depütos");
        assertEquals(forms.getNoun().getFPlural(), "depütedas");
    }

    @Test
    public void testRule11() {
        forms = generator.generateForms("11", "impiego");
        assertEquals(forms.getNoun().getMSingular(), "impiego");
        assertEquals(forms.getNoun().getFSingular(), "impiegheda");
        assertEquals(forms.getNoun().getMPlural(), "impiegos");
        assertEquals(forms.getNoun().getFPlural(), "impieghedas");
    }

    @Test
    public void testRule12() {
        forms = generator.generateForms("12", "piglio");
        assertEquals(forms.getNoun().getMSingular(), "piglio");
        assertEquals(forms.getNoun().getFSingular(), "piglieda");
        assertEquals(forms.getNoun().getMPlural(), "piglios");
        assertEquals(forms.getNoun().getFPlural(), "pigliedas");
    }

    @Test
    public void testRule13() {
        forms = generator.generateForms("13", "impedieu");
        assertEquals(forms.getNoun().getMSingular(), "impedieu");
        assertEquals(forms.getNoun().getFSingular(), "impedida");
        assertEquals(forms.getNoun().getMPlural(), "impedieus");
        assertEquals(forms.getNoun().getFPlural(), "impedidas");
    }

    @Test
    public void testRule14() {
        forms = generator.generateForms("14", "assaglieu");
        assertEquals(forms.getNoun().getMSingular(), "assaglieu");
        assertEquals(forms.getNoun().getFSingular(), "assaglida");
        assertEquals(forms.getNoun().getMPlural(), "assaglieus");
        assertEquals(forms.getNoun().getFPlural(), "assaglidas");
    }

    @Test
    public void testRule15() {
        forms = generator.generateForms("15", "besch");
        assertEquals(forms.getNoun().getMSingular(), "besch");
        assertEquals(forms.getNoun().getFSingular(), null);
        assertEquals(forms.getNoun().getMPlural(), "beschs");
        assertEquals(forms.getNoun().getFPlural(), null);
        assertEquals(forms.getNoun().getPluralCollectiv(), "bescha");
    }

    @Test
    public void testPluralRule2() {
        forms = generator.generateForms("1", "pass");
        assertEquals(forms.getNoun().getMSingular(), "pass");
        assertEquals(forms.getNoun().getMPlural(), "pass");

        forms = generator.generateForms("1", "nes");
        assertEquals(forms.getNoun().getMSingular(), "nes");
        assertEquals(forms.getNoun().getMPlural(), "nes");
    }

    @Test
    public void testPluralRule3() {
        forms = generator.generateForms("1", "curtè");
        assertEquals(forms.getNoun().getMSingular(), "curtè");
        assertEquals(forms.getNoun().getMPlural(), "curtels");
    }

    @Test
    public void testPluralRule4() {
        forms = generator.generateForms("2", "chalur");
        assertEquals(forms.getNoun().getFSingular(), "chalur");
        assertEquals(forms.getNoun().getFPlural(), "chaluors");
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
