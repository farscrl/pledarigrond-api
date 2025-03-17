package ch.pledarigrond.inflection.generation.verb.sursilvan;

import ch.pledarigrond.inflection.model.InflectionSubType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SursilvanConjugationClasses {

    private static final List<InflectionSubType> conjugationClasses = new ArrayList<>(
            Arrays.asList(
                    new InflectionSubType("1", "1: -ar", "Finiziun sin -ar (gidar)"),
                    new InflectionSubType("6", "-ar (-esch)", "Finiziun sin -ar cun infix -esch- (gratular)", true),
                    new InflectionSubType("1a", "a –> o", "(p.ex. clamar)", true),
                    new InflectionSubType("1b", "i –> o", "(p.ex. tschintschar)", true),
                    new InflectionSubType("1c", "u –> o", "(p.ex. purtar)", true),
                    new InflectionSubType("1d", "o –> uo", "(p.ex. formar)", true),
                    new InflectionSubType("1e", "u –> uo ", "(p.ex. mussar)", true),
                    new InflectionSubType("1f", "a –> ei", "(p.ex. zavrar)", true),
                    new InflectionSubType("1g", "e –> ei", "(p.ex. tedlar)", true),
                    new InflectionSubType("1h", "i –> ei", "(p.ex. pinar)", true),
                    new InflectionSubType("1i", "u –> au", "(p.ex. ludar)", true),
                    new InflectionSubType("1j", "i –> au", "(p.ex. filtschar)", true),
                    new InflectionSubType("1k", "a –> au", "(p.ex. alzar)", true),
                    new InflectionSubType("1l", "a –> ia", "(p.ex. emparar)", true),
                    new InflectionSubType("1m", "e –> ia", "(p.ex. serrar)", true),
                    new InflectionSubType("1n", "i –> ia", "(p.ex. zanistrar)", true),
                    new InflectionSubType("1o", "u –> ue", "(p.ex. ugliar)", true),
                    new InflectionSubType("1p", "i –> e", "(p.ex. spitgar)", true),
                    new InflectionSubType("1q", "e –> ie", "(p.ex. semiar)", true),
                    new InflectionSubType("1r", "i –> a", "(p.ex. rischlar)", true),
                    new InflectionSubType("1s", "e –> i", "(p.ex. sbuserar)", true),
                    new InflectionSubType("1t", "a –> u", "(p.ex. smarschanar)", true),
                    new InflectionSubType("1u", "e –> u", "(p.ex. cuglienar)", true),
                    new InflectionSubType("1v", "u –> ua", "(p.ex. curclar)", true),
                    new InflectionSubType("1w", "u –> iu", "(p.ex. suar)", true),
                    new InflectionSubType("1x", "u–a –> a–o ", "(p.ex. cumandar)", true),
                    new InflectionSubType("1y", "u–i –> a–o", "(p.ex. dumignar)", true),
                    new InflectionSubType("1z", "u–a –> a–u", "(p.ex. rumplanar)", true),
                    new InflectionSubType("1aa", "u–i –> a–u", "(p.ex. murmignar)", true),
                    new InflectionSubType("1ab", "u–a –> i–u", "(p.ex. buntganar)", true),
                    new InflectionSubType("1ac", "u(–)r –> ro", "(p.ex. duvrar)", true),
                    new InflectionSubType("1ad", "er –> ra", "(p.ex. patertgar)", true),
                    new InflectionSubType("1ae", "er –> ro", "(p.ex. tschuffergnar)", true),
                    new InflectionSubType("1af", "ar –> re", "(p.ex. sgarflar)", true),
                    new InflectionSubType("1ag", "er –> re", "(p.ex. sblundergiar)", true),
                    new InflectionSubType("1ah", "ar –> ri", "(p.ex. barschar)", true),
                    new InflectionSubType("1ai", "(vocal)r -> r(vocal)", "(p.ex. furschar)", true),

                    new InflectionSubType("2", "2: -er", "Finiziun sin -er, accent silla finiziun (temer)"),
                    new InflectionSubType("2a", "u -> au", "(p.ex. guder)", true),

                    new InflectionSubType("3", "3: -er", "Finiziun sin -er, accent sil tschep (vender)"),
                    new InflectionSubType("3a", "o -> u", "(p.ex. volver)", true),
                    new InflectionSubType("3b", "ie -> i", "(p.ex. rietscher)", true),
                    new InflectionSubType("3c", "au -> u", "(p.ex. clauder)", true),
                    new InflectionSubType("3d", "ei -> e", "(p.ex. entscheiver)", true),
                    new InflectionSubType("3e", "uo -> u", "(p.ex. laguoter)", true),
                    new InflectionSubType("3f", "ia -> e", "(p.ex. piarder)", true),

                    new InflectionSubType("4", "4: -ir", "Finiziun sin -ir (sentir)"),
                    new InflectionSubType("5", "-ir (-esch-)", "Finiziun sin -ir cun infix -esch- (capir)", true),
                    new InflectionSubType("4a", "u -> o", "(p.ex. durmir)", true),
                    new InflectionSubType("4b", "u -> ie", "(p.ex. durmir)", true),
                    new InflectionSubType("4c", "u -> uo", "(p.ex. burlir)", true),
                    new InflectionSubType("4d", "u -> e", "(p.ex. burlir)", true),
                    new InflectionSubType("4e", "e -> ia", "(p.ex. embellir)", true),
                    new InflectionSubType("4f", "(vocal)r -> r(vocal)", "(p.ex. bargir)", true)
            )
    );

    public static List<InflectionSubType> getVerbInflectionSubtypes() {
        return conjugationClasses;
    }

    public static InflectionSubType getConjugationClass(String subtypeId) {
        return conjugationClasses.stream().filter(subType -> subType.id.equals(subtypeId)).findFirst().orElse(null);
    }
}
