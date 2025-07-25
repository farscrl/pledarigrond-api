package ch.pledarigrond.inflection.verb.sursilvan;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.noun.sursilvan.SursilvanNounGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SursilvanNounGuessingTest {

    private static SursilvanNounGenerator generator;
    private InflectionDto forms;

    @BeforeAll
    public static void initialize() {
        generator = new SursilvanNounGenerator();
    }

    @Test
    public void testRule3() {
        forms = generator.guessInflection("scolast", null, null);
        assertEquals("3", forms.getNoun().getInflectionSubtype());
        assertEquals("scolast", forms.getNoun().getMSingular());
        assertEquals("scolasta", forms.getNoun().getFSingular());
        assertEquals("scolasts", forms.getNoun().getMPlural());
        assertEquals("scolastas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule4() {
        forms = generator.guessInflection("responsabel",null, null);
        assertEquals("4", forms.getNoun().getInflectionSubtype());
        assertEquals("responsabel", forms.getNoun().getMSingular());
        assertEquals("responsabla", forms.getNoun().getFSingular());
        assertEquals("responsabels", forms.getNoun().getMPlural());
        assertEquals("responsablas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule6() {
        forms = generator.guessInflection("figliaster", null, null);
        assertEquals("6", forms.getNoun().getInflectionSubtype());
        assertEquals("figliaster", forms.getNoun().getMSingular());
        assertEquals("figliastra", forms.getNoun().getFSingular());
        assertEquals("figliasters", forms.getNoun().getMPlural());
        assertEquals("figliastras", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule7() {
        forms = generator.guessInflection("tat", null, null);
        assertEquals("7", forms.getNoun().getInflectionSubtype());
        assertEquals("tat", forms.getNoun().getMSingular());
        assertEquals("tatta", forms.getNoun().getFSingular());
        assertEquals("tats", forms.getNoun().getMPlural());
        assertEquals("tattas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule7NotSingleSyllable() {
        forms = generator.guessInflection("advocat", null, null);
        assertEquals("3", forms.getNoun().getInflectionSubtype());
        assertEquals("advocat", forms.getNoun().getMSingular());
        assertEquals("advocata", forms.getNoun().getFSingular());
        assertEquals("advocats", forms.getNoun().getMPlural());
        assertEquals("advocatas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule8() {
        forms = generator.guessInflection("quinau", null, null);
        assertEquals("8", forms.getNoun().getInflectionSubtype());
        assertEquals("quinau", forms.getNoun().getMSingular());
        assertEquals("quinada", forms.getNoun().getFSingular());
        assertEquals("quinaus", forms.getNoun().getMPlural());
        assertEquals("quinadas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule9() {
        forms = generator.guessInflection("bueder", null, null);
        assertEquals("9", forms.getNoun().getInflectionSubtype());
        assertEquals("bueder", forms.getNoun().getMSingular());
        assertEquals("buedra", forms.getNoun().getFSingular());
        assertEquals("bueders", forms.getNoun().getMPlural());
        assertEquals("buedras", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule10() {
        forms = generator.guessInflection("giuven", null, null);
        assertEquals("10", forms.getNoun().getInflectionSubtype());
        assertEquals("giuven", forms.getNoun().getMSingular());
        assertEquals("giuvna", forms.getNoun().getFSingular());
        assertEquals("giuvens", forms.getNoun().getMPlural());
        assertEquals("giuvnas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule11() {
        forms = generator.guessInflection("lader", null, null);
        assertEquals("11", forms.getNoun().getInflectionSubtype());
        assertEquals("lader", forms.getNoun().getMSingular());
        assertEquals("ladra", forms.getNoun().getFSingular());
        assertEquals("laders", forms.getNoun().getMPlural());
        assertEquals("ladras", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule13() {
        forms = generator.guessInflection("vendider", null, null);
        assertEquals("13", forms.getNoun().getInflectionSubtype());
        assertEquals("vendider", forms.getNoun().getMSingular());
        assertEquals("vendidra", forms.getNoun().getFSingular());
        assertEquals("vendiders", forms.getNoun().getMPlural());
        assertEquals("vendidras", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule15() {
        forms = generator.guessInflection("magister", null, null);
        assertEquals("15", forms.getNoun().getInflectionSubtype());
        assertEquals("magister", forms.getNoun().getMSingular());
        assertEquals("magistra", forms.getNoun().getFSingular());
        assertEquals("magisters", forms.getNoun().getMPlural());
        assertEquals("magistras", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule16() {
        forms = generator.guessInflection("pitoc", null, null);
        assertEquals("16", forms.getNoun().getInflectionSubtype());
        assertEquals("pitoc", forms.getNoun().getMSingular());
        assertEquals("pitocca", forms.getNoun().getFSingular());
        assertEquals("pitocs", forms.getNoun().getMPlural());
        assertEquals("pitoccas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule17() {
        forms = generator.guessInflection("tschut", null, null);
        assertEquals("17", forms.getNoun().getInflectionSubtype());
        assertEquals("tschut", forms.getNoun().getMSingular());
        assertEquals("tschutta", forms.getNoun().getFSingular());
        assertEquals("tschuts", forms.getNoun().getMPlural());
        assertEquals("tschuttas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule18a() {
        forms = generator.guessInflection("plevon", null, null);
        assertEquals("18", forms.getNoun().getInflectionSubtype());
        assertEquals("plevon", forms.getNoun().getMSingular());
        assertEquals("plevonessa", forms.getNoun().getFSingular());
        assertEquals("plevons", forms.getNoun().getMPlural());
        assertEquals("plevonessas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule18b() {
        forms = generator.guessInflection("barun", null, null);
        assertEquals("18", forms.getNoun().getInflectionSubtype());
        assertEquals("barun", forms.getNoun().getMSingular());
        assertEquals("barunessa", forms.getNoun().getFSingular());
        assertEquals("baruns", forms.getNoun().getMPlural());
        assertEquals("barunessas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule19() {
        forms = generator.guessInflection("apotecher", null, null);
        assertEquals("19", forms.getNoun().getInflectionSubtype());
        assertEquals("apotecher", forms.getNoun().getMSingular());
        assertEquals("apotecra", forms.getNoun().getFSingular());
        assertEquals("apotechers", forms.getNoun().getMPlural());
        assertEquals("apotecras", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule20() {
        forms = generator.guessInflection("flevel", null, null);
        assertEquals("20", forms.getNoun().getInflectionSubtype());
        assertEquals("flevel", forms.getNoun().getMSingular());
        assertEquals("flevla", forms.getNoun().getFSingular());
        assertEquals("flevels", forms.getNoun().getMPlural());
        assertEquals("flevlas", forms.getNoun().getFPlural());
    }

    @Test
    public void testNormalizeBaseForm() {
        forms = generator.guessInflection("  magister         ", null, null);
        assertEquals("15", forms.getNoun().getInflectionSubtype());
        assertEquals("magister", forms.getNoun().getMSingular());
    }

    @Test
    public void testWithEndingIndicationBla() {
        forms = generator.guessInflection("responsabel, -bla", null, null);
        assertEquals("4", forms.getNoun().getInflectionSubtype());
    }

    @Test
    public void testWithEndingIndicationAstra() {
        forms = generator.guessInflection("figliaster, -astra", null, null);
        assertEquals("6", forms.getNoun().getInflectionSubtype());
    }

    @Test
    public void testWithEndingIndicationAtta() {
        forms = generator.guessInflection("tat, -ta", null, null);
        assertEquals("7", forms.getNoun().getInflectionSubtype());

        forms = generator.guessInflection("tat, -atta", null, null);
        assertEquals("7", forms.getNoun().getInflectionSubtype());
    }

    @Test
    public void testWithEndingIndicationAda() {
        forms = generator.guessInflection("quinau, -ada", null, null);
        assertEquals("8", forms.getNoun().getInflectionSubtype());
    }

    @Test
    public void testWithEndingIndicationEdra() {
        forms = generator.guessInflection("bueder, -edra", null, null);
        assertEquals("9", forms.getNoun().getInflectionSubtype());
    }

    @Test
    public void testWithEndingIndicationNa() {
        forms = generator.guessInflection("giuven, -na", null, null);
        assertEquals("10", forms.getNoun().getInflectionSubtype());
    }

    @Test
    public void testWithEndingIndicationRa() {
        forms = generator.guessInflection("lader, -ra", null, null);
        assertEquals("11", forms.getNoun().getInflectionSubtype());
    }

    @Test
    public void testWithEndingIndicationIdra() {
        forms = generator.guessInflection("vendider, -idra", null, null);
        assertEquals("13", forms.getNoun().getInflectionSubtype());
    }

    @Test
    public void testWithEndingIndicationTra() {
        forms = generator.guessInflection("magister, -tra", null, null);
        assertEquals("15", forms.getNoun().getInflectionSubtype());
    }

    @Test
    public void testWithEndingIndicationOcca() {
        forms = generator.guessInflection("pitoc, -occa", null, null);
        assertEquals("16", forms.getNoun().getInflectionSubtype());
    }

    @Test
    public void testWithEndingIndicationUtta() {
        forms = generator.guessInflection("tschut, -utta", null, null);
        assertEquals("17", forms.getNoun().getInflectionSubtype());
    }

    @Test
    public void testWithEndingIndicationEssa() {
        forms = generator.guessInflection("plevon, -essa", null, null);
        assertEquals("18", forms.getNoun().getInflectionSubtype());

        forms = generator.guessInflection("barun, -essa", null, null);
        assertEquals("18", forms.getNoun().getInflectionSubtype());

        forms = generator.guessInflection("cont, -essa", null, null);
        assertEquals("18", forms.getNoun().getInflectionSubtype());
    }

    @Test
    public void testWithEndingIndicationCra() {
        forms = generator.guessInflection("apotecher, -cra", null, null);
        assertEquals("19", forms.getNoun().getInflectionSubtype());
    }

    @Test
    public void testWithEndingIndicationVla() {
        forms = generator.guessInflection("fleivel, -vla", null, null);
        assertEquals("20", forms.getNoun().getInflectionSubtype());
    }
}
