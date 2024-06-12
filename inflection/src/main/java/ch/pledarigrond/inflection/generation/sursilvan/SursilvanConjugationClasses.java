package ch.pledarigrond.inflection.generation.sursilvan;

import ch.pledarigrond.inflection.model.InflectionSubType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SursilvanConjugationClasses {

    private static final List<InflectionSubType> conjugationClasses = new ArrayList<>(
            Arrays.asList(
                    new InflectionSubType("1", "1: -ar", "Finiziun sin -ar (gidar)"),
                    new InflectionSubType("6", "1: -ar (-esch)", "Finiziun sin -ar cun infix -esch- (gratular)"),
                    new InflectionSubType("1a", "a –> o", "(p.ex. clamar)"),
                    new InflectionSubType("1b", "i –> o", "(p.ex. tschintschar)"),
                    new InflectionSubType("1c", "u –> o", "(p.ex. purtar)"),
                    new InflectionSubType("1d", "o –> uo", "(p.ex. formar)"),
                    new InflectionSubType("1e", "u –> uo ", "(p.ex. mussar)"),
                    new InflectionSubType("1f", "a –> ei", "(p.ex. zavrar)"),
                    new InflectionSubType("1g", "e –> ei", "(p.ex. tedlar)"),
                    new InflectionSubType("1h", "i –> ei", "(p.ex. pinar)"),
                    new InflectionSubType("1i", "u –> au", "(p.ex. ludar)"),
                    new InflectionSubType("1j", "i –> au", "(p.ex. filtschar)"),
                    new InflectionSubType("1k", "a –> au", "(p.ex. alzar)"),
                    new InflectionSubType("1l", "a –> ia", "(p.ex. emparar)"),
                    new InflectionSubType("1m", "e –> ia", "(p.ex. serrar)"),
                    new InflectionSubType("1n", "i –> ia", "(p.ex. zanistrar)"),
                    new InflectionSubType("1o", "u –> ue", "(p.ex. ugliar)"),
                    new InflectionSubType("1p", "i –> e", "(p.ex. spitgar)"),
                    new InflectionSubType("1q", "e –> ie", "(p.ex. semiar)"),
                    new InflectionSubType("1r", "i –> a", "(p.ex. rischlar)"),
                    new InflectionSubType("1s", "e –> i", "(p.ex. sbuserar)"),
                    new InflectionSubType("1t", "a –> u", "(p.ex. smarschanar)"),
                    new InflectionSubType("1u", "e –> u", "(p.ex. cuglienar)"),
                    new InflectionSubType("1v", "u –> ua", "(p.ex. curclar)"),
                    new InflectionSubType("1w", "u –> iu", "(p.ex. suar)"),

                    new InflectionSubType("2", "3: -er", "Finiziun sin -er, accent silla finiziun (temer)"),

                    new InflectionSubType("3", "3: -er", "Finiziun sin -er, accent sil tschep (vender)"),

                    new InflectionSubType("4", "4: -ir", "Finiziun sin -ir (sentir)"),
                    new InflectionSubType("5", "4: -ir (-esch-)", "Finiziun sin -ir cun infix -esch- (capir)")
            )
    );

    public static List<InflectionSubType> getVerbInflectionSubtypes() {
        return conjugationClasses;
    }

    public static InflectionSubType getConjugationClass(String subtypeId) {
        return conjugationClasses.stream().filter(subType -> subType.id.equals(subtypeId)).findFirst().orElse(null);
    }
}
