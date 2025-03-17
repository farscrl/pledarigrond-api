package ch.pledarigrond.inflection.verb.surmiran;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.adjective.surmiran.SurmiranAdjectiveGenerator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class SurmiranAdjectiveGeneratorTest {

    private static SurmiranAdjectiveGenerator generator;
    private InflectionDto forms;

    @BeforeClass
    public static void initialize() {
        generator = new SurmiranAdjectiveGenerator();
    }

    @Test
    public void testRule1() {
        forms = generator.generateForms("1", "puril");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "puril");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "purila");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "purils");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "purilas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "purilamaintg");
    }

    @Test
    public void testRule2() {
        forms = generator.generateForms("2", "veder");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "veder");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "vedra");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "veders");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "vedras");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "vedramaintg");
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("3", "admirabel");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "admirabel");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "admirabla");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "admirabels");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "admirablas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "admirablamaintg");
    }

    @Test
    public void testRule4() {
        forms = generator.generateForms("4", "zacher");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "zacher");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "zacra");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "zachers");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "zacras");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "zacramaintg");
    }

    @Test
    public void testRule5() {
        forms = generator.generateForms("5", "capavel");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "capavel");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "capavla");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "capavels");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "capavlas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "capavlamaintg");
    }

    @Test
    public void testRule6() {
        forms = generator.generateForms("6", "gioven");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "gioven");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "giovna");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "giovens");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "giovnas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "giovnamaintg");
    }

    @Test
    public void testRule7() {
        forms = generator.generateForms("7", "distinguia");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "distinguia");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "distingueida");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "distinguias");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "distingueidas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "distingueidamaintg");
    }

    @Test
    public void testRule8() {
        forms = generator.generateForms("8", "attempo");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "attempo");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "attempada");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "attempos");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "attempadas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "attempadamaintg");
    }

    @Test
    public void testRule9() {
        forms = generator.generateForms("9", "schlaschea");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "schlaschea");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "schlascheda");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "schlascheas");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "schlaschedas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "schlaschedamaintg");
    }

    @Test
    public void testRule10() {
        forms = generator.generateForms("10", "tschuf");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "tschuf");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "tschuffa");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "tschufs");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "tschuffas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "tschuffamaintg");
    }
}
