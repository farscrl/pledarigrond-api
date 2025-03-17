package ch.pledarigrond.inflection.verb.sutsilvan;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.generation.noun.sutsilvan.SutsilvanNounGenerator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class SutsilvanNounGeneratorTest {

    private static SutsilvanNounGenerator generator;
    private InflectionDto forms;

    @BeforeClass
    public static void initialize() {
        generator = new SutsilvanNounGenerator();
    }

    @Test
    public void testRule3() {
        forms = generator.generateForms("3", "scular");
        Assert.assertEquals(forms.getNoun().getMSingular(), "scular");
        Assert.assertEquals(forms.getNoun().getFSingular(), "sculara");
        Assert.assertEquals(forms.getNoun().getMPlural(), "sculars");
        Assert.assertEquals(forms.getNoun().getFPlural(), "scularas");
    }

    @Test
    public void testRule4() {
        forms = generator.generateForms("4", "antschavider");
        Assert.assertEquals(forms.getNoun().getMSingular(), "antschavider");
        Assert.assertEquals(forms.getNoun().getFSingular(), "antschavidra");
        Assert.assertEquals(forms.getNoun().getMPlural(), "antschaviders");
        Assert.assertEquals(forms.getNoun().getFPlural(), "antschavidras");
    }

    @Test
    public void testRule5() {
        forms = generator.generateForms("5", "raspunsabel");
        Assert.assertEquals(forms.getNoun().getMSingular(), "raspunsabel");
        Assert.assertEquals(forms.getNoun().getFSingular(), "raspunsabla");
        Assert.assertEquals(forms.getNoun().getMPlural(), "raspunsabels");
        Assert.assertEquals(forms.getNoun().getFPlural(), "raspunsablas");
    }

    @Test
    public void testRule6() {
        forms = generator.generateForms("6", "apotecer");
        Assert.assertEquals(forms.getNoun().getMSingular(), "apotecer");
        Assert.assertEquals(forms.getNoun().getFSingular(), "apotecra");
        Assert.assertEquals(forms.getNoun().getMPlural(), "apotecers");
        Assert.assertEquals(forms.getNoun().getFPlural(), "apotecras");
    }

    @Test
    public void testRule7() {
        forms = generator.generateForms("7", "raspunsavel");
        Assert.assertEquals(forms.getNoun().getMSingular(), "raspunsavel");
        Assert.assertEquals(forms.getNoun().getFSingular(), "raspunsavla");
        Assert.assertEquals(forms.getNoun().getMPlural(), "raspunsavels");
        Assert.assertEquals(forms.getNoun().getFPlural(), "raspunsavlas");
    }

    @Test
    public void testRule8() {
        forms = generator.generateForms("8", "giuven");
        Assert.assertEquals(forms.getNoun().getMSingular(), "giuven");
        Assert.assertEquals(forms.getNoun().getFSingular(), "giuvna");
        Assert.assertEquals(forms.getNoun().getMPlural(), "giuvens");
        Assert.assertEquals(forms.getNoun().getFPlural(), "giuvnas");
    }

    @Test
    public void testRule9() {
        forms = generator.generateForms("9", "pover");
        Assert.assertEquals(forms.getNoun().getMSingular(), "pover");
        Assert.assertEquals(forms.getNoun().getFSingular(), "povra");
        Assert.assertEquals(forms.getNoun().getMPlural(), "povers");
        Assert.assertEquals(forms.getNoun().getFPlural(), "povras");
    }

    @Test
    public void testRule10() {
        forms = generator.generateForms("10", "impiego");
        Assert.assertEquals(forms.getNoun().getMSingular(), "impiego");
        Assert.assertEquals(forms.getNoun().getFSingular(), "impiegada");
        Assert.assertEquals(forms.getNoun().getMPlural(), "impiegos");
        Assert.assertEquals(forms.getNoun().getFPlural(), "impiegadas");
    }

    @Test
    public void testRule11() {
        forms = generator.generateForms("11", "impedieu");
        Assert.assertEquals(forms.getNoun().getMSingular(), "impedieu");
        Assert.assertEquals(forms.getNoun().getFSingular(), "impedida");
        Assert.assertEquals(forms.getNoun().getMPlural(), "impedieus");
        Assert.assertEquals(forms.getNoun().getFPlural(), "impedidas");
    }

    @Test
    public void testRule12() {
        forms = generator.generateForms("12", "sgiaglieu");
        Assert.assertEquals(forms.getNoun().getMSingular(), "sgiaglieu");
        Assert.assertEquals(forms.getNoun().getFSingular(), "sgiaglieada");
        Assert.assertEquals(forms.getNoun().getMPlural(), "sgiaglieus");
        Assert.assertEquals(forms.getNoun().getFPlural(), "sgiaglieadas");
    }

    @Test
    public void testRule13() {
        forms = generator.generateForms("13", "partutgieu");
        Assert.assertEquals(forms.getNoun().getMSingular(), "partutgieu");
        Assert.assertEquals(forms.getNoun().getFSingular(), "partutgeada");
        Assert.assertEquals(forms.getNoun().getMPlural(), "partutgieus");
        Assert.assertEquals(forms.getNoun().getFPlural(), "partutgeadas");
    }

    @Test
    public void testRule14() {
        forms = generator.generateForms("14", "exiliieu");
        Assert.assertEquals(forms.getNoun().getMSingular(), "exiliieu");
        Assert.assertEquals(forms.getNoun().getFSingular(), "exilieada");
        Assert.assertEquals(forms.getNoun().getMPlural(), "exiliieus");
        Assert.assertEquals(forms.getNoun().getFPlural(), "exilieadas");
    }

    @Test
    public void testRule15() {
        forms = generator.generateForms("15", "pér");
        Assert.assertEquals(forms.getNoun().getMSingular(), "pér");
        Assert.assertEquals(forms.getNoun().getFSingular(), null);
        Assert.assertEquals(forms.getNoun().getMPlural(), "pérs");
        Assert.assertEquals(forms.getNoun().getFPlural(), null);
        Assert.assertEquals(forms.getNoun().getPluralCollectiv(), "péra");
    }

    @Test
    public void testPluralRule2() {
        forms = generator.generateForms("1", "pass");
        Assert.assertEquals(forms.getNoun().getMSingular(), "pass");
        Assert.assertEquals(forms.getNoun().getMPlural(), "pass");
    }

    @Test
    public void testPluralRule3() {
        forms = generator.generateForms("1", "esch-tgea");
        Assert.assertEquals(forms.getNoun().getMSingular(), "esch-tgea");
        Assert.assertEquals(forms.getNoun().getMPlural(), "eschs-tgea");
    }

    @Test
    public void testPluralRule4() {
        forms = generator.generateForms("1", "cunti");
        Assert.assertEquals(forms.getNoun().getMSingular(), "cunti");
        Assert.assertEquals(forms.getNoun().getMPlural(), "cunteals");
    }
}
