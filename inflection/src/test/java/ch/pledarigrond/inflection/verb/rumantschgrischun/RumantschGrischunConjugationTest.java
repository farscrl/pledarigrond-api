package ch.pledarigrond.inflection.verb.rumantschgrischun;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.verb.rumantschgrischun.RumantschGrischunConjugation;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class RumantschGrischunConjugationTest {

    private static RumantschGrischunConjugation generator;
    private InflectionDto forms;

    @BeforeClass
    public static void initialize() {
        generator = new RumantschGrischunConjugation();
    }

    @Test
    public void testRule1() {
        forms =  generator.generateConjugation("1", "chantar");

        Assert.assertEquals("chantar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jau chant", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti chantas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella chanta", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus chantain", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus chantais", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas chantan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jau chantava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti chantavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella chantava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus chantavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus chantavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas chantavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jau chantia", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti chantias", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella chantia", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus chantian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus chantias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas chantian", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("jau chantass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti chantassas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella chantass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus chantassan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus chantassas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas chantassan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jau vegn a chantar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a chantar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a chantar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a chantar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a chantar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a chantar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("chantà", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("chantada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("chantads", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("chantadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("chanta!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("chantai!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("chantond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule2() {
        forms =  generator.generateConjugation("2", "taschair");

        Assert.assertEquals("taschair", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jau tasch", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti taschas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella tascha", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus taschain", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus taschais", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas taschan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jau tascheva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti taschevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella tascheva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus taschevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus taschevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas taschevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jau taschia", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti taschias", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella taschia", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus taschian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus taschias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas taschian", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("jau taschess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti taschessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella taschess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus taschessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus taschessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas taschessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jau vegn a taschair", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a taschair", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a taschair", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a taschair", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a taschair", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a taschair", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("taschì", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("taschida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("taschids", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("taschidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("tascha!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("taschai!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("taschend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3() {
        forms =  generator.generateConjugation("3", "vender");

        Assert.assertEquals("vender", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jau vend", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti vendas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella venda", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus vendain", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus vendais", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas vendan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jau vendeva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti vendevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella vendeva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus vendevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus vendevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas vendevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jau vendia", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti vendias", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella vendia", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus vendian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus vendias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas vendian", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("jau vendess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti vendessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella vendess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus vendessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus vendessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas vendessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jau vegn a vender", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a vender", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a vender", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a vender", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a vender", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a vender", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("vendì", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("vendida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("vendids", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("vendidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("venda!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("vendai!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("vendend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4() {
        forms =  generator.generateConjugation("4", "partir");

        Assert.assertEquals("partir", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jau part", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti partas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella parta", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus partin", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus partis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas partan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jau partiva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti partivas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella partiva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus partivan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus partivas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas partivan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jau partia", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti partias", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella partia", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus partian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus partias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas partian", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("jau partiss", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti partissas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella partiss", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus partissan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus partissas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas partissan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jau vegn a partir", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a partir", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a partir", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a partir", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a partir", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a partir", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("partì", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("partida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("partids", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("partidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("parta!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("parti!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("partind", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4Er() {
        forms =  generator.generateConjugation("4", "deponer");

        Assert.assertEquals("deponer", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jau depon", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti deponas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella depona", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus deponin", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus deponis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas deponan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jau deponiva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti deponivas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella deponiva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus deponivan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus deponivas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas deponivan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jau deponia", forms.getVerb().getConjunctiv().getSing1()); // SIC
        Assert.assertEquals("che ti deponias", forms.getVerb().getConjunctiv().getSing2()); // SIC
        Assert.assertEquals("ch'el/ella deponia", forms.getVerb().getConjunctiv().getSing3()); // SIC
        Assert.assertEquals("che nus deponian", forms.getVerb().getConjunctiv().getPlural1()); // SIC
        Assert.assertEquals("che vus deponias", forms.getVerb().getConjunctiv().getPlural2()); // SIC
        Assert.assertEquals("ch'els/ellas deponian", forms.getVerb().getConjunctiv().getPlural3()); // SIC

        Assert.assertEquals("jau deponiss", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti deponissas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella deponiss", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus deponissan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus deponissas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas deponissan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jau vegn a deponer", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a deponer", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a deponer", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a deponer", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a deponer", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a deponer", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("deponì", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("deponida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("deponids", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("deponidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("depona!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("deponi!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("deponind", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule5() {
        forms =  generator.generateConjugation("5", "offrir");

        Assert.assertEquals("offrir", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jau offresch", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti offreschas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella offrescha", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus offrin", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus offris", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas offreschan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jau offriva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti offrivas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella offriva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus offrivan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus offrivas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas offrivan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jau offreschia", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti offreschias", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella offreschia", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus offreschian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus offreschias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas offreschian", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("jau offriss", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti offrissas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella offriss", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus offrissan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus offrissas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas offrissan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jau vegn ad offrir", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns ad offrir", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn ad offrir", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin ad offrir", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis ad offrir", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan ad offrir", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("offrì", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("offrida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("offrids", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("offridas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("offrescha!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("offri!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("offrind", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule6() {
        forms =  generator.generateConjugation("6", "telefonar");

        Assert.assertEquals("telefonar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jau telefonesch", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti telefoneschas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella telefonescha", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus telefonain", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus telefonais", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas telefoneschan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jau telefonava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti telefonavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella telefonava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus telefonavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus telefonavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas telefonavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jau telefoneschia", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti telefoneschias", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella telefoneschia", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus telefoneschian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus telefoneschias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas telefoneschian", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("jau telefonass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti telefonassas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella telefonass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus telefonassan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus telefonassas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas telefonassan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jau vegn a telefonar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a telefonar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a telefonar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a telefonar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a telefonar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a telefonar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("telefonà", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("telefonada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("telefonads", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("telefonadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("telefonescha!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("telefonai!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("telefonond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule7() {
        forms =  generator.generateConjugation("7", "liar");

        Assert.assertEquals("liar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jau liel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti lias", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella lia", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus liain", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus liais", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas lian", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jau liava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti liavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella liava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus liavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus liavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas liavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jau liia", forms.getVerb().getConjunctiv().getSing1()); // SIC
        Assert.assertEquals("che ti liias", forms.getVerb().getConjunctiv().getSing2()); // SIC
        Assert.assertEquals("ch'el/ella liia", forms.getVerb().getConjunctiv().getSing3()); // SIC
        Assert.assertEquals("che nus liian", forms.getVerb().getConjunctiv().getPlural1()); // SIC
        Assert.assertEquals("che vus liias", forms.getVerb().getConjunctiv().getPlural2()); // SIC
        Assert.assertEquals("ch'els/ellas liian", forms.getVerb().getConjunctiv().getPlural3()); // SIC

        Assert.assertEquals("jau liass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti liassas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella liass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus liassan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus liassas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas liassan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jau vegn a liar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a liar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a liar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a liar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a liar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a liar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("lià", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("liada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("liads", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("liadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("lia!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("liai!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("liond", forms.getVerb().getGerundium());
    }
}
