package ch.pledarigrond.inflection.generation.sursilvan;

import ch.pledarigrond.inflection.model.InflectionResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class SursilvanConjugationTest {

    private static SursilvanConjugation generator;
    private InflectionResponse forms;

    @BeforeClass
    public static void initialize() {
        generator = new SursilvanConjugation();
    }

    @Test
    public void testRule1() {
        forms =  generator.generateConjugation("1", "gidar");

        Assert.assertEquals("gidar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu gidel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti gidas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella gida", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus gidein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus gideis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas gidan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu gidavel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti gidavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella gidava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus gidavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus gidavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas gidavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu gidi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti gidies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella gidi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus gidien\nche nus gideien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus gidies\nche vus gideies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas gidien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu gidavi", forms.getInflectionValues().get("conjunctivimperfectsing1"));
        Assert.assertEquals("che ti gidavies", forms.getInflectionValues().get("conjunctivimperfectsing2"));
        Assert.assertEquals("ch'el/ella gidavi", forms.getInflectionValues().get("conjunctivimperfectsing3"));
        Assert.assertEquals("che nus gidavien", forms.getInflectionValues().get("conjunctivimperfectplural1"));
        Assert.assertEquals("che vus gidavies", forms.getInflectionValues().get("conjunctivimperfectplural2"));
        Assert.assertEquals("ch'els/ellas gidavien", forms.getInflectionValues().get("conjunctivimperfectplural3"));

        Assert.assertEquals("jeu gidass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti gidasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella gidass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus gidassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus gidasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas gidassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu gidassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti gidassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella gidassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus gidassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus gidassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas gidassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a gidar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a gidar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a gidar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a gidar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a gidar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a gidar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("gidau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("gidada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("gidai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("gidadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("gida!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("gidei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("gidond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule2() {
        forms =  generator.generateConjugation("2", "temer");

        Assert.assertEquals("temer", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu temel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti temas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella tema", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus temein\nnus temin", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus temeis\nvus temis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas teman", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu temevel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti temevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella temeva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus temevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus temevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas temevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu temi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti temies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella temi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus temien\nche nus temeien\nche nus temîen", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus temies\nche vus temeies\nche vus temîes", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas temien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu temevi", forms.getInflectionValues().get("conjunctivimperfectsing1"));
        Assert.assertEquals("che ti temevies", forms.getInflectionValues().get("conjunctivimperfectsing2"));
        Assert.assertEquals("ch'el/ella temevi", forms.getInflectionValues().get("conjunctivimperfectsing3"));
        Assert.assertEquals("che nus temevien", forms.getInflectionValues().get("conjunctivimperfectplural1"));
        Assert.assertEquals("che vus temevies", forms.getInflectionValues().get("conjunctivimperfectplural2"));
        Assert.assertEquals("ch'els/ellas temevien", forms.getInflectionValues().get("conjunctivimperfectplural3"));

        Assert.assertEquals("jeu temess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti temesses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella temess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus temessen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus temesses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas temessen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu temessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti temessies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella temessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus temessien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus temessies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas temessien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a temer", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a temer", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a temer", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a temer", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a temer", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a temer", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("temiu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("temida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("temi", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("temidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("tema!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("temei!\ntemi!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("temend", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule3() {
        forms =  generator.generateConjugation("3", "vender");

        Assert.assertEquals("vender", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu vendel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti vendas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella venda", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus vendein\nnus vendin", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus vendeis\nvus vendis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas vendan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu vendevel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti vendevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella vendeva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus vendevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus vendevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas vendevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu vendi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti vendies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella vendi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus vendien\nche nus vendeien\nche nus vendîen", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus vendies\nche vus vendeies\nche vus vendîes", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas vendien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu vendevi", forms.getInflectionValues().get("conjunctivimperfectsing1"));
        Assert.assertEquals("che ti vendevies", forms.getInflectionValues().get("conjunctivimperfectsing2"));
        Assert.assertEquals("ch'el/ella vendevi", forms.getInflectionValues().get("conjunctivimperfectsing3"));
        Assert.assertEquals("che nus vendevien", forms.getInflectionValues().get("conjunctivimperfectplural1"));
        Assert.assertEquals("che vus vendevies", forms.getInflectionValues().get("conjunctivimperfectplural2"));
        Assert.assertEquals("ch'els/ellas vendevien", forms.getInflectionValues().get("conjunctivimperfectplural3"));

        Assert.assertEquals("jeu vendess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti vendesses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella vendess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus vendessen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus vendesses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas vendessen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu vendessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti vendessies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella vendessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus vendessien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus vendessies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas vendessien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a vender", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a vender", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a vender", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a vender", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a vender", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a vender", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("vendiu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("vendida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("vendi", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("vendidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("venda!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("vendei!\nvendi!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("vendend", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule4() {
        forms =  generator.generateConjugation("4", "sentir");

        Assert.assertEquals("sentir", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu sentel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti sentas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella senta", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus sentin", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus sentis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas sentan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu sentevel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti sentevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella senteva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus sentevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus sentevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas sentevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu senti", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti senties", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella senti", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus sentîen", forms.getInflectionValues().get("conjunctivplural1")); // DIFF
        Assert.assertEquals("che vus sentîes", forms.getInflectionValues().get("conjunctivplural2")); // DIFF
        Assert.assertEquals("ch'els/ellas sentien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu sentevi", forms.getInflectionValues().get("conjunctivimperfectsing1"));
        Assert.assertEquals("che ti sentevies", forms.getInflectionValues().get("conjunctivimperfectsing2"));
        Assert.assertEquals("ch'el/ella sentevi", forms.getInflectionValues().get("conjunctivimperfectsing3"));
        Assert.assertEquals("che nus sentevien", forms.getInflectionValues().get("conjunctivimperfectplural1"));
        Assert.assertEquals("che vus sentevies", forms.getInflectionValues().get("conjunctivimperfectplural2"));
        Assert.assertEquals("ch'els/ellas sentevien", forms.getInflectionValues().get("conjunctivimperfectplural3"));

        Assert.assertEquals("jeu sentess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti sentesses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella sentess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus sentessen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus sentesses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas sentessen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu sentessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti sentessies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella sentessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus sentessien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus sentessies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas sentessien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a sentir", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a sentir", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a sentir", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a sentir", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a sentir", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a sentir", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("sentiu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("sentida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("senti", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("sentidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("senta!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("senti!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("sentend", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1a() {
        forms =  generator.generateConjugation("1a", "gratular");

        Assert.assertEquals("gratular", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu gratuleschel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti gratuleschas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella gratulescha", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus gratulein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus gratuleis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas gratuleschan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu gratulavel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti gratulavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella gratulava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus gratulavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus gratulavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas gratulavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu gratuleschi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti gratuleschies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella gratuleschi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus gratuleschien\nche nus gratuleien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus gratuleschies\nche vus gratuleies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas gratuleschien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu gratulavi", forms.getInflectionValues().get("conjunctivimperfectsing1"));
        Assert.assertEquals("che ti gratulavies", forms.getInflectionValues().get("conjunctivimperfectsing2"));
        Assert.assertEquals("ch'el/ella gratulavi", forms.getInflectionValues().get("conjunctivimperfectsing3"));
        Assert.assertEquals("che nus gratulavien", forms.getInflectionValues().get("conjunctivimperfectplural1"));
        Assert.assertEquals("che vus gratulavies", forms.getInflectionValues().get("conjunctivimperfectplural2"));
        Assert.assertEquals("ch'els/ellas gratulavien", forms.getInflectionValues().get("conjunctivimperfectplural3"));

        Assert.assertEquals("jeu gratulass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti gratulasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella gratulass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus gratulassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus gratulasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas gratulassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu gratulassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti gratulassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella gratulassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus gratulassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus gratulassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas gratulassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a gratular", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a gratular", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a gratular", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a gratular", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a gratular", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a gratular", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("gratulau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("gratulada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("gratulai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("gratuladas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("gratulescha!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("gratulei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("gratulond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule4a() {
        forms =  generator.generateConjugation("4a", "capir");

        Assert.assertEquals("capir", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu capeschel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti capeschas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella capescha", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus capin", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus capis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas capeschan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu capevel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti capevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella capeva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus capevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus capevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas capevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu capeschi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti capeschies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella capeschi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus capeschien\nche nus capîen", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus capeschies\nche vus capîes", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas capeschien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu capevi", forms.getInflectionValues().get("conjunctivimperfectsing1"));
        Assert.assertEquals("che ti capevies", forms.getInflectionValues().get("conjunctivimperfectsing2"));
        Assert.assertEquals("ch'el/ella capevi", forms.getInflectionValues().get("conjunctivimperfectsing3"));
        Assert.assertEquals("che nus capevien", forms.getInflectionValues().get("conjunctivimperfectplural1"));
        Assert.assertEquals("che vus capevies", forms.getInflectionValues().get("conjunctivimperfectplural2"));
        Assert.assertEquals("ch'els/ellas capevien", forms.getInflectionValues().get("conjunctivimperfectplural3"));

        Assert.assertEquals("jeu capess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti capesses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella capess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus capessen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus capesses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas capessen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu capessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti capessies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella capessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus capessien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus capessies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas capessien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a capir", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a capir", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a capir", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a capir", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a capir", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a capir", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("capiu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("capida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("capi", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("capidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("capescha!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("capi!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("capend", forms.getInflectionValues().get("gerundium"));
    }
}
