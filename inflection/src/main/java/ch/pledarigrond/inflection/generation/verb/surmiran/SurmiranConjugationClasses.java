package ch.pledarigrond.inflection.generation.verb.surmiran;

import ch.pledarigrond.inflection.model.InflectionSubType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SurmiranConjugationClasses {

    private static final List<InflectionSubType> conjugationClasses = new ArrayList<>(
            Arrays.asList(
                    new InflectionSubType("1", "-ar", "Finiziun sin -ar (p.ex. cantar)"),
                    new InflectionSubType("8", "-ar (esch)", "Finiziun sin -ar cun infix esch (p.ex. abitar)", true),
                    new InflectionSubType("1a", "a -> e", " (p.ex. tadlar)", true),
                    new InflectionSubType("1b", "i -> e", " (p.ex. fimar)", true),
                    new InflectionSubType("1c", "a -> ei", " (p.ex. pasar)", true),
                    new InflectionSubType("1d", "i -> ei", " (p.ex. midar)", true),
                    new InflectionSubType("1e", "a -> ai ", " (p.ex. passantar)", true),
                    new InflectionSubType("1f", "i -> ie", " (p.ex. irtar)", true),
                    new InflectionSubType("1g", "u -> ov", " (p.ex. ampruar)", true),
                    new InflectionSubType("1h", "-ar- -> rei", " (p.ex. sfardar)", true),
                    new InflectionSubType("1i", "-ar- -> ro", " (p.ex. sgarmar)", true),
                    new InflectionSubType("1j", "-an- -> -ang", " (p.ex. splanar)", true),
                    new InflectionSubType("1k", "-un- -> -ung", " (p.ex. perdunar)", true),
                    new InflectionSubType("1l", "-an- -> -agn", " (p.ex. manar)", true),
                    new InflectionSubType("1m", "-in- -> -egn", " (p.ex. pinar)", true),
                    new InflectionSubType("1n", "-in- -> -ign", " (p.ex. schinar)", true),
                    new InflectionSubType("1o", "-ga- -> -gio", " (p.ex. anganar)", true),
                    new InflectionSubType("1p", "-ca- -> -tgo-", " (p.ex. scanar)", true),
                    new InflectionSubType("1q", "u-a -> a-u", " (p.ex. ruschanar)", true),
                    new InflectionSubType("1r", "e-a -> a-e", " (p.ex. declarar)", true),
                    new InflectionSubType("1s", "-entar: e -> ai", " (p.ex. preschentar)", true),
                    new InflectionSubType("1t", "a -> o", " (p.ex. clamar)", true),
                    new InflectionSubType("1u", "u -> o", " (p.ex. crudar)", true),

                    new InflectionSubType("2", "-er", "Finiziun sin -er, accent sin finiziun (p.ex. lascher)"),
                    new InflectionSubType("2c", "-er (esch)", "Finiziun sin -er cun infix esch, accent sin finiziun (p.ex. avantager)", true),
                    new InflectionSubType("2a", "-ag- -> eg", " (p.ex. manager)", true),
                    new InflectionSubType("2b", "-cag- -> cheg", " (p.ex. macager)", true),

                    new InflectionSubType("5", "-er", "Finiziun sin -er, accent sin tschep (p.ex. tanscher)"),
                    new InflectionSubType("5a", "e -> a [midada 1. pl.]", " (p.ex. vender)", true),
                    new InflectionSubType("5b", "o -> u [midada 1. pl.]", " (p.ex. cloder)", true),
                    new InflectionSubType("5c", "ei -> i [midada 1. pl.]", " (p.ex. veiver)", true),

                    new InflectionSubType("3", "-ier", "Finiziun sin -ier (p.ex. cuntschier)"),
                    new InflectionSubType("3d", "-ier (-esch)", "Finiziun sin -ier cun infix esch (p.ex. cudischier, modifitgier)", true),
                    new InflectionSubType("3a", "i -> e", " (p.ex. piglier)", true),
                    new InflectionSubType("3b", "u -> o", " (p.ex. tutgier)", true),
                    new InflectionSubType("3c", "u -> ou", " (p.ex. truschier)", true),

                    new InflectionSubType("4", "-eir", "Finiziun sin -eir, accent sin finiziun (p.ex. tigneir)"),
                    new InflectionSubType("4a", "a -> e", " (p.ex. tameir)", true),

                    new InflectionSubType("6", "-eir", "Finiziun sin -eir, accent sin tschep (p.ex. parteir)"),
                    new InflectionSubType("7", "-eir (esch)", "Finiziun sin -eir cun infix esch (p.ex. amplaneir)", true),
                    new InflectionSubType("6a", "-ar- -> ra", " (p.ex. bargeir)", true),
                    new InflectionSubType("6b", "a -> e", " (p.ex. darveir)", true),
                    new InflectionSubType("6c", "i -> e", " (p.ex. sgrigneir)", true),
                    new InflectionSubType("6d", "ugl -> ogl", " (p.ex. buglier)", true),
                    new InflectionSubType("6e", "a -> ai", " (p.ex. santier)", true),

                    new InflectionSubType("9", "regla veglia", "Betg pli utilisar")
            )
    );

    public static List<InflectionSubType> getVerbInflectionSubtypes() {
        return conjugationClasses;
    }

    public static InflectionSubType getConjugationClass(String subtypeId) {
        return conjugationClasses.stream().filter(subType -> subType.id.equals(subtypeId)).findFirst().orElse(null);
    }
}
