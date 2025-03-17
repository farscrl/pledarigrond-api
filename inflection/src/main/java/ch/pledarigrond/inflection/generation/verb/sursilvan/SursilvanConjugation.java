package ch.pledarigrond.inflection.generation.verb.sursilvan;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.common.data.dictionary.inflection.InflectionType;
import ch.pledarigrond.common.data.dictionary.inflection.VerbDto;
import ch.pledarigrond.inflection.generation.verb.LanguageConjugation;
import ch.pledarigrond.inflection.model.InflectionSubType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SursilvanConjugation extends LanguageConjugation {

    private static final Logger logger = LoggerFactory.getLogger(SursilvanConjugation.class);

    private InflectionDto inflection;
    private VerbDto verb;
    private InflectionSubType verbClass;

    private String infinitiv;
    private boolean isReflexive;

    private String root;
    private String modRoot;
    private String modRootAlternative;
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
        modRoot = null;
        modRootAlternative = null;
        ending = null;
    }

    @Override
    public InflectionDto generateConjugation(String conjugationClass, String infinitivForm) {
        reset();

        verb.setInfinitiv(infinitivForm);
        infinitiv = checkReflexiveness(infinitivForm);
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
            case "1x" -> {
                int lastU = root.lastIndexOf("u");
                int lastA = root.lastIndexOf("a");
                if (lastU == -1 || lastA == -1) break;
                modRoot = root.substring(0, lastU) + "a" + root.substring(lastU + 1, lastA) + "o" + root.substring(lastA + 1);
            }
            case "1y" -> {
                int lastU = root.lastIndexOf("u");
                int lastI = root.lastIndexOf("i");
                if (lastU == -1 || lastI == -1) break;
                modRoot = root.substring(0, lastU) + "a" + root.substring(lastU + 1, lastI) + "o" + root.substring(lastI + 1);
            }
            case "1z" -> {
                int lastU = root.lastIndexOf("u");
                int lastA = root.lastIndexOf("a");
                if (lastU == -1 || lastA == -1) break;
                modRoot = root.substring(0, lastU) + "a" + root.substring(lastU + 1, lastA) + "u" + root.substring(lastA + 1);
            }
            case "1aa" -> {
                int lastU = root.lastIndexOf("u");
                int lastI = root.lastIndexOf("i");
                if (lastU == -1 || lastI == -1) break;
                modRoot = root.substring(0, lastU) + "a" + root.substring(lastU + 1, lastI) + "u" + root.substring(lastI + 1);
            }
            case "1ab" -> {
                int lastU = root.lastIndexOf("u");
                int lastA = root.lastIndexOf("a");
                if (lastU == -1 || lastA == -1) break;
                modRoot = root.substring(0, lastU) + "i" + root.substring(lastU + 1, lastA) + "u" + root.substring(lastA + 1);
            }
            case "1ac" -> {
                int start = root.lastIndexOf("uvr");
                if (start != -1) {
                    modRoot = root.substring(0, start) + "rov" + root.substring(start + 3);
                } else {
                    start = root.lastIndexOf("ur");
                    if (start == -1) break;
                    modRoot = root.substring(0, start) + "ro" + root.substring(start + 2);
                }
            }
            case "1ad" -> {
                int start = root.lastIndexOf("er");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ra" + root.substring(start + 2);
            }
            case "1ae" -> {
                int start = root.lastIndexOf("er");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ro" + root.substring(start + 2);
            }
            case "1af" -> {
                int start = root.lastIndexOf("ar");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "re" + root.substring(start + 2);
            }
            case "1ag" -> {
                int start = root.lastIndexOf("er");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "re" + root.substring(start + 2);
            }
            case "1ah" -> {
                int start = root.lastIndexOf("ar");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ri" + root.substring(start + 2);
            }
            case "1ai" -> {
                Object[] result = findLastVowelWithRAndIndex(root);
                if ((int) result[0] != -1) {
                    int index = (int) result[0];
                    String vowel = (String) result[1];

                    modRoot = root.substring(0, index) + "r" + vowel.charAt(0) + root.substring(index + 2);
                }
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
        } else if (ending == null) {
            throw new RuntimeException(infinitivForm + " is not a valid verb. Please type a verb in its infinitive form.");
        }

        verbClass = subType;
        inflection.setInflectionSubtype(verbClass.id);
        conjugate();
        addPronouns();
        return inflection;
    }

    private String getRoot(String infinitivForm) {
        if (infinitivForm != null) {
            infinitivForm = normalizeString(infinitivForm);

            // exception: word 'ir' is too short for the default logic
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
        String[] verbsOnSeThatAreNotReflexive = {
                "sentir", "serrar", "semiar", "sellar", "segirar", "seduler", "segenar", "semegliar", "semtgar"
        };

        if (isStringInArray(verbsOnSeThatAreNotReflexive, infinitivForm)) {
            isReflexive = false;
            infinitiv = infinitivForm;
            return infinitivForm;
        }

        if (infinitivForm.startsWith("se") || infinitivForm.startsWith("s'") || infinitivForm.startsWith("s’")) {
            infinitivForm = infinitivForm.length() > 2 ? infinitivForm.substring(2) : infinitivForm;
            isReflexive = true;
        } else {
            isReflexive = false;
        }

        infinitiv = infinitivForm;
        return infinitivForm;
    }

    private String processQuery(String infinitivForm) {
        String l2 = infinitivForm.substring(infinitivForm.length() - 2);

        switch (l2) {
            case "ar":
            case "er":
            case "ir":
                ending = l2;
                infinitivForm = infinitivForm.substring(0, infinitivForm.length() - 2);
                return infinitivForm;

            default:
                return infinitivForm;
        }
    }

    private void conjugate() {
        verb.setReflexive(isReflexive);

        setPreschent();
        setImperfect();
        setConjunctiv();
        setConjunctiv2();
        setCundizional();
        setCundizionalIndirect();
        setParticipPerfect();
        setImperativ();
        setGerundium();
        setFutur();
    }

    private void setPreschent() {
        VerbDto.PersonalVerbDto preschent = new VerbDto.PersonalVerbDto();

        switch (verbClass.id) {
            case "1":
                preschent.setSing1(root + "el");
                preschent.setSing2(root + "as");
                preschent.setSing3(root + "a");

                preschent.setPlural1(root + "ein");
                preschent.setPlural2(root + "eis");
                preschent.setPlural3(root + "an");
                break;

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
            case "1t":
            case "1u":
            case "1v":
            case "1w":
            case "1x":
            case "1y":
            case "1z":
            case "1aa":
            case "1ab":
            case "1ac":
            case "1ad":
            case "1ae":
            case "1af":
            case "1ag":
            case "1ah":
            case "1ai":
                if (modRoot.endsWith("gi")) {
                    preschent.setSing1(modRoot.substring(0, modRoot.length() - 1) + "el");
                } else {
                    preschent.setSing1(modRoot + "el");
                }
                preschent.setSing2(modRoot + "as");
                preschent.setSing3(modRoot + "a");

                if (modRoot.endsWith("gi")) {
                    preschent.setPlural1(root.substring(0, modRoot.length() - 1) + "ein");
                    preschent.setPlural2(root.substring(0, modRoot.length() - 1) + "eis");
                } else {
                    preschent.setPlural1(root + "ein");
                    preschent.setPlural2(root + "eis");
                }

                preschent.setPlural3(modRoot + "an");
                break;
            case "1s":
                preschent.setSing1(modRoot + "el\n" + modRootAlternative + "el");
                preschent.setSing2(modRoot + "as\n" + modRootAlternative + "as");
                preschent.setSing3(modRoot + "a\n" + modRootAlternative + "a");

                preschent.setPlural1(root + "ein");
                preschent.setPlural2(root + "eis");
                preschent.setPlural3(modRoot + "an\n" + modRootAlternative + "an");
                break;

            case "6":
                preschent.setSing1(root + "eschel");
                preschent.setSing2(root + "eschas");
                preschent.setSing3(root + "escha");
                preschent.setPlural1(root + "ein");
                preschent.setPlural2(root + "eis");
                preschent.setPlural3(root + "eschan");
                break;

            case "2":
            case "3":
                preschent.setSing1(root + "el");
                preschent.setSing2(root + "as");
                preschent.setSing3(root + "a");

                preschent.setPlural1(root + "ein\n" + root + "in");
                preschent.setPlural2(root + "eis\n" + root + "is");
                preschent.setPlural3(root + "an");
                break;
            case "3a":
            case "3c":
            case "3d":
            case "3e":
            case "3f":
                preschent.setSing1(root + "el");
                preschent.setSing2(root + "as");
                preschent.setSing3(root + "a");

                preschent.setPlural1(modRoot + "ein\n" + modRoot + "in");
                preschent.setPlural2(modRoot + "eis\n" + modRoot + "is");
                preschent.setPlural3(root + "an");
                break;
            case "3b":
                preschent.setSing1(root + "el");
                preschent.setSing2(root + "as");
                preschent.setSing3(root + "a");

                preschent.setPlural1(modRoot + "in");
                preschent.setPlural2(modRoot + "is");
                preschent.setPlural3(root + "an");
                break;

            case "2a":
                preschent.setSing1(modRoot + "el");
                preschent.setSing2(modRoot + "as");
                preschent.setSing3(modRoot + "a");

                preschent.setPlural1(root + "in");
                preschent.setPlural2(root + "is");
                preschent.setPlural3(modRoot + "an");
                break;

            case "4":
                preschent.setSing1(root + "el");
                preschent.setSing2(root + "as");
                preschent.setSing3(root + "a");

                preschent.setPlural1(root + "in");
                preschent.setPlural2(root + "is");
                preschent.setPlural3(root + "an");
                break;
            case "4a":
            case "4b":
            case "4c":
            case "4d":
            case "4e":
            case "4f":
                if (modRoot.endsWith("gi")) {
                    preschent.setSing1(modRoot.substring(0, modRoot.length() - 1) + "el");
                } else {
                    preschent.setSing1(modRoot + "el");
                }
                preschent.setSing2(modRoot + "as");
                preschent.setSing3(modRoot + "a");

                preschent.setPlural1(root + "in");
                preschent.setPlural2(root + "is");
                preschent.setPlural3(modRoot + "an");
                break;

            case "5":
                preschent.setSing1(root + "eschel");
                preschent.setSing2(root + "eschas");
                preschent.setSing3(root + "escha");
                preschent.setPlural1(root + "in");
                preschent.setPlural2(root + "is");
                preschent.setPlural3(root + "eschan");
                break;
        }

        verb.setPreschent(preschent);
    }

    private void setImperfect() {
        VerbDto.PersonalVerbDto imperfect = new VerbDto.PersonalVerbDto();

        switch (verbClass.id) {
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
            case "1x":
            case "1y":
            case "1z":
            case "1aa":
            case "1ab":
            case "1ac":
            case "1ad":
            case "1ae":
            case "1af":
            case "1ag":
            case "1ah":
            case "1ai":
            case "6":
                imperfect.setSing1(root + "avel");
                imperfect.setSing2(root + "avas");
                imperfect.setSing3(root + "ava");

                imperfect.setPlural1(root + "avan");
                imperfect.setPlural2(root + "avas");
                imperfect.setPlural3(root + "avan");
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
                imperfect.setSing1(root + "evel");
                imperfect.setSing2(root + "evas");
                imperfect.setSing3(root + "eva");

                imperfect.setPlural1(root + "evan");
                imperfect.setPlural2(root + "evas");
                imperfect.setPlural3(root + "evan");
                break;

            case "3a":
            case "3b":
            case "3c":
            case "3d":
            case "3e":
            case "3f":
                imperfect.setSing1(modRoot + "evel");
                imperfect.setSing2(modRoot + "evas");
                imperfect.setSing3(modRoot + "eva");

                imperfect.setPlural1(modRoot + "evan");
                imperfect.setPlural2(modRoot + "evas");
                imperfect.setPlural3(modRoot + "evan");
                break;
        }

        verb.setImperfect(imperfect);
    }

    private void setConjunctiv() {
        VerbDto.PersonalVerbDto conjunctiv = new VerbDto.PersonalVerbDto();

        switch (verbClass.id) {
            case "1":
                conjunctiv.setSing1(root + "i");
                conjunctiv.setSing2(root + "ies");
                conjunctiv.setSing3(root + "i");

                conjunctiv.setPlural1(root + "ien\n" + root + "eien");
                conjunctiv.setPlural2(root + "ies\n" + root + "eies");
                conjunctiv.setPlural3(root + "ien");
                break;
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
            case "1t":
            case "1u":
            case "1v":
            case "1w":
            case "1x":
            case "1y":
            case "1z":
            case "1aa":
            case "1ab":
            case "1ac":
            case "1ad":
            case "1ae":
            case "1af":
            case "1ag":
            case "1ah":
            case "1ai":
                if (modRoot.endsWith("i")) {
                    conjunctiv.setSing1(modRoot);
                    conjunctiv.setSing2(modRoot + "es");
                    conjunctiv.setSing3(modRoot);

                    if (modRoot.endsWith("i")) {
                        conjunctiv.setPlural1(modRoot + "en\n" + root.substring(0, modRoot.length() - 1) + "eien");
                        conjunctiv.setPlural2(modRoot + "es\n" + root.substring(0, modRoot.length() - 1) + "eies");
                    } else {
                        conjunctiv.setPlural1(modRoot + "en\n" + root + "eien");
                        conjunctiv.setPlural2(modRoot + "es\n" + root + "eies");
                    }
                    conjunctiv.setPlural3(modRoot + "en");
                } else {
                    conjunctiv.setSing1(modRoot + "i");
                    conjunctiv.setSing2(modRoot + "ies");
                    conjunctiv.setSing3(modRoot + "i");

                    conjunctiv.setPlural1(modRoot + "ien\n" + root + "eien");
                    conjunctiv.setPlural2(modRoot + "ies\n" + root + "eies");
                    conjunctiv.setPlural3(modRoot + "ien");
                }
                break;

            case "1s":
                conjunctiv.setSing1(modRoot + "i\n" + modRootAlternative + "i");
                conjunctiv.setSing2(modRoot + "ies\n" + modRootAlternative + "ies");
                conjunctiv.setSing3(modRoot + "i\n" + modRootAlternative + "i");

                conjunctiv.setPlural1(modRoot + "ien\n" + modRootAlternative + "ien\n" + root + "eien");
                conjunctiv.setPlural2(modRoot + "ies\n" + modRootAlternative + "ies\n" + root + "eies");
                conjunctiv.setPlural3(modRoot + "ien\n" + modRootAlternative + "ien");
                break;

            case "6":
                conjunctiv.setSing1(root + "eschi");
                conjunctiv.setSing2(root + "eschies");
                conjunctiv.setSing3(root + "eschi");

                conjunctiv.setPlural1(root + "eschien\n" + root + "eien");
                conjunctiv.setPlural2(root + "eschies\n" + root + "eies");
                conjunctiv.setPlural3(root + "eschien");
                break;

            case "2":
            case "3":
                conjunctiv.setSing1(root + "i");
                conjunctiv.setSing2(root + "ies");
                conjunctiv.setSing3(root + "i");

                conjunctiv.setPlural1(root + "ien\n" + root + "eien\n" + root + "îen");
                conjunctiv.setPlural2(root + "ies\n" + root + "eies\n" + root + "îes");
                conjunctiv.setPlural3(root + "ien");
                break;
            case "3a":
            case "3c":
            case "3d":
            case "3e":
            case "3f":
                conjunctiv.setSing1(root + "i");
                conjunctiv.setSing2(root + "ies");
                conjunctiv.setSing3(root + "i");

                conjunctiv.setPlural1(root + "ien\n" + modRoot + "eien\n" + modRoot + "îen");
                conjunctiv.setPlural2(root + "ies\n" + modRoot + "eies\n" + modRoot + "îes");
                conjunctiv.setPlural3(root + "ien");
                break;
            case "3b":
                conjunctiv.setSing1(root + "i");
                conjunctiv.setSing2(root + "ies");
                conjunctiv.setSing3(root + "i");

                conjunctiv.setPlural1(root + "ien\n" + modRoot + "îen");
                conjunctiv.setPlural2(root + "ies\n" + modRoot + "îes");
                conjunctiv.setPlural3(root + "ien");
                break;

            case "2a":
                conjunctiv.setSing1(modRoot + "i");
                conjunctiv.setSing2(modRoot + "ies");
                conjunctiv.setSing3(modRoot + "i");

                conjunctiv.setPlural1(modRoot + "ien\n" + root + "îen");
                conjunctiv.setPlural2(modRoot + "ies\n" + root + "îes");
                conjunctiv.setPlural3(modRoot + "ien");
                break;

            case "4":
                conjunctiv.setSing1(root + "i");
                conjunctiv.setSing2(root + "ies");
                conjunctiv.setSing3(root + "i");

                conjunctiv.setPlural1(root + "îen");
                conjunctiv.setPlural2(root + "îes");
                conjunctiv.setPlural3(root + "ien");
                break;

            case "4a":
            case "4b":
            case "4c":
            case "4d":
            case "4e":
            case "4f":
                if (modRoot.endsWith("gi")) {
                    conjunctiv.setSing1(modRoot);
                    conjunctiv.setSing2(modRoot + "es");
                    conjunctiv.setSing3(modRoot);

                    conjunctiv.setPlural1(modRoot + "en\n" + root + "îen");
                    conjunctiv.setPlural2(modRoot + "es\n" + root + "îes");
                    conjunctiv.setPlural3(modRoot + "en");
                } else {
                    conjunctiv.setSing1(modRoot + "i");
                    conjunctiv.setSing2(modRoot + "ies");
                    conjunctiv.setSing3(modRoot + "i");

                    conjunctiv.setPlural1(modRoot + "ien\n" + root + "îen");
                    conjunctiv.setPlural2(modRoot + "ies\n" + root + "îes");
                    conjunctiv.setPlural3(modRoot + "ien");
                }

                break;

            case "5":
                conjunctiv.setSing1(root + "eschi");
                conjunctiv.setSing2(root + "eschies");
                conjunctiv.setSing3(root + "eschi");

                conjunctiv.setPlural1(root + "eschien\n" + root + "îen");
                conjunctiv.setPlural2(root + "eschies\n" + root + "îes");
                conjunctiv.setPlural3(root + "eschien");
                break;
        }

        verb.setConjunctiv(conjunctiv);
    }

    private void setConjunctiv2() {
        VerbDto.PersonalVerbDto conjunctiv2 = new VerbDto.PersonalVerbDto();

        switch (verbClass.id) {
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
            case "1x":
            case "1y":
            case "1z":
            case "1aa":
            case "1ab":
            case "1ac":
            case "1ad":
            case "1ae":
            case "1af":
            case "1ag":
            case "1ah":
            case "1ai":
            case "6":
                conjunctiv2.setSing1(root + "avi");
                conjunctiv2.setSing2(root + "avies");
                conjunctiv2.setSing3(root + "avi");

                conjunctiv2.setPlural1(root + "avien");
                conjunctiv2.setPlural2(root + "avies");
                conjunctiv2.setPlural3(root + "avien");
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
                conjunctiv2.setSing1(root + "evi");
                conjunctiv2.setSing2(root + "evies");
                conjunctiv2.setSing3(root + "evi");

                conjunctiv2.setPlural1(root + "evien");
                conjunctiv2.setPlural2(root + "evies");
                conjunctiv2.setPlural3(root + "evien");
                break;

            case "3a":
            case "3b":
            case "3c":
            case "3d":
            case "3e":
            case "3f":
                conjunctiv2.setSing1(modRoot + "evi");
                conjunctiv2.setSing2(modRoot + "evies");
                conjunctiv2.setSing3(modRoot + "evi");

                conjunctiv2.setPlural1(modRoot + "evien");
                conjunctiv2.setPlural2(modRoot + "evies");
                conjunctiv2.setPlural3(modRoot + "evien");
                break;
        }

        verb.setConjunctiv2(conjunctiv2);
    }

    private void setCundizional() {
        VerbDto.PersonalVerbDto cundizional = new VerbDto.PersonalVerbDto();

        switch (verbClass.id) {
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
            case "1x":
            case "1y":
            case "1z":
            case "1aa":
            case "1ab":
            case "1ac":
            case "1ad":
            case "1ae":
            case "1af":
            case "1ag":
            case "1ah":
            case "1ai":
            case "6":
                cundizional.setSing1(root + "ass");
                cundizional.setSing2(root + "asses");
                cundizional.setSing3(root + "ass");

                cundizional.setPlural1(root + "assen");
                cundizional.setPlural2(root + "asses");
                cundizional.setPlural3(root + "assen");
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
                cundizional.setSing1(root + "ess");
                cundizional.setSing2(root + "esses");
                cundizional.setSing3(root + "ess");

                cundizional.setPlural1(root + "essen");
                cundizional.setPlural2(root + "esses");
                cundizional.setPlural3(root + "essen");
                break;

            case "3a":
            case "3b":
            case "3c":
            case "3d":
            case "3e":
            case "3f":
                cundizional.setSing1(modRoot + "ess");
                cundizional.setSing2(modRoot + "esses");
                cundizional.setSing3(modRoot + "ess");

                cundizional.setPlural1(modRoot + "essen");
                cundizional.setPlural2(modRoot + "esses");
                cundizional.setPlural3(modRoot + "essen");
                break;
        }

        verb.setCundiziunal(cundizional);
    }

    private void setCundizionalIndirect() {
        VerbDto.PersonalVerbDto cundizionalIndirect = new VerbDto.PersonalVerbDto();

        switch (verbClass.id) {
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
            case "1x":
            case "1y":
            case "1z":
            case "1aa":
            case "1ab":
            case "1ac":
            case "1ad":
            case "1ae":
            case "1af":
            case "1ag":
            case "1ah":
            case "1ai":
            case "6":
                cundizionalIndirect.setSing1(root + "assi");
                cundizionalIndirect.setSing2(root + "assies");
                cundizionalIndirect.setSing3(root + "assi");

                cundizionalIndirect.setPlural1(root + "assien");
                cundizionalIndirect.setPlural2(root + "assies");
                cundizionalIndirect.setPlural3(root + "assien");
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
                cundizionalIndirect.setSing1(root + "essi");
                cundizionalIndirect.setSing2(root + "essies");
                cundizionalIndirect.setSing3(root + "essi");

                cundizionalIndirect.setPlural1(root + "essien");
                cundizionalIndirect.setPlural2(root + "essies");
                cundizionalIndirect.setPlural3(root + "essien");
                break;

            case "3a":
            case "3b":
            case "3c":
            case "3d":
            case "3e":
            case "3f":
                cundizionalIndirect.setSing1(modRoot + "essi");
                cundizionalIndirect.setSing2(modRoot + "essies");
                cundizionalIndirect.setSing3(modRoot + "essi");

                cundizionalIndirect.setPlural1(modRoot + "essien");
                cundizionalIndirect.setPlural2(modRoot + "essies");
                cundizionalIndirect.setPlural3(modRoot + "essien");
                break;
        }

        verb.setCundiziunalIndirect(cundizionalIndirect);
    }

    private void setParticipPerfect() {
        VerbDto.ParticipPerfectDto participPerfect = new VerbDto.ParticipPerfectDto();

        switch (verbClass.id) {
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
            case "1x":
            case "1y":
            case "1z":
            case "1aa":
            case "1ab":
            case "1ac":
            case "1ad":
            case "1ae":
            case "1af":
            case "1ag":
            case "1ah":
            case "1ai":
            case "6":
                participPerfect.setMs(root + "au");
                participPerfect.setMsPredicativ(root + "aus");
                participPerfect.setFs(root + "ada");
                participPerfect.setMp(root + "ai");
                participPerfect.setFp(root + "adas");
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
                participPerfect.setMs(root + "iu");
                participPerfect.setMsPredicativ(root + "ius");
                participPerfect.setFs(root + "ida");
                participPerfect.setMp(root + "i");
                participPerfect.setFp(root + "idas");
                break;

            case "3a":
            case "3b":
            case "3c":
            case "3d":
            case "3e":
            case "3f":
                participPerfect.setMs(modRoot + "iu");
                participPerfect.setMsPredicativ(modRoot + "ius");
                participPerfect.setFs(modRoot + "ida");
                participPerfect.setMp(modRoot + "i");
                participPerfect.setFp(modRoot + "idas");
                break;
        }

        verb.setParticipPerfect(participPerfect);

    }

    private void setGerundium() {
        switch (verbClass.id) {
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
            case "1x":
            case "1y":
            case "1z":
            case "1aa":
            case "1ab":
            case "1ac":
            case "1ad":
            case "1ae":
            case "1af":
            case "1ag":
            case "1ah":
            case "1ai":
            case "6":
                verb.setGerundium(root + "ond");
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
                verb.setGerundium(root + "end");
                break;

            case "3a":
            case "3b":
            case "3c":
            case "3d":
            case "3e":
            case "3f":
                verb.setGerundium(modRoot + "end");
                break;
        }
    }

    private void setImperativ() {
        VerbDto.ImperativDto imperativ = new VerbDto.ImperativDto();

        switch (verbClass.id) {
            case "1":
                imperativ.setSingular(root + "a!");
                imperativ.setPlural(root + "ei!");
                break;

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
            case "1t":
            case "1u":
            case "1v":
            case "1w":
            case "1x":
            case "1y":
            case "1z":
            case "1aa":
            case "1ab":
            case "1ac":
            case "1ad":
            case "1ae":
            case "1af":
            case "1ag":
            case "1ah":
            case "1ai":
                imperativ.setSingular(modRoot + "a!");
                if (root.endsWith("gi")) {
                    imperativ.setPlural(root.substring(0, modRoot.length() - 1) + "ei!");
                } else {
                    imperativ.setPlural(root + "ei!");
                }
                break;

            case "1s":
                imperativ.setSingular(modRoot + "a!\n" + modRootAlternative + "a!");
                imperativ.setPlural(root + "ei!");
                break;

            case "6":
                imperativ.setSingular(root + "escha!");
                imperativ.setPlural(root + "ei!");
                break;

            case "2":
            case "3":
                imperativ.setSingular(root + "a!");
                imperativ.setPlural(root + "ei!\n" + root + "i!");
                break;
            case "2a":
                imperativ.setSingular(modRoot + "a!");
                imperativ.setPlural(root + "i!");
                break;
            case "3a":
            case "3c":
            case "3d":
            case "3f":
                imperativ.setSingular(root + "a!");
                imperativ.setPlural(modRoot + "ei!\n" + modRoot + "i!");
                break;
            case "3b":
            case "3e":
                imperativ.setSingular(root + "a!");
                imperativ.setPlural(modRoot + "i!");
                break;

            case "4":
                imperativ.setSingular(root + "a!");
                imperativ.setPlural(root + "i!");
                break;
            case "4a":
            case "4b":
            case "4c":
            case "4d":
            case "4e":
            case "4f":
                imperativ.setSingular(modRoot + "a!");
                imperativ.setPlural(root + "i!");
                break;

            case "5":
                imperativ.setSingular(root + "escha!");
                imperativ.setPlural(root + "i!");
                break;
        }

        verb.setImperativ(imperativ);
    }

    private void setFutur() {
        VerbDto.PersonalVerbDto futur = new VerbDto.PersonalVerbDto();

        char startChar = root.charAt(0);
        if (isReflexive) {
            if (isVocal(startChar)) {
                futur.setSing1("vegn(el) a " + SursilvanPronouns.pron_r_v + infinitiv);
                futur.setSing2("vegns a " + SursilvanPronouns.pron_r_v + infinitiv);
                futur.setSing3("vegn a " + SursilvanPronouns.pron_r_v + infinitiv);

                futur.setPlural1("vegnin ad " + SursilvanPronouns.pron_r_v + infinitiv);
                futur.setPlural2("vegnis ad " + SursilvanPronouns.pron_r_v + infinitiv);
                futur.setPlural3("vegnan a " + SursilvanPronouns.pron_r_v + infinitiv);

            } else {
                futur.setSing1("vegn(el) a " + SursilvanPronouns.pron_r + infinitiv);
                futur.setSing2("vegns a " + SursilvanPronouns.pron_r + infinitiv);
                futur.setSing3("vegn a " + SursilvanPronouns.pron_r + infinitiv);

                futur.setPlural1("vegnin ad " + SursilvanPronouns.pron_r + infinitiv);
                futur.setPlural2("vegnis ad " + SursilvanPronouns.pron_r + infinitiv);
                futur.setPlural3("vegnan a " + SursilvanPronouns.pron_r + infinitiv);

            }
        } else {
            if (isVocal(startChar)) {
                futur.setSing1("vegn(el) ad " + infinitiv);
                futur.setSing2("vegns ad " + infinitiv);
                futur.setSing3("vegn ad " + infinitiv);
                futur.setPlural1("vegnin ad " + infinitiv);
                futur.setPlural2("vegnis ad " + infinitiv);
                futur.setPlural3("vegnan ad " + infinitiv);

            } else {
                futur.setSing1("vegn(el) a " + infinitiv);
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

        if (isReflexive) {
            if (verb.getInfinitiv().startsWith("se")) {
                // Reflexive Verbs that start with Consonants
                addReflexivePronouns();

            } else if (verb.getInfinitiv().startsWith("s'")) {
                // Reflexive Verbs that start with vowel
                addReflexivePronounsVowel();
            }
        } else {
            // Standard Verbs
            addStandardPronouns();
        }
    }

    private void addReflexivePronouns() {

        // PRESCHENT
        verb.getPreschent().setSing1(setPronoun(SursilvanPronouns.pron_1ps + SursilvanPronouns.pron_r, verb.getPreschent().getSing1()));
        verb.getPreschent().setSing2(setPronoun(SursilvanPronouns.pron_2ps + SursilvanPronouns.pron_r, verb.getPreschent().getSing2()));
        verb.getPreschent().setSing3(setPronoun(SursilvanPronouns.pron_3ps + SursilvanPronouns.pron_r, verb.getPreschent().getSing3()));
        verb.getPreschent().setPlural1(setPronoun(SursilvanPronouns.pron_1pp + SursilvanPronouns.pron_r, verb.getPreschent().getPlural1()));
        verb.getPreschent().setPlural2(setPronoun(SursilvanPronouns.pron_2pp + SursilvanPronouns.pron_r, verb.getPreschent().getPlural2()));
        verb.getPreschent().setPlural3(setPronoun(SursilvanPronouns.pron_3pp + SursilvanPronouns.pron_r, verb.getPreschent().getPlural3()));

        // IMPERFECT
        verb.getImperfect().setSing1(setPronoun(SursilvanPronouns.pron_1ps + SursilvanPronouns.pron_r, verb.getImperfect().getSing1()));
        verb.getImperfect().setSing2(setPronoun(SursilvanPronouns.pron_2ps + SursilvanPronouns.pron_r, verb.getImperfect().getSing2()));
        verb.getImperfect().setSing3(setPronoun(SursilvanPronouns.pron_3ps + SursilvanPronouns.pron_r, verb.getImperfect().getSing3()));
        verb.getImperfect().setPlural1(setPronoun(SursilvanPronouns.pron_1pp + SursilvanPronouns.pron_r, verb.getImperfect().getPlural1()));
        verb.getImperfect().setPlural2(setPronoun(SursilvanPronouns.pron_2pp + SursilvanPronouns.pron_r, verb.getImperfect().getPlural2()));
        verb.getImperfect().setPlural3(setPronoun(SursilvanPronouns.pron_3pp + SursilvanPronouns.pron_r, verb.getImperfect().getPlural3()));

        // CONJUNCTIV
        verb.getConjunctiv().setSing1(setPronoun(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_1ps + SursilvanPronouns.pron_r, verb.getConjunctiv().getSing1()));
        verb.getConjunctiv().setSing2(setPronoun(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_2ps + SursilvanPronouns.pron_r, verb.getConjunctiv().getSing2()));
        verb.getConjunctiv().setSing3(setPronoun(SursilvanPronouns.pron_conjunctiv_v + SursilvanPronouns.pron_3ps + SursilvanPronouns.pron_r, verb.getConjunctiv().getSing3()));
        verb.getConjunctiv().setPlural1(setPronoun(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_1pp + SursilvanPronouns.pron_r, verb.getConjunctiv().getPlural1()));
        verb.getConjunctiv().setPlural2(setPronoun(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_2pp + SursilvanPronouns.pron_r,verb.getConjunctiv().getPlural2()));
        verb.getConjunctiv().setPlural3(setPronoun(SursilvanPronouns.pron_conjunctiv_v + SursilvanPronouns.pron_3pp + SursilvanPronouns.pron_r, verb.getConjunctiv().getPlural3()));

        // CONJUNCTIV IMPERFECT
        verb.getConjunctiv2().setSing1(setPronoun(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_1ps + SursilvanPronouns.pron_r, verb.getConjunctiv2().getSing1()));
        verb.getConjunctiv2().setSing2(setPronoun(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_2ps + SursilvanPronouns.pron_r, verb.getConjunctiv2().getSing2()));
        verb.getConjunctiv2().setSing3(setPronoun(SursilvanPronouns.pron_conjunctiv_v + SursilvanPronouns.pron_3ps + SursilvanPronouns.pron_r, verb.getConjunctiv2().getSing3()));
        verb.getConjunctiv2().setPlural1(setPronoun(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_1pp + SursilvanPronouns.pron_r, verb.getConjunctiv2().getPlural1()));
        verb.getConjunctiv2().setPlural2(setPronoun(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_2pp + SursilvanPronouns.pron_r, verb.getConjunctiv2().getPlural2()));
        verb.getConjunctiv2().setPlural3(setPronoun(SursilvanPronouns.pron_conjunctiv_v + SursilvanPronouns.pron_3pp + SursilvanPronouns.pron_r, verb.getConjunctiv2().getPlural3()));

        // CUNDIZIONAL
        verb.getCundiziunal().setSing1(setPronoun(SursilvanPronouns.pron_1ps + SursilvanPronouns.pron_r, verb.getCundiziunal().getSing1()));
        verb.getCundiziunal().setSing2(setPronoun(SursilvanPronouns.pron_2ps + SursilvanPronouns.pron_r, verb.getCundiziunal().getSing2()));
        verb.getCundiziunal().setSing3(setPronoun(SursilvanPronouns.pron_3ps + SursilvanPronouns.pron_r, verb.getCundiziunal().getSing3()));
        verb.getCundiziunal().setPlural1(setPronoun(SursilvanPronouns.pron_1pp + SursilvanPronouns.pron_r, verb.getCundiziunal().getPlural1()));
        verb.getCundiziunal().setPlural2(setPronoun(SursilvanPronouns.pron_2pp + SursilvanPronouns.pron_r, verb.getCundiziunal().getPlural2()));
        verb.getCundiziunal().setPlural3(setPronoun(SursilvanPronouns.pron_3pp + SursilvanPronouns.pron_r, verb.getCundiziunal().getPlural3()));

        // CUNDIZIONAL INDIRECT
        verb.getCundiziunalIndirect().setSing1(setPronoun(SursilvanPronouns.pron_1ps + SursilvanPronouns.pron_r, verb.getCundiziunalIndirect().getSing1()));
        verb.getCundiziunalIndirect().setSing2(setPronoun(SursilvanPronouns.pron_2ps + SursilvanPronouns.pron_r, verb.getCundiziunalIndirect().getSing2()));
        verb.getCundiziunalIndirect().setSing3(setPronoun(SursilvanPronouns.pron_3ps + SursilvanPronouns.pron_r, verb.getCundiziunalIndirect().getSing3()));
        verb.getCundiziunalIndirect().setPlural1(setPronoun(SursilvanPronouns.pron_1pp + SursilvanPronouns.pron_r, verb.getCundiziunalIndirect().getPlural1()));
        verb.getCundiziunalIndirect().setPlural2(setPronoun(SursilvanPronouns.pron_2pp + SursilvanPronouns.pron_r, verb.getCundiziunalIndirect().getPlural2()));
        verb.getCundiziunalIndirect().setPlural3(setPronoun(SursilvanPronouns.pron_3pp + SursilvanPronouns.pron_r, verb.getCundiziunalIndirect().getPlural3()));

        // IMPERATIV
        verb.getImperativ().setSingular(setPronoun(SursilvanPronouns.pron_r, verb.getImperativ().getSingular()));
        verb.getImperativ().setPlural(setPronoun(SursilvanPronouns.pron_r, verb.getImperativ().getPlural()));

        // PARTICIP_PERFECT
        verb.getParticipPerfect().setMs(setPronoun(SursilvanPronouns.pron_r, verb.getParticipPerfect().getMs()));
        verb.getParticipPerfect().setFs(setPronoun(SursilvanPronouns.pron_r, verb.getParticipPerfect().getFs()));
        verb.getParticipPerfect().setMp(setPronoun(SursilvanPronouns.pron_r, verb.getParticipPerfect().getMp()));
        verb.getParticipPerfect().setFp(setPronoun(SursilvanPronouns.pron_r, verb.getParticipPerfect().getFp()));
        verb.getParticipPerfect().setMsPredicativ(setPronoun(SursilvanPronouns.pron_r, verb.getParticipPerfect().getMsPredicativ()));

        // GERUNDIUM
        verb.setGerundium(setPronoun(SursilvanPronouns.pron_r, verb.getGerundium()));

        // FUTUR
        verb.getFutur().setSing1(setPronoun(SursilvanPronouns.pron_1ps, verb.getFutur().getSing1()));
        verb.getFutur().setSing2(setPronoun(SursilvanPronouns.pron_2ps, verb.getFutur().getSing2()));
        verb.getFutur().setSing3(setPronoun(SursilvanPronouns.pron_3ps, verb.getFutur().getSing3()));
        verb.getFutur().setPlural1(setPronoun(SursilvanPronouns.pron_1pp, verb.getFutur().getPlural1()));
        verb.getFutur().setPlural2(setPronoun(SursilvanPronouns.pron_2pp, verb.getFutur().getPlural2()));
        verb.getFutur().setPlural3(setPronoun(SursilvanPronouns.pron_3pp, verb.getFutur().getPlural3()));
    }

    private void addReflexivePronounsVowel() {

        // PRESCHENT
        verb.getPreschent().setSing1(setPronoun(SursilvanPronouns.pron_1ps + SursilvanPronouns.pron_r_v, verb.getPreschent().getSing1()));
        verb.getPreschent().setSing2(setPronoun(SursilvanPronouns.pron_2ps + SursilvanPronouns.pron_r_v, verb.getPreschent().getSing2()));
        verb.getPreschent().setSing3(setPronoun(SursilvanPronouns.pron_3ps + SursilvanPronouns.pron_r_v, verb.getPreschent().getSing3()));
        verb.getPreschent().setPlural1(setPronoun(SursilvanPronouns.pron_1pp + SursilvanPronouns.pron_r_v, verb.getPreschent().getPlural1()));
        verb.getPreschent().setPlural2(setPronoun(SursilvanPronouns.pron_2pp + SursilvanPronouns.pron_r_v, verb.getPreschent().getPlural2()));
        verb.getPreschent().setPlural3(setPronoun(SursilvanPronouns.pron_3pp + SursilvanPronouns.pron_r_v, verb.getPreschent().getPlural3()));

        // IMPERFECT
        verb.getImperfect().setSing1(setPronoun(SursilvanPronouns.pron_1ps + SursilvanPronouns.pron_r_v, verb.getImperfect().getSing1()));
        verb.getImperfect().setSing2(setPronoun(SursilvanPronouns.pron_2ps + SursilvanPronouns.pron_r_v, verb.getImperfect().getSing2()));
        verb.getImperfect().setSing3(setPronoun(SursilvanPronouns.pron_3ps + SursilvanPronouns.pron_r_v, verb.getImperfect().getSing3()));
        verb.getImperfect().setPlural1(setPronoun(SursilvanPronouns.pron_1pp + SursilvanPronouns.pron_r_v, verb.getImperfect().getPlural1()));
        verb.getImperfect().setPlural2(setPronoun(SursilvanPronouns.pron_2pp + SursilvanPronouns.pron_r_v, verb.getImperfect().getPlural2()));
        verb.getImperfect().setPlural3(setPronoun(SursilvanPronouns.pron_3pp + SursilvanPronouns.pron_r_v, verb.getImperfect().getPlural3()));

        // CONJUNCTIV
        verb.getConjunctiv().setSing1(setPronoun(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_1ps + SursilvanPronouns.pron_r_v, verb.getConjunctiv().getSing1()));
        verb.getConjunctiv().setSing2(setPronoun(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_2ps + SursilvanPronouns.pron_r_v, verb.getConjunctiv().getSing2()));
        verb.getConjunctiv().setSing3(setPronoun(SursilvanPronouns.pron_conjunctiv_v + SursilvanPronouns.pron_3ps + SursilvanPronouns.pron_r_v, verb.getConjunctiv().getSing3()));
        verb.getConjunctiv().setPlural1(setPronoun(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_1pp + SursilvanPronouns.pron_r_v, verb.getConjunctiv().getPlural1()));
        verb.getConjunctiv().setPlural2(setPronoun(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_2pp + SursilvanPronouns.pron_r_v,verb.getConjunctiv().getPlural2()));
        verb.getConjunctiv().setPlural3(setPronoun(SursilvanPronouns.pron_conjunctiv_v + SursilvanPronouns.pron_3pp + SursilvanPronouns.pron_r_v, verb.getConjunctiv().getPlural3()));

        // CONJUNCTIV IMPERFECT
        verb.getConjunctiv2().setSing1(setPronoun(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_1ps + SursilvanPronouns.pron_r_v, verb.getConjunctiv2().getSing1()));
        verb.getConjunctiv2().setSing2(setPronoun(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_2ps + SursilvanPronouns.pron_r_v, verb.getConjunctiv2().getSing2()));
        verb.getConjunctiv2().setSing3(setPronoun(SursilvanPronouns.pron_conjunctiv_v + SursilvanPronouns.pron_3ps + SursilvanPronouns.pron_r_v, verb.getConjunctiv2().getSing3()));
        verb.getConjunctiv2().setPlural1(setPronoun(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_1pp + SursilvanPronouns.pron_r_v, verb.getConjunctiv2().getPlural1()));
        verb.getConjunctiv2().setPlural2(setPronoun(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_2pp + SursilvanPronouns.pron_r_v, verb.getConjunctiv2().getPlural2()));
        verb.getConjunctiv2().setPlural3(setPronoun(SursilvanPronouns.pron_conjunctiv_v + SursilvanPronouns.pron_3pp + SursilvanPronouns.pron_r_v, verb.getConjunctiv2().getPlural3()));

        // CUNDIZIONAL
        verb.getCundiziunal().setSing1(setPronoun(SursilvanPronouns.pron_1ps + SursilvanPronouns.pron_r_v, verb.getCundiziunal().getSing1()));
        verb.getCundiziunal().setSing2(setPronoun(SursilvanPronouns.pron_2ps + SursilvanPronouns.pron_r_v, verb.getCundiziunal().getSing2()));
        verb.getCundiziunal().setSing3(setPronoun(SursilvanPronouns.pron_3ps + SursilvanPronouns.pron_r_v, verb.getCundiziunal().getSing3()));
        verb.getCundiziunal().setPlural1(setPronoun(SursilvanPronouns.pron_1pp + SursilvanPronouns.pron_r_v, verb.getCundiziunal().getPlural1()));
        verb.getCundiziunal().setPlural2(setPronoun(SursilvanPronouns.pron_2pp + SursilvanPronouns.pron_r_v, verb.getCundiziunal().getPlural2()));
        verb.getCundiziunal().setPlural3(setPronoun(SursilvanPronouns.pron_3pp + SursilvanPronouns.pron_r_v, verb.getCundiziunal().getPlural3()));

        // CUNDIZIONAL INDIRECT
        verb.getCundiziunalIndirect().setSing1(setPronoun(SursilvanPronouns.pron_1ps + SursilvanPronouns.pron_r_v, verb.getCundiziunalIndirect().getSing1()));
        verb.getCundiziunalIndirect().setSing2(setPronoun(SursilvanPronouns.pron_2ps + SursilvanPronouns.pron_r_v, verb.getCundiziunalIndirect().getSing2()));
        verb.getCundiziunalIndirect().setSing3(setPronoun(SursilvanPronouns.pron_3ps + SursilvanPronouns.pron_r_v, verb.getCundiziunalIndirect().getSing3()));
        verb.getCundiziunalIndirect().setPlural1(setPronoun(SursilvanPronouns.pron_1pp + SursilvanPronouns.pron_r_v, verb.getCundiziunalIndirect().getPlural1()));
        verb.getCundiziunalIndirect().setPlural2(setPronoun(SursilvanPronouns.pron_2pp + SursilvanPronouns.pron_r_v, verb.getCundiziunalIndirect().getPlural2()));
        verb.getCundiziunalIndirect().setPlural3(setPronoun(SursilvanPronouns.pron_3pp + SursilvanPronouns.pron_r_v, verb.getCundiziunalIndirect().getPlural3()));

        // IMPERATIV
        verb.getImperativ().setSingular(setPronoun(SursilvanPronouns.pron_r_v, verb.getImperativ().getSingular()));
        verb.getImperativ().setPlural(setPronoun(SursilvanPronouns.pron_r_v, verb.getImperativ().getPlural()));

        // PARTICIP_PERFECT
        verb.getParticipPerfect().setMs(setPronoun(SursilvanPronouns.pron_r_v, verb.getParticipPerfect().getMs()));
        verb.getParticipPerfect().setFs(setPronoun(SursilvanPronouns.pron_r_v, verb.getParticipPerfect().getFs()));
        verb.getParticipPerfect().setMp(setPronoun(SursilvanPronouns.pron_r_v, verb.getParticipPerfect().getMp()));
        verb.getParticipPerfect().setFp(setPronoun(SursilvanPronouns.pron_r_v, verb.getParticipPerfect().getFp()));
        verb.getParticipPerfect().setMsPredicativ(setPronoun(SursilvanPronouns.pron_r_v, verb.getParticipPerfect().getMsPredicativ()));

        // GERUNDIUM
        verb.setGerundium(setPronoun(SursilvanPronouns.pron_r_v, verb.getGerundium()));

        // FUTUR
        verb.getFutur().setSing1(setPronoun(SursilvanPronouns.pron_1ps, verb.getFutur().getSing1()));
        verb.getFutur().setSing2(setPronoun(SursilvanPronouns.pron_2ps, verb.getFutur().getSing2()));
        verb.getFutur().setSing3(setPronoun(SursilvanPronouns.pron_3ps, verb.getFutur().getSing3()));
        verb.getFutur().setPlural1(setPronoun(SursilvanPronouns.pron_1pp, verb.getFutur().getPlural1()));
        verb.getFutur().setPlural2(setPronoun(SursilvanPronouns.pron_2pp, verb.getFutur().getPlural2()));
        verb.getFutur().setPlural3(setPronoun(SursilvanPronouns.pron_3pp, verb.getFutur().getPlural3()));
    }


    private void addStandardPronouns() {

        // PRESCHENT
        verb.getPreschent().setSing1(setPronoun(SursilvanPronouns.pron_1ps, verb.getPreschent().getSing1()));
        verb.getPreschent().setSing2(setPronoun(SursilvanPronouns.pron_2ps, verb.getPreschent().getSing2()));
        verb.getPreschent().setSing3(setPronoun(SursilvanPronouns.pron_3ps, verb.getPreschent().getSing3()));
        verb.getPreschent().setPlural1(setPronoun(SursilvanPronouns.pron_1pp, verb.getPreschent().getPlural1()));
        verb.getPreschent().setPlural2(setPronoun(SursilvanPronouns.pron_2pp, verb.getPreschent().getPlural2()));
        verb.getPreschent().setPlural3(setPronoun(SursilvanPronouns.pron_3pp, verb.getPreschent().getPlural3()));

        // IMPERFECT
        verb.getImperfect().setSing1(setPronoun(SursilvanPronouns.pron_1ps, verb.getImperfect().getSing1()));
        verb.getImperfect().setSing2(setPronoun(SursilvanPronouns.pron_2ps, verb.getImperfect().getSing2()));
        verb.getImperfect().setSing3(setPronoun(SursilvanPronouns.pron_3ps, verb.getImperfect().getSing3()));
        verb.getImperfect().setPlural1(setPronoun(SursilvanPronouns.pron_1pp, verb.getImperfect().getPlural1()));
        verb.getImperfect().setPlural2(setPronoun(SursilvanPronouns.pron_2pp, verb.getImperfect().getPlural2()));
        verb.getImperfect().setPlural3(setPronoun(SursilvanPronouns.pron_3pp, verb.getImperfect().getPlural3()));

        // CONJUNCTIV
        verb.getConjunctiv().setSing1(setPronoun(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_1ps, verb.getConjunctiv().getSing1()));
        verb.getConjunctiv().setSing2(setPronoun(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_2ps, verb.getConjunctiv().getSing2()));
        verb.getConjunctiv().setSing3(setPronoun(SursilvanPronouns.pron_conjunctiv_v + SursilvanPronouns.pron_3ps, verb.getConjunctiv().getSing3()));
        verb.getConjunctiv().setPlural1(setPronoun(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_1pp, verb.getConjunctiv().getPlural1()));
        verb.getConjunctiv().setPlural2(setPronoun(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_2pp, verb.getConjunctiv().getPlural2()));
        verb.getConjunctiv().setPlural3(setPronoun(SursilvanPronouns.pron_conjunctiv_v + SursilvanPronouns.pron_3pp, verb.getConjunctiv().getPlural3()));

        // CONJUNCTIV IMPERFECT
        verb.getConjunctiv2().setSing1(setPronoun(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_1ps, verb.getConjunctiv2().getSing1()));
        verb.getConjunctiv2().setSing2(setPronoun(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_2ps, verb.getConjunctiv2().getSing2()));
        verb.getConjunctiv2().setSing3(setPronoun(SursilvanPronouns.pron_conjunctiv_v + SursilvanPronouns.pron_3ps, verb.getConjunctiv2().getSing3()));
        verb.getConjunctiv2().setPlural1(setPronoun(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_1pp, verb.getConjunctiv2().getPlural1()));
        verb.getConjunctiv2().setPlural2(setPronoun(SursilvanPronouns.pron_conjunctiv_c + SursilvanPronouns.pron_2pp, verb.getConjunctiv2().getPlural2()));
        verb.getConjunctiv2().setPlural3(setPronoun(SursilvanPronouns.pron_conjunctiv_v + SursilvanPronouns.pron_3pp, verb.getConjunctiv2().getPlural3()));

        // CUNDIZIONAL
        verb.getCundiziunal().setSing1(setPronoun(SursilvanPronouns.pron_1ps, verb.getCundiziunal().getSing1()));
        verb.getCundiziunal().setSing2(setPronoun(SursilvanPronouns.pron_2ps, verb.getCundiziunal().getSing2()));
        verb.getCundiziunal().setSing3(setPronoun(SursilvanPronouns.pron_3ps, verb.getCundiziunal().getSing3()));
        verb.getCundiziunal().setPlural1(setPronoun(SursilvanPronouns.pron_1pp, verb.getCundiziunal().getPlural1()));
        verb.getCundiziunal().setPlural2(setPronoun(SursilvanPronouns.pron_2pp, verb.getCundiziunal().getPlural2()));
        verb.getCundiziunal().setPlural3(setPronoun(SursilvanPronouns.pron_3pp, verb.getCundiziunal().getPlural3()));

        // CUNDIZIONAL INDIRECT
        verb.getCundiziunalIndirect().setSing1(setPronoun(SursilvanPronouns.pron_1ps, verb.getCundiziunalIndirect().getSing1()));
        verb.getCundiziunalIndirect().setSing2(setPronoun(SursilvanPronouns.pron_2ps, verb.getCundiziunalIndirect().getSing2()));
        verb.getCundiziunalIndirect().setSing3(setPronoun(SursilvanPronouns.pron_3ps, verb.getCundiziunalIndirect().getSing3()));
        verb.getCundiziunalIndirect().setPlural1(setPronoun(SursilvanPronouns.pron_1pp, verb.getCundiziunalIndirect().getPlural1()));
        verb.getCundiziunalIndirect().setPlural2(setPronoun(SursilvanPronouns.pron_2pp, verb.getCundiziunalIndirect().getPlural2()));
        verb.getCundiziunalIndirect().setPlural3(setPronoun(SursilvanPronouns.pron_3pp, verb.getCundiziunalIndirect().getPlural3()));

        // IMPERATIV
        verb.getImperativ().setSingular(verb.getImperativ().getSingular());
        verb.getImperativ().setPlural(verb.getImperativ().getPlural());

        // PARTICIP_PERFECT
        verb.getParticipPerfect().setMs(verb.getParticipPerfect().getMs());
        verb.getParticipPerfect().setFs(verb.getParticipPerfect().getFs());
        verb.getParticipPerfect().setMp(verb.getParticipPerfect().getMp());
        verb.getParticipPerfect().setFp(verb.getParticipPerfect().getFp());
        verb.getParticipPerfect().setMsPredicativ(verb.getParticipPerfect().getMsPredicativ());

        // GERUNDIUM
        verb.setGerundium(verb.getGerundium());

        // FUTUR
        verb.getFutur().setSing1(setPronoun(SursilvanPronouns.pron_1ps, verb.getFutur().getSing1()));
        verb.getFutur().setSing2(setPronoun(SursilvanPronouns.pron_2ps, verb.getFutur().getSing2()));
        verb.getFutur().setSing3(setPronoun(SursilvanPronouns.pron_3ps, verb.getFutur().getSing3()));
        verb.getFutur().setPlural1(setPronoun(SursilvanPronouns.pron_1pp, verb.getFutur().getPlural1()));
        verb.getFutur().setPlural2(setPronoun(SursilvanPronouns.pron_2pp, verb.getFutur().getPlural2()));
        verb.getFutur().setPlural3(setPronoun(SursilvanPronouns.pron_3pp, verb.getFutur().getPlural3()));
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
