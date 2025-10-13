package ch.pledarigrond.inflection.verb.vallader;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.verb.vallader.ValladerConjugation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValladerConjugationEncliticTest {

    private static ValladerConjugation generator;
    private InflectionDto forms;

    @BeforeAll
    public static void initialize() {
        generator = new ValladerConjugation();
    }

    @Test
    public void testRuleAr() {
        forms =  generator.generateConjugation("1", "müdar");

        assertEquals("müdar", forms.getVerb().getInfinitiv());

        assertEquals("müda", forms.getVerb().getPreschentEnclitic().getSing1());
        assertEquals("müdast", forms.getVerb().getPreschentEnclitic().getSing2());
        assertEquals("müda'l", forms.getVerb().getPreschentEnclitic().getSing3m());
        assertEquals("müd'la", forms.getVerb().getPreschentEnclitic().getSing3f());
        assertEquals("müdaina", forms.getVerb().getPreschentEnclitic().getPlural1());
        assertEquals("müdais\n(müdaivat)", forms.getVerb().getPreschentEnclitic().getPlural2());
        assertEquals("müdna", forms.getVerb().getPreschentEnclitic().getPlural3());

        assertEquals("müdaiva", forms.getVerb().getImperfectEnclitic().getSing1());
        assertEquals("müdaivast", forms.getVerb().getImperfectEnclitic().getSing2());
        assertEquals("müdaiva'l", forms.getVerb().getImperfectEnclitic().getSing3m());
        assertEquals("müdaiv'la", forms.getVerb().getImperfectEnclitic().getSing3f());
        assertEquals("müdaivna", forms.getVerb().getImperfectEnclitic().getPlural1());
        assertEquals("müdaivat", forms.getVerb().getImperfectEnclitic().getPlural2());
        assertEquals("müdaivna", forms.getVerb().getImperfectEnclitic().getPlural3());

        assertEquals("müdessa", forms.getVerb().getCundiziunalEnclitic().getSing1());
        assertEquals("müdessast", forms.getVerb().getCundiziunalEnclitic().getSing2());
        assertEquals("müdessa'l", forms.getVerb().getCundiziunalEnclitic().getSing3m());
        assertEquals("müdess'la", forms.getVerb().getCundiziunalEnclitic().getSing3f());
        assertEquals("müdessna", forms.getVerb().getCundiziunalEnclitic().getPlural1());
        assertEquals("müdessat", forms.getVerb().getCundiziunalEnclitic().getPlural2());
        assertEquals("müdessna", forms.getVerb().getCundiziunalEnclitic().getPlural3());

        assertEquals("müdaraja", forms.getVerb().getFuturEnclitic().getSing1());
        assertEquals("müdarast", forms.getVerb().getFuturEnclitic().getSing2());
        assertEquals("müdarà'l", forms.getVerb().getFuturEnclitic().getSing3m());
        assertEquals("müdarà'la", forms.getVerb().getFuturEnclitic().getSing3f());
        assertEquals("müdarana", forms.getVerb().getFuturEnclitic().getPlural1());
        assertEquals("müdarat", forms.getVerb().getFuturEnclitic().getPlural2());
        assertEquals("müdarana", forms.getVerb().getFuturEnclitic().getPlural3());
    }

    @Test
    public void testRuleIr() {
        forms =  generator.generateConjugation("4", "partir");

        assertEquals("partir", forms.getVerb().getInfinitiv());

        assertEquals("parta", forms.getVerb().getPreschentEnclitic().getSing1());
        assertEquals("partast", forms.getVerb().getPreschentEnclitic().getSing2());
        assertEquals("parta'l", forms.getVerb().getPreschentEnclitic().getSing3m());
        assertEquals("part'la", forms.getVerb().getPreschentEnclitic().getSing3f());
        assertEquals("partina", forms.getVerb().getPreschentEnclitic().getPlural1());
        assertEquals("partis\n(partivat)", forms.getVerb().getPreschentEnclitic().getPlural2());
        assertEquals("partna", forms.getVerb().getPreschentEnclitic().getPlural3());

        assertEquals("partiva", forms.getVerb().getImperfectEnclitic().getSing1());
        assertEquals("partivast", forms.getVerb().getImperfectEnclitic().getSing2());
        assertEquals("partiva'l", forms.getVerb().getImperfectEnclitic().getSing3m());
        assertEquals("partiv'la", forms.getVerb().getImperfectEnclitic().getSing3f());
        assertEquals("partivna", forms.getVerb().getImperfectEnclitic().getPlural1());
        assertEquals("partivat", forms.getVerb().getImperfectEnclitic().getPlural2());
        assertEquals("partivna", forms.getVerb().getImperfectEnclitic().getPlural3());

        assertEquals("partissa", forms.getVerb().getCundiziunalEnclitic().getSing1());
        assertEquals("partissast", forms.getVerb().getCundiziunalEnclitic().getSing2());
        assertEquals("partissa'l", forms.getVerb().getCundiziunalEnclitic().getSing3m());
        assertEquals("partiss'la", forms.getVerb().getCundiziunalEnclitic().getSing3f());
        assertEquals("partissna", forms.getVerb().getCundiziunalEnclitic().getPlural1());
        assertEquals("partissat", forms.getVerb().getCundiziunalEnclitic().getPlural2());
        assertEquals("partissna", forms.getVerb().getCundiziunalEnclitic().getPlural3());

        assertEquals("partiraja", forms.getVerb().getFuturEnclitic().getSing1());
        assertEquals("partirast", forms.getVerb().getFuturEnclitic().getSing2());
        assertEquals("partirà'l", forms.getVerb().getFuturEnclitic().getSing3m());
        assertEquals("partirà'la", forms.getVerb().getFuturEnclitic().getSing3f());
        assertEquals("partirana", forms.getVerb().getFuturEnclitic().getPlural1());
        assertEquals("partirat", forms.getVerb().getFuturEnclitic().getPlural2());
        assertEquals("partirana", forms.getVerb().getFuturEnclitic().getPlural3());
    }

    @Test
    public void testRuleGiar() {
        forms =  generator.generateConjugation("1", "mangiar");

        assertEquals("mangiar", forms.getVerb().getInfinitiv());

        assertEquals("eu mang", forms.getVerb().getPreschent().getSing1());
        assertEquals("tü mangiast", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella mangia", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus mangiain", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus mangiais\n(vo mangiaivat)", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas mangian", forms.getVerb().getPreschent().getPlural3());

        assertEquals("mangia", forms.getVerb().getPreschentEnclitic().getSing1());
        assertEquals("mangiast", forms.getVerb().getPreschentEnclitic().getSing2());
        assertEquals("mangia'l", forms.getVerb().getPreschentEnclitic().getSing3m());
        assertEquals("mang'la", forms.getVerb().getPreschentEnclitic().getSing3f());
        assertEquals("mangiaina", forms.getVerb().getPreschentEnclitic().getPlural1());
        assertEquals("mangiais\n(mangiaivat)", forms.getVerb().getPreschentEnclitic().getPlural2());
        assertEquals("mangna", forms.getVerb().getPreschentEnclitic().getPlural3());
    }

    @Test
    public void testRuleGliar() {
        forms =  generator.generateConjugation("1", "tagliar");

        assertEquals("tagliar", forms.getVerb().getInfinitiv());

        assertEquals("eu tagl", forms.getVerb().getPreschent().getSing1());
        assertEquals("tü tagliast", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella taglia", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus tagliain", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus tagliais\n(vo tagliaivat)", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas taglian", forms.getVerb().getPreschent().getPlural3());

        assertEquals("taglia", forms.getVerb().getPreschentEnclitic().getSing1());
        assertEquals("tagliast", forms.getVerb().getPreschentEnclitic().getSing2());
        assertEquals("taglia'l", forms.getVerb().getPreschentEnclitic().getSing3m());
        assertEquals("tagl'la", forms.getVerb().getPreschentEnclitic().getSing3f());
        assertEquals("tagliaina", forms.getVerb().getPreschentEnclitic().getPlural1());
        assertEquals("tagliais\n(tagliaivat)", forms.getVerb().getPreschentEnclitic().getPlural2());
        assertEquals("taglna", forms.getVerb().getPreschentEnclitic().getPlural3());
    }

    @Test
    public void testRuleGer() {
        forms =  generator.generateConjugation("3", "leger");

        assertEquals("leger", forms.getVerb().getInfinitiv());

        assertEquals("eu leg", forms.getVerb().getPreschent().getSing1());
        assertEquals("tü legiast", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella legia", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus legiain", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus legiais\n(vo legiaivat)", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas legian", forms.getVerb().getPreschent().getPlural3());

        assertEquals("legia", forms.getVerb().getPreschentEnclitic().getSing1());
        assertEquals("legiast", forms.getVerb().getPreschentEnclitic().getSing2());
        assertEquals("legia'l", forms.getVerb().getPreschentEnclitic().getSing3m());
        assertEquals("leg'la", forms.getVerb().getPreschentEnclitic().getSing3f());
        assertEquals("legiaina", forms.getVerb().getPreschentEnclitic().getPlural1());
        assertEquals("legiais\n(legiaivat)", forms.getVerb().getPreschentEnclitic().getPlural2());
        assertEquals("legna", forms.getVerb().getPreschentEnclitic().getPlural3());
    }

    @Test
    public void testRuleGir() {
        forms =  generator.generateConjugation("4", "sclingir");

        assertEquals("sclingir", forms.getVerb().getInfinitiv());

        assertEquals("eu scling", forms.getVerb().getPreschent().getSing1());
        assertEquals("tü sclingiast", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella sclingia", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus sclingin", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus sclingis\n(vo sclingivat)", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas sclingian", forms.getVerb().getPreschent().getPlural3());

        assertEquals("sclingia", forms.getVerb().getPreschentEnclitic().getSing1());
        assertEquals("sclingiast", forms.getVerb().getPreschentEnclitic().getSing2());
        assertEquals("sclingia'l", forms.getVerb().getPreschentEnclitic().getSing3m());
        assertEquals("scling'la", forms.getVerb().getPreschentEnclitic().getSing3f());
        assertEquals("sclingina", forms.getVerb().getPreschentEnclitic().getPlural1());
        assertEquals("sclingis\n(sclingivat)", forms.getVerb().getPreschentEnclitic().getPlural2());
        assertEquals("sclingna", forms.getVerb().getPreschentEnclitic().getPlural3());
    }

    @Test
    public void testRuleGlir() {
        forms =  generator.generateConjugation("4", "assaglir");

        assertEquals("assaglir", forms.getVerb().getInfinitiv());

        assertEquals("eu assagl", forms.getVerb().getPreschent().getSing1());
        assertEquals("tü assagliast", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella assaglia", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus assaglin", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus assaglis\n(vo assaglivat)", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas assaglian", forms.getVerb().getPreschent().getPlural3());

        assertEquals("assaglia", forms.getVerb().getPreschentEnclitic().getSing1());
        assertEquals("assagliast", forms.getVerb().getPreschentEnclitic().getSing2());
        assertEquals("assaglia'l", forms.getVerb().getPreschentEnclitic().getSing3m());
        assertEquals("assagl'la", forms.getVerb().getPreschentEnclitic().getSing3f());
        assertEquals("assaglina", forms.getVerb().getPreschentEnclitic().getPlural1());
        assertEquals("assaglis\n(assaglivat)", forms.getVerb().getPreschentEnclitic().getPlural2());
        assertEquals("assaglna", forms.getVerb().getPreschentEnclitic().getPlural3());
    }

    @Test
    public void testRuleZZ() {
        forms =  generator.generateConjugation("1", "drizzar");

        assertEquals("drizzar", forms.getVerb().getInfinitiv());

        assertEquals("eu driz", forms.getVerb().getPreschent().getSing1());
        assertEquals("tü drizzast", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella drizza", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus drizzain", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus drizzais\n(vo drizzaivat)", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas drizzan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("drizza", forms.getVerb().getPreschentEnclitic().getSing1());
        assertEquals("drizzast", forms.getVerb().getPreschentEnclitic().getSing2());
        assertEquals("drizza'l", forms.getVerb().getPreschentEnclitic().getSing3m());
        assertEquals("drizz'la", forms.getVerb().getPreschentEnclitic().getSing3f());
        assertEquals("drizzaina", forms.getVerb().getPreschentEnclitic().getPlural1());
        assertEquals("drizzais\n(drizzaivat)", forms.getVerb().getPreschentEnclitic().getPlural2());
        assertEquals("drizna", forms.getVerb().getPreschentEnclitic().getPlural3());
    }

    @Test
    public void testRule1reflexiv() {
        forms = generator.generateConjugation("1a", "as barricadar");

        assertEquals("am barricadescha", forms.getVerb().getPreschentEnclitic().getSing1());
        assertEquals("at barricadeschast", forms.getVerb().getPreschentEnclitic().getSing2());
        assertEquals("as barricadescha'l", forms.getVerb().getPreschentEnclitic().getSing3m());
        assertEquals("as barricadesch'la", forms.getVerb().getPreschentEnclitic().getSing3f());
        assertEquals("ans barricadaina", forms.getVerb().getPreschentEnclitic().getPlural1());
        assertEquals("as barricadais\n(as barricadaivat)", forms.getVerb().getPreschentEnclitic().getPlural2());
        assertEquals("as barricadeschna", forms.getVerb().getPreschentEnclitic().getPlural3());

        assertEquals("am barricadaiva", forms.getVerb().getImperfectEnclitic().getSing1());
        assertEquals("at barricadaivast", forms.getVerb().getImperfectEnclitic().getSing2());
        assertEquals("as barricadaiva'l", forms.getVerb().getImperfectEnclitic().getSing3m());
        assertEquals("as barricadaiv'la", forms.getVerb().getImperfectEnclitic().getSing3f());
        assertEquals("ans barricadaivna", forms.getVerb().getImperfectEnclitic().getPlural1());
        assertEquals("as barricadaivat", forms.getVerb().getImperfectEnclitic().getPlural2());
        assertEquals("as barricadaivna", forms.getVerb().getImperfectEnclitic().getPlural3());

        assertEquals("am barricadessa", forms.getVerb().getCundiziunalEnclitic().getSing1());
        assertEquals("at barricadessast", forms.getVerb().getCundiziunalEnclitic().getSing2());
        assertEquals("as barricadessa'l", forms.getVerb().getCundiziunalEnclitic().getSing3m());
        assertEquals("as barricadess'la", forms.getVerb().getCundiziunalEnclitic().getSing3f());
        assertEquals("ans barricadessna", forms.getVerb().getCundiziunalEnclitic().getPlural1());
        assertEquals("as barricadessat", forms.getVerb().getCundiziunalEnclitic().getPlural2());
        assertEquals("as barricadessna", forms.getVerb().getCundiziunalEnclitic().getPlural3());

        assertEquals("am barricadaraja", forms.getVerb().getFuturEnclitic().getSing1());
        assertEquals("at barricadarast", forms.getVerb().getFuturEnclitic().getSing2());
        assertEquals("as barricadarà'l", forms.getVerb().getFuturEnclitic().getSing3m());
        assertEquals("as barricadarà'la", forms.getVerb().getFuturEnclitic().getSing3f());
        assertEquals("ans barricadarana", forms.getVerb().getFuturEnclitic().getPlural1());
        assertEquals("as barricadarat", forms.getVerb().getFuturEnclitic().getPlural2());
        assertEquals("as barricadarana", forms.getVerb().getFuturEnclitic().getPlural3());
    }

    @Test
    public void testRule1reflexivVowel() {
        forms = generator.generateConjugation("1", "s'inachürar");

        assertEquals("m'inachüra", forms.getVerb().getPreschentEnclitic().getSing1());
        assertEquals("t'inachürast", forms.getVerb().getPreschentEnclitic().getSing2());
        assertEquals("s'inachüra'l", forms.getVerb().getPreschentEnclitic().getSing3m());
        assertEquals("s'inachür'la", forms.getVerb().getPreschentEnclitic().getSing3f());
        assertEquals("ans inachüraina", forms.getVerb().getPreschentEnclitic().getPlural1());
        assertEquals("s'inachürais\n(s'inachüraivat)", forms.getVerb().getPreschentEnclitic().getPlural2());
        assertEquals("s'inachürna", forms.getVerb().getPreschentEnclitic().getPlural3());

        assertEquals("m'inachüraiva", forms.getVerb().getImperfectEnclitic().getSing1());
        assertEquals("t'inachüraivast", forms.getVerb().getImperfectEnclitic().getSing2());
        assertEquals("s'inachüraiva'l", forms.getVerb().getImperfectEnclitic().getSing3m());
        assertEquals("s'inachüraiv'la", forms.getVerb().getImperfectEnclitic().getSing3f());
        assertEquals("ans inachüraivna", forms.getVerb().getImperfectEnclitic().getPlural1());
        assertEquals("s'inachüraivat", forms.getVerb().getImperfectEnclitic().getPlural2());
        assertEquals("s'inachüraivna", forms.getVerb().getImperfectEnclitic().getPlural3());

        assertEquals("m'inachüressa", forms.getVerb().getCundiziunalEnclitic().getSing1());
        assertEquals("t'inachüressast", forms.getVerb().getCundiziunalEnclitic().getSing2());
        assertEquals("s'inachüressa'l", forms.getVerb().getCundiziunalEnclitic().getSing3m());
        assertEquals("s'inachüress'la", forms.getVerb().getCundiziunalEnclitic().getSing3f());
        assertEquals("ans inachüressna", forms.getVerb().getCundiziunalEnclitic().getPlural1());
        assertEquals("s'inachüressat", forms.getVerb().getCundiziunalEnclitic().getPlural2());
        assertEquals("s'inachüressna", forms.getVerb().getCundiziunalEnclitic().getPlural3());

        assertEquals("m'inachüraraja", forms.getVerb().getFuturEnclitic().getSing1());
        assertEquals("t'inachürarast", forms.getVerb().getFuturEnclitic().getSing2());
        assertEquals("s'inachürarà'l", forms.getVerb().getFuturEnclitic().getSing3m());
        assertEquals("s'inachürarà'la", forms.getVerb().getFuturEnclitic().getSing3f());
        assertEquals("ans inachürarana", forms.getVerb().getFuturEnclitic().getPlural1());
        assertEquals("s'inachürarat", forms.getVerb().getFuturEnclitic().getPlural2());
        assertEquals("s'inachürarana", forms.getVerb().getFuturEnclitic().getPlural3());
    }
}
