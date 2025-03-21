package ch.pledarigrond.inflection.verb.surmiran;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.adjective.surmiran.SurmiranAdjectiveGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SurmiranAdjectiveGeneratorTest {

    private static SurmiranAdjectiveGenerator generator;
    private InflectionDto forms;

    @BeforeAll
    public static void initialize() {
        generator = new SurmiranAdjectiveGenerator();
    }

    @Test
    public void testRule1() {
        forms = generator.generateForms("1", "puril");
        assertEquals(forms.getAdjective().getMSingular(), "puril");
        assertEquals(forms.getAdjective().getFSingular(), "purila");
        assertEquals(forms.getAdjective().getMPlural(), "purils");
        assertEquals(forms.getAdjective().getFPlural(), "purilas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "purilamaintg");
    }

    @Test
    public void testRule2() {
        forms = generator.generateForms("2", "veder");
        assertEquals(forms.getAdjective().getMSingular(), "veder");
        assertEquals(forms.getAdjective().getFSingular(), "vedra");
        assertEquals(forms.getAdjective().getMPlural(), "veders");
        assertEquals(forms.getAdjective().getFPlural(), "vedras");
        assertEquals(forms.getAdjective().getAdverbialForm(), "vedramaintg");
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("3", "admirabel");
        assertEquals(forms.getAdjective().getMSingular(), "admirabel");
        assertEquals(forms.getAdjective().getFSingular(), "admirabla");
        assertEquals(forms.getAdjective().getMPlural(), "admirabels");
        assertEquals(forms.getAdjective().getFPlural(), "admirablas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "admirablamaintg");
    }

    @Test
    public void testRule4() {
        forms = generator.generateForms("4", "zacher");
        assertEquals(forms.getAdjective().getMSingular(), "zacher");
        assertEquals(forms.getAdjective().getFSingular(), "zacra");
        assertEquals(forms.getAdjective().getMPlural(), "zachers");
        assertEquals(forms.getAdjective().getFPlural(), "zacras");
        assertEquals(forms.getAdjective().getAdverbialForm(), "zacramaintg");
    }

    @Test
    public void testRule5() {
        forms = generator.generateForms("5", "capavel");
        assertEquals(forms.getAdjective().getMSingular(), "capavel");
        assertEquals(forms.getAdjective().getFSingular(), "capavla");
        assertEquals(forms.getAdjective().getMPlural(), "capavels");
        assertEquals(forms.getAdjective().getFPlural(), "capavlas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "capavlamaintg");
    }

    @Test
    public void testRule6() {
        forms = generator.generateForms("6", "gioven");
        assertEquals(forms.getAdjective().getMSingular(), "gioven");
        assertEquals(forms.getAdjective().getFSingular(), "giovna");
        assertEquals(forms.getAdjective().getMPlural(), "giovens");
        assertEquals(forms.getAdjective().getFPlural(), "giovnas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "giovnamaintg");
    }

    @Test
    public void testRule7() {
        forms = generator.generateForms("7", "distinguia");
        assertEquals(forms.getAdjective().getMSingular(), "distinguia");
        assertEquals(forms.getAdjective().getFSingular(), "distingueida");
        assertEquals(forms.getAdjective().getMPlural(), "distinguias");
        assertEquals(forms.getAdjective().getFPlural(), "distingueidas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "distingueidamaintg");
    }

    @Test
    public void testRule8() {
        forms = generator.generateForms("8", "attempo");
        assertEquals(forms.getAdjective().getMSingular(), "attempo");
        assertEquals(forms.getAdjective().getFSingular(), "attempada");
        assertEquals(forms.getAdjective().getMPlural(), "attempos");
        assertEquals(forms.getAdjective().getFPlural(), "attempadas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "attempadamaintg");
    }

    @Test
    public void testRule9() {
        forms = generator.generateForms("9", "schlaschea");
        assertEquals(forms.getAdjective().getMSingular(), "schlaschea");
        assertEquals(forms.getAdjective().getFSingular(), "schlascheda");
        assertEquals(forms.getAdjective().getMPlural(), "schlascheas");
        assertEquals(forms.getAdjective().getFPlural(), "schlaschedas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "schlaschedamaintg");
    }

    @Test
    public void testRule10() {
        forms = generator.generateForms("10", "tschuf");
        assertEquals(forms.getAdjective().getMSingular(), "tschuf");
        assertEquals(forms.getAdjective().getFSingular(), "tschuffa");
        assertEquals(forms.getAdjective().getMPlural(), "tschufs");
        assertEquals(forms.getAdjective().getFPlural(), "tschuffas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "tschuffamaintg");
    }
}
