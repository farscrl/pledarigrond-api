package ch.pledarigrond.database.dictionary.entities.inflection;

import lombok.Data;

@Data
public class Verb {
    public String infinitiv;
    public boolean irregular;
    public boolean isReflexive;
    public String composedWith;

    public PersonalVerb preschent;
    public PersonalVerb imperfect;
    public PersonalVerb conjunctiv;
    public PersonalVerb conjunctiv2;
    public PersonalVerb cundiziunal;
    public PersonalVerb cundiziunalIndirect;
    public ParticipPerfect participPerfect;
    public Imperativ imperativ;
    public String gerundium;
    public PersonalVerb futur;
    public PersonalVerb futurDubitativ;


    public PersonalVerbEnclitic preschentEnclitic;
    public PersonalVerbEnclitic imperfectEnclitic;
    public PersonalVerbEnclitic cundizionalEnclitic;
    public PersonalVerbEnclitic futurEnclitic;
    public PersonalVerbEnclitic futurDubitativEnclitic;

    @Data
    public static class PersonalVerb {
        public String sing1;
        public String sing2;
        public String sing3;
        public String plural1;
        public String plural2;
        public String plural3;
    }

    @Data
    public static class PersonalVerbEnclitic {
        public String sing1;
        public String sing2;
        public String sing3m;
        public String sing3f;
        public String plural1;
        public String plural2;
        public String plural3;
    }

    @Data
    public static class ParticipPerfect {
        public String ms;
        public String fs;
        public String mp;
        public String fp;
        public String msPredicativ;
    }

    @Data
    public static class Imperativ {
        public String singular;
        public String plural;
        public String form1;
        public String form2;
        public String form3;
        public String form4;
    }
}
