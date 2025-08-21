package ch.pledarigrond.database.dictionary.entities.inflection;

import lombok.Data;

@Data
public class Verb implements Cloneable {
    private String infinitiv;
    private boolean irregular;
    private String inflectionSubtype;
    private Boolean isReflexive;
    private String composedWith;

    private PersonalVerb preschent;
    private PersonalVerb imperfect;
    private PersonalVerb conjunctiv;
    private PersonalVerb conjunctivImperfect;
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
    public static class PersonalVerb implements Cloneable {
        private String sing1;
        private String sing2;
        private String sing3;
        private String plural1;
        private String plural2;
        private String plural3;

        @Override
        public PersonalVerb clone() {
            try {
                return (PersonalVerb) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
    }

    @Data
    public static class PersonalVerbEnclitic implements Cloneable {
        private String sing1;
        private String sing2;
        private String sing3m;
        private String sing3f;
        private String plural1;
        private String plural2;
        private String plural3;

        @Override
        public PersonalVerbEnclitic clone() {
            try {
                return (PersonalVerbEnclitic) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
    }

    @Data
    public static class ParticipPerfect implements Cloneable {
        private String ms;
        private String fs;
        private String mp;
        private String fp;
        private String msPredicativ;

        @Override
        public ParticipPerfect clone() {
            try {
                return (ParticipPerfect) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
    }

    @Data
    public static class Imperativ implements Cloneable {
        private String singular;
        private String plural;
        private String form3;
        private String form4;
        private String form5;
        private String form6;

        @Override
        public Imperativ clone() {
            try {
                return (Imperativ) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
    }

    @Override
    public Verb clone() {
        try {
            Verb cloned = (Verb) super.clone();
            if (this.preschent != null) cloned.preschent = this.preschent.clone();
            if (this.imperfect != null) cloned.imperfect = this.imperfect.clone();
            if (this.conjunctiv != null) cloned.conjunctiv = this.conjunctiv.clone();
            if (this.conjunctivImperfect != null) cloned.conjunctivImperfect = this.conjunctivImperfect.clone();
            if (this.cundiziunal != null) cloned.cundiziunal = this.cundiziunal.clone();
            if (this.cundiziunalIndirect != null) cloned.cundiziunalIndirect = this.cundiziunalIndirect.clone();
            if (this.participPerfect != null) cloned.participPerfect = this.participPerfect.clone();
            if (this.imperativ != null) cloned.imperativ = this.imperativ.clone();
            if (this.futur != null) cloned.futur = this.futur.clone();
            if (this.futurDubitativ != null) cloned.futurDubitativ = this.futurDubitativ.clone();
            if (this.preschentEnclitic != null) cloned.preschentEnclitic = this.preschentEnclitic.clone();
            if (this.imperfectEnclitic != null) cloned.imperfectEnclitic = this.imperfectEnclitic.clone();
            if (this.cundizionalEnclitic != null) cloned.cundizionalEnclitic = this.cundizionalEnclitic.clone();
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
