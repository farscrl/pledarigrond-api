package ch.pledarigrond.inflection.verb.vallader;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.adjective.vallader.ValladerAdjectiveGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValladerAdjectiveGeneratorTest {

    private static ValladerAdjectiveGenerator generator;
    private InflectionDto forms;

    @BeforeAll
    public static void initialize() {
        generator = new ValladerAdjectiveGenerator();
    }

    @Test
    public void testRule1() {
        forms = generator.generateForms("1", "pauril");
        assertEquals(forms.getAdjective().getMSingular(), "pauril");
        assertEquals(forms.getAdjective().getFSingular(), "paurila");
        assertEquals(forms.getAdjective().getMPlural(), "paurils");
        assertEquals(forms.getAdjective().getFPlural(), "paurilas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "paurilamaing");
    }

    @Test
    public void testRule2() {
        forms = generator.generateForms("2", "asper");
        assertEquals(forms.getAdjective().getMSingular(), "asper");
        assertEquals(forms.getAdjective().getFSingular(), "aspra");
        assertEquals(forms.getAdjective().getMPlural(), "aspers");
        assertEquals(forms.getAdjective().getFPlural(), "aspras");
        assertEquals(forms.getAdjective().getAdverbialForm(), "aspramaing");
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("3", "debel");
        assertEquals(forms.getAdjective().getMSingular(), "debel");
        assertEquals(forms.getAdjective().getFSingular(), "debla");
        assertEquals(forms.getAdjective().getMPlural(), "debels");
        assertEquals(forms.getAdjective().getFPlural(), "deblas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "debelmaing");

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
        assertEquals(forms.getAdjective().getAdverbialForm(), "mediocarmaing");
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
        forms = generator.generateForms("9", "stanguel");
        assertEquals(forms.getAdjective().getMSingular(), "stanguel");
        assertEquals(forms.getAdjective().getFSingular(), "stangla");
        assertEquals(forms.getAdjective().getMPlural(), "stanguels");
        assertEquals(forms.getAdjective().getFPlural(), "stanglas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "stanguelmaing");
    }

    @Test
    public void testRule10() {
        forms = generator.generateForms("10", "portà");
        assertEquals(forms.getAdjective().getMSingular(), "portà");
        assertEquals(forms.getAdjective().getFSingular(), "portada");
        assertEquals(forms.getAdjective().getMPlural(), "portats");
        assertEquals(forms.getAdjective().getFPlural(), "portadas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "portadamaing");
    }

    @Test
    public void testRule11() {
        forms = generator.generateForms("10", "vendü");
        assertEquals(forms.getAdjective().getMSingular(), "vendü");
        assertEquals(forms.getAdjective().getFSingular(), "vendüda");
        assertEquals(forms.getAdjective().getMPlural(), "vendüts");
        assertEquals(forms.getAdjective().getFPlural(), "vendüdas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "vendüdamaing");
    }

    @Test
    public void testRule12() {
        forms = generator.generateForms("10", "impedi");
        assertEquals(forms.getAdjective().getMSingular(), "impedi");
        assertEquals(forms.getAdjective().getFSingular(), "impedida");
        assertEquals(forms.getAdjective().getMPlural(), "impedits");
        assertEquals(forms.getAdjective().getFPlural(), "impedidas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "impedidamaing");
    }

    @Test
    public void testRule13() {
        forms = generator.generateForms("13", "pluffer");
        assertEquals(forms.getAdjective().getMSingular(), "pluffer");
        assertEquals(forms.getAdjective().getFSingular(), "plufra");
        assertEquals(forms.getAdjective().getMPlural(), "pluffers");
        assertEquals(forms.getAdjective().getFPlural(), "plufras");
        assertEquals(forms.getAdjective().getAdverbialForm(), "pluframaing");

        forms = generator.generateForms("13", "prossem");
        assertEquals(forms.getAdjective().getMSingular(), "prossem");
        assertEquals(forms.getAdjective().getFSingular(), "prosma");
        assertEquals(forms.getAdjective().getMPlural(), "prossems");
        assertEquals(forms.getAdjective().getFPlural(), "prosmas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "prosmamaing");

        forms = generator.generateForms("13", "grossottel");
        assertEquals(forms.getAdjective().getMSingular(), "grossottel");
        assertEquals(forms.getAdjective().getFSingular(), "grossotla");
        assertEquals(forms.getAdjective().getMPlural(), "grossottels");
        assertEquals(forms.getAdjective().getFPlural(), "grossotlas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "grossottelmaing");

        forms = generator.generateForms("13", "svizzer");
        assertEquals(forms.getAdjective().getMSingular(), "svizzer");
        assertEquals(forms.getAdjective().getFSingular(), "svizra");
        assertEquals(forms.getAdjective().getMPlural(), "svizzers");
        assertEquals(forms.getAdjective().getFPlural(), "svizras");
        assertEquals(forms.getAdjective().getAdverbialForm(), "svizramaing");
    }

    @Test
    public void testRule14() {
        forms = generator.generateForms("14", "fiac");
        assertEquals(forms.getAdjective().getMSingular(), "fiac");
        assertEquals(forms.getAdjective().getFSingular(), "fiacca");
        assertEquals(forms.getAdjective().getMPlural(), "fiacs");
        assertEquals(forms.getAdjective().getFPlural(), "fiaccas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "fiaccamaing");

        forms = generator.generateForms("14", "lom");
        assertEquals(forms.getAdjective().getMSingular(), "lom");
        assertEquals(forms.getAdjective().getFSingular(), "lomma");
        assertEquals(forms.getAdjective().getMPlural(), "loms");
        assertEquals(forms.getAdjective().getFPlural(), "lommas");
        assertEquals(forms.getAdjective().getAdverbialForm(), "lommamaing");

        forms = generator.generateForms("14", "fop");
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
        forms = generator.guessInflection("stanguel", null, null);
        assertEquals(forms.getInflectionSubtype(), "9");
    }

    @Test
    public void testGuessRule10() {
        forms = generator.guessInflection("portà", null, null);
        assertEquals(forms.getInflectionSubtype(), "10");
    }

    @Test
    public void testGuessRule11() {
        forms = generator.guessInflection("vendü", null, null);
        assertEquals(forms.getInflectionSubtype(), "11");
    }

    @Test
    public void testGuessRule12() {
        forms = generator.guessInflection("impedi", null, null);
        assertEquals(forms.getInflectionSubtype(), "12");
    }

    @Test
    public void testGuessRule13() {
        forms = generator.guessInflection("pluffer", null, null);
        assertEquals(forms.getInflectionSubtype(), "13");

        forms = generator.guessInflection("mellan", null, null);
        assertEquals(forms.getInflectionSubtype(), "13");

        forms = generator.guessInflection("prossem", null, null);
        assertEquals(forms.getInflectionSubtype(), "13");

        forms = generator.guessInflection("svizzer", null, null);
        assertEquals(forms.getInflectionSubtype(), "13");
    }

    @Test
    public void testGuessRule14() {
        forms = generator.guessInflection("fiac", null, null);
        assertEquals(forms.getInflectionSubtype(), "14");

        forms = generator.guessInflection("lom", null, null);
        assertEquals(forms.getInflectionSubtype(), "14");

        forms = generator.guessInflection("fop", null, null);
        assertEquals(forms.getInflectionSubtype(), "14");


        // rule 14 should only be used for single syllable words
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
