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
        resetValues();

        root = getRoot(infinitiv);

        switch (conjugationClass) {
            case "1a" -> {
                int start = root.lastIndexOf("a");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "e" + root.substring(start + 1);
            }
            case "1b" -> {
                int start = root.lastIndexOf("i");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "e" + root.substring(start + 1);
            }
            case "1c" -> {
                int start = root.lastIndexOf("a");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ei" + root.substring(start + 1);
            }
            case "1d" -> {
                int start = root.lastIndexOf("i");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ei" + root.substring(start + 1);
            }
            case "1e" -> {
                int start = root.lastIndexOf("a");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ai" + root.substring(start + 1);
            }
            case "1f" -> {
                int start = root.lastIndexOf("i");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ie" + root.substring(start + 1);
            }
            case "1g" -> {
                int start = root.lastIndexOf("u");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ov" + root.substring(start + 1);
            }
            case "1h" -> {
                int start = root.lastIndexOf("ar");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "rei" + root.substring(start + 2);
            }
            case "1i" -> {
                int start = root.lastIndexOf("ar");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ro" + root.substring(start + 2);
            }
            case "1j" -> {
                int start = root.lastIndexOf("an");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ang" + root.substring(start + 2);
            }
            case "1k" -> {
                int start = root.lastIndexOf("un");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ung" + root.substring(start + 2);
            }
            case "1l" -> {
                int start = root.lastIndexOf("an");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "agn" + root.substring(start + 2);
            }
            case "1m" -> {
                int start = root.lastIndexOf("in");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "egn" + root.substring(start + 2);
            }
            case "1n" -> {
                int start = root.lastIndexOf("in");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ign" + root.substring(start + 2);
            }
            case "1o" -> {
                int start = root.lastIndexOf("ga");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "gio" + root.substring(start + 2);
            }
            case "1p" -> {
                int start = root.lastIndexOf("ca");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "tgo" + root.substring(start + 2);
            }
            case "1q" -> {
                int indexA = root.lastIndexOf("a");
                int indexU = root.lastIndexOf("u");
                if (indexA == -1 || indexU == -1) break;
                modRoot = root.substring(0, indexU) + "a" + root.substring(indexU + 1, indexA) + "u" + root.substring(indexA + 1) + "g";
            }
            case "1r" -> {
                int indexE = root.lastIndexOf("e");
                int indexA = root.lastIndexOf("a");
                if (indexA == -1 || indexE == -1) break;
                modRoot = root.substring(0, indexE) + "a" + root.substring(indexE + 1, indexA) + "e" + root.substring(indexA + 1);
            }
            case "1s" -> {
                int start = root.lastIndexOf("e");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ai" + root.substring(start + 1);
            }
            case "1t" -> {
                int start = root.lastIndexOf("a");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "o" + root.substring(start + 1);
            }
            case "1u" -> {
                int start = root.lastIndexOf("u");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "o" + root.substring(start + 1);
            }
            case "2" -> {
                if(infinitiv.endsWith("gler")) {
                    modRoot = root;
                    modRoot = modRoot + "i";
                }
            }
            case "2a" -> {
                int start = root.lastIndexOf("ag");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "eg" + root.substring(start + 2) + "i";
                if(infinitiv.endsWith("gler")) {
                    modRoot = modRoot + "i";
                }
            }
            case "2b" -> {
                int start = root.lastIndexOf("cag");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "cheg" + root.substring(start + 3) + "i";
                if(infinitiv.endsWith("gler")) {
                    modRoot = modRoot + "i";
                }
            }
            case "2c" -> {
                modRoot = root + "esch";
            }
            case "3" -> {
                if(infinitiv.endsWith("glier")) {
                    modRoot = root;
                    modRoot = modRoot + "i";
                }
            }
            case "3a" -> {
                int start = root.lastIndexOf("i");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "e" + root.substring(start + 1);

                if(infinitiv.endsWith("glier")) {
                    modRoot = modRoot + "i";
                }
            }
            case "3b" -> {
                int start = root.lastIndexOf("u");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "o" + root.substring(start + 1);

                if(infinitiv.endsWith("glier")) {
                    modRoot = modRoot + "i";
                }
            }
            case "3c" -> {
                int start = root.lastIndexOf("u");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ou" + root.substring(start + 1);

                if(infinitiv.endsWith("glier")) {
                    modRoot = modRoot + "i";
                }
            }
            case "3d" -> {
                modRoot = root + "esch";
            }
            case "4" -> {
                if(infinitiv.endsWith("gleir")) {
                    modRoot = root;
                    modRoot = modRoot + "i";
                }
            }
            case "4a" -> {
                int start = root.lastIndexOf("a");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "e" + root.substring(start + 1);

                if(infinitiv.endsWith("gleir")) {
                    modRoot = modRoot + "i";
                }
            }
            case "5" -> {
                if(infinitiv.endsWith("gler")) {
                    modRoot = root;
                    modRoot = modRoot + "i";
                }
            }
            case "5a" -> {
                int start = root.lastIndexOf("e");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "a" + root.substring(start + 1);

                if(infinitiv.endsWith("gler")) {
                    modRoot = modRoot + "i";
                }
            }
            case "5b" -> {
                int start = root.lastIndexOf("o");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "u" + root.substring(start + 1);

                if(infinitiv.endsWith("gler")) {
                    modRoot = modRoot + "i";
                }
            }
            case "5c" -> {
                int start = root.lastIndexOf("ei");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "i" + root.substring(start + 2);

                if(infinitiv.endsWith("gler")) {
                    modRoot = modRoot + "i";
                }
            }
            case "6" -> {
                if(infinitiv.endsWith("gleir")) {
                    modRoot = root;
                    modRoot = modRoot + "i";
                }
            }
            case "6a" -> {
                int start = root.lastIndexOf("ar");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ra" + root.substring(start + 2);

                if(infinitiv.endsWith("gleir")) {
                    modRoot = modRoot + "i";
                }
                if(infinitiv.endsWith("geir")) {
                    modRoot = modRoot + "i";
                }
            }
            case "6b" -> {
                int start = root.lastIndexOf("a");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "e" + root.substring(start + 1);

                if(infinitiv.endsWith("gleir")) {
                    modRoot = modRoot + "i";
                }
            }
            case "6c" -> {
                int start = root.lastIndexOf("i");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "e" + root.substring(start + 1);

                if(infinitiv.endsWith("gleir")) {
                    modRoot = modRoot + "i";
                }
            }
            case "6d" -> {
                int start = root.lastIndexOf("ugl");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ogl" + root.substring(start + 3);

                if(infinitiv.endsWith("gleir")) {
                    modRoot = modRoot + "i";
                }
            }
            case "6e" -> {
                int start = root.lastIndexOf("a");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ai" + root.substring(start + 1);

                if(infinitiv.endsWith("gleir")) {
                    modRoot = modRoot + "i";
                }
            }
            case "9" -> {
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
        if (baseForm.length() < 3) {
            return null;
        }

        String lastTwo = baseForm.substring(baseForm.length() - 2);
        String lastThree = baseForm.substring(baseForm.length() - 3);

        if (lastThree.equals("ier")) {
            if ((flex != null && flex.equals("-escha")) || baseForm.endsWith("tgier")) {
                return generateConjugation("3d", baseForm);
            }
            if (flex != null && !flex.equals("")) {
                String[] subForms = new String[] { "3a", "3b", "3c" };
                for (String subForm : subForms) {
                    InflectionResponse r = generateConjugation(subForm, baseForm);
                    if (doesFormContainAllValues(r, flex)) {
                        return r;
                    }
                }
            }


            return generateConjugation("3", baseForm);
        }

        if (lastThree.equals("eir")) {
            if (flex != null && flex.equals("-escha")) {
                return generateConjugation("7", baseForm);
            }

            if (flex != null && !flex.equals("")) {
                String[] subForms = new String[]{"4", "4a", "6a", "6b", "6c", "6d", "6e"};
                for (String subForm : subForms) {
                    InflectionResponse r = generateConjugation(subForm, baseForm);
                    if (doesFormContainAllValues(r, flex)) {
                        return r;
                    }
                }
            }

            return generateConjugation("6", baseForm);
        }

        if (lastTwo.equals("ar")) {
            if (flex != null && flex.equals("-escha")) {
                return generateConjugation("8", baseForm);
            }

            if (baseForm.endsWith("anar")) {
                String possibleRoot = baseForm.replaceFirst(".{2}$", "");
                int indexU = possibleRoot.lastIndexOf("u");
                int indexA = possibleRoot.lastIndexOf("a");

                if (indexU != -1 && indexA != -1 && indexU <= indexA - 2) {
                    return generateConjugation("1q", baseForm);
                }
            }

            if (baseForm.endsWith("entar")) {
                return generateConjugation("1s", baseForm);
            }

            if (flex != null && !flex.equals("")) {
                String[] subForms = new String[]{"1a", "1b", "1c", "1d", "1e", "1f", "1g", "1h", "1i", "1k", "1l", "1m", "1n", "1o", "1p", "1r", "1t", "1u"};
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
            if (baseForm.endsWith("cager")) {
                return generateConjugation("2b", baseForm);
            }

            if (lastThree.equals("ger")) {
                return generateConjugation("2a", baseForm);
            }

            if (flex != null && !flex.equals("")) {
                String[] subForms = new String[]{"5", "5a", "5b", "5c"};
                for (String subForm : subForms) {
                    InflectionResponse r = generateConjugation(subForm, baseForm);
                    if (doesFormContainAllValues(r, flex)) {
                        return r;
                    }
                }
            }

            return generateConjugation("2", baseForm);
        }

        return null;
    }

    public static SurmiranConjugationStructure addEncliticForms(SurmiranConjugationStructure cs) {
        SurmiranConjugation.setPreschentEnclitic(cs);
        SurmiranConjugation.setImperfectEnclitic(cs);
        SurmiranConjugation.setCundizionalEnclitic(cs);
        SurmiranConjugation.setFuturEnclitic(cs);

        return cs;
    }

    private String getRoot(String infinitiv) {
        if (infinitiv != null) {
            infinitiv = normalizeString(infinitiv);

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

        setPreschentEnclitic(cs);
        setImperfectEnclitic(cs);
        setCundizionalEnclitic(cs);
        setFuturEnclitic(cs);

        return cs.getValues();
    }

    private void setPreschent(SurmiranConjugationStructure cs) {

        switch (cs.getConjugationclass().id) {

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
            case "1r":
            case "1s":
            case "1t":
            case "1u":
            case "2":
            case "2c":
            case "3":
            case "3a":
            case "3b":
            case "3c":
            case "3d":
            case "4":
            case "4a":
            case "5":
            case "6":
            case "6a":
            case "6b":
            case "6c":
            case "6d":
            case "6e":
                if (ending.equals("ar") && !getInfinitiv().equals("mussar") && endsWithDoubleConsonant(modRoot)) {
                    String firstSingular = modRoot.substring(0, modRoot.length() - 1);
                    cs.setPreschentsing1(firstSingular);
                } else if(endsOnGl(cs)) {
                    cs.setPreschentsing1(modRoot.substring(0, modRoot.length() - 1));
                } else if (endsOnGeir(cs)){
                    cs.setPreschentsing1(modRoot.substring(0, modRoot.length() - 2) + "tg");
                } else {
                    cs.setPreschentsing1(modRoot);
                }
                cs.setPreschentsing2(modRoot + "as");
                cs.setPreschentsing3(modRoot + "a");
                if (cs.getConjugationclass().id.charAt(0) == '6') {
                    // 1pp
                    cs.setPreschentplural1(root + "ign");
                    // 2pp
                    cs.setPreschentplural2(root + "iz");
                } else {
                    // 1pp
                    if(endsOnGl(cs)) {
                        cs.setPreschentplural1(root + "iagn");
                    } else {
                        cs.setPreschentplural1(root + "agn");
                    }

                    // 2pp
                    cs.setPreschentplural2(root + "ez");
                }
                cs.setPreschentplural3(modRoot + "an");
                break;

            case "2a":
            case "2b":
                cs.setPreschentsing1(modRoot.substring(0, modRoot.length() - 2) + "tg");
                cs.setPreschentsing2(modRoot + "as");
                cs.setPreschentsing3(modRoot + "a");
                cs.setPreschentplural1(root + "iagn");
                cs.setPreschentplural2(root + "ez");
                cs.setPreschentplural3(modRoot + "an");
                break;


            case "5a":
            case "5b":
            case "5c":
                cs.setPreschentsing1(root);
                cs.setPreschentsing2(root + "as");
                cs.setPreschentsing3(root + "a");
                cs.setPreschentplural1(modRoot + "agn");
                cs.setPreschentplural2(modRoot + "ez");
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

                if (cs.getConjugationclass().id.charAt(0) == '7') {
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

        switch (cs.getConjugationclass().id.substring(0, 1)) {

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
                if (cs.getConjugationclass().id.equals("5a") || cs.getConjugationclass().id.equals("5b") || cs.getConjugationclass().id.equals("5c")) {
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
                if (cs.getConjugationclass().id.equals("5a") || cs.getConjugationclass().id.equals("5b") || cs.getConjugationclass().id.equals("5c")) {
                    cs.setConjunctivsing1(root + "a");
                    cs.setConjunctivsing2(root + "as");
                    cs.setConjunctivsing3(root + "a");
                    cs.setConjunctivplural1(root + "an");
                    cs.setConjunctivplural2(root + "as");
                    cs.setConjunctivplural3(root + "an");
                    break;
                }

                cs.setConjunctivsing1(modRoot + "a");
                cs.setConjunctivsing2(modRoot + "as");
                cs.setConjunctivsing3(modRoot + "a");
                cs.setConjunctivplural1(modRoot + "an");
                cs.setConjunctivplural2(modRoot + "as");
                cs.setConjunctivplural3(modRoot + "an");
                break;
        }
    }

    private void setCundizional(SurmiranConjugationStructure cs) {

        switch (cs.getConjugationclass().id.substring(0, 1)) {

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
                if (cs.getConjugationclass().id.equals("5a") || cs.getConjugationclass().id.equals("5b") || cs.getConjugationclass().id.equals("5c")) {
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

    private void setParticipPerfect(SurmiranConjugationStructure cs) {

        switch (cs.getConjugationclass().id.substring(0, 1)) {
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
                if (cs.getConjugationclass().id.equals("5a") || cs.getConjugationclass().id.equals("5b") || cs.getConjugationclass().id.equals("5c")) {
                    cs.setParticipperfectms(modRoot + "ia");
                    cs.setParticipperfectmp(modRoot + "ias");
                    cs.setParticipperfectfs(modRoot + "eida");
                    cs.setParticipperfectfp(modRoot + "eidas");
                    break;
                }

                cs.setParticipperfectms(root + "ia");
                cs.setParticipperfectmp(root + "ias");
                cs.setParticipperfectfs(root + "eida");
                cs.setParticipperfectfp(root + "eidas");
                break;
        }
    }

    private void setGerundium(SurmiranConjugationStructure cs) {

        switch (cs.getConjugationclass().id.substring(0, 1)) {
            case "6":
            case "7":
                if (endsOnGeir(cs)) {
                    cs.setGerundium(root + "iond" + "/" + root + "end");
                    break;
                }
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
                if (cs.getConjugationclass().id.equals("2a") || cs.getConjugationclass().id.equals("2b") || endsOnGl(cs)) {
                    cs.setGerundium(root + "iond");
                    break;
                }
                if (cs.getConjugationclass().id.equals("5a") || cs.getConjugationclass().id.equals("5b") || cs.getConjugationclass().id.equals("5c")) {
                    cs.setGerundium(modRoot + "ond");
                    break;
                }
                cs.setGerundium(root + "ond");
                break;
        }

    }

    private void setImperativ(SurmiranConjugationStructure cs) {
        switch (cs.getConjugationclass().id) {
            case "7":
            case "8":
                cs.setImperativ1(root + "escha!");

                if (cs.getConjugationclass().id.charAt(0) == '7') {
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
                if (cs.getConjugationclass().id.equals("5a") || cs.getConjugationclass().id.equals("5b") || cs.getConjugationclass().id.equals("5c")) {
                    cs.setImperativ1(root + "a!");
                    cs.setImperativ2(modRoot + "e!");
                    break;
                }
                cs.setImperativ1(modRoot + "a!");
                if (cs.getConjugationclass().id.charAt(0) == '6') {
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
                    switch (cs.getConjugationclass().id.substring(0, 1)) {
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
                    switch (cs.getConjugationclass().id.substring(0, 1)) {
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
                            if (cs.getConjugationclass().id.equals("5a") || cs.getConjugationclass().id.equals("5b") || cs.getConjugationclass().id.equals("5c")) {
                                cs.setFutursing1(SurmiranPronouns.pron_r_1ps + modRoot + "aro");
                                cs.setFutursing2(SurmiranPronouns.pron_r_2ps + modRoot + "arossas");
                                cs.setFutursing3(SurmiranPronouns.pron_r_3ps + modRoot + "aro");
                                cs.setFuturplural1(SurmiranPronouns.pron_r_1pp + modRoot + "aron");
                                cs.setFuturplural2(SurmiranPronouns.pron_r_2pp + modRoot + "arossas");
                                cs.setFuturplural3(SurmiranPronouns.pron_r_3pp + modRoot + "aron");
                                break;
                            }
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

                switch (cs.getConjugationclass().id.substring(0, 1)) {
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
                        if (cs.getConjugationclass().id.equals("2a") || cs.getConjugationclass().id.equals("2b") || endsOnGl(cs)) {
                            cs.setFutursing1(root + "iaro");
                            cs.setFutursing2(root + "iarossas");
                            cs.setFutursing3(root + "iaro");
                            cs.setFuturplural1(root + "iaron");
                            cs.setFuturplural2(root + "iarossas");
                            cs.setFuturplural3(root + "iaron");
                            break;
                        }
                        if (cs.getConjugationclass().id.equals("5a") || cs.getConjugationclass().id.equals("5b") || cs.getConjugationclass().id.equals("5c")) {
                            cs.setFutursing1(modRoot + "aro");
                            cs.setFutursing2(modRoot + "arossas");
                            cs.setFutursing3(modRoot + "aro");
                            cs.setFuturplural1(modRoot + "aron");
                            cs.setFuturplural2(modRoot + "arossas");
                            cs.setFuturplural3(modRoot + "aron");
                            break;
                        }
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

    private static void setPreschentEnclitic(SurmiranConjugationStructure cs) {
        if(cs.getPreschentsing1().endsWith("gl")) {
            cs.setPreschentsing1Enclitic(cs.getPreschentsing1() + "ia");
        } else if (cs.getPreschentsing1().endsWith("tg")) {
            cs.setPreschentsing1Enclitic(cs.getPreschentsing1().substring(0, cs.getPreschentsing1().length() - 3) + "egia");
        } else {
            cs.setPreschentsing1Enclitic(cs.getPreschentsing1() + "a");
        }

        cs.setPreschentsing2Enclitic(cs.getPreschentsing2() + "t");


        if (SurmiranConjugation.isSingleSyllable(cs.getPreschentsing3()) && SurmiranConjugation.endsOnConsonantOrDiftong(cs.getPreschentsing3())) {
            cs.setPreschentsing3EncliticM(cs.getPreschentsing3() + "el");
        } else {
            cs.setPreschentsing3EncliticM(cs.getPreschentsing3() + "'l");
        }

        if (cs.getPreschentsing3().endsWith("gia")) {
            cs.setPreschentsing3EncliticF(cs.getPreschentsing3().substring(0, cs.getPreschentsing3().length() - 3) + "tg'la");
        } else {
            cs.setPreschentsing3EncliticF(cs.getPreschentsing3().substring(0, cs.getPreschentsing3().length() - 1) + "'la");
        }

        if (cs.getConjugationclass().id.charAt(0) == '6' || cs.getConjugationclass().id.charAt(0) == '7') {
            cs.setPreschentplural1Enclitic(cs.getPreschentplural1() + "sa");
        } else {
            cs.setPreschentplural1Enclitic(cs.getPreschentplural1().substring(0, cs.getPreschentplural1().length() - 3) + "ainsa");
        }

        cs.setPreschentplural2Enclitic(cs.getPreschentplural2());

        if (cs.getPreschentplural3().endsWith("gian")) {
            cs.setPreschentplural3Enclitic(cs.getPreschentplural3().substring(0, cs.getPreschentplural3().length() - 3) + "igl");
        } else {
            cs.setPreschentplural3Enclitic(cs.getPreschentplural3().substring(0, cs.getPreschentplural3().length() - 2) + "igl");
        }

    }

    private static void setImperfectEnclitic(SurmiranConjugationStructure cs) {
        cs.setImperfectsing1Enclitic(cs.getImperfectsing1());
        cs.setImperfectsing2Enclitic(cs.getImperfectsing2() + "t");
        cs.setImperfectsing3EncliticM(cs.getImperfectsing3() + "'l");
        cs.setImperfectsing3EncliticF(cs.getImperfectsing3().substring(0, cs.getImperfectsing3().length() - 1) + "'la");
        cs.setImperfectplural1Enclitic(cs.getImperfectplural1() + "s");
        cs.setImperfectplural2Enclitic(cs.getImperfectplural2());
        cs.setImperfectplural3Enclitic(cs.getImperfectplural3().substring(0, cs.getImperfectplural3().length() - 2) + "igl");
    }

    private static void setCundizionalEnclitic(SurmiranConjugationStructure cs) {
        cs.setCundizionalsing1Enclitic(cs.getCundizionalsing1() + "a");
        cs.setCundizionalsing2Enclitic(cs.getCundizionalsing2() + "t");
        cs.setCundizionalsing3EncliticM(cs.getCundizionalsing3() + "a'l");
        cs.setCundizionalsing3EncliticF(cs.getCundizionalsing3() + "'la");
        cs.setCundizionalplural1Enclitic(cs.getCundizionalplural1() + "s");
        cs.setCundizionalplural2Enclitic(cs.getCundizionalplural2());
        cs.setCundizionalplural3Enclitic(cs.getCundizionalplural3().substring(0, cs.getCundizionalplural3().length() - 2) + "igl");
    }

    private static void setFuturEnclitic(SurmiranConjugationStructure cs) {
        cs.setFutursing1Enclitic(cs.getFutursing1() + "ia");
        cs.setFutursing2Enclitic(cs.getFutursing2() + "t");
        cs.setFutursing3EncliticM(cs.getFutursing3() + "'l");
        cs.setFutursing3EncliticF(cs.getFutursing3() + "'la");
        cs.setFuturplural1Enclitic(cs.getFuturplural1() + "sa");
        cs.setFuturplural2Enclitic(cs.getFuturplural2());
        cs.setFuturplural3Enclitic(cs.getFuturplural3() + "igl");
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

        copyEncliticForms(conjugation, cs);

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

        copyEncliticForms(conjugation, cs);

        return cs.getAllFormValues();
    }

    private void copyEncliticForms(Map<String, String> conjugation, SurmiranConjugationStructure cs) {
        cs.setPreschentsing1Enclitic(conjugation.get(SurmiranConjugationStructure.preschentsing1enclitic));
        cs.setPreschentsing2Enclitic(conjugation.get(SurmiranConjugationStructure.preschentsing2enclitic));
        cs.setPreschentsing3EncliticM(conjugation.get(SurmiranConjugationStructure.preschentsing3encliticm));
        cs.setPreschentsing3EncliticF(conjugation.get(SurmiranConjugationStructure.preschentsing3encliticf));
        cs.setPreschentplural1Enclitic(conjugation.get(SurmiranConjugationStructure.preschentplural1enclitic));
        cs.setPreschentplural2Enclitic(conjugation.get(SurmiranConjugationStructure.preschentplural2enclitic));
        cs.setPreschentplural3Enclitic(conjugation.get(SurmiranConjugationStructure.preschentplural3enclitic));

        cs.setImperfectsing1Enclitic(conjugation.get(SurmiranConjugationStructure.imperfectsing1enclitic));
        cs.setImperfectsing2Enclitic(conjugation.get(SurmiranConjugationStructure.imperfectsing2enclitic));
        cs.setImperfectsing3EncliticM(conjugation.get(SurmiranConjugationStructure.imperfectsing3encliticm));
        cs.setImperfectsing3EncliticF(conjugation.get(SurmiranConjugationStructure.imperfectsing3encliticf));
        cs.setImperfectplural1Enclitic(conjugation.get(SurmiranConjugationStructure.imperfectplural1enclitic));
        cs.setImperfectplural2Enclitic(conjugation.get(SurmiranConjugationStructure.imperfectplural2enclitic));
        cs.setImperfectplural3Enclitic(conjugation.get(SurmiranConjugationStructure.imperfectplural3enclitic));

        cs.setCundizionalsing1Enclitic(conjugation.get(SurmiranConjugationStructure.cundizionalsing1enclitic));
        cs.setCundizionalsing2Enclitic(conjugation.get(SurmiranConjugationStructure.cundizionalsing2enclitic));
        cs.setCundizionalsing3EncliticM(conjugation.get(SurmiranConjugationStructure.cundizionalsing3encliticm));
        cs.setCundizionalsing3EncliticF(conjugation.get(SurmiranConjugationStructure.cundizionalsing3encliticf));
        cs.setCundizionalplural1Enclitic(conjugation.get(SurmiranConjugationStructure.cundizionalplural1enclitic));
        cs.setCundizionalplural2Enclitic(conjugation.get(SurmiranConjugationStructure.cundizionalplural2enclitic));
        cs.setCundizionalplural3Enclitic(conjugation.get(SurmiranConjugationStructure.cundizionalplural3enclitic));

        cs.setFutursing1Enclitic(conjugation.get(SurmiranConjugationStructure.futursing1enclitic));
        cs.setFutursing2Enclitic(conjugation.get(SurmiranConjugationStructure.futursing2enclitic));
        cs.setFutursing3EncliticM(conjugation.get(SurmiranConjugationStructure.futursing3encliticm));
        cs.setFutursing3EncliticF(conjugation.get(SurmiranConjugationStructure.futursing3encliticf));
        cs.setFuturplural1Enclitic(conjugation.get(SurmiranConjugationStructure.futurplural1enclitic));
        cs.setFuturplural2Enclitic(conjugation.get(SurmiranConjugationStructure.futurplural2enclitic));
        cs.setFuturplural3Enclitic(conjugation.get(SurmiranConjugationStructure.futurplural3enclitic));
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

    private boolean endsOnGl(SurmiranConjugationStructure cs) {
        return
                (cs.getConjugationclass().id.charAt(0) == '2' && getInfinitiv().endsWith("gler"))
                ||
                (cs.getConjugationclass().id.charAt(0) == '3' && getInfinitiv().endsWith("glier") && !cs.getConjugationclass().id.equals("3d"))
                ||
                (cs.getConjugationclass().id.charAt(0) == '5' && getInfinitiv().endsWith("gler"))
                ||
                (cs.getConjugationclass().id.charAt(0) == '6' && getInfinitiv().endsWith("gleir"));
    }

    private boolean endsOnGeir(SurmiranConjugationStructure cs) {
        return
                (cs.getConjugationclass().id.equals("6a") && getInfinitiv().endsWith("geir"));
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

    private static boolean endsOnConsonantOrDiftong(String word) {
        if (!isVocal(word.charAt(word.length() - 1))) {
            return true;
        }
        if (isVocal(word.charAt(word.length() - 2))) {
            return true;
        }
        return false;
    }
}
