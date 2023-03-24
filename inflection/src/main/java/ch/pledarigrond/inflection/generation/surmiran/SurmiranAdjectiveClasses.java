package ch.pledarigrond.inflection.generation.surmiran;

import ch.pledarigrond.inflection.model.InflectionSubType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SurmiranAdjectiveClasses {
    private static final List<InflectionSubType> nounClasses = new ArrayList<>(
            Arrays.asList(
                    new InflectionSubType("1", "regla da basa","p.ex. puril -> purila, purils, purilas"),
                    new InflectionSubType("2", "finiziun sen «der»","p.ex. veder -> vedra, veders, vedras"),
                    new InflectionSubType("3", "finiziun sen «bel»","p.ex. admirabel -> admirabla, admirabels, admirablas"),
                    new InflectionSubType("4", "finiziun sen «cher»","p.ex. zacher – zacra, zachers, zacras"),
                    new InflectionSubType("5", "finiziun sen «vel»","p.ex. capavel – capavla, capavels, capavlas"),
                    new InflectionSubType("6", "finiziun sen «ven»","p.ex. gioven – giovna, giovens, giovnas"),
                    new InflectionSubType("7", "finiziun sen «ia»","p.ex. distinguia – distingueida, distinguias, distingueidas"),
                    new InflectionSubType("8", "finiziun sen «o»","p.ex. attempo – attempada, attempos, attempadas"),
                    new InflectionSubType("9", "finiziun sen «ea»","p.ex. schlaschea – schlascheda, schlascheas, schlaschedas"),
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
