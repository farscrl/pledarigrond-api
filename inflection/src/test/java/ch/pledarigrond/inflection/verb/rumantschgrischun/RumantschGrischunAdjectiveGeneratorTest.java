package ch.pledarigrond.inflection.verb.rumantschgrischun;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.adjective.rumantschgrischun.RumantschGrischunAdjectiveGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RumantschGrischunAdjectiveGeneratorTest {

    private static RumantschGrischunAdjectiveGenerator generator;
    private InflectionDto forms;

    @BeforeAll
    public static void initialize() {
        generator = new RumantschGrischunAdjectiveGenerator();
    }

    @Test
    public void testRule1() {
        forms = generator.generateForms("1", "puril");
        assertEquals(forms.getAdjective().getMSingular(), "puril");
        assertEquals(forms.getAdjective().getFSingular(), "purila");
        assertEquals(forms.getAdjective().getMPlural(), "purils");
        assertEquals(forms.getAdjective().getFPlural(), "purilas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "purilmain");
    }

    @Test
    public void testRule2() {
        forms = generator.generateForms("2", "veder");
        assertEquals(forms.getAdjective().getMSingular(), "veder");
        assertEquals(forms.getAdjective().getFSingular(), "vedra");
        assertEquals(forms.getAdjective().getMPlural(), "veders");
        assertEquals(forms.getAdjective().getFPlural(), "vedras");
        assertEquals(forms.getAdjective().getAdverbialForm(), "vedramain");
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("3", "admirabel");
        assertEquals(forms.getAdjective().getMSingular(), "admirabel");
        assertEquals(forms.getAdjective().getFSingular(), "admirabla");
        assertEquals(forms.getAdjective().getMPlural(), "admirabels");
        assertEquals(forms.getAdjective().getFPlural(), "admirablas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "admirablamain");
    }

    @Test
    public void testRule4() {
        forms = generator.generateForms("4", "mediocher");
        assertEquals(forms.getAdjective().getMSingular(), "mediocher");
        assertEquals(forms.getAdjective().getFSingular(), "mediocra");
        assertEquals(forms.getAdjective().getMPlural(), "mediochers");
        assertEquals(forms.getAdjective().getFPlural(), "mediocras");
        assertEquals(forms.getAdjective().getAdverbialForm(), "mediocramain");
    }

    @Test
    public void testRule5() {
        forms = generator.generateForms("5", "amiaivel");
        assertEquals(forms.getAdjective().getMSingular(), "amiaivel");
        assertEquals(forms.getAdjective().getFSingular(), "amiaivla");
        assertEquals(forms.getAdjective().getMPlural(), "amiaivels");
        assertEquals(forms.getAdjective().getFPlural(), "amiaivlas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "amiaivlamain");
    }

    @Test
    public void testRule6() {
        forms = generator.generateForms("6", "giuven");
        assertEquals(forms.getAdjective().getMSingular(), "giuven");
        assertEquals(forms.getAdjective().getFSingular(), "giuvna");
        assertEquals(forms.getAdjective().getMPlural(), "giuvens");
        assertEquals(forms.getAdjective().getFPlural(), "giuvnas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "giuvnamain");
    }

    @Test
    public void testRule7() {
        forms = generator.generateForms("7", "isolà");
        assertEquals(forms.getAdjective().getMSingular(), "isolà");
        assertEquals(forms.getAdjective().getFSingular(), "isolada");
        assertEquals(forms.getAdjective().getMPlural(), "isolads");
        assertEquals(forms.getAdjective().getFPlural(), "isoladas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "isoladamain");
    }

    @Test
    public void testRule8() {
        forms = generator.generateForms("8", "sclerì");
        assertEquals(forms.getAdjective().getMSingular(), "sclerì");
        assertEquals(forms.getAdjective().getFSingular(), "sclerida");
        assertEquals(forms.getAdjective().getMPlural(), "sclerids");
        assertEquals(forms.getAdjective().getFPlural(), "scleridas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "scleridamain");
    }

    @Test
    public void testRule9() {
        forms = generator.generateForms("9", "tschuf");
        assertEquals(forms.getAdjective().getMSingular(), "tschuf");
        assertEquals(forms.getAdjective().getFSingular(), "tschuffa");
        assertEquals(forms.getAdjective().getMPlural(), "tschufs");
        assertEquals(forms.getAdjective().getFPlural(), "tschuffas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "tschuffamain");

        forms = generator.generateForms("9", "rut");
        assertEquals(forms.getAdjective().getMSingular(), "rut");
        assertEquals(forms.getAdjective().getFSingular(), "rutta");
        assertEquals(forms.getAdjective().getMPlural(), "ruts");
        assertEquals(forms.getAdjective().getFPlural(), "ruttas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "ruttamain");
    }
}
