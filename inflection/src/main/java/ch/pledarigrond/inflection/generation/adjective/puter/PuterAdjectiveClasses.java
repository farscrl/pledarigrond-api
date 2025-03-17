package ch.pledarigrond.inflection.generation.adjective.puter;

import ch.pledarigrond.inflection.model.InflectionSubType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PuterAdjectiveClasses {
    private static final List<InflectionSubType> adjectiveClasses = new ArrayList<>(
            Arrays.asList(
                    new InflectionSubType("1", "regla da basa","p.ex. puril -> purila, purils, purilas"),
                    new InflectionSubType("2", "finiziun sün «er»","p.ex. asper -> aspra, aspers, aspras"),
                    new InflectionSubType("3", "finiziun sün «el»","p.ex. debel -> debla, debels, deblas"),
                    new InflectionSubType("4", "finiziun sün «car»","p.ex. mediocar –> mediocra, mediocars, mediocras"),
                    new InflectionSubType("5", "finiziun sün «en»","p.ex. giuven –> giuvna, giuvens, giuvnas"),
                    new InflectionSubType("6", "finiziun sün «em»","p.ex. bellischem –> bellischma, bellischems, bellischmas"),
                    new InflectionSubType("7", "finiziun sün «g»","p.ex. larg –> largia, largs, largias"),
                    new InflectionSubType("8", "finiziun sün «gl»","p.ex. vegl –> veglia, vegls, veglias"),
                    new InflectionSubType("9", "finiziun sün «o»","p.ex. purto –> purteda, purtos, purtedas"),
                    new InflectionSubType("10", "finiziun sün «ieu»","p.ex. bainvgnieu –> bainvgnida, bainvgnieus, bainvgnidas"),
                    new InflectionSubType("11", "finiziun sün «(vocal)(ff/ll/ss/zz)(an/em/er)»","p.ex. pluffer -> plufra, pluffers, plufras"),
                    new InflectionSubType("12", "finiziun sün «(vocal)(c/f/m/n/p/r/t/z)»","p.ex. fiac –> fiacca, fiacs, fiaccas")
            )
    );

    public static List<InflectionSubType> getAdjectiveInflectionSubtypes() {
        return adjectiveClasses;
    }

    public static InflectionSubType getAdjectiveInflectionClass(String subtypeId) {
        return adjectiveClasses.stream().filter(subType -> subType.id.equals(subtypeId)).findFirst().orElse(null);
    }
}
