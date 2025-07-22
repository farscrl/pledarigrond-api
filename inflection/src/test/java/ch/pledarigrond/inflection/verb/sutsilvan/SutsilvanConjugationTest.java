package ch.pledarigrond.inflection.verb.sutsilvan;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.verb.sutsilvan.SutsilvanConjugation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SutsilvanConjugationTest {

    private static SutsilvanConjugation generator;
    private InflectionDto forms;

    @BeforeAll
    public static void initialize() {
        generator = new SutsilvanConjugation();
    }

    @Test
    public void testRule1() {
        forms =  generator.generateConjugation("1", "gidar");

        assertEquals("gidar", forms.getVerb().getInfinitiv());

        assertEquals("jou gid\njou gidel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei gidas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella gida", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus gidagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus gidaz", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas gidan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou gidava", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei gidavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella gidava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus gidavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus gidavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas gidavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou gidi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei gidias\nca tei gidas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella gidi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus gideian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus gideias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas gidian\nc'els/ellas gidan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou gidavi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei gidavias\nca tei gidavas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella gidavi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus gidavian\nca nus gidavan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus gidavias\nca vus gidavas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas gidavian\nc'els/ellas gidavan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou gidass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei gidassas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella gidass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus gidassan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus gidassas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas gidassan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou gidassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei gidassias\ntei gidassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella gidassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus gidassian\nnus gidassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus gidassias\nvus gidassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas gidassian\nels/ellas gidassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a gidar", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a gidar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a gidar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a gidar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a gidar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a gidar", forms.getVerb().getFutur().getPlural3());

        assertEquals("gido", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("gidada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("gidos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("gidadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("gida!", forms.getVerb().getImperativ().getSingular());
        assertEquals("gidad!", forms.getVerb().getImperativ().getPlural());

        assertEquals("gidànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1a() {
        forms =  generator.generateConjugation("1a", "tarlar");

        assertEquals("tarlar", forms.getVerb().getInfinitiv());

        assertEquals("jou tearl\njou tearlel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei tearlas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella tearla", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus tarlagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus tarlaz", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas tearlan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou tarlava", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei tarlavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella tarlava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus tarlavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus tarlavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas tarlavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou tearli", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei tearlias\nca tei tearlas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella tearli", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus tarleian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus tarleias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas tearlian\nc'els/ellas tearlan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou tarlavi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei tarlavias\nca tei tarlavas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella tarlavi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus tarlavian\nca nus tarlavan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus tarlavias\nca vus tarlavas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas tarlavian\nc'els/ellas tarlavan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou tarlass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei tarlassas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella tarlass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus tarlassan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus tarlassas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas tarlassan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou tarlassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei tarlassias\ntei tarlassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella tarlassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus tarlassian\nnus tarlassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus tarlassias\nvus tarlassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas tarlassian\nels/ellas tarlassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a tarlar", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a tarlar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a tarlar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a tarlar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a tarlar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a tarlar", forms.getVerb().getFutur().getPlural3());

        assertEquals("tarlo", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("tarlada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("tarlos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("tarladas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("tearla!", forms.getVerb().getImperativ().getSingular());
        assertEquals("tarlad!", forms.getVerb().getImperativ().getPlural());

        assertEquals("tarlànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1b() {
        forms =  generator.generateConjugation("1b", "pasar");

        assertEquals("pasar", forms.getVerb().getInfinitiv());

        assertEquals("jou pes\njou pesel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei pesas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella pesa", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus pasagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus pasaz", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas pesan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou pasava", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei pasavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella pasava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus pasavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus pasavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas pasavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou pesi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei pesias\nca tei pesas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella pesi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus paseian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus paseias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas pesian\nc'els/ellas pesan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou pasavi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei pasavias\nca tei pasavas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella pasavi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus pasavian\nca nus pasavan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus pasavias\nca vus pasavas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas pasavian\nc'els/ellas pasavan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou pasass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei pasassas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella pasass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus pasassan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus pasassas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas pasassan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou pasassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei pasassias\ntei pasassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella pasassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus pasassian\nnus pasassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus pasassias\nvus pasassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas pasassian\nels/ellas pasassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a pasar", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a pasar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a pasar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a pasar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a pasar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a pasar", forms.getVerb().getFutur().getPlural3());

        assertEquals("paso", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("pasada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("pasos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("pasadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("pesa!", forms.getVerb().getImperativ().getSingular());
        assertEquals("pasad!", forms.getVerb().getImperativ().getPlural());

        assertEquals("pasànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1c() {
        forms =  generator.generateConjugation("1c", "clamar");

        assertEquals("clamar", forms.getVerb().getInfinitiv());

        assertEquals("jou clom\njou clomel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei clomas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella cloma", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus clamagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus clamaz", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas cloman", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou clamava", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei clamavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella clamava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus clamavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus clamavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas clamavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou clomi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei clomias\nca tei clomas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella clomi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus clameian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus clameias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas clomian\nc'els/ellas cloman", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou clamavi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei clamavias\nca tei clamavas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella clamavi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus clamavian\nca nus clamavan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus clamavias\nca vus clamavas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas clamavian\nc'els/ellas clamavan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou clamass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei clamassas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella clamass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus clamassan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus clamassas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas clamassan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou clamassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei clamassias\ntei clamassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella clamassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus clamassian\nnus clamassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus clamassias\nvus clamassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas clamassian\nels/ellas clamassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a clamar", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a clamar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a clamar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a clamar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a clamar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a clamar", forms.getVerb().getFutur().getPlural3());

        assertEquals("clamo", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("clamada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("clamos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("clamadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("cloma!", forms.getVerb().getImperativ().getSingular());
        assertEquals("clamad!", forms.getVerb().getImperativ().getPlural());

        assertEquals("clamànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1d() {
        forms =  generator.generateConjugation("1d", "passantar");

        assertEquals("passantar", forms.getVerb().getInfinitiv());

        assertEquals("jou passaint\njou passaintel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei passaintas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella passainta", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus passantagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus passantaz", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas passaintan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou passantava", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei passantavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella passantava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus passantavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus passantavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas passantavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou passainti", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei passaintias\nca tei passaintas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella passainti", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus passanteian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus passanteias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas passaintian\nc'els/ellas passaintan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou passantavi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei passantavias\nca tei passantavas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella passantavi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus passantavian\nca nus passantavan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus passantavias\nca vus passantavas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas passantavian\nc'els/ellas passantavan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou passantass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei passantassas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella passantass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus passantassan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus passantassas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas passantassan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou passantassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei passantassias\ntei passantassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella passantassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus passantassian\nnus passantassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus passantassias\nvus passantassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas passantassian\nels/ellas passantassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a passantar", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a passantar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a passantar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a passantar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a passantar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a passantar", forms.getVerb().getFutur().getPlural3());

        assertEquals("passanto", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("passantada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("passantos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("passantadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("passainta!", forms.getVerb().getImperativ().getSingular());
        assertEquals("passantad!", forms.getVerb().getImperativ().getPlural());

        assertEquals("passantànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1e() {
        forms =  generator.generateConjugation("1e", "irtar");

        assertEquals("irtar", forms.getVerb().getInfinitiv());

        assertEquals("jou iert\njou iertel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei iertas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella ierta", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus irtagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus irtaz", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas iertan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou irtava", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei irtavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella irtava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus irtavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus irtavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas irtavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou ierti", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei iertias\nca tei iertas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella ierti", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus irteian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus irteias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas iertian\nc'els/ellas iertan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou irtavi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei irtavias\nca tei irtavas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella irtavi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus irtavian\nca nus irtavan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus irtavias\nca vus irtavas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas irtavian\nc'els/ellas irtavan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou irtass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei irtassas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella irtass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus irtassan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus irtassas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas irtassan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou irtassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei irtassias\ntei irtassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella irtassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus irtassian\nnus irtassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus irtassias\nvus irtassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas irtassian\nels/ellas irtassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt ad irtar", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans ad irtar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean ad irtar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn ad irtar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez ad irtar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan ad irtar", forms.getVerb().getFutur().getPlural3());

        assertEquals("irto", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("irtada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("irtos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("irtadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("ierta!", forms.getVerb().getImperativ().getSingular());
        assertEquals("irtad!", forms.getVerb().getImperativ().getPlural());

        assertEquals("irtànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1f() {
        forms =  generator.generateConjugation("1f", "fimar");

        assertEquals("fimar", forms.getVerb().getInfinitiv());

        assertEquals("jou fem\njou femel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei femas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella fema", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus fimagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus fimaz", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas feman", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou fimava", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei fimavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella fimava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus fimavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus fimavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas fimavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou femi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei femias\nca tei femas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella femi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus fimeian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus fimeias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas femian\nc'els/ellas feman", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou fimavi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei fimavias\nca tei fimavas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella fimavi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus fimavian\nca nus fimavan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus fimavias\nca vus fimavas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas fimavian\nc'els/ellas fimavan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou fimass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei fimassas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella fimass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus fimassan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus fimassas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas fimassan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou fimassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei fimassias\ntei fimassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella fimassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus fimassian\nnus fimassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus fimassias\nvus fimassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas fimassian\nels/ellas fimassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a fimar", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a fimar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a fimar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a fimar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a fimar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a fimar", forms.getVerb().getFutur().getPlural3());

        assertEquals("fimo", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("fimada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("fimos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("fimadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("fema!", forms.getVerb().getImperativ().getSingular());
        assertEquals("fimad!", forms.getVerb().getImperativ().getPlural());

        assertEquals("fimànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1g() {
        forms =  generator.generateConjugation("1g", "scalinar");

        assertEquals("scalinar", forms.getVerb().getInfinitiv());

        assertEquals("jou scalegn\njou scalegnel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei scalegnas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella scalegna", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus scalinagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus scalinaz", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas scalegnan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou scalinava", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei scalinavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella scalinava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus scalinavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus scalinavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas scalinavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou scalegni", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei scalegnias\nca tei scalegnas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella scalegni", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus scalineian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus scalineias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas scalegnian\nc'els/ellas scalegnan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou scalinavi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei scalinavias\nca tei scalinavas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella scalinavi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus scalinavian\nca nus scalinavan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus scalinavias\nca vus scalinavas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas scalinavian\nc'els/ellas scalinavan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou scalinass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei scalinassas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella scalinass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus scalinassan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus scalinassas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas scalinassan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou scalinassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei scalinassias\ntei scalinassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella scalinassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus scalinassian\nnus scalinassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus scalinassias\nvus scalinassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas scalinassian\nels/ellas scalinassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a scalinar", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a scalinar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a scalinar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a scalinar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a scalinar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a scalinar", forms.getVerb().getFutur().getPlural3());

        assertEquals("scalino", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("scalinada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("scalinos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("scalinadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("scalegna!", forms.getVerb().getImperativ().getSingular());
        assertEquals("scalinad!", forms.getVerb().getImperativ().getPlural());

        assertEquals("scalinànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1h() {
        forms =  generator.generateConjugation("1h", "luvrar");

        assertEquals("luvrar", forms.getVerb().getInfinitiv());

        assertEquals("jou lavur\njou lavurel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei lavuras", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella lavura", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus luvragn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus luvraz", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas lavuran", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou luvrava", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei luvravas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella luvrava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus luvravan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus luvravas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas luvravan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou lavuri", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei lavurias\nca tei lavuras", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella lavuri", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus luvreian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus luvreias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas lavurian\nc'els/ellas lavuran", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou luvravi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei luvravias\nca tei luvravas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella luvravi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus luvravian\nca nus luvravan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus luvravias\nca vus luvravas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas luvravian\nc'els/ellas luvravan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou luvrass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei luvrassas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella luvrass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus luvrassan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus luvrassas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas luvrassan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou luvrassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei luvrassias\ntei luvrassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella luvrassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus luvrassian\nnus luvrassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus luvrassias\nvus luvrassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas luvrassian\nels/ellas luvrassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a luvrar", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a luvrar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a luvrar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a luvrar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a luvrar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a luvrar", forms.getVerb().getFutur().getPlural3());

        assertEquals("luvro", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("luvrada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("luvros", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("luvradas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("lavura!", forms.getVerb().getImperativ().getSingular());
        assertEquals("luvrad!", forms.getVerb().getImperativ().getPlural());

        assertEquals("luvrànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1i() {
        forms =  generator.generateConjugation("1i", "purtar");

        assertEquals("purtar", forms.getVerb().getInfinitiv());

        assertEquals("jou port\njou portel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei portas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella porta", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus purtagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus purtaz", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas portan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou purtava", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei purtavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella purtava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus purtavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus purtavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas purtavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou porti", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei portias\nca tei portas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella porti", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus purteian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus purteias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas portian\nc'els/ellas portan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou purtavi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei purtavias\nca tei purtavas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella purtavi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus purtavian\nca nus purtavan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus purtavias\nca vus purtavas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas purtavian\nc'els/ellas purtavan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou purtass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei purtassas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella purtass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus purtassan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus purtassas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas purtassan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou purtassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei purtassias\ntei purtassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella purtassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus purtassian\nnus purtassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus purtassias\nvus purtassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas purtassian\nels/ellas purtassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a purtar", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a purtar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a purtar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a purtar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a purtar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a purtar", forms.getVerb().getFutur().getPlural3());

        assertEquals("purto", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("purtada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("purtos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("purtadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("porta!", forms.getVerb().getImperativ().getSingular());
        assertEquals("purtad!", forms.getVerb().getImperativ().getPlural());

        assertEquals("purtànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1j() {
        forms =  generator.generateConjugation("1j", "tschanar");

        assertEquals("tschanar", forms.getVerb().getInfinitiv());

        assertEquals("jou tschagn\njou tschagnel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei tschagnas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella tschagna", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus tschanagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus tschanaz", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas tschagnan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou tschanava", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei tschanavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella tschanava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus tschanavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus tschanavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas tschanavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou tschagni", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei tschagnias\nca tei tschagnas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella tschagni", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus tschaneian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus tschaneias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas tschagnian\nc'els/ellas tschagnan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou tschanavi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei tschanavias\nca tei tschanavas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella tschanavi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus tschanavian\nca nus tschanavan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus tschanavias\nca vus tschanavas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas tschanavian\nc'els/ellas tschanavan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou tschanass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei tschanassas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella tschanass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus tschanassan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus tschanassas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas tschanassan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou tschanassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei tschanassias\ntei tschanassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella tschanassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus tschanassian\nnus tschanassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus tschanassias\nvus tschanassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas tschanassian\nels/ellas tschanassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a tschanar", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a tschanar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a tschanar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a tschanar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a tschanar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a tschanar", forms.getVerb().getFutur().getPlural3());

        assertEquals("tschano", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("tschanada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("tschanos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("tschanadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("tschagna!", forms.getVerb().getImperativ().getSingular());
        assertEquals("tschanad!", forms.getVerb().getImperativ().getPlural());

        assertEquals("tschanànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1k() {
        forms =  generator.generateConjugation("1k", "plantar");

        assertEquals("plantar", forms.getVerb().getInfinitiv());

        assertEquals("jou plànt\njou plàntel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei plàntas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella plànta", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus plantagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus plantaz", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas plàntan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou plantava", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei plantavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella plantava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus plantavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus plantavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas plantavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou plànti", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei plàntias\nca tei plàntas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella plànti", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus planteian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus planteias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas plàntian\nc'els/ellas plàntan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou plantavi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei plantavias\nca tei plantavas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella plantavi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus plantavian\nca nus plantavan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus plantavias\nca vus plantavas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas plantavian\nc'els/ellas plantavan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou plantass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei plantassas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella plantass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus plantassan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus plantassas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas plantassan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou plantassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei plantassias\ntei plantassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella plantassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus plantassian\nnus plantassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus plantassias\nvus plantassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas plantassian\nels/ellas plantassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a plantar", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a plantar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a plantar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a plantar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a plantar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a plantar", forms.getVerb().getFutur().getPlural3());

        assertEquals("planto", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("plantada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("plantos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("plantadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("plànta!", forms.getVerb().getImperativ().getSingular());
        assertEquals("plantad!", forms.getVerb().getImperativ().getPlural());

        assertEquals("plantànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1l() {
        forms =  generator.generateConjugation("1l", "sunar");

        assertEquals("sunar", forms.getVerb().getInfinitiv());

        assertEquals("jou sùn\njou sùnel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei sùnas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella sùna", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus sunagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus sunaz", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas sùnan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou sunava", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei sunavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella sunava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus sunavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus sunavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas sunavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou sùni", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei sùnias\nca tei sùnas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella sùni", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus suneian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus suneias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas sùnian\nc'els/ellas sùnan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou sunavi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei sunavias\nca tei sunavas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella sunavi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus sunavian\nca nus sunavan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus sunavias\nca vus sunavas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas sunavian\nc'els/ellas sunavan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou sunass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei sunassas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella sunass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus sunassan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus sunassas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas sunassan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou sunassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei sunassias\ntei sunassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella sunassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus sunassian\nnus sunassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus sunassias\nvus sunassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas sunassian\nels/ellas sunassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a sunar", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a sunar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a sunar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a sunar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a sunar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a sunar", forms.getVerb().getFutur().getPlural3());

        assertEquals("suno", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("sunada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("sunos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("sunadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("sùna!", forms.getVerb().getImperativ().getSingular());
        assertEquals("sunad!", forms.getVerb().getImperativ().getPlural());

        assertEquals("sunànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1m() {
        forms =  generator.generateConjugation("1m", "sfardar");

        assertEquals("sfardar", forms.getVerb().getInfinitiv());

        assertEquals("jou sfred\njou sfredel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei sfredas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella sfreda", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus sfardagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus sfardaz", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas sfredan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou sfardava", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei sfardavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella sfardava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus sfardavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus sfardavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas sfardavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou sfredi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei sfredias\nca tei sfredas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella sfredi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus sfardeian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus sfardeias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas sfredian\nc'els/ellas sfredan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou sfardavi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei sfardavias\nca tei sfardavas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella sfardavi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus sfardavian\nca nus sfardavan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus sfardavias\nca vus sfardavas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas sfardavian\nc'els/ellas sfardavan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou sfardass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei sfardassas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella sfardass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus sfardassan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus sfardassas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas sfardassan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou sfardassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei sfardassias\ntei sfardassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella sfardassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus sfardassian\nnus sfardassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus sfardassias\nvus sfardassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas sfardassian\nels/ellas sfardassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a sfardar", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a sfardar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a sfardar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a sfardar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a sfardar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a sfardar", forms.getVerb().getFutur().getPlural3());

        assertEquals("sfardo", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("sfardada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("sfardos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("sfardadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("sfreda!", forms.getVerb().getImperativ().getSingular());
        assertEquals("sfardad!", forms.getVerb().getImperativ().getPlural());

        assertEquals("sfardànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1n() {
        forms =  generator.generateConjugation("1n", "sgarmar");

        assertEquals("sgarmar", forms.getVerb().getInfinitiv());

        assertEquals("jou sgrom\njou sgromel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei sgromas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella sgroma", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus sgarmagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus sgarmaz", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas sgroman", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou sgarmava", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei sgarmavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella sgarmava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus sgarmavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus sgarmavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas sgarmavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou sgromi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei sgromias\nca tei sgromas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella sgromi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus sgarmeian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus sgarmeias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas sgromian\nc'els/ellas sgroman", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou sgarmavi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei sgarmavias\nca tei sgarmavas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella sgarmavi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus sgarmavian\nca nus sgarmavan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus sgarmavias\nca vus sgarmavas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas sgarmavian\nc'els/ellas sgarmavan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou sgarmass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei sgarmassas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella sgarmass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus sgarmassan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus sgarmassas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas sgarmassan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou sgarmassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei sgarmassias\ntei sgarmassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella sgarmassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus sgarmassian\nnus sgarmassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus sgarmassias\nvus sgarmassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas sgarmassian\nels/ellas sgarmassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a sgarmar", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a sgarmar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a sgarmar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a sgarmar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a sgarmar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a sgarmar", forms.getVerb().getFutur().getPlural3());

        assertEquals("sgarmo", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("sgarmada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("sgarmos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("sgarmadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("sgroma!", forms.getVerb().getImperativ().getSingular());
        assertEquals("sgarmad!", forms.getVerb().getImperativ().getPlural());

        assertEquals("sgarmànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1o() {
        forms =  generator.generateConjugation("1o", "sgartar");

        assertEquals("sgartar", forms.getVerb().getInfinitiv());

        assertEquals("jou sgrat\njou sgratel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei sgratas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella sgrata", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus sgartagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus sgartaz", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas sgratan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou sgartava", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei sgartavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella sgartava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus sgartavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus sgartavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas sgartavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou sgrati", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei sgratias\nca tei sgratas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella sgrati", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus sgarteian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus sgarteias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas sgratian\nc'els/ellas sgratan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou sgartavi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei sgartavias\nca tei sgartavas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella sgartavi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus sgartavian\nca nus sgartavan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus sgartavias\nca vus sgartavas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas sgartavian\nc'els/ellas sgartavan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou sgartass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei sgartassas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella sgartass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus sgartassan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus sgartassas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas sgartassan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou sgartassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei sgartassias\ntei sgartassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella sgartassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus sgartassian\nnus sgartassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus sgartassias\nvus sgartassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas sgartassian\nels/ellas sgartassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a sgartar", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a sgartar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a sgartar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a sgartar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a sgartar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a sgartar", forms.getVerb().getFutur().getPlural3());

        assertEquals("sgarto", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("sgartada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("sgartos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("sgartadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("sgrata!", forms.getVerb().getImperativ().getSingular());
        assertEquals("sgartad!", forms.getVerb().getImperativ().getPlural());

        assertEquals("sgartànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1p() {
        forms =  generator.generateConjugation("1p", "cantar");

        assertEquals("cantar", forms.getVerb().getInfinitiv());

        assertEquals("jou tgànt\njou tgàntel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei tgàntas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella tgànta", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus cantagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus cantaz", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas tgàntan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou cantava", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei cantavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella cantava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus cantavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus cantavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas cantavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou tgànti", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei tgàntias\nca tei tgàntas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella tgànti", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus canteian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus canteias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas tgàntian\nc'els/ellas tgàntan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou cantavi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei cantavias\nca tei cantavas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella cantavi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus cantavian\nca nus cantavan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus cantavias\nca vus cantavas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas cantavian\nc'els/ellas cantavan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou cantass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei cantassas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella cantass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus cantassan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus cantassas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas cantassan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou cantassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei cantassias\ntei cantassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella cantassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus cantassian\nnus cantassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus cantassias\nvus cantassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas cantassian\nels/ellas cantassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a cantar", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a cantar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a cantar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a cantar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a cantar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a cantar", forms.getVerb().getFutur().getPlural3());

        assertEquals("canto", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("cantada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("cantos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("cantadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("tgànta!", forms.getVerb().getImperativ().getSingular());
        assertEquals("cantad!", forms.getVerb().getImperativ().getPlural());

        assertEquals("cantànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1q() {
        forms =  generator.generateConjugation("1q", "anganar");

        assertEquals("anganar", forms.getVerb().getInfinitiv());

        assertEquals("jou angon\njou angonel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei angonas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella angona", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus anganagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus anganaz", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas angonan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou anganava", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei anganavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella anganava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus anganavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus anganavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas anganavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou angoni", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei angonias\nca tei angonas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella angoni", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus anganeian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus anganeias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas angonian\nc'els/ellas angonan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou anganavi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei anganavias\nca tei anganavas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella anganavi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus anganavian\nca nus anganavan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus anganavias\nca vus anganavas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas anganavian\nc'els/ellas anganavan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou anganass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei anganassas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella anganass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus anganassan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus anganassas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas anganassan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou anganassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei anganassias\ntei anganassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella anganassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus anganassian\nnus anganassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus anganassias\nvus anganassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas anganassian\nels/ellas anganassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt ad anganar", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans ad anganar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean ad anganar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn ad anganar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez ad anganar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan ad anganar", forms.getVerb().getFutur().getPlural3());

        assertEquals("angano", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("anganada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("anganos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("anganadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("angona!", forms.getVerb().getImperativ().getSingular());
        assertEquals("anganad!", forms.getVerb().getImperativ().getPlural());

        assertEquals("anganànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule2() {
        forms =  generator.generateConjugation("2", "spargnear");

        assertEquals("spargnear", forms.getVerb().getInfinitiv());

        assertEquals("jou spargn\njou spargnel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei spargnas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella spargna", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus spargnagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus spargneaz", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas spargnan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou spargneava", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei spargneavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella spargneava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus spargneavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus spargneavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas spargneavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou spargni", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei spargnias\nca tei spargnas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella spargni", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus spargneian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus spargneias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas spargnian\nc'els/ellas spargnan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou spargneavi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei spargneavias\nca tei spargneavas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella spargneavi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus spargneavian\nca nus spargneavan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus spargneavias\nca vus spargneavas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas spargneavian\nc'els/ellas spargneavan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou spargness", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei spargnessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella spargness", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus spargnessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus spargnessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas spargnessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou spargnessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei spargnessias\ntei spargnessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella spargnessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus spargnessian\nnus spargnessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus spargnessias\nvus spargnessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas spargnessian\nels/ellas spargnessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a spargnear", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a spargnear", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a spargnear", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a spargnear", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a spargnear", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a spargnear", forms.getVerb().getFutur().getPlural3());

        assertEquals("spargnieu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("spargneada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("spargnieus", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("spargneadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("spargna!", forms.getVerb().getImperativ().getSingular());
        assertEquals("spargnead!", forms.getVerb().getImperativ().getPlural());

        assertEquals("spargnànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule2a() {
        forms =  generator.generateConjugation("2a", "samtgear");

        assertEquals("samtgear", forms.getVerb().getInfinitiv());

        assertEquals("jou semtg\njou semtgel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei semtgas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella semtga", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus samtgagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus samtgeaz", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas semtgan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou samtgeava", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei samtgeavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella samtgeava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus samtgeavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus samtgeavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas samtgeavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou semtgi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei semtgias\nca tei semtgas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella semtgi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus samtgeian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus samtgeias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas semtgian\nc'els/ellas semtgan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou samtgeavi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei samtgeavias\nca tei samtgeavas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella samtgeavi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus samtgeavian\nca nus samtgeavan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus samtgeavias\nca vus samtgeavas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas samtgeavian\nc'els/ellas samtgeavan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou samtgess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei samtgessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella samtgess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus samtgessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus samtgessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas samtgessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou samtgessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei samtgessias\ntei samtgessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella samtgessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus samtgessian\nnus samtgessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus samtgessias\nvus samtgessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas samtgessian\nels/ellas samtgessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a samtgear", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a samtgear", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a samtgear", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a samtgear", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a samtgear", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a samtgear", forms.getVerb().getFutur().getPlural3());

        assertEquals("samtgieu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("samtgeada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("samtgieus", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("samtgeadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("semtga!", forms.getVerb().getImperativ().getSingular());
        assertEquals("samtgead!", forms.getVerb().getImperativ().getPlural());

        assertEquals("samtgànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule2b() {
        forms =  generator.generateConjugation("2b", "spitgear");

        assertEquals("spitgear", forms.getVerb().getInfinitiv());

        assertEquals("jou spetg\njou spetgel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei spetgas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella spetga", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus spitgagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus spitgeaz", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas spetgan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou spitgeava", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei spitgeavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella spitgeava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus spitgeavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus spitgeavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas spitgeavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou spetgi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei spetgias\nca tei spetgas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella spetgi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus spitgeian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus spitgeias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas spetgian\nc'els/ellas spetgan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou spitgeavi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei spitgeavias\nca tei spitgeavas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella spitgeavi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus spitgeavian\nca nus spitgeavan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus spitgeavias\nca vus spitgeavas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas spitgeavian\nc'els/ellas spitgeavan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou spitgess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei spitgessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella spitgess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus spitgessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus spitgessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas spitgessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou spitgessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei spitgessias\ntei spitgessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella spitgessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus spitgessian\nnus spitgessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus spitgessias\nvus spitgessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas spitgessian\nels/ellas spitgessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a spitgear", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a spitgear", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a spitgear", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a spitgear", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a spitgear", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a spitgear", forms.getVerb().getFutur().getPlural3());

        assertEquals("spitgieu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("spitgeada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("spitgieus", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("spitgeadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("spetga!", forms.getVerb().getImperativ().getSingular());
        assertEquals("spitgead!", forms.getVerb().getImperativ().getPlural());

        assertEquals("spitgànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule2c() {
        forms =  generator.generateConjugation("2c", "cuptgear");

        assertEquals("cuptgear", forms.getVerb().getInfinitiv());

        assertEquals("jou coptg\njou coptgel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei coptgas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella coptga", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus cuptgagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus cuptgeaz", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas coptgan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou cuptgeava", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei cuptgeavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella cuptgeava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus cuptgeavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus cuptgeavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas cuptgeavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou coptgi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei coptgias\nca tei coptgas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella coptgi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus cuptgeian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus cuptgeias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas coptgian\nc'els/ellas coptgan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou cuptgeavi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei cuptgeavias\nca tei cuptgeavas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella cuptgeavi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus cuptgeavian\nca nus cuptgeavan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus cuptgeavias\nca vus cuptgeavas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas cuptgeavian\nc'els/ellas cuptgeavan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou cuptgess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei cuptgessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella cuptgess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus cuptgessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus cuptgessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas cuptgessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou cuptgessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei cuptgessias\ntei cuptgessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella cuptgessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus cuptgessian\nnus cuptgessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus cuptgessias\nvus cuptgessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas cuptgessian\nels/ellas cuptgessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a cuptgear", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a cuptgear", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a cuptgear", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a cuptgear", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a cuptgear", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a cuptgear", forms.getVerb().getFutur().getPlural3());

        assertEquals("cuptgieu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("cuptgeada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("cuptgieus", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("cuptgeadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("coptga!", forms.getVerb().getImperativ().getSingular());
        assertEquals("cuptgead!", forms.getVerb().getImperativ().getPlural());

        assertEquals("cuptgànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule2d() {
        forms =  generator.generateConjugation("2d", "tschartgear");

        assertEquals("tschartgear", forms.getVerb().getInfinitiv());

        assertEquals("jou tscheartg\njou tscheartgel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei tscheartgas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella tscheartga", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus tschartgagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus tschartgeaz", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas tscheartgan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou tschartgeava", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei tschartgeavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella tschartgeava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus tschartgeavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus tschartgeavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas tschartgeavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou tscheartgi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei tscheartgias\nca tei tscheartgas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella tscheartgi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus tschartgeian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus tschartgeias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas tscheartgian\nc'els/ellas tscheartgan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou tschartgeavi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei tschartgeavias\nca tei tschartgeavas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella tschartgeavi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus tschartgeavian\nca nus tschartgeavan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus tschartgeavias\nca vus tschartgeavas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas tschartgeavian\nc'els/ellas tschartgeavan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou tschartgess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei tschartgessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella tschartgess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus tschartgessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus tschartgessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas tschartgessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou tschartgessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei tschartgessias\ntei tschartgessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella tschartgessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus tschartgessian\nnus tschartgessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus tschartgessias\nvus tschartgessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas tschartgessian\nels/ellas tschartgessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a tschartgear", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a tschartgear", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a tschartgear", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a tschartgear", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a tschartgear", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a tschartgear", forms.getVerb().getFutur().getPlural3());

        assertEquals("tschartgieu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("tschartgeada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("tschartgieus", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("tschartgeadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("tscheartga!", forms.getVerb().getImperativ().getSingular());
        assertEquals("tschartgead!", forms.getVerb().getImperativ().getPlural());

        assertEquals("tschartgànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule2e() {
        forms =  generator.generateConjugation("2e", "svagliear");

        assertEquals("svagliear", forms.getVerb().getInfinitiv());

        assertEquals("jou svegl\njou svegliel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei sveglias", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella sveglia", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus svagliagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus svaglieaz", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas sveglian", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou svaglieava", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei svaglieavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella svaglieava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus svaglieavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus svaglieavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas svaglieavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou svegli", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei sveglias", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella svegli", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus svaglieian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus svaglieias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas sveglian", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou svaglieavi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei svaglieavias\nca tei svaglieavas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella svaglieavi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus svaglieavian\nca nus svaglieavan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus svaglieavias\nca vus svaglieavas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas svaglieavian\nc'els/ellas svaglieavan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou svagliess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei svagliessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella svagliess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus svagliessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus svagliessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas svagliessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou svagliessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei svagliessias\ntei svagliessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella svagliessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus svagliessian\nnus svagliessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus svagliessias\nvus svagliessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas svagliessian\nels/ellas svagliessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a svagliear", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a svagliear", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a svagliear", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a svagliear", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a svagliear", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a svagliear", forms.getVerb().getFutur().getPlural3());

        assertEquals("svaglieu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("svaglieada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("svaglieus", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("svaglieadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("sveglia!", forms.getVerb().getImperativ().getSingular());
        assertEquals("svagliead!", forms.getVerb().getImperativ().getPlural());

        assertEquals("svagliànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule2f() {
        forms =  generator.generateConjugation("2f", "spassagear");

        assertEquals("spassagear", forms.getVerb().getInfinitiv());

        assertEquals("jou spasseg\njou spassegel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei spassegias", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella spassegia", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus spassagiagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus spassageaz", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas spassegian", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou spassageava", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei spassageavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella spassageava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus spassageavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus spassageavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas spassageavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou spassegi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei spassegias", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella spassegi", forms.getVerb().getConjunctiv().getSing3());
        //assertEquals("ca nus sapssegeian", forms.getVerb().getConjunctiv().getPlural1());
        //assertEquals("ca vus spassegeias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas spassegian", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou spassageavi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei spassageavias\nca tei spassageavas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella spassageavi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus spassageavian\nca nus spassageavan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus spassageavias\nca vus spassageavas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas spassageavian\nc'els/ellas spassageavan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou spassagess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei spassagessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella spassagess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus spassagessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus spassagessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas spassagessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou spassagessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei spassagessias\ntei spassagessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella spassagessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus spassagessian\nnus spassagessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus spassagessias\nvus spassagessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas spassagessian\nels/ellas spassagessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a spassagear", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a spassagear", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a spassagear", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a spassagear", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a spassagear", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a spassagear", forms.getVerb().getFutur().getPlural3());

        assertEquals("spassagieu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("spassageada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("spassagieus", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("spassageadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("spassegia!", forms.getVerb().getImperativ().getSingular());
        assertEquals("spassagead!", forms.getVerb().getImperativ().getPlural());

        assertEquals("spassagiànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule2g() {
        forms =  generator.generateConjugation("2g", "zambargear");

        assertEquals("zambargear", forms.getVerb().getInfinitiv());

        assertEquals("jou zambreg\njou zambregel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei zambregias", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella zambregia", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus zambargiagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus zambargeaz", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas zambregian", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou zambargeava", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei zambargeavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella zambargeava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus zambargeavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus zambargeavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas zambargeavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou zambregi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei zambregias", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella zambregi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus zambargeian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus zambargeias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas zambregian", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou zambargeavi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei zambargeavias\nca tei zambargeavas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella zambargeavi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus zambargeavian\nca nus zambargeavan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus zambargeavias\nca vus zambargeavas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas zambargeavian\nc'els/ellas zambargeavan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou zambargess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei zambargessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella zambargess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus zambargessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus zambargessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas zambargessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou zambargessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei zambargessias\ntei zambargessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella zambargessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus zambargessian\nnus zambargessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus zambargessias\nvus zambargessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas zambargessian\nels/ellas zambargessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a zambargear", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a zambargear", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a zambargear", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a zambargear", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a zambargear", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a zambargear", forms.getVerb().getFutur().getPlural3());

        assertEquals("zambargieu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("zambargeada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("zambargieus", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("zambargeadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("zambregia!", forms.getVerb().getImperativ().getSingular());
        assertEquals("zambargead!", forms.getVerb().getImperativ().getPlural());

        assertEquals("zambargiànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3() {
        forms =  generator.generateConjugation("3", "proponer");

        assertEquals("proponer", forms.getVerb().getInfinitiv());

        assertEquals("jou propon\njou proponel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei proponas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella propona", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus proponagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus proponez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas proponan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou proponeva", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei proponevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella proponeva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus proponevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus proponevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas proponevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou proponi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei proponias\nca tei proponas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella proponi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus proponeian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus proponeias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas proponian\nc'els/ellas proponan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou proponevi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei proponevias\nca tei proponevas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella proponevi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus proponevian\nca nus proponevan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus proponevias\nca vus proponevas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas proponevian\nc'els/ellas proponevan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou proponess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei proponessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella proponess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus proponessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus proponessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas proponessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou proponessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei proponessias\ntei proponessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella proponessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus proponessian\nnus proponessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus proponessias\nvus proponessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas proponessian\nels/ellas proponessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a proponer", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a proponer", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a proponer", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a proponer", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a proponer", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a proponer", forms.getVerb().getFutur().getPlural3());

        assertEquals("proponieu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("proponida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("proponieus", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("proponidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("propona!", forms.getVerb().getImperativ().getSingular());
        assertEquals("proponed!", forms.getVerb().getImperativ().getPlural());

        assertEquals("proponànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3a() {
        forms =  generator.generateConjugation("3a", "vender");

        assertEquals("vender", forms.getVerb().getInfinitiv());

        assertEquals("jou vend\njou vendel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei vendas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella venda", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus vandagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus vandez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas vendan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou vandeva", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei vandevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella vandeva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus vandevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus vandevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas vandevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou vendi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei vendias\nca tei vendas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella vendi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus vandeian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus vandeias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas vendian\nc'els/ellas vendan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou vandevi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei vandevias\nca tei vandevas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella vandevi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus vandevian\nca nus vandevan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus vandevias\nca vus vandevas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas vandevian\nc'els/ellas vandevan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou vandess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei vandessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella vandess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus vandessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus vandessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas vandessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou vandessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei vandessias\ntei vandessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella vandessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus vandessian\nnus vandessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus vandessias\nvus vandessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas vandessian\nels/ellas vandessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a vender", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a vender", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a vender", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a vender", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a vender", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a vender", forms.getVerb().getFutur().getPlural3());

        assertEquals("vandieu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("vandida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("vandieus", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("vandidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("venda!", forms.getVerb().getImperativ().getSingular());
        assertEquals("vanded!", forms.getVerb().getImperativ().getPlural());

        assertEquals("vandànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3b() {
        forms =  generator.generateConjugation("3b", "dezeder");

        assertEquals("dezeder", forms.getVerb().getInfinitiv());

        assertEquals("jou dezed\njou dezedel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei dezedas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella dezeda", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus dezidagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus dezidez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas dezedan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou dezideva", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei dezidevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella dezideva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus dezidevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus dezidevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas dezidevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou dezedi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei dezedias\nca tei dezedas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella dezedi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus dezideian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus dezideias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas dezedian\nc'els/ellas dezedan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou dezidevi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei dezidevias\nca tei dezidevas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella dezidevi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus dezidevian\nca nus dezidevan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus dezidevias\nca vus dezidevas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas dezidevian\nc'els/ellas dezidevan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou dezidess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei dezidessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella dezidess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus dezidessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus dezidessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas dezidessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou dezidessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei dezidessias\ntei dezidessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella dezidessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus dezidessian\nnus dezidessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus dezidessias\nvus dezidessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas dezidessian\nels/ellas dezidessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a dezeder", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a dezeder", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a dezeder", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a dezeder", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a dezeder", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a dezeder", forms.getVerb().getFutur().getPlural3());

        assertEquals("dezidieu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("dezidida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("dezidieus", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("dezididas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("dezeda!", forms.getVerb().getImperativ().getSingular());
        assertEquals("dezided!", forms.getVerb().getImperativ().getPlural());

        assertEquals("dezidànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3c() {
        forms =  generator.generateConjugation("3c", "pearder");

        assertEquals("pearder", forms.getVerb().getInfinitiv());

        assertEquals("jou peard\njou peardel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei peardas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella pearda", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus pardagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus pardez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas peardan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou pardeva", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei pardevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella pardeva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus pardevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus pardevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas pardevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou peardi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei peardias\nca tei peardas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella peardi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus pardeian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus pardeias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas peardian\nc'els/ellas peardan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou pardevi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei pardevias\nca tei pardevas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella pardevi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus pardevian\nca nus pardevan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus pardevias\nca vus pardevas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas pardevian\nc'els/ellas pardevan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou pardess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei pardessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella pardess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus pardessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus pardessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas pardessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou pardessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei pardessias\ntei pardessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella pardessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus pardessian\nnus pardessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus pardessias\nvus pardessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas pardessian\nels/ellas pardessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a pearder", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a pearder", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a pearder", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a pearder", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a pearder", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a pearder", forms.getVerb().getFutur().getPlural3());

        /*
        Example is irregular
        assertEquals("pears", forms.getVerb().getParticipperfect().getMs());
        assertEquals("pearsa", forms.getVerb().getParticipperfect().getFs());
        assertEquals("pears", forms.getVerb().getParticipperfect().getMp());
        assertEquals("pearsas", forms.getVerb().getParticipperfect().getFp());
         */

        assertEquals("pearda!", forms.getVerb().getImperativ().getSingular());
        assertEquals("parded!", forms.getVerb().getImperativ().getPlural());

        assertEquals("pardànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3d() {
        forms =  generator.generateConjugation("3d", "tànscher");

        assertEquals("tànscher", forms.getVerb().getInfinitiv());

        assertEquals("jou tànsch\njou tànschel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei tànschas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella tànscha", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus tanschagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus tanschez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas tànschan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou tanscheva", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei tanschevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella tanscheva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus tanschevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus tanschevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas tanschevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou tànschi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei tànschias\nca tei tànschas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella tànschi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus tanscheian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus tanscheias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas tànschian\nc'els/ellas tànschan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou tanschevi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei tanschevias\nca tei tanschevas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella tanschevi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus tanschevian\nca nus tanschevan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus tanschevias\nca vus tanschevas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas tanschevian\nc'els/ellas tanschevan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou tanschess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei tanschessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella tanschess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus tanschessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus tanschessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas tanschessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou tanschessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei tanschessias\ntei tanschessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella tanschessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus tanschessian\nnus tanschessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus tanschessias\nvus tanschessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas tanschessian\nels/ellas tanschessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a tànscher", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a tànscher", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a tànscher", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a tànscher", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a tànscher", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a tànscher", forms.getVerb().getFutur().getPlural3());

        assertEquals("tanschieu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("tanschida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("tanschieus", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("tanschidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("tànscha!", forms.getVerb().getImperativ().getSingular());
        assertEquals("tansched!", forms.getVerb().getImperativ().getPlural());

        assertEquals("tanschànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3e() {
        forms =  generator.generateConjugation("3e", "porscher");

        assertEquals("porscher", forms.getVerb().getInfinitiv());

        assertEquals("jou porsch\njou porschel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei porschas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella porscha", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus purschagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus purschez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas porschan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou purscheva", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei purschevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella purscheva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus purschevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus purschevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas purschevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou porschi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei porschias\nca tei porschas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella porschi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus purscheian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus purscheias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas porschian\nc'els/ellas porschan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou purschevi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei purschevias\nca tei purschevas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella purschevi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus purschevian\nca nus purschevan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus purschevias\nca vus purschevas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas purschevian\nc'els/ellas purschevan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou purschess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei purschessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella purschess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus purschessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus purschessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas purschessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou purschessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei purschessias\ntei purschessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella purschessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus purschessian\nnus purschessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus purschessias\nvus purschessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas purschessian\nels/ellas purschessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a porscher", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a porscher", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a porscher", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a porscher", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a porscher", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a porscher", forms.getVerb().getFutur().getPlural3());

        assertEquals("purschieu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("purschida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("purschieus", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("purschidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("porscha!", forms.getVerb().getImperativ().getSingular());
        assertEquals("pursched!", forms.getVerb().getImperativ().getPlural());

        assertEquals("purschànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3f() {
        forms =  generator.generateConjugation("3f", "liger");

        assertEquals("liger", forms.getVerb().getInfinitiv());

        assertEquals("jou lig\njou ligel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei ligias", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella ligia", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus ligiagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus ligez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas ligian", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou ligeva", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei ligevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella ligeva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus ligevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus ligevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas ligevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou ligi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei ligias", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella ligi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus ligeian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus ligeias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas ligian", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou ligevi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei ligevias\nca tei ligevas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella ligevi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus ligevian\nca nus ligevan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus ligevias\nca vus ligevas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas ligevian\nc'els/ellas ligevan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou ligess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei ligessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella ligess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus ligessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus ligessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas ligessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou ligessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei ligessias\ntei ligessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella ligessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus ligessian\nnus ligessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus ligessias\nvus ligessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas ligessian\nels/ellas ligessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a liger", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a liger", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a liger", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a liger", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a liger", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a liger", forms.getVerb().getFutur().getPlural3());

        assertEquals("ligieu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("ligida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("ligieus", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("ligidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("ligia!", forms.getVerb().getImperativ().getSingular());
        assertEquals("liged!", forms.getVerb().getImperativ().getPlural());

        assertEquals("ligiànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4() {
        forms =  generator.generateConjugation("4", "partir");

        assertEquals("partir", forms.getVerb().getInfinitiv());

        assertEquals("jou part\njou partel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei partas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella parta", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus partagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus partez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas partan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou parteva", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei partevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella parteva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus partevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus partevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas partevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou parti", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei partias\nca tei partas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella parti", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus parteian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus parteias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas partian\nc'els/ellas partan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou partevi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei partevias\nca tei partevas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella partevi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus partevian\nca nus partevan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus partevias\nca vus partevas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas partevian\nc'els/ellas partevan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou partess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei partessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella partess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus partessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus partessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas partessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou partessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei partessias\ntei partessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella partessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus partessian\nnus partessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus partessias\nvus partessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas partessian\nels/ellas partessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a partir", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a partir", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a partir", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a partir", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a partir", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a partir", forms.getVerb().getFutur().getPlural3());

        assertEquals("partieu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("partida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("partieus", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("partidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("parta!", forms.getVerb().getImperativ().getSingular());
        assertEquals("parted!", forms.getVerb().getImperativ().getPlural());

        assertEquals("partànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4a() {
        forms =  generator.generateConjugation("4a", "santir");

        assertEquals("santir", forms.getVerb().getInfinitiv());

        assertEquals("jou saint\njou saintel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei saintas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella sainta", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus santagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus santez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas saintan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou santeva", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei santevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella santeva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus santevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus santevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas santevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou sainti", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei saintias\nca tei saintas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella sainti", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus santeian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus santeias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas saintian\nc'els/ellas saintan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou santevi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei santevias\nca tei santevas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella santevi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus santevian\nca nus santevan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus santevias\nca vus santevas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas santevian\nc'els/ellas santevan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou santess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei santessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella santess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus santessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus santessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas santessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou santessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei santessias\ntei santessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella santessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus santessian\nnus santessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus santessias\nvus santessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas santessian\nels/ellas santessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a santir", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a santir", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a santir", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a santir", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a santir", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a santir", forms.getVerb().getFutur().getPlural3());

        assertEquals("santieu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("santida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("santieus", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("santidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("sainta!", forms.getVerb().getImperativ().getSingular());
        assertEquals("santed!", forms.getVerb().getImperativ().getPlural());

        assertEquals("santànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4b() {
        forms =  generator.generateConjugation("4b", "sgrignir");

        assertEquals("sgrignir", forms.getVerb().getInfinitiv());

        assertEquals("jou sgregn\njou sgregnel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei sgregnas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella sgregna", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus sgrignagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus sgrignez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas sgregnan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou sgrigneva", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei sgrignevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella sgrigneva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus sgrignevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus sgrignevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas sgrignevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou sgregni", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei sgregnias\nca tei sgregnas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella sgregni", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus sgrigneian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus sgrigneias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas sgregnian\nc'els/ellas sgregnan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou sgrignevi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei sgrignevias\nca tei sgrignevas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella sgrignevi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus sgrignevian\nca nus sgrignevan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus sgrignevias\nca vus sgrignevas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas sgrignevian\nc'els/ellas sgrignevan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou sgrigness", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei sgrignessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella sgrigness", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus sgrignessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus sgrignessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas sgrignessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou sgrignessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei sgrignessias\ntei sgrignessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella sgrignessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus sgrignessian\nnus sgrignessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus sgrignessias\nvus sgrignessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas sgrignessian\nels/ellas sgrignessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a sgrignir", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a sgrignir", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a sgrignir", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a sgrignir", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a sgrignir", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a sgrignir", forms.getVerb().getFutur().getPlural3());

        assertEquals("sgrignieu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("sgrignida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("sgrignieus", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("sgrignidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("sgregna!", forms.getVerb().getImperativ().getSingular());
        assertEquals("sgrigned!", forms.getVerb().getImperativ().getPlural());

        assertEquals("sgrignànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4c() {
        forms =  generator.generateConjugation("4c", "udir");

        assertEquals("udir", forms.getVerb().getInfinitiv());

        assertEquals("jou od\njou odel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei odas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella oda", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus udagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus udez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas odan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou udeva", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei udevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella udeva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus udevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus udevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas udevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou odi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei odias\nca tei odas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella odi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus udeian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus udeias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas odian\nc'els/ellas odan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou udevi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei udevias\nca tei udevas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella udevi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus udevian\nca nus udevan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus udevias\nca vus udevas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas udevian\nc'els/ellas udevan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou udess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei udessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella udess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus udessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus udessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas udessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou udessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei udessias\ntei udessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella udessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus udessian\nnus udessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus udessias\nvus udessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas udessian\nels/ellas udessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt ad udir", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans ad udir", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean ad udir", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn ad udir", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez ad udir", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan ad udir", forms.getVerb().getFutur().getPlural3());

        assertEquals("udieu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("udida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("udieus", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("udidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("oda!", forms.getVerb().getImperativ().getSingular());
        assertEquals("uded!", forms.getVerb().getImperativ().getPlural());

        assertEquals("udànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4d() {
        forms =  generator.generateConjugation("4d", "bargir");

        assertEquals("bargir", forms.getVerb().getInfinitiv());

        assertEquals("jou brag\njou bragel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei bragias", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella bragia", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus bargiagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus bargez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas bragian", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou bargeva", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei bargevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella bargeva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus bargevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus bargevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas bargevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou bragi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei bragias", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella bragi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus bargeian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus bargeias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas bragian", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou bargevi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei bargevias\nca tei bargevas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella bargevi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus bargevian\nca nus bargevan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus bargevias\nca vus bargevas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas bargevian\nc'els/ellas bargevan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou bargess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei bargessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella bargess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus bargessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus bargessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas bargessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou bargessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei bargessias\ntei bargessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella bargessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus bargessian\nnus bargessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus bargessias\nvus bargessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas bargessian\nels/ellas bargessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a bargir", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a bargir", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a bargir", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a bargir", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a bargir", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a bargir", forms.getVerb().getFutur().getPlural3());

        assertEquals("bargieu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("bargida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("bargieus", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("bargidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("bragia!", forms.getVerb().getImperativ().getSingular());
        assertEquals("barged!", forms.getVerb().getImperativ().getPlural());

        assertEquals("bargiànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4e() {
        forms =  generator.generateConjugation("4e", "ancurir");

        assertEquals("ancurir", forms.getVerb().getInfinitiv());

        assertEquals("jou anquier\njou anquierel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei anquieras", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella anquiera", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus ancuragn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus ancurez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas anquieran", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou ancureva", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei ancurevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella ancureva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus ancurevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus ancurevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas ancurevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou anquieri", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei anquierias\nca tei anquieras", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella anquieri", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus ancureian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus ancureias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas anquierian\nc'els/ellas anquieran", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou ancurevi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei ancurevias\nca tei ancurevas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella ancurevi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus ancurevian\nca nus ancurevan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus ancurevias\nca vus ancurevas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas ancurevian\nc'els/ellas ancurevan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou ancuress", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei ancuressas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella ancuress", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus ancuressan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus ancuressas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas ancuressan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou ancuressi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei ancuressias\ntei ancuressas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella ancuressi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus ancuressian\nnus ancuressan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus ancuressias\nvus ancuressas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas ancuressian\nels/ellas ancuressan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt ad ancurir", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans ad ancurir", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean ad ancurir", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn ad ancurir", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez ad ancurir", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan ad ancurir", forms.getVerb().getFutur().getPlural3());

        /*
        Irregular form
        assertEquals("ancuretg", forms.getVerb().getParticipperfect().getMs());
        assertEquals("ancuretga", forms.getVerb().getParticipperfect().getFs());
        assertEquals("ancuretgs", forms.getVerb().getParticipperfect().getMp());
        assertEquals("ancuretgas", forms.getVerb().getParticipperfect().getFp());
         */

        assertEquals("anquiera!", forms.getVerb().getImperativ().getSingular());
        assertEquals("ancured!", forms.getVerb().getImperativ().getPlural());

        assertEquals("ancurànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule5() {
        forms =  generator.generateConjugation("5", "gratular");

        assertEquals("gratular", forms.getVerb().getInfinitiv());

        assertEquals("jou gratulesch\njou gratuleschel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei gratuleschas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella gratulescha", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus gratulagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus gratulaz", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas gratuleschan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou gratulava", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei gratulavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella gratulava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus gratulavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus gratulavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas gratulavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou gratuleschi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei gratuleschias\nca tei gratuleschas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella gratuleschi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus gratuleian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus gratuleias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas gratuleschian\nc'els/ellas gratuleschan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou gratulavi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei gratulavias\nca tei gratulavas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella gratulavi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus gratulavian\nca nus gratulavan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus gratulavias\nca vus gratulavas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas gratulavian\nc'els/ellas gratulavan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou gratulass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei gratulassas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella gratulass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus gratulassan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus gratulassas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas gratulassan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou gratulassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei gratulassias\ntei gratulassas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella gratulassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus gratulassian\nnus gratulassan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus gratulassias\nvus gratulassas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas gratulassian\nels/ellas gratulassan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a gratular", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a gratular", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a gratular", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a gratular", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a gratular", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a gratular", forms.getVerb().getFutur().getPlural3());

        assertEquals("gratulo", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("gratulada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("gratulos", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("gratuladas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("gratulescha!", forms.getVerb().getImperativ().getSingular());
        assertEquals("gratulad!", forms.getVerb().getImperativ().getPlural());

        assertEquals("gratulànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule6() {
        forms =  generator.generateConjugation("6", "inditgear");

        assertEquals("inditgear", forms.getVerb().getInfinitiv());

        assertEquals("jou inditgesch\njou inditgeschel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei inditgeschas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella inditgescha", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus inditgagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus inditgeaz", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas inditgeschan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou inditgeava", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei inditgeavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella inditgeava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus inditgeavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus inditgeavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas inditgeavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou inditgeschi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei inditgeschias\nca tei inditgeschas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella inditgeschi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus inditgeian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus inditgeias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas inditgeschian\nc'els/ellas inditgeschan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou inditgevi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei inditgevias\nca tei inditgevas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella inditgevi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus inditgevian\nca nus inditgevan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus inditgevias\nca vus inditgevas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas inditgevian\nc'els/ellas inditgevan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou inditgess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei inditgessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella inditgess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus inditgessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus inditgessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas inditgessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou inditgessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei inditgessias\ntei inditgessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella inditgessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus inditgessian\nnus inditgessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus inditgessias\nvus inditgessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas inditgessian\nels/ellas inditgessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt ad inditgear", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans ad inditgear", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean ad inditgear", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn ad inditgear", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez ad inditgear", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan ad inditgear", forms.getVerb().getFutur().getPlural3());

        assertEquals("inditgieu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("inditgeada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("inditgieus", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("inditgeadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("inditgescha!", forms.getVerb().getImperativ().getSingular());
        assertEquals("inditgead!", forms.getVerb().getImperativ().getPlural());

        assertEquals("inditgànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule7() {
        forms =  generator.generateConjugation("7", "capir");

        assertEquals("capir", forms.getVerb().getInfinitiv());

        assertEquals("jou capesch\njou capeschel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei capeschas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella capescha", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus capagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus capez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas capeschan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou capeva", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei capevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella capeva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus capevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus capevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas capevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou capeschi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei capeschias\nca tei capeschas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella capeschi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus capeian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus capeias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas capeschian\nc'els/ellas capeschan", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou capevi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei capevias\nca tei capevas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella capevi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus capevian\nca nus capevan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus capevias\nca vus capevas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas capevian\nc'els/ellas capevan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou capess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei capessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella capess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus capessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus capessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas capessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou capessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei capessias\ntei capessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella capessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus capessian\nnus capessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus capessias\nvus capessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas capessian\nels/ellas capessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a capir", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a capir", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a capir", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a capir", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a capir", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a capir", forms.getVerb().getFutur().getPlural3());

        assertEquals("capieu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("capida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("capieus", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("capidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("capescha!", forms.getVerb().getImperativ().getSingular());
        assertEquals("caped!", forms.getVerb().getImperativ().getPlural());

        assertEquals("capànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule9() {
        forms =  generator.generateConjugation("9", "parer");

        assertEquals("parer", forms.getVerb().getInfinitiv());

        assertEquals("jou par\njou parel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei paras", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella para", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus paragn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus parez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas paran", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou pareva", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei parevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella pareva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus parevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus parevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas parevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou pari", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei parias\nca tei paras", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella pari", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus pareian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus pareias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas parian\nc'els/ellas paran", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou parevi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei parevias\nca tei parevas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella parevi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus parevian\nca nus parevan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus parevias\nca vus parevas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas parevian\nc'els/ellas parevan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou paress", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei paressas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella paress", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus paressan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus paressas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas paressan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou paressi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei paressias\ntei paressas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella paressi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus paressian\nnus paressan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus paressias\nvus paressas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas paressian\nels/ellas paressan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a parer", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a parer", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a parer", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a parer", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a parer", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a parer", forms.getVerb().getFutur().getPlural3());

        assertEquals("parieu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("parida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("parieus", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("paridas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("para!", forms.getVerb().getImperativ().getSingular());
        assertEquals("pared!", forms.getVerb().getImperativ().getPlural());

        assertEquals("parànd", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule9a() {
        forms =  generator.generateConjugation("9a", "tamer");

        assertEquals("tamer", forms.getVerb().getInfinitiv());

        assertEquals("jou tem\njou temel", forms.getVerb().getPreschent().getSing1());
        assertEquals("tei temas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella tema", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus tamagn", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus tamez", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas teman", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jou tameva", forms.getVerb().getImperfect().getSing1());
        assertEquals("tei tamevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella tameva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus tamevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus tamevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas tamevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("ca jou temi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("ca tei temias\nca tei temas", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("c'el/ella temi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("ca nus tameian", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("ca vus tameias", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("c'els/ellas temian\nc'els/ellas teman", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("ca jou tamevi", forms.getVerb().getConjunctivImperfect().getSing1());
        assertEquals("ca tei tamevias\nca tei tamevas", forms.getVerb().getConjunctivImperfect().getSing2());
        assertEquals("c'el/ella tamevi", forms.getVerb().getConjunctivImperfect().getSing3());
        assertEquals("ca nus tamevian\nca nus tamevan", forms.getVerb().getConjunctivImperfect().getPlural1());
        assertEquals("ca vus tamevias\nca vus tamevas", forms.getVerb().getConjunctivImperfect().getPlural2());
        assertEquals("c'els/ellas tamevian\nc'els/ellas tamevan", forms.getVerb().getConjunctivImperfect().getPlural3());

        assertEquals("jou tamess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("tei tamessas", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella tamess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus tamessan", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus tamessas", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas tamessan", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jou tamessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("tei tamessias\ntei tamessas", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella tamessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus tamessian\nnus tamessan", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus tamessias\nvus tamessas", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas tamessian\nels/ellas tamessan", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jou vignt a tamer", forms.getVerb().getFutur().getSing1());
        assertEquals("tei veans a tamer", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vean a tamer", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vagnagn a tamer", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vagnez a tamer", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vignan a tamer", forms.getVerb().getFutur().getPlural3());

        assertEquals("tamieu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("tamida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("tamieus", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("tamidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("tema!", forms.getVerb().getImperativ().getSingular());
        assertEquals("tamed!", forms.getVerb().getImperativ().getPlural());

        assertEquals("tamànd", forms.getVerb().getGerundium());
    }
}
