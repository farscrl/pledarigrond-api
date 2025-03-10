package ch.pledarigrond.common.data.common;

import ch.pledarigrond.common.data.common.inflection.Inflection;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DictionaryVersion {

    // info romansh
    private String RStichwort;
    private String RStichwortSort;
    private String RSemantik;
    private String RSubsemantik;
    private String RGrammatik;
    private String RGenus;
    private String RFlex;
    private String RTags;
    private String RRedirect;
    private String REtymologie;
    private String RPronunciation;
    private Inflection inflection;

    // info german
    private String DStichwort;
    private String DStichwortSort;
    private String DSemantik;
    private String DSubsemantik;
    private String DGrammatik;
    private String DGenus;
    private String DTags;
    private String DRedirect;

    // general info
    private String categories;
    private List<Example> examples = new ArrayList<>();
}
