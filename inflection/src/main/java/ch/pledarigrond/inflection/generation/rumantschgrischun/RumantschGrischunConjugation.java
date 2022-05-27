package ch.pledarigrond.inflection.generation.rumantschgrischun;

import ch.pledarigrond.inflection.generation.generic.LanguageConjugation;
import ch.pledarigrond.inflection.model.InflectionResponse;
import ch.pledarigrond.inflection.model.InflectionSubType;
import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
public class RumantschGrischunConjugation extends LanguageConjugation {

    private RumantschGrischunConjugationStructure cs;

    private String infinitiv;
    private String verb;

    private String isReflexive;

    private String lastTwo;
    private String lastThree;

    private HashMap<String, String> conjugation;

    @Override
    public InflectionResponse generateConjugation(String conjugationClass, String infinitiv) {
        resetValues();

        String root = getRoot(infinitiv);

        InflectionSubType subType = RumantschGrischunConjugationClasses.getConjugationClass(conjugationClass);
        if (subType == null) {
            throw new RuntimeException(conjugationClass + " is not a valid conjugation class.");
        } else if (getEnding() == null) {
            throw new RuntimeException(infinitiv + " is not a valid verb. Please type a verb in its infinitive form.");
        }

        conjugation = conjugate(root, subType);

        return new InflectionResponse(subType, addPronouns(conjugation, subType));
    }

    private String getRoot(String infinitiv) {
        if (infinitiv != null) {
            infinitiv = normalizeString(infinitiv);
            if (infinitiv.equals("ir")) {
                setVerb(infinitiv);
                setEnding(infinitiv);
                infinitiv = checkReflexiveness(infinitiv);
                return infinitiv;
            }

            switch (infinitiv.length()) {
                case 0:
                case 1:
                case 2:
                    throw new RuntimeException("'" + infinitiv + "' is not a valid verb. Please type a verb in its infinitive form.");

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

                    if (avoid.contains(infinitiv)) {
                        throw new RuntimeException("'" + infinitiv + "' is not a valid verb. Please type a verb in its infinitive form.");
                    }

                    infinitiv = processQuery(infinitiv);
            }
        }
        return infinitiv;
    }

    public InflectionResponse guessInflection(String baseForm, String genus, String flex) {
        // TODO: implement me
        return null;
    }

    private String checkReflexiveness(String infinitiv) {
        if (infinitiv.startsWith("sa ")) {
            setVerb(infinitiv);
            infinitiv = infinitiv.length() > 2 ? infinitiv.substring(3) : infinitiv;
            setIsReflexive(new String("true"));

        } else if (infinitiv.startsWith("s'")) {
            setVerb(infinitiv);
            infinitiv = infinitiv.length() > 2 ? infinitiv.substring(2) : infinitiv;
            setIsReflexive("true");
        } else {
            setIsReflexive("false");
            setVerb(infinitiv);
        }

        setInfinitiv(infinitiv);

        return infinitiv;
    }

    private String processQuery(String infinitiv) {
        String l2 = infinitiv.substring(infinitiv.length() - 2);
        String l3 = infinitiv.substring(infinitiv.length() - 3);

        switch (l2) {
            case "ar":
            case "er":
            case "ir":
                if (!l3.equals("air")) {
                    setLastTwo(l2);
                    setEnding(getLastTwo());
                    infinitiv = checkReflexiveness(infinitiv);
                    infinitiv = infinitiv.substring(0, infinitiv.length() - 2);

                    return infinitiv;
                }
                break;
        }

        if (l3.equals("air")) {
            setLastThree(l3);
            setEnding(getLastThree());
            infinitiv = checkReflexiveness(infinitiv);
            infinitiv = infinitiv.substring(0, infinitiv.length() - 3);

            return infinitiv;
        }

        return infinitiv;
    }

    private HashMap<String, String> conjugate(String root, InflectionSubType conjugationClass) {
        cs = new RumantschGrischunConjugationStructure();
        cs.setVerb(getVerb());
        cs.setInfinitiv(getInfinitiv());
        cs.setRoot(root);
        cs.setEnding(getEnding());

        cs.setConjugationClass(conjugationClass);

        cs.setReflexive(getIsReflexive());

        setPreschent(root, cs);
        setImperfect(root, cs);
        setConjunctiv(root, cs);
        setCundizional(root, cs);
        setParticipPerfect(root, cs);
        setImperativ(root, cs);
        setGerundium(root, cs);
        setFutur(root, cs);

        return cs.getValues();
    }

