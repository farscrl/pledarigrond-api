package ch.pledarigrond.inflection.generation.sursilvan;

import ch.pledarigrond.inflection.model.InflectionResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

public class SursilvanNounGuessingTest {

    private static SursilvanNounGenerator generator;
    private InflectionResponse forms;

    @BeforeClass
    public static void initialize() {
        generator = new SursilvanNounGenerator();
    }

    @Test
    public void testPrintOutForms() throws IOException {
        forms = generator.guessInflection("scolast", null, null);
        generator.printForms(forms.getInflectionValues(), "test");
    }

    @Test
    public void testRule3() {
        forms = generator.guessInflection("scolast", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "3");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "scolast");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "scolasta");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "scolasts");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "scolastas");
    }

    @Test
    public void testRule4() {
        forms = generator.guessInflection("responsabel",null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "4");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "responsabel");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "responsabla");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "responsabels");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "responsablas");
    }

    @Test
    public void testRule6() {
        forms = generator.guessInflection("figliaster", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "6");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "figliaster");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "figliastra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "figliasters");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "figliastras");
    }

    @Test
    public void testRule7() {
        forms = generator.guessInflection("tat", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "7");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "tat");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "tatta");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "tats");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "tattas");
    }

    @Test
    public void testRule8() {
        forms = generator.guessInflection("quinau", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "8");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "quinau");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "quinada");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "quinaus");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "quinadas");
    }

    @Test
    public void testRule9() {
        forms = generator.guessInflection("bueder", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "9");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "bueder");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "buedra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "bueders");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "buedras");
    }

    @Test
    public void testRule10() {
        forms = generator.guessInflection("giuven", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "10");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "giuven");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "giuvna");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "giuvens");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "giuvnas");
    }

    @Test
    public void testRule11() {
        forms = generator.guessInflection("lader", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "11");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "lader");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "ladra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "laders");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "ladras");
    }

    @Test
    public void testRule13() {
        forms = generator.guessInflection("vendider", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "13");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "vendider");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "vendidra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "vendiders");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "vendidras");
    }

    @Test
    public void testRule15() {
        forms = generator.guessInflection("magister", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "15");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "magister");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "magistra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "magisters");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "magistras");
    }

    @Test
    public void testRule16() {
        forms = generator.guessInflection("pitoc", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "16");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "pitoc");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "pitocca");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "pitocs");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "pitoccas");
    }

    @Test
    public void testRule17() {
        forms = generator.guessInflection("tschut", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "17");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "tschut");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "tschutta");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "tschuts");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "tschuttas");
    }

    @Test
    public void testRule18a() {
        forms = generator.guessInflection("plevon", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "18");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "plevon");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "plevonessa");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "plevons");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "plevonessas");
    }

    @Test
    public void testRule18b() {
        forms = generator.guessInflection("barun", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "18");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "barun");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "barunessa");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "baruns");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "barunessas");
    }

    @Test
    public void testRule19() {
        forms = generator.guessInflection("apotecher", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "19");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "apotecher");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "apotecra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "apotechers");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "apotecras");
    }

    @Test
    public void testRule20() {
        forms = generator.guessInflection("flevel", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "20");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "flevel");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "flevla");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "flevels");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "flevlas");
    }

    @Test
    public void testNormalizeBaseForm() {
        forms = generator.guessInflection("  magister         ", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "15");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "magister");
    }

    @Test
    public void testWithEndingIndicationBla() {
        forms = generator.guessInflection("responsabel, -bla", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "4");
    }

    @Test
    public void testWithEndingIndicationAstra() {
        forms = generator.guessInflection("figliaster, -astra", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "6");
    }

    @Test
    public void testWithEndingIndicationAtta() {
        forms = generator.guessInflection("tat, -ta", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "7");

        forms = generator.guessInflection("tat, -atta", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "7");
    }

    @Test
    public void testWithEndingIndicationAda() {
        forms = generator.guessInflection("quinau, -ada", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "8");
    }

    @Test
    public void testWithEndingIndicationEdra() {
        forms = generator.guessInflection("bueder, -edra", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "9");
    }

    @Test
    public void testWithEndingIndicationNa() {
        forms = generator.guessInflection("giuven, -na", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "10");
    }

    @Test
    public void testWithEndingIndicationRa() {
        forms = generator.guessInflection("lader, -ra", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "11");
    }

    @Test
    public void testWithEndingIndicationIdra() {
        forms = generator.guessInflection("vendider, -idra", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "13");
    }

    @Test
    public void testWithEndingIndicationTra() {
        forms = generator.guessInflection("magister, -tra", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "15");
    }

    @Test
    public void testWithEndingIndicationOcca() {
        forms = generator.guessInflection("pitoc, -occa", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "16");
    }

    @Test
    public void testWithEndingIndicationUtta() {
        forms = generator.guessInflection("tschut, -utta", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "17");
    }

    @Test
    public void testWithEndingIndicationEssa() {
        forms = generator.guessInflection("plevon, -essa", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "18");

        forms = generator.guessInflection("barun, -essa", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "18");

        forms = generator.guessInflection("cont, -essa", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "18");
    }

    @Test
    public void testWithEndingIndicationCra() {
        forms = generator.guessInflection("apotecher, -cra", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "19");
    }

    @Test
    public void testWithEndingIndicationVla() {
        forms = generator.guessInflection("fleivel, -vla", null, null);
        Assert.assertEquals(forms.getInflectionSubType().id, "20");
    }
}
