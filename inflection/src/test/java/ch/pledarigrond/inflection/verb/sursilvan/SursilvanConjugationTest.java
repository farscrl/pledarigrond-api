package ch.pledarigrond.inflection.verb.sursilvan;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.verb.sursilvan.SursilvanConjugation;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class SursilvanConjugationTest {

    private static SursilvanConjugation generator;
    private InflectionDto forms;

    @BeforeClass
    public static void initialize() {
        generator = new SursilvanConjugation();
    }

    @Test
    public void testRule1() {
        forms =  generator.generateConjugation("1", "gidar");

        Assert.assertEquals("gidar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu gidel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti gidas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella gida", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus gidein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus gideis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas gidan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu gidavel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti gidavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella gidava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus gidavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus gidavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas gidavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu gidi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti gidies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella gidi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus gidien\nche nus gideien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus gidies\nche vus gideies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas gidien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu gidavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti gidavies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella gidavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus gidavien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus gidavies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas gidavien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu gidass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti gidasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella gidass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus gidassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus gidasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas gidassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu gidassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti gidassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella gidassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus gidassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus gidassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas gidassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a gidar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a gidar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a gidar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a gidar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a gidar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a gidar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("gidau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("gidaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("gidada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("gidai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("gidadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("gida!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("gidei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("gidond", forms.getVerb().getGerundium());
    }


    @Test
    public void testRule1a() {
        forms =  generator.generateConjugation("1a", "clamar");

        Assert.assertEquals("clamar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu clomel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti clomas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella cloma", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus clamein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus clameis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas cloman", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu clamavel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti clamavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella clamava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus clamavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus clamavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas clamavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu clomi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti clomies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella clomi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus clomien\nche nus clameien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus clomies\nche vus clameies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas clomien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu clamavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti clamavies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella clamavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus clamavien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus clamavies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas clamavien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu clamass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti clamasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella clamass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus clamassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus clamasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas clamassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu clamassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti clamassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella clamassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus clamassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus clamassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas clamassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a clamar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a clamar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a clamar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a clamar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a clamar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a clamar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("clamau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("clamaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("clamada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("clamai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("clamadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("cloma!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("clamei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("clamond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1b() {
        forms =  generator.generateConjugation("1b", "tschintschar");

        Assert.assertEquals("tschintschar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu tschontschel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti tschontschas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella tschontscha", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus tschintschein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus tschintscheis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas tschontschan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu tschintschavel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti tschintschavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella tschintschava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus tschintschavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus tschintschavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas tschintschavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu tschontschi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti tschontschies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella tschontschi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus tschontschien\nche nus tschintscheien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus tschontschies\nche vus tschintscheies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas tschontschien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu tschintschavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti tschintschavies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella tschintschavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus tschintschavien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus tschintschavies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas tschintschavien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu tschintschass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti tschintschasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella tschintschass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus tschintschassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus tschintschasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas tschintschassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu tschintschassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti tschintschassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella tschintschassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus tschintschassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus tschintschassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas tschintschassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a tschintschar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a tschintschar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a tschintschar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a tschintschar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a tschintschar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a tschintschar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("tschintschau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("tschintschaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("tschintschada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("tschintschai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("tschintschadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("tschontscha!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("tschintschei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("tschintschond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1c() {
        forms =  generator.generateConjugation("1c", "purtar");

        Assert.assertEquals("purtar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu portel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti portas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella porta", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus purtein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus purteis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas portan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu purtavel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti purtavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella purtava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus purtavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus purtavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas purtavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu porti", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti porties", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella porti", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus portien\nche nus purteien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus porties\nche vus purteies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas portien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu purtavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti purtavies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella purtavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus purtavien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus purtavies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas purtavien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu purtass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti purtasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella purtass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus purtassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus purtasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas purtassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu purtassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti purtassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella purtassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus purtassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus purtassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas purtassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a purtar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a purtar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a purtar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a purtar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a purtar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a purtar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("purtau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("purtaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("purtada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("purtai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("purtadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("porta!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("purtei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("purtond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1d() {
        forms =  generator.generateConjugation("1d", "formar");

        Assert.assertEquals("formar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu fuormel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti fuormas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella fuorma", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus formein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus formeis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas fuorman", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu formavel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti formavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella formava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus formavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus formavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas formavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu fuormi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti fuormies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella fuormi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus fuormien\nche nus formeien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus fuormies\nche vus formeies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas fuormien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu formavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti formavies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella formavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus formavien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus formavies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas formavien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu formass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti formasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella formass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus formassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus formasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas formassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu formassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti formassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella formassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus formassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus formassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas formassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a formar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a formar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a formar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a formar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a formar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a formar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("formau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("formaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("formada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("formai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("formadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("fuorma!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("formei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("formond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1e() {
        forms =  generator.generateConjugation("1e", "mussar");

        Assert.assertEquals("mussar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu muossel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti muossas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella muossa", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus mussein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus musseis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas muossan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu mussavel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti mussavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella mussava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus mussavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus mussavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas mussavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu muossi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti muossies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella muossi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus muossien\nche nus musseien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus muossies\nche vus musseies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas muossien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu mussavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti mussavies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella mussavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus mussavien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus mussavies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas mussavien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu mussass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti mussasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella mussass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus mussassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus mussasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas mussassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu mussassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti mussassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella mussassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus mussassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus mussassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas mussassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a mussar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a mussar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a mussar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a mussar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a mussar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a mussar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("mussau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("mussaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("mussada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("mussai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("mussadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("muossa!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("mussei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("mussond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1f() {
        forms =  generator.generateConjugation("1f", "zavrar");

        Assert.assertEquals("zavrar", forms.getVerb().getInfinitiv());

        // zavrar is a special case, as it has also an alternative form jeu zavrel, but only the pattern form is tested here
        Assert.assertEquals("jeu zeivrel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti zeivras", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella zeivra", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus zavrein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus zavreis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas zeivran", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu zavravel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti zavravas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella zavrava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus zavravan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus zavravas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas zavravan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu zeivri", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti zeivries", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella zeivri", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus zeivrien\nche nus zavreien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus zeivries\nche vus zavreies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas zeivrien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu zavravi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti zavravies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella zavravi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus zavravien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus zavravies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas zavravien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu zavrass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti zavrasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella zavrass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus zavrassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus zavrasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas zavrassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu zavrassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti zavrassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella zavrassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus zavrassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus zavrassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas zavrassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a zavrar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a zavrar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a zavrar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a zavrar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a zavrar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a zavrar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("zavrau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("zavraus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("zavrada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("zavrai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("zavradas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("zeivra!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("zavrei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("zavrond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1g() {
        forms =  generator.generateConjugation("1g", "tedlar");

        Assert.assertEquals("tedlar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu teidlel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti teidlas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella teidla", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus tedlein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus tedleis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas teidlan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu tedlavel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti tedlavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella tedlava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus tedlavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus tedlavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas tedlavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu teidli", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti teidlies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella teidli", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus teidlien\nche nus tedleien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus teidlies\nche vus tedleies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas teidlien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu tedlavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti tedlavies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella tedlavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus tedlavien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus tedlavies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas tedlavien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu tedlass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti tedlasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella tedlass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus tedlassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus tedlasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas tedlassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu tedlassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti tedlassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella tedlassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus tedlassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus tedlassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas tedlassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a tedlar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a tedlar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a tedlar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a tedlar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a tedlar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a tedlar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("tedlau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("tedlaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("tedlada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("tedlai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("tedladas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("teidla!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("tedlei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("tedlond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1h() {
        forms =  generator.generateConjugation("1h", "pinar");

        Assert.assertEquals("pinar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu peinel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti peinas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella peina", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus pinein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus pineis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas peinan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu pinavel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti pinavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella pinava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus pinavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus pinavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas pinavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu peini", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti peinies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella peini", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus peinien\nche nus pineien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus peinies\nche vus pineies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas peinien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu pinavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti pinavies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella pinavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus pinavien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus pinavies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas pinavien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu pinass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti pinasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella pinass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus pinassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus pinasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas pinassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu pinassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti pinassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella pinassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus pinassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus pinassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas pinassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a pinar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a pinar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a pinar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a pinar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a pinar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a pinar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("pinau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("pinaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("pinada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("pinai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("pinadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("peina!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("pinei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("pinond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1i() {
        forms =  generator.generateConjugation("1i", "ludar");

        Assert.assertEquals("ludar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu laudel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti laudas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella lauda", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus ludein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus ludeis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas laudan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu ludavel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti ludavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella ludava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus ludavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus ludavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas ludavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu laudi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti laudies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella laudi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus laudien\nche nus ludeien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus laudies\nche vus ludeies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas laudien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu ludavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti ludavies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella ludavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus ludavien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus ludavies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas ludavien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu ludass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti ludasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella ludass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus ludassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus ludasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas ludassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu ludassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti ludassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella ludassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus ludassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus ludassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas ludassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a ludar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a ludar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a ludar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a ludar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a ludar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a ludar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("ludau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("ludaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("ludada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("ludai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("ludadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("lauda!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("ludei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("ludond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1j() {
        forms =  generator.generateConjugation("1j", "filtschar");

        Assert.assertEquals("filtschar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu faultschel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti faultschas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella faultscha", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus filtschein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus filtscheis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas faultschan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu filtschavel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti filtschavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella filtschava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus filtschavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus filtschavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas filtschavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu faultschi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti faultschies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella faultschi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus faultschien\nche nus filtscheien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus faultschies\nche vus filtscheies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas faultschien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu filtschavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti filtschavies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella filtschavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus filtschavien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus filtschavies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas filtschavien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu filtschass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti filtschasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella filtschass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus filtschassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus filtschasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas filtschassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu filtschassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti filtschassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella filtschassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus filtschassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus filtschassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas filtschassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a filtschar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a filtschar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a filtschar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a filtschar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a filtschar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a filtschar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("filtschau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("filtschaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("filtschada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("filtschai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("filtschadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("faultscha!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("filtschei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("filtschond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1k() {
        forms =  generator.generateConjugation("1k", "alzar");

        Assert.assertEquals("alzar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu aulzel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti aulzas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella aulza", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus alzein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus alzeis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas aulzan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu alzavel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti alzavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella alzava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus alzavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus alzavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas alzavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu aulzi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti aulzies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella aulzi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus aulzien\nche nus alzeien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus aulzies\nche vus alzeies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas aulzien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu alzavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti alzavies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella alzavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus alzavien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus alzavies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas alzavien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu alzass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti alzasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella alzass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus alzassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus alzasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas alzassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu alzassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti alzassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella alzassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus alzassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus alzassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas alzassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) ad alzar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns ad alzar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn ad alzar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin ad alzar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis ad alzar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan ad alzar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("alzau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("alzaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("alzada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("alzai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("alzadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("aulza!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("alzei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("alzond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1l() {
        forms =  generator.generateConjugation("1l", "emparar");

        Assert.assertEquals("emparar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu empiarel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti empiaras", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella empiara", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus emparein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus empareis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas empiaran", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu emparavel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti emparavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella emparava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus emparavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus emparavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas emparavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu empiari", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti empiaries", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella empiari", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus empiarien\nche nus empareien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus empiaries\nche vus empareies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas empiarien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu emparavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti emparavies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella emparavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus emparavien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus emparavies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas emparavien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu emparass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti emparasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella emparass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus emparassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus emparasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas emparassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu emparassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti emparassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella emparassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus emparassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus emparassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas emparassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) ad emparar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns ad emparar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn ad emparar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin ad emparar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis ad emparar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan ad emparar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("emparau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("emparaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("emparada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("emparai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("emparadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("empiara!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("emparei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("emparond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1m() {
        forms =  generator.generateConjugation("1m", "serrar");

        Assert.assertEquals("serrar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu siarel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti siaras", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella siara", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus serrein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus serreis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas siaran", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu serravel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti serravas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella serrava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus serravan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus serravas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas serravan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu siari", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti siaries", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella siari", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus siarien\nche nus serreien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus siaries\nche vus serreies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas siarien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu serravi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti serravies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella serravi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus serravien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus serravies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas serravien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu serrass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti serrasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella serrass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus serrassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus serrasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas serrassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu serrassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti serrassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella serrassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus serrassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus serrassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas serrassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a serrar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a serrar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a serrar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a serrar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a serrar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a serrar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("serrau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("serraus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("serrada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("serrai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("serradas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("siara!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("serrei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("serrond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1n() {
        forms =  generator.generateConjugation("1n", "zanistrar");

        Assert.assertEquals("zanistrar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu zaniastrel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti zaniastras", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella zaniastra", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus zanistrein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus zanistreis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas zaniastran", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu zanistravel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti zanistravas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella zanistrava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus zanistravan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus zanistravas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas zanistravan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu zaniastri", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti zaniastries", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella zaniastri", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus zaniastrien\nche nus zanistreien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus zaniastries\nche vus zanistreies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas zaniastrien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu zanistravi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti zanistravies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella zanistravi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus zanistravien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus zanistravies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas zanistravien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu zanistrass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti zanistrasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella zanistrass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus zanistrassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus zanistrasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas zanistrassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu zanistrassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti zanistrassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella zanistrassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus zanistrassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus zanistrassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas zanistrassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a zanistrar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a zanistrar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a zanistrar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a zanistrar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a zanistrar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a zanistrar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("zanistrau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("zanistraus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("zanistrada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("zanistrai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("zanistradas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("zaniastra!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("zanistrei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("zanistrond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1o() {
        forms =  generator.generateConjugation("1o", "ugliar");

        Assert.assertEquals("ugliar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu uegliel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti ueglias", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella ueglia", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus ugliein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus uglieis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas ueglian", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu ugliavel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti ugliavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella ugliava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus ugliavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus ugliavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas ugliavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu uegli", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti ueglies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella uegli", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus ueglien\nche nus uglieien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus ueglies\nche vus uglieies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas ueglien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu ugliavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti ugliavies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella ugliavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus ugliavien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus ugliavies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas ugliavien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu ugliass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti ugliasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella ugliass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus ugliassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus ugliasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas ugliassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu ugliassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti ugliassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella ugliassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus ugliassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus ugliassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas ugliassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) ad ugliar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns ad ugliar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn ad ugliar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin ad ugliar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis ad ugliar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan ad ugliar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("ugliau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("ugliaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("ugliada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("ugliai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("ugliadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("ueglia!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("ugliei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("ugliond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1p() {
        forms =  generator.generateConjugation("1p", "spitgar");

        Assert.assertEquals("spitgar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu spetgel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti spetgas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella spetga", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus spitgein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus spitgeis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas spetgan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu spitgavel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti spitgavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella spitgava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus spitgavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus spitgavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas spitgavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu spetgi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti spetgies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella spetgi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus spetgien\nche nus spitgeien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus spetgies\nche vus spitgeies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas spetgien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu spitgavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti spitgavies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella spitgavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus spitgavien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus spitgavies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas spitgavien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu spitgass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti spitgasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella spitgass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus spitgassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus spitgasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas spitgassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu spitgassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti spitgassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella spitgassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus spitgassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus spitgassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas spitgassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a spitgar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a spitgar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a spitgar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a spitgar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a spitgar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a spitgar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("spitgau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("spitgaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("spitgada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("spitgai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("spitgadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("spetga!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("spitgei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("spitgond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1q() {
        forms =  generator.generateConjugation("1q", "semiar");

        Assert.assertEquals("semiar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu siemiel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti siemias", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella siemia", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus semiein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus semieis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas siemian", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu semiavel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti semiavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella semiava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus semiavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus semiavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas semiavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu siemi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti siemies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella siemi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus siemien\nche nus semieien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus siemies\nche vus semieies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas siemien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu semiavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti semiavies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella semiavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus semiavien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus semiavies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas semiavien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu semiass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti semiasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella semiass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus semiassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus semiasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas semiassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu semiassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti semiassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella semiassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus semiassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus semiassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas semiassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a semiar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a semiar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a semiar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a semiar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a semiar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a semiar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("semiau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("semiaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("semiada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("semiai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("semiadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("siemia!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("semiei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("semiond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1r() {
        forms =  generator.generateConjugation("1r", "rischlar");

        Assert.assertEquals("rischlar", forms.getVerb().getInfinitiv());

        // there is also the irregular form 'jeu reschlel', but the test tests only the regular pattern
        Assert.assertEquals("jeu raschlel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti raschlas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella raschla", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus rischlein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus rischleis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas raschlan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu rischlavel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti rischlavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella rischlava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus rischlavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus rischlavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas rischlavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu raschli", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti raschlies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella raschli", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus raschlien\nche nus rischleien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus raschlies\nche vus rischleies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas raschlien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu rischlavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti rischlavies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella rischlavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus rischlavien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus rischlavies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas rischlavien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu rischlass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti rischlasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella rischlass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus rischlassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus rischlasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas rischlassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu rischlassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti rischlassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella rischlassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus rischlassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus rischlassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas rischlassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a rischlar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a rischlar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a rischlar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a rischlar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a rischlar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a rischlar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("rischlau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("rischlaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("rischlada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("rischlai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("rischladas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("raschla!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("rischlei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("rischlond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1s() {
        forms =  generator.generateConjugation("1s", "sbuserar");

        Assert.assertEquals("sbuserar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu sbusirel\njeu sbusarel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti sbusiras\nti sbusaras", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella sbusira\nel/ella sbusara", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus sbuserein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus sbusereis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas sbusiran\nels/ellas sbusaran", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu sbuseravel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti sbuseravas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella sbuserava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus sbuseravan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus sbuseravas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas sbuseravan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu sbusiri\nche jeu sbusari", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti sbusiries\nche ti sbusaries", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella sbusiri\nch'el/ella sbusari", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus sbusirien\nche nus sbusarien\nche nus sbusereien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus sbusiries\nche vus sbusaries\nche vus sbusereies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas sbusirien\nch'els/ellas sbusarien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu sbuseravi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti sbuseravies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella sbuseravi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus sbuseravien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus sbuseravies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas sbuseravien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu sbuserass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti sbuserasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella sbuserass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus sbuserassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus sbuserasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas sbuserassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu sbuserassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti sbuserassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella sbuserassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus sbuserassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus sbuserassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas sbuserassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a sbuserar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a sbuserar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a sbuserar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a sbuserar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a sbuserar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a sbuserar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("sbuserau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("sbuseraus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("sbuserada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("sbuserai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("sbuseradas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("sbusira!\nsbusara!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("sbuserei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("sbuserond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1t() {
        forms =  generator.generateConjugation("1t", "smarschanar");

        Assert.assertEquals("smarschanar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu smarschunel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti smarschunas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella smarschuna", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus smarschanein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus smarschaneis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas smarschunan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu smarschanavel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti smarschanavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella smarschanava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus smarschanavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus smarschanavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas smarschanavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu smarschuni", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti smarschunies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella smarschuni", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus smarschunien\nche nus smarschaneien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus smarschunies\nche vus smarschaneies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas smarschunien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu smarschanavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti smarschanavies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella smarschanavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus smarschanavien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus smarschanavies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas smarschanavien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu smarschanass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti smarschanasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella smarschanass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus smarschanassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus smarschanasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas smarschanassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu smarschanassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti smarschanassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella smarschanassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus smarschanassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus smarschanassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas smarschanassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a smarschanar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a smarschanar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a smarschanar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a smarschanar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a smarschanar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a smarschanar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("smarschanau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("smarschanaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("smarschanada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("smarschanai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("smarschanadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("smarschuna!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("smarschanei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("smarschanond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1u() {
        forms =  generator.generateConjugation("1u", "cuglienar");

        Assert.assertEquals("cuglienar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu cugliunel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti cugliunas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella cugliuna", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus cuglienein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus cuglieneis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas cugliunan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu cuglienavel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti cuglienavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella cuglienava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus cuglienavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus cuglienavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas cuglienavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu cugliuni", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti cugliunies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella cugliuni", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus cugliunien\nche nus cuglieneien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus cugliunies\nche vus cuglieneies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas cugliunien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu cuglienavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti cuglienavies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella cuglienavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus cuglienavien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus cuglienavies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas cuglienavien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu cuglienass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti cuglienasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella cuglienass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus cuglienassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus cuglienasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas cuglienassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu cuglienassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti cuglienassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella cuglienassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus cuglienassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus cuglienassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas cuglienassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a cuglienar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a cuglienar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a cuglienar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a cuglienar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a cuglienar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a cuglienar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("cuglienau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("cuglienaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("cuglienada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("cuglienai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("cuglienadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("cugliuna!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("cuglienei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("cuglienond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1v() {
        forms =  generator.generateConjugation("1v", "curclar");

        Assert.assertEquals("curclar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu cuarclel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti cuarclas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella cuarcla", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus curclein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus curcleis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas cuarclan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu curclavel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti curclavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella curclava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus curclavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus curclavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas curclavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu cuarcli", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti cuarclies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella cuarcli", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus cuarclien\nche nus curcleien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus cuarclies\nche vus curcleies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas cuarclien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu curclavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti curclavies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella curclavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus curclavien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus curclavies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas curclavien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu curclass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti curclasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella curclass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus curclassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus curclasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas curclassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu curclassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti curclassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella curclassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus curclassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus curclassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas curclassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a curclar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a curclar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a curclar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a curclar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a curclar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a curclar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("curclau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("curclaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("curclada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("curclai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("curcladas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("cuarcla!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("curclei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("curclond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1w() {
        forms =  generator.generateConjugation("1w", "suar");

        Assert.assertEquals("suar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu siuel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti siuas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella siua", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus suein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus sueis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas siuan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu suavel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti suavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella suava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus suavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus suavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas suavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu siui", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti siuies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella siui", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus siuien\nche nus sueien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus siuies\nche vus sueies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas siuien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu suavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti suavies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella suavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus suavien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus suavies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas suavien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu suass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti suasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella suass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus suassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus suasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas suassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu suassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti suassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella suassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus suassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus suassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas suassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a suar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a suar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a suar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a suar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a suar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a suar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("suau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("suaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("suada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("suai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("suadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("siua!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("suei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("suond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1x() {
        forms =  generator.generateConjugation("1x", "cumandar");

        Assert.assertEquals("cumandar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu camondel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti camondas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella camonda", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus cumandein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus cumandeis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas camondan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu cumandavel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti cumandavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella cumandava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus cumandavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus cumandavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas cumandavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu camondi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti camondies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella camondi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus camondien\nche nus cumandeien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus camondies\nche vus cumandeies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas camondien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu cumandavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti cumandavies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella cumandavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus cumandavien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus cumandavies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas cumandavien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu cumandass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti cumandasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella cumandass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus cumandassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus cumandasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas cumandassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu cumandassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti cumandassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella cumandassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus cumandassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus cumandassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas cumandassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a cumandar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a cumandar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a cumandar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a cumandar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a cumandar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a cumandar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("cumandau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("cumandaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("cumandada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("cumandai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("cumandadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("camonda!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("cumandei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("cumandond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1y() {
        forms =  generator.generateConjugation("1y", "dumignar");

        Assert.assertEquals("dumignar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu damognel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti damognas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella damogna", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus dumignein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus dumigneis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas damognan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu dumignavel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti dumignavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella dumignava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus dumignavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus dumignavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas dumignavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu damogni", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti damognies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella damogni", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus damognien\nche nus dumigneien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus damognies\nche vus dumigneies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas damognien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu dumignavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti dumignavies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella dumignavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus dumignavien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus dumignavies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas dumignavien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu dumignass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti dumignasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella dumignass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus dumignassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus dumignasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas dumignassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu dumignassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti dumignassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella dumignassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus dumignassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus dumignassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas dumignassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a dumignar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a dumignar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a dumignar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a dumignar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a dumignar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a dumignar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("dumignau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("dumignaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("dumignada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("dumignai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("dumignadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("damogna!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("dumignei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("dumignond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1z() {
        forms =  generator.generateConjugation("1z", "rumplanar");

        Assert.assertEquals("rumplanar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu ramplunel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti ramplunas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella rampluna", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus rumplanein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus rumplaneis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas ramplunan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu rumplanavel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti rumplanavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella rumplanava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus rumplanavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus rumplanavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas rumplanavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu rampluni", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti ramplunies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella rampluni", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus ramplunien\nche nus rumplaneien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus ramplunies\nche vus rumplaneies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas ramplunien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu rumplanavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti rumplanavies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella rumplanavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus rumplanavien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus rumplanavies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas rumplanavien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu rumplanass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti rumplanasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella rumplanass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus rumplanassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus rumplanasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas rumplanassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu rumplanassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti rumplanassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella rumplanassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus rumplanassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus rumplanassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas rumplanassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a rumplanar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a rumplanar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a rumplanar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a rumplanar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a rumplanar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a rumplanar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("rumplanau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("rumplanaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("rumplanada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("rumplanai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("rumplanadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("rampluna!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("rumplanei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("rumplanond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1aa() {
        forms =  generator.generateConjugation("1aa", "murmignar");

        Assert.assertEquals("murmignar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu marmugnel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti marmugnas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella marmugna", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus murmignein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus murmigneis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas marmugnan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu murmignavel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti murmignavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella murmignava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus murmignavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus murmignavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas murmignavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu marmugni", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti marmugnies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella marmugni", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus marmugnien\nche nus murmigneien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus marmugnies\nche vus murmigneies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas marmugnien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu murmignavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti murmignavies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella murmignavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus murmignavien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus murmignavies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas murmignavien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu murmignass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti murmignasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella murmignass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus murmignassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus murmignasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas murmignassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu murmignassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti murmignassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella murmignassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus murmignassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus murmignassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas murmignassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a murmignar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a murmignar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a murmignar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a murmignar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a murmignar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a murmignar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("murmignau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("murmignaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("murmignada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("murmignai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("murmignadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("marmugna!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("murmignei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("murmignond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1ab() {
        forms =  generator.generateConjugation("1ab", "buntganar");

        Assert.assertEquals("buntganar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu bintgunel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti bintgunas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella bintguna", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus buntganein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus buntganeis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas bintgunan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu buntganavel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti buntganavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella buntganava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus buntganavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus buntganavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas buntganavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu bintguni", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti bintgunies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella bintguni", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus bintgunien\nche nus buntganeien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus bintgunies\nche vus buntganeies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas bintgunien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu buntganavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti buntganavies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella buntganavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus buntganavien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus buntganavies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas buntganavien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu buntganass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti buntganasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella buntganass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus buntganassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus buntganasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas buntganassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu buntganassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti buntganassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella buntganassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus buntganassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus buntganassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas buntganassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a buntganar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a buntganar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a buntganar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a buntganar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a buntganar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a buntganar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("buntganau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("buntganaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("buntganada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("buntganai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("buntganadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("bintguna!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("buntganei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("buntganond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1ac() {
        forms =  generator.generateConjugation("1ac", "duvrar");

        Assert.assertEquals("duvrar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu drovel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti drovas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella drova", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus duvrein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus duvreis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas drovan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu duvravel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti duvravas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella duvrava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus duvravan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus duvravas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas duvravan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu drovi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti drovies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella drovi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus drovien\nche nus duvreien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus drovies\nche vus duvreies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas drovien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu duvravi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti duvravies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella duvravi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus duvravien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus duvravies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas duvravien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu duvrass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti duvrasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella duvrass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus duvrassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus duvrasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas duvrassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu duvrassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti duvrassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella duvrassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus duvrassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus duvrassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas duvrassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a duvrar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a duvrar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a duvrar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a duvrar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a duvrar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a duvrar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("duvrau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("duvraus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("duvrada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("duvrai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("duvradas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("drova!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("duvrei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("duvrond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1acAlternativ() {
        forms =  generator.generateConjugation("1ac", "curdar");

        Assert.assertEquals("curdar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu crodel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti crodas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella croda", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus curdein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus curdeis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas crodan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu curdavel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti curdavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella curdava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus curdavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus curdavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas curdavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu crodi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti crodies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella crodi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus crodien\nche nus curdeien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus crodies\nche vus curdeies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas crodien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu curdavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti curdavies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella curdavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus curdavien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus curdavies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas curdavien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu curdass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti curdasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella curdass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus curdassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus curdasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas curdassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu curdassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti curdassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella curdassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus curdassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus curdassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas curdassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a curdar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a curdar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a curdar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a curdar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a curdar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a curdar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("curdau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("curdaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("curdada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("curdai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("curdadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("croda!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("curdei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("curdond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1ad() {
        forms =  generator.generateConjugation("1ad", "patertgar");

        Assert.assertEquals("patertgar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu patratgel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti patratgas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella patratga", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus patertgein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus patertgeis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas patratgan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu patertgavel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti patertgavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella patertgava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus patertgavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus patertgavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas patertgavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu patratgi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti patratgies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella patratgi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus patratgien\nche nus patertgeien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus patratgies\nche vus patertgeies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas patratgien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu patertgavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti patertgavies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella patertgavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus patertgavien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus patertgavies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas patertgavien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu patertgass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti patertgasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella patertgass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus patertgassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus patertgasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas patertgassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu patertgassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti patertgassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella patertgassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus patertgassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus patertgassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas patertgassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a patertgar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a patertgar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a patertgar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a patertgar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a patertgar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a patertgar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("patertgau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("patertgaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("patertgada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("patertgai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("patertgadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("patratga!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("patertgei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("patertgond", forms.getVerb().getGerundium());
    }

    @Test
    @Ignore
    public void testRule1ae() {
        forms =  generator.generateConjugation("1ae", "tschuffergnar");

        Assert.assertEquals("tschuffergnar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu tschufrognel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti tschufrognas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella tschufrogna", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus tschuffergnein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus tschuffergneis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas tschufrognan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu tschuffergnavel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti tschuffergnavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella tschuffergnava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus tschuffergnavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus tschuffergnavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas tschuffergnavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu tschufrogni", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti tschufrognies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella tschufrogni", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus tschufrognien\nche nus tschuffergneien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus tschufrognies\nche vus tschuffergneies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas tschufrognien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu tschuffergnavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti tschuffergnavies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella tschuffergnavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus tschuffergnavien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus tschuffergnavies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas tschuffergnavien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu tschuffergnass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti tschuffergnasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella tschuffergnass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus tschuffergnassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus tschuffergnasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas tschuffergnassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu tschuffergnassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti tschuffergnassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella tschuffergnassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus tschuffergnassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus tschuffergnassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas tschuffergnassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a tschuffergnar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a tschuffergnar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a tschuffergnar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a tschuffergnar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a tschuffergnar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a tschuffergnar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("tschuffergnau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("tschuffergnaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("tschuffergnada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("tschuffergnai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("tschuffergnadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("tschufrogna!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("tschuffergnei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("tschuffergnond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1af() {
        forms =  generator.generateConjugation("1af", "sgarflar");

        Assert.assertEquals("sgarflar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu sgreflel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti sgreflas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella sgrefla", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus sgarflein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus sgarfleis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas sgreflan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu sgarflavel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti sgarflavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella sgarflava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus sgarflavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus sgarflavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas sgarflavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu sgrefli", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti sgreflies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella sgrefli", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus sgreflien\nche nus sgarfleien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus sgreflies\nche vus sgarfleies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas sgreflien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu sgarflavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti sgarflavies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella sgarflavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus sgarflavien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus sgarflavies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas sgarflavien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu sgarflass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti sgarflasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella sgarflass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus sgarflassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus sgarflasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas sgarflassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu sgarflassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti sgarflassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella sgarflassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus sgarflassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus sgarflassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas sgarflassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a sgarflar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a sgarflar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a sgarflar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a sgarflar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a sgarflar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a sgarflar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("sgarflau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("sgarflaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("sgarflada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("sgarflai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("sgarfladas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("sgrefla!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("sgarflei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("sgarflond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1ag() {
        forms =  generator.generateConjugation("1ag", "sblundergiar");

        Assert.assertEquals("sblundergiar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu sblundregel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti sblundregias", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella sblundregia", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus sblundergein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus sblundergeis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas sblundregian", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu sblundergiavel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti sblundergiavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella sblundergiava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus sblundergiavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus sblundergiavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas sblundergiavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu sblundregi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti sblundregies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella sblundregi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus sblundregien\nche nus sblundergeien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus sblundregies\nche vus sblundergeies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas sblundregien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu sblundergiavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti sblundergiavies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella sblundergiavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus sblundergiavien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus sblundergiavies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas sblundergiavien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu sblundergiass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti sblundergiasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella sblundergiass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus sblundergiassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus sblundergiasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas sblundergiassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu sblundergiassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti sblundergiassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella sblundergiassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus sblundergiassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus sblundergiassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas sblundergiassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a sblundergiar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a sblundergiar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a sblundergiar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a sblundergiar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a sblundergiar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a sblundergiar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("sblundergiau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("sblundergiaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("sblundergiada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("sblundergiai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("sblundergiadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("sblundregia!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("sblundergei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("sblundergiond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1ah() {
        forms =  generator.generateConjugation("1ah", "barschar");

        Assert.assertEquals("barschar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu brischel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti brischas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella brischa", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus barschein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus barscheis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas brischan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu barschavel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti barschavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella barschava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus barschavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus barschavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas barschavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu brischi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti brischies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella brischi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus brischien\nche nus barscheien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus brischies\nche vus barscheies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas brischien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu barschavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti barschavies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella barschavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus barschavien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus barschavies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas barschavien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu barschass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti barschasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella barschass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus barschassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus barschasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas barschassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu barschassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti barschassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella barschassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus barschassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus barschassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas barschassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a barschar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a barschar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a barschar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a barschar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a barschar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a barschar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("barschau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("barschaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("barschada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("barschai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("barschadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("brischa!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("barschei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("barschond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1ai() {
        forms =  generator.generateConjugation("1ai", "furschar");

        Assert.assertEquals("furschar", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu fruschel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti fruschas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella fruscha", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus furschein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus furscheis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas fruschan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu furschavel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti furschavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella furschava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus furschavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus furschavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas furschavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu fruschi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti fruschies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella fruschi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus fruschien\nche nus furscheien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus fruschies\nche vus furscheies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas fruschien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu furschavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti furschavies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella furschavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus furschavien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus furschavies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas furschavien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu furschass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti furschasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella furschass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus furschassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus furschasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas furschassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu furschassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti furschassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella furschassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus furschassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus furschassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas furschassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a furschar", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a furschar", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a furschar", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a furschar", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a furschar", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a furschar", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("furschau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("furschaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("furschada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("furschai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("furschadas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("fruscha!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("furschei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("furschond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule2() {
        forms =  generator.generateConjugation("2", "temer");

        Assert.assertEquals("temer", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu temel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti temas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella tema", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus temein\nnus temin", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus temeis\nvus temis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas teman", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu temevel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti temevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella temeva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus temevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus temevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas temevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu temi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti temies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella temi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus temien\nche nus temeien\nche nus temîen", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus temies\nche vus temeies\nche vus temîes", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas temien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu temevi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti temevies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella temevi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus temevien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus temevies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas temevien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu temess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti temesses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella temess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus temessen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus temesses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas temessen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu temessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti temessies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella temessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus temessien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus temessies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas temessien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a temer", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a temer", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a temer", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a temer", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a temer", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a temer", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("temiu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("temius", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("temida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("temi", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("temidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("tema!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("temei!\ntemi!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("temend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule2a() {
        forms =  generator.generateConjugation("2a", "guder");

        Assert.assertEquals("guder", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu gaudel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti gaudas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella gauda", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus gudin", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus gudis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas gaudan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu gudevel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti gudevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella gudeva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus gudevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus gudevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas gudevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu gaudi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti gaudies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella gaudi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus gaudien\nche nus gudîen", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus gaudies\nche vus gudîes", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas gaudien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu gudevi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti gudevies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella gudevi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus gudevien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus gudevies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas gudevien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu gudess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti gudesses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella gudess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus gudessen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus gudesses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas gudessen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu gudessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti gudessies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella gudessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus gudessien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus gudessies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas gudessien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a guder", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a guder", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a guder", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a guder", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a guder", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a guder", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("gudiu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("gudius", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("gudida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("gudi", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("gudidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("gauda!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("gudi!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("gudend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3() {
        forms =  generator.generateConjugation("3", "vender");

        Assert.assertEquals("vender", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu vendel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti vendas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella venda", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus vendein\nnus vendin", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus vendeis\nvus vendis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas vendan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu vendevel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti vendevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella vendeva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus vendevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus vendevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas vendevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu vendi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti vendies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella vendi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus vendien\nche nus vendeien\nche nus vendîen", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus vendies\nche vus vendeies\nche vus vendîes", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas vendien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu vendevi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti vendevies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella vendevi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus vendevien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus vendevies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas vendevien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu vendess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti vendesses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella vendess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus vendessen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus vendesses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas vendessen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu vendessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti vendessies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella vendessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus vendessien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus vendessies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas vendessien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a vender", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a vender", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a vender", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a vender", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a vender", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a vender", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("vendiu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("vendius", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("vendida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("vendi", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("vendidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("venda!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("vendei!\nvendi!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("vendend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3a() {
        forms =  generator.generateConjugation("3a", "volver");

        Assert.assertEquals("volver", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu volvel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti volvas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella volva", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus vulvein\nnus vulvin", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus vulveis\nvus vulvis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas volvan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu vulvevel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti vulvevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella vulveva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus vulvevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus vulvevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas vulvevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu volvi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti volvies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella volvi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus volvien\nche nus vulveien\nche nus vulvîen", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus volvies\nche vus vulveies\nche vus vulvîes", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas volvien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu vulvevi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti vulvevies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella vulvevi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus vulvevien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus vulvevies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas vulvevien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu vulvess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti vulvesses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella vulvess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus vulvessen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus vulvesses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas vulvessen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu vulvessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti vulvessies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella vulvessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus vulvessien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus vulvessies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas vulvessien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a volver", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a volver", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a volver", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a volver", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a volver", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a volver", forms.getVerb().getFutur().getPlural3());

        // 'volver' would also have an irregular form 'viult', but the test is only for the regular pattern
        Assert.assertEquals("vulviu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("vulvius", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("vulvida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("vulvi", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("vulvidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("volva!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("vulvei!\nvulvi!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("vulvend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3b() {
        forms =  generator.generateConjugation("3b", "rietscher");

        Assert.assertEquals("rietscher", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu rietschel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti rietschas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella rietscha", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus ritschin", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus ritschis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas rietschan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu ritschevel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti ritschevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella ritscheva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus ritschevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus ritschevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas ritschevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu rietschi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti rietschies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella rietschi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus rietschien\nche nus ritschîen", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus rietschies\nche vus ritschîes", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas rietschien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu ritschevi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti ritschevies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella ritschevi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus ritschevien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus ritschevies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas ritschevien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu ritschess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti ritschesses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella ritschess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus ritschessen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus ritschesses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas ritschessen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu ritschessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti ritschessies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella ritschessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus ritschessien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus ritschessies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas ritschessien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a rietscher", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a rietscher", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a rietscher", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a rietscher", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a rietscher", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a rietscher", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("ritschiu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("ritschius", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("ritschida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("ritschi", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("ritschidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("rietscha!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("ritschi!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("ritschend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3c() {
        forms =  generator.generateConjugation("3c", "clauder");

        Assert.assertEquals("clauder", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu claudel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti claudas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella clauda", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus cludein\nnus cludin", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus cludeis\nvus cludis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas claudan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu cludevel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti cludevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella cludeva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus cludevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus cludevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas cludevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu claudi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti claudies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella claudi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus claudien\nche nus cludeien\nche nus cludîen", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus claudies\nche vus cludeies\nche vus cludîes", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas claudien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu cludevi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti cludevies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella cludevi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus cludevien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus cludevies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas cludevien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu cludess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti cludesses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella cludess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus cludessen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus cludesses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas cludessen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu cludessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti cludessies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella cludessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus cludessien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus cludessies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas cludessien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a clauder", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a clauder", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a clauder", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a clauder", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a clauder", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a clauder", forms.getVerb().getFutur().getPlural3());

        // particip is irregular for 'claus'; 'claudiu' is not correct. But the regular pattern would be like this,
        // thus testing for a wrong verb form.
        Assert.assertEquals("cludiu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("cludius", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("cludida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("cludi", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("cludidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("clauda!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("cludei!\ncludi!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("cludend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3d() {
        forms =  generator.generateConjugation("3d", "reiver");

        Assert.assertEquals("reiver", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu reivel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti reivas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella reiva", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus revein\nnus revin", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus reveis\nvus revis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas reivan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu revevel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti revevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella reveva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus revevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus revevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas revevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu reivi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti reivies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella reivi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus reivien\nche nus reveien\nche nus revîen", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus reivies\nche vus reveies\nche vus revîes", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas reivien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu revevi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti revevies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella revevi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus revevien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus revevies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas revevien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu revess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti revesses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella revess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus revessen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus revesses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas revessen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu revessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti revessies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella revessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus revessien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus revessies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas revessien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a reiver", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a reiver", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a reiver", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a reiver", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a reiver", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a reiver", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("reviu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("revius", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("revida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("revi", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("revidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("reiva!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("revei!\nrevi!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("revend", forms.getVerb().getGerundium());
    }

    @Test
    @Ignore
    public void testRule3e() {
        forms =  generator.generateConjugation("3e", "laguoter");

        Assert.assertEquals("laguoter", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu laguotel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti laguotas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella laguota", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus laguttin", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus laguttis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas laguotan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu laguttevel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti laguttevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella lagutteva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus laguttevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus laguttevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas laguttevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu laguoti", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti laguoties", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella laguoti", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus laguotien\nche nus laguttîen", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus laguoties\nche vus laguttîes", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas laguotien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu laguttevi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti laguttevies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella laguttevi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus laguttevien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus laguttevies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas laguttevien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu laguttess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti laguttesses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella laguttess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus laguttessen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus laguttesses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas laguttessen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu laguttessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti laguttessies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella laguttessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus laguttessien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus laguttessies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas laguttessien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a laguoter", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a laguoter", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a laguoter", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a laguoter", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a laguoter", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a laguoter", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("laguttiu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("laguttius", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("laguttida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("lagutti", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("laguttidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("laguota!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("lagutti!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("laguttend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3f() {
        forms =  generator.generateConjugation("3f", "piarder");

        Assert.assertEquals("piarder", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu piardel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti piardas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella piarda", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus perdein\nnus perdin", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus perdeis\nvus perdis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas piardan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu perdevel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti perdevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella perdeva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus perdevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus perdevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas perdevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu piardi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti piardies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella piardi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus piardien\nche nus perdeien\nche nus perdîen", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus piardies\nche vus perdeies\nche vus perdîes", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas piardien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu perdevi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti perdevies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella perdevi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus perdevien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus perdevies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas perdevien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu perdess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti perdesses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella perdess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus perdessen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus perdesses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas perdessen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu perdessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti perdessies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella perdessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus perdessien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus perdessies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas perdessien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a piarder", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a piarder", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a piarder", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a piarder", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a piarder", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a piarder", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("perdiu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("perdius", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("perdida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("perdi", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("perdidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("piarda!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("perdei!\nperdi!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("perdend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4() {
        forms =  generator.generateConjugation("4", "sentir");

        Assert.assertEquals("sentir", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu sentel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti sentas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella senta", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus sentin", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus sentis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas sentan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu sentevel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti sentevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella senteva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus sentevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus sentevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas sentevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu senti", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti senties", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella senti", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus sentîen", forms.getVerb().getConjunctiv().getPlural1()); // DIFF
        Assert.assertEquals("che vus sentîes", forms.getVerb().getConjunctiv().getPlural2()); // DIFF
        Assert.assertEquals("ch'els/ellas sentien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu sentevi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti sentevies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella sentevi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus sentevien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus sentevies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas sentevien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu sentess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti sentesses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella sentess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus sentessen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus sentesses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas sentessen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu sentessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti sentessies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella sentessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus sentessien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus sentessies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas sentessien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a sentir", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a sentir", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a sentir", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a sentir", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a sentir", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a sentir", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("sentiu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("sentius", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("sentida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("senti", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("sentidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("senta!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("senti!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("sentend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4a() {
        forms =  generator.generateConjugation("4a", "durmir");

        Assert.assertEquals("durmir", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu dormel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti dormas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella dorma", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus durmin", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus durmis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas dorman", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu durmevel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti durmevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella durmeva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus durmevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus durmevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas durmevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu dormi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti dormies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella dormi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus dormien\nche nus durmîen", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus dormies\nche vus durmîes", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas dormien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu durmevi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti durmevies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella durmevi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus durmevien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus durmevies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas durmevien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu durmess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti durmesses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella durmess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus durmessen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus durmesses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas durmessen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu durmessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti durmessies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella durmessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus durmessien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus durmessies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas durmessien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a durmir", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a durmir", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a durmir", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a durmir", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a durmir", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a durmir", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("durmiu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("durmius", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("durmida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("durmi", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("durmidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("dorma!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("durmi!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("durmend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4b() {
        forms =  generator.generateConjugation("4b", "durmir");

        Assert.assertEquals("durmir", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu diermel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti diermas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella dierma", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus durmin", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus durmis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas dierman", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu durmevel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti durmevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella durmeva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus durmevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus durmevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas durmevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu diermi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti diermies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella diermi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus diermien\nche nus durmîen", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus diermies\nche vus durmîes", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas diermien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu durmevi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti durmevies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella durmevi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus durmevien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus durmevies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas durmevien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu durmess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti durmesses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella durmess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus durmessen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus durmesses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas durmessen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu durmessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti durmessies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella durmessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus durmessien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus durmessies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas durmessien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a durmir", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a durmir", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a durmir", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a durmir", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a durmir", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a durmir", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("durmiu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("durmius", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("durmida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("durmi", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("durmidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("dierma!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("durmi!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("durmend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4c() {
        forms =  generator.generateConjugation("4c", "burlir");

        Assert.assertEquals("burlir", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu buorlel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti buorlas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella buorla", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus burlin", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus burlis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas buorlan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu burlevel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti burlevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella burleva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus burlevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus burlevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas burlevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu buorli", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti buorlies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella buorli", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus buorlien\nche nus burlîen", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus buorlies\nche vus burlîes", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas buorlien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu burlevi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti burlevies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella burlevi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus burlevien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus burlevies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas burlevien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu burless", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti burlesses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella burless", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus burlessen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus burlesses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas burlessen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu burlessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti burlessies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella burlessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus burlessien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus burlessies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas burlessien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a burlir", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a burlir", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a burlir", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a burlir", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a burlir", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a burlir", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("burliu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("burlius", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("burlida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("burli", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("burlidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("buorla!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("burli!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("burlend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4d() {
        forms =  generator.generateConjugation("4d", "burlir");

        Assert.assertEquals("burlir", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu berlel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti berlas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella berla", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus burlin", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus burlis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas berlan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu burlevel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti burlevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella burleva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus burlevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus burlevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas burlevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu berli", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti berlies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella berli", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus berlien\nche nus burlîen", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus berlies\nche vus burlîes", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas berlien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu burlevi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti burlevies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella burlevi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus burlevien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus burlevies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas burlevien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu burless", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti burlesses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella burless", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus burlessen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus burlesses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas burlessen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu burlessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti burlessies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella burlessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus burlessien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus burlessies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas burlessien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a burlir", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a burlir", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a burlir", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a burlir", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a burlir", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a burlir", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("burliu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("burlius", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("burlida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("burli", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("burlidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("berla!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("burli!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("burlend", forms.getVerb().getGerundium());
    }

    @Test
    @Ignore
    public void testRule4e() {
        forms =  generator.generateConjugation("4e", "embellir");

        Assert.assertEquals("embellir", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu embialel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti embialas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella embiala", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus embellin", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus embellis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas embialan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu embellevel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti embellevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella embelleva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus embellevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus embellevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas embellevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu embiali", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti embialies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella embiali", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus embialien\nembelleschien\nembellîen", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus embialies \nembelleschies\nembellîes", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas embialien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu embellevi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti embellevies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella embellevi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus embellevien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus embellevies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas embellevien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu embelless", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti embellesses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella embelless", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus embellessen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus embellesses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas embellessen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu embellessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti embellessies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella embellessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus embellessien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus embellessies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas embellessien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a embellir", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a embellir", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a embellir", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a embellir", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a embellir", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a embellir", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("embelliu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("embellius", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("embellida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("embelli", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("embellidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("embiala!\nembellescha!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("embelli!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("embellend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4f() {
        forms =  generator.generateConjugation("4f", "bargir");

        Assert.assertEquals("bargir", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu bragel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti bragias", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella bragia", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus bargin", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus bargis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas bragian", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu bargevel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti bargevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella bargeva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus bargevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus bargevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas bargevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu bragi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti bragies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella bragi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus bragien\nche nus bargîen", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus bragies\nche vus bargîes", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas bragien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu bargevi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti bargevies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella bargevi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus bargevien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus bargevies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas bargevien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu bargess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti bargesses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella bargess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus bargessen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus bargesses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas bargessen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu bargessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti bargessies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella bargessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus bargessien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus bargessies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas bargessien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a bargir", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a bargir", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a bargir", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a bargir", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a bargir", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a bargir", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("bargiu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("bargius", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("bargida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("bargi", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("bargidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("bragia!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("bargi!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("bargend", forms.getVerb().getGerundium());
    }


    @Test
    public void testRule5() {
        forms =  generator.generateConjugation("5", "capir");

        Assert.assertEquals("capir", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu capeschel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti capeschas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella capescha", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus capin", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus capis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas capeschan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu capevel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti capevas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella capeva", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus capevan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus capevas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas capevan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu capeschi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti capeschies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella capeschi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus capeschien\nche nus capîen", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus capeschies\nche vus capîes", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas capeschien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu capevi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti capevies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella capevi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus capevien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus capevies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas capevien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu capess", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti capesses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella capess", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus capessen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus capesses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas capessen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu capessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti capessies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella capessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus capessien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus capessies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas capessien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a capir", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a capir", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a capir", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a capir", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a capir", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a capir", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("capiu", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("capius", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("capida", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("capi", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("capidas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("capescha!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("capi!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("capend", forms.getVerb().getGerundium());
    }


    @Test
    public void testRule6() {
        forms =  generator.generateConjugation("6", "gratular");

        Assert.assertEquals("gratular", forms.getVerb().getInfinitiv());

        Assert.assertEquals("jeu gratuleschel", forms.getVerb().getPreschent().getSing1());
        Assert.assertEquals("ti gratuleschas", forms.getVerb().getPreschent().getSing2());
        Assert.assertEquals("el/ella gratulescha", forms.getVerb().getPreschent().getSing3());
        Assert.assertEquals("nus gratulein", forms.getVerb().getPreschent().getPlural1());
        Assert.assertEquals("vus gratuleis", forms.getVerb().getPreschent().getPlural2());
        Assert.assertEquals("els/ellas gratuleschan", forms.getVerb().getPreschent().getPlural3());

        Assert.assertEquals("jeu gratulavel", forms.getVerb().getImperfect().getSing1());
        Assert.assertEquals("ti gratulavas", forms.getVerb().getImperfect().getSing2());
        Assert.assertEquals("el/ella gratulava", forms.getVerb().getImperfect().getSing3());
        Assert.assertEquals("nus gratulavan", forms.getVerb().getImperfect().getPlural1());
        Assert.assertEquals("vus gratulavas", forms.getVerb().getImperfect().getPlural2());
        Assert.assertEquals("els/ellas gratulavan", forms.getVerb().getImperfect().getPlural3());

        Assert.assertEquals("che jeu gratuleschi", forms.getVerb().getConjunctiv().getSing1());
        Assert.assertEquals("che ti gratuleschies", forms.getVerb().getConjunctiv().getSing2());
        Assert.assertEquals("ch'el/ella gratuleschi", forms.getVerb().getConjunctiv().getSing3());
        Assert.assertEquals("che nus gratuleschien\nche nus gratuleien", forms.getVerb().getConjunctiv().getPlural1());
        Assert.assertEquals("che vus gratuleschies\nche vus gratuleies", forms.getVerb().getConjunctiv().getPlural2());
        Assert.assertEquals("ch'els/ellas gratuleschien", forms.getVerb().getConjunctiv().getPlural3());

        Assert.assertEquals("che jeu gratulavi", forms.getVerb().getConjunctiv2().getSing1());
        Assert.assertEquals("che ti gratulavies", forms.getVerb().getConjunctiv2().getSing2());
        Assert.assertEquals("ch'el/ella gratulavi", forms.getVerb().getConjunctiv2().getSing3());
        Assert.assertEquals("che nus gratulavien", forms.getVerb().getConjunctiv2().getPlural1());
        Assert.assertEquals("che vus gratulavies", forms.getVerb().getConjunctiv2().getPlural2());
        Assert.assertEquals("ch'els/ellas gratulavien", forms.getVerb().getConjunctiv2().getPlural3());

        Assert.assertEquals("jeu gratulass", forms.getVerb().getCundiziunal().getSing1());
        Assert.assertEquals("ti gratulasses", forms.getVerb().getCundiziunal().getSing2());
        Assert.assertEquals("el/ella gratulass", forms.getVerb().getCundiziunal().getSing3());
        Assert.assertEquals("nus gratulassen", forms.getVerb().getCundiziunal().getPlural1());
        Assert.assertEquals("vus gratulasses", forms.getVerb().getCundiziunal().getPlural2());
        Assert.assertEquals("els/ellas gratulassen", forms.getVerb().getCundiziunal().getPlural3());

        Assert.assertEquals("jeu gratulassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        Assert.assertEquals("ti gratulassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        Assert.assertEquals("el/ella gratulassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        Assert.assertEquals("nus gratulassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        Assert.assertEquals("vus gratulassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        Assert.assertEquals("els/ellas gratulassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        Assert.assertEquals("jeu vegn(el) a gratular", forms.getVerb().getFutur().getSing1());
        Assert.assertEquals("ti vegns a gratular", forms.getVerb().getFutur().getSing2());
        Assert.assertEquals("el/ella vegn a gratular", forms.getVerb().getFutur().getSing3());
        Assert.assertEquals("nus vegnin a gratular", forms.getVerb().getFutur().getPlural1());
        Assert.assertEquals("vus vegnis a gratular", forms.getVerb().getFutur().getPlural2());
        Assert.assertEquals("els/ellas vegnan a gratular", forms.getVerb().getFutur().getPlural3());

        Assert.assertEquals("gratulau", forms.getVerb().getParticipPerfect().getMs());
        Assert.assertEquals("gratulaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        Assert.assertEquals("gratulada", forms.getVerb().getParticipPerfect().getFs());
        Assert.assertEquals("gratulai", forms.getVerb().getParticipPerfect().getMp());
        Assert.assertEquals("gratuladas", forms.getVerb().getParticipPerfect().getFp());

        Assert.assertEquals("gratulescha!", forms.getVerb().getImperativ().getSingular());
        Assert.assertEquals("gratulei!", forms.getVerb().getImperativ().getPlural());

        Assert.assertEquals("gratulond", forms.getVerb().getGerundium());
    }
}
