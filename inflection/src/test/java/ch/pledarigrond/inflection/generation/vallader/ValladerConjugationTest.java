package ch.pledarigrond.inflection.generation.vallader;

import ch.pledarigrond.inflection.model.InflectionResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ValladerConjugationTest {

    private static ValladerConjugation generator;
    private InflectionResponse forms;

    @BeforeClass
    public static void initialize() {
        generator = new ValladerConjugation();
    }

    @Test
    public void testRule1() {
        forms =  generator.generateConjugation("1", "salüdar");

        Assert.assertEquals("salüdar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("eu salüd", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tü salüdast", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella salüda", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus salüdain", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus salüdais\nvo salüdaivat", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas salüdan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("eu salüdaiva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tü salüdaivast", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella salüdaiva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus salüdaivan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vo salüdaivat", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas salüdaivan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ch'eu salüda", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("cha tü salüdast", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella salüda", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("cha nus salüdan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("cha vus salüdat", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas salüdan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("eu salüdess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tü salüdessast", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella salüdess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus salüdessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus salüdessat", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas salüdessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("eu salüdarà", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tü salüdarast", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella salüdarà", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus salüdaran", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus salüdarat", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas salüdaran", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("salüdà", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("salüdada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("salüdats", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("salüdadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("salüda!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("salüdai!", forms.getInflectionValues().get("imperativ2"));
        Assert.assertEquals("nu salüdar!", forms.getInflectionValues().get("imperativ3"));
        Assert.assertEquals("nu salüdarai!", forms.getInflectionValues().get("imperativ4"));
        Assert.assertEquals("ch'El/Ella salüda!", forms.getInflectionValues().get("imperativ5"));
        Assert.assertEquals("ch'Els/Ellas salüdan!", forms.getInflectionValues().get("imperativ6"));

        Assert.assertEquals("salüdond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1a() {
        forms =  generator.generateConjugation("1a", "gratular");

        Assert.assertEquals("gratular", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("eu gratulesch", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tü gratuleschast", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella gratulescha", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus gratulain", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus gratulais\nvo gratulaivat", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas gratuleschan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("eu gratulaiva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tü gratulaivast", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella gratulaiva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus gratulaivan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vo gratulaivat", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas gratulaivan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ch'eu gratulescha", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("cha tü gratuleschast", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella gratulescha", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("cha nus gratuleschan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("cha vus gratuleschat", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas gratuleschan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("eu gratuless", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tü gratulessast", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella gratuless", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus gratulessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus gratulessat", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas gratulessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("eu gratularà", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tü gratularast", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella gratularà", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus gratularan", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus gratularat", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas gratularan", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("gratulà", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("gratulada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("gratulats", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("gratuladas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("gratulescha!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("gratulai!", forms.getInflectionValues().get("imperativ2"));
        Assert.assertEquals("nu gratular!", forms.getInflectionValues().get("imperativ3"));
        Assert.assertEquals("nu gratularai!", forms.getInflectionValues().get("imperativ4"));
        Assert.assertEquals("ch'El/Ella gratulescha!", forms.getInflectionValues().get("imperativ5"));
        Assert.assertEquals("ch'Els/Ellas gratuleschan!", forms.getInflectionValues().get("imperativ6"));

        Assert.assertEquals("gratulond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1reflexiv() {
        forms =  generator.generateConjugation("1a", "as barricadar");

        Assert.assertEquals("as barricadar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("eu am barricadesch", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tü at barricadeschast", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella as barricadescha", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus ans barricadain", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus as barricadais\nvo as barricadaivat", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas as barricadeschan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("eu am barricadaiva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tü at barricadaivast", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella as barricadaiva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus ans barricadaivan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vo as barricadaivat", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas as barricadaivan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ch'eu am barricadescha", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("cha tü at barricadeschast", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella as barricadescha", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("cha nus ans barricadeschan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("cha vus as barricadeschat", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas as barricadeschan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("eu am barricadess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tü at barricadessast", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella as barricadess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus ans barricadessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus as barricadessat", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas as barricadessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("eu am barricadarà", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tü at barricadarast", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella as barricadarà", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus ans barricadaran", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus as barricadarat", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas as barricadaran", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("barricadà", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("barricadada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("barricadats", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("barricadadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("at barricadescha!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("as barricadai!", forms.getInflectionValues().get("imperativ2"));
        Assert.assertEquals("nu't barricadar!", forms.getInflectionValues().get("imperativ3"));
        Assert.assertEquals("nu's barricadarai!", forms.getInflectionValues().get("imperativ4"));
        Assert.assertEquals("ch'El/Ella as barricadescha!", forms.getInflectionValues().get("imperativ5"));
        Assert.assertEquals("ch'Els/Ellas as barricadeschan!", forms.getInflectionValues().get("imperativ6"));

        Assert.assertEquals("as barricadond", forms.getInflectionValues().get("gerundium"));
    }

    /*
    @Test
    public void testRule1reflexiv() {
        forms =  generator.generateConjugation("1", "as cuntantar");

        Assert.assertEquals("as cuntantar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("eu am cuntaint ", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tü at cuntaintast", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella as cuntainta", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus ans cuntantain ", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus as cuntantais/aivat", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas as cuntaintan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("eu am cuntantaiva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tü at cuntantaivast", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella as cuntantaiva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus ans cuntantaivan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus as cuntantaivat", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas as cuntantaivan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ch'eu am cuntainta", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("cha tü at cuntaintast", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella as cuntainta", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("cha nus ans cuntaintan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("cha vus as cuntaintat", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas as cuntaintan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("eu am cuntantess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tü at cuntantessast", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella as cuntantess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus ans cuntantessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus as cuntantessat", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas as cuntantessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("eu am cuntantarà", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tü at cuntantarast", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella as cuntantarà", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus ans cuntantaran", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus as cuntantarat", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas as cuntantaran", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("cuntantà", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("cuntantada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("cuntantats", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("cuntantadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("at cuntainta!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("as cuntantai!", forms.getInflectionValues().get("imperativ2"));
        Assert.assertEquals("nu't cuntantar!", forms.getInflectionValues().get("imperativ3"));
        Assert.assertEquals("nu's cuntantarai!", forms.getInflectionValues().get("imperativ4"));
        Assert.assertEquals("ch'El/Ella-as cuntainta!", forms.getInflectionValues().get("imperativ5"));
        Assert.assertEquals("ch'Els/Ellas-as cuntaintan!", forms.getInflectionValues().get("imperativ6"));

        Assert.assertEquals("as cuntantond", forms.getInflectionValues().get("gerundium"));
    }
    */

    @Test
    public void testRule1reflexivVowel() {
        forms =  generator.generateConjugation("1", "s'inachürar");

        Assert.assertEquals("s'inachürar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("eu m'inachür", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tü t'inachürast", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella s'inachüra", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus ans inachürain", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus s'inachürais\nvo s'inachüraivat", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas s'inachüran", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("eu m'inachüraiva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tü t'inachüraivast", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella s'inachüraiva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus ans inachüraivan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vo s'inachüraivat", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas s'inachüraivan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ch'eu m'inachüra", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("cha tü t'inachürast", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella s'inachüra", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("cha nus ans inachüran", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("cha vus s'inachürat", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas s'inachüran", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("eu m'inachüress", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tü t'inachüressast", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella s'inachüress", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus ans inachüressan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus s'inachüressat", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas s'inachüressan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("eu m'inachürarà", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tü t'inachürarast", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella s'inachürarà", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus ans inachüraran", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus s'inachürarat", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas s'inachüraran", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("inachürà", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("inachürada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("inachürats", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("inachüradas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("t'inachüra!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("s'inachürai!", forms.getInflectionValues().get("imperativ2"));
        Assert.assertEquals("nu t'inachürar!", forms.getInflectionValues().get("imperativ3"));
        Assert.assertEquals("nu s'inachürarai!", forms.getInflectionValues().get("imperativ4"));
        Assert.assertEquals("ch'El/Ella s'inachüra!", forms.getInflectionValues().get("imperativ5"));
        Assert.assertEquals("ch'Els/Ellas s'inachüran!", forms.getInflectionValues().get("imperativ6"));

        Assert.assertEquals("s'inachürond", forms.getInflectionValues().get("gerundium"));
    }

    /*
    @Test
    public void testRule1reflexivVowel() {
        forms =  generator.generateConjugation("1", "s'absantar");

        Assert.assertEquals("s'absantar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("eu m'absaint", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tü t'absaintast", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella s'absainta", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus ans absantain", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus s'absantais/aivat", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas s'absantan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("eu m'absantaiva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tü t'absantaivast", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella s'absantaiva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus ans absantaivan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus s'absantaivat", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas s'absantaivan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ch'eu m'absainta", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("cha tü t'absaintast", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella s'absainta", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("cha nus ans absaintan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("cha vus s'absaintat", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas s'absaintan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("eu m'absantess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tü t'absantessast", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella s'absantess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus ans absantessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus s'absantessat", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas s'absantessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("eu m'absantarà", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tü t'absantarast", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella s'absantarà", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus ans absantaran", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus s'absantarat", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas s'absantaran", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("absantà", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("absantada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("absantats", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("absantadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("t'absainta!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("s'absantai!", forms.getInflectionValues().get("imperativ2"));
        Assert.assertEquals("nu t'absantar!", forms.getInflectionValues().get("imperativ3"));
        Assert.assertEquals("nu s'absantarai!", forms.getInflectionValues().get("imperativ4"));
        Assert.assertEquals("ch'El/Ella s'absainta!", forms.getInflectionValues().get("imperativ5"));
        Assert.assertEquals("ch'Els/Ellas s'absaintan!", forms.getInflectionValues().get("imperativ6"));

        Assert.assertEquals("s' absantond", forms.getInflectionValues().get("gerundium"));
    }
    */

    @Test
    public void testRule2() {
        forms =  generator.generateConjugation("2", "giodair");

        Assert.assertEquals("giodair", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("eu giod", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tü giodast", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella gioda", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus giodain", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus giodais\nvo giodaivat", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas giodan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("eu giodaiva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tü giodaivast", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella giodaiva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus giodaivan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vo giodaivat", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas giodaivan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ch'eu gioda", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("cha tü giodast", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella gioda", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("cha nus giodan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("cha vus giodat", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas giodan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("eu giodess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tü giodessast", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella giodess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus giodessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus giodessat", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas giodessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("eu giodarà", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tü giodarast", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella giodarà", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus giodaran", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus giodarat", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas giodaran", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("giodü", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("giodüda", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("giodüts", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("giodüdas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("gioda!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("giodai!", forms.getInflectionValues().get("imperativ2"));
        Assert.assertEquals("nu giodair!", forms.getInflectionValues().get("imperativ3"));
        Assert.assertEquals("nu giodarai!", forms.getInflectionValues().get("imperativ4"));
        Assert.assertEquals("ch'El/Ella gioda!", forms.getInflectionValues().get("imperativ5"));
        Assert.assertEquals("ch'Els/Ellas giodan!", forms.getInflectionValues().get("imperativ6"));

        Assert.assertEquals("giodond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule3() {
        forms =  generator.generateConjugation("3", "vender");

        Assert.assertEquals("vender", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("eu vend", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tü vendast", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella venda", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus vendain", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus vendais\nvo vendaivat", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas vendan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("eu vendaiva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tü vendaivast", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella vendaiva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus vendaivan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vo vendaivat", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas vendaivan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ch'eu venda", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("cha tü vendast", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella venda", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("cha nus vendan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("cha vus vendat", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas vendan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("eu vendess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tü vendessast", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella vendess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus vendessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus vendessat", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas vendessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("eu vendarà", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tü vendarast", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vendarà", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vendaran", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vendarat", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vendaran", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("vendü", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("vendüda", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("vendüts", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("vendüdas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("venda!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("vendai!", forms.getInflectionValues().get("imperativ2"));
        Assert.assertEquals("nu vender!", forms.getInflectionValues().get("imperativ3"));
        Assert.assertEquals("nu vendarai!", forms.getInflectionValues().get("imperativ4"));
        Assert.assertEquals("ch'El/Ella venda!", forms.getInflectionValues().get("imperativ5"));
        Assert.assertEquals("ch'Els/Ellas vendan!", forms.getInflectionValues().get("imperativ6"));

        Assert.assertEquals("vendond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule4() {
        forms =  generator.generateConjugation("4", "cusir");

        Assert.assertEquals("cusir", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("eu cus", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tü cusast", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella cusa", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus cusin", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus cusis\nvus cusivat", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas cusan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("eu cusiva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tü cusivast", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella cusiva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus cusivan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus cusivat", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas cusivan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ch'eu cusa", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("cha tü cusast", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella cusa", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("cha nus cusan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("cha vus cusat", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas cusan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("eu cusiss", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tü cusissast", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella cusiss", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus cusissan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus cusissat", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas cusissan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("eu cusirà", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tü cusirast", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella cusirà", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus cusiran", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus cusirat", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas cusiran", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("cusi", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("cusida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("cusits", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("cusidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("cusa!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("cusi!", forms.getInflectionValues().get("imperativ2"));
        Assert.assertEquals("nu cusir!", forms.getInflectionValues().get("imperativ3"));
        Assert.assertEquals("nu cusirai!", forms.getInflectionValues().get("imperativ4"));
        Assert.assertEquals("ch'El/Ella cusa!", forms.getInflectionValues().get("imperativ5"));
        Assert.assertEquals("ch'Els/Ellas cusan!", forms.getInflectionValues().get("imperativ6"));

        Assert.assertEquals("cusind", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule4a() {
        forms =  generator.generateConjugation("4a", "admonir");

        Assert.assertEquals("admonir", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("eu admonisch", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tü admonischast", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella admonischa", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus admonin", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus admonis\nvus admonivat", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas admonischan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("eu admoniva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tü admonivast", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella admoniva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus admonivan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus admonivat", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas admonivan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ch'eu admonischa", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("cha tü admonischast", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella admonischa", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("cha nus admonischan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("cha vus admonischat", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas admonischan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("eu admoniss", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tü admonissast", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella admoniss", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus admonissan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus admonissat", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas admonissan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("eu admonirà", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tü admonirast", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella admonirà", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus admoniran", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus admonirat", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas admoniran", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("admoni", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("admonida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("admonits", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("admonidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("admonischa!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("admoni!", forms.getInflectionValues().get("imperativ2"));
        Assert.assertEquals("nun admonir!", forms.getInflectionValues().get("imperativ3"));
        Assert.assertEquals("nun admonirai!", forms.getInflectionValues().get("imperativ4"));
        Assert.assertEquals("ch'El/Ella admonischa!", forms.getInflectionValues().get("imperativ5"));
        Assert.assertEquals("ch'Els/Ellas admonischan!", forms.getInflectionValues().get("imperativ6"));

        Assert.assertEquals("admonind", forms.getInflectionValues().get("gerundium"));
    }
}