    private void setPreschent(String root, RumantschGrischunConjugationStructure cs) {

        switch (cs.getConjugationclass().id) {
            case "1":
            case "2":
            case "3":
            case "4":
            case "7":
                // 1ps
                if (cs.getConjugationclass().id.equals("7")) {
                    cs.setPreschentsing1(root + "el");
                } else {
                    cs.setPreschentsing1(root);
                }

                cs.setPreschentsing2(root + "as");
                cs.setPreschentsing3(root + "a");

                if (cs.getConjugationclass().id.equals("4")) {
                    // 1pp
                    cs.setPreschentplural1(root + "in");
                    // 2pp
                    cs.setPreschentplural2(root + "is");
                } else {
                    // 1pp
                    cs.setPreschentplural1(root + "ain");
                    // 2pp
                    cs.setPreschentplural2(root + "ais");
                }
                cs.setPreschentplural3(root + "an");
                break;

            case "5":
            case "6":
                cs.setPreschentsing1(root + "esch");
                cs.setPreschentsing2(root + "eschas");
                cs.setPreschentsing3(root + "escha");

                if (cs.getConjugationclass().id.equals("5")) {
                    cs.setPreschentplural1(root + "in");
                    cs.setPreschentplural2(root + "is");
                } else {
                    cs.setPreschentplural1(root + "ain");
                    cs.setPreschentplural2(root + "ais");
                }

                cs.setPreschentplural3(root + "eschan");
                break;
        }
    }

    private void setImperfect(String root, RumantschGrischunConjugationStructure cs) {
        switch (cs.getConjugationclass().id) {

            case "1":
            case "6":
            case "7":
                cs.setImperfectsing1(root + "ava");
                cs.setImperfectsing2(root + "avas");
                cs.setImperfectsing3(root + "ava");

                cs.setImperfectplural1(root + "avan");
                cs.setImperfectplural2(root + "avas");
                cs.setImperfectplural3(root + "avan");
                break;

            case "4":
            case "5":
                cs.setImperfectsing1(root + "iva");
                cs.setImperfectsing2(root + "ivas");
                cs.setImperfectsing3(root + "iva");

                cs.setImperfectplural1(root + "ivan");
                cs.setImperfectplural2(root + "ivas");
                cs.setImperfectplural3(root + "ivan");
                break;

            case "2":
            case "3":
                cs.setImperfectsing1(root + "eva");
                cs.setImperfectsing2(root + "evas");
                cs.setImperfectsing3(root + "eva");

                cs.setImperfectplural1(root + "evan");
                cs.setImperfectplural2(root + "evas");
                cs.setImperfectplural3(root + "evan");
                break;
        }
    }

    private void setConjunctiv(String root, RumantschGrischunConjugationStructure cs) {
        switch (cs.getConjugationclass().id) {
            case "5":
                cs.setConjunctivsing1(root + "eschia");
                cs.setConjunctivsing2(root + "eschias");
                cs.setConjunctivsing3(root + "eschia");

                cs.setConjunctivplural1(root + "eschian");
                cs.setConjunctivplural2(root + "eschias");
                cs.setConjunctivplural3(root + "eschian");
                break;

            default:
                cs.setConjunctivsing1(root + "ia");
                cs.setConjunctivsing2(root + "ias");
                cs.setConjunctivsing3(root + "ia");

                cs.setConjunctivplural1(root + "ian");
                cs.setConjunctivplural2(root + "ias");
                cs.setConjunctivplural3(root + "ian");
                break;
        }
    }

    private void setCundizional(String root, RumantschGrischunConjugationStructure cs) {
        switch (cs.getConjugationclass().id) {
            case "1":
            case "6":
            case "7":
                cs.setCundizionalsing1(root + "ass");
                cs.setCundizionalsing2(root + "assas");
                cs.setCundizionalsing3(root + "ass");

                cs.setCundizionalplural1(root + "assan");
                cs.setCundizionalplural2(root + "assas");
                cs.setCundizionalplural3(root + "assan");
                break;

            case "4":
            case "5":
                cs.setCundizionalsing1(root + "iss");
                cs.setCundizionalsing2(root + "issas");
                cs.setCundizionalsing3(root + "iss");

                cs.setCundizionalplural1(root + "issan");
                cs.setCundizionalplural2(root + "issas");
                cs.setCundizionalplural3(root + "issan");
                break;

            case "2":
            case "3":
                cs.setCundizionalsing1(root + "ess");
                cs.setCundizionalsing2(root + "essas");
                cs.setCundizionalsing3(root + "ess");

                cs.setCundizionalplural1(root + "essan");
                cs.setCundizionalplural2(root + "essas");
                cs.setCundizionalplural3(root + "essan");
                break;
        }
    }

