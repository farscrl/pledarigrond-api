package ch.pledarigrond.inflection.generation.sutsilvan;

import ch.pledarigrond.inflection.model.InflectionResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class SutsilvanConjugationTest {

    private static SutsilvanConjugation generator;
    private InflectionResponse forms;

    @BeforeClass
    public static void initialize() {
        generator = new SutsilvanConjugation();
    }

    @Test
    public void testRule1() {
        forms =  generator.generateConjugation("1", "gidar");

        Assert.assertEquals("gidar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou gid\njou gidel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei gidas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella gida", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus gidagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus gidaz", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas gidan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou gidava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei gidavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella gidava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus gidavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus gidavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas gidavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou gidi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei gidias\nca tei gidas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella gidi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus gideian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus gideias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas gidian\nc'els/ellas gidan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou gidavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei gidavias\nca tei gidavas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella gidavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus gidavian\nca nus gidavan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus gidavias\nca vus gidavas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas gidavian\nc'els/ellas gidavan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou gidass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei gidassas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella gidass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus gidassan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus gidassas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas gidassan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou gidassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei gidassias\ntei gidassas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella gidassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus gidassian\nnus gidassan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus gidassias\nvus gidassas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas gidassian\nels/ellas gidassan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a gidar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a gidar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a gidar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a gidar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a gidar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a gidar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("gido", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("gidada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("gidos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("gidadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("gida!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("gidad!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("gidànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1a() {
        forms =  generator.generateConjugation("1a", "tarlar");

        Assert.assertEquals("tarlar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou tearl\njou tearlel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei tearlas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella tearla", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus tarlagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus tarlaz", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas tearlan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou tarlava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei tarlavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella tarlava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus tarlavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus tarlavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas tarlavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou tearli", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei tearlias\nca tei tearlas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella tearli", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus tarleian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus tarleias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas tearlian\nc'els/ellas tearlan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou tarlavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei tarlavias\nca tei tarlavas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella tarlavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus tarlavian\nca nus tarlavan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus tarlavias\nca vus tarlavas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas tarlavian\nc'els/ellas tarlavan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou tarlass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei tarlassas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella tarlass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus tarlassan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus tarlassas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas tarlassan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou tarlassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei tarlassias\ntei tarlassas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella tarlassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus tarlassian\nnus tarlassan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus tarlassias\nvus tarlassas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas tarlassian\nels/ellas tarlassan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a tarlar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a tarlar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a tarlar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a tarlar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a tarlar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a tarlar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("tarlo", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("tarlada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("tarlos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("tarladas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("tearla!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("tarlad!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("tarlànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1b() {
        forms =  generator.generateConjugation("1b", "pasar");

        Assert.assertEquals("pasar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou pes\njou pesel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei pesas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella pesa", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus pasagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus pasaz", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas pesan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou pasava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei pasavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella pasava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus pasavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus pasavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas pasavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou pesi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei pesias\nca tei pesas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella pesi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus paseian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus paseias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas pesian\nc'els/ellas pesan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou pasavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei pasavias\nca tei pasavas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella pasavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus pasavian\nca nus pasavan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus pasavias\nca vus pasavas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas pasavian\nc'els/ellas pasavan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou pasass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei pasassas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella pasass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus pasassan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus pasassas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas pasassan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou pasassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei pasassias\ntei pasassas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella pasassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus pasassian\nnus pasassan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus pasassias\nvus pasassas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas pasassian\nels/ellas pasassan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a pasar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a pasar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a pasar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a pasar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a pasar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a pasar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("paso", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("pasada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("pasos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("pasadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("pesa!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("pasad!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("pasànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1c() {
        forms =  generator.generateConjugation("1c", "clamar");

        Assert.assertEquals("clamar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou clom\njou clomel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei clomas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella cloma", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus clamagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus clamaz", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas cloman", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou clamava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei clamavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella clamava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus clamavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus clamavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas clamavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou clomi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei clomias\nca tei clomas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella clomi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus clameian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus clameias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas clomian\nc'els/ellas cloman", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou clamavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei clamavias\nca tei clamavas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella clamavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus clamavian\nca nus clamavan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus clamavias\nca vus clamavas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas clamavian\nc'els/ellas clamavan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou clamass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei clamassas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella clamass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus clamassan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus clamassas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas clamassan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou clamassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei clamassias\ntei clamassas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella clamassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus clamassian\nnus clamassan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus clamassias\nvus clamassas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas clamassian\nels/ellas clamassan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a clamar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a clamar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a clamar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a clamar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a clamar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a clamar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("clamo", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("clamada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("clamos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("clamadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("cloma!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("clamad!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("clamànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1d() {
        forms =  generator.generateConjugation("1d", "passantar");

        Assert.assertEquals("passantar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou passaint\njou passaintel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei passaintas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella passainta", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus passantagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus passantaz", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas passaintan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou passantava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei passantavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella passantava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus passantavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus passantavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas passantavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou passainti", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei passaintias\nca tei passaintas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella passainti", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus passanteian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus passanteias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas passaintian\nc'els/ellas passaintan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou passantavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei passantavias\nca tei passantavas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella passantavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus passantavian\nca nus passantavan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus passantavias\nca vus passantavas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas passantavian\nc'els/ellas passantavan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou passantass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei passantassas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella passantass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus passantassan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus passantassas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas passantassan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou passantassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei passantassias\ntei passantassas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella passantassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus passantassian\nnus passantassan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus passantassias\nvus passantassas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas passantassian\nels/ellas passantassan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a passantar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a passantar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a passantar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a passantar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a passantar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a passantar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("passanto", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("passantada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("passantos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("passantadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("passainta!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("passantad!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("passantànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1e() {
        forms =  generator.generateConjugation("1e", "irtar");

        Assert.assertEquals("irtar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou iert\njou iertel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei iertas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella ierta", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus irtagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus irtaz", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas iertan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou irtava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei irtavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella irtava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus irtavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus irtavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas irtavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou ierti", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei iertias\nca tei iertas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella ierti", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus irteian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus irteias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas iertian\nc'els/ellas iertan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou irtavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei irtavias\nca tei irtavas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella irtavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus irtavian\nca nus irtavan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus irtavias\nca vus irtavas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas irtavian\nc'els/ellas irtavan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou irtass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei irtassas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella irtass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus irtassan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus irtassas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas irtassan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou irtassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei irtassias\ntei irtassas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella irtassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus irtassian\nnus irtassan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus irtassias\nvus irtassas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas irtassian\nels/ellas irtassan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt ad irtar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans ad irtar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean ad irtar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn ad irtar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez ad irtar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan ad irtar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("irto", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("irtada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("irtos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("irtadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("ierta!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("irtad!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("irtànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1f() {
        forms =  generator.generateConjugation("1f", "fimar");

        Assert.assertEquals("fimar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou fem\njou femel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei femas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella fema", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus fimagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus fimaz", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas feman", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou fimava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei fimavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella fimava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus fimavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus fimavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas fimavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou femi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei femias\nca tei femas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella femi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus fimeian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus fimeias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas femian\nc'els/ellas feman", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou fimavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei fimavias\nca tei fimavas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella fimavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus fimavian\nca nus fimavan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus fimavias\nca vus fimavas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas fimavian\nc'els/ellas fimavan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou fimass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei fimassas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella fimass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus fimassan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus fimassas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas fimassan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou fimassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei fimassias\ntei fimassas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella fimassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus fimassian\nnus fimassan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus fimassias\nvus fimassas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas fimassian\nels/ellas fimassan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a fimar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a fimar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a fimar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a fimar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a fimar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a fimar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("fimo", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("fimada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("fimos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("fimadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("fema!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("fimad!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("fimànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1g() {
        forms =  generator.generateConjugation("1g", "scalinar");

        Assert.assertEquals("scalinar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou scalegn\njou scalegnel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei scalegnas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella scalegna", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus scalinagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus scalinaz", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas scalegnan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou scalinava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei scalinavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella scalinava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus scalinavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus scalinavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas scalinavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou scalegni", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei scalegnias\nca tei scalegnas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella scalegni", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus scalineian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus scalineias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas scalegnian\nc'els/ellas scalegnan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou scalinavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei scalinavias\nca tei scalinavas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella scalinavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus scalinavian\nca nus scalinavan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus scalinavias\nca vus scalinavas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas scalinavian\nc'els/ellas scalinavan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou scalinass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei scalinassas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella scalinass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus scalinassan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus scalinassas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas scalinassan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou scalinassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei scalinassias\ntei scalinassas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella scalinassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus scalinassian\nnus scalinassan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus scalinassias\nvus scalinassas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas scalinassian\nels/ellas scalinassan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a scalinar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a scalinar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a scalinar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a scalinar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a scalinar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a scalinar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("scalino", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("scalinada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("scalinos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("scalinadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("scalegna!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("scalinad!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("scalinànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1h() {
        forms =  generator.generateConjugation("1h", "luvrar");

        Assert.assertEquals("luvrar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou lavur\njou lavurel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei lavuras", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella lavura", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus luvragn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus luvraz", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas lavuran", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou luvrava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei luvravas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella luvrava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus luvravan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus luvravas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas luvravan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou lavuri", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei lavurias\nca tei lavuras", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella lavuri", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus luvreian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus luvreias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas lavurian\nc'els/ellas lavuran", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou luvravi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei luvravias\nca tei luvravas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella luvravi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus luvravian\nca nus luvravan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus luvravias\nca vus luvravas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas luvravian\nc'els/ellas luvravan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou luvrass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei luvrassas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella luvrass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus luvrassan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus luvrassas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas luvrassan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou luvrassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei luvrassias\ntei luvrassas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella luvrassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus luvrassian\nnus luvrassan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus luvrassias\nvus luvrassas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas luvrassian\nels/ellas luvrassan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a luvrar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a luvrar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a luvrar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a luvrar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a luvrar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a luvrar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("luvro", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("luvrada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("luvros", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("luvradas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("lavura!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("luvrad!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("luvrànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1i() {
        forms =  generator.generateConjugation("1i", "purtar");

        Assert.assertEquals("purtar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou port\njou portel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei portas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella porta", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus purtagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus purtaz", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas portan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou purtava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei purtavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella purtava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus purtavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus purtavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas purtavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou porti", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei portias\nca tei portas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella porti", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus purteian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus purteias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas portian\nc'els/ellas portan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou purtavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei purtavias\nca tei purtavas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella purtavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus purtavian\nca nus purtavan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus purtavias\nca vus purtavas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas purtavian\nc'els/ellas purtavan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou purtass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei purtassas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella purtass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus purtassan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus purtassas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas purtassan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou purtassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei purtassias\ntei purtassas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella purtassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus purtassian\nnus purtassan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus purtassias\nvus purtassas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas purtassian\nels/ellas purtassan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a purtar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a purtar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a purtar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a purtar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a purtar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a purtar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("purto", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("purtada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("purtos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("purtadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("porta!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("purtad!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("purtànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1j() {
        forms =  generator.generateConjugation("1j", "tschanar");

        Assert.assertEquals("tschanar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou tschagn\njou tschagnel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei tschagnas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella tschagna", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus tschanagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus tschanaz", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas tschagnan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou tschanava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei tschanavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella tschanava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus tschanavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus tschanavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas tschanavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou tschagni", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei tschagnias\nca tei tschagnas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella tschagni", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus tschaneian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus tschaneias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas tschagnian\nc'els/ellas tschagnan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou tschanavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei tschanavias\nca tei tschanavas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella tschanavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus tschanavian\nca nus tschanavan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus tschanavias\nca vus tschanavas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas tschanavian\nc'els/ellas tschanavan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou tschanass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei tschanassas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella tschanass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus tschanassan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus tschanassas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas tschanassan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou tschanassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei tschanassias\ntei tschanassas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella tschanassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus tschanassian\nnus tschanassan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus tschanassias\nvus tschanassas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas tschanassian\nels/ellas tschanassan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a tschanar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a tschanar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a tschanar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a tschanar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a tschanar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a tschanar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("tschano", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("tschanada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("tschanos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("tschanadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("tschagna!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("tschanad!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("tschanànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1k() {
        forms =  generator.generateConjugation("1k", "plantar");

        Assert.assertEquals("plantar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou plànt\njou plàntel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei plàntas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella plànta", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus plantagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus plantaz", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas plàntan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou plantava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei plantavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella plantava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus plantavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus plantavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas plantavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou plànti", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei plàntias\nca tei plàntas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella plànti", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus planteian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus planteias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas plàntian\nc'els/ellas plàntan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou plantavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei plantavias\nca tei plantavas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella plantavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus plantavian\nca nus plantavan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus plantavias\nca vus plantavas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas plantavian\nc'els/ellas plantavan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou plantass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei plantassas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella plantass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus plantassan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus plantassas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas plantassan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou plantassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei plantassias\ntei plantassas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella plantassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus plantassian\nnus plantassan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus plantassias\nvus plantassas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas plantassian\nels/ellas plantassan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a plantar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a plantar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a plantar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a plantar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a plantar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a plantar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("planto", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("plantada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("plantos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("plantadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("plànta!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("plantad!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("plantànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1l() {
        forms =  generator.generateConjugation("1l", "sunar");

        Assert.assertEquals("sunar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou sùn\njou sùnel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei sùnas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella sùna", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus sunagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus sunaz", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas sùnan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou sunava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei sunavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella sunava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus sunavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus sunavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas sunavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou sùni", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei sùnias\nca tei sùnas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella sùni", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus suneian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus suneias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas sùnian\nc'els/ellas sùnan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou sunavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei sunavias\nca tei sunavas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella sunavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus sunavian\nca nus sunavan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus sunavias\nca vus sunavas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas sunavian\nc'els/ellas sunavan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou sunass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei sunassas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella sunass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus sunassan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus sunassas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas sunassan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou sunassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei sunassias\ntei sunassas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella sunassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus sunassian\nnus sunassan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus sunassias\nvus sunassas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas sunassian\nels/ellas sunassan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a sunar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a sunar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a sunar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a sunar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a sunar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a sunar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("suno", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("sunada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("sunos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("sunadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("sùna!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("sunad!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("sunànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1m() {
        forms =  generator.generateConjugation("1m", "sfardar");

        Assert.assertEquals("sfardar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou sfred\njou sfredel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei sfredas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella sfreda", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus sfardagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus sfardaz", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas sfredan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou sfardava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei sfardavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella sfardava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus sfardavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus sfardavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas sfardavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou sfredi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei sfredias\nca tei sfredas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella sfredi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus sfardeian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus sfardeias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas sfredian\nc'els/ellas sfredan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou sfardavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei sfardavias\nca tei sfardavas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella sfardavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus sfardavian\nca nus sfardavan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus sfardavias\nca vus sfardavas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas sfardavian\nc'els/ellas sfardavan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou sfardass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei sfardassas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella sfardass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus sfardassan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus sfardassas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas sfardassan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou sfardassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei sfardassias\ntei sfardassas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella sfardassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus sfardassian\nnus sfardassan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus sfardassias\nvus sfardassas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas sfardassian\nels/ellas sfardassan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a sfardar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a sfardar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a sfardar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a sfardar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a sfardar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a sfardar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("sfardo", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("sfardada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("sfardos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("sfardadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("sfreda!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("sfardad!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("sfardànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1n() {
        forms =  generator.generateConjugation("1n", "sgarmar");

        Assert.assertEquals("sgarmar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou sgrom\njou sgromel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei sgromas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella sgroma", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus sgarmagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus sgarmaz", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas sgroman", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou sgarmava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei sgarmavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella sgarmava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus sgarmavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus sgarmavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas sgarmavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou sgromi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei sgromias\nca tei sgromas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella sgromi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus sgarmeian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus sgarmeias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas sgromian\nc'els/ellas sgroman", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou sgarmavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei sgarmavias\nca tei sgarmavas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella sgarmavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus sgarmavian\nca nus sgarmavan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus sgarmavias\nca vus sgarmavas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas sgarmavian\nc'els/ellas sgarmavan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou sgarmass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei sgarmassas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella sgarmass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus sgarmassan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus sgarmassas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas sgarmassan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou sgarmassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei sgarmassias\ntei sgarmassas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella sgarmassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus sgarmassian\nnus sgarmassan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus sgarmassias\nvus sgarmassas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas sgarmassian\nels/ellas sgarmassan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a sgarmar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a sgarmar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a sgarmar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a sgarmar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a sgarmar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a sgarmar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("sgarmo", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("sgarmada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("sgarmos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("sgarmadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("sgroma!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("sgarmad!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("sgarmànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1o() {
        forms =  generator.generateConjugation("1o", "sgartar");

        Assert.assertEquals("sgartar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou sgrat\njou sgratel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei sgratas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella sgrata", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus sgartagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus sgartaz", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas sgratan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou sgartava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei sgartavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella sgartava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus sgartavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus sgartavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas sgartavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou sgrati", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei sgratias\nca tei sgratas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella sgrati", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus sgarteian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus sgarteias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas sgratian\nc'els/ellas sgratan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou sgartavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei sgartavias\nca tei sgartavas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella sgartavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus sgartavian\nca nus sgartavan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus sgartavias\nca vus sgartavas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas sgartavian\nc'els/ellas sgartavan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou sgartass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei sgartassas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella sgartass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus sgartassan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus sgartassas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas sgartassan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou sgartassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei sgartassias\ntei sgartassas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella sgartassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus sgartassian\nnus sgartassan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus sgartassias\nvus sgartassas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas sgartassian\nels/ellas sgartassan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a sgartar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a sgartar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a sgartar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a sgartar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a sgartar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a sgartar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("sgarto", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("sgartada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("sgartos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("sgartadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("sgrata!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("sgartad!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("sgartànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1p() {
        forms =  generator.generateConjugation("1p", "cantar");

        Assert.assertEquals("cantar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou tgànt\njou tgàntel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei tgàntas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella tgànta", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus cantagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus cantaz", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas tgàntan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou cantava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei cantavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella cantava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus cantavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus cantavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas cantavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou tgànti", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei tgàntias\nca tei tgàntas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella tgànti", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus canteian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus canteias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas tgàntian\nc'els/ellas tgàntan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou cantavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei cantavias\nca tei cantavas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella cantavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus cantavian\nca nus cantavan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus cantavias\nca vus cantavas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas cantavian\nc'els/ellas cantavan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou cantass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei cantassas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella cantass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus cantassan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus cantassas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas cantassan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou cantassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei cantassias\ntei cantassas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella cantassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus cantassian\nnus cantassan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus cantassias\nvus cantassas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas cantassian\nels/ellas cantassan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a cantar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a cantar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a cantar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a cantar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a cantar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a cantar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("canto", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("cantada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("cantos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("cantadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("tgànta!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("cantad!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("cantànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1q() {
        forms =  generator.generateConjugation("1q", "anganar");

        Assert.assertEquals("anganar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou angon\njou angonel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei angonas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella angona", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus anganagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus anganaz", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas angonan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou anganava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei anganavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella anganava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus anganavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus anganavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas anganavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou angoni", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei angonias\nca tei angonas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella angoni", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus anganeian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus anganeias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas angonian\nc'els/ellas angonan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou anganavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei anganavias\nca tei anganavas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella anganavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus anganavian\nca nus anganavan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus anganavias\nca vus anganavas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas anganavian\nc'els/ellas anganavan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou anganass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei anganassas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella anganass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus anganassan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus anganassas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas anganassan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou anganassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei anganassias\ntei anganassas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella anganassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus anganassian\nnus anganassan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus anganassias\nvus anganassas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas anganassian\nels/ellas anganassan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt ad anganar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans ad anganar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean ad anganar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn ad anganar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez ad anganar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan ad anganar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("angano", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("anganada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("anganos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("anganadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("angona!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("anganad!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("anganànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule2() {
        forms =  generator.generateConjugation("2", "spargnear");

        Assert.assertEquals("spargnear", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou spargn\njou spargnel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei spargnas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella spargna", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus spargnagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus spargneaz", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas spargnan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou spargneava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei spargneavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella spargneava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus spargneavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus spargneavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas spargneavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou spargni", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei spargnias\nca tei spargnas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella spargni", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus spargneian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus spargneias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas spargnian\nc'els/ellas spargnan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou spargneavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei spargneavias\nca tei spargneavas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella spargneavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus spargneavian\nca nus spargneavan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus spargneavias\nca vus spargneavas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas spargneavian\nc'els/ellas spargneavan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou spargness", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei spargnessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella spargness", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus spargnessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus spargnessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas spargnessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou spargnessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei spargnessias\ntei spargnessas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella spargnessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus spargnessian\nnus spargnessan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus spargnessias\nvus spargnessas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas spargnessian\nels/ellas spargnessan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a spargnear", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a spargnear", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a spargnear", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a spargnear", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a spargnear", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a spargnear", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("spargnieu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("spargneada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("spargnieus", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("spargneadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("spargna!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("spargnead!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("spargnànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule2a() {
        forms =  generator.generateConjugation("2a", "samtgear");

        Assert.assertEquals("samtgear", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou semtg\njou semtgel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei semtgas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella semtga", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus samtgagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus samtgeaz", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas semtgan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou samtgeava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei samtgeavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella samtgeava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus samtgeavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus samtgeavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas samtgeavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou semtgi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei semtgias\nca tei semtgas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella semtgi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus samtgeian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus samtgeias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas semtgian\nc'els/ellas semtgan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou samtgeavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei samtgeavias\nca tei samtgeavas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella samtgeavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus samtgeavian\nca nus samtgeavan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus samtgeavias\nca vus samtgeavas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas samtgeavian\nc'els/ellas samtgeavan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou samtgess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei samtgessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella samtgess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus samtgessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus samtgessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas samtgessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou samtgessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei samtgessias\ntei samtgessas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella samtgessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus samtgessian\nnus samtgessan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus samtgessias\nvus samtgessas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas samtgessian\nels/ellas samtgessan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a samtgear", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a samtgear", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a samtgear", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a samtgear", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a samtgear", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a samtgear", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("samtgieu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("samtgeada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("samtgieus", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("samtgeadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("semtga!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("samtgead!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("samtgànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule2b() {
        forms =  generator.generateConjugation("2b", "spitgear");

        Assert.assertEquals("spitgear", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou spetg\njou spetgel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei spetgas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella spetga", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus spitgagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus spitgeaz", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas spetgan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou spitgeava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei spitgeavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella spitgeava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus spitgeavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus spitgeavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas spitgeavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou spetgi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei spetgias\nca tei spetgas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella spetgi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus spitgeian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus spitgeias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas spetgian\nc'els/ellas spetgan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou spitgeavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei spitgeavias\nca tei spitgeavas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella spitgeavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus spitgeavian\nca nus spitgeavan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus spitgeavias\nca vus spitgeavas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas spitgeavian\nc'els/ellas spitgeavan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou spitgess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei spitgessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella spitgess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus spitgessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus spitgessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas spitgessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou spitgessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei spitgessias\ntei spitgessas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella spitgessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus spitgessian\nnus spitgessan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus spitgessias\nvus spitgessas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas spitgessian\nels/ellas spitgessan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a spitgear", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a spitgear", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a spitgear", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a spitgear", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a spitgear", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a spitgear", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("spitgieu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("spitgeada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("spitgieus", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("spitgeadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("spetga!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("spitgead!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("spitgànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule2c() {
        forms =  generator.generateConjugation("2c", "cuptgear");

        Assert.assertEquals("cuptgear", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou coptg\njou coptgel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei coptgas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella coptga", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus cuptgagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus cuptgeaz", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas coptgan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou cuptgeava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei cuptgeavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella cuptgeava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus cuptgeavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus cuptgeavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas cuptgeavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou coptgi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei coptgias\nca tei coptgas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella coptgi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus cuptgeian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus cuptgeias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas coptgian\nc'els/ellas coptgan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou cuptgeavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei cuptgeavias\nca tei cuptgeavas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella cuptgeavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus cuptgeavian\nca nus cuptgeavan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus cuptgeavias\nca vus cuptgeavas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas cuptgeavian\nc'els/ellas cuptgeavan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou cuptgess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei cuptgessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella cuptgess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus cuptgessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus cuptgessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas cuptgessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou cuptgessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei cuptgessias\ntei cuptgessas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella cuptgessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus cuptgessian\nnus cuptgessan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus cuptgessias\nvus cuptgessas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas cuptgessian\nels/ellas cuptgessan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a cuptgear", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a cuptgear", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a cuptgear", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a cuptgear", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a cuptgear", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a cuptgear", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("cuptgieu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("cuptgeada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("cuptgieus", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("cuptgeadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("coptga!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("cuptgead!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("cuptgànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule2d() {
        forms =  generator.generateConjugation("2d", "tschartgear");

        Assert.assertEquals("tschartgear", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou tscheartg\njou tscheartgel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei tscheartgas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella tscheartga", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus tschartgagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus tschartgeaz", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas tscheartgan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou tschartgeava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei tschartgeavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella tschartgeava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus tschartgeavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus tschartgeavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas tschartgeavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou tscheartgi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei tscheartgias\nca tei tscheartgas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella tscheartgi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus tschartgeian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus tschartgeias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas tscheartgian\nc'els/ellas tscheartgan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou tschartgeavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei tschartgeavias\nca tei tschartgeavas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella tschartgeavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus tschartgeavian\nca nus tschartgeavan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus tschartgeavias\nca vus tschartgeavas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas tschartgeavian\nc'els/ellas tschartgeavan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou tschartgess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei tschartgessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella tschartgess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus tschartgessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus tschartgessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas tschartgessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou tschartgessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei tschartgessias\ntei tschartgessas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella tschartgessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus tschartgessian\nnus tschartgessan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus tschartgessias\nvus tschartgessas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas tschartgessian\nels/ellas tschartgessan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a tschartgear", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a tschartgear", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a tschartgear", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a tschartgear", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a tschartgear", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a tschartgear", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("tschartgieu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("tschartgeada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("tschartgieus", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("tschartgeadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("tscheartga!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("tschartgead!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("tschartgànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule2e() {
        forms =  generator.generateConjugation("2e", "svagliear");

        Assert.assertEquals("svagliear", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou svegl\njou svegliel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei sveglias", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella sveglia", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus svagliagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus svaglieaz", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas sveglian", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou svaglieava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei svaglieavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella svaglieava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus svaglieavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus svaglieavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas svaglieavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou svegli", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei sveglias", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella svegli", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus svaglieian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus svaglieias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas sveglian", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou svaglieavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei svaglieavias\nca tei svaglieavas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella svaglieavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus svaglieavian\nca nus svaglieavan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus svaglieavias\nca vus svaglieavas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas svaglieavian\nc'els/ellas svaglieavan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou svagliess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei svagliessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella svagliess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus svagliessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus svagliessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas svagliessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou svagliessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei svagliessias\ntei svagliessas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella svagliessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus svagliessian\nnus svagliessan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus svagliessias\nvus svagliessas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas svagliessian\nels/ellas svagliessan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a svagliear", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a svagliear", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a svagliear", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a svagliear", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a svagliear", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a svagliear", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("svaglieu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("svaglieada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("svaglieus", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("svaglieadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("sveglia!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("svagliead!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("svagliànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule2f() {
        forms =  generator.generateConjugation("2f", "spassagear");

        Assert.assertEquals("spassagear", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou spasseg\njou spassegel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei spassegias", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella spassegia", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus spassagiagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus spassageaz", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas spassegian", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou spassageava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei spassageavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella spassageava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus spassageavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus spassageavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas spassageavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou spassegi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei spassegias", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella spassegi", forms.getInflectionValues().get("conjunctivsing3"));
        //Assert.assertEquals("ca nus sapssegeian", forms.getInflectionValues().get("conjunctivplural1"));
        //Assert.assertEquals("ca vus spassegeias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas spassegian", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou spassageavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei spassageavias\nca tei spassageavas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella spassageavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus spassageavian\nca nus spassageavan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus spassageavias\nca vus spassageavas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas spassageavian\nc'els/ellas spassageavan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou spassagess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei spassagessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella spassagess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus spassagessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus spassagessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas spassagessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou spassagessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei spassagessias\ntei spassagessas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella spassagessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus spassagessian\nnus spassagessan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus spassagessias\nvus spassagessas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas spassagessian\nels/ellas spassagessan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a spassagear", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a spassagear", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a spassagear", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a spassagear", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a spassagear", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a spassagear", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("spassagieu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("spassageada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("spassagieus", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("spassageadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("spassegia!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("spassagead!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("spassagiànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule2g() {
        forms =  generator.generateConjugation("2g", "zambargear");

        Assert.assertEquals("zambargear", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou zambreg\njou zambregel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei zambregias", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella zambregia", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus zambargiagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus zambargeaz", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas zambregian", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou zambargeava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei zambargeavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella zambargeava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus zambargeavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus zambargeavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas zambargeavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou zambregi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei zambregias", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella zambregi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus zambargeian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus zambargeias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas zambregian", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou zambargeavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei zambargeavias\nca tei zambargeavas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella zambargeavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus zambargeavian\nca nus zambargeavan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus zambargeavias\nca vus zambargeavas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas zambargeavian\nc'els/ellas zambargeavan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou zambargess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei zambargessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella zambargess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus zambargessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus zambargessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas zambargessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou zambargessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei zambargessias\ntei zambargessas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella zambargessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus zambargessian\nnus zambargessan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus zambargessias\nvus zambargessas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas zambargessian\nels/ellas zambargessan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a zambargear", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a zambargear", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a zambargear", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a zambargear", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a zambargear", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a zambargear", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("zambargieu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("zambargeada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("zambargieus", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("zambargeadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("zambregia!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("zambargead!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("zambargiànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule3() {
        forms =  generator.generateConjugation("3", "proponer");

        Assert.assertEquals("proponer", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou propon\njou proponel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei proponas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella propona", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus proponagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus proponez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas proponan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou proponeva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei proponevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella proponeva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus proponevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus proponevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas proponevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou proponi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei proponias\nca tei proponas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella proponi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus proponeian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus proponeias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas proponian\nc'els/ellas proponan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou proponevi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei proponevias\nca tei proponevas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella proponevi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus proponevian\nca nus proponevan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus proponevias\nca vus proponevas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas proponevian\nc'els/ellas proponevan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou proponess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei proponessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella proponess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus proponessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus proponessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas proponessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou proponessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei proponessias\ntei proponessas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella proponessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus proponessian\nnus proponessan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus proponessias\nvus proponessas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas proponessian\nels/ellas proponessan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a proponer", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a proponer", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a proponer", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a proponer", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a proponer", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a proponer", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("proponieu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("proponida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("proponieus", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("proponidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("propona!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("proponed!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("proponànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule3a() {
        forms =  generator.generateConjugation("3a", "vender");

        Assert.assertEquals("vender", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou vend\njou vendel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei vendas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella venda", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus vandagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus vandez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas vendan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou vandeva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei vandevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella vandeva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus vandevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus vandevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas vandevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou vendi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei vendias\nca tei vendas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella vendi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus vandeian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus vandeias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas vendian\nc'els/ellas vendan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou vandevi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei vandevias\nca tei vandevas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella vandevi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus vandevian\nca nus vandevan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus vandevias\nca vus vandevas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas vandevian\nc'els/ellas vandevan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou vandess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei vandessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella vandess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus vandessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus vandessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas vandessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou vandessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei vandessias\ntei vandessas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella vandessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus vandessian\nnus vandessan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus vandessias\nvus vandessas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas vandessian\nels/ellas vandessan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a vender", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a vender", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a vender", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a vender", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a vender", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a vender", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("vandieu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("vandida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("vandieus", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("vandidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("venda!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("vanded!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("vandànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule3b() {
        forms =  generator.generateConjugation("3b", "dezeder");

        Assert.assertEquals("dezeder", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou dezed\njou dezedel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei dezedas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella dezeda", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus dezidagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus dezidez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas dezedan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou dezideva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei dezidevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella dezideva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus dezidevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus dezidevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas dezidevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou dezedi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei dezedias\nca tei dezedas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella dezedi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus dezideian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus dezideias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas dezedian\nc'els/ellas dezedan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou dezidevi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei dezidevias\nca tei dezidevas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella dezidevi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus dezidevian\nca nus dezidevan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus dezidevias\nca vus dezidevas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas dezidevian\nc'els/ellas dezidevan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou dezidess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei dezidessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella dezidess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus dezidessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus dezidessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas dezidessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou dezidessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei dezidessias\ntei dezidessas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella dezidessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus dezidessian\nnus dezidessan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus dezidessias\nvus dezidessas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas dezidessian\nels/ellas dezidessan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a dezeder", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a dezeder", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a dezeder", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a dezeder", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a dezeder", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a dezeder", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("dezidieu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("dezidida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("dezidieus", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("dezididas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("dezeda!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("dezided!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("dezidànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule3c() {
        forms =  generator.generateConjugation("3c", "pearder");

        Assert.assertEquals("pearder", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou peard\njou peardel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei peardas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella pearda", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus pardagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus pardez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas peardan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou pardeva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei pardevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella pardeva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus pardevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus pardevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas pardevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou peardi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei peardias\nca tei peardas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella peardi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus pardeian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus pardeias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas peardian\nc'els/ellas peardan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou pardevi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei pardevias\nca tei pardevas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella pardevi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus pardevian\nca nus pardevan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus pardevias\nca vus pardevas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas pardevian\nc'els/ellas pardevan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou pardess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei pardessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella pardess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus pardessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus pardessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas pardessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou pardessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei pardessias\ntei pardessas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella pardessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus pardessian\nnus pardessan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus pardessias\nvus pardessas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas pardessian\nels/ellas pardessan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a pearder", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a pearder", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a pearder", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a pearder", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a pearder", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a pearder", forms.getInflectionValues().get("futurplural3"));

        /*
        Example is irregular
        Assert.assertEquals("pears", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("pearsa", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("pears", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("pearsas", forms.getInflectionValues().get("participperfectfp"));
         */

        Assert.assertEquals("pearda!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("parded!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("pardànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule3d() {
        forms =  generator.generateConjugation("3d", "tànscher");

        Assert.assertEquals("tànscher", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou tànsch\njou tànschel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei tànschas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella tànscha", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus tanschagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus tanschez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas tànschan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou tanscheva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei tanschevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella tanscheva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus tanschevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus tanschevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas tanschevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou tànschi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei tànschias\nca tei tànschas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella tànschi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus tanscheian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus tanscheias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas tànschian\nc'els/ellas tànschan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou tanschevi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei tanschevias\nca tei tanschevas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella tanschevi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus tanschevian\nca nus tanschevan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus tanschevias\nca vus tanschevas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas tanschevian\nc'els/ellas tanschevan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou tanschess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei tanschessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella tanschess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus tanschessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus tanschessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas tanschessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou tanschessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei tanschessias\ntei tanschessas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella tanschessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus tanschessian\nnus tanschessan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus tanschessias\nvus tanschessas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas tanschessian\nels/ellas tanschessan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a tànscher", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a tànscher", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a tànscher", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a tànscher", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a tànscher", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a tànscher", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("tanschieu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("tanschida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("tanschieus", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("tanschidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("tànscha!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("tansched!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("tanschànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule3e() {
        forms =  generator.generateConjugation("3e", "porscher");

        Assert.assertEquals("porscher", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou porsch\njou porschel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei porschas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella porscha", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus purschagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus purschez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas porschan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou purscheva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei purschevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella purscheva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus purschevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus purschevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas purschevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou porschi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei porschias\nca tei porschas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella porschi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus purscheian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus purscheias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas porschian\nc'els/ellas porschan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou purschevi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei purschevias\nca tei purschevas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella purschevi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus purschevian\nca nus purschevan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus purschevias\nca vus purschevas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas purschevian\nc'els/ellas purschevan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou purschess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei purschessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella purschess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus purschessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus purschessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas purschessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou purschessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei purschessias\ntei purschessas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella purschessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus purschessian\nnus purschessan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus purschessias\nvus purschessas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas purschessian\nels/ellas purschessan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a porscher", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a porscher", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a porscher", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a porscher", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a porscher", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a porscher", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("purschieu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("purschida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("purschieus", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("purschidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("porscha!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("pursched!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("purschànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule3f() {
        forms =  generator.generateConjugation("3f", "liger");

        Assert.assertEquals("liger", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou lig\njou ligel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei ligias", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella ligia", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus ligiagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus ligez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas ligian", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou ligeva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei ligevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella ligeva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus ligevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus ligevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas ligevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou ligi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei ligias", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella ligi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus ligeian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus ligeias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas ligian", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou ligevi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei ligevias\nca tei ligevas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella ligevi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus ligevian\nca nus ligevan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus ligevias\nca vus ligevas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas ligevian\nc'els/ellas ligevan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou ligess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei ligessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella ligess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus ligessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus ligessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas ligessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou ligessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei ligessias\ntei ligessas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella ligessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus ligessian\nnus ligessan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus ligessias\nvus ligessas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas ligessian\nels/ellas ligessan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a liger", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a liger", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a liger", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a liger", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a liger", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a liger", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("ligieu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("ligida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("ligieus", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("ligidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("ligia!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("liged!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("ligiànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule4() {
        forms =  generator.generateConjugation("4", "partir");

        Assert.assertEquals("partir", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou part\njou partel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei partas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella parta", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus partagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus partez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas partan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou parteva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei partevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella parteva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus partevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus partevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas partevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou parti", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei partias\nca tei partas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella parti", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus parteian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus parteias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas partian\nc'els/ellas partan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou partevi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei partevias\nca tei partevas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella partevi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus partevian\nca nus partevan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus partevias\nca vus partevas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas partevian\nc'els/ellas partevan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou partess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei partessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella partess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus partessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus partessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas partessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou partessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei partessias\ntei partessas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella partessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus partessian\nnus partessan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus partessias\nvus partessas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas partessian\nels/ellas partessan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a partir", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a partir", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a partir", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a partir", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a partir", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a partir", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("partieu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("partida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("partieus", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("partidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("parta!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("parted!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("partànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule4a() {
        forms =  generator.generateConjugation("4a", "santir");

        Assert.assertEquals("santir", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou saint\njou saintel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei saintas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella sainta", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus santagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus santez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas saintan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou santeva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei santevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella santeva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus santevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus santevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas santevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou sainti", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei saintias\nca tei saintas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella sainti", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus santeian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus santeias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas saintian\nc'els/ellas saintan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou santevi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei santevias\nca tei santevas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella santevi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus santevian\nca nus santevan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus santevias\nca vus santevas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas santevian\nc'els/ellas santevan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou santess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei santessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella santess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus santessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus santessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas santessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou santessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei santessias\ntei santessas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella santessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus santessian\nnus santessan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus santessias\nvus santessas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas santessian\nels/ellas santessan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a santir", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a santir", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a santir", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a santir", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a santir", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a santir", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("santieu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("santida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("santieus", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("santidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("sainta!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("santed!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("santànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule4b() {
        forms =  generator.generateConjugation("4b", "sgrignir");

        Assert.assertEquals("sgrignir", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou sgregn\njou sgregnel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei sgregnas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella sgregna", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus sgrignagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus sgrignez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas sgregnan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou sgrigneva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei sgrignevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella sgrigneva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus sgrignevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus sgrignevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas sgrignevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou sgregni", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei sgregnias\nca tei sgregnas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella sgregni", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus sgrigneian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus sgrigneias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas sgregnian\nc'els/ellas sgregnan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou sgrignevi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei sgrignevias\nca tei sgrignevas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella sgrignevi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus sgrignevian\nca nus sgrignevan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus sgrignevias\nca vus sgrignevas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas sgrignevian\nc'els/ellas sgrignevan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou sgrigness", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei sgrignessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella sgrigness", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus sgrignessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus sgrignessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas sgrignessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou sgrignessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei sgrignessias\ntei sgrignessas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella sgrignessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus sgrignessian\nnus sgrignessan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus sgrignessias\nvus sgrignessas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas sgrignessian\nels/ellas sgrignessan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a sgrignir", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a sgrignir", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a sgrignir", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a sgrignir", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a sgrignir", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a sgrignir", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("sgrignieu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("sgrignida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("sgrignieus", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("sgrignidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("sgregna!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("sgrigned!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("sgrignànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule4c() {
        forms =  generator.generateConjugation("4c", "udir");

        Assert.assertEquals("udir", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou od\njou odel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei odas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella oda", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus udagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus udez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas odan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou udeva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei udevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella udeva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus udevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus udevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas udevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou odi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei odias\nca tei odas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella odi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus udeian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus udeias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas odian\nc'els/ellas odan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou udevi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei udevias\nca tei udevas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella udevi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus udevian\nca nus udevan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus udevias\nca vus udevas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas udevian\nc'els/ellas udevan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou udess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei udessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella udess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus udessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus udessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas udessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou udessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei udessias\ntei udessas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella udessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus udessian\nnus udessan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus udessias\nvus udessas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas udessian\nels/ellas udessan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt ad udir", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans ad udir", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean ad udir", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn ad udir", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez ad udir", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan ad udir", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("udieu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("udida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("udieus", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("udidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("oda!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("uded!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("udànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule4d() {
        forms =  generator.generateConjugation("4d", "bargir");

        Assert.assertEquals("bargir", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou brag\njou bragel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei bragias", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella bragia", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus bargiagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus bargez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas bragian", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou bargeva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei bargevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella bargeva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus bargevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus bargevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas bargevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou bragi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei bragias", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella bragi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus bargeian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus bargeias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas bragian", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou bargevi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei bargevias\nca tei bargevas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella bargevi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus bargevian\nca nus bargevan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus bargevias\nca vus bargevas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas bargevian\nc'els/ellas bargevan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou bargess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei bargessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella bargess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus bargessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus bargessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas bargessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou bargessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei bargessias\ntei bargessas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella bargessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus bargessian\nnus bargessan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus bargessias\nvus bargessas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas bargessian\nels/ellas bargessan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a bargir", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a bargir", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a bargir", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a bargir", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a bargir", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a bargir", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("bargieu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("bargida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("bargieus", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("bargidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("bragia!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("barged!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("bargiànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule4e() {
        forms =  generator.generateConjugation("4e", "ancurir");

        Assert.assertEquals("ancurir", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou anquier\njou anquierel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei anquieras", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella anquiera", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus ancuragn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus ancurez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas anquieran", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou ancureva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei ancurevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella ancureva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus ancurevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus ancurevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas ancurevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou anquieri", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei anquierias\nca tei anquieras", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella anquieri", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus ancureian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus ancureias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas anquierian\nc'els/ellas anquieran", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou ancurevi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei ancurevias\nca tei ancurevas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella ancurevi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus ancurevian\nca nus ancurevan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus ancurevias\nca vus ancurevas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas ancurevian\nc'els/ellas ancurevan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou ancuress", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei ancuressas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella ancuress", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus ancuressan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus ancuressas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas ancuressan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou ancuressi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei ancuressias\ntei ancuressas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella ancuressi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus ancuressian\nnus ancuressan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus ancuressias\nvus ancuressas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas ancuressian\nels/ellas ancuressan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt ad ancurir", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans ad ancurir", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean ad ancurir", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn ad ancurir", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez ad ancurir", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan ad ancurir", forms.getInflectionValues().get("futurplural3"));

        /*
        Irregular form
        Assert.assertEquals("ancuretg", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("ancuretga", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("ancuretgs", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("ancuretgas", forms.getInflectionValues().get("participperfectfp"));
         */

        Assert.assertEquals("anquiera!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("ancured!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("ancurànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule5() {
        forms =  generator.generateConjugation("5", "gratular");

        Assert.assertEquals("gratular", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou gratulesch\njou gratuleschel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei gratuleschas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella gratulescha", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus gratulagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus gratulaz", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas gratuleschan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou gratulava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei gratulavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella gratulava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus gratulavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus gratulavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas gratulavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou gratuleschi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei gratuleschias\nca tei gratuleschas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella gratuleschi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus gratuleian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus gratuleias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas gratuleschian\nc'els/ellas gratuleschan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou gratulavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei gratulavias\nca tei gratulavas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella gratulavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus gratulavian\nca nus gratulavan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus gratulavias\nca vus gratulavas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas gratulavian\nc'els/ellas gratulavan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou gratulass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei gratulassas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella gratulass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus gratulassan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus gratulassas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas gratulassan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou gratulassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei gratulassias\ntei gratulassas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella gratulassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus gratulassian\nnus gratulassan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus gratulassias\nvus gratulassas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas gratulassian\nels/ellas gratulassan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a gratular", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a gratular", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a gratular", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a gratular", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a gratular", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a gratular", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("gratulo", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("gratulada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("gratulos", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("gratuladas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("gratulescha!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("gratulad!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("gratulànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule6() {
        forms =  generator.generateConjugation("6", "inditgear");

        Assert.assertEquals("inditgear", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou inditgesch\njou inditgeschel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei inditgeschas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella inditgescha", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus inditgagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus inditgeaz", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas inditgeschan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou inditgeava", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei inditgeavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella inditgeava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus inditgeavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus inditgeavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas inditgeavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou inditgeschi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei inditgeschias\nca tei inditgeschas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella inditgeschi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus inditgeian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus inditgeias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas inditgeschian\nc'els/ellas inditgeschan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou inditgevi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei inditgevias\nca tei inditgevas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella inditgevi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus inditgevian\nca nus inditgevan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus inditgevias\nca vus inditgevas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas inditgevian\nc'els/ellas inditgevan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou inditgess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei inditgessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella inditgess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus inditgessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus inditgessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas inditgessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou inditgessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei inditgessias\ntei inditgessas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella inditgessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus inditgessian\nnus inditgessan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus inditgessias\nvus inditgessas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas inditgessian\nels/ellas inditgessan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt ad inditgear", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans ad inditgear", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean ad inditgear", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn ad inditgear", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez ad inditgear", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan ad inditgear", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("inditgieu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("inditgeada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("inditgieus", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("inditgeadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("inditgescha!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("inditgead!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("inditgànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule7() {
        forms =  generator.generateConjugation("7", "capir");

        Assert.assertEquals("capir", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou capesch\njou capeschel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei capeschas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella capescha", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus capagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus capez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas capeschan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou capeva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei capevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella capeva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus capevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus capevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas capevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou capeschi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei capeschias\nca tei capeschas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella capeschi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus capeian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus capeias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas capeschian\nc'els/ellas capeschan", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou capevi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei capevias\nca tei capevas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella capevi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus capevian\nca nus capevan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus capevias\nca vus capevas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas capevian\nc'els/ellas capevan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou capess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei capessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella capess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus capessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus capessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas capessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou capessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei capessias\ntei capessas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella capessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus capessian\nnus capessan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus capessias\nvus capessas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas capessian\nels/ellas capessan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a capir", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a capir", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a capir", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a capir", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a capir", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a capir", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("capieu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("capida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("capieus", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("capidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("capescha!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("caped!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("capànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule9() {
        forms =  generator.generateConjugation("9", "parer");

        Assert.assertEquals("parer", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou par\njou parel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei paras", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella para", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus paragn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus parez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas paran", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou pareva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei parevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella pareva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus parevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus parevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas parevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou pari", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei parias\nca tei paras", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella pari", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus pareian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus pareias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas parian\nc'els/ellas paran", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou parevi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei parevias\nca tei parevas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella parevi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus parevian\nca nus parevan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus parevias\nca vus parevas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas parevian\nc'els/ellas parevan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou paress", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei paressas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella paress", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus paressan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus paressas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas paressan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou paressi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei paressias\ntei paressas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella paressi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus paressian\nnus paressan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus paressias\nvus paressas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas paressian\nels/ellas paressan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a parer", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a parer", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a parer", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a parer", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a parer", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a parer", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("parieu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("parida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("parieus", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("paridas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("para!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("pared!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("parànd", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule9a() {
        forms =  generator.generateConjugation("9a", "tamer");

        Assert.assertEquals("tamer", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jou tem\njou temel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("tei temas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella tema", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus tamagn", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus tamez", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas teman", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jou tameva", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("tei tamevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella tameva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus tamevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus tamevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas tamevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("ca jou temi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("ca tei temias\nca tei temas", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("c'el/ella temi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("ca nus tameian", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("ca vus tameias", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("c'els/ellas temian\nc'els/ellas teman", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("ca jou tamevi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("ca tei tamevias\nca tei tamevas", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("c'el/ella tamevi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("ca nus tamevian\nca nus tamevan", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("ca vus tamevias\nca vus tamevas", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("c'els/ellas tamevian\nc'els/ellas tamevan", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jou tamess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("tei tamessas", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella tamess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus tamessan", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus tamessas", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas tamessan", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jou tamessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("tei tamessias\ntei tamessas", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella tamessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus tamessian\nnus tamessan", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus tamessias\nvus tamessas", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas tamessian\nels/ellas tamessan", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jou vignt a tamer", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("tei veans a tamer", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vean a tamer", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vagnagn a tamer", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vagnez a tamer", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vignan a tamer", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("tamieu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("tamida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("tamieus", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("tamidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("tema!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("tamed!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("tamànd", forms.getInflectionValues().get("gerundium"));
    }
}
