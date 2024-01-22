package ch.pledarigrond.inflection.generation.sursilvan;

import ch.pledarigrond.inflection.model.InflectionSubType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SursilvanConjugationClasses {

    private static final List<InflectionSubType> conjugationClasses = new ArrayList<>(
            Arrays.asList(
                    new InflectionSubType("1", "1: -ar", "Finiziun sin -ar (gidar)"),
                    new InflectionSubType("1a", "1: -ar (-esch)", "Finiziun sin -ar cun infix -esch- (gratular)"),
                    new InflectionSubType("2", "3: -er", "Finiziun sin -er, accent silla finiziun (temer)"),
                    new InflectionSubType("3", "3: -er", "Finiziun sin -er, accent sil tschep (vender)"),
                    new InflectionSubType("4", "4: -ir", "Finiziun sin -ir (sentir)"),
                    new InflectionSubType("4a", "4: -ir (-esch-)", "Finiziun sin -ir cun infix -esch- (capir)")
            )
    );

    public static List<InflectionSubType> getVerbInflectionSubtypes() {
        return conjugationClasses;
    }

    public static InflectionSubType getConjugationClass(String subtypeId) {
        return conjugationClasses.stream().filter(subType -> subType.id.equals(subtypeId)).findFirst().orElse(null);
    }
}
