package ch.pledarigrond.inflection.generation.puter;

import ch.pledarigrond.inflection.generation.generic.LanguageConjugation;
import ch.pledarigrond.inflection.model.InflectionResponse;
import ch.pledarigrond.inflection.model.InflectionSubType;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class PuterConjugation extends LanguageConjugation {

    private char vowelInRoot;

    private PuterConjugationStructure cs;

    private String infinitiv;
    private String verb;

    private String isReflexive;

    private String lastTwo;
    private String lastThree;

    private HashMap<String, String> conjugation;

    public InflectionResponse generateConjugation(String conjugationClass, String infinitiv) {
        resetValues();

        root = getRoot(infinitiv);

        if (conjugationClass.equals("1a") || conjugationClass.equals("4a")) {
            modRoot = root + "esch";
        }

        if (infinitiv.endsWith("ger")) {
            modRoot = infinitiv.substring(0, infinitiv.length() - 2) + "i";
        }

        if (infinitiv.endsWith("glier")) {
            modRoot = infinitiv.substring(0, infinitiv.length() - 2);
        }

        if (infinitiv.endsWith("cker")) {
            modRoot = infinitiv.substring(0, infinitiv.length() - 3) + "c";
        }

        if (modRoot == null) {
            modRoot = root;
        }

        InflectionSubType subType = PuterConjugationClasses.getConjugationClass(conjugationClass);
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


        if (lastTwo.equals("er")) {
            if (flex != null && flex.contains("-esch")) {
                return generateConjugation("1a", baseForm);
            }

            if (flex != null && !flex.equals("")) {
                String[] subForms = new String[]{"3"};
                for (String subForm : subForms) {
                    InflectionResponse r = generateConjugation(subForm, baseForm);
                    if (doesFormContainAllValues(r, flex)) {
                        return r;
                    }
                }
            }

            return generateConjugation("1", baseForm);
        }

        if (lastTwo.equals("ir")) {
            if (flex != null && flex.contains("-esch")) {
                return generateConjugation("4a", baseForm);
            }

            return generateConjugation("4", baseForm);
        }

        return null;
    }

    public static PuterConjugationStructure addEncliticForms(PuterConjugationStructure cs) {
        PuterConjugation.setPreschentEnclitic(cs);
        PuterConjugation.setImperfectEnclitic(cs);
        PuterConjugation.setCundizionalEnclitic(cs);
        PuterConjugation.setFuturEnclitic(cs);
        PuterConjugation.setFuturDubitativEnclitic(cs);

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

        switch (l2) {
            case "er":
            case "ir":
                if (!l3.equals("air")) {
                    setLastTwo(l2);
                    setEnding(getLastTwo());
                    infintiv = checkReflexiveness(infintiv);
                    infintiv = infintiv.substring(0, infintiv.length() - 2);

                    return infintiv;
                }
                break;
        }

        if (l3.equals("air")) {
            setLastThree(l3);
            setEnding(getLastThree());
            infintiv = checkReflexiveness(infintiv);
            infintiv = infintiv.substring(0, infintiv.length() - 3);

            return infintiv;
        }

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

        cs = new PuterConjugationStructure();
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
        setFuturDubitativ(cs);

        setPreschentEnclitic(cs);
        setImperfectEnclitic(cs);
        setCundizionalEnclitic(cs);
        setFuturEnclitic(cs);
        setFuturDubitativEnclitic(cs);

        return cs.getValues();
    }

    private void setPreschent(PuterConjugationStructure cs) {
        switch (cs.getConjugationclass().id) {
            case "1":
            case "1a":
            case "2":
            case "3":
                if (cs.getInfinitiv().endsWith("ger") || cs.getInfinitiv().endsWith("glier") || cs.getInfinitiv().endsWith("cker")) {
                    cs.setPreschentsing1(modRoot.substring(0, modRoot.length() - 1));
                } else if (
                        cs.getInfinitiv().endsWith("ffer") ||
                        cs.getInfinitiv().endsWith("ller") ||
                        cs.getInfinitiv().endsWith("mmer") ||
                        cs.getInfinitiv().endsWith("nner") ||
                        cs.getInfinitiv().endsWith("pper") ||
                        cs.getInfinitiv().endsWith("rrer") ||
                        cs.getInfinitiv().endsWith("tter") ||
                        cs.getInfinitiv().endsWith("zzer")
                ) {
                    cs.setPreschentsing1(modRoot.substring(0, modRoot.length() - 1));
                } else {
                    cs.setPreschentsing1(modRoot);
                }
                cs.setPreschentsing2(modRoot + "ast");
                cs.setPreschentsing3(modRoot + "a");

                if (cs.getInfinitiv().endsWith("ger") || cs.getInfinitiv().endsWith("glier") || cs.getInfinitiv().endsWith("cker")) {
                    cs.setPreschentplural1(modRoot + "ains");
                    cs.setPreschentplural2(modRoot + "ais");
                } else {
                    cs.setPreschentplural1(root + "ains");
                    cs.setPreschentplural2(root + "ais");
                }
                cs.setPreschentplural3(modRoot + "an");
                break;


            case "4":
            case "4a":
                cs.setPreschentsing1(modRoot);
                cs.setPreschentsing2(modRoot + "ast");
                cs.setPreschentsing3(modRoot + "a");
                cs.setPreschentplural1(root + "ins");
                cs.setPreschentplural2(root + "is");
                cs.setPreschentplural3(modRoot + "an");
                break;
        }

    }

    private void setImperfect(PuterConjugationStructure cs) {

        switch (cs.getConjugationclass().id.substring(0, 1)) {

            case "1":
            case "2":
            case "3":
                if (cs.getInfinitiv().endsWith("ger")) {
                    cs.setImperfectsing1(modRoot + "aiva");
                    cs.setImperfectsing2(modRoot + "aivast");
                    cs.setImperfectsing3(modRoot + "aiva");
                    cs.setImperfectplural1(modRoot + "aivans");
                    cs.setImperfectplural2(modRoot + "aivas");
                    cs.setImperfectplural3(modRoot + "aivan");
                } else {
                    cs.setImperfectsing1(root + "aiva");
                    cs.setImperfectsing2(root + "aivast");
                    cs.setImperfectsing3(root + "aiva");
                    cs.setImperfectplural1(root + "aivans");
                    cs.setImperfectplural2(root + "aivas");
                    cs.setImperfectplural3(root + "aivan");
                }
                break;

            case "4":
                cs.setImperfectsing1(root + "iva");
                cs.setImperfectsing2(root + "ivast");
                cs.setImperfectsing3(root + "iva");
                cs.setImperfectplural1(root + "ivans");
                cs.setImperfectplural2(root + "ivas");
                cs.setImperfectplural3(root + "ivan");
                break;
        }

    }

    private void setConjunctiv(PuterConjugationStructure cs) {
        switch (cs.getConjugationclass().id) {
            default:
                cs.setConjunctivsing1(modRoot + "a");
                cs.setConjunctivsing2(modRoot + "ast");
                cs.setConjunctivsing3(modRoot + "a");
                cs.setConjunctivplural1(modRoot + "ans");
                cs.setConjunctivplural2(modRoot + "as");
                cs.setConjunctivplural3(modRoot + "an");
                break;
        }
    }

    private void setCundizional(PuterConjugationStructure cs) {
        switch (cs.getConjugationclass().id.substring(0, 1)) {
            case "4":
                cs.setCundizionalsing1(root + "iss");
                cs.setCundizionalsing2(root + "issast");
                cs.setCundizionalsing3(root + "iss");
                cs.setCundizionalplural1(root + "issans");
                cs.setCundizionalplural2(root + "issas");
                cs.setCundizionalplural3(root + "issan");
                break;

            default:
                cs.setCundizionalsing1(root + "ess");
                cs.setCundizionalsing2(root + "essast");
                cs.setCundizionalsing3(root + "ess");
                cs.setCundizionalplural1(root + "essans");
                cs.setCundizionalplural2(root + "essas");
                cs.setCundizionalplural3(root + "essan");
                break;
        }
    }

    private void setParticipPerfect(PuterConjugationStructure cs) {

        switch (cs.getConjugationclass().id.substring(0, 1)) {
            case "1":
                cs.setParticipperfectms(root + "o");
                cs.setParticipperfectmp(root + "os");
                cs.setParticipperfectfs(root + "eda");
                cs.setParticipperfectfp(root + "edas");
                break;

            default:
                cs.setParticipperfectms(root + "ieu");
                cs.setParticipperfectmp(root + "ieus");
                cs.setParticipperfectfs(root + "ida");
                cs.setParticipperfectfp(root + "idas");
                break;
        }
    }

    private void setGerundium(PuterConjugationStructure cs) {

        switch (cs.getConjugationclass().id.substring(0, 1)) {
            case "4":
                cs.setGerundium(root + "ind");
                break;
            default:
                cs.setGerundium(root + "and");
                break;
        }

    }

    private void setImperativ(PuterConjugationStructure cs) {
        switch (cs.getConjugationclass().id.substring(0, 1)) {
            case "4":
                cs.setImperativ1(modRoot + "a");
                cs.setImperativ2(root + "i");
                cs.setImperativ3(getInfinitiv());
                cs.setImperativ4(root + "i");
                cs.setImperativ5(modRoot + "a");
                cs.setImperativ6(modRoot + "an");
                break;


            default:
                cs.setImperativ1(modRoot + "a");
                cs.setImperativ2(root + "è");
                cs.setImperativ3(getInfinitiv());
                cs.setImperativ4(root + "è");
                cs.setImperativ5(modRoot + "a");
                cs.setImperativ6(modRoot + "an");
                break;
        }
    }

    private void setFutur(PuterConjugationStructure cs) {
        switch (cs.getConjugationclass().id.substring(0, 1)) {
            case "4":
                cs.setFutursing1(root + "iro");
                cs.setFutursing2(root + "irost");
                cs.setFutursing3(root + "iro");
                cs.setFuturplural1(root + "irons");
                cs.setFuturplural2(root + "iros");
                cs.setFuturplural3(root + "iron");
                break;

            default:
                if (cs.getInfinitiv().endsWith("ger")) {
                    cs.setFutursing1(modRoot + "aro");
                    cs.setFutursing2(modRoot + "arost");
                    cs.setFutursing3(modRoot + "aro");
                    cs.setFuturplural1(modRoot + "arons");
                    cs.setFuturplural2(modRoot + "aros");
                    cs.setFuturplural3(modRoot + "aron");
                } else {
                    cs.setFutursing1(root + "aro");
                    cs.setFutursing2(root + "arost");
                    cs.setFutursing3(root + "aro");
                    cs.setFuturplural1(root + "arons");
                    cs.setFuturplural2(root + "aros");
                    cs.setFuturplural3(root + "aron");
                }
                break;
        }
    }

    private void setFuturDubitativ(PuterConjugationStructure cs) {
        switch (cs.getConjugationclass().id.substring(0, 1)) {
            case "4":
                cs.setFuturdubitativsing1(root + "iregia");
                cs.setFuturdubitativsing2(root + "iregiast");
                cs.setFuturdubitativsing3(root + "iregia");
                cs.setFuturdubitativplural1(root + "iregians");
                cs.setFuturdubitativplural2(root + "iregias");
                cs.setFuturdubitativplural3(root + "iregian");
                break;

            default:
                if (cs.getInfinitiv().endsWith("ger")) {
                    cs.setFuturdubitativsing1(modRoot + "aregia");
                    cs.setFuturdubitativsing2(modRoot + "aregiast");
                    cs.setFuturdubitativsing3(modRoot + "aregia");
                    cs.setFuturdubitativplural1(modRoot + "aregians");
                    cs.setFuturdubitativplural2(modRoot + "aregias");
                    cs.setFuturdubitativplural3(modRoot + "aregian");
                } else {
                    cs.setFuturdubitativsing1(root + "aregia");
                    cs.setFuturdubitativsing2(root + "aregiast");
                    cs.setFuturdubitativsing3(root + "aregia");
                    cs.setFuturdubitativplural1(root + "aregians");
                    cs.setFuturdubitativplural2(root + "aregias");
                    cs.setFuturdubitativplural3(root + "aregian");
                }
                break;
        }
    }


    private static void setPreschentEnclitic(PuterConjugationStructure cs) {
        if (cs.getInfinitiv().endsWith("cker")) {
            cs.setPreschentsing1Enclitic(cs.getPreschentsing1() + "ki");
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
            cs.setPreschentsing1Enclitic(cs.getPreschentsing1() + consonant + "i");
        } else {
            cs.setPreschentsing1Enclitic(cs.getPreschentsing1() + "i");
        }
        cs.setPreschentsing2Enclitic(cs.getPreschentsing2());
        cs.setPreschentsing3EncliticM(cs.getPreschentsing3() + "'l");
        if (cs.getInfinitiv().endsWith("ger") ||
                cs.getInfinitiv().endsWith("cker") ||
                cs.getInfinitiv().endsWith("glier") ||
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
                cs.getInfinitiv().endsWith("zzer")) {
            cs.setPreschentsing3EncliticF(cs.getPreschentsing3().substring(0, cs.getPreschentsing3().length() - 2) + "'la");
        } else {
            cs.setPreschentsing3EncliticF(cs.getPreschentsing3().substring(0, cs.getPreschentsing3().length() - 1) + "'la");
        }
        cs.setPreschentplural1Enclitic(cs.getPreschentplural1() + "a");
        cs.setPreschentplural2Enclitic(cs.getPreschentplural2());
        cs.setPreschentplural3Enclitic(cs.getPreschentplural3() + "e");
    }

    private static void setImperfectEnclitic(PuterConjugationStructure cs) {
        cs.setImperfectsing1Enclitic(cs.getImperfectsing1().substring(0, cs.getImperfectsing1().length() - 1) + "i");
        cs.setImperfectsing2Enclitic(cs.getImperfectsing2());
        cs.setImperfectsing3EncliticM(cs.getImperfectsing3() + "'l");
        cs.setImperfectsing3EncliticF(cs.getImperfectsing3().substring(0, cs.getImperfectsing3().length() - 1) + "'la");
        cs.setImperfectplural1Enclitic(cs.getImperfectplural1());
        cs.setImperfectplural2Enclitic(cs.getImperfectplural2());
        cs.setImperfectplural3Enclitic(cs.getImperfectplural3() + "e");
    }

    private static void setCundizionalEnclitic(PuterConjugationStructure cs) {
        cs.setCundizionalsing1Enclitic(cs.getCundizionalsing1() + "i");
        cs.setCundizionalsing2Enclitic(cs.getCundizionalsing2());
        cs.setCundizionalsing3EncliticM(cs.getCundizionalsing3() + "a'l");
        cs.setCundizionalsing3EncliticF(cs.getCundizionalsing3() + "'la");
        cs.setCundizionalplural1Enclitic(cs.getCundizionalplural1());
        cs.setCundizionalplural2Enclitic(cs.getCundizionalplural2());
        cs.setCundizionalplural3Enclitic(cs.getCundizionalplural3() + "e");
    }

    private static void setFuturEnclitic(PuterConjugationStructure cs) {
        cs.setFutursing1Enclitic(cs.getFutursing1() + " eau");
        cs.setFutursing2Enclitic(cs.getFutursing2());
        cs.setFutursing3EncliticM(cs.getFutursing3() + "'l");
        cs.setFutursing3EncliticF(cs.getFutursing3() + "'la");
        cs.setFuturplural1Enclitic(cs.getFuturplural1() + "a");
        cs.setFuturplural2Enclitic(cs.getFuturplural2());
        cs.setFuturplural3Enclitic(cs.getFuturplural3() + "e");
    }

    private static void setFuturDubitativEnclitic(PuterConjugationStructure cs) {
        cs.setFuturdubitativsing1Enclitic(cs.getFuturdubitativsing1().substring(0, cs.getFuturdubitativsing1().length() - 1));
        cs.setFuturdubitativsing2Enclitic(cs.getFuturdubitativsing2());
        cs.setFuturdubitativsing3EncliticM(cs.getFuturdubitativsing3() + "'l");
        cs.setFuturdubitativsing3EncliticF(cs.getFuturdubitativsing3().substring(0, cs.getFuturdubitativsing3().length() - 2) + "'la");
        cs.setFuturdubitativplural1Enclitic(cs.getFuturdubitativplural1());
        cs.setFuturdubitativplural2Enclitic(cs.getFuturdubitativplural2());
        cs.setFuturdubitativplural3Enclitic(cs.getFuturdubitativplural3() + "e");
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
        cs = new PuterConjugationStructure();
        cs.setVerb(conjugation.get("verb"));
        cs.setInfinitiv(PuterConjugationPronouns.pron_r_3ps + conjugation.get(PuterConjugationStructure.infinitiv));
        cs.setRoot(conjugation.get(PuterConjugationStructure.root));
        cs.setEnding(conjugation.get(PuterConjugationStructure.ending));
        cs.setReflexive(conjugation.get(PuterConjugationStructure.reflexive));
        cs.setConjugationClass(subType);

        // PRESCHENT
        cs.setPreschentsing1(setPronoun(PuterConjugationPronouns.pron_1ps + PuterConjugationPronouns.pron_r_1ps, conjugation.get(PuterConjugationStructure.preschentsing1)));
        cs.setPreschentsing2(setPronoun(PuterConjugationPronouns.pron_2ps + PuterConjugationPronouns.pron_r_2ps, conjugation.get(PuterConjugationStructure.preschentsing2)));
        cs.setPreschentsing3(setPronoun(PuterConjugationPronouns.pron_3ps + PuterConjugationPronouns.pron_r_3ps, conjugation.get(PuterConjugationStructure.preschentsing3)));
        cs.setPreschentplural1(setPronoun(PuterConjugationPronouns.pron_1pp + PuterConjugationPronouns.pron_r_1pp, conjugation.get(PuterConjugationStructure.preschentplural1)));
        cs.setPreschentplural2(setPronoun(PuterConjugationPronouns.pron_2pp + PuterConjugationPronouns.pron_r_2pp,conjugation.get(PuterConjugationStructure.preschentplural2)));
        cs.setPreschentplural3(setPronoun(PuterConjugationPronouns.pron_3pp + PuterConjugationPronouns.pron_r_3pp, conjugation.get(PuterConjugationStructure.preschentplural3)));

        // IMPERFECT
        cs.setImperfectsing1(setPronoun(PuterConjugationPronouns.pron_1ps + PuterConjugationPronouns.pron_r_1ps, conjugation.get(PuterConjugationStructure.imperfectsing1)));
        cs.setImperfectsing2(setPronoun(PuterConjugationPronouns.pron_2ps + PuterConjugationPronouns.pron_r_2ps, conjugation.get(PuterConjugationStructure.imperfectsing2)));
        cs.setImperfectsing3(setPronoun(PuterConjugationPronouns.pron_3ps + PuterConjugationPronouns.pron_r_3ps, conjugation.get(PuterConjugationStructure.imperfectsing3)));
        cs.setImperfectplural1(setPronoun(PuterConjugationPronouns.pron_1pp + PuterConjugationPronouns.pron_r_1pp, conjugation.get(PuterConjugationStructure.imperfectplural1)));
        cs.setImperfectplural2(setPronoun(PuterConjugationPronouns.pron_2pp + PuterConjugationPronouns.pron_r_2pp, conjugation.get(PuterConjugationStructure.imperfectplural2)));
        cs.setImperfectplural3(setPronoun(PuterConjugationPronouns.pron_3pp + PuterConjugationPronouns.pron_r_3pp, conjugation.get(PuterConjugationStructure.imperfectplural3)));

        // CONJUNCTIV
        cs.setConjunctivsing1(setPronoun(PuterConjugationPronouns.pron_conjunctiv_v + PuterConjugationPronouns.pron_1ps + PuterConjugationPronouns.pron_r_1ps, conjugation.get(PuterConjugationStructure.conjunctivsing1)));
        cs.setConjunctivsing2(setPronoun(PuterConjugationPronouns.pron_conjunctiv_c + PuterConjugationPronouns.pron_2ps + PuterConjugationPronouns.pron_r_2ps, conjugation.get(PuterConjugationStructure.conjunctivsing2)));
        cs.setConjunctivsing3(setPronoun(PuterConjugationPronouns.pron_conjunctiv_v + PuterConjugationPronouns.pron_3ps + PuterConjugationPronouns.pron_r_3ps, conjugation.get(PuterConjugationStructure.conjunctivsing3)));
        cs.setConjunctivplural1(setPronoun(PuterConjugationPronouns.pron_conjunctiv_c + PuterConjugationPronouns.pron_1pp + PuterConjugationPronouns.pron_r_1pp, conjugation.get(PuterConjugationStructure.conjunctivplural1)));
        cs.setConjunctivplural2(setPronoun(PuterConjugationPronouns.pron_conjunctiv_c + PuterConjugationPronouns.pron_2pp + PuterConjugationPronouns.pron_r_2pp, conjugation.get(PuterConjugationStructure.conjunctivplural2)));
        cs.setConjunctivplural3(setPronoun(PuterConjugationPronouns.pron_conjunctiv_v + PuterConjugationPronouns.pron_3pp + PuterConjugationPronouns.pron_r_3pp, conjugation.get(PuterConjugationStructure.conjunctivplural3)));

        // CUNDIZIONAL
        cs.setCundizionalsing1(setPronoun(PuterConjugationPronouns.pron_1ps + PuterConjugationPronouns.pron_r_1ps, conjugation.get(PuterConjugationStructure.cundizionalsing1)));
        cs.setCundizionalsing2(setPronoun(PuterConjugationPronouns.pron_2ps + PuterConjugationPronouns.pron_r_2ps, conjugation.get(PuterConjugationStructure.cundizionalsing2)));
        cs.setCundizionalsing3(setPronoun(PuterConjugationPronouns.pron_3ps + PuterConjugationPronouns.pron_r_3ps, conjugation.get(PuterConjugationStructure.cundizionalsing3)));
        cs.setCundizionalplural1(setPronoun(PuterConjugationPronouns.pron_1pp + PuterConjugationPronouns.pron_r_1pp, conjugation.get(PuterConjugationStructure.cundizionalplural1)));
        cs.setCundizionalplural2(setPronoun(PuterConjugationPronouns.pron_2pp + PuterConjugationPronouns.pron_r_2pp, conjugation.get(PuterConjugationStructure.cundizionalplural2)));
        cs.setCundizionalplural3(setPronoun(PuterConjugationPronouns.pron_3pp + PuterConjugationPronouns.pron_r_3pp, conjugation.get(PuterConjugationStructure.cundizionalplural3)));

        // IMPERATIV
        cs.setImperativ1(setPronoun(conjugation.get(PuterConjugationStructure.imperativ1), PuterConjugationPronouns.imperativ_refl_sg, "!"));
        cs.setImperativ2(setPronoun(conjugation.get(PuterConjugationStructure.imperativ2), PuterConjugationPronouns.imperativ_refl_pl, "!"));
        cs.setImperativ3(setPronoun(PuterConjugationPronouns.imperativ_not_refl_sg, conjugation.get(PuterConjugationStructure.imperativ3), "!"));
        cs.setImperativ4(setPronoun(PuterConjugationPronouns.imperativ_not_refl_pl, conjugation.get(PuterConjugationStructure.imperativ4), "!"));
        cs.setImperativ5(setPronoun(PuterConjugationPronouns.imperativ_polite_sg, PuterConjugationPronouns.pron_r_3ps + conjugation.get(PuterConjugationStructure.imperativ5), "!"));
        cs.setImperativ6(setPronoun(PuterConjugationPronouns.imperativ_polite_pl, PuterConjugationPronouns.pron_r_3pp + conjugation.get(PuterConjugationStructure.imperativ6), "!"));

        // PARTICIP_PERFECT
        cs.setParticipperfectms(conjugation.get(PuterConjugationStructure.participperfectms));
        cs.setParticipperfectfs(conjugation.get(PuterConjugationStructure.participperfectfs));
        cs.setParticipperfectmp(conjugation.get(PuterConjugationStructure.participperfectmp));
        cs.setParticipperfectfp(conjugation.get(PuterConjugationStructure.participperfectfp));

        // GERUNDIUM
        cs.setGerundium(setPronoun(PuterConjugationPronouns.pron_r_2pp, conjugation.get(PuterConjugationStructure.gerundium)));

        // FUTUR
        cs.setFutursing1(setPronoun(PuterConjugationPronouns.pron_1ps + PuterConjugationPronouns.pron_r_1ps, conjugation.get(PuterConjugationStructure.futursing1)));
        cs.setFutursing2(setPronoun(PuterConjugationPronouns.pron_2ps + PuterConjugationPronouns.pron_r_2ps, conjugation.get(PuterConjugationStructure.futursing2)));
        cs.setFutursing3(setPronoun(PuterConjugationPronouns.pron_3ps + PuterConjugationPronouns.pron_r_3ps, conjugation.get(PuterConjugationStructure.futursing3)));
        cs.setFuturplural1(setPronoun(PuterConjugationPronouns.pron_1pp + PuterConjugationPronouns.pron_r_1pp, conjugation.get(PuterConjugationStructure.futurplural1)));
        cs.setFuturplural2(setPronoun(PuterConjugationPronouns.pron_2pp + PuterConjugationPronouns.pron_r_2pp, conjugation.get(PuterConjugationStructure.futurplural2)));
        cs.setFuturplural3(setPronoun(PuterConjugationPronouns.pron_3pp + PuterConjugationPronouns.pron_r_3pp, conjugation.get(PuterConjugationStructure.futurplural3)));

        // FUTUR DUBITATIV
        cs.setFuturdubitativsing1(setPronoun(PuterConjugationPronouns.pron_1ps + PuterConjugationPronouns.pron_r_1ps, conjugation.get(PuterConjugationStructure.futurdubitativsing1)));
        cs.setFuturdubitativsing2(setPronoun(PuterConjugationPronouns.pron_2ps + PuterConjugationPronouns.pron_r_2ps, conjugation.get(PuterConjugationStructure.futurdubitativsing2)));
        cs.setFuturdubitativsing3(setPronoun(PuterConjugationPronouns.pron_3ps + PuterConjugationPronouns.pron_r_3ps, conjugation.get(PuterConjugationStructure.futurdubitativsing3)));
        cs.setFuturdubitativplural1(setPronoun(PuterConjugationPronouns.pron_1pp + PuterConjugationPronouns.pron_r_1pp, conjugation.get(PuterConjugationStructure.futurdubitativplural1)));
        cs.setFuturdubitativplural2(setPronoun(PuterConjugationPronouns.pron_2pp + PuterConjugationPronouns.pron_r_2pp, conjugation.get(PuterConjugationStructure.futurdubitativplural2)));
        cs.setFuturdubitativplural3(setPronoun(PuterConjugationPronouns.pron_3pp + PuterConjugationPronouns.pron_r_3pp, conjugation.get(PuterConjugationStructure.futurdubitativplural3)));

        copyEncliticForms(conjugation, cs);

        // PRESCHENT
        cs.setPreschentsing1Enclitic(setPronoun(PuterConjugationPronouns.pron_r_1ps, conjugation.get(PuterConjugationStructure.preschentsing1enclitic)));
        cs.setPreschentsing2Enclitic(setPronoun(PuterConjugationPronouns.pron_r_2ps, conjugation.get(PuterConjugationStructure.preschentsing2enclitic)));
        cs.setPreschentsing3EncliticM(setPronoun(PuterConjugationPronouns.pron_r_3ps, conjugation.get(PuterConjugationStructure.preschentsing3encliticm)));
        cs.setPreschentsing3EncliticF(setPronoun(PuterConjugationPronouns.pron_r_3ps, conjugation.get(PuterConjugationStructure.preschentsing3encliticf)));
        cs.setPreschentplural1Enclitic(setPronoun(PuterConjugationPronouns.pron_r_1pp, conjugation.get(PuterConjugationStructure.preschentplural1enclitic)));
        cs.setPreschentplural2Enclitic(setPronoun(PuterConjugationPronouns.pron_r_2pp,conjugation.get(PuterConjugationStructure.preschentplural2enclitic)));
        cs.setPreschentplural3Enclitic(setPronoun(PuterConjugationPronouns.pron_r_3pp, conjugation.get(PuterConjugationStructure.preschentplural3enclitic)));

        // IMPERFECT
        cs.setImperfectsing1Enclitic(setPronoun(PuterConjugationPronouns.pron_r_1ps, conjugation.get(PuterConjugationStructure.imperfectsing1enclitic)));
        cs.setImperfectsing2Enclitic(setPronoun(PuterConjugationPronouns.pron_r_2ps, conjugation.get(PuterConjugationStructure.imperfectsing2enclitic)));
        cs.setImperfectsing3EncliticM(setPronoun(PuterConjugationPronouns.pron_r_3ps, conjugation.get(PuterConjugationStructure.imperfectsing3encliticm)));
        cs.setImperfectsing3EncliticF(setPronoun(PuterConjugationPronouns.pron_r_3ps, conjugation.get(PuterConjugationStructure.imperfectsing3encliticf)));
        cs.setImperfectplural1Enclitic(setPronoun(PuterConjugationPronouns.pron_r_1pp, conjugation.get(PuterConjugationStructure.imperfectplural1enclitic)));
        cs.setImperfectplural2Enclitic(setPronoun(PuterConjugationPronouns.pron_r_2pp, conjugation.get(PuterConjugationStructure.imperfectplural2enclitic)));
        cs.setImperfectplural3Enclitic(setPronoun(PuterConjugationPronouns.pron_r_3pp, conjugation.get(PuterConjugationStructure.imperfectplural3enclitic)));

        // CUNDIZIONAL
        cs.setCundizionalsing1Enclitic(setPronoun(PuterConjugationPronouns.pron_r_1ps, conjugation.get(PuterConjugationStructure.cundizionalsing1enclitic)));
        cs.setCundizionalsing2Enclitic(setPronoun(PuterConjugationPronouns.pron_r_2ps, conjugation.get(PuterConjugationStructure.cundizionalsing2enclitic)));
        cs.setCundizionalsing3EncliticM(setPronoun(PuterConjugationPronouns.pron_r_3ps, conjugation.get(PuterConjugationStructure.cundizionalsing3encliticm)));
        cs.setCundizionalsing3EncliticF(setPronoun(PuterConjugationPronouns.pron_r_3ps, conjugation.get(PuterConjugationStructure.cundizionalsing3encliticf)));
        cs.setCundizionalplural1Enclitic(setPronoun(PuterConjugationPronouns.pron_r_1pp, conjugation.get(PuterConjugationStructure.cundizionalplural1enclitic)));
        cs.setCundizionalplural2Enclitic(setPronoun(PuterConjugationPronouns.pron_r_2pp, conjugation.get(PuterConjugationStructure.cundizionalplural2enclitic)));
        cs.setCundizionalplural3Enclitic(setPronoun(PuterConjugationPronouns.pron_r_3pp, conjugation.get(PuterConjugationStructure.cundizionalplural3enclitic)));

        // FUTUR
        cs.setFutursing1Enclitic(setPronoun(PuterConjugationPronouns.pron_r_1ps, conjugation.get(PuterConjugationStructure.futursing1enclitic)));
        cs.setFutursing2Enclitic(setPronoun(PuterConjugationPronouns.pron_r_2ps, conjugation.get(PuterConjugationStructure.futursing2enclitic)));
        cs.setFutursing3EncliticM(setPronoun(PuterConjugationPronouns.pron_r_3ps, conjugation.get(PuterConjugationStructure.futursing3encliticm)));
        cs.setFutursing3EncliticF(setPronoun(PuterConjugationPronouns.pron_r_3ps, conjugation.get(PuterConjugationStructure.futursing3encliticf)));
        cs.setFuturplural1Enclitic(setPronoun(PuterConjugationPronouns.pron_r_1pp, conjugation.get(PuterConjugationStructure.futurplural1enclitic)));
        cs.setFuturplural2Enclitic(setPronoun(PuterConjugationPronouns.pron_r_2pp, conjugation.get(PuterConjugationStructure.futurplural2enclitic)));
        cs.setFuturplural3Enclitic(setPronoun(PuterConjugationPronouns.pron_r_3pp, conjugation.get(PuterConjugationStructure.futurplural3enclitic)));

        // FUTUR DUBITATIV
        cs.setFuturdubitativsing1Enclitic(setPronoun(PuterConjugationPronouns.pron_r_1ps, conjugation.get(PuterConjugationStructure.futurdubitativsing1enclitic)));
        cs.setFuturdubitativsing2Enclitic(setPronoun(PuterConjugationPronouns.pron_r_2ps, conjugation.get(PuterConjugationStructure.futurdubitativsing2enclitic)));
        cs.setFuturdubitativsing3EncliticM(setPronoun(PuterConjugationPronouns.pron_r_3ps, conjugation.get(PuterConjugationStructure.futurdubitativsing3encliticm)));
        cs.setFuturdubitativsing3EncliticF(setPronoun(PuterConjugationPronouns.pron_r_3ps, conjugation.get(PuterConjugationStructure.futurdubitativsing3encliticf)));
        cs.setFuturdubitativplural1Enclitic(setPronoun(PuterConjugationPronouns.pron_r_1pp, conjugation.get(PuterConjugationStructure.futurdubitativplural1enclitic)));
        cs.setFuturdubitativplural2Enclitic(setPronoun(PuterConjugationPronouns.pron_r_2pp, conjugation.get(PuterConjugationStructure.futurdubitativplural2enclitic)));
        cs.setFuturdubitativplural3Enclitic(setPronoun(PuterConjugationPronouns.pron_r_3pp, conjugation.get(PuterConjugationStructure.futurdubitativplural3enclitic)));

        return cs.getAllFormValues();
    }

    private HashMap<String, String> addReflexivePronounsVowel(Map<String, String> conjugation, InflectionSubType subType) {
        cs = new PuterConjugationStructure();
        cs.setVerb(conjugation.get("verb"));
        cs.setInfinitiv(PuterConjugationPronouns.pron_r_v_3ps + conjugation.get(PuterConjugationStructure.infinitiv));
        cs.setRoot(conjugation.get(PuterConjugationStructure.root));
        cs.setEnding(conjugation.get(PuterConjugationStructure.ending));
        cs.setReflexive(conjugation.get(PuterConjugationStructure.reflexive));
        cs.setConjugationClass(subType);

        // PRESCHENT
        cs.setPreschentsing1(setPronoun(PuterConjugationPronouns.pron_1ps + PuterConjugationPronouns.pron_r_v_1ps, conjugation.get(PuterConjugationStructure.preschentsing1)));
        cs.setPreschentsing2(setPronoun(PuterConjugationPronouns.pron_2ps + PuterConjugationPronouns.pron_r_v_2ps, conjugation.get(PuterConjugationStructure.preschentsing2)));
        cs.setPreschentsing3(setPronoun(PuterConjugationPronouns.pron_3ps + PuterConjugationPronouns.pron_r_v_3ps, conjugation.get(PuterConjugationStructure.preschentsing3)));
        cs.setPreschentplural1(setPronoun(PuterConjugationPronouns.pron_1pp + PuterConjugationPronouns.pron_r_v_1pp, conjugation.get(PuterConjugationStructure.preschentplural1)));
        cs.setPreschentplural2(setPronoun(PuterConjugationPronouns.pron_2pp + PuterConjugationPronouns.pron_r_v_2pp, conjugation.get(PuterConjugationStructure.preschentplural2)));
        cs.setPreschentplural3(setPronoun(PuterConjugationPronouns.pron_3pp + PuterConjugationPronouns.pron_r_v_3pp, conjugation.get(PuterConjugationStructure.preschentplural3)));

        // IMPERFECT
        cs.setImperfectsing1(setPronoun(PuterConjugationPronouns.pron_1ps + PuterConjugationPronouns.pron_r_v_1ps, conjugation.get(PuterConjugationStructure.imperfectsing1)));
        cs.setImperfectsing2(setPronoun(PuterConjugationPronouns.pron_2ps + PuterConjugationPronouns.pron_r_v_2ps, conjugation.get(PuterConjugationStructure.imperfectsing2)));
        cs.setImperfectsing3(setPronoun(PuterConjugationPronouns.pron_3ps + PuterConjugationPronouns.pron_r_v_3ps, conjugation.get(PuterConjugationStructure.imperfectsing3)));
        cs.setImperfectplural1(setPronoun(PuterConjugationPronouns.pron_1pp + PuterConjugationPronouns.pron_r_v_1pp, conjugation.get(PuterConjugationStructure.imperfectplural1)));
        cs.setImperfectplural2(setPronoun(PuterConjugationPronouns.pron_2pp + PuterConjugationPronouns.pron_r_v_2pp, conjugation.get(PuterConjugationStructure.imperfectplural2)));
        cs.setImperfectplural3(setPronoun(PuterConjugationPronouns.pron_3pp + PuterConjugationPronouns.pron_r_v_3pp, conjugation.get(PuterConjugationStructure.imperfectplural3)));

        // CONJUNCTIV
        cs.setConjunctivsing1(setPronoun(PuterConjugationPronouns.pron_conjunctiv_v + PuterConjugationPronouns.pron_1ps + PuterConjugationPronouns.pron_r_v_1ps, conjugation.get(PuterConjugationStructure.conjunctivsing1)));
        cs.setConjunctivsing2(setPronoun(PuterConjugationPronouns.pron_conjunctiv_c + PuterConjugationPronouns.pron_2ps + PuterConjugationPronouns.pron_r_v_2ps, conjugation.get(PuterConjugationStructure.conjunctivsing2)));
        cs.setConjunctivsing3(setPronoun(PuterConjugationPronouns.pron_conjunctiv_v + PuterConjugationPronouns.pron_3ps + PuterConjugationPronouns.pron_r_v_3ps, conjugation.get(PuterConjugationStructure.conjunctivsing3)));
        cs.setConjunctivplural1(setPronoun(PuterConjugationPronouns.pron_conjunctiv_c + PuterConjugationPronouns.pron_1pp + PuterConjugationPronouns.pron_r_v_1pp, conjugation.get(PuterConjugationStructure.conjunctivplural1)));
        cs.setConjunctivplural2(setPronoun(PuterConjugationPronouns.pron_conjunctiv_c + PuterConjugationPronouns.pron_2pp + PuterConjugationPronouns.pron_r_v_2pp, conjugation.get(PuterConjugationStructure.conjunctivplural2)));
        cs.setConjunctivplural3(setPronoun(PuterConjugationPronouns.pron_conjunctiv_v + PuterConjugationPronouns.pron_3pp + PuterConjugationPronouns.pron_r_v_3pp, conjugation.get(PuterConjugationStructure.conjunctivplural3)));

        // CUNDIZIONAL
        cs.setCundizionalsing1(setPronoun(PuterConjugationPronouns.pron_1ps +PuterConjugationPronouns.pron_r_v_1ps, conjugation.get(PuterConjugationStructure.cundizionalsing1)));
        cs.setCundizionalsing2(setPronoun(PuterConjugationPronouns.pron_2ps +PuterConjugationPronouns.pron_r_v_2ps, conjugation.get(PuterConjugationStructure.cundizionalsing2)));
        cs.setCundizionalsing3(setPronoun(PuterConjugationPronouns.pron_3ps +PuterConjugationPronouns.pron_r_v_3ps, conjugation.get(PuterConjugationStructure.cundizionalsing3)));
        cs.setCundizionalplural1(setPronoun(PuterConjugationPronouns.pron_1pp + PuterConjugationPronouns.pron_r_v_1pp, conjugation.get(PuterConjugationStructure.cundizionalplural1)));
        cs.setCundizionalplural2(setPronoun(PuterConjugationPronouns.pron_2pp + PuterConjugationPronouns.pron_r_v_2pp, conjugation.get(PuterConjugationStructure.cundizionalplural2)));
        cs.setCundizionalplural3(setPronoun(PuterConjugationPronouns.pron_3pp + PuterConjugationPronouns.pron_r_v_3pp, conjugation.get(PuterConjugationStructure.cundizionalplural3)));

        // IMPERATIV
        cs.setImperativ1(setPronoun(conjugation.get(PuterConjugationStructure.imperativ1), PuterConjugationPronouns.imperativ_refl_sg, "!"));
        cs.setImperativ2(setPronoun(conjugation.get(PuterConjugationStructure.imperativ2), PuterConjugationPronouns.imperativ_refl_pl, "!"));
        cs.setImperativ3(setPronoun(PuterConjugationPronouns.imperativ_not + PuterConjugationPronouns.pron_r_v_2ps, conjugation.get(PuterConjugationStructure.imperativ3), "!"));
        cs.setImperativ4(setPronoun(PuterConjugationPronouns.imperativ_not + PuterConjugationPronouns.pron_r_v_2pp, conjugation.get(PuterConjugationStructure.imperativ4), "!"));
        cs.setImperativ5(setPronoun(PuterConjugationPronouns.imperativ_polite_sg + PuterConjugationPronouns.pron_r_v_3ps, conjugation.get(PuterConjugationStructure.imperativ5), "!"));
        cs.setImperativ6(setPronoun(PuterConjugationPronouns.imperativ_polite_pl + PuterConjugationPronouns.pron_r_v_3pp, conjugation.get(PuterConjugationStructure.imperativ6), "!"));

        // PARTICIP_PERFECT
        cs.setParticipperfectms(conjugation.get(PuterConjugationStructure.participperfectms));
        cs.setParticipperfectfs(conjugation.get(PuterConjugationStructure.participperfectfs));
        cs.setParticipperfectmp(conjugation.get(PuterConjugationStructure.participperfectmp));
        cs.setParticipperfectfp(conjugation.get(PuterConjugationStructure.participperfectfp));

        // GERUNDIUM
        cs.setGerundium(setPronoun(PuterConjugationPronouns.pron_r_v_3pp, conjugation.get(PuterConjugationStructure.gerundium)));

        // FUTUR
        cs.setFutursing1(setPronoun(PuterConjugationPronouns.pron_1ps + PuterConjugationPronouns.pron_r_v_1ps, conjugation.get(PuterConjugationStructure.futursing1)));
        cs.setFutursing2(setPronoun(PuterConjugationPronouns.pron_2ps + PuterConjugationPronouns.pron_r_v_2ps, conjugation.get(PuterConjugationStructure.futursing2)));
        cs.setFutursing3(setPronoun(PuterConjugationPronouns.pron_3ps + PuterConjugationPronouns.pron_r_v_3ps, conjugation.get(PuterConjugationStructure.futursing3)));
        cs.setFuturplural1(setPronoun(PuterConjugationPronouns.pron_1pp + PuterConjugationPronouns.pron_r_v_1pp, conjugation.get(PuterConjugationStructure.futurplural1)));
        cs.setFuturplural2(setPronoun(PuterConjugationPronouns.pron_2pp + PuterConjugationPronouns.pron_r_v_2pp, conjugation.get(PuterConjugationStructure.futurplural2)));
        cs.setFuturplural3(setPronoun(PuterConjugationPronouns.pron_3pp + PuterConjugationPronouns.pron_r_v_3pp, conjugation.get(PuterConjugationStructure.futurplural3)));

        // FUTUR DUBITATIV
        cs.setFuturdubitativsing1(setPronoun(PuterConjugationPronouns.pron_1ps + PuterConjugationPronouns.pron_r_v_1ps, conjugation.get(PuterConjugationStructure.futurdubitativsing1)));
        cs.setFuturdubitativsing2(setPronoun(PuterConjugationPronouns.pron_2ps + PuterConjugationPronouns.pron_r_v_2ps, conjugation.get(PuterConjugationStructure.futurdubitativsing2)));
        cs.setFuturdubitativsing3(setPronoun(PuterConjugationPronouns.pron_3ps + PuterConjugationPronouns.pron_r_v_3ps, conjugation.get(PuterConjugationStructure.futurdubitativsing3)));
        cs.setFuturdubitativplural1(setPronoun(PuterConjugationPronouns.pron_1pp + PuterConjugationPronouns.pron_r_v_1pp, conjugation.get(PuterConjugationStructure.futurdubitativplural1)));
        cs.setFuturdubitativplural2(setPronoun(PuterConjugationPronouns.pron_2pp + PuterConjugationPronouns.pron_r_v_2pp, conjugation.get(PuterConjugationStructure.futurdubitativplural2)));
        cs.setFuturdubitativplural3(setPronoun(PuterConjugationPronouns.pron_3pp + PuterConjugationPronouns.pron_r_v_3pp, conjugation.get(PuterConjugationStructure.futurdubitativplural3)));

        copyEncliticForms(conjugation, cs);

        // PRESCHENT
        cs.setPreschentsing1Enclitic(setPronoun(PuterConjugationPronouns.pron_r_v_1ps, conjugation.get(PuterConjugationStructure.preschentsing1enclitic)));
        cs.setPreschentsing2Enclitic(setPronoun(PuterConjugationPronouns.pron_r_v_2ps, conjugation.get(PuterConjugationStructure.preschentsing2enclitic)));
        cs.setPreschentsing3EncliticM(setPronoun(PuterConjugationPronouns.pron_r_v_3ps, conjugation.get(PuterConjugationStructure.preschentsing3encliticm)));
        cs.setPreschentsing3EncliticF(setPronoun(PuterConjugationPronouns.pron_r_v_3ps, conjugation.get(PuterConjugationStructure.preschentsing3encliticf)));
        cs.setPreschentplural1Enclitic(setPronoun(PuterConjugationPronouns.pron_r_v_1pp, conjugation.get(PuterConjugationStructure.preschentplural1enclitic)));
        cs.setPreschentplural2Enclitic(setPronoun(PuterConjugationPronouns.pron_r_v_2pp,conjugation.get(PuterConjugationStructure.preschentplural2enclitic)));
        cs.setPreschentplural3Enclitic(setPronoun(PuterConjugationPronouns.pron_r_v_3pp, conjugation.get(PuterConjugationStructure.preschentplural3enclitic)));

        // IMPERFECT
        cs.setImperfectsing1Enclitic(setPronoun(PuterConjugationPronouns.pron_r_v_1ps, conjugation.get(PuterConjugationStructure.imperfectsing1enclitic)));
        cs.setImperfectsing2Enclitic(setPronoun(PuterConjugationPronouns.pron_r_v_2ps, conjugation.get(PuterConjugationStructure.imperfectsing2enclitic)));
        cs.setImperfectsing3EncliticM(setPronoun(PuterConjugationPronouns.pron_r_v_3ps, conjugation.get(PuterConjugationStructure.imperfectsing3encliticm)));
        cs.setImperfectsing3EncliticF(setPronoun(PuterConjugationPronouns.pron_r_v_3ps, conjugation.get(PuterConjugationStructure.imperfectsing3encliticf)));
        cs.setImperfectplural1Enclitic(setPronoun(PuterConjugationPronouns.pron_r_v_1pp, conjugation.get(PuterConjugationStructure.imperfectplural1enclitic)));
        cs.setImperfectplural2Enclitic(setPronoun(PuterConjugationPronouns.pron_r_v_2pp, conjugation.get(PuterConjugationStructure.imperfectplural2enclitic)));
        cs.setImperfectplural3Enclitic(setPronoun(PuterConjugationPronouns.pron_r_v_3pp, conjugation.get(PuterConjugationStructure.imperfectplural3enclitic)));

        // CUNDIZIONAL
        cs.setCundizionalsing1Enclitic(setPronoun(PuterConjugationPronouns.pron_r_v_1ps, conjugation.get(PuterConjugationStructure.cundizionalsing1enclitic)));
        cs.setCundizionalsing2Enclitic(setPronoun(PuterConjugationPronouns.pron_r_v_2ps, conjugation.get(PuterConjugationStructure.cundizionalsing2enclitic)));
        cs.setCundizionalsing3EncliticM(setPronoun(PuterConjugationPronouns.pron_r_v_3ps, conjugation.get(PuterConjugationStructure.cundizionalsing3encliticm)));
        cs.setCundizionalsing3EncliticF(setPronoun(PuterConjugationPronouns.pron_r_v_3ps, conjugation.get(PuterConjugationStructure.cundizionalsing3encliticf)));
        cs.setCundizionalplural1Enclitic(setPronoun(PuterConjugationPronouns.pron_r_v_1pp, conjugation.get(PuterConjugationStructure.cundizionalplural1enclitic)));
        cs.setCundizionalplural2Enclitic(setPronoun(PuterConjugationPronouns.pron_r_v_2pp, conjugation.get(PuterConjugationStructure.cundizionalplural2enclitic)));
        cs.setCundizionalplural3Enclitic(setPronoun(PuterConjugationPronouns.pron_r_v_3pp, conjugation.get(PuterConjugationStructure.cundizionalplural3enclitic)));

        // FUTUR
        cs.setFutursing1Enclitic(setPronoun(PuterConjugationPronouns.pron_r_v_1ps, conjugation.get(PuterConjugationStructure.futursing1enclitic)));
        cs.setFutursing2Enclitic(setPronoun(PuterConjugationPronouns.pron_r_v_2ps, conjugation.get(PuterConjugationStructure.futursing2enclitic)));
        cs.setFutursing3EncliticM(setPronoun(PuterConjugationPronouns.pron_r_v_3ps, conjugation.get(PuterConjugationStructure.futursing3encliticm)));
        cs.setFutursing3EncliticF(setPronoun(PuterConjugationPronouns.pron_r_v_3ps, conjugation.get(PuterConjugationStructure.futursing3encliticf)));
        cs.setFuturplural1Enclitic(setPronoun(PuterConjugationPronouns.pron_r_v_1pp, conjugation.get(PuterConjugationStructure.futurplural1enclitic)));
        cs.setFuturplural2Enclitic(setPronoun(PuterConjugationPronouns.pron_r_v_2pp, conjugation.get(PuterConjugationStructure.futurplural2enclitic)));
        cs.setFuturplural3Enclitic(setPronoun(PuterConjugationPronouns.pron_r_v_3pp, conjugation.get(PuterConjugationStructure.futurplural3enclitic)));

        // FUTUR DUBITATIV
        cs.setFuturdubitativsing1Enclitic(setPronoun(PuterConjugationPronouns.pron_r_v_1ps, conjugation.get(PuterConjugationStructure.futurdubitativsing1enclitic)));
        cs.setFuturdubitativsing2Enclitic(setPronoun(PuterConjugationPronouns.pron_r_v_2ps, conjugation.get(PuterConjugationStructure.futurdubitativsing2enclitic)));
        cs.setFuturdubitativsing3EncliticM(setPronoun(PuterConjugationPronouns.pron_r_v_3ps, conjugation.get(PuterConjugationStructure.futurdubitativsing3encliticm)));
        cs.setFuturdubitativsing3EncliticF(setPronoun(PuterConjugationPronouns.pron_r_v_3ps, conjugation.get(PuterConjugationStructure.futurdubitativsing3encliticf)));
        cs.setFuturdubitativplural1Enclitic(setPronoun(PuterConjugationPronouns.pron_r_v_1pp, conjugation.get(PuterConjugationStructure.futurdubitativplural1enclitic)));
        cs.setFuturdubitativplural2Enclitic(setPronoun(PuterConjugationPronouns.pron_r_v_2pp, conjugation.get(PuterConjugationStructure.futurdubitativplural2enclitic)));
        cs.setFuturdubitativplural3Enclitic(setPronoun(PuterConjugationPronouns.pron_r_v_3pp, conjugation.get(PuterConjugationStructure.futurdubitativplural3enclitic)));

        return cs.getAllFormValues();
    }

    private HashMap<String, String> addStandardPronouns(Map<String, String> conjugation, InflectionSubType subType) {

        PuterConjugationStructure cs = new PuterConjugationStructure();

        cs.setVerb(conjugation.get("verb"));
        cs.setInfinitiv(conjugation.get(PuterConjugationStructure.infinitiv));
        cs.setRoot(conjugation.get(PuterConjugationStructure.root));
        cs.setEnding(conjugation.get(PuterConjugationStructure.ending));
        cs.setReflexive(conjugation.get(PuterConjugationStructure.reflexive));
        cs.setConjugationClass(subType);

        // PRESCHENT
        cs.setPreschentsing1(setPronoun(PuterConjugationPronouns.pron_1ps, conjugation.get(PuterConjugationStructure.preschentsing1)));
        cs.setPreschentsing2(setPronoun(PuterConjugationPronouns.pron_2ps, conjugation.get(PuterConjugationStructure.preschentsing2)));
        cs.setPreschentsing3(setPronoun(PuterConjugationPronouns.pron_3ps, conjugation.get(PuterConjugationStructure.preschentsing3)));
        cs.setPreschentplural1(setPronoun(PuterConjugationPronouns.pron_1pp, conjugation.get(PuterConjugationStructure.preschentplural1)));
        cs.setPreschentplural2(setPronoun(PuterConjugationPronouns.pron_2pp, conjugation.get(PuterConjugationStructure.preschentplural2)));
        cs.setPreschentplural3(setPronoun(PuterConjugationPronouns.pron_3pp, conjugation.get(PuterConjugationStructure.preschentplural3)));

        // IMPERFECT
        cs.setImperfectsing1(setPronoun(PuterConjugationPronouns.pron_1ps, conjugation.get(PuterConjugationStructure.imperfectsing1)));
        cs.setImperfectsing2(setPronoun(PuterConjugationPronouns.pron_2ps, conjugation.get(PuterConjugationStructure.imperfectsing2)));
        cs.setImperfectsing3(setPronoun(PuterConjugationPronouns.pron_3ps, conjugation.get(PuterConjugationStructure.imperfectsing3)));
        cs.setImperfectplural1(setPronoun(PuterConjugationPronouns.pron_1pp, conjugation.get(PuterConjugationStructure.imperfectplural1)));
        cs.setImperfectplural2(setPronoun(PuterConjugationPronouns.pron_2pp, conjugation.get(PuterConjugationStructure.imperfectplural2)));
        cs.setImperfectplural3(setPronoun(PuterConjugationPronouns.pron_3pp, conjugation.get(PuterConjugationStructure.imperfectplural3)));

        // CONJUNCTIV
        cs.setConjunctivsing1(setPronoun(PuterConjugationPronouns.pron_conjunctiv_v + PuterConjugationPronouns.pron_1ps, conjugation.get(PuterConjugationStructure.conjunctivsing1)));
        cs.setConjunctivsing2(setPronoun(PuterConjugationPronouns.pron_conjunctiv_c + PuterConjugationPronouns.pron_2ps, conjugation.get(PuterConjugationStructure.conjunctivsing2)));
        cs.setConjunctivsing3(setPronoun(PuterConjugationPronouns.pron_conjunctiv_v + PuterConjugationPronouns.pron_3ps, conjugation.get(PuterConjugationStructure.conjunctivsing3)));
        cs.setConjunctivplural1(setPronoun(PuterConjugationPronouns.pron_conjunctiv_c + PuterConjugationPronouns.pron_1pp, conjugation.get(PuterConjugationStructure.conjunctivplural1)));
        cs.setConjunctivplural2(setPronoun(PuterConjugationPronouns.pron_conjunctiv_c + PuterConjugationPronouns.pron_2pp, conjugation.get(PuterConjugationStructure.conjunctivplural2)));
        cs.setConjunctivplural3(setPronoun(PuterConjugationPronouns.pron_conjunctiv_v + PuterConjugationPronouns.pron_3pp, conjugation.get(PuterConjugationStructure.conjunctivplural3)));

        // CUNDIZIONAL
        cs.setCundizionalsing1(setPronoun(PuterConjugationPronouns.pron_1ps, conjugation.get(PuterConjugationStructure.cundizionalsing1)));
        cs.setCundizionalsing2(setPronoun(PuterConjugationPronouns.pron_2ps, conjugation.get(PuterConjugationStructure.cundizionalsing2)));
        cs.setCundizionalsing3(setPronoun(PuterConjugationPronouns.pron_3ps, conjugation.get(PuterConjugationStructure.cundizionalsing3)));
        cs.setCundizionalplural1(setPronoun(PuterConjugationPronouns.pron_1pp, conjugation.get(PuterConjugationStructure.cundizionalplural1)));
        cs.setCundizionalplural2(setPronoun(PuterConjugationPronouns.pron_2pp, conjugation.get(PuterConjugationStructure.cundizionalplural2)));
        cs.setCundizionalplural3(setPronoun(PuterConjugationPronouns.pron_3pp, conjugation.get(PuterConjugationStructure.cundizionalplural3)));

        // IMPERATIV
        cs.setImperativ1(setPronoun("", conjugation.get(PuterConjugationStructure.imperativ1), "!"));
        cs.setImperativ2(setPronoun("", conjugation.get(PuterConjugationStructure.imperativ2), "!"));
        cs.setImperativ3(setPronoun(PuterConjugationPronouns.imperativ_not, conjugation.get(PuterConjugationStructure.imperativ3), "!"));
        cs.setImperativ4(setPronoun(PuterConjugationPronouns.imperativ_not, conjugation.get(PuterConjugationStructure.imperativ4), "!"));
        cs.setImperativ5(setPronoun(PuterConjugationPronouns.imperativ_polite_sg, conjugation.get(PuterConjugationStructure.imperativ5), "!"));
        cs.setImperativ6(setPronoun(PuterConjugationPronouns.imperativ_polite_pl, conjugation.get(PuterConjugationStructure.imperativ6), "!"));

        // PARTICIP_PERFECT
        cs.setParticipperfectms(conjugation.get(PuterConjugationStructure.participperfectms));
        cs.setParticipperfectfs(conjugation.get(PuterConjugationStructure.participperfectfs));
        cs.setParticipperfectmp(conjugation.get(PuterConjugationStructure.participperfectmp));
        cs.setParticipperfectfp(conjugation.get(PuterConjugationStructure.participperfectfp));

        // GERUNDIUM
        cs.setGerundium(conjugation.get(PuterConjugationStructure.gerundium));

        // FUTUR
        cs.setFutursing1(setPronoun(PuterConjugationPronouns.pron_1ps, conjugation.get(PuterConjugationStructure.futursing1)));
        cs.setFutursing2(setPronoun(PuterConjugationPronouns.pron_2ps, conjugation.get(PuterConjugationStructure.futursing2)));
        cs.setFutursing3(setPronoun(PuterConjugationPronouns.pron_3ps, conjugation.get(PuterConjugationStructure.futursing3)));
        cs.setFuturplural1(setPronoun(PuterConjugationPronouns.pron_1pp, conjugation.get(PuterConjugationStructure.futurplural1)));
        cs.setFuturplural2(setPronoun(PuterConjugationPronouns.pron_2pp, conjugation.get(PuterConjugationStructure.futurplural2)));
        cs.setFuturplural3(setPronoun(PuterConjugationPronouns.pron_3pp, conjugation.get(PuterConjugationStructure.futurplural3)));

        // FUTUR
        cs.setFuturdubitativsing1(setPronoun(PuterConjugationPronouns.pron_1ps, conjugation.get(PuterConjugationStructure.futurdubitativsing1)));
        cs.setFuturdubitativsing2(setPronoun(PuterConjugationPronouns.pron_2ps, conjugation.get(PuterConjugationStructure.futurdubitativsing2)));
        cs.setFuturdubitativsing3(setPronoun(PuterConjugationPronouns.pron_3ps, conjugation.get(PuterConjugationStructure.futurdubitativsing3)));
        cs.setFuturdubitativplural1(setPronoun(PuterConjugationPronouns.pron_1pp, conjugation.get(PuterConjugationStructure.futurdubitativplural1)));
        cs.setFuturdubitativplural2(setPronoun(PuterConjugationPronouns.pron_2pp, conjugation.get(PuterConjugationStructure.futurdubitativplural2)));
        cs.setFuturdubitativplural3(setPronoun(PuterConjugationPronouns.pron_3pp, conjugation.get(PuterConjugationStructure.futurdubitativplural3)));

        copyEncliticForms(conjugation, cs);

        return cs.getAllFormValues();
    }

    private void copyEncliticForms(Map<String, String> conjugation, PuterConjugationStructure cs) {
        cs.setPreschentsing1Enclitic(conjugation.get(PuterConjugationStructure.preschentsing1enclitic));
        cs.setPreschentsing2Enclitic(conjugation.get(PuterConjugationStructure.preschentsing2enclitic));
        cs.setPreschentsing3EncliticM(conjugation.get(PuterConjugationStructure.preschentsing3encliticm));
        cs.setPreschentsing3EncliticF(conjugation.get(PuterConjugationStructure.preschentsing3encliticf));
        cs.setPreschentplural1Enclitic(conjugation.get(PuterConjugationStructure.preschentplural1enclitic));
        cs.setPreschentplural2Enclitic(conjugation.get(PuterConjugationStructure.preschentplural2enclitic));
        cs.setPreschentplural3Enclitic(conjugation.get(PuterConjugationStructure.preschentplural3enclitic));

        cs.setImperfectsing1Enclitic(conjugation.get(PuterConjugationStructure.imperfectsing1enclitic));
        cs.setImperfectsing2Enclitic(conjugation.get(PuterConjugationStructure.imperfectsing2enclitic));
        cs.setImperfectsing3EncliticM(conjugation.get(PuterConjugationStructure.imperfectsing3encliticm));
        cs.setImperfectsing3EncliticF(conjugation.get(PuterConjugationStructure.imperfectsing3encliticf));
        cs.setImperfectplural1Enclitic(conjugation.get(PuterConjugationStructure.imperfectplural1enclitic));
        cs.setImperfectplural2Enclitic(conjugation.get(PuterConjugationStructure.imperfectplural2enclitic));
        cs.setImperfectplural3Enclitic(conjugation.get(PuterConjugationStructure.imperfectplural3enclitic));

        cs.setCundizionalsing1Enclitic(conjugation.get(PuterConjugationStructure.cundizionalsing1enclitic));
        cs.setCundizionalsing2Enclitic(conjugation.get(PuterConjugationStructure.cundizionalsing2enclitic));
        cs.setCundizionalsing3EncliticM(conjugation.get(PuterConjugationStructure.cundizionalsing3encliticm));
        cs.setCundizionalsing3EncliticF(conjugation.get(PuterConjugationStructure.cundizionalsing3encliticf));
        cs.setCundizionalplural1Enclitic(conjugation.get(PuterConjugationStructure.cundizionalplural1enclitic));
        cs.setCundizionalplural2Enclitic(conjugation.get(PuterConjugationStructure.cundizionalplural2enclitic));
        cs.setCundizionalplural3Enclitic(conjugation.get(PuterConjugationStructure.cundizionalplural3enclitic));

        cs.setFutursing1Enclitic(conjugation.get(PuterConjugationStructure.futursing1enclitic));
        cs.setFutursing2Enclitic(conjugation.get(PuterConjugationStructure.futursing2enclitic));
        cs.setFutursing3EncliticM(conjugation.get(PuterConjugationStructure.futursing3encliticm));
        cs.setFutursing3EncliticF(conjugation.get(PuterConjugationStructure.futursing3encliticf));
        cs.setFuturplural1Enclitic(conjugation.get(PuterConjugationStructure.futurplural1enclitic));
        cs.setFuturplural2Enclitic(conjugation.get(PuterConjugationStructure.futurplural2enclitic));
        cs.setFuturplural3Enclitic(conjugation.get(PuterConjugationStructure.futurplural3enclitic));

        cs.setFuturdubitativsing1Enclitic(conjugation.get(PuterConjugationStructure.futurdubitativsing1enclitic));
        cs.setFuturdubitativsing2Enclitic(conjugation.get(PuterConjugationStructure.futurdubitativsing2enclitic));
        cs.setFuturdubitativsing3EncliticM(conjugation.get(PuterConjugationStructure.futurdubitativsing3encliticm));
        cs.setFuturdubitativsing3EncliticF(conjugation.get(PuterConjugationStructure.futurdubitativsing3encliticf));
        cs.setFuturdubitativplural1Enclitic(conjugation.get(PuterConjugationStructure.futurdubitativplural1enclitic));
        cs.setFuturdubitativplural2Enclitic(conjugation.get(PuterConjugationStructure.futurdubitativplural2enclitic));
        cs.setFuturdubitativplural3Enclitic(conjugation.get(PuterConjugationStructure.futurdubitativplural3enclitic));
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
        lastTwo = null;
        lastThree = null;
        conjugation = null;
        root = null;
        ending = null;
        modRoot = null;
    }
}
