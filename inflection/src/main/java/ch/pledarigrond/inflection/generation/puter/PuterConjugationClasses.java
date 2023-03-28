package ch.pledarigrond.inflection.generation.puter;

import ch.pledarigrond.inflection.model.InflectionSubType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PuterConjugationClasses {

    private static final List<InflectionSubType> conjugationClasses = new ArrayList<>(
            Arrays.asList(
                    new InflectionSubType("1", "-er", "Finiziun sün -er, accent sün la finiziun (p.ex. güder)"),
                    new InflectionSubType("1a", "-er (-esch-)", "Finiziun sün -er, accent sün la finiziun, cun infix -esch- (p.ex. gratuler)", true),

                    new InflectionSubType("2", "-air", "Finiziun sün -air, accent sün finiziun (p.ex. giodair)"),

                    new InflectionSubType("3", "-er", "Finiziun sün -er, accent sün tschep (p.ex. vender)"),

                    new InflectionSubType("4", "-ir", "Finiziun sün -ir (p.ex. partir)"),
                    new InflectionSubType("4a", "-ir", "Finiziun sün -ir, cun infix -esch- (p.ex. chapir)", true)
            )
    );

    public static List<InflectionSubType> getVerbInflectionSubtypes() {
        return conjugationClasses;
    }

    public static InflectionSubType getConjugationClass(String subtypeId) {
        return conjugationClasses.stream().filter(subType -> subType.id.equals(subtypeId)).findFirst().orElse(null);
    }
}
