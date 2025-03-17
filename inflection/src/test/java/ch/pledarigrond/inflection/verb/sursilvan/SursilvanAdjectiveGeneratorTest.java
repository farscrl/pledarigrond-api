package ch.pledarigrond.inflection.verb.sursilvan;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.adjective.sursilvan.SursilvanAdjectiveGenerator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class SursilvanAdjectiveGeneratorTest {

    private static SursilvanAdjectiveGenerator generator;
    private InflectionDto forms;

    @BeforeClass
    public static void initialize() {
        generator = new SursilvanAdjectiveGenerator();
    }

    @Test
    public void testRule1() {
        forms = generator.generateForms("1", "verd");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "verd");
        Assert.assertEquals(forms.getAdjective().getPredicative(), "verds");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "verda");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "verds");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "verdas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "verdamein");
    }

    @Test
    public void testRule2() {
        forms = generator.generateForms("2", "ventireivel");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "ventireivel");
        Assert.assertEquals(forms.getAdjective().getPredicative(), "ventireivels");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "ventireivla");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "ventireivels");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "ventireivlas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "ventireivlamein");
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("3", "viscal");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "viscal");
        Assert.assertEquals(forms.getAdjective().getPredicative(), "viscals");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "viscla");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "viscals");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "visclas");
        // exception, as accent is on the first syllable. Correct would be visclamein, but checking the regular pattern
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "viscalmein");
    }

    @Test
    public void testRule4() {
        forms = generator.generateForms("4", "giuven");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "giuven");
        Assert.assertEquals(forms.getAdjective().getPredicative(), "giuvens");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "giuvna");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "giuvens");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "giuvnas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "giuvnamein");
    }

    @Test
    public void testRule6() {
        forms = generator.generateForms("6", "pauper");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "pauper");
        Assert.assertEquals(forms.getAdjective().getPredicative(), "paupers");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "paupra");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "paupers");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "paupras");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "paupramein");
    }

    @Test
    public void testRule7() {
        forms = generator.generateForms("7", "bugliu");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "bugliu");
        Assert.assertEquals(forms.getAdjective().getPredicative(), "buglius");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "buglida");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "buglius");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "buglidas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "buglidamein");
    }

    @Test
    public void testRule8() {
        forms = generator.generateForms("8", "barsau");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "barsau");
        Assert.assertEquals(forms.getAdjective().getPredicative(), "barsaus");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "barsada");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "barsaus");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "barsadas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "barsadamein");
    }

    @Test
    public void testRule10() {
        forms = generator.generateForms("10", "tschuf");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "tschuf");
        Assert.assertEquals(forms.getAdjective().getPredicative(), "tschufs");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "tschuffa");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "tschufs");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "tschuffas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "tschuffamein");
    }

    @Test
    public void testRuleSpecialAdverbial() {
        forms = generator.generateForms("1", "final");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "final");
        Assert.assertEquals(forms.getAdjective().getPredicative(), "finals");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "finala");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "finals");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "finalas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "finalmein");
    }
}
