package ch.pledarigrond.inflection.verb.vallader;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.verb.vallader.ValladerConjugation;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ValladerConjugationTest {

    private static ValladerConjugation generator;
    private InflectionDto forms;

    @BeforeClass
    public static void initialize() {
        generator = new ValladerConjugation();
    }

    @Test
    public void testRule1() {
        forms =  generator.generateConjugation("1", "salüdar");

        Assert.assertEquals("salüdar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("eu salüd", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tü salüdast", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella salüda", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus salüdain", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus salüdais\n(vo salüdaivat)", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas salüdan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("eu salüdaiva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tü salüdaivast", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella salüdaiva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus salüdaivan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus salüdaivat", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas salüdaivan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ch'eu salüda", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("cha tü salüdast", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella salüda", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("cha nus salüdan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("cha vus salüdat", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas salüdan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ch'eu salüdess", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("cha tü salüdessast", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella salüdess", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("cha nus salüdessan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("cha vus salüdessat", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas salüdessan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("eu salüdess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tü salüdessast", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella salüdess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus salüdessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus salüdessat", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas salüdessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("eu salüdarà", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tü salüdarast", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella salüdarà", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus salüdaran", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus salüdarat", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas salüdaran", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("salüdà", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("salüdada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("salüdats", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("salüdadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("salüda!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("salüdai!", forms.getVerb().getImperativ().getPlural());
        Assert.assertEquals("nu salüdar!", forms.getVerb().getImperativ().getForm3());
        Assert.assertEquals("nu salüdarai!", forms.getVerb().getImperativ().getForm4());
        Assert.assertEquals("ch'El/Ella salüda!", forms.getVerb().getImperativ().getForm5());
        Assert.assertEquals("ch'Els/Ellas salüdan!", forms.getVerb().getImperativ().getForm6());

        Assert.assertEquals("salüdond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1a() {
        forms =  generator.generateConjugation("1a", "gratular");

        Assert.assertEquals("gratular", forms.getVerb().getInfinitiv());

        Assert.assertEquals("eu gratulesch", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tü gratuleschast", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella gratulescha", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus gratulain", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus gratulais\n(vo gratulaivat)", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas gratuleschan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("eu gratulaiva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tü gratulaivast", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella gratulaiva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus gratulaivan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus gratulaivat", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas gratulaivan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ch'eu gratulescha", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("cha tü gratuleschast", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella gratulescha", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("cha nus gratuleschan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("cha vus gratuleschat", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas gratuleschan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ch'eu gratuless", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("cha tü gratulessast", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella gratuless", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("cha nus gratulessan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("cha vus gratulessat", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas gratulessan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("eu gratuless", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tü gratulessast", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella gratuless", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus gratulessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus gratulessat", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas gratulessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("eu gratularà", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tü gratularast", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella gratularà", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus gratularan", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus gratularat", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas gratularan", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("gratulà", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("gratulada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("gratulats", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("gratuladas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("gratulescha!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("gratulai!", forms.getVerb().getImperativ().getPlural());
        Assert.assertEquals("nu gratular!", forms.getVerb().getImperativ().getForm3());
        Assert.assertEquals("nu gratularai!", forms.getVerb().getImperativ().getForm4());
        Assert.assertEquals("ch'El/Ella gratulescha!", forms.getVerb().getImperativ().getForm5());
        Assert.assertEquals("ch'Els/Ellas gratuleschan!", forms.getVerb().getImperativ().getForm6());

        Assert.assertEquals("gratulond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1reflexivModRoot() {
        forms = generator.generateConjugation("1a", "as manger");

        Assert.assertEquals("eu am mang", forms.getVerb().getPreschent().getSing1());
    }

    @Test
    public void testRule1reflexiv() {
        forms =  generator.generateConjugation("1a", "as barricadar");

        Assert.assertEquals("as barricadar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("eu am barricadesch", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tü at barricadeschast", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella as barricadescha", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus ans barricadain", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus as barricadais\n(vo as barricadaivat)", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas as barricadeschan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("eu am barricadaiva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tü at barricadaivast", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella as barricadaiva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus ans barricadaivan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus as barricadaivat", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas as barricadaivan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ch'eu am barricadescha", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("cha tü at barricadeschast", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella as barricadescha", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("cha nus ans barricadeschan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("cha vus as barricadeschat", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas as barricadeschan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ch'eu am barricadess", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("cha tü at barricadessast", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella as barricadess", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("cha nus ans barricadessan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("cha vus as barricadessat", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas as barricadessan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("eu am barricadess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tü at barricadessast", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella as barricadess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus ans barricadessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus as barricadessat", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas as barricadessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("eu am barricadarà", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tü at barricadarast", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella as barricadarà", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus ans barricadaran", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus as barricadarat", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas as barricadaran", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("barricadà", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("barricadada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("barricadats", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("barricadadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("at barricadescha!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("as barricadai!", forms.getVerb().getImperativ().getPlural());
        Assert.assertEquals("nu't barricadar!", forms.getVerb().getImperativ().getForm3());
        Assert.assertEquals("nu's barricadarai!", forms.getVerb().getImperativ().getForm4());
        Assert.assertEquals("ch'El/Ella as barricadescha!", forms.getVerb().getImperativ().getForm5());
        Assert.assertEquals("ch'Els/Ellas as barricadeschan!", forms.getVerb().getImperativ().getForm6());

        Assert.assertEquals("as barricadond", forms.getVerb().getGerundium());
    }

    /*
    @Test
    public void testRule1reflexiv() {
        forms =  generator.generateConjugation("1", "as cuntantar");

        Assert.assertEquals("as cuntantar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("eu am cuntaint ", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tü at cuntaintast", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella as cuntainta", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus ans cuntantain ", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus as cuntantais/aivat", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas as cuntaintan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("eu am cuntantaiva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tü at cuntantaivast", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella as cuntantaiva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus ans cuntantaivan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus as cuntantaivat", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas as cuntantaivan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ch'eu am cuntainta", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("cha tü at cuntaintast", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella as cuntainta", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("cha nus ans cuntaintan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("cha vus as cuntaintat", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas as cuntaintan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ch'eu am cuntantess", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("cha tü at cuntantessast", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella as cuntantess", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("cha nus ans cuntantessan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("cha vus as cuntantessat", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas as cuntantessan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("eu am cuntantess", forms.getVerb().getCundizional().getSing1());
        Assert.assertEquals("tü at cuntantessast", forms.getVerb().getCundizional().getSing2());
        Assert.assertEquals("el/ella as cuntantess", forms.getVerb().getCundizional().getSing3());
        Assert.assertEquals("nus ans cuntantessan", forms.getVerb().getCundizional().getPlural1());
        Assert.assertEquals("vus as cuntantessat", forms.getVerb().getCundizional().getPlural2());
        Assert.assertEquals("els/ellas as cuntantessan", forms.getVerb().getCundizional().getPlural3());

        Assert.assertEquals("eu am cuntantarà", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tü at cuntantarast", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella as cuntantarà", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus ans cuntantaran", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus as cuntantarat", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas as cuntantaran", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("cuntantà", forms.getVerb().getParticipperfect().getMs());
        Assert.assertEquals("cuntantada", forms.getVerb().getParticipperfect().getFs());
        Assert.assertEquals("cuntantats", forms.getVerb().getParticipperfect().getMp());
        Assert.assertEquals("cuntantadas", forms.getVerb().getParticipperfect().getFp());

        Assert.assertEquals("at cuntainta!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("as cuntantai!", forms.getVerb().getImperativ().getPlural());
        Assert.assertEquals("nu't cuntantar!", forms.getVerb().getImperativ().getForm3());
        Assert.assertEquals("nu's cuntantarai!", forms.getVerb().getImperativ().getForm4());
        Assert.assertEquals("ch'El/Ella-as cuntainta!", forms.getVerb().getImperativ().getForm5());
        Assert.assertEquals("ch'Els/Ellas-as cuntaintan!", forms.getVerb().getImperativ().getForm6());

        Assert.assertEquals("as cuntantond", forms.getVerb().getGerundium());
    }
    */

    @Test
    public void testRule1reflexivVowel() {
        forms =  generator.generateConjugation("1", "s'inachürar");

        Assert.assertEquals("s'inachürar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("eu m'inachür", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tü t'inachürast", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella s'inachüra", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus ans inachürain", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus s'inachürais\n(vo s'inachüraivat)", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas s'inachüran", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("eu m'inachüraiva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tü t'inachüraivast", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella s'inachüraiva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus ans inachüraivan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus s'inachüraivat", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas s'inachüraivan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ch'eu m'inachüra", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("cha tü t'inachürast", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella s'inachüra", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("cha nus ans inachüran", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("cha vus s'inachürat", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas s'inachüran", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ch'eu m'inachüress", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("cha tü t'inachüressast", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella s'inachüress", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("cha nus ans inachüressan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("cha vus s'inachüressat", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas s'inachüressan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("eu m'inachüress", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tü t'inachüressast", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella s'inachüress", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus ans inachüressan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus s'inachüressat", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas s'inachüressan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("eu m'inachürarà", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tü t'inachürarast", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella s'inachürarà", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus ans inachüraran", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus s'inachürarat", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas s'inachüraran", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("inachürà", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("inachürada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("inachürats", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("inachüradas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("t'inachüra!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("s'inachürai!", forms.getVerb().getImperativ().getPlural());
        Assert.assertEquals("nu t'inachürar!", forms.getVerb().getImperativ().getForm3());
        Assert.assertEquals("nu s'inachürarai!", forms.getVerb().getImperativ().getForm4());
        Assert.assertEquals("ch'El/Ella s'inachüra!", forms.getVerb().getImperativ().getForm5());
        Assert.assertEquals("ch'Els/Ellas s'inachüran!", forms.getVerb().getImperativ().getForm6());

        Assert.assertEquals("s'inachürond", forms.getVerb().getGerundium());
    }

    /*
    @Test
    public void testRule1reflexivVowel() {
        forms =  generator.generateConjugation("1", "s'absantar");

        Assert.assertEquals("s'absantar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("eu m'absaint", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tü t'absaintast", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella s'absainta", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus ans absantain", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus s'absantais/aivat", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas s'absantan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("eu m'absantaiva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tü t'absantaivast", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella s'absantaiva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus ans absantaivan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus s'absantaivat", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas s'absantaivan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ch'eu m'absainta", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("cha tü t'absaintast", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella s'absainta", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("cha nus ans absaintan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("cha vus s'absaintat", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas s'absaintan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ch'eu m'absantess", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("cha tü t'absantessast", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella s'absantess", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ch'el/ella s'absantess", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ch'el/ella s'absantess", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'el/ella s'absantess", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("eu m'absantess", forms.getVerb().getCundizional().getSing1());
        Assert.assertEquals("tü t'absantessast", forms.getVerb().getCundizional().getSing2());
        Assert.assertEquals("el/ella s'absantess", forms.getVerb().getCundizional().getSing3());
        Assert.assertEquals("nus ans absantessan", forms.getVerb().getCundizional().getPlural1());
        Assert.assertEquals("vus s'absantessat", forms.getVerb().getCundizional().getPlural2());
        Assert.assertEquals("els/ellas s'absantessan", forms.getVerb().getCundizional().getPlural3());

        Assert.assertEquals("eu m'absantarà", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tü t'absantarast", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella s'absantarà", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus ans absantaran", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus s'absantarat", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas s'absantaran", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("absantà", forms.getVerb().getParticipperfect().getMs());
        Assert.assertEquals("absantada", forms.getVerb().getParticipperfect().getFs());
        Assert.assertEquals("absantats", forms.getVerb().getParticipperfect().getMp());
        Assert.assertEquals("absantadas", forms.getVerb().getParticipperfect().getFp());

        Assert.assertEquals("t'absainta!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("s'absantai!", forms.getVerb().getImperativ().getPlural());
        Assert.assertEquals("nu t'absantar!", forms.getVerb().getImperativ().getForm3());
        Assert.assertEquals("nu s'absantarai!", forms.getVerb().getImperativ().getForm4());
        Assert.assertEquals("ch'El/Ella s'absainta!", forms.getVerb().getImperativ().getForm5());
        Assert.assertEquals("ch'Els/Ellas s'absaintan!", forms.getVerb().getImperativ().getForm6());

        Assert.assertEquals("s' absantond", forms.getVerb().getGerundium());
    }
    */

    @Test
    public void testRule2() {
        forms =  generator.generateConjugation("2", "giodair");

        Assert.assertEquals("giodair", forms.getVerb().getInfinitiv());

        Assert.assertEquals("eu giod", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tü giodast", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella gioda", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus giodain", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus giodais\n(vo giodaivat)", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas giodan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("eu giodaiva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tü giodaivast", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella giodaiva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus giodaivan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus giodaivat", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas giodaivan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ch'eu gioda", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("cha tü giodast", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella gioda", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("cha nus giodan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("cha vus giodat", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas giodan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ch'eu giodess", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("cha tü giodessast", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella giodess", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("cha nus giodessan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("cha vus giodessat", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas giodessan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("eu giodess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tü giodessast", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella giodess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus giodessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus giodessat", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas giodessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("eu giodarà", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tü giodarast", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella giodarà", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus giodaran", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus giodarat", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas giodaran", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("giodü", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("giodüda", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("giodüts", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("giodüdas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("gioda!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("giodai!", forms.getVerb().getImperativ().getPlural());
        Assert.assertEquals("nu giodair!", forms.getVerb().getImperativ().getForm3());
        Assert.assertEquals("nu giodarai!", forms.getVerb().getImperativ().getForm4());
        Assert.assertEquals("ch'El/Ella gioda!", forms.getVerb().getImperativ().getForm5());
        Assert.assertEquals("ch'Els/Ellas giodan!", forms.getVerb().getImperativ().getForm6());

        Assert.assertEquals("giodond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3() {
        forms =  generator.generateConjugation("3", "vender");

        Assert.assertEquals("vender", forms.getVerb().getInfinitiv());

        Assert.assertEquals("eu vend", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tü vendast", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella venda", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus vendain", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus vendais\n(vo vendaivat)", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas vendan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("eu vendaiva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tü vendaivast", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella vendaiva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus vendaivan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus vendaivat", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas vendaivan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ch'eu venda", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("cha tü vendast", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella venda", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("cha nus vendan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("cha vus vendat", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas vendan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ch'eu vendess", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("cha tü vendessast", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella vendess", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("cha nus vendessan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("cha vus vendessat", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas vendessan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("eu vendess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tü vendessast", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella vendess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus vendessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus vendessat", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas vendessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("eu vendarà", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tü vendarast", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vendarà", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vendaran", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vendarat", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vendaran", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("vendü", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("vendüda", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("vendüts", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("vendüdas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("venda!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("vendai!", forms.getVerb().getImperativ().getPlural());
        Assert.assertEquals("nu vender!", forms.getVerb().getImperativ().getForm3());
        Assert.assertEquals("nu vendarai!", forms.getVerb().getImperativ().getForm4());
        Assert.assertEquals("ch'El/Ella venda!", forms.getVerb().getImperativ().getForm5());
        Assert.assertEquals("ch'Els/Ellas vendan!", forms.getVerb().getImperativ().getForm6());

        Assert.assertEquals("vendond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4() {
        forms =  generator.generateConjugation("4", "cusir");

        Assert.assertEquals("cusir", forms.getVerb().getInfinitiv());

        Assert.assertEquals("eu cus", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tü cusast", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella cusa", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus cusin", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus cusis\n(vo cusivat)", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas cusan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("eu cusiva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tü cusivast", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella cusiva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus cusivan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus cusivat", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas cusivan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ch'eu cusa", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("cha tü cusast", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella cusa", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("cha nus cusan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("cha vus cusat", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas cusan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ch'eu cusiss", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("cha tü cusissast", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella cusiss", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("cha nus cusissan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("cha vus cusissat", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas cusissan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("eu cusiss", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tü cusissast", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella cusiss", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus cusissan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus cusissat", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas cusissan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("eu cusirà", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tü cusirast", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella cusirà", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus cusiran", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus cusirat", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas cusiran", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("cusi", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("cusida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("cusits", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("cusidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("cusa!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("cusi!", forms.getVerb().getImperativ().getPlural());
        Assert.assertEquals("nu cusir!", forms.getVerb().getImperativ().getForm3());
        Assert.assertEquals("nu cusirai!", forms.getVerb().getImperativ().getForm4());
        Assert.assertEquals("ch'El/Ella cusa!", forms.getVerb().getImperativ().getForm5());
        Assert.assertEquals("ch'Els/Ellas cusan!", forms.getVerb().getImperativ().getForm6());

        Assert.assertEquals("cusind", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4a() {
        forms =  generator.generateConjugation("4a", "admonir");

        Assert.assertEquals("admonir", forms.getVerb().getInfinitiv());

        Assert.assertEquals("eu admonisch", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tü admonischast", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella admonischa", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus admonin", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus admonis\n(vo admonivat)", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas admonischan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("eu admoniva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tü admonivast", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella admoniva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus admonivan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus admonivat", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas admonivan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ch'eu admonischa", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("cha tü admonischast", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella admonischa", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("cha nus admonischan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("cha vus admonischat", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas admonischan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ch'eu admoniss", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("cha tü admonissast", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella admoniss", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("cha nus admonissan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("cha vus admonissat", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas admonissan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("eu admoniss", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tü admonissast", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella admoniss", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus admonissan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus admonissat", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas admonissan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("eu admonirà", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tü admonirast", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella admonirà", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus admoniran", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus admonirat", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas admoniran", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("admoni", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("admonida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("admonits", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("admonidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("admonischa!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("admoni!", forms.getVerb().getImperativ().getPlural());
        Assert.assertEquals("nun admonir!", forms.getVerb().getImperativ().getForm3());
        Assert.assertEquals("nun admonirai!", forms.getVerb().getImperativ().getForm4());
        Assert.assertEquals("ch'El/Ella admonischa!", forms.getVerb().getImperativ().getForm5());
        Assert.assertEquals("ch'Els/Ellas admonischan!", forms.getVerb().getImperativ().getForm6());

        Assert.assertEquals("admonind", forms.getVerb().getGerundium());
    }
}
