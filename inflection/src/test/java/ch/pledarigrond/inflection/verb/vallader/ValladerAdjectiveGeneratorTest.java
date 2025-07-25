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
        assertEquals("pauril", forms.getAdjective().getMSingular());
        assertEquals("paurila", forms.getAdjective().getFSingular());
        assertEquals("paurils", forms.getAdjective().getMPlural());
        assertEquals("paurilas", forms.getAdjective().getFPlural());
        assertEquals("paurilamaing", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule2() {
        forms = generator.generateForms("2", "asper");
        assertEquals("asper", forms.getAdjective().getMSingular());
        assertEquals("aspra", forms.getAdjective().getFSingular());
        assertEquals("aspers", forms.getAdjective().getMPlural());
        assertEquals("aspras", forms.getAdjective().getFPlural());
        assertEquals("aspramaing", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("3", "debel");
        assertEquals("debel", forms.getAdjective().getMSingular());
        assertEquals("debla", forms.getAdjective().getFSingular());
        assertEquals("debels", forms.getAdjective().getMPlural());
        assertEquals("deblas", forms.getAdjective().getFPlural());
        assertEquals("debelmaing", forms.getAdjective().getAdverbialForm());

        forms = generator.generateForms("3", "crettaivel");
        assertEquals("crettaivel", forms.getAdjective().getMSingular());
        assertEquals("crettaivla", forms.getAdjective().getFSingular());
        assertEquals("crettaivels", forms.getAdjective().getMPlural());
        assertEquals("crettaivlas", forms.getAdjective().getFPlural());
        assertEquals("crettaivelmaing", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule4() {
        forms = generator.generateForms("4", "mediocar");
        assertEquals("mediocar", forms.getAdjective().getMSingular());
        assertEquals("mediocra", forms.getAdjective().getFSingular());
        assertEquals("mediocars", forms.getAdjective().getMPlural());
        assertEquals("mediocras", forms.getAdjective().getFPlural());
        assertEquals("mediocarmaing", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule5() {
        forms = generator.generateForms("5", "giuven");
        assertEquals("giuven", forms.getAdjective().getMSingular());
        assertEquals("giuvna", forms.getAdjective().getFSingular());
        assertEquals("giuvens", forms.getAdjective().getMPlural());
        assertEquals("giuvnas", forms.getAdjective().getFPlural());
        assertEquals("giuvnamaing", forms.getAdjective().getAdverbialForm());

        forms = generator.generateForms("5", "cotschen");
        assertEquals("cotschen", forms.getAdjective().getMSingular());
        assertEquals("cotschna", forms.getAdjective().getFSingular());
        assertEquals("cotschens", forms.getAdjective().getMPlural());
        assertEquals("cotschnas", forms.getAdjective().getFPlural());
        assertEquals("cotschnamaing", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule6() {
        forms = generator.generateForms("6", "bellischem");
        assertEquals("bellischem", forms.getAdjective().getMSingular());
        assertEquals("bellischma", forms.getAdjective().getFSingular());
        assertEquals("bellischems", forms.getAdjective().getMPlural());
        assertEquals("bellischmas", forms.getAdjective().getFPlural());
        assertEquals("bellischmamaing", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule7() {
        forms = generator.generateForms("7", "larg");
        assertEquals("larg", forms.getAdjective().getMSingular());
        assertEquals("largia", forms.getAdjective().getFSingular());
        assertEquals("largs", forms.getAdjective().getMPlural());
        assertEquals("largias", forms.getAdjective().getFPlural());
        assertEquals("largiamaing", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule8() {
        forms = generator.generateForms("8", "vegl");
        assertEquals("vegl", forms.getAdjective().getMSingular());
        assertEquals("veglia", forms.getAdjective().getFSingular());
        assertEquals("vegls", forms.getAdjective().getMPlural());
        assertEquals("veglias", forms.getAdjective().getFPlural());
        assertEquals("vegliamaing", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule9() {
        forms = generator.generateForms("9", "stanguel");
        assertEquals("stanguel", forms.getAdjective().getMSingular());
        assertEquals("stangla", forms.getAdjective().getFSingular());
        assertEquals("stanguels", forms.getAdjective().getMPlural());
        assertEquals("stanglas", forms.getAdjective().getFPlural());
        assertEquals("stanguelmaing", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule10() {
        forms = generator.generateForms("10", "portà");
        assertEquals("portà", forms.getAdjective().getMSingular());
        assertEquals("portada", forms.getAdjective().getFSingular());
        assertEquals("portats", forms.getAdjective().getMPlural());
        assertEquals("portadas", forms.getAdjective().getFPlural());
        assertEquals("portadamaing", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule11() {
        forms = generator.generateForms("10", "vendü");
        assertEquals("vendü", forms.getAdjective().getMSingular());
        assertEquals("vendüda", forms.getAdjective().getFSingular());
        assertEquals("vendüts", forms.getAdjective().getMPlural());
        assertEquals("vendüdas", forms.getAdjective().getFPlural());
        assertEquals("vendüdamaing", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule12() {
        forms = generator.generateForms("10", "impedi");
        assertEquals("impedi", forms.getAdjective().getMSingular());
        assertEquals("impedida", forms.getAdjective().getFSingular());
        assertEquals("impedits", forms.getAdjective().getMPlural());
        assertEquals("impedidas", forms.getAdjective().getFPlural());
        assertEquals("impedidamaing", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule13() {
        forms = generator.generateForms("13", "pluffer");
        assertEquals("pluffer", forms.getAdjective().getMSingular());
        assertEquals("plufra", forms.getAdjective().getFSingular());
        assertEquals("pluffers", forms.getAdjective().getMPlural());
        assertEquals("plufras", forms.getAdjective().getFPlural());
        assertEquals("pluframaing", forms.getAdjective().getAdverbialForm());

        forms = generator.generateForms("13", "prossem");
        assertEquals("prossem", forms.getAdjective().getMSingular());
        assertEquals("prosma", forms.getAdjective().getFSingular());
        assertEquals("prossems", forms.getAdjective().getMPlural());
        assertEquals("prosmas", forms.getAdjective().getFPlural());
        assertEquals("prosmamaing", forms.getAdjective().getAdverbialForm());

        forms = generator.generateForms("13", "grossottel");
        assertEquals("grossottel", forms.getAdjective().getMSingular());
        assertEquals("grossotla", forms.getAdjective().getFSingular());
        assertEquals("grossottels", forms.getAdjective().getMPlural());
        assertEquals("grossotlas", forms.getAdjective().getFPlural());
        assertEquals("grossottelmaing", forms.getAdjective().getAdverbialForm());

        forms = generator.generateForms("13", "svizzer");
        assertEquals("svizzer", forms.getAdjective().getMSingular());
        assertEquals("svizra", forms.getAdjective().getFSingular());
        assertEquals("svizzers", forms.getAdjective().getMPlural());
        assertEquals("svizras", forms.getAdjective().getFPlural());
        assertEquals("svizramaing", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testRule14() {
        forms = generator.generateForms("14", "fiac");
        assertEquals("fiac", forms.getAdjective().getMSingular());
        assertEquals("fiacca", forms.getAdjective().getFSingular());
        assertEquals("fiacs", forms.getAdjective().getMPlural());
        assertEquals("fiaccas", forms.getAdjective().getFPlural());
        assertEquals("fiaccamaing", forms.getAdjective().getAdverbialForm());

        forms = generator.generateForms("14", "lom");
        assertEquals("lom", forms.getAdjective().getMSingular());
        assertEquals("lomma", forms.getAdjective().getFSingular());
        assertEquals("loms", forms.getAdjective().getMPlural());
        assertEquals("lommas", forms.getAdjective().getFPlural());
        assertEquals("lommamaing", forms.getAdjective().getAdverbialForm());

        forms = generator.generateForms("14", "fop");
        assertEquals("fop", forms.getAdjective().getMSingular());
        assertEquals("foppa", forms.getAdjective().getFSingular());
        assertEquals("fops", forms.getAdjective().getMPlural());
        assertEquals("foppas", forms.getAdjective().getFPlural());
        assertEquals("foppamaing", forms.getAdjective().getAdverbialForm());
    }

    @Test
    public void testGuessRule1() {
        forms = generator.guessInflection("puril", null, null);
        assertEquals("1", forms.getAdjective().getInflectionSubtype());
    }

    @Test
    public void testGuessRule2() {
        forms = generator.guessInflection("asper", null, null);
        assertEquals("2", forms.getAdjective().getInflectionSubtype());
    }

    @Test
    public void testGuessRule3() {
        forms = generator.guessInflection("debel", null, null);
        assertEquals("3", forms.getAdjective().getInflectionSubtype());

        forms = generator.guessInflection("crettaivel", null, null);
        assertEquals("3", forms.getAdjective().getInflectionSubtype());
    }

    @Test
    public void testGuessRule4() {
        forms = generator.guessInflection("mediocar", null, null);
        assertEquals("4", forms.getAdjective().getInflectionSubtype());
    }


    @Test
    public void testGuessRule5() {
        forms = generator.guessInflection("giuven", null, null);
        assertEquals("5", forms.getAdjective().getInflectionSubtype());

        forms = generator.guessInflection("cotschen", null, null);
        assertEquals("5", forms.getAdjective().getInflectionSubtype());
    }

    @Test
    public void testGuessRule6() {
        forms = generator.guessInflection("bellischem", null, null);
        assertEquals("6", forms.getAdjective().getInflectionSubtype());
    }

    @Test
    public void testGuessRule7() {
        forms = generator.guessInflection("larg", null, null);
        assertEquals("7", forms.getAdjective().getInflectionSubtype());
    }

    @Test
    public void testGuessRule8() {
        forms = generator.guessInflection("vegl", null, null);
        assertEquals("8", forms.getAdjective().getInflectionSubtype());
    }

    @Test
    public void testGuessRule9() {
        forms = generator.guessInflection("stanguel", null, null);
        assertEquals("9", forms.getAdjective().getInflectionSubtype());
    }

    @Test
    public void testGuessRule10() {
        forms = generator.guessInflection("portà", null, null);
        assertEquals("10", forms.getAdjective().getInflectionSubtype());
    }

    @Test
    public void testGuessRule11() {
        forms = generator.guessInflection("vendü", null, null);
        assertEquals("11", forms.getAdjective().getInflectionSubtype());
    }

    @Test
    public void testGuessRule12() {
        forms = generator.guessInflection("impedi", null, null);
        assertEquals("12", forms.getAdjective().getInflectionSubtype());
    }

    @Test
    public void testGuessRule13() {
        forms = generator.guessInflection("pluffer", null, null);
        assertEquals("13", forms.getAdjective().getInflectionSubtype());

        forms = generator.guessInflection("mellan", null, null);
        assertEquals("13", forms.getAdjective().getInflectionSubtype());

        forms = generator.guessInflection("prossem", null, null);
        assertEquals("13", forms.getAdjective().getInflectionSubtype());

        forms = generator.guessInflection("svizzer", null, null);
        assertEquals("13", forms.getAdjective().getInflectionSubtype());
    }

    @Test
    public void testGuessRule14() {
        forms = generator.guessInflection("fiac", null, null);
        assertEquals("14", forms.getAdjective().getInflectionSubtype());

        forms = generator.guessInflection("lom", null, null);
        assertEquals("14", forms.getAdjective().getInflectionSubtype());

        forms = generator.guessInflection("fop", null, null);
        assertEquals("14", forms.getAdjective().getInflectionSubtype());


        // rule 14 should only be used for single syllable words
        forms = generator.guessInflection("electric", null, null);
        assertEquals("1", forms.getAdjective().getInflectionSubtype());

        forms = generator.guessInflection("dogmatic", null, null);
        assertEquals("1", forms.getAdjective().getInflectionSubtype());

        forms = generator.guessInflection("obligatoric", null, null);
        assertEquals("1", forms.getAdjective().getInflectionSubtype());

        forms = generator.guessInflection("difficil", null, null);
        assertEquals("1", forms.getAdjective().getInflectionSubtype());
    }
}
