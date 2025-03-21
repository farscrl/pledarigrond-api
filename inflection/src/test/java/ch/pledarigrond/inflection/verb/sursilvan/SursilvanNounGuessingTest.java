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
        assertEquals(forms.getInflectionSubtype(), "3");
        assertEquals(forms.getNoun().getMSingular(), "scolast");
        assertEquals(forms.getNoun().getFSingular(), "scolasta");
        assertEquals(forms.getNoun().getMPlural(), "scolasts");
        assertEquals(forms.getNoun().getFPlural(), "scolastas");
    }

    @Test
    public void testRule4() {
        forms = generator.guessInflection("responsabel",null, null);
        assertEquals(forms.getInflectionSubtype(), "4");
        assertEquals(forms.getNoun().getMSingular(), "responsabel");
        assertEquals(forms.getNoun().getFSingular(), "responsabla");
        assertEquals(forms.getNoun().getMPlural(), "responsabels");
        assertEquals(forms.getNoun().getFPlural(), "responsablas");
    }

    @Test
    public void testRule6() {
        forms = generator.guessInflection("figliaster", null, null);
        assertEquals(forms.getInflectionSubtype(), "6");
        assertEquals(forms.getNoun().getMSingular(), "figliaster");
        assertEquals(forms.getNoun().getFSingular(), "figliastra");
        assertEquals(forms.getNoun().getMPlural(), "figliasters");
        assertEquals(forms.getNoun().getFPlural(), "figliastras");
    }

    @Test
    public void testRule7() {
        forms = generator.guessInflection("tat", null, null);
        assertEquals(forms.getInflectionSubtype(), "7");
        assertEquals(forms.getNoun().getMSingular(), "tat");
        assertEquals(forms.getNoun().getFSingular(), "tatta");
        assertEquals(forms.getNoun().getMPlural(), "tats");
        assertEquals(forms.getNoun().getFPlural(), "tattas");
    }

    @Test
    public void testRule7NotSingleSyllable() {
        forms = generator.guessInflection("advocat", null, null);
        assertEquals(forms.getInflectionSubtype(), "3");
        assertEquals(forms.getNoun().getMSingular(), "advocat");
        assertEquals(forms.getNoun().getFSingular(), "advocata");
        assertEquals(forms.getNoun().getMPlural(), "advocats");
        assertEquals(forms.getNoun().getFPlural(), "advocatas");
    }

    @Test
    public void testRule8() {
        forms = generator.guessInflection("quinau", null, null);
        assertEquals(forms.getInflectionSubtype(), "8");
        assertEquals(forms.getNoun().getMSingular(), "quinau");
        assertEquals(forms.getNoun().getFSingular(), "quinada");
        assertEquals(forms.getNoun().getMPlural(), "quinaus");
        assertEquals(forms.getNoun().getFPlural(), "quinadas");
    }

    @Test
    public void testRule9() {
        forms = generator.guessInflection("bueder", null, null);
        assertEquals(forms.getInflectionSubtype(), "9");
        assertEquals(forms.getNoun().getMSingular(), "bueder");
        assertEquals(forms.getNoun().getFSingular(), "buedra");
        assertEquals(forms.getNoun().getMPlural(), "bueders");
        assertEquals(forms.getNoun().getFPlural(), "buedras");
    }

    @Test
    public void testRule10() {
        forms = generator.guessInflection("giuven", null, null);
        assertEquals(forms.getInflectionSubtype(), "10");
        assertEquals(forms.getNoun().getMSingular(), "giuven");
        assertEquals(forms.getNoun().getFSingular(), "giuvna");
        assertEquals(forms.getNoun().getMPlural(), "giuvens");
        assertEquals(forms.getNoun().getFPlural(), "giuvnas");
    }

    @Test
    public void testRule11() {
        forms = generator.guessInflection("lader", null, null);
        assertEquals(forms.getInflectionSubtype(), "11");
        assertEquals(forms.getNoun().getMSingular(), "lader");
        assertEquals(forms.getNoun().getFSingular(), "ladra");
        assertEquals(forms.getNoun().getMPlural(), "laders");
        assertEquals(forms.getNoun().getFPlural(), "ladras");
    }

    @Test
    public void testRule13() {
        forms = generator.guessInflection("vendider", null, null);
        assertEquals(forms.getInflectionSubtype(), "13");
        assertEquals(forms.getNoun().getMSingular(), "vendider");
        assertEquals(forms.getNoun().getFSingular(), "vendidra");
        assertEquals(forms.getNoun().getMPlural(), "vendiders");
        assertEquals(forms.getNoun().getFPlural(), "vendidras");
    }

    @Test
    public void testRule15() {
        forms = generator.guessInflection("magister", null, null);
        assertEquals(forms.getInflectionSubtype(), "15");
        assertEquals(forms.getNoun().getMSingular(), "magister");
        assertEquals(forms.getNoun().getFSingular(), "magistra");
        assertEquals(forms.getNoun().getMPlural(), "magisters");
        assertEquals(forms.getNoun().getFPlural(), "magistras");
    }

    @Test
    public void testRule16() {
        forms = generator.guessInflection("pitoc", null, null);
        assertEquals(forms.getInflectionSubtype(), "16");
        assertEquals(forms.getNoun().getMSingular(), "pitoc");
        assertEquals(forms.getNoun().getFSingular(), "pitocca");
        assertEquals(forms.getNoun().getMPlural(), "pitocs");
        assertEquals(forms.getNoun().getFPlural(), "pitoccas");
    }

    @Test
    public void testRule17() {
        forms = generator.guessInflection("tschut", null, null);
        assertEquals(forms.getInflectionSubtype(), "17");
        assertEquals(forms.getNoun().getMSingular(), "tschut");
        assertEquals(forms.getNoun().getFSingular(), "tschutta");
        assertEquals(forms.getNoun().getMPlural(), "tschuts");
        assertEquals(forms.getNoun().getFPlural(), "tschuttas");
    }

    @Test
    public void testRule18a() {
        forms = generator.guessInflection("plevon", null, null);
        assertEquals(forms.getInflectionSubtype(), "18");
        assertEquals(forms.getNoun().getMSingular(), "plevon");
        assertEquals(forms.getNoun().getFSingular(), "plevonessa");
        assertEquals(forms.getNoun().getMPlural(), "plevons");
        assertEquals(forms.getNoun().getFPlural(), "plevonessas");
    }

    @Test
    public void testRule18b() {
        forms = generator.guessInflection("barun", null, null);
        assertEquals(forms.getInflectionSubtype(), "18");
        assertEquals(forms.getNoun().getMSingular(), "barun");
        assertEquals(forms.getNoun().getFSingular(), "barunessa");
        assertEquals(forms.getNoun().getMPlural(), "baruns");
        assertEquals(forms.getNoun().getFPlural(), "barunessas");
    }

    @Test
    public void testRule19() {
        forms = generator.guessInflection("apotecher", null, null);
        assertEquals(forms.getInflectionSubtype(), "19");
        assertEquals(forms.getNoun().getMSingular(), "apotecher");
        assertEquals(forms.getNoun().getFSingular(), "apotecra");
        assertEquals(forms.getNoun().getMPlural(), "apotechers");
        assertEquals(forms.getNoun().getFPlural(), "apotecras");
    }

    @Test
    public void testRule20() {
        forms = generator.guessInflection("flevel", null, null);
        assertEquals(forms.getInflectionSubtype(), "20");
        assertEquals(forms.getNoun().getMSingular(), "flevel");
        assertEquals(forms.getNoun().getFSingular(), "flevla");
        assertEquals(forms.getNoun().getMPlural(), "flevels");
        assertEquals(forms.getNoun().getFPlural(), "flevlas");
    }

    @Test
    public void testNormalizeBaseForm() {
        forms = generator.guessInflection("  magister         ", null, null);
        assertEquals(forms.getInflectionSubtype(), "15");
        assertEquals(forms.getNoun().getMSingular(), "magister");
    }

    @Test
    public void testWithEndingIndicationBla() {
        forms = generator.guessInflection("responsabel, -bla", null, null);
        assertEquals(forms.getInflectionSubtype(), "4");
    }

    @Test
    public void testWithEndingIndicationAstra() {
        forms = generator.guessInflection("figliaster, -astra", null, null);
        assertEquals(forms.getInflectionSubtype(), "6");
    }

    @Test
    public void testWithEndingIndicationAtta() {
        forms = generator.guessInflection("tat, -ta", null, null);
        assertEquals(forms.getInflectionSubtype(), "7");

        forms = generator.guessInflection("tat, -atta", null, null);
        assertEquals(forms.getInflectionSubtype(), "7");
    }

    @Test
    public void testWithEndingIndicationAda() {
        forms = generator.guessInflection("quinau, -ada", null, null);
        assertEquals(forms.getInflectionSubtype(), "8");
    }

    @Test
    public void testWithEndingIndicationEdra() {
        forms = generator.guessInflection("bueder, -edra", null, null);
        assertEquals(forms.getInflectionSubtype(), "9");
    }

    @Test
    public void testWithEndingIndicationNa() {
        forms = generator.guessInflection("giuven, -na", null, null);
        assertEquals(forms.getInflectionSubtype(), "10");
    }

    @Test
    public void testWithEndingIndicationRa() {
        forms = generator.guessInflection("lader, -ra", null, null);
        assertEquals(forms.getInflectionSubtype(), "11");
    }

    @Test
    public void testWithEndingIndicationIdra() {
        forms = generator.guessInflection("vendider, -idra", null, null);
        assertEquals(forms.getInflectionSubtype(), "13");
    }

    @Test
    public void testWithEndingIndicationTra() {
        forms = generator.guessInflection("magister, -tra", null, null);
        assertEquals(forms.getInflectionSubtype(), "15");
    }

    @Test
    public void testWithEndingIndicationOcca() {
        forms = generator.guessInflection("pitoc, -occa", null, null);
        assertEquals(forms.getInflectionSubtype(), "16");
    }

    @Test
    public void testWithEndingIndicationUtta() {
        forms = generator.guessInflection("tschut, -utta", null, null);
        assertEquals(forms.getInflectionSubtype(), "17");
    }

    @Test
    public void testWithEndingIndicationEssa() {
        forms = generator.guessInflection("plevon, -essa", null, null);
        assertEquals(forms.getInflectionSubtype(), "18");

        forms = generator.guessInflection("barun, -essa", null, null);
        assertEquals(forms.getInflectionSubtype(), "18");

        forms = generator.guessInflection("cont, -essa", null, null);
        assertEquals(forms.getInflectionSubtype(), "18");
    }

    @Test
    public void testWithEndingIndicationCra() {
        forms = generator.guessInflection("apotecher, -cra", null, null);
        assertEquals(forms.getInflectionSubtype(), "19");
    }

    @Test
    public void testWithEndingIndicationVla() {
        forms = generator.guessInflection("fleivel, -vla", null, null);
        assertEquals(forms.getInflectionSubtype(), "20");
    }
}
