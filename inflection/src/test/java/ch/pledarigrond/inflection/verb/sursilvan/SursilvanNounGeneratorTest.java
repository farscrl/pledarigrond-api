package ch.pledarigrond.inflection.verb.sursilvan;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.noun.sursilvan.SursilvanNounGenerator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class SursilvanNounGeneratorTest {

    private static SursilvanNounGenerator generator;
    private InflectionDto forms;

    @BeforeClass
    public static void initialize() {
        generator = new SursilvanNounGenerator();
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("3", "scolast");
        Assert.assertEquals(forms.getNoun().getMSingular(), "scolast");
        Assert.assertEquals(forms.getNoun().getFSingular(), "scolasta");
        Assert.assertEquals(forms.getNoun().getMPlural(), "scolasts");
        Assert.assertEquals(forms.getNoun().getFPlural(), "scolastas");
    }

    @Test
    public void testRule4() {
        forms = generator.generateForms("4", "responsabel");
        Assert.assertEquals(forms.getNoun().getMSingular(), "responsabel");
        Assert.assertEquals(forms.getNoun().getFSingular(), "responsabla");
        Assert.assertEquals(forms.getNoun().getMPlural(), "responsabels");
        Assert.assertEquals(forms.getNoun().getFPlural(), "responsablas");
    }

    @Test
    public void testRule6() {
        forms = generator.generateForms("6", "figliaster");
        Assert.assertEquals(forms.getNoun().getMSingular(), "figliaster");
        Assert.assertEquals(forms.getNoun().getFSingular(), "figliastra");
        Assert.assertEquals(forms.getNoun().getMPlural(), "figliasters");
        Assert.assertEquals(forms.getNoun().getFPlural(), "figliastras");
    }

    @Test
    public void testRule7() {
        forms = generator.generateForms("7", "tat");
        Assert.assertEquals(forms.getNoun().getMSingular(), "tat");
        Assert.assertEquals(forms.getNoun().getFSingular(), "tatta");
        Assert.assertEquals(forms.getNoun().getMPlural(), "tats");
        Assert.assertEquals(forms.getNoun().getFPlural(), "tattas");
    }

    @Test
    public void testRule8() {
        forms = generator.generateForms("8", "quinau");
        Assert.assertEquals(forms.getNoun().getMSingular(), "quinau");
        Assert.assertEquals(forms.getNoun().getFSingular(), "quinada");
        Assert.assertEquals(forms.getNoun().getMPlural(), "quinaus");
        Assert.assertEquals(forms.getNoun().getFPlural(), "quinadas");
    }

    @Test
    public void testRule9() {
        forms = generator.generateForms("9", "bueder");
        Assert.assertEquals(forms.getNoun().getMSingular(), "bueder");
        Assert.assertEquals(forms.getNoun().getFSingular(), "buedra");
        Assert.assertEquals(forms.getNoun().getMPlural(), "bueders");
        Assert.assertEquals(forms.getNoun().getFPlural(), "buedras");
    }

    @Test
    public void testRule10() {
        forms = generator.generateForms("10", "giuven");
        Assert.assertEquals(forms.getNoun().getMSingular(), "giuven");
        Assert.assertEquals(forms.getNoun().getFSingular(), "giuvna");
        Assert.assertEquals(forms.getNoun().getMPlural(), "giuvens");
        Assert.assertEquals(forms.getNoun().getFPlural(), "giuvnas");
    }

    @Test
    public void testRule11() {
        forms = generator.generateForms("11", "lader");
        Assert.assertEquals(forms.getNoun().getMSingular(), "lader");
        Assert.assertEquals(forms.getNoun().getFSingular(), "ladra");
        Assert.assertEquals(forms.getNoun().getMPlural(), "laders");
        Assert.assertEquals(forms.getNoun().getFPlural(), "ladras");
    }

    @Test
    public void testRule13() {
        forms = generator.generateForms("13", "vendider");
        Assert.assertEquals(forms.getNoun().getMSingular(), "vendider");
        Assert.assertEquals(forms.getNoun().getFSingular(), "vendidra");
        Assert.assertEquals(forms.getNoun().getMPlural(), "vendiders");
        Assert.assertEquals(forms.getNoun().getFPlural(), "vendidras");
    }

    @Test
    public void testRule15() {
        forms = generator.generateForms("15", "magister");
        Assert.assertEquals(forms.getNoun().getMSingular(), "magister");
        Assert.assertEquals(forms.getNoun().getFSingular(), "magistra");
        Assert.assertEquals(forms.getNoun().getMPlural(), "magisters");
        Assert.assertEquals(forms.getNoun().getFPlural(), "magistras");
    }

    @Test
    public void testRule16() {
        forms = generator.generateForms("16", "pitoc");
        Assert.assertEquals(forms.getNoun().getMSingular(), "pitoc");
        Assert.assertEquals(forms.getNoun().getFSingular(), "pitocca");
        Assert.assertEquals(forms.getNoun().getMPlural(), "pitocs");
        Assert.assertEquals(forms.getNoun().getFPlural(), "pitoccas");
    }

    @Test
    public void testRule17() {
        forms = generator.generateForms("17", "tschut");
        Assert.assertEquals(forms.getNoun().getMSingular(), "tschut");
        Assert.assertEquals(forms.getNoun().getFSingular(), "tschutta");
        Assert.assertEquals(forms.getNoun().getMPlural(), "tschuts");
        Assert.assertEquals(forms.getNoun().getFPlural(), "tschuttas");
    }

    @Test
    public void testRule18a() {
        forms = generator.generateForms("18", "plevon");
        Assert.assertEquals(forms.getNoun().getMSingular(), "plevon");
        Assert.assertEquals(forms.getNoun().getFSingular(), "plevonessa");
        Assert.assertEquals(forms.getNoun().getMPlural(), "plevons");
        Assert.assertEquals(forms.getNoun().getFPlural(), "plevonessas");
    }

    @Test
    public void testRule18b() {
        forms = generator.generateForms("18", "barun");
        Assert.assertEquals(forms.getNoun().getMSingular(), "barun");
        Assert.assertEquals(forms.getNoun().getFSingular(), "barunessa");
        Assert.assertEquals(forms.getNoun().getMPlural(), "baruns");
        Assert.assertEquals(forms.getNoun().getFPlural(), "barunessas");
    }

    @Test
    public void testRule18c() {
        forms = generator.generateForms("18", "cont");
        Assert.assertEquals(forms.getNoun().getMSingular(), "cont");
        Assert.assertEquals(forms.getNoun().getFSingular(), "contessa");
        Assert.assertEquals(forms.getNoun().getMPlural(), "conts");
        Assert.assertEquals(forms.getNoun().getFPlural(), "contessas");
    }

    @Test
    public void testRule19() {
        forms = generator.generateForms("19", "apotecher");
        Assert.assertEquals(forms.getNoun().getMSingular(), "apotecher");
        Assert.assertEquals(forms.getNoun().getFSingular(), "apotecra");
        Assert.assertEquals(forms.getNoun().getMPlural(), "apotechers");
        Assert.assertEquals(forms.getNoun().getFPlural(), "apotecras");
    }

    @Test
    public void testRule20() {
        forms = generator.generateForms("20", "flevel");
        Assert.assertEquals(forms.getNoun().getMSingular(), "flevel");
        Assert.assertEquals(forms.getNoun().getFSingular(), "flevla");
        Assert.assertEquals(forms.getNoun().getMPlural(), "flevels");
        Assert.assertEquals(forms.getNoun().getFPlural(), "flevlas");
    }

    @Test
    public void testRule21() {
        forms = generator.generateForms("21", "pèr");
        Assert.assertEquals(forms.getNoun().getMSingular(), "pèr");
        Assert.assertNull(forms.getNoun().getFSingular());
        Assert.assertEquals(forms.getNoun().getMPlural(), "pèrs");
        Assert.assertNull(forms.getNoun().getFPlural());
        Assert.assertEquals(forms.getNoun().getPluralCollectiv(), "pèra");
    }

    @Test
    public void testBaseFormWithWhitespace() {
        forms = generator.guessInflection("esch casa", null, null);
        Assert.assertNull(forms);
    }

    @Test
    public void testPluralRule1() {
        forms = generator.generateForms("1", "esch-casa");
        Assert.assertEquals(forms.getNoun().getMSingular(), "esch-casa");
        Assert.assertEquals(forms.getNoun().getMPlural(), "eschs-casa");
    }

    @Test
    public void testPluralRule2() {
        forms = generator.generateForms("1", "utschi");
        Assert.assertEquals(forms.getNoun().getMSingular(), "utschi");
        Assert.assertEquals(forms.getNoun().getMPlural(), "utschals");

        forms = generator.generateForms("1", "ischi");
        Assert.assertEquals(forms.getNoun().getMSingular(), "ischi");
        Assert.assertEquals(forms.getNoun().getMPlural(), "ischals");

        forms = generator.generateForms("1", "purschi");
        Assert.assertEquals(forms.getNoun().getMSingular(), "purschi");
        Assert.assertEquals(forms.getNoun().getMPlural(), "purschals");

        forms = generator.generateForms("1", "agni");
        Assert.assertEquals(forms.getNoun().getMSingular(), "agni");
        Assert.assertEquals(forms.getNoun().getMPlural(), "agnals");
    }


    @Test
    public void testPluralRule4() {
        forms = generator.generateForms("1", "cunti");
        Assert.assertEquals(forms.getNoun().getMSingular(), "cunti");
        Assert.assertEquals(forms.getNoun().getMPlural(), "cuntials");

        forms = generator.generateForms("1", "risti");
        Assert.assertEquals(forms.getNoun().getMSingular(), "risti");
        Assert.assertEquals(forms.getNoun().getMPlural(), "ristials");

        forms = generator.generateForms("1", "casti");
        Assert.assertEquals(forms.getNoun().getMSingular(), "casti");
        Assert.assertEquals(forms.getNoun().getMPlural(), "castials");

        forms = generator.generateForms("1", "rispli");
        Assert.assertEquals(forms.getNoun().getMSingular(), "rispli");
        Assert.assertEquals(forms.getNoun().getMPlural(), "risplials");
    }

    @Test
    public void testPluralRule5() {
        forms = generator.generateForms("1", "tschierv");
        Assert.assertEquals(forms.getNoun().getMSingular(), "tschierv");
        Assert.assertEquals(forms.getNoun().getMPlural(), "tscharvs");

        forms = generator.generateForms("1", "schierl");
        Assert.assertEquals(forms.getNoun().getMSingular(), "schierl");
        Assert.assertEquals(forms.getNoun().getMPlural(), "scharls");

        forms = generator.generateForms("1", "gnierv");
        Assert.assertEquals(forms.getNoun().getMSingular(), "gnierv");
        Assert.assertEquals(forms.getNoun().getMPlural(), "gnarvs");
    }

    @Test
    public void testPluralRule6() {
        forms = generator.generateForms("1", "fiep");
        Assert.assertEquals(forms.getNoun().getMSingular(), "fiep");
        Assert.assertEquals(forms.getNoun().getMPlural(), "fops");

        forms = generator.generateForms("1", "paliet");
        Assert.assertEquals(forms.getNoun().getMSingular(), "paliet");
        Assert.assertEquals(forms.getNoun().getMPlural(), "paliats");

        forms = generator.generateForms("1", "iert");
        Assert.assertEquals(forms.getNoun().getMSingular(), "iert");
        Assert.assertEquals(forms.getNoun().getMPlural(), "orts");

        forms = generator.generateForms("1", "iev");
        Assert.assertEquals(forms.getNoun().getMSingular(), "iev");
        Assert.assertEquals(forms.getNoun().getMPlural(), "ovs");

        forms = generator.generateForms("1", "criec");
        Assert.assertEquals(forms.getNoun().getMSingular(), "criec");
        Assert.assertEquals(forms.getNoun().getMPlural(), "crocs");
    }


    @Test
    public void testPluralRule7() {
        forms = generator.generateForms("1", "gavigliel");
        Assert.assertEquals(forms.getNoun().getMSingular(), "gavigliel");
        Assert.assertEquals(forms.getNoun().getMPlural(), "gaviglieuls");

        forms = generator.generateForms("1", "gugliel");
        Assert.assertEquals(forms.getNoun().getMSingular(), "gugliel");
        Assert.assertEquals(forms.getNoun().getMPlural(), "guglieuls");

        forms = generator.generateForms("1", "tagliel");
        Assert.assertEquals(forms.getNoun().getMSingular(), "tagliel");
        Assert.assertEquals(forms.getNoun().getMPlural(), "taglieuls");
    }

    @Test
    public void testPluralRule8() {
        forms = generator.generateForms("1", "ansiel");
        Assert.assertEquals(forms.getNoun().getMSingular(), "ansiel");
        Assert.assertEquals(forms.getNoun().getMPlural(), "anseuls");

        forms = generator.generateForms("1", "cavriel");
        Assert.assertEquals(forms.getNoun().getMSingular(), "cavriel");
        Assert.assertEquals(forms.getNoun().getMPlural(), "cavreuls");

        forms = generator.generateForms("1", "migiel");
        Assert.assertEquals(forms.getNoun().getMSingular(), "migiel");
        Assert.assertEquals(forms.getNoun().getMPlural(), "migeuls");

        forms = generator.generateForms("1", "caltschiel");
        Assert.assertEquals(forms.getNoun().getMSingular(), "caltschiel");
        Assert.assertEquals(forms.getNoun().getMPlural(), "caltscheuls");

        forms = generator.generateForms("1", "pigiel");
        Assert.assertEquals(forms.getNoun().getMSingular(), "pigiel");
        Assert.assertEquals(forms.getNoun().getMPlural(), "pigeuls");
    }

    @Test
    public void testPluralRule9() {
        forms = generator.generateForms("1", "glas");
        Assert.assertEquals(forms.getNoun().getMSingular(), "glas");
        Assert.assertEquals(forms.getNoun().getMPlural(), "glas");

        forms = generator.generateForms("1", "tais");
        Assert.assertEquals(forms.getNoun().getMSingular(), "tais");
        Assert.assertEquals(forms.getNoun().getMPlural(), "tais");

        forms = generator.generateForms("1", "ris");
        Assert.assertEquals(forms.getNoun().getMSingular(), "ris");
        Assert.assertEquals(forms.getNoun().getMPlural(), "ris");

        forms = generator.generateForms("1", "pass");
        Assert.assertEquals(forms.getNoun().getMSingular(), "pass");
        Assert.assertEquals(forms.getNoun().getMPlural(), "pass");

        forms = generator.generateForms("1", "cass");
        Assert.assertEquals(forms.getNoun().getMSingular(), "cass");
        Assert.assertEquals(forms.getNoun().getMPlural(), "cass");

        forms = generator.generateForms("1", "bass");
        Assert.assertEquals(forms.getNoun().getMSingular(), "bass");
        Assert.assertEquals(forms.getNoun().getMPlural(), "bass");
    }

    @Test
    public void testPluralRule10() {
        forms = generator.generateForms("1", "herox");
        Assert.assertEquals(forms.getNoun().getMSingular(), "herox");
        Assert.assertEquals(forms.getNoun().getMPlural(), "heroxs");

        forms = generator.generateForms("1", "suffix");
        Assert.assertEquals(forms.getNoun().getMSingular(), "suffix");
        Assert.assertEquals(forms.getNoun().getMPlural(), "suffixs");

        forms = generator.generateForms("1", "plaz");
        Assert.assertEquals(forms.getNoun().getMSingular(), "plaz");
        Assert.assertEquals(forms.getNoun().getMPlural(), "plazs");

        forms = generator.generateForms("1", "caz");
        Assert.assertEquals(forms.getNoun().getMSingular(), "caz");
        Assert.assertEquals(forms.getNoun().getMPlural(), "cazs");
    }

    @Test
    public void testPluralRuleMbase() {
        forms = generator.generateForms("1", "canaster");
        Assert.assertEquals(forms.getNoun().getMSingular(), "canaster");
        Assert.assertNull(forms.getNoun().getFSingular());
        Assert.assertEquals(forms.getNoun().getMPlural(), "canasters");
        Assert.assertNull(forms.getNoun().getFPlural());
    }

    @Test
    public void testPluralRuleFbase() {
        forms = generator.generateForms("2", "dunna");
        Assert.assertNull(forms.getNoun().getMSingular());
        Assert.assertEquals(forms.getNoun().getFSingular(), "dunna");
        Assert.assertNull(forms.getNoun().getMPlural());
        Assert.assertEquals(forms.getNoun().getFPlural(), "dunnas");
    }

    @Test
    public void testPluralRuleMFbase() {
        forms = generator.generateForms("3", "scolast");
        Assert.assertEquals(forms.getNoun().getMSingular(), "scolast");
        Assert.assertEquals(forms.getNoun().getFSingular(), "scolasta");
        Assert.assertEquals(forms.getNoun().getMPlural(), "scolasts");
        Assert.assertEquals(forms.getNoun().getFPlural(), "scolastas");
    }

    @Test
    public void testUpperCase() {
        forms = generator.generateForms("3", "Argoves");
        Assert.assertEquals(forms.getNoun().getMSingular(), "Argoves");
        Assert.assertEquals(forms.getNoun().getFSingular(), "Argovesa");
        Assert.assertEquals(forms.getNoun().getMPlural(), "Argoves");
        Assert.assertEquals(forms.getNoun().getFPlural(), "Argovesas");
    }
}
