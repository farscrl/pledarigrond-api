package ch.pledarigrond.inflection.generation.sursilvan;

import ch.pledarigrond.inflection.generation.generic.LanguageConjugation;
import ch.pledarigrond.inflection.model.InflectionResponse;
import ch.pledarigrond.inflection.model.InflectionSubType;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class SursilvanConjugation extends LanguageConjugation {

    private SursilvanConjugationStructure cs;

    private String infinitiv;
    private String verb;

    private String isReflexive;

    private String lastTwo;
    private String lastThree;
    private String modRootAlternative;

    private HashMap<String, String> conjugation;

    @Override
    public InflectionResponse generateConjugation(String conjugationClass, String infinitiv) {
        resetValues();

        root = getRoot(infinitiv);

        switch (conjugationClass) {
            case "1a" -> {
                int start = root.lastIndexOf("a");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "o" + root.substring(start + 1);
            }
            case "1b" -> {
                int start = root.lastIndexOf("i");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "o" + root.substring(start + 1);
            }
            case "1c" -> {
                int start = root.lastIndexOf("u");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "o" + root.substring(start + 1);
            }
            case "1d" -> {
                int start = root.lastIndexOf("o");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "uo" + root.substring(start + 1);
            }
            case "1e" -> {
                int start = root.lastIndexOf("u");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "uo" + root.substring(start + 1);
            }
            case "1f" -> {
                int start = root.lastIndexOf("a");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ei" + root.substring(start + 1);
            }
            case "1g" -> {
                int start = root.lastIndexOf("e");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ei" + root.substring(start + 1);
            }
            case "1h" -> {
                int start = root.lastIndexOf("i");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ei" + root.substring(start + 1);
            }
            case "1i" -> {
                int start = root.lastIndexOf("u");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "au" + root.substring(start + 1);
            }
            case "1j" -> {
                int start = root.lastIndexOf("i");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "au" + root.substring(start + 1);
            }
            case "1k" -> {
                int start = root.lastIndexOf("a");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "au" + root.substring(start + 1);
            }
            case "1l" -> {
                int start = root.lastIndexOf("a");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ia" + root.substring(start + 1);
            }
            case "1m" -> {
                int start = root.lastIndexOf("e");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ia" + root.substring(start + 1);
                if (endsWithTwoIdenticalConsonants(modRoot)) {
                    modRoot = modRoot.substring(0, modRoot.length() - 1);
                }
            }
            case "1n" -> {
                int start = root.lastIndexOf("i");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ia" + root.substring(start + 1);
            }
            case "1o" -> {
                int start = root.lastIndexOf("u");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ue" + root.substring(start + 1);
            }
            case "1p" -> {
                int start = root.lastIndexOf("i");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "e" + root.substring(start + 1);
            }
            case "1q" -> {
                int start = root.lastIndexOf("e");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ie" + root.substring(start + 1);
            }
            case "1r" -> {
                int start = root.lastIndexOf("i");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "a" + root.substring(start + 1);
                modRootAlternative = root.substring(0, start) + "e" + root.substring(start + 1);
            }
            case "1s" -> {
                int start = root.lastIndexOf("e");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "i" + root.substring(start + 1);
                modRootAlternative = root.substring(0, start) + "a" + root.substring(start + 1);
            }
            case "1t" -> {
                int start = root.lastIndexOf("a");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "u" + root.substring(start + 1);
            }
            case "1u" -> {
                int start = root.lastIndexOf("e");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "u" + root.substring(start + 1);
            }
            case "1v" -> {
                int start = root.lastIndexOf("u");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ua" + root.substring(start + 1);
            }
            case "1w" -> {
                int start = root.lastIndexOf("u");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "iu" + root.substring(start + 1);
            }

            case "2a" -> {
                int start = root.lastIndexOf("u");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "au" + root.substring(start + 1);
            }

            case "3a" -> {
                int start = root.lastIndexOf("o");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "u" + root.substring(start + 1);
                modRootAlternative = root.substring(0, start) + "iu" + root.substring(start + 1, root.length() - 1);
            }
            case "3b" -> {
                int start = root.lastIndexOf("ie");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "i" + root.substring(start + 2);
            }
            case "3c" -> {
                int start = root.lastIndexOf("au");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "u" + root.substring(start + 2);
            }
            case "3d" -> {
                int start = root.lastIndexOf("ei");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "e" + root.substring(start + 2);
            }
            case "3e" -> {
                int start = root.lastIndexOf("ou");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "u" + root.substring(start + 2);
            }
            case "3f" -> {
                int start = root.lastIndexOf("ia");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "e" + root.substring(start + 2);
            }

            case "4a" -> {
                int start = root.lastIndexOf("u");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "o" + root.substring(start + 1);
            }
            case "4b" -> {
                int start = root.lastIndexOf("u");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ie" + root.substring(start + 1);
                modRootAlternative = root.substring(0, start) + "o" + root.substring(start + 1);
            }
            case "4c" -> {
                int start = root.lastIndexOf("u");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "uo" + root.substring(start + 1);
            }
            case "4d" -> {
                int start = root.lastIndexOf("u");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "e" + root.substring(start + 1);
            }
            case "4e" -> {
                int start = root.lastIndexOf("e");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ia" + root.substring(start + 1);
            }
            case "4f" -> {
                Object[] result = findLastVowelWithRAndIndex(root);
                if ((int) result[0] != -1) {
                    int index = (int) result[0];
                    String vowel = (String) result[1];

                    modRoot = root.substring(0, index) + "r" + vowel.charAt(0) + root.substring(index + 2) + "i";
                }
            }
        }

        InflectionSubType subType = SursilvanConjugationClasses.getConjugationClass(conjugationClass);
        if (subType == null) {
            throw new RuntimeException(conjugationClass + " is not a valid conjugation class.");
        } else if (getEnding() == null) {
            throw new RuntimeException(infinitiv + " is not a valid verb. Please type a verb in its infinitive form.");
        }

        conjugation = conjugate(subType);

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
                    infinitiv = processQuery(infinitiv);
            }
        }
        return infinitiv;
    }

    public InflectionResponse guessInflection(String baseForm, String genus, String flex) {
        if (baseForm.length() < 3) {
            return null;
        }

        String lastTwo = baseForm.substring(baseForm.length() - 2);

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

    private String checkReflexiveness(String infinitiv) {
        String[] verbsOnSeThatAreNotReflexive = {
                "sentir", "serrar", "semiar", "sellar", "segirar", "seduler", "segenar", "semegliar", "semtgar"
        };

        if (isStringInArray(verbsOnSeThatAreNotReflexive, infinitiv)) {
            setIsReflexive("false");
            setVerb(infinitiv);
            setInfinitiv(infinitiv);
            return infinitiv;
        }

        if (infinitiv.startsWith("se")) {
            setVerb(infinitiv);
            infinitiv = infinitiv.length() > 2 ? infinitiv.substring(2) : infinitiv;
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

        switch (l2) {
            case "ar":
            case "er":
            case "ir":
                setLastTwo(l2);
                setEnding(getLastTwo());
                infinitiv = checkReflexiveness(infinitiv);
                infinitiv = infinitiv.substring(0, infinitiv.length() - 2);

                return infinitiv;

            default:
                return infinitiv;
        }
    }

    private HashMap<String, String> conjugate(InflectionSubType conjugationClass) {
        cs = new SursilvanConjugationStructure();
        cs.setVerb(getVerb());
        cs.setInfinitiv(getInfinitiv());
        cs.setRoot(root);
        cs.setEnding(getEnding());

        cs.setConjugationClass(conjugationClass);

        cs.setReflexive(getIsReflexive());

        setPreschent(cs);
        setImperfect(cs);
        setConjunctiv(cs);
        setConjunctivImperfect(cs);
        setCundizional(cs);
        setCundizionalIndirect(cs);
        setParticipPerfect(cs);
        setImperativ(cs);
        setGerundium(cs);
        setFutur(cs);

        return cs.getValues();
    }

    private void setPreschent(SursilvanConjugationStructure cs) {
        switch (cs.getConjugationclass().id) {
            case "1":
                cs.setPreschentsing1(root + "el");
                cs.setPreschentsing2(root + "as");
                cs.setPreschentsing3(root + "a");

                cs.setPreschentplural1(root + "ein");
                cs.setPreschentplural2(root + "eis");
                cs.setPreschentplural3(root + "an");
                break;

            case "1a":
            case "1b":
            case "1c":
            case "1d":
            case "1e":
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
            case "1t":
            case "1u":
            case "1v":
            case "1w":
                cs.setPreschentsing1(modRoot + "el");
                cs.setPreschentsing2(modRoot + "as");
                cs.setPreschentsing3(modRoot + "a");

                cs.setPreschentplural1(root + "ein");
                cs.setPreschentplural2(root + "eis");
                cs.setPreschentplural3(modRoot + "an");
                break;

            case "1f":
                cs.setPreschentsing1(modRoot + "el\n" + root + "el");
                cs.setPreschentsing2(modRoot + "as\n" + root + "as");
                cs.setPreschentsing3(modRoot + "a\n" + root + "a");

                cs.setPreschentplural1(root + "ein");
                cs.setPreschentplural2(root + "eis");
                cs.setPreschentplural3(modRoot + "an\n" + root + "an");
                break;
            case "1r":
            case "1s":
                cs.setPreschentsing1(modRoot + "el\n" + modRootAlternative + "el");
                cs.setPreschentsing2(modRoot + "as\n" + modRootAlternative + "as");
                cs.setPreschentsing3(modRoot + "a\n" + modRootAlternative + "a");

                cs.setPreschentplural1(root + "ein");
                cs.setPreschentplural2(root + "eis");
                cs.setPreschentplural3(modRoot + "an\n" + modRootAlternative + "an");
                break;

            case "6":
                cs.setPreschentsing1(root + "eschel");
                cs.setPreschentsing2(root + "eschas");
                cs.setPreschentsing3(root + "escha");
                cs.setPreschentplural1(root + "ein");
                cs.setPreschentplural2(root + "eis");
                cs.setPreschentplural3(root + "eschan");
                break;

            case "2":
            case "3":
                cs.setPreschentsing1(root + "el");
                cs.setPreschentsing2(root + "as");
                cs.setPreschentsing3(root + "a");

                cs.setPreschentplural1(root + "ein\n" + root + "in");
                cs.setPreschentplural2(root + "eis\n" + root + "is");
                cs.setPreschentplural3(root + "an");
                break;
            case "3a":
            case "3c":
            case "3d":
            case "3e":
            case "3f":
                cs.setPreschentsing1(root + "el");
                cs.setPreschentsing2(root + "as");
                cs.setPreschentsing3(root + "a");

                cs.setPreschentplural1(modRoot + "ein\n" + modRoot + "in");
                cs.setPreschentplural2(modRoot + "eis\n" + modRoot + "is");
                cs.setPreschentplural3(root + "an");
                break;
            case "3b":
                cs.setPreschentsing1(root + "el");
                cs.setPreschentsing2(root + "as");
                cs.setPreschentsing3(root + "a");

                cs.setPreschentplural1(modRoot + "in");
                cs.setPreschentplural2(modRoot + "is");
                cs.setPreschentplural3(root + "an");
                break;

            case "2a":
                cs.setPreschentsing1(modRoot + "el");
                cs.setPreschentsing2(modRoot + "as");
                cs.setPreschentsing3(modRoot + "a");

                cs.setPreschentplural1(root + "in");
                cs.setPreschentplural2(root + "is");
                cs.setPreschentplural3(modRoot + "an");
                break;

            case "4":
                cs.setPreschentsing1(root + "el");
                cs.setPreschentsing2(root + "as");
                cs.setPreschentsing3(root + "a");

                cs.setPreschentplural1(root + "in");
                cs.setPreschentplural2(root + "is");
                cs.setPreschentplural3(root + "an");
                break;
            case "4a":
            case "4b":
            case "4c":
            case "4d":
            case "4e":
            case "4f":
                if (modRoot.endsWith("i")) {
                    cs.setPreschentsing1(modRoot.substring(0, modRoot.length() - 1) + "el");
                } else {
                    cs.setPreschentsing1(modRoot + "el");
                }
                cs.setPreschentsing2(modRoot + "as");
                cs.setPreschentsing3(modRoot + "a");

                cs.setPreschentplural1(root + "in");
                cs.setPreschentplural2(root + "is");
                cs.setPreschentplural3(modRoot + "an");
                break;

            case "5":
                cs.setPreschentsing1(root + "eschel");
                cs.setPreschentsing2(root + "eschas");
                cs.setPreschentsing3(root + "escha");
                cs.setPreschentplural1(root + "in");
                cs.setPreschentplural2(root + "is");
                cs.setPreschentplural3(root + "eschan");
                break;
        }
    }

    private void setImperfect(SursilvanConjugationStructure cs) {
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
            case "1v":
            case "1w":
            case "6":
                cs.setImperfectsing1(root + "avel");
                cs.setImperfectsing2(root + "avas");
                cs.setImperfectsing3(root + "ava");

                cs.setImperfectplural1(root + "avan");
                cs.setImperfectplural2(root + "avas");
                cs.setImperfectplural3(root + "avan");
                break;

            case "2":
            case "2a":
            case "3":
            case "4":
            case "4a":
            case "4b":
            case "4c":
            case "4d":
            case "4e":
            case "4f":
            case "5":
                cs.setImperfectsing1(root + "evel");
                cs.setImperfectsing2(root + "evas");
                cs.setImperfectsing3(root + "eva");

                cs.setImperfectplural1(root + "evan");
                cs.setImperfectplural2(root + "evas");
                cs.setImperfectplural3(root + "evan");
                break;

            case "3a":
            case "3b":
            case "3c":
            case "3d":
            case "3e":
            case "3f":
                cs.setImperfectsing1(modRoot + "evel");
                cs.setImperfectsing2(modRoot + "evas");
                cs.setImperfectsing3(modRoot + "eva");

                cs.setImperfectplural1(modRoot + "evan");
                cs.setImperfectplural2(modRoot + "evas");
                cs.setImperfectplural3(modRoot + "evan");
                break;
        }
    }

    private void setConjunctiv(SursilvanConjugationStructure cs) {
        switch (cs.getConjugationclass().id) {
            case "1":
                cs.setConjunctivsing1(root + "i");
                cs.setConjunctivsing2(root + "ies");
                cs.setConjunctivsing3(root + "i");

                cs.setConjunctivplural1(root + "ien\n" + root + "eien");
                cs.setConjunctivplural2(root + "ies\n" + root + "eies");
                cs.setConjunctivplural3(root + "ien");
                break;
            case "1a":
            case "1b":
            case "1c":
            case "1d":
            case "1e":
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
            case "1t":
            case "1u":
            case "1v":
            case "1w":
                if (modRoot.endsWith("i")) {
                    cs.setConjunctivsing1(modRoot);
                    cs.setConjunctivsing2(modRoot + "es");
                    cs.setConjunctivsing3(modRoot);

                    cs.setConjunctivplural1(modRoot + "en\n" + root + "eien");
                    cs.setConjunctivplural2(modRoot + "es\n" + root + "eies");
                    cs.setConjunctivplural3(modRoot + "en");
                } else {
                    cs.setConjunctivsing1(modRoot + "i");
                    cs.setConjunctivsing2(modRoot + "ies");
                    cs.setConjunctivsing3(modRoot + "i");

                    cs.setConjunctivplural1(modRoot + "ien\n" + root + "eien");
                    cs.setConjunctivplural2(modRoot + "ies\n" + root + "eies");
                    cs.setConjunctivplural3(modRoot + "ien");
                }
                break;
            case "1f":
                cs.setConjunctivsing1(modRoot + "i\n" + root + "i");
                cs.setConjunctivsing2(modRoot + "ies\n" + root + "ies");
                cs.setConjunctivsing3(modRoot + "i\n" + root + "i");

                cs.setConjunctivplural1(modRoot + "ien\n" + root + "ien\n" + root + "eien");
                cs.setConjunctivplural2(modRoot + "ies\n" + root + "ies\n" + root + "eies");
                cs.setConjunctivplural3(modRoot + "ien\n" + root + "ien");
                break;
            case "1r":
            case "1s":
                cs.setConjunctivsing1(modRoot + "i\n" + modRootAlternative + "i");
                cs.setConjunctivsing2(modRoot + "ies\n" + modRootAlternative + "ies");
                cs.setConjunctivsing3(modRoot + "i\n" + modRootAlternative + "i");

                cs.setConjunctivplural1(modRoot + "ien\n" + modRootAlternative + "ien\n" + root + "eien");
                cs.setConjunctivplural2(modRoot + "ies\n" + modRootAlternative + "ies\n" + root + "eies");
                cs.setConjunctivplural3(modRoot + "ien\n" + modRootAlternative + "ien");
                break;

            case "6":
                cs.setConjunctivsing1(root + "eschi");
                cs.setConjunctivsing2(root + "eschies");
                cs.setConjunctivsing3(root + "eschi");

                cs.setConjunctivplural1(root + "eschien\n" + root + "eien");
                cs.setConjunctivplural2(root + "eschies\n" + root + "eies");
                cs.setConjunctivplural3(root + "eschien");
                break;

            case "2":
            case "3":
                cs.setConjunctivsing1(root + "i");
                cs.setConjunctivsing2(root + "ies");
                cs.setConjunctivsing3(root + "i");

                cs.setConjunctivplural1(root + "ien\n" + root + "eien\n" + root + "îen");
                cs.setConjunctivplural2(root + "ies\n" + root + "eies\n" + root + "îes");
                cs.setConjunctivplural3(root + "ien");
                break;
            case "3a":
            case "3c":
            case "3d":
            case "3e":
            case "3f":
                cs.setConjunctivsing1(root + "i");
                cs.setConjunctivsing2(root + "ies");
                cs.setConjunctivsing3(root + "i");

                cs.setConjunctivplural1(root + "ien\n" + modRoot + "eien\n" + modRoot + "îen");
                cs.setConjunctivplural2(root + "ies\n" + modRoot + "eies\n" + modRoot + "îes");
                cs.setConjunctivplural3(root + "ien");
                break;
            case "3b":
                cs.setConjunctivsing1(root + "i");
                cs.setConjunctivsing2(root + "ies");
                cs.setConjunctivsing3(root + "i");

                cs.setConjunctivplural1(root + "ien\n" + modRoot + "îen");
                cs.setConjunctivplural2(root + "ies\n" + modRoot + "îes");
                cs.setConjunctivplural3(root + "ien");
                break;

            case "2a":
                cs.setConjunctivsing1(modRoot + "i");
                cs.setConjunctivsing2(modRoot + "ies");
                cs.setConjunctivsing3(modRoot + "i");

                cs.setConjunctivplural1(modRoot + "ien\n" + root + "îen");
                cs.setConjunctivplural2(modRoot + "ies\n" + root + "îes");
                cs.setConjunctivplural3(modRoot + "ien");
                break;

            case "4":
                cs.setConjunctivsing1(root + "i");
                cs.setConjunctivsing2(root + "ies");
                cs.setConjunctivsing3(root + "i");

                cs.setConjunctivplural1(root + "îen");
                cs.setConjunctivplural2(root + "îes");
                cs.setConjunctivplural3(root + "ien");
                break;

            case "4a":
            case "4b":
            case "4c":
            case "4d":
            case "4e":
            case "4f":
                if (modRoot.endsWith("i")) {
                    cs.setConjunctivsing1(modRoot);
                    cs.setConjunctivsing2(modRoot + "es");
                    cs.setConjunctivsing3(modRoot);

                    cs.setConjunctivplural1(modRoot + "en\n" + root + "îen");
                    cs.setConjunctivplural2(modRoot + "es\n" + root + "îes");
                    cs.setConjunctivplural3(modRoot + "en");
                } else {
                    cs.setConjunctivsing1(modRoot + "i");
                    cs.setConjunctivsing2(modRoot + "ies");
                    cs.setConjunctivsing3(modRoot + "i");

                    cs.setConjunctivplural1(modRoot + "ien\n" + root + "îen");
                    cs.setConjunctivplural2(modRoot + "ies\n" + root + "îes");
                    cs.setConjunctivplural3(modRoot + "ien");
                }

                break;

            case "5":
                cs.setConjunctivsing1(root + "eschi");
                cs.setConjunctivsing2(root + "eschies");
                cs.setConjunctivsing3(root + "eschi");

                cs.setConjunctivplural1(root + "eschien\n" + root + "îen");
                cs.setConjunctivplural2(root + "eschies\n" + root + "îes");
                cs.setConjunctivplural3(root + "eschien");
                break;
        }
    }

    private void setConjunctivImperfect(SursilvanConjugationStructure cs) {
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
            case "1v":
            case "1w":
            case "6":
                cs.setConjunctivimperfectsing1(root + "avi");
                cs.setConjunctivimperfectsing2(root + "avies");
                cs.setConjunctivimperfectsing3(root + "avi");

                cs.setConjunctivimperfectplural1(root + "avien");
                cs.setConjunctivimperfectplural2(root + "avies");
                cs.setConjunctivimperfectplural3(root + "avien");
                break;

            case "2":
            case "2a":
            case "3":
            case "4":
            case "4a":
            case "4b":
            case "4c":
            case "4d":
            case "4e":
            case "4f":
            case "5":
                cs.setConjunctivimperfectsing1(root + "evi");
                cs.setConjunctivimperfectsing2(root + "evies");
                cs.setConjunctivimperfectsing3(root + "evi");

                cs.setConjunctivimperfectplural1(root + "evien");
                cs.setConjunctivimperfectplural2(root + "evies");
                cs.setConjunctivimperfectplural3(root + "evien");
                break;

            case "3a":
            case "3b":
            case "3c":
            case "3d":
            case "3e":
            case "3f":
                cs.setConjunctivimperfectsing1(modRoot + "evi");
                cs.setConjunctivimperfectsing2(modRoot + "evies");
                cs.setConjunctivimperfectsing3(modRoot + "evi");

                cs.setConjunctivimperfectplural1(modRoot + "evien");
                cs.setConjunctivimperfectplural2(modRoot + "evies");
                cs.setConjunctivimperfectplural3(modRoot + "evien");
                break;
        }
    }

    private void setCundizional(SursilvanConjugationStructure cs) {
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
            case "1v":
            case "1w":
            case "6":
                cs.setCundizionalsing1(root + "ass");
                cs.setCundizionalsing2(root + "asses");
                cs.setCundizionalsing3(root + "ass");

                cs.setCundizionalplural1(root + "assen");
                cs.setCundizionalplural2(root + "asses");
                cs.setCundizionalplural3(root + "assen");
                break;

            case "2":
            case "2a":
            case "3":
            case "4":
            case "4a":
            case "4b":
            case "4c":
            case "4d":
            case "4e":
            case "4f":
            case "5":
                cs.setCundizionalsing1(root + "ess");
                cs.setCundizionalsing2(root + "esses");
                cs.setCundizionalsing3(root + "ess");

                cs.setCundizionalplural1(root + "essen");
                cs.setCundizionalplural2(root + "esses");
                cs.setCundizionalplural3(root + "essen");
                break;

            case "3a":
            case "3b":
            case "3c":
            case "3d":
            case "3e":
            case "3f":
                cs.setCundizionalsing1(modRoot + "ess");
                cs.setCundizionalsing2(modRoot + "esses");
                cs.setCundizionalsing3(modRoot + "ess");

                cs.setCundizionalplural1(modRoot + "essen");
                cs.setCundizionalplural2(modRoot + "esses");
                cs.setCundizionalplural3(modRoot + "essen");
                break;
        }
    }

    private void setCundizionalIndirect(SursilvanConjugationStructure cs) {
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
            case "1v":
            case "1w":
            case "6":
                cs.setCundizionalindirectsing1(root + "assi");
                cs.setCundizionalindirectsing2(root + "assies");
                cs.setCundizionalindirectsing3(root + "assi");

                cs.setCundizionalindirectplural1(root + "assien");
                cs.setCundizionalindirectplural2(root + "assies");
                cs.setCundizionalindirectplural3(root + "assien");
                break;


            case "2":
            case "2a":
            case "3":
            case "4":
            case "4a":
            case "4b":
            case "4c":
            case "4d":
            case "4e":
            case "4f":
            case "5":
                cs.setCundizionalindirectsing1(root + "essi");
                cs.setCundizionalindirectsing2(root + "essies");
                cs.setCundizionalindirectsing3(root + "essi");

                cs.setCundizionalindirectplural1(root + "essien");
                cs.setCundizionalindirectplural2(root + "essies");
                cs.setCundizionalindirectplural3(root + "essien");
                break;

            case "3a":
            case "3b":
            case "3c":
            case "3d":
            case "3e":
            case "3f":
                cs.setCundizionalindirectsing1(modRoot + "essi");
                cs.setCundizionalindirectsing2(modRoot + "essies");
                cs.setCundizionalindirectsing3(modRoot + "essi");

                cs.setCundizionalindirectplural1(modRoot + "essien");
                cs.setCundizionalindirectplural2(modRoot + "essies");
                cs.setCundizionalindirectplural3(modRoot + "essien");
                break;
        }
    }

    private void setParticipPerfect(SursilvanConjugationStructure cs) {
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
            case "1v":
            case "1w":
            case "6":
                cs.setParticipperfectms(root + "au");
                cs.setParticipperfectfs(root + "ada");
                cs.setParticipperfectmp(root + "ai");
                cs.setParticipperfectfp(root + "adas");
                break;

            case "2":
            case "2a":
            case "3":
            case "4":
            case "4a":
            case "4b":
            case "4c":
            case "4d":
            case "4e":
            case "4f":
            case "5":
                cs.setParticipperfectms(root + "iu");
                cs.setParticipperfectfs(root + "ida");
                cs.setParticipperfectmp(root + "i");
                cs.setParticipperfectfp(root + "idas");
                break;

            case "3a":
                cs.setParticipperfectms(modRootAlternative + "t\n" + modRoot + "iu");
                cs.setParticipperfectfs(modRootAlternative + "ta\n" + modRoot + "ida");
                cs.setParticipperfectmp(modRootAlternative + "ts\n" + modRoot + "i");
                cs.setParticipperfectfp(modRootAlternative + "tas\n" + modRoot + "idas");
                break;

            case "3b":
            case "3c":
            case "3d":
            case "3e":
            case "3f":
                cs.setParticipperfectms(modRoot + "iu");
                cs.setParticipperfectfs(modRoot + "ida");
                cs.setParticipperfectmp(modRoot + "i");
                cs.setParticipperfectfp(modRoot + "idas");
                break;
        }

    }

    private void setGerundium(SursilvanConjugationStructure cs) {
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
            case "1v":
            case "1w":
            case "6":
                cs.setGerundium(root + "ond");
                break;

            case "2":
            case "2a":
            case "3":
            case "4":
            case "4a":
            case "4b":
            case "4c":
            case "4d":
            case "4e":
            case "4f":
            case "5":
                cs.setGerundium(root + "end");
                break;

            case "3a":
            case "3b":
            case "3c":
            case "3d":
            case "3e":
            case "3f":
                cs.setGerundium(modRoot + "end");
                break;
        }
    }

    private void setImperativ(SursilvanConjugationStructure cs) {
        switch (cs.getConjugationclass().id) {
            case "1":
                cs.setImperativ1(root + "a!");
                cs.setImperativ2(root + "ei!");
                break;

            case "1a":
            case "1b":
            case "1c":
            case "1d":
            case "1e":
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
            case "1t":
            case "1u":
            case "1v":
            case "1w":
                cs.setImperativ1(modRoot + "a!");
                cs.setImperativ2(root + "ei!");
                break;
            case "1f":
                cs.setImperativ1(modRoot + "a!\n" + root + "a!");
                cs.setImperativ2(root + "ei!");
                break;
            case "1r":
            case "1s":
                cs.setImperativ1(modRoot + "a!\n" + modRootAlternative + "a!");
                cs.setImperativ2(root + "ei!");
                break;

            case "6":
                cs.setImperativ1(root + "escha!");
                cs.setImperativ2(root + "ei!");
                break;

            case "2":
            case "3":
                cs.setImperativ1(root + "a!");
                cs.setImperativ2(root + "ei!\n" + root + "i!");
                break;
            case "2a":
                cs.setImperativ1(modRoot + "a!");
                cs.setImperativ2(root + "i!");
                break;
            case "3a":
            case "3c":
            case "3d":
            case "3f":
                cs.setImperativ1(root + "a!");
                cs.setImperativ2(modRoot + "ei!\n" + modRoot + "i!");
                break;
            case "3b":
            case "3e":
                cs.setImperativ1(root + "a!");
                cs.setImperativ2(modRoot + "i!");
                break;

            case "4":
                cs.setImperativ1(root + "a!");
                cs.setImperativ2(root + "i!");
                break;
            case "4a":
            case "4c":
            case "4d":
            case "4e":
            case "4f":
                cs.setImperativ1(modRoot + "a!");
                cs.setImperativ2(root + "i!");
                break;
            case "4b":
                cs.setImperativ1(modRootAlternative + "a!\n" + modRoot + "a!");
                cs.setImperativ2(root + "i!");
                break;

            case "5":
                cs.setImperativ1(root + "escha!");
                cs.setImperativ2(root + "i!");
                break;
        }
    }

    private void setFutur(SursilvanConjugationStructure cs) {
        char startChar = root.charAt(0);
        switch (getIsReflexive()) {
            case "true":
                if (isVocal(startChar)) {
                    cs.setFutursing1("vegn(el) a " + SursilvanPronouns.pron_r_v + cs.getInfinitiv());
                    cs.setFutursing2("vegns a " + SursilvanPronouns.pron_r_v + cs.getInfinitiv());
                    cs.setFutursing3("vegn a " + SursilvanPronouns.pron_r_v + cs.getInfinitiv());

                    cs.setFuturplural1("vegnin ad " + SursilvanPronouns.pron_r_v + cs.getInfinitiv());
                    cs.setFuturplural2("vegnis ad " + SursilvanPronouns.pron_r_v + cs.getInfinitiv());
                    cs.setFuturplural3("vegnan a " + SursilvanPronouns.pron_r_v + cs.getInfinitiv());

                } else {
                    cs.setFutursing1("vegn(el) a " + SursilvanPronouns.pron_r + cs.getInfinitiv());
                    cs.setFutursing2("vegns a " + SursilvanPronouns.pron_r + cs.getInfinitiv());
                    cs.setFutursing3("vegn a " + SursilvanPronouns.pron_r + cs.getInfinitiv());

                    cs.setFuturplural1("vegnin ad " + SursilvanPronouns.pron_r + cs.getInfinitiv());
                    cs.setFuturplural2("vegnis ad " + SursilvanPronouns.pron_r + cs.getInfinitiv());
                    cs.setFuturplural3("vegnan a " + SursilvanPronouns.pron_r + cs.getInfinitiv());

                }
                break;

            case "false":
                if (isVocal(startChar)) {
                    cs.setFutursing1("vegn(el) ad " + cs.getInfinitiv());
                    cs.setFutursing2("vegns ad " + cs.getInfinitiv());
                    cs.setFutursing3("vegn ad " + cs.getInfinitiv());
                    cs.setFuturplural1("vegnin ad " + cs.getInfinitiv());
                    cs.setFuturplural2("vegnis ad " + cs.getInfinitiv());
                    cs.setFuturplural3("vegnan ad " + cs.getInfinitiv());

                } else {
                    cs.setFutursing1("vegn(el) a " + cs.getInfinitiv());
                    cs.setFutursing2("vegns a " + cs.getInfinitiv());
                    cs.setFutursing3("vegn a " + cs.getInfinitiv());
                    cs.setFuturplural1("vegnin a " + cs.getInfinitiv());
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
        SursilvanPronouns pronouns = new SursilvanPronouns();
        // STANDARD
        pronouns.setFirstPs(SursilvanPronouns.pron_1ps + SursilvanPronouns.pron_r);
        pronouns.setSecondPs(SursilvanPronouns.pron_2ps + SursilvanPronouns.pron_r);
        pronouns.setThirdPs(SursilvanPronouns.pron_3ps + SursilvanPronouns.pron_r);
        pronouns.setFirstPp(SursilvanPronouns.pron_1pp + SursilvanPronouns.pron_r);
        pronouns.setSecondPp(SursilvanPronouns.pron_2pp + SursilvanPronouns.pron_r);
        pronouns.setThirdPp(SursilvanPronouns.pron_3pp + SursilvanPronouns.pron_r);

        // CONJUNCTIV
        pronouns.setFirstPsC(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_1ps + SursilvanPronouns.pron_r);
        pronouns.setSecondPsC(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_2ps + SursilvanPronouns.pron_r);
        pronouns.setThirdPsC(SursilvanPronouns.pron_conjunctiv_v + SursilvanPronouns.pron_3ps + SursilvanPronouns.pron_r);
        pronouns.setFirstPpC(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_1pp + SursilvanPronouns.pron_r);
        pronouns.setSecondPpC(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_2pp + SursilvanPronouns.pron_r);
        pronouns.setThirdPpC(SursilvanPronouns.pron_conjunctiv_v + SursilvanPronouns.pron_3pp + SursilvanPronouns.pron_r);

        // PARTICIP PERFECT
        pronouns.setPp_1(SursilvanPronouns.pp_r1 + " " + SursilvanPronouns.pron_r);
        pronouns.setPp_2(SursilvanPronouns.pp_r2 + " " + SursilvanPronouns.pron_r);

        // IMPERATIV
        pronouns.setImperat1(SursilvanPronouns.pron_r);
        pronouns.setImperat2(SursilvanPronouns.pron_r);

        // GERUNDIUM
        pronouns.setGer(SursilvanPronouns.pron_r);

        return pronouns.getValues();
    }

    private Map<String, String> pronounsForReflexiveVocalicVerbs() {
        SursilvanPronouns pronouns = new SursilvanPronouns();
        // STANDARD
        pronouns.setFirstPs(SursilvanPronouns.pron_1ps + SursilvanPronouns.pron_r_v);
        pronouns.setSecondPs(SursilvanPronouns.pron_2ps + SursilvanPronouns.pron_r_v);
        pronouns.setThirdPs(SursilvanPronouns.pron_3ps + SursilvanPronouns.pron_r_v);
        pronouns.setFirstPp(SursilvanPronouns.pron_1pp + SursilvanPronouns.pron_r_v);
        pronouns.setSecondPp(SursilvanPronouns.pron_2pp + SursilvanPronouns.pron_r_v);
        pronouns.setThirdPp(SursilvanPronouns.pron_3pp + SursilvanPronouns.pron_r_v);

        // CONJUNCTIV
        pronouns.setFirstPsC(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_1ps + SursilvanPronouns.pron_r_v);
        pronouns.setSecondPsC(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_2ps + SursilvanPronouns.pron_r_v);
        pronouns.setThirdPsC(SursilvanPronouns.pron_conjunctiv_v + SursilvanPronouns.pron_3ps + SursilvanPronouns.pron_r_v);
        pronouns.setFirstPpC(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_1pp + SursilvanPronouns.pron_r_v);
        pronouns.setSecondPpC(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_2pp + SursilvanPronouns.pron_r_v);
        pronouns.setThirdPpC(SursilvanPronouns.pron_conjunctiv_v + SursilvanPronouns.pron_3pp + SursilvanPronouns.pron_r_v);

        // PARTICIP PERFECT
        pronouns.setPp_1(SursilvanPronouns.pp_r1 + " " + SursilvanPronouns.pron_r_v);
        pronouns.setPp_2(SursilvanPronouns.pp_r2 + " " + SursilvanPronouns.pron_r_v);

        // IMPERATIV
        pronouns.setImperat1(SursilvanPronouns.pron_r_v);
        pronouns.setImperat2(SursilvanPronouns.pron_r_v);

        // GERUNDIUM
        pronouns.setGer(SursilvanPronouns.pron_r_v);

        return pronouns.getValues();
    }

    private HashMap<String, String> addReflexivePronouns(Map<String, String> conjugation, Map<String, String> pronouns, InflectionSubType subType) {
        cs = new SursilvanConjugationStructure();
        cs.setVerb(conjugation.get("verb"));
        cs.setInfinitiv(conjugation.get(SursilvanConjugationStructure.infinitiv));
        cs.setRoot(conjugation.get(SursilvanConjugationStructure.root));
        cs.setEnding(conjugation.get(SursilvanConjugationStructure.ending));
        cs.setReflexive(conjugation.get(SursilvanConjugationStructure.reflexive));
        cs.setConjugationClass(subType);

        // PRESCHENT
        cs.setPreschentsing1(pronouns.get(SursilvanPronouns.first_ps) + conjugation.get(SursilvanConjugationStructure.preschentsing1));
        cs.setPreschentsing2(pronouns.get(SursilvanPronouns.second_ps) + conjugation.get(SursilvanConjugationStructure.preschentsing2));
        cs.setPreschentsing3(pronouns.get(SursilvanPronouns.third_ps) + conjugation.get(SursilvanConjugationStructure.preschentsing3));
        cs.setPreschentplural1(pronouns.get(SursilvanPronouns.first_pp) + conjugation.get(SursilvanConjugationStructure.preschentplural1));
        cs.setPreschentplural2(pronouns.get(SursilvanPronouns.second_pp) + conjugation.get(SursilvanConjugationStructure.preschentplural2));
        cs.setPreschentplural3(pronouns.get(SursilvanPronouns.third_pp) + conjugation.get(SursilvanConjugationStructure.preschentplural3));

        // IMPERFECT
        cs.setImperfectsing1(pronouns.get(SursilvanPronouns.first_ps) + conjugation.get(SursilvanConjugationStructure.imperfectsing1));
        cs.setImperfectsing2(pronouns.get(SursilvanPronouns.second_ps) + conjugation.get(SursilvanConjugationStructure.imperfectsing2));
        cs.setImperfectsing3(pronouns.get(SursilvanPronouns.third_ps) + conjugation.get(SursilvanConjugationStructure.imperfectsing3));
        cs.setImperfectplural1(pronouns.get(SursilvanPronouns.first_pp) + conjugation.get(SursilvanConjugationStructure.imperfectplural1));
        cs.setImperfectplural2(pronouns.get(SursilvanPronouns.second_pp) + conjugation.get(SursilvanConjugationStructure.imperfectplural2));
        cs.setImperfectplural3(pronouns.get(SursilvanPronouns.third_pp) + conjugation.get(SursilvanConjugationStructure.imperfectplural3));

        // CONJUNCTIV
        cs.setConjunctivsing1(pronouns.get(SursilvanPronouns.first_ps_c) + conjugation.get(SursilvanConjugationStructure.conjunctivsing1));
        cs.setConjunctivsing2(pronouns.get(SursilvanPronouns.second_ps_c) + conjugation.get(SursilvanConjugationStructure.conjunctivsing2));
        cs.setConjunctivsing3(pronouns.get(SursilvanPronouns.third_ps_c) + conjugation.get(SursilvanConjugationStructure.conjunctivsing3));
        cs.setConjunctivplural1(pronouns.get(SursilvanPronouns.first_pp_c) + conjugation.get(SursilvanConjugationStructure.conjunctivplural1));
        cs.setConjunctivplural2(pronouns.get(SursilvanPronouns.second_pp_c) + conjugation.get(SursilvanConjugationStructure.conjunctivplural2));
        cs.setConjunctivplural3(pronouns.get(SursilvanPronouns.third_pp_c) + conjugation.get(SursilvanConjugationStructure.conjunctivplural3));

        // CONJUNCTIV IMPERFECT
        cs.setConjunctivimperfectsing1(setPronoun(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_1ps, conjugation.get(SursilvanConjugationStructure.conjunctivimperfectsing1)));
        cs.setConjunctivimperfectsing2(setPronoun(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_2ps, conjugation.get(SursilvanConjugationStructure.conjunctivimperfectsing2)));
        cs.setConjunctivimperfectsing3(setPronoun(SursilvanPronouns.pron_conjunctiv_v + SursilvanPronouns.pron_3ps, conjugation.get(SursilvanConjugationStructure.conjunctivimperfectsing3)));
        cs.setConjunctivimperfectplural1(setPronoun(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_1pp, conjugation.get(SursilvanConjugationStructure.conjunctivimperfectplural1)));
        cs.setConjunctivimperfectplural2(setPronoun(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_2pp, conjugation.get(SursilvanConjugationStructure.conjunctivimperfectplural2)));
        cs.setConjunctivimperfectplural3(setPronoun(SursilvanPronouns.pron_conjunctiv_v + SursilvanPronouns.pron_3pp, conjugation.get(SursilvanConjugationStructure.conjunctivimperfectplural3)));

        // CUNDIZIONAL
        cs.setCundizionalsing1(pronouns.get(SursilvanPronouns.first_ps) + conjugation.get(SursilvanConjugationStructure.cundizionalsing1));
        cs.setCundizionalsing2(pronouns.get(SursilvanPronouns.second_ps) + conjugation.get(SursilvanConjugationStructure.cundizionalsing2));
        cs.setCundizionalsing3(pronouns.get(SursilvanPronouns.third_ps) + conjugation.get(SursilvanConjugationStructure.cundizionalsing3));
        cs.setCundizionalplural1(pronouns.get(SursilvanPronouns.first_pp) + conjugation.get(SursilvanConjugationStructure.cundizionalplural1));
        cs.setCundizionalplural2(pronouns.get(SursilvanPronouns.second_pp) + conjugation.get(SursilvanConjugationStructure.cundizionalplural2));
        cs.setCundizionalplural3(pronouns.get(SursilvanPronouns.third_pp) + conjugation.get(SursilvanConjugationStructure.cundizionalplural3));

        // CUNDIZIONAL INDIRECT
        cs.setCundizionalindirectsing1(setPronoun(SursilvanPronouns.pron_1ps, conjugation.get(SursilvanConjugationStructure.cundizionalindirectsing1)));
        cs.setCundizionalindirectsing2(setPronoun(SursilvanPronouns.pron_2ps, conjugation.get(SursilvanConjugationStructure.cundizionalindirectsing2)));
        cs.setCundizionalindirectsing3(setPronoun(SursilvanPronouns.pron_3ps, conjugation.get(SursilvanConjugationStructure.cundizionalindirectsing3)));
        cs.setCundizionalindirectplural1(setPronoun(SursilvanPronouns.pron_1pp, conjugation.get(SursilvanConjugationStructure.cundizionalindirectplural1)));
        cs.setCundizionalindirectplural2(setPronoun(SursilvanPronouns.pron_2pp, conjugation.get(SursilvanConjugationStructure.cundizionalindirectplural2)));
        cs.setCundizionalindirectplural3(setPronoun(SursilvanPronouns.pron_3pp, conjugation.get(SursilvanConjugationStructure.cundizionalindirectplural3)));

        // IMPERATIV
        cs.setImperativ1(pronouns.get(SursilvanPronouns.imperat_1) + conjugation.get(SursilvanConjugationStructure.imperativ1));
        cs.setImperativ2(pronouns.get(SursilvanPronouns.imperat_2) + conjugation.get(SursilvanConjugationStructure.imperativ2));

        // PARTICIP_PERFECT
        cs.setParticipperfectms(pronouns.get(SursilvanPronouns.pp_1) + conjugation.get(SursilvanConjugationStructure.participperfectms));
        cs.setParticipperfectfs(pronouns.get(SursilvanPronouns.pp_2) + conjugation.get(SursilvanConjugationStructure.participperfectms) + "/" + conjugation.get(SursilvanConjugationStructure.participperfectfs));

        // GERUNDIUM
        cs.setGerundium(pronouns.get(SursilvanPronouns.gerund) + conjugation.get(SursilvanConjugationStructure.gerundium));

        // FUTUR
        cs.setFutursing1(SursilvanPronouns.pron_1ps + conjugation.get(SursilvanConjugationStructure.futursing1));
        cs.setFutursing2(SursilvanPronouns.pron_2ps + conjugation.get(SursilvanConjugationStructure.futursing2));
        cs.setFutursing3(SursilvanPronouns.pron_3ps + conjugation.get(SursilvanConjugationStructure.futursing3));
        cs.setFuturplural1(SursilvanPronouns.pron_1pp + conjugation.get(SursilvanConjugationStructure.futurplural1));
        cs.setFuturplural2(SursilvanPronouns.pron_2pp + conjugation.get(SursilvanConjugationStructure.futurplural2));
        cs.setFuturplural3(SursilvanPronouns.pron_3pp + conjugation.get(SursilvanConjugationStructure.futurplural3));

        return cs.getAllFormValues();
    }

    private HashMap<String, String> addStandardPronouns(Map<String, String> conjugation, InflectionSubType subType) {

        SursilvanConjugationStructure cs = new SursilvanConjugationStructure();

        cs.setVerb(conjugation.get("verb"));
        cs.setInfinitiv(conjugation.get(SursilvanConjugationStructure.infinitiv));
        cs.setRoot(conjugation.get(SursilvanConjugationStructure.root));
        cs.setEnding(conjugation.get(SursilvanConjugationStructure.ending));
        cs.setReflexive(conjugation.get(SursilvanConjugationStructure.reflexive));
        cs.setConjugationClass(subType);

        // PRESCHENT
        cs.setPreschentsing1(setPronoun(SursilvanPronouns.pron_1ps, conjugation.get(SursilvanConjugationStructure.preschentsing1)));
        cs.setPreschentsing2(setPronoun(SursilvanPronouns.pron_2ps, conjugation.get(SursilvanConjugationStructure.preschentsing2)));
        cs.setPreschentsing3(setPronoun(SursilvanPronouns.pron_3ps, conjugation.get(SursilvanConjugationStructure.preschentsing3)));
        cs.setPreschentplural1(setPronoun(SursilvanPronouns.pron_1pp, conjugation.get(SursilvanConjugationStructure.preschentplural1)));
        cs.setPreschentplural2(setPronoun(SursilvanPronouns.pron_2pp, conjugation.get(SursilvanConjugationStructure.preschentplural2)));
        cs.setPreschentplural3(setPronoun(SursilvanPronouns.pron_3pp, conjugation.get(SursilvanConjugationStructure.preschentplural3)));

        // IMPERFECT
        cs.setImperfectsing1(SursilvanPronouns.pron_1ps + conjugation.get(SursilvanConjugationStructure.imperfectsing1));
        cs.setImperfectsing2(SursilvanPronouns.pron_2ps + conjugation.get(SursilvanConjugationStructure.imperfectsing2));
        cs.setImperfectsing3(SursilvanPronouns.pron_3ps + conjugation.get(SursilvanConjugationStructure.imperfectsing3));
        cs.setImperfectplural1(SursilvanPronouns.pron_1pp + conjugation.get(SursilvanConjugationStructure.imperfectplural1));
        cs.setImperfectplural2(SursilvanPronouns.pron_2pp + conjugation.get(SursilvanConjugationStructure.imperfectplural2));
        cs.setImperfectplural3(SursilvanPronouns.pron_3pp + conjugation.get(SursilvanConjugationStructure.imperfectplural3));

        // CONJUNCTIV
        cs.setConjunctivsing1(setPronoun(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_1ps, conjugation.get(SursilvanConjugationStructure.conjunctivsing1)));
        cs.setConjunctivsing2(setPronoun(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_2ps, conjugation.get(SursilvanConjugationStructure.conjunctivsing2)));
        cs.setConjunctivsing3(setPronoun(SursilvanPronouns.pron_conjunctiv_v + SursilvanPronouns.pron_3ps, conjugation.get(SursilvanConjugationStructure.conjunctivsing3)));
        cs.setConjunctivplural1(setPronoun(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_1pp, conjugation.get(SursilvanConjugationStructure.conjunctivplural1)));
        cs.setConjunctivplural2(setPronoun(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_2pp, conjugation.get(SursilvanConjugationStructure.conjunctivplural2)));
        cs.setConjunctivplural3(setPronoun(SursilvanPronouns.pron_conjunctiv_v + SursilvanPronouns.pron_3pp, conjugation.get(SursilvanConjugationStructure.conjunctivplural3)));

        // CONJUNCTIV IMPERFECT
        cs.setConjunctivimperfectsing1(setPronoun(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_1ps, conjugation.get(SursilvanConjugationStructure.conjunctivimperfectsing1)));
        cs.setConjunctivimperfectsing2(setPronoun(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_2ps, conjugation.get(SursilvanConjugationStructure.conjunctivimperfectsing2)));
        cs.setConjunctivimperfectsing3(setPronoun(SursilvanPronouns.pron_conjunctiv_v + SursilvanPronouns.pron_3ps, conjugation.get(SursilvanConjugationStructure.conjunctivimperfectsing3)));
        cs.setConjunctivimperfectplural1(setPronoun(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_1pp, conjugation.get(SursilvanConjugationStructure.conjunctivimperfectplural1)));
        cs.setConjunctivimperfectplural2(setPronoun(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_2pp, conjugation.get(SursilvanConjugationStructure.conjunctivimperfectplural2)));
        cs.setConjunctivimperfectplural3(setPronoun(SursilvanPronouns.pron_conjunctiv_v + SursilvanPronouns.pron_3pp, conjugation.get(SursilvanConjugationStructure.conjunctivimperfectplural3)));

        // CUNDIZIONAL
        cs.setCundizionalsing1(SursilvanPronouns.pron_1ps + conjugation.get(SursilvanConjugationStructure.cundizionalsing1));
        cs.setCundizionalsing2(SursilvanPronouns.pron_2ps + conjugation.get(SursilvanConjugationStructure.cundizionalsing2));
        cs.setCundizionalsing3(SursilvanPronouns.pron_3ps + conjugation.get(SursilvanConjugationStructure.cundizionalsing3));
        cs.setCundizionalplural1(SursilvanPronouns.pron_1pp + conjugation.get(SursilvanConjugationStructure.cundizionalplural1));
        cs.setCundizionalplural2(SursilvanPronouns.pron_2pp + conjugation.get(SursilvanConjugationStructure.cundizionalplural2));
        cs.setCundizionalplural3(SursilvanPronouns.pron_3pp + conjugation.get(SursilvanConjugationStructure.cundizionalplural3));

        // CUNDIZIONAL INDIRECT
        cs.setCundizionalindirectsing1(setPronoun(SursilvanPronouns.pron_1ps, conjugation.get(SursilvanConjugationStructure.cundizionalindirectsing1)));
        cs.setCundizionalindirectsing2(setPronoun(SursilvanPronouns.pron_2ps, conjugation.get(SursilvanConjugationStructure.cundizionalindirectsing2)));
        cs.setCundizionalindirectsing3(setPronoun(SursilvanPronouns.pron_3ps, conjugation.get(SursilvanConjugationStructure.cundizionalindirectsing3)));
        cs.setCundizionalindirectplural1(setPronoun(SursilvanPronouns.pron_1pp, conjugation.get(SursilvanConjugationStructure.cundizionalindirectplural1)));
        cs.setCundizionalindirectplural2(setPronoun(SursilvanPronouns.pron_2pp, conjugation.get(SursilvanConjugationStructure.cundizionalindirectplural2)));
        cs.setCundizionalindirectplural3(setPronoun(SursilvanPronouns.pron_3pp, conjugation.get(SursilvanConjugationStructure.cundizionalindirectplural3)));

        // IMPERATIV
        cs.setImperativ1(conjugation.get(SursilvanConjugationStructure.imperativ1));
        cs.setImperativ2(conjugation.get(SursilvanConjugationStructure.imperativ2));

        // PARTICIP_PERFECT
        cs.setParticipperfectms(conjugation.get(SursilvanConjugationStructure.participperfectms));
        cs.setParticipperfectfs(conjugation.get(SursilvanConjugationStructure.participperfectfs));
        cs.setParticipperfectmp(conjugation.get(SursilvanConjugationStructure.participperfectmp));
        cs.setParticipperfectfp(conjugation.get(SursilvanConjugationStructure.participperfectfp));

        // GERUNDIUM
        cs.setGerundium(conjugation.get(SursilvanConjugationStructure.gerundium));

        // FUTUR
        cs.setFutursing1(SursilvanPronouns.pron_1ps + conjugation.get(SursilvanConjugationStructure.futursing1));
        cs.setFutursing2(SursilvanPronouns.pron_2ps + conjugation.get(SursilvanConjugationStructure.futursing2));
        cs.setFutursing3(SursilvanPronouns.pron_3ps + conjugation.get(SursilvanConjugationStructure.futursing3));
        cs.setFuturplural1(SursilvanPronouns.pron_1pp + conjugation.get(SursilvanConjugationStructure.futurplural1));
        cs.setFuturplural2(SursilvanPronouns.pron_2pp + conjugation.get(SursilvanConjugationStructure.futurplural2));
        cs.setFuturplural3(SursilvanPronouns.pron_3pp + conjugation.get(SursilvanConjugationStructure.futurplural3));

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
        modRootAlternative = null;
    }

    public static boolean endsWithTwoIdenticalConsonants(String input) {
        if (input == null || input.length() < 2) {
            return false;
        }

        String lowerInput = input.toLowerCase();
        char lastChar = lowerInput.charAt(lowerInput.length() - 1);
        char secondLastChar = lowerInput.charAt(lowerInput.length() - 2);

        return isConsonant(lastChar) && lastChar == secondLastChar;
    }

    private static boolean isConsonant(char c) {
        return "bcdfghjklmnpqrstvwxyz".indexOf(c) >= 0;
    }

    private static boolean isStringInArray(String[] array, String input) {
        for (String str : array) {
            if (str.equals(input)) {
                return true;
            }
        }
        return false;
    }

    private static Object[] findLastVowelWithRAndIndex(String str) {
        Pattern pattern = Pattern.compile("[aeiouAEIOU]r");
        Matcher matcher = pattern.matcher(str);

        int lastIndex = -1;
        String lastVowel = null;

        while (matcher.find()) {
            lastIndex = matcher.start();
            lastVowel = matcher.group();
        }

        return new Object[]{lastIndex, lastVowel};
    }
}
