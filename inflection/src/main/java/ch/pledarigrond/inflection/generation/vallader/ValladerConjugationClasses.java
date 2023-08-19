package ch.pledarigrond.inflection.generation.vallader;

import ch.pledarigrond.inflection.model.InflectionSubType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValladerConjugationClasses {

    private static final List<InflectionSubType> conjugationClasses = new ArrayList<>(
            Arrays.asList(
                    new InflectionSubType("1", "-ar", "Finiziun sün -ar (chantar)"),
                    new InflectionSubType("1a", "-ar (-esch-)", "Finiziun sün -ar cun infix -esch- (programmar)", true),

                    new InflectionSubType("3", "-er", "Finiziun sün -er (vender)"),

                    new InflectionSubType("4", "-ir/-er", "Finiziun sün -ir/-er cun 1./2. pl. sün -in/-is (partir, cuorrer)"),
                    new InflectionSubType("4a", "-ir", "Finiziun sün -ir cun infix -isch- (chapir)", true),

                    new InflectionSubType("2", "-air", "Finiziun sün -air, accent sün finiziun (p.ex. giodair)")
            )
    );

    public static List<InflectionSubType> getVerbInflectionSubtypes() {
        return conjugationClasses;
    }

    public static InflectionSubType getConjugationClass(String subtypeId) {
        return conjugationClasses.stream().filter(subType -> subType.id.equals(subtypeId)).findFirst().orElse(null);
    }
}
