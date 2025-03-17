package ch.pledarigrond.inflection.generation.verb.sutsilvan;

import ch.pledarigrond.inflection.model.InflectionSubType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SutsilvanConjugationClasses {

    private static final List<InflectionSubType> conjugationClasses = new ArrayList<>(
            Arrays.asList(
                    new InflectionSubType("1", "-ar", "Finiziun sin -ar (gidar)"),
                    new InflectionSubType("5", "-ar (esch)", "Finiziun sin -ar cun infix esch (cumbinar)", true),
                    new InflectionSubType("1a", "a -> ea", "tarlar - tearla - tarlagn - tarlo", true),
                    new InflectionSubType("1b", "a -> e", "pasar - pesa - pasagn - paso", true),
                    new InflectionSubType("1c", "a -> o", "clamar - cloma - clamagn - clamo", true),
                    new InflectionSubType("1d", "a -> ai ", "passantar - passainta - passantagn - passanto", true),
                    new InflectionSubType("1e", "i -> ie", "irtar - ierta - irtagn - irto", true),
                    new InflectionSubType("1f", "i -> e", "fimar - fema - fimagn - fimo", true),
                    new InflectionSubType("1g", "i -> egn", "scalinar - scalegna - scalinagn - scalino", true),
                    new InflectionSubType("1h", "u-> a", "luvrar - lavura - luvragn - luvro", true),
                    new InflectionSubType("1i", "u -> o", "purtar - porta - purtagn - purto", true),
                    new InflectionSubType("1j", "-an- -> -agn-", "tschanar - tschagna - tschanagn - tschano", true),
                    new InflectionSubType("1k", "-an- -> -à-", "plantar - plànt - plantagn - planto", true),
                    new InflectionSubType("1l", "-un- -> -ù-", "sunar - sùna - sunagn - suno", true),
                    new InflectionSubType("1m", "-ar- -> re", "sfardar - sfreda - sfardagn - sfardo", true),
                    new InflectionSubType("1n", "-ar- -> ro", "sgarmar - sgroma - sgarmagn - sgramo", true),
                    new InflectionSubType("1o", "-ar- -> ra", "sgartar - sgrata - sgartagn - sgarto", true),
                    new InflectionSubType("1p", "ca- -> tgà", "cantar - tgànta - cantagn - canto", true),
                    new InflectionSubType("1q", "-ga- -> go", "anganar - angona - anganagn - angano", true),

                    new InflectionSubType("2", "-ear", "Finiziun sin -ear (angraztgear)"),
                    new InflectionSubType("6", "-ear (esch)", "Finiziun sin -ear cun infix esch (inditgear)", true),
                    new InflectionSubType("2a", "a -> e", "samtgear – semtga - samtgagn - samtgieu", true),
                    new InflectionSubType("2b", "i -> e", "spitgear - spetga - spitgagn - spitgieu", true),
                    new InflectionSubType("2c", "u -> o", "cuptgear - coptga – cuptgagn - cuptgieu", true),
                    new InflectionSubType("2d", "a -> ea", "tgartgear - tgeartga - tgartgagn - tgartgieu", true),
                    new InflectionSubType("2e", "-agliear  -> -egl-", "svagliear - sveglia - svagliagn - svaglieu", true),
                    new InflectionSubType("2f", "-agear -> -eg-", "spassagear - spassegia - spassagiagn - spassagieu", true),
                    new InflectionSubType("2g", "-argear -> -reg-", "zambargear - zambregia - zambergiagn - zambergieu", true),

                    new InflectionSubType("9", "-er", "Finiziun sin -er, accent sin finiziun (parer)"),
                    new InflectionSubType("9a", "a -> e", "tamer - tema - tamagn - tamieu", true),

                    new InflectionSubType("3", "-er", "Finiziun sin -er (repeter)"),
                    new InflectionSubType("3a", "e -> a", "vender - venda - vandagn - vandieu", true),
                    new InflectionSubType("3b", "e -> i", "dezeder - dezeda - dezidagn - dezidieu", true),
                    new InflectionSubType("3c", "ea -> a", "pearder - pearda - pardagn - pears", true),
                    new InflectionSubType("3d", "à -> a ", "tànscher - tànscha - tanschagn - tanschieu", true),
                    new InflectionSubType("3e", "o -> u", "porscher - porscha - purschagn - purschieu", true),
                    new InflectionSubType("3f", "-ger -> -gia", "liger – ligia – ligiagn - ligieu", true),

                    new InflectionSubType("4", "-ir", "Finiziun sin -ir (partir)"),
                    new InflectionSubType("7", "-ir (esch)", "Finiziun sin -ir cun infix esch (capir)", true),
                    new InflectionSubType("4a", "a -> ai", "santir - sainta - santagn - santieu", true),
                    new InflectionSubType("4b", "i -> e", "sgrignir - sgregna - sgrignagn - sgrignieu", true),
                    new InflectionSubType("4c", "u -> o", "udir - oda - udagn - udieu", true),
                    new InflectionSubType("4d", "-ar- -> -ra-", "bargir - bragia - bargiagn - bargieu", true),
                    new InflectionSubType("4e", "-cu- -> -quie-", "ancurir - anquiera - ancuragn - ancuretg", true),

                    new InflectionSubType("8", "Verbs alternànts", "Midada dal tschep")
            )
    );

    public static List<InflectionSubType> getVerbInflectionSubtypes() {
        return conjugationClasses;
    }

    public static InflectionSubType getConjugationClass(String subtypeId) {
        return conjugationClasses.stream().filter(subType -> subType.id.equals(subtypeId)).findFirst().orElse(null);
    }
}