    private void setParticipPerfect(String root, RumantschGrischunConjugationStructure cs) {
        switch (cs.getConjugationclass().id) {
            case "1":
            case "6":
            case "7":
                cs.setParticipperfectms(root + "à");
                cs.setParticipperfectfs(root + "ada");
                cs.setParticipperfectmp(root + "ads");
                cs.setParticipperfectfp(root + "adas");
                break;

            default:
                cs.setParticipperfectms(root + "ì");
                cs.setParticipperfectfs(root + "ida");
                cs.setParticipperfectmp(root + "ids");
                cs.setParticipperfectfp(root + "idas");
                break;

        }

    }

    private void setGerundium(String root, RumantschGrischunConjugationStructure cs) {
        switch (getEnding()) {
            case "ar":
                cs.setGerundium(root + "ond");
                break;
            case "er":
            case "air":
                cs.setGerundium(root + "end");
                break;
            case "ir":
                cs.setGerundium(root + "ind");
                break;
            default:
                break;
        }
    }

    private void setImperativ(String root, RumantschGrischunConjugationStructure cs) {
        switch (cs.getConjugationclass().id) {
            case "5":
            case "6":
                cs.setImperativ1(root + "escha!");

                switch (getEnding()) {
                    case "ar":
                        cs.setImperativ2(root + "ai!");
                        break;
                    case "er":
                        cs.setImperativ2(root + "si!");
                        break;
                    case "ir":
                    case "air":
                        cs.setImperativ2(root + "i!");
                        break;
                }
                break;

            default:
                cs.setImperativ1(root + "a!");
                cs.setImperativ2(root + "ai!");
                break;
        }
    }

    private void setFutur(String root, RumantschGrischunConjugationStructure cs) {
        char startChar = root.charAt(0);
        switch (getIsReflexive()) {
            case "true":
                if (isVocal(startChar)) {
                    cs.setFutursing1("vegn a " + RumantschGrischunPronouns.pron_r_v_1ps + cs.getInfinitiv());
                    cs.setFutursing2("vengs a " + RumantschGrischunPronouns.pron_r_v_2ps + cs.getInfinitiv());
                    cs.setFutursing3("vegn a " + RumantschGrischunPronouns.pron_r_v_3ps + cs.getInfinitiv());

                    cs.setFuturplural1("vengnin ad " + RumantschGrischunPronouns.pron_r_v_1pp + cs.getInfinitiv());
                    cs.setFuturplural2("vegnis ad " + RumantschGrischunPronouns.pron_r_v_2pp + cs.getInfinitiv());
                    cs.setFuturplural3("vegnan a " + RumantschGrischunPronouns.pron_r_v_3pp + cs.getInfinitiv());

                } else {
                    cs.setFutursing1("vegn a " + RumantschGrischunPronouns.pron_r_1ps + cs.getInfinitiv());
                    cs.setFutursing2("vengs a " + RumantschGrischunPronouns.pron_r_2ps + cs.getInfinitiv());
                    cs.setFutursing3("vegn a " + RumantschGrischunPronouns.pron_r_3ps + cs.getInfinitiv());

                    cs.setFuturplural1("vengnin ad " + RumantschGrischunPronouns.pron_r_1pp + cs.getInfinitiv());
                    cs.setFuturplural2("vegnis ad " + RumantschGrischunPronouns.pron_r_2pp + cs.getInfinitiv());
                    cs.setFuturplural3("vegnan a " + RumantschGrischunPronouns.pron_r_3pp + cs.getInfinitiv());

                }
                break;

            case "false":
                if (isVocal(startChar)) {
                    cs.setFutursing1("vegn ad " + cs.getInfinitiv());
                    cs.setFutursing2("vengs ad " + cs.getInfinitiv());
                    cs.setFutursing3("vegn ad " + cs.getInfinitiv());
                    cs.setFuturplural1("vengnin ad " + cs.getInfinitiv());
                    cs.setFuturplural2("vegnis ad " + cs.getInfinitiv());
                    cs.setFuturplural3("vegnan ad " + cs.getInfinitiv());

                } else {
                    cs.setFutursing1("vegn a " + cs.getInfinitiv());
                    cs.setFutursing2("vengs a " + cs.getInfinitiv());
                    cs.setFutursing3("vegn a " + cs.getInfinitiv());
                    cs.setFuturplural1("vengnin a " + cs.getInfinitiv());
                    cs.setFuturplural2("vegnis a " + cs.getInfinitiv());
                    cs.setFuturplural3("vegnan a " + cs.getInfinitiv());
                }
                break;
        }
    }

