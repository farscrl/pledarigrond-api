package ch.pledarigrond.inflection.verb.sutsilvan;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.noun.sutsilvan.SutsilvanNounGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SutsilvanNounGeneratorTest {

    private static SutsilvanNounGenerator generator;
    private InflectionDto forms;

    @BeforeAll
    public static void initialize() {
        generator = new SutsilvanNounGenerator();
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("3", "scular");
        assertEquals(forms.getNoun().getMSingular(), "scular");
        assertEquals(forms.getNoun().getFSingular(), "sculara");
        assertEquals(forms.getNoun().getMPlural(), "sculars");
        assertEquals(forms.getNoun().getFPlural(), "scularas");
    }

    @Test
    public void testRule4() {
        forms = generator.generateForms("4", "antschavider");
        assertEquals(forms.getNoun().getMSingular(), "antschavider");
        assertEquals(forms.getNoun().getFSingular(), "antschavidra");
        assertEquals(forms.getNoun().getMPlural(), "antschaviders");
        assertEquals(forms.getNoun().getFPlural(), "antschavidras");
    }

    @Test
    public void testRule5() {
        forms = generator.generateForms("5", "raspunsabel");
        assertEquals(forms.getNoun().getMSingular(), "raspunsabel");
        assertEquals(forms.getNoun().getFSingular(), "raspunsabla");
        assertEquals(forms.getNoun().getMPlural(), "raspunsabels");
        assertEquals(forms.getNoun().getFPlural(), "raspunsablas");
    }

    @Test
    public void testRule6() {
        forms = generator.generateForms("6", "apotecer");
        assertEquals(forms.getNoun().getMSingular(), "apotecer");
        assertEquals(forms.getNoun().getFSingular(), "apotecra");
        assertEquals(forms.getNoun().getMPlural(), "apotecers");
        assertEquals(forms.getNoun().getFPlural(), "apotecras");
    }

    @Test
    public void testRule7() {
        forms = generator.generateForms("7", "raspunsavel");
        assertEquals(forms.getNoun().getMSingular(), "raspunsavel");
        assertEquals(forms.getNoun().getFSingular(), "raspunsavla");
        assertEquals(forms.getNoun().getMPlural(), "raspunsavels");
        assertEquals(forms.getNoun().getFPlural(), "raspunsavlas");
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
        forms = generator.generateForms("10", "impiego");
        assertEquals(forms.getNoun().getMSingular(), "impiego");
        assertEquals(forms.getNoun().getFSingular(), "impiegada");
        assertEquals(forms.getNoun().getMPlural(), "impiegos");
        assertEquals(forms.getNoun().getFPlural(), "impiegadas");
    }

    @Test
    public void testRule11() {
        forms = generator.generateForms("11", "impedieu");
        assertEquals(forms.getNoun().getMSingular(), "impedieu");
        assertEquals(forms.getNoun().getFSingular(), "impedida");
        assertEquals(forms.getNoun().getMPlural(), "impedieus");
        assertEquals(forms.getNoun().getFPlural(), "impedidas");
    }

    @Test
    public void testRule12() {
        forms = generator.generateForms("12", "sgiaglieu");
        assertEquals(forms.getNoun().getMSingular(), "sgiaglieu");
        assertEquals(forms.getNoun().getFSingular(), "sgiaglieada");
        assertEquals(forms.getNoun().getMPlural(), "sgiaglieus");
        assertEquals(forms.getNoun().getFPlural(), "sgiaglieadas");
    }

    @Test
    public void testRule13() {
        forms = generator.generateForms("13", "partutgieu");
        assertEquals(forms.getNoun().getMSingular(), "partutgieu");
        assertEquals(forms.getNoun().getFSingular(), "partutgeada");
        assertEquals(forms.getNoun().getMPlural(), "partutgieus");
        assertEquals(forms.getNoun().getFPlural(), "partutgeadas");
    }

    @Test
    public void testRule14() {
        forms = generator.generateForms("14", "exiliieu");
        assertEquals(forms.getNoun().getMSingular(), "exiliieu");
        assertEquals(forms.getNoun().getFSingular(), "exilieada");
        assertEquals(forms.getNoun().getMPlural(), "exiliieus");
        assertEquals(forms.getNoun().getFPlural(), "exilieadas");
    }

    @Test
    public void testRule15() {
        forms = generator.generateForms("15", "pér");
        assertEquals(forms.getNoun().getMSingular(), "pér");
        assertEquals(forms.getNoun().getFSingular(), null);
        assertEquals(forms.getNoun().getMPlural(), "pérs");
        assertEquals(forms.getNoun().getFPlural(), null);
        assertEquals(forms.getNoun().getPluralCollectiv(), "péra");
    }

    @Test
    public void testPluralRule2() {
        forms = generator.generateForms("1", "pass");
        assertEquals(forms.getNoun().getMSingular(), "pass");
        assertEquals(forms.getNoun().getMPlural(), "pass");
    }

    @Test
    public void testPluralRule3() {
        forms = generator.generateForms("1", "esch-tgea");
        assertEquals(forms.getNoun().getMSingular(), "esch-tgea");
        assertEquals(forms.getNoun().getMPlural(), "eschs-tgea");
    }

    @Test
    public void testPluralRule4() {
        forms = generator.generateForms("1", "cunti");
        assertEquals(forms.getNoun().getMSingular(), "cunti");
        assertEquals(forms.getNoun().getMPlural(), "cunteals");
    }
}
