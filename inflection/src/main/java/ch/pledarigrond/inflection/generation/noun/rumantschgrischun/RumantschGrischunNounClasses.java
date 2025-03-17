package ch.pledarigrond.inflection.generation.noun.rumantschgrischun;

import ch.pledarigrond.inflection.model.InflectionSubType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RumantschGrischunNounClasses {
    private static final List<InflectionSubType> nounClasses = new ArrayList<>(
            Arrays.asList(
                    new InflectionSubType("1", "mo masculin", "p.ex. il cas"),
                    new InflectionSubType("2", "mo feminin", "p.ex. la palì"),
                    new InflectionSubType("3", "regular", "p.ex. scolast – scolasta"),
                    new InflectionSubType("4", "der -> dra", "p.ex. manader – manadra"),
                    new InflectionSubType("5", "bel -> bla", "p.ex. responsabel - responsabla"),
                    new InflectionSubType("6", "cher -> cra", "p.ex. apotecher – apotecra"),
                    new InflectionSubType("7", "à -> ada", "p.ex. deputà – deputada"),
                    new InflectionSubType("8", "(vocal))(c/f/m/n/p/r/t/z)", "p.ex. nar – narra"),
                    new InflectionSubType("9", "plural coll", "p.ex. pair - pairs - paira")
            )
    );

    public static List<InflectionSubType> getNounInflectionSubtypes() {
        return nounClasses;
    }

    public static InflectionSubType getNounInflectionClass(String subtypeId) {
        return nounClasses.stream().filter(subType -> subType.id.equals(subtypeId)).findFirst().orElse(null);
    }
}