    private HashMap<String, String> addPronouns(HashMap<String, String> conjugation, InflectionSubType subType) {
        Map<String, String> pronouns;

        String verb = conjugation.get("verb");

        if (verb.startsWith("sa ")) {
            // Reflexive Verbs that start with Consonants
            pronouns = pronounsForReflexiveConsonantalVerbs();
            return addReflexivePronouns(conjugation, pronouns, subType);

        } else if (verb.startsWith("s'")) {
            // Reflexive Verbs that start with Vocals
            pronouns = pronounsForReflexiveVocalicVerbs();
            return addReflexivePronouns(conjugation, pronouns, subType);

        } else {
            // Standard Verbs
            return addStandardPronouns(conjugation, subType);
        }
    }

    private Map<String, String> pronounsForReflexiveConsonantalVerbs() {
        RumantschGrischunPronouns pronouns = new RumantschGrischunPronouns();
        // STANDARD
        pronouns.setFirstPs(RumantschGrischunPronouns.pron_1ps + RumantschGrischunPronouns.pron_r_1ps);
        pronouns.setSecondPs(RumantschGrischunPronouns.pron_2ps + RumantschGrischunPronouns.pron_r_2ps);
        pronouns.setThirdPs(RumantschGrischunPronouns.pron_3ps + RumantschGrischunPronouns.pron_r_3ps);
        pronouns.setFirstPp(RumantschGrischunPronouns.pron_1pp + RumantschGrischunPronouns.pron_r_1pp);
        pronouns.setSecondPp(RumantschGrischunPronouns.pron_2pp + RumantschGrischunPronouns.pron_r_2pp);
        pronouns.setThirdPp(RumantschGrischunPronouns.pron_3pp + RumantschGrischunPronouns.pron_r_3pp);

        // CONJUNCTIV
        pronouns.setFirstPsC(RumantschGrischunPronouns.pron_conjunctiv_c + RumantschGrischunPronouns.pron_1ps + RumantschGrischunPronouns.pron_r_1ps);
        pronouns.setSecondPsC(RumantschGrischunPronouns.pron_conjunctiv_c + RumantschGrischunPronouns.pron_2ps + RumantschGrischunPronouns.pron_r_2ps);
        pronouns.setThirdPsC(RumantschGrischunPronouns.pron_conjunctiv_v + RumantschGrischunPronouns.pron_3ps + RumantschGrischunPronouns.pron_r_3ps);
        pronouns.setFirstPpC(RumantschGrischunPronouns.pron_conjunctiv_c + RumantschGrischunPronouns.pron_1pp + RumantschGrischunPronouns.pron_r_1pp);
        pronouns.setSecondPpC(RumantschGrischunPronouns.pron_conjunctiv_c + RumantschGrischunPronouns.pron_2pp + RumantschGrischunPronouns.pron_r_2pp);
        pronouns.setThirdPpC(RumantschGrischunPronouns.pron_conjunctiv_v + RumantschGrischunPronouns.pron_3pp + RumantschGrischunPronouns.pron_r_3pp);

        // PARTICIP PERFECT
        pronouns.setPp_1(RumantschGrischunPronouns.pp_r1 + " " + RumantschGrischunPronouns.pron_r_3ps);
        pronouns.setPp_2(RumantschGrischunPronouns.pp_r2 + " " + RumantschGrischunPronouns.pron_r_3ps);

        // IMPERATIV
        pronouns.setImperat1(RumantschGrischunPronouns.pron_r_2ps);
        pronouns.setImperat2(RumantschGrischunPronouns.pron_r_2pp);

        // GERUNDIUM
        pronouns.setGer(RumantschGrischunPronouns.pron_r_3ps);

        return pronouns.getValues();
    }

