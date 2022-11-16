package ch.pledarigrond.inflection.generation.rumantschgrischun;

import ch.pledarigrond.inflection.model.InflectionSubType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RumantschGrischunAdjectiveClasses {
    private static final List<InflectionSubType> nounClasses = new ArrayList<>(
            Arrays.asList(
                    new InflectionSubType("1", "regla da basa","p.ex. puril -> purila, purils, purilas"),
                    new InflectionSubType("2", "finiziun sin «der»","p.ex. veder -> vedra, veders, vedras"),
                    new InflectionSubType("3", "finiziun sin «bel»","p.ex. admirabel -> admirabla, admirabels, admirablas"),
                    new InflectionSubType("4", "finiziun sin «cher»","p.ex. mediocher –> mediocra, mediochers, mediocras"),
                    new InflectionSubType("5", "finiziun sin «vel»","p.ex. amiaivel –> amiaivla, amiaivels, amiaivlas"),
                    new InflectionSubType("6", "finiziun sin «ven»","p.ex. giuven –> giuvna, giuvens, giuvnas"),
                    new InflectionSubType("7", "finiziun sin «à»","p.ex. isolà –> isolada, isolads, isoladas"),
                    new InflectionSubType("8", "finiziun sin «ì»","p.ex. sclerì –> sclerida, sclerids, scleridas"),
                    new InflectionSubType("9", "finiziun sin «(vocal)(c/f/m/n/p/r/t/z)»","p.ex. tschuf –> tschuffa, tschufs, tschuffas")
            )
    );

    public static List<InflectionSubType> getAdjectiveInflectionSubtypes() {
        return nounClasses;
    }

    public static InflectionSubType getAdjectiveInflectionClass(String subtypeId) {
        return nounClasses.stream().filter(subType -> subType.id.equals(subtypeId)).findFirst().orElse(null);
    }
}
