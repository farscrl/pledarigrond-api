package ch.pledarigrond.inflection.generation.surmiran;

import ch.pledarigrond.inflection.generation.generic.LanguageConjugation;
import ch.pledarigrond.inflection.model.InflectionResponse;
import ch.pledarigrond.inflection.model.InflectionSubType;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class SurmiranConjugation extends LanguageConjugation {

    private char vowelInRoot;

    private SurmiranConjugationStructure cs;

    private String infinitiv;
    private String verb;

    private String isReflexive;

    private String lastTwo;
    private String lastThree;

    private HashMap<String, String> conjugation;

    public InflectionResponse generateConjugation(String conjugationClass, String infinitiv) {
        root = getRoot(infinitiv);

        if (conjugationClass.equals("9")) {
            modRoot = changeVocalInRoot(root);

            if (modRoot == null) {
                throw new RuntimeException("For this conjugation you need to enter a verb with a vowel in its root!");
            }
        }

        InflectionSubType subType = SurmiranConjugationClasses.getConjugationClass(conjugationClass);
        if (subType == null) {
            throw new RuntimeException(conjugationClass + " is not a valid conjugation class.");
        } else if (getEnding() == null) {
            throw new RuntimeException(infinitiv + "  is not a valid verb. Please enter a verb in its infinitive form.");
        }

        conjugation = conjugate(root, subType);

        return new InflectionResponse(subType, addPronouns(conjugation, subType));
    }

    public InflectionResponse guessInflection(String baseForm, String genus, String flex) {
        // TODO: implement me
        return null;
    }

    private String getRoot(String infinitiv) {
        if (infinitiv != null) {
            infinitiv = removeWhitespaces(infinitiv);

            if (infinitiv.equals("eir")) {
                setVerb(infinitiv);
                setEnding(infinitiv);
                infinitiv = checkReflexiveness(infinitiv);
                return infinitiv;
            }

            switch (infinitiv.length()) {
                case 0:
                case 1:
                case 2:
                    throw new RuntimeException("'" + infinitiv + "' is not a valid verb. Please enter a verb in its infinitive form.");

                default:
                    infinitiv = processQuery(infinitiv);
            }
        }
        return infinitiv;
    }

    private String changeVocalInRoot(String root) {
        StringBuilder builder = null;

        for (int i = root.length() - 1; i >= 0; i--) {
            char ch = root.charAt(i);
            if (isVocal(ch)) {
                setVowelInRoot(ch);
                builder = new StringBuilder(root);
                switch (ch) {
                    case 'a' -> builder.setCharAt(i, 'o');
                    case 'e' -> builder.setCharAt(i, 'a');
                    case 'i' -> builder.setCharAt(i, 'e');
                    case 'o' -> builder.setCharAt(i, 'u');
                    case 'u' -> builder.setCharAt(i, 'o');
                }
                break;
            }
        }

        if (builder == null) {
            return null;
        }

        return builder.toString();
    }

    private String processQuery(String infintiv) {
        String l2 = infintiv.substring(infintiv.length() - 2);
        String l3 = infintiv.substring(infintiv.length() - 3);

        switch (l2) {
            case "ar":
            case "er":
            case "ir":
                if (!l3.equals("eir") && !l3.equals("ier")) {
                    setLastTwo(l2);
                    setEnding(getLastTwo());
                    infintiv = checkReflexiveness(infintiv);
                    infintiv = infintiv.substring(0, infintiv.length() - 2);

                    return infintiv;
                }
                break;
        }

        if (l3.equals("eir")) {
            setLastThree(l3);
            setEnding(getLastThree());
            infintiv = checkReflexiveness(infintiv);
            infintiv = infintiv.substring(0, infintiv.length() - 3);

            return infintiv;
        }

        if (l3.equals("ier")) {
            setLastThree(l3);
            setEnding(getLastThree());
            infintiv = checkReflexiveness(infintiv);
            infintiv = infintiv.substring(0, infintiv.length() - 3);

            return infintiv;
        }

        return infintiv;
    }

    private String checkReflexiveness(String query) {

        if (query.startsWith("sa ")) {
            setVerb(query);
            query = query.length() > 2 ? query.substring(3) : query;
            setIsReflexive(new String("true"));

        } else if (query.startsWith("s'")) {
            setVerb(query);
            query = query.length() > 2 ? query.substring(2) : query;
            setIsReflexive(new String("true"));
        } else {
            setIsReflexive(new String("false"));
            setVerb(query);
        }

        setInfinitiv(query);

        return query;
    }

    private HashMap<String, String> conjugate(String root, InflectionSubType conjugationClass) {

        cs = new SurmiranConjugationStructure();
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

        return cs.getValues();
    }

    private void setPreschent(SurmiranConjugationStructure cs) {

        switch (cs.getConjugationclass().id) {

            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
                // 1ps
                if (ending.equals("ar") && !getInfinitiv().equals("mussar")) {
                    if (endsWithDoubleConsonant(root)) {
                        String firstSingular = root.substring(0, root.length() - 1);
                        cs.setPreschentsing1(firstSingular);
                    } else {
                        cs.setPreschentsing1(root);
                    }
                }

                else {
                    cs.setPreschentsing1(root);
                }

                cs.setPreschentsing2(root + "as");
                cs.setPreschentsing3(root + "a");

                if (cs.getConjugationclass().id.equals("6")) {
                    // 1pp
                    cs.setPreschentplural1(root + "ign");
                    // 2pp
                    cs.setPreschentplural2(root + "iz");
                } else {

                    // 1pp
                    cs.setPreschentplural1(root + "agn");
                    // 2pp
                    cs.setPreschentplural2(root + "ez");
                }

                cs.setPreschentplural3(root + "an");
                break;

            case "7":
            case "8":

                // PRESCHENT
                // 1ps
                cs.setPreschentsing1(root + "esch");
                // 2ps
                cs.setPreschentsing2(root + "eschas");
                // 3ps
                cs.setPreschentsing3(root + "escha");

                if (cs.getConjugationclass().id.equals("7")) {
                    // 1pp
                    cs.setPreschentplural1(root + "ign");
                    // 2pp
                    cs.setPreschentplural2(root + "iz");
                } else {
                    // 1pp
                    cs.setPreschentplural1(root + "agn");
                    // 2pp
                    cs.setPreschentplural2(root + "ez");
                }

                // 3pp
                cs.setPreschentplural3(root + "eschan");
                break;

            case "9":
                switch (getVowelInRoot()) {
                    case 'a':
                    case 'u':
                    case 'i':
                        // PRESCHENT
                        // 1ps
                        if (ending.equals("ar") && !getInfinitiv().equals("mussar")) {

                            if (endsWithDoubleConsonant(root)) {
                                String firstSingular = modRoot.substring(0, modRoot.length() - 1);
                                cs.setPreschentsing1(firstSingular);
                            } else {
                                cs.setPreschentsing1(modRoot);
                            }
                        }

                        else {
                            cs.setPreschentsing1(modRoot);
                        }

                        cs.setPreschentsing2(modRoot + "as");
                        cs.setPreschentsing3(modRoot + "a");
                        cs.setPreschentplural1(root + "agn");
                        cs.setPreschentplural2(root + "ez");
                        cs.setPreschentplural3(modRoot + "an");
                        break;

                    case 'e':
                    case 'o':

                        // PRESCHENT
                        // 1ps
                        if (ending.equals("ar")) {

                            if (endsWithDoubleConsonant(root)) {
                                String firstSingular = root.substring(0,
                                        root.length() - 1);
                                cs.setPreschentsing1(firstSingular);
                            } else {

                                cs.setPreschentsing1(root);
                            }

                        }

                        else {
                            cs.setPreschentsing1(root);
                        }

                        cs.setPreschentsing2(root + "as");
                        cs.setPreschentsing3(root + "a");
                        cs.setPreschentplural1(modRoot + "agn");
                        cs.setPreschentplural2(modRoot + "ez");
                        cs.setPreschentplural3(root + "an");
                        break;

                }
        }

    }

    private void setImperfect(SurmiranConjugationStructure cs) {

        switch (cs.getConjugationclass().id) {

            case "1":
            case "8":
                cs.setImperfectsing1(root + "ava");
                cs.setImperfectsing2(root + "avas");
                cs.setImperfectsing3(root + "ava");
                cs.setImperfectplural1(root + "avan");
                cs.setImperfectplural2(root + "avas");
                cs.setImperfectplural3(root + "avan");
                break;

            case "3":
            case "6":
            case "7":
                cs.setImperfectsing1(root + "iva");
                cs.setImperfectsing2(root + "ivas");
                cs.setImperfectsing3(root + "iva");
                cs.setImperfectplural1(root + "ivan");
                cs.setImperfectplural2(root + "ivas");
                cs.setImperfectplural3(root + "ivan");
                break;

            case "2":
            case "4":
            case "5":
                cs.setImperfectsing1(root + "eva");
                cs.setImperfectsing2(root + "evas");
                cs.setImperfectsing3(root + "eva");
                cs.setImperfectplural1(root + "evan");
                cs.setImperfectplural2(root + "evas");
                cs.setImperfectplural3(root + "evan");
                break;

            case "9":
                if (getEnding().equals("ar") && getVowelInRoot() == 'u') {
                    cs.setImperfectsing1(root + "ava");
                    cs.setImperfectsing2(root + "avas");
                    cs.setImperfectsing3(root + "ava");
                    cs.setImperfectplural1(root + "avan");
                    cs.setImperfectplural2(root + "avas");
                    cs.setImperfectplural3(root + "avan");
                    break;

                } else {
                    switch (getVowelInRoot()) {
                        case 'a':
                        case 'i':
                            cs.setImperfectsing1(root + "ava");
                            cs.setImperfectsing2(root + "avas");
                            cs.setImperfectsing3(root + "ava");
                            cs.setImperfectplural1(root + "avan");
                            cs.setImperfectplural2(root + "avas");
                            cs.setImperfectplural3(root + "avan");
                            break;

                        case 'e':
                        case 'o':
                        case 'u':
                            cs.setImperfectsing1(modRoot + "eva");
                            cs.setImperfectsing2(modRoot + "evas");
                            cs.setImperfectsing3(modRoot + "eva");
                            cs.setImperfectplural1(modRoot + "evan");
                            cs.setImperfectplural2(modRoot + "evas");
                            cs.setImperfectplural3(modRoot + "evan");
                            break;
                    }

                }
                break;
        }

    }

    private void setConjunctiv(SurmiranConjugationStructure cs) {
        switch (cs.getConjugationclass().id) {

            case "7":
            case "8":
                cs.setConjunctivsing1(root + "escha");
                cs.setConjunctivsing2(root + "eschas");
                cs.setConjunctivsing3(root + "escha");
                cs.setConjunctivplural1(root + "eschan");
                cs.setConjunctivplural2(root + "eschas");
                cs.setConjunctivplural3(root + "eschan");
                break;

            case "9":
                switch (getVowelInRoot()) {
                    case 'e':
                    case 'o':
                        cs.setConjunctivsing1(root + "a");
                        cs.setConjunctivsing2(root + "as");
                        cs.setConjunctivsing3(root + "a");
                        cs.setConjunctivplural1(root + "an");
                        cs.setConjunctivplural2(root + "as");
                        cs.setConjunctivplural3(root + "an");
                        break;

                    case 'a':
                    case 'i':
                    case 'u':
                        cs.setConjunctivsing1(modRoot + "a");
                        cs.setConjunctivsing2(modRoot + "as");
                        cs.setConjunctivsing3(modRoot + "a");
                        cs.setConjunctivplural1(modRoot + "an");
                        cs.setConjunctivplural2(modRoot + "as");
                        cs.setConjunctivplural3(modRoot + "an");
                        break;

                }

                break;

            default:
                cs.setConjunctivsing1(root + "a");
                cs.setConjunctivsing2(root + "as");
                cs.setConjunctivsing3(root + "a");
                cs.setConjunctivplural1(root + "an");
                cs.setConjunctivplural2(root + "as");
                cs.setConjunctivplural3(root + "an");
                break;
        }
    }

    private void setCundizional(SurmiranConjugationStructure cs) {

        switch (cs.getConjugationclass().id) {

            case "6":
            case "7":
                cs.setCundizionalsing1(root + "iss");
                cs.setCundizionalsing2(root + "issas");
                cs.setCundizionalsing3(root + "iss");
                cs.setCundizionalplural1(root + "issan");
                cs.setCundizionalplural2(root + "issas");
                cs.setCundizionalplural3(root + "issan");
                break;

            case "9":
                switch (getVowelInRoot()) {
                    case 'e':
                    case 'o':
                        cs.setCundizionalsing1(modRoot + "ess");
                        cs.setCundizionalsing2(modRoot + "essas");
                        cs.setCundizionalsing3(modRoot + "ess");
                        cs.setCundizionalplural1(modRoot + "essan");
                        cs.setCundizionalplural2(modRoot + "essas");
                        cs.setCundizionalplural3(modRoot + "essan");
                        break;

                    case 'a':
                    case 'i':
                    case 'u':
                        cs.setCundizionalsing1(root + "ess");
                        cs.setCundizionalsing2(root + "essas");
                        cs.setCundizionalsing3(root + "ess");
                        cs.setCundizionalplural1(root + "essan");
                        cs.setCundizionalplural2(root + "essas");
                        cs.setCundizionalplural3(root + "essan");
                        break;
                }
                break;

            default:
                cs.setCundizionalsing1(root + "ess");
                cs.setCundizionalsing2(root + "essas");
                cs.setCundizionalsing3(root + "ess");
                cs.setCundizionalplural1(root + "essan");
                cs.setCundizionalplural2(root + "essas");
                cs.setCundizionalplural3(root + "essan");
                break;
        }
    }

    private void setParticipPerfect(SurmiranConjugationStructure cs) {

        switch (cs.getConjugationclass().id) {
            case "1":
            case "8":
                cs.setParticipperfectms(root + "o");
                cs.setParticipperfectmp(root + "os");
                cs.setParticipperfectfs(root + "ada");
                cs.setParticipperfectfp(root + "adas");
                break;

            case "2":
                cs.setParticipperfectms(root + "ea");
                cs.setParticipperfectmp(root + "eas");
                cs.setParticipperfectfs(root + "eda");
                cs.setParticipperfectfp(root + "edas");
                break;

            case "9":
                if (ending.equals("ar")) {
                    cs.setParticipperfectms(root + "o");
                    cs.setParticipperfectmp(root + "os");
                    cs.setParticipperfectfs(root + "ada");
                    cs.setParticipperfectfp(root + "adas");
                }
                else {
                    switch (getVowelInRoot()) {
                        case 'e':
                        case 'o':
                            cs.setParticipperfectms(modRoot + "ia");
                            cs.setParticipperfectmp(modRoot + "ias");
                            cs.setParticipperfectfs(modRoot + "eida");
                            cs.setParticipperfectfp(modRoot + "eidas");
                            break;

                        case 'a':
                            cs.setParticipperfectms(root + "o");
                            cs.setParticipperfectmp(root + "os");
                            cs.setParticipperfectfs(root + "ada");
                            cs.setParticipperfectfp(root + "adas");
                            break;

                        case 'i':
                            cs.setParticipperfectms(modRoot + "a");
                            cs.setParticipperfectmp(modRoot + "as");
                            cs.setParticipperfectfs(modRoot + "ada");
                            cs.setParticipperfectfp(modRoot + "adas");
                            break;

                        case 'u':
                            cs.setParticipperfectms(root + "ia");
                            cs.setParticipperfectmp(root + "ias");
                            cs.setParticipperfectfs(root + "eida");
                            cs.setParticipperfectfp(root + "eidas");
                            break;

                    }
                }
                break;

            default:
                cs.setParticipperfectms(root + "ia");
                cs.setParticipperfectmp(root + "ias");
                cs.setParticipperfectfs(root + "eida");
                cs.setParticipperfectfp(root + "eidas");
                break;
        }
    }

    private void setGerundium(SurmiranConjugationStructure cs) {

        switch (cs.getConjugationclass().id) {
            case "6":
            case "7":
                cs.setGerundium(root + "ond" + "/" + root + "end");
                break;

            case "9":
                switch (getVowelInRoot()) {
                    case 'a':
                    case 'i':
                    case 'u':
                        cs.setGerundium(root + "ond");
                        break;

                    case 'o':
                    case 'e':
                        cs.setGerundium(modRoot + "ond");
                        break;

                }
                break;
            default:
                cs.setGerundium(root + "ond");
                break;
        }

    }

    private void setImperativ(SurmiranConjugationStructure cs) {
        switch (cs.getConjugationclass().id) {
            case "7":
            case "8":
                cs.setImperativ1(root + "escha!");

                if (cs.getConjugationclass().id.equals("7")) {
                    cs.setImperativ2(root + "i!");
                } else {
                    cs.setImperativ2(root + "e!");
                }
                break;

            case "9":
                switch (getVowelInRoot()) {
                    case 'a':
                    case 'i':
                    case 'u':
                        cs.setImperativ1(modRoot + "a!");
                        cs.setImperativ2(root + "e!");
                        break;

                    case 'e':
                    case 'o':
                        cs.setImperativ1(root + "a!");
                        cs.setImperativ2(modRoot + "e!");
                        break;
                }
                break;
            default:
                cs.setImperativ1(root + "a!");
                if (cs.getConjugationclass().id.equals("6")) {
                    cs.setImperativ2(root + "i!");
                } else {
                    cs.setImperativ2(root + "e!");
                }
                break;
        }
    }

    private void setFutur(SurmiranConjugationStructure cs) {
        switch (getIsReflexive()) {
            case "true":
                if (startsWithVocal(root)) {
                    switch (cs.getConjugationclass().id) {
                        case "6":
                        case "7":
                            cs.setFutursing1(SurmiranPronouns.pron_r_v_1ps + root + "iro");
                            cs.setFutursing2(SurmiranPronouns.pron_r_v_2ps + root + "irossas");
                            cs.setFutursing3(SurmiranPronouns.pron_r_v_3ps + root + "iro");
                            cs.setFuturplural1(SurmiranPronouns.pron_r_v_1pp + root + "iron");
                            cs.setFuturplural2(SurmiranPronouns.pron_r_v_2pp + root + "irossas");
                            cs.setFuturplural3(SurmiranPronouns.pron_r_v_3pp + root + "iron");
                            break;

                        case "9":
                            switch (getVowelInRoot()) {
                                case 'e':
                                case 'o':
                                    cs.setFutursing1(SurmiranPronouns.pron_r_v_1ps + modRoot + "aro");
                                    cs.setFutursing2(SurmiranPronouns.pron_r_v_2ps + modRoot + "arossas");
                                    cs.setFutursing3(SurmiranPronouns.pron_r_v_3ps + modRoot + "aro");
                                    cs.setFuturplural1(SurmiranPronouns.pron_r_v_1pp + modRoot + "aron");
                                    cs.setFuturplural2(SurmiranPronouns.pron_r_v_2pp + modRoot + "arossas");
                                    cs.setFuturplural3(SurmiranPronouns.pron_r_v_3pp + modRoot + "aron");
                                    break;

                                case 'a':
                                case 'i':
                                case 'u':
                                    cs.setFutursing1(SurmiranPronouns.pron_r_v_1ps + root + "aro");
                                    cs.setFutursing2(SurmiranPronouns.pron_r_v_2ps + root + "arossas");
                                    cs.setFutursing3(SurmiranPronouns.pron_r_v_3ps + root + "aro");
                                    cs.setFuturplural1(SurmiranPronouns.pron_r_v_1pp + root + "aron");
                                    cs.setFuturplural2(SurmiranPronouns.pron_r_v_2pp + root + "arossas");
                                    cs.setFuturplural3(SurmiranPronouns.pron_r_v_3pp + root + "aron");
                                    break;
                            }
                            break;

                        default:
                            cs.setFutursing1(SurmiranPronouns.pron_r_v_1ps + root + "aro");
                            cs.setFutursing2(SurmiranPronouns.pron_r_v_2ps + root + "arossas");
                            cs.setFutursing3(SurmiranPronouns.pron_r_v_3ps + root + "aro");
                            cs.setFuturplural1(SurmiranPronouns.pron_r_v_1pp + root + "aron");
                            cs.setFuturplural2(SurmiranPronouns.pron_r_v_2pp + root + "arossas");
                            cs.setFuturplural3(SurmiranPronouns.pron_r_v_3pp + root + "aron");
                            break;
                    }

                } else {
                    switch (cs.getConjugationclass().id) {
                        case "6":
                        case "7":
                            cs.setFutursing1(SurmiranPronouns.pron_r_1ps + root + "iro");
                            cs.setFutursing2(SurmiranPronouns.pron_r_2ps + root + "irossas");
                            cs.setFutursing3(SurmiranPronouns.pron_r_3ps + root + "iro");
                            cs.setFuturplural1(SurmiranPronouns.pron_r_1pp + root + "iron");
                            cs.setFuturplural2(SurmiranPronouns.pron_r_2pp + root + "irossas");
                            cs.setFuturplural3(SurmiranPronouns.pron_r_3pp + root + "iron");
                            break;

                        case "9":
                            switch (getVowelInRoot()) {
                                case 'e':
                                case 'o':
                                    cs.setFutursing1(SurmiranPronouns.pron_r_1ps + modRoot + "aro");
                                    cs.setFutursing2(SurmiranPronouns.pron_r_2ps + modRoot + "arossas");
                                    cs.setFutursing3(SurmiranPronouns.pron_r_3ps + modRoot + "aro");
                                    cs.setFuturplural1(SurmiranPronouns.pron_r_1pp + modRoot + "aron");
                                    cs.setFuturplural2(SurmiranPronouns.pron_r_2pp + modRoot + "arossas");
                                    cs.setFuturplural3(SurmiranPronouns.pron_r_3pp + modRoot + "aron");
                                    break;

                                case 'a':
                                case 'i':
                                case 'u':
                                    cs.setFutursing1(SurmiranPronouns.pron_r_1ps + root + "aro");
                                    cs.setFutursing2(SurmiranPronouns.pron_r_2ps + root + "arossas");
                                    cs.setFutursing3(SurmiranPronouns.pron_r_3ps + root + "aro");
                                    cs.setFuturplural1(SurmiranPronouns.pron_r_1pp + root + "aron");
                                    cs.setFuturplural2(SurmiranPronouns.pron_r_2pp + root + "arossas");
                                    cs.setFuturplural3(SurmiranPronouns.pron_r_3pp + root + "aron");
                                    break;

                            }
                            break;

                        default:
                            cs.setFutursing1(SurmiranPronouns.pron_r_1ps + root + "aro");
                            cs.setFutursing2(SurmiranPronouns.pron_r_2ps + root + "arossas");
                            cs.setFutursing3(SurmiranPronouns.pron_r_3ps + root + "aro");
                            cs.setFuturplural1(SurmiranPronouns.pron_r_1pp + root + "aron");
                            cs.setFuturplural2(SurmiranPronouns.pron_r_2pp + root + "arossas");
                            cs.setFuturplural3(SurmiranPronouns.pron_r_3pp + root + "aron");
                            break;

                    }

                }

                break;

            case "false":

                switch (cs.getConjugationclass().id) {
                    case "6":
                    case "7":
                        cs.setFutursing1(root + "iro");
                        cs.setFutursing2(root + "irossas");
                        cs.setFutursing3(root + "iro");
                        cs.setFuturplural1(root + "iron");
                        cs.setFuturplural2(root + "irossas");
                        cs.setFuturplural3(root + "iron");
                        break;

                    case "9":
                        switch (getVowelInRoot()) {
                            case 'e':
                            case 'o':
                                cs.setFutursing1(modRoot + "aro");
                                cs.setFutursing2(modRoot + "arossas");
                                cs.setFutursing3(modRoot + "aro");
                                cs.setFuturplural1(modRoot + "aron");
                                cs.setFuturplural2(modRoot + "arossas");
                                cs.setFuturplural3(modRoot + "aron");
                                break;

                            case 'a':
                            case 'i':
                            case 'u':
                                cs.setFutursing1(root + "aro");
                                cs.setFutursing2(root + "arossas");
                                cs.setFutursing3(root + "aro");
                                cs.setFuturplural1(root + "aron");
                                cs.setFuturplural2(root + "arossas");
                                cs.setFuturplural3(root + "aron");
                                break;
                        }

                        break;

                    default:
                        cs.setFutursing1(root + "aro");
                        cs.setFutursing2(root + "arossas");
                        cs.setFutursing3(root + "aro");
                        cs.setFuturplural1(root + "aron");
                        cs.setFuturplural2(root + "arossas");
                        cs.setFuturplural3(root + "aron");
                        break;

                }
        }
    }

    private HashMap<String, String> addPronouns(HashMap<String, String> conjugation, InflectionSubType subType) {
        Map<String, String> pronouns;

        String verb = conjugation.get("verb");

        if (verb.startsWith("sa ")) {
            // Reflexive Verbs that start with Consonants
            pronouns = SurmiranPronouns.pronounsForReflexiveConsonantalVerbs();
            return addReflexivePronouns(conjugation, pronouns, subType);

        } else if (verb.startsWith("s'")) {
            // Reflexive Verbs that start with Vocals
            pronouns = SurmiranPronouns.pronounsForReflexiveVocalicVerbs();
            return addReflexivePronouns(conjugation, pronouns, subType);

        } else {
            // Standard Verbs
            return addStandardPronouns(conjugation, subType);
        }
    }

    private HashMap<String, String> addReflexivePronouns(Map<String, String> conjugation, Map<String, String> pronouns, InflectionSubType subType) {
        cs = new SurmiranConjugationStructure();
        cs.setVerb(conjugation.get("verb"));
        cs.setInfinitiv(conjugation.get(SurmiranConjugationStructure.infinitiv));
        cs.setRoot(conjugation.get(SurmiranConjugationStructure.root));
        cs.setEnding(conjugation.get(SurmiranConjugationStructure.ending));
        cs.setReflexive(conjugation.get(SurmiranConjugationStructure.reflexive));
        cs.setConjugationClass(subType);

        // PRESCHENT
        cs.setPreschentsing1(pronouns.get(SurmiranPronouns.first_ps) + conjugation.get(SurmiranConjugationStructure.preschentsing1));
        cs.setPreschentsing2(pronouns.get(SurmiranPronouns.second_ps) + conjugation.get(SurmiranConjugationStructure.preschentsing2));
        cs.setPreschentsing3(pronouns.get(SurmiranPronouns.third_ps) + conjugation.get(SurmiranConjugationStructure.preschentsing3));
        cs.setPreschentplural1(pronouns.get(SurmiranPronouns.first_pp) + conjugation.get(SurmiranConjugationStructure.preschentplural1));
        cs.setPreschentplural2(pronouns.get(SurmiranPronouns.second_pp) + conjugation.get(SurmiranConjugationStructure.preschentplural2));
        cs.setPreschentplural3(pronouns.get(SurmiranPronouns.third_pp) + conjugation.get(SurmiranConjugationStructure.preschentplural3));

        // IMPERFECT
        cs.setImperfectsing1(pronouns.get(SurmiranPronouns.first_ps) + conjugation.get(SurmiranConjugationStructure.imperfectsing1));
        cs.setImperfectsing2(pronouns.get(SurmiranPronouns.second_ps) + conjugation.get(SurmiranConjugationStructure.imperfectsing2));
        cs.setImperfectsing3(pronouns.get(SurmiranPronouns.third_ps) + conjugation.get(SurmiranConjugationStructure.imperfectsing3));
        cs.setImperfectplural1(pronouns.get(SurmiranPronouns.first_pp) + conjugation.get(SurmiranConjugationStructure.imperfectplural1));
        cs.setImperfectplural2(pronouns.get(SurmiranPronouns.second_pp) + conjugation.get(SurmiranConjugationStructure.imperfectplural2));
        cs.setImperfectplural3(pronouns.get(SurmiranPronouns.third_pp) + conjugation.get(SurmiranConjugationStructure.imperfectplural3));

        // CONJUNCTIV
        cs.setConjunctivsing1(pronouns.get(SurmiranPronouns.first_ps_c) + conjugation.get(SurmiranConjugationStructure.conjunctivsing1));
        cs.setConjunctivsing2(pronouns.get(SurmiranPronouns.second_ps_c) + conjugation.get(SurmiranConjugationStructure.conjunctivsing2));
        cs.setConjunctivsing3(pronouns.get(SurmiranPronouns.third_ps_c) + conjugation.get(SurmiranConjugationStructure.conjunctivsing3));
        cs.setConjunctivplural1(pronouns.get(SurmiranPronouns.first_pp_c) + conjugation.get(SurmiranConjugationStructure.conjunctivplural1));
        cs.setConjunctivplural2(pronouns.get(SurmiranPronouns.second_pp_c) + conjugation.get(SurmiranConjugationStructure.conjunctivplural2));
        cs.setConjunctivplural3(pronouns.get(SurmiranPronouns.third_pp_c) + conjugation.get(SurmiranConjugationStructure.conjunctivplural3));

        // CUNDIZIONAL
        cs.setCundizionalsing1(pronouns.get(SurmiranPronouns.first_ps) + conjugation.get(SurmiranConjugationStructure.cundizionalsing1));
        cs.setCundizionalsing2(pronouns.get(SurmiranPronouns.second_ps) + conjugation.get(SurmiranConjugationStructure.cundizionalsing2));
        cs.setCundizionalsing3(pronouns.get(SurmiranPronouns.third_ps) + conjugation.get(SurmiranConjugationStructure.cundizionalsing3));
        cs.setCundizionalplural1(pronouns.get(SurmiranPronouns.first_pp) + conjugation.get(SurmiranConjugationStructure.cundizionalplural1));
        cs.setCundizionalplural2(pronouns.get(SurmiranPronouns.second_pp) + conjugation.get(SurmiranConjugationStructure.cundizionalplural2));
        cs.setCundizionalplural3(pronouns.get(SurmiranPronouns.third_pp) + conjugation.get(SurmiranConjugationStructure.cundizionalplural3));

        // IMPERATIV
        cs.setImperativ1(pronouns.get(SurmiranPronouns.imperat_1) + conjugation.get(SurmiranConjugationStructure.imperativ1));
        cs.setImperativ2(pronouns.get(SurmiranPronouns.imperat_2) + conjugation.get(SurmiranConjugationStructure.imperativ2));

        // PARTICIP_PERFECT
        cs.setParticipperfectms(conjugation.get(SurmiranConjugationStructure.participperfectms));
        cs.setParticipperfectfs(conjugation.get(SurmiranConjugationStructure.participperfectfs));
        cs.setParticipperfectmp(conjugation.get(SurmiranConjugationStructure.participperfectmp));
        cs.setParticipperfectfp(conjugation.get(SurmiranConjugationStructure.participperfectfp));

        // GERUNDIUM
        cs.setGerundium(conjugation.get(SurmiranConjugationStructure.gerundium));

        // FUTUR
        cs.setFutursing1(SurmiranPronouns.pron_1ps + conjugation.get(SurmiranConjugationStructure.futursing1));
        cs.setFutursing2(SurmiranPronouns.pron_2ps + conjugation.get(SurmiranConjugationStructure.futursing2));
        cs.setFutursing3(SurmiranPronouns.pron_3ps + conjugation.get(SurmiranConjugationStructure.futursing3));
        cs.setFuturplural1(SurmiranPronouns.pron_1pp + conjugation.get(SurmiranConjugationStructure.futurplural1));
        cs.setFuturplural2(SurmiranPronouns.pron_2pp + conjugation.get(SurmiranConjugationStructure.futurplural2));
        cs.setFuturplural3(SurmiranPronouns.pron_3pp + conjugation.get(SurmiranConjugationStructure.futurplural3));

        return cs.getAllFormValues();
    }

    private HashMap<String, String> addStandardPronouns(Map<String, String> conjugation, InflectionSubType subType) {

        SurmiranConjugationStructure cs = new SurmiranConjugationStructure();

        cs.setVerb(conjugation.get("verb"));
        cs.setInfinitiv(conjugation.get(SurmiranConjugationStructure.infinitiv));
        cs.setRoot(conjugation.get(SurmiranConjugationStructure.root));
        cs.setEnding(conjugation.get(SurmiranConjugationStructure.ending));
        cs.setReflexive(conjugation.get(SurmiranConjugationStructure.reflexive));
        cs.setConjugationClass(subType);

        // PRESCHENT
        cs.setPreschentsing1(SurmiranPronouns.pron_1ps + conjugation.get(SurmiranConjugationStructure.preschentsing1));
        cs.setPreschentsing2(SurmiranPronouns.pron_2ps + conjugation.get(SurmiranConjugationStructure.preschentsing2));
        cs.setPreschentsing3(SurmiranPronouns.pron_3ps + conjugation.get(SurmiranConjugationStructure.preschentsing3));
        cs.setPreschentplural1(SurmiranPronouns.pron_1pp + conjugation.get(SurmiranConjugationStructure.preschentplural1));
        cs.setPreschentplural2(SurmiranPronouns.pron_2pp + conjugation.get(SurmiranConjugationStructure.preschentplural2));
        cs.setPreschentplural3(SurmiranPronouns.pron_3pp + conjugation.get(SurmiranConjugationStructure.preschentplural3));

        // IMPERFECT
        cs.setImperfectsing1(SurmiranPronouns.pron_1ps + conjugation.get(SurmiranConjugationStructure.imperfectsing1));
        cs.setImperfectsing2(SurmiranPronouns.pron_2ps + conjugation.get(SurmiranConjugationStructure.imperfectsing2));
        cs.setImperfectsing3(SurmiranPronouns.pron_3ps + conjugation.get(SurmiranConjugationStructure.imperfectsing3));
        cs.setImperfectplural1(SurmiranPronouns.pron_1pp + conjugation.get(SurmiranConjugationStructure.imperfectplural1));
        cs.setImperfectplural2(SurmiranPronouns.pron_2pp + conjugation.get(SurmiranConjugationStructure.imperfectplural2));
        cs.setImperfectplural3(SurmiranPronouns.pron_3pp + conjugation.get(SurmiranConjugationStructure.imperfectplural3));

        // CONJUNCTIV
        cs.setConjunctivsing1(SurmiranPronouns.pron_conjunctiv_v + SurmiranPronouns.pron_1ps + conjugation.get(SurmiranConjugationStructure.conjunctivsing1));
        cs.setConjunctivsing2(SurmiranPronouns.pron_conjunctiv_c + SurmiranPronouns.pron_2ps + conjugation.get(SurmiranConjugationStructure.conjunctivsing2));
        cs.setConjunctivsing3(SurmiranPronouns.pron_conjunctiv_v + SurmiranPronouns.pron_3ps + conjugation.get(SurmiranConjugationStructure.conjunctivsing3));
        cs.setConjunctivplural1(SurmiranPronouns.pron_conjunctiv_c + SurmiranPronouns.pron_1pp + conjugation.get(SurmiranConjugationStructure.conjunctivplural1));
        cs.setConjunctivplural2(SurmiranPronouns.pron_conjunctiv_c + SurmiranPronouns.pron_2pp + conjugation.get(SurmiranConjugationStructure.conjunctivplural2));
        cs.setConjunctivplural3(SurmiranPronouns.pron_conjunctiv_v + SurmiranPronouns.pron_3pp + conjugation.get(SurmiranConjugationStructure.conjunctivplural3));

        // CUNDIZIONAL
        cs.setCundizionalsing1(SurmiranPronouns.pron_1ps + conjugation.get(SurmiranConjugationStructure.cundizionalsing1));
        cs.setCundizionalsing2(SurmiranPronouns.pron_2ps + conjugation.get(SurmiranConjugationStructure.cundizionalsing2));
        cs.setCundizionalsing3(SurmiranPronouns.pron_3ps + conjugation.get(SurmiranConjugationStructure.cundizionalsing3));
        cs.setCundizionalplural1(SurmiranPronouns.pron_1pp + conjugation.get(SurmiranConjugationStructure.cundizionalplural1));
        cs.setCundizionalplural2(SurmiranPronouns.pron_2pp + conjugation.get(SurmiranConjugationStructure.cundizionalplural2));
        cs.setCundizionalplural3(SurmiranPronouns.pron_3pp + conjugation.get(SurmiranConjugationStructure.cundizionalplural3));

        // IMPERATIV
        cs.setImperativ1(conjugation.get(SurmiranConjugationStructure.imperativ1));
        cs.setImperativ2(conjugation.get(SurmiranConjugationStructure.imperativ2));

        // PARTICIP_PERFECT
        cs.setParticipperfectms(conjugation.get(SurmiranConjugationStructure.participperfectms));
        cs.setParticipperfectfs(conjugation.get(SurmiranConjugationStructure.participperfectfs));
        cs.setParticipperfectmp(conjugation.get(SurmiranConjugationStructure.participperfectmp));
        cs.setParticipperfectfp(conjugation.get(SurmiranConjugationStructure.participperfectfp));

        // GERUNDIUM
        cs.setGerundium(conjugation.get(SurmiranConjugationStructure.gerundium));

        // FUTUR
        cs.setFutursing1(SurmiranPronouns.pron_1ps + conjugation.get(SurmiranConjugationStructure.futursing1));
        cs.setFutursing2(SurmiranPronouns.pron_2ps + conjugation.get(SurmiranConjugationStructure.futursing2));
        cs.setFutursing3(SurmiranPronouns.pron_3ps + conjugation.get(SurmiranConjugationStructure.futursing3));
        cs.setFuturplural1(SurmiranPronouns.pron_1pp + conjugation.get(SurmiranConjugationStructure.futurplural1));
        cs.setFuturplural2(SurmiranPronouns.pron_2pp + conjugation.get(SurmiranConjugationStructure.futurplural2));
        cs.setFuturplural3(SurmiranPronouns.pron_3pp + conjugation.get(SurmiranConjugationStructure.futurplural3));

        return cs.getAllFormValues();
    }
}