    private Map<String, String> pronounsForReflexiveVocalicVerbs() {
        RumantschGrischunPronouns pronouns = new RumantschGrischunPronouns();
        // STANDARD
        pronouns.setFirstPs(RumantschGrischunPronouns.pron_1ps + RumantschGrischunPronouns.pron_r_v_1ps);
        pronouns.setSecondPs(RumantschGrischunPronouns.pron_2ps + RumantschGrischunPronouns.pron_r_v_2ps);
        pronouns.setThirdPs(RumantschGrischunPronouns.pron_3ps + RumantschGrischunPronouns.pron_r_v_3ps);
        pronouns.setFirstPp(RumantschGrischunPronouns.pron_1pp + RumantschGrischunPronouns.pron_r_v_1pp);
        pronouns.setSecondPp(RumantschGrischunPronouns.pron_2pp + RumantschGrischunPronouns.pron_r_v_2pp);
        pronouns.setThirdPp(RumantschGrischunPronouns.pron_3pp + RumantschGrischunPronouns.pron_r_v_3pp);

        // CONJUNCTIV
        pronouns.setFirstPsC(RumantschGrischunPronouns.pron_conjunctiv_c + RumantschGrischunPronouns.pron_1ps + RumantschGrischunPronouns.pron_r_v_1ps);
        pronouns.setSecondPsC(RumantschGrischunPronouns.pron_conjunctiv_c + RumantschGrischunPronouns.pron_2ps + RumantschGrischunPronouns.pron_r_v_2ps);
        pronouns.setThirdPsC(RumantschGrischunPronouns.pron_conjunctiv_v + RumantschGrischunPronouns.pron_3ps + RumantschGrischunPronouns.pron_r_v_3ps);
        pronouns.setFirstPpC(RumantschGrischunPronouns.pron_conjunctiv_c + RumantschGrischunPronouns.pron_1pp + RumantschGrischunPronouns.pron_r_v_1pp);
        pronouns.setSecondPpC(RumantschGrischunPronouns.pron_conjunctiv_c + RumantschGrischunPronouns.pron_2pp + RumantschGrischunPronouns.pron_r_v_2pp);
        pronouns.setThirdPpC(RumantschGrischunPronouns.pron_conjunctiv_v + RumantschGrischunPronouns.pron_3pp + RumantschGrischunPronouns.pron_r_v_3pp);

        // PARTICIP PERFECT
        pronouns.setPp_1(RumantschGrischunPronouns.pp_r1 + " " + RumantschGrischunPronouns.pron_r_v_3ps);
        pronouns.setPp_2(RumantschGrischunPronouns.pp_r2 + " " + RumantschGrischunPronouns.pron_r_v_3ps);

        // IMPERATIV
        pronouns.setImperat1(RumantschGrischunPronouns.pron_r_v_2ps);
        pronouns.setImperat2(RumantschGrischunPronouns.pron_r_v_2pp);

        // GERUNDIUM
        pronouns.setGer(RumantschGrischunPronouns.pron_r_v_3ps);

        return pronouns.getValues();
    }

