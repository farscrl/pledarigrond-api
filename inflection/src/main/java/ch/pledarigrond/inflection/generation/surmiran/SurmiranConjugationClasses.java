package ch.pledarigrond.inflection.generation.surmiran;

import ch.pledarigrond.inflection.model.InflectionSubType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SurmiranConjugationClasses {

    private static final List<InflectionSubType> conjugationClasses = new ArrayList<>(
            Arrays.asList(
                    new InflectionSubType("1", "-ar", "Finiziun sin -ar (cantar)"),
                    new InflectionSubType("2", "-er", "Finiziun sin -er (lascher)"),
                    new InflectionSubType("3", "-ier", "Finiziun sin -ier (cuntschier)"),
                    new InflectionSubType("4", "-eir", "Finiziun sin -eir (tigneir)"),
                    new InflectionSubType("5", "-er", "Finiziun sin -er (tanscher)"),
                    new InflectionSubType("6", "-eir", "Finiziun sin -eir (parteir)"),
                    new InflectionSubType("7", "-eir (esch)", "Finiziun sin -eir cun infix esch (amplaneir)"),
                    new InflectionSubType("8", "-ar (esch)", "Finiziun sin -ar cun infix esch (abitar)"),
                    new InflectionSubType("9", "verbs alternonts", "Midada dal vocal en il tschep")
            )
    );

    public static List<InflectionSubType> getVerbInflectionSubtypes() {
        return conjugationClasses;
    }

    public static InflectionSubType getConjugationClass(String subtypeId) {
        return conjugationClasses.stream().filter(subType -> subType.id.equals(subtypeId)).findFirst().orElse(null);
    }
}
