package ch.pledarigrond.inflection.verb.sursilvan;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.noun.sursilvan.SursilvanNounGenerator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class SursilvanNounGuessingTest {

    private static SursilvanNounGenerator generator;
    private InflectionDto forms;

    @BeforeClass
    public static void initialize() {
        generator = new SursilvanNounGenerator();
    }

    @Test
    public void testRule3() {
        forms = generator.guessInflection("scolast", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "3");
        Assert.assertEquals(forms.getNoun().getMSingular(), "scolast");
        Assert.assertEquals(forms.getNoun().getFSingular(), "scolasta");
        Assert.assertEquals(forms.getNoun().getMPlural(), "scolasts");
        Assert.assertEquals(forms.getNoun().getFPlural(), "scolastas");
    }

    @Test
    public void testRule4() {
        forms = generator.guessInflection("responsabel",null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "4");
        Assert.assertEquals(forms.getNoun().getMSingular(), "responsabel");
        Assert.assertEquals(forms.getNoun().getFSingular(), "responsabla");
        Assert.assertEquals(forms.getNoun().getMPlural(), "responsabels");
        Assert.assertEquals(forms.getNoun().getFPlural(), "responsablas");
    }

    @Test
    public void testRule6() {
        forms = generator.guessInflection("figliaster", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "6");
        Assert.assertEquals(forms.getNoun().getMSingular(), "figliaster");
        Assert.assertEquals(forms.getNoun().getFSingular(), "figliastra");
        Assert.assertEquals(forms.getNoun().getMPlural(), "figliasters");
        Assert.assertEquals(forms.getNoun().getFPlural(), "figliastras");
    }

    @Test
    public void testRule7() {
        forms = generator.guessInflection("tat", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "7");
        Assert.assertEquals(forms.getNoun().getMSingular(), "tat");
        Assert.assertEquals(forms.getNoun().getFSingular(), "tatta");
        Assert.assertEquals(forms.getNoun().getMPlural(), "tats");
        Assert.assertEquals(forms.getNoun().getFPlural(), "tattas");
    }

    @Test
    public void testRule7NotSingleSyllable() {
        forms = generator.guessInflection("advocat", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "3");
        Assert.assertEquals(forms.getNoun().getMSingular(), "advocat");
        Assert.assertEquals(forms.getNoun().getFSingular(), "advocata");
        Assert.assertEquals(forms.getNoun().getMPlural(), "advocats");
        Assert.assertEquals(forms.getNoun().getFPlural(), "advocatas");
    }

    @Test
    public void testRule8() {
        forms = generator.guessInflection("quinau", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "8");
        Assert.assertEquals(forms.getNoun().getMSingular(), "quinau");
        Assert.assertEquals(forms.getNoun().getFSingular(), "quinada");
        Assert.assertEquals(forms.getNoun().getMPlural(), "quinaus");
        Assert.assertEquals(forms.getNoun().getFPlural(), "quinadas");
    }

    @Test
    public void testRule9() {
        forms = generator.guessInflection("bueder", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "9");
        Assert.assertEquals(forms.getNoun().getMSingular(), "bueder");
        Assert.assertEquals(forms.getNoun().getFSingular(), "buedra");
        Assert.assertEquals(forms.getNoun().getMPlural(), "bueders");
        Assert.assertEquals(forms.getNoun().getFPlural(), "buedras");
    }

    @Test
    public void testRule10() {
        forms = generator.guessInflection("giuven", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "10");
        Assert.assertEquals(forms.getNoun().getMSingular(), "giuven");
        Assert.assertEquals(forms.getNoun().getFSingular(), "giuvna");
        Assert.assertEquals(forms.getNoun().getMPlural(), "giuvens");
        Assert.assertEquals(forms.getNoun().getFPlural(), "giuvnas");
    }

    @Test
    public void testRule11() {
        forms = generator.guessInflection("lader", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "11");
        Assert.assertEquals(forms.getNoun().getMSingular(), "lader");
        Assert.assertEquals(forms.getNoun().getFSingular(), "ladra");
        Assert.assertEquals(forms.getNoun().getMPlural(), "laders");
        Assert.assertEquals(forms.getNoun().getFPlural(), "ladras");
    }

    @Test
    public void testRule13() {
        forms = generator.guessInflection("vendider", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "13");
        Assert.assertEquals(forms.getNoun().getMSingular(), "vendider");
        Assert.assertEquals(forms.getNoun().getFSingular(), "vendidra");
        Assert.assertEquals(forms.getNoun().getMPlural(), "vendiders");
        Assert.assertEquals(forms.getNoun().getFPlural(), "vendidras");
    }

    @Test
    public void testRule15() {
        forms = generator.guessInflection("magister", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "15");
        Assert.assertEquals(forms.getNoun().getMSingular(), "magister");
        Assert.assertEquals(forms.getNoun().getFSingular(), "magistra");
        Assert.assertEquals(forms.getNoun().getMPlural(), "magisters");
        Assert.assertEquals(forms.getNoun().getFPlural(), "magistras");
    }

    @Test
    public void testRule16() {
        forms = generator.guessInflection("pitoc", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "16");
        Assert.assertEquals(forms.getNoun().getMSingular(), "pitoc");
        Assert.assertEquals(forms.getNoun().getFSingular(), "pitocca");
        Assert.assertEquals(forms.getNoun().getMPlural(), "pitocs");
        Assert.assertEquals(forms.getNoun().getFPlural(), "pitoccas");
    }

    @Test
    public void testRule17() {
        forms = generator.guessInflection("tschut", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "17");
        Assert.assertEquals(forms.getNoun().getMSingular(), "tschut");
        Assert.assertEquals(forms.getNoun().getFSingular(), "tschutta");
        Assert.assertEquals(forms.getNoun().getMPlural(), "tschuts");
        Assert.assertEquals(forms.getNoun().getFPlural(), "tschuttas");
    }

    @Test
    public void testRule18a() {
        forms = generator.guessInflection("plevon", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "18");
        Assert.assertEquals(forms.getNoun().getMSingular(), "plevon");
        Assert.assertEquals(forms.getNoun().getFSingular(), "plevonessa");
        Assert.assertEquals(forms.getNoun().getMPlural(), "plevons");
        Assert.assertEquals(forms.getNoun().getFPlural(), "plevonessas");
    }

    @Test
    public void testRule18b() {
        forms = generator.guessInflection("barun", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "18");
        Assert.assertEquals(forms.getNoun().getMSingular(), "barun");
        Assert.assertEquals(forms.getNoun().getFSingular(), "barunessa");
        Assert.assertEquals(forms.getNoun().getMPlural(), "baruns");
        Assert.assertEquals(forms.getNoun().getFPlural(), "barunessas");
    }

    @Test
    public void testRule19() {
        forms = generator.guessInflection("apotecher", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "19");
        Assert.assertEquals(forms.getNoun().getMSingular(), "apotecher");
        Assert.assertEquals(forms.getNoun().getFSingular(), "apotecra");
        Assert.assertEquals(forms.getNoun().getMPlural(), "apotechers");
        Assert.assertEquals(forms.getNoun().getFPlural(), "apotecras");
    }

    @Test
    public void testRule20() {
        forms = generator.guessInflection("flevel", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "20");
        Assert.assertEquals(forms.getNoun().getMSingular(), "flevel");
        Assert.assertEquals(forms.getNoun().getFSingular(), "flevla");
        Assert.assertEquals(forms.getNoun().getMPlural(), "flevels");
        Assert.assertEquals(forms.getNoun().getFPlural(), "flevlas");
    }

    @Test
    public void testNormalizeBaseForm() {
        forms = generator.guessInflection("  magister         ", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "15");
        Assert.assertEquals(forms.getNoun().getMSingular(), "magister");
    }

    @Test
    public void testWithEndingIndicationBla() {
        forms = generator.guessInflection("responsabel, -bla", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "4");
    }

    @Test
    public void testWithEndingIndicationAstra() {
        forms = generator.guessInflection("figliaster, -astra", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "6");
    }

    @Test
    public void testWithEndingIndicationAtta() {
        forms = generator.guessInflection("tat, -ta", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "7");

        forms = generator.guessInflection("tat, -atta", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "7");
    }

    @Test
    public void testWithEndingIndicationAda() {
        forms = generator.guessInflection("quinau, -ada", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "8");
    }

    @Test
    public void testWithEndingIndicationEdra() {
        forms = generator.guessInflection("bueder, -edra", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "9");
    }

    @Test
    public void testWithEndingIndicationNa() {
        forms = generator.guessInflection("giuven, -na", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "10");
    }

    @Test
    public void testWithEndingIndicationRa() {
        forms = generator.guessInflection("lader, -ra", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "11");
    }

    @Test
    public void testWithEndingIndicationIdra() {
        forms = generator.guessInflection("vendider, -idra", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "13");
    }

    @Test
    public void testWithEndingIndicationTra() {
        forms = generator.guessInflection("magister, -tra", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "15");
    }

    @Test
    public void testWithEndingIndicationOcca() {
        forms = generator.guessInflection("pitoc, -occa", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "16");
    }

    @Test
    public void testWithEndingIndicationUtta() {
        forms = generator.guessInflection("tschut, -utta", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "17");
    }

    @Test
    public void testWithEndingIndicationEssa() {
        forms = generator.guessInflection("plevon, -essa", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "18");

        forms = generator.guessInflection("barun, -essa", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "18");

        forms = generator.guessInflection("cont, -essa", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "18");
    }

    @Test
    public void testWithEndingIndicationCra() {
        forms = generator.guessInflection("apotecher, -cra", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "19");
    }

    @Test
    public void testWithEndingIndicationVla() {
        forms = generator.guessInflection("fleivel, -vla", null, null);
        Assert.assertEquals(forms.getInflectionSubtype(), "20");
    }
}
