package ch.pledarigrond.inflection.generation.surmiran;

import ch.pledarigrond.inflection.model.InflectionSubType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SurmiranConjugationClasses {

    private static final List<InflectionSubType> conjugationClasses = new ArrayList<>(
            Arrays.asList(
                    new InflectionSubType("1", "-ar", "Finiziun sin -ar"),
                    new InflectionSubType("2", "-er", "Finiziun sin -er"),
                    new InflectionSubType("3", "-ier", "Finiziun sin -ier"),
                    new InflectionSubType("4", "-eir [è]", "Finiziun sin -eir [è]"),
                    new InflectionSubType("5", "-er", "Finiziun sin -er"),
                    new InflectionSubType("6", "-eir [é]", "Finiziun sin -eir [é]"),
                    new InflectionSubType("7", "-eir [é](esch)", "Finiziun sin -eir [é](esch)"),
                    new InflectionSubType("8", "-ar (esch)", "Finiziun sin -ar (esch)"),
                    new InflectionSubType("9", "vokal", "Vokaländerung in der Wurzel")
            )
    );

    public static List<InflectionSubType> getVerbInflectionSubtypes() {
        return conjugationClasses;
    }

    public static InflectionSubType getConjugationClass(String subtypeId) {
        return conjugationClasses.stream().filter(subType -> subType.id.equals(subtypeId)).findFirst().orElse(null);
    }
}
