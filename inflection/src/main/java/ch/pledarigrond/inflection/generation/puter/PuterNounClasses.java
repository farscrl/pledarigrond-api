package ch.pledarigrond.inflection.generation.puter;

import ch.pledarigrond.inflection.model.InflectionSubType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PuterNounClasses {
    private static final List<InflectionSubType> nounClasses = new ArrayList<>(
            Arrays.asList(
                    new InflectionSubType("1", "be masculin", "p.ex. il cas"),
                    new InflectionSubType("2", "be feminin", "p.ex. la palüd"),
                    new InflectionSubType("3", "regular", "p.ex. scolar – scolara"),
                    new InflectionSubType("4", "der -> dra", "p.ex. mneder – mnedra"),
                    new InflectionSubType("5", "bel -> bla", "p.ex. respunsabel – respunsabla"),
                    new InflectionSubType("6", "vel -> vla", "p.ex. crettaivel – crettaivla"),
                    new InflectionSubType("7", "ker -> cra", "p.ex. apoteker – apotecra"),
                    new InflectionSubType("8", "ven -> vna", "p.ex. giuven – giuvna"),
                    new InflectionSubType("9", "ver -> vra", "p.ex. pover – povra"),
                    new InflectionSubType("10", "o -> eda", "p.ex. depüto – depüteda"),
                    new InflectionSubType("11", "go -> gheda", "p.ex. impiego – impiegheda"),
                    new InflectionSubType("12", "glio -> glieada", "p.ex. piglio – piglieda"),
                    new InflectionSubType("13", "ieu -> ida", "p.ex. impedieu – impedida"),
                    new InflectionSubType("14", "glieu -> glida", "p.ex. assaglieu - assaglida"),
                    new InflectionSubType("15", "plural coll", "p.ex. bês-ch - bês-chs - bês-cha")

            )
    );

    public static List<InflectionSubType> getNounInflectionSubtypes() {
        return nounClasses;
    }

    public static InflectionSubType getNounInflectionClass(String subtypeId) {
        return nounClasses.stream().filter(subType -> subType.id.equals(subtypeId)).findFirst().orElse(null);
    }
}
