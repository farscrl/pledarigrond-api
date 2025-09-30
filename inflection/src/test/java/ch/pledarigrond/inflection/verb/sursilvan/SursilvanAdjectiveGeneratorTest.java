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
        assertEquals("verd", forms.getAdjective().getMSingular());
        assertEquals("verds", forms.getAdjective().getPredicative());
        assertEquals("verda", forms.getAdjective().getFSingular());
        assertEquals("verds", forms.getAdjective().getMPlural());
        assertEquals("verdas", forms.getAdjective().getFPlural());
        assertEquals("verdamein", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule2() {
        forms = generator.generateForms("2", "ventireivel");
        assertEquals("ventireivel", forms.getAdjective().getMSingular());
        assertEquals("ventireivels", forms.getAdjective().getPredicative());
        assertEquals("ventireivla", forms.getAdjective().getFSingular());
        assertEquals("ventireivels", forms.getAdjective().getMPlural());
        assertEquals("ventireivlas", forms.getAdjective().getFPlural());
        assertEquals("ventireivlamein", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("3", "viscal");
        assertEquals("viscal", forms.getAdjective().getMSingular());
        assertEquals("viscals", forms.getAdjective().getPredicative());
        assertEquals("viscla", forms.getAdjective().getFSingular());
        assertEquals("viscals", forms.getAdjective().getMPlural());
        assertEquals("visclas", forms.getAdjective().getFPlural());
        // exception, as accent is on the first syllable. Correct would be visclamein, but checking the regular pattern
        assertEquals("viscalmein", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule4() {
        forms = generator.generateForms("4", "giuven");
        assertEquals("giuven", forms.getAdjective().getMSingular());
        assertEquals("giuvens", forms.getAdjective().getPredicative());
        assertEquals("giuvna", forms.getAdjective().getFSingular());
        assertEquals("giuvens", forms.getAdjective().getMPlural());
        assertEquals("giuvnas", forms.getAdjective().getFPlural());
        assertEquals("giuvnamein", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule6() {
        forms = generator.generateForms("6", "pauper");
        assertEquals("pauper", forms.getAdjective().getMSingular());
        assertEquals("paupers", forms.getAdjective().getPredicative());
        assertEquals("paupra", forms.getAdjective().getFSingular());
        assertEquals("paupers", forms.getAdjective().getMPlural());
        assertEquals("paupras", forms.getAdjective().getFPlural());
        assertEquals("paupramein", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule7() {
        forms = generator.generateForms("7", "bugliu");
        assertEquals("bugliu", forms.getAdjective().getMSingular());
        assertEquals("buglius", forms.getAdjective().getPredicative());
        assertEquals("buglida", forms.getAdjective().getFSingular());
        assertEquals("bugli", forms.getAdjective().getMPlural());
        assertEquals("buglidas", forms.getAdjective().getFPlural());
        assertEquals("buglidamein", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule8() {
        forms = generator.generateForms("8", "barsau");
        assertEquals("barsau", forms.getAdjective().getMSingular());
        assertEquals("barsaus", forms.getAdjective().getPredicative());
        assertEquals("barsada", forms.getAdjective().getFSingular());
        assertEquals("barsai", forms.getAdjective().getMPlural());
        assertEquals("barsadas", forms.getAdjective().getFPlural());
        assertEquals("barsadamein", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule10() {
        forms = generator.generateForms("10", "tschuf");
        assertEquals("tschuf", forms.getAdjective().getMSingular());
        assertEquals("tschufs", forms.getAdjective().getPredicative());
        assertEquals("tschuffa", forms.getAdjective().getFSingular());
        assertEquals("tschufs", forms.getAdjective().getMPlural());
        assertEquals("tschuffas", forms.getAdjective().getFPlural());
        assertEquals("tschuffamein", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRuleSpecialAdverbial() {
        forms = generator.generateForms("1", "final");
        assertEquals("final", forms.getAdjective().getMSingular());
        assertEquals("finals", forms.getAdjective().getPredicative());
        assertEquals("finala", forms.getAdjective().getFSingular());
        assertEquals("finals", forms.getAdjective().getMPlural());
        assertEquals("finalas", forms.getAdjective().getFPlural());
        assertEquals("finalmein", forms.getAdjective().getAdverbialForm());
    }
}
