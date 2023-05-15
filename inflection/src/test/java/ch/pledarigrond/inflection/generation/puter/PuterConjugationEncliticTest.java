package ch.pledarigrond.inflection.generation.puter;

import ch.pledarigrond.inflection.model.InflectionResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class PuterConjugationEncliticTest {

    private static PuterConjugation generator;
    private InflectionResponse forms;

    @BeforeClass
    public static void initialize() {
        generator = new PuterConjugation();
    }

    @Test
    public void testRuleAr() {
        forms =  generator.generateConjugation("1", "manger");

        Assert.assertEquals("manger", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("mangi", forms.getInflectionValues().get("preschentsing1enclitic"));
        Assert.assertEquals("mangiast", forms.getInflectionValues().get("preschentsing2enclitic"));
        Assert.assertEquals("mangia'l", forms.getInflectionValues().get("preschentsing3encliticm"));
        Assert.assertEquals("mang'la", forms.getInflectionValues().get("preschentsing3encliticf"));
        Assert.assertEquals("mangiainsa", forms.getInflectionValues().get("preschentplural1enclitic"));
        Assert.assertEquals("mangiais", forms.getInflectionValues().get("preschentplural2enclitic"));
        Assert.assertEquals("mangiane", forms.getInflectionValues().get("preschentplural3enclitic"));

        Assert.assertEquals("mangiaivi", forms.getInflectionValues().get("imperfectsing1enclitic"));
        Assert.assertEquals("mangiaivast", forms.getInflectionValues().get("imperfectsing2enclitic"));
        Assert.assertEquals("mangiaiva'l", forms.getInflectionValues().get("imperfectsing3encliticm"));
        Assert.assertEquals("mangiaiv'la", forms.getInflectionValues().get("imperfectsing3encliticf"));
        Assert.assertEquals("mangiaivans", forms.getInflectionValues().get("imperfectplural1enclitic"));
        Assert.assertEquals("mangiaivas", forms.getInflectionValues().get("imperfectplural2enclitic"));
        Assert.assertEquals("mangiaivane", forms.getInflectionValues().get("imperfectplural3enclitic"));

        Assert.assertEquals("mangessi", forms.getInflectionValues().get("cundizionalsing1enclitic"));
        Assert.assertEquals("mangessast", forms.getInflectionValues().get("cundizionalsing2enclitic"));
        Assert.assertEquals("mangessa'l", forms.getInflectionValues().get("cundizionalsing3encliticm"));
        Assert.assertEquals("mangess'la", forms.getInflectionValues().get("cundizionalsing3encliticf"));
        Assert.assertEquals("mangessans", forms.getInflectionValues().get("cundizionalplural1enclitic"));
        Assert.assertEquals("mangessas", forms.getInflectionValues().get("cundizionalplural2enclitic"));
        Assert.assertEquals("mangessane", forms.getInflectionValues().get("cundizionalplural3enclitic"));

        Assert.assertEquals("mangiaro", forms.getInflectionValues().get("futursing1enclitic"));
        Assert.assertEquals("mangiarost", forms.getInflectionValues().get("futursing2enclitic"));
        Assert.assertEquals("mangiaro'l", forms.getInflectionValues().get("futursing3encliticm"));
        Assert.assertEquals("mangiaro'la", forms.getInflectionValues().get("futursing3encliticf"));
        Assert.assertEquals("mangiaronsa", forms.getInflectionValues().get("futurplural1enclitic"));
        Assert.assertEquals("mangiaros", forms.getInflectionValues().get("futurplural2enclitic"));
        Assert.assertEquals("mangiarone", forms.getInflectionValues().get("futurplural3enclitic"));

        Assert.assertEquals("mangiaregi", forms.getInflectionValues().get("futurdubitativsing1enclitic"));
        Assert.assertEquals("mangiaregiast", forms.getInflectionValues().get("futurdubitativsing2enclitic"));
        Assert.assertEquals("mangiaregia'l", forms.getInflectionValues().get("futurdubitativsing3encliticm"));
        Assert.assertEquals("mangiareg'la", forms.getInflectionValues().get("futurdubitativsing3encliticf"));
        Assert.assertEquals("mangiaregians", forms.getInflectionValues().get("futurdubitativplural1enclitic"));
        Assert.assertEquals("mangiaregias", forms.getInflectionValues().get("futurdubitativplural2enclitic"));
        Assert.assertEquals("mangiaregiane", forms.getInflectionValues().get("futurdubitativplural3enclitic"));
    }

    @Test
    public void testRuleCker() {
        forms =  generator.generateConjugation("1", "blocker");

        Assert.assertEquals("blocker", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("eau bloc", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tü bloccast", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella blocca", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus bloccains", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus bloccais", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas bloccan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("blocki", forms.getInflectionValues().get("preschentsing1enclitic"));
        Assert.assertEquals("bloccast", forms.getInflectionValues().get("preschentsing2enclitic"));
        Assert.assertEquals("blocca'l", forms.getInflectionValues().get("preschentsing3encliticm"));
        Assert.assertEquals("bloc'la", forms.getInflectionValues().get("preschentsing3encliticf"));
        Assert.assertEquals("bloccainsa", forms.getInflectionValues().get("preschentplural1enclitic"));
        Assert.assertEquals("bloccais", forms.getInflectionValues().get("preschentplural2enclitic"));
        Assert.assertEquals("bloccane", forms.getInflectionValues().get("preschentplural3enclitic"));
    }

    @Test
    public void testRuleGlier() {
        forms =  generator.generateConjugation("1", "piglier");

        Assert.assertEquals("piglier", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("eau pigl", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tü pigliast", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella piglia", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus pigliains", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus pigliais", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas piglian", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("pigli", forms.getInflectionValues().get("preschentsing1enclitic"));
        Assert.assertEquals("pigliast", forms.getInflectionValues().get("preschentsing2enclitic"));
        Assert.assertEquals("piglia'l", forms.getInflectionValues().get("preschentsing3encliticm"));
        Assert.assertEquals("pigl'la", forms.getInflectionValues().get("preschentsing3encliticf"));
        Assert.assertEquals("pigliainsa", forms.getInflectionValues().get("preschentplural1enclitic"));
        Assert.assertEquals("pigliais", forms.getInflectionValues().get("preschentplural2enclitic"));
        Assert.assertEquals("pigliane", forms.getInflectionValues().get("preschentplural3enclitic"));
    }

    @Test
    public void testRuleFfer() {
        forms =  generator.generateConjugation("1", "püffer");

        Assert.assertEquals("püffer", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("eau püf", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tü püffast", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella püffa", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus püffains", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus püffais", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas püffan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("püffi", forms.getInflectionValues().get("preschentsing1enclitic"));
        Assert.assertEquals("püffast", forms.getInflectionValues().get("preschentsing2enclitic"));
        Assert.assertEquals("püffa'l", forms.getInflectionValues().get("preschentsing3encliticm"));
        Assert.assertEquals("püf'la", forms.getInflectionValues().get("preschentsing3encliticf"));
        Assert.assertEquals("püffainsa", forms.getInflectionValues().get("preschentplural1enclitic"));
        Assert.assertEquals("püffais", forms.getInflectionValues().get("preschentplural2enclitic"));
        Assert.assertEquals("püffane", forms.getInflectionValues().get("preschentplural3enclitic"));
    }

    @Test
    public void testRuleZzer() {
        forms =  generator.generateConjugation("1", "drizzer");

        Assert.assertEquals("drizzer", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("eau driz", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tü drizzast", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella drizza", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus drizzains", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus drizzais", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas drizzan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("drizzi", forms.getInflectionValues().get("preschentsing1enclitic"));
        Assert.assertEquals("drizzast", forms.getInflectionValues().get("preschentsing2enclitic"));
        Assert.assertEquals("drizza'l", forms.getInflectionValues().get("preschentsing3encliticm"));
        Assert.assertEquals("driz'la", forms.getInflectionValues().get("preschentsing3encliticf"));
        Assert.assertEquals("drizzainsa", forms.getInflectionValues().get("preschentplural1enclitic"));
        Assert.assertEquals("drizzais", forms.getInflectionValues().get("preschentplural2enclitic"));
        Assert.assertEquals("drizzane", forms.getInflectionValues().get("preschentplural3enclitic"));
    }
}
