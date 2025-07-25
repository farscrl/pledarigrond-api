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
        assertEquals("puril", forms.getAdjective().getMSingular());
        assertEquals("purila", forms.getAdjective().getFSingular());
        assertEquals("purils", forms.getAdjective().getMPlural());
        assertEquals("purilas", forms.getAdjective().getFPlural());
        assertEquals("purilameing", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule2() {
        forms = generator.generateForms("2", "veader");
        assertEquals("veader", forms.getAdjective().getMSingular());
        assertEquals("veadra", forms.getAdjective().getFSingular());
        assertEquals("veaders", forms.getAdjective().getMPlural());
        assertEquals("veadras", forms.getAdjective().getFPlural());
        assertEquals("veadrameing", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("3", "admirabel");
        assertEquals("admirabel", forms.getAdjective().getMSingular());
        assertEquals("admirabla", forms.getAdjective().getFSingular());
        assertEquals("admirabels", forms.getAdjective().getMPlural());
        assertEquals("admirablas", forms.getAdjective().getFPlural());
        assertEquals("admirablameing", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule4() {
        forms = generator.generateForms("4", "legher");
        assertEquals("legher", forms.getAdjective().getMSingular());
        assertEquals("legra", forms.getAdjective().getFSingular());
        assertEquals("leghers", forms.getAdjective().getMPlural());
        assertEquals("legras", forms.getAdjective().getFPlural());
        assertEquals("legrameing", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule5() {
        forms = generator.generateForms("5", "vantirevel");
        assertEquals("vantirevel", forms.getAdjective().getMSingular());
        assertEquals("vantirevla", forms.getAdjective().getFSingular());
        assertEquals("vantirevels", forms.getAdjective().getMPlural());
        assertEquals("vantirevlas", forms.getAdjective().getFPlural());
        assertEquals("vantirevlameing", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule6() {
        forms = generator.generateForms("6", "giuven");
        assertEquals("giuven", forms.getAdjective().getMSingular());
        assertEquals("giuvna", forms.getAdjective().getFSingular());
        assertEquals("giuvens", forms.getAdjective().getMPlural());
        assertEquals("giuvnas", forms.getAdjective().getFPlural());
        assertEquals("giuvnameing", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule7() {
        forms = generator.generateForms("7", "surprieu");
        assertEquals("surprieu", forms.getAdjective().getMSingular());
        assertEquals("surprida", forms.getAdjective().getFSingular());
        assertEquals("surprieus", forms.getAdjective().getMPlural());
        assertEquals("surpridas", forms.getAdjective().getFPlural());
        assertEquals("surpridameing", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule8() {
        forms = generator.generateForms("8", "ocupo");
        assertEquals("ocupo", forms.getAdjective().getMSingular());
        assertEquals("ocupada", forms.getAdjective().getFSingular());
        assertEquals("ocupos", forms.getAdjective().getMPlural());
        assertEquals("ocupadas", forms.getAdjective().getFPlural());
        assertEquals("ocupadameing", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule9() {
        forms = generator.generateForms("9", "vescel");
        assertEquals("vescel", forms.getAdjective().getMSingular());
        assertEquals("vescla", forms.getAdjective().getFSingular());
        assertEquals("vescels", forms.getAdjective().getMPlural());
        assertEquals("vesclas", forms.getAdjective().getFPlural());
        assertEquals("vesclameing", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule10() {
        forms = generator.generateForms("10", "pover");
        assertEquals("pover", forms.getAdjective().getMSingular());
        assertEquals("povra", forms.getAdjective().getFSingular());
        assertEquals("povers", forms.getAdjective().getMPlural());
        assertEquals("povras", forms.getAdjective().getFPlural());
        assertEquals("povrameing", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule11() {
        forms = generator.generateForms("11", "orfen");
        assertEquals("orfen", forms.getAdjective().getMSingular());
        assertEquals("orfna", forms.getAdjective().getFSingular());
        assertEquals("orfens", forms.getAdjective().getMPlural());
        assertEquals("orfnas", forms.getAdjective().getFPlural());
        assertEquals("orfnameing", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule12() {
        forms = generator.generateForms("12", "detaglieu");
        assertEquals("detaglieu", forms.getAdjective().getMSingular());
        assertEquals("detaglieada", forms.getAdjective().getFSingular());
        assertEquals("detaglieus", forms.getAdjective().getMPlural());
        assertEquals("detaglieadas", forms.getAdjective().getFPlural());
        assertEquals("detaglieadameing", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule13() {
        forms = generator.generateForms("13", "partutgieu");
        assertEquals("partutgieu", forms.getAdjective().getMSingular());
        assertEquals("partutgeada", forms.getAdjective().getFSingular());
        assertEquals("partutgieus", forms.getAdjective().getMPlural());
        assertEquals("partutgeadas", forms.getAdjective().getFPlural());
        assertEquals("partutgeadameing", forms.getAdjective().getAdverbialForm());
    }
}
