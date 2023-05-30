package ch.pledarigrond.inflection.generation.vallader;

import ch.pledarigrond.inflection.generation.generic.LanguageConjugation;
import ch.pledarigrond.inflection.model.InflectionResponse;
import ch.pledarigrond.inflection.model.InflectionSubType;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ValladerConjugation extends LanguageConjugation {

    private char vowelInRoot;

    private ValladerConjugationStructure cs;

    private String infinitiv;
    private String verb;

    private String isReflexive;

    private HashMap<String, String> conjugation;

    public InflectionResponse generateConjugation(String conjugationClass, String infinitiv) {
        resetValues();

        root = getRoot(infinitiv);

        if (conjugationClass.equals("1a")) {
            modRoot = root + "esch";
        }

        if (conjugationClass.equals("4a")) {
            modRoot = root + "isch";
        }

        if (infinitiv.endsWith("ger") || infinitiv.endsWith("gir") || infinitiv.endsWith("glir")) {
            modRoot = infinitiv.substring(0, infinitiv.length() - 2) + "i";
        }

        if (modRoot == null) {
            modRoot = root;
        }

        InflectionSubType subType = ValladerConjugationClasses.getConjugationClass(conjugationClass);
        if (subType == null) {
            throw new RuntimeException(conjugationClass + " is not a valid conjugation class.");
        } else if (getEnding() == null) {
            throw new RuntimeException(infinitiv + "  is not a valid verb. Please enter a verb in its infinitive form.");
        }

        conjugation = conjugate(root, subType);

        return new InflectionResponse(subType, addPronouns(conjugation, subType));
    }

    public InflectionResponse guessInflection(String baseForm, String genus, String flex) {
        if (baseForm.length() < 3) {
            return null;
        }

        String lastTwo = baseForm.substring(baseForm.length() - 2);
        String lastThree = baseForm.substring(baseForm.length() - 3);

        if (lastThree.equals("air")) {
            return generateConjugation("2", baseForm);
        }

        if (lastTwo.equals("ar")) {
            if (flex != null && flex.contains("-esch")) {
                return generateConjugation("1a", baseForm);
            }

            return generateConjugation("1", baseForm);
        }

        if (lastTwo.equals("er")) {
            return generateConjugation("3", baseForm);
        }

        if (lastTwo.equals("ir")) {
            if (flex != null && flex.contains("-esch")) {
                return generateConjugation("4a", baseForm);
            }

            return generateConjugation("4", baseForm);
        }

        return null;
    }

    public static ValladerConjugationStructure addEncliticForms(ValladerConjugationStructure cs) {
        ValladerConjugation.setPreschentEnclitic(cs);
        ValladerConjugation.setImperfectEnclitic(cs);
        ValladerConjugation.setCundizionalEnclitic(cs);
        ValladerConjugation.setFuturEnclitic(cs);

        return cs;
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

            if (infinitiv.length() < 3) {
                    throw new RuntimeException("'" + infinitiv + "' is not a valid verb. Please enter a verb in its infinitive form.");
            }

            infinitiv = extractInfinitiv(infinitiv);
        }
        return infinitiv;
    }

    private String extractInfinitiv(String infintiv) {
        String l2 = infintiv.substring(infintiv.length() - 2);
        String l3 = infintiv.substring(infintiv.length() - 3);

        if (l3.equals("air")) {
            setEnding(l3);
            infintiv = checkReflexiveness(infintiv);
            infintiv = infintiv.substring(0, infintiv.length() - 3);

            return infintiv;
        }

        setEnding(l2);
        infintiv = checkReflexiveness(infintiv);
        infintiv = infintiv.substring(0, infintiv.length() - 2);

        return infintiv;
    }

    private String checkReflexiveness(String query) {
        if (query.startsWith("as ")) {
            setVerb(query);
            query = query.length() > 3 ? query.substring(3) : query;
            setIsReflexive("true");

        } else if (query.startsWith("s'")) {
            setVerb(query);
            query = query.length() > 2 ? query.substring(2) : query;
            setIsReflexive("true");
        } else {
            setIsReflexive("false");
            setVerb(query);
        }

        setInfinitiv(query);

        return query;
    }

    private HashMap<String, String> conjugate(String root, InflectionSubType conjugationClass) {

        cs = new ValladerConjugationStructure();
        cs.setVerb(getVerb());
        cs.setInfinitiv(getInfinitiv());
        cs.setRoot(root);
        cs.setEnding(getEnding());

        cs.setConjugationClass(conjugationClass);

        cs.setReflexive(getIsReflexive());

        setPreschent(cs);
        setImperfect(cs);
        setConjunctiv(cs);
        setCundizional(cs);
        setParticipPerfect(cs);
        setImperativ(cs);
        setGerundium(cs);
        setFutur(cs);

        setPreschentEnclitic(cs);
        setImperfectEnclitic(cs);
        setCundizionalEnclitic(cs);
        setFuturEnclitic(cs);

        return cs.getValues();
    }

    private void setPreschent(ValladerConjugationStructure cs) {
        switch (cs.getConjugationclass().id) {
            case "1":
            case "1a":
            case "2":
            case "3":
                if(cs.getInfinitiv().endsWith("giar") || cs.getInfinitiv().endsWith("gliar") || cs.getInfinitiv().endsWith("ger")) {
                    cs.setPreschentsing1(modRoot.substring(0, modRoot.length() - 1));
                } else if (
                        cs.getInfinitiv().endsWith("ffar") ||
                                cs.getInfinitiv().endsWith("ffer") ||
                                cs.getInfinitiv().endsWith("llar") ||
                                cs.getInfinitiv().endsWith("ller") ||
                                cs.getInfinitiv().endsWith("mmar") ||
                                cs.getInfinitiv().endsWith("mmer") ||
                                cs.getInfinitiv().endsWith("nnar") ||
                                cs.getInfinitiv().endsWith("nner") ||
                                cs.getInfinitiv().endsWith("ppar") ||
                                cs.getInfinitiv().endsWith("pper") ||
                                cs.getInfinitiv().endsWith("rrar") ||
                                cs.getInfinitiv().endsWith("rrer") ||
                                cs.getInfinitiv().endsWith("ttar") ||
                                cs.getInfinitiv().endsWith("tter") ||
                                cs.getInfinitiv().endsWith("zzar") ||
                                cs.getInfinitiv().endsWith("zzer")
                ) {
                    cs.setPreschentsing1(modRoot.substring(0, modRoot.length() - 1));
                } else {
                    cs.setPreschentsing1(modRoot);
                }
                cs.setPreschentsing2(modRoot + "ast");
                cs.setPreschentsing3(modRoot + "a");
                if (cs.getInfinitiv().endsWith("ger")) {
                    cs.setPreschentplural1(modRoot + "ain");
                    cs.setPreschentplural2(modRoot + "ais\n(" + modRoot + "aivat)");
                } else {
                    cs.setPreschentplural1(root + "ain");
                    cs.setPreschentplural2(root + "ais\n(" + root + "aivat)");
                }
                cs.setPreschentplural3(modRoot + "an");
                break;


            case "4":
            case "4a":
                if(cs.getInfinitiv().endsWith("gir") || cs.getInfinitiv().endsWith("glir")) {
                    cs.setPreschentsing1(modRoot.substring(0, modRoot.length() - 1));
                } else {
                    cs.setPreschentsing1(modRoot);
                }
                cs.setPreschentsing2(modRoot + "ast");
                cs.setPreschentsing3(modRoot + "a");
                cs.setPreschentplural1(root + "in");
                cs.setPreschentplural2(root + "is\n(" + root + "ivat)");
                cs.setPreschentplural3(modRoot + "an");
                break;
        }

    }

    private void setImperfect(ValladerConjugationStructure cs) {

        switch (cs.getConjugationclass().id.substring(0, 1)) {

            case "1":
            case "2":
            case "3":
                cs.setImperfectsing1(root + "aiva");
                cs.setImperfectsing2(root + "aivast");
                cs.setImperfectsing3(root + "aiva");
                cs.setImperfectplural1(root + "aivan");
                cs.setImperfectplural2(root + "aivat");
                cs.setImperfectplural3(root + "aivan");
                break;

            case "4":
                cs.setImperfectsing1(root + "iva");
                cs.setImperfectsing2(root + "ivast");
                cs.setImperfectsing3(root + "iva");
                cs.setImperfectplural1(root + "ivan");
                cs.setImperfectplural2(root + "ivat");
                cs.setImperfectplural3(root + "ivan");
                break;
        }

    }

    private void setConjunctiv(ValladerConjugationStructure cs) {
        switch (cs.getConjugationclass().id) {
            default:
                cs.setConjunctivsing1(modRoot + "a");
                cs.setConjunctivsing2(modRoot + "ast");
                cs.setConjunctivsing3(modRoot + "a");
                cs.setConjunctivplural1(modRoot + "an");
                cs.setConjunctivplural2(modRoot + "at");
                cs.setConjunctivplural3(modRoot + "an");
                break;
        }
    }

    private void setCundizional(ValladerConjugationStructure cs) {
        switch (cs.getConjugationclass().id.substring(0, 1)) {
            case "4":
                cs.setCundizionalsing1(root + "iss");
                cs.setCundizionalsing2(root + "issast");
                cs.setCundizionalsing3(root + "iss");
                cs.setCundizionalplural1(root + "issan");
                cs.setCundizionalplural2(root + "issat");
                cs.setCundizionalplural3(root + "issan");
                break;

            default:
                cs.setCundizionalsing1(root + "ess");
                cs.setCundizionalsing2(root + "essast");
                cs.setCundizionalsing3(root + "ess");
                cs.setCundizionalplural1(root + "essan");
                cs.setCundizionalplural2(root + "essat");
                cs.setCundizionalplural3(root + "essan");
                break;
        }
    }

    private void setParticipPerfect(ValladerConjugationStructure cs) {

        switch (cs.getConjugationclass().id.substring(0, 1)) {
            case "1":
                cs.setParticipperfectms(root + "à");
                cs.setParticipperfectmp(root + "ats");
                cs.setParticipperfectfs(root + "ada");
                cs.setParticipperfectfp(root + "adas");
                break;

            case "4":
                cs.setParticipperfectms(root + "i");
                cs.setParticipperfectmp(root + "its");
                cs.setParticipperfectfs(root + "ida");
                cs.setParticipperfectfp(root + "idas");
                break;

            default:
                cs.setParticipperfectms(root + "ü");
                cs.setParticipperfectmp(root + "üts");
                cs.setParticipperfectfs(root + "üda");
                cs.setParticipperfectfp(root + "üdas");
                break;
        }
    }

    private void setGerundium(ValladerConjugationStructure cs) {

        switch (cs.getConjugationclass().id.substring(0, 1)) {
            case "4":
                cs.setGerundium(root + "ind");
                break;
            default:
                cs.setGerundium(root + "ond");
                break;
        }

    }

    private void setImperativ(ValladerConjugationStructure cs) {
        switch (cs.getConjugationclass().id.substring(0, 1)) {
            case "4":
                cs.setImperativ1(modRoot + "a");
                cs.setImperativ2(root + "i");
                cs.setImperativ3(getInfinitiv());
                cs.setImperativ4(root + "irai");
                cs.setImperativ5(modRoot + "a");
                cs.setImperativ6(modRoot + "an");
                break;


            default:
                cs.setImperativ1(modRoot + "a");
                cs.setImperativ2(root + "ai");
                cs.setImperativ3(getInfinitiv());
                cs.setImperativ4(root + "arai");
                cs.setImperativ5(modRoot + "a");
                cs.setImperativ6(modRoot + "an");
                break;
        }
    }

    private void setFutur(ValladerConjugationStructure cs) {
        switch (cs.getConjugationclass().id.substring(0, 1)) {
            case "4":
                cs.setFutursing1(root + "irà");
                cs.setFutursing2(root + "irast");
                cs.setFutursing3(root + "irà");
                cs.setFuturplural1(root + "iran");
                cs.setFuturplural2(root + "irat");
                cs.setFuturplural3(root + "iran");
                break;

            default:
                cs.setFutursing1(root + "arà");
                cs.setFutursing2(root + "arast");
                cs.setFutursing3(root + "arà");
                cs.setFuturplural1(root + "aran");
                cs.setFuturplural2(root + "arat");
                cs.setFuturplural3(root + "aran");
                break;
        }
    }


    private static void setPreschentEnclitic(ValladerConjugationStructure cs) {
        if (cs.getInfinitiv().endsWith("giar") ||
            cs.getInfinitiv().endsWith("gliar") ||
            cs.getInfinitiv().endsWith("ger") ||
            cs.getInfinitiv().endsWith("gir") ||
            cs.getInfinitiv().endsWith("glir")) {
            cs.setPreschentsing1Enclitic(cs.getPreschentsing1() + "ia");
            cs.setPreschentsing2Enclitic(cs.getPreschentsing2());
            cs.setPreschentsing3EncliticM(cs.getPreschentsing3() + "'l");
            cs.setPreschentsing3EncliticF(cs.getPreschentsing3().substring(0, cs.getPreschentsing3().length() - 2) + "'la");
            cs.setPreschentplural1Enclitic(cs.getPreschentplural1() + "a");
            cs.setPreschentplural2Enclitic(cs.getPreschentplural2());
            cs.setPreschentplural3Enclitic(cs.getPreschentplural3().substring(0, cs.getPreschentplural3().length() - 3) + "na");
        } else if (
                cs.getInfinitiv().endsWith("ffar") ||
                cs.getInfinitiv().endsWith("ffer") ||
                cs.getInfinitiv().endsWith("llar") ||
                cs.getInfinitiv().endsWith("ller") ||
                cs.getInfinitiv().endsWith("mmar") ||
                cs.getInfinitiv().endsWith("mmer") ||
                cs.getInfinitiv().endsWith("nnar") ||
                cs.getInfinitiv().endsWith("nner") ||
                cs.getInfinitiv().endsWith("ppar") ||
                cs.getInfinitiv().endsWith("pper") ||
                cs.getInfinitiv().endsWith("rrar") ||
                cs.getInfinitiv().endsWith("rrer") ||
                cs.getInfinitiv().endsWith("ttar") ||
                cs.getInfinitiv().endsWith("tter") ||
                cs.getInfinitiv().endsWith("zzar") ||
                cs.getInfinitiv().endsWith("zzer")
        ) {
            String consonant = cs.getPreschentsing1().substring(cs.getPreschentsing1().length() - 1);

            cs.setPreschentsing1Enclitic(cs.getPreschentsing1() + consonant + "a");
            cs.setPreschentsing2Enclitic(cs.getPreschentsing2());
            cs.setPreschentsing3EncliticM(cs.getPreschentsing3() + "'l");
            cs.setPreschentsing3EncliticF(cs.getPreschentsing3().substring(0, cs.getPreschentsing3().length() - 1) + "'la");
            cs.setPreschentplural1Enclitic(cs.getPreschentplural1() + "a");
            cs.setPreschentplural2Enclitic(cs.getPreschentplural2());
            cs.setPreschentplural3Enclitic(cs.getPreschentplural3().substring(0, cs.getPreschentplural3().length() - 3) + "na");
        } else {
            cs.setPreschentsing1Enclitic(cs.getPreschentsing1() + "a");
            cs.setPreschentsing2Enclitic(cs.getPreschentsing2());
            cs.setPreschentsing3EncliticM(cs.getPreschentsing3() + "'l");
            cs.setPreschentsing3EncliticF(cs.getPreschentsing3().substring(0, cs.getPreschentsing3().length() - 1) + "'la");
            cs.setPreschentplural1Enclitic(cs.getPreschentplural1() + "a");
            cs.setPreschentplural2Enclitic(cs.getPreschentplural2());
            cs.setPreschentplural3Enclitic(cs.getPreschentplural3().substring(0, cs.getPreschentplural3().length() - 2) + "na");
        }
    }

    private static void setImperfectEnclitic(ValladerConjugationStructure cs) {
        cs.setImperfectsing1Enclitic(cs.getImperfectsing1());
        cs.setImperfectsing2Enclitic(cs.getImperfectsing2());
        cs.setImperfectsing3EncliticM(cs.getImperfectsing3() + "'l");
        cs.setImperfectsing3EncliticF(cs.getImperfectsing3().substring(0, cs.getImperfectsing3().length() - 1) + "'la");
        cs.setImperfectplural1Enclitic(cs.getImperfectplural1().substring(0, cs.getImperfectplural1().length() - 2) + "na");
        cs.setImperfectplural2Enclitic(cs.getImperfectplural2());
        cs.setImperfectplural3Enclitic(cs.getImperfectplural3().substring(0, cs.getImperfectplural3().length() - 2) + "na");
    }

    private static void setCundizionalEnclitic(ValladerConjugationStructure cs) {
        cs.setCundizionalsing1Enclitic(cs.getCundizionalsing1() + "a");
        cs.setCundizionalsing2Enclitic(cs.getCundizionalsing2());
        cs.setCundizionalsing3EncliticM(cs.getCundizionalsing3() + "a'l");
        cs.setCundizionalsing3EncliticF(cs.getCundizionalsing3() + "'la");
        cs.setCundizionalplural1Enclitic(cs.getCundizionalplural1().substring(0, cs.getCundizionalplural1().length() - 2) + "na");
        cs.setCundizionalplural2Enclitic(cs.getCundizionalplural2());
        cs.setCundizionalplural3Enclitic(cs.getCundizionalplural3().substring(0, cs.getCundizionalplural3().length() - 2) + "na");
    }

    private static void setFuturEnclitic(ValladerConjugationStructure cs) {
        cs.setFutursing1Enclitic(cs.getFutursing1().substring(0, cs.getFutursing1().length() - 1) + "aja");
        cs.setFutursing2Enclitic(cs.getFutursing2());
        cs.setFutursing3EncliticM(cs.getFutursing3() + "'l");
        cs.setFutursing3EncliticF(cs.getFutursing3() + "'la");
        cs.setFuturplural1Enclitic(cs.getFuturplural1() + "a");
        cs.setFuturplural2Enclitic(cs.getFuturplural2());
        cs.setFuturplural3Enclitic(cs.getFuturplural3() + "a");
    }

    private HashMap<String, String> addPronouns(HashMap<String, String> conjugation, InflectionSubType subType) {
        String verb = conjugation.get("verb");

        if (verb.startsWith("as ")) {
            // Reflexive Verbs that start with consonants
            return addReflexivePronouns(conjugation, subType);

        } else if (verb.startsWith("s'")) {
            // Reflexive Verbs that start with vowels
            return addReflexivePronounsVowel(conjugation, subType);

        } else {
            // Standard Verbs
            return addStandardPronouns(conjugation, subType);
        }
    }

    private HashMap<String, String> addReflexivePronouns(Map<String, String> conjugation, InflectionSubType subType) {
        cs = new ValladerConjugationStructure();
        cs.setVerb(conjugation.get("verb"));
        cs.setInfinitiv(ValladerConjugationPronouns.pron_r_3ps + conjugation.get(ValladerConjugationStructure.infinitiv));
        cs.setRoot(conjugation.get(ValladerConjugationStructure.root));
        cs.setEnding(conjugation.get(ValladerConjugationStructure.ending));
        cs.setReflexive(conjugation.get(ValladerConjugationStructure.reflexive));
        cs.setConjugationClass(subType);

        // PRESCHENT
        cs.setPreschentsing1(setPronoun(ValladerConjugationPronouns.pron_1ps + ValladerConjugationPronouns.pron_r_1ps, conjugation.get(ValladerConjugationStructure.preschentsing1)));
        cs.setPreschentsing2(setPronoun(ValladerConjugationPronouns.pron_2ps + ValladerConjugationPronouns.pron_r_2ps, conjugation.get(ValladerConjugationStructure.preschentsing2)));
        cs.setPreschentsing3(setPronoun(ValladerConjugationPronouns.pron_3ps + ValladerConjugationPronouns.pron_r_3ps, conjugation.get(ValladerConjugationStructure.preschentsing3)));
        cs.setPreschentplural1(setPronoun(ValladerConjugationPronouns.pron_1pp + ValladerConjugationPronouns.pron_r_1pp, conjugation.get(ValladerConjugationStructure.preschentplural1)));
        cs.setPreschentplural2(setPronoun(ValladerConjugationPronouns.pron_2pp + ValladerConjugationPronouns.pron_r_2pp, conjugation.get(ValladerConjugationStructure.preschentplural2)));
        cs.setPreschentplural3(setPronoun(ValladerConjugationPronouns.pron_3pp + ValladerConjugationPronouns.pron_r_3pp, conjugation.get(ValladerConjugationStructure.preschentplural3)));

        // IMPERFECT
        cs.setImperfectsing1(setPronoun(ValladerConjugationPronouns.pron_1ps + ValladerConjugationPronouns.pron_r_1ps, conjugation.get(ValladerConjugationStructure.imperfectsing1)));
        cs.setImperfectsing2(setPronoun(ValladerConjugationPronouns.pron_2ps + ValladerConjugationPronouns.pron_r_2ps, conjugation.get(ValladerConjugationStructure.imperfectsing2)));
        cs.setImperfectsing3(setPronoun(ValladerConjugationPronouns.pron_3ps + ValladerConjugationPronouns.pron_r_3ps, conjugation.get(ValladerConjugationStructure.imperfectsing3)));
        cs.setImperfectplural1(setPronoun(ValladerConjugationPronouns.pron_1pp + ValladerConjugationPronouns.pron_r_1pp, conjugation.get(ValladerConjugationStructure.imperfectplural1)));
        cs.setImperfectplural2(setPronoun(ValladerConjugationPronouns.pron_2pp + ValladerConjugationPronouns.pron_r_2pp, conjugation.get(ValladerConjugationStructure.imperfectplural2)));
        cs.setImperfectplural3(setPronoun(ValladerConjugationPronouns.pron_3pp + ValladerConjugationPronouns.pron_r_3pp, conjugation.get(ValladerConjugationStructure.imperfectplural3)));

        // CONJUNCTIV
        cs.setConjunctivsing1(setPronoun(ValladerConjugationPronouns.pron_conjunctiv_v + ValladerConjugationPronouns.pron_1ps + ValladerConjugationPronouns.pron_r_1ps, conjugation.get(ValladerConjugationStructure.conjunctivsing1)));
        cs.setConjunctivsing2(setPronoun(ValladerConjugationPronouns.pron_conjunctiv_c + ValladerConjugationPronouns.pron_2ps + ValladerConjugationPronouns.pron_r_2ps, conjugation.get(ValladerConjugationStructure.conjunctivsing2)));
        cs.setConjunctivsing3(setPronoun(ValladerConjugationPronouns.pron_conjunctiv_v + ValladerConjugationPronouns.pron_3ps + ValladerConjugationPronouns.pron_r_3ps, conjugation.get(ValladerConjugationStructure.conjunctivsing3)));
        cs.setConjunctivplural1(setPronoun(ValladerConjugationPronouns.pron_conjunctiv_c + ValladerConjugationPronouns.pron_1pp + ValladerConjugationPronouns.pron_r_1pp, conjugation.get(ValladerConjugationStructure.conjunctivplural1)));
        cs.setConjunctivplural2(setPronoun(ValladerConjugationPronouns.pron_conjunctiv_c + ValladerConjugationPronouns.pron_2pp + ValladerConjugationPronouns.pron_r_2pp, conjugation.get(ValladerConjugationStructure.conjunctivplural2)));
        cs.setConjunctivplural3(setPronoun(ValladerConjugationPronouns.pron_conjunctiv_v + ValladerConjugationPronouns.pron_3pp + ValladerConjugationPronouns.pron_r_3pp, conjugation.get(ValladerConjugationStructure.conjunctivplural3)));

        // CUNDIZIONAL
        cs.setCundizionalsing1(setPronoun(ValladerConjugationPronouns.pron_1ps + ValladerConjugationPronouns.pron_r_1ps, conjugation.get(ValladerConjugationStructure.cundizionalsing1)));
        cs.setCundizionalsing2(setPronoun(ValladerConjugationPronouns.pron_2ps + ValladerConjugationPronouns.pron_r_2ps, conjugation.get(ValladerConjugationStructure.cundizionalsing2)));
        cs.setCundizionalsing3(setPronoun(ValladerConjugationPronouns.pron_3ps + ValladerConjugationPronouns.pron_r_3ps, conjugation.get(ValladerConjugationStructure.cundizionalsing3)));
        cs.setCundizionalplural1(setPronoun(ValladerConjugationPronouns.pron_1pp + ValladerConjugationPronouns.pron_r_1pp, conjugation.get(ValladerConjugationStructure.cundizionalplural1)));
        cs.setCundizionalplural2(setPronoun(ValladerConjugationPronouns.pron_2pp + ValladerConjugationPronouns.pron_r_2pp, conjugation.get(ValladerConjugationStructure.cundizionalplural2)));
        cs.setCundizionalplural3(setPronoun(ValladerConjugationPronouns.pron_3pp + ValladerConjugationPronouns.pron_r_3pp, conjugation.get(ValladerConjugationStructure.cundizionalplural3)));

        // IMPERATIV
        cs.setImperativ1(setPronoun(ValladerConjugationPronouns.imperativ_refl_sg, conjugation.get(ValladerConjugationStructure.imperativ1), "!"));
        cs.setImperativ2(setPronoun(ValladerConjugationPronouns.imperativ_refl_pl, conjugation.get(ValladerConjugationStructure.imperativ2), "!"));
        cs.setImperativ3(setPronoun(ValladerConjugationPronouns.imperativ_not_refl_sg, conjugation.get(ValladerConjugationStructure.imperativ3), "!"));
        cs.setImperativ4(setPronoun(ValladerConjugationPronouns.imperativ_not_refl_pl, conjugation.get(ValladerConjugationStructure.imperativ4), "!"));
        cs.setImperativ5(setPronoun(ValladerConjugationPronouns.imperativ_polite_sg + ValladerConjugationPronouns.pron_r_3ps, conjugation.get(ValladerConjugationStructure.imperativ5), "!"));
        cs.setImperativ6(setPronoun(ValladerConjugationPronouns.imperativ_polite_pl + ValladerConjugationPronouns.pron_r_3pp, conjugation.get(ValladerConjugationStructure.imperativ6), "!"));

        // PARTICIP_PERFECT
        cs.setParticipperfectms(conjugation.get(ValladerConjugationStructure.participperfectms));
        cs.setParticipperfectfs(conjugation.get(ValladerConjugationStructure.participperfectfs));
        cs.setParticipperfectmp(conjugation.get(ValladerConjugationStructure.participperfectmp));
        cs.setParticipperfectfp(conjugation.get(ValladerConjugationStructure.participperfectfp));

        // GERUNDIUM
        cs.setGerundium(setPronoun(ValladerConjugationPronouns.pron_r_2pp, conjugation.get(ValladerConjugationStructure.gerundium)));

        // FUTUR
        cs.setFutursing1(setPronoun(ValladerConjugationPronouns.pron_1ps, ValladerConjugationPronouns.pron_r_1ps + conjugation.get(ValladerConjugationStructure.futursing1)));
        cs.setFutursing2(setPronoun(ValladerConjugationPronouns.pron_2ps, ValladerConjugationPronouns.pron_r_2ps + conjugation.get(ValladerConjugationStructure.futursing2)));
        cs.setFutursing3(setPronoun(ValladerConjugationPronouns.pron_3ps, ValladerConjugationPronouns.pron_r_3ps + conjugation.get(ValladerConjugationStructure.futursing3)));
        cs.setFuturplural1(setPronoun(ValladerConjugationPronouns.pron_1pp, ValladerConjugationPronouns.pron_r_1pp + conjugation.get(ValladerConjugationStructure.futurplural1)));
        cs.setFuturplural2(setPronoun(ValladerConjugationPronouns.pron_2pp, ValladerConjugationPronouns.pron_r_2pp + conjugation.get(ValladerConjugationStructure.futurplural2)));
        cs.setFuturplural3(setPronoun(ValladerConjugationPronouns.pron_3pp, ValladerConjugationPronouns.pron_r_3pp + conjugation.get(ValladerConjugationStructure.futurplural3)));

        copyEncliticForms(conjugation, cs);

        return cs.getAllFormValues();
    }

    private HashMap<String, String> addReflexivePronounsVowel(Map<String, String> conjugation, InflectionSubType subType) {
        cs = new ValladerConjugationStructure();
        cs.setVerb(conjugation.get("verb"));
        cs.setInfinitiv(ValladerConjugationPronouns.pron_r_v_3ps + conjugation.get(ValladerConjugationStructure.infinitiv));
        cs.setRoot(conjugation.get(ValladerConjugationStructure.root));
        cs.setEnding(conjugation.get(ValladerConjugationStructure.ending));
        cs.setReflexive(conjugation.get(ValladerConjugationStructure.reflexive));
        cs.setConjugationClass(subType);

        // PRESCHENT
        cs.setPreschentsing1(setPronoun(ValladerConjugationPronouns.pron_1ps + ValladerConjugationPronouns.pron_r_v_1ps, conjugation.get(ValladerConjugationStructure.preschentsing1)));
        cs.setPreschentsing2(setPronoun(ValladerConjugationPronouns.pron_2ps + ValladerConjugationPronouns.pron_r_v_2ps, conjugation.get(ValladerConjugationStructure.preschentsing2)));
        cs.setPreschentsing3(setPronoun(ValladerConjugationPronouns.pron_3ps + ValladerConjugationPronouns.pron_r_v_3ps, conjugation.get(ValladerConjugationStructure.preschentsing3)));
        cs.setPreschentplural1(setPronoun(ValladerConjugationPronouns.pron_1pp + ValladerConjugationPronouns.pron_r_v_1pp, conjugation.get(ValladerConjugationStructure.preschentplural1)));
        cs.setPreschentplural2(setPronoun(ValladerConjugationPronouns.pron_2pp + ValladerConjugationPronouns.pron_r_v_2pp, conjugation.get(ValladerConjugationStructure.preschentplural2)));
        cs.setPreschentplural3(setPronoun(ValladerConjugationPronouns.pron_3pp + ValladerConjugationPronouns.pron_r_v_3pp, conjugation.get(ValladerConjugationStructure.preschentplural3)));

        // IMPERFECT
        cs.setImperfectsing1(setPronoun(ValladerConjugationPronouns.pron_1ps + ValladerConjugationPronouns.pron_r_v_1ps, conjugation.get(ValladerConjugationStructure.imperfectsing1)));
        cs.setImperfectsing2(setPronoun(ValladerConjugationPronouns.pron_2ps + ValladerConjugationPronouns.pron_r_v_2ps, conjugation.get(ValladerConjugationStructure.imperfectsing2)));
        cs.setImperfectsing3(setPronoun(ValladerConjugationPronouns.pron_3ps + ValladerConjugationPronouns.pron_r_v_3ps, conjugation.get(ValladerConjugationStructure.imperfectsing3)));
        cs.setImperfectplural1(setPronoun(ValladerConjugationPronouns.pron_1pp + ValladerConjugationPronouns.pron_r_v_1pp, conjugation.get(ValladerConjugationStructure.imperfectplural1)));
        cs.setImperfectplural2(setPronoun(ValladerConjugationPronouns.pron_2pp + ValladerConjugationPronouns.pron_r_v_2pp, conjugation.get(ValladerConjugationStructure.imperfectplural2)));
        cs.setImperfectplural3(setPronoun(ValladerConjugationPronouns.pron_3pp + ValladerConjugationPronouns.pron_r_v_3pp, conjugation.get(ValladerConjugationStructure.imperfectplural3)));

        // CONJUNCTIV
        cs.setConjunctivsing1(setPronoun(ValladerConjugationPronouns.pron_conjunctiv_v + ValladerConjugationPronouns.pron_1ps + ValladerConjugationPronouns.pron_r_v_1ps, conjugation.get(ValladerConjugationStructure.conjunctivsing1)));
        cs.setConjunctivsing2(setPronoun(ValladerConjugationPronouns.pron_conjunctiv_c + ValladerConjugationPronouns.pron_2ps + ValladerConjugationPronouns.pron_r_v_2ps, conjugation.get(ValladerConjugationStructure.conjunctivsing2)));
        cs.setConjunctivsing3(setPronoun(ValladerConjugationPronouns.pron_conjunctiv_v + ValladerConjugationPronouns.pron_3ps + ValladerConjugationPronouns.pron_r_v_3ps, conjugation.get(ValladerConjugationStructure.conjunctivsing3)));
        cs.setConjunctivplural1(setPronoun(ValladerConjugationPronouns.pron_conjunctiv_c + ValladerConjugationPronouns.pron_1pp + ValladerConjugationPronouns.pron_r_v_1pp, conjugation.get(ValladerConjugationStructure.conjunctivplural1)));
        cs.setConjunctivplural2(setPronoun(ValladerConjugationPronouns.pron_conjunctiv_c + ValladerConjugationPronouns.pron_2pp + ValladerConjugationPronouns.pron_r_v_2pp, conjugation.get(ValladerConjugationStructure.conjunctivplural2)));
        cs.setConjunctivplural3(setPronoun(ValladerConjugationPronouns.pron_conjunctiv_v + ValladerConjugationPronouns.pron_3pp + ValladerConjugationPronouns.pron_r_v_3pp, conjugation.get(ValladerConjugationStructure.conjunctivplural3)));

        // CUNDIZIONAL
        cs.setCundizionalsing1(setPronoun(ValladerConjugationPronouns.pron_1ps + ValladerConjugationPronouns.pron_r_v_1ps, conjugation.get(ValladerConjugationStructure.cundizionalsing1)));
        cs.setCundizionalsing2(setPronoun(ValladerConjugationPronouns.pron_2ps + ValladerConjugationPronouns.pron_r_v_2ps, conjugation.get(ValladerConjugationStructure.cundizionalsing2)));
        cs.setCundizionalsing3(setPronoun(ValladerConjugationPronouns.pron_3ps + ValladerConjugationPronouns.pron_r_v_3ps, conjugation.get(ValladerConjugationStructure.cundizionalsing3)));
        cs.setCundizionalplural1(setPronoun(ValladerConjugationPronouns.pron_1pp + ValladerConjugationPronouns.pron_r_v_1pp, conjugation.get(ValladerConjugationStructure.cundizionalplural1)));
        cs.setCundizionalplural2(setPronoun(ValladerConjugationPronouns.pron_2pp + ValladerConjugationPronouns.pron_r_v_2pp, conjugation.get(ValladerConjugationStructure.cundizionalplural2)));
        cs.setCundizionalplural3(setPronoun(ValladerConjugationPronouns.pron_3pp + ValladerConjugationPronouns.pron_r_v_3pp, conjugation.get(ValladerConjugationStructure.cundizionalplural3)));

        // IMPERATIV
        cs.setImperativ1(setPronoun(ValladerConjugationPronouns.imperativ_refl_sg_vowel, conjugation.get(ValladerConjugationStructure.imperativ1), "!"));
        cs.setImperativ2(setPronoun(ValladerConjugationPronouns.imperativ_refl_pl_vowel, conjugation.get(ValladerConjugationStructure.imperativ2), "!"));
        cs.setImperativ3(setPronoun(ValladerConjugationPronouns.imperativ_not + ValladerConjugationPronouns.pron_r_v_2ps, conjugation.get(ValladerConjugationStructure.imperativ3), "!"));
        cs.setImperativ4(setPronoun(ValladerConjugationPronouns.imperativ_not + ValladerConjugationPronouns.pron_r_v_2pp, conjugation.get(ValladerConjugationStructure.imperativ4), "!"));
        cs.setImperativ5(setPronoun(ValladerConjugationPronouns.imperativ_polite_sg + ValladerConjugationPronouns.pron_r_v_3ps, conjugation.get(ValladerConjugationStructure.imperativ5), "!"));
        cs.setImperativ6(setPronoun(ValladerConjugationPronouns.imperativ_polite_pl + ValladerConjugationPronouns.pron_r_v_3pp, conjugation.get(ValladerConjugationStructure.imperativ6), "!"));

        // PARTICIP_PERFECT
        cs.setParticipperfectms(conjugation.get(ValladerConjugationStructure.participperfectms));
        cs.setParticipperfectfs(conjugation.get(ValladerConjugationStructure.participperfectfs));
        cs.setParticipperfectmp(conjugation.get(ValladerConjugationStructure.participperfectmp));
        cs.setParticipperfectfp(conjugation.get(ValladerConjugationStructure.participperfectfp));

        // GERUNDIUM
        cs.setGerundium(setPronoun(ValladerConjugationPronouns.pron_r_v_3pp, conjugation.get(ValladerConjugationStructure.gerundium)));

        // FUTUR
        cs.setFutursing1(setPronoun(ValladerConjugationPronouns.pron_1ps, ValladerConjugationPronouns.pron_r_v_1ps + conjugation.get(ValladerConjugationStructure.futursing1)));
        cs.setFutursing2(setPronoun(ValladerConjugationPronouns.pron_2ps, ValladerConjugationPronouns.pron_r_v_2ps + conjugation.get(ValladerConjugationStructure.futursing2)));
        cs.setFutursing3(setPronoun(ValladerConjugationPronouns.pron_3ps, ValladerConjugationPronouns.pron_r_v_3ps + conjugation.get(ValladerConjugationStructure.futursing3)));
        cs.setFuturplural1(setPronoun(ValladerConjugationPronouns.pron_1pp, ValladerConjugationPronouns.pron_r_v_1pp + conjugation.get(ValladerConjugationStructure.futurplural1)));
        cs.setFuturplural2(setPronoun(ValladerConjugationPronouns.pron_2pp, ValladerConjugationPronouns.pron_r_v_2pp + conjugation.get(ValladerConjugationStructure.futurplural2)));
        cs.setFuturplural3(setPronoun(ValladerConjugationPronouns.pron_3pp, ValladerConjugationPronouns.pron_r_v_3pp + conjugation.get(ValladerConjugationStructure.futurplural3)));

        copyEncliticForms(conjugation, cs);

        return cs.getAllFormValues();
    }

    private HashMap<String, String> addStandardPronouns(Map<String, String> conjugation, InflectionSubType subType) {

        ValladerConjugationStructure cs = new ValladerConjugationStructure();

        cs.setVerb(conjugation.get("verb"));
        cs.setInfinitiv(conjugation.get(ValladerConjugationStructure.infinitiv));
        cs.setRoot(conjugation.get(ValladerConjugationStructure.root));
        cs.setEnding(conjugation.get(ValladerConjugationStructure.ending));
        cs.setReflexive(conjugation.get(ValladerConjugationStructure.reflexive));
        cs.setConjugationClass(subType);

        // PRESCHENT
        cs.setPreschentsing1(setPronoun(ValladerConjugationPronouns.pron_1ps, conjugation.get(ValladerConjugationStructure.preschentsing1)));
        cs.setPreschentsing2(setPronoun(ValladerConjugationPronouns.pron_2ps, conjugation.get(ValladerConjugationStructure.preschentsing2)));
        cs.setPreschentsing3(setPronoun(ValladerConjugationPronouns.pron_3ps, conjugation.get(ValladerConjugationStructure.preschentsing3)));
        cs.setPreschentplural1(setPronoun(ValladerConjugationPronouns.pron_1pp, conjugation.get(ValladerConjugationStructure.preschentplural1)));
        cs.setPreschentplural2(setPronoun(ValladerConjugationPronouns.pron_2pp, conjugation.get(ValladerConjugationStructure.preschentplural2)));
        cs.setPreschentplural3(setPronoun(ValladerConjugationPronouns.pron_3pp, conjugation.get(ValladerConjugationStructure.preschentplural3)));

        // IMPERFECT
        cs.setImperfectsing1(setPronoun(ValladerConjugationPronouns.pron_1ps, conjugation.get(ValladerConjugationStructure.imperfectsing1)));
        cs.setImperfectsing2(setPronoun(ValladerConjugationPronouns.pron_2ps, conjugation.get(ValladerConjugationStructure.imperfectsing2)));
        cs.setImperfectsing3(setPronoun(ValladerConjugationPronouns.pron_3ps, conjugation.get(ValladerConjugationStructure.imperfectsing3)));
        cs.setImperfectplural1(setPronoun(ValladerConjugationPronouns.pron_1pp, conjugation.get(ValladerConjugationStructure.imperfectplural1)));
        cs.setImperfectplural2(setPronoun(ValladerConjugationPronouns.pron_2pp, conjugation.get(ValladerConjugationStructure.imperfectplural2)));
        cs.setImperfectplural3(setPronoun(ValladerConjugationPronouns.pron_3pp, conjugation.get(ValladerConjugationStructure.imperfectplural3)));

        // CONJUNCTIV
        cs.setConjunctivsing1(setPronoun(ValladerConjugationPronouns.pron_conjunctiv_v + ValladerConjugationPronouns.pron_1ps, conjugation.get(ValladerConjugationStructure.conjunctivsing1)));
        cs.setConjunctivsing2(setPronoun(ValladerConjugationPronouns.pron_conjunctiv_c + ValladerConjugationPronouns.pron_2ps, conjugation.get(ValladerConjugationStructure.conjunctivsing2)));
        cs.setConjunctivsing3(setPronoun(ValladerConjugationPronouns.pron_conjunctiv_v + ValladerConjugationPronouns.pron_3ps, conjugation.get(ValladerConjugationStructure.conjunctivsing3)));
        cs.setConjunctivplural1(setPronoun(ValladerConjugationPronouns.pron_conjunctiv_c + ValladerConjugationPronouns.pron_1pp, conjugation.get(ValladerConjugationStructure.conjunctivplural1)));
        cs.setConjunctivplural2(setPronoun(ValladerConjugationPronouns.pron_conjunctiv_c + ValladerConjugationPronouns.pron_2pp, conjugation.get(ValladerConjugationStructure.conjunctivplural2)));
        cs.setConjunctivplural3(setPronoun(ValladerConjugationPronouns.pron_conjunctiv_v + ValladerConjugationPronouns.pron_3pp, conjugation.get(ValladerConjugationStructure.conjunctivplural3)));

        // CUNDIZIONAL
        cs.setCundizionalsing1(setPronoun(ValladerConjugationPronouns.pron_1ps, conjugation.get(ValladerConjugationStructure.cundizionalsing1)));
        cs.setCundizionalsing2(setPronoun(ValladerConjugationPronouns.pron_2ps, conjugation.get(ValladerConjugationStructure.cundizionalsing2)));
        cs.setCundizionalsing3(setPronoun(ValladerConjugationPronouns.pron_3ps, conjugation.get(ValladerConjugationStructure.cundizionalsing3)));
        cs.setCundizionalplural1(setPronoun(ValladerConjugationPronouns.pron_1pp, conjugation.get(ValladerConjugationStructure.cundizionalplural1)));
        cs.setCundizionalplural2(setPronoun(ValladerConjugationPronouns.pron_2pp, conjugation.get(ValladerConjugationStructure.cundizionalplural2)));
        cs.setCundizionalplural3(setPronoun(ValladerConjugationPronouns.pron_3pp, conjugation.get(ValladerConjugationStructure.cundizionalplural3)));

        // IMPERATIV
        cs.setImperativ1(setPronoun("", conjugation.get(ValladerConjugationStructure.imperativ1), "!"));
        cs.setImperativ2(setPronoun("", conjugation.get(ValladerConjugationStructure.imperativ2), "!"));
        if (startsWithVowel(conjugation.get(ValladerConjugationStructure.imperativ3))) {
            cs.setImperativ3(setPronoun(ValladerConjugationPronouns.imperativ_not_vowel, conjugation.get(ValladerConjugationStructure.imperativ3), "!"));
            cs.setImperativ4(setPronoun(ValladerConjugationPronouns.imperativ_not_vowel, conjugation.get(ValladerConjugationStructure.imperativ4), "!"));
        } else {
            cs.setImperativ3(setPronoun(ValladerConjugationPronouns.imperativ_not, conjugation.get(ValladerConjugationStructure.imperativ3), "!"));
            cs.setImperativ4(setPronoun(ValladerConjugationPronouns.imperativ_not, conjugation.get(ValladerConjugationStructure.imperativ4), "!"));
        }
        cs.setImperativ5(setPronoun(ValladerConjugationPronouns.imperativ_polite_sg, conjugation.get(ValladerConjugationStructure.imperativ5), "!"));
        cs.setImperativ6(setPronoun(ValladerConjugationPronouns.imperativ_polite_pl, conjugation.get(ValladerConjugationStructure.imperativ6), "!"));

        // PARTICIP_PERFECT
        cs.setParticipperfectms(conjugation.get(ValladerConjugationStructure.participperfectms));
        cs.setParticipperfectfs(conjugation.get(ValladerConjugationStructure.participperfectfs));
        cs.setParticipperfectmp(conjugation.get(ValladerConjugationStructure.participperfectmp));
        cs.setParticipperfectfp(conjugation.get(ValladerConjugationStructure.participperfectfp));

        // GERUNDIUM
        cs.setGerundium(conjugation.get(ValladerConjugationStructure.gerundium));

        // FUTUR
        cs.setFutursing1(setPronoun(ValladerConjugationPronouns.pron_1ps, conjugation.get(ValladerConjugationStructure.futursing1)));
        cs.setFutursing2(setPronoun(ValladerConjugationPronouns.pron_2ps, conjugation.get(ValladerConjugationStructure.futursing2)));
        cs.setFutursing3(setPronoun(ValladerConjugationPronouns.pron_3ps, conjugation.get(ValladerConjugationStructure.futursing3)));
        cs.setFuturplural1(setPronoun(ValladerConjugationPronouns.pron_1pp, conjugation.get(ValladerConjugationStructure.futurplural1)));
        cs.setFuturplural2(setPronoun(ValladerConjugationPronouns.pron_2pp, conjugation.get(ValladerConjugationStructure.futurplural2)));
        cs.setFuturplural3(setPronoun(ValladerConjugationPronouns.pron_3pp, conjugation.get(ValladerConjugationStructure.futurplural3)));

        copyEncliticForms(conjugation, cs);

        return cs.getAllFormValues();
    }

    private void copyEncliticForms(Map<String, String> conjugation, ValladerConjugationStructure cs) {
        cs.setPreschentsing1Enclitic(conjugation.get(ValladerConjugationStructure.preschentsing1enclitic));
        cs.setPreschentsing2Enclitic(conjugation.get(ValladerConjugationStructure.preschentsing2enclitic));
        cs.setPreschentsing3EncliticM(conjugation.get(ValladerConjugationStructure.preschentsing3encliticm));
        cs.setPreschentsing3EncliticF(conjugation.get(ValladerConjugationStructure.preschentsing3encliticf));
        cs.setPreschentplural1Enclitic(conjugation.get(ValladerConjugationStructure.preschentplural1enclitic));
        cs.setPreschentplural2Enclitic(conjugation.get(ValladerConjugationStructure.preschentplural2enclitic));
        cs.setPreschentplural3Enclitic(conjugation.get(ValladerConjugationStructure.preschentplural3enclitic));

        cs.setImperfectsing1Enclitic(conjugation.get(ValladerConjugationStructure.imperfectsing1enclitic));
        cs.setImperfectsing2Enclitic(conjugation.get(ValladerConjugationStructure.imperfectsing2enclitic));
        cs.setImperfectsing3EncliticM(conjugation.get(ValladerConjugationStructure.imperfectsing3encliticm));
        cs.setImperfectsing3EncliticF(conjugation.get(ValladerConjugationStructure.imperfectsing3encliticf));
        cs.setImperfectplural1Enclitic(conjugation.get(ValladerConjugationStructure.imperfectplural1enclitic));
        cs.setImperfectplural2Enclitic(conjugation.get(ValladerConjugationStructure.imperfectplural2enclitic));
        cs.setImperfectplural3Enclitic(conjugation.get(ValladerConjugationStructure.imperfectplural3enclitic));

        cs.setCundizionalsing1Enclitic(conjugation.get(ValladerConjugationStructure.cundizionalsing1enclitic));
        cs.setCundizionalsing2Enclitic(conjugation.get(ValladerConjugationStructure.cundizionalsing2enclitic));
        cs.setCundizionalsing3EncliticM(conjugation.get(ValladerConjugationStructure.cundizionalsing3encliticm));
        cs.setCundizionalsing3EncliticF(conjugation.get(ValladerConjugationStructure.cundizionalsing3encliticf));
        cs.setCundizionalplural1Enclitic(conjugation.get(ValladerConjugationStructure.cundizionalplural1enclitic));
        cs.setCundizionalplural2Enclitic(conjugation.get(ValladerConjugationStructure.cundizionalplural2enclitic));
        cs.setCundizionalplural3Enclitic(conjugation.get(ValladerConjugationStructure.cundizionalplural3enclitic));

        cs.setFutursing1Enclitic(conjugation.get(ValladerConjugationStructure.futursing1enclitic));
        cs.setFutursing2Enclitic(conjugation.get(ValladerConjugationStructure.futursing2enclitic));
        cs.setFutursing3EncliticM(conjugation.get(ValladerConjugationStructure.futursing3encliticm));
        cs.setFutursing3EncliticF(conjugation.get(ValladerConjugationStructure.futursing3encliticf));
        cs.setFuturplural1Enclitic(conjugation.get(ValladerConjugationStructure.futurplural1enclitic));
        cs.setFuturplural2Enclitic(conjugation.get(ValladerConjugationStructure.futurplural2enclitic));
        cs.setFuturplural3Enclitic(conjugation.get(ValladerConjugationStructure.futurplural3enclitic));
    }

    private boolean doesFormContainAllValues(InflectionResponse response, String flex) {
        if (flex == null) {
            return false;
        }
        String[] flexValues = flex.split("; ");

        for (String value : flexValues) {
            String flexValue = value;
            if (flexValue.startsWith("particip perfect: ")) {
                flexValue = flexValue.substring("particip perfect: ".length());
            }

            boolean isEqual = false;
            for (String key : response.getInflectionValues().keySet()) {
                if (key.equals("RInflectionSubtype") || key.equals("RInflectionType") || key.equals("RRegularInflection") || key.equals("infinitiv")) {
                    continue;
                }

                String form = response.getInflectionValues().get(key);
                // remove pronoun
                String[] formSplit = form.split(" ");
                form = formSplit[formSplit.length-1];
                if (form.equals(flexValue)) {
                    isEqual = true;
                    break;
                }
            }
            if (!isEqual) {
                return false;
            }
        }

        return true;
    }

    private void resetValues() {
        cs = null;
        infinitiv = null;
        verb = null;
        conjugation = null;
        root = null;
        ending = null;
        modRoot = null;
    }

    // special case for 'vo' pronoun
    protected String setPronoun(String pronoun, String forms, String suffix) {
        String[] singleForms = forms.split("\n");
        for (int i = 0; i < singleForms.length; i++) {

            boolean enclosedInBrackets = false;
            if (singleForms[i].charAt(0) == '(' && singleForms[i].charAt(singleForms[i].length()-1) == ')') {
                enclosedInBrackets = true;
                singleForms[i] = singleForms[i].replace("(", "").replace(")", "");
            }

            // 'vus' u 'vus as'
            if (pronoun.startsWith(ValladerConjugationPronouns.pron_2pp) && singleForms[i].endsWith("ivat")) {
                singleForms[i] = pronoun.replace(ValladerConjugationPronouns.pron_2pp, ValladerConjugationPronouns.pron_2pp_alt) + singleForms[i] + suffix;
            } else {
                singleForms[i] = pronoun + singleForms[i] + suffix;
            }

            if (enclosedInBrackets) {
                singleForms[i] = "(" + singleForms[i] + ")";
            }
        }
        return String.join("\n", singleForms);
    }
}