    private HashMap<String, String> addReflexivePronouns(Map<String, String> conjugation, Map<String, String> pronouns, InflectionSubType subType) {
        cs = new RumantschGrischunConjugationStructure();
        cs.setVerb(conjugation.get("verb"));
        cs.setInfinitiv(conjugation.get(RumantschGrischunConjugationStructure.infinitiv));
        cs.setRoot(conjugation.get(RumantschGrischunConjugationStructure.root));
        cs.setEnding(conjugation.get(RumantschGrischunConjugationStructure.ending));
        cs.setReflexive(conjugation.get(RumantschGrischunConjugationStructure.reflexive));
        cs.setConjugationClass(subType);

        // PRESCHENT
        cs.setPreschentsing1(pronouns.get(RumantschGrischunPronouns.first_ps) + conjugation.get(RumantschGrischunConjugationStructure.preschentsing1));
        cs.setPreschentsing2(pronouns.get(RumantschGrischunPronouns.second_ps) + conjugation.get(RumantschGrischunConjugationStructure.preschentsing2));
        cs.setPreschentsing3(pronouns.get(RumantschGrischunPronouns.third_ps) + conjugation.get(RumantschGrischunConjugationStructure.preschentsing3));
        cs.setPreschentplural1(pronouns.get(RumantschGrischunPronouns.first_pp) + conjugation.get(RumantschGrischunConjugationStructure.preschentplural1));
        cs.setPreschentplural2(pronouns.get(RumantschGrischunPronouns.second_pp) + conjugation.get(RumantschGrischunConjugationStructure.preschentplural2));
        cs.setPreschentplural3(pronouns.get(RumantschGrischunPronouns.third_pp) + conjugation.get(RumantschGrischunConjugationStructure.preschentplural3));

        // IMPERFECT
        cs.setImperfectsing1(pronouns.get(RumantschGrischunPronouns.first_ps) + conjugation.get(RumantschGrischunConjugationStructure.imperfectsing1));
        cs.setImperfectsing2(pronouns.get(RumantschGrischunPronouns.second_ps) + conjugation.get(RumantschGrischunConjugationStructure.imperfectsing2));
        cs.setImperfectsing3(pronouns.get(RumantschGrischunPronouns.third_ps) + conjugation.get(RumantschGrischunConjugationStructure.imperfectsing3));
        cs.setImperfectplural1(pronouns.get(RumantschGrischunPronouns.first_pp) + conjugation.get(RumantschGrischunConjugationStructure.imperfectplural1));
        cs.setImperfectplural2(pronouns.get(RumantschGrischunPronouns.second_pp) + conjugation.get(RumantschGrischunConjugationStructure.imperfectplural2));
        cs.setImperfectplural3(pronouns.get(RumantschGrischunPronouns.third_pp) + conjugation.get(RumantschGrischunConjugationStructure.imperfectplural3));

        // CONJUNCTIV
        cs.setConjunctivsing1(pronouns.get(RumantschGrischunPronouns.first_ps_c) + conjugation.get(RumantschGrischunConjugationStructure.conjunctivsing1));
        cs.setConjunctivsing2(pronouns.get(RumantschGrischunPronouns.second_ps_c) + conjugation.get(RumantschGrischunConjugationStructure.conjunctivsing2));
        cs.setConjunctivsing3(pronouns.get(RumantschGrischunPronouns.third_ps_c) + conjugation.get(RumantschGrischunConjugationStructure.conjunctivsing3));
        cs.setConjunctivplural1(pronouns.get(RumantschGrischunPronouns.first_pp_c) + conjugation.get(RumantschGrischunConjugationStructure.conjunctivplural1));
        cs.setConjunctivplural2(pronouns.get(RumantschGrischunPronouns.second_pp_c) + conjugation.get(RumantschGrischunConjugationStructure.conjunctivplural2));
        cs.setConjunctivplural3(pronouns.get(RumantschGrischunPronouns.third_pp_c) + conjugation.get(RumantschGrischunConjugationStructure.conjunctivplural3));

        // CUNDIZIONAL
        cs.setCundizionalsing1(pronouns.get(RumantschGrischunPronouns.first_ps) + conjugation.get(RumantschGrischunConjugationStructure.cundizionalsing1));
        cs.setCundizionalsing2(pronouns.get(RumantschGrischunPronouns.second_ps) + conjugation.get(RumantschGrischunConjugationStructure.cundizionalsing2));
        cs.setCundizionalsing3(pronouns.get(RumantschGrischunPronouns.third_ps) + conjugation.get(RumantschGrischunConjugationStructure.cundizionalsing3));
        cs.setCundizionalplural1(pronouns.get(RumantschGrischunPronouns.first_pp) + conjugation.get(RumantschGrischunConjugationStructure.cundizionalplural1));
        cs.setCundizionalplural2(pronouns.get(RumantschGrischunPronouns.second_pp) + conjugation.get(RumantschGrischunConjugationStructure.cundizionalplural2));
        cs.setCundizionalplural3(pronouns.get(RumantschGrischunPronouns.third_pp) + conjugation.get(RumantschGrischunConjugationStructure.cundizionalplural3));

        // IMPERATIV
        cs.setImperativ1(pronouns.get(RumantschGrischunPronouns.imperat_1) + conjugation.get(RumantschGrischunConjugationStructure.imperativ1));
        cs.setImperativ2(pronouns.get(RumantschGrischunPronouns.imperat_2) + conjugation.get(RumantschGrischunConjugationStructure.imperativ2));

        // PARTICIP_PERFECT
        cs.setParticipperfectms(pronouns.get(RumantschGrischunPronouns.pp_1) + conjugation.get(RumantschGrischunConjugationStructure.participperfectms));
        cs.setParticipperfectfs(pronouns.get(RumantschGrischunPronouns.pp_2) + conjugation.get(RumantschGrischunConjugationStructure.participperfectms) + "/" + conjugation.get(RumantschGrischunConjugationStructure.participperfectfs));

        // GERUNDIUM
        cs.setGerundium(pronouns.get(RumantschGrischunPronouns.gerund) + conjugation.get(RumantschGrischunConjugationStructure.gerundium));

        // FUTUR
        cs.setFutursing1(RumantschGrischunPronouns.pron_1ps + conjugation.get(RumantschGrischunConjugationStructure.futursing1));

        cs.setFutursing2(RumantschGrischunPronouns.pron_2ps + conjugation.get(RumantschGrischunConjugationStructure.futursing2));

        cs.setFutursing3(RumantschGrischunPronouns.pron_3ps + conjugation.get(RumantschGrischunConjugationStructure.futursing3));

        cs.setFuturplural1(RumantschGrischunPronouns.pron_1pp + conjugation.get(RumantschGrischunConjugationStructure.futurplural1));

        cs.setFuturplural2(RumantschGrischunPronouns.pron_2pp + conjugation.get(RumantschGrischunConjugationStructure.futurplural2));

        cs.setFuturplural3(RumantschGrischunPronouns.pron_3pp + conjugation.get(RumantschGrischunConjugationStructure.futurplural3));

        return cs.getAllFormValues();

    }

