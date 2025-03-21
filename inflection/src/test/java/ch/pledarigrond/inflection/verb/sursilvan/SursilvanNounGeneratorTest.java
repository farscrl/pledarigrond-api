package ch.pledarigrond.inflection.verb.sursilvan;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.noun.sursilvan.SursilvanNounGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SursilvanNounGeneratorTest {

    private static SursilvanNounGenerator generator;
    private InflectionDto forms;

    @BeforeAll
    public static void initialize() {
        generator = new SursilvanNounGenerator();
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("3", "scolast");
        assertEquals(forms.getNoun().getMSingular(), "scolast");
        assertEquals(forms.getNoun().getFSingular(), "scolasta");
        assertEquals(forms.getNoun().getMPlural(), "scolasts");
        assertEquals(forms.getNoun().getFPlural(), "scolastas");
    }

    @Test
    public void testRule4() {
        forms = generator.generateForms("4", "responsabel");
        assertEquals(forms.getNoun().getMSingular(), "responsabel");
        assertEquals(forms.getNoun().getFSingular(), "responsabla");
        assertEquals(forms.getNoun().getMPlural(), "responsabels");
        assertEquals(forms.getNoun().getFPlural(), "responsablas");
    }

    @Test
    public void testRule6() {
        forms = generator.generateForms("6", "figliaster");
        assertEquals(forms.getNoun().getMSingular(), "figliaster");
        assertEquals(forms.getNoun().getFSingular(), "figliastra");
        assertEquals(forms.getNoun().getMPlural(), "figliasters");
        assertEquals(forms.getNoun().getFPlural(), "figliastras");
    }

    @Test
    public void testRule7() {
        forms = generator.generateForms("7", "tat");
        assertEquals(forms.getNoun().getMSingular(), "tat");
        assertEquals(forms.getNoun().getFSingular(), "tatta");
        assertEquals(forms.getNoun().getMPlural(), "tats");
        assertEquals(forms.getNoun().getFPlural(), "tattas");
    }

    @Test
    public void testRule8() {
        forms = generator.generateForms("8", "quinau");
        assertEquals(forms.getNoun().getMSingular(), "quinau");
        assertEquals(forms.getNoun().getFSingular(), "quinada");
        assertEquals(forms.getNoun().getMPlural(), "quinaus");
        assertEquals(forms.getNoun().getFPlural(), "quinadas");
    }

    @Test
    public void testRule9() {
        forms = generator.generateForms("9", "bueder");
        assertEquals(forms.getNoun().getMSingular(), "bueder");
        assertEquals(forms.getNoun().getFSingular(), "buedra");
        assertEquals(forms.getNoun().getMPlural(), "bueders");
        assertEquals(forms.getNoun().getFPlural(), "buedras");
    }

    @Test
    public void testRule10() {
        forms = generator.generateForms("10", "giuven");
        assertEquals(forms.getNoun().getMSingular(), "giuven");
        assertEquals(forms.getNoun().getFSingular(), "giuvna");
        assertEquals(forms.getNoun().getMPlural(), "giuvens");
        assertEquals(forms.getNoun().getFPlural(), "giuvnas");
    }

    @Test
    public void testRule11() {
        forms = generator.generateForms("11", "lader");
        assertEquals(forms.getNoun().getMSingular(), "lader");
        assertEquals(forms.getNoun().getFSingular(), "ladra");
        assertEquals(forms.getNoun().getMPlural(), "laders");
        assertEquals(forms.getNoun().getFPlural(), "ladras");
    }

    @Test
    public void testRule13() {
        forms = generator.generateForms("13", "vendider");
        assertEquals(forms.getNoun().getMSingular(), "vendider");
        assertEquals(forms.getNoun().getFSingular(), "vendidra");
        assertEquals(forms.getNoun().getMPlural(), "vendiders");
        assertEquals(forms.getNoun().getFPlural(), "vendidras");
    }

    @Test
    public void testRule15() {
        forms = generator.generateForms("15", "magister");
        assertEquals(forms.getNoun().getMSingular(), "magister");
        assertEquals(forms.getNoun().getFSingular(), "magistra");
        assertEquals(forms.getNoun().getMPlural(), "magisters");
        assertEquals(forms.getNoun().getFPlural(), "magistras");
    }

    @Test
    public void testRule16() {
        forms = generator.generateForms("16", "pitoc");
        assertEquals(forms.getNoun().getMSingular(), "pitoc");
        assertEquals(forms.getNoun().getFSingular(), "pitocca");
        assertEquals(forms.getNoun().getMPlural(), "pitocs");
        assertEquals(forms.getNoun().getFPlural(), "pitoccas");
    }

    @Test
    public void testRule17() {
        forms = generator.generateForms("17", "tschut");
        assertEquals(forms.getNoun().getMSingular(), "tschut");
        assertEquals(forms.getNoun().getFSingular(), "tschutta");
        assertEquals(forms.getNoun().getMPlural(), "tschuts");
        assertEquals(forms.getNoun().getFPlural(), "tschuttas");
    }

    @Test
    public void testRule18a() {
        forms = generator.generateForms("18", "plevon");
        assertEquals(forms.getNoun().getMSingular(), "plevon");
        assertEquals(forms.getNoun().getFSingular(), "plevonessa");
        assertEquals(forms.getNoun().getMPlural(), "plevons");
        assertEquals(forms.getNoun().getFPlural(), "plevonessas");
    }

    @Test
    public void testRule18b() {
        forms = generator.generateForms("18", "barun");
        assertEquals(forms.getNoun().getMSingular(), "barun");
        assertEquals(forms.getNoun().getFSingular(), "barunessa");
        assertEquals(forms.getNoun().getMPlural(), "baruns");
        assertEquals(forms.getNoun().getFPlural(), "barunessas");
    }

    @Test
    public void testRule18c() {
        forms = generator.generateForms("18", "cont");
        assertEquals(forms.getNoun().getMSingular(), "cont");
        assertEquals(forms.getNoun().getFSingular(), "contessa");
        assertEquals(forms.getNoun().getMPlural(), "conts");
        assertEquals(forms.getNoun().getFPlural(), "contessas");
    }

    @Test
    public void testRule19() {
        forms = generator.generateForms("19", "apotecher");
        assertEquals(forms.getNoun().getMSingular(), "apotecher");
        assertEquals(forms.getNoun().getFSingular(), "apotecra");
        assertEquals(forms.getNoun().getMPlural(), "apotechers");
        assertEquals(forms.getNoun().getFPlural(), "apotecras");
    }

    @Test
    public void testRule20() {
        forms = generator.generateForms("20", "flevel");
        assertEquals(forms.getNoun().getMSingular(), "flevel");
        assertEquals(forms.getNoun().getFSingular(), "flevla");
        assertEquals(forms.getNoun().getMPlural(), "flevels");
        assertEquals(forms.getNoun().getFPlural(), "flevlas");
    }

    @Test
    public void testRule21() {
        forms = generator.generateForms("21", "pèr");
        assertEquals(forms.getNoun().getMSingular(), "pèr");
        assertNull(forms.getNoun().getFSingular());
        assertEquals(forms.getNoun().getMPlural(), "pèrs");
        assertNull(forms.getNoun().getFPlural());
        assertEquals(forms.getNoun().getPluralCollectiv(), "pèra");
    }

    @Test
    public void testBaseFormWithWhitespace() {
        forms = generator.guessInflection("esch casa", null, null);
        assertNull(forms);
    }

    @Test
    public void testPluralRule1() {
        forms = generator.generateForms("1", "esch-casa");
        assertEquals(forms.getNoun().getMSingular(), "esch-casa");
        assertEquals(forms.getNoun().getMPlural(), "eschs-casa");
    }

    @Test
    public void testPluralRule2() {
        forms = generator.generateForms("1", "utschi");
        assertEquals(forms.getNoun().getMSingular(), "utschi");
        assertEquals(forms.getNoun().getMPlural(), "utschals");

        forms = generator.generateForms("1", "ischi");
        assertEquals(forms.getNoun().getMSingular(), "ischi");
        assertEquals(forms.getNoun().getMPlural(), "ischals");

        forms = generator.generateForms("1", "purschi");
        assertEquals(forms.getNoun().getMSingular(), "purschi");
        assertEquals(forms.getNoun().getMPlural(), "purschals");

        forms = generator.generateForms("1", "agni");
        assertEquals(forms.getNoun().getMSingular(), "agni");
        assertEquals(forms.getNoun().getMPlural(), "agnals");
    }


    @Test
    public void testPluralRule4() {
        forms = generator.generateForms("1", "cunti");
        assertEquals(forms.getNoun().getMSingular(), "cunti");
        assertEquals(forms.getNoun().getMPlural(), "cuntials");

        forms = generator.generateForms("1", "risti");
        assertEquals(forms.getNoun().getMSingular(), "risti");
        assertEquals(forms.getNoun().getMPlural(), "ristials");

        forms = generator.generateForms("1", "casti");
        assertEquals(forms.getNoun().getMSingular(), "casti");
        assertEquals(forms.getNoun().getMPlural(), "castials");

        forms = generator.generateForms("1", "rispli");
        assertEquals(forms.getNoun().getMSingular(), "rispli");
        assertEquals(forms.getNoun().getMPlural(), "risplials");
    }

    @Test
    public void testPluralRule5() {
        forms = generator.generateForms("1", "tschierv");
        assertEquals(forms.getNoun().getMSingular(), "tschierv");
        assertEquals(forms.getNoun().getMPlural(), "tscharvs");

        forms = generator.generateForms("1", "schierl");
        assertEquals(forms.getNoun().getMSingular(), "schierl");
        assertEquals(forms.getNoun().getMPlural(), "scharls");

        forms = generator.generateForms("1", "gnierv");
        assertEquals(forms.getNoun().getMSingular(), "gnierv");
        assertEquals(forms.getNoun().getMPlural(), "gnarvs");
    }

    @Test
    public void testPluralRule6() {
        forms = generator.generateForms("1", "fiep");
        assertEquals(forms.getNoun().getMSingular(), "fiep");
        assertEquals(forms.getNoun().getMPlural(), "fops");

        forms = generator.generateForms("1", "paliet");
        assertEquals(forms.getNoun().getMSingular(), "paliet");
        assertEquals(forms.getNoun().getMPlural(), "paliats");

        forms = generator.generateForms("1", "iert");
        assertEquals(forms.getNoun().getMSingular(), "iert");
        assertEquals(forms.getNoun().getMPlural(), "orts");

        forms = generator.generateForms("1", "iev");
        assertEquals(forms.getNoun().getMSingular(), "iev");
        assertEquals(forms.getNoun().getMPlural(), "ovs");

        forms = generator.generateForms("1", "criec");
        assertEquals(forms.getNoun().getMSingular(), "criec");
        assertEquals(forms.getNoun().getMPlural(), "crocs");
    }


    @Test
    public void testPluralRule7() {
        forms = generator.generateForms("1", "gavigliel");
        assertEquals(forms.getNoun().getMSingular(), "gavigliel");
        assertEquals(forms.getNoun().getMPlural(), "gaviglieuls");

        forms = generator.generateForms("1", "gugliel");
        assertEquals(forms.getNoun().getMSingular(), "gugliel");
        assertEquals(forms.getNoun().getMPlural(), "guglieuls");

        forms = generator.generateForms("1", "tagliel");
        assertEquals(forms.getNoun().getMSingular(), "tagliel");
        assertEquals(forms.getNoun().getMPlural(), "taglieuls");
    }

    @Test
    public void testPluralRule8() {
        forms = generator.generateForms("1", "ansiel");
        assertEquals(forms.getNoun().getMSingular(), "ansiel");
        assertEquals(forms.getNoun().getMPlural(), "anseuls");

        forms = generator.generateForms("1", "cavriel");
        assertEquals(forms.getNoun().getMSingular(), "cavriel");
        assertEquals(forms.getNoun().getMPlural(), "cavreuls");

        forms = generator.generateForms("1", "migiel");
        assertEquals(forms.getNoun().getMSingular(), "migiel");
        assertEquals(forms.getNoun().getMPlural(), "migeuls");

        forms = generator.generateForms("1", "caltschiel");
        assertEquals(forms.getNoun().getMSingular(), "caltschiel");
        assertEquals(forms.getNoun().getMPlural(), "caltscheuls");

        forms = generator.generateForms("1", "pigiel");
        assertEquals(forms.getNoun().getMSingular(), "pigiel");
        assertEquals(forms.getNoun().getMPlural(), "pigeuls");
    }

    @Test
    public void testPluralRule9() {
        forms = generator.generateForms("1", "glas");
        assertEquals(forms.getNoun().getMSingular(), "glas");
        assertEquals(forms.getNoun().getMPlural(), "glas");

        forms = generator.generateForms("1", "tais");
        assertEquals(forms.getNoun().getMSingular(), "tais");
        assertEquals(forms.getNoun().getMPlural(), "tais");

        forms = generator.generateForms("1", "ris");
        assertEquals(forms.getNoun().getMSingular(), "ris");
        assertEquals(forms.getNoun().getMPlural(), "ris");

        forms = generator.generateForms("1", "pass");
        assertEquals(forms.getNoun().getMSingular(), "pass");
        assertEquals(forms.getNoun().getMPlural(), "pass");

        forms = generator.generateForms("1", "cass");
        assertEquals(forms.getNoun().getMSingular(), "cass");
        assertEquals(forms.getNoun().getMPlural(), "cass");

        forms = generator.generateForms("1", "bass");
        assertEquals(forms.getNoun().getMSingular(), "bass");
        assertEquals(forms.getNoun().getMPlural(), "bass");
    }

    @Test
    public void testPluralRule10() {
        forms = generator.generateForms("1", "herox");
        assertEquals(forms.getNoun().getMSingular(), "herox");
        assertEquals(forms.getNoun().getMPlural(), "heroxs");

        forms = generator.generateForms("1", "suffix");
        assertEquals(forms.getNoun().getMSingular(), "suffix");
        assertEquals(forms.getNoun().getMPlural(), "suffixs");

        forms = generator.generateForms("1", "plaz");
        assertEquals(forms.getNoun().getMSingular(), "plaz");
        assertEquals(forms.getNoun().getMPlural(), "plazs");

        forms = generator.generateForms("1", "caz");
        assertEquals(forms.getNoun().getMSingular(), "caz");
        assertEquals(forms.getNoun().getMPlural(), "cazs");
    }

    @Test
    public void testPluralRuleMbase() {
        forms = generator.generateForms("1", "canaster");
        assertEquals(forms.getNoun().getMSingular(), "canaster");
        assertNull(forms.getNoun().getFSingular());
        assertEquals(forms.getNoun().getMPlural(), "canasters");
        assertNull(forms.getNoun().getFPlural());
    }

    @Test
    public void testPluralRuleFbase() {
        forms = generator.generateForms("2", "dunna");
        assertNull(forms.getNoun().getMSingular());
        assertEquals(forms.getNoun().getFSingular(), "dunna");
        assertNull(forms.getNoun().getMPlural());
        assertEquals(forms.getNoun().getFPlural(), "dunnas");
    }

    @Test
    public void testPluralRuleMFbase() {
        forms = generator.generateForms("3", "scolast");
        assertEquals(forms.getNoun().getMSingular(), "scolast");
        assertEquals(forms.getNoun().getFSingular(), "scolasta");
        assertEquals(forms.getNoun().getMPlural(), "scolasts");
        assertEquals(forms.getNoun().getFPlural(), "scolastas");
    }

    @Test
    public void testUpperCase() {
        forms = generator.generateForms("3", "Argoves");
        assertEquals(forms.getNoun().getMSingular(), "Argoves");
        assertEquals(forms.getNoun().getFSingular(), "Argovesa");
        assertEquals(forms.getNoun().getMPlural(), "Argoves");
        assertEquals(forms.getNoun().getFPlural(), "Argovesas");
    }
}
