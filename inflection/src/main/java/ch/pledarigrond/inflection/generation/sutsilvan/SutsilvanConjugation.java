package ch.pledarigrond.inflection.generation.sutsilvan;

import ch.pledarigrond.inflection.generation.generic.LanguageConjugation;
import ch.pledarigrond.inflection.model.InflectionResponse;
import ch.pledarigrond.inflection.model.InflectionSubType;
import lombok.Data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Data
public class SutsilvanConjugation extends LanguageConjugation {

    private char vowelInRoot;

    private SutsilvanConjugationStructure cs;

    private String infinitiv;
    private String verb;

    private String lastTwo;
    private String lastThree;

    private HashMap<String, String> conjugation;

    @Override
    public InflectionResponse generateConjugation(String conjugationClass, String infinitiv) {
        resetValues();

        root = getRoot(infinitiv);

        if (conjugationClass.equals("8")) {
            modRoot = changeVocalInRoot(root);
            if (modRoot == null) {
                throw new RuntimeException("For this conjugation you need to enter a verb with a vowel in its root!");
            }
        }

        InflectionSubType subType = SutsilvanConjugationClasses.getConjugationClass(conjugationClass);
        if (subType == null) {
            throw new RuntimeException(conjugationClass + " is not a valid conjugation class.");
        } else if (getEnding() == null) {
            throw new RuntimeException(infinitiv + "  is not a valid verb. Please type a verb in its infinitive form.");
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
            infinitiv = normalizeString(infinitiv);
            setInfinitiv(infinitiv);
            if (infinitiv.equals("ir") || infinitiv.equals("er")) {
                setVerb(infinitiv);
                setEnding(infinitiv);
                return infinitiv;
            }
            HashSet<String> avoid;
            switch (infinitiv.length()) {
                case 0:
                case 1:
                case 2:
                    throw new RuntimeException("'" + infinitiv + "' is not a valid verb. Please type a verb in its infinitive form.");
                default:
                    avoid = new HashSet<>();
                    avoid.add("near");
                    avoid.add("sa");
                    if (avoid.contains(infinitiv)) {
                        throw new RuntimeException("'" + infinitiv + "' is not a valid verb. Please type a verb in its infinitive form.");
                    }
                    infinitiv = processQuery(infinitiv);
            }
        }
        return infinitiv;
    }

    private String processQuery(String infinitiv) {

        String l2 = infinitiv.substring(infinitiv.length() - 2);
        String l3 = infinitiv.substring(infinitiv.length() - 3);

        switch (l2) {
            case "ar":
            case "er":
            case "ir":
                if (!l3.equals("ear")) {
                    setLastTwo(l2);
                    setEnding(getLastTwo());
                    infinitiv = infinitiv.substring(0, infinitiv.length() - 2);
                    return infinitiv;
                }
                break;
        }

        if (l3.equals("ear")) {
            setLastThree(l3);
            setEnding(getLastThree());
            infinitiv = infinitiv.substring(0, infinitiv.length() - 3);
            return infinitiv;
        }
        return infinitiv;
    }

    private String changeVocalInRoot(String root) {
        StringBuilder builder = null;
        List<String> a_turnsTo_o = Arrays.asList("clam", "dumand", "sadumand", "racumand");

        for (int i = root.length() - 1; i >= 0; i--) {
            char ch = root.charAt(i);
            if (isVocal(ch)) {
                setVowelInRoot(ch);
                builder = new StringBuilder(root);

                switch (ch) {
                    case 'a':
                        if (a_turnsTo_o.contains(root)) {
                            builder.setCharAt(i, 'o');
                        } else {
                            builder.setCharAt(i, 'e');
                        }
                        break;
                    case 'i':
                        builder.setCharAt(i, 'e');
                        break;
                    case 'u':
                        builder.setCharAt(i, 'o');
                        break;
                }
                break;
            }
        }
        if (builder == null) {
            return null;
        }
        return builder.toString();
    }

