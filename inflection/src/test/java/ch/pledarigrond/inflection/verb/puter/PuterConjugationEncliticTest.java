package ch.pledarigrond.inflection.verb.puter;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.verb.puter.PuterConjugation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PuterConjugationEncliticTest {

    private static PuterConjugation generator;
    private InflectionDto forms;

    @BeforeAll
    public static void initialize() {
        generator = new PuterConjugation();
    }

    @Test
    public void testRuleEr() {
        forms =  generator.generateConjugation("1", "manger");

        assertEquals("manger", forms.getVerb().getInfinitiv());

        assertEquals("mangi", forms.getVerb().getPreschentEnclitic().getSing1());
        assertEquals("mangiast", forms.getVerb().getPreschentEnclitic().getSing2());
        assertEquals("mangia'l", forms.getVerb().getPreschentEnclitic().getSing3m());
        assertEquals("mang'la", forms.getVerb().getPreschentEnclitic().getSing3f());
        assertEquals("mangiainsa", forms.getVerb().getPreschentEnclitic().getPlural1());
        assertEquals("mangiais", forms.getVerb().getPreschentEnclitic().getPlural2());
        assertEquals("mangiane", forms.getVerb().getPreschentEnclitic().getPlural3());

        assertEquals("mangiaivi", forms.getVerb().getImperfectEnclitic().getSing1());
        assertEquals("mangiaivast", forms.getVerb().getImperfectEnclitic().getSing2());
        assertEquals("mangiaiva'l", forms.getVerb().getImperfectEnclitic().getSing3m());
        assertEquals("mangiaiv'la", forms.getVerb().getImperfectEnclitic().getSing3f());
        assertEquals("mangiaivans", forms.getVerb().getImperfectEnclitic().getPlural1());
        assertEquals("mangiaivas", forms.getVerb().getImperfectEnclitic().getPlural2());
        assertEquals("mangiaivane", forms.getVerb().getImperfectEnclitic().getPlural3());

        assertEquals("mangessi", forms.getVerb().getCundizionalEnclitic().getSing1());
        assertEquals("mangessast", forms.getVerb().getCundizionalEnclitic().getSing2());
        assertEquals("mangessa'l", forms.getVerb().getCundizionalEnclitic().getSing3m());
        assertEquals("mangess'la", forms.getVerb().getCundizionalEnclitic().getSing3f());
        assertEquals("mangessans", forms.getVerb().getCundizionalEnclitic().getPlural1());
        assertEquals("mangessas", forms.getVerb().getCundizionalEnclitic().getPlural2());
        assertEquals("mangessane", forms.getVerb().getCundizionalEnclitic().getPlural3());

        assertEquals("mangiaro eau", forms.getVerb().getFuturEnclitic().getSing1());
        assertEquals("mangiarost", forms.getVerb().getFuturEnclitic().getSing2());
        assertEquals("mangiaro'l", forms.getVerb().getFuturEnclitic().getSing3m());
        assertEquals("mangiaro'la", forms.getVerb().getFuturEnclitic().getSing3f());
        assertEquals("mangiaronsa", forms.getVerb().getFuturEnclitic().getPlural1());
        assertEquals("mangiaros", forms.getVerb().getFuturEnclitic().getPlural2());
        assertEquals("mangiarone", forms.getVerb().getFuturEnclitic().getPlural3());

        assertEquals("mangiaregi", forms.getVerb().getFuturDubitativEnclitic().getSing1());
        assertEquals("mangiaregiast", forms.getVerb().getFuturDubitativEnclitic().getSing2());
        assertEquals("mangiaregia'l", forms.getVerb().getFuturDubitativEnclitic().getSing3m());
        assertEquals("mangiareg'la", forms.getVerb().getFuturDubitativEnclitic().getSing3f());
        assertEquals("mangiaregians", forms.getVerb().getFuturDubitativEnclitic().getPlural1());
        assertEquals("mangiaregias", forms.getVerb().getFuturDubitativEnclitic().getPlural2());
        assertEquals("mangiaregiane", forms.getVerb().getFuturDubitativEnclitic().getPlural3());
    }

    @Test
    public void testRuleCker() {
        forms =  generator.generateConjugation("1", "blocker");

        assertEquals("blocker", forms.getVerb().getInfinitiv());

        assertEquals("eau bloc", forms.getVerb().getPreschent().getSing1());
        assertEquals("tü bloccast", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella blocca", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus bloccains", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus bloccais", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas bloccan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("blocki", forms.getVerb().getPreschentEnclitic().getSing1());
        assertEquals("bloccast", forms.getVerb().getPreschentEnclitic().getSing2());
        assertEquals("blocca'l", forms.getVerb().getPreschentEnclitic().getSing3m());
        assertEquals("blocc'la", forms.getVerb().getPreschentEnclitic().getSing3f());
        assertEquals("bloccainsa", forms.getVerb().getPreschentEnclitic().getPlural1());
        assertEquals("bloccais", forms.getVerb().getPreschentEnclitic().getPlural2());
        assertEquals("bloccane", forms.getVerb().getPreschentEnclitic().getPlural3());
    }

    @Test
    public void testRuleGlier() {
        forms =  generator.generateConjugation("1", "piglier");

        assertEquals("piglier", forms.getVerb().getInfinitiv());

        assertEquals("eau pigl", forms.getVerb().getPreschent().getSing1());
        assertEquals("tü pigliast", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella piglia", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus pigliains", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus pigliais", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas piglian", forms.getVerb().getPreschent().getPlural3());

        assertEquals("pigli", forms.getVerb().getPreschentEnclitic().getSing1());
        assertEquals("pigliast", forms.getVerb().getPreschentEnclitic().getSing2());
        assertEquals("piglia'l", forms.getVerb().getPreschentEnclitic().getSing3m());
        assertEquals("pigl'la", forms.getVerb().getPreschentEnclitic().getSing3f());
        assertEquals("pigliainsa", forms.getVerb().getPreschentEnclitic().getPlural1());
        assertEquals("pigliais", forms.getVerb().getPreschentEnclitic().getPlural2());
        assertEquals("pigliane", forms.getVerb().getPreschentEnclitic().getPlural3());
    }

    @Test
    public void testRuleFfer() {
        forms =  generator.generateConjugation("1", "püffer");

        assertEquals("püffer", forms.getVerb().getInfinitiv());

        assertEquals("eau püf", forms.getVerb().getPreschent().getSing1());
        assertEquals("tü püffast", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella püffa", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus püffains", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus püffais", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas püffan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("püffi", forms.getVerb().getPreschentEnclitic().getSing1());
        assertEquals("püffast", forms.getVerb().getPreschentEnclitic().getSing2());
        assertEquals("püffa'l", forms.getVerb().getPreschentEnclitic().getSing3m());
        assertEquals("püff'la", forms.getVerb().getPreschentEnclitic().getSing3f());
        assertEquals("püffainsa", forms.getVerb().getPreschentEnclitic().getPlural1());
        assertEquals("püffais", forms.getVerb().getPreschentEnclitic().getPlural2());
        assertEquals("püffane", forms.getVerb().getPreschentEnclitic().getPlural3());
    }

    @Test
    public void testRuleZzer() {
        forms =  generator.generateConjugation("1", "drizzer");

        assertEquals("drizzer", forms.getVerb().getInfinitiv());

        assertEquals("eau driz", forms.getVerb().getPreschent().getSing1());
        assertEquals("tü drizzast", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella drizza", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus drizzains", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus drizzais", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas drizzan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("drizzi", forms.getVerb().getPreschentEnclitic().getSing1());
        assertEquals("drizzast", forms.getVerb().getPreschentEnclitic().getSing2());
        assertEquals("drizza'l", forms.getVerb().getPreschentEnclitic().getSing3m());
        assertEquals("drizz'la", forms.getVerb().getPreschentEnclitic().getSing3f());
        assertEquals("drizzainsa", forms.getVerb().getPreschentEnclitic().getPlural1());
        assertEquals("drizzais", forms.getVerb().getPreschentEnclitic().getPlural2());
        assertEquals("drizzane", forms.getVerb().getPreschentEnclitic().getPlural3());
    }

    @Test
    public void testRule1reflexiv() {
        forms = generator.generateConjugation("1", "as s-chaluner");

        assertEquals("am s-chaluni", forms.getVerb().getPreschentEnclitic().getSing1());
        assertEquals("at s-chalunast", forms.getVerb().getPreschentEnclitic().getSing2());
        assertEquals("as s-chaluna'l", forms.getVerb().getPreschentEnclitic().getSing3m());
        assertEquals("as s-chalun'la", forms.getVerb().getPreschentEnclitic().getSing3f());
        assertEquals("ans s-chalunainsa", forms.getVerb().getPreschentEnclitic().getPlural1());
        assertEquals("as s-chalunais", forms.getVerb().getPreschentEnclitic().getPlural2());
        assertEquals("as s-chalunane", forms.getVerb().getPreschentEnclitic().getPlural3());

        assertEquals("am s-chalunaivi", forms.getVerb().getImperfectEnclitic().getSing1());
        assertEquals("at s-chalunaivast", forms.getVerb().getImperfectEnclitic().getSing2());
        assertEquals("as s-chalunaiva'l", forms.getVerb().getImperfectEnclitic().getSing3m());
        assertEquals("as s-chalunaiv'la", forms.getVerb().getImperfectEnclitic().getSing3f());
        assertEquals("ans s-chalunaivans", forms.getVerb().getImperfectEnclitic().getPlural1());
        assertEquals("as s-chalunaivas", forms.getVerb().getImperfectEnclitic().getPlural2());
        assertEquals("as s-chalunaivane", forms.getVerb().getImperfectEnclitic().getPlural3());

        assertEquals("am s-chalunessi", forms.getVerb().getCundizionalEnclitic().getSing1());
        assertEquals("at s-chalunessast", forms.getVerb().getCundizionalEnclitic().getSing2());
        assertEquals("as s-chalunessa'l", forms.getVerb().getCundizionalEnclitic().getSing3m());
        assertEquals("as s-chaluness'la", forms.getVerb().getCundizionalEnclitic().getSing3f());
        assertEquals("ans s-chalunessans", forms.getVerb().getCundizionalEnclitic().getPlural1());
        assertEquals("as s-chalunessas", forms.getVerb().getCundizionalEnclitic().getPlural2());
        assertEquals("as s-chalunessane", forms.getVerb().getCundizionalEnclitic().getPlural3());

        assertEquals("am s-chalunaro eau", forms.getVerb().getFuturEnclitic().getSing1());
        assertEquals("at s-chalunarost", forms.getVerb().getFuturEnclitic().getSing2());
        assertEquals("as s-chalunaro'l", forms.getVerb().getFuturEnclitic().getSing3m());
        assertEquals("as s-chalunaro'la", forms.getVerb().getFuturEnclitic().getSing3f());
        assertEquals("ans s-chalunaronsa", forms.getVerb().getFuturEnclitic().getPlural1());
        assertEquals("as s-chalunaros", forms.getVerb().getFuturEnclitic().getPlural2());
        assertEquals("as s-chalunarone", forms.getVerb().getFuturEnclitic().getPlural3());

        assertEquals("am s-chalunaregi", forms.getVerb().getFuturDubitativEnclitic().getSing1());
        assertEquals("at s-chalunaregiast", forms.getVerb().getFuturDubitativEnclitic().getSing2());
        assertEquals("as s-chalunaregia'l", forms.getVerb().getFuturDubitativEnclitic().getSing3m());
        assertEquals("as s-chalunareg'la", forms.getVerb().getFuturDubitativEnclitic().getSing3f());
        assertEquals("ans s-chalunaregians", forms.getVerb().getFuturDubitativEnclitic().getPlural1());
        assertEquals("as s-chalunaregias", forms.getVerb().getFuturDubitativEnclitic().getPlural2());
        assertEquals("as s-chalunaregiane", forms.getVerb().getFuturDubitativEnclitic().getPlural3());
    }

    @Test
    public void testRule1reflexivVowel() {
        forms = generator.generateConjugation("1", "s'abasser");

        assertEquals("m'abassi", forms.getVerb().getPreschentEnclitic().getSing1());
        assertEquals("t'abassast", forms.getVerb().getPreschentEnclitic().getSing2());
        assertEquals("s'abassa'l", forms.getVerb().getPreschentEnclitic().getSing3m());
        assertEquals("s'abass'la", forms.getVerb().getPreschentEnclitic().getSing3f());
        assertEquals("ans abassainsa", forms.getVerb().getPreschentEnclitic().getPlural1());
        assertEquals("s'abassais", forms.getVerb().getPreschentEnclitic().getPlural2());
        assertEquals("s'abassane", forms.getVerb().getPreschentEnclitic().getPlural3());

        assertEquals("m'abassaivi", forms.getVerb().getImperfectEnclitic().getSing1());
        assertEquals("t'abassaivast", forms.getVerb().getImperfectEnclitic().getSing2());
        assertEquals("s'abassaiva'l", forms.getVerb().getImperfectEnclitic().getSing3m());
        assertEquals("s'abassaiv'la", forms.getVerb().getImperfectEnclitic().getSing3f());
        assertEquals("ans abassaivans", forms.getVerb().getImperfectEnclitic().getPlural1());
        assertEquals("s'abassaivas", forms.getVerb().getImperfectEnclitic().getPlural2());
        assertEquals("s'abassaivane", forms.getVerb().getImperfectEnclitic().getPlural3());

        assertEquals("m'abassessi", forms.getVerb().getCundizionalEnclitic().getSing1());
        assertEquals("t'abassessast", forms.getVerb().getCundizionalEnclitic().getSing2());
        assertEquals("s'abassessa'l", forms.getVerb().getCundizionalEnclitic().getSing3m());
        assertEquals("s'abassess'la", forms.getVerb().getCundizionalEnclitic().getSing3f());
        assertEquals("ans abassessans", forms.getVerb().getCundizionalEnclitic().getPlural1());
        assertEquals("s'abassessas", forms.getVerb().getCundizionalEnclitic().getPlural2());
        assertEquals("s'abassessane", forms.getVerb().getCundizionalEnclitic().getPlural3());

        assertEquals("m'abassaro eau", forms.getVerb().getFuturEnclitic().getSing1());
        assertEquals("t'abassarost", forms.getVerb().getFuturEnclitic().getSing2());
        assertEquals("s'abassaro'l", forms.getVerb().getFuturEnclitic().getSing3m());
        assertEquals("s'abassaro'la", forms.getVerb().getFuturEnclitic().getSing3f());
        assertEquals("ans abassaronsa", forms.getVerb().getFuturEnclitic().getPlural1());
        assertEquals("s'abassaros", forms.getVerb().getFuturEnclitic().getPlural2());
        assertEquals("s'abassarone", forms.getVerb().getFuturEnclitic().getPlural3());

        assertEquals("m'abassaregi", forms.getVerb().getFuturDubitativEnclitic().getSing1());
        assertEquals("t'abassaregiast", forms.getVerb().getFuturDubitativEnclitic().getSing2());
        assertEquals("s'abassaregia'l", forms.getVerb().getFuturDubitativEnclitic().getSing3m());
        assertEquals("s'abassareg'la", forms.getVerb().getFuturDubitativEnclitic().getSing3f());
        assertEquals("ans abassaregians", forms.getVerb().getFuturDubitativEnclitic().getPlural1());
        assertEquals("s'abassaregias", forms.getVerb().getFuturDubitativEnclitic().getPlural2());
        assertEquals("s'abassaregiane", forms.getVerb().getFuturDubitativEnclitic().getPlural3());
    }
}
