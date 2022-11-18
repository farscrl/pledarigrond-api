package ch.pledarigrond.inflection.generation.sutsilvan;

import ch.pledarigrond.inflection.model.InflectionSubType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SutsilvanNounClasses {
    private static final List<InflectionSubType> nounClasses = new ArrayList<>(
            Arrays.asList(
                    new InflectionSubType("1", "mo masculin", "p.ex. il cass"),
                    new InflectionSubType("2", "mo feminin", "p.ex. la palé"),
                    new InflectionSubType("3", "regular", "p.ex. scular – sculara"),
                    new InflectionSubType("4", "der -> dra", "p.ex. antschavider – antschavidra"),
                    new InflectionSubType("5", "bel -> bla", "p.ex. raspunsabel -> raspunsabla"),
                    new InflectionSubType("6", "cher -> cra", "p.ex. apotecer – apotecra"),
                    new InflectionSubType("7", "vel -> vla", "p.ex. raspunsavel – raspunsavla"),
                    new InflectionSubType("8", "ven -> vna", "p.ex. giuven – giuvna"),
                    new InflectionSubType("9", "ver -> vra", "p.ex. pover – povra"),
                    new InflectionSubType("10", "o -> ada", "p.ex. impiego – impiegada"),
                    new InflectionSubType("11", "ieu -> ida", "p.ex. impedieu – impedida"),
                    new InflectionSubType("12", "glieu -> eada", "p.ex. sgiaglieu - sgiaglieada"),
                    new InflectionSubType("13", "tgieu -> eada", "p.ex. partutgieu - partutgeada"),
                    new InflectionSubType("14", "iieu -> eada", "p.ex. exiliieu – exilieada"),
                    new InflectionSubType("15", "plural coll", "p.ex. peir - peirs - peira")
            )
    );

    public static List<InflectionSubType> getNounInflectionSubtypes() {
        return nounClasses;
    }

    public static InflectionSubType getNounInflectionClass(String subtypeId) {
        return nounClasses.stream().filter(subType -> subType.id.equals(subtypeId)).findFirst().orElse(null);
    }
}
