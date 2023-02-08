package ch.pledarigrond.inflection.generation.rumantschgrischun;

import ch.pledarigrond.inflection.model.InflectionResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class RumantschGrischunConjugationTest {

    private static RumantschGrischunConjugation generator;
    private InflectionResponse forms;

    @BeforeClass
    public static void initialize() {
        generator = new RumantschGrischunConjugation();
    }

    @Test
    public void testRule1() {
        forms =  generator.generateConjugation("1", "chantar");

        Assert.assertEquals("chantar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jau chant", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti chantas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella chanta", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus chantain", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus chantais", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas chantan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jau chantava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti chantavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella chantava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus chantavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus chantavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas chantavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jau chantia", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti chantias", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella chantia", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus chantian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus chantias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas chantian", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("jau chantass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti chantassas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella chantass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus chantassan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus chantassas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas chantassan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jau vegn a chantar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a chantar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a chantar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a chantar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a chantar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a chantar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("chantà", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("chantada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("chantads", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("chantadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("chanta!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("chantai!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("chantond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule2() {
        forms =  generator.generateConjugation("2", "taschair");

        Assert.assertEquals("taschair", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jau tasch", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti taschas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella tascha", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus taschain", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus taschais", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas taschan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jau tascheva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti taschevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella tascheva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus taschevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus taschevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas taschevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jau taschia", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti taschias", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella taschia", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus taschian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus taschias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas taschian", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("jau taschess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti taschessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella taschess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus taschessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus taschessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas taschessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jau vegn a taschair", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a taschair", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a taschair", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a taschair", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a taschair", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a taschair", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("taschì", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("taschida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("taschids", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("taschidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("tascha!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("taschai!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("taschend", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule3() {
        forms =  generator.generateConjugation("3", "vender");

        Assert.assertEquals("vender", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jau vend", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti vendas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella venda", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus vendain", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus vendais", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas vendan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jau vendeva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti vendevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella vendeva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus vendevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus vendevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas vendevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jau vendia", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti vendias", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella vendia", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus vendian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus vendias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas vendian", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("jau vendess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti vendessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella vendess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus vendessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus vendessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas vendessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jau vegn a vender", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a vender", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a vender", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a vender", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a vender", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a vender", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("vendì", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("vendida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("vendids", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("vendidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("venda!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("vendai!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("vendend", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule4() {
        forms =  generator.generateConjugation("4", "partir");

        Assert.assertEquals("partir", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jau part", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti partas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella parta", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus partin", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus partis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas partan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jau partiva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti partivas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella partiva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus partivan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus partivas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas partivan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jau partia", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti partias", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella partia", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus partian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus partias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas partian", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("jau partiss", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti partissas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella partiss", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus partissan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus partissas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas partissan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jau vegn a partir", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a partir", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a partir", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a partir", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a partir", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a partir", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("partì", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("partida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("partids", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("partidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("parta!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("parti!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("partind", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule4Er() {
        forms =  generator.generateConjugation("4", "deponer");

        Assert.assertEquals("deponer", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jau depon", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti deponas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella depona", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus deponin", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus deponis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas deponan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jau deponiva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti deponivas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella deponiva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus deponivan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus deponivas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas deponivan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jau deponia", forms.getInflectionValues().get("conjunctivsing1")); // SIC
        Assert.assertEquals("che ti deponias", forms.getInflectionValues().get("conjunctivsing2")); // SIC
        Assert.assertEquals("ch'el/ella deponia", forms.getInflectionValues().get("conjunctivsing3")); // SIC
        Assert.assertEquals("che nus deponian", forms.getInflectionValues().get("conjunctivplural1")); // SIC
        Assert.assertEquals("che vus deponias", forms.getInflectionValues().get("conjunctivplural2")); // SIC
        Assert.assertEquals("ch'els/ellas deponian", forms.getInflectionValues().get("conjunctivplural3")); // SIC

        Assert.assertEquals("jau deponiss", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti deponissas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella deponiss", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus deponissan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus deponissas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas deponissan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jau vegn a deponer", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a deponer", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a deponer", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a deponer", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a deponer", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a deponer", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("deponì", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("deponida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("deponids", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("deponidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("depona!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("deponi!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("deponind", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule5() {
        forms =  generator.generateConjugation("5", "offrir");

        Assert.assertEquals("offrir", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jau offresch", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti offreschas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella offrescha", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus offrin", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus offris", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas offreschan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jau offriva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti offrivas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella offriva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus offrivan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus offrivas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas offrivan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jau offreschia", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti offreschias", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella offreschia", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus offreschian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus offreschias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas offreschian", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("jau offriss", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti offrissas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella offriss", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus offrissan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus offrissas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas offrissan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jau vegn ad offrir", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns ad offrir", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn ad offrir", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin ad offrir", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis ad offrir", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan ad offrir", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("offrì", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("offrida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("offrids", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("offridas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("offrescha!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("offri!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("offrind", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule6() {
        forms =  generator.generateConjugation("6", "telefonar");

        Assert.assertEquals("telefonar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jau telefonesch", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti telefoneschas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella telefonescha", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus telefonain", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus telefonais", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas telefoneschan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jau telefonava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti telefonavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella telefonava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus telefonavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus telefonavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas telefonavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jau telefoneschia", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti telefoneschias", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella telefoneschia", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus telefoneschian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus telefoneschias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas telefoneschian", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("jau telefonass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti telefonassas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella telefonass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus telefonassan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus telefonassas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas telefonassan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jau vegn a telefonar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a telefonar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a telefonar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a telefonar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a telefonar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a telefonar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("telefonà", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("telefonada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("telefonads", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("telefonadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("telefonescha!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("telefonai!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("telefonond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule7() {
        forms =  generator.generateConjugation("7", "liar");

        Assert.assertEquals("liar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jau liel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti lias", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella lia", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus liain", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus liais", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas lian", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jau liava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti liavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella liava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus liavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus liavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas liavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jau liia", forms.getInflectionValues().get("conjunctivsing1")); // SIC
        Assert.assertEquals("che ti liias", forms.getInflectionValues().get("conjunctivsing2")); // SIC
        Assert.assertEquals("ch'el/ella liia", forms.getInflectionValues().get("conjunctivsing3")); // SIC
        Assert.assertEquals("che nus liian", forms.getInflectionValues().get("conjunctivplural1")); // SIC
        Assert.assertEquals("che vus liias", forms.getInflectionValues().get("conjunctivplural2")); // SIC
        Assert.assertEquals("ch'els/ellas liian", forms.getInflectionValues().get("conjunctivplural3")); // SIC

        Assert.assertEquals("jau liass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti liassas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella liass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus liassan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus liassas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas liassan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jau vegn a liar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a liar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a liar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a liar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a liar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a liar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("lià", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("liada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("liads", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("liadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("lia!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("liai!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("liond", forms.getInflectionValues().get("gerundium"));
    }
}
