package ch.pledarigrond.inflection.generation.noun.vallader;

import ch.pledarigrond.inflection.model.InflectionSubType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValladerNounClasses {
    private static final List<InflectionSubType> nounClasses = new ArrayList<>(
            Arrays.asList(
                    new InflectionSubType("1", "be masculin", "p.ex. il cas"),
                    new InflectionSubType("2", "be feminin", "p.ex. la palü"),
                    new InflectionSubType("3", "regular", "p.ex. scolar – scolara"),
                    new InflectionSubType("4", "der -> dra", "p.ex. manader – manadra"),
                    new InflectionSubType("5", "bel -> bla", "p.ex. respunsabel – respunsabla"),
                    new InflectionSubType("6", "vel -> vla", "p.ex. crettaivel – crettaivla"),
                    new InflectionSubType("7", "ker -> cra", "p.ex. apoteker – apotecra"),
                    new InflectionSubType("8", "ven -> vna", "p.ex. giuven – giuvna"),
                    new InflectionSubType("9", "ver -> vra", "p.ex. pover – povra"),
                    new InflectionSubType("10", "à -> ada", "p.ex. deputà – deputada"),
                    new InflectionSubType("11", "ü -> üda", "p.ex. vendü – vendüda"),
                    new InflectionSubType("12", "i -> ida", "p.ex. impedi – impedida"),
                    new InflectionSubType("13", "plural coll", "p.ex. bös-ch - bös-chs – bös-cha")

            )
    );

    public static List<InflectionSubType> getNounInflectionSubtypes() {
        return nounClasses;
    }

    public static InflectionSubType getNounInflectionClass(String subtypeId) {
        return nounClasses.stream().filter(subType -> subType.id.equals(subtypeId)).findFirst().orElse(null);
    }
}
