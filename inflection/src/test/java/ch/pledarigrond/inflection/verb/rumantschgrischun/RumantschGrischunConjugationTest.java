package ch.pledarigrond.inflection.verb.rumantschgrischun;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.verb.rumantschgrischun.RumantschGrischunConjugation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RumantschGrischunConjugationTest {

    private static RumantschGrischunConjugation generator;
    private InflectionDto forms;

    @BeforeAll
    public static void initialize() {
        generator = new RumantschGrischunConjugation();
    }

    @Test
    public void testRule1() {
        forms =  generator.generateConjugation("1", "chantar");

        assertEquals("chantar", forms.getVerb().getInfinitiv());

        assertEquals("jau chant", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti chantas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella chanta", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus chantain", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus chantais", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas chantan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jau chantava", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti chantavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella chantava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus chantavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus chantavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas chantavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jau chantia", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti chantias", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella chantia", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus chantian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus chantias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas chantian", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("jau chantass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti chantassas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella chantass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus chantassan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus chantassas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas chantassan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jau vegn a chantar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a chantar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a chantar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a chantar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a chantar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a chantar", forms.getVerb().getFutur().getPlural3());

        assertEquals("chantà", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("chantada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("chantads", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("chantadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("chanta!", forms.getVerb().getImperativ().getSingular());
        assertEquals("chantai!", forms.getVerb().getImperativ().getPlural());

        assertEquals("chantond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule2() {
        forms =  generator.generateConjugation("2", "taschair");

        assertEquals("taschair", forms.getVerb().getInfinitiv());

        assertEquals("jau tasch", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti taschas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella tascha", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus taschain", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus taschais", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas taschan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jau tascheva", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti taschevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella tascheva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus taschevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus taschevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas taschevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jau taschia", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti taschias", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella taschia", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus taschian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus taschias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas taschian", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("jau taschess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti taschessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella taschess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus taschessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus taschessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas taschessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jau vegn a taschair", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a taschair", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a taschair", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a taschair", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a taschair", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a taschair", forms.getVerb().getFutur().getPlural3());

        assertEquals("taschì", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("taschida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("taschids", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("taschidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("tascha!", forms.getVerb().getImperativ().getSingular());
        assertEquals("taschai!", forms.getVerb().getImperativ().getPlural());

        assertEquals("taschend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3() {
        forms =  generator.generateConjugation("3", "vender");

        assertEquals("vender", forms.getVerb().getInfinitiv());

        assertEquals("jau vend", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti vendas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella venda", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus vendain", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus vendais", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas vendan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jau vendeva", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti vendevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella vendeva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus vendevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus vendevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas vendevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jau vendia", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti vendias", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella vendia", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus vendian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus vendias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas vendian", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("jau vendess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti vendessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella vendess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus vendessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus vendessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas vendessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jau vegn a vender", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a vender", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a vender", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a vender", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a vender", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a vender", forms.getVerb().getFutur().getPlural3());

        assertEquals("vendì", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("vendida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("vendids", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("vendidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("venda!", forms.getVerb().getImperativ().getSingular());
        assertEquals("vendai!", forms.getVerb().getImperativ().getPlural());

        assertEquals("vendend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4() {
        forms =  generator.generateConjugation("4", "partir");

        assertEquals("partir", forms.getVerb().getInfinitiv());

        assertEquals("jau part", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti partas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella parta", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus partin", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus partis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas partan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jau partiva", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti partivas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella partiva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus partivan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus partivas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas partivan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jau partia", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti partias", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella partia", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus partian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus partias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas partian", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("jau partiss", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti partissas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella partiss", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus partissan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus partissas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas partissan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jau vegn a partir", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a partir", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a partir", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a partir", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a partir", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a partir", forms.getVerb().getFutur().getPlural3());

        assertEquals("partì", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("partida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("partids", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("partidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("parta!", forms.getVerb().getImperativ().getSingular());
        assertEquals("parti!", forms.getVerb().getImperativ().getPlural());

        assertEquals("partind", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4Er() {
        forms =  generator.generateConjugation("4", "deponer");

        assertEquals("deponer", forms.getVerb().getInfinitiv());

        assertEquals("jau depon", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti deponas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella depona", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus deponin", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus deponis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas deponan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jau deponiva", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti deponivas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella deponiva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus deponivan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus deponivas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas deponivan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jau deponia", forms.getVerb().getConjunctiv().getSing1()); // SIC
        assertEquals("che ti deponias", forms.getVerb().getConjunctiv().getSing2()); // SIC
        assertEquals("ch'el/ella deponia", forms.getVerb().getConjunctiv().getSing3()); // SIC
        assertEquals("che nus deponian", forms.getVerb().getConjunctiv().getPlural1()); // SIC
        assertEquals("che vus deponias", forms.getVerb().getConjunctiv().getPlural2()); // SIC
        assertEquals("ch'els/ellas deponian", forms.getVerb().getConjunctiv().getPlural3()); // SIC

        assertEquals("jau deponiss", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti deponissas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella deponiss", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus deponissan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus deponissas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas deponissan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jau vegn a deponer", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a deponer", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a deponer", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a deponer", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a deponer", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a deponer", forms.getVerb().getFutur().getPlural3());

        assertEquals("deponì", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("deponida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("deponids", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("deponidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("depona!", forms.getVerb().getImperativ().getSingular());
        assertEquals("deponi!", forms.getVerb().getImperativ().getPlural());

        assertEquals("deponind", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule5() {
        forms =  generator.generateConjugation("5", "offrir");

        assertEquals("offrir", forms.getVerb().getInfinitiv());

        assertEquals("jau offresch", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti offreschas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella offrescha", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus offrin", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus offris", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas offreschan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jau offriva", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti offrivas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella offriva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus offrivan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus offrivas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas offrivan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jau offreschia", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti offreschias", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella offreschia", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus offreschian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus offreschias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas offreschian", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("jau offriss", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti offrissas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella offriss", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus offrissan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus offrissas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas offrissan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jau vegn ad offrir", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns ad offrir", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn ad offrir", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin ad offrir", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis ad offrir", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan ad offrir", forms.getVerb().getFutur().getPlural3());

        assertEquals("offrì", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("offrida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("offrids", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("offridas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("offrescha!", forms.getVerb().getImperativ().getSingular());
        assertEquals("offri!", forms.getVerb().getImperativ().getPlural());

        assertEquals("offrind", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule6() {
        forms =  generator.generateConjugation("6", "telefonar");

        assertEquals("telefonar", forms.getVerb().getInfinitiv());

        assertEquals("jau telefonesch", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti telefoneschas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella telefonescha", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus telefonain", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus telefonais", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas telefoneschan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jau telefonava", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti telefonavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella telefonava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus telefonavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus telefonavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas telefonavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jau telefoneschia", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti telefoneschias", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella telefoneschia", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus telefoneschian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus telefoneschias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas telefoneschian", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("jau telefonass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti telefonassas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella telefonass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus telefonassan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus telefonassas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas telefonassan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jau vegn a telefonar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a telefonar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a telefonar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a telefonar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a telefonar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a telefonar", forms.getVerb().getFutur().getPlural3());

        assertEquals("telefonà", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("telefonada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("telefonads", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("telefonadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("telefonescha!", forms.getVerb().getImperativ().getSingular());
        assertEquals("telefonai!", forms.getVerb().getImperativ().getPlural());

        assertEquals("telefonond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule7() {
        forms =  generator.generateConjugation("7", "liar");

        assertEquals("liar", forms.getVerb().getInfinitiv());

        assertEquals("jau liel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti lias", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella lia", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus liain", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus liais", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas lian", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jau liava", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti liavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella liava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus liavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus liavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas liavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jau liia", forms.getVerb().getConjunctiv().getSing1()); // SIC
        assertEquals("che ti liias", forms.getVerb().getConjunctiv().getSing2()); // SIC
        assertEquals("ch'el/ella liia", forms.getVerb().getConjunctiv().getSing3()); // SIC
        assertEquals("che nus liian", forms.getVerb().getConjunctiv().getPlural1()); // SIC
        assertEquals("che vus liias", forms.getVerb().getConjunctiv().getPlural2()); // SIC
        assertEquals("ch'els/ellas liian", forms.getVerb().getConjunctiv().getPlural3()); // SIC

        assertEquals("jau liass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti liassas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella liass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus liassan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus liassas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas liassan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jau vegn a liar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a liar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a liar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a liar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a liar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a liar", forms.getVerb().getFutur().getPlural3());

        assertEquals("lià", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("liada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("liads", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("liadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("lia!", forms.getVerb().getImperativ().getSingular());
        assertEquals("liai!", forms.getVerb().getImperativ().getPlural());

        assertEquals("liond", forms.getVerb().getGerundium());
    }
}
