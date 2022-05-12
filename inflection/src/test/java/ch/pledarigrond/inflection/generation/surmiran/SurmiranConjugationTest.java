package ch.pledarigrond.inflection.generation.surmiran;

import ch.pledarigrond.inflection.model.InflectionResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class SurmiranConjugationTest {

    private static SurmiranConjugation generator;
    private InflectionResponse forms;

    @BeforeClass
    public static void initialize() {
        generator = new SurmiranConjugation();
    }

    @Test
    public void testRule1() {
        forms =  generator.generateConjugation("1", "cantar");

        Assert.assertEquals("cantar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia cant", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te cantas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella canta", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous cantagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous cantez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas cantan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia cantava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te cantavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella cantava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous cantavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous cantavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas cantavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia canta", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te cantas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella canta", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous cantan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous cantas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas cantan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia cantess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te cantessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella cantess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous cantessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous cantessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas cantessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia cantaro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te cantarossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella cantaro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous cantaron", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous cantarossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas cantaron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("canto", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("cantada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("cantos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("cantadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("canta!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("cante!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("cantond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1a() {
        forms =  generator.generateConjugation("1a", "tadlar");

        Assert.assertEquals("tadlar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia tedl", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te tedlas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella tedla", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous tadlagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous tadlez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas tedlan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia tadlava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te tadlavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella tadlava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous tadlavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous tadlavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas tadlavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia tedla", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te tedlas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella tedla", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous tedlan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous tedlas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas tedlan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia tadless", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te tadlessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella tadless", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous tadlessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous tadlessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas tadlessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia tadlaro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te tadlarossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella tadlaro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous tadlaron", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous tadlarossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas tadlaron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("tadlo", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("tadlada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("tadlos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("tadladas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("tedla!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("tadle!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("tadlond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1b() {
        forms =  generator.generateConjugation("1b", "fimar");

        Assert.assertEquals("fimar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia fem", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te femas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella fema", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous fimagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous fimez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas feman", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia fimava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te fimavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella fimava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous fimavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous fimavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas fimavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia fema", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te femas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella fema", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous feman", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous femas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas feman", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia fimess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te fimessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella fimess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous fimessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous fimessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas fimessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia fimaro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te fimarossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella fimaro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous fimaron", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous fimarossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas fimaron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("fimo", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("fimada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("fimos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("fimadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("fema!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("fime!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("fimond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1c() {
        forms =  generator.generateConjugation("1c", "pasar");

        Assert.assertEquals("pasar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia pais", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te paisas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella paisa", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous pasagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous pasez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas paisan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia pasava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te pasavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella pasava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous pasavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous pasavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas pasavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia paisa", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te paisas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella paisa", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous paisan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous paisas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas paisan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia pasess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te pasessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella pasess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous pasessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous pasessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas pasessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia pasaro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te pasarossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella pasaro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous pasaron", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous pasarossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas pasaron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("paso", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("pasada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("pasos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("pasadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("paisa!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("pase!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("pasond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1d() {
        forms =  generator.generateConjugation("1d", "midar");

        Assert.assertEquals("midar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia meid", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te meidas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella meida", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous midagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous midez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas meidan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia midava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te midavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella midava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous midavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous midavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas midavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia meida", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te meidas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella meida", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous meidan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous meidas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas meidan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia midess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te midessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella midess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous midessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous midessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas midessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia midaro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te midarossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella midaro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous midaron", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous midarossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas midaron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("mido", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("midada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("midos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("midadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("meida!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("mide!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("midond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1e() {
        forms =  generator.generateConjugation("1e", "passantar");

        Assert.assertEquals("passantar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia passaint", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te passaintas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella passainta", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous passantagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous passantez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas passaintan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia passantava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te passantavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella passantava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous passantavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous passantavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas passantavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia passainta", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te passaintas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella passainta", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous passaintan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous passaintas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas passaintan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia passantess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te passantessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella passantess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous passantessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous passantessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas passantessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia passantaro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te passantarossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella passantaro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous passantaron", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous passantarossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas passantaron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("passanto", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("passantada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("passantos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("passantadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("passainta!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("passante!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("passantond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1f() {
        forms =  generator.generateConjugation("1f", "irtar");

        Assert.assertEquals("irtar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia iert", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te iertas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella ierta", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous irtagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous irtez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas iertan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia irtava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te irtavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella irtava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous irtavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous irtavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas irtavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia ierta", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te iertas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella ierta", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous iertan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous iertas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas iertan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia irtess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te irtessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella irtess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous irtessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous irtessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas irtessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia irtaro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te irtarossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella irtaro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous irtaron", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous irtarossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas irtaron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("irto", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("irtada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("irtos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("irtadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("ierta!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("irte!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("irtond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1g() {
        forms =  generator.generateConjugation("1g", "ampruar");

        Assert.assertEquals("ampruar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia amprov", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te amprovas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella amprova", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous ampruagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous ampruez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas amprovan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia ampruava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te ampruavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella ampruava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous ampruavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous ampruavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas ampruavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia amprova", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te amprovas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella amprova", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous amprovan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous amprovas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas amprovan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia ampruess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te ampruessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella ampruess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous ampruessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous ampruessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas ampruessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia ampruaro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te ampruarossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella ampruaro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous ampruaron", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous ampruarossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas ampruaron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("ampruo", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("ampruada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("ampruos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("ampruadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("amprova!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("amprue!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("ampruond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1h() {
        forms =  generator.generateConjugation("1h", "sfardar");

        Assert.assertEquals("sfardar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia sfreid", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te sfreidas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella sfreida", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous sfardagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous sfardez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas sfreidan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia sfardava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te sfardavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella sfardava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous sfardavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous sfardavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas sfardavan", forms.getInflectionValues().get("imperfectplural3")); // TODO: "sfradavan"?

        Assert.assertEquals("tg'ia sfreida", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te sfreidas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella sfreida", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous sfreidan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous sfreidas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas sfreidan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia sfardess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te sfardessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella sfardess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous sfardessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous sfardessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas sfardessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia sfardaro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te sfardarossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella sfardaro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous sfardaron", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous sfardarossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas sfardaron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("sfardo", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("sfardada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("sfardos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("sfardadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("sfreida!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("sfarde!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("sfardond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1i() {
        forms =  generator.generateConjugation("1i", "sgarmar");

        Assert.assertEquals("sgarmar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia sgrom", forms.getInflectionValues().get("preschentsing1")); // TODO: "sgroma"?
        Assert.assertEquals("te sgromas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella sgroma", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous sgarmagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous sgarmez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas sgroman", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia sgarmava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te sgarmavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella sgarmava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous sgarmavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous sgarmavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas sgarmavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia sgroma", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te sgromas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella sgroma", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous sgroman", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous sgromas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas sgroman", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia sgarmess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te sgarmessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella sgarmess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous sgarmessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous sgarmessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas sgarmessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia sgarmaro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te sgarmarossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella sgarmaro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous sgarmaron", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous sgarmarossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas sgarmaron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("sgarmo", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("sgarmada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("sgarmos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("sgarmadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("sgroma!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("sgarme!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("sgarmond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1j() {
        forms =  generator.generateConjugation("1j", "splanar");

        Assert.assertEquals("splanar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia splang", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te splangas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella splanga", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous splanagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous splanez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas splangan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia splanava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te splanavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella splanava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous splanavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous splanavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas splanavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia splanga", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te splangas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella splanga", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous splangan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous splangas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas splangan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia splaness", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te splanessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella splaness", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous splanessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous splanessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas splanessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia splanaro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te splanarossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella splanaro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous splanaron", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous splanarossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas splanaron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("splano", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("splanada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("splanos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("splanadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("splanga!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("splane!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("splanond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1k() {
        forms =  generator.generateConjugation("1k", "perdunar");

        Assert.assertEquals("perdunar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia perdung", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te perdungas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella perdunga", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous perdunagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous perdunez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas perdungan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia perdunava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te perdunavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella perdunava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous perdunavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous perdunavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas perdunavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia perdunga", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te perdungas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella perdunga", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous perdungan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous perdungas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas perdungan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia perduness", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te perdunessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella perduness", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous perdunessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous perdunessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas perdunessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia perdunaro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te perdunarossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella perdunaro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous perdunaron", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous perdunarossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas perdunaron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("perduno", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("perdunada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("perdunos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("perdunadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("perdunga!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("perdune!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("perdunond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1l() {
        forms =  generator.generateConjugation("1l", "manar");

        Assert.assertEquals("manar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia magn", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te magnas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella magna", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous managn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous manez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas magnan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia manava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te manavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella manava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous manavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous manavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas manavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia magna", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te magnas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella magna", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous magnan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous magnas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas magnan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia maness", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te manessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella maness", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous manessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous manessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas manessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia manaro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te manarossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella manaro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous manaron", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous manarossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas manaron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("mano", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("manada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("manos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("manadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("magna!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("mane!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("manond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1m() {
        forms =  generator.generateConjugation("1m", "pinar");

        Assert.assertEquals("pinar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia pegn", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te pegnas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella pegna", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous pinagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous pinez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas pegnan", forms.getInflectionValues().get("preschentplural3")); // TODO: "pignan"?

        Assert.assertEquals("ia pinava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te pinavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella pinava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous pinavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous pinavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas pinavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia pegna", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te pegnas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella pegna", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous pegnan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous pegnas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas pegnan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia piness", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te pinessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella piness", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous pinessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous pinessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas pinessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia pinaro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te pinarossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella pinaro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous pinaron", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous pinarossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas pinaron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("pino", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("pinada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("pinos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("pinadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("pegna!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("pine!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("pinond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1n() {
        forms =  generator.generateConjugation("1n", "schinar");

        Assert.assertEquals("schinar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia schign", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te schignas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella schigna", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous schinagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous schinez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas schignan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia schinava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te schinavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella schinava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous schinavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous schinavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas schinavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia schigna", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te schignas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella schigna", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous schignan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous schignas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas schignan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia schiness", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te schinessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella schiness", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous schinessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous schinessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas schinessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia schinaro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te schinarossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella schinaro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous schinaron", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous schinarossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas schinaron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("schino", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("schinada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("schinos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("schinadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("schigna!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("schine!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("schinond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1o() {
        forms =  generator.generateConjugation("1o", "anganar");

        Assert.assertEquals("anganar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia angion", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te angionas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella angiona", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous anganagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous anganez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas angionan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia anganava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te anganavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella anganava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous anganavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous anganavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas anganavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia angiona", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te angionas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella angiona", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous angionan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous angionas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas angionan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia anganess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te anganessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella anganess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous anganessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous anganessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas anganessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia anganaro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te anganarossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella anganaro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous anganaron", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous anganarossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas anganaron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("angano", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("anganada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("anganos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("anganadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("angiona!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("angane!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("anganond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1p() {
        forms =  generator.generateConjugation("1p", "scanar");

        Assert.assertEquals("scanar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia stgon", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te stgonas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella stgona", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous scanagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous scanez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas stgonan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia scanava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te scanavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella scanava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous scanavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous scanavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas scanavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia stgona", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te stgonas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella stgona", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous stgonan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous stgonas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas stgonan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia scaness", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te scanessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella scaness", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous scanessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous scanessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas scanessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia scanaro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te scanarossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella scanaro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous scanaron", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous scanarossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas scanaron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("scano", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("scanada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("scanos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("scanadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("stgona!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("scane!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("scanond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    @Ignore
    public void testRule1q() {
        forms =  generator.generateConjugation("1q", "ruschanar");

        Assert.assertEquals("ruschanar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia raschung", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te raschungas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella raschunga", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous ruschanagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous ruschanez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas raschungan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia ruschanava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te ruschanavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella ruschanava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous ruschanavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous ruschanavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas ruschanavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia raschunga", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te raschungas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella raschunga", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous raschungan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous raschungas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas raschungan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia ruschaness", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te ruschanessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella ruschaness", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous ruschanessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous ruschanessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas ruschanessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia ruschanaro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te ruschanarossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella ruschanaro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous ruschanaron", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous ruschanarossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas ruschanaron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("ruschano", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("ruschanada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("ruschanos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("ruschanadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("raschunga!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("ruschane!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("ruschanond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1r() {
        forms =  generator.generateConjugation("1r", "declarar");

        Assert.assertEquals("declarar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia dacler", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te dacleras", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella daclera", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous declaragn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous declarez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas dacleran", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia declarava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te declaravas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella declarava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous declaravan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous declaravas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas declaravan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia daclera", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te dacleras", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella daclera", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous dacleran", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous dacleras", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas dacleran", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia declaress", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te declaressas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella declaress", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous declaressan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous declaressas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas declaressan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia declararo", forms.getInflectionValues().get("futursing1")); // TODO: "declaro"?
        Assert.assertEquals("te declararossas", forms.getInflectionValues().get("futursing2")); // TODO: "declarossas"?
        Assert.assertEquals("el/ella declararo", forms.getInflectionValues().get("futursing3")); // TODO: "declaro"?
        Assert.assertEquals("nous declararon", forms.getInflectionValues().get("futurplural1")); // TODO: "declaron"?
        Assert.assertEquals("vous declararossas", forms.getInflectionValues().get("futurplural2")); // TODO: "declarossas"?
        Assert.assertEquals("els/ellas declararon", forms.getInflectionValues().get("futurplural3")); // TODO: "declaron"?

        Assert.assertEquals("declaro", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("declarada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("declaros", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("declaradas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("daclera!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("declare!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("declarond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule2() {
        forms =  generator.generateConjugation("2", "lascher");

        Assert.assertEquals("lascher", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia lasch", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te laschas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella lascha", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous laschagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous laschez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas laschan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia lascheva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te laschevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella lascheva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous laschevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous laschevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas laschevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia lascha", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te laschas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella lascha", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous laschan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous laschas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas laschan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia laschess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te laschessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella laschess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous laschessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous laschessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas laschessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia lascharo", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te lascharossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella lascharo", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous lascharon", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous lascharossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas lascharon", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("laschea", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("lascheda", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("lascheas", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("laschedas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("lascha!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("lasche!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("laschond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    @Ignore
    public void testRule2a() {
        forms =  generator.generateConjugation("2a", "manager");

        Assert.assertEquals("manager", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia manetg", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te manegias", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella manegia", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous managiagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous managez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas manegian", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia manageva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te managevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella manageva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous managevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous managevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas managevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia manegia", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te manegias", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella manegia", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous manegian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous manegias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas manegian", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia managess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te managessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella managess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous managessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous managessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas managessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia managiaro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te managiarossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella managiaro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous managiaron", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous managiarossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas managiaron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("managea", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("manageda", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("manageas", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("managedas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("manegia!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("manage!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("managiond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    @Ignore
    public void testRule2b() {
        forms =  generator.generateConjugation("2b", "macager");

        Assert.assertEquals("macager", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia machetg", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te machegias", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella machegia", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous macagiagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous macagez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas machegian", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia macageva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te macagevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella macageva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous macagevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous macagevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas macagevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia machegia", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te machegias", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella machegia", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous machegian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous machegias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas machegian", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia macagess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te macagessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella macagess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous macagessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous macagessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas macagessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia macagiaro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te macagiarossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella macagiaro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous macagiaron", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous macagiarossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas macagiaron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals(" macagea", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals(" macageda", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals(" macageas", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals(" macagedas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals(" machegia", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals(" macage", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals(" macagiond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule3() {
        forms =  generator.generateConjugation("3", "cuntschier");

        Assert.assertEquals("cuntschier", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia cuntsch", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te cuntschas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella cuntscha", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous cuntschagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous cuntschez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas cuntschan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia cuntschiva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te cuntschivas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella cuntschiva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous cuntschivan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous cuntschivas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas cuntschivan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia cuntscha", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te cuntschas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella cuntscha", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous cuntschan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous cuntschas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas cuntschan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia cuntschess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te cuntschessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella cuntschess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous cuntschessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous cuntschessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas cuntschessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia cuntscharo", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te cuntscharossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella cuntscharo", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous cuntscharon", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous cuntscharossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas cuntscharon", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("cuntschia", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("cuntscheida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("cuntschias", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("cuntscheidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("cuntscha!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("cuntsche!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("cuntschond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    @Ignore
    public void testRule3a() {
        forms =  generator.generateConjugation("3a", "piglier");

        Assert.assertEquals("piglier", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia pegl", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te peglias", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella peglia", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous pigliagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous piglez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas peglian", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia pigliva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te piglivas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella pigliva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous piglivan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous piglivas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas piglivan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia peglia", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te peglias", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella peglia", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous peglian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous peglias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas peglian", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia pigless", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te piglessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella pigless", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous piglessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous piglessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas piglessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia pigliaro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te pigliarossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella pigliaro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous pigliaron", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous pigliarossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas pigliaron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("piglia", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("pigleida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("piglias", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("pigleidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("peglia!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("pigle!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("pigliond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule3b() {
        forms =  generator.generateConjugation("3b", "tutgier");

        Assert.assertEquals("tutgier", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia totg", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te totgas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella totga", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous tutgagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous tutgez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas totgan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia tutgiva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te tutgivas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella tutgiva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous tutgivan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous tutgivas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas tutgivan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia totga", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te totgas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella totga", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous totgan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous totgas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas totgan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia tutgess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te tutgessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella tutgess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous tutgessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous tutgessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas tutgessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia tutgaro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te tutgarossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella tutgaro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous tutgaron", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous tutgarossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas tutgaron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("tutgia", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("tutgeida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("tutgias", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("tutgeidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("totga!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("tutge!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("tutgond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule3c() {
        forms =  generator.generateConjugation("3c", "truschier");

        Assert.assertEquals("truschier", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia trousch", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te trouschas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella trouscha", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous truschagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous truschez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas trouschan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia truschiva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te truschivas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella truschiva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous truschivan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous truschivas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas truschivan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia trouscha", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te trouschas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella trouscha", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous trouschan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous trouschas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas trouschan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia truschess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te truschessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella truschess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous truschessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous truschessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas truschessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia truscharo", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te truscharossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella truscharo", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous truscharon", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous truscharossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas truscharon", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("truschia", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("truscheida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("truschias", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("truscheidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("trouscha!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("trusche!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("truschond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule4() {
        forms =  generator.generateConjugation("4", "tigneir");

        Assert.assertEquals("tigneir", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia tign", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te tignas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella tigna", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous tignagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous tignez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas tignan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia tigneva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te tignevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella tigneva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous tignevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous tignevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas tignevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia tigna", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te tignas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella tigna", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous tignan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous tignas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas tignan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia tigness", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te tignessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella tigness", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous tignessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous tignessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas tignessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia tignaro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te tignarossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella tignaro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous tignaron", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous tignarossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas tignaron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("tignia", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("tigneida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("tignias", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("tigneidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("tigna!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("tigne!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("tignond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule4a() {
        forms =  generator.generateConjugation("4a", "tameir");

        Assert.assertEquals("tameir", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia tem", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te temas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella tema", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous tamagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous tamez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas teman", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia tameva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te tamevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella tameva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous tamevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous tamevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas tamevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia tema", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te temas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella tema", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous teman", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous temas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas teman", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia tamess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te tamessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella tamess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous tamessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous tamessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas tamessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia tamaro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te tamarossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella tamaro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous tamaron", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous tamarossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas tamaron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("tamia", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("tameida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("tamias", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("tameidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("tema!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("tame!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("tamond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule5() {
        forms =  generator.generateConjugation("5", "tanscher");

        Assert.assertEquals("tanscher", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia tansch", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te tanschas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella tanscha", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous tanschagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous tanschez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas tanschan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia tanscheva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te tanschevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella tanscheva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous tanschevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous tanschevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas tanschevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia tanscha", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te tanschas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella tanscha", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous tanschan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous tanschas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas tanschan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia tanschess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te tanschessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella tanschess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous tanschessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous tanschessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas tanschessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia tanscharo", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te tanscharossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella tanscharo", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous tanscharon", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous tanscharossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas tanscharon", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("tanschia", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("tanscheida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("tanschias", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("tanscheidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("tanscha!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("tansche!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("tanschond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule5a() {
        forms =  generator.generateConjugation("5a", "vender");

        Assert.assertEquals("vender", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia vend", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te vendas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella venda", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous vandagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous vandez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas vendan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia vandeva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te vandevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella vandeva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous vandevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous vandevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas vandevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia venda", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te vendas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella venda", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous vendan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous vendas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas vendan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia vandess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te vandessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella vandess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous vandessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous vandessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas vandessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia vandaro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te vandarossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vandaro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous vandaron", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous vandarossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vandaron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("vandia", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("vandeida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("vandias", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("vandeidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("venda!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("vande!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("vandond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule5b() {
        forms =  generator.generateConjugation("5b", "cloder");

        Assert.assertEquals("cloder", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia clod", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te clodas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella cloda", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous cludagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous cludez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas clodan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia cludeva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te cludevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella cludeva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous cludevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous cludevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas cludevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia cloda", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te clodas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella cloda", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous clodan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous clodas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas clodan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia cludess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te cludessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella cludess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous cludessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous cludessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas cludessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia cludaro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te cludarossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella cludaro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous cludaron", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous cludarossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas cludaron", forms.getInflectionValues().get("futurplural3"));

        /*
        Assert.assertEquals("clos", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("closa", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("clos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("closas", forms.getInflectionValues().get("participperfectfp"));
         */

        Assert.assertEquals("cloda!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("clude!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("cludond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule5c() {
        forms =  generator.generateConjugation("5c", "veiver");

        Assert.assertEquals("veiver", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia veiv", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te veivas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella veiva", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous vivagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous vivez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas veivan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia viveva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te vivevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella viveva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous vivevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous vivevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas vivevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia veiva", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te veivas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella veiva", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous veivan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous veivas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas veivan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia vivess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te vivessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella vivess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous vivessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous vivessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas vivessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia vivaro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te vivarossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vivaro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous vivaron", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous vivarossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vivaron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("vivia", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("viveida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("vivias", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("viveidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("veiva!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("vive!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("vivond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule6() {
        forms =  generator.generateConjugation("6", "parteir");

        Assert.assertEquals("parteir", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia part", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te partas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella parta", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous partign", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous partiz", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas partan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia partiva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te partivas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella partiva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous partivan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous partivas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas partivan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia parta", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te partas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella parta", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous partan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous partas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas partan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia partiss", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te partissas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella partiss", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous partissan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous partissas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas partissan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia partiro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te partirossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella partiro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous partiron", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous partirossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas partiron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("partia", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("parteida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("partias", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("parteidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("parta!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("parti!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("partond/partend", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    @Ignore
    public void testRule6a() {
        forms =  generator.generateConjugation("6a", "bargeir");

        Assert.assertEquals("bargeir", forms.getInflectionValues().get("infinitiv"));

        //Assert.assertEquals("ia bratg", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te bragias", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella bragia", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous bargign", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous bargiz", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas bragian", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia bargiva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te bargivas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella bargiva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous bargivan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous bargivas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas bargivan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia bragia", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te bragias", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella bragia", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous bragian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous bragias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas bragian", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia bargiss", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te bargissas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella bargiss", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous bargissan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous bargissas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas bargissan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia bargiro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te bargirossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella bargiro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous bargiron", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous bargirossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas bargiron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("bargia", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("bargeida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("bargias", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("bargeidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("bragia!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("bargi!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("bargiond/bargend", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule6b() {
        forms =  generator.generateConjugation("6b", "darveir");

        Assert.assertEquals("darveir", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia derv", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te dervas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella derva", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous darvign", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous darviz", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas dervan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia darviva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te darvivas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella darviva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous darvivan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous darvivas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas darvivan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia derva", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te dervas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella derva", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous dervan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous dervas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas dervan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia darviss", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te darvissas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella darviss", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous darvissan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous darvissas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas darvissan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia darviro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te darvirossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella darviro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous darviron", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous darvirossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas darviron", forms.getInflectionValues().get("futurplural3"));

        /*
        Assert.assertEquals("davert", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("daverta", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("daverts", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("davertas", forms.getInflectionValues().get("participperfectfp"));
         */

        Assert.assertEquals("derva!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("darvi!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("darvond/darvend", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule6c() {
        forms =  generator.generateConjugation("6c", "sgrigneir");

        Assert.assertEquals("sgrigneir", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia sgregn", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te sgregnas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella sgregna", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous sgrignign", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous sgrigniz", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas sgregnan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia sgrigniva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te sgrignivas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella sgrigniva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous sgrignivan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous sgrignivas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas sgrignivan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia sgregna", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te sgregnas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella sgregna", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous sgregnan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous sgregnas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas sgregnan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia sgrigniss", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te sgrignissas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella sgrigniss", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous sgrignissan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous sgrignissas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas sgrignissan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia sgrigniro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te sgrignirossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella sgrigniro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous sgrigniron", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous sgrignirossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas sgrigniron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("sgrignia", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("sgrigneida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("sgrignias", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("sgrigneidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("sgregna!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("sgrigni!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("sgrignond/sgrignend", forms.getInflectionValues().get("gerundium")); // TODO: mo "sgrignond"?
    }

    @Test
    @Ignore
    public void testRule6d() {
        forms =  generator.generateConjugation("6d", "bugleir");

        Assert.assertEquals("bugleir", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia bogl", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te boglias", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella boglia", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous buglign", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous bugliz", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas boglian", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia bugliva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te buglivas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella bugliva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous buglivan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous buglivas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas buglivan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia boglia", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te boglias", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella boglia", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous boglian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous boglias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas boglian", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia bugliss", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te buglissas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella bugliss", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous buglissan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous buglissas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas buglissan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia bugliro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te buglirossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella bugliro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous bugliron", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous buglirossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas bugliron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("buglia", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("bugleida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("buglias", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("bugleidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("boglia!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("bugli!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("bugliond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule6e() {
        forms =  generator.generateConjugation("6e", "santeir");

        Assert.assertEquals("santeir", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia saint", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te saintas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella sainta", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous santign", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous santiz", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas saintan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia santiva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te santivas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella santiva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous santivan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous santivas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas santivan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia sainta", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te saintas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella sainta", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous saintan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous saintas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas saintan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia santiss", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te santissas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella santiss", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous santissan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous santissas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas santissan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia santiro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te santirossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella santiro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous santiron", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous santirossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas santiron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("santia", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("santeida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("santias", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("santeidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("sainta!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("santi!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("santond/santend", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule7() {
        forms =  generator.generateConjugation("7", "amplaneir");

        Assert.assertEquals("amplaneir", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia amplanesch", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te amplaneschas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella amplanescha", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous amplanign", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous amplaniz", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas amplaneschan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia amplaniva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te amplanivas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella amplaniva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous amplanivan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous amplanivas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas amplanivan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia amplanescha", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te amplaneschas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella amplanescha", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous amplaneschan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous amplaneschas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas amplaneschan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia amplaniss", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te amplanissas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella amplaniss", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous amplanissan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous amplanissas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas amplanissan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia amplaniro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te amplanirossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella amplaniro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous amplaniron", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous amplanirossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas amplaniron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("amplania", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("amplaneida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("amplanias", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("amplaneidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("amplanescha!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("amplani!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("amplanond/amplanend", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule8() {
        forms =  generator.generateConjugation("8", "abitar");

        Assert.assertEquals("abitar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia abitesch", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te abiteschas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella abitescha", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous abitagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous abitez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas abiteschan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia abitava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te abitavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella abitava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous abitavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous abitavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas abitavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia abitescha", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te abiteschas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella abitescha", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous abiteschan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous abiteschas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas abiteschan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia abitess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te abitessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella abitess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous abitessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous abitessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas abitessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia abitaro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te abitarossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella abitaro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous abitaron", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous abitarossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas abitaron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("abito", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("abitada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("abitos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("abitadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("abitescha!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("abite!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("abitond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule9() {
        forms =  generator.generateConjugation("9", "numnar");

        Assert.assertEquals("numnar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("ia nomn", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("te nomnas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella nomna", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nous numnagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vous numnez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas nomnan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("ia numnava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("te numnavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella numnava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nous numnavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vous numnavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas numnavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("tg'ia nomna", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("tgi te nomnas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("tg'el/ella nomna", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("tgi nous nomnan", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("tgi vous nomnas", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("tg'els/ellas nomnan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ia numness", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("te numnessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella numness", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nous numnessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vous numnessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas numnessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("ia numnaro", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("te numnarossas", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella numnaro", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nous numnaron", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vous numnarossas", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas numnaron", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("numno", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("numnada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("numnos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("numnadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("nomna!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("numne!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("numnond", forms.getInflectionValues().get("gerundium"));
    }
}
