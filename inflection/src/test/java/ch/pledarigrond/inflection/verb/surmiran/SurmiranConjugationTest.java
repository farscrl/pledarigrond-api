package ch.pledarigrond.inflection.verb.surmiran;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.verb.surmiran.SurmiranConjugation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SurmiranConjugationTest {

    private static SurmiranConjugation generator;
    private InflectionDto forms;

    @BeforeAll
    public static void initialize() {
        generator = new SurmiranConjugation();
    }

    @Test
    public void testRule1() {
        forms =  generator.generateConjugation("1", "cantar");

        assertEquals("cantar", forms.getVerb().getInfinitiv());

        assertEquals("ia cant", forms.getVerb().getPreschent().getSing1());
        assertEquals("te cantas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella canta", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous cantagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous cantez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas cantan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia cantava", forms.getVerb().getImperfect().getSing1());
        assertEquals("te cantavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella cantava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous cantavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous cantavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas cantavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia canta", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te cantas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella canta", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous cantan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous cantas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas cantan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia cantess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te cantessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella cantess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous cantessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous cantessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas cantessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia cantaro", forms.getVerb().getFutur().getSing1());
        assertEquals("te cantarossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella cantaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous cantaron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous cantarossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas cantaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("canto", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("cantada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("cantos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("cantadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("canta!", forms.getVerb().getImperativ().getSingular());
        assertEquals("cante!", forms.getVerb().getImperativ().getPlural());

        assertEquals("cantond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1ReflexiveModRoot() {
        forms = generator.generateConjugation("1", "sa magler");

        assertEquals("sa magler", forms.getVerb().getInfinitiv());

        assertEquals("ia ma magl", forms.getVerb().getPreschent().getSing1());
    }

    @Test
    public void testRule1a() {
        forms =  generator.generateConjugation("1a", "tadlar");

        assertEquals("tadlar", forms.getVerb().getInfinitiv());

        assertEquals("ia tedl", forms.getVerb().getPreschent().getSing1());
        assertEquals("te tedlas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella tedla", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous tadlagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous tadlez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas tedlan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia tadlava", forms.getVerb().getImperfect().getSing1());
        assertEquals("te tadlavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella tadlava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous tadlavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous tadlavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas tadlavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia tedla", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te tedlas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella tedla", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous tedlan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous tedlas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas tedlan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia tadless", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te tadlessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella tadless", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous tadlessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous tadlessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas tadlessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia tadlaro", forms.getVerb().getFutur().getSing1());
        assertEquals("te tadlarossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella tadlaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous tadlaron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous tadlarossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas tadlaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("tadlo", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("tadlada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("tadlos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("tadladas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("tedla!", forms.getVerb().getImperativ().getSingular());
        assertEquals("tadle!", forms.getVerb().getImperativ().getPlural());

        assertEquals("tadlond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1b() {
        forms =  generator.generateConjugation("1b", "fimar");

        assertEquals("fimar", forms.getVerb().getInfinitiv());

        assertEquals("ia fem", forms.getVerb().getPreschent().getSing1());
        assertEquals("te femas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella fema", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous fimagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous fimez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas feman", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia fimava", forms.getVerb().getImperfect().getSing1());
        assertEquals("te fimavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella fimava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous fimavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous fimavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas fimavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia fema", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te femas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella fema", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous feman", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous femas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas feman", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia fimess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te fimessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella fimess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous fimessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous fimessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas fimessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia fimaro", forms.getVerb().getFutur().getSing1());
        assertEquals("te fimarossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella fimaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous fimaron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous fimarossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas fimaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("fimo", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("fimada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("fimos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("fimadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("fema!", forms.getVerb().getImperativ().getSingular());
        assertEquals("fime!", forms.getVerb().getImperativ().getPlural());

        assertEquals("fimond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1c() {
        forms =  generator.generateConjugation("1c", "pasar");

        assertEquals("pasar", forms.getVerb().getInfinitiv());

        assertEquals("ia peis", forms.getVerb().getPreschent().getSing1());
        assertEquals("te peisas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella peisa", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous pasagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous pasez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas peisan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia pasava", forms.getVerb().getImperfect().getSing1());
        assertEquals("te pasavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella pasava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous pasavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous pasavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas pasavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia peisa", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te peisas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella peisa", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous peisan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous peisas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas peisan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia pasess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te pasessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella pasess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous pasessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous pasessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas pasessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia pasaro", forms.getVerb().getFutur().getSing1());
        assertEquals("te pasarossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella pasaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous pasaron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous pasarossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas pasaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("paso", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("pasada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("pasos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("pasadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("peisa!", forms.getVerb().getImperativ().getSingular());
        assertEquals("pase!", forms.getVerb().getImperativ().getPlural());

        assertEquals("pasond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1d() {
        forms =  generator.generateConjugation("1d", "midar");

        assertEquals("midar", forms.getVerb().getInfinitiv());

        assertEquals("ia meid", forms.getVerb().getPreschent().getSing1());
        assertEquals("te meidas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella meida", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous midagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous midez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas meidan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia midava", forms.getVerb().getImperfect().getSing1());
        assertEquals("te midavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella midava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous midavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous midavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas midavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia meida", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te meidas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella meida", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous meidan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous meidas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas meidan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia midess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te midessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella midess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous midessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous midessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas midessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia midaro", forms.getVerb().getFutur().getSing1());
        assertEquals("te midarossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella midaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous midaron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous midarossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas midaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("mido", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("midada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("midos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("midadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("meida!", forms.getVerb().getImperativ().getSingular());
        assertEquals("mide!", forms.getVerb().getImperativ().getPlural());

        assertEquals("midond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1e() {
        forms =  generator.generateConjugation("1e", "passantar");

        assertEquals("passantar", forms.getVerb().getInfinitiv());

        assertEquals("ia passaint", forms.getVerb().getPreschent().getSing1());
        assertEquals("te passaintas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella passainta", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous passantagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous passantez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas passaintan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia passantava", forms.getVerb().getImperfect().getSing1());
        assertEquals("te passantavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella passantava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous passantavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous passantavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas passantavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia passainta", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te passaintas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella passainta", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous passaintan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous passaintas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas passaintan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia passantess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te passantessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella passantess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous passantessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous passantessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas passantessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia passantaro", forms.getVerb().getFutur().getSing1());
        assertEquals("te passantarossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella passantaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous passantaron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous passantarossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas passantaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("passanto", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("passantada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("passantos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("passantadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("passainta!", forms.getVerb().getImperativ().getSingular());
        assertEquals("passante!", forms.getVerb().getImperativ().getPlural());

        assertEquals("passantond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1f() {
        forms =  generator.generateConjugation("1f", "irtar");

        assertEquals("irtar", forms.getVerb().getInfinitiv());

        assertEquals("ia iert", forms.getVerb().getPreschent().getSing1());
        assertEquals("te iertas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella ierta", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous irtagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous irtez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas iertan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia irtava", forms.getVerb().getImperfect().getSing1());
        assertEquals("te irtavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella irtava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous irtavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous irtavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas irtavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia ierta", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te iertas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella ierta", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous iertan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous iertas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas iertan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia irtess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te irtessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella irtess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous irtessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous irtessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas irtessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia irtaro", forms.getVerb().getFutur().getSing1());
        assertEquals("te irtarossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella irtaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous irtaron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous irtarossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas irtaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("irto", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("irtada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("irtos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("irtadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("ierta!", forms.getVerb().getImperativ().getSingular());
        assertEquals("irte!", forms.getVerb().getImperativ().getPlural());

        assertEquals("irtond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1g() {
        forms =  generator.generateConjugation("1g", "ampruar");

        assertEquals("ampruar", forms.getVerb().getInfinitiv());

        assertEquals("ia amprov", forms.getVerb().getPreschent().getSing1());
        assertEquals("te amprovas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella amprova", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous ampruagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous ampruez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas amprovan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia ampruava", forms.getVerb().getImperfect().getSing1());
        assertEquals("te ampruavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella ampruava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous ampruavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous ampruavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas ampruavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia amprova", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te amprovas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella amprova", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous amprovan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous amprovas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas amprovan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia ampruess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te ampruessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella ampruess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous ampruessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous ampruessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas ampruessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia ampruaro", forms.getVerb().getFutur().getSing1());
        assertEquals("te ampruarossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella ampruaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous ampruaron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous ampruarossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas ampruaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("ampruo", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("ampruada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("ampruos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("ampruadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("amprova!", forms.getVerb().getImperativ().getSingular());
        assertEquals("amprue!", forms.getVerb().getImperativ().getPlural());

        assertEquals("ampruond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1h() {
        forms =  generator.generateConjugation("1h", "sfardar");

        assertEquals("sfardar", forms.getVerb().getInfinitiv());

        assertEquals("ia sfreid", forms.getVerb().getPreschent().getSing1());
        assertEquals("te sfreidas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella sfreida", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous sfardagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous sfardez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas sfreidan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia sfardava", forms.getVerb().getImperfect().getSing1());
        assertEquals("te sfardavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella sfardava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous sfardavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous sfardavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas sfardavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia sfreida", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te sfreidas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella sfreida", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous sfreidan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous sfreidas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas sfreidan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia sfardess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te sfardessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella sfardess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous sfardessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous sfardessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas sfardessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia sfardaro", forms.getVerb().getFutur().getSing1());
        assertEquals("te sfardarossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella sfardaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous sfardaron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous sfardarossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas sfardaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("sfardo", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("sfardada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("sfardos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("sfardadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("sfreida!", forms.getVerb().getImperativ().getSingular());
        assertEquals("sfarde!", forms.getVerb().getImperativ().getPlural());

        assertEquals("sfardond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1i() {
        forms =  generator.generateConjugation("1i", "sgarmar");

        assertEquals("sgarmar", forms.getVerb().getInfinitiv());

        assertEquals("ia sgrom", forms.getVerb().getPreschent().getSing1());
        assertEquals("te sgromas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella sgroma", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous sgarmagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous sgarmez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas sgroman", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia sgarmava", forms.getVerb().getImperfect().getSing1());
        assertEquals("te sgarmavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella sgarmava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous sgarmavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous sgarmavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas sgarmavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia sgroma", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te sgromas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella sgroma", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous sgroman", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous sgromas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas sgroman", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia sgarmess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te sgarmessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella sgarmess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous sgarmessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous sgarmessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas sgarmessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia sgarmaro", forms.getVerb().getFutur().getSing1());
        assertEquals("te sgarmarossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella sgarmaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous sgarmaron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous sgarmarossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas sgarmaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("sgarmo", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("sgarmada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("sgarmos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("sgarmadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("sgroma!", forms.getVerb().getImperativ().getSingular());
        assertEquals("sgarme!", forms.getVerb().getImperativ().getPlural());

        assertEquals("sgarmond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1j() {
        forms =  generator.generateConjugation("1j", "splanar");

        assertEquals("splanar", forms.getVerb().getInfinitiv());

        assertEquals("ia splang", forms.getVerb().getPreschent().getSing1());
        assertEquals("te splangas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella splanga", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous splanagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous splanez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas splangan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia splanava", forms.getVerb().getImperfect().getSing1());
        assertEquals("te splanavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella splanava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous splanavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous splanavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas splanavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia splanga", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te splangas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella splanga", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous splangan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous splangas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas splangan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia splaness", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te splanessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella splaness", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous splanessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous splanessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas splanessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia splanaro", forms.getVerb().getFutur().getSing1());
        assertEquals("te splanarossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella splanaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous splanaron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous splanarossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas splanaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("splano", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("splanada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("splanos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("splanadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("splanga!", forms.getVerb().getImperativ().getSingular());
        assertEquals("splane!", forms.getVerb().getImperativ().getPlural());

        assertEquals("splanond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1k() {
        forms =  generator.generateConjugation("1k", "perdunar");

        assertEquals("perdunar", forms.getVerb().getInfinitiv());

        assertEquals("ia perdung", forms.getVerb().getPreschent().getSing1());
        assertEquals("te perdungas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella perdunga", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous perdunagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous perdunez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas perdungan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia perdunava", forms.getVerb().getImperfect().getSing1());
        assertEquals("te perdunavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella perdunava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous perdunavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous perdunavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas perdunavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia perdunga", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te perdungas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella perdunga", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous perdungan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous perdungas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas perdungan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia perduness", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te perdunessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella perduness", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous perdunessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous perdunessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas perdunessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia perdunaro", forms.getVerb().getFutur().getSing1());
        assertEquals("te perdunarossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella perdunaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous perdunaron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous perdunarossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas perdunaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("perduno", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("perdunada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("perdunos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("perdunadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("perdunga!", forms.getVerb().getImperativ().getSingular());
        assertEquals("perdune!", forms.getVerb().getImperativ().getPlural());

        assertEquals("perdunond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1l() {
        forms =  generator.generateConjugation("1l", "manar");

        assertEquals("manar", forms.getVerb().getInfinitiv());

        assertEquals("ia magn", forms.getVerb().getPreschent().getSing1());
        assertEquals("te magnas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella magna", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous managn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous manez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas magnan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia manava", forms.getVerb().getImperfect().getSing1());
        assertEquals("te manavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella manava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous manavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous manavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas manavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia magna", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te magnas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella magna", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous magnan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous magnas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas magnan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia maness", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te manessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella maness", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous manessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous manessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas manessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia manaro", forms.getVerb().getFutur().getSing1());
        assertEquals("te manarossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella manaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous manaron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous manarossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas manaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("mano", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("manada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("manos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("manadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("magna!", forms.getVerb().getImperativ().getSingular());
        assertEquals("mane!", forms.getVerb().getImperativ().getPlural());

        assertEquals("manond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1m() {
        forms =  generator.generateConjugation("1m", "pinar");

        assertEquals("pinar", forms.getVerb().getInfinitiv());

        assertEquals("ia pegn", forms.getVerb().getPreschent().getSing1());
        assertEquals("te pegnas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella pegna", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous pinagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous pinez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas pegnan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia pinava", forms.getVerb().getImperfect().getSing1());
        assertEquals("te pinavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella pinava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous pinavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous pinavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas pinavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia pegna", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te pegnas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella pegna", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous pegnan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous pegnas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas pegnan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia piness", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te pinessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella piness", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous pinessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous pinessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas pinessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia pinaro", forms.getVerb().getFutur().getSing1());
        assertEquals("te pinarossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella pinaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous pinaron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous pinarossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas pinaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("pino", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("pinada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("pinos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("pinadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("pegna!", forms.getVerb().getImperativ().getSingular());
        assertEquals("pine!", forms.getVerb().getImperativ().getPlural());

        assertEquals("pinond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1n() {
        forms =  generator.generateConjugation("1n", "schinar");

        assertEquals("schinar", forms.getVerb().getInfinitiv());

        assertEquals("ia schign", forms.getVerb().getPreschent().getSing1());
        assertEquals("te schignas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella schigna", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous schinagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous schinez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas schignan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia schinava", forms.getVerb().getImperfect().getSing1());
        assertEquals("te schinavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella schinava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous schinavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous schinavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas schinavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia schigna", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te schignas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella schigna", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous schignan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous schignas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas schignan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia schiness", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te schinessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella schiness", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous schinessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous schinessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas schinessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia schinaro", forms.getVerb().getFutur().getSing1());
        assertEquals("te schinarossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella schinaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous schinaron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous schinarossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas schinaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("schino", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("schinada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("schinos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("schinadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("schigna!", forms.getVerb().getImperativ().getSingular());
        assertEquals("schine!", forms.getVerb().getImperativ().getPlural());

        assertEquals("schinond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1o() {
        forms =  generator.generateConjugation("1o", "anganar");

        assertEquals("anganar", forms.getVerb().getInfinitiv());

        assertEquals("ia angion", forms.getVerb().getPreschent().getSing1());
        assertEquals("te angionas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella angiona", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous anganagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous anganez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas angionan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia anganava", forms.getVerb().getImperfect().getSing1());
        assertEquals("te anganavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella anganava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous anganavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous anganavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas anganavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia angiona", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te angionas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella angiona", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous angionan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous angionas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas angionan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia anganess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te anganessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella anganess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous anganessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous anganessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas anganessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia anganaro", forms.getVerb().getFutur().getSing1());
        assertEquals("te anganarossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella anganaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous anganaron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous anganarossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas anganaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("angano", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("anganada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("anganos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("anganadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("angiona!", forms.getVerb().getImperativ().getSingular());
        assertEquals("angane!", forms.getVerb().getImperativ().getPlural());

        assertEquals("anganond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1p() {
        forms =  generator.generateConjugation("1p", "scanar");

        assertEquals("scanar", forms.getVerb().getInfinitiv());

        assertEquals("ia stgon", forms.getVerb().getPreschent().getSing1());
        assertEquals("te stgonas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella stgona", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous scanagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous scanez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas stgonan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia scanava", forms.getVerb().getImperfect().getSing1());
        assertEquals("te scanavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella scanava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous scanavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous scanavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas scanavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia stgona", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te stgonas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella stgona", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous stgonan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous stgonas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas stgonan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia scaness", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te scanessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella scaness", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous scanessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous scanessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas scanessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia scanaro", forms.getVerb().getFutur().getSing1());
        assertEquals("te scanarossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella scanaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous scanaron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous scanarossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas scanaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("scano", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("scanada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("scanos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("scanadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("stgona!", forms.getVerb().getImperativ().getSingular());
        assertEquals("scane!", forms.getVerb().getImperativ().getPlural());

        assertEquals("scanond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1q() {
        forms =  generator.generateConjugation("1q", "ruschanar");

        assertEquals("ruschanar", forms.getVerb().getInfinitiv());

        assertEquals("ia raschung", forms.getVerb().getPreschent().getSing1());
        assertEquals("te raschungas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella raschunga", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous ruschanagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous ruschanez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas raschungan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia ruschanava", forms.getVerb().getImperfect().getSing1());
        assertEquals("te ruschanavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella ruschanava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous ruschanavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous ruschanavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas ruschanavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia raschunga", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te raschungas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella raschunga", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous raschungan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous raschungas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas raschungan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia ruschaness", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te ruschanessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella ruschaness", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous ruschanessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous ruschanessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas ruschanessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia ruschanaro", forms.getVerb().getFutur().getSing1());
        assertEquals("te ruschanarossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella ruschanaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous ruschanaron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous ruschanarossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas ruschanaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("ruschano", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("ruschanada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("ruschanos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("ruschanadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("raschunga!", forms.getVerb().getImperativ().getSingular());
        assertEquals("ruschane!", forms.getVerb().getImperativ().getPlural());

        assertEquals("ruschanond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1r() {
        forms =  generator.generateConjugation("1r", "declarar");

        assertEquals("declarar", forms.getVerb().getInfinitiv());

        assertEquals("ia dacler", forms.getVerb().getPreschent().getSing1());
        assertEquals("te dacleras", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella daclera", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous declaragn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous declarez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas dacleran", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia declarava", forms.getVerb().getImperfect().getSing1());
        assertEquals("te declaravas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella declarava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous declaravan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous declaravas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas declaravan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia daclera", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te dacleras", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella daclera", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous dacleran", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous dacleras", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas dacleran", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia declaress", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te declaressas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella declaress", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous declaressan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous declaressas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas declaressan", forms.getVerb().getCundiziunal().getPlural3());

        /* Exeption in ending (double syllable is removed)
        assertEquals("ia declaro", forms.getVerb().getFutur().getSing1());
        assertEquals("te declarossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella declaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous declaron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous declarossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas declaron", forms.getVerb().getFutur().getPlural3());
        */

        assertEquals("declaro", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("declarada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("declaros", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("declaradas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("daclera!", forms.getVerb().getImperativ().getSingular());
        assertEquals("declare!", forms.getVerb().getImperativ().getPlural());

        assertEquals("declarond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1s() {
        forms =  generator.generateConjugation("1s", "preschentar");

        assertEquals("preschentar", forms.getVerb().getInfinitiv());

        assertEquals("ia preschaint", forms.getVerb().getPreschent().getSing1());
        assertEquals("te preschaintas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella preschainta", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous preschentagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous preschentez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas preschaintan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia preschentava", forms.getVerb().getImperfect().getSing1());
        assertEquals("te preschentavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella preschentava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous preschentavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous preschentavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas preschentavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia preschainta", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te preschaintas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella preschainta", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous preschaintan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous preschaintas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas preschaintan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia preschentess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te preschentessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella preschentess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous preschentessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous preschentessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas preschentessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia preschentaro", forms.getVerb().getFutur().getSing1());
        assertEquals("te preschentarossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella preschentaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous preschentaron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous preschentarossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas preschentaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("preschento", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("preschentada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("preschentos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("preschentadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("preschainta!", forms.getVerb().getImperativ().getSingular());
        assertEquals("preschente!", forms.getVerb().getImperativ().getPlural());

        assertEquals("preschentond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1t() {
        forms =  generator.generateConjugation("1t", "clamar");

        assertEquals("clamar", forms.getVerb().getInfinitiv());

        assertEquals("ia clom", forms.getVerb().getPreschent().getSing1());
        assertEquals("te clomas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella cloma", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous clamagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous clamez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas cloman", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia clamava", forms.getVerb().getImperfect().getSing1());
        assertEquals("te clamavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella clamava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous clamavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous clamavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas clamavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia cloma", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te clomas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella cloma", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous cloman", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous clomas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas cloman", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia clamess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te clamessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella clamess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous clamessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous clamessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas clamessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia clamaro", forms.getVerb().getFutur().getSing1());
        assertEquals("te clamarossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella clamaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous clamaron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous clamarossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas clamaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("clamo", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("clamada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("clamos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("clamadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("cloma!", forms.getVerb().getImperativ().getSingular());
        assertEquals("clame!", forms.getVerb().getImperativ().getPlural());

        assertEquals("clamond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1u() {
        forms =  generator.generateConjugation("1u", "crudar");

        assertEquals("crudar", forms.getVerb().getInfinitiv());

        assertEquals("ia crod", forms.getVerb().getPreschent().getSing1());
        assertEquals("te crodas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella croda", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous crudagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous crudez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas crodan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia crudava", forms.getVerb().getImperfect().getSing1());
        assertEquals("te crudavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella crudava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous crudavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous crudavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas crudavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia croda", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te crodas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella croda", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous crodan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous crodas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas crodan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia crudess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te crudessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella crudess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous crudessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous crudessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas crudessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia crudaro", forms.getVerb().getFutur().getSing1());
        assertEquals("te crudarossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella crudaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous crudaron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous crudarossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas crudaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("crudo", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("crudada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("crudos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("crudadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("croda!", forms.getVerb().getImperativ().getSingular());
        assertEquals("crude!", forms.getVerb().getImperativ().getPlural());

        assertEquals("crudond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule2() {
        forms =  generator.generateConjugation("2", "lascher");

        assertEquals("lascher", forms.getVerb().getInfinitiv());

        assertEquals("ia lasch", forms.getVerb().getPreschent().getSing1());
        assertEquals("te laschas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella lascha", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous laschagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous laschez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas laschan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia lascheva", forms.getVerb().getImperfect().getSing1());
        assertEquals("te laschevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella lascheva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous laschevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous laschevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas laschevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia lascha", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te laschas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella lascha", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous laschan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous laschas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas laschan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia laschess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te laschessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella laschess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous laschessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous laschessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas laschessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia lascharo", forms.getVerb().getFutur().getSing1());
        assertEquals("te lascharossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella lascharo", forms.getVerb().getFutur().getSing3());
        assertEquals("nous lascharon", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous lascharossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas lascharon", forms.getVerb().getFutur().getPlural3());

        assertEquals("laschea", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("lascheda", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("lascheas", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("laschedas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("lascha!", forms.getVerb().getImperativ().getSingular());
        assertEquals("lasche!", forms.getVerb().getImperativ().getPlural());

        assertEquals("laschond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule2Gl() {
        forms =  generator.generateConjugation("2", "magler");

        assertEquals("magler", forms.getVerb().getInfinitiv());

        assertEquals("ia magl", forms.getVerb().getPreschent().getSing1());
        assertEquals("te maglias", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella maglia", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous magliagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous maglez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas maglian", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia magleva", forms.getVerb().getImperfect().getSing1());
        assertEquals("te maglevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella magleva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous maglevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous maglevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas maglevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia maglia", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te maglias", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella maglia", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous maglian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous maglias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas maglian", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia magless", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te maglessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella magless", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous maglessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous maglessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas maglessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia magliaro", forms.getVerb().getFutur().getSing1());
        assertEquals("te magliarossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella magliaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous magliaron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous magliarossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas magliaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("maglea", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("magleda", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("magleas", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("magledas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("maglia!", forms.getVerb().getImperativ().getSingular());
        assertEquals("magle!", forms.getVerb().getImperativ().getPlural());

        assertEquals("magliond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule2a() {
        forms =  generator.generateConjugation("2a", "manager");

        assertEquals("manager", forms.getVerb().getInfinitiv());

        assertEquals("ia manetg", forms.getVerb().getPreschent().getSing1());
        assertEquals("te manegias", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella manegia", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous managiagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous managez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas manegian", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia manageva", forms.getVerb().getImperfect().getSing1());
        assertEquals("te managevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella manageva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous managevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous managevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas managevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia manegia", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te manegias", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella manegia", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous manegian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous manegias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas manegian", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia managess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te managessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella managess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous managessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous managessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas managessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia managiaro", forms.getVerb().getFutur().getSing1());
        assertEquals("te managiarossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella managiaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous managiaron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous managiarossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas managiaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("managea", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("manageda", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("manageas", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("managedas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("manegia!", forms.getVerb().getImperativ().getSingular());
        assertEquals("manage!", forms.getVerb().getImperativ().getPlural());

        assertEquals("managiond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule2b() {
        forms =  generator.generateConjugation("2b", "macager");

        assertEquals("macager", forms.getVerb().getInfinitiv());

        assertEquals("ia machetg", forms.getVerb().getPreschent().getSing1());
        assertEquals("te machegias", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella machegia", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous macagiagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous macagez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas machegian", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia macageva", forms.getVerb().getImperfect().getSing1());
        assertEquals("te macagevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella macageva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous macagevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous macagevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas macagevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia machegia", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te machegias", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella machegia", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous machegian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous machegias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas machegian", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia macagess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te macagessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella macagess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous macagessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous macagessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas macagessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia macagiaro", forms.getVerb().getFutur().getSing1());
        assertEquals("te macagiarossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella macagiaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous macagiaron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous macagiarossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas macagiaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("macagea", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("macageda", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("macageas", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("macagedas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("machegia!", forms.getVerb().getImperativ().getSingular());
        assertEquals("macage!", forms.getVerb().getImperativ().getPlural());

        assertEquals("macagiond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule2c() {
        forms =  generator.generateConjugation("2c", "arranscher");

        assertEquals("arranscher", forms.getVerb().getInfinitiv());

        assertEquals("ia arranschesch", forms.getVerb().getPreschent().getSing1());
        assertEquals("te arranscheschas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella arranschescha", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous arranschagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous arranschez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas arranscheschan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia arranscheva", forms.getVerb().getImperfect().getSing1());
        assertEquals("te arranschevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella arranscheva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous arranschevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous arranschevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas arranschevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia arranschescha", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te arranscheschas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella arranschescha", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous arranscheschan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous arranscheschas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas arranscheschan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia arranschess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te arranschessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella arranschess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous arranschessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous arranschessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas arranschessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia arranscharo", forms.getVerb().getFutur().getSing1());
        assertEquals("te arranscharossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella arranscharo", forms.getVerb().getFutur().getSing3());
        assertEquals("nous arranscharon", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous arranscharossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas arranscharon", forms.getVerb().getFutur().getPlural3());

        assertEquals("arranschea", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("arranscheda", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("arranscheas", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("arranschedas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("arranschescha!", forms.getVerb().getImperativ().getSingular());
        assertEquals("arransche!", forms.getVerb().getImperativ().getPlural());

        assertEquals("arranschond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3() {
        forms =  generator.generateConjugation("3", "cuntschier");

        assertEquals("cuntschier", forms.getVerb().getInfinitiv());

        assertEquals("ia cuntsch", forms.getVerb().getPreschent().getSing1());
        assertEquals("te cuntschas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella cuntscha", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous cuntschagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous cuntschez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas cuntschan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia cuntschiva", forms.getVerb().getImperfect().getSing1());
        assertEquals("te cuntschivas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella cuntschiva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous cuntschivan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous cuntschivas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas cuntschivan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia cuntscha", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te cuntschas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella cuntscha", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous cuntschan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous cuntschas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas cuntschan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia cuntschess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te cuntschessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella cuntschess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous cuntschessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous cuntschessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas cuntschessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia cuntscharo", forms.getVerb().getFutur().getSing1());
        assertEquals("te cuntscharossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella cuntscharo", forms.getVerb().getFutur().getSing3());
        assertEquals("nous cuntscharon", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous cuntscharossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas cuntscharon", forms.getVerb().getFutur().getPlural3());

        assertEquals("cuntschia", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("cuntscheida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("cuntschias", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("cuntscheidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("cuntscha!", forms.getVerb().getImperativ().getSingular());
        assertEquals("cuntsche!", forms.getVerb().getImperativ().getPlural());

        assertEquals("cuntschond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3a() {
        forms =  generator.generateConjugation("3a", "assignier");

        assertEquals("assignier", forms.getVerb().getInfinitiv());

        assertEquals("ia assegn", forms.getVerb().getPreschent().getSing1());
        assertEquals("te assegnas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella assegna", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous assignagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous assignez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas assegnan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia assigniva", forms.getVerb().getImperfect().getSing1());
        assertEquals("te assignivas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella assigniva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous assignivan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous assignivas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas assignivan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia assegna", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te assegnas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella assegna", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous assegnan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous assegnas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas assegnan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia assigness", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te assignessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella assigness", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous assignessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous assignessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas assignessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia assignaro", forms.getVerb().getFutur().getSing1());
        assertEquals("te assignarossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella assignaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous assignaron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous assignarossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas assignaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("assignia", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("assigneida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("assignias", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("assigneidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("assegna!", forms.getVerb().getImperativ().getSingular());
        assertEquals("assigne!", forms.getVerb().getImperativ().getPlural());

        assertEquals("assignond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3aGl() {
        forms =  generator.generateConjugation("3a", "piglier");

        assertEquals("piglier", forms.getVerb().getInfinitiv());

        assertEquals("ia pegl", forms.getVerb().getPreschent().getSing1());
        assertEquals("te peglias", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella peglia", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous pigliagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous piglez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas peglian", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia pigliva", forms.getVerb().getImperfect().getSing1());
        assertEquals("te piglivas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella pigliva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous piglivan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous piglivas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas piglivan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia peglia", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te peglias", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella peglia", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous peglian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous peglias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas peglian", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia pigless", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te piglessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella pigless", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous piglessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous piglessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas piglessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia pigliaro", forms.getVerb().getFutur().getSing1());
        assertEquals("te pigliarossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella pigliaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous pigliaron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous pigliarossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas pigliaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("piglia", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("pigleida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("piglias", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("pigleidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("peglia!", forms.getVerb().getImperativ().getSingular());
        assertEquals("pigle!", forms.getVerb().getImperativ().getPlural());

        assertEquals("pigliond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3b() {
        forms =  generator.generateConjugation("3b", "tutgier");

        assertEquals("tutgier", forms.getVerb().getInfinitiv());

        assertEquals("ia totg", forms.getVerb().getPreschent().getSing1());
        assertEquals("te totgas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella totga", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous tutgagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous tutgez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas totgan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia tutgiva", forms.getVerb().getImperfect().getSing1());
        assertEquals("te tutgivas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella tutgiva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous tutgivan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous tutgivas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas tutgivan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia totga", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te totgas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella totga", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous totgan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous totgas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas totgan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia tutgess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te tutgessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella tutgess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous tutgessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous tutgessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas tutgessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia tutgaro", forms.getVerb().getFutur().getSing1());
        assertEquals("te tutgarossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella tutgaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous tutgaron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous tutgarossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas tutgaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("tutgia", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("tutgeida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("tutgias", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("tutgeidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("totga!", forms.getVerb().getImperativ().getSingular());
        assertEquals("tutge!", forms.getVerb().getImperativ().getPlural());

        assertEquals("tutgond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3bGl() {
        forms =  generator.generateConjugation("3b", "anzuglier");

        assertEquals("anzuglier", forms.getVerb().getInfinitiv());

        assertEquals("ia anzogl", forms.getVerb().getPreschent().getSing1());
        assertEquals("te anzoglias", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella anzoglia", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous anzugliagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous anzuglez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas anzoglian", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia anzugliva", forms.getVerb().getImperfect().getSing1());
        assertEquals("te anzuglivas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella anzugliva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous anzuglivan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous anzuglivas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas anzuglivan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia anzoglia", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te anzoglias", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella anzoglia", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous anzoglian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous anzoglias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas anzoglian", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia anzugless", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te anzuglessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella anzugless", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous anzuglessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous anzuglessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas anzuglessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia anzugliaro", forms.getVerb().getFutur().getSing1());
        assertEquals("te anzugliarossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella anzugliaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous anzugliaron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous anzugliarossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas anzugliaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("anzuglia", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("anzugleida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("anzuglias", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("anzugleidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("anzoglia!", forms.getVerb().getImperativ().getSingular());
        assertEquals("anzugle!", forms.getVerb().getImperativ().getPlural());

        assertEquals("anzugliond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3c() {
        forms =  generator.generateConjugation("3c", "truschier");

        assertEquals("truschier", forms.getVerb().getInfinitiv());

        assertEquals("ia trousch", forms.getVerb().getPreschent().getSing1());
        assertEquals("te trouschas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella trouscha", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous truschagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous truschez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas trouschan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia truschiva", forms.getVerb().getImperfect().getSing1());
        assertEquals("te truschivas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella truschiva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous truschivan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous truschivas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas truschivan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia trouscha", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te trouschas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella trouscha", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous trouschan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous trouschas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas trouschan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia truschess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te truschessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella truschess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous truschessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous truschessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas truschessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia truscharo", forms.getVerb().getFutur().getSing1());
        assertEquals("te truscharossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella truscharo", forms.getVerb().getFutur().getSing3());
        assertEquals("nous truscharon", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous truscharossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas truscharon", forms.getVerb().getFutur().getPlural3());

        assertEquals("truschia", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("truscheida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("truschias", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("truscheidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("trouscha!", forms.getVerb().getImperativ().getSingular());
        assertEquals("trusche!", forms.getVerb().getImperativ().getPlural());

        assertEquals("truschond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3d() {
        forms =  generator.generateConjugation("3d", "modifitgier");

        assertEquals("modifitgier", forms.getVerb().getInfinitiv());

        assertEquals("ia modifitgesch", forms.getVerb().getPreschent().getSing1());
        assertEquals("te modifitgeschas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella modifitgescha", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous modifitgagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous modifitgez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas modifitgeschan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia modifitgiva", forms.getVerb().getImperfect().getSing1());
        assertEquals("te modifitgivas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella modifitgiva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous modifitgivan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous modifitgivas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas modifitgivan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia modifitgescha", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te modifitgeschas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella modifitgescha", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous modifitgeschan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous modifitgeschas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas modifitgeschan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia modifitgess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te modifitgessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella modifitgess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous modifitgessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous modifitgessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas modifitgessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia modifitgaro", forms.getVerb().getFutur().getSing1());
        assertEquals("te modifitgarossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella modifitgaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous modifitgaron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous modifitgarossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas modifitgaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("modifitgia", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("modifitgeida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("modifitgias", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("modifitgeidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("modifitgescha!", forms.getVerb().getImperativ().getSingular());
        assertEquals("modifitge!", forms.getVerb().getImperativ().getPlural());

        assertEquals("modifitgond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4() {
        forms =  generator.generateConjugation("4", "tigneir");

        assertEquals("tigneir", forms.getVerb().getInfinitiv());

        assertEquals("ia tign", forms.getVerb().getPreschent().getSing1());
        assertEquals("te tignas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella tigna", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous tignagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous tignez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas tignan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia tigneva", forms.getVerb().getImperfect().getSing1());
        assertEquals("te tignevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella tigneva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous tignevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous tignevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas tignevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia tigna", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te tignas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella tigna", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous tignan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous tignas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas tignan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia tigness", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te tignessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella tigness", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous tignessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous tignessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas tignessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia tignaro", forms.getVerb().getFutur().getSing1());
        assertEquals("te tignarossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella tignaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous tignaron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous tignarossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas tignaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("tignia", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("tigneida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("tignias", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("tigneidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("tigna!", forms.getVerb().getImperativ().getSingular());
        assertEquals("tigne!", forms.getVerb().getImperativ().getPlural());

        assertEquals("tignond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4a() {
        forms =  generator.generateConjugation("4a", "tameir");

        assertEquals("tameir", forms.getVerb().getInfinitiv());

        assertEquals("ia tem", forms.getVerb().getPreschent().getSing1());
        assertEquals("te temas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella tema", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous tamagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous tamez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas teman", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia tameva", forms.getVerb().getImperfect().getSing1());
        assertEquals("te tamevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella tameva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous tamevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous tamevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas tamevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia tema", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te temas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella tema", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous teman", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous temas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas teman", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia tamess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te tamessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella tamess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous tamessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous tamessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas tamessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia tamaro", forms.getVerb().getFutur().getSing1());
        assertEquals("te tamarossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella tamaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous tamaron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous tamarossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas tamaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("tamia", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("tameida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("tamias", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("tameidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("tema!", forms.getVerb().getImperativ().getSingular());
        assertEquals("tame!", forms.getVerb().getImperativ().getPlural());

        assertEquals("tamond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule5() {
        forms =  generator.generateConjugation("5", "tanscher");

        assertEquals("tanscher", forms.getVerb().getInfinitiv());

        assertEquals("ia tansch", forms.getVerb().getPreschent().getSing1());
        assertEquals("te tanschas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella tanscha", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous tanschagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous tanschez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas tanschan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia tanscheva", forms.getVerb().getImperfect().getSing1());
        assertEquals("te tanschevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella tanscheva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous tanschevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous tanschevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas tanschevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia tanscha", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te tanschas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella tanscha", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous tanschan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous tanschas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas tanschan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia tanschess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te tanschessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella tanschess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous tanschessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous tanschessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas tanschessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia tanscharo", forms.getVerb().getFutur().getSing1());
        assertEquals("te tanscharossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella tanscharo", forms.getVerb().getFutur().getSing3());
        assertEquals("nous tanscharon", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous tanscharossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas tanscharon", forms.getVerb().getFutur().getPlural3());

        assertEquals("tanschia", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("tanscheida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("tanschias", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("tanscheidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("tanscha!", forms.getVerb().getImperativ().getSingular());
        assertEquals("tansche!", forms.getVerb().getImperativ().getPlural());

        assertEquals("tanschond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule5a() {
        forms =  generator.generateConjugation("5a", "vender");

        assertEquals("vender", forms.getVerb().getInfinitiv());

        assertEquals("ia vend", forms.getVerb().getPreschent().getSing1());
        assertEquals("te vendas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella venda", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous vandagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous vandez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas vendan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia vandeva", forms.getVerb().getImperfect().getSing1());
        assertEquals("te vandevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella vandeva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous vandevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous vandevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas vandevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia venda", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te vendas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella venda", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous vendan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous vendas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas vendan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia vandess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te vandessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella vandess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous vandessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous vandessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas vandessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia vandaro", forms.getVerb().getFutur().getSing1());
        assertEquals("te vandarossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vandaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous vandaron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous vandarossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vandaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("vandia", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("vandeida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("vandias", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("vandeidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("venda!", forms.getVerb().getImperativ().getSingular());
        assertEquals("vande!", forms.getVerb().getImperativ().getPlural());

        assertEquals("vandond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule5b() {
        forms =  generator.generateConjugation("5b", "cloder");

        assertEquals("cloder", forms.getVerb().getInfinitiv());

        assertEquals("ia clod", forms.getVerb().getPreschent().getSing1());
        assertEquals("te clodas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella cloda", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous cludagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous cludez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas clodan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia cludeva", forms.getVerb().getImperfect().getSing1());
        assertEquals("te cludevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella cludeva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous cludevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous cludevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas cludevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia cloda", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te clodas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella cloda", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous clodan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous clodas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas clodan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia cludess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te cludessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella cludess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous cludessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous cludessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas cludessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia cludaro", forms.getVerb().getFutur().getSing1());
        assertEquals("te cludarossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella cludaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous cludaron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous cludarossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas cludaron", forms.getVerb().getFutur().getPlural3());

        /*
        assertEquals("clos", forms.getVerb().getParticipperfect().getMs());
        assertEquals("closa", forms.getVerb().getParticipperfect().getFs());
        assertEquals("clos", forms.getVerb().getParticipperfect().getMp());
        assertEquals("closas", forms.getVerb().getParticipperfect().getFp());
         */

        assertEquals("cloda!", forms.getVerb().getImperativ().getSingular());
        assertEquals("clude!", forms.getVerb().getImperativ().getPlural());

        assertEquals("cludond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule5c() {
        forms =  generator.generateConjugation("5c", "veiver");

        assertEquals("veiver", forms.getVerb().getInfinitiv());

        assertEquals("ia veiv", forms.getVerb().getPreschent().getSing1());
        assertEquals("te veivas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella veiva", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous vivagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous vivez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas veivan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia viveva", forms.getVerb().getImperfect().getSing1());
        assertEquals("te vivevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella viveva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous vivevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous vivevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas vivevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia veiva", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te veivas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella veiva", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous veivan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous veivas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas veivan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia vivess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te vivessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella vivess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous vivessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous vivessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas vivessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia vivaro", forms.getVerb().getFutur().getSing1());
        assertEquals("te vivarossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vivaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous vivaron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous vivarossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vivaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("vivia", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("viveida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("vivias", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("viveidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("veiva!", forms.getVerb().getImperativ().getSingular());
        assertEquals("vive!", forms.getVerb().getImperativ().getPlural());

        assertEquals("vivond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule6() {
        forms =  generator.generateConjugation("6", "parteir");

        assertEquals("parteir", forms.getVerb().getInfinitiv());

        assertEquals("ia part", forms.getVerb().getPreschent().getSing1());
        assertEquals("te partas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella parta", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous partign", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous partiz", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas partan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia partiva", forms.getVerb().getImperfect().getSing1());
        assertEquals("te partivas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella partiva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous partivan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous partivas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas partivan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia parta", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te partas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella parta", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous partan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous partas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas partan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia partiss", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te partissas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella partiss", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous partissan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous partissas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas partissan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia partiro", forms.getVerb().getFutur().getSing1());
        assertEquals("te partirossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella partiro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous partiron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous partirossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas partiron", forms.getVerb().getFutur().getPlural3());

        assertEquals("partia", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("parteida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("partias", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("parteidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("parta!", forms.getVerb().getImperativ().getSingular());
        assertEquals("parti!", forms.getVerb().getImperativ().getPlural());

        assertEquals("partond/partend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule6a() {
        forms =  generator.generateConjugation("6a", "bargeir");

        assertEquals("bargeir", forms.getVerb().getInfinitiv());

        assertEquals("ia bratg", forms.getVerb().getPreschent().getSing1());
        assertEquals("te bragias", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella bragia", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous bargign", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous bargiz", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas bragian", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia bargiva", forms.getVerb().getImperfect().getSing1());
        assertEquals("te bargivas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella bargiva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous bargivan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous bargivas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas bargivan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia bragia", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te bragias", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella bragia", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous bragian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous bragias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas bragian", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia bargiss", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te bargissas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella bargiss", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous bargissan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous bargissas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas bargissan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia bargiro", forms.getVerb().getFutur().getSing1());
        assertEquals("te bargirossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella bargiro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous bargiron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous bargirossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas bargiron", forms.getVerb().getFutur().getPlural3());

        assertEquals("bargia", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("bargeida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("bargias", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("bargeidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("bragia!", forms.getVerb().getImperativ().getSingular());
        assertEquals("bargi!", forms.getVerb().getImperativ().getPlural());

        assertEquals("bargiond/bargend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule6b() {
        forms =  generator.generateConjugation("6b", "darveir");

        assertEquals("darveir", forms.getVerb().getInfinitiv());

        assertEquals("ia derv", forms.getVerb().getPreschent().getSing1());
        assertEquals("te dervas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella derva", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous darvign", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous darviz", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas dervan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia darviva", forms.getVerb().getImperfect().getSing1());
        assertEquals("te darvivas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella darviva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous darvivan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous darvivas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas darvivan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia derva", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te dervas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella derva", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous dervan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous dervas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas dervan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia darviss", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te darvissas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella darviss", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous darvissan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous darvissas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas darvissan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia darviro", forms.getVerb().getFutur().getSing1());
        assertEquals("te darvirossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella darviro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous darviron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous darvirossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas darviron", forms.getVerb().getFutur().getPlural3());

        /*
        assertEquals("davert", forms.getVerb().getParticipperfect().getMs());
        assertEquals("daverta", forms.getVerb().getParticipperfect().getFs());
        assertEquals("daverts", forms.getVerb().getParticipperfect().getMp());
        assertEquals("davertas", forms.getVerb().getParticipperfect().getFp());
         */

        assertEquals("derva!", forms.getVerb().getImperativ().getSingular());
        assertEquals("darvi!", forms.getVerb().getImperativ().getPlural());

        assertEquals("darvond/darvend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule6c() {
        forms =  generator.generateConjugation("6c", "sgrigneir");

        assertEquals("sgrigneir", forms.getVerb().getInfinitiv());

        assertEquals("ia sgregn", forms.getVerb().getPreschent().getSing1());
        assertEquals("te sgregnas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella sgregna", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous sgrignign", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous sgrigniz", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas sgregnan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia sgrigniva", forms.getVerb().getImperfect().getSing1());
        assertEquals("te sgrignivas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella sgrigniva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous sgrignivan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous sgrignivas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas sgrignivan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia sgregna", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te sgregnas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella sgregna", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous sgregnan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous sgregnas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas sgregnan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia sgrigniss", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te sgrignissas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella sgrigniss", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous sgrignissan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous sgrignissas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas sgrignissan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia sgrigniro", forms.getVerb().getFutur().getSing1());
        assertEquals("te sgrignirossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella sgrigniro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous sgrigniron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous sgrignirossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas sgrigniron", forms.getVerb().getFutur().getPlural3());

        assertEquals("sgrignia", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("sgrigneida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("sgrignias", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("sgrigneidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("sgregna!", forms.getVerb().getImperativ().getSingular());
        assertEquals("sgrigni!", forms.getVerb().getImperativ().getPlural());

        assertEquals("sgrignond/sgrignend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule6d() {
        forms =  generator.generateConjugation("6d", "bugleir");

        assertEquals("bugleir", forms.getVerb().getInfinitiv());

        assertEquals("ia bogl", forms.getVerb().getPreschent().getSing1());
        assertEquals("te boglias", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella boglia", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous buglign", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous bugliz", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas boglian", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia bugliva", forms.getVerb().getImperfect().getSing1());
        assertEquals("te buglivas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella bugliva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous buglivan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous buglivas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas buglivan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia boglia", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te boglias", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella boglia", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous boglian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous boglias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas boglian", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia bugliss", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te buglissas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella bugliss", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous buglissan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous buglissas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas buglissan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia bugliro", forms.getVerb().getFutur().getSing1());
        assertEquals("te buglirossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella bugliro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous bugliron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous buglirossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas bugliron", forms.getVerb().getFutur().getPlural3());

        assertEquals("buglia", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("bugleida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("buglias", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("bugleidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("boglia!", forms.getVerb().getImperativ().getSingular());
        assertEquals("bugli!", forms.getVerb().getImperativ().getPlural());

        assertEquals("buglond/buglend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule6e() {
        forms =  generator.generateConjugation("6e", "santeir");

        assertEquals("santeir", forms.getVerb().getInfinitiv());

        assertEquals("ia saint", forms.getVerb().getPreschent().getSing1());
        assertEquals("te saintas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella sainta", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous santign", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous santiz", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas saintan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia santiva", forms.getVerb().getImperfect().getSing1());
        assertEquals("te santivas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella santiva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous santivan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous santivas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas santivan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia sainta", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te saintas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella sainta", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous saintan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous saintas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas saintan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia santiss", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te santissas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella santiss", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous santissan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous santissas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas santissan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia santiro", forms.getVerb().getFutur().getSing1());
        assertEquals("te santirossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella santiro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous santiron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous santirossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas santiron", forms.getVerb().getFutur().getPlural3());

        assertEquals("santia", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("santeida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("santias", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("santeidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("sainta!", forms.getVerb().getImperativ().getSingular());
        assertEquals("santi!", forms.getVerb().getImperativ().getPlural());

        assertEquals("santond/santend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule7() {
        forms =  generator.generateConjugation("7", "amplaneir");

        assertEquals("amplaneir", forms.getVerb().getInfinitiv());

        assertEquals("ia amplanesch", forms.getVerb().getPreschent().getSing1());
        assertEquals("te amplaneschas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella amplanescha", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous amplanign", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous amplaniz", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas amplaneschan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia amplaniva", forms.getVerb().getImperfect().getSing1());
        assertEquals("te amplanivas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella amplaniva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous amplanivan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous amplanivas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas amplanivan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia amplanescha", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te amplaneschas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella amplanescha", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous amplaneschan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous amplaneschas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas amplaneschan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia amplaniss", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te amplanissas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella amplaniss", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous amplanissan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous amplanissas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas amplanissan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia amplaniro", forms.getVerb().getFutur().getSing1());
        assertEquals("te amplanirossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella amplaniro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous amplaniron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous amplanirossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas amplaniron", forms.getVerb().getFutur().getPlural3());

        assertEquals("amplania", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("amplaneida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("amplanias", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("amplaneidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("amplanescha!", forms.getVerb().getImperativ().getSingular());
        assertEquals("amplani!", forms.getVerb().getImperativ().getPlural());

        assertEquals("amplanond/amplanend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule8() {
        forms =  generator.generateConjugation("8", "abitar");

        assertEquals("abitar", forms.getVerb().getInfinitiv());

        assertEquals("ia abitesch", forms.getVerb().getPreschent().getSing1());
        assertEquals("te abiteschas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella abitescha", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous abitagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous abitez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas abiteschan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia abitava", forms.getVerb().getImperfect().getSing1());
        assertEquals("te abitavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella abitava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous abitavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous abitavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas abitavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia abitescha", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te abiteschas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella abitescha", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous abiteschan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous abiteschas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas abiteschan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia abitess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te abitessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella abitess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous abitessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous abitessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas abitessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia abitaro", forms.getVerb().getFutur().getSing1());
        assertEquals("te abitarossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella abitaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous abitaron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous abitarossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas abitaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("abito", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("abitada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("abitos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("abitadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("abitescha!", forms.getVerb().getImperativ().getSingular());
        assertEquals("abite!", forms.getVerb().getImperativ().getPlural());

        assertEquals("abitond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule9() {
        forms =  generator.generateConjugation("9", "numnar");

        assertEquals("numnar", forms.getVerb().getInfinitiv());

        assertEquals("ia nomn", forms.getVerb().getPreschent().getSing1());
        assertEquals("te nomnas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella nomna", forms.getVerb().getPreschent().getSing3());
        assertEquals("nous numnagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vous numnez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas nomnan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("ia numnava", forms.getVerb().getImperfect().getSing1());
        assertEquals("te numnavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella numnava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nous numnavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vous numnavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas numnavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("tg'ia nomna", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("tgi te nomnas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("tg'el/ella nomna", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("tgi nous nomnan", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("tgi vous nomnas", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("tg'els/ellas nomnan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ia numness", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("te numnessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella numness", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nous numnessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vous numnessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas numnessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("ia numnaro", forms.getVerb().getFutur().getSing1());
        assertEquals("te numnarossas", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella numnaro", forms.getVerb().getFutur().getSing3());
        assertEquals("nous numnaron", forms.getVerb().getFutur().getPlural1());
        assertEquals("vous numnarossas", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas numnaron", forms.getVerb().getFutur().getPlural3());

        assertEquals("numno", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("numnada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("numnos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("numnadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("nomna!", forms.getVerb().getImperativ().getSingular());
        assertEquals("numne!", forms.getVerb().getImperativ().getPlural());

        assertEquals("numnond", forms.getVerb().getGerundium());
    }
}
