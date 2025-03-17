package ch.pledarigrond.inflection.verb.vallader;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.verb.vallader.ValladerConjugation;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ValladerConjugationEncliticTest {

    private static ValladerConjugation generator;
    private InflectionDto forms;

    @BeforeClass
    public static void initialize() {
        generator = new ValladerConjugation();
    }

    @Test
    public void testRuleAr() {
        forms =  generator.generateConjugation("1", "müdar");

        Assert.assertEquals("müdar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("müda", forms.getVerb().getPreschentEnclitic().getSing1());
        Assert.assertEquals("müdast", forms.getVerb().getPreschentEnclitic().getSing2());
        Assert.assertEquals("müda'l", forms.getVerb().getPreschentEnclitic().getSing3m());
        Assert.assertEquals("müd'la", forms.getVerb().getPreschentEnclitic().getSing3f());
        Assert.assertEquals("müdaina", forms.getVerb().getPreschentEnclitic().getPlural1());
        Assert.assertEquals("müdais\n(müdaivat)", forms.getVerb().getPreschentEnclitic().getPlural2());
        Assert.assertEquals("müdna", forms.getVerb().getPreschentEnclitic().getPlural3());

        Assert.assertEquals("müdaiva", forms.getVerb().getImperfectEnclitic().getSing1());
        Assert.assertEquals("müdaivast", forms.getVerb().getImperfectEnclitic().getSing2());
        Assert.assertEquals("müdaiva'l", forms.getVerb().getImperfectEnclitic().getSing3m());
        Assert.assertEquals("müdaiv'la", forms.getVerb().getImperfectEnclitic().getSing3f());
        Assert.assertEquals("müdaivna", forms.getVerb().getImperfectEnclitic().getPlural1());
        Assert.assertEquals("müdaivat", forms.getVerb().getImperfectEnclitic().getPlural2());
        Assert.assertEquals("müdaivna", forms.getVerb().getImperfectEnclitic().getPlural3());

        Assert.assertEquals("müdessa", forms.getVerb().getCundizionalEnclitic().getSing1());
        Assert.assertEquals("müdessast", forms.getVerb().getCundizionalEnclitic().getSing2());
        Assert.assertEquals("müdessa'l", forms.getVerb().getCundizionalEnclitic().getSing3m());
        Assert.assertEquals("müdess'la", forms.getVerb().getCundizionalEnclitic().getSing3f());
        Assert.assertEquals("müdessna", forms.getVerb().getCundizionalEnclitic().getPlural1());
        Assert.assertEquals("müdessat", forms.getVerb().getCundizionalEnclitic().getPlural2());
        Assert.assertEquals("müdessna", forms.getVerb().getCundizionalEnclitic().getPlural3());

        Assert.assertEquals("müdaraja", forms.getVerb().getFuturEnclitic().getSing1());
        Assert.assertEquals("müdarast", forms.getVerb().getFuturEnclitic().getSing2());
        Assert.assertEquals("müdarà'l", forms.getVerb().getFuturEnclitic().getSing3m());
        Assert.assertEquals("müdarà'la", forms.getVerb().getFuturEnclitic().getSing3f());
        Assert.assertEquals("müdarana", forms.getVerb().getFuturEnclitic().getPlural1());
        Assert.assertEquals("müdarat", forms.getVerb().getFuturEnclitic().getPlural2());
        Assert.assertEquals("müdarana", forms.getVerb().getFuturEnclitic().getPlural3());
    }

    @Test
    public void testRuleIr() {
        forms =  generator.generateConjugation("4", "partir");

        Assert.assertEquals("partir", forms.getVerb().getInfinitiv());

        Assert.assertEquals("parta", forms.getVerb().getPreschentEnclitic().getSing1());
        Assert.assertEquals("partast", forms.getVerb().getPreschentEnclitic().getSing2());
        Assert.assertEquals("parta'l", forms.getVerb().getPreschentEnclitic().getSing3m());
        Assert.assertEquals("part'la", forms.getVerb().getPreschentEnclitic().getSing3f());
        Assert.assertEquals("partina", forms.getVerb().getPreschentEnclitic().getPlural1());
        Assert.assertEquals("partis\n(partivat)", forms.getVerb().getPreschentEnclitic().getPlural2());
        Assert.assertEquals("partna", forms.getVerb().getPreschentEnclitic().getPlural3());

        Assert.assertEquals("partiva", forms.getVerb().getImperfectEnclitic().getSing1());
        Assert.assertEquals("partivast", forms.getVerb().getImperfectEnclitic().getSing2());
        Assert.assertEquals("partiva'l", forms.getVerb().getImperfectEnclitic().getSing3m());
        Assert.assertEquals("partiv'la", forms.getVerb().getImperfectEnclitic().getSing3f());
        Assert.assertEquals("partivna", forms.getVerb().getImperfectEnclitic().getPlural1());
        Assert.assertEquals("partivat", forms.getVerb().getImperfectEnclitic().getPlural2());
        Assert.assertEquals("partivna", forms.getVerb().getImperfectEnclitic().getPlural3());

        Assert.assertEquals("partissa", forms.getVerb().getCundizionalEnclitic().getSing1());
        Assert.assertEquals("partissast", forms.getVerb().getCundizionalEnclitic().getSing2());
        Assert.assertEquals("partissa'l", forms.getVerb().getCundizionalEnclitic().getSing3m());
        Assert.assertEquals("partiss'la", forms.getVerb().getCundizionalEnclitic().getSing3f());
        Assert.assertEquals("partissna", forms.getVerb().getCundizionalEnclitic().getPlural1());
        Assert.assertEquals("partissat", forms.getVerb().getCundizionalEnclitic().getPlural2());
        Assert.assertEquals("partissna", forms.getVerb().getCundizionalEnclitic().getPlural3());

        Assert.assertEquals("partiraja", forms.getVerb().getFuturEnclitic().getSing1());
        Assert.assertEquals("partirast", forms.getVerb().getFuturEnclitic().getSing2());
        Assert.assertEquals("partirà'l", forms.getVerb().getFuturEnclitic().getSing3m());
        Assert.assertEquals("partirà'la", forms.getVerb().getFuturEnclitic().getSing3f());
        Assert.assertEquals("partirana", forms.getVerb().getFuturEnclitic().getPlural1());
        Assert.assertEquals("partirat", forms.getVerb().getFuturEnclitic().getPlural2());
        Assert.assertEquals("partirana", forms.getVerb().getFuturEnclitic().getPlural3());
    }

    @Test
    public void testRuleGiar() {
        forms =  generator.generateConjugation("1", "mangiar");

        Assert.assertEquals("mangiar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("eu mang", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tü mangiast", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella mangia", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus mangiain", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus mangiais\n(vo mangiaivat)", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas mangian", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("mangia", forms.getVerb().getPreschentEnclitic().getSing1());
        Assert.assertEquals("mangiast", forms.getVerb().getPreschentEnclitic().getSing2());
        Assert.assertEquals("mangia'l", forms.getVerb().getPreschentEnclitic().getSing3m());
        Assert.assertEquals("mang'la", forms.getVerb().getPreschentEnclitic().getSing3f());
        Assert.assertEquals("mangiaina", forms.getVerb().getPreschentEnclitic().getPlural1());
        Assert.assertEquals("mangiais\n(mangiaivat)", forms.getVerb().getPreschentEnclitic().getPlural2());
        Assert.assertEquals("mangna", forms.getVerb().getPreschentEnclitic().getPlural3());
    }

    @Test
    public void testRuleGliar() {
        forms =  generator.generateConjugation("1", "tagliar");

        Assert.assertEquals("tagliar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("eu tagl", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tü tagliast", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella taglia", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus tagliain", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus tagliais\n(vo tagliaivat)", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas taglian", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("taglia", forms.getVerb().getPreschentEnclitic().getSing1());
        Assert.assertEquals("tagliast", forms.getVerb().getPreschentEnclitic().getSing2());
        Assert.assertEquals("taglia'l", forms.getVerb().getPreschentEnclitic().getSing3m());
        Assert.assertEquals("tagl'la", forms.getVerb().getPreschentEnclitic().getSing3f());
        Assert.assertEquals("tagliaina", forms.getVerb().getPreschentEnclitic().getPlural1());
        Assert.assertEquals("tagliais\n(tagliaivat)", forms.getVerb().getPreschentEnclitic().getPlural2());
        Assert.assertEquals("taglna", forms.getVerb().getPreschentEnclitic().getPlural3());
    }

    @Test
    public void testRuleGer() {
        forms =  generator.generateConjugation("3", "leger");

        Assert.assertEquals("leger", forms.getVerb().getInfinitiv());

        Assert.assertEquals("eu leg", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tü legiast", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella legia", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus legiain", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus legiais\n(vo legiaivat)", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas legian", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("legia", forms.getVerb().getPreschentEnclitic().getSing1());
        Assert.assertEquals("legiast", forms.getVerb().getPreschentEnclitic().getSing2());
        Assert.assertEquals("legia'l", forms.getVerb().getPreschentEnclitic().getSing3m());
        Assert.assertEquals("leg'la", forms.getVerb().getPreschentEnclitic().getSing3f());
        Assert.assertEquals("legiaina", forms.getVerb().getPreschentEnclitic().getPlural1());
        Assert.assertEquals("legiais\n(legiaivat)", forms.getVerb().getPreschentEnclitic().getPlural2());
        Assert.assertEquals("legna", forms.getVerb().getPreschentEnclitic().getPlural3());
    }

    @Test
    public void testRuleGir() {
        forms =  generator.generateConjugation("4", "sclingir");

        Assert.assertEquals("sclingir", forms.getVerb().getInfinitiv());

        Assert.assertEquals("eu scling", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tü sclingiast", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella sclingia", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus sclingin", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus sclingis\n(vo sclingivat)", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas sclingian", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("sclingia", forms.getVerb().getPreschentEnclitic().getSing1());
        Assert.assertEquals("sclingiast", forms.getVerb().getPreschentEnclitic().getSing2());
        Assert.assertEquals("sclingia'l", forms.getVerb().getPreschentEnclitic().getSing3m());
        Assert.assertEquals("scling'la", forms.getVerb().getPreschentEnclitic().getSing3f());
        Assert.assertEquals("sclingina", forms.getVerb().getPreschentEnclitic().getPlural1());
        Assert.assertEquals("sclingis\n(sclingivat)", forms.getVerb().getPreschentEnclitic().getPlural2());
        Assert.assertEquals("sclingna", forms.getVerb().getPreschentEnclitic().getPlural3());
    }

    @Test
    public void testRuleGlir() {
        forms =  generator.generateConjugation("4", "assaglir");

        Assert.assertEquals("assaglir", forms.getVerb().getInfinitiv());

        Assert.assertEquals("eu assagl", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tü assagliast", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella assaglia", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus assaglin", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus assaglis\n(vo assaglivat)", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas assaglian", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("assaglia", forms.getVerb().getPreschentEnclitic().getSing1());
        Assert.assertEquals("assagliast", forms.getVerb().getPreschentEnclitic().getSing2());
        Assert.assertEquals("assaglia'l", forms.getVerb().getPreschentEnclitic().getSing3m());
        Assert.assertEquals("assagl'la", forms.getVerb().getPreschentEnclitic().getSing3f());
        Assert.assertEquals("assaglina", forms.getVerb().getPreschentEnclitic().getPlural1());
        Assert.assertEquals("assaglis\n(assaglivat)", forms.getVerb().getPreschentEnclitic().getPlural2());
        Assert.assertEquals("assaglna", forms.getVerb().getPreschentEnclitic().getPlural3());
    }

    @Test
    public void testRuleZZ() {
        forms =  generator.generateConjugation("1", "drizzar");

        Assert.assertEquals("drizzar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("eu driz", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tü drizzast", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella drizza", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus drizzain", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus drizzais\n(vo drizzaivat)", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas drizzan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("drizza", forms.getVerb().getPreschentEnclitic().getSing1());
        Assert.assertEquals("drizzast", forms.getVerb().getPreschentEnclitic().getSing2());
        Assert.assertEquals("drizza'l", forms.getVerb().getPreschentEnclitic().getSing3m());
        Assert.assertEquals("drizz'la", forms.getVerb().getPreschentEnclitic().getSing3f());
        Assert.assertEquals("drizzaina", forms.getVerb().getPreschentEnclitic().getPlural1());
        Assert.assertEquals("drizzais\n(drizzaivat)", forms.getVerb().getPreschentEnclitic().getPlural2());
        Assert.assertEquals("drizna", forms.getVerb().getPreschentEnclitic().getPlural3());
    }

    @Test
    public void testRule1reflexiv() {
        forms = generator.generateConjugation("1a", "as barricadar");

        Assert.assertEquals("am barricadescha", forms.getVerb().getPreschentEnclitic().getSing1());
        Assert.assertEquals("at barricadeschast", forms.getVerb().getPreschentEnclitic().getSing2());
        Assert.assertEquals("as barricadescha'l", forms.getVerb().getPreschentEnclitic().getSing3m());
        Assert.assertEquals("as barricadesch'la", forms.getVerb().getPreschentEnclitic().getSing3f());
        Assert.assertEquals("ans barricadaina", forms.getVerb().getPreschentEnclitic().getPlural1());
        Assert.assertEquals("as barricadais\n(as barricadaivat)", forms.getVerb().getPreschentEnclitic().getPlural2());
        Assert.assertEquals("as barricadeschna", forms.getVerb().getPreschentEnclitic().getPlural3());

        Assert.assertEquals("am barricadaiva", forms.getVerb().getImperfectEnclitic().getSing1());
        Assert.assertEquals("at barricadaivast", forms.getVerb().getImperfectEnclitic().getSing2());
        Assert.assertEquals("as barricadaiva'l", forms.getVerb().getImperfectEnclitic().getSing3m());
        Assert.assertEquals("as barricadaiv'la", forms.getVerb().getImperfectEnclitic().getSing3f());
        Assert.assertEquals("ans barricadaivna", forms.getVerb().getImperfectEnclitic().getPlural1());
        Assert.assertEquals("as barricadaivat", forms.getVerb().getImperfectEnclitic().getPlural2());
        Assert.assertEquals("as barricadaivna", forms.getVerb().getImperfectEnclitic().getPlural3());

        Assert.assertEquals("am barricadessa", forms.getVerb().getCundizionalEnclitic().getSing1());
        Assert.assertEquals("at barricadessast", forms.getVerb().getCundizionalEnclitic().getSing2());
        Assert.assertEquals("as barricadessa'l", forms.getVerb().getCundizionalEnclitic().getSing3m());
        Assert.assertEquals("as barricadess'la", forms.getVerb().getCundizionalEnclitic().getSing3f());
        Assert.assertEquals("ans barricadessna", forms.getVerb().getCundizionalEnclitic().getPlural1());
        Assert.assertEquals("as barricadessat", forms.getVerb().getCundizionalEnclitic().getPlural2());
        Assert.assertEquals("as barricadessna", forms.getVerb().getCundizionalEnclitic().getPlural3());

        Assert.assertEquals("am barricadaraja", forms.getVerb().getFuturEnclitic().getSing1());
        Assert.assertEquals("at barricadarast", forms.getVerb().getFuturEnclitic().getSing2());
        Assert.assertEquals("as barricadarà'l", forms.getVerb().getFuturEnclitic().getSing3m());
        Assert.assertEquals("as barricadarà'la", forms.getVerb().getFuturEnclitic().getSing3f());
        Assert.assertEquals("ans barricadarana", forms.getVerb().getFuturEnclitic().getPlural1());
        Assert.assertEquals("as barricadarat", forms.getVerb().getFuturEnclitic().getPlural2());
        Assert.assertEquals("as barricadarana", forms.getVerb().getFuturEnclitic().getPlural3());
    }

    @Test
    public void testRule1reflexivVowel() {
        forms = generator.generateConjugation("1", "s'inachürar");

        Assert.assertEquals("m'inachüra", forms.getVerb().getPreschentEnclitic().getSing1());
        Assert.assertEquals("t'inachürast", forms.getVerb().getPreschentEnclitic().getSing2());
        Assert.assertEquals("s'inachüra'l", forms.getVerb().getPreschentEnclitic().getSing3m());
        Assert.assertEquals("s'inachür'la", forms.getVerb().getPreschentEnclitic().getSing3f());
        Assert.assertEquals("ans inachüraina", forms.getVerb().getPreschentEnclitic().getPlural1());
        Assert.assertEquals("s'inachürais\n(s'inachüraivat)", forms.getVerb().getPreschentEnclitic().getPlural2());
        Assert.assertEquals("s'inachürna", forms.getVerb().getPreschentEnclitic().getPlural3());

        Assert.assertEquals("m'inachüraiva", forms.getVerb().getImperfectEnclitic().getSing1());
        Assert.assertEquals("t'inachüraivast", forms.getVerb().getImperfectEnclitic().getSing2());
        Assert.assertEquals("s'inachüraiva'l", forms.getVerb().getImperfectEnclitic().getSing3m());
        Assert.assertEquals("s'inachüraiv'la", forms.getVerb().getImperfectEnclitic().getSing3f());
        Assert.assertEquals("ans inachüraivna", forms.getVerb().getImperfectEnclitic().getPlural1());
        Assert.assertEquals("s'inachüraivat", forms.getVerb().getImperfectEnclitic().getPlural2());
        Assert.assertEquals("s'inachüraivna", forms.getVerb().getImperfectEnclitic().getPlural3());

        Assert.assertEquals("m'inachüressa", forms.getVerb().getCundizionalEnclitic().getSing1());
        Assert.assertEquals("t'inachüressast", forms.getVerb().getCundizionalEnclitic().getSing2());
        Assert.assertEquals("s'inachüressa'l", forms.getVerb().getCundizionalEnclitic().getSing3m());
        Assert.assertEquals("s'inachüress'la", forms.getVerb().getCundizionalEnclitic().getSing3f());
        Assert.assertEquals("ans inachüressna", forms.getVerb().getCundizionalEnclitic().getPlural1());
        Assert.assertEquals("s'inachüressat", forms.getVerb().getCundizionalEnclitic().getPlural2());
        Assert.assertEquals("s'inachüressna", forms.getVerb().getCundizionalEnclitic().getPlural3());

        Assert.assertEquals("m'inachüraraja", forms.getVerb().getFuturEnclitic().getSing1());
        Assert.assertEquals("t'inachürarast", forms.getVerb().getFuturEnclitic().getSing2());
        Assert.assertEquals("s'inachürarà'l", forms.getVerb().getFuturEnclitic().getSing3m());
        Assert.assertEquals("s'inachürarà'la", forms.getVerb().getFuturEnclitic().getSing3f());
        Assert.assertEquals("ans inachürarana", forms.getVerb().getFuturEnclitic().getPlural1());
        Assert.assertEquals("s'inachürarat", forms.getVerb().getFuturEnclitic().getPlural2());
        Assert.assertEquals("s'inachürarana", forms.getVerb().getFuturEnclitic().getPlural3());
    }
}
