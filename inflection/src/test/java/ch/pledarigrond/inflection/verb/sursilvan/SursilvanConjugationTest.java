package ch.pledarigrond.inflection.verb.sursilvan;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.verb.sursilvan.SursilvanConjugation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SursilvanConjugationTest {

    private static SursilvanConjugation generator;
    private InflectionDto forms;

    @BeforeAll
    public static void initialize() {
        generator = new SursilvanConjugation();
    }

    @Test
    public void testRule1() {
        forms =  generator.generateConjugation("1", "gidar");

        assertEquals("gidar", forms.getVerb().getInfinitiv());

        assertEquals("jeu gidel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti gidas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella gida", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus gidein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus gideis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas gidan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu gidavel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti gidavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella gidava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus gidavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus gidavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas gidavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu gidi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti gidies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella gidi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus gidien\nche nus gideien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus gidies\nche vus gideies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas gidien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu gidavi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti gidavies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella gidavi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus gidavien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus gidavies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas gidavien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu gidass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti gidasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella gidass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus gidassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus gidasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas gidassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu gidassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti gidassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella gidassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus gidassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus gidassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas gidassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a gidar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a gidar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a gidar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a gidar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a gidar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a gidar", forms.getVerb().getFutur().getPlural3());

        assertEquals("gidau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("gidaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("gidada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("gidai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("gidadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("gida!", forms.getVerb().getImperativ().getSingular());
        assertEquals("gidei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("gidond", forms.getVerb().getGerundium());
    }


    @Test
    public void testRule1a() {
        forms =  generator.generateConjugation("1a", "clamar");

        assertEquals("clamar", forms.getVerb().getInfinitiv());

        assertEquals("jeu clomel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti clomas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella cloma", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus clamein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus clameis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas cloman", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu clamavel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti clamavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella clamava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus clamavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus clamavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas clamavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu clomi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti clomies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella clomi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus clomien\nche nus clameien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus clomies\nche vus clameies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas clomien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu clamavi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti clamavies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella clamavi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus clamavien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus clamavies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas clamavien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu clamass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti clamasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella clamass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus clamassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus clamasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas clamassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu clamassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti clamassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella clamassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus clamassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus clamassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas clamassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a clamar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a clamar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a clamar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a clamar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a clamar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a clamar", forms.getVerb().getFutur().getPlural3());

        assertEquals("clamau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("clamaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("clamada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("clamai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("clamadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("cloma!", forms.getVerb().getImperativ().getSingular());
        assertEquals("clamei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("clamond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1b() {
        forms =  generator.generateConjugation("1b", "tschintschar");

        assertEquals("tschintschar", forms.getVerb().getInfinitiv());

        assertEquals("jeu tschontschel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti tschontschas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella tschontscha", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus tschintschein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus tschintscheis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas tschontschan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu tschintschavel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti tschintschavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella tschintschava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus tschintschavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus tschintschavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas tschintschavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu tschontschi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti tschontschies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella tschontschi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus tschontschien\nche nus tschintscheien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus tschontschies\nche vus tschintscheies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas tschontschien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu tschintschavi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti tschintschavies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella tschintschavi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus tschintschavien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus tschintschavies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas tschintschavien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu tschintschass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti tschintschasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella tschintschass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus tschintschassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus tschintschasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas tschintschassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu tschintschassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti tschintschassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella tschintschassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus tschintschassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus tschintschassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas tschintschassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a tschintschar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a tschintschar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a tschintschar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a tschintschar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a tschintschar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a tschintschar", forms.getVerb().getFutur().getPlural3());

        assertEquals("tschintschau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("tschintschaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("tschintschada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("tschintschai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("tschintschadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("tschontscha!", forms.getVerb().getImperativ().getSingular());
        assertEquals("tschintschei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("tschintschond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1c() {
        forms =  generator.generateConjugation("1c", "purtar");

        assertEquals("purtar", forms.getVerb().getInfinitiv());

        assertEquals("jeu portel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti portas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella porta", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus purtein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus purteis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas portan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu purtavel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti purtavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella purtava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus purtavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus purtavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas purtavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu porti", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti porties", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella porti", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus portien\nche nus purteien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus porties\nche vus purteies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas portien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu purtavi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti purtavies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella purtavi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus purtavien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus purtavies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas purtavien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu purtass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti purtasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella purtass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus purtassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus purtasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas purtassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu purtassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti purtassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella purtassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus purtassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus purtassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas purtassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a purtar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a purtar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a purtar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a purtar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a purtar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a purtar", forms.getVerb().getFutur().getPlural3());

        assertEquals("purtau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("purtaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("purtada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("purtai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("purtadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("porta!", forms.getVerb().getImperativ().getSingular());
        assertEquals("purtei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("purtond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1d() {
        forms =  generator.generateConjugation("1d", "formar");

        assertEquals("formar", forms.getVerb().getInfinitiv());

        assertEquals("jeu fuormel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti fuormas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella fuorma", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus formein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus formeis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas fuorman", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu formavel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti formavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella formava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus formavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus formavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas formavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu fuormi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti fuormies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella fuormi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus fuormien\nche nus formeien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus fuormies\nche vus formeies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas fuormien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu formavi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti formavies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella formavi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus formavien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus formavies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas formavien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu formass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti formasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella formass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus formassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus formasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas formassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu formassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti formassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella formassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus formassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus formassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas formassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a formar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a formar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a formar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a formar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a formar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a formar", forms.getVerb().getFutur().getPlural3());

        assertEquals("formau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("formaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("formada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("formai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("formadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("fuorma!", forms.getVerb().getImperativ().getSingular());
        assertEquals("formei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("formond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1e() {
        forms =  generator.generateConjugation("1e", "mussar");

        assertEquals("mussar", forms.getVerb().getInfinitiv());

        assertEquals("jeu muossel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti muossas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella muossa", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus mussein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus musseis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas muossan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu mussavel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti mussavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella mussava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus mussavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus mussavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas mussavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu muossi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti muossies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella muossi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus muossien\nche nus musseien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus muossies\nche vus musseies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas muossien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu mussavi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti mussavies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella mussavi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus mussavien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus mussavies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas mussavien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu mussass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti mussasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella mussass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus mussassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus mussasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas mussassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu mussassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti mussassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella mussassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus mussassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus mussassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas mussassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a mussar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a mussar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a mussar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a mussar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a mussar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a mussar", forms.getVerb().getFutur().getPlural3());

        assertEquals("mussau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("mussaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("mussada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("mussai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("mussadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("muossa!", forms.getVerb().getImperativ().getSingular());
        assertEquals("mussei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("mussond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1f() {
        forms =  generator.generateConjugation("1f", "zavrar");

        assertEquals("zavrar", forms.getVerb().getInfinitiv());

        // zavrar is a special case, as it has also an alternative form jeu zavrel, but only the pattern form is tested here
        assertEquals("jeu zeivrel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti zeivras", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella zeivra", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus zavrein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus zavreis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas zeivran", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu zavravel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti zavravas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella zavrava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus zavravan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus zavravas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas zavravan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu zeivri", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti zeivries", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella zeivri", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus zeivrien\nche nus zavreien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus zeivries\nche vus zavreies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas zeivrien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu zavravi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti zavravies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella zavravi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus zavravien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus zavravies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas zavravien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu zavrass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti zavrasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella zavrass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus zavrassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus zavrasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas zavrassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu zavrassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti zavrassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella zavrassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus zavrassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus zavrassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas zavrassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a zavrar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a zavrar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a zavrar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a zavrar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a zavrar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a zavrar", forms.getVerb().getFutur().getPlural3());

        assertEquals("zavrau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("zavraus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("zavrada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("zavrai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("zavradas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("zeivra!", forms.getVerb().getImperativ().getSingular());
        assertEquals("zavrei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("zavrond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1g() {
        forms =  generator.generateConjugation("1g", "tedlar");

        assertEquals("tedlar", forms.getVerb().getInfinitiv());

        assertEquals("jeu teidlel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti teidlas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella teidla", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus tedlein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus tedleis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas teidlan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu tedlavel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti tedlavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella tedlava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus tedlavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus tedlavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas tedlavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu teidli", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti teidlies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella teidli", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus teidlien\nche nus tedleien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus teidlies\nche vus tedleies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas teidlien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu tedlavi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti tedlavies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella tedlavi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus tedlavien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus tedlavies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas tedlavien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu tedlass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti tedlasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella tedlass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus tedlassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus tedlasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas tedlassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu tedlassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti tedlassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella tedlassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus tedlassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus tedlassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas tedlassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a tedlar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a tedlar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a tedlar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a tedlar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a tedlar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a tedlar", forms.getVerb().getFutur().getPlural3());

        assertEquals("tedlau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("tedlaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("tedlada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("tedlai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("tedladas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("teidla!", forms.getVerb().getImperativ().getSingular());
        assertEquals("tedlei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("tedlond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1h() {
        forms =  generator.generateConjugation("1h", "pinar");

        assertEquals("pinar", forms.getVerb().getInfinitiv());

        assertEquals("jeu peinel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti peinas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella peina", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus pinein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus pineis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas peinan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu pinavel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti pinavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella pinava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus pinavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus pinavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas pinavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu peini", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti peinies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella peini", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus peinien\nche nus pineien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus peinies\nche vus pineies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas peinien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu pinavi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti pinavies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella pinavi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus pinavien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus pinavies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas pinavien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu pinass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti pinasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella pinass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus pinassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus pinasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas pinassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu pinassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti pinassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella pinassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus pinassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus pinassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas pinassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a pinar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a pinar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a pinar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a pinar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a pinar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a pinar", forms.getVerb().getFutur().getPlural3());

        assertEquals("pinau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("pinaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("pinada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("pinai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("pinadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("peina!", forms.getVerb().getImperativ().getSingular());
        assertEquals("pinei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("pinond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1i() {
        forms =  generator.generateConjugation("1i", "ludar");

        assertEquals("ludar", forms.getVerb().getInfinitiv());

        assertEquals("jeu laudel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti laudas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella lauda", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus ludein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus ludeis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas laudan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu ludavel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti ludavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella ludava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus ludavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus ludavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas ludavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu laudi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti laudies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella laudi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus laudien\nche nus ludeien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus laudies\nche vus ludeies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas laudien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu ludavi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti ludavies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella ludavi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus ludavien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus ludavies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas ludavien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu ludass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti ludasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella ludass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus ludassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus ludasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas ludassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu ludassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti ludassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella ludassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus ludassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus ludassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas ludassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a ludar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a ludar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a ludar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a ludar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a ludar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a ludar", forms.getVerb().getFutur().getPlural3());

        assertEquals("ludau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("ludaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("ludada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("ludai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("ludadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("lauda!", forms.getVerb().getImperativ().getSingular());
        assertEquals("ludei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("ludond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1j() {
        forms =  generator.generateConjugation("1j", "filtschar");

        assertEquals("filtschar", forms.getVerb().getInfinitiv());

        assertEquals("jeu faultschel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti faultschas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella faultscha", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus filtschein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus filtscheis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas faultschan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu filtschavel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti filtschavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella filtschava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus filtschavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus filtschavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas filtschavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu faultschi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti faultschies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella faultschi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus faultschien\nche nus filtscheien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus faultschies\nche vus filtscheies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas faultschien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu filtschavi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti filtschavies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella filtschavi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus filtschavien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus filtschavies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas filtschavien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu filtschass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti filtschasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella filtschass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus filtschassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus filtschasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas filtschassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu filtschassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti filtschassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella filtschassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus filtschassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus filtschassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas filtschassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a filtschar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a filtschar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a filtschar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a filtschar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a filtschar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a filtschar", forms.getVerb().getFutur().getPlural3());

        assertEquals("filtschau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("filtschaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("filtschada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("filtschai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("filtschadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("faultscha!", forms.getVerb().getImperativ().getSingular());
        assertEquals("filtschei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("filtschond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1k() {
        forms =  generator.generateConjugation("1k", "alzar");

        assertEquals("alzar", forms.getVerb().getInfinitiv());

        assertEquals("jeu aulzel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti aulzas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella aulza", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus alzein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus alzeis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas aulzan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu alzavel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti alzavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella alzava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus alzavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus alzavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas alzavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu aulzi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti aulzies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella aulzi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus aulzien\nche nus alzeien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus aulzies\nche vus alzeies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas aulzien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu alzavi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti alzavies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella alzavi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus alzavien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus alzavies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas alzavien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu alzass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti alzasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella alzass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus alzassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus alzasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas alzassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu alzassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti alzassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella alzassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus alzassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus alzassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas alzassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) ad alzar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns ad alzar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn ad alzar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin ad alzar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis ad alzar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan ad alzar", forms.getVerb().getFutur().getPlural3());

        assertEquals("alzau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("alzaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("alzada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("alzai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("alzadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("aulza!", forms.getVerb().getImperativ().getSingular());
        assertEquals("alzei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("alzond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1l() {
        forms =  generator.generateConjugation("1l", "emparar");

        assertEquals("emparar", forms.getVerb().getInfinitiv());

        assertEquals("jeu empiarel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti empiaras", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella empiara", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus emparein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus empareis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas empiaran", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu emparavel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti emparavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella emparava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus emparavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus emparavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas emparavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu empiari", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti empiaries", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella empiari", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus empiarien\nche nus empareien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus empiaries\nche vus empareies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas empiarien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu emparavi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti emparavies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella emparavi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus emparavien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus emparavies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas emparavien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu emparass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti emparasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella emparass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus emparassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus emparasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas emparassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu emparassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti emparassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella emparassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus emparassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus emparassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas emparassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) ad emparar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns ad emparar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn ad emparar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin ad emparar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis ad emparar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan ad emparar", forms.getVerb().getFutur().getPlural3());

        assertEquals("emparau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("emparaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("emparada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("emparai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("emparadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("empiara!", forms.getVerb().getImperativ().getSingular());
        assertEquals("emparei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("emparond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1m() {
        forms =  generator.generateConjugation("1m", "serrar");

        assertEquals("serrar", forms.getVerb().getInfinitiv());

        assertEquals("jeu siarel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti siaras", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella siara", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus serrein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus serreis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas siaran", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu serravel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti serravas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella serrava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus serravan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus serravas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas serravan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu siari", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti siaries", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella siari", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus siarien\nche nus serreien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus siaries\nche vus serreies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas siarien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu serravi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti serravies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella serravi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus serravien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus serravies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas serravien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu serrass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti serrasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella serrass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus serrassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus serrasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas serrassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu serrassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti serrassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella serrassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus serrassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus serrassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas serrassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a serrar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a serrar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a serrar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a serrar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a serrar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a serrar", forms.getVerb().getFutur().getPlural3());

        assertEquals("serrau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("serraus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("serrada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("serrai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("serradas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("siara!", forms.getVerb().getImperativ().getSingular());
        assertEquals("serrei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("serrond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1n() {
        forms =  generator.generateConjugation("1n", "zanistrar");

        assertEquals("zanistrar", forms.getVerb().getInfinitiv());

        assertEquals("jeu zaniastrel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti zaniastras", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella zaniastra", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus zanistrein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus zanistreis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas zaniastran", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu zanistravel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti zanistravas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella zanistrava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus zanistravan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus zanistravas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas zanistravan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu zaniastri", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti zaniastries", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella zaniastri", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus zaniastrien\nche nus zanistreien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus zaniastries\nche vus zanistreies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas zaniastrien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu zanistravi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti zanistravies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella zanistravi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus zanistravien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus zanistravies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas zanistravien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu zanistrass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti zanistrasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella zanistrass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus zanistrassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus zanistrasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas zanistrassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu zanistrassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti zanistrassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella zanistrassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus zanistrassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus zanistrassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas zanistrassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a zanistrar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a zanistrar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a zanistrar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a zanistrar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a zanistrar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a zanistrar", forms.getVerb().getFutur().getPlural3());

        assertEquals("zanistrau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("zanistraus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("zanistrada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("zanistrai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("zanistradas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("zaniastra!", forms.getVerb().getImperativ().getSingular());
        assertEquals("zanistrei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("zanistrond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1o() {
        forms =  generator.generateConjugation("1o", "ugliar");

        assertEquals("ugliar", forms.getVerb().getInfinitiv());

        assertEquals("jeu uegliel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti ueglias", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella ueglia", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus ugliein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus uglieis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas ueglian", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu ugliavel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti ugliavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella ugliava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus ugliavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus ugliavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas ugliavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu uegli", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti ueglies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella uegli", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus ueglien\nche nus uglieien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus ueglies\nche vus uglieies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas ueglien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu ugliavi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti ugliavies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella ugliavi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus ugliavien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus ugliavies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas ugliavien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu ugliass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti ugliasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella ugliass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus ugliassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus ugliasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas ugliassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu ugliassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti ugliassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella ugliassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus ugliassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus ugliassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas ugliassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) ad ugliar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns ad ugliar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn ad ugliar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin ad ugliar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis ad ugliar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan ad ugliar", forms.getVerb().getFutur().getPlural3());

        assertEquals("ugliau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("ugliaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("ugliada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("ugliai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("ugliadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("ueglia!", forms.getVerb().getImperativ().getSingular());
        assertEquals("ugliei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("ugliond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1p() {
        forms =  generator.generateConjugation("1p", "spitgar");

        assertEquals("spitgar", forms.getVerb().getInfinitiv());

        assertEquals("jeu spetgel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti spetgas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella spetga", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus spitgein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus spitgeis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas spetgan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu spitgavel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti spitgavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella spitgava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus spitgavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus spitgavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas spitgavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu spetgi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti spetgies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella spetgi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus spetgien\nche nus spitgeien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus spetgies\nche vus spitgeies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas spetgien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu spitgavi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti spitgavies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella spitgavi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus spitgavien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus spitgavies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas spitgavien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu spitgass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti spitgasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella spitgass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus spitgassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus spitgasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas spitgassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu spitgassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti spitgassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella spitgassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus spitgassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus spitgassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas spitgassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a spitgar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a spitgar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a spitgar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a spitgar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a spitgar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a spitgar", forms.getVerb().getFutur().getPlural3());

        assertEquals("spitgau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("spitgaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("spitgada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("spitgai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("spitgadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("spetga!", forms.getVerb().getImperativ().getSingular());
        assertEquals("spitgei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("spitgond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1q() {
        forms =  generator.generateConjugation("1q", "semiar");

        assertEquals("semiar", forms.getVerb().getInfinitiv());

        assertEquals("jeu siemiel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti siemias", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella siemia", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus semiein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus semieis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas siemian", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu semiavel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti semiavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella semiava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus semiavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus semiavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas semiavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu siemi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti siemies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella siemi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus siemien\nche nus semieien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus siemies\nche vus semieies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas siemien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu semiavi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti semiavies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella semiavi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus semiavien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus semiavies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas semiavien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu semiass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti semiasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella semiass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus semiassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus semiasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas semiassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu semiassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti semiassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella semiassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus semiassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus semiassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas semiassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a semiar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a semiar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a semiar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a semiar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a semiar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a semiar", forms.getVerb().getFutur().getPlural3());

        assertEquals("semiau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("semiaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("semiada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("semiai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("semiadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("siemia!", forms.getVerb().getImperativ().getSingular());
        assertEquals("semiei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("semiond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1r() {
        forms =  generator.generateConjugation("1r", "rischlar");

        assertEquals("rischlar", forms.getVerb().getInfinitiv());

        // there is also the irregular form 'jeu reschlel', but the test tests only the regular pattern
        assertEquals("jeu raschlel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti raschlas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella raschla", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus rischlein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus rischleis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas raschlan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu rischlavel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti rischlavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella rischlava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus rischlavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus rischlavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas rischlavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu raschli", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti raschlies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella raschli", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus raschlien\nche nus rischleien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus raschlies\nche vus rischleies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas raschlien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu rischlavi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti rischlavies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella rischlavi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus rischlavien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus rischlavies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas rischlavien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu rischlass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti rischlasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella rischlass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus rischlassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus rischlasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas rischlassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu rischlassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti rischlassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella rischlassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus rischlassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus rischlassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas rischlassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a rischlar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a rischlar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a rischlar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a rischlar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a rischlar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a rischlar", forms.getVerb().getFutur().getPlural3());

        assertEquals("rischlau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("rischlaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("rischlada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("rischlai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("rischladas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("raschla!", forms.getVerb().getImperativ().getSingular());
        assertEquals("rischlei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("rischlond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1s() {
        forms =  generator.generateConjugation("1s", "sbuserar");

        assertEquals("sbuserar", forms.getVerb().getInfinitiv());

        assertEquals("jeu sbusirel\njeu sbusarel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti sbusiras\nti sbusaras", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella sbusira\nel/ella sbusara", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus sbuserein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus sbusereis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas sbusiran\nels/ellas sbusaran", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu sbuseravel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti sbuseravas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella sbuserava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus sbuseravan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus sbuseravas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas sbuseravan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu sbusiri\nche jeu sbusari", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti sbusiries\nche ti sbusaries", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella sbusiri\nch'el/ella sbusari", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus sbusirien\nche nus sbusarien\nche nus sbusereien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus sbusiries\nche vus sbusaries\nche vus sbusereies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas sbusirien\nch'els/ellas sbusarien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu sbuseravi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti sbuseravies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella sbuseravi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus sbuseravien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus sbuseravies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas sbuseravien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu sbuserass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti sbuserasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella sbuserass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus sbuserassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus sbuserasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas sbuserassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu sbuserassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti sbuserassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella sbuserassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus sbuserassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus sbuserassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas sbuserassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a sbuserar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a sbuserar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a sbuserar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a sbuserar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a sbuserar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a sbuserar", forms.getVerb().getFutur().getPlural3());

        assertEquals("sbuserau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("sbuseraus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("sbuserada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("sbuserai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("sbuseradas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("sbusira!\nsbusara!", forms.getVerb().getImperativ().getSingular());
        assertEquals("sbuserei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("sbuserond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1t() {
        forms =  generator.generateConjugation("1t", "smarschanar");

        assertEquals("smarschanar", forms.getVerb().getInfinitiv());

        assertEquals("jeu smarschunel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti smarschunas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella smarschuna", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus smarschanein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus smarschaneis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas smarschunan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu smarschanavel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti smarschanavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella smarschanava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus smarschanavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus smarschanavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas smarschanavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu smarschuni", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti smarschunies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella smarschuni", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus smarschunien\nche nus smarschaneien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus smarschunies\nche vus smarschaneies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas smarschunien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu smarschanavi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti smarschanavies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella smarschanavi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus smarschanavien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus smarschanavies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas smarschanavien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu smarschanass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti smarschanasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella smarschanass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus smarschanassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus smarschanasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas smarschanassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu smarschanassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti smarschanassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella smarschanassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus smarschanassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus smarschanassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas smarschanassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a smarschanar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a smarschanar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a smarschanar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a smarschanar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a smarschanar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a smarschanar", forms.getVerb().getFutur().getPlural3());

        assertEquals("smarschanau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("smarschanaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("smarschanada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("smarschanai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("smarschanadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("smarschuna!", forms.getVerb().getImperativ().getSingular());
        assertEquals("smarschanei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("smarschanond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1u() {
        forms =  generator.generateConjugation("1u", "cuglienar");

        assertEquals("cuglienar", forms.getVerb().getInfinitiv());

        assertEquals("jeu cugliunel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti cugliunas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella cugliuna", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus cuglienein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus cuglieneis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas cugliunan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu cuglienavel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti cuglienavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella cuglienava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus cuglienavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus cuglienavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas cuglienavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu cugliuni", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti cugliunies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella cugliuni", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus cugliunien\nche nus cuglieneien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus cugliunies\nche vus cuglieneies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas cugliunien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu cuglienavi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti cuglienavies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella cuglienavi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus cuglienavien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus cuglienavies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas cuglienavien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu cuglienass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti cuglienasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella cuglienass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus cuglienassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus cuglienasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas cuglienassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu cuglienassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti cuglienassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella cuglienassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus cuglienassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus cuglienassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas cuglienassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a cuglienar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a cuglienar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a cuglienar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a cuglienar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a cuglienar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a cuglienar", forms.getVerb().getFutur().getPlural3());

        assertEquals("cuglienau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("cuglienaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("cuglienada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("cuglienai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("cuglienadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("cugliuna!", forms.getVerb().getImperativ().getSingular());
        assertEquals("cuglienei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("cuglienond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1v() {
        forms =  generator.generateConjugation("1v", "curclar");

        assertEquals("curclar", forms.getVerb().getInfinitiv());

        assertEquals("jeu cuarclel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti cuarclas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella cuarcla", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus curclein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus curcleis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas cuarclan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu curclavel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti curclavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella curclava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus curclavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus curclavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas curclavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu cuarcli", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti cuarclies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella cuarcli", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus cuarclien\nche nus curcleien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus cuarclies\nche vus curcleies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas cuarclien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu curclavi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti curclavies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella curclavi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus curclavien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus curclavies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas curclavien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu curclass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti curclasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella curclass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus curclassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus curclasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas curclassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu curclassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti curclassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella curclassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus curclassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus curclassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas curclassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a curclar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a curclar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a curclar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a curclar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a curclar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a curclar", forms.getVerb().getFutur().getPlural3());

        assertEquals("curclau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("curclaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("curclada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("curclai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("curcladas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("cuarcla!", forms.getVerb().getImperativ().getSingular());
        assertEquals("curclei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("curclond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1w() {
        forms =  generator.generateConjugation("1w", "suar");

        assertEquals("suar", forms.getVerb().getInfinitiv());

        assertEquals("jeu siuel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti siuas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella siua", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus suein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus sueis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas siuan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu suavel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti suavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella suava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus suavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus suavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas suavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu siui", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti siuies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella siui", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus siuien\nche nus sueien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus siuies\nche vus sueies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas siuien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu suavi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti suavies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella suavi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus suavien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus suavies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas suavien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu suass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti suasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella suass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus suassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus suasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas suassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu suassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti suassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella suassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus suassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus suassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas suassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a suar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a suar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a suar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a suar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a suar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a suar", forms.getVerb().getFutur().getPlural3());

        assertEquals("suau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("suaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("suada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("suai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("suadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("siua!", forms.getVerb().getImperativ().getSingular());
        assertEquals("suei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("suond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1x() {
        forms =  generator.generateConjugation("1x", "cumandar");

        assertEquals("cumandar", forms.getVerb().getInfinitiv());

        assertEquals("jeu camondel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti camondas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella camonda", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus cumandein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus cumandeis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas camondan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu cumandavel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti cumandavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella cumandava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus cumandavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus cumandavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas cumandavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu camondi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti camondies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella camondi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus camondien\nche nus cumandeien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus camondies\nche vus cumandeies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas camondien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu cumandavi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti cumandavies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella cumandavi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus cumandavien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus cumandavies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas cumandavien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu cumandass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti cumandasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella cumandass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus cumandassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus cumandasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas cumandassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu cumandassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti cumandassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella cumandassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus cumandassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus cumandassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas cumandassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a cumandar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a cumandar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a cumandar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a cumandar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a cumandar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a cumandar", forms.getVerb().getFutur().getPlural3());

        assertEquals("cumandau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("cumandaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("cumandada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("cumandai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("cumandadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("camonda!", forms.getVerb().getImperativ().getSingular());
        assertEquals("cumandei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("cumandond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1y() {
        forms =  generator.generateConjugation("1y", "dumignar");

        assertEquals("dumignar", forms.getVerb().getInfinitiv());

        assertEquals("jeu damognel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti damognas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella damogna", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus dumignein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus dumigneis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas damognan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu dumignavel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti dumignavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella dumignava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus dumignavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus dumignavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas dumignavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu damogni", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti damognies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella damogni", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus damognien\nche nus dumigneien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus damognies\nche vus dumigneies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas damognien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu dumignavi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti dumignavies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella dumignavi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus dumignavien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus dumignavies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas dumignavien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu dumignass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti dumignasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella dumignass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus dumignassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus dumignasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas dumignassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu dumignassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti dumignassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella dumignassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus dumignassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus dumignassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas dumignassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a dumignar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a dumignar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a dumignar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a dumignar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a dumignar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a dumignar", forms.getVerb().getFutur().getPlural3());

        assertEquals("dumignau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("dumignaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("dumignada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("dumignai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("dumignadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("damogna!", forms.getVerb().getImperativ().getSingular());
        assertEquals("dumignei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("dumignond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1z() {
        forms =  generator.generateConjugation("1z", "rumplanar");

        assertEquals("rumplanar", forms.getVerb().getInfinitiv());

        assertEquals("jeu ramplunel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti ramplunas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella rampluna", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus rumplanein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus rumplaneis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas ramplunan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu rumplanavel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti rumplanavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella rumplanava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus rumplanavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus rumplanavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas rumplanavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu rampluni", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti ramplunies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella rampluni", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus ramplunien\nche nus rumplaneien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus ramplunies\nche vus rumplaneies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas ramplunien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu rumplanavi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti rumplanavies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella rumplanavi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus rumplanavien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus rumplanavies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas rumplanavien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu rumplanass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti rumplanasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella rumplanass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus rumplanassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus rumplanasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas rumplanassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu rumplanassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti rumplanassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella rumplanassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus rumplanassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus rumplanassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas rumplanassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a rumplanar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a rumplanar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a rumplanar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a rumplanar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a rumplanar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a rumplanar", forms.getVerb().getFutur().getPlural3());

        assertEquals("rumplanau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("rumplanaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("rumplanada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("rumplanai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("rumplanadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("rampluna!", forms.getVerb().getImperativ().getSingular());
        assertEquals("rumplanei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("rumplanond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1aa() {
        forms =  generator.generateConjugation("1aa", "murmignar");

        assertEquals("murmignar", forms.getVerb().getInfinitiv());

        assertEquals("jeu marmugnel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti marmugnas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella marmugna", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus murmignein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus murmigneis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas marmugnan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu murmignavel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti murmignavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella murmignava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus murmignavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus murmignavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas murmignavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu marmugni", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti marmugnies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella marmugni", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus marmugnien\nche nus murmigneien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus marmugnies\nche vus murmigneies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas marmugnien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu murmignavi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti murmignavies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella murmignavi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus murmignavien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus murmignavies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas murmignavien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu murmignass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti murmignasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella murmignass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus murmignassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus murmignasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas murmignassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu murmignassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti murmignassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella murmignassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus murmignassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus murmignassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas murmignassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a murmignar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a murmignar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a murmignar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a murmignar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a murmignar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a murmignar", forms.getVerb().getFutur().getPlural3());

        assertEquals("murmignau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("murmignaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("murmignada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("murmignai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("murmignadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("marmugna!", forms.getVerb().getImperativ().getSingular());
        assertEquals("murmignei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("murmignond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1ab() {
        forms =  generator.generateConjugation("1ab", "buntganar");

        assertEquals("buntganar", forms.getVerb().getInfinitiv());

        assertEquals("jeu bintgunel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti bintgunas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella bintguna", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus buntganein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus buntganeis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas bintgunan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu buntganavel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti buntganavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella buntganava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus buntganavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus buntganavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas buntganavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu bintguni", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti bintgunies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella bintguni", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus bintgunien\nche nus buntganeien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus bintgunies\nche vus buntganeies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas bintgunien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu buntganavi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti buntganavies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella buntganavi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus buntganavien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus buntganavies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas buntganavien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu buntganass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti buntganasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella buntganass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus buntganassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus buntganasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas buntganassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu buntganassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti buntganassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella buntganassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus buntganassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus buntganassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas buntganassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a buntganar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a buntganar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a buntganar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a buntganar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a buntganar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a buntganar", forms.getVerb().getFutur().getPlural3());

        assertEquals("buntganau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("buntganaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("buntganada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("buntganai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("buntganadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("bintguna!", forms.getVerb().getImperativ().getSingular());
        assertEquals("buntganei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("buntganond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1ac() {
        forms =  generator.generateConjugation("1ac", "duvrar");

        assertEquals("duvrar", forms.getVerb().getInfinitiv());

        assertEquals("jeu drovel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti drovas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella drova", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus duvrein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus duvreis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas drovan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu duvravel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti duvravas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella duvrava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus duvravan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus duvravas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas duvravan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu drovi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti drovies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella drovi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus drovien\nche nus duvreien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus drovies\nche vus duvreies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas drovien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu duvravi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti duvravies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella duvravi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus duvravien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus duvravies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas duvravien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu duvrass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti duvrasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella duvrass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus duvrassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus duvrasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas duvrassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu duvrassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti duvrassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella duvrassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus duvrassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus duvrassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas duvrassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a duvrar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a duvrar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a duvrar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a duvrar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a duvrar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a duvrar", forms.getVerb().getFutur().getPlural3());

        assertEquals("duvrau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("duvraus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("duvrada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("duvrai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("duvradas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("drova!", forms.getVerb().getImperativ().getSingular());
        assertEquals("duvrei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("duvrond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1acAlternativ() {
        forms =  generator.generateConjugation("1ac", "curdar");

        assertEquals("curdar", forms.getVerb().getInfinitiv());

        assertEquals("jeu crodel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti crodas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella croda", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus curdein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus curdeis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas crodan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu curdavel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti curdavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella curdava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus curdavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus curdavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas curdavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu crodi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti crodies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella crodi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus crodien\nche nus curdeien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus crodies\nche vus curdeies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas crodien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu curdavi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti curdavies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella curdavi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus curdavien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus curdavies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas curdavien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu curdass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti curdasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella curdass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus curdassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus curdasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas curdassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu curdassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti curdassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella curdassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus curdassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus curdassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas curdassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a curdar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a curdar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a curdar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a curdar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a curdar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a curdar", forms.getVerb().getFutur().getPlural3());

        assertEquals("curdau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("curdaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("curdada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("curdai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("curdadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("croda!", forms.getVerb().getImperativ().getSingular());
        assertEquals("curdei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("curdond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1ad() {
        forms =  generator.generateConjugation("1ad", "patertgar");

        assertEquals("patertgar", forms.getVerb().getInfinitiv());

        assertEquals("jeu patratgel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti patratgas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella patratga", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus patertgein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus patertgeis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas patratgan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu patertgavel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti patertgavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella patertgava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus patertgavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus patertgavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas patertgavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu patratgi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti patratgies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella patratgi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus patratgien\nche nus patertgeien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus patratgies\nche vus patertgeies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas patratgien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu patertgavi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti patertgavies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella patertgavi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus patertgavien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus patertgavies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas patertgavien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu patertgass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti patertgasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella patertgass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus patertgassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus patertgasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas patertgassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu patertgassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti patertgassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella patertgassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus patertgassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus patertgassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas patertgassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a patertgar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a patertgar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a patertgar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a patertgar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a patertgar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a patertgar", forms.getVerb().getFutur().getPlural3());

        assertEquals("patertgau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("patertgaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("patertgada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("patertgai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("patertgadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("patratga!", forms.getVerb().getImperativ().getSingular());
        assertEquals("patertgei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("patertgond", forms.getVerb().getGerundium());
    }

    @Test
    @Disabled
    public void testRule1ae() {
        forms =  generator.generateConjugation("1ae", "tschuffergnar");

        assertEquals("tschuffergnar", forms.getVerb().getInfinitiv());

        assertEquals("jeu tschufrognel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti tschufrognas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella tschufrogna", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus tschuffergnein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus tschuffergneis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas tschufrognan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu tschuffergnavel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti tschuffergnavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella tschuffergnava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus tschuffergnavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus tschuffergnavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas tschuffergnavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu tschufrogni", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti tschufrognies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella tschufrogni", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus tschufrognien\nche nus tschuffergneien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus tschufrognies\nche vus tschuffergneies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas tschufrognien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu tschuffergnavi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti tschuffergnavies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella tschuffergnavi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus tschuffergnavien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus tschuffergnavies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas tschuffergnavien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu tschuffergnass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti tschuffergnasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella tschuffergnass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus tschuffergnassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus tschuffergnasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas tschuffergnassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu tschuffergnassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti tschuffergnassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella tschuffergnassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus tschuffergnassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus tschuffergnassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas tschuffergnassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a tschuffergnar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a tschuffergnar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a tschuffergnar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a tschuffergnar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a tschuffergnar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a tschuffergnar", forms.getVerb().getFutur().getPlural3());

        assertEquals("tschuffergnau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("tschuffergnaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("tschuffergnada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("tschuffergnai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("tschuffergnadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("tschufrogna!", forms.getVerb().getImperativ().getSingular());
        assertEquals("tschuffergnei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("tschuffergnond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1af() {
        forms =  generator.generateConjugation("1af", "sgarflar");

        assertEquals("sgarflar", forms.getVerb().getInfinitiv());

        assertEquals("jeu sgreflel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti sgreflas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella sgrefla", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus sgarflein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus sgarfleis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas sgreflan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu sgarflavel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti sgarflavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella sgarflava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus sgarflavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus sgarflavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas sgarflavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu sgrefli", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti sgreflies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella sgrefli", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus sgreflien\nche nus sgarfleien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus sgreflies\nche vus sgarfleies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas sgreflien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu sgarflavi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti sgarflavies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella sgarflavi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus sgarflavien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus sgarflavies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas sgarflavien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu sgarflass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti sgarflasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella sgarflass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus sgarflassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus sgarflasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas sgarflassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu sgarflassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti sgarflassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella sgarflassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus sgarflassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus sgarflassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas sgarflassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a sgarflar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a sgarflar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a sgarflar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a sgarflar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a sgarflar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a sgarflar", forms.getVerb().getFutur().getPlural3());

        assertEquals("sgarflau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("sgarflaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("sgarflada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("sgarflai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("sgarfladas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("sgrefla!", forms.getVerb().getImperativ().getSingular());
        assertEquals("sgarflei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("sgarflond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1ag() {
        forms =  generator.generateConjugation("1ag", "sblundergiar");

        assertEquals("sblundergiar", forms.getVerb().getInfinitiv());

        assertEquals("jeu sblundregel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti sblundregias", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella sblundregia", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus sblundergein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus sblundergeis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas sblundregian", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu sblundergiavel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti sblundergiavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella sblundergiava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus sblundergiavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus sblundergiavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas sblundergiavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu sblundregi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti sblundregies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella sblundregi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus sblundregien\nche nus sblundergeien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus sblundregies\nche vus sblundergeies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas sblundregien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu sblundergiavi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti sblundergiavies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella sblundergiavi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus sblundergiavien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus sblundergiavies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas sblundergiavien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu sblundergiass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti sblundergiasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella sblundergiass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus sblundergiassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus sblundergiasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas sblundergiassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu sblundergiassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti sblundergiassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella sblundergiassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus sblundergiassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus sblundergiassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas sblundergiassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a sblundergiar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a sblundergiar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a sblundergiar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a sblundergiar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a sblundergiar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a sblundergiar", forms.getVerb().getFutur().getPlural3());

        assertEquals("sblundergiau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("sblundergiaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("sblundergiada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("sblundergiai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("sblundergiadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("sblundregia!", forms.getVerb().getImperativ().getSingular());
        assertEquals("sblundergei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("sblundergiond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1ah() {
        forms =  generator.generateConjugation("1ah", "barschar");

        assertEquals("barschar", forms.getVerb().getInfinitiv());

        assertEquals("jeu brischel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti brischas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella brischa", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus barschein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus barscheis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas brischan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu barschavel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti barschavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella barschava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus barschavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus barschavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas barschavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu brischi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti brischies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella brischi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus brischien\nche nus barscheien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus brischies\nche vus barscheies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas brischien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu barschavi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti barschavies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella barschavi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus barschavien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus barschavies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas barschavien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu barschass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti barschasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella barschass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus barschassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus barschasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas barschassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu barschassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti barschassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella barschassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus barschassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus barschassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas barschassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a barschar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a barschar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a barschar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a barschar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a barschar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a barschar", forms.getVerb().getFutur().getPlural3());

        assertEquals("barschau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("barschaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("barschada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("barschai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("barschadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("brischa!", forms.getVerb().getImperativ().getSingular());
        assertEquals("barschei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("barschond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule1ai() {
        forms =  generator.generateConjugation("1ai", "furschar");

        assertEquals("furschar", forms.getVerb().getInfinitiv());

        assertEquals("jeu fruschel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti fruschas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella fruscha", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus furschein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus furscheis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas fruschan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu furschavel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti furschavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella furschava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus furschavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus furschavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas furschavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu fruschi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti fruschies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella fruschi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus fruschien\nche nus furscheien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus fruschies\nche vus furscheies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas fruschien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu furschavi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti furschavies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella furschavi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus furschavien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus furschavies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas furschavien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu furschass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti furschasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella furschass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus furschassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus furschasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas furschassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu furschassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti furschassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella furschassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus furschassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus furschassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas furschassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a furschar", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a furschar", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a furschar", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a furschar", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a furschar", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a furschar", forms.getVerb().getFutur().getPlural3());

        assertEquals("furschau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("furschaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("furschada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("furschai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("furschadas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("fruscha!", forms.getVerb().getImperativ().getSingular());
        assertEquals("furschei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("furschond", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule2() {
        forms =  generator.generateConjugation("2", "temer");

        assertEquals("temer", forms.getVerb().getInfinitiv());

        assertEquals("jeu temel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti temas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella tema", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus temein\nnus temin", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus temeis\nvus temis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas teman", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu temevel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti temevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella temeva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus temevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus temevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas temevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu temi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti temies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella temi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus temien\nche nus temeien\nche nus temîen", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus temies\nche vus temeies\nche vus temîes", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas temien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu temevi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti temevies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella temevi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus temevien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus temevies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas temevien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu temess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti temesses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella temess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus temessen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus temesses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas temessen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu temessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti temessies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella temessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus temessien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus temessies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas temessien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a temer", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a temer", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a temer", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a temer", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a temer", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a temer", forms.getVerb().getFutur().getPlural3());

        assertEquals("temiu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("temius", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("temida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("temi", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("temidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("tema!", forms.getVerb().getImperativ().getSingular());
        assertEquals("temei!\ntemi!", forms.getVerb().getImperativ().getPlural());

        assertEquals("temend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule2a() {
        forms =  generator.generateConjugation("2a", "guder");

        assertEquals("guder", forms.getVerb().getInfinitiv());

        assertEquals("jeu gaudel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti gaudas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella gauda", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus gudin", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus gudis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas gaudan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu gudevel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti gudevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella gudeva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus gudevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus gudevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas gudevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu gaudi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti gaudies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella gaudi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus gaudien\nche nus gudîen", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus gaudies\nche vus gudîes", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas gaudien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu gudevi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti gudevies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella gudevi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus gudevien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus gudevies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas gudevien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu gudess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti gudesses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella gudess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus gudessen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus gudesses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas gudessen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu gudessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti gudessies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella gudessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus gudessien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus gudessies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas gudessien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a guder", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a guder", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a guder", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a guder", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a guder", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a guder", forms.getVerb().getFutur().getPlural3());

        assertEquals("gudiu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("gudius", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("gudida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("gudi", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("gudidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("gauda!", forms.getVerb().getImperativ().getSingular());
        assertEquals("gudi!", forms.getVerb().getImperativ().getPlural());

        assertEquals("gudend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3() {
        forms =  generator.generateConjugation("3", "vender");

        assertEquals("vender", forms.getVerb().getInfinitiv());

        assertEquals("jeu vendel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti vendas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella venda", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus vendein\nnus vendin", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus vendeis\nvus vendis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas vendan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu vendevel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti vendevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella vendeva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus vendevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus vendevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas vendevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu vendi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti vendies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella vendi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus vendien\nche nus vendeien\nche nus vendîen", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus vendies\nche vus vendeies\nche vus vendîes", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas vendien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu vendevi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti vendevies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella vendevi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus vendevien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus vendevies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas vendevien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu vendess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti vendesses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella vendess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus vendessen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus vendesses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas vendessen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu vendessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti vendessies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella vendessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus vendessien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus vendessies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas vendessien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a vender", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a vender", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a vender", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a vender", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a vender", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a vender", forms.getVerb().getFutur().getPlural3());

        assertEquals("vendiu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("vendius", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("vendida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("vendi", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("vendidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("venda!", forms.getVerb().getImperativ().getSingular());
        assertEquals("vendei!\nvendi!", forms.getVerb().getImperativ().getPlural());

        assertEquals("vendend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3a() {
        forms =  generator.generateConjugation("3a", "volver");

        assertEquals("volver", forms.getVerb().getInfinitiv());

        assertEquals("jeu volvel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti volvas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella volva", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus vulvein\nnus vulvin", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus vulveis\nvus vulvis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas volvan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu vulvevel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti vulvevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella vulveva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus vulvevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus vulvevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas vulvevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu volvi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti volvies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella volvi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus volvien\nche nus vulveien\nche nus vulvîen", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus volvies\nche vus vulveies\nche vus vulvîes", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas volvien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu vulvevi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti vulvevies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella vulvevi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus vulvevien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus vulvevies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas vulvevien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu vulvess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti vulvesses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella vulvess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus vulvessen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus vulvesses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas vulvessen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu vulvessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti vulvessies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella vulvessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus vulvessien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus vulvessies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas vulvessien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a volver", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a volver", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a volver", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a volver", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a volver", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a volver", forms.getVerb().getFutur().getPlural3());

        // 'volver' would also have an irregular form 'viult', but the test is only for the regular pattern
        assertEquals("vulviu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("vulvius", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("vulvida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("vulvi", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("vulvidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("volva!", forms.getVerb().getImperativ().getSingular());
        assertEquals("vulvei!\nvulvi!", forms.getVerb().getImperativ().getPlural());

        assertEquals("vulvend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3b() {
        forms =  generator.generateConjugation("3b", "rietscher");

        assertEquals("rietscher", forms.getVerb().getInfinitiv());

        assertEquals("jeu rietschel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti rietschas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella rietscha", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus ritschin", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus ritschis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas rietschan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu ritschevel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti ritschevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella ritscheva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus ritschevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus ritschevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas ritschevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu rietschi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti rietschies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella rietschi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus rietschien\nche nus ritschîen", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus rietschies\nche vus ritschîes", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas rietschien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu ritschevi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti ritschevies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella ritschevi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus ritschevien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus ritschevies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas ritschevien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu ritschess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti ritschesses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella ritschess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus ritschessen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus ritschesses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas ritschessen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu ritschessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti ritschessies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella ritschessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus ritschessien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus ritschessies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas ritschessien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a rietscher", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a rietscher", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a rietscher", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a rietscher", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a rietscher", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a rietscher", forms.getVerb().getFutur().getPlural3());

        assertEquals("ritschiu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("ritschius", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("ritschida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("ritschi", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("ritschidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("rietscha!", forms.getVerb().getImperativ().getSingular());
        assertEquals("ritschi!", forms.getVerb().getImperativ().getPlural());

        assertEquals("ritschend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3c() {
        forms =  generator.generateConjugation("3c", "clauder");

        assertEquals("clauder", forms.getVerb().getInfinitiv());

        assertEquals("jeu claudel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti claudas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella clauda", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus cludein\nnus cludin", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus cludeis\nvus cludis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas claudan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu cludevel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti cludevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella cludeva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus cludevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus cludevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas cludevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu claudi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti claudies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella claudi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus claudien\nche nus cludeien\nche nus cludîen", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus claudies\nche vus cludeies\nche vus cludîes", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas claudien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu cludevi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti cludevies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella cludevi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus cludevien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus cludevies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas cludevien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu cludess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti cludesses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella cludess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus cludessen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus cludesses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas cludessen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu cludessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti cludessies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella cludessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus cludessien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus cludessies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas cludessien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a clauder", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a clauder", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a clauder", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a clauder", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a clauder", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a clauder", forms.getVerb().getFutur().getPlural3());

        // particip is irregular for 'claus'; 'claudiu' is not correct. But the regular pattern would be like this,
        // thus testing for a wrong verb form.
        assertEquals("cludiu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("cludius", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("cludida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("cludi", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("cludidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("clauda!", forms.getVerb().getImperativ().getSingular());
        assertEquals("cludei!\ncludi!", forms.getVerb().getImperativ().getPlural());

        assertEquals("cludend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3d() {
        forms =  generator.generateConjugation("3d", "reiver");

        assertEquals("reiver", forms.getVerb().getInfinitiv());

        assertEquals("jeu reivel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti reivas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella reiva", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus revein\nnus revin", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus reveis\nvus revis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas reivan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu revevel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti revevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella reveva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus revevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus revevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas revevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu reivi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti reivies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella reivi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus reivien\nche nus reveien\nche nus revîen", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus reivies\nche vus reveies\nche vus revîes", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas reivien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu revevi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti revevies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella revevi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus revevien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus revevies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas revevien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu revess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti revesses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella revess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus revessen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus revesses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas revessen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu revessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti revessies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella revessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus revessien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus revessies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas revessien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a reiver", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a reiver", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a reiver", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a reiver", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a reiver", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a reiver", forms.getVerb().getFutur().getPlural3());

        assertEquals("reviu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("revius", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("revida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("revi", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("revidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("reiva!", forms.getVerb().getImperativ().getSingular());
        assertEquals("revei!\nrevi!", forms.getVerb().getImperativ().getPlural());

        assertEquals("revend", forms.getVerb().getGerundium());
    }

    @Test
    @Disabled
    public void testRule3e() {
        forms =  generator.generateConjugation("3e", "laguoter");

        assertEquals("laguoter", forms.getVerb().getInfinitiv());

        assertEquals("jeu laguotel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti laguotas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella laguota", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus laguttin", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus laguttis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas laguotan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu laguttevel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti laguttevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella lagutteva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus laguttevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus laguttevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas laguttevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu laguoti", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti laguoties", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella laguoti", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus laguotien\nche nus laguttîen", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus laguoties\nche vus laguttîes", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas laguotien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu laguttevi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti laguttevies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella laguttevi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus laguttevien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus laguttevies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas laguttevien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu laguttess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti laguttesses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella laguttess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus laguttessen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus laguttesses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas laguttessen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu laguttessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti laguttessies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella laguttessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus laguttessien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus laguttessies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas laguttessien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a laguoter", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a laguoter", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a laguoter", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a laguoter", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a laguoter", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a laguoter", forms.getVerb().getFutur().getPlural3());

        assertEquals("laguttiu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("laguttius", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("laguttida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("lagutti", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("laguttidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("laguota!", forms.getVerb().getImperativ().getSingular());
        assertEquals("lagutti!", forms.getVerb().getImperativ().getPlural());

        assertEquals("laguttend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule3f() {
        forms =  generator.generateConjugation("3f", "piarder");

        assertEquals("piarder", forms.getVerb().getInfinitiv());

        assertEquals("jeu piardel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti piardas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella piarda", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus perdein\nnus perdin", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus perdeis\nvus perdis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas piardan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu perdevel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti perdevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella perdeva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus perdevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus perdevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas perdevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu piardi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti piardies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella piardi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus piardien\nche nus perdeien\nche nus perdîen", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus piardies\nche vus perdeies\nche vus perdîes", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas piardien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu perdevi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti perdevies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella perdevi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus perdevien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus perdevies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas perdevien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu perdess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti perdesses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella perdess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus perdessen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus perdesses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas perdessen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu perdessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti perdessies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella perdessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus perdessien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus perdessies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas perdessien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a piarder", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a piarder", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a piarder", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a piarder", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a piarder", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a piarder", forms.getVerb().getFutur().getPlural3());

        assertEquals("perdiu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("perdius", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("perdida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("perdi", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("perdidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("piarda!", forms.getVerb().getImperativ().getSingular());
        assertEquals("perdei!\nperdi!", forms.getVerb().getImperativ().getPlural());

        assertEquals("perdend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4() {
        forms =  generator.generateConjugation("4", "sentir");

        assertEquals("sentir", forms.getVerb().getInfinitiv());

        assertEquals("jeu sentel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti sentas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella senta", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus sentin", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus sentis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas sentan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu sentevel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti sentevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella senteva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus sentevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus sentevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas sentevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu senti", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti senties", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella senti", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus sentîen", forms.getVerb().getConjunctiv().getPlural1()); // DIFF
        assertEquals("che vus sentîes", forms.getVerb().getConjunctiv().getPlural2()); // DIFF
        assertEquals("ch'els/ellas sentien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu sentevi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti sentevies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella sentevi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus sentevien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus sentevies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas sentevien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu sentess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti sentesses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella sentess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus sentessen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus sentesses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas sentessen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu sentessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti sentessies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella sentessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus sentessien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus sentessies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas sentessien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a sentir", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a sentir", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a sentir", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a sentir", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a sentir", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a sentir", forms.getVerb().getFutur().getPlural3());

        assertEquals("sentiu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("sentius", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("sentida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("senti", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("sentidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("senta!", forms.getVerb().getImperativ().getSingular());
        assertEquals("senti!", forms.getVerb().getImperativ().getPlural());

        assertEquals("sentend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4a() {
        forms =  generator.generateConjugation("4a", "durmir");

        assertEquals("durmir", forms.getVerb().getInfinitiv());

        assertEquals("jeu dormel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti dormas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella dorma", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus durmin", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus durmis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas dorman", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu durmevel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti durmevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella durmeva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus durmevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus durmevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas durmevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu dormi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti dormies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella dormi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus dormien\nche nus durmîen", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus dormies\nche vus durmîes", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas dormien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu durmevi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti durmevies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella durmevi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus durmevien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus durmevies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas durmevien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu durmess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti durmesses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella durmess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus durmessen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus durmesses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas durmessen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu durmessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti durmessies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella durmessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus durmessien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus durmessies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas durmessien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a durmir", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a durmir", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a durmir", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a durmir", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a durmir", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a durmir", forms.getVerb().getFutur().getPlural3());

        assertEquals("durmiu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("durmius", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("durmida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("durmi", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("durmidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("dorma!", forms.getVerb().getImperativ().getSingular());
        assertEquals("durmi!", forms.getVerb().getImperativ().getPlural());

        assertEquals("durmend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4b() {
        forms =  generator.generateConjugation("4b", "durmir");

        assertEquals("durmir", forms.getVerb().getInfinitiv());

        assertEquals("jeu diermel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti diermas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella dierma", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus durmin", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus durmis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas dierman", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu durmevel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti durmevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella durmeva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus durmevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus durmevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas durmevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu diermi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti diermies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella diermi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus diermien\nche nus durmîen", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus diermies\nche vus durmîes", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas diermien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu durmevi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti durmevies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella durmevi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus durmevien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus durmevies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas durmevien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu durmess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti durmesses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella durmess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus durmessen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus durmesses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas durmessen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu durmessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti durmessies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella durmessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus durmessien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus durmessies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas durmessien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a durmir", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a durmir", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a durmir", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a durmir", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a durmir", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a durmir", forms.getVerb().getFutur().getPlural3());

        assertEquals("durmiu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("durmius", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("durmida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("durmi", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("durmidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("dierma!", forms.getVerb().getImperativ().getSingular());
        assertEquals("durmi!", forms.getVerb().getImperativ().getPlural());

        assertEquals("durmend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4c() {
        forms =  generator.generateConjugation("4c", "burlir");

        assertEquals("burlir", forms.getVerb().getInfinitiv());

        assertEquals("jeu buorlel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti buorlas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella buorla", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus burlin", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus burlis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas buorlan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu burlevel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti burlevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella burleva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus burlevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus burlevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas burlevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu buorli", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti buorlies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella buorli", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus buorlien\nche nus burlîen", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus buorlies\nche vus burlîes", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas buorlien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu burlevi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti burlevies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella burlevi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus burlevien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus burlevies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas burlevien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu burless", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti burlesses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella burless", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus burlessen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus burlesses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas burlessen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu burlessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti burlessies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella burlessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus burlessien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus burlessies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas burlessien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a burlir", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a burlir", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a burlir", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a burlir", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a burlir", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a burlir", forms.getVerb().getFutur().getPlural3());

        assertEquals("burliu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("burlius", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("burlida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("burli", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("burlidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("buorla!", forms.getVerb().getImperativ().getSingular());
        assertEquals("burli!", forms.getVerb().getImperativ().getPlural());

        assertEquals("burlend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4d() {
        forms =  generator.generateConjugation("4d", "burlir");

        assertEquals("burlir", forms.getVerb().getInfinitiv());

        assertEquals("jeu berlel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti berlas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella berla", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus burlin", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus burlis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas berlan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu burlevel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti burlevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella burleva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus burlevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus burlevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas burlevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu berli", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti berlies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella berli", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus berlien\nche nus burlîen", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus berlies\nche vus burlîes", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas berlien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu burlevi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti burlevies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella burlevi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus burlevien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus burlevies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas burlevien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu burless", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti burlesses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella burless", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus burlessen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus burlesses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas burlessen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu burlessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti burlessies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella burlessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus burlessien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus burlessies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas burlessien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a burlir", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a burlir", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a burlir", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a burlir", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a burlir", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a burlir", forms.getVerb().getFutur().getPlural3());

        assertEquals("burliu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("burlius", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("burlida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("burli", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("burlidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("berla!", forms.getVerb().getImperativ().getSingular());
        assertEquals("burli!", forms.getVerb().getImperativ().getPlural());

        assertEquals("burlend", forms.getVerb().getGerundium());
    }

    @Test
    @Disabled
    public void testRule4e() {
        forms =  generator.generateConjugation("4e", "embellir");

        assertEquals("embellir", forms.getVerb().getInfinitiv());

        assertEquals("jeu embialel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti embialas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella embiala", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus embellin", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus embellis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas embialan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu embellevel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti embellevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella embelleva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus embellevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus embellevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas embellevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu embiali", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti embialies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella embiali", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus embialien\nembelleschien\nembellîen", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus embialies \nembelleschies\nembellîes", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas embialien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu embellevi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti embellevies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella embellevi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus embellevien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus embellevies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas embellevien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu embelless", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti embellesses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella embelless", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus embellessen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus embellesses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas embellessen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu embellessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti embellessies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella embellessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus embellessien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus embellessies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas embellessien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a embellir", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a embellir", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a embellir", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a embellir", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a embellir", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a embellir", forms.getVerb().getFutur().getPlural3());

        assertEquals("embelliu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("embellius", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("embellida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("embelli", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("embellidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("embiala!\nembellescha!", forms.getVerb().getImperativ().getSingular());
        assertEquals("embelli!", forms.getVerb().getImperativ().getPlural());

        assertEquals("embellend", forms.getVerb().getGerundium());
    }

    @Test
    public void testRule4f() {
        forms =  generator.generateConjugation("4f", "bargir");

        assertEquals("bargir", forms.getVerb().getInfinitiv());

        assertEquals("jeu bragel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti bragias", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella bragia", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus bargin", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus bargis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas bragian", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu bargevel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti bargevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella bargeva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus bargevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus bargevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas bargevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu bragi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti bragies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella bragi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus bragien\nche nus bargîen", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus bragies\nche vus bargîes", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas bragien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu bargevi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti bargevies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella bargevi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus bargevien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus bargevies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas bargevien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu bargess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti bargesses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella bargess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus bargessen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus bargesses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas bargessen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu bargessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti bargessies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella bargessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus bargessien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus bargessies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas bargessien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a bargir", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a bargir", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a bargir", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a bargir", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a bargir", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a bargir", forms.getVerb().getFutur().getPlural3());

        assertEquals("bargiu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("bargius", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("bargida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("bargi", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("bargidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("bragia!", forms.getVerb().getImperativ().getSingular());
        assertEquals("bargi!", forms.getVerb().getImperativ().getPlural());

        assertEquals("bargend", forms.getVerb().getGerundium());
    }


    @Test
    public void testRule5() {
        forms =  generator.generateConjugation("5", "capir");

        assertEquals("capir", forms.getVerb().getInfinitiv());

        assertEquals("jeu capeschel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti capeschas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella capescha", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus capin", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus capis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas capeschan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu capevel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti capevas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella capeva", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus capevan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus capevas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas capevan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu capeschi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti capeschies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella capeschi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus capeschien\nche nus capîen", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus capeschies\nche vus capîes", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas capeschien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu capevi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti capevies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella capevi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus capevien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus capevies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas capevien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu capess", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti capesses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella capess", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus capessen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus capesses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas capessen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu capessi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti capessies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella capessi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus capessien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus capessies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas capessien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a capir", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a capir", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a capir", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a capir", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a capir", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a capir", forms.getVerb().getFutur().getPlural3());

        assertEquals("capiu", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("capius", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("capida", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("capi", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("capidas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("capescha!", forms.getVerb().getImperativ().getSingular());
        assertEquals("capi!", forms.getVerb().getImperativ().getPlural());

        assertEquals("capend", forms.getVerb().getGerundium());
    }


    @Test
    public void testRule6() {
        forms =  generator.generateConjugation("6", "gratular");

        assertEquals("gratular", forms.getVerb().getInfinitiv());

        assertEquals("jeu gratuleschel", forms.getVerb().getPreschent().getSing1());
        assertEquals("ti gratuleschas", forms.getVerb().getPreschent().getSing2());
        assertEquals("el/ella gratulescha", forms.getVerb().getPreschent().getSing3());
        assertEquals("nus gratulein", forms.getVerb().getPreschent().getPlural1());
        assertEquals("vus gratuleis", forms.getVerb().getPreschent().getPlural2());
        assertEquals("els/ellas gratuleschan", forms.getVerb().getPreschent().getPlural3());

        assertEquals("jeu gratulavel", forms.getVerb().getImperfect().getSing1());
        assertEquals("ti gratulavas", forms.getVerb().getImperfect().getSing2());
        assertEquals("el/ella gratulava", forms.getVerb().getImperfect().getSing3());
        assertEquals("nus gratulavan", forms.getVerb().getImperfect().getPlural1());
        assertEquals("vus gratulavas", forms.getVerb().getImperfect().getPlural2());
        assertEquals("els/ellas gratulavan", forms.getVerb().getImperfect().getPlural3());

        assertEquals("che jeu gratuleschi", forms.getVerb().getConjunctiv().getSing1());
        assertEquals("che ti gratuleschies", forms.getVerb().getConjunctiv().getSing2());
        assertEquals("ch'el/ella gratuleschi", forms.getVerb().getConjunctiv().getSing3());
        assertEquals("che nus gratuleschien\nche nus gratuleien", forms.getVerb().getConjunctiv().getPlural1());
        assertEquals("che vus gratuleschies\nche vus gratuleies", forms.getVerb().getConjunctiv().getPlural2());
        assertEquals("ch'els/ellas gratuleschien", forms.getVerb().getConjunctiv().getPlural3());

        assertEquals("che jeu gratulavi", forms.getVerb().getConjunctiv2().getSing1());
        assertEquals("che ti gratulavies", forms.getVerb().getConjunctiv2().getSing2());
        assertEquals("ch'el/ella gratulavi", forms.getVerb().getConjunctiv2().getSing3());
        assertEquals("che nus gratulavien", forms.getVerb().getConjunctiv2().getPlural1());
        assertEquals("che vus gratulavies", forms.getVerb().getConjunctiv2().getPlural2());
        assertEquals("ch'els/ellas gratulavien", forms.getVerb().getConjunctiv2().getPlural3());

        assertEquals("jeu gratulass", forms.getVerb().getCundiziunal().getSing1());
        assertEquals("ti gratulasses", forms.getVerb().getCundiziunal().getSing2());
        assertEquals("el/ella gratulass", forms.getVerb().getCundiziunal().getSing3());
        assertEquals("nus gratulassen", forms.getVerb().getCundiziunal().getPlural1());
        assertEquals("vus gratulasses", forms.getVerb().getCundiziunal().getPlural2());
        assertEquals("els/ellas gratulassen", forms.getVerb().getCundiziunal().getPlural3());

        assertEquals("jeu gratulassi", forms.getVerb().getCundiziunalIndirect().getSing1());
        assertEquals("ti gratulassies", forms.getVerb().getCundiziunalIndirect().getSing2());
        assertEquals("el/ella gratulassi", forms.getVerb().getCundiziunalIndirect().getSing3());
        assertEquals("nus gratulassien", forms.getVerb().getCundiziunalIndirect().getPlural1());
        assertEquals("vus gratulassies", forms.getVerb().getCundiziunalIndirect().getPlural2());
        assertEquals("els/ellas gratulassien", forms.getVerb().getCundiziunalIndirect().getPlural3());

        assertEquals("jeu vegn(el) a gratular", forms.getVerb().getFutur().getSing1());
        assertEquals("ti vegns a gratular", forms.getVerb().getFutur().getSing2());
        assertEquals("el/ella vegn a gratular", forms.getVerb().getFutur().getSing3());
        assertEquals("nus vegnin a gratular", forms.getVerb().getFutur().getPlural1());
        assertEquals("vus vegnis a gratular", forms.getVerb().getFutur().getPlural2());
        assertEquals("els/ellas vegnan a gratular", forms.getVerb().getFutur().getPlural3());

        assertEquals("gratulau", forms.getVerb().getParticipPerfect().getMs());
        assertEquals("gratulaus", forms.getVerb().getParticipPerfect().getMsPredicativ());
        assertEquals("gratulada", forms.getVerb().getParticipPerfect().getFs());
        assertEquals("gratulai", forms.getVerb().getParticipPerfect().getMp());
        assertEquals("gratuladas", forms.getVerb().getParticipPerfect().getFp());

        assertEquals("gratulescha!", forms.getVerb().getImperativ().getSingular());
        assertEquals("gratulei!", forms.getVerb().getImperativ().getPlural());

        assertEquals("gratulond", forms.getVerb().getGerundium());
    }
}
