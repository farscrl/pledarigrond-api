package ch.pledarigrond.inflection.generation.sutsilvan;

import ch.pledarigrond.inflection.model.InflectionSubType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SutsilvanConjugationClasses {

    private static final List<InflectionSubType> conjugationClasses = new ArrayList<>(
            Arrays.asList(
                    new InflectionSubType("1", "-ar", "Finiziun sin -ar (gidar)"),
                    new InflectionSubType("2", "-ear", "Finiziun sin -ear (angraztgear)"),
                    new InflectionSubType("3", "-er", "Finiziun sin -er (repeter)"),
                    new InflectionSubType("4", "-ir", "Finiziun sin -ir (partir)"),
                    new InflectionSubType("5", "-ar (esch)", "Finiziun sin -ar cun infix esch (cumbinar)"),
                    new InflectionSubType("6", "-ear (esch)", "Finiziun sin -ear cun infix esch (inditgear)"),
                    new InflectionSubType("7", "-ir (esch)", "Finiziun sin -ir cun infix esch (capir)"),
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
