package ch.pledarigrond.inflection.verb.surmiran;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.verb.surmiran.SurmiranConjugation;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class SurmiranConjugationTest {

    private static SurmiranConjugation generator;
    private InflectionDto forms;

    @BeforeClass
    public static void initialize() {
        generator = new SurmiranConjugation();
    }

    @Test
    public void testRule1() {
        forms =  generator.generateConjugation("1", "cantar");

        Assert.assertEquals("cantar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia cant", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te cantas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella canta", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous cantagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous cantez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas cantan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia cantava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te cantavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella cantava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous cantavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous cantavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas cantavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia canta", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te cantas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella canta", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous cantan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous cantas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas cantan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia cantess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te cantessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella cantess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous cantessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous cantessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas cantessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia cantaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te cantarossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella cantaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous cantaron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous cantarossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas cantaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("canto", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("cantada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("cantos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("cantadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("canta!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("cante!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("cantond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1ReflexiveModRoot() {
        forms = generator.generateConjugation("1", "sa magler");

        Assert.assertEquals("sa magler", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia ma magl", forms.getVerb().getPreschent().getSing1());
    }

    @Test
    public void testRule1a() {
        forms =  generator.generateConjugation("1a", "tadlar");

        Assert.assertEquals("tadlar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia tedl", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te tedlas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella tedla", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous tadlagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous tadlez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas tedlan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia tadlava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te tadlavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella tadlava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous tadlavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous tadlavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas tadlavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia tedla", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te tedlas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella tedla", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous tedlan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous tedlas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas tedlan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia tadless", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te tadlessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella tadless", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous tadlessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous tadlessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas tadlessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia tadlaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te tadlarossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella tadlaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous tadlaron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous tadlarossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas tadlaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("tadlo", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("tadlada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("tadlos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("tadladas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("tedla!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("tadle!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("tadlond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1b() {
        forms =  generator.generateConjugation("1b", "fimar");

        Assert.assertEquals("fimar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia fem", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te femas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella fema", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous fimagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous fimez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas feman", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia fimava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te fimavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella fimava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous fimavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous fimavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas fimavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia fema", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te femas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella fema", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous feman", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous femas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas feman", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia fimess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te fimessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella fimess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous fimessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous fimessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas fimessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia fimaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te fimarossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella fimaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous fimaron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous fimarossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas fimaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("fimo", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("fimada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("fimos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("fimadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("fema!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("fime!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("fimond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1c() {
        forms =  generator.generateConjugation("1c", "pasar");

        Assert.assertEquals("pasar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia peis", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te peisas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella peisa", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous pasagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous pasez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas peisan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia pasava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te pasavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella pasava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous pasavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous pasavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas pasavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia peisa", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te peisas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella peisa", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous peisan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous peisas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas peisan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia pasess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te pasessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella pasess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous pasessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous pasessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas pasessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia pasaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te pasarossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella pasaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous pasaron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous pasarossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas pasaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("paso", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("pasada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("pasos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("pasadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("peisa!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("pase!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("pasond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1d() {
        forms =  generator.generateConjugation("1d", "midar");

        Assert.assertEquals("midar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia meid", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te meidas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella meida", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous midagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous midez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas meidan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia midava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te midavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella midava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous midavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous midavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas midavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia meida", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te meidas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella meida", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous meidan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous meidas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas meidan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia midess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te midessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella midess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous midessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous midessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas midessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia midaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te midarossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella midaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous midaron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous midarossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas midaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("mido", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("midada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("midos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("midadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("meida!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("mide!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("midond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1e() {
        forms =  generator.generateConjugation("1e", "passantar");

        Assert.assertEquals("passantar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia passaint", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te passaintas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella passainta", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous passantagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous passantez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas passaintan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia passantava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te passantavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella passantava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous passantavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous passantavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas passantavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia passainta", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te passaintas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella passainta", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous passaintan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous passaintas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas passaintan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia passantess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te passantessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella passantess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous passantessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous passantessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas passantessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia passantaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te passantarossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella passantaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous passantaron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous passantarossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas passantaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("passanto", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("passantada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("passantos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("passantadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("passainta!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("passante!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("passantond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1f() {
        forms =  generator.generateConjugation("1f", "irtar");

        Assert.assertEquals("irtar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia iert", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te iertas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella ierta", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous irtagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous irtez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas iertan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia irtava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te irtavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella irtava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous irtavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous irtavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas irtavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia ierta", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te iertas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella ierta", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous iertan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous iertas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas iertan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia irtess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te irtessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella irtess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous irtessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous irtessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas irtessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia irtaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te irtarossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella irtaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous irtaron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous irtarossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas irtaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("irto", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("irtada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("irtos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("irtadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("ierta!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("irte!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("irtond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1g() {
        forms =  generator.generateConjugation("1g", "ampruar");

        Assert.assertEquals("ampruar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia amprov", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te amprovas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella amprova", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous ampruagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous ampruez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas amprovan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia ampruava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te ampruavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella ampruava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous ampruavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous ampruavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas ampruavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia amprova", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te amprovas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella amprova", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous amprovan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous amprovas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas amprovan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia ampruess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te ampruessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella ampruess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous ampruessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous ampruessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas ampruessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia ampruaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te ampruarossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella ampruaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous ampruaron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous ampruarossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas ampruaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("ampruo", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("ampruada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("ampruos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("ampruadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("amprova!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("amprue!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("ampruond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1h() {
        forms =  generator.generateConjugation("1h", "sfardar");

        Assert.assertEquals("sfardar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia sfreid", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te sfreidas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella sfreida", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous sfardagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous sfardez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas sfreidan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia sfardava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te sfardavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella sfardava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous sfardavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous sfardavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas sfardavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia sfreida", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te sfreidas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella sfreida", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous sfreidan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous sfreidas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas sfreidan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia sfardess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te sfardessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella sfardess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous sfardessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous sfardessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas sfardessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia sfardaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te sfardarossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella sfardaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous sfardaron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous sfardarossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas sfardaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("sfardo", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("sfardada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("sfardos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("sfardadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("sfreida!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("sfarde!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("sfardond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1i() {
        forms =  generator.generateConjugation("1i", "sgarmar");

        Assert.assertEquals("sgarmar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia sgrom", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te sgromas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella sgroma", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous sgarmagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous sgarmez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas sgroman", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia sgarmava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te sgarmavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella sgarmava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous sgarmavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous sgarmavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas sgarmavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia sgroma", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te sgromas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella sgroma", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous sgroman", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous sgromas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas sgroman", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia sgarmess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te sgarmessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella sgarmess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous sgarmessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous sgarmessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas sgarmessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia sgarmaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te sgarmarossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella sgarmaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous sgarmaron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous sgarmarossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas sgarmaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("sgarmo", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("sgarmada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("sgarmos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("sgarmadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("sgroma!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("sgarme!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("sgarmond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1j() {
        forms =  generator.generateConjugation("1j", "splanar");

        Assert.assertEquals("splanar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia splang", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te splangas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella splanga", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous splanagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous splanez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas splangan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia splanava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te splanavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella splanava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous splanavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous splanavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas splanavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia splanga", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te splangas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella splanga", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous splangan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous splangas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas splangan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia splaness", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te splanessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella splaness", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous splanessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous splanessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas splanessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia splanaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te splanarossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella splanaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous splanaron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous splanarossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas splanaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("splano", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("splanada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("splanos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("splanadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("splanga!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("splane!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("splanond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1k() {
        forms =  generator.generateConjugation("1k", "perdunar");

        Assert.assertEquals("perdunar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia perdung", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te perdungas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella perdunga", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous perdunagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous perdunez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas perdungan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia perdunava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te perdunavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella perdunava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous perdunavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous perdunavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas perdunavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia perdunga", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te perdungas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella perdunga", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous perdungan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous perdungas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas perdungan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia perduness", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te perdunessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella perduness", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous perdunessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous perdunessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas perdunessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia perdunaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te perdunarossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella perdunaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous perdunaron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous perdunarossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas perdunaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("perduno", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("perdunada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("perdunos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("perdunadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("perdunga!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("perdune!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("perdunond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1l() {
        forms =  generator.generateConjugation("1l", "manar");

        Assert.assertEquals("manar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia magn", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te magnas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella magna", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous managn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous manez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas magnan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia manava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te manavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella manava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous manavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous manavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas manavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia magna", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te magnas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella magna", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous magnan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous magnas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas magnan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia maness", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te manessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella maness", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous manessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous manessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas manessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia manaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te manarossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella manaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous manaron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous manarossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas manaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("mano", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("manada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("manos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("manadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("magna!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("mane!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("manond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1m() {
        forms =  generator.generateConjugation("1m", "pinar");

        Assert.assertEquals("pinar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia pegn", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te pegnas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella pegna", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous pinagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous pinez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas pegnan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia pinava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te pinavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella pinava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous pinavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous pinavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas pinavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia pegna", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te pegnas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella pegna", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous pegnan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous pegnas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas pegnan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia piness", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te pinessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella piness", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous pinessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous pinessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas pinessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia pinaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te pinarossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella pinaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous pinaron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous pinarossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas pinaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("pino", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("pinada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("pinos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("pinadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("pegna!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("pine!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("pinond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1n() {
        forms =  generator.generateConjugation("1n", "schinar");

        Assert.assertEquals("schinar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia schign", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te schignas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella schigna", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous schinagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous schinez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas schignan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia schinava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te schinavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella schinava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous schinavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous schinavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas schinavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia schigna", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te schignas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella schigna", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous schignan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous schignas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas schignan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia schiness", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te schinessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella schiness", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous schinessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous schinessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas schinessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia schinaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te schinarossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella schinaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous schinaron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous schinarossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas schinaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("schino", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("schinada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("schinos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("schinadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("schigna!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("schine!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("schinond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1o() {
        forms =  generator.generateConjugation("1o", "anganar");

        Assert.assertEquals("anganar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia angion", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te angionas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella angiona", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous anganagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous anganez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas angionan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia anganava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te anganavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella anganava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous anganavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous anganavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas anganavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia angiona", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te angionas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella angiona", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous angionan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous angionas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas angionan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia anganess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te anganessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella anganess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous anganessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous anganessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas anganessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia anganaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te anganarossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella anganaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous anganaron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous anganarossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas anganaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("angano", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("anganada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("anganos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("anganadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("angiona!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("angane!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("anganond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1p() {
        forms =  generator.generateConjugation("1p", "scanar");

        Assert.assertEquals("scanar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia stgon", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te stgonas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella stgona", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous scanagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous scanez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas stgonan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia scanava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te scanavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella scanava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous scanavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous scanavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas scanavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia stgona", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te stgonas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella stgona", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous stgonan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous stgonas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas stgonan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia scaness", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te scanessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella scaness", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous scanessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous scanessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas scanessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia scanaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te scanarossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella scanaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous scanaron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous scanarossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas scanaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("scano", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("scanada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("scanos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("scanadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("stgona!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("scane!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("scanond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1q() {
        forms =  generator.generateConjugation("1q", "ruschanar");

        Assert.assertEquals("ruschanar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia raschung", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te raschungas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella raschunga", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous ruschanagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous ruschanez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas raschungan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia ruschanava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te ruschanavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella ruschanava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous ruschanavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous ruschanavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas ruschanavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia raschunga", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te raschungas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella raschunga", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous raschungan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous raschungas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas raschungan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia ruschaness", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te ruschanessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella ruschaness", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous ruschanessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous ruschanessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas ruschanessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia ruschanaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te ruschanarossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella ruschanaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous ruschanaron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous ruschanarossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas ruschanaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("ruschano", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("ruschanada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("ruschanos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("ruschanadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("raschunga!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("ruschane!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("ruschanond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1r() {
        forms =  generator.generateConjugation("1r", "declarar");

        Assert.assertEquals("declarar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia dacler", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te dacleras", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella daclera", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous declaragn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous declarez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas dacleran", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia declarava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te declaravas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella declarava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous declaravan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous declaravas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas declaravan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia daclera", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te dacleras", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella daclera", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous dacleran", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous dacleras", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas dacleran", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia declaress", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te declaressas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella declaress", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous declaressan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous declaressas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas declaressan", forms.getVerb().getCundiziunal().getPlural3());

        /* Exeption in ending (double syllable is removed)
        Assert.assertEquals("ia declaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te declarossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella declaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous declaron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous declarossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas declaron", forms.getVerb().getFutur().getPlural3());
        */

        Assert.assertEquals("declaro", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("declarada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("declaros", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("declaradas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("daclera!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("declare!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("declarond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1s() {
        forms =  generator.generateConjugation("1s", "preschentar");

        Assert.assertEquals("preschentar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia preschaint", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te preschaintas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella preschainta", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous preschentagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous preschentez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas preschaintan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia preschentava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te preschentavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella preschentava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous preschentavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous preschentavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas preschentavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia preschainta", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te preschaintas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella preschainta", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous preschaintan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous preschaintas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas preschaintan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia preschentess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te preschentessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella preschentess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous preschentessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous preschentessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas preschentessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia preschentaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te preschentarossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella preschentaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous preschentaron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous preschentarossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas preschentaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("preschento", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("preschentada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("preschentos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("preschentadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("preschainta!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("preschente!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("preschentond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1t() {
        forms =  generator.generateConjugation("1t", "clamar");

        Assert.assertEquals("clamar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia clom", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te clomas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella cloma", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous clamagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous clamez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas cloman", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia clamava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te clamavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella clamava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous clamavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous clamavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas clamavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia cloma", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te clomas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella cloma", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous cloman", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous clomas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas cloman", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia clamess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te clamessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella clamess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous clamessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous clamessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas clamessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia clamaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te clamarossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella clamaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous clamaron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous clamarossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas clamaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("clamo", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("clamada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("clamos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("clamadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("cloma!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("clame!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("clamond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1u() {
        forms =  generator.generateConjugation("1u", "crudar");

        Assert.assertEquals("crudar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia crod", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te crodas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella croda", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous crudagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous crudez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas crodan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia crudava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te crudavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella crudava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous crudavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous crudavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas crudavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia croda", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te crodas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella croda", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous crodan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous crodas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas crodan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia crudess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te crudessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella crudess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous crudessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous crudessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas crudessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia crudaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te crudarossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella crudaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous crudaron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous crudarossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas crudaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("crudo", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("crudada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("crudos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("crudadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("croda!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("crude!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("crudond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule2() {
        forms =  generator.generateConjugation("2", "lascher");

        Assert.assertEquals("lascher", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia lasch", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te laschas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella lascha", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous laschagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous laschez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas laschan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia lascheva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te laschevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella lascheva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous laschevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous laschevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas laschevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia lascha", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te laschas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella lascha", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous laschan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous laschas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas laschan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia laschess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te laschessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella laschess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous laschessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous laschessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas laschessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia lascharo", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te lascharossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella lascharo", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous lascharon", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous lascharossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas lascharon", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("laschea", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("lascheda", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("lascheas", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("laschedas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("lascha!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("lasche!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("laschond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule2Gl() {
        forms =  generator.generateConjugation("2", "magler");

        Assert.assertEquals("magler", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia magl", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te maglias", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella maglia", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous magliagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous maglez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas maglian", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia magleva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te maglevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella magleva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous maglevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous maglevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas maglevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia maglia", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te maglias", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella maglia", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous maglian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous maglias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas maglian", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia magless", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te maglessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella magless", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous maglessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous maglessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas maglessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia magliaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te magliarossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella magliaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous magliaron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous magliarossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas magliaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("maglea", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("magleda", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("magleas", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("magledas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("maglia!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("magle!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("magliond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule2a() {
        forms =  generator.generateConjugation("2a", "manager");

        Assert.assertEquals("manager", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia manetg", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te manegias", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella manegia", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous managiagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous managez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas manegian", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia manageva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te managevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella manageva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous managevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous managevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas managevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia manegia", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te manegias", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella manegia", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous manegian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous manegias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas manegian", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia managess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te managessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella managess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous managessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous managessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas managessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia managiaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te managiarossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella managiaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous managiaron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous managiarossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas managiaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("managea", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("manageda", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("manageas", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("managedas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("manegia!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("manage!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("managiond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule2b() {
        forms =  generator.generateConjugation("2b", "macager");

        Assert.assertEquals("macager", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia machetg", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te machegias", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella machegia", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous macagiagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous macagez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas machegian", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia macageva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te macagevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella macageva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous macagevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous macagevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas macagevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia machegia", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te machegias", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella machegia", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous machegian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous machegias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas machegian", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia macagess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te macagessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella macagess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous macagessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous macagessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas macagessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia macagiaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te macagiarossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella macagiaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous macagiaron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous macagiarossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas macagiaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("macagea", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("macageda", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("macageas", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("macagedas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("machegia!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("macage!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("macagiond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule2c() {
        forms =  generator.generateConjugation("2c", "arranscher");

        Assert.assertEquals("arranscher", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia arranschesch", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te arranscheschas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella arranschescha", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous arranschagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous arranschez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas arranscheschan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia arranscheva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te arranschevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella arranscheva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous arranschevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous arranschevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas arranschevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia arranschescha", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te arranscheschas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella arranschescha", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous arranscheschan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous arranscheschas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas arranscheschan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia arranschess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te arranschessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella arranschess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous arranschessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous arranschessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas arranschessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia arranscharo", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te arranscharossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella arranscharo", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous arranscharon", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous arranscharossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas arranscharon", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("arranschea", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("arranscheda", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("arranscheas", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("arranschedas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("arranschescha!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("arransche!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("arranschond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3() {
        forms =  generator.generateConjugation("3", "cuntschier");

        Assert.assertEquals("cuntschier", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia cuntsch", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te cuntschas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella cuntscha", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous cuntschagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous cuntschez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas cuntschan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia cuntschiva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te cuntschivas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella cuntschiva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous cuntschivan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous cuntschivas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas cuntschivan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia cuntscha", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te cuntschas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella cuntscha", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous cuntschan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous cuntschas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas cuntschan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia cuntschess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te cuntschessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella cuntschess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous cuntschessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous cuntschessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas cuntschessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia cuntscharo", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te cuntscharossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella cuntscharo", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous cuntscharon", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous cuntscharossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas cuntscharon", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("cuntschia", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("cuntscheida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("cuntschias", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("cuntscheidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("cuntscha!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("cuntsche!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("cuntschond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3a() {
        forms =  generator.generateConjugation("3a", "assignier");

        Assert.assertEquals("assignier", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia assegn", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te assegnas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella assegna", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous assignagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous assignez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas assegnan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia assigniva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te assignivas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella assigniva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous assignivan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous assignivas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas assignivan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia assegna", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te assegnas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella assegna", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous assegnan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous assegnas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas assegnan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia assigness", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te assignessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella assigness", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous assignessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous assignessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas assignessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia assignaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te assignarossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella assignaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous assignaron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous assignarossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas assignaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("assignia", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("assigneida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("assignias", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("assigneidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("assegna!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("assigne!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("assignond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3aGl() {
        forms =  generator.generateConjugation("3a", "piglier");

        Assert.assertEquals("piglier", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia pegl", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te peglias", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella peglia", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous pigliagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous piglez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas peglian", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia pigliva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te piglivas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella pigliva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous piglivan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous piglivas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas piglivan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia peglia", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te peglias", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella peglia", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous peglian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous peglias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas peglian", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia pigless", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te piglessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella pigless", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous piglessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous piglessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas piglessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia pigliaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te pigliarossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella pigliaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous pigliaron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous pigliarossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas pigliaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("piglia", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("pigleida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("piglias", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("pigleidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("peglia!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("pigle!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("pigliond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3b() {
        forms =  generator.generateConjugation("3b", "tutgier");

        Assert.assertEquals("tutgier", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia totg", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te totgas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella totga", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous tutgagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous tutgez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas totgan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia tutgiva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te tutgivas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella tutgiva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous tutgivan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous tutgivas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas tutgivan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia totga", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te totgas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella totga", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous totgan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous totgas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas totgan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia tutgess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te tutgessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella tutgess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous tutgessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous tutgessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas tutgessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia tutgaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te tutgarossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella tutgaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous tutgaron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous tutgarossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas tutgaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("tutgia", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("tutgeida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("tutgias", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("tutgeidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("totga!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("tutge!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("tutgond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3bGl() {
        forms =  generator.generateConjugation("3b", "anzuglier");

        Assert.assertEquals("anzuglier", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia anzogl", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te anzoglias", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella anzoglia", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous anzugliagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous anzuglez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas anzoglian", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia anzugliva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te anzuglivas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella anzugliva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous anzuglivan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous anzuglivas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas anzuglivan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia anzoglia", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te anzoglias", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella anzoglia", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous anzoglian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous anzoglias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas anzoglian", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia anzugless", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te anzuglessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella anzugless", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous anzuglessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous anzuglessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas anzuglessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia anzugliaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te anzugliarossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella anzugliaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous anzugliaron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous anzugliarossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas anzugliaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("anzuglia", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("anzugleida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("anzuglias", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("anzugleidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("anzoglia!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("anzugle!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("anzugliond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3c() {
        forms =  generator.generateConjugation("3c", "truschier");

        Assert.assertEquals("truschier", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia trousch", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te trouschas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella trouscha", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous truschagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous truschez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas trouschan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia truschiva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te truschivas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella truschiva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous truschivan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous truschivas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas truschivan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia trouscha", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te trouschas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella trouscha", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous trouschan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous trouschas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas trouschan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia truschess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te truschessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella truschess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous truschessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous truschessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas truschessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia truscharo", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te truscharossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella truscharo", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous truscharon", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous truscharossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas truscharon", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("truschia", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("truscheida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("truschias", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("truscheidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("trouscha!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("trusche!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("truschond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3d() {
        forms =  generator.generateConjugation("3d", "modifitgier");

        Assert.assertEquals("modifitgier", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia modifitgesch", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te modifitgeschas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella modifitgescha", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous modifitgagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous modifitgez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas modifitgeschan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia modifitgiva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te modifitgivas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella modifitgiva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous modifitgivan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous modifitgivas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas modifitgivan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia modifitgescha", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te modifitgeschas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella modifitgescha", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous modifitgeschan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous modifitgeschas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas modifitgeschan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia modifitgess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te modifitgessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella modifitgess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous modifitgessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous modifitgessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas modifitgessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia modifitgaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te modifitgarossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella modifitgaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous modifitgaron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous modifitgarossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas modifitgaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("modifitgia", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("modifitgeida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("modifitgias", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("modifitgeidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("modifitgescha!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("modifitge!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("modifitgond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4() {
        forms =  generator.generateConjugation("4", "tigneir");

        Assert.assertEquals("tigneir", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia tign", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te tignas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella tigna", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous tignagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous tignez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas tignan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia tigneva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te tignevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella tigneva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous tignevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous tignevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas tignevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia tigna", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te tignas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella tigna", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous tignan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous tignas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas tignan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia tigness", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te tignessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella tigness", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous tignessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous tignessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas tignessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia tignaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te tignarossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella tignaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous tignaron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous tignarossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas tignaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("tignia", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("tigneida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("tignias", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("tigneidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("tigna!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("tigne!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("tignond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4a() {
        forms =  generator.generateConjugation("4a", "tameir");

        Assert.assertEquals("tameir", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia tem", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te temas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella tema", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous tamagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous tamez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas teman", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia tameva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te tamevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella tameva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous tamevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous tamevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas tamevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia tema", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te temas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella tema", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous teman", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous temas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas teman", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia tamess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te tamessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella tamess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous tamessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous tamessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas tamessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia tamaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te tamarossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella tamaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous tamaron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous tamarossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas tamaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("tamia", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("tameida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("tamias", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("tameidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("tema!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("tame!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("tamond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule5() {
        forms =  generator.generateConjugation("5", "tanscher");

        Assert.assertEquals("tanscher", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia tansch", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te tanschas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella tanscha", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous tanschagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous tanschez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas tanschan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia tanscheva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te tanschevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella tanscheva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous tanschevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous tanschevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas tanschevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia tanscha", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te tanschas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella tanscha", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous tanschan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous tanschas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas tanschan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia tanschess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te tanschessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella tanschess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous tanschessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous tanschessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas tanschessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia tanscharo", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te tanscharossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella tanscharo", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous tanscharon", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous tanscharossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas tanscharon", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("tanschia", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("tanscheida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("tanschias", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("tanscheidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("tanscha!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("tansche!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("tanschond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule5a() {
        forms =  generator.generateConjugation("5a", "vender");

        Assert.assertEquals("vender", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia vend", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te vendas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella venda", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous vandagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous vandez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas vendan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia vandeva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te vandevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella vandeva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous vandevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous vandevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas vandevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia venda", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te vendas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella venda", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous vendan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous vendas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas vendan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia vandess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te vandessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella vandess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous vandessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous vandessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas vandessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia vandaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te vandarossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vandaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous vandaron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous vandarossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vandaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("vandia", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("vandeida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("vandias", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("vandeidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("venda!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("vande!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("vandond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule5b() {
        forms =  generator.generateConjugation("5b", "cloder");

        Assert.assertEquals("cloder", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia clod", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te clodas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella cloda", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous cludagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous cludez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas clodan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia cludeva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te cludevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella cludeva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous cludevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous cludevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas cludevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia cloda", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te clodas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella cloda", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous clodan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous clodas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas clodan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia cludess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te cludessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella cludess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous cludessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous cludessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas cludessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia cludaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te cludarossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella cludaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous cludaron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous cludarossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas cludaron", forms.getVerb().getFutur().getPlural3());

        /*
        Assert.assertEquals("clos", forms.getVerb().getParticipperfect().getMs());
        Assert.assertEquals("closa", forms.getVerb().getParticipperfect().getFs());
        Assert.assertEquals("clos", forms.getVerb().getParticipperfect().getMp());
        Assert.assertEquals("closas", forms.getVerb().getParticipperfect().getFp());
         */

        Assert.assertEquals("cloda!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("clude!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("cludond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule5c() {
        forms =  generator.generateConjugation("5c", "veiver");

        Assert.assertEquals("veiver", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia veiv", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te veivas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella veiva", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous vivagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous vivez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas veivan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia viveva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te vivevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella viveva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous vivevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous vivevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas vivevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia veiva", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te veivas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella veiva", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous veivan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous veivas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas veivan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia vivess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te vivessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella vivess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous vivessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous vivessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas vivessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia vivaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te vivarossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vivaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous vivaron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous vivarossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vivaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("vivia", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("viveida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("vivias", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("viveidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("veiva!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("vive!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("vivond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule6() {
        forms =  generator.generateConjugation("6", "parteir");

        Assert.assertEquals("parteir", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia part", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te partas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella parta", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous partign", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous partiz", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas partan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia partiva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te partivas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella partiva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous partivan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous partivas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas partivan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia parta", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te partas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella parta", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous partan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous partas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas partan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia partiss", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te partissas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella partiss", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous partissan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous partissas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas partissan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia partiro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te partirossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella partiro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous partiron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous partirossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas partiron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("partia", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("parteida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("partias", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("parteidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("parta!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("parti!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("partond/partend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule6a() {
        forms =  generator.generateConjugation("6a", "bargeir");

        Assert.assertEquals("bargeir", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia bratg", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te bragias", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella bragia", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous bargign", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous bargiz", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas bragian", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia bargiva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te bargivas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella bargiva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous bargivan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous bargivas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas bargivan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia bragia", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te bragias", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella bragia", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous bragian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous bragias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas bragian", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia bargiss", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te bargissas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella bargiss", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous bargissan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous bargissas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas bargissan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia bargiro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te bargirossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella bargiro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous bargiron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous bargirossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas bargiron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("bargia", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("bargeida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("bargias", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("bargeidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("bragia!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("bargi!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("bargiond/bargend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule6b() {
        forms =  generator.generateConjugation("6b", "darveir");

        Assert.assertEquals("darveir", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia derv", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te dervas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella derva", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous darvign", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous darviz", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas dervan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia darviva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te darvivas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella darviva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous darvivan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous darvivas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas darvivan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia derva", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te dervas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella derva", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous dervan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous dervas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas dervan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia darviss", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te darvissas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella darviss", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous darvissan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous darvissas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas darvissan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia darviro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te darvirossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella darviro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous darviron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous darvirossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas darviron", forms.getVerb().getFutur().getPlural3());

        /*
        Assert.assertEquals("davert", forms.getVerb().getParticipperfect().getMs());
        Assert.assertEquals("daverta", forms.getVerb().getParticipperfect().getFs());
        Assert.assertEquals("daverts", forms.getVerb().getParticipperfect().getMp());
        Assert.assertEquals("davertas", forms.getVerb().getParticipperfect().getFp());
         */

        Assert.assertEquals("derva!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("darvi!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("darvond/darvend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule6c() {
        forms =  generator.generateConjugation("6c", "sgrigneir");

        Assert.assertEquals("sgrigneir", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia sgregn", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te sgregnas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella sgregna", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous sgrignign", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous sgrigniz", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas sgregnan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia sgrigniva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te sgrignivas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella sgrigniva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous sgrignivan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous sgrignivas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas sgrignivan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia sgregna", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te sgregnas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella sgregna", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous sgregnan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous sgregnas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas sgregnan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia sgrigniss", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te sgrignissas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella sgrigniss", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous sgrignissan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous sgrignissas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas sgrignissan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia sgrigniro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te sgrignirossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella sgrigniro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous sgrigniron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous sgrignirossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas sgrigniron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("sgrignia", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("sgrigneida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("sgrignias", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("sgrigneidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("sgregna!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("sgrigni!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("sgrignond/sgrignend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule6d() {
        forms =  generator.generateConjugation("6d", "bugleir");

        Assert.assertEquals("bugleir", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia bogl", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te boglias", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella boglia", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous buglign", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous bugliz", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas boglian", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia bugliva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te buglivas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella bugliva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous buglivan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous buglivas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas buglivan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia boglia", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te boglias", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella boglia", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous boglian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous boglias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas boglian", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia bugliss", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te buglissas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella bugliss", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous buglissan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous buglissas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas buglissan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia bugliro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te buglirossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella bugliro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous bugliron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous buglirossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas bugliron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("buglia", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("bugleida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("buglias", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("bugleidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("boglia!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("bugli!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("buglond/buglend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule6e() {
        forms =  generator.generateConjugation("6e", "santeir");

        Assert.assertEquals("santeir", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia saint", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te saintas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella sainta", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous santign", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous santiz", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas saintan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia santiva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te santivas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella santiva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous santivan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous santivas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas santivan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia sainta", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te saintas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella sainta", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous saintan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous saintas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas saintan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia santiss", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te santissas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella santiss", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous santissan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous santissas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas santissan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia santiro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te santirossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella santiro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous santiron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous santirossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas santiron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("santia", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("santeida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("santias", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("santeidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("sainta!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("santi!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("santond/santend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule7() {
        forms =  generator.generateConjugation("7", "amplaneir");

        Assert.assertEquals("amplaneir", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia amplanesch", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te amplaneschas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella amplanescha", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous amplanign", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous amplaniz", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas amplaneschan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia amplaniva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te amplanivas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella amplaniva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous amplanivan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous amplanivas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas amplanivan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia amplanescha", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te amplaneschas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella amplanescha", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous amplaneschan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous amplaneschas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas amplaneschan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia amplaniss", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te amplanissas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella amplaniss", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous amplanissan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous amplanissas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas amplanissan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia amplaniro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te amplanirossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella amplaniro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous amplaniron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous amplanirossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas amplaniron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("amplania", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("amplaneida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("amplanias", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("amplaneidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("amplanescha!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("amplani!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("amplanond/amplanend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule8() {
        forms =  generator.generateConjugation("8", "abitar");

        Assert.assertEquals("abitar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia abitesch", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te abiteschas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella abitescha", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous abitagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous abitez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas abiteschan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia abitava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te abitavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella abitava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous abitavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous abitavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas abitavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia abitescha", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te abiteschas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella abitescha", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous abiteschan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous abiteschas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas abiteschan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia abitess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te abitessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella abitess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous abitessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous abitessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas abitessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia abitaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te abitarossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella abitaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous abitaron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous abitarossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas abitaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("abito", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("abitada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("abitos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("abitadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("abitescha!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("abite!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("abitond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule9() {
        forms =  generator.generateConjugation("9", "numnar");

        Assert.assertEquals("numnar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("ia nomn", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("te nomnas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella nomna", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nous numnagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vous numnez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas nomnan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("ia numnava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("te numnavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella numnava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nous numnavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vous numnavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas numnavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("tg'ia nomna", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("tgi te nomnas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("tg'el/ella nomna", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("tgi nous nomnan", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("tgi vous nomnas", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("tg'els/ellas nomnan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ia numness", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("te numnessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella numness", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nous numnessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vous numnessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas numnessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("ia numnaro", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("te numnarossas", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella numnaro", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nous numnaron", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vous numnarossas", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas numnaron", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("numno", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("numnada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("numnos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("numnadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("nomna!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("numne!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("numnond", forms.getVerb().getGerundium());
    }
}
