package ch.pledarigrond.inflection.generation.adjective.sutsilvan;

import ch.pledarigrond.inflection.model.InflectionSubType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SutsilvanAdjectiveClasses {
    private static final List<InflectionSubType> nounClasses = new ArrayList<>(
            Arrays.asList(
                    new InflectionSubType("1", "regla da basa","p.ex. puril -> purila, purils, purilas"),
                    new InflectionSubType("2", "finiziun sen «der»","p.ex. veader -> veadra, veaders, veadras"),
                    new InflectionSubType("3", "finiziun sen «bel»","p.ex. admirabla, admirabels, admirablas"),
                    new InflectionSubType("4", "finiziun sen «gher»","p.ex. legher – legra, leghers, legras"),
                    new InflectionSubType("5", "finiziun sen «vel»","p.ex. vantirevel – vantirevla, vantirevels, vantirevlas"),
                    new InflectionSubType("6", "finiziun sen «ven»","p.ex. giuven – giuvna, giuvens, giuvnas"),
                    new InflectionSubType("7", "finiziun sen «ieu»","p.ex. surprieu – surprida, surprieus, surpridas"),
                    new InflectionSubType("8", "finiziun sen «o»","p.ex. ocupo – ocupada, ocupos, ocupadas"),
                    new InflectionSubType("9", "finiziun sen «cel»","p.ex. vescel – vescla, vescels, vesclas"),
                    new InflectionSubType("10", "finiziun sen «ver»","p.ex. pover – povra, povers, povras"),
                    new InflectionSubType("11", "finiziun sen «fen»","p.ex. orfen – orfna, orfens, orfnas"),
                    new InflectionSubType("12", "finiziun sen «glieu»","p.ex. detaglieu – detaglieada, detaglieus, detaglieadas"),
                    new InflectionSubType("13", "finiziun sen «tgieu»","p.ex. partutgieu – partutgeada, partutgieus, partutgeadas")
            )
    );

    public static List<InflectionSubType> getAdjectiveInflectionSubtypes() {
        return nounClasses;
    }

    public static InflectionSubType getAdjectiveInflectionClass(String subtypeId) {
        return nounClasses.stream().filter(subType -> subType.id.equals(subtypeId)).findFirst().orElse(null);
    }
}
