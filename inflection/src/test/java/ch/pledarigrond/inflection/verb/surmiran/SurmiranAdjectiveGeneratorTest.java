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
        assertEquals("puril", forms.getAdjective().getMSingular());
        assertEquals("purila", forms.getAdjective().getFSingular());
        assertEquals("purils", forms.getAdjective().getMPlural());
        assertEquals("purilas", forms.getAdjective().getFPlural());
        assertEquals("purilamaintg", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule2() {
        forms = generator.generateForms("2", "veder");
        assertEquals("veder", forms.getAdjective().getMSingular());
        assertEquals("vedra", forms.getAdjective().getFSingular());
        assertEquals("veders", forms.getAdjective().getMPlural());
        assertEquals("vedras", forms.getAdjective().getFPlural());
        assertEquals("vedramaintg", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("3", "admirabel");
        assertEquals("admirabel", forms.getAdjective().getMSingular());
        assertEquals("admirabla", forms.getAdjective().getFSingular());
        assertEquals("admirabels", forms.getAdjective().getMPlural());
        assertEquals("admirablas", forms.getAdjective().getFPlural());
        assertEquals("admirablamaintg", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule4() {
        forms = generator.generateForms("4", "zacher");
        assertEquals("zacher", forms.getAdjective().getMSingular());
        assertEquals("zacra", forms.getAdjective().getFSingular());
        assertEquals("zachers", forms.getAdjective().getMPlural());
        assertEquals("zacras", forms.getAdjective().getFPlural());
        assertEquals("zacramaintg", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule5() {
        forms = generator.generateForms("5", "capavel");
        assertEquals("capavel", forms.getAdjective().getMSingular());
        assertEquals("capavla", forms.getAdjective().getFSingular());
        assertEquals("capavels", forms.getAdjective().getMPlural());
        assertEquals("capavlas", forms.getAdjective().getFPlural());
        assertEquals("capavlamaintg", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule6() {
        forms = generator.generateForms("6", "gioven");
        assertEquals("gioven", forms.getAdjective().getMSingular());
        assertEquals("giovna", forms.getAdjective().getFSingular());
        assertEquals("giovens", forms.getAdjective().getMPlural());
        assertEquals("giovnas", forms.getAdjective().getFPlural());
        assertEquals("giovnamaintg", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule7() {
        forms = generator.generateForms("7", "distinguia");
        assertEquals("distinguia", forms.getAdjective().getMSingular());
        assertEquals("distingueida", forms.getAdjective().getFSingular());
        assertEquals("distinguias", forms.getAdjective().getMPlural());
        assertEquals("distingueidas", forms.getAdjective().getFPlural());
        assertEquals("distingueidamaintg", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule8() {
        forms = generator.generateForms("8", "attempo");
        assertEquals("attempo", forms.getAdjective().getMSingular());
        assertEquals("attempada", forms.getAdjective().getFSingular());
        assertEquals("attempos", forms.getAdjective().getMPlural());
        assertEquals("attempadas", forms.getAdjective().getFPlural());
        assertEquals("attempadamaintg", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule9() {
        forms = generator.generateForms("9", "schlaschea");
        assertEquals("schlaschea", forms.getAdjective().getMSingular());
        assertEquals("schlascheda", forms.getAdjective().getFSingular());
        assertEquals("schlascheas", forms.getAdjective().getMPlural());
        assertEquals("schlaschedas", forms.getAdjective().getFPlural());
        assertEquals("schlaschedamaintg", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule10() {
        forms = generator.generateForms("10", "tschuf");
        assertEquals("tschuf", forms.getAdjective().getMSingular());
        assertEquals("tschuffa", forms.getAdjective().getFSingular());
        assertEquals("tschufs", forms.getAdjective().getMPlural());
        assertEquals("tschuffas", forms.getAdjective().getFPlural());
        assertEquals("tschuffamaintg", forms.getAdjective().getAdverbialForm());
    }
}
