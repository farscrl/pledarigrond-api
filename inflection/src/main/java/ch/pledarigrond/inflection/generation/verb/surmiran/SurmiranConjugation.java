package ch.pledarigrond.inflection.generation.verb.surmiran;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.common.data.dictionary.inflection.InflectionType;
import ch.pledarigrond.common.data.dictionary.inflection.VerbDto;
import ch.pledarigrond.inflection.generation.verb.LanguageConjugation;
import ch.pledarigrond.inflection.model.InflectionSubType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class SurmiranConjugation extends LanguageConjugation {

    private static final Logger logger = LoggerFactory.getLogger(SurmiranConjugation.class);

    private InflectionDto inflection;
    private VerbDto verb;
    private InflectionSubType verbClass;

    private String infinitiv;
    private boolean isReflexive;

    private String root;
    private String modRoot;
    private String ending;
    private char vowelInRoot;

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
                if(infinitivForm.endsWith("gler")) {
                    modRoot = root;
                    modRoot = modRoot + "i";
                }
            }
            case "2a" -> {
                int start = root.lastIndexOf("ag");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "eg" + root.substring(start + 2) + "i";
                if(infinitivForm.endsWith("gler")) {
                    modRoot = modRoot + "i";
                }
            }
            case "2b" -> {
                int start = root.lastIndexOf("cag");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "cheg" + root.substring(start + 3) + "i";
                if(infinitivForm.endsWith("gler")) {
                    modRoot = modRoot + "i";
                }
            }
            case "2c" -> {
                modRoot = root + "esch";
            }
            case "3" -> {
                if(infinitivForm.endsWith("glier")) {
                    modRoot = root;
                    modRoot = modRoot + "i";
                }
            }
            case "3a" -> {
                int start = root.lastIndexOf("i");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "e" + root.substring(start + 1);

                if(infinitivForm.endsWith("glier")) {
                    modRoot = modRoot + "i";
                }
            }
            case "3b" -> {
                int start = root.lastIndexOf("u");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "o" + root.substring(start + 1);

                if(infinitivForm.endsWith("glier")) {
                    modRoot = modRoot + "i";
                }
            }
            case "3c" -> {
                int start = root.lastIndexOf("u");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ou" + root.substring(start + 1);

                if(infinitivForm.endsWith("glier")) {
                    modRoot = modRoot + "i";
                }
            }
            case "3d" -> {
                modRoot = root + "esch";
            }
            case "4" -> {
                if(infinitivForm.endsWith("gleir")) {
                    modRoot = root;
                    modRoot = modRoot + "i";
                }
            }
            case "4a" -> {
                int start = root.lastIndexOf("a");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "e" + root.substring(start + 1);

                if(infinitivForm.endsWith("gleir")) {
                    modRoot = modRoot + "i";
                }
            }
            case "5" -> {
                if(infinitivForm.endsWith("gler")) {
                    modRoot = root;
                    modRoot = modRoot + "i";
                }
            }
            case "5a" -> {
                int start = root.lastIndexOf("e");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "a" + root.substring(start + 1);

                if(infinitivForm.endsWith("gler")) {
                    modRoot = modRoot + "i";
                }
            }
            case "5b" -> {
                int start = root.lastIndexOf("o");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "u" + root.substring(start + 1);

                if(infinitivForm.endsWith("gler")) {
                    modRoot = modRoot + "i";
                }
            }
            case "5c" -> {
                int start = root.lastIndexOf("ei");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "i" + root.substring(start + 2);

                if(infinitivForm.endsWith("gler")) {
                    modRoot = modRoot + "i";
                }
            }
            case "6" -> {
                if(infinitivForm.endsWith("gleir")) {
                    modRoot = root;
                    modRoot = modRoot + "i";
                }
            }
            case "6a" -> {
                int start = root.lastIndexOf("ar");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ra" + root.substring(start + 2);

                if(infinitivForm.endsWith("gleir")) {
                    modRoot = modRoot + "i";
                }
                if(infinitivForm.endsWith("geir")) {
                    modRoot = modRoot + "i";
                }
            }
            case "6b" -> {
                int start = root.lastIndexOf("a");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "e" + root.substring(start + 1);

                if(infinitivForm.endsWith("gleir")) {
                    modRoot = modRoot + "i";
                }
            }
            case "6c" -> {
                int start = root.lastIndexOf("i");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "e" + root.substring(start + 1);

                if(infinitivForm.endsWith("gleir")) {
                    modRoot = modRoot + "i";
                }
            }
            case "6d" -> {
                int start = root.lastIndexOf("ugl");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ogl" + root.substring(start + 3);

                if(infinitivForm.endsWith("gleir")) {
                    modRoot = modRoot + "i";
                }
            }
            case "6e" -> {
                int start = root.lastIndexOf("a");
                if (start == -1) break;
                modRoot = root.substring(0, start) + "ai" + root.substring(start + 1);

                if(infinitivForm.endsWith("gleir")) {
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
        } else if (ending == null) {
            throw new RuntimeException(infinitivForm + "  is not a valid verb. Please enter a verb in its infinitive form.");
        }

        verbClass = subType;
        verb.setInflectionSubtype(verbClass.id);
        conjugate();
        addPronouns();
        return inflection;
    }

    @Override
    public InflectionDto guessInflection(String baseForm, String genus, String flex) {
        reset();

        if (baseForm.length() < 3) {
            return null;
        }

        String lastTwo = baseForm.substring(baseForm.length() - 2);
        String lastThree = baseForm.substring(baseForm.length() - 3);

        if (lastThree.equals("ier")) {
            if ((flex != null && flex.equals("-escha")) || baseForm.endsWith("tgier")) {
                return generateConjugation("3d", baseForm);
            }

            return generateConjugation("3", baseForm);
        }

        if (lastThree.equals("eir")) {
            if (flex != null && flex.equals("-escha")) {
                return generateConjugation("7", baseForm);
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

            return generateConjugation("1", baseForm);
        }

        if (lastTwo.equals("er")) {
            if (baseForm.endsWith("cager")) {
                return generateConjugation("2b", baseForm);
            }

            if (lastThree.equals("ger")) {
                return generateConjugation("2a", baseForm);
            }

            return generateConjugation("2", baseForm);
        }

        return null;
    }

    private String getRoot(String infinitivForm) {
        if (infinitivForm != null) {
            infinitivForm = normalizeString(infinitivForm);

            if (infinitivForm.equals("eir")) {
                ending = infinitivForm;
                return infinitivForm;
            }

            switch (infinitivForm.length()) {
                case 0:
                case 1:
                case 2:
                    throw new RuntimeException("'" + infinitivForm + "' is not a valid verb. Please enter a verb in its infinitive form.");

                default:
                    infinitivForm = processQuery(infinitivForm);
            }
        }
        return infinitivForm;
    }

    private String changeVocalInRoot(String root) {
        StringBuilder builder = null;

        for (int i = root.length() - 1; i >= 0; i--) {
            char ch = root.charAt(i);
            if (isVocal(ch)) {
                vowelInRoot = ch;
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

    private String processQuery(String infintivForm) {
        String l2 = infintivForm.substring(infintivForm.length() - 2);
        String l3 = infintivForm.substring(infintivForm.length() - 3);

        switch (l2) {
            case "ar":
            case "er":
            case "ir":
                if (!l3.equals("eir") && !l3.equals("ier")) {
                    ending = l2;
                    infintivForm = infintivForm.substring(0, infintivForm.length() - 2);

                    return infintivForm;
                }
                break;
        }

        if (l3.equals("eir")) {
            ending = l3;
            infintivForm = infintivForm.substring(0, infintivForm.length() - 3);

            return infintivForm;
        }

        if (l3.equals("ier")) {
            ending = l3;
            infintivForm = infintivForm.substring(0, infintivForm.length() - 3);

            return infintivForm;
        }

        return infintivForm;
    }

    private String checkReflexiveness(String query) {

        if (query.startsWith("sa ")) {
            query = query.length() > 2 ? query.substring(3) : query;
            isReflexive = true;

        } else if (query.startsWith("s'")) {
            query = query.length() > 2 ? query.substring(2) : query;
            isReflexive = true;
        } else {
            isReflexive = false;
        }

        return query;
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

        setPreschentEnclitic();
        setImperfectEnclitic();
        setCundizionalEnclitic();
        setFuturEnclitic();
    }

    private void setPreschent() {
        VerbDto.PersonalVerbDto preschent = new VerbDto.PersonalVerbDto();

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
                if (ending.equals("ar") && !infinitiv.equals("mussar") && endsWithDoubleConsonant(modRoot)) {
                    String firstSingular = modRoot.substring(0, modRoot.length() - 1);
                    preschent.setSing1(firstSingular);
                } else if(endsOnGl()) {
                    preschent.setSing1(modRoot.substring(0, modRoot.length() - 1));
                } else if (endsOnGeir()){
                    preschent.setSing1(modRoot.substring(0, modRoot.length() - 2) + "tg");
                } else {
                    preschent.setSing1(modRoot);
                }
                preschent.setSing2(modRoot + "as");
                preschent.setSing3(modRoot + "a");
                if (verbClass.id.charAt(0) == '6') {
                    // 1pp
                    preschent.setPlural1(root + "ign");
                    // 2pp
                    preschent.setPlural2(root + "iz");
                } else {
                    // 1pp
                    if(endsOnGl()) {
                        preschent.setPlural1(root + "iagn");
                    } else {
                        preschent.setPlural1(root + "agn");
                    }

                    // 2pp
                    preschent.setPlural2(root + "ez");
                }
                preschent.setPlural3(modRoot + "an");
                break;

            case "2a":
            case "2b":
                preschent.setSing1(modRoot.substring(0, modRoot.length() - 2) + "tg");
                preschent.setSing2(modRoot + "as");
                preschent.setSing3(modRoot + "a");
                preschent.setPlural1(root + "iagn");
                preschent.setPlural2(root + "ez");
                preschent.setPlural3(modRoot + "an");
                break;


            case "5a":
            case "5b":
            case "5c":
                preschent.setSing1(root);
                preschent.setSing2(root + "as");
                preschent.setSing3(root + "a");
                preschent.setPlural1(modRoot + "agn");
                preschent.setPlural2(modRoot + "ez");
                preschent.setPlural3(root + "an");
                break;

            case "7":
            case "8":

                // PRESCHENT
                // 1ps
                preschent.setSing1(root + "esch");
                // 2ps
                preschent.setSing2(root + "eschas");
                // 3ps
                preschent.setSing3(root + "escha");

                if (verbClass.id.charAt(0) == '7') {
                    // 1pp
                    preschent.setPlural1(root + "ign");
                    // 2pp
                    preschent.setPlural2(root + "iz");
                } else {
                    // 1pp
                    preschent.setPlural1(root + "agn");
                    // 2pp
                    preschent.setPlural2(root + "ez");
                }

                // 3pp
                preschent.setPlural3(root + "eschan");
                break;

            case "9":
                switch (vowelInRoot) {
                    case 'a':
                    case 'u':
                    case 'i':
                        // PRESCHENT
                        // 1ps
                        if (ending.equals("ar") && !infinitiv.equals("mussar")) {

                            if (endsWithDoubleConsonant(root)) {
                                String firstSingular = modRoot.substring(0, modRoot.length() - 1);
                                preschent.setSing1(firstSingular);
                            } else {
                                preschent.setSing1(modRoot);
                            }
                        }

                        else {
                            preschent.setSing1(modRoot);
                        }

                        preschent.setSing2(modRoot + "as");
                        preschent.setSing3(modRoot + "a");
                        preschent.setPlural1(root + "agn");
                        preschent.setPlural2(root + "ez");
                        preschent.setPlural3(modRoot + "an");
                        break;

                    case 'e':
                    case 'o':

                        // PRESCHENT
                        // 1ps
                        if (ending.equals("ar")) {

                            if (endsWithDoubleConsonant(root)) {
                                String firstSingular = root.substring(0,
                                        root.length() - 1);
                                preschent.setSing1(firstSingular);
                            } else {

                                preschent.setSing1(root);
                            }

                        }

                        else {
                            preschent.setSing1(root);
                        }

                        preschent.setSing2(root + "as");
                        preschent.setSing3(root + "a");
                        preschent.setPlural1(modRoot + "agn");
                        preschent.setPlural2(modRoot + "ez");
                        preschent.setPlural3(root + "an");
                        break;

                }
        }

        verb.setPreschent(preschent);
    }

    private void setImperfect() {
        VerbDto.PersonalVerbDto imperfect = new VerbDto.PersonalVerbDto();

        switch (verbClass.id.substring(0, 1)) {

            case "1":
            case "8":
                imperfect.setSing1(root + "ava");
                imperfect.setSing2(root + "avas");
                imperfect.setSing3(root + "ava");
                imperfect.setPlural1(root + "avan");
                imperfect.setPlural2(root + "avas");
                imperfect.setPlural3(root + "avan");
                break;

            case "3":
            case "6":
            case "7":
                imperfect.setSing1(root + "iva");
                imperfect.setSing2(root + "ivas");
                imperfect.setSing3(root + "iva");
                imperfect.setPlural1(root + "ivan");
                imperfect.setPlural2(root + "ivas");
                imperfect.setPlural3(root + "ivan");
                break;

            case "2":
            case "4":
            case "5":
                if (verbClass.id.equals("5a") || verbClass.id.equals("5b") || verbClass.id.equals("5c")) {
                    imperfect.setSing1(modRoot + "eva");
                    imperfect.setSing2(modRoot + "evas");
                    imperfect.setSing3(modRoot + "eva");
                    imperfect.setPlural1(modRoot + "evan");
                    imperfect.setPlural2(modRoot + "evas");
                    imperfect.setPlural3(modRoot + "evan");
                    break;
                }
                imperfect.setSing1(root + "eva");
                imperfect.setSing2(root + "evas");
                imperfect.setSing3(root + "eva");
                imperfect.setPlural1(root + "evan");
                imperfect.setPlural2(root + "evas");
                imperfect.setPlural3(root + "evan");
                break;

            case "9":
                if (ending.equals("ar") && vowelInRoot == 'u') {
                    imperfect.setSing1(root + "ava");
                    imperfect.setSing2(root + "avas");
                    imperfect.setSing3(root + "ava");
                    imperfect.setPlural1(root + "avan");
                    imperfect.setPlural2(root + "avas");
                    imperfect.setPlural3(root + "avan");
                    break;

                } else {
                    switch (vowelInRoot) {
                        case 'a':
                        case 'i':
                            imperfect.setSing1(root + "ava");
                            imperfect.setSing2(root + "avas");
                            imperfect.setSing3(root + "ava");
                            imperfect.setPlural1(root + "avan");
                            imperfect.setPlural2(root + "avas");
                            imperfect.setPlural3(root + "avan");
                            break;

                        case 'e':
                        case 'o':
                        case 'u':
                            imperfect.setSing1(modRoot + "eva");
                            imperfect.setSing2(modRoot + "evas");
                            imperfect.setSing3(modRoot + "eva");
                            imperfect.setPlural1(modRoot + "evan");
                            imperfect.setPlural2(modRoot + "evas");
                            imperfect.setPlural3(modRoot + "evan");
                            break;
                    }

                }
                break;
        }

        verb.setImperfect(imperfect);
    }

    private void setConjunctiv() {
        VerbDto.PersonalVerbDto conjunctiv = new VerbDto.PersonalVerbDto();

        switch (verbClass.id) {

            case "7":
            case "8":
                conjunctiv.setSing1(root + "escha");
                conjunctiv.setSing2(root + "eschas");
                conjunctiv.setSing3(root + "escha");
                conjunctiv.setPlural1(root + "eschan");
                conjunctiv.setPlural2(root + "eschas");
                conjunctiv.setPlural3(root + "eschan");
                break;

            case "9":
                switch (vowelInRoot) {
                    case 'e':
                    case 'o':
                        conjunctiv.setSing1(root + "a");
                        conjunctiv.setSing2(root + "as");
                        conjunctiv.setSing3(root + "a");
                        conjunctiv.setPlural1(root + "an");
                        conjunctiv.setPlural2(root + "as");
                        conjunctiv.setPlural3(root + "an");
                        break;

                    case 'a':
                    case 'i':
                    case 'u':
                        conjunctiv.setSing1(modRoot + "a");
                        conjunctiv.setSing2(modRoot + "as");
                        conjunctiv.setSing3(modRoot + "a");
                        conjunctiv.setPlural1(modRoot + "an");
                        conjunctiv.setPlural2(modRoot + "as");
                        conjunctiv.setPlural3(modRoot + "an");
                        break;

                }

                break;

            default:
                if (verbClass.id.equals("5a") || verbClass.id.equals("5b") || verbClass.id.equals("5c")) {
                    conjunctiv.setSing1(root + "a");
                    conjunctiv.setSing2(root + "as");
                    conjunctiv.setSing3(root + "a");
                    conjunctiv.setPlural1(root + "an");
                    conjunctiv.setPlural2(root + "as");
                    conjunctiv.setPlural3(root + "an");
                    break;
                }

                conjunctiv.setSing1(modRoot + "a");
                conjunctiv.setSing2(modRoot + "as");
                conjunctiv.setSing3(modRoot + "a");
                conjunctiv.setPlural1(modRoot + "an");
                conjunctiv.setPlural2(modRoot + "as");
                conjunctiv.setPlural3(modRoot + "an");
                break;
        }

        verb.setConjunctiv(conjunctiv);
    }

    private void setCundizional() {
        VerbDto.PersonalVerbDto cundizional = new VerbDto.PersonalVerbDto();

        switch (verbClass.id.substring(0, 1)) {

            case "6":
            case "7":
                cundizional.setSing1(root + "iss");
                cundizional.setSing2(root + "issas");
                cundizional.setSing3(root + "iss");
                cundizional.setPlural1(root + "issan");
                cundizional.setPlural2(root + "issas");
                cundizional.setPlural3(root + "issan");
                break;

            case "9":
                switch (vowelInRoot) {
                    case 'e':
                    case 'o':
                        cundizional.setSing1(modRoot + "ess");
                        cundizional.setSing2(modRoot + "essas");
                        cundizional.setSing3(modRoot + "ess");
                        cundizional.setPlural1(modRoot + "essan");
                        cundizional.setPlural2(modRoot + "essas");
                        cundizional.setPlural3(modRoot + "essan");
                        break;

                    case 'a':
                    case 'i':
                    case 'u':
                        cundizional.setSing1(root + "ess");
                        cundizional.setSing2(root + "essas");
                        cundizional.setSing3(root + "ess");
                        cundizional.setPlural1(root + "essan");
                        cundizional.setPlural2(root + "essas");
                        cundizional.setPlural3(root + "essan");
                        break;
                }
                break;

            default:
                if (verbClass.id.equals("5a") || verbClass.id.equals("5b") || verbClass.id.equals("5c")) {
                    cundizional.setSing1(modRoot + "ess");
                    cundizional.setSing2(modRoot + "essas");
                    cundizional.setSing3(modRoot + "ess");
                    cundizional.setPlural1(modRoot + "essan");
                    cundizional.setPlural2(modRoot + "essas");
                    cundizional.setPlural3(modRoot + "essan");
                    break;
                }
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

        switch (verbClass.id.substring(0, 1)) {
            case "1":
            case "8":
                participPerfect.setMs(root + "o");
                participPerfect.setMp(root + "os");
                participPerfect.setFs(root + "ada");
                participPerfect.setFp(root + "adas");
                break;

            case "2":
                participPerfect.setMs(root + "ea");
                participPerfect.setMp(root + "eas");
                participPerfect.setFs(root + "eda");
                participPerfect.setFp(root + "edas");
                break;

            case "9":
                if (ending.equals("ar")) {
                    participPerfect.setMs(root + "o");
                    participPerfect.setMp(root + "os");
                    participPerfect.setFs(root + "ada");
                    participPerfect.setFp(root + "adas");
                }
                else {
                    switch (vowelInRoot) {
                        case 'e':
                        case 'o':
                            participPerfect.setMs(modRoot + "ia");
                            participPerfect.setMp(modRoot + "ias");
                            participPerfect.setFs(modRoot + "eida");
                            participPerfect.setFp(modRoot + "eidas");
                            break;

                        case 'a':
                            participPerfect.setMs(root + "o");
                            participPerfect.setMp(root + "os");
                            participPerfect.setFs(root + "ada");
                            participPerfect.setFp(root + "adas");
                            break;

                        case 'i':
                            participPerfect.setMs(modRoot + "a");
                            participPerfect.setMp(modRoot + "as");
                            participPerfect.setFs(modRoot + "ada");
                            participPerfect.setFp(modRoot + "adas");
                            break;

                        case 'u':
                            participPerfect.setMs(root + "ia");
                            participPerfect.setMp(root + "ias");
                            participPerfect.setFs(root + "eida");
                            participPerfect.setFp(root + "eidas");
                            break;

                    }
                }
                break;

            default:
                if (verbClass.id.equals("5a") || verbClass.id.equals("5b") || verbClass.id.equals("5c")) {
                    participPerfect.setMs(modRoot + "ia");
                    participPerfect.setMp(modRoot + "ias");
                    participPerfect.setFs(modRoot + "eida");
                    participPerfect.setFp(modRoot + "eidas");
                    break;
                }

                participPerfect.setMs(root + "ia");
                participPerfect.setMp(root + "ias");
                participPerfect.setFs(root + "eida");
                participPerfect.setFp(root + "eidas");
                break;
        }

        verb.setParticipPerfect(participPerfect);
    }

    private void setGerundium() {

        switch (verbClass.id.substring(0, 1)) {
            case "6":
            case "7":
                if (endsOnGeir()) {
                    verb.setGerundium(root + "iond" + "/" + root + "end");
                    break;
                }
                verb.setGerundium(root + "ond" + "/" + root + "end");
                break;

            case "9":
                switch (vowelInRoot) {
                    case 'a':
                    case 'i':
                    case 'u':
                        verb.setGerundium(root + "ond");
                        break;

                    case 'o':
                    case 'e':
                        verb.setGerundium(modRoot + "ond");
                        break;

                }
                break;
            default:
                if (verbClass.id.equals("2a") || verbClass.id.equals("2b") || endsOnGl()) {
                    verb.setGerundium(root + "iond");
                    break;
                }
                if (verbClass.id.equals("5a") || verbClass.id.equals("5b") || verbClass.id.equals("5c")) {
                    verb.setGerundium(modRoot + "ond");
                    break;
                }
                verb.setGerundium(root + "ond");
                break;
        }

    }

    private void setImperativ() {
        VerbDto.ImperativDto imperativ = new VerbDto.ImperativDto();

        switch (verbClass.id) {
            case "7":
            case "8":
                imperativ.setSingular(root + "escha!");

                if (verbClass.id.charAt(0) == '7') {
                    imperativ.setPlural(root + "i!");
                } else {
                    imperativ.setPlural(root + "e!");
                }
                break;

            case "9":
                switch (vowelInRoot) {
                    case 'a':
                    case 'i':
                    case 'u':
                        imperativ.setSingular(modRoot + "a!");
                        imperativ.setPlural(root + "e!");
                        break;

                    case 'e':
                    case 'o':
                        imperativ.setSingular(root + "a!");
                        imperativ.setPlural(modRoot + "e!");
                        break;
                }
                break;

            default:
                if (verbClass.id.equals("5a") || verbClass.id.equals("5b") || verbClass.id.equals("5c")) {
                    imperativ.setSingular(root + "a!");
                    imperativ.setPlural(modRoot + "e!");
                    break;
                }
                imperativ.setSingular(modRoot + "a!");
                if (verbClass.id.charAt(0) == '6') {
                    imperativ.setPlural(root + "i!");
                } else {
                    imperativ.setPlural(root + "e!");
                }
                break;
        }

        verb.setImperativ(imperativ);
    }

    private void setFutur() {
        VerbDto.PersonalVerbDto futur = new VerbDto.PersonalVerbDto();

        if (isReflexive) {
            if (startsWithVowel(root)) {
                switch (verbClass.id.substring(0, 1)) {
                    case "6":
                    case "7":
                        futur.setSing1(SurmiranPronouns.pron_r_v_1ps + root + "iro");
                        futur.setSing2(SurmiranPronouns.pron_r_v_2ps + root + "irossas");
                        futur.setSing3(SurmiranPronouns.pron_r_v_3ps + root + "iro");
                        futur.setPlural1(SurmiranPronouns.pron_r_v_1pp + root + "iron");
                        futur.setPlural2(SurmiranPronouns.pron_r_v_2pp + root + "irossas");
                        futur.setPlural3(SurmiranPronouns.pron_r_v_3pp + root + "iron");
                        break;

                    case "9":
                        switch (vowelInRoot) {
                            case 'e':
                            case 'o':
                                futur.setSing1(SurmiranPronouns.pron_r_v_1ps + modRoot + "aro");
                                futur.setSing2(SurmiranPronouns.pron_r_v_2ps + modRoot + "arossas");
                                futur.setSing3(SurmiranPronouns.pron_r_v_3ps + modRoot + "aro");
                                futur.setPlural1(SurmiranPronouns.pron_r_v_1pp + modRoot + "aron");
                                futur.setPlural2(SurmiranPronouns.pron_r_v_2pp + modRoot + "arossas");
                                futur.setPlural3(SurmiranPronouns.pron_r_v_3pp + modRoot + "aron");
                                break;

                            case 'a':
                            case 'i':
                            case 'u':
                                futur.setSing1(SurmiranPronouns.pron_r_v_1ps + root + "aro");
                                futur.setSing2(SurmiranPronouns.pron_r_v_2ps + root + "arossas");
                                futur.setSing3(SurmiranPronouns.pron_r_v_3ps + root + "aro");
                                futur.setPlural1(SurmiranPronouns.pron_r_v_1pp + root + "aron");
                                futur.setPlural2(SurmiranPronouns.pron_r_v_2pp + root + "arossas");
                                futur.setPlural3(SurmiranPronouns.pron_r_v_3pp + root + "aron");
                                break;
                        }
                        break;

                    default:
                        futur.setSing1(SurmiranPronouns.pron_r_v_1ps + root + "aro");
                        futur.setSing2(SurmiranPronouns.pron_r_v_2ps + root + "arossas");
                        futur.setSing3(SurmiranPronouns.pron_r_v_3ps + root + "aro");
                        futur.setPlural1(SurmiranPronouns.pron_r_v_1pp + root + "aron");
                        futur.setPlural2(SurmiranPronouns.pron_r_v_2pp + root + "arossas");
                        futur.setPlural3(SurmiranPronouns.pron_r_v_3pp + root + "aron");
                        break;
                }

            } else {
                switch (verbClass.id.substring(0, 1)) {
                    case "6":
                    case "7":
                        futur.setSing1(SurmiranPronouns.pron_r_1ps + root + "iro");
                        futur.setSing2(SurmiranPronouns.pron_r_2ps + root + "irossas");
                        futur.setSing3(SurmiranPronouns.pron_r_3ps + root + "iro");
                        futur.setPlural1(SurmiranPronouns.pron_r_1pp + root + "iron");
                        futur.setPlural2(SurmiranPronouns.pron_r_2pp + root + "irossas");
                        futur.setPlural3(SurmiranPronouns.pron_r_3pp + root + "iron");
                        break;

                    case "9":
                        switch (vowelInRoot) {
                            case 'e':
                            case 'o':
                                futur.setSing1(SurmiranPronouns.pron_r_1ps + modRoot + "aro");
                                futur.setSing2(SurmiranPronouns.pron_r_2ps + modRoot + "arossas");
                                futur.setSing3(SurmiranPronouns.pron_r_3ps + modRoot + "aro");
                                futur.setPlural1(SurmiranPronouns.pron_r_1pp + modRoot + "aron");
                                futur.setPlural2(SurmiranPronouns.pron_r_2pp + modRoot + "arossas");
                                futur.setPlural3(SurmiranPronouns.pron_r_3pp + modRoot + "aron");
                                break;

                            case 'a':
                            case 'i':
                            case 'u':
                                futur.setSing1(SurmiranPronouns.pron_r_1ps + root + "aro");
                                futur.setSing2(SurmiranPronouns.pron_r_2ps + root + "arossas");
                                futur.setSing3(SurmiranPronouns.pron_r_3ps + root + "aro");
                                futur.setPlural1(SurmiranPronouns.pron_r_1pp + root + "aron");
                                futur.setPlural2(SurmiranPronouns.pron_r_2pp + root + "arossas");
                                futur.setPlural3(SurmiranPronouns.pron_r_3pp + root + "aron");
                                break;

                        }
                        break;

                    default:
                        if (verbClass.id.equals("5a") || verbClass.id.equals("5b") || verbClass.id.equals("5c")) {
                            futur.setSing1(SurmiranPronouns.pron_r_1ps + modRoot + "aro");
                            futur.setSing2(SurmiranPronouns.pron_r_2ps + modRoot + "arossas");
                            futur.setSing3(SurmiranPronouns.pron_r_3ps + modRoot + "aro");
                            futur.setPlural1(SurmiranPronouns.pron_r_1pp + modRoot + "aron");
                            futur.setPlural2(SurmiranPronouns.pron_r_2pp + modRoot + "arossas");
                            futur.setPlural3(SurmiranPronouns.pron_r_3pp + modRoot + "aron");
                            break;
                        }
                        futur.setSing1(SurmiranPronouns.pron_r_1ps + root + "aro");
                        futur.setSing2(SurmiranPronouns.pron_r_2ps + root + "arossas");
                        futur.setSing3(SurmiranPronouns.pron_r_3ps + root + "aro");
                        futur.setPlural1(SurmiranPronouns.pron_r_1pp + root + "aron");
                        futur.setPlural2(SurmiranPronouns.pron_r_2pp + root + "arossas");
                        futur.setPlural3(SurmiranPronouns.pron_r_3pp + root + "aron");
                        break;

                }

            }
        } else {
            switch (verbClass.id.substring(0, 1)) {
                case "6":
                case "7":
                    futur.setSing1(root + "iro");
                    futur.setSing2(root + "irossas");
                    futur.setSing3(root + "iro");
                    futur.setPlural1(root + "iron");
                    futur.setPlural2(root + "irossas");
                    futur.setPlural3(root + "iron");
                    break;

                case "9":
                    switch (vowelInRoot) {
                        case 'e':
                        case 'o':
                            futur.setSing1(modRoot + "aro");
                            futur.setSing2(modRoot + "arossas");
                            futur.setSing3(modRoot + "aro");
                            futur.setPlural1(modRoot + "aron");
                            futur.setPlural2(modRoot + "arossas");
                            futur.setPlural3(modRoot + "aron");
                            break;

                        case 'a':
                        case 'i':
                        case 'u':
                            futur.setSing1(root + "aro");
                            futur.setSing2(root + "arossas");
                            futur.setSing3(root + "aro");
                            futur.setPlural1(root + "aron");
                            futur.setPlural2(root + "arossas");
                            futur.setPlural3(root + "aron");
                            break;
                    }

                    break;

                default:
                    if (verbClass.id.equals("2a") || verbClass.id.equals("2b") || endsOnGl()) {
                        futur.setSing1(root + "iaro");
                        futur.setSing2(root + "iarossas");
                        futur.setSing3(root + "iaro");
                        futur.setPlural1(root + "iaron");
                        futur.setPlural2(root + "iarossas");
                        futur.setPlural3(root + "iaron");
                        break;
                    }
                    if (verbClass.id.equals("5a") || verbClass.id.equals("5b") || verbClass.id.equals("5c")) {
                        futur.setSing1(modRoot + "aro");
                        futur.setSing2(modRoot + "arossas");
                        futur.setSing3(modRoot + "aro");
                        futur.setPlural1(modRoot + "aron");
                        futur.setPlural2(modRoot + "arossas");
                        futur.setPlural3(modRoot + "aron");
                        break;
                    }
                    futur.setSing1(root + "aro");
                    futur.setSing2(root + "arossas");
                    futur.setSing3(root + "aro");
                    futur.setPlural1(root + "aron");
                    futur.setPlural2(root + "arossas");
                    futur.setPlural3(root + "aron");
                    break;

            }
        }

        verb.setFutur(futur);
    }

    private void setPreschentEnclitic() {
        VerbDto.PersonalVerbEncliticDto preschentEnclitic = new VerbDto.PersonalVerbEncliticDto();

        if(verb.getPreschent().getSing1().endsWith("gl")) {
            preschentEnclitic.setSing1(verb.getPreschent().getSing1() + "ia");
        } else if (verb.getPreschent().getSing1().endsWith("etg")) {
            preschentEnclitic.setSing1(verb.getPreschent().getSing1().substring(0, verb.getPreschent().getSing1().length() - 3) + "egia");
        } else {
            preschentEnclitic.setSing1(verb.getPreschent().getSing1() + "a");
        }

        preschentEnclitic.setSing2(verb.getPreschent().getSing2() + "t");


        if (SurmiranConjugation.isSingleSyllable(verb.getPreschent().getSing3()) && SurmiranConjugation.endsOnConsonantOrDiftong(verb.getPreschent().getSing3())) {
            preschentEnclitic.setSing3m(verb.getPreschent().getSing3() + "el");
        } else {
            preschentEnclitic.setSing3m(verb.getPreschent().getSing3() + "'l");
        }

        if (verb.getPreschent().getSing3().endsWith("gia")) {
            preschentEnclitic.setSing3f(verb.getPreschent().getSing3().substring(0, verb.getPreschent().getSing3().length() - 3) + "tg'la");
        } else if (verb.getPreschent().getSing3().endsWith("glia")) {
            preschentEnclitic.setSing3f(verb.getPreschent().getSing3().substring(0, verb.getPreschent().getSing3().length() - 2) + "'la");
        } else {
            preschentEnclitic.setSing3f(verb.getPreschent().getSing3().substring(0, verb.getPreschent().getSing3().length() - 1) + "'la");
        }

        if (verbClass.id.charAt(0) == '6' || verbClass.id.charAt(0) == '7') {
            preschentEnclitic.setPlural1(verb.getPreschent().getPlural1() + "sa");
        } else {
            preschentEnclitic.setPlural1(verb.getPreschent().getPlural1().substring(0, verb.getPreschent().getPlural1().length() - 3) + "ainsa");
        }

        preschentEnclitic.setPlural2(verb.getPreschent().getPlural2());

        if (verb.getPreschent().getPlural3().endsWith("ian")) {
            preschentEnclitic.setPlural3(verb.getPreschent().getPlural3().substring(0, verb.getPreschent().getPlural3().length() - 3) + "igl");
        } else {
            preschentEnclitic.setPlural3(verb.getPreschent().getPlural3().substring(0, verb.getPreschent().getPlural3().length() - 2) + "igl");
        }

        verb.setPreschentEnclitic(preschentEnclitic);
    }

    private void setImperfectEnclitic() {
        VerbDto.PersonalVerbEncliticDto imperfectEnclitic = new VerbDto.PersonalVerbEncliticDto();

        imperfectEnclitic.setSing1(verb.getImperfect().getSing1());
        imperfectEnclitic.setSing2(verb.getImperfect().getSing2() + "t");
        imperfectEnclitic.setSing3m(verb.getImperfect().getSing3() + "'l");
        imperfectEnclitic.setSing3f(verb.getImperfect().getSing3().substring(0, verb.getImperfect().getSing3().length() - 1) + "'la");
        imperfectEnclitic.setPlural1(verb.getImperfect().getPlural1() + "s");
        imperfectEnclitic.setPlural2(verb.getImperfect().getPlural2());
        imperfectEnclitic.setPlural3(verb.getImperfect().getPlural3().substring(0, verb.getImperfect().getPlural3().length() - 2) + "igl");

        verb.setImperfectEnclitic(imperfectEnclitic);
    }

    private void setCundizionalEnclitic() {
        VerbDto.PersonalVerbEncliticDto cundizionalEnclitic = new VerbDto.PersonalVerbEncliticDto();

        cundizionalEnclitic.setSing1(verb.getCundiziunal().getSing1() + "a");
        cundizionalEnclitic.setSing2(verb.getCundiziunal().getSing2() + "t");
        cundizionalEnclitic.setSing3m(verb.getCundiziunal().getSing3() + "a'l");
        cundizionalEnclitic.setSing3f(verb.getCundiziunal().getSing3() + "'la");
        cundizionalEnclitic.setPlural1(verb.getCundiziunal().getPlural1() + "s");
        cundizionalEnclitic.setPlural2(verb.getCundiziunal().getPlural2());
        cundizionalEnclitic.setPlural3(verb.getCundiziunal().getPlural3().substring(0, verb.getCundiziunal().getPlural3().length() - 2) + "igl");

        verb.setCundiziunalEnclitic(cundizionalEnclitic);
    }

    private void setFuturEnclitic() {
        VerbDto.PersonalVerbEncliticDto futurEnclitic = new VerbDto.PersonalVerbEncliticDto();

        futurEnclitic.setSing1(verb.getFutur().getSing1() + "ia");
        futurEnclitic.setSing2(verb.getFutur().getSing2() + "t");
        futurEnclitic.setSing3m(verb.getFutur().getSing3() + "'l");
        futurEnclitic.setSing3f(verb.getFutur().getSing3() + "'la");
        futurEnclitic.setPlural1(verb.getFutur().getPlural1() + "sa");
        futurEnclitic.setPlural2(verb.getFutur().getPlural2());
        futurEnclitic.setPlural3(verb.getFutur().getPlural3() + "igl");

        verb.setFuturEnclitic(futurEnclitic);
    }

    private void addPronouns() {
        Map<String, String> pronouns;

        if (verb.getInfinitiv().startsWith("sa ")) {
            // Reflexive Verbs that start with Consonants
            addReflexivePronouns();

        } else if (verb.getInfinitiv().startsWith("s'")) {
            // Reflexive Verbs that start with vowels
            addReflexivePronounsVowel();

        } else {
            // Standard Verbs
            addStandardPronouns();
        }
    }

    private void addReflexivePronouns() {

        // PRESCHENT
        verb.getPreschent().setSing1(SurmiranPronouns.pron_1ps + SurmiranPronouns.pron_r_1ps + verb.getPreschent().getSing1());
        verb.getPreschent().setSing2(SurmiranPronouns.pron_2ps + SurmiranPronouns.pron_r_2ps + verb.getPreschent().getSing2());
        verb.getPreschent().setSing3(SurmiranPronouns.pron_3ps + SurmiranPronouns.pron_r_3ps + verb.getPreschent().getSing3());
        verb.getPreschent().setPlural1(SurmiranPronouns.pron_1pp + SurmiranPronouns.pron_r_1pp + verb.getPreschent().getPlural1());
        verb.getPreschent().setPlural2(SurmiranPronouns.pron_2pp + SurmiranPronouns.pron_r_2pp + verb.getPreschent().getPlural2());
        verb.getPreschent().setPlural3(SurmiranPronouns.pron_3pp + SurmiranPronouns.pron_r_3pp + verb.getPreschent().getPlural3());

        // IMPERFECT
        verb.getImperfect().setSing1(SurmiranPronouns.pron_1ps + SurmiranPronouns.pron_r_1ps + verb.getImperfect().getSing1());
        verb.getImperfect().setSing2(SurmiranPronouns.pron_2ps + SurmiranPronouns.pron_r_2ps + verb.getImperfect().getSing2());
        verb.getImperfect().setSing3(SurmiranPronouns.pron_3ps + SurmiranPronouns.pron_r_3ps + verb.getImperfect().getSing3());
        verb.getImperfect().setPlural1(SurmiranPronouns.pron_1pp + SurmiranPronouns.pron_r_1pp + verb.getImperfect().getPlural1());
        verb.getImperfect().setPlural2(SurmiranPronouns.pron_2pp + SurmiranPronouns.pron_r_2pp + verb.getImperfect().getPlural2());
        verb.getImperfect().setPlural3(SurmiranPronouns.pron_3pp + SurmiranPronouns.pron_r_3pp + verb.getImperfect().getPlural3());

        // CONJUNCTIV
        verb.getConjunctiv().setSing1(SurmiranPronouns.pron_conjunctiv_v + SurmiranPronouns.pron_1ps + SurmiranPronouns.pron_r_1ps + verb.getConjunctiv().getSing1());
        verb.getConjunctiv().setSing2(SurmiranPronouns.pron_conjunctiv_c + SurmiranPronouns.pron_2ps + SurmiranPronouns.pron_r_2ps + verb.getConjunctiv().getSing2());
        verb.getConjunctiv().setSing3(SurmiranPronouns.pron_conjunctiv_v + SurmiranPronouns.pron_3ps + SurmiranPronouns.pron_r_3ps + verb.getConjunctiv().getSing3());
        verb.getConjunctiv().setPlural1(SurmiranPronouns.pron_conjunctiv_c + SurmiranPronouns.pron_1pp + SurmiranPronouns.pron_r_1pp + verb.getConjunctiv().getPlural1());
        verb.getConjunctiv().setPlural2(SurmiranPronouns.pron_conjunctiv_c + SurmiranPronouns.pron_2pp + SurmiranPronouns.pron_r_2pp + verb.getConjunctiv().getPlural2());
        verb.getConjunctiv().setPlural3(SurmiranPronouns.pron_conjunctiv_v + SurmiranPronouns.pron_3pp + SurmiranPronouns.pron_r_3pp + verb.getConjunctiv().getPlural3());

        // CUNDIZIONAL
        verb.getCundiziunal().setSing1(SurmiranPronouns.pron_1ps + SurmiranPronouns.pron_r_1ps + verb.getCundiziunal().getSing1());
        verb.getCundiziunal().setSing2(SurmiranPronouns.pron_2ps + SurmiranPronouns.pron_r_2ps + verb.getCundiziunal().getSing2());
        verb.getCundiziunal().setSing3(SurmiranPronouns.pron_3ps + SurmiranPronouns.pron_r_3ps + verb.getCundiziunal().getSing3());
        verb.getCundiziunal().setPlural1(SurmiranPronouns.pron_1pp + SurmiranPronouns.pron_r_1pp + verb.getCundiziunal().getPlural1());
        verb.getCundiziunal().setPlural2(SurmiranPronouns.pron_2pp + SurmiranPronouns.pron_r_2pp + verb.getCundiziunal().getPlural2());
        verb.getCundiziunal().setPlural3(SurmiranPronouns.pron_3pp + SurmiranPronouns.pron_r_3pp + verb.getCundiziunal().getPlural3());

        // IMPERATIV
        verb.getImperativ().setSingular(SurmiranPronouns.pron_r_2ps + verb.getImperativ().getSingular());
        verb.getImperativ().setPlural(SurmiranPronouns.pron_r_2pp + verb.getImperativ().getPlural());

        // PARTICIP_PERFECT
        verb.getParticipPerfect().setMs(verb.getParticipPerfect().getMs());
        verb.getParticipPerfect().setFs(verb.getParticipPerfect().getFs());
        verb.getParticipPerfect().setMp(verb.getParticipPerfect().getMp());
        verb.getParticipPerfect().setFp(verb.getParticipPerfect().getFp());

        // GERUNDIUM
        verb.setGerundium(verb.getGerundium());

        // FUTUR
        verb.getFutur().setSing1(SurmiranPronouns.pron_1ps + verb.getFutur().getSing1());
        verb.getFutur().setSing2(SurmiranPronouns.pron_2ps + verb.getFutur().getSing2());
        verb.getFutur().setSing3(SurmiranPronouns.pron_3ps + verb.getFutur().getSing3());
        verb.getFutur().setPlural1(SurmiranPronouns.pron_1pp + verb.getFutur().getPlural1());
        verb.getFutur().setPlural2(SurmiranPronouns.pron_2pp + verb.getFutur().getPlural2());
        verb.getFutur().setPlural3(SurmiranPronouns.pron_3pp + verb.getFutur().getPlural3());

        if (verb.getInfinitiv().startsWith("sa ")) {
            addReflexivePronounsAndCopyEncliticForms(false);

        } else if (verb.getInfinitiv().startsWith("s'")) {
            addReflexivePronounsAndCopyEncliticForms(true);
        }
    }

    private void addReflexivePronounsVowel() {

        // PRESCHENT
        verb.getPreschent().setSing1(SurmiranPronouns.pron_1ps + SurmiranPronouns.pron_r_v_1ps + verb.getPreschent().getSing1());
        verb.getPreschent().setSing2(SurmiranPronouns.pron_2ps + SurmiranPronouns.pron_r_v_2ps + verb.getPreschent().getSing2());
        verb.getPreschent().setSing3(SurmiranPronouns.pron_3ps + SurmiranPronouns.pron_r_v_3ps + verb.getPreschent().getSing3());
        verb.getPreschent().setPlural1(SurmiranPronouns.pron_1pp + SurmiranPronouns.pron_r_v_1pp + verb.getPreschent().getPlural1());
        verb.getPreschent().setPlural2(SurmiranPronouns.pron_2pp + SurmiranPronouns.pron_r_v_2pp + verb.getPreschent().getPlural2());
        verb.getPreschent().setPlural3(SurmiranPronouns.pron_3pp + SurmiranPronouns.pron_r_v_3pp + verb.getPreschent().getPlural3());

        // IMPERFECT
        verb.getImperfect().setSing1(SurmiranPronouns.pron_1ps + SurmiranPronouns.pron_r_v_1ps + verb.getImperfect().getSing1());
        verb.getImperfect().setSing2(SurmiranPronouns.pron_2ps + SurmiranPronouns.pron_r_v_2ps + verb.getImperfect().getSing2());
        verb.getImperfect().setSing3(SurmiranPronouns.pron_3ps + SurmiranPronouns.pron_r_v_3ps + verb.getImperfect().getSing3());
        verb.getImperfect().setPlural1(SurmiranPronouns.pron_1pp + SurmiranPronouns.pron_r_v_1pp + verb.getImperfect().getPlural1());
        verb.getImperfect().setPlural2(SurmiranPronouns.pron_2pp + SurmiranPronouns.pron_r_v_2pp + verb.getImperfect().getPlural2());
        verb.getImperfect().setPlural3(SurmiranPronouns.pron_3pp + SurmiranPronouns.pron_r_v_3pp + verb.getImperfect().getPlural3());

        // CONJUNCTIV
        verb.getConjunctiv().setSing1(SurmiranPronouns.pron_conjunctiv_v + SurmiranPronouns.pron_1ps + SurmiranPronouns.pron_r_v_1ps + verb.getConjunctiv().getSing1());
        verb.getConjunctiv().setSing2(SurmiranPronouns.pron_conjunctiv_c + SurmiranPronouns.pron_2ps + SurmiranPronouns.pron_r_v_2ps + verb.getConjunctiv().getSing2());
        verb.getConjunctiv().setSing3(SurmiranPronouns.pron_conjunctiv_v + SurmiranPronouns.pron_3ps + SurmiranPronouns.pron_r_v_3ps + verb.getConjunctiv().getSing3());
        verb.getConjunctiv().setPlural1(SurmiranPronouns.pron_conjunctiv_c + SurmiranPronouns.pron_1pp + SurmiranPronouns.pron_r_v_1pp + verb.getConjunctiv().getPlural1());
        verb.getConjunctiv().setPlural2(SurmiranPronouns.pron_conjunctiv_c + SurmiranPronouns.pron_2pp + SurmiranPronouns.pron_r_v_2pp + verb.getConjunctiv().getPlural2());
        verb.getConjunctiv().setPlural3(SurmiranPronouns.pron_conjunctiv_v + SurmiranPronouns.pron_3pp + SurmiranPronouns.pron_r_v_3pp + verb.getConjunctiv().getPlural3());

        // CUNDIZIONAL
        verb.getCundiziunal().setSing1(SurmiranPronouns.pron_1ps + SurmiranPronouns.pron_r_v_1ps + verb.getCundiziunal().getSing1());
        verb.getCundiziunal().setSing2(SurmiranPronouns.pron_2ps + SurmiranPronouns.pron_r_v_2ps + verb.getCundiziunal().getSing2());
        verb.getCundiziunal().setSing3(SurmiranPronouns.pron_3ps + SurmiranPronouns.pron_r_v_3ps + verb.getCundiziunal().getSing3());
        verb.getCundiziunal().setPlural1(SurmiranPronouns.pron_1pp + SurmiranPronouns.pron_r_v_1pp + verb.getCundiziunal().getPlural1());
        verb.getCundiziunal().setPlural2(SurmiranPronouns.pron_2pp + SurmiranPronouns.pron_r_v_2pp + verb.getCundiziunal().getPlural2());
        verb.getCundiziunal().setPlural3(SurmiranPronouns.pron_3pp + SurmiranPronouns.pron_r_v_3pp + verb.getCundiziunal().getPlural3());

        // IMPERATIV
        verb.getImperativ().setSingular(SurmiranPronouns.pron_r_v_2ps + verb.getImperativ().getSingular());
        verb.getImperativ().setPlural(SurmiranPronouns.pron_r_v_2pp + verb.getImperativ().getPlural());

        // PARTICIP_PERFECT
        verb.getParticipPerfect().setMs(verb.getParticipPerfect().getMs());
        verb.getParticipPerfect().setFs(verb.getParticipPerfect().getFs());
        verb.getParticipPerfect().setMp(verb.getParticipPerfect().getMp());
        verb.getParticipPerfect().setFp(verb.getParticipPerfect().getFp());

        // GERUNDIUM
        verb.setGerundium(verb.getGerundium());

        // FUTUR
        verb.getFutur().setSing1(SurmiranPronouns.pron_1ps + verb.getFutur().getSing1());
        verb.getFutur().setSing2(SurmiranPronouns.pron_2ps + verb.getFutur().getSing2());
        verb.getFutur().setSing3(SurmiranPronouns.pron_3ps + verb.getFutur().getSing3());
        verb.getFutur().setPlural1(SurmiranPronouns.pron_1pp + verb.getFutur().getPlural1());
        verb.getFutur().setPlural2(SurmiranPronouns.pron_2pp + verb.getFutur().getPlural2());
        verb.getFutur().setPlural3(SurmiranPronouns.pron_3pp + verb.getFutur().getPlural3());

        if (verb.getInfinitiv().startsWith("sa ")) {
            addReflexivePronounsAndCopyEncliticForms(false);

        } else if (verb.getInfinitiv().startsWith("s'")) {
            addReflexivePronounsAndCopyEncliticForms(true);
        }
    }

    private void addStandardPronouns() {

        // PRESCHENT
        verb.getPreschent().setSing1(SurmiranPronouns.pron_1ps + verb.getPreschent().getSing1());
        verb.getPreschent().setSing2(SurmiranPronouns.pron_2ps + verb.getPreschent().getSing2());
        verb.getPreschent().setSing3(SurmiranPronouns.pron_3ps + verb.getPreschent().getSing3());
        verb.getPreschent().setPlural1(SurmiranPronouns.pron_1pp + verb.getPreschent().getPlural1());
        verb.getPreschent().setPlural2(SurmiranPronouns.pron_2pp + verb.getPreschent().getPlural2());
        verb.getPreschent().setPlural3(SurmiranPronouns.pron_3pp + verb.getPreschent().getPlural3());

        // IMPERFECT
        verb.getImperfect().setSing1(SurmiranPronouns.pron_1ps + verb.getImperfect().getSing1());
        verb.getImperfect().setSing2(SurmiranPronouns.pron_2ps + verb.getImperfect().getSing2());
        verb.getImperfect().setSing3(SurmiranPronouns.pron_3ps + verb.getImperfect().getSing3());
        verb.getImperfect().setPlural1(SurmiranPronouns.pron_1pp + verb.getImperfect().getPlural1());
        verb.getImperfect().setPlural2(SurmiranPronouns.pron_2pp + verb.getImperfect().getPlural2());
        verb.getImperfect().setPlural3(SurmiranPronouns.pron_3pp + verb.getImperfect().getPlural3());

        // CONJUNCTIV
        verb.getConjunctiv().setSing1(SurmiranPronouns.pron_conjunctiv_v + SurmiranPronouns.pron_1ps + verb.getConjunctiv().getSing1());
        verb.getConjunctiv().setSing2(SurmiranPronouns.pron_conjunctiv_c + SurmiranPronouns.pron_2ps + verb.getConjunctiv().getSing2());
        verb.getConjunctiv().setSing3(SurmiranPronouns.pron_conjunctiv_v + SurmiranPronouns.pron_3ps + verb.getConjunctiv().getSing3());
        verb.getConjunctiv().setPlural1(SurmiranPronouns.pron_conjunctiv_c + SurmiranPronouns.pron_1pp + verb.getConjunctiv().getPlural1());
        verb.getConjunctiv().setPlural2(SurmiranPronouns.pron_conjunctiv_c + SurmiranPronouns.pron_2pp + verb.getConjunctiv().getPlural2());
        verb.getConjunctiv().setPlural3(SurmiranPronouns.pron_conjunctiv_v + SurmiranPronouns.pron_3pp + verb.getConjunctiv().getPlural3());

        // CUNDIZIONAL
        verb.getCundiziunal().setSing1(SurmiranPronouns.pron_1ps + verb.getCundiziunal().getSing1());
        verb.getCundiziunal().setSing2(SurmiranPronouns.pron_2ps + verb.getCundiziunal().getSing2());
        verb.getCundiziunal().setSing3(SurmiranPronouns.pron_3ps + verb.getCundiziunal().getSing3());
        verb.getCundiziunal().setPlural1(SurmiranPronouns.pron_1pp + verb.getCundiziunal().getPlural1());
        verb.getCundiziunal().setPlural2(SurmiranPronouns.pron_2pp + verb.getCundiziunal().getPlural2());
        verb.getCundiziunal().setPlural3(SurmiranPronouns.pron_3pp + verb.getCundiziunal().getPlural3());

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
        verb.getFutur().setSing1(SurmiranPronouns.pron_1ps + verb.getFutur().getSing1());
        verb.getFutur().setSing2(SurmiranPronouns.pron_2ps + verb.getFutur().getSing2());
        verb.getFutur().setSing3(SurmiranPronouns.pron_3ps + verb.getFutur().getSing3());
        verb.getFutur().setPlural1(SurmiranPronouns.pron_1pp + verb.getFutur().getPlural1());
        verb.getFutur().setPlural2(SurmiranPronouns.pron_2pp + verb.getFutur().getPlural2());
        verb.getFutur().setPlural3(SurmiranPronouns.pron_3pp + verb.getFutur().getPlural3());
    }

    private void addReflexivePronounsAndCopyEncliticForms(boolean isVowel) {
        if (isVowel) {
            verb.getPreschentEnclitic().setSing1(SurmiranPronouns.pron_r_v_1ps + verb.getPreschentEnclitic().getSing1());
            verb.getPreschentEnclitic().setSing2(SurmiranPronouns.pron_r_v_2ps + verb.getPreschentEnclitic().getSing2());
            verb.getPreschentEnclitic().setSing3m(SurmiranPronouns.pron_r_v_3ps + verb.getPreschentEnclitic().getSing3m());
            verb.getPreschentEnclitic().setSing3f(SurmiranPronouns.pron_r_v_3ps + verb.getPreschentEnclitic().getSing3f());
            verb.getPreschentEnclitic().setPlural1(SurmiranPronouns.pron_r_v_1pp + verb.getPreschentEnclitic().getPlural1());
            verb.getPreschentEnclitic().setPlural2(SurmiranPronouns.pron_r_v_2pp + verb.getPreschentEnclitic().getPlural2());
            verb.getPreschentEnclitic().setPlural3(SurmiranPronouns.pron_r_v_3pp + verb.getPreschentEnclitic().getPlural3());

            verb.getImperfectEnclitic().setSing1(SurmiranPronouns.pron_r_v_1ps + verb.getImperfectEnclitic().getSing1());
            verb.getImperfectEnclitic().setSing2(SurmiranPronouns.pron_r_v_2ps + verb.getImperfectEnclitic().getSing2());
            verb.getImperfectEnclitic().setSing3m(SurmiranPronouns.pron_r_v_3ps + verb.getImperfectEnclitic().getSing3m());
            verb.getImperfectEnclitic().setSing3f(SurmiranPronouns.pron_r_v_3ps + verb.getImperfectEnclitic().getSing3f());
            verb.getImperfectEnclitic().setPlural1(SurmiranPronouns.pron_r_v_1pp + verb.getImperfectEnclitic().getPlural1());
            verb.getImperfectEnclitic().setPlural2(SurmiranPronouns.pron_r_v_2pp + verb.getImperfectEnclitic().getPlural2());
            verb.getImperfectEnclitic().setPlural3(SurmiranPronouns.pron_r_v_3pp + verb.getImperfectEnclitic().getPlural3());

            verb.getCundiziunalEnclitic().setSing1(SurmiranPronouns.pron_r_v_1ps + verb.getCundiziunalEnclitic().getSing1());
            verb.getCundiziunalEnclitic().setSing2(SurmiranPronouns.pron_r_v_2ps + verb.getCundiziunalEnclitic().getSing2());
            verb.getCundiziunalEnclitic().setSing3m(SurmiranPronouns.pron_r_v_3ps + verb.getCundiziunalEnclitic().getSing3m());
            verb.getCundiziunalEnclitic().setSing3f(SurmiranPronouns.pron_r_v_3ps + verb.getCundiziunalEnclitic().getSing3f());
            verb.getCundiziunalEnclitic().setPlural1(SurmiranPronouns.pron_r_v_1pp + verb.getCundiziunalEnclitic().getPlural1());
            verb.getCundiziunalEnclitic().setPlural2(SurmiranPronouns.pron_r_v_2pp + verb.getCundiziunalEnclitic().getPlural2());
            verb.getCundiziunalEnclitic().setPlural3(SurmiranPronouns.pron_r_v_3pp + verb.getCundiziunalEnclitic().getPlural3());
        } else {
            verb.getPreschentEnclitic().setSing1(SurmiranPronouns.pron_r_1ps + verb.getPreschentEnclitic().getSing1());
            verb.getPreschentEnclitic().setSing2(SurmiranPronouns.pron_r_2ps + verb.getPreschentEnclitic().getSing2());
            verb.getPreschentEnclitic().setSing3m(SurmiranPronouns.pron_r_3ps + verb.getPreschentEnclitic().getSing3m());
            verb.getPreschentEnclitic().setSing3f(SurmiranPronouns.pron_r_3ps + verb.getPreschentEnclitic().getSing3f());
            verb.getPreschentEnclitic().setPlural1(SurmiranPronouns.pron_r_1pp + verb.getPreschentEnclitic().getPlural1());
            verb.getPreschentEnclitic().setPlural2(SurmiranPronouns.pron_r_2pp + verb.getPreschentEnclitic().getPlural2());
            verb.getPreschentEnclitic().setPlural3(SurmiranPronouns.pron_r_3pp + verb.getPreschentEnclitic().getPlural3());

            verb.getImperfectEnclitic().setSing1(SurmiranPronouns.pron_r_1ps + verb.getImperfectEnclitic().getSing1());
            verb.getImperfectEnclitic().setSing2(SurmiranPronouns.pron_r_2ps + verb.getImperfectEnclitic().getSing2());
            verb.getImperfectEnclitic().setSing3m(SurmiranPronouns.pron_r_3ps + verb.getImperfectEnclitic().getSing3m());
            verb.getImperfectEnclitic().setSing3f(SurmiranPronouns.pron_r_3ps + verb.getImperfectEnclitic().getSing3f());
            verb.getImperfectEnclitic().setPlural1(SurmiranPronouns.pron_r_1pp + verb.getImperfectEnclitic().getPlural1());
            verb.getImperfectEnclitic().setPlural2(SurmiranPronouns.pron_r_2pp + verb.getImperfectEnclitic().getPlural2());
            verb.getImperfectEnclitic().setPlural3(SurmiranPronouns.pron_r_3pp + verb.getImperfectEnclitic().getPlural3());

            verb.getCundiziunalEnclitic().setSing1(SurmiranPronouns.pron_r_1ps + verb.getCundiziunalEnclitic().getSing1());
            verb.getCundiziunalEnclitic().setSing2(SurmiranPronouns.pron_r_2ps + verb.getCundiziunalEnclitic().getSing2());
            verb.getCundiziunalEnclitic().setSing3m(SurmiranPronouns.pron_r_3ps + verb.getCundiziunalEnclitic().getSing3m());
            verb.getCundiziunalEnclitic().setSing3f(SurmiranPronouns.pron_r_3ps + verb.getCundiziunalEnclitic().getSing3f());
            verb.getCundiziunalEnclitic().setPlural1(SurmiranPronouns.pron_r_1pp + verb.getCundiziunalEnclitic().getPlural1());
            verb.getCundiziunalEnclitic().setPlural2(SurmiranPronouns.pron_r_2pp + verb.getCundiziunalEnclitic().getPlural2());
            verb.getCundiziunalEnclitic().setPlural3(SurmiranPronouns.pron_r_3pp + verb.getCundiziunalEnclitic().getPlural3());
        }


        // futur forms already have the reflexive pronouns
        verb.getFuturEnclitic().setSing1(verb.getFuturEnclitic().getSing1());
        verb.getFuturEnclitic().setSing2(verb.getFuturEnclitic().getSing2());
        verb.getFuturEnclitic().setSing3m(verb.getFuturEnclitic().getSing3m());
        verb.getFuturEnclitic().setSing3f(verb.getFuturEnclitic().getSing3f());
        verb.getFuturEnclitic().setPlural1(verb.getFuturEnclitic().getPlural1());
        verb.getFuturEnclitic().setPlural2(verb.getFuturEnclitic().getPlural2());
        verb.getFuturEnclitic().setPlural3(verb.getFuturEnclitic().getPlural3());
    }

    private boolean endsOnGl() {
        return
                (verbClass.id.charAt(0) == '2' && infinitiv.endsWith("gler"))
                ||
                (verbClass.id.charAt(0) == '3' && infinitiv.endsWith("glier") && !verbClass.id.equals("3d"))
                ||
                (verbClass.id.charAt(0) == '5' && infinitiv.endsWith("gler"))
                ||
                (verbClass.id.charAt(0) == '6' && infinitiv.endsWith("gleir"));
    }

    private boolean endsOnGeir() {
        return
                (verbClass.id.equals("6a") && infinitiv.endsWith("geir"));
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
