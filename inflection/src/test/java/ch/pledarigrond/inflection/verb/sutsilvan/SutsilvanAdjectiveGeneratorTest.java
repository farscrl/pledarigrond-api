package ch.pledarigrond.inflection.verb.sutsilvan;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.adjective.sutsilvan.SutsilvanAdjectiveGenerator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class SutsilvanAdjectiveGeneratorTest {

    private static SutsilvanAdjectiveGenerator generator;
    private InflectionDto forms;

    @BeforeClass
    public static void initialize() {
        generator = new SutsilvanAdjectiveGenerator();
    }

    @Test
    public void testRule1() {
        forms = generator.generateForms("1", "puril");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "puril");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "purila");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "purils");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "purilas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "purilameing");
    }

    @Test
    public void testRule2() {
        forms = generator.generateForms("2", "veader");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "veader");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "veadra");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "veaders");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "veadras");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "veadrameing");
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("3", "admirabel");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "admirabel");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "admirabla");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "admirabels");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "admirablas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "admirablameing");
    }

    @Test
    public void testRule4() {
        forms = generator.generateForms("4", "legher");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "legher");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "legra");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "leghers");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "legras");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "legrameing");
    }

    @Test
    public void testRule5() {
        forms = generator.generateForms("5", "vantirevel");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "vantirevel");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "vantirevla");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "vantirevels");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "vantirevlas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "vantirevlameing");
    }

    @Test
    public void testRule6() {
        forms = generator.generateForms("6", "giuven");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "giuven");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "giuvna");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "giuvens");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "giuvnas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "giuvnameing");
    }

    @Test
    public void testRule7() {
        forms = generator.generateForms("7", "surprieu");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "surprieu");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "surprida");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "surprieus");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "surpridas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "surpridameing");
    }

    @Test
    public void testRule8() {
        forms = generator.generateForms("8", "ocupo");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "ocupo");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "ocupada");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "ocupos");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "ocupadas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "ocupadameing");
    }

    @Test
    public void testRule9() {
        forms = generator.generateForms("9", "vescel");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "vescel");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "vescla");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "vescels");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "vesclas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "vesclameing");
    }

    @Test
    public void testRule10() {
        forms = generator.generateForms("10", "pover");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "pover");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "povra");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "povers");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "povras");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "povrameing");
    }

    @Test
    public void testRule11() {
        forms = generator.generateForms("11", "orfen");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "orfen");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "orfna");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "orfens");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "orfnas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "orfnameing");
    }

    @Test
    public void testRule12() {
        forms = generator.generateForms("12", "detaglieu");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "detaglieu");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "detaglieada");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "detaglieus");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "detaglieadas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "detaglieadameing");
    }

    @Test
    public void testRule13() {
        forms = generator.generateForms("13", "partutgieu");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "partutgieu");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "partutgeada");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "partutgieus");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "partutgeadas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "partutgeadameing");
    }
}
