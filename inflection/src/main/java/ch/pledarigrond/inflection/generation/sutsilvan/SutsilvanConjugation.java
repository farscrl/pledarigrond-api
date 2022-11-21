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

        switch (conjugationClass) {
            case "1a" -> {
                int start = root.lastIndexOf("a");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "e" + root.substring(start);
            }
            case "1b" -> {
                int start = root.lastIndexOf("a");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "e" + root.substring(start + 1);
            }
            case "1c" -> {
                int start = root.lastIndexOf("a");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "o" + root.substring(start + 1);
            }
            case "1d" -> {
                int start = root.lastIndexOf("a");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ai" + root.substring(start + 1);
            }
            case "1e" -> {
                int start = root.lastIndexOf("i");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ie" + root.substring(start + 1);
            }
            case "1f" -> {
                int start = root.lastIndexOf("i");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "e" + root.substring(start + 1);
            }
            case "1g" -> {
                int start = root.lastIndexOf("in");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "egn" + root.substring(start + 2);
            }
            case "1h" -> {
                int start = root.lastIndexOf("uv");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "avu" + root.substring(start + 2);
            }
            case "1i" -> {
                int start = root.lastIndexOf("u");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "o" + root.substring(start + 1);
            }
            case "1j" -> {
                int start = root.lastIndexOf("an");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "agn" + root.substring(start + 2);
            }
            case "1k" -> {
                int start = root.lastIndexOf("an");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "àn" + root.substring(start + 2);
            }
            case "1l" -> {
                int start = root.lastIndexOf("un");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ùn" + root.substring(start + 2);
            }
            case "1m" -> {
                int start = root.lastIndexOf("ar");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "re" + root.substring(start + 2);
            }
            case "1n" -> {
                int start = root.lastIndexOf("ar");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ro" + root.substring(start + 2);
            }
            case "1o" -> {
                int start = root.lastIndexOf("ar");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ra" + root.substring(start + 2);
            }
            case "1p" -> {
                int start = root.indexOf("ca");
                if (start == -1) break;
                modRoot = "tgà" + root.substring(start + 2);
            }
            case "1q" -> {
                int start = root.lastIndexOf("ga");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "go" + root.substring(start + 2);
            }

            case "2a" -> {
                int start = root.lastIndexOf("a");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "e" + root.substring(start + 1);
            }
            case "2b" -> {
                int start = root.lastIndexOf("i");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "e" + root.substring(start + 1);
            }
            case "2c" -> {
                int start = root.lastIndexOf("u");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "o" + root.substring(start + 1);
            }
            case "2d" -> {
                int start = root.lastIndexOf("a");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ea" + root.substring(start + 1);
            }
            case "2e" -> {
                int start = root.lastIndexOf("agli");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "egli" + root.substring(start + 4);
            }
            case "2f" -> {
                int start = root.lastIndexOf("ag");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "eg" + root.substring(start + 2);
            }
            case "2g" -> {
                int start = root.lastIndexOf("arg");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "reg" + root.substring(start + 3);
            }

            case "3a" -> {
                int start = root.lastIndexOf("e");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "a" + root.substring(start + 1);
            }
            case "3b" -> {
                int start = root.lastIndexOf("e");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "i" + root.substring(start + 1);
            }
            case "3c" -> {
                int start = root.lastIndexOf("ea");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "a" + root.substring(start + 2);
            }
            case "3d" -> {
                int start = root.lastIndexOf("à");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "a" + root.substring(start + 1);
            }
            case "3e" -> {
                int start = root.lastIndexOf("o");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "u" + root.substring(start + 1);
            }
            case "3f" -> {

            }

            case "4a" -> {
                int start = root.lastIndexOf("a");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ai" + root.substring(start + 1);
            }
            case "4b" -> {
                int start = root.lastIndexOf("i");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "e" + root.substring(start + 1);
            }
            case "4c" -> {
                int start = root.lastIndexOf("u");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "o" + root.substring(start + 1);
            }
            case "4d" -> {
                int start = root.lastIndexOf("ar");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ra" + root.substring(start + 2);
            }
            case "4e" -> {
                int start = root.lastIndexOf("cu");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "quie" + root.substring(start + 2);
            }

            case "9a" -> {
                int start = root.lastIndexOf("a");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "e" + root.substring(start + 1);
            }

            case "8" -> {
                modRoot = changeVocalInRoot(root);
                if (modRoot == null) {
                    throw new RuntimeException("For this conjugation you need to enter a verb with a vowel in its root!");
                }
            }

            default -> modRoot = root;
        }

        if (modRoot == null) {
            modRoot = root;
        }

        if(infinitiv.endsWith("gler")) {
            modRoot = modRoot + "i";
        }

        if(needsBindingIEnding(conjugationClass)) {
            modRoot = modRoot + "i";
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
        if (baseForm.length() < 3) {
            return null;
        }

        String lastTwo = baseForm.substring(baseForm.length() - 2);
        String lastThree = baseForm.substring(baseForm.length() - 3);

        if (lastThree.equals("ear")) {
            if (flex != null && flex.equals("-escha")) {
                return generateConjugation("6", baseForm);
            }

            if(baseForm.endsWith("agliear")) {
                return generateConjugation("2e", baseForm);
            }

            if(baseForm.endsWith("agear")) {
                return generateConjugation("2f", baseForm);
            }

            if(baseForm.endsWith("argear")) {
                return generateConjugation("2g", baseForm);
            }

            if (flex != null && !flex.equals("")) {
                String[] subForms = new String[]{"2a", "2b", "2c", "2d"};
                for (String subForm : subForms) {
                    InflectionResponse r = generateConjugation(subForm, baseForm);
                    if (doesFormContainAllValues(r, flex)) {
                        return r;
                    }
                }
            }

            return generateConjugation("2", baseForm);
        }

        if (lastTwo.equals("ar")) {
            if (flex != null && flex.equals("-escha")) {
                return generateConjugation("5", baseForm);
            }

            // -antar cun almain 3 silbas
            if(baseForm.endsWith("antar") && baseForm.length() > 6) {
                return generateConjugation("1d", baseForm);
            }

            // verbs sin -inar
            if(baseForm.endsWith("inar")) {
                return generateConjugation("1g", baseForm);
            }

            // verbs sin -anar
            if(baseForm.endsWith("anar")) {
                return generateConjugation("1j", baseForm);
            }

            // verbs sin -unar; + scuntrar; + -untar
            if(baseForm.endsWith("unar") || baseForm.endsWith("untar") || baseForm.equals("scuntrar")) {
                return generateConjugation("1l", baseForm);
            }

            if (flex != null && !flex.equals("")) {
                String[] subForms = new String[]{"1a", "1b", "1c", "1d", "1e", "1f", "1g", "1h", "1i", "1k", "1l", "1m", "1n", "1o", "1p", "1q"};
                for (String subForm : subForms) {
                    InflectionResponse r = generateConjugation(subForm, baseForm);
                    if (doesFormContainAllValues(r, flex)) {
                        return r;
                    }
                }
            }

            return generateConjugation("1", baseForm);
        }

        if (lastTwo.equals("er")) {
            if (flex != null && !flex.equals("")) {
                String[] subForms = new String[]{"9", "9a", "3", "3a", "3b", "3c", "3d", "3e", "3f"};
                for (String subForm : subForms) {
                    InflectionResponse r = generateConjugation(subForm, baseForm);
                    if (doesFormContainAllValues(r, flex)) {
                        return r;
                    }
                }
            }

            return generateConjugation("3", baseForm);
        }

        if (lastTwo.equals("ir")) {
            if (flex != null && flex.equals("-escha")) {
                return generateConjugation("7", baseForm);
            }

            if (flex != null && !flex.equals("")) {
                String[] subForms = new String[]{"4", "4a", "4b", "4c", "4d", "4e"};
                for (String subForm : subForms) {
                    InflectionResponse r = generateConjugation(subForm, baseForm);
                    if (doesFormContainAllValues(r, flex)) {
                        return r;
                    }
                }
            }

            return generateConjugation("4", baseForm);
        }

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
        setConjunctivImperfect(root, cs);
        setCundizional(root, cs);
        setCundizionalIndirect(root, cs);
        setParticipPerfect(root, cs);
        setImperativ(root, cs);
        setGerundium(root, cs);
        setFutur(root, cs);

        setPreschentEnclitic(cs);
        setImperfectEnclitic(cs);
        setCundizionalEnclitic(cs);

        return cs.getValues();
    }

    private void setPreschent(String root, SutsilvanConjugationStructure cs) {

        switch (cs.getConjugationClass().id) {
            case "1":
            case "1a":
            case "1b":
            case "1c":
            case "1d":
            case "1e":
            case "1f":
            case "1g":
            case "1h":
            case "1i":
            case "1j":
            case "1k":
            case "1l":
            case "1m":
            case "1n":
            case "1o":
            case "1p":
            case "1q":
            case "2":
            case "2a":
            case "2b":
            case "2c":
            case "2d":
            case "2e":
            case "2f":
            case "2g":
            case "3":
            case "3f":
            case "4":
            case "4a":
            case "4b":
            case "4c":
            case "4d":
            case "4e":
            case "9":
            case "9a":
                if(endsOnGl(cs)) {
                    cs.setPreschentsing1(modRoot.substring(0, modRoot.length() - 1) + "\n" + modRoot + "el");
                } else if(needsBindingIEnding(cs.getConjugationclass().id)) {
                    cs.setPreschentsing1(modRoot.substring(0, modRoot.length() - 1) + "\n" + modRoot.substring(0, modRoot.length() - 1) + "el");
                } else {
                    cs.setPreschentsing1(modRoot + "\n" + modRoot + "el");
                }

                cs.setPreschentsing2(modRoot + "as");
                cs.setPreschentsing3(modRoot + "a");

                if (needsBindingIEnding(cs.getConjugationClass().id)) {
                    cs.setPreschentplural1(root + "iagn");
                } else {
                    cs.setPreschentplural1(root + "agn");
                }

                // 2pp
                if (cs.getConjugationClass().id.charAt(0) == '1') {
                    cs.setPreschentplural2(root + "az");
                } else if (cs.getConjugationClass().id.charAt(0) == '2') {
                    cs.setPreschentplural2(root + "eaz");
                } else {
                    cs.setPreschentplural2(root + "ez");
                }

                cs.setPreschentplural3(modRoot + "an");
                break;

            case "3a":
            case "3b":
            case "3c":
            case "3d":
            case "3e":
                cs.setPreschentsing1(root + "\n" + root + "el");
                cs.setPreschentsing2(root + "as");
                cs.setPreschentsing3(root + "a");
                cs.setPreschentplural1(modRoot + "agn");
                cs.setPreschentplural2(modRoot + "ez");
                cs.setPreschentplural3(root + "an");
                break;

            case "5":
            case "6":
            case "7":
                cs.setPreschentsing1(root + "esch\n" + root + "eschel");
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
                cs.setPreschentsing1(modRoot + "\n" + modRoot + "el");
                cs.setPreschentsing2(modRoot + "as");
                cs.setPreschentsing3(modRoot + "a");
                cs.setPreschentplural1(root + "agn");
                cs.setPreschentplural2(root + "az");
                cs.setPreschentplural3(modRoot + "an");
                break;
        }
    }

    private void setImperfect(String root, SutsilvanConjugationStructure cs) {

        switch (cs.getConjugationClass().id.substring(0, 1)) {
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

            /*case "3":
            case "4":
            case "7":*/
            default:
                if (cs.getConjugationclass().id.equals("3a") || cs.getConjugationclass().id.equals("3b") || cs.getConjugationclass().id.equals("3c") || cs.getConjugationclass().id.equals("3d") || cs.getConjugationclass().id.equals("3e")) {
                    cs.setImperfectsing1(modRoot + "eva");
                    cs.setImperfectsing2(modRoot + "evas");
                    cs.setImperfectsing3(modRoot + "eva");
                    cs.setImperfectplural1(modRoot + "evan");
                    cs.setImperfectplural2(modRoot + "evas");
                    cs.setImperfectplural3(modRoot + "evan");
                    break;
                }
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

        switch (cs.getConjugationClass().id.substring(0, 1)) {
            case "5":
            case "6":
            case "7":
                cs.setConjunctivsing1(root + "eschi");
                cs.setConjunctivsing2(root + "eschias\n" + root + "eschas");
                cs.setConjunctivsing3(root + "eschi");
                cs.setConjunctivplural1(root + "eian");
                cs.setConjunctivplural2(root + "eias");
                cs.setConjunctivplural3(root + "eschian\n" + root + "eschan");
                break;

            //case "8":
            default:
                if (cs.getConjugationclass().id.equals("3a") || cs.getConjugationclass().id.equals("3b") || cs.getConjugationclass().id.equals("3c") || cs.getConjugationclass().id.equals("3d") || cs.getConjugationclass().id.equals("3e")) {
                    cs.setConjunctivsing1(root + "i");
                    cs.setConjunctivsing2(root + "ias\n" + root + "as");
                    cs.setConjunctivsing3(root + "i");
                    cs.setConjunctivplural1(modRoot + "eian");
                    cs.setConjunctivplural2(modRoot + "eias");
                    cs.setConjunctivplural3(root + "ian\n" + root + "an");
                    break;
                }
                if (endsOnGl(cs) || needsBindingIEnding(cs.getConjugationClass().id)) {
                    cs.setConjunctivsing1(modRoot);
                    cs.setConjunctivsing2(modRoot + "as");
                    cs.setConjunctivsing3(modRoot);
                } else {
                    cs.setConjunctivsing1(modRoot + "i");
                    cs.setConjunctivsing2(modRoot + "ias\n" + modRoot + "as");
                    cs.setConjunctivsing3(modRoot + "i");
                }

                cs.setConjunctivplural1(root + "eian");
                cs.setConjunctivplural2(root + "eias");
                if (endsOnGl(cs) || needsBindingIEnding(cs.getConjugationClass().id)) {
                    cs.setConjunctivplural3(modRoot + "an");
                } else {
                    cs.setConjunctivplural3(modRoot + "ian\n" + modRoot + "an");
                }

                break;

            /*case "1":
            case "2":
            case "3":
            case "4":
                cs.setConjunctivsing1(root + "i");
                cs.setConjunctivsing2(root + "ias\n" + root + "as");
                cs.setConjunctivsing3(root + "i");
                cs.setConjunctivplural1(root + "eian");
                cs.setConjunctivplural2(root + "eias");
                cs.setConjunctivplural3(root + "ian\n" + root + "an");
                break;*/
        }
    }

    private void setConjunctivImperfect(String root, SutsilvanConjugationStructure cs) {

        switch (cs.getConjugationClass().id.substring(0, 1)) {
            case "8":
                cs.setConjunctivimperfectsing1(modRoot + "avi");
                cs.setConjunctivimperfectsing2(modRoot + "avias\n" + modRoot + "avas");
                cs.setConjunctivimperfectsing3(modRoot + "avi");
                cs.setConjunctivimperfectplural1(modRoot + "avian\n" + modRoot + "avan");
                cs.setConjunctivimperfectplural2(modRoot + "avias\n" + modRoot + "avas");
                cs.setConjunctivimperfectplural3(modRoot + "avian\n" + modRoot + "avan");
                break;

            case "1":
            case "5":
                cs.setConjunctivimperfectsing1(root + "avi");
                cs.setConjunctivimperfectsing2(root + "avias\n" + root + "avas");
                cs.setConjunctivimperfectsing3(root + "avi");
                cs.setConjunctivimperfectplural1(root + "avian\n" + root + "avan");
                cs.setConjunctivimperfectplural2(root + "avias\n" + root + "avas");
                cs.setConjunctivimperfectplural3(root + "avian\n" + root + "avan");
                break;

            case "2":
                cs.setConjunctivimperfectsing1(root + "eavi");
                cs.setConjunctivimperfectsing2(root + "eavias\n" + root + "eavas");
                cs.setConjunctivimperfectsing3(root + "eavi");
                cs.setConjunctivimperfectplural1(root + "eavian\n" + root + "eavan");
                cs.setConjunctivimperfectplural2(root + "eavias\n" + root + "eavas");
                cs.setConjunctivimperfectplural3(root + "eavian\n" + root + "eavan");
                break;

            /*case "3":
            case "4":
            case "6":
            case "7":*/
            default:
                if (cs.getConjugationclass().id.equals("3a") || cs.getConjugationclass().id.equals("3b") || cs.getConjugationclass().id.equals("3c") || cs.getConjugationclass().id.equals("3d") || cs.getConjugationclass().id.equals("3e")) {
                    cs.setConjunctivimperfectsing1(modRoot + "evi");
                    cs.setConjunctivimperfectsing2(modRoot + "evias\n" + modRoot + "evas");
                    cs.setConjunctivimperfectsing3(modRoot + "evi");
                    cs.setConjunctivimperfectplural1(modRoot + "evian\n" + modRoot + "evan");
                    cs.setConjunctivimperfectplural2(modRoot + "evias\n" + modRoot + "evas");
                    cs.setConjunctivimperfectplural3(modRoot + "evian\n" + modRoot + "evan");
                    break;
                }

                cs.setConjunctivimperfectsing1(root + "evi");
                cs.setConjunctivimperfectsing2(root + "evias\n" + root + "evas");
                cs.setConjunctivimperfectsing3(root + "evi");
                cs.setConjunctivimperfectplural1(root + "evian\n" + root + "evan");
                cs.setConjunctivimperfectplural2(root + "evias\n" + root + "evas");
                cs.setConjunctivimperfectplural3(root + "evian\n" + root + "evan");
                break;
        }
    }

    private void setCundizional(String root, SutsilvanConjugationStructure cs) {

        switch (cs.getConjugationClass().id.substring(0, 1)) {
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

            /*case "2":
            case "3":
            case "4":
            case "6":
            case "7":*/
            default:
                if (cs.getConjugationclass().id.equals("3a") || cs.getConjugationclass().id.equals("3b") || cs.getConjugationclass().id.equals("3c") || cs.getConjugationclass().id.equals("3d") || cs.getConjugationclass().id.equals("3e")) {
                    cs.setCundizionalsing1(modRoot + "ess");
                    cs.setCundizionalsing2(modRoot + "essas");
                    cs.setCundizionalsing3(modRoot + "ess");
                    cs.setCundizionalplural1(modRoot + "essan");
                    cs.setCundizionalplural2(modRoot + "essas");
                    cs.setCundizionalplural3(modRoot + "essan");
                    break;
                }

                cs.setCundizionalsing1(root + "ess");
                cs.setCundizionalsing2(root + "essas");
                cs.setCundizionalsing3(root + "ess");
                cs.setCundizionalplural1(root + "essan");
                cs.setCundizionalplural2(root + "essas");
                cs.setCundizionalplural3(root + "essan");
                break;
        }
    }

    private void setCundizionalIndirect(String root, SutsilvanConjugationStructure cs) {

        switch (cs.getConjugationClass().id.substring(0, 1)) {
            case "1":
            case "5":
            case "8":
                cs.setCundizionalindirectsing1(root + "assi");
                cs.setCundizionalindirectsing2(root + "assias\n" + root + "assas");
                cs.setCundizionalindirectsing3(root + "assi");
                cs.setCundizionalindirectplural1(root + "assian\n" + root + "assan");
                cs.setCundizionalindirectplural2(root + "assias\n" + root + "assas");
                cs.setCundizionalindirectplural3(root + "assian\n" + root + "assan");
                break;

                /*
            case "2":
            case "3":
            case "4":
            case "6":
            case "7":
            */
            default:
                if (cs.getConjugationclass().id.equals("3a") || cs.getConjugationclass().id.equals("3b") || cs.getConjugationclass().id.equals("3c") || cs.getConjugationclass().id.equals("3d") || cs.getConjugationclass().id.equals("3e")) {
                    cs.setCundizionalindirectsing1(modRoot + "essi");
                    cs.setCundizionalindirectsing2(modRoot + "essias\n" + modRoot + "essas");
                    cs.setCundizionalindirectsing3(modRoot + "essi");
                    cs.setCundizionalindirectplural1(modRoot + "essian\n" + modRoot + "essan");
                    cs.setCundizionalindirectplural2(modRoot + "essias\n" + modRoot + "essas");
                    cs.setCundizionalindirectplural3(modRoot + "essian\n" + modRoot + "essan");
                    break;
                }
                cs.setCundizionalindirectsing1(root + "essi");
                cs.setCundizionalindirectsing2(root + "essias\n" + root + "essas");
                cs.setCundizionalindirectsing3(root + "essi");
                cs.setCundizionalindirectplural1(root + "essian\n" + root + "essan");
                cs.setCundizionalindirectplural2(root + "essias\n" + root + "essas");
                cs.setCundizionalindirectplural3(root + "essian\n" + root + "essan");
                break;
        }
    }

    private void setParticipPerfect(String root, SutsilvanConjugationStructure cs) {

        switch (cs.getConjugationClass().id.substring(0, 1)) {
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
                if (endsOnGl(cs)) {
                    cs.setParticipperfectms(root + "eu");
                    cs.setParticipperfectfs(root + "eada");
                    cs.setParticipperfectmp(root + "eus");
                    cs.setParticipperfectfp(root + "eadas");
                    break;
                }
                cs.setParticipperfectms(root + "ieu");
                cs.setParticipperfectfs(root + "eada");
                cs.setParticipperfectmp(root + "ieus");
                cs.setParticipperfectfp(root + "eadas");
                break;

            /*case "3":
            case "4":
            case "7":*/
            default:
                if (cs.getConjugationclass().id.equals("3a") || cs.getConjugationclass().id.equals("3b") || cs.getConjugationclass().id.equals("3c") || cs.getConjugationclass().id.equals("3d") || cs.getConjugationclass().id.equals("3e")) {
                    cs.setParticipperfectms(modRoot + "ieu");
                    cs.setParticipperfectfs(modRoot + "ida");
                    cs.setParticipperfectmp(modRoot + "ieus");
                    cs.setParticipperfectfp(modRoot + "idas");
                    break;
                }
                cs.setParticipperfectms(root + "ieu");
                cs.setParticipperfectfs(root + "ida");
                cs.setParticipperfectmp(root + "ieus");
                cs.setParticipperfectfp(root + "idas");
                break;
        }
    }

    private void setGerundium(String root, SutsilvanConjugationStructure cs) {
        if (cs.getConjugationclass().id.equals("3a") || cs.getConjugationclass().id.equals("3b") || cs.getConjugationclass().id.equals("3c") || cs.getConjugationclass().id.equals("3d") || cs.getConjugationclass().id.equals("3e")) {
            cs.setGerundium(modRoot + "ànd");
            return;
        }
        if(needsBindingIEnding(cs.getConjugationclass().id)) {
            cs.setGerundium(root + "iànd");
            return;
        }
        cs.setGerundium(root + "ànd");
    }

    private void setImperativ(String root, SutsilvanConjugationStructure cs) {

        switch (cs.getConjugationClass().id.substring(0, 1)) {
            /*case "1":
                cs.setImperativ1(root + "a!");
                cs.setImperativ2(root + "ad!");
                break;*/

            case "2":
                cs.setImperativ1(modRoot + "a!");
                cs.setImperativ2(root + "ead!");
                break;

            case "3":
            case "4":
            case "9":
                if (cs.getConjugationclass().id.equals("3a") || cs.getConjugationclass().id.equals("3b") || cs.getConjugationclass().id.equals("3c") || cs.getConjugationclass().id.equals("3d") || cs.getConjugationclass().id.equals("3e")) {
                    cs.setImperativ1(root + "a!");
                    cs.setImperativ2(modRoot + "ed!");
                    break;
                }
                cs.setImperativ1(modRoot + "a!");
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

            //case "8":
            default:
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

    private static void setPreschentEnclitic(SutsilvanConjugationStructure cs) {
        String preschentsing1 = cs.getPreschentsing1();
        if (preschentsing1.contains("\n")) {
            preschentsing1 = (preschentsing1.split("\n"))[0];
        }
        cs.setPreschentsing1Enclitic(preschentsing1 + "iou\n" + preschentsing1 + " jou");
        cs.setPreschentsing2Enclitic(cs.getPreschentsing2() );
        cs.setPreschentsing3EncliticM(cs.getPreschentsing3());
        cs.setPreschentsing3EncliticF(cs.getPreschentsing3());
        cs.setPreschentplural1Enclitic(cs.getPreschentplural1().substring(0, cs.getPreschentplural1().length() - 3) + "ainsa");
        cs.setPreschentplural2Enclitic(cs.getPreschentplural2());
        cs.setPreschentplural3Enclitic(cs.getPreschentplural3() + "i");

    }

    private static void setImperfectEnclitic(SutsilvanConjugationStructure cs) {
        cs.setImperfectsing1Enclitic(cs.getImperfectsing1().substring(0, cs.getImperfectsing1().length() - 1) + "iou");
        cs.setImperfectsing2Enclitic(cs.getImperfectsing2());
        cs.setImperfectsing3EncliticM(cs.getImperfectsing3());
        cs.setImperfectsing3EncliticF(cs.getImperfectsing3());
        cs.setImperfectplural1Enclitic(cs.getImperfectplural1());
        cs.setImperfectplural2Enclitic(cs.getImperfectplural2());
        cs.setImperfectplural3Enclitic(cs.getImperfectplural3() + "i");
    }

    private static void setCundizionalEnclitic(SutsilvanConjugationStructure cs) {
        cs.setCundizionalsing1Enclitic(cs.getCundizionalsing1() + "iou\n" + cs.getCundizionalsing1() + " jou");
        cs.setCundizionalsing2Enclitic(cs.getCundizionalsing2());
        cs.setCundizionalsing3EncliticM(cs.getCundizionalsing3());
        cs.setCundizionalsing3EncliticF(cs.getCundizionalsing3());
        cs.setCundizionalplural1Enclitic(cs.getCundizionalplural1());
        cs.setCundizionalplural2Enclitic(cs.getCundizionalplural2());
        cs.setCundizionalplural3Enclitic(cs.getCundizionalplural3() + "i");
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
        cs.setPreschentsing1(setPronoun(SutsilvanPronouns.pron_1ps, conjugation.get(SutsilvanConjugationStructure.preschentsing1)));
        cs.setPreschentsing2(setPronoun(SutsilvanPronouns.pron_2ps, conjugation.get(SutsilvanConjugationStructure.preschentsing2)));
        cs.setPreschentsing3(setPronoun(SutsilvanPronouns.pron_3ps, conjugation.get(SutsilvanConjugationStructure.preschentsing3)));
        cs.setPreschentplural1(setPronoun(SutsilvanPronouns.pron_1pp, conjugation.get(SutsilvanConjugationStructure.preschentplural1)));
        cs.setPreschentplural2(setPronoun(SutsilvanPronouns.pron_2pp, conjugation.get(SutsilvanConjugationStructure.preschentplural2)));
        cs.setPreschentplural3(setPronoun(SutsilvanPronouns.pron_3pp, conjugation.get(SutsilvanConjugationStructure.preschentplural3)));

        // IMPERFECT
        cs.setImperfectsing1(setPronoun(SutsilvanPronouns.pron_1ps, conjugation.get(SutsilvanConjugationStructure.imperfectsing1)));
        cs.setImperfectsing2(setPronoun(SutsilvanPronouns.pron_2ps, conjugation.get(SutsilvanConjugationStructure.imperfectsing2)));
        cs.setImperfectsing3(setPronoun(SutsilvanPronouns.pron_3ps, conjugation.get(SutsilvanConjugationStructure.imperfectsing3)));
        cs.setImperfectplural1(setPronoun(SutsilvanPronouns.pron_1pp, conjugation.get(SutsilvanConjugationStructure.imperfectplural1)));
        cs.setImperfectplural2(setPronoun(SutsilvanPronouns.pron_2pp, conjugation.get(SutsilvanConjugationStructure.imperfectplural2)));
        cs.setImperfectplural3(setPronoun(SutsilvanPronouns.pron_3pp, conjugation.get(SutsilvanConjugationStructure.imperfectplural3)));

        // CONJUNCTIV
        cs.setConjunctivsing1(setPronoun(SutsilvanPronouns.pron_conjunctiv_c + SutsilvanPronouns.pron_1ps, conjugation.get(SutsilvanConjugationStructure.conjunctivsing1)));
        cs.setConjunctivsing2(setPronoun(SutsilvanPronouns.pron_conjunctiv_c + SutsilvanPronouns.pron_2ps, conjugation.get(SutsilvanConjugationStructure.conjunctivsing2)));
        cs.setConjunctivsing3(setPronoun(SutsilvanPronouns.pron_conjunctiv_v + SutsilvanPronouns.pron_3ps, conjugation.get(SutsilvanConjugationStructure.conjunctivsing3)));
        cs.setConjunctivplural1(setPronoun(SutsilvanPronouns.pron_conjunctiv_c + SutsilvanPronouns.pron_1pp, conjugation.get(SutsilvanConjugationStructure.conjunctivplural1)));
        cs.setConjunctivplural2(setPronoun(SutsilvanPronouns.pron_conjunctiv_c + SutsilvanPronouns.pron_2pp, conjugation.get(SutsilvanConjugationStructure.conjunctivplural2)));
        cs.setConjunctivplural3(setPronoun(SutsilvanPronouns.pron_conjunctiv_v + SutsilvanPronouns.pron_3pp, conjugation.get(SutsilvanConjugationStructure.conjunctivplural3)));

        // CONJUNCTIV IMPERFECT
        cs.setConjunctivimperfectsing1(setPronoun(SutsilvanPronouns.pron_conjunctiv_c + SutsilvanPronouns.pron_1ps, conjugation.get(SutsilvanConjugationStructure.conjunctivimperfectsing1)));
        cs.setConjunctivimperfectsing2(setPronoun(SutsilvanPronouns.pron_conjunctiv_c + SutsilvanPronouns.pron_2ps, conjugation.get(SutsilvanConjugationStructure.conjunctivimperfectsing2)));
        cs.setConjunctivimperfectsing3(setPronoun(SutsilvanPronouns.pron_conjunctiv_v + SutsilvanPronouns.pron_3ps, conjugation.get(SutsilvanConjugationStructure.conjunctivimperfectsing3)));
        cs.setConjunctivimperfectplural1(setPronoun(SutsilvanPronouns.pron_conjunctiv_c + SutsilvanPronouns.pron_1pp, conjugation.get(SutsilvanConjugationStructure.conjunctivimperfectplural1)));
        cs.setConjunctivimperfectplural2(setPronoun(SutsilvanPronouns.pron_conjunctiv_c + SutsilvanPronouns.pron_2pp, conjugation.get(SutsilvanConjugationStructure.conjunctivimperfectplural2)));
        cs.setConjunctivimperfectplural3(setPronoun(SutsilvanPronouns.pron_conjunctiv_v + SutsilvanPronouns.pron_3pp, conjugation.get(SutsilvanConjugationStructure.conjunctivimperfectplural3)));

        // CUNDIZIONAL
        cs.setCundizionalsing1(setPronoun(SutsilvanPronouns.pron_1ps, conjugation.get(SutsilvanConjugationStructure.cundizionalsing1)));
        cs.setCundizionalsing2(setPronoun(SutsilvanPronouns.pron_2ps, conjugation.get(SutsilvanConjugationStructure.cundizionalsing2)));
        cs.setCundizionalsing3(setPronoun(SutsilvanPronouns.pron_3ps, conjugation.get(SutsilvanConjugationStructure.cundizionalsing3)));
        cs.setCundizionalplural1(setPronoun(SutsilvanPronouns.pron_1pp, conjugation.get(SutsilvanConjugationStructure.cundizionalplural1)));
        cs.setCundizionalplural2(setPronoun(SutsilvanPronouns.pron_2pp, conjugation.get(SutsilvanConjugationStructure.cundizionalplural2)));
        cs.setCundizionalplural3(setPronoun(SutsilvanPronouns.pron_3pp, conjugation.get(SutsilvanConjugationStructure.cundizionalplural3)));

        // CUNDIZIONAL INDIRECT
        cs.setCundizionalindirectsing1(setPronoun(SutsilvanPronouns.pron_1ps, conjugation.get(SutsilvanConjugationStructure.cundizionalindirectsing1)));
        cs.setCundizionalindirectsing2(setPronoun(SutsilvanPronouns.pron_2ps, conjugation.get(SutsilvanConjugationStructure.cundizionalindirectsing2)));
        cs.setCundizionalindirectsing3(setPronoun(SutsilvanPronouns.pron_3ps, conjugation.get(SutsilvanConjugationStructure.cundizionalindirectsing3)));
        cs.setCundizionalindirectplural1(setPronoun(SutsilvanPronouns.pron_1pp, conjugation.get(SutsilvanConjugationStructure.cundizionalindirectplural1)));
        cs.setCundizionalindirectplural2(setPronoun(SutsilvanPronouns.pron_2pp, conjugation.get(SutsilvanConjugationStructure.cundizionalindirectplural2)));
        cs.setCundizionalindirectplural3(setPronoun(SutsilvanPronouns.pron_3pp, conjugation.get(SutsilvanConjugationStructure.cundizionalindirectplural3)));

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
        cs.setFutursing1(setPronoun(SutsilvanPronouns.pron_1ps, conjugation.get(SutsilvanConjugationStructure.futursing1)));
        cs.setFutursing2(setPronoun(SutsilvanPronouns.pron_2ps, conjugation.get(SutsilvanConjugationStructure.futursing2)));
        cs.setFutursing3(setPronoun(SutsilvanPronouns.pron_3ps, conjugation.get(SutsilvanConjugationStructure.futursing3)));
        cs.setFuturplural1(setPronoun(SutsilvanPronouns.pron_1pp, conjugation.get(SutsilvanConjugationStructure.futurplural1)));
        cs.setFuturplural2(setPronoun(SutsilvanPronouns.pron_2pp, conjugation.get(SutsilvanConjugationStructure.futurplural2)));
        cs.setFuturplural3(setPronoun(SutsilvanPronouns.pron_3pp, conjugation.get(SutsilvanConjugationStructure.futurplural3)));

        // ENCLITIC: PRESCHENT
        cs.setPreschentsing1Enclitic(conjugation.get(SutsilvanConjugationStructure.preschentsing1enclitic));
        cs.setPreschentsing2Enclitic(conjugation.get(SutsilvanConjugationStructure.preschentsing2enclitic));
        cs.setPreschentsing3EncliticM(conjugation.get(SutsilvanConjugationStructure.preschentsing3encliticm));
        cs.setPreschentsing3EncliticF(conjugation.get(SutsilvanConjugationStructure.preschentsing3encliticf));
        cs.setPreschentplural1Enclitic(conjugation.get(SutsilvanConjugationStructure.preschentplural1enclitic));
        cs.setPreschentplural2Enclitic(conjugation.get(SutsilvanConjugationStructure.preschentplural2enclitic));
        cs.setPreschentplural3Enclitic(conjugation.get(SutsilvanConjugationStructure.preschentplural3enclitic));

        // ENCLITIC: IMPERFECT
        cs.setImperfectsing1Enclitic(conjugation.get(SutsilvanConjugationStructure.imperfectsing1enclitic));
        cs.setImperfectsing2Enclitic(conjugation.get(SutsilvanConjugationStructure.imperfectsing2enclitic));
        cs.setImperfectsing3EncliticM(conjugation.get(SutsilvanConjugationStructure.imperfectsing3encliticm));
        cs.setImperfectsing3EncliticF(conjugation.get(SutsilvanConjugationStructure.imperfectsing3encliticf));
        cs.setImperfectplural1Enclitic(conjugation.get(SutsilvanConjugationStructure.imperfectplural1enclitic));
        cs.setImperfectplural2Enclitic(conjugation.get(SutsilvanConjugationStructure.imperfectplural2enclitic));
        cs.setImperfectplural3Enclitic(conjugation.get(SutsilvanConjugationStructure.imperfectplural3enclitic));

        // ENCLITIC: CUNDIZIUNAL
        cs.setCundizionalsing1Enclitic(conjugation.get(SutsilvanConjugationStructure.cundizionalsing1enclitic));
        cs.setCundizionalsing2Enclitic(conjugation.get(SutsilvanConjugationStructure.cundizionalsing2enclitic));
        cs.setCundizionalsing3EncliticM(conjugation.get(SutsilvanConjugationStructure.cundizionalsing3encliticm));
        cs.setCundizionalsing3EncliticF(conjugation.get(SutsilvanConjugationStructure.cundizionalsing3encliticf));
        cs.setCundizionalplural1Enclitic(conjugation.get(SutsilvanConjugationStructure.cundizionalplural1enclitic));
        cs.setCundizionalplural2Enclitic(conjugation.get(SutsilvanConjugationStructure.cundizionalplural2enclitic));
        cs.setCundizionalplural3Enclitic(conjugation.get(SutsilvanConjugationStructure.cundizionalplural3enclitic));

        return cs.getAllFormValues();
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

    private boolean endsOnGl(SutsilvanConjugationStructure cs) {
        return (cs.getConjugationclass().id.charAt(0) == '2' && getInfinitiv().endsWith("gliear"));
    }

    private boolean needsBindingIEnding(String conjugationClass) {
        return (conjugationClass.startsWith("2") && infinitiv.endsWith("gear") && !infinitiv.endsWith("tgear")) ||
                (conjugationClass.startsWith("3") && infinitiv.endsWith("ger") && !infinitiv.endsWith("tger")) ||
                (conjugationClass.startsWith("4") && infinitiv.endsWith("gir") && !infinitiv.endsWith("tgir"))
                ;
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

    private String setPronoun(String pronoun, String forms) {
        String[] singleForms = forms.split("\n");
        for (int i = 0; i < singleForms.length; i++) {
            singleForms[i] = pronoun + singleForms[i];
        }
        return String.join("\n", singleForms);
    }
}
