package ch.pledarigrond.inflection.generation.vallader;

import ch.pledarigrond.inflection.model.InflectionResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ValladerAdjectiveGeneratorTest {

    private static ValladerAdjectiveGenerator generator;
    private InflectionResponse forms;

    @BeforeClass
    public static void initialize() {
        generator = new ValladerAdjectiveGenerator();
    }

    @Test
    public void testRule1() {
        forms = generator.generateForms("1", "pauril");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "pauril");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "paurila");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "paurils");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "paurilas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "paurilamaing");
    }

    @Test
    public void testRule2() {
        forms = generator.generateForms("2", "asper");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "asper");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "aspra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "aspers");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "aspras");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "aspramaing");
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("3", "debel");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "debel");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "debla");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "debels");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "deblas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "debelmaing");

        forms = generator.generateForms("3", "crettaivel");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "crettaivel");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "crettaivla");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "crettaivels");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "crettaivlas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "crettaivelmaing");
    }

    @Test
    public void testRule4() {
        forms = generator.generateForms("4", "mediocar");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "mediocar");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "mediocra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "mediocars");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "mediocras");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "mediocarmaing");
    }

    @Test
    public void testRule5() {
        forms = generator.generateForms("5", "giuven");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "giuven");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "giuvna");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "giuvens");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "giuvnas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "giuvnamaing");

        forms = generator.generateForms("5", "cotschen");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "cotschen");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "cotschna");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "cotschens");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "cotschnas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "cotschnamaing");
    }

    @Test
    public void testRule6() {
        forms = generator.generateForms("6", "bellischem");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "bellischem");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "bellischma");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "bellischems");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "bellischmas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "bellischmamaing");
    }

    @Test
    public void testRule7() {
        forms = generator.generateForms("7", "larg");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "larg");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "largia");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "largs");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "largias");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "largiamaing");
    }

    @Test
    public void testRule8() {
        forms = generator.generateForms("8", "vegl");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "vegl");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "veglia");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "vegls");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "veglias");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "vegliamaing");
    }

    @Test
    public void testRule9() {
        forms = generator.generateForms("9", "stanguel");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "stanguel");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "stangla");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "stanguels");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "stanglas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "stanguelmaing");
    }

    @Test
    public void testRule10() {
        forms = generator.generateForms("10", "portà");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "portà");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "portada");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "portats");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "portadas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "portadamaing");
    }

    @Test
    public void testRule11() {
        forms = generator.generateForms("10", "vendü");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "vendü");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "vendüda");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "vendüts");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "vendüdas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "vendüdamaing");
    }

    @Test
    public void testRule12() {
        forms = generator.generateForms("10", "impedi");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "impedi");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "impedida");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "impedits");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "impedidas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "impedidamaing");
    }

    @Test
    public void testRule13() {
        forms = generator.generateForms("13", "pluffer");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "pluffer");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "plufra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "pluffers");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "plufras");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "pluframaing");

        forms = generator.generateForms("13", "prossem");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "prossem");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "prosma");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "prossems");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "prosmas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "prosmamaing");

        forms = generator.generateForms("13", "grossottel");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "grossottel");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "grossotla");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "grossottels");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "grossotlas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "grossottelmaing");

        forms = generator.generateForms("13", "svizzer");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "svizzer");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "svizra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "svizzers");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "svizras");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "svizramaing");
    }

    @Test
    public void testRule14() {
        forms = generator.generateForms("14", "fiac");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "fiac");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "fiacca");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "fiacs");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "fiaccas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "fiaccamaing");

        forms = generator.generateForms("14", "lom");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "lom");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "lomma");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "loms");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "lommas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "lommamaing");

        forms = generator.generateForms("14", "fop");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "fop");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "foppa");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "fops");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "foppas");
        Assert.assertEquals(forms.getInflectionValues().get("adverbialForm"), "foppamaing");
    }

    @Test
    public void testGuessRule1() {
        forms = generator.guessInflection("puril", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "1");
    }

    @Test
    public void testGuessRule2() {
        forms = generator.guessInflection("asper", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "2");
    }

    @Test
    public void testGuessRule3() {
        forms = generator.guessInflection("debel", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "3");

        forms = generator.guessInflection("crettaivel", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "3");
    }

    @Test
    public void testGuessRule4() {
        forms = generator.guessInflection("mediocar", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "4");
    }


    @Test
    public void testGuessRule5() {
        forms = generator.guessInflection("giuven", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "5");

        forms = generator.guessInflection("cotschen", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "5");
    }

    @Test
    public void testGuessRule6() {
        forms = generator.guessInflection("bellischem", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "6");
    }

    @Test
    public void testGuessRule7() {
        forms = generator.guessInflection("larg", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "7");
    }

    @Test
    public void testGuessRule8() {
        forms = generator.guessInflection("vegl", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "8");
    }

    @Test
    public void testGuessRule9() {
        forms = generator.guessInflection("stanguel", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "9");
    }

    @Test
    public void testGuessRule10() {
        forms = generator.guessInflection("portà", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "10");
    }

    @Test
    public void testGuessRule11() {
        forms = generator.guessInflection("vendü", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "11");
    }

    @Test
    public void testGuessRule12() {
        forms = generator.guessInflection("impedi", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "12");
    }

    @Test
    public void testGuessRule13() {
        forms = generator.guessInflection("pluffer", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "13");

        forms = generator.guessInflection("mellan", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "13");

        forms = generator.guessInflection("prossem", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "13");

        forms = generator.guessInflection("svizzer", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "13");
    }

    @Test
    public void testGuessRule14() {
        forms = generator.guessInflection("fiac", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "14");

        forms = generator.guessInflection("lom", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "14");

        forms = generator.guessInflection("fop", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "14");


        // rule 14 should only be used for single syllable words
        forms = generator.guessInflection("electric", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "1");

        forms = generator.guessInflection("dogmatic", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "1");

        forms = generator.guessInflection("obligatoric", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "1");

        forms = generator.guessInflection("difficil", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "1");
    }
}
