package ch.pledarigrond.inflection.verb.sutsilvan;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.verb.sutsilvan.SutsilvanConjugation;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class SutsilvanConjugationTest {

    private static SutsilvanConjugation generator;
    private InflectionDto forms;

    @BeforeClass
    public static void initialize() {
        generator = new SutsilvanConjugation();
    }

    @Test
    public void testRule1() {
        forms =  generator.generateConjugation("1", "gidar");

        Assert.assertEquals("gidar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou gid\njou gidel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei gidas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella gida", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus gidagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus gidaz", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas gidan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou gidava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei gidavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella gidava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus gidavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus gidavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas gidavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou gidi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei gidias\nca tei gidas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella gidi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus gideian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus gideias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas gidian\nc'els/ellas gidan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou gidavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei gidavias\nca tei gidavas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella gidavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus gidavian\nca nus gidavan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus gidavias\nca vus gidavas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas gidavian\nc'els/ellas gidavan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou gidass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei gidassas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella gidass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus gidassan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus gidassas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas gidassan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou gidassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei gidassias\ntei gidassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella gidassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus gidassian\nnus gidassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus gidassias\nvus gidassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas gidassian\nels/ellas gidassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a gidar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a gidar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a gidar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a gidar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a gidar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a gidar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("gido", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("gidada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("gidos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("gidadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("gida!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("gidad!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("gidànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1a() {
        forms =  generator.generateConjugation("1a", "tarlar");

        Assert.assertEquals("tarlar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou tearl\njou tearlel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei tearlas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella tearla", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus tarlagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus tarlaz", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas tearlan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou tarlava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei tarlavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella tarlava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus tarlavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus tarlavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas tarlavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou tearli", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei tearlias\nca tei tearlas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella tearli", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus tarleian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus tarleias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas tearlian\nc'els/ellas tearlan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou tarlavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei tarlavias\nca tei tarlavas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella tarlavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus tarlavian\nca nus tarlavan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus tarlavias\nca vus tarlavas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas tarlavian\nc'els/ellas tarlavan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou tarlass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei tarlassas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella tarlass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus tarlassan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus tarlassas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas tarlassan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou tarlassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei tarlassias\ntei tarlassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella tarlassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus tarlassian\nnus tarlassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus tarlassias\nvus tarlassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas tarlassian\nels/ellas tarlassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a tarlar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a tarlar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a tarlar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a tarlar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a tarlar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a tarlar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("tarlo", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("tarlada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("tarlos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("tarladas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("tearla!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("tarlad!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("tarlànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1b() {
        forms =  generator.generateConjugation("1b", "pasar");

        Assert.assertEquals("pasar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou pes\njou pesel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei pesas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella pesa", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus pasagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus pasaz", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas pesan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou pasava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei pasavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella pasava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus pasavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus pasavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas pasavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou pesi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei pesias\nca tei pesas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella pesi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus paseian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus paseias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas pesian\nc'els/ellas pesan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou pasavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei pasavias\nca tei pasavas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella pasavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus pasavian\nca nus pasavan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus pasavias\nca vus pasavas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas pasavian\nc'els/ellas pasavan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou pasass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei pasassas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella pasass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus pasassan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus pasassas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas pasassan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou pasassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei pasassias\ntei pasassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella pasassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus pasassian\nnus pasassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus pasassias\nvus pasassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas pasassian\nels/ellas pasassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a pasar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a pasar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a pasar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a pasar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a pasar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a pasar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("paso", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("pasada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("pasos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("pasadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("pesa!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("pasad!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("pasànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1c() {
        forms =  generator.generateConjugation("1c", "clamar");

        Assert.assertEquals("clamar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou clom\njou clomel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei clomas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella cloma", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus clamagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus clamaz", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas cloman", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou clamava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei clamavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella clamava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus clamavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus clamavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas clamavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou clomi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei clomias\nca tei clomas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella clomi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus clameian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus clameias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas clomian\nc'els/ellas cloman", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou clamavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei clamavias\nca tei clamavas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella clamavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus clamavian\nca nus clamavan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus clamavias\nca vus clamavas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas clamavian\nc'els/ellas clamavan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou clamass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei clamassas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella clamass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus clamassan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus clamassas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas clamassan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou clamassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei clamassias\ntei clamassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella clamassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus clamassian\nnus clamassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus clamassias\nvus clamassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas clamassian\nels/ellas clamassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a clamar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a clamar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a clamar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a clamar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a clamar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a clamar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("clamo", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("clamada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("clamos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("clamadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("cloma!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("clamad!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("clamànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1d() {
        forms =  generator.generateConjugation("1d", "passantar");

        Assert.assertEquals("passantar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou passaint\njou passaintel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei passaintas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella passainta", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus passantagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus passantaz", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas passaintan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou passantava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei passantavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella passantava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus passantavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus passantavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas passantavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou passainti", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei passaintias\nca tei passaintas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella passainti", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus passanteian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus passanteias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas passaintian\nc'els/ellas passaintan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou passantavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei passantavias\nca tei passantavas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella passantavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus passantavian\nca nus passantavan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus passantavias\nca vus passantavas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas passantavian\nc'els/ellas passantavan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou passantass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei passantassas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella passantass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus passantassan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus passantassas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas passantassan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou passantassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei passantassias\ntei passantassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella passantassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus passantassian\nnus passantassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus passantassias\nvus passantassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas passantassian\nels/ellas passantassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a passantar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a passantar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a passantar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a passantar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a passantar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a passantar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("passanto", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("passantada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("passantos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("passantadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("passainta!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("passantad!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("passantànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1e() {
        forms =  generator.generateConjugation("1e", "irtar");

        Assert.assertEquals("irtar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou iert\njou iertel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei iertas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella ierta", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus irtagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus irtaz", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas iertan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou irtava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei irtavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella irtava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus irtavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus irtavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas irtavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou ierti", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei iertias\nca tei iertas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella ierti", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus irteian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus irteias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas iertian\nc'els/ellas iertan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou irtavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei irtavias\nca tei irtavas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella irtavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus irtavian\nca nus irtavan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus irtavias\nca vus irtavas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas irtavian\nc'els/ellas irtavan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou irtass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei irtassas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella irtass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus irtassan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus irtassas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas irtassan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou irtassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei irtassias\ntei irtassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella irtassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus irtassian\nnus irtassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus irtassias\nvus irtassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas irtassian\nels/ellas irtassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt ad irtar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans ad irtar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean ad irtar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn ad irtar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez ad irtar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan ad irtar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("irto", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("irtada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("irtos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("irtadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("ierta!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("irtad!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("irtànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1f() {
        forms =  generator.generateConjugation("1f", "fimar");

        Assert.assertEquals("fimar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou fem\njou femel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei femas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella fema", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus fimagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus fimaz", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas feman", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou fimava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei fimavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella fimava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus fimavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus fimavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas fimavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou femi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei femias\nca tei femas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella femi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus fimeian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus fimeias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas femian\nc'els/ellas feman", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou fimavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei fimavias\nca tei fimavas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella fimavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus fimavian\nca nus fimavan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus fimavias\nca vus fimavas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas fimavian\nc'els/ellas fimavan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou fimass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei fimassas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella fimass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus fimassan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus fimassas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas fimassan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou fimassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei fimassias\ntei fimassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella fimassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus fimassian\nnus fimassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus fimassias\nvus fimassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas fimassian\nels/ellas fimassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a fimar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a fimar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a fimar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a fimar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a fimar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a fimar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("fimo", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("fimada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("fimos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("fimadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("fema!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("fimad!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("fimànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1g() {
        forms =  generator.generateConjugation("1g", "scalinar");

        Assert.assertEquals("scalinar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou scalegn\njou scalegnel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei scalegnas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella scalegna", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus scalinagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus scalinaz", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas scalegnan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou scalinava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei scalinavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella scalinava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus scalinavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus scalinavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas scalinavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou scalegni", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei scalegnias\nca tei scalegnas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella scalegni", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus scalineian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus scalineias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas scalegnian\nc'els/ellas scalegnan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou scalinavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei scalinavias\nca tei scalinavas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella scalinavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus scalinavian\nca nus scalinavan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus scalinavias\nca vus scalinavas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas scalinavian\nc'els/ellas scalinavan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou scalinass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei scalinassas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella scalinass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus scalinassan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus scalinassas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas scalinassan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou scalinassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei scalinassias\ntei scalinassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella scalinassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus scalinassian\nnus scalinassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus scalinassias\nvus scalinassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas scalinassian\nels/ellas scalinassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a scalinar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a scalinar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a scalinar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a scalinar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a scalinar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a scalinar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("scalino", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("scalinada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("scalinos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("scalinadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("scalegna!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("scalinad!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("scalinànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1h() {
        forms =  generator.generateConjugation("1h", "luvrar");

        Assert.assertEquals("luvrar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou lavur\njou lavurel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei lavuras", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella lavura", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus luvragn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus luvraz", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas lavuran", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou luvrava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei luvravas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella luvrava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus luvravan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus luvravas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas luvravan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou lavuri", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei lavurias\nca tei lavuras", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella lavuri", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus luvreian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus luvreias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas lavurian\nc'els/ellas lavuran", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou luvravi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei luvravias\nca tei luvravas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella luvravi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus luvravian\nca nus luvravan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus luvravias\nca vus luvravas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas luvravian\nc'els/ellas luvravan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou luvrass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei luvrassas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella luvrass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus luvrassan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus luvrassas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas luvrassan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou luvrassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei luvrassias\ntei luvrassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella luvrassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus luvrassian\nnus luvrassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus luvrassias\nvus luvrassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas luvrassian\nels/ellas luvrassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a luvrar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a luvrar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a luvrar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a luvrar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a luvrar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a luvrar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("luvro", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("luvrada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("luvros", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("luvradas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("lavura!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("luvrad!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("luvrànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1i() {
        forms =  generator.generateConjugation("1i", "purtar");

        Assert.assertEquals("purtar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou port\njou portel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei portas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella porta", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus purtagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus purtaz", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas portan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou purtava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei purtavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella purtava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus purtavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus purtavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas purtavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou porti", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei portias\nca tei portas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella porti", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus purteian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus purteias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas portian\nc'els/ellas portan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou purtavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei purtavias\nca tei purtavas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella purtavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus purtavian\nca nus purtavan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus purtavias\nca vus purtavas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas purtavian\nc'els/ellas purtavan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou purtass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei purtassas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella purtass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus purtassan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus purtassas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas purtassan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou purtassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei purtassias\ntei purtassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella purtassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus purtassian\nnus purtassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus purtassias\nvus purtassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas purtassian\nels/ellas purtassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a purtar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a purtar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a purtar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a purtar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a purtar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a purtar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("purto", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("purtada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("purtos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("purtadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("porta!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("purtad!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("purtànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1j() {
        forms =  generator.generateConjugation("1j", "tschanar");

        Assert.assertEquals("tschanar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou tschagn\njou tschagnel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei tschagnas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella tschagna", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus tschanagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus tschanaz", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas tschagnan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou tschanava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei tschanavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella tschanava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus tschanavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus tschanavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas tschanavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou tschagni", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei tschagnias\nca tei tschagnas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella tschagni", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus tschaneian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus tschaneias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas tschagnian\nc'els/ellas tschagnan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou tschanavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei tschanavias\nca tei tschanavas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella tschanavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus tschanavian\nca nus tschanavan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus tschanavias\nca vus tschanavas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas tschanavian\nc'els/ellas tschanavan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou tschanass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei tschanassas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella tschanass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus tschanassan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus tschanassas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas tschanassan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou tschanassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei tschanassias\ntei tschanassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella tschanassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus tschanassian\nnus tschanassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus tschanassias\nvus tschanassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas tschanassian\nels/ellas tschanassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a tschanar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a tschanar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a tschanar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a tschanar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a tschanar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a tschanar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("tschano", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("tschanada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("tschanos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("tschanadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("tschagna!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("tschanad!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("tschanànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1k() {
        forms =  generator.generateConjugation("1k", "plantar");

        Assert.assertEquals("plantar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou plànt\njou plàntel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei plàntas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella plànta", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus plantagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus plantaz", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas plàntan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou plantava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei plantavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella plantava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus plantavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus plantavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas plantavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou plànti", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei plàntias\nca tei plàntas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella plànti", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus planteian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus planteias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas plàntian\nc'els/ellas plàntan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou plantavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei plantavias\nca tei plantavas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella plantavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus plantavian\nca nus plantavan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus plantavias\nca vus plantavas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas plantavian\nc'els/ellas plantavan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou plantass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei plantassas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella plantass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus plantassan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus plantassas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas plantassan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou plantassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei plantassias\ntei plantassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella plantassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus plantassian\nnus plantassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus plantassias\nvus plantassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas plantassian\nels/ellas plantassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a plantar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a plantar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a plantar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a plantar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a plantar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a plantar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("planto", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("plantada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("plantos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("plantadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("plànta!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("plantad!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("plantànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1l() {
        forms =  generator.generateConjugation("1l", "sunar");

        Assert.assertEquals("sunar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou sùn\njou sùnel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei sùnas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella sùna", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus sunagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus sunaz", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas sùnan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou sunava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei sunavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella sunava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus sunavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus sunavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas sunavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou sùni", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei sùnias\nca tei sùnas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella sùni", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus suneian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus suneias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas sùnian\nc'els/ellas sùnan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou sunavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei sunavias\nca tei sunavas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella sunavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus sunavian\nca nus sunavan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus sunavias\nca vus sunavas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas sunavian\nc'els/ellas sunavan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou sunass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei sunassas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella sunass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus sunassan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus sunassas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas sunassan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou sunassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei sunassias\ntei sunassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella sunassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus sunassian\nnus sunassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus sunassias\nvus sunassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas sunassian\nels/ellas sunassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a sunar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a sunar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a sunar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a sunar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a sunar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a sunar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("suno", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("sunada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("sunos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("sunadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("sùna!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("sunad!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("sunànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1m() {
        forms =  generator.generateConjugation("1m", "sfardar");

        Assert.assertEquals("sfardar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou sfred\njou sfredel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei sfredas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella sfreda", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus sfardagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus sfardaz", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas sfredan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou sfardava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei sfardavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella sfardava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus sfardavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus sfardavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas sfardavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou sfredi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei sfredias\nca tei sfredas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella sfredi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus sfardeian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus sfardeias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas sfredian\nc'els/ellas sfredan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou sfardavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei sfardavias\nca tei sfardavas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella sfardavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus sfardavian\nca nus sfardavan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus sfardavias\nca vus sfardavas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas sfardavian\nc'els/ellas sfardavan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou sfardass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei sfardassas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella sfardass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus sfardassan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus sfardassas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas sfardassan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou sfardassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei sfardassias\ntei sfardassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella sfardassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus sfardassian\nnus sfardassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus sfardassias\nvus sfardassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas sfardassian\nels/ellas sfardassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a sfardar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a sfardar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a sfardar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a sfardar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a sfardar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a sfardar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("sfardo", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("sfardada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("sfardos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("sfardadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("sfreda!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("sfardad!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("sfardànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1n() {
        forms =  generator.generateConjugation("1n", "sgarmar");

        Assert.assertEquals("sgarmar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou sgrom\njou sgromel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei sgromas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella sgroma", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus sgarmagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus sgarmaz", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas sgroman", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou sgarmava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei sgarmavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella sgarmava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus sgarmavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus sgarmavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas sgarmavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou sgromi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei sgromias\nca tei sgromas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella sgromi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus sgarmeian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus sgarmeias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas sgromian\nc'els/ellas sgroman", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou sgarmavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei sgarmavias\nca tei sgarmavas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella sgarmavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus sgarmavian\nca nus sgarmavan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus sgarmavias\nca vus sgarmavas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas sgarmavian\nc'els/ellas sgarmavan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou sgarmass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei sgarmassas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella sgarmass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus sgarmassan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus sgarmassas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas sgarmassan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou sgarmassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei sgarmassias\ntei sgarmassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella sgarmassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus sgarmassian\nnus sgarmassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus sgarmassias\nvus sgarmassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas sgarmassian\nels/ellas sgarmassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a sgarmar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a sgarmar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a sgarmar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a sgarmar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a sgarmar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a sgarmar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("sgarmo", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("sgarmada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("sgarmos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("sgarmadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("sgroma!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("sgarmad!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("sgarmànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1o() {
        forms =  generator.generateConjugation("1o", "sgartar");

        Assert.assertEquals("sgartar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou sgrat\njou sgratel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei sgratas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella sgrata", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus sgartagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus sgartaz", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas sgratan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou sgartava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei sgartavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella sgartava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus sgartavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus sgartavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas sgartavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou sgrati", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei sgratias\nca tei sgratas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella sgrati", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus sgarteian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus sgarteias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas sgratian\nc'els/ellas sgratan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou sgartavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei sgartavias\nca tei sgartavas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella sgartavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus sgartavian\nca nus sgartavan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus sgartavias\nca vus sgartavas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas sgartavian\nc'els/ellas sgartavan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou sgartass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei sgartassas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella sgartass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus sgartassan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus sgartassas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas sgartassan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou sgartassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei sgartassias\ntei sgartassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella sgartassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus sgartassian\nnus sgartassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus sgartassias\nvus sgartassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas sgartassian\nels/ellas sgartassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a sgartar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a sgartar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a sgartar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a sgartar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a sgartar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a sgartar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("sgarto", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("sgartada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("sgartos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("sgartadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("sgrata!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("sgartad!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("sgartànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1p() {
        forms =  generator.generateConjugation("1p", "cantar");

        Assert.assertEquals("cantar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou tgànt\njou tgàntel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei tgàntas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella tgànta", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus cantagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus cantaz", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas tgàntan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou cantava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei cantavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella cantava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus cantavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus cantavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas cantavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou tgànti", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei tgàntias\nca tei tgàntas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella tgànti", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus canteian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus canteias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas tgàntian\nc'els/ellas tgàntan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou cantavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei cantavias\nca tei cantavas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella cantavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus cantavian\nca nus cantavan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus cantavias\nca vus cantavas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas cantavian\nc'els/ellas cantavan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou cantass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei cantassas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella cantass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus cantassan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus cantassas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas cantassan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou cantassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei cantassias\ntei cantassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella cantassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus cantassian\nnus cantassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus cantassias\nvus cantassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas cantassian\nels/ellas cantassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a cantar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a cantar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a cantar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a cantar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a cantar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a cantar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("canto", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("cantada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("cantos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("cantadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("tgànta!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("cantad!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("cantànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1q() {
        forms =  generator.generateConjugation("1q", "anganar");

        Assert.assertEquals("anganar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou angon\njou angonel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei angonas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella angona", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus anganagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus anganaz", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas angonan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou anganava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei anganavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella anganava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus anganavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus anganavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas anganavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou angoni", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei angonias\nca tei angonas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella angoni", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus anganeian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus anganeias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas angonian\nc'els/ellas angonan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou anganavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei anganavias\nca tei anganavas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella anganavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus anganavian\nca nus anganavan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus anganavias\nca vus anganavas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas anganavian\nc'els/ellas anganavan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou anganass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei anganassas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella anganass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus anganassan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus anganassas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas anganassan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou anganassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei anganassias\ntei anganassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella anganassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus anganassian\nnus anganassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus anganassias\nvus anganassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas anganassian\nels/ellas anganassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt ad anganar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans ad anganar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean ad anganar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn ad anganar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez ad anganar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan ad anganar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("angano", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("anganada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("anganos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("anganadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("angona!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("anganad!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("anganànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule2() {
        forms =  generator.generateConjugation("2", "spargnear");

        Assert.assertEquals("spargnear", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou spargn\njou spargnel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei spargnas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella spargna", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus spargnagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus spargneaz", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas spargnan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou spargneava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei spargneavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella spargneava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus spargneavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus spargneavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas spargneavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou spargni", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei spargnias\nca tei spargnas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella spargni", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus spargneian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus spargneias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas spargnian\nc'els/ellas spargnan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou spargneavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei spargneavias\nca tei spargneavas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella spargneavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus spargneavian\nca nus spargneavan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus spargneavias\nca vus spargneavas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas spargneavian\nc'els/ellas spargneavan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou spargness", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei spargnessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella spargness", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus spargnessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus spargnessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas spargnessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou spargnessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei spargnessias\ntei spargnessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella spargnessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus spargnessian\nnus spargnessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus spargnessias\nvus spargnessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas spargnessian\nels/ellas spargnessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a spargnear", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a spargnear", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a spargnear", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a spargnear", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a spargnear", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a spargnear", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("spargnieu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("spargneada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("spargnieus", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("spargneadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("spargna!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("spargnead!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("spargnànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule2a() {
        forms =  generator.generateConjugation("2a", "samtgear");

        Assert.assertEquals("samtgear", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou semtg\njou semtgel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei semtgas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella semtga", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus samtgagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus samtgeaz", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas semtgan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou samtgeava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei samtgeavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella samtgeava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus samtgeavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus samtgeavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas samtgeavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou semtgi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei semtgias\nca tei semtgas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella semtgi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus samtgeian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus samtgeias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas semtgian\nc'els/ellas semtgan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou samtgeavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei samtgeavias\nca tei samtgeavas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella samtgeavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus samtgeavian\nca nus samtgeavan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus samtgeavias\nca vus samtgeavas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas samtgeavian\nc'els/ellas samtgeavan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou samtgess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei samtgessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella samtgess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus samtgessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus samtgessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas samtgessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou samtgessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei samtgessias\ntei samtgessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella samtgessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus samtgessian\nnus samtgessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus samtgessias\nvus samtgessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas samtgessian\nels/ellas samtgessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a samtgear", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a samtgear", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a samtgear", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a samtgear", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a samtgear", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a samtgear", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("samtgieu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("samtgeada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("samtgieus", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("samtgeadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("semtga!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("samtgead!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("samtgànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule2b() {
        forms =  generator.generateConjugation("2b", "spitgear");

        Assert.assertEquals("spitgear", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou spetg\njou spetgel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei spetgas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella spetga", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus spitgagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus spitgeaz", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas spetgan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou spitgeava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei spitgeavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella spitgeava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus spitgeavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus spitgeavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas spitgeavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou spetgi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei spetgias\nca tei spetgas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella spetgi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus spitgeian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus spitgeias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas spetgian\nc'els/ellas spetgan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou spitgeavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei spitgeavias\nca tei spitgeavas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella spitgeavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus spitgeavian\nca nus spitgeavan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus spitgeavias\nca vus spitgeavas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas spitgeavian\nc'els/ellas spitgeavan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou spitgess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei spitgessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella spitgess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus spitgessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus spitgessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas spitgessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou spitgessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei spitgessias\ntei spitgessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella spitgessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus spitgessian\nnus spitgessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus spitgessias\nvus spitgessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas spitgessian\nels/ellas spitgessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a spitgear", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a spitgear", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a spitgear", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a spitgear", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a spitgear", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a spitgear", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("spitgieu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("spitgeada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("spitgieus", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("spitgeadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("spetga!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("spitgead!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("spitgànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule2c() {
        forms =  generator.generateConjugation("2c", "cuptgear");

        Assert.assertEquals("cuptgear", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou coptg\njou coptgel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei coptgas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella coptga", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus cuptgagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus cuptgeaz", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas coptgan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou cuptgeava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei cuptgeavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella cuptgeava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus cuptgeavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus cuptgeavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas cuptgeavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou coptgi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei coptgias\nca tei coptgas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella coptgi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus cuptgeian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus cuptgeias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas coptgian\nc'els/ellas coptgan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou cuptgeavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei cuptgeavias\nca tei cuptgeavas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella cuptgeavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus cuptgeavian\nca nus cuptgeavan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus cuptgeavias\nca vus cuptgeavas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas cuptgeavian\nc'els/ellas cuptgeavan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou cuptgess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei cuptgessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella cuptgess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus cuptgessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus cuptgessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas cuptgessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou cuptgessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei cuptgessias\ntei cuptgessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella cuptgessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus cuptgessian\nnus cuptgessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus cuptgessias\nvus cuptgessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas cuptgessian\nels/ellas cuptgessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a cuptgear", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a cuptgear", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a cuptgear", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a cuptgear", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a cuptgear", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a cuptgear", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("cuptgieu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("cuptgeada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("cuptgieus", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("cuptgeadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("coptga!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("cuptgead!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("cuptgànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule2d() {
        forms =  generator.generateConjugation("2d", "tschartgear");

        Assert.assertEquals("tschartgear", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou tscheartg\njou tscheartgel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei tscheartgas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella tscheartga", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus tschartgagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus tschartgeaz", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas tscheartgan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou tschartgeava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei tschartgeavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella tschartgeava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus tschartgeavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus tschartgeavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas tschartgeavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou tscheartgi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei tscheartgias\nca tei tscheartgas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella tscheartgi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus tschartgeian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus tschartgeias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas tscheartgian\nc'els/ellas tscheartgan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou tschartgeavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei tschartgeavias\nca tei tschartgeavas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella tschartgeavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus tschartgeavian\nca nus tschartgeavan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus tschartgeavias\nca vus tschartgeavas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas tschartgeavian\nc'els/ellas tschartgeavan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou tschartgess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei tschartgessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella tschartgess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus tschartgessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus tschartgessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas tschartgessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou tschartgessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei tschartgessias\ntei tschartgessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella tschartgessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus tschartgessian\nnus tschartgessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus tschartgessias\nvus tschartgessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas tschartgessian\nels/ellas tschartgessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a tschartgear", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a tschartgear", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a tschartgear", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a tschartgear", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a tschartgear", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a tschartgear", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("tschartgieu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("tschartgeada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("tschartgieus", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("tschartgeadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("tscheartga!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("tschartgead!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("tschartgànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule2e() {
        forms =  generator.generateConjugation("2e", "svagliear");

        Assert.assertEquals("svagliear", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou svegl\njou svegliel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei sveglias", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella sveglia", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus svagliagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus svaglieaz", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas sveglian", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou svaglieava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei svaglieavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella svaglieava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus svaglieavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus svaglieavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas svaglieavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou svegli", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei sveglias", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella svegli", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus svaglieian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus svaglieias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas sveglian", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou svaglieavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei svaglieavias\nca tei svaglieavas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella svaglieavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus svaglieavian\nca nus svaglieavan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus svaglieavias\nca vus svaglieavas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas svaglieavian\nc'els/ellas svaglieavan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou svagliess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei svagliessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella svagliess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus svagliessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus svagliessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas svagliessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou svagliessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei svagliessias\ntei svagliessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella svagliessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus svagliessian\nnus svagliessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus svagliessias\nvus svagliessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas svagliessian\nels/ellas svagliessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a svagliear", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a svagliear", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a svagliear", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a svagliear", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a svagliear", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a svagliear", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("svaglieu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("svaglieada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("svaglieus", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("svaglieadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("sveglia!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("svagliead!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("svagliànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule2f() {
        forms =  generator.generateConjugation("2f", "spassagear");

        Assert.assertEquals("spassagear", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou spasseg\njou spassegel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei spassegias", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella spassegia", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus spassagiagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus spassageaz", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas spassegian", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou spassageava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei spassageavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella spassageava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus spassageavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus spassageavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas spassageavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou spassegi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei spassegias", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella spassegi", forms.getVerb().getConjunctiv().getSing3());
        //Assert.assertEquals("ca nus sapssegeian", forms.getVerb().getConjunctiv().getPlural1());
        //Assert.assertEquals("ca vus spassegeias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas spassegian", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou spassageavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei spassageavias\nca tei spassageavas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella spassageavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus spassageavian\nca nus spassageavan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus spassageavias\nca vus spassageavas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas spassageavian\nc'els/ellas spassageavan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou spassagess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei spassagessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella spassagess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus spassagessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus spassagessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas spassagessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou spassagessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei spassagessias\ntei spassagessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella spassagessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus spassagessian\nnus spassagessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus spassagessias\nvus spassagessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas spassagessian\nels/ellas spassagessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a spassagear", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a spassagear", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a spassagear", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a spassagear", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a spassagear", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a spassagear", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("spassagieu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("spassageada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("spassagieus", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("spassageadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("spassegia!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("spassagead!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("spassagiànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule2g() {
        forms =  generator.generateConjugation("2g", "zambargear");

        Assert.assertEquals("zambargear", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou zambreg\njou zambregel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei zambregias", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella zambregia", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus zambargiagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus zambargeaz", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas zambregian", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou zambargeava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei zambargeavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella zambargeava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus zambargeavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus zambargeavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas zambargeavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou zambregi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei zambregias", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella zambregi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus zambargeian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus zambargeias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas zambregian", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou zambargeavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei zambargeavias\nca tei zambargeavas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella zambargeavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus zambargeavian\nca nus zambargeavan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus zambargeavias\nca vus zambargeavas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas zambargeavian\nc'els/ellas zambargeavan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou zambargess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei zambargessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella zambargess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus zambargessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus zambargessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas zambargessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou zambargessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei zambargessias\ntei zambargessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella zambargessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus zambargessian\nnus zambargessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus zambargessias\nvus zambargessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas zambargessian\nels/ellas zambargessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a zambargear", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a zambargear", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a zambargear", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a zambargear", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a zambargear", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a zambargear", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("zambargieu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("zambargeada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("zambargieus", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("zambargeadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("zambregia!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("zambargead!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("zambargiànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3() {
        forms =  generator.generateConjugation("3", "proponer");

        Assert.assertEquals("proponer", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou propon\njou proponel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei proponas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella propona", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus proponagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus proponez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas proponan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou proponeva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei proponevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella proponeva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus proponevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus proponevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas proponevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou proponi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei proponias\nca tei proponas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella proponi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus proponeian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus proponeias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas proponian\nc'els/ellas proponan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou proponevi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei proponevias\nca tei proponevas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella proponevi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus proponevian\nca nus proponevan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus proponevias\nca vus proponevas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas proponevian\nc'els/ellas proponevan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou proponess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei proponessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella proponess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus proponessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus proponessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas proponessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou proponessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei proponessias\ntei proponessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella proponessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus proponessian\nnus proponessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus proponessias\nvus proponessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas proponessian\nels/ellas proponessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a proponer", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a proponer", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a proponer", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a proponer", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a proponer", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a proponer", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("proponieu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("proponida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("proponieus", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("proponidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("propona!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("proponed!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("proponànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3a() {
        forms =  generator.generateConjugation("3a", "vender");

        Assert.assertEquals("vender", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou vend\njou vendel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei vendas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella venda", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus vandagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus vandez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas vendan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou vandeva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei vandevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella vandeva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus vandevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus vandevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas vandevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou vendi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei vendias\nca tei vendas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella vendi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus vandeian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus vandeias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas vendian\nc'els/ellas vendan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou vandevi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei vandevias\nca tei vandevas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella vandevi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus vandevian\nca nus vandevan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus vandevias\nca vus vandevas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas vandevian\nc'els/ellas vandevan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou vandess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei vandessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella vandess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus vandessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus vandessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas vandessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou vandessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei vandessias\ntei vandessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella vandessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus vandessian\nnus vandessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus vandessias\nvus vandessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas vandessian\nels/ellas vandessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a vender", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a vender", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a vender", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a vender", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a vender", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a vender", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("vandieu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("vandida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("vandieus", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("vandidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("venda!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("vanded!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("vandànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3b() {
        forms =  generator.generateConjugation("3b", "dezeder");

        Assert.assertEquals("dezeder", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou dezed\njou dezedel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei dezedas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella dezeda", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus dezidagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus dezidez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas dezedan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou dezideva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei dezidevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella dezideva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus dezidevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus dezidevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas dezidevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou dezedi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei dezedias\nca tei dezedas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella dezedi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus dezideian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus dezideias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas dezedian\nc'els/ellas dezedan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou dezidevi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei dezidevias\nca tei dezidevas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella dezidevi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus dezidevian\nca nus dezidevan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus dezidevias\nca vus dezidevas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas dezidevian\nc'els/ellas dezidevan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou dezidess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei dezidessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella dezidess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus dezidessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus dezidessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas dezidessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou dezidessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei dezidessias\ntei dezidessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella dezidessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus dezidessian\nnus dezidessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus dezidessias\nvus dezidessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas dezidessian\nels/ellas dezidessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a dezeder", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a dezeder", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a dezeder", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a dezeder", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a dezeder", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a dezeder", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("dezidieu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("dezidida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("dezidieus", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("dezididas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("dezeda!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("dezided!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("dezidànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3c() {
        forms =  generator.generateConjugation("3c", "pearder");

        Assert.assertEquals("pearder", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou peard\njou peardel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei peardas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella pearda", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus pardagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus pardez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas peardan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou pardeva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei pardevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella pardeva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus pardevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus pardevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas pardevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou peardi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei peardias\nca tei peardas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella peardi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus pardeian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus pardeias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas peardian\nc'els/ellas peardan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou pardevi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei pardevias\nca tei pardevas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella pardevi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus pardevian\nca nus pardevan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus pardevias\nca vus pardevas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas pardevian\nc'els/ellas pardevan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou pardess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei pardessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella pardess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus pardessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus pardessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas pardessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou pardessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei pardessias\ntei pardessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella pardessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus pardessian\nnus pardessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus pardessias\nvus pardessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas pardessian\nels/ellas pardessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a pearder", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a pearder", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a pearder", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a pearder", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a pearder", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a pearder", forms.getVerb().getFutur().getPlural3());

        /*
        Example is irregular
        Assert.assertEquals("pears", forms.getVerb().getParticipperfect().getMs());
        Assert.assertEquals("pearsa", forms.getVerb().getParticipperfect().getFs());
        Assert.assertEquals("pears", forms.getVerb().getParticipperfect().getMp());
        Assert.assertEquals("pearsas", forms.getVerb().getParticipperfect().getFp());
         */

        Assert.assertEquals("pearda!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("parded!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("pardànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3d() {
        forms =  generator.generateConjugation("3d", "tànscher");

        Assert.assertEquals("tànscher", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou tànsch\njou tànschel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei tànschas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella tànscha", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus tanschagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus tanschez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas tànschan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou tanscheva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei tanschevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella tanscheva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus tanschevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus tanschevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas tanschevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou tànschi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei tànschias\nca tei tànschas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella tànschi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus tanscheian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus tanscheias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas tànschian\nc'els/ellas tànschan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou tanschevi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei tanschevias\nca tei tanschevas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella tanschevi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus tanschevian\nca nus tanschevan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus tanschevias\nca vus tanschevas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas tanschevian\nc'els/ellas tanschevan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou tanschess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei tanschessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella tanschess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus tanschessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus tanschessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas tanschessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou tanschessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei tanschessias\ntei tanschessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella tanschessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus tanschessian\nnus tanschessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus tanschessias\nvus tanschessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas tanschessian\nels/ellas tanschessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a tànscher", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a tànscher", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a tànscher", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a tànscher", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a tànscher", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a tànscher", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("tanschieu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("tanschida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("tanschieus", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("tanschidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("tànscha!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("tansched!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("tanschànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3e() {
        forms =  generator.generateConjugation("3e", "porscher");

        Assert.assertEquals("porscher", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou porsch\njou porschel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei porschas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella porscha", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus purschagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus purschez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas porschan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou purscheva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei purschevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella purscheva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus purschevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus purschevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas purschevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou porschi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei porschias\nca tei porschas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella porschi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus purscheian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus purscheias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas porschian\nc'els/ellas porschan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou purschevi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei purschevias\nca tei purschevas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella purschevi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus purschevian\nca nus purschevan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus purschevias\nca vus purschevas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas purschevian\nc'els/ellas purschevan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou purschess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei purschessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella purschess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus purschessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus purschessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas purschessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou purschessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei purschessias\ntei purschessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella purschessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus purschessian\nnus purschessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus purschessias\nvus purschessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas purschessian\nels/ellas purschessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a porscher", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a porscher", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a porscher", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a porscher", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a porscher", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a porscher", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("purschieu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("purschida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("purschieus", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("purschidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("porscha!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("pursched!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("purschànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3f() {
        forms =  generator.generateConjugation("3f", "liger");

        Assert.assertEquals("liger", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou lig\njou ligel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei ligias", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella ligia", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus ligiagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus ligez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas ligian", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou ligeva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei ligevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella ligeva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus ligevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus ligevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas ligevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou ligi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei ligias", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella ligi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus ligeian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus ligeias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas ligian", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou ligevi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei ligevias\nca tei ligevas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella ligevi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus ligevian\nca nus ligevan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus ligevias\nca vus ligevas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas ligevian\nc'els/ellas ligevan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou ligess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei ligessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella ligess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus ligessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus ligessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas ligessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou ligessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei ligessias\ntei ligessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella ligessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus ligessian\nnus ligessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus ligessias\nvus ligessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas ligessian\nels/ellas ligessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a liger", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a liger", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a liger", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a liger", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a liger", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a liger", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("ligieu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("ligida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("ligieus", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("ligidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("ligia!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("liged!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("ligiànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4() {
        forms =  generator.generateConjugation("4", "partir");

        Assert.assertEquals("partir", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou part\njou partel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei partas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella parta", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus partagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus partez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas partan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou parteva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei partevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella parteva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus partevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus partevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas partevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou parti", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei partias\nca tei partas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella parti", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus parteian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus parteias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas partian\nc'els/ellas partan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou partevi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei partevias\nca tei partevas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella partevi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus partevian\nca nus partevan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus partevias\nca vus partevas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas partevian\nc'els/ellas partevan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou partess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei partessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella partess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus partessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus partessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas partessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou partessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei partessias\ntei partessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella partessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus partessian\nnus partessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus partessias\nvus partessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas partessian\nels/ellas partessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a partir", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a partir", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a partir", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a partir", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a partir", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a partir", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("partieu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("partida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("partieus", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("partidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("parta!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("parted!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("partànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4a() {
        forms =  generator.generateConjugation("4a", "santir");

        Assert.assertEquals("santir", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou saint\njou saintel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei saintas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella sainta", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus santagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus santez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas saintan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou santeva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei santevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella santeva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus santevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus santevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas santevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou sainti", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei saintias\nca tei saintas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella sainti", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus santeian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus santeias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas saintian\nc'els/ellas saintan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou santevi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei santevias\nca tei santevas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella santevi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus santevian\nca nus santevan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus santevias\nca vus santevas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas santevian\nc'els/ellas santevan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou santess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei santessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella santess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus santessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus santessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas santessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou santessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei santessias\ntei santessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella santessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus santessian\nnus santessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus santessias\nvus santessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas santessian\nels/ellas santessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a santir", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a santir", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a santir", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a santir", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a santir", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a santir", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("santieu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("santida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("santieus", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("santidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("sainta!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("santed!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("santànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4b() {
        forms =  generator.generateConjugation("4b", "sgrignir");

        Assert.assertEquals("sgrignir", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou sgregn\njou sgregnel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei sgregnas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella sgregna", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus sgrignagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus sgrignez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas sgregnan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou sgrigneva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei sgrignevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella sgrigneva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus sgrignevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus sgrignevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas sgrignevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou sgregni", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei sgregnias\nca tei sgregnas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella sgregni", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus sgrigneian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus sgrigneias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas sgregnian\nc'els/ellas sgregnan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou sgrignevi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei sgrignevias\nca tei sgrignevas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella sgrignevi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus sgrignevian\nca nus sgrignevan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus sgrignevias\nca vus sgrignevas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas sgrignevian\nc'els/ellas sgrignevan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou sgrigness", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei sgrignessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella sgrigness", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus sgrignessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus sgrignessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas sgrignessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou sgrignessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei sgrignessias\ntei sgrignessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella sgrignessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus sgrignessian\nnus sgrignessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus sgrignessias\nvus sgrignessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas sgrignessian\nels/ellas sgrignessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a sgrignir", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a sgrignir", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a sgrignir", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a sgrignir", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a sgrignir", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a sgrignir", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("sgrignieu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("sgrignida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("sgrignieus", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("sgrignidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("sgregna!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("sgrigned!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("sgrignànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4c() {
        forms =  generator.generateConjugation("4c", "udir");

        Assert.assertEquals("udir", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou od\njou odel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei odas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella oda", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus udagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus udez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas odan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou udeva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei udevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella udeva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus udevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus udevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas udevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou odi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei odias\nca tei odas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella odi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus udeian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus udeias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas odian\nc'els/ellas odan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou udevi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei udevias\nca tei udevas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella udevi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus udevian\nca nus udevan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus udevias\nca vus udevas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas udevian\nc'els/ellas udevan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou udess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei udessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella udess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus udessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus udessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas udessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou udessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei udessias\ntei udessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella udessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus udessian\nnus udessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus udessias\nvus udessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas udessian\nels/ellas udessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt ad udir", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans ad udir", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean ad udir", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn ad udir", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez ad udir", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan ad udir", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("udieu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("udida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("udieus", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("udidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("oda!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("uded!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("udànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4d() {
        forms =  generator.generateConjugation("4d", "bargir");

        Assert.assertEquals("bargir", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou brag\njou bragel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei bragias", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella bragia", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus bargiagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus bargez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas bragian", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou bargeva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei bargevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella bargeva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus bargevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus bargevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas bargevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou bragi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei bragias", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella bragi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus bargeian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus bargeias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas bragian", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou bargevi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei bargevias\nca tei bargevas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella bargevi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus bargevian\nca nus bargevan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus bargevias\nca vus bargevas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas bargevian\nc'els/ellas bargevan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou bargess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei bargessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella bargess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus bargessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus bargessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas bargessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou bargessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei bargessias\ntei bargessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella bargessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus bargessian\nnus bargessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus bargessias\nvus bargessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas bargessian\nels/ellas bargessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a bargir", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a bargir", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a bargir", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a bargir", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a bargir", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a bargir", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("bargieu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("bargida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("bargieus", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("bargidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("bragia!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("barged!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("bargiànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4e() {
        forms =  generator.generateConjugation("4e", "ancurir");

        Assert.assertEquals("ancurir", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou anquier\njou anquierel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei anquieras", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella anquiera", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus ancuragn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus ancurez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas anquieran", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou ancureva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei ancurevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella ancureva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus ancurevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus ancurevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas ancurevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou anquieri", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei anquierias\nca tei anquieras", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella anquieri", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus ancureian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus ancureias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas anquierian\nc'els/ellas anquieran", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou ancurevi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei ancurevias\nca tei ancurevas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella ancurevi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus ancurevian\nca nus ancurevan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus ancurevias\nca vus ancurevas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas ancurevian\nc'els/ellas ancurevan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou ancuress", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei ancuressas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella ancuress", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus ancuressan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus ancuressas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas ancuressan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou ancuressi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei ancuressias\ntei ancuressas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella ancuressi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus ancuressian\nnus ancuressan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus ancuressias\nvus ancuressas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas ancuressian\nels/ellas ancuressan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt ad ancurir", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans ad ancurir", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean ad ancurir", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn ad ancurir", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez ad ancurir", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan ad ancurir", forms.getVerb().getFutur().getPlural3());

        /*
        Irregular form
        Assert.assertEquals("ancuretg", forms.getVerb().getParticipperfect().getMs());
        Assert.assertEquals("ancuretga", forms.getVerb().getParticipperfect().getFs());
        Assert.assertEquals("ancuretgs", forms.getVerb().getParticipperfect().getMp());
        Assert.assertEquals("ancuretgas", forms.getVerb().getParticipperfect().getFp());
         */

        Assert.assertEquals("anquiera!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("ancured!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("ancurànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule5() {
        forms =  generator.generateConjugation("5", "gratular");

        Assert.assertEquals("gratular", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou gratulesch\njou gratuleschel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei gratuleschas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella gratulescha", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus gratulagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus gratulaz", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas gratuleschan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou gratulava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei gratulavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella gratulava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus gratulavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus gratulavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas gratulavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou gratuleschi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei gratuleschias\nca tei gratuleschas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella gratuleschi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus gratuleian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus gratuleias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas gratuleschian\nc'els/ellas gratuleschan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou gratulavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei gratulavias\nca tei gratulavas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella gratulavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus gratulavian\nca nus gratulavan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus gratulavias\nca vus gratulavas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas gratulavian\nc'els/ellas gratulavan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou gratulass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei gratulassas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella gratulass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus gratulassan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus gratulassas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas gratulassan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou gratulassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei gratulassias\ntei gratulassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella gratulassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus gratulassian\nnus gratulassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus gratulassias\nvus gratulassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas gratulassian\nels/ellas gratulassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a gratular", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a gratular", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a gratular", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a gratular", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a gratular", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a gratular", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("gratulo", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("gratulada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("gratulos", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("gratuladas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("gratulescha!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("gratulad!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("gratulànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule6() {
        forms =  generator.generateConjugation("6", "inditgear");

        Assert.assertEquals("inditgear", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou inditgesch\njou inditgeschel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei inditgeschas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella inditgescha", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus inditgagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus inditgeaz", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas inditgeschan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou inditgeava", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei inditgeavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella inditgeava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus inditgeavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus inditgeavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas inditgeavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou inditgeschi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei inditgeschias\nca tei inditgeschas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella inditgeschi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus inditgeian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus inditgeias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas inditgeschian\nc'els/ellas inditgeschan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou inditgevi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei inditgevias\nca tei inditgevas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella inditgevi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus inditgevian\nca nus inditgevan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus inditgevias\nca vus inditgevas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas inditgevian\nc'els/ellas inditgevan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou inditgess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei inditgessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella inditgess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus inditgessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus inditgessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas inditgessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou inditgessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei inditgessias\ntei inditgessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella inditgessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus inditgessian\nnus inditgessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus inditgessias\nvus inditgessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas inditgessian\nels/ellas inditgessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt ad inditgear", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans ad inditgear", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean ad inditgear", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn ad inditgear", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez ad inditgear", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan ad inditgear", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("inditgieu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("inditgeada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("inditgieus", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("inditgeadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("inditgescha!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("inditgead!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("inditgànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule7() {
        forms =  generator.generateConjugation("7", "capir");

        Assert.assertEquals("capir", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou capesch\njou capeschel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei capeschas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella capescha", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus capagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus capez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas capeschan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou capeva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei capevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella capeva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus capevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus capevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas capevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou capeschi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei capeschias\nca tei capeschas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella capeschi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus capeian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus capeias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas capeschian\nc'els/ellas capeschan", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou capevi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei capevias\nca tei capevas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella capevi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus capevian\nca nus capevan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus capevias\nca vus capevas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas capevian\nc'els/ellas capevan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou capess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei capessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella capess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus capessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus capessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas capessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou capessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei capessias\ntei capessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella capessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus capessian\nnus capessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus capessias\nvus capessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas capessian\nels/ellas capessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a capir", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a capir", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a capir", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a capir", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a capir", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a capir", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("capieu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("capida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("capieus", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("capidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("capescha!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("caped!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("capànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule9() {
        forms =  generator.generateConjugation("9", "parer");

        Assert.assertEquals("parer", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou par\njou parel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei paras", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella para", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus paragn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus parez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas paran", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou pareva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei parevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella pareva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus parevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus parevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas parevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou pari", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei parias\nca tei paras", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella pari", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus pareian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus pareias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas parian\nc'els/ellas paran", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou parevi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei parevias\nca tei parevas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella parevi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus parevian\nca nus parevan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus parevias\nca vus parevas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas parevian\nc'els/ellas parevan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou paress", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei paressas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella paress", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus paressan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus paressas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas paressan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou paressi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei paressias\ntei paressas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella paressi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus paressian\nnus paressan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus paressias\nvus paressas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas paressian\nels/ellas paressan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a parer", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a parer", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a parer", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a parer", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a parer", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a parer", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("parieu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("parida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("parieus", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("paridas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("para!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("pared!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("parànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule9a() {
        forms =  generator.generateConjugation("9a", "tamer");

        Assert.assertEquals("tamer", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jou tem\njou temel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("tei temas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella tema", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus tamagn", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus tamez", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas teman", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jou tameva", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("tei tamevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella tameva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus tamevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus tamevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas tamevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("ca jou temi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("ca tei temias\nca tei temas", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("c'el/ella temi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("ca nus tameian", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("ca vus tameias", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("c'els/ellas temian\nc'els/ellas teman", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("ca jou tamevi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("ca tei tamevias\nca tei tamevas", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("c'el/ella tamevi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("ca nus tamevian\nca nus tamevan", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("ca vus tamevias\nca vus tamevas", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("c'els/ellas tamevian\nc'els/ellas tamevan", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jou tamess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("tei tamessas", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella tamess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus tamessan", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus tamessas", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas tamessan", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jou tamessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("tei tamessias\ntei tamessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella tamessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus tamessian\nnus tamessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus tamessias\nvus tamessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas tamessian\nels/ellas tamessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jou vignt a tamer", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("tei veans a tamer", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vean a tamer", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vagnagn a tamer", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vagnez a tamer", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vignan a tamer", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("tamieu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("tamida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("tamieus", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("tamidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("tema!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("tamed!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("tamànd", forms.getVerb().getGerundium());
    }
}
