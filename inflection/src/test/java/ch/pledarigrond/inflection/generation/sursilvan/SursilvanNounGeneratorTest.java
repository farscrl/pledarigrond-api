package ch.pledarigrond.inflection.generation.sursilvan;

import ch.pledarigrond.inflection.model.InflectionResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

public class SursilvanNounGeneratorTest {

    private static SursilvanNounGenerator generator;
    private InflectionResponse forms;

    @BeforeClass
    public static void initialize() {
        generator = new SursilvanNounGenerator();
    }

    @Test
    public void testPrintOutForms() throws IOException {
        forms = generator.generateForms("3", "scolast");
        generator.printForms(forms.getInflectionValues(), "test");
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("3", "scolast");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "scolast");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "scolasta");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "scolasts");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "scolastas");
    }

    @Test
    public void testRule4() {
        forms = generator.generateForms("4", "responsabel");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "responsabel");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "responsabla");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "responsabels");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "responsablas");
    }

    @Test
    public void testRule6() {
        forms = generator.generateForms("6", "figliaster");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "figliaster");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "figliastra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "figliasters");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "figliastras");
    }

    @Test
    public void testRule7() {
        forms = generator.generateForms("7", "tat");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "tat");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "tatta");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "tats");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "tattas");
    }

    @Test
    public void testRule8() {
        forms = generator.generateForms("8", "quinau");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "quinau");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "quinada");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "quinaus");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "quinadas");
    }

    @Test
    public void testRule9() {
        forms = generator.generateForms("9", "bueder");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "bueder");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "buedra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "bueders");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "buedras");
    }

    @Test
    public void testRule10() {
        forms = generator.generateForms("10", "giuven");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "giuven");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "giuvna");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "giuvens");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "giuvnas");
    }

    @Test
    public void testRule11() {
        forms = generator.generateForms("11", "lader");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "lader");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "ladra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "laders");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "ladras");
    }

    @Test
    public void testRule12() {
        forms = generator.generateForms("12", "vadi");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "vadi");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "vadiala");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "vadials");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "vadialas");
    }

    @Test
    public void testRule13() {
        forms = generator.generateForms("13", "vendider");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "vendider");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "vendidra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "vendiders");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "vendidras");
    }

    @Test
    public void testRule14() {
        forms = generator.generateForms("14", "ansiel");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "ansiel");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "ansola");
        // Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "ansiels");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "ansolas");
    }

    @Test
    public void testRule15() {
        forms = generator.generateForms("15", "magister");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "magister");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "magistra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "magisters");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "magistras");
    }

    @Test
    public void testRule16() {
        forms = generator.generateForms("16", "pitoc");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "pitoc");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "pitocca");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "pitocs");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "pitoccas");
    }

    @Test
    public void testRule17() {
        forms = generator.generateForms("17", "tschut");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "tschut");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "tschutta");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "tschuts");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "tschuttas");
    }

    @Test
    public void testRule18a() {
        forms = generator.generateForms("18", "plevon");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "plevon");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "plevonessa");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "plevons");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "plevonessas");
    }

    @Test
    public void testRule18b() {
        forms = generator.generateForms("18", "barun");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "barun");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "barunessa");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "baruns");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "barunessas");
    }

    @Test
    public void testRule18c() {
        forms = generator.generateForms("18", "cont");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "cont");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "contessa");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "conts");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "contessas");
    }

    @Test
    public void testRule19() {
        forms = generator.generateForms("19", "apotecher");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "apotecher");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "apotecra");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "apotechers");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "apotecras");
    }

    @Test
    public void testRule20() {
        forms = generator.generateForms("20", "flevel");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "flevel");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "flevla");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "flevels");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "flevlas");
    }

    @Test
    public void testRule21() {
        forms = generator.generateForms("21", "pèr");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "pèr");
        Assert.assertNull(forms.getInflectionValues().get("fSingular"));
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "pèrs");
        Assert.assertNull(forms.getInflectionValues().get("fPlural"));
        Assert.assertEquals(forms.getInflectionValues().get("pluralCollectiv"), "pèra");
    }

    @Test
    public void testBaseFormWithWhitespace() {
        forms = generator.guessInflection("esch casa", null, null);
        Assert.assertNull(forms);
    }

    @Test
    public void testPluralRule1() {
        forms = generator.generateForms("1", "esch-casa");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "esch-casa");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "eschs-casa");
    }

    @Test
    public void testPluralRule2() {
        forms = generator.generateForms("1", "utschi");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "utschi");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "utschals");

        forms = generator.generateForms("1", "ischi");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "ischi");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "ischals");

        forms = generator.generateForms("1", "purschi");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "purschi");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "purschals");

        forms = generator.generateForms("1", "agni");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "agni");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "agnals");
    }


    @Test
    public void testPluralRule4() {
        forms = generator.generateForms("1", "cunti");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "cunti");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "cuntials");

        forms = generator.generateForms("1", "risti");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "risti");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "ristials");

        forms = generator.generateForms("1", "casti");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "casti");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "castials");

        forms = generator.generateForms("1", "rispli");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "rispli");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "risplials");
    }

    @Test
    public void testPluralRule5() {
        forms = generator.generateForms("1", "tschierv");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "tschierv");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "tscharvs");

        forms = generator.generateForms("1", "schierl");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "schierl");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "scharls");

        forms = generator.generateForms("1", "gnierv");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "gnierv");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "gnarvs");
    }

    @Test
    public void testPluralRule6() {
        forms = generator.generateForms("1", "fiep");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "fiep");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "fops");

        forms = generator.generateForms("1", "paliet");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "paliet");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "paliats");

        forms = generator.generateForms("1", "iert");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "iert");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "orts");

        forms = generator.generateForms("1", "iev");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "iev");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "ovs");

        forms = generator.generateForms("1", "criec");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "criec");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "crocs");
    }


    @Test
    public void testPluralRule7() {
        forms = generator.generateForms("1", "gavigliel");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "gavigliel");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "gaviglieuls");

        forms = generator.generateForms("1", "gugliel");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "gugliel");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "guglieuls");

        forms = generator.generateForms("1", "tagliel");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "tagliel");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "taglieuls");
    }

    @Test
    public void testPluralRule8() {
        forms = generator.generateForms("1", "ansiel");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "ansiel");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "anseuls");

        forms = generator.generateForms("1", "cavriel");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "cavriel");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "cavreuls");

        forms = generator.generateForms("1", "migiel");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "migiel");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "migeuls");

        forms = generator.generateForms("1", "caltschiel");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "caltschiel");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "caltscheuls");

        forms = generator.generateForms("1", "pigiel");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "pigiel");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "pigeuls");
    }

    @Test
    public void testPluralRule9() {
        forms = generator.generateForms("1", "glas");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "glas");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "glas");

        forms = generator.generateForms("1", "tais");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "tais");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "tais");

        forms = generator.generateForms("1", "ris");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "ris");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "ris");

        forms = generator.generateForms("1", "pass");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "pass");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "pass");

        forms = generator.generateForms("1", "cass");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "cass");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "cass");

        forms = generator.generateForms("1", "bass");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "bass");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "bass");
    }

    @Test
    public void testPluralRule10() {
        forms = generator.generateForms("1", "herox");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "herox");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "heroxs");

        forms = generator.generateForms("1", "suffix");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "suffix");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "suffixs");

        forms = generator.generateForms("1", "plaz");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "plaz");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "plazs");

        forms = generator.generateForms("1", "caz");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "caz");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "cazs");
    }

    @Test
    public void testPluralRuleMbase() {
        forms = generator.generateForms("1", "canaster");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "canaster");
        Assert.assertNull(forms.getInflectionValues().get("fSingular"));
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "canasters");
        Assert.assertNull(forms.getInflectionValues().get("fPlural"));
    }

    @Test
    public void testPluralRuleFbase() {
        forms = generator.generateForms("2", "dunna");
        Assert.assertNull(forms.getInflectionValues().get("mSingular"));
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "dunna");
        Assert.assertNull(forms.getInflectionValues().get("mPlural"));
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "dunnas");
    }

    @Test
    public void testPluralRuleMFbase() {
        forms = generator.generateForms("3", "scolast");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "scolast");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "scolasta");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "scolasts");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "scolastas");
    }

    @Test
    public void testUpperCase() {
        forms = generator.generateForms("3", "Argoves");
        Assert.assertEquals(forms.getInflectionValues().get("mSingular"), "Argoves");
        Assert.assertEquals(forms.getInflectionValues().get("fSingular"), "Argovesa");
        Assert.assertEquals(forms.getInflectionValues().get("mPlural"), "Argoves");
        Assert.assertEquals(forms.getInflectionValues().get("fPlural"), "Argovesas");
    }
}
