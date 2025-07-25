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
        assertEquals("scolast", forms.getNoun().getMSingular());
        assertEquals("scolasta", forms.getNoun().getFSingular());
        assertEquals("scolasts", forms.getNoun().getMPlural());
        assertEquals("scolastas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule4() {
        forms = generator.generateForms("4", "responsabel");
        assertEquals("responsabel", forms.getNoun().getMSingular());
        assertEquals("responsabla", forms.getNoun().getFSingular());
        assertEquals("responsabels", forms.getNoun().getMPlural());
        assertEquals("responsablas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule6() {
        forms = generator.generateForms("6", "figliaster");
        assertEquals("figliaster", forms.getNoun().getMSingular());
        assertEquals("figliastra", forms.getNoun().getFSingular());
        assertEquals("figliasters", forms.getNoun().getMPlural());
        assertEquals("figliastras", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule7() {
        forms = generator.generateForms("7", "tat");
        assertEquals("tat", forms.getNoun().getMSingular());
        assertEquals("tatta", forms.getNoun().getFSingular());
        assertEquals("tats", forms.getNoun().getMPlural());
        assertEquals("tattas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule8() {
        forms = generator.generateForms("8", "quinau");
        assertEquals("quinau", forms.getNoun().getMSingular());
        assertEquals("quinada", forms.getNoun().getFSingular());
        assertEquals("quinaus", forms.getNoun().getMPlural());
        assertEquals("quinadas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule9() {
        forms = generator.generateForms("9", "bueder");
        assertEquals("bueder", forms.getNoun().getMSingular());
        assertEquals("buedra", forms.getNoun().getFSingular());
        assertEquals("bueders", forms.getNoun().getMPlural());
        assertEquals("buedras", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule10() {
        forms = generator.generateForms("10", "giuven");
        assertEquals("giuven", forms.getNoun().getMSingular());
        assertEquals("giuvna", forms.getNoun().getFSingular());
        assertEquals("giuvens", forms.getNoun().getMPlural());
        assertEquals("giuvnas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule11() {
        forms = generator.generateForms("11", "lader");
        assertEquals("lader", forms.getNoun().getMSingular());
        assertEquals("ladra", forms.getNoun().getFSingular());
        assertEquals("laders", forms.getNoun().getMPlural());
        assertEquals("ladras", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule13() {
        forms = generator.generateForms("13", "vendider");
        assertEquals("vendider", forms.getNoun().getMSingular());
        assertEquals("vendidra", forms.getNoun().getFSingular());
        assertEquals("vendiders", forms.getNoun().getMPlural());
        assertEquals("vendidras", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule15() {
        forms = generator.generateForms("15", "magister");
        assertEquals("magister", forms.getNoun().getMSingular());
        assertEquals("magistra", forms.getNoun().getFSingular());
        assertEquals("magisters", forms.getNoun().getMPlural());
        assertEquals("magistras", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule16() {
        forms = generator.generateForms("16", "pitoc");
        assertEquals("pitoc", forms.getNoun().getMSingular());
        assertEquals("pitocca", forms.getNoun().getFSingular());
        assertEquals("pitocs", forms.getNoun().getMPlural());
        assertEquals("pitoccas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule17() {
        forms = generator.generateForms("17", "tschut");
        assertEquals("tschut", forms.getNoun().getMSingular());
        assertEquals("tschutta", forms.getNoun().getFSingular());
        assertEquals("tschuts", forms.getNoun().getMPlural());
        assertEquals("tschuttas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule18a() {
        forms = generator.generateForms("18", "plevon");
        assertEquals("plevon", forms.getNoun().getMSingular());
        assertEquals("plevonessa", forms.getNoun().getFSingular());
        assertEquals("plevons", forms.getNoun().getMPlural());
        assertEquals("plevonessas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule18b() {
        forms = generator.generateForms("18", "barun");
        assertEquals("barun", forms.getNoun().getMSingular());
        assertEquals("barunessa", forms.getNoun().getFSingular());
        assertEquals("baruns", forms.getNoun().getMPlural());
        assertEquals("barunessas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule18c() {
        forms = generator.generateForms("18", "cont");
        assertEquals("cont", forms.getNoun().getMSingular());
        assertEquals("contessa", forms.getNoun().getFSingular());
        assertEquals("conts", forms.getNoun().getMPlural());
        assertEquals("contessas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule19() {
        forms = generator.generateForms("19", "apotecher");
        assertEquals("apotecher", forms.getNoun().getMSingular());
        assertEquals("apotecra", forms.getNoun().getFSingular());
        assertEquals("apotechers", forms.getNoun().getMPlural());
        assertEquals("apotecras", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule20() {
        forms = generator.generateForms("20", "flevel");
        assertEquals("flevel", forms.getNoun().getMSingular());
        assertEquals("flevla", forms.getNoun().getFSingular());
        assertEquals("flevels", forms.getNoun().getMPlural());
        assertEquals("flevlas", forms.getNoun().getFPlural());
    }

    @Test
    public void testRule21() {
        forms = generator.generateForms("21", "pèr");
        assertEquals("pèr", forms.getNoun().getMSingular());
        assertNull(forms.getNoun().getFSingular());
        assertEquals("pèrs", forms.getNoun().getMPlural());
        assertNull(forms.getNoun().getFPlural());
        assertEquals("pèra", forms.getNoun().getPluralCollectiv());
    }

    @Test
    public void testBaseFormWithWhitespace() {
        forms = generator.guessInflection("esch casa", null, null);
        assertNull(forms);
    }

    @Test
    public void testPluralRule1() {
        forms = generator.generateForms("1", "esch-casa");
        assertEquals("esch-casa", forms.getNoun().getMSingular());
        assertEquals("eschs-casa", forms.getNoun().getMPlural());
    }

    @Test
    public void testPluralRule2() {
        forms = generator.generateForms("1", "utschi");
        assertEquals("utschi", forms.getNoun().getMSingular());
        assertEquals("utschals", forms.getNoun().getMPlural());

        forms = generator.generateForms("1", "ischi");
        assertEquals("ischi", forms.getNoun().getMSingular());
        assertEquals("ischals", forms.getNoun().getMPlural());

        forms = generator.generateForms("1", "purschi");
        assertEquals("purschi", forms.getNoun().getMSingular());
        assertEquals("purschals", forms.getNoun().getMPlural());

        forms = generator.generateForms("1", "agni");
        assertEquals("agni", forms.getNoun().getMSingular());
        assertEquals("agnals", forms.getNoun().getMPlural());
    }


    @Test
    public void testPluralRule4() {
        forms = generator.generateForms("1", "cunti");
        assertEquals("cunti", forms.getNoun().getMSingular());
        assertEquals("cuntials", forms.getNoun().getMPlural());

        forms = generator.generateForms("1", "risti");
        assertEquals("risti", forms.getNoun().getMSingular());
        assertEquals("ristials", forms.getNoun().getMPlural());

        forms = generator.generateForms("1", "casti");
        assertEquals("casti", forms.getNoun().getMSingular());
        assertEquals("castials", forms.getNoun().getMPlural());

        forms = generator.generateForms("1", "rispli");
        assertEquals("rispli", forms.getNoun().getMSingular());
        assertEquals("risplials", forms.getNoun().getMPlural());
    }

    @Test
    public void testPluralRule5() {
        forms = generator.generateForms("1", "tschierv");
        assertEquals("tschierv", forms.getNoun().getMSingular());
        assertEquals("tscharvs", forms.getNoun().getMPlural());

        forms = generator.generateForms("1", "schierl");
        assertEquals("schierl", forms.getNoun().getMSingular());
        assertEquals("scharls", forms.getNoun().getMPlural());

        forms = generator.generateForms("1", "gnierv");
        assertEquals("gnierv", forms.getNoun().getMSingular());
        assertEquals("gnarvs", forms.getNoun().getMPlural());
    }

    @Test
    public void testPluralRule6() {
        forms = generator.generateForms("1", "fiep");
        assertEquals("fiep", forms.getNoun().getMSingular());
        assertEquals("fops", forms.getNoun().getMPlural());

        forms = generator.generateForms("1", "paliet");
        assertEquals("paliet", forms.getNoun().getMSingular());
        assertEquals("paliats", forms.getNoun().getMPlural());

        forms = generator.generateForms("1", "iert");
        assertEquals("iert", forms.getNoun().getMSingular());
        assertEquals("orts", forms.getNoun().getMPlural());

        forms = generator.generateForms("1", "iev");
        assertEquals("iev", forms.getNoun().getMSingular());
        assertEquals("ovs", forms.getNoun().getMPlural());

        forms = generator.generateForms("1", "criec");
        assertEquals("criec", forms.getNoun().getMSingular());
        assertEquals("crocs", forms.getNoun().getMPlural());
    }


    @Test
    public void testPluralRule7() {
        forms = generator.generateForms("1", "gavigliel");
        assertEquals("gavigliel", forms.getNoun().getMSingular());
        assertEquals("gaviglieuls", forms.getNoun().getMPlural());

        forms = generator.generateForms("1", "gugliel");
        assertEquals("gugliel", forms.getNoun().getMSingular());
        assertEquals("guglieuls", forms.getNoun().getMPlural());

        forms = generator.generateForms("1", "tagliel");
        assertEquals("tagliel", forms.getNoun().getMSingular());
        assertEquals("taglieuls", forms.getNoun().getMPlural());
    }

    @Test
    public void testPluralRule8() {
        forms = generator.generateForms("1", "ansiel");
        assertEquals("ansiel", forms.getNoun().getMSingular());
        assertEquals("anseuls", forms.getNoun().getMPlural());

        forms = generator.generateForms("1", "cavriel");
        assertEquals("cavriel", forms.getNoun().getMSingular());
        assertEquals("cavreuls", forms.getNoun().getMPlural());

        forms = generator.generateForms("1", "migiel");
        assertEquals("migiel", forms.getNoun().getMSingular());
        assertEquals("migeuls", forms.getNoun().getMPlural());

        forms = generator.generateForms("1", "caltschiel");
        assertEquals("caltschiel", forms.getNoun().getMSingular());
        assertEquals("caltscheuls", forms.getNoun().getMPlural());

        forms = generator.generateForms("1", "pigiel");
        assertEquals("pigiel", forms.getNoun().getMSingular());
        assertEquals("pigeuls", forms.getNoun().getMPlural());
    }

    @Test
    public void testPluralRule9() {
        forms = generator.generateForms("1", "glas");
        assertEquals("glas", forms.getNoun().getMSingular());
        assertEquals("glas", forms.getNoun().getMPlural());

        forms = generator.generateForms("1", "tais");
        assertEquals("tais", forms.getNoun().getMSingular());
        assertEquals("tais", forms.getNoun().getMPlural());

        forms = generator.generateForms("1", "ris");
        assertEquals("ris", forms.getNoun().getMSingular());
        assertEquals("ris", forms.getNoun().getMPlural());

        forms = generator.generateForms("1", "pass");
        assertEquals("pass", forms.getNoun().getMSingular());
        assertEquals("pass", forms.getNoun().getMPlural());

        forms = generator.generateForms("1", "cass");
        assertEquals("cass", forms.getNoun().getMSingular());
        assertEquals("cass", forms.getNoun().getMPlural());

        forms = generator.generateForms("1", "bass");
        assertEquals("bass", forms.getNoun().getMSingular());
        assertEquals("bass", forms.getNoun().getMPlural());
    }

    @Test
    public void testPluralRule10() {
        forms = generator.generateForms("1", "herox");
        assertEquals("herox", forms.getNoun().getMSingular());
        assertEquals("heroxs", forms.getNoun().getMPlural());

        forms = generator.generateForms("1", "suffix");
        assertEquals("suffix", forms.getNoun().getMSingular());
        assertEquals("suffixs", forms.getNoun().getMPlural());

        forms = generator.generateForms("1", "plaz");
        assertEquals("plaz", forms.getNoun().getMSingular());
        assertEquals("plazs", forms.getNoun().getMPlural());

        forms = generator.generateForms("1", "caz");
        assertEquals("caz", forms.getNoun().getMSingular());
        assertEquals("cazs", forms.getNoun().getMPlural());
    }

    @Test
    public void testPluralRuleMbase() {
        forms = generator.generateForms("1", "canaster");
        assertEquals("canaster", forms.getNoun().getMSingular());
        assertNull(forms.getNoun().getFSingular());
        assertEquals("canasters", forms.getNoun().getMPlural());
        assertNull(forms.getNoun().getFPlural());
    }

    @Test
    public void testPluralRuleFbase() {
        forms = generator.generateForms("2", "dunna");
        assertNull(forms.getNoun().getMSingular());
        assertEquals("dunna", forms.getNoun().getFSingular());
        assertNull(forms.getNoun().getMPlural());
        assertEquals("dunnas", forms.getNoun().getFPlural());
    }

    @Test
    public void testPluralRuleMFbase() {
        forms = generator.generateForms("3", "scolast");
        assertEquals("scolast", forms.getNoun().getMSingular());
        assertEquals("scolasta", forms.getNoun().getFSingular());
        assertEquals("scolasts", forms.getNoun().getMPlural());
        assertEquals("scolastas", forms.getNoun().getFPlural());
    }

    @Test
    public void testUpperCase() {
        forms = generator.generateForms("3", "Argoves");
        assertEquals("Argoves", forms.getNoun().getMSingular());
        assertEquals("Argovesa", forms.getNoun().getFSingular());
        assertEquals("Argoves", forms.getNoun().getMPlural());
        assertEquals("Argovesas", forms.getNoun().getFPlural());
    }
}
