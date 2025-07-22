package ch.pledarigrond.inflection.verb.vallader;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.verb.vallader.ValladerConjugation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValladerConjugationTest {

    private static ValladerConjugation generator;
    private InflectionDto forms;

    @BeforeAll
    public static void initialize() {
        generator = new ValladerConjugation();
    }

    @Test
    public void testRule1() {
        forms =  generator.generateConjugation("1", "salüdar");

        assertEquals("salüdar", forms.getVerb().getInfinitiv());

        assertEquals("eu salüd", forms.getVerb().getPreschent().getSing1());
        assertEquals("tü salüdast", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella salüda", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus salüdain", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus salüdais\n(vo salüdaivat)", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas salüdan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("eu salüdaiva", forms.getVerb().getImperfect().getSing1());
        assertEquals("tü salüdaivast", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella salüdaiva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus salüdaivan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus salüdaivat", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas salüdaivan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ch'eu salüda", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("cha tü salüdast", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella salüda", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("cha nus salüdan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("cha vus salüdat", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas salüdan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ch'eu salüdess", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("cha tü salüdessast", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("ch'el/ella salüdess", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("cha nus salüdessan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("cha vus salüdessat", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("ch'els/ellas salüdessan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("eu salüdess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tü salüdessast", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella salüdess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus salüdessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus salüdessat", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas salüdessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("eu salüdarà", forms.getVerb().getFutur().getSing1());
        assertEquals("tü salüdarast", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella salüdarà", forms.getVerb().getFutur().getSing3());
        assertEquals("nus salüdaran", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus salüdarat", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas salüdaran", forms.getVerb().getFutur().getPlural3());

        assertEquals("salüdà", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("salüdada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("salüdats", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("salüdadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("salüda!", forms.getVerb().getImperativ().getSingular());
        assertEquals("salüdai!", forms.getVerb().getImperativ().getPlural());
        assertEquals("nu salüdar!", forms.getVerb().getImperativ().getForm3());
        assertEquals("nu salüdarai!", forms.getVerb().getImperativ().getForm4());
        assertEquals("ch'El/Ella salüda!", forms.getVerb().getImperativ().getForm5());
        assertEquals("ch'Els/Ellas salüdan!", forms.getVerb().getImperativ().getForm6());

        assertEquals("salüdond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1a() {
        forms =  generator.generateConjugation("1a", "gratular");

        assertEquals("gratular", forms.getVerb().getInfinitiv());

        assertEquals("eu gratulesch", forms.getVerb().getPreschent().getSing1());
        assertEquals("tü gratuleschast", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella gratulescha", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus gratulain", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus gratulais\n(vo gratulaivat)", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas gratuleschan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("eu gratulaiva", forms.getVerb().getImperfect().getSing1());
        assertEquals("tü gratulaivast", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella gratulaiva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus gratulaivan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus gratulaivat", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas gratulaivan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ch'eu gratulescha", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("cha tü gratuleschast", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella gratulescha", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("cha nus gratuleschan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("cha vus gratuleschat", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas gratuleschan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ch'eu gratuless", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("cha tü gratulessast", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("ch'el/ella gratuless", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("cha nus gratulessan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("cha vus gratulessat", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("ch'els/ellas gratulessan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("eu gratuless", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tü gratulessast", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella gratuless", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus gratulessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus gratulessat", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas gratulessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("eu gratularà", forms.getVerb().getFutur().getSing1());
        assertEquals("tü gratularast", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella gratularà", forms.getVerb().getFutur().getSing3());
        assertEquals("nus gratularan", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus gratularat", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas gratularan", forms.getVerb().getFutur().getPlural3());

        assertEquals("gratulà", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("gratulada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("gratulats", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("gratuladas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("gratulescha!", forms.getVerb().getImperativ().getSingular());
        assertEquals("gratulai!", forms.getVerb().getImperativ().getPlural());
        assertEquals("nu gratular!", forms.getVerb().getImperativ().getForm3());
        assertEquals("nu gratularai!", forms.getVerb().getImperativ().getForm4());
        assertEquals("ch'El/Ella gratulescha!", forms.getVerb().getImperativ().getForm5());
        assertEquals("ch'Els/Ellas gratuleschan!", forms.getVerb().getImperativ().getForm6());

        assertEquals("gratulond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1reflexivModRoot() {
        forms = generator.generateConjugation("1a", "as manger");

        assertEquals("eu am mang", forms.getVerb().getPreschent().getSing1());
    }

    @Test
    public void testRule1reflexiv() {
        forms =  generator.generateConjugation("1a", "as barricadar");

        assertEquals("as barricadar", forms.getVerb().getInfinitiv());

        assertEquals("eu am barricadesch", forms.getVerb().getPreschent().getSing1());
        assertEquals("tü at barricadeschast", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella as barricadescha", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus ans barricadain", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus as barricadais\n(vo as barricadaivat)", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas as barricadeschan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("eu am barricadaiva", forms.getVerb().getImperfect().getSing1());
        assertEquals("tü at barricadaivast", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella as barricadaiva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus ans barricadaivan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus as barricadaivat", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas as barricadaivan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ch'eu am barricadescha", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("cha tü at barricadeschast", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella as barricadescha", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("cha nus ans barricadeschan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("cha vus as barricadeschat", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas as barricadeschan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ch'eu am barricadess", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("cha tü at barricadessast", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("ch'el/ella as barricadess", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("cha nus ans barricadessan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("cha vus as barricadessat", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("ch'els/ellas as barricadessan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("eu am barricadess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tü at barricadessast", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella as barricadess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus ans barricadessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus as barricadessat", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas as barricadessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("eu am barricadarà", forms.getVerb().getFutur().getSing1());
        assertEquals("tü at barricadarast", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella as barricadarà", forms.getVerb().getFutur().getSing3());
        assertEquals("nus ans barricadaran", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus as barricadarat", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas as barricadaran", forms.getVerb().getFutur().getPlural3());

        assertEquals("barricadà", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("barricadada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("barricadats", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("barricadadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("at barricadescha!", forms.getVerb().getImperativ().getSingular());
        assertEquals("as barricadai!", forms.getVerb().getImperativ().getPlural());
        assertEquals("nu't barricadar!", forms.getVerb().getImperativ().getForm3());
        assertEquals("nu's barricadarai!", forms.getVerb().getImperativ().getForm4());
        assertEquals("ch'El/Ella as barricadescha!", forms.getVerb().getImperativ().getForm5());
        assertEquals("ch'Els/Ellas as barricadeschan!", forms.getVerb().getImperativ().getForm6());

        assertEquals("as barricadond", forms.getVerb().getGerundium());
    }

    /*
    @Test
    public void testRule1reflexiv() {
        forms =  generator.generateConjugation("1", "as cuntantar");

        assertEquals("as cuntantar", forms.getVerb().getInfinitiv());

        assertEquals("eu am cuntaint ", forms.getVerb().getPreschent().getSing1());
        assertEquals("tü at cuntaintast", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella as cuntainta", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus ans cuntantain ", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus as cuntantais/aivat", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas as cuntaintan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("eu am cuntantaiva", forms.getVerb().getImperfect().getSing1());
        assertEquals("tü at cuntantaivast", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella as cuntantaiva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus ans cuntantaivan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus as cuntantaivat", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas as cuntantaivan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ch'eu am cuntainta", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("cha tü at cuntaintast", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella as cuntainta", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("cha nus ans cuntaintan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("cha vus as cuntaintat", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas as cuntaintan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ch'eu am cuntantess", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("cha tü at cuntantessast", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella as cuntantess", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("cha nus ans cuntantessan", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("cha vus as cuntantessat", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas as cuntantessan", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("eu am cuntantess", forms.getVerb().getCundizional().getSing1());
        assertEquals("tü at cuntantessast", forms.getVerb().getCundizional().getSing2());
        assertEquals("el/ella as cuntantess", forms.getVerb().getCundizional().getSing3());
        assertEquals("nus ans cuntantessan", forms.getVerb().getCundizional().getPlural1());
        assertEquals("vus as cuntantessat", forms.getVerb().getCundizional().getPlural2());
        assertEquals("els/ellas as cuntantessan", forms.getVerb().getCundizional().getPlural3());

        assertEquals("eu am cuntantarà", forms.getVerb().getFutur().getSing1());
        assertEquals("tü at cuntantarast", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella as cuntantarà", forms.getVerb().getFutur().getSing3());
        assertEquals("nus ans cuntantaran", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus as cuntantarat", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas as cuntantaran", forms.getVerb().getFutur().getPlural3());

        assertEquals("cuntantà", forms.getVerb().getParticipperfect().getMs());
        assertEquals("cuntantada", forms.getVerb().getParticipperfect().getFs());
        assertEquals("cuntantats", forms.getVerb().getParticipperfect().getMp());
        assertEquals("cuntantadas", forms.getVerb().getParticipperfect().getFp());

        assertEquals("at cuntainta!", forms.getVerb().getImperativ().getSingular());
        assertEquals("as cuntantai!", forms.getVerb().getImperativ().getPlural());
        assertEquals("nu't cuntantar!", forms.getVerb().getImperativ().getForm3());
        assertEquals("nu's cuntantarai!", forms.getVerb().getImperativ().getForm4());
        assertEquals("ch'El/Ella-as cuntainta!", forms.getVerb().getImperativ().getForm5());
        assertEquals("ch'Els/Ellas-as cuntaintan!", forms.getVerb().getImperativ().getForm6());

        assertEquals("as cuntantond", forms.getVerb().getGerundium());
    }
    */

    @Test
    public void testRule1reflexivVowel() {
        forms =  generator.generateConjugation("1", "s'inachürar");

        assertEquals("s'inachürar", forms.getVerb().getInfinitiv());

        assertEquals("eu m'inachür", forms.getVerb().getPreschent().getSing1());
        assertEquals("tü t'inachürast", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella s'inachüra", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus ans inachürain", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus s'inachürais\n(vo s'inachüraivat)", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas s'inachüran", forms.getVerb().getPreschent().getPlural3());

        assertEquals("eu m'inachüraiva", forms.getVerb().getImperfect().getSing1());
        assertEquals("tü t'inachüraivast", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella s'inachüraiva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus ans inachüraivan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus s'inachüraivat", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas s'inachüraivan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ch'eu m'inachüra", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("cha tü t'inachürast", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella s'inachüra", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("cha nus ans inachüran", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("cha vus s'inachürat", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas s'inachüran", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ch'eu m'inachüress", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("cha tü t'inachüressast", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("ch'el/ella s'inachüress", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("cha nus ans inachüressan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("cha vus s'inachüressat", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("ch'els/ellas s'inachüressan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("eu m'inachüress", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tü t'inachüressast", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella s'inachüress", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus ans inachüressan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus s'inachüressat", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas s'inachüressan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("eu m'inachürarà", forms.getVerb().getFutur().getSing1());
        assertEquals("tü t'inachürarast", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella s'inachürarà", forms.getVerb().getFutur().getSing3());
        assertEquals("nus ans inachüraran", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus s'inachürarat", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas s'inachüraran", forms.getVerb().getFutur().getPlural3());

        assertEquals("inachürà", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("inachürada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("inachürats", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("inachüradas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("t'inachüra!", forms.getVerb().getImperativ().getSingular());
        assertEquals("s'inachürai!", forms.getVerb().getImperativ().getPlural());
        assertEquals("nu t'inachürar!", forms.getVerb().getImperativ().getForm3());
        assertEquals("nu s'inachürarai!", forms.getVerb().getImperativ().getForm4());
        assertEquals("ch'El/Ella s'inachüra!", forms.getVerb().getImperativ().getForm5());
        assertEquals("ch'Els/Ellas s'inachüran!", forms.getVerb().getImperativ().getForm6());

        assertEquals("s'inachürond", forms.getVerb().getGerundium());
    }

    /*
    @Test
    public void testRule1reflexivVowel() {
        forms =  generator.generateConjugation("1", "s'absantar");

        assertEquals("s'absantar", forms.getVerb().getInfinitiv());

        assertEquals("eu m'absaint", forms.getVerb().getPreschent().getSing1());
        assertEquals("tü t'absaintast", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella s'absainta", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus ans absantain", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus s'absantais/aivat", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas s'absantan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("eu m'absantaiva", forms.getVerb().getImperfect().getSing1());
        assertEquals("tü t'absantaivast", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella s'absantaiva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus ans absantaivan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus s'absantaivat", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas s'absantaivan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ch'eu m'absainta", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("cha tü t'absaintast", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella s'absainta", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("cha nus ans absaintan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("cha vus s'absaintat", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas s'absaintan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ch'eu m'absantess", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("cha tü t'absantessast", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella s'absantess", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("ch'el/ella s'absantess", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("ch'el/ella s'absantess", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'el/ella s'absantess", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("eu m'absantess", forms.getVerb().getCundizional().getSing1());
        assertEquals("tü t'absantessast", forms.getVerb().getCundizional().getSing2());
        assertEquals("el/ella s'absantess", forms.getVerb().getCundizional().getSing3());
        assertEquals("nus ans absantessan", forms.getVerb().getCundizional().getPlural1());
        assertEquals("vus s'absantessat", forms.getVerb().getCundizional().getPlural2());
        assertEquals("els/ellas s'absantessan", forms.getVerb().getCundizional().getPlural3());

        assertEquals("eu m'absantarà", forms.getVerb().getFutur().getSing1());
        assertEquals("tü t'absantarast", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella s'absantarà", forms.getVerb().getFutur().getSing3());
        assertEquals("nus ans absantaran", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus s'absantarat", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas s'absantaran", forms.getVerb().getFutur().getPlural3());

        assertEquals("absantà", forms.getVerb().getParticipperfect().getMs());
        assertEquals("absantada", forms.getVerb().getParticipperfect().getFs());
        assertEquals("absantats", forms.getVerb().getParticipperfect().getMp());
        assertEquals("absantadas", forms.getVerb().getParticipperfect().getFp());

        assertEquals("t'absainta!", forms.getVerb().getImperativ().getSingular());
        assertEquals("s'absantai!", forms.getVerb().getImperativ().getPlural());
        assertEquals("nu t'absantar!", forms.getVerb().getImperativ().getForm3());
        assertEquals("nu s'absantarai!", forms.getVerb().getImperativ().getForm4());
        assertEquals("ch'El/Ella s'absainta!", forms.getVerb().getImperativ().getForm5());
        assertEquals("ch'Els/Ellas s'absaintan!", forms.getVerb().getImperativ().getForm6());

        assertEquals("s' absantond", forms.getVerb().getGerundium());
    }
    */

    @Test
    public void testRule2() {
        forms =  generator.generateConjugation("2", "giodair");

        assertEquals("giodair", forms.getVerb().getInfinitiv());

        assertEquals("eu giod", forms.getVerb().getPreschent().getSing1());
        assertEquals("tü giodast", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella gioda", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus giodain", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus giodais\n(vo giodaivat)", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas giodan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("eu giodaiva", forms.getVerb().getImperfect().getSing1());
        assertEquals("tü giodaivast", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella giodaiva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus giodaivan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus giodaivat", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas giodaivan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ch'eu gioda", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("cha tü giodast", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella gioda", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("cha nus giodan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("cha vus giodat", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas giodan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ch'eu giodess", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("cha tü giodessast", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("ch'el/ella giodess", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("cha nus giodessan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("cha vus giodessat", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("ch'els/ellas giodessan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("eu giodess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tü giodessast", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella giodess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus giodessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus giodessat", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas giodessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("eu giodarà", forms.getVerb().getFutur().getSing1());
        assertEquals("tü giodarast", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella giodarà", forms.getVerb().getFutur().getSing3());
        assertEquals("nus giodaran", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus giodarat", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas giodaran", forms.getVerb().getFutur().getPlural3());

        assertEquals("giodü", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("giodüda", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("giodüts", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("giodüdas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("gioda!", forms.getVerb().getImperativ().getSingular());
        assertEquals("giodai!", forms.getVerb().getImperativ().getPlural());
        assertEquals("nu giodair!", forms.getVerb().getImperativ().getForm3());
        assertEquals("nu giodarai!", forms.getVerb().getImperativ().getForm4());
        assertEquals("ch'El/Ella gioda!", forms.getVerb().getImperativ().getForm5());
        assertEquals("ch'Els/Ellas giodan!", forms.getVerb().getImperativ().getForm6());

        assertEquals("giodond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3() {
        forms =  generator.generateConjugation("3", "vender");

        assertEquals("vender", forms.getVerb().getInfinitiv());

        assertEquals("eu vend", forms.getVerb().getPreschent().getSing1());
        assertEquals("tü vendast", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella venda", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus vendain", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus vendais\n(vo vendaivat)", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas vendan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("eu vendaiva", forms.getVerb().getImperfect().getSing1());
        assertEquals("tü vendaivast", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella vendaiva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus vendaivan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus vendaivat", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas vendaivan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ch'eu venda", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("cha tü vendast", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella venda", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("cha nus vendan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("cha vus vendat", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas vendan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ch'eu vendess", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("cha tü vendessast", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("ch'el/ella vendess", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("cha nus vendessan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("cha vus vendessat", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("ch'els/ellas vendessan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("eu vendess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tü vendessast", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella vendess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus vendessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus vendessat", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas vendessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("eu vendarà", forms.getVerb().getFutur().getSing1());
        assertEquals("tü vendarast", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vendarà", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vendaran", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vendarat", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vendaran", forms.getVerb().getFutur().getPlural3());

        assertEquals("vendü", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("vendüda", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("vendüts", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("vendüdas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("venda!", forms.getVerb().getImperativ().getSingular());
        assertEquals("vendai!", forms.getVerb().getImperativ().getPlural());
        assertEquals("nu vender!", forms.getVerb().getImperativ().getForm3());
        assertEquals("nu vendarai!", forms.getVerb().getImperativ().getForm4());
        assertEquals("ch'El/Ella venda!", forms.getVerb().getImperativ().getForm5());
        assertEquals("ch'Els/Ellas vendan!", forms.getVerb().getImperativ().getForm6());

        assertEquals("vendond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4() {
        forms =  generator.generateConjugation("4", "cusir");

        assertEquals("cusir", forms.getVerb().getInfinitiv());

        assertEquals("eu cus", forms.getVerb().getPreschent().getSing1());
        assertEquals("tü cusast", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella cusa", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus cusin", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus cusis\n(vo cusivat)", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas cusan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("eu cusiva", forms.getVerb().getImperfect().getSing1());
        assertEquals("tü cusivast", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella cusiva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus cusivan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus cusivat", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas cusivan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ch'eu cusa", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("cha tü cusast", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella cusa", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("cha nus cusan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("cha vus cusat", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas cusan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ch'eu cusiss", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("cha tü cusissast", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("ch'el/ella cusiss", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("cha nus cusissan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("cha vus cusissat", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("ch'els/ellas cusissan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("eu cusiss", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tü cusissast", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella cusiss", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus cusissan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus cusissat", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas cusissan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("eu cusirà", forms.getVerb().getFutur().getSing1());
        assertEquals("tü cusirast", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella cusirà", forms.getVerb().getFutur().getSing3());
        assertEquals("nus cusiran", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus cusirat", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas cusiran", forms.getVerb().getFutur().getPlural3());

        assertEquals("cusi", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("cusida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("cusits", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("cusidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("cusa!", forms.getVerb().getImperativ().getSingular());
        assertEquals("cusi!", forms.getVerb().getImperativ().getPlural());
        assertEquals("nu cusir!", forms.getVerb().getImperativ().getForm3());
        assertEquals("nu cusirai!", forms.getVerb().getImperativ().getForm4());
        assertEquals("ch'El/Ella cusa!", forms.getVerb().getImperativ().getForm5());
        assertEquals("ch'Els/Ellas cusan!", forms.getVerb().getImperativ().getForm6());

        assertEquals("cusind", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4a() {
        forms =  generator.generateConjugation("4a", "admonir");

        assertEquals("admonir", forms.getVerb().getInfinitiv());

        assertEquals("eu admonisch", forms.getVerb().getPreschent().getSing1());
        assertEquals("tü admonischast", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella admonischa", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus admonin", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus admonis\n(vo admonivat)", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas admonischan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("eu admoniva", forms.getVerb().getImperfect().getSing1());
        assertEquals("tü admonivast", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella admoniva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus admonivan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus admonivat", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas admonivan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ch'eu admonischa", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("cha tü admonischast", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella admonischa", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("cha nus admonischan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("cha vus admonischat", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas admonischan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ch'eu admoniss", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("cha tü admonissast", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("ch'el/ella admoniss", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("cha nus admonissan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("cha vus admonissat", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("ch'els/ellas admonissan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("eu admoniss", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tü admonissast", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella admoniss", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus admonissan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus admonissat", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas admonissan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("eu admonirà", forms.getVerb().getFutur().getSing1());
        assertEquals("tü admonirast", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella admonirà", forms.getVerb().getFutur().getSing3());
        assertEquals("nus admoniran", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus admonirat", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas admoniran", forms.getVerb().getFutur().getPlural3());

        assertEquals("admoni", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("admonida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("admonits", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("admonidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("admonischa!", forms.getVerb().getImperativ().getSingular());
        assertEquals("admoni!", forms.getVerb().getImperativ().getPlural());
        assertEquals("nun admonir!", forms.getVerb().getImperativ().getForm3());
        assertEquals("nun admonirai!", forms.getVerb().getImperativ().getForm4());
        assertEquals("ch'El/Ella admonischa!", forms.getVerb().getImperativ().getForm5());
        assertEquals("ch'Els/Ellas admonischan!", forms.getVerb().getImperativ().getForm6());

        assertEquals("admonind", forms.getVerb().getGerundium());
    }
}
