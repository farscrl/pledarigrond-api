package ch.pledarigrond.inflection.verb.sursilvan;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.adjective.sursilvan.SursilvanAdjectiveGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SursilvanAdjectiveGeneratorTest {

    private static SursilvanAdjectiveGenerator generator;
    private InflectionDto forms;

    @BeforeAll
    public static void initialize() {
        generator = new SursilvanAdjectiveGenerator();
    }

    @Test
    public void testRule1() {
        forms = generator.generateForms("1", "verd");
        assertEquals(forms.getAdjective().getMSingular(), "verd");
        assertEquals(forms.getAdjective().getPredicative(), "verds");
        assertEquals(forms.getAdjective().getFSingular(), "verda");
        assertEquals(forms.getAdjective().getMPlural(), "verds");
        assertEquals(forms.getAdjective().getFPlural(), "verdas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "verdamein");
    }

    @Test
    public void testRule2() {
        forms = generator.generateForms("2", "ventireivel");
        assertEquals(forms.getAdjective().getMSingular(), "ventireivel");
        assertEquals(forms.getAdjective().getPredicative(), "ventireivels");
        assertEquals(forms.getAdjective().getFSingular(), "ventireivla");
        assertEquals(forms.getAdjective().getMPlural(), "ventireivels");
        assertEquals(forms.getAdjective().getFPlural(), "ventireivlas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "ventireivlamein");
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("3", "viscal");
        assertEquals(forms.getAdjective().getMSingular(), "viscal");
        assertEquals(forms.getAdjective().getPredicative(), "viscals");
        assertEquals(forms.getAdjective().getFSingular(), "viscla");
        assertEquals(forms.getAdjective().getMPlural(), "viscals");
        assertEquals(forms.getAdjective().getFPlural(), "visclas");
        // exception, as accent is on the first syllable. Correct would be visclamein, but checking the regular pattern
        assertEquals(forms.getAdjective().getAdverbialForm(), "viscalmein");
    }

    @Test
    public void testRule4() {
        forms = generator.generateForms("4", "giuven");
        assertEquals(forms.getAdjective().getMSingular(), "giuven");
        assertEquals(forms.getAdjective().getPredicative(), "giuvens");
        assertEquals(forms.getAdjective().getFSingular(), "giuvna");
        assertEquals(forms.getAdjective().getMPlural(), "giuvens");
        assertEquals(forms.getAdjective().getFPlural(), "giuvnas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "giuvnamein");
    }

    @Test
    public void testRule6() {
        forms = generator.generateForms("6", "pauper");
        assertEquals(forms.getAdjective().getMSingular(), "pauper");
        assertEquals(forms.getAdjective().getPredicative(), "paupers");
        assertEquals(forms.getAdjective().getFSingular(), "paupra");
        assertEquals(forms.getAdjective().getMPlural(), "paupers");
        assertEquals(forms.getAdjective().getFPlural(), "paupras");
        assertEquals(forms.getAdjective().getAdverbialForm(), "paupramein");
    }

    @Test
    public void testRule7() {
        forms = generator.generateForms("7", "bugliu");
        assertEquals(forms.getAdjective().getMSingular(), "bugliu");
        assertEquals(forms.getAdjective().getPredicative(), "buglius");
        assertEquals(forms.getAdjective().getFSingular(), "buglida");
        assertEquals(forms.getAdjective().getMPlural(), "buglius");
        assertEquals(forms.getAdjective().getFPlural(), "buglidas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "buglidamein");
    }

    @Test
    public void testRule8() {
        forms = generator.generateForms("8", "barsau");
        assertEquals(forms.getAdjective().getMSingular(), "barsau");
        assertEquals(forms.getAdjective().getPredicative(), "barsaus");
        assertEquals(forms.getAdjective().getFSingular(), "barsada");
        assertEquals(forms.getAdjective().getMPlural(), "barsaus");
        assertEquals(forms.getAdjective().getFPlural(), "barsadas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "barsadamein");
    }

    @Test
    public void testRule10() {
        forms = generator.generateForms("10", "tschuf");
        assertEquals(forms.getAdjective().getMSingular(), "tschuf");
        assertEquals(forms.getAdjective().getPredicative(), "tschufs");
        assertEquals(forms.getAdjective().getFSingular(), "tschuffa");
        assertEquals(forms.getAdjective().getMPlural(), "tschufs");
        assertEquals(forms.getAdjective().getFPlural(), "tschuffas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "tschuffamein");
    }

    @Test
    public void testRuleSpecialAdverbial() {
        forms = generator.generateForms("1", "final");
        assertEquals(forms.getAdjective().getMSingular(), "final");
        assertEquals(forms.getAdjective().getPredicative(), "finals");
        assertEquals(forms.getAdjective().getFSingular(), "finala");
        assertEquals(forms.getAdjective().getMPlural(), "finals");
        assertEquals(forms.getAdjective().getFPlural(), "finalas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "finalmein");
    }
}
