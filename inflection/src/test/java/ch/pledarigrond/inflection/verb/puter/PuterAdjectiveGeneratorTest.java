package ch.pledarigrond.inflection.verb.puter;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.adjective.puter.PuterAdjectiveGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PuterAdjectiveGeneratorTest {

    private static PuterAdjectiveGenerator generator;
    private InflectionDto forms;

    @BeforeAll
    public static void initialize() {
        generator = new PuterAdjectiveGenerator();
    }

    @Test
    public void testRule1() {
        forms = generator.generateForms("1", "puril");
        assertEquals(forms.getAdjective().getMSingular(), "puril");
        assertEquals(forms.getAdjective().getFSingular(), "purila");
        assertEquals(forms.getAdjective().getMPlural(), "purils");
        assertEquals(forms.getAdjective().getFPlural(), "purilas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "purilamaing");
    }

    @Test
    public void testRule2() {
        forms = generator.generateForms("2", "asper");
        assertEquals(forms.getAdjective().getMSingular(), "asper");
        assertEquals(forms.getAdjective().getFSingular(), "aspra");
        assertEquals(forms.getAdjective().getMPlural(), "aspers");
        assertEquals(forms.getAdjective().getFPlural(), "aspras");
        assertEquals(forms.getAdjective().getAdverbialForm(), "aspermaing");
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("3", "debel");
        assertEquals(forms.getAdjective().getMSingular(), "debel");
        assertEquals(forms.getAdjective().getFSingular(), "debla");
        assertEquals(forms.getAdjective().getMPlural(), "debels");
        assertEquals(forms.getAdjective().getFPlural(), "deblas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "debelmaing");

        forms = generator.generateForms("3", "staungel");
        assertEquals(forms.getAdjective().getMSingular(), "staungel");
        assertEquals(forms.getAdjective().getFSingular(), "staungla");
        assertEquals(forms.getAdjective().getMPlural(), "staungels");
        assertEquals(forms.getAdjective().getFPlural(), "staunglas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "staungelmaing");

        forms = generator.generateForms("3", "daschütel");
        assertEquals(forms.getAdjective().getMSingular(), "daschütel");
        assertEquals(forms.getAdjective().getFSingular(), "daschütla");
        assertEquals(forms.getAdjective().getMPlural(), "daschütels");
        assertEquals(forms.getAdjective().getFPlural(), "daschütlas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "daschütelmaing");

        forms = generator.generateForms("3", "crettaivel");
        assertEquals(forms.getAdjective().getMSingular(), "crettaivel");
        assertEquals(forms.getAdjective().getFSingular(), "crettaivla");
        assertEquals(forms.getAdjective().getMPlural(), "crettaivels");
        assertEquals(forms.getAdjective().getFPlural(), "crettaivlas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "crettaivelmaing");
    }

    @Test
    public void testRule4() {
        forms = generator.generateForms("4", "mediocar");
        assertEquals(forms.getAdjective().getMSingular(), "mediocar");
        assertEquals(forms.getAdjective().getFSingular(), "mediocra");
        assertEquals(forms.getAdjective().getMPlural(), "mediocars");
        assertEquals(forms.getAdjective().getFPlural(), "mediocras");
        assertEquals(forms.getAdjective().getAdverbialForm(), "mediocramaing");
    }

    @Test
    public void testRule5() {
        forms = generator.generateForms("5", "giuven");
        assertEquals(forms.getAdjective().getMSingular(), "giuven");
        assertEquals(forms.getAdjective().getFSingular(), "giuvna");
        assertEquals(forms.getAdjective().getMPlural(), "giuvens");
        assertEquals(forms.getAdjective().getFPlural(), "giuvnas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "giuvnamaing");

        forms = generator.generateForms("5", "cotschen");
        assertEquals(forms.getAdjective().getMSingular(), "cotschen");
        assertEquals(forms.getAdjective().getFSingular(), "cotschna");
        assertEquals(forms.getAdjective().getMPlural(), "cotschens");
        assertEquals(forms.getAdjective().getFPlural(), "cotschnas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "cotschnamaing");
    }

    @Test
    public void testRule6() {
        forms = generator.generateForms("6", "bellischem");
        assertEquals(forms.getAdjective().getMSingular(), "bellischem");
        assertEquals(forms.getAdjective().getFSingular(), "bellischma");
        assertEquals(forms.getAdjective().getMPlural(), "bellischems");
        assertEquals(forms.getAdjective().getFPlural(), "bellischmas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "bellischmamaing");
    }

    @Test
    public void testRule7() {
        forms = generator.generateForms("7", "larg");
        assertEquals(forms.getAdjective().getMSingular(), "larg");
        assertEquals(forms.getAdjective().getFSingular(), "largia");
        assertEquals(forms.getAdjective().getMPlural(), "largs");
        assertEquals(forms.getAdjective().getFPlural(), "largias");
        assertEquals(forms.getAdjective().getAdverbialForm(), "largiamaing");
    }

    @Test
    public void testRule8() {
        forms = generator.generateForms("8", "vegl");
        assertEquals(forms.getAdjective().getMSingular(), "vegl");
        assertEquals(forms.getAdjective().getFSingular(), "veglia");
        assertEquals(forms.getAdjective().getMPlural(), "vegls");
        assertEquals(forms.getAdjective().getFPlural(), "veglias");
        assertEquals(forms.getAdjective().getAdverbialForm(), "vegliamaing");
    }

    @Test
    public void testRule9() {
        forms = generator.generateForms("9", "purto");
        assertEquals(forms.getAdjective().getMSingular(), "purto");
        assertEquals(forms.getAdjective().getFSingular(), "purteda");
        assertEquals(forms.getAdjective().getMPlural(), "purtos");
        assertEquals(forms.getAdjective().getFPlural(), "purtedas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "purtedamaing");
    }

    @Test
    public void testRule10() {
        forms = generator.generateForms("10", "bainvgnieu");
        assertEquals(forms.getAdjective().getMSingular(), "bainvgnieu");
        assertEquals(forms.getAdjective().getFSingular(), "bainvgnida");
        assertEquals(forms.getAdjective().getMPlural(), "bainvgnieus");
        assertEquals(forms.getAdjective().getFPlural(), "bainvgnidas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "bainvgnidamaing");
    }

    @Test
    public void testRule11() {
        forms = generator.generateForms("11", "pluffer");
        assertEquals(forms.getAdjective().getMSingular(), "pluffer");
        assertEquals(forms.getAdjective().getFSingular(), "plufra");
        assertEquals(forms.getAdjective().getMPlural(), "pluffers");
        assertEquals(forms.getAdjective().getFPlural(), "plufras");
        assertEquals(forms.getAdjective().getAdverbialForm(), "pluffermaing");

        forms = generator.generateForms("11", "mellan");
        assertEquals(forms.getAdjective().getMSingular(), "mellan");
        assertEquals(forms.getAdjective().getFSingular(), "melna");
        assertEquals(forms.getAdjective().getMPlural(), "mellans");
        assertEquals(forms.getAdjective().getFPlural(), "melnas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "melnamaing");

        forms = generator.generateForms("11", "prossem");
        assertEquals(forms.getAdjective().getMSingular(), "prossem");
        assertEquals(forms.getAdjective().getFSingular(), "prosma");
        assertEquals(forms.getAdjective().getMPlural(), "prossems");
        assertEquals(forms.getAdjective().getFPlural(), "prosmas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "prosmamaing");

        forms = generator.generateForms("11", "svizzer");
        assertEquals(forms.getAdjective().getMSingular(), "svizzer");
        assertEquals(forms.getAdjective().getFSingular(), "svizra");
        assertEquals(forms.getAdjective().getMPlural(), "svizzers");
        assertEquals(forms.getAdjective().getFPlural(), "svizras");
        assertEquals(forms.getAdjective().getAdverbialForm(), "svizzermaing");
    }

    @Test
    public void testRule12() {
        forms = generator.generateForms("12", "fiac");
        assertEquals(forms.getAdjective().getMSingular(), "fiac");
        assertEquals(forms.getAdjective().getFSingular(), "fiacca");
        assertEquals(forms.getAdjective().getMPlural(), "fiacs");
        assertEquals(forms.getAdjective().getFPlural(), "fiaccas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "fiaccamaing");

        forms = generator.generateForms("12", "fop");
        assertEquals(forms.getAdjective().getMSingular(), "fop");
        assertEquals(forms.getAdjective().getFSingular(), "foppa");
        assertEquals(forms.getAdjective().getMPlural(), "fops");
        assertEquals(forms.getAdjective().getFPlural(), "foppas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "foppamaing");
    }

    @Test
    public void testGuessRule1() {
        forms = generator.guessInflection("puril", null, null);
        assertEquals(forms.getInflectionSubtype(), "1");
    }

    @Test
    public void testGuessRule2() {
        forms = generator.guessInflection("asper", null, null);
        assertEquals(forms.getInflectionSubtype(), "2");
    }

    @Test
    public void testGuessRule3() {
        forms = generator.guessInflection("debel", null, null);
        assertEquals(forms.getInflectionSubtype(), "3");

        forms = generator.guessInflection("staungel", null, null);
        assertEquals(forms.getInflectionSubtype(), "3");

        forms = generator.guessInflection("daschütel", null, null);
        assertEquals(forms.getInflectionSubtype(), "3");

        forms = generator.guessInflection("crettaivel", null, null);
        assertEquals(forms.getInflectionSubtype(), "3");
    }

    @Test
    public void testGuessRule4() {
        forms = generator.guessInflection("mediocar", null, null);
        assertEquals(forms.getInflectionSubtype(), "4");
    }


    @Test
    public void testGuessRule5() {
        forms = generator.guessInflection("giuven", null, null);
        assertEquals(forms.getInflectionSubtype(), "5");

        forms = generator.guessInflection("cotschen", null, null);
        assertEquals(forms.getInflectionSubtype(), "5");
    }

    @Test
    public void testGuessRule6() {
        forms = generator.guessInflection("bellischem", null, null);
        assertEquals(forms.getInflectionSubtype(), "6");
    }

    @Test
    public void testGuessRule7() {
        forms = generator.guessInflection("larg", null, null);
        assertEquals(forms.getInflectionSubtype(), "7");
    }

    @Test
    public void testGuessRule8() {
        forms = generator.guessInflection("vegl", null, null);
        assertEquals(forms.getInflectionSubtype(), "8");
    }

    @Test
    public void testGuessRule9() {
        forms = generator.guessInflection("purto", null, null);
        assertEquals(forms.getInflectionSubtype(), "9");
    }

    @Test
    public void testGuessRule10() {
        forms = generator.guessInflection("bainvgnieu", null, null);
        assertEquals(forms.getInflectionSubtype(), "10");
    }

    @Test
    public void testGuessRule11() {
        forms = generator.guessInflection("pluffer", null, null);
        assertEquals(forms.getInflectionSubtype(), "11");

        forms = generator.guessInflection("mellan", null, null);
        assertEquals(forms.getInflectionSubtype(), "11");

        forms = generator.guessInflection("prossem", null, null);
        assertEquals(forms.getInflectionSubtype(), "11");

        forms = generator.guessInflection("svizzer", null, null);
        assertEquals(forms.getInflectionSubtype(), "11");
    }

    @Test
    public void testGuessRule12() {
        forms = generator.guessInflection("fiac", null, null);
        assertEquals(forms.getInflectionSubtype(), "12");

        forms = generator.guessInflection("fop", null, null);
        assertEquals(forms.getInflectionSubtype(), "12");


        // rule 12 should only be used for single syllable words
        forms = generator.guessInflection("electric", null, null);
        assertEquals(forms.getInflectionSubtype(), "1");

        forms = generator.guessInflection("dogmatic", null, null);
        assertEquals(forms.getInflectionSubtype(), "1");

        forms = generator.guessInflection("obligatoric", null, null);
        assertEquals(forms.getInflectionSubtype(), "1");

        forms = generator.guessInflection("difficil", null, null);
        assertEquals(forms.getInflectionSubtype(), "1");
    }
}
