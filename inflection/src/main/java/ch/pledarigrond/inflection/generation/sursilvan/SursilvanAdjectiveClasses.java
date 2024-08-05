package ch.pledarigrond.inflection.generation.sursilvan;

import ch.pledarigrond.inflection.model.InflectionSubType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SursilvanAdjectiveClasses {
    private static final List<InflectionSubType> nounClasses = new ArrayList<>(
            Arrays.asList(
                    new InflectionSubType("1", "regla da basa","p.ex. verd -> verds, verda, verds, verdas"),
                    new InflectionSubType("2", "finiziun sin «-el»","p.ex. ventireivel -> ventireivels, ventireivla, ventireivels, ventireivlas"),
                    new InflectionSubType("3", "finiziun sin «-al»","p.ex. viscal -> viscals, viscla, viscals, visclas"),
                    new InflectionSubType("4", "finiziun sin «-en»","p.ex. giuven -> giuvens, giuvna, giuvens, giuvnas"),
                    new InflectionSubType("6", "finiziun sin «-er»","p.ex. pauper -> paupers, paupra, paupers, paupras"),
                    new InflectionSubType("7", "finiziun sen «-iu»","p.ex. bugliu – buglius, buglida, buglius, buglidas"),
                    new InflectionSubType("8", "finiziun sen «-au»","p.ex. barsau – barsaus, barsada, barsaus, barsada"),

                    // TODO: valid?
                    new InflectionSubType("10", "finiziun sen «(vocal)(c/f/m/n/p/r/t/z)»","p.ex. tschuf – tschuffa, tschufs, tschuffas")
            )
    );

    public static List<InflectionSubType> getAdjectiveInflectionSubtypes() {
        return nounClasses;
    }

    public static InflectionSubType getAdjectiveInflectionClass(String subtypeId) {
        return nounClasses.stream().filter(subType -> subType.id.equals(subtypeId)).findFirst().orElse(null);
    }
}
