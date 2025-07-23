package ch.pledarigrond.common.data.dictionary.inflection;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VerbDto {
    @Size(max = 200)  private String infinitiv;
    private boolean irregular;
    private boolean isReflexive;
    @Size(max = 10) private String inflectionSubtype;
    @Size(max = 200) private String composedWith;

    private PersonalVerbDto preschent;
    private PersonalVerbDto imperfect;
    private PersonalVerbDto conjunctiv;
    private PersonalVerbDto conjunctivImperfect;
    private PersonalVerbDto cundiziunal;
    private PersonalVerbDto cundiziunalIndirect;
    private ParticipPerfectDto participPerfect;
    private ImperativDto imperativ;
    @Size(max = 200) private String gerundium;
    private PersonalVerbDto futur;
    private PersonalVerbDto futurDubitativ;

    private PersonalVerbEncliticDto preschentEnclitic;
    private PersonalVerbEncliticDto imperfectEnclitic;
    private PersonalVerbEncliticDto cundizionalEnclitic;
    private PersonalVerbEncliticDto futurEnclitic;
    private PersonalVerbEncliticDto futurDubitativEnclitic;

    @Data
    @NoArgsConstructor
    public static class PersonalVerbDto {
        @Size(max = 200) private String sing1;
        @Size(max = 200) private String sing2;
        @Size(max = 200) private String sing3;
        @Size(max = 200) private String plural1;
        @Size(max = 200) private String plural2;
        @Size(max = 200) private String plural3;
    }

    @Data
    @NoArgsConstructor
    public static class PersonalVerbEncliticDto {
        @Size(max = 200) private String sing1;
        @Size(max = 200) private String sing2;
        @Size(max = 200) private String sing3m;
        @Size(max = 200) private String sing3f;
        @Size(max = 200) private String plural1;
        @Size(max = 200) private String plural2;
        @Size(max = 200) private String plural3;
    }

    @Data
    @NoArgsConstructor
    public static class ParticipPerfectDto {
        @Size(max = 200) private String ms;
        @Size(max = 200) private String fs;
        @Size(max = 200) private String mp;
        @Size(max = 200) private String fp;
        @Size(max = 200) private String msPredicativ;
    }

    @Data
    @NoArgsConstructor
    public static class ImperativDto {
        @Size(max = 200) private String singular;
        @Size(max = 200) private String plural;
        @Size(max = 200) private String form3;
        @Size(max = 200) private String form4;
        @Size(max = 200) private String form5;
        @Size(max = 200) private String form6;
    }
}

