package ch.pledarigrond.inflection.verb.sutsilvan;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.noun.sutsilvan.SutsilvanNounGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SutsilvanNounGuessingTest {

    private static SutsilvanNounGenerator generator;
    private InflectionDto forms;

    @BeforeAll
    public static void initialize() {
        generator = new SutsilvanNounGenerator();
    }

    @Test
    public void testRule3() {
        forms = generator.guessInflection("scular", null, null);
        assertEquals("3", forms.getNoun().getInflectionSubtype());
        assertEquals("scular", forms.getNoun().getMSingular());
        assertEquals("sculara", forms.getNoun().getFSingular());
        assertEquals("sculars", forms.getNoun().getMPlural());
        assertEquals("scularas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule4() {
        forms = generator.guessInflection("antschavider",null, null);
        assertEquals("4", forms.getNoun().getInflectionSubtype());
        assertEquals("antschavider", forms.getNoun().getMSingular());
        assertEquals("antschavidra", forms.getNoun().getFSingular());
        assertEquals("antschaviders", forms.getNoun().getMPlural());
        assertEquals("antschavidras", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule5() {
        forms = generator.guessInflection("raspunsabel", null, null);
        assertEquals("5", forms.getNoun().getInflectionSubtype());
        assertEquals("raspunsabel", forms.getNoun().getMSingular());
        assertEquals("raspunsabla", forms.getNoun().getFSingular());
        assertEquals("raspunsabels", forms.getNoun().getMPlural());
        assertEquals("raspunsablas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule6() {
        forms = generator.guessInflection("apotecer", null, null);
        assertEquals("6", forms.getNoun().getInflectionSubtype());
        assertEquals("apotecer", forms.getNoun().getMSingular());
        assertEquals("apotecra", forms.getNoun().getFSingular());
        assertEquals("apotecers", forms.getNoun().getMPlural());
        assertEquals("apotecras", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule7() {
        forms = generator.guessInflection("raspunsavel", null, null);
        assertEquals("7", forms.getNoun().getInflectionSubtype());
        assertEquals("raspunsavel", forms.getNoun().getMSingular());
        assertEquals("raspunsavla", forms.getNoun().getFSingular());
        assertEquals("raspunsavels", forms.getNoun().getMPlural());
        assertEquals("raspunsavlas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule8() {
        forms = generator.guessInflection("giuven", null, null);
        assertEquals("8", forms.getNoun().getInflectionSubtype());
        assertEquals("giuven", forms.getNoun().getMSingular());
        assertEquals("giuvna", forms.getNoun().getFSingular());
        assertEquals("giuvens", forms.getNoun().getMPlural());
        assertEquals("giuvnas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule9() {
        forms = generator.guessInflection("pover", null, null);
        assertEquals("9", forms.getNoun().getInflectionSubtype());
        assertEquals("pover", forms.getNoun().getMSingular());
        assertEquals("povra", forms.getNoun().getFSingular());
        assertEquals("povers", forms.getNoun().getMPlural());
        assertEquals("povras", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule10() {
        forms = generator.guessInflection("impiego", null, null);
        assertEquals("10", forms.getNoun().getInflectionSubtype());
        assertEquals("impiego", forms.getNoun().getMSingular());
        assertEquals("impiegada", forms.getNoun().getFSingular());
        assertEquals("impiegos", forms.getNoun().getMPlural());
        assertEquals("impiegadas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule11() {
        forms = generator.guessInflection("impedieu", null, null);
        assertEquals("11", forms.getNoun().getInflectionSubtype());
        assertEquals("impedieu", forms.getNoun().getMSingular());
        assertEquals("impedida", forms.getNoun().getFSingular());
        assertEquals("impedieus", forms.getNoun().getMPlural());
        assertEquals("impedidas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule12() {
        forms = generator.guessInflection("sgiaglieu", null, null);
        assertEquals("12", forms.getNoun().getInflectionSubtype());
        assertEquals("sgiaglieu", forms.getNoun().getMSingular());
        assertEquals("sgiaglieada", forms.getNoun().getFSingular());
        assertEquals("sgiaglieus", forms.getNoun().getMPlural());
        assertEquals("sgiaglieadas", forms.getNoun().getFPlural());
    }


    @Test
    public void testRule13() {
        forms = generator.guessInflection("partutgieu", null, null);
        assertEquals("13", forms.getNoun().getInflectionSubtype());
        assertEquals("partutgieu", forms.getNoun().getMSingular());
        assertEquals("partutgeada", forms.getNoun().getFSingular());
        assertEquals("partutgieus", forms.getNoun().getMPlural());
        assertEquals("partutgeadas", forms.getNoun().getFPlural());
    }


    @Test
    public void testRule14() {
        forms = generator.guessInflection("exiliieu", null, null);
        assertEquals("14", forms.getNoun().getInflectionSubtype());
        assertEquals("exiliieu", forms.getNoun().getMSingular());
        assertEquals("exilieada", forms.getNoun().getFSingular());
        assertEquals("exiliieus", forms.getNoun().getMPlural());
        assertEquals("exilieadas", forms.getNoun().getFPlural());
    }

    @Test
    public void testBaseFormWithWhitespace() {
        forms = generator.guessInflection("isch tga", null, null);
        assertEquals(null, forms);
    }

    @Test
    public void testNormalizeBaseForm() {
        forms = generator.guessInflection("  sgiaglieu          ", null, null);
        assertEquals("12", forms.getNoun().getInflectionSubtype());
        assertEquals("sgiaglieu", forms.getNoun().getMSingular());
    }

    @Test
    public void testWithEndingIndicationA() {
        forms = generator.guessInflection("client, -a", null, null);
        assertEquals("3", forms.getNoun().getInflectionSubtype());
    }

    @Test
    public void testWithEndingIndicationDra() {
        forms = generator.guessInflection("invantader, -dra", null, null);
        assertEquals("4", forms.getNoun().getInflectionSubtype());
    }

    @Test
    public void testWithEndingIndicationBla() {
        forms = generator.guessInflection("movibel, -bla", null, null);
        assertEquals("5", forms.getNoun().getInflectionSubtype());
    }

    @Test
    public void testWithEndingIndicationCra() {
        forms = generator.guessInflection("scepticer, -cra", null, null);
        assertEquals("6", forms.getNoun().getInflectionSubtype());
    }

    @Test
    public void testWithEndingIndicationVla() {
        forms = generator.guessInflection("pussevel, -vla", null, null);
        assertEquals("7", forms.getNoun().getInflectionSubtype());
    }

    @Test
    public void testWithEndingIndicationVna() {
        forms = generator.guessInflection("gioven, -vna", null, null);
        assertEquals("8", forms.getNoun().getInflectionSubtype());
    }

    @Test
    public void testWithEndingIndicationVra() {
        forms = generator.guessInflection("pover, -vra", null, null);
        assertEquals("9", forms.getNoun().getInflectionSubtype());
    }

    @Test
    public void testWithEndingIndicationAda() {
        forms = generator.guessInflection("scienzio, -ada", null, null);
        assertEquals("10", forms.getNoun().getInflectionSubtype());
    }

    @Test
    public void testWithEndingIndicationIda() {
        forms = generator.guessInflection("impedieu, -ida", null, null);
        assertEquals("11", forms.getNoun().getInflectionSubtype());
    }
}
