package ch.pledarigrond.inflection.verb.vallader;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.adjective.vallader.ValladerAdjectiveGenerator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ValladerAdjectiveGeneratorTest {

    private static ValladerAdjectiveGenerator generator;
    private InflectionDto forms;

    @BeforeClass
    public static void initialize() {
        generator = new ValladerAdjectiveGenerator();
    }

    @Test
    public void testRule1() {
        forms = generator.generateForms("1", "pauril");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "pauril");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "paurila");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "paurils");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "paurilas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "paurilamaing");
    }

    @Test
    public void testRule2() {
        forms = generator.generateForms("2", "asper");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "asper");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "aspra");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "aspers");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "aspras");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "aspramaing");
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("3", "debel");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "debel");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "debla");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "debels");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "deblas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "debelmaing");

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
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "mediocarmaing");
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
        forms = generator.generateForms("9", "stanguel");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "stanguel");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "stangla");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "stanguels");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "stanglas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "stanguelmaing");
    }

    @Test
    public void testRule10() {
        forms = generator.generateForms("10", "portà");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "portà");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "portada");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "portats");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "portadas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "portadamaing");
    }

    @Test
    public void testRule11() {
        forms = generator.generateForms("10", "vendü");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "vendü");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "vendüda");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "vendüts");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "vendüdas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "vendüdamaing");
    }

    @Test
    public void testRule12() {
        forms = generator.generateForms("10", "impedi");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "impedi");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "impedida");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "impedits");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "impedidas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "impedidamaing");
    }

    @Test
    public void testRule13() {
        forms = generator.generateForms("13", "pluffer");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "pluffer");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "plufra");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "pluffers");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "plufras");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "pluframaing");

        forms = generator.generateForms("13", "prossem");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "prossem");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "prosma");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "prossems");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "prosmas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "prosmamaing");

        forms = generator.generateForms("13", "grossottel");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "grossottel");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "grossotla");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "grossottels");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "grossotlas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "grossottelmaing");

        forms = generator.generateForms("13", "svizzer");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "svizzer");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "svizra");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "svizzers");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "svizras");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "svizramaing");
    }

    @Test
    public void testRule14() {
        forms = generator.generateForms("14", "fiac");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "fiac");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "fiacca");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "fiacs");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "fiaccas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "fiaccamaing");

        forms = generator.generateForms("14", "lom");
        Assert.assertEquals(forms.getAdjective().getMSingular(), "lom");
        Assert.assertEquals(forms.getAdjective().getFSingular(), "lomma");
        Assert.assertEquals(forms.getAdjective().getMPlural(), "loms");
        Assert.assertEquals(forms.getAdjective().getFPlural(), "lommas");
        Assert.assertEquals(forms.getAdjective().getAdverbialForm(), "lommamaing");

        forms = generator.generateForms("14", "fop");
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
        forms = generator.guessInflection("stanguel", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "9");
    }

    @Test
    public void testGuessRule10() {
        forms = generator.guessInflection("portà", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "10");
    }

    @Test
    public void testGuessRule11() {
        forms = generator.guessInflection("vendü", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "11");
    }

    @Test
    public void testGuessRule12() {
        forms = generator.guessInflection("impedi", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "12");
    }

    @Test
    public void testGuessRule13() {
        forms = generator.guessInflection("pluffer", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "13");

        forms = generator.guessInflection("mellan", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "13");

        forms = generator.guessInflection("prossem", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "13");

        forms = generator.guessInflection("svizzer", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "13");
    }

    @Test
    public void testGuessRule14() {
        forms = generator.guessInflection("fiac", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "14");

        forms = generator.guessInflection("lom", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "14");

        forms = generator.guessInflection("fop", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "14");


        // rule 14 should only be used for single syllable words
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
