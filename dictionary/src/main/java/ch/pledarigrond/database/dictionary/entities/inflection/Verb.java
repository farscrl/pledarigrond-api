package ch.pledarigrond.database.dictionary.entities.inflection;

import lombok.Data;

@Data
public class Verb {
    private String infinitiv;
    private boolean irregular;
    private String inflectionSubtype;
    private boolean isReflexive;
    private String composedWith;

    private PersonalVerb preschent;
    private PersonalVerb imperfect;
    private PersonalVerb conjunctiv;
    private PersonalVerb conjunctiv2;
    private PersonalVerb cundiziunal;
    private PersonalVerb cundiziunalIndirect;
    private ParticipPerfect participPerfect;
    private Imperativ imperativ;
    private String gerundium;
    private PersonalVerb futur;
    private PersonalVerb futurDubitativ;


    private PersonalVerbEnclitic preschentEnclitic;
    private PersonalVerbEnclitic imperfectEnclitic;
    private PersonalVerbEnclitic cundizionalEnclitic;
    private PersonalVerbEnclitic futurEnclitic;
    private PersonalVerbEnclitic futurDubitativEnclitic;

    @Data
    public static class PersonalVerb {
        private String sing1;
        private String sing2;
        private String sing3;
        private String plural1;
        private String plural2;
        private String plural3;
    }

    @Data
    public static class PersonalVerbEnclitic {
        private String sing1;
        private String sing2;
        private String sing3m;
        private String sing3f;
        private String plural1;
        private String plural2;
        private String plural3;
    }

    @Data
    public static class ParticipPerfect {
        private String ms;
        private String fs;
        private String mp;
        private String fp;
        private String msPredicativ;
    }

    @Data
    public static class Imperativ {
        private String singular;
        private String plural;
        private String form3;
        private String form4;
        private String form5;
        private String form6;
    }
}
