package ch.pledarigrond.inflection.generation.vallader;

import ch.pledarigrond.inflection.model.InflectionSubType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValladerAdjectiveClasses {
    private static final List<InflectionSubType> adjectiveClasses = new ArrayList<>(
            Arrays.asList(
                    new InflectionSubType("1", "regla da basa","p.ex. pauril -> paurila, paurils, paurilas"),
                    new InflectionSubType("2", "finiziun sün «er»","p.ex. asper -> aspra, aspers, aspras"),
                    new InflectionSubType("3", "finiziun sün «el»","p.ex. debel -> debla, debels, deblas"),
                    new InflectionSubType("4", "finiziun sün «car»","p.ex. mediocar –> mediocra, mediocars, mediocras"),
                    new InflectionSubType("5", "finiziun sün «en»","p.ex. giuven –> giuvna, giuvens, giuvnas"),
                    new InflectionSubType("6", "finiziun sün «em»","p.ex. bellischem –> bellischma, bellischems, bellischmas"),
                    new InflectionSubType("7", "finiziun sün «g»","p.ex. larg –> largia, largs, largias"),
                    new InflectionSubType("8", "finiziun sün «gl»","p.ex. vegl –> veglia, vegls, veglias"),
                    new InflectionSubType("9", "finiziun sün «guel»","p.ex. stanguel -> stangla, stanguels, stanglas"),
                    new InflectionSubType("10", "finiziun sün «à»","p.ex. portà –> portada, portats, portadas"),
                    new InflectionSubType("11", "finiziun sün «ü»","p.ex. vendü -> vendüda, vendüts, vendüdas"),
                    new InflectionSubType("12", "finiziun sün «i»","p.ex. impedi –> impedida, impedits, impedidas"),
                    new InflectionSubType("13", "finiziun sün «(vocal)(ff/ll/ss/tt/zz)(an/el/em/er)»","p.ex. pluffer -> plufra, pluffers, plufras"),
                    new InflectionSubType("14", "finiziun sün «(vocal)(c/f/l/m/n/p/r/t/z)»","p.ex. fiac –> fiacca, fiacs, fiaccas")
            )
    );

    public static List<InflectionSubType> getAdjectiveInflectionSubtypes() {
        return adjectiveClasses;
    }

    public static InflectionSubType getAdjectiveInflectionClass(String subtypeId) {
        return adjectiveClasses.stream().filter(subType -> subType.id.equals(subtypeId)).findFirst().orElse(null);
    }
}
