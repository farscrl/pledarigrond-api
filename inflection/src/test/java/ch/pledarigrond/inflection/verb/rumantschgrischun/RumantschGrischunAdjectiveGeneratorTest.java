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
        assertEquals("puril", forms.getAdjective().getMSingular());
        assertEquals("purila", forms.getAdjective().getFSingular());
        assertEquals("purils", forms.getAdjective().getMPlural());
        assertEquals("purilas", forms.getAdjective().getFPlural());
        assertEquals("purilmain", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule2() {
        forms = generator.generateForms("2", "veder");
        assertEquals("veder", forms.getAdjective().getMSingular());
        assertEquals("vedra", forms.getAdjective().getFSingular());
        assertEquals("veders", forms.getAdjective().getMPlural());
        assertEquals("vedras", forms.getAdjective().getFPlural());
        assertEquals("vedramain", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("3", "admirabel");
        assertEquals("admirabel", forms.getAdjective().getMSingular());
        assertEquals("admirabla", forms.getAdjective().getFSingular());
        assertEquals("admirabels", forms.getAdjective().getMPlural());
        assertEquals("admirablas", forms.getAdjective().getFPlural());
        assertEquals("admirablamain", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule4() {
        forms = generator.generateForms("4", "mediocher");
        assertEquals("mediocher", forms.getAdjective().getMSingular());
        assertEquals("mediocra", forms.getAdjective().getFSingular());
        assertEquals("mediochers", forms.getAdjective().getMPlural());
        assertEquals("mediocras", forms.getAdjective().getFPlural());
        assertEquals("mediocramain", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule5() {
        forms = generator.generateForms("5", "amiaivel");
        assertEquals("amiaivel", forms.getAdjective().getMSingular());
        assertEquals("amiaivla", forms.getAdjective().getFSingular());
        assertEquals("amiaivels", forms.getAdjective().getMPlural());
        assertEquals("amiaivlas", forms.getAdjective().getFPlural());
        assertEquals("amiaivlamain", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule6() {
        forms = generator.generateForms("6", "giuven");
        assertEquals("giuven", forms.getAdjective().getMSingular());
        assertEquals("giuvna", forms.getAdjective().getFSingular());
        assertEquals("giuvens", forms.getAdjective().getMPlural());
        assertEquals("giuvnas", forms.getAdjective().getFPlural());
        assertEquals("giuvnamain", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule7() {
        forms = generator.generateForms("7", "isolà");
        assertEquals("isolà", forms.getAdjective().getMSingular());
        assertEquals("isolada", forms.getAdjective().getFSingular());
        assertEquals("isolads", forms.getAdjective().getMPlural());
        assertEquals("isoladas", forms.getAdjective().getFPlural());
        assertEquals("isoladamain", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule8() {
        forms = generator.generateForms("8", "sclerì");
        assertEquals("sclerì", forms.getAdjective().getMSingular());
        assertEquals("sclerida", forms.getAdjective().getFSingular());
        assertEquals("sclerids", forms.getAdjective().getMPlural());
        assertEquals("scleridas", forms.getAdjective().getFPlural());
        assertEquals("scleridamain", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule9() {
        forms = generator.generateForms("9", "tschuf");
        assertEquals("tschuf", forms.getAdjective().getMSingular());
        assertEquals("tschuffa", forms.getAdjective().getFSingular());
        assertEquals("tschufs", forms.getAdjective().getMPlural());
        assertEquals("tschuffas", forms.getAdjective().getFPlural());
        assertEquals("tschuffamain", forms.getAdjective().getAdverbialForm());

        forms = generator.generateForms("9", "rut");
        assertEquals("rut", forms.getAdjective().getMSingular());
        assertEquals("rutta", forms.getAdjective().getFSingular());
        assertEquals("ruts", forms.getAdjective().getMPlural());
        assertEquals("ruttas", forms.getAdjective().getFPlural());
        assertEquals("ruttamain", forms.getAdjective().getAdverbialForm());
    }
}
