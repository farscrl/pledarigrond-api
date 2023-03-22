package ch.pledarigrond.inflection.generation.puter;

import ch.pledarigrond.inflection.model.InflectionSubType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PuterAdjectiveClasses {
    private static final List<InflectionSubType> adjectiveClasses = new ArrayList<>(
            Arrays.asList(
                    new InflectionSubType("1", "regla da basa","p.ex. puril -> purila, purils, purilas"),
                    new InflectionSubType("2", "finiziun sen «er»","p.ex. asper -> aspra, aspers, aspras"),
                    new InflectionSubType("3", "finiziun sen «el»","p.ex. debel -> debla, debels, deblas"),
                    new InflectionSubType("4", "finiziun sen «car»","p.ex. mediocar –> mediocra, mediocars, mediocras"),
                    new InflectionSubType("5", "finiziun sen «en»","p.ex. giuven –> giuvna, giuvens, giuvnas"),
                    new InflectionSubType("6", "finiziun sen «em»","p.ex. bellischem –> bellischma, bellischems, bellischmas"),
                    new InflectionSubType("7", "finiziun sen «g»","p.ex. larg –> largia, largs, largias"),
                    new InflectionSubType("8", "finiziun sen «gl»","p.ex. vegl –> veglia, vegls, veglias"),
                    new InflectionSubType("9", "finiziun sen «o»","p.ex. purto –> purteda, purtos, purtedas"),
                    new InflectionSubType("10", "finiziun sen «ieu»","p.ex. bainvgnieu –> bainvgnida, bainvgnieus, bainvgnidas"),
                    new InflectionSubType("11", "finiziun sen «(vocal)(ff/ll/ss/zz)(an/em/er)»","p.ex. pluffer -> plufra, pluffers, plufras"),
                    new InflectionSubType("12", "finiziun sen «(vocal)(c/f/m/n/p/r/t/z)»","p.ex. tschuf – tschuffa, tschus, tschuffas")
            )
    );

    public static List<InflectionSubType> getAdjectiveInflectionSubtypes() {
        return adjectiveClasses;
    }

    public static InflectionSubType getAdjectiveInflectionClass(String subtypeId) {
        return adjectiveClasses.stream().filter(subType -> subType.id.equals(subtypeId)).findFirst().orElse(null);
    }
}
