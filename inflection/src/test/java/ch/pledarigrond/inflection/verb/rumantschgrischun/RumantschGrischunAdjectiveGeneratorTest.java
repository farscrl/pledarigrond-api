package ch.pledarigrond.inflection.verb.rumantschgrischun;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.adjective.rumantschgrischun.RumantschGrischunAdjectiveGenerator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class RumantschGrischunAdjectiveGeneratorTest {

    private static RumantschGrischunAdjectiveGenerator generator;
    private InflectionDto forms;

    @BeforeClass
    public static void initialize() {
        generator = new RumantschGrischunAdjectiveGenerator();
    }

    @Test
    public void testRule1() {
        forms = generator.generateForms("1", "puril");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "puril");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "purila");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "purils");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "purilas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "purilmain");
    }

    @Test
    public void testRule2() {
        forms = generator.generateForms("2", "veder");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "veder");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "vedra");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "veders");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "vedras");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "vedramain");
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("3", "admirabel");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "admirabel");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "admirabla");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "admirabels");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "admirablas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "admirablamain");
    }

    @Test
    public void testRule4() {
        forms = generator.generateForms("4", "mediocher");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "mediocher");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "mediocra");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "mediochers");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "mediocras");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "mediocramain");
    }

    @Test
    public void testRule5() {
        forms = generator.generateForms("5", "amiaivel");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "amiaivel");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "amiaivla");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "amiaivels");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "amiaivlas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "amiaivlamain");
    }

    @Test
    public void testRule6() {
        forms = generator.generateForms("6", "giuven");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "giuven");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "giuvna");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "giuvens");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "giuvnas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "giuvnamain");
    }

    @Test
    public void testRule7() {
        forms = generator.generateForms("7", "isolà");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "isolà");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "isolada");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "isolads");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "isoladas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "isoladamain");
    }

    @Test
    public void testRule8() {
        forms = generator.generateForms("8", "sclerì");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "sclerì");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "sclerida");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "sclerids");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "scleridas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "scleridamain");
    }

    @Test
    public void testRule9() {
        forms = generator.generateForms("9", "tschuf");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "tschuf");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "tschuffa");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "tschufs");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "tschuffas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "tschuffamain");

        forms = generator.generateForms("9", "rut");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "rut");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "rutta");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "ruts");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "ruttas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "ruttamain");
    }
}
