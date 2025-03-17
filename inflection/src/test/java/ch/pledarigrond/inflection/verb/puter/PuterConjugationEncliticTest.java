package ch.pledarigrond.inflection.verb.puter;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.verb.puter.PuterConjugation;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class PuterConjugationEncliticTest {

    private static PuterConjugation generator;
    private InflectionDto forms;

    @BeforeClass
    public static void initialize() {
        generator = new PuterConjugation();
    }

    @Test
    public void testRuleEr() {
        forms =  generator.generateConjugation("1", "manger");

        Assert.assertEquals("manger", forms.getVerb().getInfinitiv());

        Assert.assertEquals("mangi", forms.getVerb().getPreschentEnclitic().getSing1());
        Assert.assertEquals("mangiast", forms.getVerb().getPreschentEnclitic().getSing2());
        Assert.assertEquals("mangia'l", forms.getVerb().getPreschentEnclitic().getSing3m());
        Assert.assertEquals("mang'la", forms.getVerb().getPreschentEnclitic().getSing3f());
        Assert.assertEquals("mangiainsa", forms.getVerb().getPreschentEnclitic().getPlural1());
        Assert.assertEquals("mangiais", forms.getVerb().getPreschentEnclitic().getPlural2());
        Assert.assertEquals("mangiane", forms.getVerb().getPreschentEnclitic().getPlural3());

        Assert.assertEquals("mangiaivi", forms.getVerb().getImperfectEnclitic().getSing1());
        Assert.assertEquals("mangiaivast", forms.getVerb().getImperfectEnclitic().getSing2());
        Assert.assertEquals("mangiaiva'l", forms.getVerb().getImperfectEnclitic().getSing3m());
        Assert.assertEquals("mangiaiv'la", forms.getVerb().getImperfectEnclitic().getSing3f());
        Assert.assertEquals("mangiaivans", forms.getVerb().getImperfectEnclitic().getPlural1());
        Assert.assertEquals("mangiaivas", forms.getVerb().getImperfectEnclitic().getPlural2());
        Assert.assertEquals("mangiaivane", forms.getVerb().getImperfectEnclitic().getPlural3());

        Assert.assertEquals("mangessi", forms.getVerb().getCundizionalEnclitic().getSing1());
        Assert.assertEquals("mangessast", forms.getVerb().getCundizionalEnclitic().getSing2());
        Assert.assertEquals("mangessa'l", forms.getVerb().getCundizionalEnclitic().getSing3m());
        Assert.assertEquals("mangess'la", forms.getVerb().getCundizionalEnclitic().getSing3f());
        Assert.assertEquals("mangessans", forms.getVerb().getCundizionalEnclitic().getPlural1());
        Assert.assertEquals("mangessas", forms.getVerb().getCundizionalEnclitic().getPlural2());
        Assert.assertEquals("mangessane", forms.getVerb().getCundizionalEnclitic().getPlural3());

        Assert.assertEquals("mangiaro eau", forms.getVerb().getFuturEnclitic().getSing1());
        Assert.assertEquals("mangiarost", forms.getVerb().getFuturEnclitic().getSing2());
        Assert.assertEquals("mangiaro'l", forms.getVerb().getFuturEnclitic().getSing3m());
        Assert.assertEquals("mangiaro'la", forms.getVerb().getFuturEnclitic().getSing3f());
        Assert.assertEquals("mangiaronsa", forms.getVerb().getFuturEnclitic().getPlural1());
        Assert.assertEquals("mangiaros", forms.getVerb().getFuturEnclitic().getPlural2());
        Assert.assertEquals("mangiarone", forms.getVerb().getFuturEnclitic().getPlural3());

        Assert.assertEquals("mangiaregi", forms.getVerb().getFuturDubitativEnclitic().getSing1());
        Assert.assertEquals("mangiaregiast", forms.getVerb().getFuturDubitativEnclitic().getSing2());
        Assert.assertEquals("mangiaregia'l", forms.getVerb().getFuturDubitativEnclitic().getSing3m());
        Assert.assertEquals("mangiareg'la", forms.getVerb().getFuturDubitativEnclitic().getSing3f());
        Assert.assertEquals("mangiaregians", forms.getVerb().getFuturDubitativEnclitic().getPlural1());
        Assert.assertEquals("mangiaregias", forms.getVerb().getFuturDubitativEnclitic().getPlural2());
        Assert.assertEquals("mangiaregiane", forms.getVerb().getFuturDubitativEnclitic().getPlural3());
    }

    @Test
    public void testRuleCker() {
        forms =  generator.generateConjugation("1", "blocker");

        Assert.assertEquals("blocker", forms.getVerb().getInfinitiv());

        Assert.assertEquals("eau bloc", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tü bloccast", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella blocca", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus bloccains", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus bloccais", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas bloccan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("blocki", forms.getVerb().getPreschentEnclitic().getSing1());
        Assert.assertEquals("bloccast", forms.getVerb().getPreschentEnclitic().getSing2());
        Assert.assertEquals("blocca'l", forms.getVerb().getPreschentEnclitic().getSing3m());
        Assert.assertEquals("blocc'la", forms.getVerb().getPreschentEnclitic().getSing3f());
        Assert.assertEquals("bloccainsa", forms.getVerb().getPreschentEnclitic().getPlural1());
        Assert.assertEquals("bloccais", forms.getVerb().getPreschentEnclitic().getPlural2());
        Assert.assertEquals("bloccane", forms.getVerb().getPreschentEnclitic().getPlural3());
    }

    @Test
    public void testRuleGlier() {
        forms =  generator.generateConjugation("1", "piglier");

        Assert.assertEquals("piglier", forms.getVerb().getInfinitiv());

        Assert.assertEquals("eau pigl", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tü pigliast", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella piglia", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus pigliains", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus pigliais", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas piglian", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("pigli", forms.getVerb().getPreschentEnclitic().getSing1());
        Assert.assertEquals("pigliast", forms.getVerb().getPreschentEnclitic().getSing2());
        Assert.assertEquals("piglia'l", forms.getVerb().getPreschentEnclitic().getSing3m());
        Assert.assertEquals("pigl'la", forms.getVerb().getPreschentEnclitic().getSing3f());
        Assert.assertEquals("pigliainsa", forms.getVerb().getPreschentEnclitic().getPlural1());
        Assert.assertEquals("pigliais", forms.getVerb().getPreschentEnclitic().getPlural2());
        Assert.assertEquals("pigliane", forms.getVerb().getPreschentEnclitic().getPlural3());
    }

    @Test
    public void testRuleFfer() {
        forms =  generator.generateConjugation("1", "püffer");

        Assert.assertEquals("püffer", forms.getVerb().getInfinitiv());

        Assert.assertEquals("eau püf", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tü püffast", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella püffa", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus püffains", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus püffais", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas püffan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("püffi", forms.getVerb().getPreschentEnclitic().getSing1());
        Assert.assertEquals("püffast", forms.getVerb().getPreschentEnclitic().getSing2());
        Assert.assertEquals("püffa'l", forms.getVerb().getPreschentEnclitic().getSing3m());
        Assert.assertEquals("püff'la", forms.getVerb().getPreschentEnclitic().getSing3f());
        Assert.assertEquals("püffainsa", forms.getVerb().getPreschentEnclitic().getPlural1());
        Assert.assertEquals("püffais", forms.getVerb().getPreschentEnclitic().getPlural2());
        Assert.assertEquals("püffane", forms.getVerb().getPreschentEnclitic().getPlural3());
    }

    @Test
    public void testRuleZzer() {
        forms =  generator.generateConjugation("1", "drizzer");

        Assert.assertEquals("drizzer", forms.getVerb().getInfinitiv());

        Assert.assertEquals("eau driz", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tü drizzast", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella drizza", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus drizzains", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus drizzais", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas drizzan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("drizzi", forms.getVerb().getPreschentEnclitic().getSing1());
        Assert.assertEquals("drizzast", forms.getVerb().getPreschentEnclitic().getSing2());
        Assert.assertEquals("drizza'l", forms.getVerb().getPreschentEnclitic().getSing3m());
        Assert.assertEquals("drizz'la", forms.getVerb().getPreschentEnclitic().getSing3f());
        Assert.assertEquals("drizzainsa", forms.getVerb().getPreschentEnclitic().getPlural1());
        Assert.assertEquals("drizzais", forms.getVerb().getPreschentEnclitic().getPlural2());
        Assert.assertEquals("drizzane", forms.getVerb().getPreschentEnclitic().getPlural3());
    }

    @Test
    public void testRule1reflexiv() {
        forms = generator.generateConjugation("1", "as s-chaluner");

        Assert.assertEquals("am s-chaluni", forms.getVerb().getPreschentEnclitic().getSing1());
        Assert.assertEquals("at s-chalunast", forms.getVerb().getPreschentEnclitic().getSing2());
        Assert.assertEquals("as s-chaluna'l", forms.getVerb().getPreschentEnclitic().getSing3m());
        Assert.assertEquals("as s-chalun'la", forms.getVerb().getPreschentEnclitic().getSing3f());
        Assert.assertEquals("ans s-chalunainsa", forms.getVerb().getPreschentEnclitic().getPlural1());
        Assert.assertEquals("as s-chalunais", forms.getVerb().getPreschentEnclitic().getPlural2());
        Assert.assertEquals("as s-chalunane", forms.getVerb().getPreschentEnclitic().getPlural3());

        Assert.assertEquals("am s-chalunaivi", forms.getVerb().getImperfectEnclitic().getSing1());
        Assert.assertEquals("at s-chalunaivast", forms.getVerb().getImperfectEnclitic().getSing2());
        Assert.assertEquals("as s-chalunaiva'l", forms.getVerb().getImperfectEnclitic().getSing3m());
        Assert.assertEquals("as s-chalunaiv'la", forms.getVerb().getImperfectEnclitic().getSing3f());
        Assert.assertEquals("ans s-chalunaivans", forms.getVerb().getImperfectEnclitic().getPlural1());
        Assert.assertEquals("as s-chalunaivas", forms.getVerb().getImperfectEnclitic().getPlural2());
        Assert.assertEquals("as s-chalunaivane", forms.getVerb().getImperfectEnclitic().getPlural3());

        Assert.assertEquals("am s-chalunessi", forms.getVerb().getCundizionalEnclitic().getSing1());
        Assert.assertEquals("at s-chalunessast", forms.getVerb().getCundizionalEnclitic().getSing2());
        Assert.assertEquals("as s-chalunessa'l", forms.getVerb().getCundizionalEnclitic().getSing3m());
        Assert.assertEquals("as s-chaluness'la", forms.getVerb().getCundizionalEnclitic().getSing3f());
        Assert.assertEquals("ans s-chalunessans", forms.getVerb().getCundizionalEnclitic().getPlural1());
        Assert.assertEquals("as s-chalunessas", forms.getVerb().getCundizionalEnclitic().getPlural2());
        Assert.assertEquals("as s-chalunessane", forms.getVerb().getCundizionalEnclitic().getPlural3());

        Assert.assertEquals("am s-chalunaro eau", forms.getVerb().getFuturEnclitic().getSing1());
        Assert.assertEquals("at s-chalunarost", forms.getVerb().getFuturEnclitic().getSing2());
        Assert.assertEquals("as s-chalunaro'l", forms.getVerb().getFuturEnclitic().getSing3m());
        Assert.assertEquals("as s-chalunaro'la", forms.getVerb().getFuturEnclitic().getSing3f());
        Assert.assertEquals("ans s-chalunaronsa", forms.getVerb().getFuturEnclitic().getPlural1());
        Assert.assertEquals("as s-chalunaros", forms.getVerb().getFuturEnclitic().getPlural2());
        Assert.assertEquals("as s-chalunarone", forms.getVerb().getFuturEnclitic().getPlural3());

        Assert.assertEquals("am s-chalunaregi", forms.getVerb().getFuturDubitativEnclitic().getSing1());
        Assert.assertEquals("at s-chalunaregiast", forms.getVerb().getFuturDubitativEnclitic().getSing2());
        Assert.assertEquals("as s-chalunaregia'l", forms.getVerb().getFuturDubitativEnclitic().getSing3m());
        Assert.assertEquals("as s-chalunareg'la", forms.getVerb().getFuturDubitativEnclitic().getSing3f());
        Assert.assertEquals("ans s-chalunaregians", forms.getVerb().getFuturDubitativEnclitic().getPlural1());
        Assert.assertEquals("as s-chalunaregias", forms.getVerb().getFuturDubitativEnclitic().getPlural2());
        Assert.assertEquals("as s-chalunaregiane", forms.getVerb().getFuturDubitativEnclitic().getPlural3());
    }

    @Test
    public void testRule1reflexivVowel() {
        forms = generator.generateConjugation("1", "s'abasser");

        Assert.assertEquals("m'abassi", forms.getVerb().getPreschentEnclitic().getSing1());
        Assert.assertEquals("t'abassast", forms.getVerb().getPreschentEnclitic().getSing2());
        Assert.assertEquals("s'abassa'l", forms.getVerb().getPreschentEnclitic().getSing3m());
        Assert.assertEquals("s'abass'la", forms.getVerb().getPreschentEnclitic().getSing3f());
        Assert.assertEquals("ans abassainsa", forms.getVerb().getPreschentEnclitic().getPlural1());
        Assert.assertEquals("s'abassais", forms.getVerb().getPreschentEnclitic().getPlural2());
        Assert.assertEquals("s'abassane", forms.getVerb().getPreschentEnclitic().getPlural3());

        Assert.assertEquals("m'abassaivi", forms.getVerb().getImperfectEnclitic().getSing1());
        Assert.assertEquals("t'abassaivast", forms.getVerb().getImperfectEnclitic().getSing2());
        Assert.assertEquals("s'abassaiva'l", forms.getVerb().getImperfectEnclitic().getSing3m());
        Assert.assertEquals("s'abassaiv'la", forms.getVerb().getImperfectEnclitic().getSing3f());
        Assert.assertEquals("ans abassaivans", forms.getVerb().getImperfectEnclitic().getPlural1());
        Assert.assertEquals("s'abassaivas", forms.getVerb().getImperfectEnclitic().getPlural2());
        Assert.assertEquals("s'abassaivane", forms.getVerb().getImperfectEnclitic().getPlural3());

        Assert.assertEquals("m'abassessi", forms.getVerb().getCundizionalEnclitic().getSing1());
        Assert.assertEquals("t'abassessast", forms.getVerb().getCundizionalEnclitic().getSing2());
        Assert.assertEquals("s'abassessa'l", forms.getVerb().getCundizionalEnclitic().getSing3m());
        Assert.assertEquals("s'abassess'la", forms.getVerb().getCundizionalEnclitic().getSing3f());
        Assert.assertEquals("ans abassessans", forms.getVerb().getCundizionalEnclitic().getPlural1());
        Assert.assertEquals("s'abassessas", forms.getVerb().getCundizionalEnclitic().getPlural2());
        Assert.assertEquals("s'abassessane", forms.getVerb().getCundizionalEnclitic().getPlural3());

        Assert.assertEquals("m'abassaro eau", forms.getVerb().getFuturEnclitic().getSing1());
        Assert.assertEquals("t'abassarost", forms.getVerb().getFuturEnclitic().getSing2());
        Assert.assertEquals("s'abassaro'l", forms.getVerb().getFuturEnclitic().getSing3m());
        Assert.assertEquals("s'abassaro'la", forms.getVerb().getFuturEnclitic().getSing3f());
        Assert.assertEquals("ans abassaronsa", forms.getVerb().getFuturEnclitic().getPlural1());
        Assert.assertEquals("s'abassaros", forms.getVerb().getFuturEnclitic().getPlural2());
        Assert.assertEquals("s'abassarone", forms.getVerb().getFuturEnclitic().getPlural3());

        Assert.assertEquals("m'abassaregi", forms.getVerb().getFuturDubitativEnclitic().getSing1());
        Assert.assertEquals("t'abassaregiast", forms.getVerb().getFuturDubitativEnclitic().getSing2());
        Assert.assertEquals("s'abassaregia'l", forms.getVerb().getFuturDubitativEnclitic().getSing3m());
        Assert.assertEquals("s'abassareg'la", forms.getVerb().getFuturDubitativEnclitic().getSing3f());
        Assert.assertEquals("ans abassaregians", forms.getVerb().getFuturDubitativEnclitic().getPlural1());
        Assert.assertEquals("s'abassaregias", forms.getVerb().getFuturDubitativEnclitic().getPlural2());
        Assert.assertEquals("s'abassaregiane", forms.getVerb().getFuturDubitativEnclitic().getPlural3());
    }
}
