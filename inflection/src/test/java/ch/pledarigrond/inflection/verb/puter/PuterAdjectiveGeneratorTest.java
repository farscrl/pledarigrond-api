package ch.pledarigrond.inflection.verb.puter;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.adjective.puter.PuterAdjectiveGenerator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class PuterAdjectiveGeneratorTest {

    private static PuterAdjectiveGenerator generator;
    private InflectionDto forms;

    @BeforeClass
    public static void initialize() {
        generator = new PuterAdjectiveGenerator();
    }

    @Test
    public void testRule1() {
        forms = generator.generateForms("1", "puril");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "puril");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "purila");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "purils");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "purilas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "purilamaing");
    }

    @Test
    public void testRule2() {
        forms = generator.generateForms("2", "asper");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "asper");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "aspra");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "aspers");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "aspras");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "aspermaing");
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("3", "debel");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "debel");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "debla");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "debels");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "deblas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "debelmaing");

        forms = generator.generateForms("3", "staungel");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "staungel");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "staungla");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "staungels");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "staunglas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "staungelmaing");

        forms = generator.generateForms("3", "daschütel");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "daschütel");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "daschütla");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "daschütels");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "daschütlas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "daschütelmaing");

        forms = generator.generateForms("3", "crettaivel");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "crettaivel");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "crettaivla");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "crettaivels");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "crettaivlas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "crettaivelmaing");
    }

    @Test
    public void testRule4() {
        forms = generator.generateForms("4", "mediocar");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "mediocar");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "mediocra");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "mediocars");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "mediocras");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "mediocramaing");
    }

    @Test
    public void testRule5() {
        forms = generator.generateForms("5", "giuven");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "giuven");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "giuvna");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "giuvens");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "giuvnas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "giuvnamaing");

        forms = generator.generateForms("5", "cotschen");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "cotschen");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "cotschna");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "cotschens");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "cotschnas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "cotschnamaing");
    }

    @Test
    public void testRule6() {
        forms = generator.generateForms("6", "bellischem");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "bellischem");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "bellischma");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "bellischems");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "bellischmas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "bellischmamaing");
    }

    @Test
    public void testRule7() {
        forms = generator.generateForms("7", "larg");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "larg");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "largia");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "largs");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "largias");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "largiamaing");
    }

    @Test
    public void testRule8() {
        forms = generator.generateForms("8", "vegl");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "vegl");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "veglia");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "vegls");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "veglias");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "vegliamaing");
    }

    @Test
    public void testRule9() {
        forms = generator.generateForms("9", "purto");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "purto");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "purteda");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "purtos");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "purtedas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "purtedamaing");
    }

    @Test
    public void testRule10() {
        forms = generator.generateForms("10", "bainvgnieu");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "bainvgnieu");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "bainvgnida");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "bainvgnieus");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "bainvgnidas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "bainvgnidamaing");
    }

    @Test
    public void testRule11() {
        forms = generator.generateForms("11", "pluffer");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "pluffer");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "plufra");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "pluffers");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "plufras");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "pluffermaing");

        forms = generator.generateForms("11", "mellan");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "mellan");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "melna");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "mellans");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "melnas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "melnamaing");

        forms = generator.generateForms("11", "prossem");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "prossem");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "prosma");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "prossems");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "prosmas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "prosmamaing");

        forms = generator.generateForms("11", "svizzer");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "svizzer");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "svizra");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "svizzers");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "svizras");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "svizzermaing");
    }

    @Test
    public void testRule12() {
        forms = generator.generateForms("12", "fiac");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "fiac");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "fiacca");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "fiacs");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "fiaccas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "fiaccamaing");

        forms = generator.generateForms("12", "fop");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "fop");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "foppa");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "fops");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "foppas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "foppamaing");
    }

    @Test
    public void testGuessRule1() {
        forms = generator.guessInflection("puril", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "1");
    }

    @Test
    public void testGuessRule2() {
        forms = generator.guessInflection("asper", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "2");
    }

    @Test
    public void testGuessRule3() {
        forms = generator.guessInflection("debel", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "3");

        forms = generator.guessInflection("staungel", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "3");

        forms = generator.guessInflection("daschütel", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "3");

        forms = generator.guessInflection("crettaivel", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "3");
    }

    @Test
    public void testGuessRule4() {
        forms = generator.guessInflection("mediocar", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "4");
    }


    @Test
    public void testGuessRule5() {
        forms = generator.guessInflection("giuven", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "5");

        forms = generator.guessInflection("cotschen", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "5");
    }

    @Test
    public void testGuessRule6() {
        forms = generator.guessInflection("bellischem", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "6");
    }

    @Test
    public void testGuessRule7() {
        forms = generator.guessInflection("larg", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "7");
    }

    @Test
    public void testGuessRule8() {
        forms = generator.guessInflection("vegl", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "8");
    }

    @Test
    public void testGuessRule9() {
        forms = generator.guessInflection("purto", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "9");
    }

    @Test
    public void testGuessRule10() {
        forms = generator.guessInflection("bainvgnieu", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "10");
    }

    @Test
    public void testGuessRule11() {
        forms = generator.guessInflection("pluffer", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "11");

        forms = generator.guessInflection("mellan", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "11");

        forms = generator.guessInflection("prossem", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "11");

        forms = generator.guessInflection("svizzer", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "11");
    }

    @Test
    public void testGuessRule12() {
        forms = generator.guessInflection("fiac", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "12");

        forms = generator.guessInflection("fop", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "12");


        // rule 12 should only be used for single syllable words
        forms = generator.guessInflection("electric", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "1");

        forms = generator.guessInflection("dogmatic", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "1");

        forms = generator.guessInflection("obligatoric", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "1");

        forms = generator.guessInflection("difficil", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "1");
    }
}
