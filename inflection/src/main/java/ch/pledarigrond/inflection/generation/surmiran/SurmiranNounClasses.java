package ch.pledarigrond.inflection.generation.surmiran;

import ch.pledarigrond.inflection.model.InflectionSubType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SurmiranNounClasses {
    private static final List<InflectionSubType> nounClasses = new ArrayList<>(
            Arrays.asList(
                    new InflectionSubType("1", "mo masculin", "p.ex. il cass"),
                    new InflectionSubType("2", "mo feminin", "p.ex. la pale"),
                    new InflectionSubType("3", "regular", "p.ex. scolast – scolasta"),
                    new InflectionSubType("4", "der -> dra", "p.ex. manader – manadra"),
                    new InflectionSubType("5", "bel -> bla", "p.ex. responsabel -> responsabla"),
                    new InflectionSubType("6", "cher -> cra", "p.ex. apotecher – apotecra"),
                    new InflectionSubType("7", "vel -> vla", "p.ex. flevel – flevla"),
                    new InflectionSubType("8", "ven -> vna", "p.ex. gioven - giovna"),
                    new InflectionSubType("9", "ia -> eida", "p.ex. ampluia – amplueida"),
                    new InflectionSubType("10", "o -> ada", "p.ex. anvido – anvidada"),
                    new InflectionSubType("11", "ea -> eda", "p.ex. relaschea – relascheda"),
                    new InflectionSubType("12", "(vocal))(c/f/m/n/p/r/t/z)", "p.ex. minoren - minorenna"),
                    new InflectionSubType("13", "plural coll", "p.ex. peir - peirs - peira")
            )
    );

    public static List<InflectionSubType> getNounInflectionSubtypes() {
        return nounClasses;
    }

    public static InflectionSubType getNounInflectionClass(String subtypeId) {
        return nounClasses.stream().filter(subType -> subType.id.equals(subtypeId)).findFirst().orElse(null);
    }
}
