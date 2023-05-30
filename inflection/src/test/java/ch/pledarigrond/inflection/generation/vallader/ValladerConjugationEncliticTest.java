package ch.pledarigrond.inflection.generation.vallader;

import ch.pledarigrond.inflection.model.InflectionResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ValladerConjugationEncliticTest {

    private static ValladerConjugation generator;
    private InflectionResponse forms;

    @BeforeClass
    public static void initialize() {
        generator = new ValladerConjugation();
    }

    @Test
    public void testRuleAr() {
        forms =  generator.generateConjugation("1", "müdar");

        Assert.assertEquals("müdar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("müda", forms.getInflectionValues().get("preschentsing1enclitic"));
        Assert.assertEquals("müdast", forms.getInflectionValues().get("preschentsing2enclitic"));
        Assert.assertEquals("müda'l", forms.getInflectionValues().get("preschentsing3encliticm"));
        Assert.assertEquals("müd'la", forms.getInflectionValues().get("preschentsing3encliticf"));
        Assert.assertEquals("müdaina", forms.getInflectionValues().get("preschentplural1enclitic"));
        Assert.assertEquals("müdais\n(müdaivat)", forms.getInflectionValues().get("preschentplural2enclitic"));
        Assert.assertEquals("müdna", forms.getInflectionValues().get("preschentplural3enclitic"));

        Assert.assertEquals("müdaiva", forms.getInflectionValues().get("imperfectsing1enclitic"));
        Assert.assertEquals("müdaivast", forms.getInflectionValues().get("imperfectsing2enclitic"));
        Assert.assertEquals("müdaiva'l", forms.getInflectionValues().get("imperfectsing3encliticm"));
        Assert.assertEquals("müdaiv'la", forms.getInflectionValues().get("imperfectsing3encliticf"));
        Assert.assertEquals("müdaivna", forms.getInflectionValues().get("imperfectplural1enclitic"));
        Assert.assertEquals("müdaivat", forms.getInflectionValues().get("imperfectplural2enclitic"));
        Assert.assertEquals("müdaivna", forms.getInflectionValues().get("imperfectplural3enclitic"));

        Assert.assertEquals("müdessa", forms.getInflectionValues().get("cundizionalsing1enclitic"));
        Assert.assertEquals("müdessast", forms.getInflectionValues().get("cundizionalsing2enclitic"));
        Assert.assertEquals("müdessa'l", forms.getInflectionValues().get("cundizionalsing3encliticm"));
        Assert.assertEquals("müdess'la", forms.getInflectionValues().get("cundizionalsing3encliticf"));
        Assert.assertEquals("müdessna", forms.getInflectionValues().get("cundizionalplural1enclitic"));
        Assert.assertEquals("müdessat", forms.getInflectionValues().get("cundizionalplural2enclitic"));
        Assert.assertEquals("müdessna", forms.getInflectionValues().get("cundizionalplural3enclitic"));

        Assert.assertEquals("müdaraja", forms.getInflectionValues().get("futursing1enclitic"));
        Assert.assertEquals("müdarast", forms.getInflectionValues().get("futursing2enclitic"));
        Assert.assertEquals("müdarà'l", forms.getInflectionValues().get("futursing3encliticm"));
        Assert.assertEquals("müdarà'la", forms.getInflectionValues().get("futursing3encliticf"));
        Assert.assertEquals("müdarana", forms.getInflectionValues().get("futurplural1enclitic"));
        Assert.assertEquals("müdarat", forms.getInflectionValues().get("futurplural2enclitic"));
        Assert.assertEquals("müdarana", forms.getInflectionValues().get("futurplural3enclitic"));
    }

    @Test
    public void testRuleIr() {
        forms =  generator.generateConjugation("4", "partir");

        Assert.assertEquals("partir", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("parta", forms.getInflectionValues().get("preschentsing1enclitic"));
        Assert.assertEquals("partast", forms.getInflectionValues().get("preschentsing2enclitic"));
        Assert.assertEquals("parta'l", forms.getInflectionValues().get("preschentsing3encliticm"));
        Assert.assertEquals("part'la", forms.getInflectionValues().get("preschentsing3encliticf"));
        Assert.assertEquals("partina", forms.getInflectionValues().get("preschentplural1enclitic"));
        Assert.assertEquals("partis\n(partivat)", forms.getInflectionValues().get("preschentplural2enclitic"));
        Assert.assertEquals("partna", forms.getInflectionValues().get("preschentplural3enclitic"));

        Assert.assertEquals("partiva", forms.getInflectionValues().get("imperfectsing1enclitic"));
        Assert.assertEquals("partivast", forms.getInflectionValues().get("imperfectsing2enclitic"));
        Assert.assertEquals("partiva'l", forms.getInflectionValues().get("imperfectsing3encliticm"));
        Assert.assertEquals("partiv'la", forms.getInflectionValues().get("imperfectsing3encliticf"));
        Assert.assertEquals("partivna", forms.getInflectionValues().get("imperfectplural1enclitic"));
        Assert.assertEquals("partivat", forms.getInflectionValues().get("imperfectplural2enclitic"));
        Assert.assertEquals("partivna", forms.getInflectionValues().get("imperfectplural3enclitic"));

        Assert.assertEquals("partissa", forms.getInflectionValues().get("cundizionalsing1enclitic"));
        Assert.assertEquals("partissast", forms.getInflectionValues().get("cundizionalsing2enclitic"));
        Assert.assertEquals("partissa'l", forms.getInflectionValues().get("cundizionalsing3encliticm"));
        Assert.assertEquals("partiss'la", forms.getInflectionValues().get("cundizionalsing3encliticf"));
        Assert.assertEquals("partissna", forms.getInflectionValues().get("cundizionalplural1enclitic"));
        Assert.assertEquals("partissat", forms.getInflectionValues().get("cundizionalplural2enclitic"));
        Assert.assertEquals("partissna", forms.getInflectionValues().get("cundizionalplural3enclitic"));

        Assert.assertEquals("partiraja", forms.getInflectionValues().get("futursing1enclitic"));
        Assert.assertEquals("partirast", forms.getInflectionValues().get("futursing2enclitic"));
        Assert.assertEquals("partirà'l", forms.getInflectionValues().get("futursing3encliticm"));
        Assert.assertEquals("partirà'la", forms.getInflectionValues().get("futursing3encliticf"));
        Assert.assertEquals("partirana", forms.getInflectionValues().get("futurplural1enclitic"));
        Assert.assertEquals("partirat", forms.getInflectionValues().get("futurplural2enclitic"));
        Assert.assertEquals("partirana", forms.getInflectionValues().get("futurplural3enclitic"));
    }

    @Test
    public void testRuleGiar() {
        forms =  generator.generateConjugation("1", "mangiar");

        Assert.assertEquals("mangiar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("eu mang", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tü mangiast", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella mangia", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus mangiain", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus mangiais\n(vo mangiaivat)", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas mangian", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("mangia", forms.getInflectionValues().get("preschentsing1enclitic"));
        Assert.assertEquals("mangiast", forms.getInflectionValues().get("preschentsing2enclitic"));
        Assert.assertEquals("mangia'l", forms.getInflectionValues().get("preschentsing3encliticm"));
        Assert.assertEquals("mang'la", forms.getInflectionValues().get("preschentsing3encliticf"));
        Assert.assertEquals("mangiaina", forms.getInflectionValues().get("preschentplural1enclitic"));
        Assert.assertEquals("mangiais\n(mangiaivat)", forms.getInflectionValues().get("preschentplural2enclitic"));
        Assert.assertEquals("mangna", forms.getInflectionValues().get("preschentplural3enclitic"));
    }

    @Test
    public void testRuleGliar() {
        forms =  generator.generateConjugation("1", "tagliar");

        Assert.assertEquals("tagliar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("eu tagl", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tü tagliast", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella taglia", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus tagliain", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus tagliais\n(vo tagliaivat)", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas taglian", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("taglia", forms.getInflectionValues().get("preschentsing1enclitic"));
        Assert.assertEquals("tagliast", forms.getInflectionValues().get("preschentsing2enclitic"));
        Assert.assertEquals("taglia'l", forms.getInflectionValues().get("preschentsing3encliticm"));
        Assert.assertEquals("tagl'la", forms.getInflectionValues().get("preschentsing3encliticf"));
        Assert.assertEquals("tagliaina", forms.getInflectionValues().get("preschentplural1enclitic"));
        Assert.assertEquals("tagliais\n(tagliaivat)", forms.getInflectionValues().get("preschentplural2enclitic"));
        Assert.assertEquals("taglna", forms.getInflectionValues().get("preschentplural3enclitic"));
    }

    @Test
    public void testRuleGer() {
        forms =  generator.generateConjugation("3", "leger");

        Assert.assertEquals("leger", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("eu leg", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tü legiast", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella legia", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus legiain", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus legiais\n(vo legiaivat)", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas legian", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("legia", forms.getInflectionValues().get("preschentsing1enclitic"));
        Assert.assertEquals("legiast", forms.getInflectionValues().get("preschentsing2enclitic"));
        Assert.assertEquals("legia'l", forms.getInflectionValues().get("preschentsing3encliticm"));
        Assert.assertEquals("leg'la", forms.getInflectionValues().get("preschentsing3encliticf"));
        Assert.assertEquals("legiaina", forms.getInflectionValues().get("preschentplural1enclitic"));
        Assert.assertEquals("legiais\n(legiaivat)", forms.getInflectionValues().get("preschentplural2enclitic"));
        Assert.assertEquals("legna", forms.getInflectionValues().get("preschentplural3enclitic"));
    }

    @Test
    public void testRuleGir() {
        forms =  generator.generateConjugation("4", "sclingir");

        Assert.assertEquals("sclingir", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("eu scling", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tü sclingiast", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella sclingia", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus sclingin", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus sclingis\n(vo sclingivat)", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas sclingian", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("sclingia", forms.getInflectionValues().get("preschentsing1enclitic"));
        Assert.assertEquals("sclingiast", forms.getInflectionValues().get("preschentsing2enclitic"));
        Assert.assertEquals("sclingia'l", forms.getInflectionValues().get("preschentsing3encliticm"));
        Assert.assertEquals("scling'la", forms.getInflectionValues().get("preschentsing3encliticf"));
        Assert.assertEquals("sclingina", forms.getInflectionValues().get("preschentplural1enclitic"));
        Assert.assertEquals("sclingis\n(sclingivat)", forms.getInflectionValues().get("preschentplural2enclitic"));
        Assert.assertEquals("sclingna", forms.getInflectionValues().get("preschentplural3enclitic"));
    }

    @Test
    public void testRuleGlir() {
        forms =  generator.generateConjugation("4", "assaglir");

        Assert.assertEquals("assaglir", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("eu assagl", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tü assagliast", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella assaglia", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus assaglin", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus assaglis\n(vo assaglivat)", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas assaglian", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("assaglia", forms.getInflectionValues().get("preschentsing1enclitic"));
        Assert.assertEquals("assagliast", forms.getInflectionValues().get("preschentsing2enclitic"));
        Assert.assertEquals("assaglia'l", forms.getInflectionValues().get("preschentsing3encliticm"));
        Assert.assertEquals("assagl'la", forms.getInflectionValues().get("preschentsing3encliticf"));
        Assert.assertEquals("assaglina", forms.getInflectionValues().get("preschentplural1enclitic"));
        Assert.assertEquals("assaglis\n(assaglivat)", forms.getInflectionValues().get("preschentplural2enclitic"));
        Assert.assertEquals("assaglna", forms.getInflectionValues().get("preschentplural3enclitic"));
    }

    @Test
    public void testRuleZZ() {
        forms =  generator.generateConjugation("1", "drizzar");

        Assert.assertEquals("drizzar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("eu driz", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tü drizzast", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella drizza", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus drizzain", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus drizzais\n(vo drizzaivat)", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas drizzan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("drizza", forms.getInflectionValues().get("preschentsing1enclitic"));
        Assert.assertEquals("drizzast", forms.getInflectionValues().get("preschentsing2enclitic"));
        Assert.assertEquals("drizza'l", forms.getInflectionValues().get("preschentsing3encliticm"));
        Assert.assertEquals("drizz'la", forms.getInflectionValues().get("preschentsing3encliticf"));
        Assert.assertEquals("drizzaina", forms.getInflectionValues().get("preschentplural1enclitic"));
        Assert.assertEquals("drizzais\n(drizzaivat)", forms.getInflectionValues().get("preschentplural2enclitic"));
        Assert.assertEquals("drizna", forms.getInflectionValues().get("preschentplural3enclitic"));
    }

    @Test
    public void testRule1reflexiv() {
        forms = generator.generateConjugation("1a", "as barricadar");

        Assert.assertEquals("am barricadescha", forms.getInflectionValues().get("preschentsing1enclitic"));
        Assert.assertEquals("at barricadeschast", forms.getInflectionValues().get("preschentsing2enclitic"));
        Assert.assertEquals("as barricadescha'l", forms.getInflectionValues().get("preschentsing3encliticm"));
        Assert.assertEquals("as barricadesch'la", forms.getInflectionValues().get("preschentsing3encliticf"));
        Assert.assertEquals("ans barricadaina", forms.getInflectionValues().get("preschentplural1enclitic"));
        Assert.assertEquals("as barricadais\n(as barricadaivat)", forms.getInflectionValues().get("preschentplural2enclitic"));
        Assert.assertEquals("as barricadeschna", forms.getInflectionValues().get("preschentplural3enclitic"));

        Assert.assertEquals("am barricadaiva", forms.getInflectionValues().get("imperfectsing1enclitic"));
        Assert.assertEquals("at barricadaivast", forms.getInflectionValues().get("imperfectsing2enclitic"));
        Assert.assertEquals("as barricadaiva'l", forms.getInflectionValues().get("imperfectsing3encliticm"));
        Assert.assertEquals("as barricadaiv'la", forms.getInflectionValues().get("imperfectsing3encliticf"));
        Assert.assertEquals("ans barricadaivna", forms.getInflectionValues().get("imperfectplural1enclitic"));
        Assert.assertEquals("as barricadaivat", forms.getInflectionValues().get("imperfectplural2enclitic"));
        Assert.assertEquals("as barricadaivna", forms.getInflectionValues().get("imperfectplural3enclitic"));

        Assert.assertEquals("am barricadessa", forms.getInflectionValues().get("cundizionalsing1enclitic"));
        Assert.assertEquals("at barricadessast", forms.getInflectionValues().get("cundizionalsing2enclitic"));
        Assert.assertEquals("as barricadessa'l", forms.getInflectionValues().get("cundizionalsing3encliticm"));
        Assert.assertEquals("as barricadess'la", forms.getInflectionValues().get("cundizionalsing3encliticf"));
        Assert.assertEquals("ans barricadessna", forms.getInflectionValues().get("cundizionalplural1enclitic"));
        Assert.assertEquals("as barricadessat", forms.getInflectionValues().get("cundizionalplural2enclitic"));
        Assert.assertEquals("as barricadessna", forms.getInflectionValues().get("cundizionalplural3enclitic"));

        Assert.assertEquals("am barricadaraja", forms.getInflectionValues().get("futursing1enclitic"));
        Assert.assertEquals("at barricadarast", forms.getInflectionValues().get("futursing2enclitic"));
        Assert.assertEquals("as barricadarà'l", forms.getInflectionValues().get("futursing3encliticm"));
        Assert.assertEquals("as barricadarà'la", forms.getInflectionValues().get("futursing3encliticf"));
        Assert.assertEquals("ans barricadarana", forms.getInflectionValues().get("futurplural1enclitic"));
        Assert.assertEquals("as barricadarat", forms.getInflectionValues().get("futurplural2enclitic"));
        Assert.assertEquals("as barricadarana", forms.getInflectionValues().get("futurplural3enclitic"));
    }

    @Test
    public void testRule1reflexivVowel() {
        forms = generator.generateConjugation("1", "s'inachürar");

        Assert.assertEquals("m'inachüra", forms.getInflectionValues().get("preschentsing1enclitic"));
        Assert.assertEquals("t'inachürast", forms.getInflectionValues().get("preschentsing2enclitic"));
        Assert.assertEquals("s'inachüra'l", forms.getInflectionValues().get("preschentsing3encliticm"));
        Assert.assertEquals("s'inachür'la", forms.getInflectionValues().get("preschentsing3encliticf"));
        Assert.assertEquals("ans inachüraina", forms.getInflectionValues().get("preschentplural1enclitic"));
        Assert.assertEquals("s'inachürais\n(s'inachüraivat)", forms.getInflectionValues().get("preschentplural2enclitic"));
        Assert.assertEquals("s'inachürna", forms.getInflectionValues().get("preschentplural3enclitic"));

        Assert.assertEquals("m'inachüraiva", forms.getInflectionValues().get("imperfectsing1enclitic"));
        Assert.assertEquals("t'inachüraivast", forms.getInflectionValues().get("imperfectsing2enclitic"));
        Assert.assertEquals("s'inachüraiva'l", forms.getInflectionValues().get("imperfectsing3encliticm"));
        Assert.assertEquals("s'inachüraiv'la", forms.getInflectionValues().get("imperfectsing3encliticf"));
        Assert.assertEquals("ans inachüraivna", forms.getInflectionValues().get("imperfectplural1enclitic"));
        Assert.assertEquals("s'inachüraivat", forms.getInflectionValues().get("imperfectplural2enclitic"));
        Assert.assertEquals("s'inachüraivna", forms.getInflectionValues().get("imperfectplural3enclitic"));

        Assert.assertEquals("m'inachüressa", forms.getInflectionValues().get("cundizionalsing1enclitic"));
        Assert.assertEquals("t'inachüressast", forms.getInflectionValues().get("cundizionalsing2enclitic"));
        Assert.assertEquals("s'inachüressa'l", forms.getInflectionValues().get("cundizionalsing3encliticm"));
        Assert.assertEquals("s'inachüress'la", forms.getInflectionValues().get("cundizionalsing3encliticf"));
        Assert.assertEquals("ans inachüressna", forms.getInflectionValues().get("cundizionalplural1enclitic"));
        Assert.assertEquals("s'inachüressat", forms.getInflectionValues().get("cundizionalplural2enclitic"));
        Assert.assertEquals("s'inachüressna", forms.getInflectionValues().get("cundizionalplural3enclitic"));

        Assert.assertEquals("m'inachüraraja", forms.getInflectionValues().get("futursing1enclitic"));
        Assert.assertEquals("t'inachürarast", forms.getInflectionValues().get("futursing2enclitic"));
        Assert.assertEquals("s'inachürarà'l", forms.getInflectionValues().get("futursing3encliticm"));
        Assert.assertEquals("s'inachürarà'la", forms.getInflectionValues().get("futursing3encliticf"));
        Assert.assertEquals("ans inachürarana", forms.getInflectionValues().get("futurplural1enclitic"));
        Assert.assertEquals("s'inachürarat", forms.getInflectionValues().get("futurplural2enclitic"));
        Assert.assertEquals("s'inachürarana", forms.getInflectionValues().get("futurplural3enclitic"));
    }
}
