package ch.pledarigrond.inflection.verb.puter;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.verb.puter.PuterConjugation;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class PuterConjugationTest {

    private static PuterConjugation generator;
    private InflectionDto forms;

    @BeforeClass
    public static void initialize() {
        generator = new PuterConjugation();
    }

    @Test
    public void testRule1() {
        forms =  generator.generateConjugation("1", "güder");

        Assert.assertEquals("güder", forms.getVerb().getInfinitiv());

        Assert.assertEquals("eau güd", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tü güdast", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella güda", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus güdains", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus güdais", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas güdan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("eau güdaiva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tü güdaivast", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella güdaiva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus güdaivans", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus güdaivas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas güdaivan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ch'eau güda", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("cha tü güdast", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella güda", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("cha nus güdans", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("cha vus güdas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas güdan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ch'eau güdess", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("cha tü güdessast", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella güdess", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("cha nus güdessans", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("cha vus güdessas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas güdessan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("eau güdess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tü güdessast", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella güdess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus güdessans", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus güdessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas güdessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("eau güdaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tü güdarost", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella güdaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus güdarons", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus güdaros", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas güdaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("eau güdaregia", forms.getVerb().getFuturDubitativ().getSing1());
        Assert.assertEquals("tü güdaregiast", forms.getVerb().getFuturDubitativ().getSing2());
        Assert.assertEquals("el/ella güdaregia", forms.getVerb().getFuturDubitativ().getSing3());
        Assert.assertEquals("nus güdaregians", forms.getVerb().getFuturDubitativ().getPlural1());
        Assert.assertEquals("vus güdaregias", forms.getVerb().getFuturDubitativ().getPlural2());
        Assert.assertEquals("els/ellas güdaregian", forms.getVerb().getFuturDubitativ().getPlural3());

        Assert.assertEquals("güdo", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("güdeda", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("güdos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("güdedas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("güda!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("güdè!", forms.getVerb().getImperativ().getPlural());
        Assert.assertEquals("nu güder!", forms.getVerb().getImperativ().getForm3());
        Assert.assertEquals("nu güdè!", forms.getVerb().getImperativ().getForm4());
        Assert.assertEquals("ch'El/Ella güda!", forms.getVerb().getImperativ().getForm5());
        Assert.assertEquals("ch'Els/Ellas güdan!", forms.getVerb().getImperativ().getForm6());

        Assert.assertEquals("güdand", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1Vowel() {
        forms =  generator.generateConjugation("1", "occuper");

        Assert.assertEquals("occuper", forms.getVerb().getInfinitiv());

        Assert.assertEquals("eau occup", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tü occupast", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella occupa", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus occupains", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus occupais", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas occupan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("occupa!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("occupè!", forms.getVerb().getImperativ().getPlural());
        Assert.assertEquals("nun occuper!", forms.getVerb().getImperativ().getForm3());
        Assert.assertEquals("nun occupè!", forms.getVerb().getImperativ().getForm4());
        Assert.assertEquals("ch'El/Ella occupa!", forms.getVerb().getImperativ().getForm5());
        Assert.assertEquals("ch'Els/Ellas occupan!", forms.getVerb().getImperativ().getForm6());

    }

    @Test
    public void testRule1a() {
        forms =  generator.generateConjugation("1a", "gratuler");

        Assert.assertEquals("gratuler", forms.getVerb().getInfinitiv());

        Assert.assertEquals("eau gratulesch", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tü gratuleschast", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella gratulescha", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus gratulains", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus gratulais", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas gratuleschan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("eau gratulaiva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tü gratulaivast", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella gratulaiva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus gratulaivans", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus gratulaivas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas gratulaivan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ch'eau gratulescha", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("cha tü gratuleschast", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella gratulescha", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("cha nus gratuleschans", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("cha vus gratuleschas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas gratuleschan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ch'eau gratuless", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("cha tü gratulessast", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella gratuless", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("cha nus gratulessans", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("cha vus gratulessas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas gratulessan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("eau gratuless", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tü gratulessast", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella gratuless", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus gratulessans", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus gratulessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas gratulessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("eau gratularo", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tü gratularost", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella gratularo", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus gratularons", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus gratularos", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas gratularon", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("eau gratularegia", forms.getVerb().getFuturDubitativ().getSing1());
        Assert.assertEquals("tü gratularegiast", forms.getVerb().getFuturDubitativ().getSing2());
        Assert.assertEquals("el/ella gratularegia", forms.getVerb().getFuturDubitativ().getSing3());
        Assert.assertEquals("nus gratularegians", forms.getVerb().getFuturDubitativ().getPlural1());
        Assert.assertEquals("vus gratularegias", forms.getVerb().getFuturDubitativ().getPlural2());
        Assert.assertEquals("els/ellas gratularegian", forms.getVerb().getFuturDubitativ().getPlural3());

        Assert.assertEquals("gratulo", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("gratuleda", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("gratulos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("gratuledas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("gratulescha!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("gratulè!", forms.getVerb().getImperativ().getPlural());
        Assert.assertEquals("nu gratuler!", forms.getVerb().getImperativ().getForm3());
        Assert.assertEquals("nu gratulè!", forms.getVerb().getImperativ().getForm4());
        Assert.assertEquals("ch'El/Ella gratulescha!", forms.getVerb().getImperativ().getForm5());
        Assert.assertEquals("ch'Els/Ellas gratuleschan!", forms.getVerb().getImperativ().getForm6());

        Assert.assertEquals("gratuland", forms.getVerb().getGerundium());
    }

    /*@Test
    public void testRule1reflexiv() {
        forms =  generator.generateConjugation("1", "as s-chuder");

        Assert.assertEquals("as s-chuder", forms.getVerb().getInfinitiv());

        Assert.assertEquals("eau am s-chod", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tü at s-chodast", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella as s-choda", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus ans s-chudains", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus as s-chudais", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas as s-chodan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("eau am s-chudaiva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tü at s-chudaivast", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella as s-chudaiva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus ans s-chudaivans", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus as s-chudaivas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas as s-chudaivan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ch'eau am s-choda", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("cha tü at s-chodast", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella as s-choda", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("cha nus ans s-chodans", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("cha vus as s-chodas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas as s-chodan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ch'eau am s-chudess", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("cha tü at s-chudessast", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella as s-chudess", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("cha nus ans s-chudessans", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("cha vus as s-chudessas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas as s-chudessan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("eau am s-chudess", forms.getVerb().getCundizional().getSing1());
        Assert.assertEquals("tü at s-chudessast", forms.getVerb().getCundizional().getSing2());
        Assert.assertEquals("el/ella as s-chudess", forms.getVerb().getCundizional().getSing3());
        Assert.assertEquals("nus ans s-chudessans", forms.getVerb().getCundizional().getPlural1());
        Assert.assertEquals("vus as s-chudessas", forms.getVerb().getCundizional().getPlural2());
        Assert.assertEquals("els/ellas as s-chudessan", forms.getVerb().getCundizional().getPlural3());

        Assert.assertEquals("eau am s-chudaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tü at s-chudarost", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella as s-chudaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus ans s-chudarons", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus as s-chudaros", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas as s-chudaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("eau am s-chudaregia", forms.getVerb().getFuturdubitativ().getSing1());
        Assert.assertEquals("tü at s-chudaregiast", forms.getVerb().getFuturdubitativ().getSing2());
        Assert.assertEquals("el/ella as s-chudaregia", forms.getVerb().getFuturdubitativ().getSing3());
        Assert.assertEquals("nus ans s-chudaregians", forms.getVerb().getFuturdubitativ().getPlural1());
        Assert.assertEquals("vus as s-chudaregias", forms.getVerb().getFuturdubitativ().getPlural2());
        Assert.assertEquals("els/ellas as s-chudaregian", forms.getVerb().getFuturdubitativ().getPlural3());

        Assert.assertEquals("s-chudo", forms.getVerb().getParticipperfect().getMs());
        Assert.assertEquals("s-chudeda", forms.getVerb().getParticipperfect().getFs());
        Assert.assertEquals("s-chudos", forms.getVerb().getParticipperfect().getMp());
        Assert.assertEquals("s-chudedas", forms.getVerb().getParticipperfect().getFp());

        Assert.assertEquals("s-choda't!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("s-chudè's!", forms.getVerb().getImperativ().getPlural());
        Assert.assertEquals("nu't s-chuder!", forms.getVerb().getImperativ().getForm3());
        Assert.assertEquals("nu's s-chudè!", forms.getVerb().getImperativ().getForm4());
        Assert.assertEquals("ch'El/Ella as s-choda!", forms.getVerb().getImperativ().getForm5());
        Assert.assertEquals("ch'Els/Ellas as s-chodan!", forms.getVerb().getImperativ().getForm6());

        Assert.assertEquals("as s-chudand", forms.getVerb().getGerundium());
    }*/
    @Test
    public void testRule1reflexiv() {
        forms =  generator.generateConjugation("1", "as s-chaluner");

        Assert.assertEquals("as s-chaluner", forms.getVerb().getInfinitiv());

        Assert.assertEquals("eau am s-chalun", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tü at s-chalunast", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella as s-chaluna", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus ans s-chalunains", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus as s-chalunais", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas as s-chalunan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("eau am s-chalunaiva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tü at s-chalunaivast", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella as s-chalunaiva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus ans s-chalunaivans", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus as s-chalunaivas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas as s-chalunaivan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ch'eau am s-chaluna", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("cha tü at s-chalunast", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella as s-chaluna", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("cha nus ans s-chalunans", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("cha vus as s-chalunas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas as s-chalunan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ch'eau am s-chaluness", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("cha tü at s-chalunessast", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella as s-chaluness", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("cha nus ans s-chalunessans", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("cha vus as s-chalunessas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas as s-chalunessan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("eau am s-chaluness", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tü at s-chalunessast", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella as s-chaluness", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus ans s-chalunessans", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus as s-chalunessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas as s-chalunessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("eau am s-chalunaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tü at s-chalunarost", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella as s-chalunaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus ans s-chalunarons", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus as s-chalunaros", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas as s-chalunaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("eau am s-chalunaregia", forms.getVerb().getFuturDubitativ().getSing1());
        Assert.assertEquals("tü at s-chalunaregiast", forms.getVerb().getFuturDubitativ().getSing2());
        Assert.assertEquals("el/ella as s-chalunaregia", forms.getVerb().getFuturDubitativ().getSing3());
        Assert.assertEquals("nus ans s-chalunaregians", forms.getVerb().getFuturDubitativ().getPlural1());
        Assert.assertEquals("vus as s-chalunaregias", forms.getVerb().getFuturDubitativ().getPlural2());
        Assert.assertEquals("els/ellas as s-chalunaregian", forms.getVerb().getFuturDubitativ().getPlural3());

        Assert.assertEquals("s-chaluno", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("s-chaluneda", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("s-chalunos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("s-chalunedas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("s-chaluna't!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("s-chalunè's!", forms.getVerb().getImperativ().getPlural());
        Assert.assertEquals("nu't s-chaluner!", forms.getVerb().getImperativ().getForm3());
        Assert.assertEquals("nu's s-chalunè!", forms.getVerb().getImperativ().getForm4());
        Assert.assertEquals("ch'El/Ella as s-chaluna!", forms.getVerb().getImperativ().getForm5());
        Assert.assertEquals("ch'Els/Ellas as s-chalunan!", forms.getVerb().getImperativ().getForm6());

        Assert.assertEquals("as s-chalunand", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1reflexivModRoot() {
        forms = generator.generateConjugation("1", "as blocker");

        Assert.assertEquals("eau am bloc", forms.getVerb().getPreschent().getSing1());
    }

    @Test
    public void testRule1reflexivVowel() {
        forms =  generator.generateConjugation("1", "s'abasser");

        Assert.assertEquals("s'abasser", forms.getVerb().getInfinitiv());

        Assert.assertEquals("eau m'abass", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tü t'abassast", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella s'abassa", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus ans abassains", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus s'abassais", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas s'abassan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("eau m'abassaiva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tü t'abassaivast", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella s'abassaiva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus ans abassaivans", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus s'abassaivas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas s'abassaivan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ch'eau m'abassa", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("cha tü t'abassast", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella s'abassa", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("cha nus ans abassans", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("cha vus s'abassas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas s'abassan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ch'eau m'abassess", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("cha tü t'abassessast", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella s'abassess", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("cha nus ans abassessans", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("cha vus s'abassessas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas s'abassessan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("eau m'abassess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tü t'abassessast", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella s'abassess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus ans abassessans", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus s'abassessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas s'abassessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("eau m'abassaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tü t'abassarost", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella s'abassaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus ans abassarons", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus s'abassaros", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas s'abassaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("eau m'abassaregia", forms.getVerb().getFuturDubitativ().getSing1());
        Assert.assertEquals("tü t'abassaregiast", forms.getVerb().getFuturDubitativ().getSing2());
        Assert.assertEquals("el/ella s'abassaregia", forms.getVerb().getFuturDubitativ().getSing3());
        Assert.assertEquals("nus ans abassaregians", forms.getVerb().getFuturDubitativ().getPlural1());
        Assert.assertEquals("vus s'abassaregias", forms.getVerb().getFuturDubitativ().getPlural2());
        Assert.assertEquals("els/ellas s'abassaregian", forms.getVerb().getFuturDubitativ().getPlural3());

        Assert.assertEquals("abasso", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("abasseda", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("abassos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("abassedas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("abassa't!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("abassè's!", forms.getVerb().getImperativ().getPlural());
        Assert.assertEquals("nu t'abasser!", forms.getVerb().getImperativ().getForm3());
        Assert.assertEquals("nu s'abassè!", forms.getVerb().getImperativ().getForm4());
        Assert.assertEquals("ch'El/Ella s'abassa!", forms.getVerb().getImperativ().getForm5());
        Assert.assertEquals("ch'Els/Ellas s'abassan!", forms.getVerb().getImperativ().getForm6());

        Assert.assertEquals("s'abassand", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule2() {
        forms =  generator.generateConjugation("2", "giodair");

        Assert.assertEquals("giodair", forms.getVerb().getInfinitiv());

        Assert.assertEquals("eau giod", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tü giodast", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella gioda", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus giodains", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus giodais", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas giodan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("eau giodaiva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tü giodaivast", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella giodaiva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus giodaivans", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus giodaivas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas giodaivan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ch'eau gioda", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("cha tü giodast", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella gioda", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("cha nus giodans", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("cha vus giodas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas giodan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ch'eau giodess", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("cha tü giodessast", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella giodess", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("cha nus giodessans", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("cha vus giodessas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas giodessan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("eau giodess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tü giodessast", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella giodess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus giodessans", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus giodessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas giodessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("eau giodaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tü giodarost", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella giodaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus giodarons", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus giodaros", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas giodaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("eau giodaregia", forms.getVerb().getFuturDubitativ().getSing1());
        Assert.assertEquals("tü giodaregiast", forms.getVerb().getFuturDubitativ().getSing2());
        Assert.assertEquals("el/ella giodaregia", forms.getVerb().getFuturDubitativ().getSing3());
        Assert.assertEquals("nus giodaregians", forms.getVerb().getFuturDubitativ().getPlural1());
        Assert.assertEquals("vus giodaregias", forms.getVerb().getFuturDubitativ().getPlural2());
        Assert.assertEquals("els/ellas giodaregian", forms.getVerb().getFuturDubitativ().getPlural3());

        Assert.assertEquals("giodieu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("giodida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("giodieus", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("giodidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("gioda!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("giodè!", forms.getVerb().getImperativ().getPlural());
        Assert.assertEquals("nu giodair!", forms.getVerb().getImperativ().getForm3());
        Assert.assertEquals("nu giodè!", forms.getVerb().getImperativ().getForm4());
        Assert.assertEquals("ch'El/Ella gioda!", forms.getVerb().getImperativ().getForm5());
        Assert.assertEquals("ch'Els/Ellas giodan!", forms.getVerb().getImperativ().getForm6());

        Assert.assertEquals("giodand", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3() {
        forms =  generator.generateConjugation("3", "vender");

        Assert.assertEquals("vender", forms.getVerb().getInfinitiv());

        Assert.assertEquals("eau vend", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tü vendast", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella venda", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus vendains", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus vendais", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas vendan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("eau vendaiva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tü vendaivast", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella vendaiva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus vendaivans", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus vendaivas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas vendaivan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ch'eau venda", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("cha tü vendast", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella venda", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("cha nus vendans", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("cha vus vendas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas vendan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ch'eau vendess", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("cha tü vendessast", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella vendess", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("cha nus vendessans", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("cha vus vendessas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas vendessan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("eau vendess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tü vendessast", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella vendess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus vendessans", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus vendessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas vendessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("eau vendaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tü vendarost", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vendaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vendarons", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vendaros", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vendaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("eau vendaregia", forms.getVerb().getFuturDubitativ().getSing1());
        Assert.assertEquals("tü vendaregiast", forms.getVerb().getFuturDubitativ().getSing2());
        Assert.assertEquals("el/ella vendaregia", forms.getVerb().getFuturDubitativ().getSing3());
        Assert.assertEquals("nus vendaregians", forms.getVerb().getFuturDubitativ().getPlural1());
        Assert.assertEquals("vus vendaregias", forms.getVerb().getFuturDubitativ().getPlural2());
        Assert.assertEquals("els/ellas vendaregian", forms.getVerb().getFuturDubitativ().getPlural3());

        Assert.assertEquals("vendieu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("vendida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("vendieus", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("vendidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("venda!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("vendè!", forms.getVerb().getImperativ().getPlural());
        Assert.assertEquals("nu vender!", forms.getVerb().getImperativ().getForm3());
        Assert.assertEquals("nu vendè!", forms.getVerb().getImperativ().getForm4());
        Assert.assertEquals("ch'El/Ella venda!", forms.getVerb().getImperativ().getForm5());
        Assert.assertEquals("ch'Els/Ellas vendan!", forms.getVerb().getImperativ().getForm6());

        Assert.assertEquals("vendand", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4() {
        forms =  generator.generateConjugation("4", "partir");

        Assert.assertEquals("partir", forms.getVerb().getInfinitiv());

        Assert.assertEquals("eau part", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tü partast", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella parta", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus partins", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus partis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas partan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("eau partiva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tü partivast", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella partiva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus partivans", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus partivas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas partivan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ch'eau parta", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("cha tü partast", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella parta", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("cha nus partans", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("cha vus partas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas partan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ch'eau partiss", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("cha tü partissast", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella partiss", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("cha nus partissans", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("cha vus partissas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas partissan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("eau partiss", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tü partissast", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella partiss", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus partissans", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus partissas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas partissan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("eau partiro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tü partirost", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella partiro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus partirons", forms.getVerb().getFutur().getPlural1());
        //Assert.assertEquals("vus partiro", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas partiron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("eau partiregia", forms.getVerb().getFuturDubitativ().getSing1());
        Assert.assertEquals("tü partiregiast", forms.getVerb().getFuturDubitativ().getSing2());
        Assert.assertEquals("el/ella partiregia", forms.getVerb().getFuturDubitativ().getSing3());
        Assert.assertEquals("nus partiregians", forms.getVerb().getFuturDubitativ().getPlural1());
        Assert.assertEquals("vus partiregias", forms.getVerb().getFuturDubitativ().getPlural2());
        Assert.assertEquals("els/ellas partiregian", forms.getVerb().getFuturDubitativ().getPlural3());

        Assert.assertEquals("partieu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("partida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("partieus", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("partidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("parta!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("parti!", forms.getVerb().getImperativ().getPlural());
        Assert.assertEquals("nu partir!", forms.getVerb().getImperativ().getForm3());
        Assert.assertEquals("nu parti!", forms.getVerb().getImperativ().getForm4());
        Assert.assertEquals("ch'El/Ella parta!", forms.getVerb().getImperativ().getForm5());
        Assert.assertEquals("ch'Els/Ellas partan!", forms.getVerb().getImperativ().getForm6());

        Assert.assertEquals("partind", forms.getVerb().getGerundium());
    }


    @Test
    public void testRule4a() {
        forms =  generator.generateConjugation("4a", "chapir");

        Assert.assertEquals("chapir", forms.getVerb().getInfinitiv());

        Assert.assertEquals("eau chapesch", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tü chapeschast", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella chapescha", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus chapins", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus chapis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas chapeschan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("eau chapiva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tü chapivast", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella chapiva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus chapivans", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus chapivas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas chapivan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ch'eau chapescha", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("cha tü chapeschast", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella chapescha", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("cha nus chapeschans", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("cha vus chapeschas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas chapeschan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ch'eau chapiss", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("cha tü chapissast", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella chapiss", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("cha nus chapissans", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("cha vus chapissas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas chapissan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("eau chapiss", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tü chapissast", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella chapiss", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus chapissans", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus chapissas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas chapissan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("eau chapiro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tü chapirost", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella chapiro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus chapirons", forms.getVerb().getFutur().getPlural1());
        // Assert.assertEquals("vus chapiro", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas chapiron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("eau chapiregia", forms.getVerb().getFuturDubitativ().getSing1());
        Assert.assertEquals("tü chapiregiast", forms.getVerb().getFuturDubitativ().getSing2());
        Assert.assertEquals("el/ella chapiregia", forms.getVerb().getFuturDubitativ().getSing3());
        Assert.assertEquals("nus chapiregians", forms.getVerb().getFuturDubitativ().getPlural1());
        Assert.assertEquals("vus chapiregias", forms.getVerb().getFuturDubitativ().getPlural2());
        Assert.assertEquals("els/ellas chapiregian", forms.getVerb().getFuturDubitativ().getPlural3());

        Assert.assertEquals("chapieu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("chapida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("chapieus", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("chapidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("chapescha!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("chapi!", forms.getVerb().getImperativ().getPlural());
        Assert.assertEquals("nu chapir!", forms.getVerb().getImperativ().getForm3());
        Assert.assertEquals("nu chapi!", forms.getVerb().getImperativ().getForm4());
        Assert.assertEquals("ch'El/Ella chapescha!", forms.getVerb().getImperativ().getForm5());
        Assert.assertEquals("ch'Els/Ellas chapeschan!", forms.getVerb().getImperativ().getForm6());

        Assert.assertEquals("chapind", forms.getVerb().getGerundium());
    }
}
