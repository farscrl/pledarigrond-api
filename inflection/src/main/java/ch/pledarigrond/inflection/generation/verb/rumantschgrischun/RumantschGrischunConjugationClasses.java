package ch.pledarigrond.inflection.generation.verb.rumantschgrischun;

import ch.pledarigrond.inflection.model.InflectionSubType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RumantschGrischunConjugationClasses {

    private static final List<InflectionSubType> conjugationClasses = new ArrayList<>(
            Arrays.asList(
                    new InflectionSubType("1", "1: -ar", "Finiziun sin -ar (chantar)"),
                    new InflectionSubType("6", "6: -ar (-esch-)", "Finiziun sin -ar cun infix -esch- (programmar)"),
                    new InflectionSubType("3", "3: -er", "Finiziun sin -er (vender)"),
                    new InflectionSubType("4", "4: -ir/-er", "Finiziun sin -ir/-er cun 1./2. pl. sin -in/-is (partir, currer)"),
                    new InflectionSubType("5", "5: -ir (-esch-)", "Finiziun sin -ir cun infix -esch- (chapir)"),
                    new InflectionSubType("2", "2. -air", "Finiziun sin -air (taschair)"),
                    new InflectionSubType("7", "7: 1. sg. sin -el", "(liar – jau liel)")

            )
    );

    public static List<InflectionSubType> getVerbInflectionSubtypes() {
        return conjugationClasses;
    }

    public static InflectionSubType getConjugationClass(String subtypeId) {
        return conjugationClasses.stream().filter(subType -> subType.id.equals(subtypeId)).findFirst().orElse(null);
    }
}
