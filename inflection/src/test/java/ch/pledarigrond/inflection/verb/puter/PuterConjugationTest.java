package ch.pledarigrond.inflection.verb.puter;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.verb.puter.PuterConjugation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PuterConjugationTest {

    private static PuterConjugation generator;
    private InflectionDto forms;

    @BeforeAll
    public static void initialize() {
        generator = new PuterConjugation();
    }

    @Test
    public void testRule1() {
        forms =  generator.generateConjugation("1", "güder");

        assertEquals("güder", forms.getVerb().getInfinitiv());

        assertEquals("eau güd", forms.getVerb().getPreschent().getSing1());
        assertEquals("tü güdast", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella güda", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus güdains", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus güdais", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas güdan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("eau güdaiva", forms.getVerb().getImperfect().getSing1());
        assertEquals("tü güdaivast", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella güdaiva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus güdaivans", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus güdaivas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas güdaivan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ch'eau güda", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("cha tü güdast", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella güda", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("cha nus güdans", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("cha vus güdas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas güdan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ch'eau güdess", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("cha tü güdessast", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella güdess", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("cha nus güdessans", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("cha vus güdessas", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas güdessan", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("eau güdess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tü güdessast", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella güdess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus güdessans", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus güdessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas güdessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("eau güdaro", forms.getVerb().getFutur().getSing1());
        assertEquals("tü güdarost", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella güdaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nus güdarons", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus güdaros", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas güdaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("eau güdaregia", forms.getVerb().getFuturDubitativ().getSing1());
        assertEquals("tü güdaregiast", forms.getVerb().getFuturDubitativ().getSing2());
        assertEquals("el/ella güdaregia", forms.getVerb().getFuturDubitativ().getSing3());
        assertEquals("nus güdaregians", forms.getVerb().getFuturDubitativ().getPlural1());
        assertEquals("vus güdaregias", forms.getVerb().getFuturDubitativ().getPlural2());
        assertEquals("els/ellas güdaregian", forms.getVerb().getFuturDubitativ().getPlural3());

        assertEquals("güdo", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("güdeda", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("güdos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("güdedas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("güda!", forms.getVerb().getImperativ().getSingular());
        assertEquals("güdè!", forms.getVerb().getImperativ().getPlural());
        assertEquals("nu güder!", forms.getVerb().getImperativ().getForm3());
        assertEquals("nu güdè!", forms.getVerb().getImperativ().getForm4());
        assertEquals("ch'El/Ella güda!", forms.getVerb().getImperativ().getForm5());
        assertEquals("ch'Els/Ellas güdan!", forms.getVerb().getImperativ().getForm6());

        assertEquals("güdand", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1Vowel() {
        forms =  generator.generateConjugation("1", "occuper");

        assertEquals("occuper", forms.getVerb().getInfinitiv());

        assertEquals("eau occup", forms.getVerb().getPreschent().getSing1());
        assertEquals("tü occupast", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella occupa", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus occupains", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus occupais", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas occupan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("occupa!", forms.getVerb().getImperativ().getSingular());
        assertEquals("occupè!", forms.getVerb().getImperativ().getPlural());
        assertEquals("nun occuper!", forms.getVerb().getImperativ().getForm3());
        assertEquals("nun occupè!", forms.getVerb().getImperativ().getForm4());
        assertEquals("ch'El/Ella occupa!", forms.getVerb().getImperativ().getForm5());
        assertEquals("ch'Els/Ellas occupan!", forms.getVerb().getImperativ().getForm6());

    }

    @Test
    public void testRule1a() {
        forms =  generator.generateConjugation("1a", "gratuler");

        assertEquals("gratuler", forms.getVerb().getInfinitiv());

        assertEquals("eau gratulesch", forms.getVerb().getPreschent().getSing1());
        assertEquals("tü gratuleschast", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella gratulescha", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus gratulains", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus gratulais", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas gratuleschan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("eau gratulaiva", forms.getVerb().getImperfect().getSing1());
        assertEquals("tü gratulaivast", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella gratulaiva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus gratulaivans", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus gratulaivas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas gratulaivan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ch'eau gratulescha", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("cha tü gratuleschast", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella gratulescha", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("cha nus gratuleschans", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("cha vus gratuleschas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas gratuleschan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ch'eau gratuless", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("cha tü gratulessast", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella gratuless", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("cha nus gratulessans", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("cha vus gratulessas", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas gratulessan", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("eau gratuless", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tü gratulessast", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella gratuless", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus gratulessans", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus gratulessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas gratulessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("eau gratularo", forms.getVerb().getFutur().getSing1());
        assertEquals("tü gratularost", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella gratularo", forms.getVerb().getFutur().getSing3());
        assertEquals("nus gratularons", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus gratularos", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas gratularon", forms.getVerb().getFutur().getPlural3());

        assertEquals("eau gratularegia", forms.getVerb().getFuturDubitativ().getSing1());
        assertEquals("tü gratularegiast", forms.getVerb().getFuturDubitativ().getSing2());
        assertEquals("el/ella gratularegia", forms.getVerb().getFuturDubitativ().getSing3());
        assertEquals("nus gratularegians", forms.getVerb().getFuturDubitativ().getPlural1());
        assertEquals("vus gratularegias", forms.getVerb().getFuturDubitativ().getPlural2());
        assertEquals("els/ellas gratularegian", forms.getVerb().getFuturDubitativ().getPlural3());

        assertEquals("gratulo", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("gratuleda", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("gratulos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("gratuledas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("gratulescha!", forms.getVerb().getImperativ().getSingular());
        assertEquals("gratulè!", forms.getVerb().getImperativ().getPlural());
        assertEquals("nu gratuler!", forms.getVerb().getImperativ().getForm3());
        assertEquals("nu gratulè!", forms.getVerb().getImperativ().getForm4());
        assertEquals("ch'El/Ella gratulescha!", forms.getVerb().getImperativ().getForm5());
        assertEquals("ch'Els/Ellas gratuleschan!", forms.getVerb().getImperativ().getForm6());

        assertEquals("gratuland", forms.getVerb().getGerundium());
    }

    /*@Test
    public void testRule1reflexiv() {
        forms =  generator.generateConjugation("1", "as s-chuder");

        assertEquals("as s-chuder", forms.getVerb().getInfinitiv());

        assertEquals("eau am s-chod", forms.getVerb().getPreschent().getSing1());
        assertEquals("tü at s-chodast", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella as s-choda", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus ans s-chudains", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus as s-chudais", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas as s-chodan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("eau am s-chudaiva", forms.getVerb().getImperfect().getSing1());
        assertEquals("tü at s-chudaivast", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella as s-chudaiva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus ans s-chudaivans", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus as s-chudaivas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas as s-chudaivan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ch'eau am s-choda", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("cha tü at s-chodast", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella as s-choda", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("cha nus ans s-chodans", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("cha vus as s-chodas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas as s-chodan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ch'eau am s-chudess", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("cha tü at s-chudessast", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella as s-chudess", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("cha nus ans s-chudessans", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("cha vus as s-chudessas", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas as s-chudessan", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("eau am s-chudess", forms.getVerb().getCundizional().getSing1());
        assertEquals("tü at s-chudessast", forms.getVerb().getCundizional().getSing2());
        assertEquals("el/ella as s-chudess", forms.getVerb().getCundizional().getSing3());
        assertEquals("nus ans s-chudessans", forms.getVerb().getCundizional().getPlural1());
        assertEquals("vus as s-chudessas", forms.getVerb().getCundizional().getPlural2());
        assertEquals("els/ellas as s-chudessan", forms.getVerb().getCundizional().getPlural3());

        assertEquals("eau am s-chudaro", forms.getVerb().getFutur().getSing1());
        assertEquals("tü at s-chudarost", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella as s-chudaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nus ans s-chudarons", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus as s-chudaros", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas as s-chudaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("eau am s-chudaregia", forms.getVerb().getFuturdubitativ().getSing1());
        assertEquals("tü at s-chudaregiast", forms.getVerb().getFuturdubitativ().getSing2());
        assertEquals("el/ella as s-chudaregia", forms.getVerb().getFuturdubitativ().getSing3());
        assertEquals("nus ans s-chudaregians", forms.getVerb().getFuturdubitativ().getPlural1());
        assertEquals("vus as s-chudaregias", forms.getVerb().getFuturdubitativ().getPlural2());
        assertEquals("els/ellas as s-chudaregian", forms.getVerb().getFuturdubitativ().getPlural3());

        assertEquals("s-chudo", forms.getVerb().getParticipperfect().getMs());
        assertEquals("s-chudeda", forms.getVerb().getParticipperfect().getFs());
        assertEquals("s-chudos", forms.getVerb().getParticipperfect().getMp());
        assertEquals("s-chudedas", forms.getVerb().getParticipperfect().getFp());

        assertEquals("s-choda't!", forms.getVerb().getImperativ().getSingular());
        assertEquals("s-chudè's!", forms.getVerb().getImperativ().getPlural());
        assertEquals("nu't s-chuder!", forms.getVerb().getImperativ().getForm3());
        assertEquals("nu's s-chudè!", forms.getVerb().getImperativ().getForm4());
        assertEquals("ch'El/Ella as s-choda!", forms.getVerb().getImperativ().getForm5());
        assertEquals("ch'Els/Ellas as s-chodan!", forms.getVerb().getImperativ().getForm6());

        assertEquals("as s-chudand", forms.getVerb().getGerundium());
    }*/
    @Test
    public void testRule1reflexiv() {
        forms =  generator.generateConjugation("1", "as s-chaluner");

        assertEquals("as s-chaluner", forms.getVerb().getInfinitiv());

        assertEquals("eau am s-chalun", forms.getVerb().getPreschent().getSing1());
        assertEquals("tü at s-chalunast", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella as s-chaluna", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus ans s-chalunains", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus as s-chalunais", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas as s-chalunan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("eau am s-chalunaiva", forms.getVerb().getImperfect().getSing1());
        assertEquals("tü at s-chalunaivast", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella as s-chalunaiva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus ans s-chalunaivans", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus as s-chalunaivas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas as s-chalunaivan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ch'eau am s-chaluna", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("cha tü at s-chalunast", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella as s-chaluna", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("cha nus ans s-chalunans", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("cha vus as s-chalunas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas as s-chalunan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ch'eau am s-chaluness", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("cha tü at s-chalunessast", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella as s-chaluness", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("cha nus ans s-chalunessans", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("cha vus as s-chalunessas", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas as s-chalunessan", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("eau am s-chaluness", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tü at s-chalunessast", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella as s-chaluness", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus ans s-chalunessans", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus as s-chalunessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas as s-chalunessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("eau am s-chalunaro", forms.getVerb().getFutur().getSing1());
        assertEquals("tü at s-chalunarost", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella as s-chalunaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nus ans s-chalunarons", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus as s-chalunaros", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas as s-chalunaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("eau am s-chalunaregia", forms.getVerb().getFuturDubitativ().getSing1());
        assertEquals("tü at s-chalunaregiast", forms.getVerb().getFuturDubitativ().getSing2());
        assertEquals("el/ella as s-chalunaregia", forms.getVerb().getFuturDubitativ().getSing3());
        assertEquals("nus ans s-chalunaregians", forms.getVerb().getFuturDubitativ().getPlural1());
        assertEquals("vus as s-chalunaregias", forms.getVerb().getFuturDubitativ().getPlural2());
        assertEquals("els/ellas as s-chalunaregian", forms.getVerb().getFuturDubitativ().getPlural3());

        assertEquals("s-chaluno", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("s-chaluneda", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("s-chalunos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("s-chalunedas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("s-chaluna't!", forms.getVerb().getImperativ().getSingular());
        assertEquals("s-chalunè's!", forms.getVerb().getImperativ().getPlural());
        assertEquals("nu't s-chaluner!", forms.getVerb().getImperativ().getForm3());
        assertEquals("nu's s-chalunè!", forms.getVerb().getImperativ().getForm4());
        assertEquals("ch'El/Ella as s-chaluna!", forms.getVerb().getImperativ().getForm5());
        assertEquals("ch'Els/Ellas as s-chalunan!", forms.getVerb().getImperativ().getForm6());

        assertEquals("as s-chalunand", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1reflexivModRoot() {
        forms = generator.generateConjugation("1", "as blocker");

        assertEquals("eau am bloc", forms.getVerb().getPreschent().getSing1());
    }

    @Test
    public void testRule1reflexivVowel() {
        forms =  generator.generateConjugation("1", "s'abasser");

        assertEquals("s'abasser", forms.getVerb().getInfinitiv());

        assertEquals("eau m'abass", forms.getVerb().getPreschent().getSing1());
        assertEquals("tü t'abassast", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella s'abassa", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus ans abassains", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus s'abassais", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas s'abassan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("eau m'abassaiva", forms.getVerb().getImperfect().getSing1());
        assertEquals("tü t'abassaivast", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella s'abassaiva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus ans abassaivans", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus s'abassaivas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas s'abassaivan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ch'eau m'abassa", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("cha tü t'abassast", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella s'abassa", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("cha nus ans abassans", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("cha vus s'abassas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas s'abassan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ch'eau m'abassess", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("cha tü t'abassessast", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella s'abassess", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("cha nus ans abassessans", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("cha vus s'abassessas", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas s'abassessan", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("eau m'abassess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tü t'abassessast", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella s'abassess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus ans abassessans", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus s'abassessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas s'abassessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("eau m'abassaro", forms.getVerb().getFutur().getSing1());
        assertEquals("tü t'abassarost", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella s'abassaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nus ans abassarons", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus s'abassaros", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas s'abassaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("eau m'abassaregia", forms.getVerb().getFuturDubitativ().getSing1());
        assertEquals("tü t'abassaregiast", forms.getVerb().getFuturDubitativ().getSing2());
        assertEquals("el/ella s'abassaregia", forms.getVerb().getFuturDubitativ().getSing3());
        assertEquals("nus ans abassaregians", forms.getVerb().getFuturDubitativ().getPlural1());
        assertEquals("vus s'abassaregias", forms.getVerb().getFuturDubitativ().getPlural2());
        assertEquals("els/ellas s'abassaregian", forms.getVerb().getFuturDubitativ().getPlural3());

        assertEquals("abasso", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("abasseda", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("abassos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("abassedas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("abassa't!", forms.getVerb().getImperativ().getSingular());
        assertEquals("abassè's!", forms.getVerb().getImperativ().getPlural());
        assertEquals("nu t'abasser!", forms.getVerb().getImperativ().getForm3());
        assertEquals("nu s'abassè!", forms.getVerb().getImperativ().getForm4());
        assertEquals("ch'El/Ella s'abassa!", forms.getVerb().getImperativ().getForm5());
        assertEquals("ch'Els/Ellas s'abassan!", forms.getVerb().getImperativ().getForm6());

        assertEquals("s'abassand", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule2() {
        forms =  generator.generateConjugation("2", "giodair");

        assertEquals("giodair", forms.getVerb().getInfinitiv());

        assertEquals("eau giod", forms.getVerb().getPreschent().getSing1());
        assertEquals("tü giodast", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella gioda", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus giodains", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus giodais", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas giodan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("eau giodaiva", forms.getVerb().getImperfect().getSing1());
        assertEquals("tü giodaivast", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella giodaiva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus giodaivans", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus giodaivas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas giodaivan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ch'eau gioda", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("cha tü giodast", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella gioda", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("cha nus giodans", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("cha vus giodas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas giodan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ch'eau giodess", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("cha tü giodessast", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella giodess", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("cha nus giodessans", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("cha vus giodessas", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas giodessan", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("eau giodess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tü giodessast", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella giodess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus giodessans", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus giodessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas giodessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("eau giodaro", forms.getVerb().getFutur().getSing1());
        assertEquals("tü giodarost", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella giodaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nus giodarons", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus giodaros", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas giodaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("eau giodaregia", forms.getVerb().getFuturDubitativ().getSing1());
        assertEquals("tü giodaregiast", forms.getVerb().getFuturDubitativ().getSing2());
        assertEquals("el/ella giodaregia", forms.getVerb().getFuturDubitativ().getSing3());
        assertEquals("nus giodaregians", forms.getVerb().getFuturDubitativ().getPlural1());
        assertEquals("vus giodaregias", forms.getVerb().getFuturDubitativ().getPlural2());
        assertEquals("els/ellas giodaregian", forms.getVerb().getFuturDubitativ().getPlural3());

        assertEquals("giodieu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("giodida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("giodieus", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("giodidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("gioda!", forms.getVerb().getImperativ().getSingular());
        assertEquals("giodè!", forms.getVerb().getImperativ().getPlural());
        assertEquals("nu giodair!", forms.getVerb().getImperativ().getForm3());
        assertEquals("nu giodè!", forms.getVerb().getImperativ().getForm4());
        assertEquals("ch'El/Ella gioda!", forms.getVerb().getImperativ().getForm5());
        assertEquals("ch'Els/Ellas giodan!", forms.getVerb().getImperativ().getForm6());

        assertEquals("giodand", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3() {
        forms =  generator.generateConjugation("3", "vender");

        assertEquals("vender", forms.getVerb().getInfinitiv());

        assertEquals("eau vend", forms.getVerb().getPreschent().getSing1());
        assertEquals("tü vendast", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella venda", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus vendains", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus vendais", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas vendan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("eau vendaiva", forms.getVerb().getImperfect().getSing1());
        assertEquals("tü vendaivast", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella vendaiva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus vendaivans", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus vendaivas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas vendaivan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ch'eau venda", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("cha tü vendast", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella venda", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("cha nus vendans", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("cha vus vendas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas vendan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ch'eau vendess", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("cha tü vendessast", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella vendess", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("cha nus vendessans", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("cha vus vendessas", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas vendessan", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("eau vendess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tü vendessast", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella vendess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus vendessans", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus vendessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas vendessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("eau vendaro", forms.getVerb().getFutur().getSing1());
        assertEquals("tü vendarost", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vendaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vendarons", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vendaros", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vendaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("eau vendaregia", forms.getVerb().getFuturDubitativ().getSing1());
        assertEquals("tü vendaregiast", forms.getVerb().getFuturDubitativ().getSing2());
        assertEquals("el/ella vendaregia", forms.getVerb().getFuturDubitativ().getSing3());
        assertEquals("nus vendaregians", forms.getVerb().getFuturDubitativ().getPlural1());
        assertEquals("vus vendaregias", forms.getVerb().getFuturDubitativ().getPlural2());
        assertEquals("els/ellas vendaregian", forms.getVerb().getFuturDubitativ().getPlural3());

        assertEquals("vendieu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("vendida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("vendieus", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("vendidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("venda!", forms.getVerb().getImperativ().getSingular());
        assertEquals("vendè!", forms.getVerb().getImperativ().getPlural());
        assertEquals("nu vender!", forms.getVerb().getImperativ().getForm3());
        assertEquals("nu vendè!", forms.getVerb().getImperativ().getForm4());
        assertEquals("ch'El/Ella venda!", forms.getVerb().getImperativ().getForm5());
        assertEquals("ch'Els/Ellas vendan!", forms.getVerb().getImperativ().getForm6());

        assertEquals("vendand", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4() {
        forms =  generator.generateConjugation("4", "partir");

        assertEquals("partir", forms.getVerb().getInfinitiv());

        assertEquals("eau part", forms.getVerb().getPreschent().getSing1());
        assertEquals("tü partast", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella parta", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus partins", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus partis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas partan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("eau partiva", forms.getVerb().getImperfect().getSing1());
        assertEquals("tü partivast", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella partiva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus partivans", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus partivas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas partivan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ch'eau parta", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("cha tü partast", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella parta", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("cha nus partans", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("cha vus partas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas partan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ch'eau partiss", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("cha tü partissast", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella partiss", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("cha nus partissans", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("cha vus partissas", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas partissan", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("eau partiss", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tü partissast", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella partiss", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus partissans", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus partissas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas partissan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("eau partiro", forms.getVerb().getFutur().getSing1());
        assertEquals("tü partirost", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella partiro", forms.getVerb().getFutur().getSing3());
        assertEquals("nus partirons", forms.getVerb().getFutur().getPlural1());
        //assertEquals("vus partiro", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas partiron", forms.getVerb().getFutur().getPlural3());

        assertEquals("eau partiregia", forms.getVerb().getFuturDubitativ().getSing1());
        assertEquals("tü partiregiast", forms.getVerb().getFuturDubitativ().getSing2());
        assertEquals("el/ella partiregia", forms.getVerb().getFuturDubitativ().getSing3());
        assertEquals("nus partiregians", forms.getVerb().getFuturDubitativ().getPlural1());
        assertEquals("vus partiregias", forms.getVerb().getFuturDubitativ().getPlural2());
        assertEquals("els/ellas partiregian", forms.getVerb().getFuturDubitativ().getPlural3());

        assertEquals("partieu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("partida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("partieus", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("partidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("parta!", forms.getVerb().getImperativ().getSingular());
        assertEquals("parti!", forms.getVerb().getImperativ().getPlural());
        assertEquals("nu partir!", forms.getVerb().getImperativ().getForm3());
        assertEquals("nu parti!", forms.getVerb().getImperativ().getForm4());
        assertEquals("ch'El/Ella parta!", forms.getVerb().getImperativ().getForm5());
        assertEquals("ch'Els/Ellas partan!", forms.getVerb().getImperativ().getForm6());

        assertEquals("partind", forms.getVerb().getGerundium());
    }


    @Test
    public void testRule4a() {
        forms =  generator.generateConjugation("4a", "chapir");

        assertEquals("chapir", forms.getVerb().getInfinitiv());

        assertEquals("eau chapesch", forms.getVerb().getPreschent().getSing1());
        assertEquals("tü chapeschast", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella chapescha", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus chapins", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus chapis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas chapeschan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("eau chapiva", forms.getVerb().getImperfect().getSing1());
        assertEquals("tü chapivast", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella chapiva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus chapivans", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus chapivas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas chapivan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ch'eau chapescha", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("cha tü chapeschast", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella chapescha", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("cha nus chapeschans", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("cha vus chapeschas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas chapeschan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ch'eau chapiss", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("cha tü chapissast", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella chapiss", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("cha nus chapissans", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("cha vus chapissas", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas chapissan", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("eau chapiss", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tü chapissast", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella chapiss", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus chapissans", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus chapissas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas chapissan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("eau chapiro", forms.getVerb().getFutur().getSing1());
        assertEquals("tü chapirost", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella chapiro", forms.getVerb().getFutur().getSing3());
        assertEquals("nus chapirons", forms.getVerb().getFutur().getPlural1());
        // assertEquals("vus chapiro", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas chapiron", forms.getVerb().getFutur().getPlural3());

        assertEquals("eau chapiregia", forms.getVerb().getFuturDubitativ().getSing1());
        assertEquals("tü chapiregiast", forms.getVerb().getFuturDubitativ().getSing2());
        assertEquals("el/ella chapiregia", forms.getVerb().getFuturDubitativ().getSing3());
        assertEquals("nus chapiregians", forms.getVerb().getFuturDubitativ().getPlural1());
        assertEquals("vus chapiregias", forms.getVerb().getFuturDubitativ().getPlural2());
        assertEquals("els/ellas chapiregian", forms.getVerb().getFuturDubitativ().getPlural3());

        assertEquals("chapieu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("chapida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("chapieus", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("chapidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("chapescha!", forms.getVerb().getImperativ().getSingular());
        assertEquals("chapi!", forms.getVerb().getImperativ().getPlural());
        assertEquals("nu chapir!", forms.getVerb().getImperativ().getForm3());
        assertEquals("nu chapi!", forms.getVerb().getImperativ().getForm4());
        assertEquals("ch'El/Ella chapescha!", forms.getVerb().getImperativ().getForm5());
        assertEquals("ch'Els/Ellas chapeschan!", forms.getVerb().getImperativ().getForm6());

        assertEquals("chapind", forms.getVerb().getGerundium());
    }
}
