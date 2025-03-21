package ch.pledarigrond.inflection.verb.vallader;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.noun.vallader.ValladerNounGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValladerNounGeneratorTest {

    private static ValladerNounGenerator generator;
    private InflectionDto forms;

    @BeforeAll
    public static void initialize() {
        generator = new ValladerNounGenerator();
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
        forms = generator.generateForms("2", "palü");
        assertEquals(forms.getNoun().getMSingular(), null);
        assertEquals(forms.getNoun().getFSingular(), "palü");
        assertEquals(forms.getNoun().getMPlural(), null);
        assertEquals(forms.getNoun().getFPlural(), "palüts");
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
        forms = generator.generateForms("4", "manader");
        assertEquals(forms.getNoun().getMSingular(), "manader");
        assertEquals(forms.getNoun().getFSingular(), "manadra");
        assertEquals(forms.getNoun().getMPlural(), "manaders");
        assertEquals(forms.getNoun().getFPlural(), "manadras");
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
        forms = generator.generateForms("10", "deputà");
        assertEquals(forms.getNoun().getMSingular(), "deputà");
        assertEquals(forms.getNoun().getFSingular(), "deputada");
        assertEquals(forms.getNoun().getMPlural(), "deputats");
        assertEquals(forms.getNoun().getFPlural(), "deputadas");
    }

    @Test
    public void testRule11() {
        forms = generator.generateForms("11", "vendü");
        assertEquals(forms.getNoun().getMSingular(), "vendü");
        assertEquals(forms.getNoun().getFSingular(), "vendüda");
        assertEquals(forms.getNoun().getMPlural(), "vendüts");
        assertEquals(forms.getNoun().getFPlural(), "vendüdas");
    }

    @Test
    public void testRule12() {
        forms = generator.generateForms("12", "impedi");
        assertEquals(forms.getNoun().getMSingular(), "impedi");
        assertEquals(forms.getNoun().getFSingular(), "impedida");
        assertEquals(forms.getNoun().getMPlural(), "impedits");
        assertEquals(forms.getNoun().getFPlural(), "impedidas");
    }

    @Test
    public void testRule13() {
        forms = generator.generateForms("13", "besch");
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

        forms = generator.generateForms("1", "nas");
        assertEquals(forms.getNoun().getMSingular(), "nas");
        assertEquals(forms.getNoun().getMPlural(), "nas");
    }

    @Test
    public void testPluralRule3() {
        forms = generator.generateForms("2", "sudà");
        assertEquals(forms.getNoun().getFSingular(), "sudà");
        assertEquals(forms.getNoun().getFPlural(), "sudats");
    }

    @Test
    public void testPluralRule4() {
        forms = generator.generateForms("1", "curtè");
        assertEquals(forms.getNoun().getMSingular(), "curtè");
        assertEquals(forms.getNoun().getMPlural(), "curtels");
    }

    @Test
    public void testPluralRule5() {
        forms = generator.generateForms("2", "vesti");
        assertEquals(forms.getNoun().getFSingular(), "vesti");
        assertEquals(forms.getNoun().getFPlural(), "vestits");
    }

    @Test
    public void testPluralRule6() {
        forms = generator.generateForms("1", "creschü");
        assertEquals(forms.getNoun().getMSingular(), "creschü");
        assertEquals(forms.getNoun().getMPlural(), "creschüts");
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