    private HashMap<String, String> addStandardPronouns(Map<String, String> conjugation, InflectionSubType subType) {

        RumantschGrischunConjugationStructure cs = new RumantschGrischunConjugationStructure();

        cs.setVerb(conjugation.get("verb"));
        cs.setInfinitiv(conjugation.get(RumantschGrischunConjugationStructure.infinitiv));
        cs.setRoot(conjugation.get(RumantschGrischunConjugationStructure.root));
        cs.setEnding(conjugation.get(RumantschGrischunConjugationStructure.ending));
        cs.setReflexive(conjugation.get(RumantschGrischunConjugationStructure.reflexive));
        cs.setConjugationClass(subType);

        // PRESCHENT
        cs.setPreschentsing1(RumantschGrischunPronouns.pron_1ps + conjugation.get(RumantschGrischunConjugationStructure.preschentsing1));
        cs.setPreschentsing2(RumantschGrischunPronouns.pron_2ps + conjugation.get(RumantschGrischunConjugationStructure.preschentsing2));
        cs.setPreschentsing3(RumantschGrischunPronouns.pron_3ps + conjugation.get(RumantschGrischunConjugationStructure.preschentsing3));
        cs.setPreschentplural1(RumantschGrischunPronouns.pron_1pp + conjugation.get(RumantschGrischunConjugationStructure.preschentplural1));
        cs.setPreschentplural2(RumantschGrischunPronouns.pron_2pp + conjugation.get(RumantschGrischunConjugationStructure.preschentplural2));
        cs.setPreschentplural3(RumantschGrischunPronouns.pron_3pp + conjugation.get(RumantschGrischunConjugationStructure.preschentplural3));

        // IMPERFECT
        cs.setImperfectsing1(RumantschGrischunPronouns.pron_1ps + conjugation.get(RumantschGrischunConjugationStructure.imperfectsing1));
        cs.setImperfectsing2(RumantschGrischunPronouns.pron_2ps + conjugation.get(RumantschGrischunConjugationStructure.imperfectsing2));
        cs.setImperfectsing3(RumantschGrischunPronouns.pron_3ps + conjugation.get(RumantschGrischunConjugationStructure.imperfectsing3));
        cs.setImperfectplural1(RumantschGrischunPronouns.pron_1pp + conjugation.get(RumantschGrischunConjugationStructure.imperfectplural1));
        cs.setImperfectplural2(RumantschGrischunPronouns.pron_2pp + conjugation.get(RumantschGrischunConjugationStructure.imperfectplural2));
        cs.setImperfectplural3(RumantschGrischunPronouns.pron_3pp + conjugation.get(RumantschGrischunConjugationStructure.imperfectplural3));

        // CONJUNCTIV
        cs.setConjunctivsing1(RumantschGrischunPronouns.pron_conjunctiv_c + RumantschGrischunPronouns.pron_1ps + conjugation.get(RumantschGrischunConjugationStructure.conjunctivsing1));
        cs.setConjunctivsing2(RumantschGrischunPronouns.pron_conjunctiv_c + RumantschGrischunPronouns.pron_2ps + conjugation.get(RumantschGrischunConjugationStructure.conjunctivsing2));
        cs.setConjunctivsing3(RumantschGrischunPronouns.pron_conjunctiv_v + RumantschGrischunPronouns.pron_3ps + conjugation.get(RumantschGrischunConjugationStructure.conjunctivsing3));
        cs.setConjunctivplural1(RumantschGrischunPronouns.pron_conjunctiv_c + RumantschGrischunPronouns.pron_1pp + conjugation.get(RumantschGrischunConjugationStructure.conjunctivplural1));
        cs.setConjunctivplural2(RumantschGrischunPronouns.pron_conjunctiv_c + RumantschGrischunPronouns.pron_2pp + conjugation.get(RumantschGrischunConjugationStructure.conjunctivplural2));
        cs.setConjunctivplural3(RumantschGrischunPronouns.pron_conjunctiv_v + RumantschGrischunPronouns.pron_3pp + conjugation.get(RumantschGrischunConjugationStructure.conjunctivplural3));

        // CUNDIZIONAL
        cs.setCundizionalsing1(RumantschGrischunPronouns.pron_1ps + conjugation.get(RumantschGrischunConjugationStructure.cundizionalsing1));
        cs.setCundizionalsing2(RumantschGrischunPronouns.pron_2ps + conjugation.get(RumantschGrischunConjugationStructure.cundizionalsing2));
        cs.setCundizionalsing3(RumantschGrischunPronouns.pron_3ps + conjugation.get(RumantschGrischunConjugationStructure.cundizionalsing3));
        cs.setCundizionalplural1(RumantschGrischunPronouns.pron_1pp + conjugation.get(RumantschGrischunConjugationStructure.cundizionalplural1));
        cs.setCundizionalplural2(RumantschGrischunPronouns.pron_2pp + conjugation.get(RumantschGrischunConjugationStructure.cundizionalplural2));
        cs.setCundizionalplural3(RumantschGrischunPronouns.pron_3pp + conjugation.get(RumantschGrischunConjugationStructure.cundizionalplural3));

        // IMPERATIV
        cs.setImperativ1(conjugation.get(RumantschGrischunConjugationStructure.imperativ1));
        cs.setImperativ2(conjugation.get(RumantschGrischunConjugationStructure.imperativ2));

        // PARTICIP_PERFECT
        cs.setParticipperfectms(conjugation.get(RumantschGrischunConjugationStructure.participperfectms));
        cs.setParticipperfectfs(conjugation.get(RumantschGrischunConjugationStructure.participperfectfs));
        cs.setParticipperfectmp(conjugation.get(RumantschGrischunConjugationStructure.participperfectmp));
        cs.setParticipperfectfp(conjugation.get(RumantschGrischunConjugationStructure.participperfectfp));

        // GERUNDIUM
        cs.setGerundium(conjugation.get(RumantschGrischunConjugationStructure.gerundium));

        // FUTUR
        cs.setFutursing1(RumantschGrischunPronouns.pron_1ps + conjugation.get(RumantschGrischunConjugationStructure.futursing1));
        cs.setFutursing2(RumantschGrischunPronouns.pron_2ps + conjugation.get(RumantschGrischunConjugationStructure.futursing2));
        cs.setFutursing3(RumantschGrischunPronouns.pron_3ps + conjugation.get(RumantschGrischunConjugationStructure.futursing3));
        cs.setFuturplural1(RumantschGrischunPronouns.pron_1pp + conjugation.get(RumantschGrischunConjugationStructure.futurplural1));
        cs.setFuturplural2(RumantschGrischunPronouns.pron_2pp + conjugation.get(RumantschGrischunConjugationStructure.futurplural2));
        cs.setFuturplural3(RumantschGrischunPronouns.pron_3pp + conjugation.get(RumantschGrischunConjugationStructure.futurplural3));

        return cs.getAllFormValues();
    }

    private void resetValues() {
        cs = null;
        infinitiv = null;
        verb = null;
        lastTwo = null;
        lastThree = null;
        conjugation = null;
        isReflexive = null;
        root = null;
        ending = null;
        modRoot = null;
    }
}
