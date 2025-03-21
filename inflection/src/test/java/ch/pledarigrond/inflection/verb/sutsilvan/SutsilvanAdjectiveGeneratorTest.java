package ch.pledarigrond.inflection.verb.sutsilvan;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.adjective.sutsilvan.SutsilvanAdjectiveGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SutsilvanAdjectiveGeneratorTest {

    private static SutsilvanAdjectiveGenerator generator;
    private InflectionDto forms;

    @BeforeAll
    public static void initialize() {
        generator = new SutsilvanAdjectiveGenerator();
    }

    @Test
    public void testRule1() {
        forms = generator.generateForms("1", "puril");
        assertEquals(forms.getAdjective().getMSingular(), "puril");
        assertEquals(forms.getAdjective().getFSingular(), "purila");
        assertEquals(forms.getAdjective().getMPlural(), "purils");
        assertEquals(forms.getAdjective().getFPlural(), "purilas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "purilameing");
    }

    @Test
    public void testRule2() {
        forms = generator.generateForms("2", "veader");
        assertEquals(forms.getAdjective().getMSingular(), "veader");
        assertEquals(forms.getAdjective().getFSingular(), "veadra");
        assertEquals(forms.getAdjective().getMPlural(), "veaders");
        assertEquals(forms.getAdjective().getFPlural(), "veadras");
        assertEquals(forms.getAdjective().getAdverbialForm(), "veadrameing");
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("3", "admirabel");
        assertEquals(forms.getAdjective().getMSingular(), "admirabel");
        assertEquals(forms.getAdjective().getFSingular(), "admirabla");
        assertEquals(forms.getAdjective().getMPlural(), "admirabels");
        assertEquals(forms.getAdjective().getFPlural(), "admirablas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "admirablameing");
    }

    @Test
    public void testRule4() {
        forms = generator.generateForms("4", "legher");
        assertEquals(forms.getAdjective().getMSingular(), "legher");
        assertEquals(forms.getAdjective().getFSingular(), "legra");
        assertEquals(forms.getAdjective().getMPlural(), "leghers");
        assertEquals(forms.getAdjective().getFPlural(), "legras");
        assertEquals(forms.getAdjective().getAdverbialForm(), "legrameing");
    }

    @Test
    public void testRule5() {
        forms = generator.generateForms("5", "vantirevel");
        assertEquals(forms.getAdjective().getMSingular(), "vantirevel");
        assertEquals(forms.getAdjective().getFSingular(), "vantirevla");
        assertEquals(forms.getAdjective().getMPlural(), "vantirevels");
        assertEquals(forms.getAdjective().getFPlural(), "vantirevlas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "vantirevlameing");
    }

    @Test
    public void testRule6() {
        forms = generator.generateForms("6", "giuven");
        assertEquals(forms.getAdjective().getMSingular(), "giuven");
        assertEquals(forms.getAdjective().getFSingular(), "giuvna");
        assertEquals(forms.getAdjective().getMPlural(), "giuvens");
        assertEquals(forms.getAdjective().getFPlural(), "giuvnas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "giuvnameing");
    }

    @Test
    public void testRule7() {
        forms = generator.generateForms("7", "surprieu");
        assertEquals(forms.getAdjective().getMSingular(), "surprieu");
        assertEquals(forms.getAdjective().getFSingular(), "surprida");
        assertEquals(forms.getAdjective().getMPlural(), "surprieus");
        assertEquals(forms.getAdjective().getFPlural(), "surpridas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "surpridameing");
    }

    @Test
    public void testRule8() {
        forms = generator.generateForms("8", "ocupo");
        assertEquals(forms.getAdjective().getMSingular(), "ocupo");
        assertEquals(forms.getAdjective().getFSingular(), "ocupada");
        assertEquals(forms.getAdjective().getMPlural(), "ocupos");
        assertEquals(forms.getAdjective().getFPlural(), "ocupadas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "ocupadameing");
    }

    @Test
    public void testRule9() {
        forms = generator.generateForms("9", "vescel");
        assertEquals(forms.getAdjective().getMSingular(), "vescel");
        assertEquals(forms.getAdjective().getFSingular(), "vescla");
        assertEquals(forms.getAdjective().getMPlural(), "vescels");
        assertEquals(forms.getAdjective().getFPlural(), "vesclas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "vesclameing");
    }

    @Test
    public void testRule10() {
        forms = generator.generateForms("10", "pover");
        assertEquals(forms.getAdjective().getMSingular(), "pover");
        assertEquals(forms.getAdjective().getFSingular(), "povra");
        assertEquals(forms.getAdjective().getMPlural(), "povers");
        assertEquals(forms.getAdjective().getFPlural(), "povras");
        assertEquals(forms.getAdjective().getAdverbialForm(), "povrameing");
    }

    @Test
    public void testRule11() {
        forms = generator.generateForms("11", "orfen");
        assertEquals(forms.getAdjective().getMSingular(), "orfen");
        assertEquals(forms.getAdjective().getFSingular(), "orfna");
        assertEquals(forms.getAdjective().getMPlural(), "orfens");
        assertEquals(forms.getAdjective().getFPlural(), "orfnas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "orfnameing");
    }

    @Test
    public void testRule12() {
        forms = generator.generateForms("12", "detaglieu");
        assertEquals(forms.getAdjective().getMSingular(), "detaglieu");
        assertEquals(forms.getAdjective().getFSingular(), "detaglieada");
        assertEquals(forms.getAdjective().getMPlural(), "detaglieus");
        assertEquals(forms.getAdjective().getFPlural(), "detaglieadas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "detaglieadameing");
    }

    @Test
    public void testRule13() {
        forms = generator.generateForms("13", "partutgieu");
        assertEquals(forms.getAdjective().getMSingular(), "partutgieu");
        assertEquals(forms.getAdjective().getFSingular(), "partutgeada");
        assertEquals(forms.getAdjective().getMPlural(), "partutgieus");
        assertEquals(forms.getAdjective().getFPlural(), "partutgeadas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "partutgeadameing");
    }
}