    private HashMap<String, String> conjugate(String root, InflectionSubType conjugationClass) {

        cs = new SutsilvanConjugationStructure();
        cs.setVerb(getVerb());
        cs.setInfinitiv(getInfinitiv());
        cs.setRoot(root);
        cs.setEnding(getEnding());
        cs.setConjugationClass(conjugationClass);

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

    private void setPreschent(String root, SutsilvanConjugationStructure cs) {

        switch (cs.getConjugationClass().id) {
            case "1":
            case "2":
            case "3":
            case "4":
                cs.setPreschentsing1(root + "[el]");
                cs.setPreschentsing2(root + "as");
                cs.setPreschentsing3(root + "a");
                cs.setPreschentplural1(root + "agn");
                // 2pp
                if (cs.getConjugationClass().id.equals("1")) {
                    cs.setPreschentplural2(root + "az");
                } else if (cs.getConjugationClass().id.equals("2")) {
                    cs.setPreschentplural2(root + "eaz");
                } else {
                    cs.setPreschentplural2(root + "ez");
                }
                cs.setPreschentplural3(root + "an");
                break;

            case "5":
            case "6":
            case "7":
                cs.setPreschentsing1(root + "esch[el]");
                cs.setPreschentsing2(root + "eschas");
                cs.setPreschentsing3(root + "escha");
                cs.setPreschentplural1(root + "agn");
                // 2pp
                if (cs.getConjugationClass().id.equals("5")) {
                    cs.setPreschentplural2(root + "az");
                } else if (cs.getConjugationClass().id.equals("6")) {
                    cs.setPreschentplural2(root + "eaz");
                } else {
                    cs.setPreschentplural2(root + "ez");
                }
                cs.setPreschentplural3(root + "eschan");
                break;

            case "8":
                cs.setPreschentsing1(modRoot + "[el]");
                cs.setPreschentsing2(modRoot + "as");
                cs.setPreschentsing3(modRoot + "a");
                cs.setPreschentplural1(root + "agn");
                cs.setPreschentplural2(root + "az");
                cs.setPreschentplural3(modRoot + "an");
                break;
        }
    }

    private void setImperfect(String root, SutsilvanConjugationStructure cs) {

        switch (cs.getConjugationClass().id) {
            case "1":
            case "5":
            case "8":
                cs.setImperfectsing1(root + "ava");
                cs.setImperfectsing2(root + "avas");
                cs.setImperfectsing3(root + "ava");
                cs.setImperfectplural1(root + "avan");
                cs.setImperfectplural2(root + "avas");
                cs.setImperfectplural3(root + "avan");
                break;

            case "2":
            case "6":
                cs.setImperfectsing1(root + "eava");
                cs.setImperfectsing2(root + "eavas");
                cs.setImperfectsing3(root + "eava");
                cs.setImperfectplural1(root + "eavan");
                cs.setImperfectplural2(root + "eavas");
                cs.setImperfectplural3(root + "eavan");
                break;

            case "3":
            case "4":
            case "7":
                cs.setImperfectsing1(root + "eva");
                cs.setImperfectsing2(root + "evas");
                cs.setImperfectsing3(root + "eva");
                cs.setImperfectplural1(root + "evan");
                cs.setImperfectplural2(root + "evas");
                cs.setImperfectplural3(root + "evan");
                break;
        }
    }

    private void setConjunctiv(String root, SutsilvanConjugationStructure cs) {

        switch (cs.getConjugationClass().id) {
            case "5":
            case "6":
            case "7":
                cs.setConjunctivsing1(root + "eschi");
                cs.setConjunctivsing2(root + "esch(i)as");
                cs.setConjunctivsing3(root + "eschi");
                cs.setConjunctivplural1(root + "eian");
                cs.setConjunctivplural2(root + "eias");
                cs.setConjunctivplural3(root + "esch(i)an");
                break;

            case "8":
                cs.setConjunctivsing1(modRoot + "i");
                cs.setConjunctivsing2(modRoot + "(i)as");
                cs.setConjunctivsing3(modRoot + "i");
                cs.setConjunctivplural1(root + "eian");
                cs.setConjunctivplural2(root + "eias");
                cs.setConjunctivplural3(modRoot + "(i)an");
                break;

            case "1":
            case "2":
            case "3":
            case "4":
                cs.setConjunctivsing1(root + "i");
                cs.setConjunctivsing2(root + "(i)as");
                cs.setConjunctivsing3(root + "i");
                cs.setConjunctivplural1(root + "eian");
                cs.setConjunctivplural2(root + "eias");
                cs.setConjunctivplural3(root + "(i)an");
                break;
        }
    }

    private void setCundizional(String root, SutsilvanConjugationStructure cs) {

        switch (cs.getConjugationClass().id) {
            case "1":
            case "5":
            case "8":
                cs.setCundizionalsing1(root + "ass");
                cs.setCundizionalsing2(root + "assas");
                cs.setCundizionalsing3(root + "ass");
                cs.setCundizionalplural1(root + "assan");
                cs.setCundizionalplural2(root + "assas");
                cs.setCundizionalplural3(root + "assan");
                break;

            case "2":
            case "3":
            case "4":
            case "6":
            case "7":
                cs.setCundizionalsing1(root + "ess");
                cs.setCundizionalsing2(root + "essas");
                cs.setCundizionalsing3(root + "ess");
                cs.setCundizionalplural1(root + "essan");
                cs.setCundizionalplural2(root + "essas");
                cs.setCundizionalplural3(root + "essan");
                break;
        }
    }

    private void setParticipPerfect(String root, SutsilvanConjugationStructure cs) {

        switch (cs.getConjugationClass().id) {
            case "1":
            case "5":
            case "8":
                cs.setParticipperfectms(root + "o");
                cs.setParticipperfectfs(root + "ada");
                cs.setParticipperfectmp(root + "os");
                cs.setParticipperfectfp(root + "adas");
                break;

            case "2":
            case "6":
                cs.setParticipperfectms(root + "ieu");
                cs.setParticipperfectfs(root + "eada");
                cs.setParticipperfectmp(root + "ieus");
                cs.setParticipperfectfp(root + "eadas");
                break;

            case "3":
            case "4":
            case "7":
                cs.setParticipperfectms(root + "ieu");
                cs.setParticipperfectfs(root + "ida");
                cs.setParticipperfectmp(root + "ieus");
                cs.setParticipperfectfp(root + "idas");
                break;
        }
    }

    private void setGerundium(String root, SutsilvanConjugationStructure cs) {
        cs.setGerundium(root + "ànd");
    }

    private void setImperativ(String root, SutsilvanConjugationStructure cs) {

        switch (cs.getConjugationClass().id) {
            case "1":
                cs.setImperativ1(root + "a!");
                cs.setImperativ2(root + "ad!");
                break;

            case "2":
                cs.setImperativ1(root + "a!");
                cs.setImperativ2(root + "ead!");
                break;

            case "3":
            case "4":
                cs.setImperativ1(root + "a!");
                cs.setImperativ2(root + "ed!");
                break;

            case "5":
                cs.setImperativ1(root + "escha!");
                cs.setImperativ2(root + "ad!");
                break;

            case "6":
                cs.setImperativ1(root + "escha!");
                cs.setImperativ2(root + "ead!");
                break;

            case "7":
                cs.setImperativ1(root + "escha!");
                cs.setImperativ2(root + "ed!");
                break;

            case "8":
                cs.setImperativ1(modRoot + "a!");
                cs.setImperativ2(root + "ad!");
                break;
        }
    }

    private void setFutur(String root, SutsilvanConjugationStructure cs) {
        char startChar = root.charAt(0);
        if (isVocal(startChar)) {
            cs.setFutursing1("vignt ad " + cs.getInfinitiv());
            cs.setFutursing2("veans ad " + cs.getInfinitiv());
            cs.setFutursing3("vean ad " + cs.getInfinitiv());
            cs.setFuturplural1("vagnagn ad " + cs.getInfinitiv());
            cs.setFuturplural2("vagnez ad " + cs.getInfinitiv());
            cs.setFuturplural3("vignan ad " + cs.getInfinitiv());
        } else {
            cs.setFutursing1("vignt a " + cs.getInfinitiv());
            cs.setFutursing2("veans a " + cs.getInfinitiv());
            cs.setFutursing3("vean a " + cs.getInfinitiv());
            cs.setFuturplural1("vagnagn a " + cs.getInfinitiv());
            cs.setFuturplural2("vagnez a " + cs.getInfinitiv());
            cs.setFuturplural3("vignan a " + cs.getInfinitiv());
        }
    }

    private HashMap<String, String> addPronouns(HashMap<String, String> conjugation, InflectionSubType inflectionSubType) {

        SutsilvanConjugationStructure cs = new SutsilvanConjugationStructure();

        cs.setVerb(conjugation.get("verb"));
        cs.setInfinitiv(conjugation.get(SutsilvanConjugationStructure.infinitiv));
        cs.setRoot(conjugation.get(SutsilvanConjugationStructure.root));
        cs.setEnding(conjugation.get(SutsilvanConjugationStructure.ending));
        cs.setReflexive(conjugation.get(SutsilvanConjugationStructure.reflexive));
        cs.setConjugationClass(inflectionSubType);

        // PRESCHENT
        cs.setPreschentsing1(SutsilvanPronouns.pron_1ps + conjugation.get(SutsilvanConjugationStructure.preschentsing1));
        cs.setPreschentsing2(SutsilvanPronouns.pron_2ps + conjugation.get(SutsilvanConjugationStructure.preschentsing2));
        cs.setPreschentsing3(SutsilvanPronouns.pron_3ps + conjugation.get(SutsilvanConjugationStructure.preschentsing3));
        cs.setPreschentplural1(SutsilvanPronouns.pron_1pp + conjugation.get(SutsilvanConjugationStructure.preschentplural1));
        cs.setPreschentplural2(SutsilvanPronouns.pron_2pp + conjugation.get(SutsilvanConjugationStructure.preschentplural2));
        cs.setPreschentplural3(SutsilvanPronouns.pron_3pp + conjugation.get(SutsilvanConjugationStructure.preschentplural3));

        // IMPERFECT
        cs.setImperfectsing1(SutsilvanPronouns.pron_1ps + conjugation.get(SutsilvanConjugationStructure.imperfectsing1));
        cs.setImperfectsing2(SutsilvanPronouns.pron_2ps + conjugation.get(SutsilvanConjugationStructure.imperfectsing2));
        cs.setImperfectsing3(SutsilvanPronouns.pron_3ps + conjugation.get(SutsilvanConjugationStructure.imperfectsing3));
        cs.setImperfectplural1(SutsilvanPronouns.pron_1pp + conjugation.get(SutsilvanConjugationStructure.imperfectplural1));
        cs.setImperfectplural2(SutsilvanPronouns.pron_2pp + conjugation.get(SutsilvanConjugationStructure.imperfectplural2));
        cs.setImperfectplural3(SutsilvanPronouns.pron_3pp + conjugation.get(SutsilvanConjugationStructure.imperfectplural3));

        // CONJUNCTIV
        cs.setConjunctivsing1(SutsilvanPronouns.pron_conjunctiv_c + SutsilvanPronouns.pron_1ps + conjugation.get(SutsilvanConjugationStructure.conjunctivsing1));
        cs.setConjunctivsing2(SutsilvanPronouns.pron_conjunctiv_c + SutsilvanPronouns.pron_2ps + conjugation.get(SutsilvanConjugationStructure.conjunctivsing2));
        cs.setConjunctivsing3(SutsilvanPronouns.pron_conjunctiv_v + SutsilvanPronouns.pron_3ps + conjugation.get(SutsilvanConjugationStructure.conjunctivsing3));
        cs.setConjunctivplural1(SutsilvanPronouns.pron_conjunctiv_c + SutsilvanPronouns.pron_1pp + conjugation.get(SutsilvanConjugationStructure.conjunctivplural1));
        cs.setConjunctivplural2(SutsilvanPronouns.pron_conjunctiv_c + SutsilvanPronouns.pron_2pp + conjugation.get(SutsilvanConjugationStructure.conjunctivplural2));
        cs.setConjunctivplural3(SutsilvanPronouns.pron_conjunctiv_v + SutsilvanPronouns.pron_3pp + conjugation.get(SutsilvanConjugationStructure.conjunctivplural3));

        // CUNDIZIONAL
        cs.setCundizionalsing1(SutsilvanPronouns.pron_1ps + conjugation.get(SutsilvanConjugationStructure.cundizionalsing1));
        cs.setCundizionalsing2(SutsilvanPronouns.pron_2ps + conjugation.get(SutsilvanConjugationStructure.cundizionalsing2));
        cs.setCundizionalsing3(SutsilvanPronouns.pron_3ps + conjugation.get(SutsilvanConjugationStructure.cundizionalsing3));
        cs.setCundizionalplural1(SutsilvanPronouns.pron_1pp + conjugation.get(SutsilvanConjugationStructure.cundizionalplural1));
        cs.setCundizionalplural2(SutsilvanPronouns.pron_2pp + conjugation.get(SutsilvanConjugationStructure.cundizionalplural2));
        cs.setCundizionalplural3(SutsilvanPronouns.pron_3pp + conjugation.get(SutsilvanConjugationStructure.cundizionalplural3));

        // IMPERATIV
        cs.setImperativ1(conjugation.get(SutsilvanConjugationStructure.imperativ1));
        cs.setImperativ2(conjugation.get(SutsilvanConjugationStructure.imperativ2));

        // PARTICIP_PERFECT
        cs.setParticipperfectms(conjugation.get(SutsilvanConjugationStructure.participperfectms));
        cs.setParticipperfectfs(conjugation.get(SutsilvanConjugationStructure.participperfectfs));
        cs.setParticipperfectmp(conjugation.get(SutsilvanConjugationStructure.participperfectmp));
        cs.setParticipperfectfp(conjugation.get(SutsilvanConjugationStructure.participperfectfp));

        // GERUNDIUM
        cs.setGerundium(conjugation.get(SutsilvanConjugationStructure.gerundium));

        // FUTUR
        cs.setFutursing1(SutsilvanPronouns.pron_1ps + conjugation.get(SutsilvanConjugationStructure.futursing1));
        cs.setFutursing2(SutsilvanPronouns.pron_2ps + conjugation.get(SutsilvanConjugationStructure.futursing2));
        cs.setFutursing3(SutsilvanPronouns.pron_3ps + conjugation.get(SutsilvanConjugationStructure.futursing3));
        cs.setFuturplural1(SutsilvanPronouns.pron_1pp + conjugation.get(SutsilvanConjugationStructure.futurplural1));
        cs.setFuturplural2(SutsilvanPronouns.pron_2pp + conjugation.get(SutsilvanConjugationStructure.futurplural2));
        cs.setFuturplural3(SutsilvanPronouns.pron_3pp + conjugation.get(SutsilvanConjugationStructure.futurplural3));

        return cs.getAllFormValues();
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
