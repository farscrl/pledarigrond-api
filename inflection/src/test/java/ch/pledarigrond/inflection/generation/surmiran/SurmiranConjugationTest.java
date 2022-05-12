package ch.pledarigrond.inflection.generation.surmiran;

import ch.pledarigrond.inflection.model.InflectionResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
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
