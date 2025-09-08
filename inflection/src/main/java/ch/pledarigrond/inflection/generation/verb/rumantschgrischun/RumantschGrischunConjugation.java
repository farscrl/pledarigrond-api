package ch.pledarigrond.inflection.generation.verb.rumantschgrischun;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.common.data.dictionary.inflection.InflectionType;
import ch.pledarigrond.common.data.dictionary.inflection.VerbDto;
import ch.pledarigrond.inflection.generation.verb.LanguageConjugation;
import ch.pledarigrond.inflection.model.InflectionSubType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class RumantschGrischunConjugation extends LanguageConjugation {
    private static final Logger logger = LoggerFactory.getLogger(RumantschGrischunConjugation.class);

    private InflectionDto inflection;
    private VerbDto verb;
    private InflectionSubType verbClass;

    private String infinitiv;
    private boolean isReflexive;

    private String root;
    private String ending;

    @Override
    public void reset() {
        inflection = new InflectionDto();
        verb = new VerbDto();
        verbClass = null;
        inflection.setVerb(verb);
        inflection.setInflectionType(InflectionType.VERB);

        infinitiv = null;
        isReflexive = false;

        root = null;
        ending = null;
    }

    @Override
    public InflectionDto generateConjugation(String conjugationClass, String infinitivForm) {
        reset();

        verb.setInfinitiv(infinitivForm);
        infinitiv = checkReflexiveness(infinitivForm);
        root = getRoot(infinitiv);

        InflectionSubType subType = RumantschGrischunConjugationClasses.getConjugationClass(conjugationClass);
        if (subType == null) {
            throw new RuntimeException(conjugationClass + " is not a valid conjugation class.");
        } else if (ending == null) {
            throw new RuntimeException(infinitivForm + " is not a valid verb. Please type a verb in its infinitive form.");
        }

        verbClass = subType;
        verb.setInflectionSubtype(verbClass.id);
        conjugate();
        addPronouns();
        return inflection;
    }

    private String getRoot(String infinitivForm) {
        if (infinitivForm != null) {
            infinitivForm = normalizeString(infinitivForm);
            if (infinitivForm.equals("ir")) {
                ending = infinitivForm;
                return infinitivForm;
            }

            switch (infinitivForm.length()) {
                case 0:
                case 1:
                case 2:
                    throw new RuntimeException("'" + infinitivForm + "' is not a valid verb. Please type a verb in its infinitive form.");

                default:

                    Set<String> avoid = new HashSet<>();
                    avoid.add("ar");
                    avoid.add("er");
                    avoid.add("air");
                    avoid.add("s'ar");
                    avoid.add("s'ir");
                    avoid.add("s'er");
                    avoid.add("s'air");
                    avoid.add("sa ar");
                    avoid.add("sa er");
                    avoid.add("sa ir");
                    avoid.add("sa air");
                    avoid.add("sa'ar");
                    avoid.add("sa'ir");
                    avoid.add("sa'er");
                    avoid.add("sa'air");

                    if (avoid.contains(infinitivForm)) {
                        throw new RuntimeException("'" + infinitivForm + "' is not a valid verb. Please type a verb in its infinitive form.");
                    }

                    infinitivForm = processQuery(infinitivForm);
            }
        }
        return infinitivForm;
    }

    @Override
    public InflectionDto guessInflection(String baseForm, String genus, String flex) {
        reset();

        if (baseForm.length() < 3) {
            return null;
        }

        String lastTwo = baseForm.substring(baseForm.length() - 2);
        String lastThree = baseForm.substring(baseForm.length() - 3);

        if (lastThree.equals("air")) {
            return generateConjugation("2", baseForm);
        }

        if (lastTwo.equals("ar")) {
            if (flex != null && flex.equals("-escha")) {
                return generateConjugation("6", baseForm);
            }
            return generateConjugation("1", baseForm);
        }

        if (lastTwo.equals("er")) {
            return generateConjugation("3", baseForm);
        }

        if (lastTwo.equals("ir")) {
            if (flex != null && flex.equals("-escha")) {
                return generateConjugation("5", baseForm);
            }
            return generateConjugation("4", baseForm);
        }

        return null;
    }

    private String checkReflexiveness(String infinitivForm) {
        if (infinitivForm.startsWith("sa ")) {
            infinitivForm = infinitivForm.length() > 2 ? infinitivForm.substring(3) : infinitivForm;
            isReflexive = true;

        } else if (infinitivForm.startsWith("s'")) {
            infinitivForm = infinitivForm.length() > 2 ? infinitivForm.substring(2) : infinitivForm;
            isReflexive = true;
        } else {
            isReflexive = false;
        }

        return infinitivForm;
    }

    private String processQuery(String infinitivForm) {
        String l2 = infinitivForm.substring(infinitivForm.length() - 2);
        String l3 = infinitivForm.substring(infinitivForm.length() - 3);

        switch (l2) {
            case "ar":
            case "er":
            case "ir":
                if (!l3.equals("air")) {
                    ending = l2;
                    infinitivForm = infinitivForm.substring(0, infinitivForm.length() - 2);

                    return infinitivForm;
                }
                break;
        }

        if (l3.equals("air")) {
            ending = l3;
            infinitivForm = infinitivForm.substring(0, infinitivForm.length() - 3);

            return infinitivForm;
        }

        return infinitivForm;
    }

    private void conjugate() {
        verb.setIsReflexive(isReflexive);

        setPreschent();
        setImperfect();
        setConjunctiv();
        setCundizional();
        setParticipPerfect();
        setImperativ();
        setGerundium();
        setFutur();
    }

    private void setPreschent() {
        VerbDto.PersonalVerbDto preschent = new VerbDto.PersonalVerbDto();

        switch (verbClass.id) {
            case "1":
            case "2":
            case "3":
            case "4":
            case "7":
                // 1ps
                if (verbClass.id.equals("7")) {
                    preschent.setSing1(root + "el");
                } else {
                    preschent.setSing1(root);
                }

                preschent.setSing2(root + "as");
                preschent.setSing3(root + "a");

                if (verbClass.id.equals("4")) {
                    // 1pp
                    preschent.setPlural1(root + "in");
                    // 2pp
                    preschent.setPlural2(root + "is");
                } else {
                    // 1pp
                    preschent.setPlural1(root + "ain");
                    // 2pp
                    preschent.setPlural2(root + "ais");
                }
                preschent.setPlural3(root + "an");
                break;

            case "5":
            case "6":
                preschent.setSing1(root + "esch");
                preschent.setSing2(root + "eschas");
                preschent.setSing3(root + "escha");

                if (verbClass.id.equals("5")) {
                    preschent.setPlural1(root + "in");
                    preschent.setPlural2(root + "is");
                } else {
                    preschent.setPlural1(root + "ain");
                    preschent.setPlural2(root + "ais");
                }

                preschent.setPlural3(root + "eschan");
                break;
        }

        verb.setPreschent(preschent);
    }

    private void setImperfect() {
        VerbDto.PersonalVerbDto imperfect = new VerbDto.PersonalVerbDto();

        switch (verbClass.id) {

            case "1":
            case "6":
            case "7":
                imperfect.setSing1(root + "ava");
                imperfect.setSing2(root + "avas");
                imperfect.setSing3(root + "ava");

                imperfect.setPlural1(root + "avan");
                imperfect.setPlural2(root + "avas");
                imperfect.setPlural3(root + "avan");
                break;

            case "4":
            case "5":
                imperfect.setSing1(root + "iva");
                imperfect.setSing2(root + "ivas");
                imperfect.setSing3(root + "iva");

                imperfect.setPlural1(root + "ivan");
                imperfect.setPlural2(root + "ivas");
                imperfect.setPlural3(root + "ivan");
                break;

            case "2":
            case "3":
                imperfect.setSing1(root + "eva");
                imperfect.setSing2(root + "evas");
                imperfect.setSing3(root + "eva");

                imperfect.setPlural1(root + "evan");
                imperfect.setPlural2(root + "evas");
                imperfect.setPlural3(root + "evan");
                break;
        }

        verb.setImperfect(imperfect);
    }

    private void setConjunctiv() {
        VerbDto.PersonalVerbDto conjunctiv = new VerbDto.PersonalVerbDto();

        switch (verbClass.id) {
            case "5":
            case "6":
                conjunctiv.setSing1(root + "eschia");
                conjunctiv.setSing2(root + "eschias");
                conjunctiv.setSing3(root + "eschia");

                conjunctiv.setPlural1(root + "eschian");
                conjunctiv.setPlural2(root + "eschias");
                conjunctiv.setPlural3(root + "eschian");
                break;

            default:
                conjunctiv.setSing1(root + "ia");
                conjunctiv.setSing2(root + "ias");
                conjunctiv.setSing3(root + "ia");

                conjunctiv.setPlural1(root + "ian");
                conjunctiv.setPlural2(root + "ias");
                conjunctiv.setPlural3(root + "ian");
                break;
        }

        verb.setConjunctiv(conjunctiv);
    }

    private void setCundizional() {
        VerbDto.PersonalVerbDto cundizional = new VerbDto.PersonalVerbDto();

        switch (verbClass.id) {
            case "1":
            case "6":
            case "7":
                cundizional.setSing1(root + "ass");
                cundizional.setSing2(root + "assas");
                cundizional.setSing3(root + "ass");

                cundizional.setPlural1(root + "assan");
                cundizional.setPlural2(root + "assas");
                cundizional.setPlural3(root + "assan");
                break;

            case "4":
            case "5":
                cundizional.setSing1(root + "iss");
                cundizional.setSing2(root + "issas");
                cundizional.setSing3(root + "iss");

                cundizional.setPlural1(root + "issan");
                cundizional.setPlural2(root + "issas");
                cundizional.setPlural3(root + "issan");
                break;

            case "2":
            case "3":
                cundizional.setSing1(root + "ess");
                cundizional.setSing2(root + "essas");
                cundizional.setSing3(root + "ess");

                cundizional.setPlural1(root + "essan");
                cundizional.setPlural2(root + "essas");
                cundizional.setPlural3(root + "essan");
                break;
        }

        verb.setCundiziunal(cundizional);
    }

    private void setParticipPerfect() {
        VerbDto.ParticipPerfectDto participPerfect = new VerbDto.ParticipPerfectDto();

        switch (verbClass.id) {
            case "1":
            case "6":
            case "7":
                participPerfect.setMs(root + "à");
                participPerfect.setFs(root + "ada");
                participPerfect.setMp(root + "ads");
                participPerfect.setFp(root + "adas");
                break;

            default:
                participPerfect.setMs(root + "ì");
                participPerfect.setFs(root + "ida");
                participPerfect.setMp(root + "ids");
                participPerfect.setFp(root + "idas");
                break;

        }

        verb.setParticipPerfect(participPerfect);
    }

    private void setGerundium() {
        if ("4".equals(verbClass.id)) {
            verb.setGerundium(root + "ind");
            return;
        }

        switch (ending) {
            case "ar":
                verb.setGerundium(root + "ond");
                break;
            case "er":
            case "air":
                verb.setGerundium(root + "end");
                break;
            case "ir":
                verb.setGerundium(root + "ind");
                break;
            default:
                break;
        }
    }

    private void setImperativ() {
        VerbDto.ImperativDto imperativ = new VerbDto.ImperativDto();

        switch (verbClass.id) {
            case "5":
            case "6":
                imperativ.setSingular(root + "escha!");

                switch (ending) {
                    case "ar":
                        imperativ.setPlural(root + "ai!");
                        break;
                    case "er":
                        imperativ.setPlural(root + "si!");
                        break;
                    case "ir":
                    case "air":
                        imperativ.setPlural(root + "i!");
                        break;
                }
                break;

            case "4":
                imperativ.setSingular(root + "a!");
                imperativ.setPlural(root + "i!");
                break;

            default:
                imperativ.setSingular(root + "a!");
                imperativ.setPlural(root + "ai!");
                break;
        }

        verb.setImperativ(imperativ);
    }

    private void setFutur() {
        VerbDto.PersonalVerbDto futur = new VerbDto.PersonalVerbDto();

        char startChar = root.charAt(0);
        if (isReflexive) {
            if (isVocal(startChar)) {
                futur.setSing1("vegn a " + RumantschGrischunPronouns.pron_r_v_1ps + infinitiv);
                futur.setSing2("vegns a " + RumantschGrischunPronouns.pron_r_v_2ps + infinitiv);
                futur.setSing3("vegn a " + RumantschGrischunPronouns.pron_r_v_3ps + infinitiv);

                futur.setPlural1("vegnin ad " + RumantschGrischunPronouns.pron_r_v_1pp + infinitiv);
                futur.setPlural2("vegnis ad " + RumantschGrischunPronouns.pron_r_v_2pp + infinitiv);
                futur.setPlural3("vegnan a " + RumantschGrischunPronouns.pron_r_v_3pp + infinitiv);

            } else {
                futur.setSing1("vegn a " + RumantschGrischunPronouns.pron_r_1ps + infinitiv);
                futur.setSing2("vegns a " + RumantschGrischunPronouns.pron_r_2ps + infinitiv);
                futur.setSing3("vegn a " + RumantschGrischunPronouns.pron_r_3ps + infinitiv);

                futur.setPlural1("vegnin ad " + RumantschGrischunPronouns.pron_r_1pp + infinitiv);
                futur.setPlural2("vegnis ad " + RumantschGrischunPronouns.pron_r_2pp + infinitiv);
                futur.setPlural3("vegnan a " + RumantschGrischunPronouns.pron_r_3pp + infinitiv);

            }
        } else {
            if (isVocal(startChar)) {
                futur.setSing1("vegn ad " + infinitiv);
                futur.setSing2("vegns ad " + infinitiv);
                futur.setSing3("vegn ad " + infinitiv);
                futur.setPlural1("vegnin ad " + infinitiv);
                futur.setPlural2("vegnis ad " + infinitiv);
                futur.setPlural3("vegnan ad " + infinitiv);

            } else {
                futur.setSing1("vegn a " + infinitiv);
                futur.setSing2("vegns a " + infinitiv);
                futur.setSing3("vegn a " + infinitiv);
                futur.setPlural1("vegnin a " + infinitiv);
                futur.setPlural2("vegnis a " + infinitiv);
                futur.setPlural3("vegnan a " + infinitiv);
            }
        }

        verb.setFutur(futur);
    }

    private void addPronouns() {
        if (verb.getInfinitiv().startsWith("sa ")) {
            // Reflexive Verbs that start with Consonants
            addReflexivePronouns();

        } else if (verb.getInfinitiv().startsWith("s'")) {
            // Reflexive Verbs that start with Vocals
            addReflexivePronounsVowel();

        } else {
            // Standard Verbs
            addStandardPronouns();
        }
    }

    private void addReflexivePronouns() {

        // PRESCHENT
        verb.getPreschent().setSing1(RumantschGrischunPronouns.pron_1ps + RumantschGrischunPronouns.pron_r_1ps + verb.getPreschent().getSing1());
        verb.getPreschent().setSing2(RumantschGrischunPronouns.pron_2ps + RumantschGrischunPronouns.pron_r_2ps + verb.getPreschent().getSing2());
        verb.getPreschent().setSing3(RumantschGrischunPronouns.pron_3ps + RumantschGrischunPronouns.pron_r_3ps + verb.getPreschent().getSing3());
        verb.getPreschent().setPlural1(RumantschGrischunPronouns.pron_1pp + RumantschGrischunPronouns.pron_r_1pp + verb.getPreschent().getPlural1());
        verb.getPreschent().setPlural2(RumantschGrischunPronouns.pron_2pp + RumantschGrischunPronouns.pron_r_2pp + verb.getPreschent().getPlural2());
        verb.getPreschent().setPlural3(RumantschGrischunPronouns.pron_3pp + RumantschGrischunPronouns.pron_r_3pp + verb.getPreschent().getPlural3());

        // IMPERFECT
        verb.getImperfect().setSing1(RumantschGrischunPronouns.pron_1ps + RumantschGrischunPronouns.pron_r_1ps + verb.getImperfect().getSing1());
        verb.getImperfect().setSing2(RumantschGrischunPronouns.pron_2ps + RumantschGrischunPronouns.pron_r_2ps + verb.getImperfect().getSing2());
        verb.getImperfect().setSing3(RumantschGrischunPronouns.pron_3ps + RumantschGrischunPronouns.pron_r_3ps + verb.getImperfect().getSing3());
        verb.getImperfect().setPlural1(RumantschGrischunPronouns.pron_1pp + RumantschGrischunPronouns.pron_r_1pp + verb.getImperfect().getPlural1());
        verb.getImperfect().setPlural2(RumantschGrischunPronouns.pron_2pp + RumantschGrischunPronouns.pron_r_2pp + verb.getImperfect().getPlural2());
        verb.getImperfect().setPlural3(RumantschGrischunPronouns.pron_3pp + RumantschGrischunPronouns.pron_r_3pp + verb.getImperfect().getPlural3());

        // CONJUNCTIV
        verb.getConjunctiv().setSing1(RumantschGrischunPronouns.pron_conjunctiv_c + RumantschGrischunPronouns.pron_1ps + RumantschGrischunPronouns.pron_r_1ps + verb.getConjunctiv().getSing1());
        verb.getConjunctiv().setSing2(RumantschGrischunPronouns.pron_conjunctiv_c + RumantschGrischunPronouns.pron_2ps + RumantschGrischunPronouns.pron_r_2ps + verb.getConjunctiv().getSing2());
        verb.getConjunctiv().setSing3(RumantschGrischunPronouns.pron_conjunctiv_v + RumantschGrischunPronouns.pron_3ps + RumantschGrischunPronouns.pron_r_3ps + verb.getConjunctiv().getSing3());
        verb.getConjunctiv().setPlural1(RumantschGrischunPronouns.pron_conjunctiv_c + RumantschGrischunPronouns.pron_1pp + RumantschGrischunPronouns.pron_r_1pp + verb.getConjunctiv().getPlural1());
        verb.getConjunctiv().setPlural2(RumantschGrischunPronouns.pron_conjunctiv_c + RumantschGrischunPronouns.pron_2pp + RumantschGrischunPronouns.pron_r_2pp + verb.getConjunctiv().getPlural2());
        verb.getConjunctiv().setPlural3(RumantschGrischunPronouns.pron_conjunctiv_v + RumantschGrischunPronouns.pron_3pp + RumantschGrischunPronouns.pron_r_3pp + verb.getConjunctiv().getPlural3());

        // CUNDIZIONAL
        verb.getCundiziunal().setSing1(RumantschGrischunPronouns.pron_1ps + RumantschGrischunPronouns.pron_r_1ps + verb.getCundiziunal().getSing1());
        verb.getCundiziunal().setSing2(RumantschGrischunPronouns.pron_2ps + RumantschGrischunPronouns.pron_r_2ps + verb.getCundiziunal().getSing2());
        verb.getCundiziunal().setSing3(RumantschGrischunPronouns.pron_3ps + RumantschGrischunPronouns.pron_r_3ps + verb.getCundiziunal().getSing3());
        verb.getCundiziunal().setPlural1(RumantschGrischunPronouns.pron_1pp + RumantschGrischunPronouns.pron_r_1pp + verb.getCundiziunal().getPlural1());
        verb.getCundiziunal().setPlural2(RumantschGrischunPronouns.pron_2pp + RumantschGrischunPronouns.pron_r_2pp + verb.getCundiziunal().getPlural2());
        verb.getCundiziunal().setPlural3(RumantschGrischunPronouns.pron_3pp + RumantschGrischunPronouns.pron_r_3pp + verb.getCundiziunal().getPlural3());

        // IMPERATIV
        verb.getImperativ().setSingular(RumantschGrischunPronouns.pron_r_2ps + verb.getImperativ().getSingular());
        verb.getImperativ().setPlural(RumantschGrischunPronouns.pron_r_2pp + verb.getImperativ().getPlural());

        // PARTICIP_PERFECT
        String participPerfectMs = verb.getParticipPerfect().getMs();
        verb.getParticipPerfect().setMs(RumantschGrischunPronouns.pp_r1 + " " + RumantschGrischunPronouns.pron_r_3ps + participPerfectMs);
        verb.getParticipPerfect().setFs(RumantschGrischunPronouns.pp_r2 + " " + RumantschGrischunPronouns.pron_r_3ps + participPerfectMs + "/" + verb.getParticipPerfect().getFs());
        verb.getParticipPerfect().setMp(null);
        verb.getParticipPerfect().setFp(null);

        // GERUNDIUM
        verb.setGerundium(RumantschGrischunPronouns.pron_r_3ps + verb.getGerundium());

        // FUTUR
        verb.getFutur().setSing1(RumantschGrischunPronouns.pron_1ps + verb.getFutur().getSing1());
        verb.getFutur().setSing2(RumantschGrischunPronouns.pron_2ps + verb.getFutur().getSing2());
        verb.getFutur().setSing3(RumantschGrischunPronouns.pron_3ps + verb.getFutur().getSing3());
        verb.getFutur().setPlural1(RumantschGrischunPronouns.pron_1pp + verb.getFutur().getPlural1());
        verb.getFutur().setPlural2(RumantschGrischunPronouns.pron_2pp + verb.getFutur().getPlural2());
        verb.getFutur().setPlural3(RumantschGrischunPronouns.pron_3pp + verb.getFutur().getPlural3());
    }

    private void addReflexivePronounsVowel() {

        // PRESCHENT
        verb.getPreschent().setSing1(RumantschGrischunPronouns.pron_1ps + RumantschGrischunPronouns.pron_r_v_1ps + verb.getPreschent().getSing1());
        verb.getPreschent().setSing2(RumantschGrischunPronouns.pron_2ps + RumantschGrischunPronouns.pron_r_v_2ps + verb.getPreschent().getSing2());
        verb.getPreschent().setSing3(RumantschGrischunPronouns.pron_3ps + RumantschGrischunPronouns.pron_r_v_3ps + verb.getPreschent().getSing3());
        verb.getPreschent().setPlural1(RumantschGrischunPronouns.pron_1pp + RumantschGrischunPronouns.pron_r_v_1pp + verb.getPreschent().getPlural1());
        verb.getPreschent().setPlural2(RumantschGrischunPronouns.pron_2pp + RumantschGrischunPronouns.pron_r_v_2pp + verb.getPreschent().getPlural2());
        verb.getPreschent().setPlural3(RumantschGrischunPronouns.pron_3pp + RumantschGrischunPronouns.pron_r_v_3pp + verb.getPreschent().getPlural3());

        // IMPERFECT
        verb.getImperfect().setSing1(RumantschGrischunPronouns.pron_1ps + RumantschGrischunPronouns.pron_r_v_1ps + verb.getImperfect().getSing1());
        verb.getImperfect().setSing2(RumantschGrischunPronouns.pron_2ps + RumantschGrischunPronouns.pron_r_v_2ps + verb.getImperfect().getSing2());
        verb.getImperfect().setSing3(RumantschGrischunPronouns.pron_3ps + RumantschGrischunPronouns.pron_r_v_3ps + verb.getImperfect().getSing3());
        verb.getImperfect().setPlural1(RumantschGrischunPronouns.pron_1pp + RumantschGrischunPronouns.pron_r_v_1pp + verb.getImperfect().getPlural1());
        verb.getImperfect().setPlural2(RumantschGrischunPronouns.pron_2pp + RumantschGrischunPronouns.pron_r_v_2pp + verb.getImperfect().getPlural2());
        verb.getImperfect().setPlural3(RumantschGrischunPronouns.pron_3pp + RumantschGrischunPronouns.pron_r_v_3pp + verb.getImperfect().getPlural3());

        // CONJUNCTIV
        verb.getConjunctiv().setSing1(RumantschGrischunPronouns.pron_conjunctiv_c + RumantschGrischunPronouns.pron_1ps + RumantschGrischunPronouns.pron_r_v_1ps + verb.getConjunctiv().getSing1());
        verb.getConjunctiv().setSing2(RumantschGrischunPronouns.pron_conjunctiv_c + RumantschGrischunPronouns.pron_2ps + RumantschGrischunPronouns.pron_r_v_2ps + verb.getConjunctiv().getSing2());
        verb.getConjunctiv().setSing3(RumantschGrischunPronouns.pron_conjunctiv_v + RumantschGrischunPronouns.pron_3ps + RumantschGrischunPronouns.pron_r_v_3ps + verb.getConjunctiv().getSing3());
        verb.getConjunctiv().setPlural1(RumantschGrischunPronouns.pron_conjunctiv_c + RumantschGrischunPronouns.pron_1pp + RumantschGrischunPronouns.pron_r_v_1pp + verb.getConjunctiv().getPlural1());
        verb.getConjunctiv().setPlural2(RumantschGrischunPronouns.pron_conjunctiv_c + RumantschGrischunPronouns.pron_2pp + RumantschGrischunPronouns.pron_r_v_2pp + verb.getConjunctiv().getPlural2());
        verb.getConjunctiv().setPlural3(RumantschGrischunPronouns.pron_conjunctiv_v + RumantschGrischunPronouns.pron_3pp + RumantschGrischunPronouns.pron_r_v_3pp + verb.getConjunctiv().getPlural3());

        // CUNDIZIONAL
        verb.getCundiziunal().setSing1(RumantschGrischunPronouns.pron_1ps + RumantschGrischunPronouns.pron_r_v_1ps + verb.getCundiziunal().getSing1());
        verb.getCundiziunal().setSing2(RumantschGrischunPronouns.pron_2ps + RumantschGrischunPronouns.pron_r_v_2ps + verb.getCundiziunal().getSing2());
        verb.getCundiziunal().setSing3(RumantschGrischunPronouns.pron_3ps + RumantschGrischunPronouns.pron_r_v_3ps + verb.getCundiziunal().getSing3());
        verb.getCundiziunal().setPlural1(RumantschGrischunPronouns.pron_1pp + RumantschGrischunPronouns.pron_r_v_1pp + verb.getCundiziunal().getPlural1());
        verb.getCundiziunal().setPlural2(RumantschGrischunPronouns.pron_2pp + RumantschGrischunPronouns.pron_r_v_2pp + verb.getCundiziunal().getPlural2());
        verb.getCundiziunal().setPlural3(RumantschGrischunPronouns.pron_3pp + RumantschGrischunPronouns.pron_r_v_3pp + verb.getCundiziunal().getPlural3());

        // IMPERATIV
        verb.getImperativ().setSingular(RumantschGrischunPronouns.pron_r_v_2ps + verb.getImperativ().getSingular());
        verb.getImperativ().setPlural(RumantschGrischunPronouns.pron_r_v_2pp + verb.getImperativ().getPlural());

        // PARTICIP_PERFECT
        String participPerfectMs = verb.getParticipPerfect().getMs();
        verb.getParticipPerfect().setMs(RumantschGrischunPronouns.pp_r1 + " " + RumantschGrischunPronouns.pron_r_v_3ps + participPerfectMs);
        verb.getParticipPerfect().setFs(RumantschGrischunPronouns.pp_r2 + " " + RumantschGrischunPronouns.pron_r_v_3ps + participPerfectMs + "/" + verb.getParticipPerfect().getFs());
        verb.getParticipPerfect().setMp(null);
        verb.getParticipPerfect().setFp(null);

        // GERUNDIUM
        verb.setGerundium(RumantschGrischunPronouns.pron_r_v_3ps + verb.getGerundium());

        // FUTUR
        verb.getFutur().setSing1(RumantschGrischunPronouns.pron_1ps + verb.getFutur().getSing1());
        verb.getFutur().setSing2(RumantschGrischunPronouns.pron_2ps + verb.getFutur().getSing2());
        verb.getFutur().setSing3(RumantschGrischunPronouns.pron_3ps + verb.getFutur().getSing3());
        verb.getFutur().setPlural1(RumantschGrischunPronouns.pron_1pp + verb.getFutur().getPlural1());
        verb.getFutur().setPlural2(RumantschGrischunPronouns.pron_2pp + verb.getFutur().getPlural2());
        verb.getFutur().setPlural3(RumantschGrischunPronouns.pron_3pp + verb.getFutur().getPlural3());
    }

    private void addStandardPronouns() {

        // PRESCHENT
        verb.getPreschent().setSing1(RumantschGrischunPronouns.pron_1ps + verb.getPreschent().getSing1());
        verb.getPreschent().setSing2(RumantschGrischunPronouns.pron_2ps + verb.getPreschent().getSing2());
        verb.getPreschent().setSing3(RumantschGrischunPronouns.pron_3ps + verb.getPreschent().getSing3());
        verb.getPreschent().setPlural1(RumantschGrischunPronouns.pron_1pp + verb.getPreschent().getPlural1());
        verb.getPreschent().setPlural2(RumantschGrischunPronouns.pron_2pp + verb.getPreschent().getPlural2());
        verb.getPreschent().setPlural3(RumantschGrischunPronouns.pron_3pp + verb.getPreschent().getPlural3());

        // IMPERFECT
        verb.getImperfect().setSing1(RumantschGrischunPronouns.pron_1ps + verb.getImperfect().getSing1());
        verb.getImperfect().setSing2(RumantschGrischunPronouns.pron_2ps + verb.getImperfect().getSing2());
        verb.getImperfect().setSing3(RumantschGrischunPronouns.pron_3ps + verb.getImperfect().getSing3());
        verb.getImperfect().setPlural1(RumantschGrischunPronouns.pron_1pp + verb.getImperfect().getPlural1());
        verb.getImperfect().setPlural2(RumantschGrischunPronouns.pron_2pp + verb.getImperfect().getPlural2());
        verb.getImperfect().setPlural3(RumantschGrischunPronouns.pron_3pp + verb.getImperfect().getPlural3());

        // CONJUNCTIV
        verb.getConjunctiv().setSing1(RumantschGrischunPronouns.pron_conjunctiv_c + RumantschGrischunPronouns.pron_1ps + verb.getConjunctiv().getSing1());
        verb.getConjunctiv().setSing2(RumantschGrischunPronouns.pron_conjunctiv_c + RumantschGrischunPronouns.pron_2ps + verb.getConjunctiv().getSing2());
        verb.getConjunctiv().setSing3(RumantschGrischunPronouns.pron_conjunctiv_v + RumantschGrischunPronouns.pron_3ps + verb.getConjunctiv().getSing3());
        verb.getConjunctiv().setPlural1(RumantschGrischunPronouns.pron_conjunctiv_c + RumantschGrischunPronouns.pron_1pp + verb.getConjunctiv().getPlural1());
        verb.getConjunctiv().setPlural2(RumantschGrischunPronouns.pron_conjunctiv_c + RumantschGrischunPronouns.pron_2pp + verb.getConjunctiv().getPlural2());
        verb.getConjunctiv().setPlural3(RumantschGrischunPronouns.pron_conjunctiv_v + RumantschGrischunPronouns.pron_3pp + verb.getConjunctiv().getPlural3());

        // CUNDIZIONAL
        verb.getCundiziunal().setSing1(RumantschGrischunPronouns.pron_1ps + verb.getCundiziunal().getSing1());
        verb.getCundiziunal().setSing2(RumantschGrischunPronouns.pron_2ps + verb.getCundiziunal().getSing2());
        verb.getCundiziunal().setSing3(RumantschGrischunPronouns.pron_3ps + verb.getCundiziunal().getSing3());
        verb.getCundiziunal().setPlural1(RumantschGrischunPronouns.pron_1pp + verb.getCundiziunal().getPlural1());
        verb.getCundiziunal().setPlural2(RumantschGrischunPronouns.pron_2pp + verb.getCundiziunal().getPlural2());
        verb.getCundiziunal().setPlural3(RumantschGrischunPronouns.pron_3pp + verb.getCundiziunal().getPlural3());

        // IMPERATIV
        verb.getImperativ().setSingular(verb.getImperativ().getSingular());
        verb.getImperativ().setPlural(verb.getImperativ().getPlural());

        // PARTICIP_PERFECT
        verb.getParticipPerfect().setMs(verb.getParticipPerfect().getMs());
        verb.getParticipPerfect().setFs(verb.getParticipPerfect().getFs());
        verb.getParticipPerfect().setMp(verb.getParticipPerfect().getMp());
        verb.getParticipPerfect().setFp(verb.getParticipPerfect().getFp());

        // GERUNDIUM
        verb.setGerundium(verb.getGerundium());

        // FUTUR
        verb.getFutur().setSing1(RumantschGrischunPronouns.pron_1ps + verb.getFutur().getSing1());
        verb.getFutur().setSing2(RumantschGrischunPronouns.pron_2ps + verb.getFutur().getSing2());
        verb.getFutur().setSing3(RumantschGrischunPronouns.pron_3ps + verb.getFutur().getSing3());
        verb.getFutur().setPlural1(RumantschGrischunPronouns.pron_1pp + verb.getFutur().getPlural1());
        verb.getFutur().setPlural2(RumantschGrischunPronouns.pron_2pp + verb.getFutur().getPlural2());
        verb.getFutur().setPlural3(RumantschGrischunPronouns.pron_3pp + verb.getFutur().getPlural3());
    }
}
