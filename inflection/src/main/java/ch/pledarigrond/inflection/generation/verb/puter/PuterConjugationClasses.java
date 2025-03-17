package ch.pledarigrond.inflection.generation.verb.puter;

import ch.pledarigrond.inflection.model.InflectionSubType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PuterConjugationClasses {

    private static final List<InflectionSubType> conjugationClasses = new ArrayList<>(
            Arrays.asList(
                    new InflectionSubType("1", "-er", "Finiziun sün -er cun accent finel (chanter)"),
                    new InflectionSubType("1a", "-er (-esch-)", "Finiziun sün -er cun accent finel ed infix -esch- (programmer)", true),

                    new InflectionSubType("3", "-er", "Finiziun sün -er cun accent sül tschep (vender)"),

                    new InflectionSubType("4", "-ir/-er", "Finiziun sün -ir/-er cun 1./2. pl. sün -ins/-is (partir, cuorrer)"),
                    new InflectionSubType("4a", "-ir", "Finiziun sün -ir cun infix -esch- (chapir)", true),

                    new InflectionSubType("2", "-air", "Finiziun sün -air (giodair)")
            )
    );

    public static List<InflectionSubType> getVerbInflectionSubtypes() {
        return conjugationClasses;
    }

    public static InflectionSubType getConjugationClass(String subtypeId) {
        return conjugationClasses.stream().filter(subType -> subType.id.equals(subtypeId)).findFirst().orElse(null);
    }
}
