package ch.pledarigrond.inflection.generation.sursilvan;

import ch.pledarigrond.inflection.model.InflectionSubType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SursilvanNounClasses {
    private static final List<InflectionSubType> nounClasses = new ArrayList<>(
            Arrays.asList(
                    new InflectionSubType("1", "mo masculin", "p.ex. il canaster"),
                    new InflectionSubType("2", "mo feminin", "p.ex. la dunna"),
                    new InflectionSubType("3", "regular", "p.ex. scolast – scolasta"),
                    new InflectionSubType("4", "bel -> bla", "p.ex. responsabel – responsabla"),
                    new InflectionSubType("6", "aster -> astra", "p.ex. figliaster – figliastra"),
                    new InflectionSubType("7", "at -> atta", "p.ex. tat – tatta"),
                    new InflectionSubType("8", "au -> ada", "p.ex. quinau – quinada"),
                    new InflectionSubType("9", "eder -> edra", "p.ex. bueder – buedra"),
                    new InflectionSubType("10", "en -> na", "p.ex. giuven – giuvna"),
                    new InflectionSubType("11", "er -> ra", "p.ex. lader"),
                    new InflectionSubType("13", "ider -> idra", "p.ex. vendider – vendidra"),
                    new InflectionSubType("15", "ter -> tra", "p.ex. magister – magistra"),
                    new InflectionSubType("16", "oc -> occa", "p.ex. pitoc – pitocca"),
                    new InflectionSubType("17", "ut -> utta", "p.ex. tschut – tschutta"),
                    new InflectionSubType("18", "on/un/t -> essa", "p.ex. plevon – plevonessa / barun – barunessa / cont – contessa"),
                    new InflectionSubType("19", "cher -> cra", "p.ex. apotecher – apotecra"),
                    new InflectionSubType("20", "vel -> vla", "p.ex. flevel – flevla"),
                    new InflectionSubType("21", "plural coll", "p.ex. peir - peirs - peira")
            )
    );

    public static List<InflectionSubType> getNounInflectionSubtypes() {
        return nounClasses;
    }

    public static InflectionSubType getNounInflectionClass(String subtypeId) {
        return nounClasses.stream().filter(subType -> subType.id.equals(subtypeId)).findFirst().orElse(null);
    }
}
