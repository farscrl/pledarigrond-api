package ch.pledarigrond.inflection.generation.sursilvan;

import ch.pledarigrond.inflection.model.InflectionResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class SursilvanConjugationTest {

    private static SursilvanConjugation generator;
    private InflectionResponse forms;

    @BeforeClass
    public static void initialize() {
        generator = new SursilvanConjugation();
    }

    @Test
    public void testRule1() {
        forms =  generator.generateConjugation("1", "gidar");

        Assert.assertEquals("gidar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu gidel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti gidas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella gida", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus gidein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus gideis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas gidan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu gidavel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti gidavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella gidava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus gidavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus gidavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas gidavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu gidi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti gidies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella gidi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus gidien\nche nus gideien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus gidies\nche vus gideies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas gidien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu gidavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti gidavies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella gidavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus gidavien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus gidavies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas gidavien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu gidass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti gidasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella gidass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus gidassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus gidasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas gidassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu gidassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti gidassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella gidassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus gidassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus gidassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas gidassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a gidar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a gidar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a gidar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a gidar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a gidar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a gidar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("gidau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("gidaus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("gidada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("gidai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("gidadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("gida!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("gidei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("gidond", forms.getInflectionValues().get("gerundium"));
    }


    @Test
    public void testRule1a() {
        forms =  generator.generateConjugation("1a", "clamar");

        Assert.assertEquals("clamar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu clomel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti clomas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella cloma", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus clamein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus clameis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas cloman", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu clamavel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti clamavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella clamava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus clamavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus clamavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas clamavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu clomi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti clomies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella clomi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus clomien\nche nus clameien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus clomies\nche vus clameies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas clomien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu clamavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti clamavies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella clamavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus clamavien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus clamavies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas clamavien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu clamass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti clamasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella clamass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus clamassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus clamasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas clamassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu clamassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti clamassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella clamassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus clamassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus clamassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas clamassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a clamar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a clamar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a clamar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a clamar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a clamar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a clamar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("clamau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("clamaus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("clamada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("clamai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("clamadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("cloma!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("clamei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("clamond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1b() {
        forms =  generator.generateConjugation("1b", "tschintschar");

        Assert.assertEquals("tschintschar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu tschontschel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti tschontschas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella tschontscha", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus tschintschein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus tschintscheis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas tschontschan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu tschintschavel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti tschintschavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella tschintschava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus tschintschavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus tschintschavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas tschintschavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu tschontschi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti tschontschies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella tschontschi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus tschontschien\nche nus tschintscheien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus tschontschies\nche vus tschintscheies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas tschontschien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu tschintschavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti tschintschavies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella tschintschavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus tschintschavien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus tschintschavies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas tschintschavien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu tschintschass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti tschintschasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella tschintschass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus tschintschassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus tschintschasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas tschintschassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu tschintschassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti tschintschassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella tschintschassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus tschintschassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus tschintschassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas tschintschassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a tschintschar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a tschintschar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a tschintschar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a tschintschar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a tschintschar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a tschintschar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("tschintschau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("tschintschaus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("tschintschada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("tschintschai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("tschintschadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("tschontscha!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("tschintschei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("tschintschond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1c() {
        forms =  generator.generateConjugation("1c", "purtar");

        Assert.assertEquals("purtar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu portel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti portas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella porta", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus purtein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus purteis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas portan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu purtavel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti purtavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella purtava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus purtavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus purtavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas purtavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu porti", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti porties", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella porti", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus portien\nche nus purteien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus porties\nche vus purteies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas portien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu purtavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti purtavies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella purtavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus purtavien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus purtavies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas purtavien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu purtass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti purtasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella purtass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus purtassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus purtasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas purtassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu purtassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti purtassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella purtassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus purtassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus purtassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas purtassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a purtar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a purtar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a purtar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a purtar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a purtar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a purtar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("purtau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("purtaus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("purtada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("purtai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("purtadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("porta!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("purtei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("purtond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1d() {
        forms =  generator.generateConjugation("1d", "formar");

        Assert.assertEquals("formar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu fuormel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti fuormas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella fuorma", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus formein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus formeis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas fuorman", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu formavel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti formavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella formava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus formavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus formavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas formavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu fuormi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti fuormies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella fuormi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus fuormien\nche nus formeien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus fuormies\nche vus formeies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas fuormien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu formavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti formavies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella formavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus formavien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus formavies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas formavien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu formass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti formasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella formass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus formassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus formasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas formassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu formassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti formassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella formassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus formassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus formassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas formassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a formar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a formar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a formar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a formar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a formar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a formar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("formau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("formaus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("formada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("formai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("formadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("fuorma!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("formei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("formond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1e() {
        forms =  generator.generateConjugation("1e", "mussar");

        Assert.assertEquals("mussar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu muossel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti muossas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella muossa", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus mussein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus musseis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas muossan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu mussavel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti mussavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella mussava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus mussavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus mussavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas mussavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu muossi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti muossies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella muossi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus muossien\nche nus musseien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus muossies\nche vus musseies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas muossien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu mussavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti mussavies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella mussavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus mussavien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus mussavies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas mussavien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu mussass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti mussasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella mussass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus mussassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus mussasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas mussassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu mussassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti mussassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella mussassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus mussassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus mussassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas mussassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a mussar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a mussar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a mussar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a mussar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a mussar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a mussar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("mussau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("mussaus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("mussada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("mussai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("mussadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("muossa!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("mussei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("mussond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1f() {
        forms =  generator.generateConjugation("1f", "zavrar");

        Assert.assertEquals("zavrar", forms.getInflectionValues().get("infinitiv"));

        // zavrar is a special case, as it has also an alternative form jeu zavrel, but only the pattern form is tested here
        Assert.assertEquals("jeu zeivrel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti zeivras", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella zeivra", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus zavrein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus zavreis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas zeivran", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu zavravel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti zavravas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella zavrava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus zavravan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus zavravas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas zavravan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu zeivri", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti zeivries", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella zeivri", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus zeivrien\nche nus zavreien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus zeivries\nche vus zavreies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas zeivrien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu zavravi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti zavravies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella zavravi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus zavravien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus zavravies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas zavravien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu zavrass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti zavrasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella zavrass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus zavrassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus zavrasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas zavrassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu zavrassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti zavrassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella zavrassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus zavrassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus zavrassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas zavrassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a zavrar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a zavrar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a zavrar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a zavrar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a zavrar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a zavrar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("zavrau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("zavraus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("zavrada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("zavrai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("zavradas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("zeivra!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("zavrei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("zavrond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1g() {
        forms =  generator.generateConjugation("1g", "tedlar");

        Assert.assertEquals("tedlar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu teidlel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti teidlas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella teidla", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus tedlein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus tedleis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas teidlan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu tedlavel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti tedlavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella tedlava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus tedlavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus tedlavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas tedlavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu teidli", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti teidlies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella teidli", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus teidlien\nche nus tedleien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus teidlies\nche vus tedleies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas teidlien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu tedlavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti tedlavies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella tedlavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus tedlavien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus tedlavies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas tedlavien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu tedlass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti tedlasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella tedlass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus tedlassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus tedlasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas tedlassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu tedlassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti tedlassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella tedlassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus tedlassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus tedlassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas tedlassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a tedlar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a tedlar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a tedlar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a tedlar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a tedlar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a tedlar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("tedlau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("tedlaus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("tedlada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("tedlai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("tedladas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("teidla!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("tedlei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("tedlond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1h() {
        forms =  generator.generateConjugation("1h", "pinar");

        Assert.assertEquals("pinar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu peinel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti peinas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella peina", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus pinein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus pineis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas peinan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu pinavel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti pinavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella pinava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus pinavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus pinavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas pinavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu peini", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti peinies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella peini", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus peinien\nche nus pineien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus peinies\nche vus pineies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas peinien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu pinavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti pinavies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella pinavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus pinavien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus pinavies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas pinavien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu pinass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti pinasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella pinass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus pinassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus pinasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas pinassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu pinassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti pinassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella pinassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus pinassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus pinassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas pinassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a pinar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a pinar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a pinar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a pinar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a pinar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a pinar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("pinau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("pinaus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("pinada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("pinai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("pinadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("peina!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("pinei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("pinond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1i() {
        forms =  generator.generateConjugation("1i", "ludar");

        Assert.assertEquals("ludar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu laudel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti laudas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella lauda", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus ludein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus ludeis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas laudan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu ludavel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti ludavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella ludava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus ludavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus ludavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas ludavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu laudi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti laudies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella laudi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus laudien\nche nus ludeien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus laudies\nche vus ludeies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas laudien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu ludavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti ludavies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella ludavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus ludavien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus ludavies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas ludavien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu ludass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti ludasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella ludass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus ludassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus ludasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas ludassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu ludassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti ludassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella ludassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus ludassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus ludassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas ludassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a ludar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a ludar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a ludar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a ludar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a ludar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a ludar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("ludau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("ludaus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("ludada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("ludai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("ludadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("lauda!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("ludei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("ludond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1j() {
        forms =  generator.generateConjugation("1j", "filtschar");

        Assert.assertEquals("filtschar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu faultschel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti faultschas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella faultscha", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus filtschein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus filtscheis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas faultschan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu filtschavel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti filtschavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella filtschava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus filtschavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus filtschavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas filtschavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu faultschi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti faultschies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella faultschi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus faultschien\nche nus filtscheien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus faultschies\nche vus filtscheies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas faultschien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu filtschavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti filtschavies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella filtschavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus filtschavien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus filtschavies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas filtschavien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu filtschass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti filtschasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella filtschass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus filtschassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus filtschasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas filtschassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu filtschassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti filtschassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella filtschassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus filtschassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus filtschassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas filtschassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a filtschar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a filtschar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a filtschar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a filtschar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a filtschar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a filtschar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("filtschau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("filtschaus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("filtschada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("filtschai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("filtschadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("faultscha!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("filtschei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("filtschond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1k() {
        forms =  generator.generateConjugation("1k", "alzar");

        Assert.assertEquals("alzar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu aulzel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti aulzas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella aulza", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus alzein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus alzeis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas aulzan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu alzavel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti alzavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella alzava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus alzavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus alzavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas alzavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu aulzi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti aulzies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella aulzi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus aulzien\nche nus alzeien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus aulzies\nche vus alzeies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas aulzien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu alzavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti alzavies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella alzavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus alzavien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus alzavies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas alzavien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu alzass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti alzasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella alzass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus alzassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus alzasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas alzassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu alzassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti alzassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella alzassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus alzassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus alzassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas alzassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) ad alzar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns ad alzar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn ad alzar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin ad alzar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis ad alzar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan ad alzar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("alzau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("alzaus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("alzada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("alzai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("alzadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("aulza!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("alzei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("alzond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1l() {
        forms =  generator.generateConjugation("1l", "emparar");

        Assert.assertEquals("emparar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu empiarel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti empiaras", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella empiara", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus emparein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus empareis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas empiaran", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu emparavel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti emparavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella emparava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus emparavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus emparavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas emparavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu empiari", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti empiaries", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella empiari", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus empiarien\nche nus empareien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus empiaries\nche vus empareies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas empiarien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu emparavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti emparavies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella emparavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus emparavien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus emparavies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas emparavien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu emparass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti emparasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella emparass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus emparassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus emparasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas emparassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu emparassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti emparassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella emparassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus emparassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus emparassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas emparassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) ad emparar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns ad emparar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn ad emparar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin ad emparar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis ad emparar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan ad emparar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("emparau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("emparaus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("emparada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("emparai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("emparadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("empiara!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("emparei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("emparond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1m() {
        forms =  generator.generateConjugation("1m", "serrar");

        Assert.assertEquals("serrar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu siarel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti siaras", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella siara", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus serrein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus serreis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas siaran", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu serravel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti serravas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella serrava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus serravan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus serravas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas serravan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu siari", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti siaries", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella siari", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus siarien\nche nus serreien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus siaries\nche vus serreies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas siarien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu serravi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti serravies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella serravi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus serravien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus serravies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas serravien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu serrass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti serrasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella serrass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus serrassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus serrasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas serrassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu serrassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti serrassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella serrassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus serrassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus serrassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas serrassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a serrar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a serrar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a serrar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a serrar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a serrar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a serrar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("serrau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("serraus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("serrada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("serrai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("serradas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("siara!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("serrei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("serrond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1n() {
        forms =  generator.generateConjugation("1n", "zanistrar");

        Assert.assertEquals("zanistrar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu zaniastrel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti zaniastras", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella zaniastra", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus zanistrein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus zanistreis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas zaniastran", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu zanistravel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti zanistravas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella zanistrava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus zanistravan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus zanistravas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas zanistravan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu zaniastri", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti zaniastries", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella zaniastri", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus zaniastrien\nche nus zanistreien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus zaniastries\nche vus zanistreies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas zaniastrien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu zanistravi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti zanistravies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella zanistravi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus zanistravien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus zanistravies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas zanistravien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu zanistrass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti zanistrasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella zanistrass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus zanistrassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus zanistrasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas zanistrassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu zanistrassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti zanistrassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella zanistrassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus zanistrassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus zanistrassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas zanistrassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a zanistrar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a zanistrar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a zanistrar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a zanistrar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a zanistrar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a zanistrar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("zanistrau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("zanistraus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("zanistrada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("zanistrai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("zanistradas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("zaniastra!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("zanistrei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("zanistrond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1o() {
        forms =  generator.generateConjugation("1o", "ugliar");

        Assert.assertEquals("ugliar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu uegliel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti ueglias", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella ueglia", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus ugliein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus uglieis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas ueglian", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu ugliavel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti ugliavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella ugliava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus ugliavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus ugliavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas ugliavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu uegli", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti ueglies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella uegli", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus ueglien\nche nus uglieien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus ueglies\nche vus uglieies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas ueglien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu ugliavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti ugliavies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella ugliavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus ugliavien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus ugliavies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas ugliavien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu ugliass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti ugliasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella ugliass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus ugliassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus ugliasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas ugliassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu ugliassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti ugliassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella ugliassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus ugliassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus ugliassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas ugliassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) ad ugliar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns ad ugliar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn ad ugliar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin ad ugliar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis ad ugliar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan ad ugliar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("ugliau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("ugliaus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("ugliada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("ugliai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("ugliadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("ueglia!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("ugliei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("ugliond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1p() {
        forms =  generator.generateConjugation("1p", "spitgar");

        Assert.assertEquals("spitgar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu spetgel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti spetgas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella spetga", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus spitgein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus spitgeis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas spetgan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu spitgavel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti spitgavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella spitgava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus spitgavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus spitgavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas spitgavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu spetgi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti spetgies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella spetgi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus spetgien\nche nus spitgeien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus spetgies\nche vus spitgeies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas spetgien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu spitgavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti spitgavies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella spitgavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus spitgavien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus spitgavies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas spitgavien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu spitgass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti spitgasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella spitgass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus spitgassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus spitgasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas spitgassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu spitgassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti spitgassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella spitgassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus spitgassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus spitgassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas spitgassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a spitgar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a spitgar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a spitgar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a spitgar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a spitgar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a spitgar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("spitgau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("spitgaus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("spitgada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("spitgai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("spitgadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("spetga!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("spitgei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("spitgond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1q() {
        forms =  generator.generateConjugation("1q", "semiar");

        Assert.assertEquals("semiar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu siemiel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti siemias", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella siemia", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus semiein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus semieis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas siemian", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu semiavel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti semiavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella semiava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus semiavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus semiavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas semiavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu siemi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti siemies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella siemi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus siemien\nche nus semieien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus siemies\nche vus semieies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas siemien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu semiavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti semiavies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella semiavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus semiavien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus semiavies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas semiavien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu semiass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti semiasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella semiass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus semiassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus semiasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas semiassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu semiassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti semiassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella semiassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus semiassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus semiassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas semiassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a semiar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a semiar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a semiar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a semiar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a semiar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a semiar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("semiau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("semiaus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("semiada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("semiai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("semiadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("siemia!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("semiei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("semiond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1r() {
        forms =  generator.generateConjugation("1r", "rischlar");

        Assert.assertEquals("rischlar", forms.getInflectionValues().get("infinitiv"));

        // there is also the irregular form 'jeu reschlel', but the test tests only the regular pattern
        Assert.assertEquals("jeu raschlel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti raschlas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella raschla", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus rischlein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus rischleis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas raschlan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu rischlavel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti rischlavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella rischlava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus rischlavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus rischlavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas rischlavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu raschli", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti raschlies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella raschli", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus raschlien\nche nus rischleien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus raschlies\nche vus rischleies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas raschlien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu rischlavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti rischlavies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella rischlavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus rischlavien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus rischlavies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas rischlavien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu rischlass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti rischlasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella rischlass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus rischlassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus rischlasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas rischlassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu rischlassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti rischlassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella rischlassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus rischlassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus rischlassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas rischlassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a rischlar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a rischlar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a rischlar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a rischlar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a rischlar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a rischlar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("rischlau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("rischlaus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("rischlada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("rischlai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("rischladas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("raschla!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("rischlei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("rischlond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1s() {
        forms =  generator.generateConjugation("1s", "sbuserar");

        Assert.assertEquals("sbuserar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu sbusirel\njeu sbusarel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti sbusiras\nti sbusaras", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella sbusira\nel/ella sbusara", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus sbuserein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus sbusereis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas sbusiran\nels/ellas sbusaran", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu sbuseravel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti sbuseravas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella sbuserava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus sbuseravan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus sbuseravas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas sbuseravan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu sbusiri\nche jeu sbusari", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti sbusiries\nche ti sbusaries", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella sbusiri\nch'el/ella sbusari", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus sbusirien\nche nus sbusarien\nche nus sbusereien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus sbusiries\nche vus sbusaries\nche vus sbusereies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas sbusirien\nch'els/ellas sbusarien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu sbuseravi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti sbuseravies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella sbuseravi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus sbuseravien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus sbuseravies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas sbuseravien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu sbuserass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti sbuserasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella sbuserass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus sbuserassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus sbuserasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas sbuserassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu sbuserassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti sbuserassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella sbuserassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus sbuserassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus sbuserassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas sbuserassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a sbuserar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a sbuserar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a sbuserar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a sbuserar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a sbuserar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a sbuserar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("sbuserau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("sbuseraus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("sbuserada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("sbuserai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("sbuseradas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("sbusira!\nsbusara!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("sbuserei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("sbuserond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1t() {
        forms =  generator.generateConjugation("1t", "smarschanar");

        Assert.assertEquals("smarschanar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu smarschunel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti smarschunas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella smarschuna", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus smarschanein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus smarschaneis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas smarschunan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu smarschanavel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti smarschanavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella smarschanava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus smarschanavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus smarschanavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas smarschanavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu smarschuni", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti smarschunies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella smarschuni", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus smarschunien\nche nus smarschaneien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus smarschunies\nche vus smarschaneies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas smarschunien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu smarschanavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti smarschanavies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella smarschanavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus smarschanavien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus smarschanavies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas smarschanavien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu smarschanass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti smarschanasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella smarschanass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus smarschanassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus smarschanasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas smarschanassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu smarschanassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti smarschanassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella smarschanassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus smarschanassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus smarschanassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas smarschanassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a smarschanar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a smarschanar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a smarschanar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a smarschanar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a smarschanar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a smarschanar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("smarschanau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("smarschanaus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("smarschanada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("smarschanai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("smarschanadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("smarschuna!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("smarschanei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("smarschanond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1u() {
        forms =  generator.generateConjugation("1u", "cuglienar");

        Assert.assertEquals("cuglienar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu cugliunel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti cugliunas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella cugliuna", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus cuglienein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus cuglieneis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas cugliunan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu cuglienavel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti cuglienavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella cuglienava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus cuglienavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus cuglienavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas cuglienavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu cugliuni", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti cugliunies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella cugliuni", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus cugliunien\nche nus cuglieneien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus cugliunies\nche vus cuglieneies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas cugliunien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu cuglienavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti cuglienavies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella cuglienavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus cuglienavien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus cuglienavies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas cuglienavien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu cuglienass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti cuglienasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella cuglienass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus cuglienassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus cuglienasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas cuglienassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu cuglienassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti cuglienassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella cuglienassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus cuglienassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus cuglienassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas cuglienassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a cuglienar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a cuglienar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a cuglienar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a cuglienar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a cuglienar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a cuglienar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("cuglienau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("cuglienaus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("cuglienada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("cuglienai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("cuglienadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("cugliuna!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("cuglienei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("cuglienond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1v() {
        forms =  generator.generateConjugation("1v", "curclar");

        Assert.assertEquals("curclar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu cuarclel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti cuarclas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella cuarcla", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus curclein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus curcleis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas cuarclan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu curclavel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti curclavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella curclava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus curclavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus curclavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas curclavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu cuarcli", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti cuarclies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella cuarcli", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus cuarclien\nche nus curcleien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus cuarclies\nche vus curcleies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas cuarclien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu curclavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti curclavies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella curclavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus curclavien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus curclavies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas curclavien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu curclass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti curclasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella curclass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus curclassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus curclasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas curclassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu curclassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti curclassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella curclassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus curclassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus curclassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas curclassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a curclar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a curclar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a curclar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a curclar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a curclar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a curclar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("curclau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("curclaus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("curclada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("curclai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("curcladas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("cuarcla!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("curclei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("curclond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1w() {
        forms =  generator.generateConjugation("1w", "suar");

        Assert.assertEquals("suar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu siuel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti siuas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella siua", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus suein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus sueis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas siuan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu suavel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti suavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella suava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus suavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus suavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas suavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu siui", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti siuies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella siui", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus siuien\nche nus sueien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus siuies\nche vus sueies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas siuien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu suavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti suavies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella suavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus suavien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus suavies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas suavien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu suass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti suasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella suass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus suassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus suasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas suassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu suassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti suassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella suassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus suassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus suassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas suassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a suar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a suar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a suar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a suar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a suar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a suar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("suau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("suaus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("suada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("suai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("suadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("siua!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("suei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("suond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1x() {
        forms =  generator.generateConjugation("1x", "cumandar");

        Assert.assertEquals("cumandar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu camondel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti camondas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella camonda", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus cumandein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus cumandeis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas camondan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu cumandavel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti cumandavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella cumandava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus cumandavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus cumandavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas cumandavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu camondi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti camondies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella camondi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus camondien\nche nus cumandeien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus camondies\nche vus cumandeies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas camondien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu cumandavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti cumandavies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella cumandavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus cumandavien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus cumandavies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas cumandavien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu cumandass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti cumandasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella cumandass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus cumandassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus cumandasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas cumandassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu cumandassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti cumandassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella cumandassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus cumandassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus cumandassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas cumandassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a cumandar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a cumandar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a cumandar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a cumandar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a cumandar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a cumandar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("cumandau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("cumandaus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("cumandada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("cumandai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("cumandadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("camonda!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("cumandei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("cumandond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1y() {
        forms =  generator.generateConjugation("1y", "dumignar");

        Assert.assertEquals("dumignar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu damognel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti damognas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella damogna", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus dumignein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus dumigneis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas damognan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu dumignavel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti dumignavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella dumignava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus dumignavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus dumignavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas dumignavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu damogni", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti damognies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella damogni", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus damognien\nche nus dumigneien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus damognies\nche vus dumigneies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas damognien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu dumignavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti dumignavies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella dumignavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus dumignavien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus dumignavies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas dumignavien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu dumignass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti dumignasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella dumignass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus dumignassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus dumignasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas dumignassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu dumignassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti dumignassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella dumignassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus dumignassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus dumignassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas dumignassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a dumignar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a dumignar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a dumignar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a dumignar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a dumignar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a dumignar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("dumignau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("dumignaus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("dumignada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("dumignai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("dumignadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("damogna!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("dumignei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("dumignond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1z() {
        forms =  generator.generateConjugation("1z", "rumplanar");

        Assert.assertEquals("rumplanar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu ramplunel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti ramplunas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella rampluna", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus rumplanein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus rumplaneis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas ramplunan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu rumplanavel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti rumplanavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella rumplanava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus rumplanavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus rumplanavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas rumplanavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu rampluni", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti ramplunies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella rampluni", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus ramplunien\nche nus rumplaneien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus ramplunies\nche vus rumplaneies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas ramplunien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu rumplanavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti rumplanavies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella rumplanavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus rumplanavien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus rumplanavies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas rumplanavien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu rumplanass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti rumplanasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella rumplanass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus rumplanassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus rumplanasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas rumplanassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu rumplanassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti rumplanassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella rumplanassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus rumplanassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus rumplanassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas rumplanassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a rumplanar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a rumplanar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a rumplanar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a rumplanar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a rumplanar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a rumplanar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("rumplanau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("rumplanaus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("rumplanada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("rumplanai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("rumplanadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("rampluna!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("rumplanei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("rumplanond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1aa() {
        forms =  generator.generateConjugation("1aa", "murmignar");

        Assert.assertEquals("murmignar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu marmugnel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti marmugnas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella marmugna", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus murmignein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus murmigneis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas marmugnan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu murmignavel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti murmignavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella murmignava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus murmignavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus murmignavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas murmignavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu marmugni", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti marmugnies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella marmugni", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus marmugnien\nche nus murmigneien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus marmugnies\nche vus murmigneies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas marmugnien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu murmignavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti murmignavies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella murmignavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus murmignavien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus murmignavies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas murmignavien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu murmignass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti murmignasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella murmignass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus murmignassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus murmignasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas murmignassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu murmignassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti murmignassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella murmignassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus murmignassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus murmignassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas murmignassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a murmignar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a murmignar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a murmignar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a murmignar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a murmignar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a murmignar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("murmignau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("murmignaus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("murmignada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("murmignai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("murmignadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("marmugna!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("murmignei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("murmignond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1ab() {
        forms =  generator.generateConjugation("1ab", "buntganar");

        Assert.assertEquals("buntganar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu bintgunel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti bintgunas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella bintguna", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus buntganein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus buntganeis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas bintgunan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu buntganavel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti buntganavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella buntganava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus buntganavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus buntganavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas buntganavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu bintguni", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti bintgunies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella bintguni", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus bintgunien\nche nus buntganeien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus bintgunies\nche vus buntganeies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas bintgunien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu buntganavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti buntganavies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella buntganavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus buntganavien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus buntganavies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas buntganavien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu buntganass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti buntganasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella buntganass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus buntganassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus buntganasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas buntganassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu buntganassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti buntganassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella buntganassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus buntganassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus buntganassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas buntganassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a buntganar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a buntganar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a buntganar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a buntganar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a buntganar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a buntganar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("buntganau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("buntganaus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("buntganada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("buntganai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("buntganadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("bintguna!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("buntganei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("buntganond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1ac() {
        forms =  generator.generateConjugation("1ac", "duvrar");

        Assert.assertEquals("duvrar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu drovel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti drovas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella drova", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus duvrein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus duvreis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas drovan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu duvravel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti duvravas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella duvrava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus duvravan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus duvravas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas duvravan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu drovi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti drovies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella drovi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus drovien\nche nus duvreien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus drovies\nche vus duvreies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas drovien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu duvravi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti duvravies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella duvravi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus duvravien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus duvravies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas duvravien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu duvrass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti duvrasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella duvrass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus duvrassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus duvrasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas duvrassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu duvrassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti duvrassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella duvrassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus duvrassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus duvrassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas duvrassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a duvrar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a duvrar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a duvrar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a duvrar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a duvrar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a duvrar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("duvrau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("duvraus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("duvrada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("duvrai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("duvradas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("drova!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("duvrei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("duvrond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1acAlternativ() {
        forms =  generator.generateConjugation("1ac", "curdar");

        Assert.assertEquals("curdar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu crodel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti crodas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella croda", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus curdein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus curdeis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas crodan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu curdavel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti curdavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella curdava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus curdavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus curdavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas curdavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu crodi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti crodies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella crodi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus crodien\nche nus curdeien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus crodies\nche vus curdeies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas crodien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu curdavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti curdavies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella curdavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus curdavien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus curdavies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas curdavien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu curdass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti curdasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella curdass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus curdassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus curdasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas curdassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu curdassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti curdassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella curdassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus curdassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus curdassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas curdassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a curdar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a curdar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a curdar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a curdar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a curdar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a curdar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("curdau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("curdaus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("curdada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("curdai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("curdadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("croda!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("curdei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("curdond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1ad() {
        forms =  generator.generateConjugation("1ad", "patertgar");

        Assert.assertEquals("patertgar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu patratgel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti patratgas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella patratga", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus patertgein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus patertgeis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas patratgan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu patertgavel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti patertgavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella patertgava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus patertgavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus patertgavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas patertgavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu patratgi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti patratgies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella patratgi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus patratgien\nche nus patertgeien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus patratgies\nche vus patertgeies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas patratgien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu patertgavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti patertgavies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella patertgavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus patertgavien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus patertgavies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas patertgavien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu patertgass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti patertgasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella patertgass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus patertgassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus patertgasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas patertgassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu patertgassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti patertgassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella patertgassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus patertgassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus patertgassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas patertgassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a patertgar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a patertgar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a patertgar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a patertgar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a patertgar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a patertgar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("patertgau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("patertgaus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("patertgada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("patertgai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("patertgadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("patratga!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("patertgei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("patertgond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    @Ignore
    public void testRule1ae() {
        forms =  generator.generateConjugation("1ae", "tschuffergnar");

        Assert.assertEquals("tschuffergnar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu tschufrognel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti tschufrognas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella tschufrogna", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus tschuffergnein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus tschuffergneis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas tschufrognan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu tschuffergnavel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti tschuffergnavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella tschuffergnava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus tschuffergnavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus tschuffergnavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas tschuffergnavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu tschufrogni", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti tschufrognies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella tschufrogni", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus tschufrognien\nche nus tschuffergneien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus tschufrognies\nche vus tschuffergneies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas tschufrognien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu tschuffergnavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti tschuffergnavies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella tschuffergnavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus tschuffergnavien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus tschuffergnavies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas tschuffergnavien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu tschuffergnass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti tschuffergnasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella tschuffergnass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus tschuffergnassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus tschuffergnasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas tschuffergnassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu tschuffergnassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti tschuffergnassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella tschuffergnassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus tschuffergnassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus tschuffergnassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas tschuffergnassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a tschuffergnar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a tschuffergnar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a tschuffergnar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a tschuffergnar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a tschuffergnar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a tschuffergnar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("tschuffergnau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("tschuffergnaus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("tschuffergnada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("tschuffergnai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("tschuffergnadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("tschufrogna!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("tschuffergnei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("tschuffergnond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1af() {
        forms =  generator.generateConjugation("1af", "sgarflar");

        Assert.assertEquals("sgarflar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu sgreflel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti sgreflas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella sgrefla", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus sgarflein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus sgarfleis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas sgreflan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu sgarflavel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti sgarflavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella sgarflava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus sgarflavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus sgarflavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas sgarflavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu sgrefli", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti sgreflies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella sgrefli", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus sgreflien\nche nus sgarfleien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus sgreflies\nche vus sgarfleies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas sgreflien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu sgarflavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti sgarflavies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella sgarflavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus sgarflavien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus sgarflavies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas sgarflavien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu sgarflass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti sgarflasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella sgarflass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus sgarflassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus sgarflasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas sgarflassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu sgarflassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti sgarflassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella sgarflassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus sgarflassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus sgarflassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas sgarflassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a sgarflar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a sgarflar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a sgarflar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a sgarflar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a sgarflar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a sgarflar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("sgarflau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("sgarflaus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("sgarflada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("sgarflai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("sgarfladas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("sgrefla!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("sgarflei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("sgarflond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1ag() {
        forms =  generator.generateConjugation("1ag", "sblundergiar");

        Assert.assertEquals("sblundergiar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu sblundregel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti sblundregias", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella sblundregia", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus sblundergein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus sblundergeis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas sblundregian", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu sblundergiavel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti sblundergiavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella sblundergiava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus sblundergiavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus sblundergiavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas sblundergiavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu sblundregi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti sblundregies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella sblundregi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus sblundregien\nche nus sblundergeien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus sblundregies\nche vus sblundergeies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas sblundregien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu sblundergiavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti sblundergiavies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella sblundergiavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus sblundergiavien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus sblundergiavies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas sblundergiavien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu sblundergiass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti sblundergiasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella sblundergiass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus sblundergiassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus sblundergiasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas sblundergiassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu sblundergiassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti sblundergiassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella sblundergiassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus sblundergiassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus sblundergiassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas sblundergiassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a sblundergiar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a sblundergiar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a sblundergiar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a sblundergiar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a sblundergiar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a sblundergiar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("sblundergiau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("sblundergiaus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("sblundergiada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("sblundergiai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("sblundergiadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("sblundregia!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("sblundergei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("sblundergiond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1ah() {
        forms =  generator.generateConjugation("1ah", "barschar");

        Assert.assertEquals("barschar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu brischel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti brischas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella brischa", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus barschein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus barscheis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas brischan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu barschavel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti barschavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella barschava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus barschavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus barschavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas barschavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu brischi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti brischies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella brischi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus brischien\nche nus barscheien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus brischies\nche vus barscheies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas brischien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu barschavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti barschavies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella barschavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus barschavien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus barschavies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas barschavien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu barschass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti barschasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella barschass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus barschassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus barschasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas barschassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu barschassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti barschassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella barschassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus barschassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus barschassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas barschassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a barschar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a barschar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a barschar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a barschar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a barschar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a barschar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("barschau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("barschaus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("barschada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("barschai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("barschadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("brischa!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("barschei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("barschond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule1ai() {
        forms =  generator.generateConjugation("1ai", "furschar");

        Assert.assertEquals("furschar", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu fruschel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti fruschas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella fruscha", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus furschein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus furscheis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas fruschan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu furschavel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti furschavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella furschava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus furschavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus furschavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas furschavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu fruschi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti fruschies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella fruschi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus fruschien\nche nus furscheien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus fruschies\nche vus furscheies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas fruschien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu furschavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti furschavies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella furschavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus furschavien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus furschavies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas furschavien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu furschass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti furschasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella furschass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus furschassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus furschasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas furschassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu furschassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti furschassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella furschassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus furschassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus furschassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas furschassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a furschar", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a furschar", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a furschar", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a furschar", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a furschar", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a furschar", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("furschau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("furschaus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("furschada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("furschai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("furschadas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("fruscha!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("furschei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("furschond", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule2() {
        forms =  generator.generateConjugation("2", "temer");

        Assert.assertEquals("temer", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu temel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti temas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella tema", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus temein\nnus temin", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus temeis\nvus temis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas teman", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu temevel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti temevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella temeva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus temevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus temevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas temevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu temi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti temies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella temi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus temien\nche nus temeien\nche nus temîen", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus temies\nche vus temeies\nche vus temîes", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas temien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu temevi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti temevies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella temevi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus temevien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus temevies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas temevien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu temess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti temesses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella temess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus temessen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus temesses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas temessen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu temessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti temessies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella temessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus temessien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus temessies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas temessien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a temer", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a temer", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a temer", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a temer", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a temer", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a temer", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("temiu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("temius", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("temida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("temi", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("temidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("tema!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("temei!\ntemi!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("temend", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule2a() {
        forms =  generator.generateConjugation("2a", "guder");

        Assert.assertEquals("guder", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu gaudel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti gaudas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella gauda", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus gudin", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus gudis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas gaudan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu gudevel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti gudevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella gudeva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus gudevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus gudevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas gudevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu gaudi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti gaudies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella gaudi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus gaudien\nche nus gudîen", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus gaudies\nche vus gudîes", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas gaudien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu gudevi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti gudevies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella gudevi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus gudevien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus gudevies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas gudevien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu gudess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti gudesses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella gudess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus gudessen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus gudesses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas gudessen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu gudessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti gudessies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella gudessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus gudessien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus gudessies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas gudessien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a guder", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a guder", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a guder", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a guder", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a guder", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a guder", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("gudiu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("gudius", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("gudida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("gudi", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("gudidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("gauda!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("gudi!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("gudend", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule3() {
        forms =  generator.generateConjugation("3", "vender");

        Assert.assertEquals("vender", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu vendel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti vendas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella venda", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus vendein\nnus vendin", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus vendeis\nvus vendis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas vendan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu vendevel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti vendevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella vendeva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus vendevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus vendevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas vendevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu vendi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti vendies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella vendi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus vendien\nche nus vendeien\nche nus vendîen", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus vendies\nche vus vendeies\nche vus vendîes", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas vendien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu vendevi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti vendevies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella vendevi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus vendevien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus vendevies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas vendevien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu vendess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti vendesses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella vendess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus vendessen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus vendesses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas vendessen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu vendessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti vendessies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella vendessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus vendessien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus vendessies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas vendessien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a vender", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a vender", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a vender", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a vender", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a vender", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a vender", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("vendiu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("vendius", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("vendida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("vendi", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("vendidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("venda!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("vendei!\nvendi!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("vendend", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule3a() {
        forms =  generator.generateConjugation("3a", "volver");

        Assert.assertEquals("volver", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu volvel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti volvas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella volva", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus vulvein\nnus vulvin", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus vulveis\nvus vulvis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas volvan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu vulvevel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti vulvevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella vulveva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus vulvevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus vulvevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas vulvevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu volvi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti volvies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella volvi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus volvien\nche nus vulveien\nche nus vulvîen", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus volvies\nche vus vulveies\nche vus vulvîes", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas volvien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu vulvevi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti vulvevies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella vulvevi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus vulvevien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus vulvevies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas vulvevien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu vulvess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti vulvesses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella vulvess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus vulvessen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus vulvesses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas vulvessen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu vulvessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti vulvessies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella vulvessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus vulvessien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus vulvessies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas vulvessien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a volver", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a volver", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a volver", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a volver", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a volver", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a volver", forms.getInflectionValues().get("futurplural3"));

        // 'volver' would also have an irregular form 'viult', but the test is only for the regular pattern
        Assert.assertEquals("vulviu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("vulvius", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("vulvida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("vulvi", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("vulvidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("volva!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("vulvei!\nvulvi!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("vulvend", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule3b() {
        forms =  generator.generateConjugation("3b", "rietscher");

        Assert.assertEquals("rietscher", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu rietschel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti rietschas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella rietscha", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus ritschin", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus ritschis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas rietschan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu ritschevel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti ritschevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella ritscheva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus ritschevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus ritschevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas ritschevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu rietschi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti rietschies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella rietschi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus rietschien\nche nus ritschîen", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus rietschies\nche vus ritschîes", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas rietschien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu ritschevi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti ritschevies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella ritschevi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus ritschevien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus ritschevies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas ritschevien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu ritschess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti ritschesses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella ritschess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus ritschessen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus ritschesses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas ritschessen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu ritschessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti ritschessies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella ritschessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus ritschessien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus ritschessies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas ritschessien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a rietscher", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a rietscher", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a rietscher", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a rietscher", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a rietscher", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a rietscher", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("ritschiu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("ritschius", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("ritschida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("ritschi", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("ritschidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("rietscha!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("ritschi!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("ritschend", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule3c() {
        forms =  generator.generateConjugation("3c", "clauder");

        Assert.assertEquals("clauder", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu claudel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti claudas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella clauda", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus cludein\nnus cludin", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus cludeis\nvus cludis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas claudan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu cludevel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti cludevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella cludeva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus cludevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus cludevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas cludevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu claudi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti claudies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella claudi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus claudien\nche nus cludeien\nche nus cludîen", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus claudies\nche vus cludeies\nche vus cludîes", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas claudien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu cludevi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti cludevies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella cludevi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus cludevien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus cludevies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas cludevien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu cludess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti cludesses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella cludess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus cludessen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus cludesses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas cludessen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu cludessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti cludessies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella cludessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus cludessien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus cludessies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas cludessien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a clauder", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a clauder", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a clauder", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a clauder", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a clauder", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a clauder", forms.getInflectionValues().get("futurplural3"));

        // particip is irregular for 'claus'; 'claudiu' is not correct. But the regular pattern would be like this,
        // thus testing for a wrong verb form.
        Assert.assertEquals("cludiu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("cludius", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("cludida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("cludi", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("cludidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("clauda!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("cludei!\ncludi!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("cludend", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule3d() {
        forms =  generator.generateConjugation("3d", "reiver");

        Assert.assertEquals("reiver", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu reivel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti reivas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella reiva", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus revein\nnus revin", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus reveis\nvus revis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas reivan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu revevel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti revevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella reveva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus revevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus revevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas revevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu reivi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti reivies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella reivi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus reivien\nche nus reveien\nche nus revîen", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus reivies\nche vus reveies\nche vus revîes", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas reivien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu revevi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti revevies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella revevi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus revevien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus revevies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas revevien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu revess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti revesses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella revess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus revessen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus revesses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas revessen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu revessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti revessies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella revessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus revessien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus revessies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas revessien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a reiver", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a reiver", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a reiver", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a reiver", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a reiver", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a reiver", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("reviu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("revius", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("revida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("revi", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("revidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("reiva!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("revei!\nrevi!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("revend", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    @Ignore
    public void testRule3e() {
        forms =  generator.generateConjugation("3e", "laguoter");

        Assert.assertEquals("laguoter", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu laguotel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti laguotas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella laguota", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus laguttin", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus laguttis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas laguotan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu laguttevel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti laguttevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella lagutteva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus laguttevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus laguttevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas laguttevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu laguoti", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti laguoties", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella laguoti", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus laguotien\nche nus laguttîen", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus laguoties\nche vus laguttîes", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas laguotien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu laguttevi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti laguttevies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella laguttevi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus laguttevien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus laguttevies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas laguttevien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu laguttess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti laguttesses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella laguttess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus laguttessen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus laguttesses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas laguttessen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu laguttessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti laguttessies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella laguttessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus laguttessien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus laguttessies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas laguttessien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a laguoter", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a laguoter", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a laguoter", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a laguoter", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a laguoter", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a laguoter", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("laguttiu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("laguttius", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("laguttida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("lagutti", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("laguttidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("laguota!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("lagutti!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("laguttend", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule3f() {
        forms =  generator.generateConjugation("3f", "piarder");

        Assert.assertEquals("piarder", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu piardel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti piardas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella piarda", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus perdein\nnus perdin", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus perdeis\nvus perdis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas piardan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu perdevel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti perdevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella perdeva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus perdevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus perdevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas perdevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu piardi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti piardies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella piardi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus piardien\nche nus perdeien\nche nus perdîen", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus piardies\nche vus perdeies\nche vus perdîes", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas piardien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu perdevi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti perdevies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella perdevi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus perdevien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus perdevies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas perdevien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu perdess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti perdesses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella perdess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus perdessen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus perdesses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas perdessen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu perdessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti perdessies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella perdessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus perdessien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus perdessies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas perdessien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a piarder", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a piarder", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a piarder", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a piarder", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a piarder", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a piarder", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("perdiu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("perdius", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("perdida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("perdi", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("perdidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("piarda!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("perdei!\nperdi!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("perdend", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule4() {
        forms =  generator.generateConjugation("4", "sentir");

        Assert.assertEquals("sentir", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu sentel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti sentas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella senta", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus sentin", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus sentis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas sentan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu sentevel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti sentevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella senteva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus sentevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus sentevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas sentevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu senti", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti senties", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella senti", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus sentîen", forms.getInflectionValues().get("conjunctivplural1")); // DIFF
        Assert.assertEquals("che vus sentîes", forms.getInflectionValues().get("conjunctivplural2")); // DIFF
        Assert.assertEquals("ch'els/ellas sentien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu sentevi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti sentevies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella sentevi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus sentevien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus sentevies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas sentevien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu sentess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti sentesses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella sentess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus sentessen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus sentesses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas sentessen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu sentessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti sentessies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella sentessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus sentessien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus sentessies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas sentessien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a sentir", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a sentir", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a sentir", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a sentir", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a sentir", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a sentir", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("sentiu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("sentius", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("sentida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("senti", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("sentidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("senta!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("senti!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("sentend", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule4a() {
        forms =  generator.generateConjugation("4a", "durmir");

        Assert.assertEquals("durmir", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu dormel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti dormas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella dorma", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus durmin", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus durmis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas dorman", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu durmevel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti durmevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella durmeva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus durmevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus durmevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas durmevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu dormi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti dormies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella dormi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus dormien\nche nus durmîen", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus dormies\nche vus durmîes", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas dormien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu durmevi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti durmevies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella durmevi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus durmevien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus durmevies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas durmevien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu durmess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti durmesses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella durmess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus durmessen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus durmesses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas durmessen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu durmessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti durmessies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella durmessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus durmessien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus durmessies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas durmessien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a durmir", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a durmir", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a durmir", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a durmir", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a durmir", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a durmir", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("durmiu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("durmius", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("durmida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("durmi", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("durmidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("dorma!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("durmi!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("durmend", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule4b() {
        forms =  generator.generateConjugation("4b", "durmir");

        Assert.assertEquals("durmir", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu diermel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti diermas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella dierma", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus durmin", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus durmis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas dierman", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu durmevel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti durmevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella durmeva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus durmevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus durmevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas durmevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu diermi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti diermies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella diermi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus diermien\nche nus durmîen", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus diermies\nche vus durmîes", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas diermien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu durmevi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti durmevies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella durmevi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus durmevien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus durmevies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas durmevien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu durmess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti durmesses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella durmess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus durmessen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus durmesses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas durmessen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu durmessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti durmessies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella durmessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus durmessien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus durmessies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas durmessien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a durmir", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a durmir", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a durmir", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a durmir", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a durmir", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a durmir", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("durmiu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("durmius", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("durmida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("durmi", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("durmidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("dierma!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("durmi!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("durmend", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule4c() {
        forms =  generator.generateConjugation("4c", "burlir");

        Assert.assertEquals("burlir", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu buorlel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti buorlas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella buorla", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus burlin", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus burlis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas buorlan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu burlevel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti burlevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella burleva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus burlevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus burlevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas burlevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu buorli", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti buorlies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella buorli", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus buorlien\nche nus burlîen", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus buorlies\nche vus burlîes", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas buorlien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu burlevi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti burlevies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella burlevi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus burlevien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus burlevies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas burlevien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu burless", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti burlesses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella burless", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus burlessen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus burlesses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas burlessen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu burlessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti burlessies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella burlessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus burlessien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus burlessies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas burlessien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a burlir", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a burlir", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a burlir", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a burlir", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a burlir", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a burlir", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("burliu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("burlius", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("burlida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("burli", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("burlidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("buorla!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("burli!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("burlend", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule4d() {
        forms =  generator.generateConjugation("4d", "burlir");

        Assert.assertEquals("burlir", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu berlel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti berlas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella berla", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus burlin", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus burlis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas berlan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu burlevel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti burlevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella burleva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus burlevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus burlevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas burlevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu berli", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti berlies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella berli", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus berlien\nche nus burlîen", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus berlies\nche vus burlîes", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas berlien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu burlevi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti burlevies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella burlevi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus burlevien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus burlevies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas burlevien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu burless", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti burlesses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella burless", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus burlessen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus burlesses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas burlessen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu burlessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti burlessies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella burlessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus burlessien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus burlessies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas burlessien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a burlir", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a burlir", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a burlir", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a burlir", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a burlir", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a burlir", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("burliu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("burlius", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("burlida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("burli", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("burlidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("berla!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("burli!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("burlend", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    @Ignore
    public void testRule4e() {
        forms =  generator.generateConjugation("4e", "embellir");

        Assert.assertEquals("embellir", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu embialel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti embialas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella embiala", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus embellin", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus embellis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas embialan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu embellevel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti embellevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella embelleva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus embellevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus embellevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas embellevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu embiali", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti embialies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella embiali", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus embialien\nembelleschien\nembellîen", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus embialies \nembelleschies\nembellîes", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas embialien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu embellevi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti embellevies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella embellevi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus embellevien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus embellevies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas embellevien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu embelless", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti embellesses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella embelless", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus embellessen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus embellesses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas embellessen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu embellessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti embellessies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella embellessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus embellessien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus embellessies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas embellessien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a embellir", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a embellir", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a embellir", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a embellir", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a embellir", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a embellir", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("embelliu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("embellius", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("embellida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("embelli", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("embellidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("embiala!\nembellescha!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("embelli!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("embellend", forms.getInflectionValues().get("gerundium"));
    }

    @Test
    public void testRule4f() {
        forms =  generator.generateConjugation("4f", "bargir");

        Assert.assertEquals("bargir", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu bragel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti bragias", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella bragia", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus bargin", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus bargis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas bragian", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu bargevel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti bargevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella bargeva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus bargevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus bargevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas bargevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu bragi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti bragies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella bragi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus bragien\nche nus bargîen", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus bragies\nche vus bargîes", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas bragien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu bargevi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti bargevies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella bargevi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus bargevien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus bargevies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas bargevien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu bargess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti bargesses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella bargess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus bargessen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus bargesses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas bargessen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu bargessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti bargessies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella bargessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus bargessien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus bargessies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas bargessien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a bargir", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a bargir", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a bargir", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a bargir", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a bargir", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a bargir", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("bargiu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("bargius", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("bargida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("bargi", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("bargidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("bragia!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("bargi!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("bargend", forms.getInflectionValues().get("gerundium"));
    }


    @Test
    public void testRule5() {
        forms =  generator.generateConjugation("5", "capir");

        Assert.assertEquals("capir", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu capeschel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti capeschas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella capescha", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus capin", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus capis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas capeschan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu capevel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti capevas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella capeva", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus capevan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus capevas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas capevan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu capeschi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti capeschies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella capeschi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus capeschien\nche nus capîen", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus capeschies\nche vus capîes", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas capeschien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu capevi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti capevies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella capevi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus capevien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus capevies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas capevien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu capess", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti capesses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella capess", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus capessen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus capesses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas capessen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu capessi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti capessies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella capessi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus capessien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus capessies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas capessien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a capir", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a capir", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a capir", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a capir", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a capir", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a capir", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("capiu", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("capius", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("capida", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("capi", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("capidas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("capescha!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("capi!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("capend", forms.getInflectionValues().get("gerundium"));
    }


    @Test
    public void testRule6() {
        forms =  generator.generateConjugation("6", "gratular");

        Assert.assertEquals("gratular", forms.getInflectionValues().get("infinitiv"));

        Assert.assertEquals("jeu gratuleschel", forms.getInflectionValues().get("preschentsing1"));
        Assert.assertEquals("ti gratuleschas", forms.getInflectionValues().get("preschentsing2"));
        Assert.assertEquals("el/ella gratulescha", forms.getInflectionValues().get("preschentsing3"));
        Assert.assertEquals("nus gratulein", forms.getInflectionValues().get("preschentplural1"));
        Assert.assertEquals("vus gratuleis", forms.getInflectionValues().get("preschentplural2"));
        Assert.assertEquals("els/ellas gratuleschan", forms.getInflectionValues().get("preschentplural3"));

        Assert.assertEquals("jeu gratulavel", forms.getInflectionValues().get("imperfectsing1"));
        Assert.assertEquals("ti gratulavas", forms.getInflectionValues().get("imperfectsing2"));
        Assert.assertEquals("el/ella gratulava", forms.getInflectionValues().get("imperfectsing3"));
        Assert.assertEquals("nus gratulavan", forms.getInflectionValues().get("imperfectplural1"));
        Assert.assertEquals("vus gratulavas", forms.getInflectionValues().get("imperfectplural2"));
        Assert.assertEquals("els/ellas gratulavan", forms.getInflectionValues().get("imperfectplural3"));

        Assert.assertEquals("che jeu gratuleschi", forms.getInflectionValues().get("conjunctivsing1"));
        Assert.assertEquals("che ti gratuleschies", forms.getInflectionValues().get("conjunctivsing2"));
        Assert.assertEquals("ch'el/ella gratuleschi", forms.getInflectionValues().get("conjunctivsing3"));
        Assert.assertEquals("che nus gratuleschien\nche nus gratuleien", forms.getInflectionValues().get("conjunctivplural1"));
        Assert.assertEquals("che vus gratuleschies\nche vus gratuleies", forms.getInflectionValues().get("conjunctivplural2"));
        Assert.assertEquals("ch'els/ellas gratuleschien", forms.getInflectionValues().get("conjunctivplural3"));

        Assert.assertEquals("che jeu gratulavi", forms.getInflectionValues().get("conjunctiv2sing1"));
        Assert.assertEquals("che ti gratulavies", forms.getInflectionValues().get("conjunctiv2sing2"));
        Assert.assertEquals("ch'el/ella gratulavi", forms.getInflectionValues().get("conjunctiv2sing3"));
        Assert.assertEquals("che nus gratulavien", forms.getInflectionValues().get("conjunctiv2plural1"));
        Assert.assertEquals("che vus gratulavies", forms.getInflectionValues().get("conjunctiv2plural2"));
        Assert.assertEquals("ch'els/ellas gratulavien", forms.getInflectionValues().get("conjunctiv2plural3"));

        Assert.assertEquals("jeu gratulass", forms.getInflectionValues().get("cundizionalsing1"));
        Assert.assertEquals("ti gratulasses", forms.getInflectionValues().get("cundizionalsing2"));
        Assert.assertEquals("el/ella gratulass", forms.getInflectionValues().get("cundizionalsing3"));
        Assert.assertEquals("nus gratulassen", forms.getInflectionValues().get("cundizionalplural1"));
        Assert.assertEquals("vus gratulasses", forms.getInflectionValues().get("cundizionalplural2"));
        Assert.assertEquals("els/ellas gratulassen", forms.getInflectionValues().get("cundizionalplural3"));

        Assert.assertEquals("jeu gratulassi", forms.getInflectionValues().get("cundizionalindirectsing1"));
        Assert.assertEquals("ti gratulassies", forms.getInflectionValues().get("cundizionalindirectsing2"));
        Assert.assertEquals("el/ella gratulassi", forms.getInflectionValues().get("cundizionalindirectsing3"));
        Assert.assertEquals("nus gratulassien", forms.getInflectionValues().get("cundizionalindirectplural1"));
        Assert.assertEquals("vus gratulassies", forms.getInflectionValues().get("cundizionalindirectplural2"));
        Assert.assertEquals("els/ellas gratulassien", forms.getInflectionValues().get("cundizionalindirectplural3"));

        Assert.assertEquals("jeu vegn(el) a gratular", forms.getInflectionValues().get("futursing1"));
        Assert.assertEquals("ti vegns a gratular", forms.getInflectionValues().get("futursing2"));
        Assert.assertEquals("el/ella vegn a gratular", forms.getInflectionValues().get("futursing3"));
        Assert.assertEquals("nus vegnin a gratular", forms.getInflectionValues().get("futurplural1"));
        Assert.assertEquals("vus vegnis a gratular", forms.getInflectionValues().get("futurplural2"));
        Assert.assertEquals("els/ellas vegnan a gratular", forms.getInflectionValues().get("futurplural3"));

        Assert.assertEquals("gratulau", forms.getInflectionValues().get("participperfectms"));
        Assert.assertEquals("gratulaus", forms.getInflectionValues().get("participperfectmspredicativ"));
        Assert.assertEquals("gratulada", forms.getInflectionValues().get("participperfectfs"));
        Assert.assertEquals("gratulai", forms.getInflectionValues().get("participperfectmp"));
        Assert.assertEquals("gratuladas", forms.getInflectionValues().get("participperfectfp"));

        Assert.assertEquals("gratulescha!", forms.getInflectionValues().get("imperativ1"));
        Assert.assertEquals("gratulei!", forms.getInflectionValues().get("imperativ2"));

        Assert.assertEquals("gratulond", forms.getInflectionValues().get("gerundium"));
    }
}
