package ch.pledarigrond.inflection.generation.vallader;

import ch.pledarigrond.inflection.model.InflectionSubType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValladerConjugationClasses {

    private static final List<InflectionSubType> conjugationClasses = new ArrayList<>(
            Arrays.asList(
                    new InflectionSubType("1", "-ar", "Finiziun sün -ar, accent sün la finiziun (p.ex. salüdar)"),
                    new InflectionSubType("1a", "-ar (-esch-)", "Finiziun sün -ar, accent sün la finiziun, cun infix -esch- (p.ex. gratular)", true),

                    new InflectionSubType("2", "-air", "Finiziun sün -air, accent sün finiziun (p.ex. giodair)"),

                    new InflectionSubType("3", "-er", "Finiziun sün -er, accent sün tschep (p.ex. vender)"),

                    new InflectionSubType("4", "-ir", "Finiziun sün -ir (p.ex. cusir)"),
                    new InflectionSubType("4a", "-ir", "Finiziun sün -ir, cun infix -isch- (p.ex. admonir)", true)
            )
    );

    public static List<InflectionSubType> getVerbInflectionSubtypes() {
        return conjugationClasses;
    }

    public static InflectionSubType getConjugationClass(String subtypeId) {
        return conjugationClasses.stream().filter(subType -> subType.id.equals(subtypeId)).findFirst().orElse(null);
    }
}
