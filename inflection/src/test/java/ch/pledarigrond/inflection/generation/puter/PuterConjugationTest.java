package ch.pledarigrond.inflection.generation.puter;

import ch.pledarigrond.inflection.model.InflectionResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class PuterConjugationTest {

    private static PuterConjugation generator;
    private InflectionResponse forms;

    @BeforeClass
    public static void initialize() {
        generator = new PuterConjugation();
    }

    @Test
    public void testRule1() {
        forms =  generator.generateConjugation("1", "güder");

        Assert.assertEquals("güder", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("eau güd", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tü güdast", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella güda", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus güdains", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus güdais", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas güdan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("eau güdaiva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tü güdaivast", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella güdaiva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus güdaivans", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus güdaivas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas güdaivan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ch'eau güda", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("cha tü güdast", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella güda", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("cha nus güdans", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("cha vus güdas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas güdan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ch'eau güdess", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("cha tü güdessast", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella güdess", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("cha nus güdessans", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("cha vus güdessas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas güdessan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("eau güdess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tü güdessast", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella güdess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus güdessans", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus güdessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas güdessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("eau güdaro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tü güdarost", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella güdaro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus güdarons", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus güdaros", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas güdaron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("eau güdaregia", forms.getInflectionValues().get("futurdubitativsing1"));
        Assert.assertEquals("tü güdaregiast", forms.getInflectionValues().get("futurdubitativsing2"));
        Assert.assertEquals("el/ella güdaregia", forms.getInflectionValues().get("futurdubitativsing3"));
        Assert.assertEquals("nus güdaregians", forms.getInflectionValues().get("futurdubitativplural1"));
        Assert.assertEquals("vus güdaregias", forms.getInflectionValues().get("futurdubitativplural2"));
        Assert.assertEquals("els/ellas güdaregian", forms.getInflectionValues().get("futurdubitativplural3"));

        Assert.assertEquals("güdo", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("güdeda", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("güdos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("güdedas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("güda!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("güdè!", forms.getInflectionValues().get("imperativ2"));
        Assert.assertEquals("nu güder!", forms.getInflectionValues().get("imperativ3"));
        Assert.assertEquals("nu güdè!", forms.getInflectionValues().get("imperativ4"));
        Assert.assertEquals("ch'El/Ella güda!", forms.getInflectionValues().get("imperativ5"));
        Assert.assertEquals("ch'Els/Ellas güdan!", forms.getInflectionValues().get("imperativ6"));

        Assert.assertEquals("güdand", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1a() {
        forms =  generator.generateConjugation("1a", "gratuler");

        Assert.assertEquals("gratuler", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("eau gratulesch", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tü gratuleschast", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella gratulescha", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus gratulains", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus gratulais", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas gratuleschan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("eau gratulaiva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tü gratulaivast", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella gratulaiva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus gratulaivans", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus gratulaivas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas gratulaivan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ch'eau gratulescha", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("cha tü gratuleschast", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella gratulescha", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("cha nus gratuleschans", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("cha vus gratuleschas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas gratuleschan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ch'eau gratuless", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("cha tü gratulessast", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella gratuless", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("cha nus gratulessans", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("cha vus gratulessas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas gratulessan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("eau gratuless", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tü gratulessast", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella gratuless", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus gratulessans", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus gratulessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas gratulessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("eau gratularo", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tü gratularost", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella gratularo", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus gratularons", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus gratularos", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas gratularon", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("eau gratularegia", forms.getInflectionValues().get("futurdubitativsing1"));
        Assert.assertEquals("tü gratularegiast", forms.getInflectionValues().get("futurdubitativsing2"));
        Assert.assertEquals("el/ella gratularegia", forms.getInflectionValues().get("futurdubitativsing3"));
        Assert.assertEquals("nus gratularegians", forms.getInflectionValues().get("futurdubitativplural1"));
        Assert.assertEquals("vus gratularegias", forms.getInflectionValues().get("futurdubitativplural2"));
        Assert.assertEquals("els/ellas gratularegian", forms.getInflectionValues().get("futurdubitativplural3"));

        Assert.assertEquals("gratulo", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("gratuleda", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("gratulos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("gratuledas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("gratulescha!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("gratulè!", forms.getInflectionValues().get("imperativ2"));
        Assert.assertEquals("nu gratuler!", forms.getInflectionValues().get("imperativ3"));
        Assert.assertEquals("nu gratulè!", forms.getInflectionValues().get("imperativ4"));
        Assert.assertEquals("ch'El/Ella gratulescha!", forms.getInflectionValues().get("imperativ5"));
        Assert.assertEquals("ch'Els/Ellas gratuleschan!", forms.getInflectionValues().get("imperativ6"));

        Assert.assertEquals("gratuland", forms.getInflectionValues().get("gerundium"));
    }

    /*@Test
    public void testRule1reflexiv() {
        forms =  generator.generateConjugation("1", "as s-chuder");

        Assert.assertEquals("as s-chuder", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("eau am s-chod", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tü at s-chodast", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella as s-choda", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus ans s-chudains", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus as s-chudais", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas as s-chodan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("eau am s-chudaiva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tü at s-chudaivast", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella as s-chudaiva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus ans s-chudaivans", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus as s-chudaivas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas as s-chudaivan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ch'eau am s-choda", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("cha tü at s-chodast", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella as s-choda", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("cha nus ans s-chodans", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("cha vus as s-chodas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas as s-chodan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ch'eau am s-chudess", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("cha tü at s-chudessast", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella as s-chudess", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("cha nus ans s-chudessans", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("cha vus as s-chudessas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas as s-chudessan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("eau am s-chudess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tü at s-chudessast", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella as s-chudess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus ans s-chudessans", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus as s-chudessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas as s-chudessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("eau am s-chudaro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tü at s-chudarost", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella as s-chudaro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus ans s-chudarons", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus as s-chudaros", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas as s-chudaron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("eau am s-chudaregia", forms.getInflectionValues().get("futurdubitativsing1"));
        Assert.assertEquals("tü at s-chudaregiast", forms.getInflectionValues().get("futurdubitativsing2"));
        Assert.assertEquals("el/ella as s-chudaregia", forms.getInflectionValues().get("futurdubitativsing3"));
        Assert.assertEquals("nus ans s-chudaregians", forms.getInflectionValues().get("futurdubitativplural1"));
        Assert.assertEquals("vus as s-chudaregias", forms.getInflectionValues().get("futurdubitativplural2"));
        Assert.assertEquals("els/ellas as s-chudaregian", forms.getInflectionValues().get("futurdubitativplural3"));

        Assert.assertEquals("s-chudo", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("s-chudeda", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("s-chudos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("s-chudedas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("s-choda't!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("s-chudè's!", forms.getInflectionValues().get("imperativ2"));
        Assert.assertEquals("nu't s-chuder!", forms.getInflectionValues().get("imperativ3"));
        Assert.assertEquals("nu's s-chudè!", forms.getInflectionValues().get("imperativ4"));
        Assert.assertEquals("ch'El/Ella as s-choda!", forms.getInflectionValues().get("imperativ5"));
        Assert.assertEquals("ch'Els/Ellas as s-chodan!", forms.getInflectionValues().get("imperativ6"));

        Assert.assertEquals("as s-chudand", forms.getInflectionValues().get("gerundium"));
    }*/
    @Test
    public void testRule1reflexiv() {
        forms =  generator.generateConjugation("1", "as s-chaluner");

        Assert.assertEquals("as s-chaluner", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("eau am s-chalun", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tü at s-chalunast", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella as s-chaluna", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus ans s-chalunains", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus as s-chalunais", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas as s-chalunan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("eau am s-chalunaiva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tü at s-chalunaivast", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella as s-chalunaiva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus ans s-chalunaivans", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus as s-chalunaivas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas as s-chalunaivan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ch'eau am s-chaluna", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("cha tü at s-chalunast", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella as s-chaluna", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("cha nus ans s-chalunans", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("cha vus as s-chalunas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas as s-chalunan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ch'eau am s-chaluness", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("cha tü at s-chalunessast", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella as s-chaluness", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("cha nus ans s-chalunessans", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("cha vus as s-chalunessas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas as s-chalunessan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("eau am s-chaluness", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tü at s-chalunessast", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella as s-chaluness", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus ans s-chalunessans", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus as s-chalunessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas as s-chalunessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("eau am s-chalunaro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tü at s-chalunarost", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella as s-chalunaro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus ans s-chalunarons", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus as s-chalunaros", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas as s-chalunaron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("eau am s-chalunaregia", forms.getInflectionValues().get("futurdubitativsing1"));
        Assert.assertEquals("tü at s-chalunaregiast", forms.getInflectionValues().get("futurdubitativsing2"));
        Assert.assertEquals("el/ella as s-chalunaregia", forms.getInflectionValues().get("futurdubitativsing3"));
        Assert.assertEquals("nus ans s-chalunaregians", forms.getInflectionValues().get("futurdubitativplural1"));
        Assert.assertEquals("vus as s-chalunaregias", forms.getInflectionValues().get("futurdubitativplural2"));
        Assert.assertEquals("els/ellas as s-chalunaregian", forms.getInflectionValues().get("futurdubitativplural3"));

        Assert.assertEquals("s-chaluno", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("s-chaluneda", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("s-chalunos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("s-chalunedas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("s-chaluna't!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("s-chalunè's!", forms.getInflectionValues().get("imperativ2"));
        Assert.assertEquals("nu't s-chaluner!", forms.getInflectionValues().get("imperativ3"));
        Assert.assertEquals("nu's s-chalunè!", forms.getInflectionValues().get("imperativ4"));
        Assert.assertEquals("ch'El/Ella as s-chaluna!", forms.getInflectionValues().get("imperativ5"));
        Assert.assertEquals("ch'Els/Ellas as s-chalunan!", forms.getInflectionValues().get("imperativ6"));

        Assert.assertEquals("as s-chalunand", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1reflexivVowel() {
        forms =  generator.generateConjugation("1", "s'abasser");

        Assert.assertEquals("s'abasser", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("eau m'abass", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tü t'abassast", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella s'abassa", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus ans abassains", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus s'abassais", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas s'abassan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("eau m'abassaiva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tü t'abassaivast", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella s'abassaiva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus ans abassaivans", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus s'abassaivas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas s'abassaivan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ch'eau m'abassa", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("cha tü t'abassast", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella s'abassa", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("cha nus ans abassans", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("cha vus s'abassas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas s'abassan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ch'eau m'abassess", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("cha tü t'abassessast", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella s'abassess", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("cha nus ans abassessans", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("cha vus s'abassessas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas s'abassessan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("eau m'abassess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tü t'abassessast", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella s'abassess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus ans abassessans", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus s'abassessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas s'abassessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("eau m'abassaro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tü t'abassarost", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella s'abassaro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus ans abassarons", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus s'abassaros", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas s'abassaron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("eau m'abassaregia", forms.getInflectionValues().get("futurdubitativsing1"));
        Assert.assertEquals("tü t'abassaregiast", forms.getInflectionValues().get("futurdubitativsing2"));
        Assert.assertEquals("el/ella s'abassaregia", forms.getInflectionValues().get("futurdubitativsing3"));
        Assert.assertEquals("nus ans abassaregians", forms.getInflectionValues().get("futurdubitativplural1"));
        Assert.assertEquals("vus s'abassaregias", forms.getInflectionValues().get("futurdubitativplural2"));
        Assert.assertEquals("els/ellas s'abassaregian", forms.getInflectionValues().get("futurdubitativplural3"));

        Assert.assertEquals("abasso", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("abasseda", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("abassos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("abassedas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("abassa't!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("abassè's!", forms.getInflectionValues().get("imperativ2"));
        Assert.assertEquals("nu t'abasser!", forms.getInflectionValues().get("imperativ3"));
        Assert.assertEquals("nu s'abassè!", forms.getInflectionValues().get("imperativ4"));
        Assert.assertEquals("ch'El/Ella s'abassa!", forms.getInflectionValues().get("imperativ5"));
        Assert.assertEquals("ch'Els/Ellas s'abassan!", forms.getInflectionValues().get("imperativ6"));

        Assert.assertEquals("s'abassand", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule2() {
        forms =  generator.generateConjugation("2", "giodair");

        Assert.assertEquals("giodair", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("eau giod", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tü giodast", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella gioda", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus giodains", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus giodais", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas giodan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("eau giodaiva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tü giodaivast", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella giodaiva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus giodaivans", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus giodaivas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas giodaivan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ch'eau gioda", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("cha tü giodast", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella gioda", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("cha nus giodans", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("cha vus giodas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas giodan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ch'eau giodess", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("cha tü giodessast", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella giodess", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("cha nus giodessans", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("cha vus giodessas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas giodessan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("eau giodess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tü giodessast", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella giodess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus giodessans", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus giodessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas giodessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("eau giodaro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tü giodarost", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella giodaro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus giodarons", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus giodaros", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas giodaron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("eau giodaregia", forms.getInflectionValues().get("futurdubitativsing1"));
        Assert.assertEquals("tü giodaregiast", forms.getInflectionValues().get("futurdubitativsing2"));
        Assert.assertEquals("el/ella giodaregia", forms.getInflectionValues().get("futurdubitativsing3"));
        Assert.assertEquals("nus giodaregians", forms.getInflectionValues().get("futurdubitativplural1"));
        Assert.assertEquals("vus giodaregias", forms.getInflectionValues().get("futurdubitativplural2"));
        Assert.assertEquals("els/ellas giodaregian", forms.getInflectionValues().get("futurdubitativplural3"));

        Assert.assertEquals("giodieu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("giodida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("giodieus", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("giodidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("gioda!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("giodè!", forms.getInflectionValues().get("imperativ2"));
        Assert.assertEquals("nu giodair!", forms.getInflectionValues().get("imperativ3"));
        Assert.assertEquals("nu giodè!", forms.getInflectionValues().get("imperativ4"));
        Assert.assertEquals("ch'El/Ella gioda!", forms.getInflectionValues().get("imperativ5"));
        Assert.assertEquals("ch'Els/Ellas giodan!", forms.getInflectionValues().get("imperativ6"));

        Assert.assertEquals("giodand", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule3() {
        forms =  generator.generateConjugation("3", "vender");

        Assert.assertEquals("vender", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("eau vend", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tü vendast", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella venda", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus vendains", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus vendais", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas vendan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("eau vendaiva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tü vendaivast", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella vendaiva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus vendaivans", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus vendaivas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas vendaivan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ch'eau venda", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("cha tü vendast", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella venda", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("cha nus vendans", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("cha vus vendas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas vendan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ch'eau vendess", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("cha tü vendessast", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella vendess", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("cha nus vendessans", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("cha vus vendessas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas vendessan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("eau vendess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tü vendessast", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella vendess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus vendessans", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus vendessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas vendessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("eau vendaro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tü vendarost", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vendaro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vendarons", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vendaros", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vendaron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("eau vendaregia", forms.getInflectionValues().get("futurdubitativsing1"));
        Assert.assertEquals("tü vendaregiast", forms.getInflectionValues().get("futurdubitativsing2"));
        Assert.assertEquals("el/ella vendaregia", forms.getInflectionValues().get("futurdubitativsing3"));
        Assert.assertEquals("nus vendaregians", forms.getInflectionValues().get("futurdubitativplural1"));
        Assert.assertEquals("vus vendaregias", forms.getInflectionValues().get("futurdubitativplural2"));
        Assert.assertEquals("els/ellas vendaregian", forms.getInflectionValues().get("futurdubitativplural3"));

        Assert.assertEquals("vendieu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("vendida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("vendieus", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("vendidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("venda!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("vendè!", forms.getInflectionValues().get("imperativ2"));
        Assert.assertEquals("nu vender!", forms.getInflectionValues().get("imperativ3"));
        Assert.assertEquals("nu vendè!", forms.getInflectionValues().get("imperativ4"));
        Assert.assertEquals("ch'El/Ella venda!", forms.getInflectionValues().get("imperativ5"));
        Assert.assertEquals("ch'Els/Ellas vendan!", forms.getInflectionValues().get("imperativ6"));

        Assert.assertEquals("vendand", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule4() {
        forms =  generator.generateConjugation("4", "partir");

        Assert.assertEquals("partir", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("eau part", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tü partast", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella parta", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus partins", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus partis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas partan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("eau partiva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tü partivast", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella partiva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus partivans", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus partivas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas partivan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ch'eau parta", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("cha tü partast", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella parta", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("cha nus partans", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("cha vus partas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas partan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ch'eau partiss", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("cha tü partissast", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella partiss", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("cha nus partissans", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("cha vus partissas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas partissan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("eau partiss", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tü partissast", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella partiss", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus partissans", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus partissas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas partissan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("eau partiro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tü partirost", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella partiro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus partirons", forms.getInflectionValues().get("futurplural1"));
        //Assert.assertEquals("vus partiro", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas partiron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("eau partiregia", forms.getInflectionValues().get("futurdubitativsing1"));
        Assert.assertEquals("tü partiregiast", forms.getInflectionValues().get("futurdubitativsing2"));
        Assert.assertEquals("el/ella partiregia", forms.getInflectionValues().get("futurdubitativsing3"));
        Assert.assertEquals("nus partiregians", forms.getInflectionValues().get("futurdubitativplural1"));
        Assert.assertEquals("vus partiregias", forms.getInflectionValues().get("futurdubitativplural2"));
        Assert.assertEquals("els/ellas partiregian", forms.getInflectionValues().get("futurdubitativplural3"));

        Assert.assertEquals("partieu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("partida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("partieus", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("partidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("parta!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("parti!", forms.getInflectionValues().get("imperativ2"));
        Assert.assertEquals("nu partir!", forms.getInflectionValues().get("imperativ3"));
        Assert.assertEquals("nu parti!", forms.getInflectionValues().get("imperativ4"));
        Assert.assertEquals("ch'El/Ella parta!", forms.getInflectionValues().get("imperativ5"));
        Assert.assertEquals("ch'Els/Ellas partan!", forms.getInflectionValues().get("imperativ6"));

        Assert.assertEquals("partind", forms.getInflectionValues().get("gerundium"));
    }


    @Test
    public void testRule4a() {
        forms =  generator.generateConjugation("4a", "chapir");

        Assert.assertEquals("chapir", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("eau chapesch", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tü chapeschast", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella chapescha", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus chapins", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus chapis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas chapeschan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("eau chapiva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tü chapivast", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella chapiva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus chapivans", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus chapivas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas chapivan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ch'eau chapescha", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("cha tü chapeschast", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella chapescha", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("cha nus chapeschans", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("cha vus chapeschas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas chapeschan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ch'eau chapiss", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("cha tü chapissast", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella chapiss", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("cha nus chapissans", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("cha vus chapissas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas chapissan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("eau chapiss", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tü chapissast", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella chapiss", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus chapissans", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus chapissas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas chapissan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("eau chapiro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tü chapirost", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella chapiro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus chapirons", forms.getInflectionValues().get("futurplural1"));
        // Assert.assertEquals("vus chapiro", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas chapiron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("eau chapiregia", forms.getInflectionValues().get("futurdubitativsing1"));
        Assert.assertEquals("tü chapiregiast", forms.getInflectionValues().get("futurdubitativsing2"));
        Assert.assertEquals("el/ella chapiregia", forms.getInflectionValues().get("futurdubitativsing3"));
        Assert.assertEquals("nus chapiregians", forms.getInflectionValues().get("futurdubitativplural1"));
        Assert.assertEquals("vus chapiregias", forms.getInflectionValues().get("futurdubitativplural2"));
        Assert.assertEquals("els/ellas chapiregian", forms.getInflectionValues().get("futurdubitativplural3"));

        Assert.assertEquals("chapieu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("chapida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("chapieus", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("chapidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("chapescha!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("chapi!", forms.getInflectionValues().get("imperativ2"));
        Assert.assertEquals("nu chapir!", forms.getInflectionValues().get("imperativ3"));
        Assert.assertEquals("nu chapi!", forms.getInflectionValues().get("imperativ4"));
        Assert.assertEquals("ch'El/Ella chapescha!", forms.getInflectionValues().get("imperativ5"));
        Assert.assertEquals("ch'Els/Ellas chapeschan!", forms.getInflectionValues().get("imperativ6"));

        Assert.assertEquals("chapind", forms.getInflectionValues().get("gerundium"));
    }
}
