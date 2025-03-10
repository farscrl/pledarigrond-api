package ch.pledarigrond.common.data.common.inflection;

public class Verb {
    public boolean irregular;

    public String infinitiv;
    public String composedWith;

    public PersonalVerb preschent;
    public PersonalVerb imperfect;
    public PersonalVerb conjunctiv;
    public PersonalVerb conjunctiv2;
    public PersonalVerb cundizional;
    public PersonalVerb condiizionalindirect;
    public ParticipPerfect participperfect;
    public Imperativ imperativ;
    public String gerundium;
    public PersonalVerb futur;
    public PersonalVerb futurdubitativ;


    public PersonalVerbEnclitic preschentEnclitic;
    public PersonalVerbEnclitic imperfectEnclitic;
    public PersonalVerbEnclitic cundizionalEnclitic;
    public PersonalVerbEnclitic futurEnclitic;
    public PersonalVerbEnclitic futurdubitativEnclitic;

    public class PersonalVerb {
        public String sing1;
        public String sing2;
        public String sing3;
        public String plural1;
        public String plural2;
        public String plural3;
    }

    public class PersonalVerbEnclitic {
        public String sing1;
        public String sing2;
        public String sing3m;
        public String sing3f;
        public String plural1;
        public String plural2;
        public String plural3;
    }

    public class ParticipPerfect {
        public String ms;
        public String fs;
        public String mp;
        public String fp;
        public String msPredicativ;
    }

    public class Imperativ {
        public String singular;
        public String plural;
        public String form1;
        public String form2;
        public String form3;
        public String form4;
    }
}
