package ch.pledarigrond.inflection.generation.verb.sutsilvan;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.common.data.dictionary.inflection.InflectionType;
import ch.pledarigrond.common.data.dictionary.inflection.VerbDto;
import ch.pledarigrond.inflection.generation.verb.LanguageConjugation;
import ch.pledarigrond.inflection.model.InflectionSubType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class SutsilvanConjugation extends LanguageConjugation {

    private static final Logger logger = LoggerFactory.getLogger(SutsilvanConjugation.class);

    private InflectionDto inflection;
    private VerbDto verb;
    private InflectionSubType verbClass;

    private String infinitiv;
    private boolean isReflexive;

    private String root;
    private String modRoot;
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
        ending = null;
    }

    @Override
    public InflectionDto generateConjugation(String conjugationClass, String infinitivForm) {
        reset();

        verb.setInfinitiv(infinitivForm);
        checkReflexiveness(infinitivForm);
        root = getRoot(infinitivForm);

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

        if(infinitivForm.endsWith("gler")) {
            modRoot = modRoot + "i";
        }


        InflectionSubType subType = SutsilvanConjugationClasses.getConjugationClass(conjugationClass);
        if (subType == null) {
            throw new RuntimeException(conjugationClass + " is not a valid conjugation class.");
        } else if (ending == null) {
            throw new RuntimeException(infinitivForm + "  is not a valid verb. Please type a verb in its infinitive form.");
        }

        verbClass = subType;
        inflection.setInflectionSubtype(verbClass.id);

        if(needsBindingIEnding()) {
            modRoot = modRoot + "i";
        }

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

        baseForm = normalizeString(baseForm);

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

            return generateConjugation("1", baseForm);
        }

        if (lastTwo.equals("er")) {
            return generateConjugation("3", baseForm);
        }

        if (lastTwo.equals("ir")) {
            if (flex != null && flex.equals("-escha")) {
                return generateConjugation("7", baseForm);
            }

            return generateConjugation("4", baseForm);
        }

        return null;
    }

    private String getRoot(String infinitivForm) {
        if (infinitivForm != null) {
            infinitivForm = normalizeString(infinitivForm);
            infinitiv = infinitivForm;
            if (infinitivForm.equals("ir") || infinitivForm.equals("er")) {
                ending = infinitivForm;
                return infinitivForm;
            }
            HashSet<String> avoid;
            switch (infinitivForm.length()) {
                case 0:
                case 1:
                case 2:
                    throw new RuntimeException("'" + infinitivForm + "' is not a valid verb. Please type a verb in its infinitive form.");
                default:
                    avoid = new HashSet<>();
                    avoid.add("near");
                    avoid.add("sa");
                    if (avoid.contains(infinitivForm)) {
                        throw new RuntimeException("'" + infinitivForm + "' is not a valid verb. Please type a verb in its infinitive form.");
                    }
                    infinitivForm = processQuery(infinitivForm);
            }
        }
        return infinitivForm;
    }

    private String processQuery(String infinitiv) {

        String l2 = infinitiv.substring(infinitiv.length() - 2);
        String l3 = infinitiv.substring(infinitiv.length() - 3);

        switch (l2) {
            case "ar":
            case "er":
            case "ir":
                if (!l3.equals("ear")) {
                    ending = l2;
                    infinitiv = infinitiv.substring(0, infinitiv.length() - 2);
                    return infinitiv;
                }
                break;
        }

        if (l3.equals("ear")) {
            ending = l3;
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

    private void checkReflexiveness(String query) {
        if (query.startsWith("se")) {
            isReflexive = true;
        } else {
            isReflexive = false;
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

        setPreschentEnclitic();
        setImperfectEnclitic();
        setCundizionalEnclitic();
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
                if(endsOnGl()) {
                    preschent.setSing1(modRoot.substring(0, modRoot.length() - 1) + "\n" + modRoot + "el");
                } else if(needsBindingIEnding()) {
                    preschent.setSing1(modRoot.substring(0, modRoot.length() - 1) + "\n" + modRoot.substring(0, modRoot.length() - 1) + "el");
                } else {
                    preschent.setSing1(modRoot + "\n" + modRoot + "el");
                }

                preschent.setSing2(modRoot + "as");
                preschent.setSing3(modRoot + "a");

                if (needsBindingIEnding()) {
                    preschent.setPlural1(root + "iagn");
                } else {
                    preschent.setPlural1(root + "agn");
                }

                // 2pp
                if (verbClass.id.charAt(0) == '1') {
                    preschent.setPlural2(root + "az");
                } else if (verbClass.id.charAt(0) == '2') {
                    preschent.setPlural2(root + "eaz");
                } else {
                    preschent.setPlural2(root + "ez");
                }

                preschent.setPlural3(modRoot + "an");
                break;

            case "3a":
            case "3b":
            case "3c":
            case "3d":
            case "3e":
                preschent.setSing1(root + "\n" + root + "el");
                preschent.setSing2(root + "as");
                preschent.setSing3(root + "a");
                preschent.setPlural1(modRoot + "agn");
                preschent.setPlural2(modRoot + "ez");
                preschent.setPlural3(root + "an");
                break;

            case "5":
            case "6":
            case "7":
                preschent.setSing1(root + "esch\n" + root + "eschel");
                preschent.setSing2(root + "eschas");
                preschent.setSing3(root + "escha");
                preschent.setPlural1(root + "agn");
                // 2pp
                if (verbClass.id.equals("5")) {
                    preschent.setPlural2(root + "az");
                } else if (verbClass.id.equals("6")) {
                    preschent.setPlural2(root + "eaz");
                } else {
                    preschent.setPlural2(root + "ez");
                }
                preschent.setPlural3(root + "eschan");
                break;

            case "8":
                preschent.setSing1(modRoot + "\n" + modRoot + "el");
                preschent.setSing2(modRoot + "as");
                preschent.setSing3(modRoot + "a");
                preschent.setPlural1(root + "agn");
                preschent.setPlural2(root + "az");
                preschent.setPlural3(modRoot + "an");
                break;
        }

        verb.setPreschent(preschent);
    }

    private void setImperfect() {
        VerbDto.PersonalVerbDto imperfect = new VerbDto.PersonalVerbDto();

        switch (verbClass.id.substring(0, 1)) {
            case "1":
            case "5":
            case "8":
                imperfect.setSing1(root + "ava");
                imperfect.setSing2(root + "avas");
                imperfect.setSing3(root + "ava");
                imperfect.setPlural1(root + "avan");
                imperfect.setPlural2(root + "avas");
                imperfect.setPlural3(root + "avan");
                break;

            case "2":
            case "6":
                imperfect.setSing1(root + "eava");
                imperfect.setSing2(root + "eavas");
                imperfect.setSing3(root + "eava");
                imperfect.setPlural1(root + "eavan");
                imperfect.setPlural2(root + "eavas");
                imperfect.setPlural3(root + "eavan");
                break;

            /*case "3":
            case "4":
            case "7":*/
            default:
                if (verbClass.id.equals("3a") || verbClass.id.equals("3b") || verbClass.id.equals("3c") || verbClass.id.equals("3d") || verbClass.id.equals("3e")) {
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
        }

        verb.setImperfect(imperfect);
    }

    private void setConjunctiv() {
        VerbDto.PersonalVerbDto conjunctiv = new VerbDto.PersonalVerbDto();

        switch (verbClass.id.substring(0, 1)) {
            case "5":
            case "6":
            case "7":
                conjunctiv.setSing1(root + "eschi");
                conjunctiv.setSing2(root + "eschias\n" + root + "eschas");
                conjunctiv.setSing3(root + "eschi");
                conjunctiv.setPlural1(root + "eian");
                conjunctiv.setPlural2(root + "eias");
                conjunctiv.setPlural3(root + "eschian\n" + root + "eschan");
                break;

            //case "8":
            default:
                if (verbClass.id.equals("3a") || verbClass.id.equals("3b") || verbClass.id.equals("3c") || verbClass.id.equals("3d") || verbClass.id.equals("3e")) {
                    conjunctiv.setSing1(root + "i");
                    conjunctiv.setSing2(root + "ias\n" + root + "as");
                    conjunctiv.setSing3(root + "i");
                    conjunctiv.setPlural1(modRoot + "eian");
                    conjunctiv.setPlural2(modRoot + "eias");
                    conjunctiv.setPlural3(root + "ian\n" + root + "an");
                    break;
                }
                if (endsOnGl() || needsBindingIEnding()) {
                    conjunctiv.setSing1(modRoot);
                    conjunctiv.setSing2(modRoot + "as");
                    conjunctiv.setSing3(modRoot);
                } else {
                    conjunctiv.setSing1(modRoot + "i");
                    conjunctiv.setSing2(modRoot + "ias\n" + modRoot + "as");
                    conjunctiv.setSing3(modRoot + "i");
                }

                conjunctiv.setPlural1(root + "eian");
                conjunctiv.setPlural2(root + "eias");
                if (endsOnGl() || needsBindingIEnding()) {
                    conjunctiv.setPlural3(modRoot + "an");
                } else {
                    conjunctiv.setPlural3(modRoot + "ian\n" + modRoot + "an");
                }

                break;

            /*case "1":
            case "2":
            case "3":
            case "4":
                conjunctiv.setSing1(root + "i");
                conjunctiv.setSing2(root + "ias\n" + root + "as");
                conjunctiv.setSing3(root + "i");
                conjunctiv.setPlural1(root + "eian");
                conjunctiv.setPlural2(root + "eias");
                conjunctiv.setPlural3(root + "ian\n" + root + "an");
                break;*/
        }

        verb.setConjunctiv(conjunctiv);
    }

    private void setConjunctiv2() {
        VerbDto.PersonalVerbDto conjunctiv2 = new VerbDto.PersonalVerbDto();

        switch (verbClass.id.substring(0, 1)) {
            case "8":
                conjunctiv2.setSing1(modRoot + "avi");
                conjunctiv2.setSing2(modRoot + "avias\n" + modRoot + "avas");
                conjunctiv2.setSing3(modRoot + "avi");
                conjunctiv2.setPlural1(modRoot + "avian\n" + modRoot + "avan");
                conjunctiv2.setPlural2(modRoot + "avias\n" + modRoot + "avas");
                conjunctiv2.setPlural3(modRoot + "avian\n" + modRoot + "avan");
                break;

            case "1":
            case "5":
                conjunctiv2.setSing1(root + "avi");
                conjunctiv2.setSing2(root + "avias\n" + root + "avas");
                conjunctiv2.setSing3(root + "avi");
                conjunctiv2.setPlural1(root + "avian\n" + root + "avan");
                conjunctiv2.setPlural2(root + "avias\n" + root + "avas");
                conjunctiv2.setPlural3(root + "avian\n" + root + "avan");
                break;

            case "2":
                conjunctiv2.setSing1(root + "eavi");
                conjunctiv2.setSing2(root + "eavias\n" + root + "eavas");
                conjunctiv2.setSing3(root + "eavi");
                conjunctiv2.setPlural1(root + "eavian\n" + root + "eavan");
                conjunctiv2.setPlural2(root + "eavias\n" + root + "eavas");
                conjunctiv2.setPlural3(root + "eavian\n" + root + "eavan");
                break;

            /*case "3":
            case "4":
            case "6":
            case "7":*/
            default:
                if (verbClass.id.equals("3a") || verbClass.id.equals("3b") || verbClass.id.equals("3c") || verbClass.id.equals("3d") || verbClass.id.equals("3e")) {
                    conjunctiv2.setSing1(modRoot + "evi");
                    conjunctiv2.setSing2(modRoot + "evias\n" + modRoot + "evas");
                    conjunctiv2.setSing3(modRoot + "evi");
                    conjunctiv2.setPlural1(modRoot + "evian\n" + modRoot + "evan");
                    conjunctiv2.setPlural2(modRoot + "evias\n" + modRoot + "evas");
                    conjunctiv2.setPlural3(modRoot + "evian\n" + modRoot + "evan");
                    break;
                }

                conjunctiv2.setSing1(root + "evi");
                conjunctiv2.setSing2(root + "evias\n" + root + "evas");
                conjunctiv2.setSing3(root + "evi");
                conjunctiv2.setPlural1(root + "evian\n" + root + "evan");
                conjunctiv2.setPlural2(root + "evias\n" + root + "evas");
                conjunctiv2.setPlural3(root + "evian\n" + root + "evan");
                break;
        }

        verb.setConjunctivImperfect(conjunctiv2);
    }

    private void setCundizional() {
        VerbDto.PersonalVerbDto cundizional = new VerbDto.PersonalVerbDto();

        switch (verbClass.id.substring(0, 1)) {
            case "1":
            case "5":
            case "8":
                cundizional.setSing1(root + "ass");
                cundizional.setSing2(root + "assas");
                cundizional.setSing3(root + "ass");
                cundizional.setPlural1(root + "assan");
                cundizional.setPlural2(root + "assas");
                cundizional.setPlural3(root + "assan");
                break;

            /*case "2":
            case "3":
            case "4":
            case "6":
            case "7":*/
            default:
                if (verbClass.id.equals("3a") || verbClass.id.equals("3b") || verbClass.id.equals("3c") || verbClass.id.equals("3d") || verbClass.id.equals("3e")) {
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

    private void setCundizionalIndirect() {
        VerbDto.PersonalVerbDto cundizionalIndirect = new VerbDto.PersonalVerbDto();

        switch (verbClass.id.substring(0, 1)) {
            case "1":
            case "5":
            case "8":
                cundizionalIndirect.setSing1(root + "assi");
                cundizionalIndirect.setSing2(root + "assias\n" + root + "assas");
                cundizionalIndirect.setSing3(root + "assi");
                cundizionalIndirect.setPlural1(root + "assian\n" + root + "assan");
                cundizionalIndirect.setPlural2(root + "assias\n" + root + "assas");
                cundizionalIndirect.setPlural3(root + "assian\n" + root + "assan");
                break;

                /*
            case "2":
            case "3":
            case "4":
            case "6":
            case "7":
            */
            default:
                if (verbClass.id.equals("3a") || verbClass.id.equals("3b") || verbClass.id.equals("3c") || verbClass.id.equals("3d") || verbClass.id.equals("3e")) {
                    cundizionalIndirect.setSing1(modRoot + "essi");
                    cundizionalIndirect.setSing2(modRoot + "essias\n" + modRoot + "essas");
                    cundizionalIndirect.setSing3(modRoot + "essi");
                    cundizionalIndirect.setPlural1(modRoot + "essian\n" + modRoot + "essan");
                    cundizionalIndirect.setPlural2(modRoot + "essias\n" + modRoot + "essas");
                    cundizionalIndirect.setPlural3(modRoot + "essian\n" + modRoot + "essan");
                    break;
                }
                cundizionalIndirect.setSing1(root + "essi");
                cundizionalIndirect.setSing2(root + "essias\n" + root + "essas");
                cundizionalIndirect.setSing3(root + "essi");
                cundizionalIndirect.setPlural1(root + "essian\n" + root + "essan");
                cundizionalIndirect.setPlural2(root + "essias\n" + root + "essas");
                cundizionalIndirect.setPlural3(root + "essian\n" + root + "essan");
                break;
        }

        verb.setCundiziunalIndirect(cundizionalIndirect);
    }

    private void setParticipPerfect() {
        VerbDto.ParticipPerfectDto participPerfect = new VerbDto.ParticipPerfectDto();

        switch (verbClass.id.substring(0, 1)) {
            case "1":
            case "5":
            case "8":
                participPerfect.setMs(root + "o");
                participPerfect.setFs(root + "ada");
                participPerfect.setMp(root + "os");
                participPerfect.setFp(root + "adas");
                break;

            case "2":
            case "6":
                if (endsOnGl()) {
                    participPerfect.setMs(root + "eu");
                    participPerfect.setFs(root + "eada");
                    participPerfect.setMp(root + "eus");
                    participPerfect.setFp(root + "eadas");
                    break;
                }
                participPerfect.setMs(root + "ieu");
                participPerfect.setFs(root + "eada");
                participPerfect.setMp(root + "ieus");
                participPerfect.setFp(root + "eadas");
                break;

            /*case "3":
            case "4":
            case "7":*/
            default:
                if (verbClass.id.equals("3a") || verbClass.id.equals("3b") || verbClass.id.equals("3c") || verbClass.id.equals("3d") || verbClass.id.equals("3e")) {
                    participPerfect.setMs(modRoot + "ieu");
                    participPerfect.setFs(modRoot + "ida");
                    participPerfect.setMp(modRoot + "ieus");
                    participPerfect.setFp(modRoot + "idas");
                    break;
                }
                participPerfect.setMs(root + "ieu");
                participPerfect.setFs(root + "ida");
                participPerfect.setMp(root + "ieus");
                participPerfect.setFp(root + "idas");
                break;
        }

        verb.setParticipPerfect(participPerfect);
    }

    private void setGerundium() {
        if (verbClass.id.equals("3a") || verbClass.id.equals("3b") || verbClass.id.equals("3c") || verbClass.id.equals("3d") || verbClass.id.equals("3e")) {
            verb.setGerundium(modRoot + "ànd");
            return;
        }
        if(needsBindingIEnding()) {
            verb.setGerundium(root + "iànd");
            return;
        }
        verb.setGerundium(root + "ànd");
    }

    private void setImperativ() {
        VerbDto.ImperativDto imperativ = new VerbDto.ImperativDto();

        switch (verbClass.id.substring(0, 1)) {
            /*case "1":
                imperativ.setSingular(root + "a!");
                imperativ.setPlural(root + "ad!");
                break;*/

            case "2":
                imperativ.setSingular(modRoot + "a!");
                imperativ.setPlural(root + "ead!");
                break;

            case "3":
            case "4":
            case "9":
                if (verbClass.id.equals("3a") || verbClass.id.equals("3b") || verbClass.id.equals("3c") || verbClass.id.equals("3d") || verbClass.id.equals("3e")) {
                    imperativ.setSingular(root + "a!");
                    imperativ.setPlural(modRoot + "ed!");
                    break;
                }
                imperativ.setSingular(modRoot + "a!");
                imperativ.setPlural(root + "ed!");
                break;

            case "5":
                imperativ.setSingular(root + "escha!");
                imperativ.setPlural(root + "ad!");
                break;

            case "6":
                imperativ.setSingular(root + "escha!");
                imperativ.setPlural(root + "ead!");
                break;

            case "7":
                imperativ.setSingular(root + "escha!");
                imperativ.setPlural(root + "ed!");
                break;

            //case "8":
            default:
                imperativ.setSingular(modRoot + "a!");
                imperativ.setPlural(root + "ad!");
                break;
        }

        verb.setImperativ(imperativ);
    }

    private void setFutur() {
        VerbDto.PersonalVerbDto futur = new VerbDto.PersonalVerbDto();

        char startChar = root.charAt(0);
        if (isVocal(startChar)) {
            futur.setSing1(SutsilvanPronouns.prefix_futur_1ps_v + infinitiv);
            futur.setSing2(SutsilvanPronouns.prefix_futur_2ps_v + infinitiv);
            futur.setSing3(SutsilvanPronouns.prefix_futur_3ps_v + infinitiv);
            futur.setPlural1(SutsilvanPronouns.prefix_futur_1pp_v + infinitiv);
            futur.setPlural2(SutsilvanPronouns.prefix_futur_2pp_v + infinitiv);
            futur.setPlural3(SutsilvanPronouns.prefix_futur_3pp_v + infinitiv);
        } else {
            futur.setSing1(SutsilvanPronouns.prefix_futur_1ps + infinitiv);
            futur.setSing2(SutsilvanPronouns.prefix_futur_2ps + infinitiv);
            futur.setSing3(SutsilvanPronouns.prefix_futur_3ps + infinitiv);
            futur.setPlural1(SutsilvanPronouns.prefix_futur_1pp + infinitiv);
            futur.setPlural2(SutsilvanPronouns.prefix_futur_2pp + infinitiv);
            futur.setPlural3(SutsilvanPronouns.prefix_futur_3pp + infinitiv);
        }

        verb.setFutur(futur);
    }

    private void setPreschentEnclitic() {
        VerbDto.PersonalVerbEncliticDto preschentEnclitic = new VerbDto.PersonalVerbEncliticDto();

        String preschentsing1 = verb.getPreschent().getSing1();
        if (preschentsing1.contains("\n")) {
            preschentsing1 = (preschentsing1.split("\n"))[0];
        }
        preschentEnclitic.setSing1(preschentsing1 + "iou\n" + preschentsing1 + " jou");
        preschentEnclitic.setSing2(verb.getPreschent().getSing2() );
        preschentEnclitic.setSing3m(verb.getPreschent().getSing3());
        preschentEnclitic.setSing3f(verb.getPreschent().getSing3());
        preschentEnclitic.setPlural1(verb.getPreschent().getPlural1().substring(0, verb.getPreschent().getPlural1().length() - 3) + "ainsa");
        preschentEnclitic.setPlural2(verb.getPreschent().getPlural2());
        preschentEnclitic.setPlural3(verb.getPreschent().getPlural3() + "i");

        verb.setPreschentEnclitic(preschentEnclitic);
    }

    private void setImperfectEnclitic() {
        VerbDto.PersonalVerbEncliticDto imperfectEnclitic = new VerbDto.PersonalVerbEncliticDto();

        imperfectEnclitic.setSing1(verb.getImperfect().getSing1().substring(0, verb.getImperfect().getSing1().length() - 1) + "iou");
        imperfectEnclitic.setSing2(verb.getImperfect().getSing2());
        imperfectEnclitic.setSing3m(verb.getImperfect().getSing3());
        imperfectEnclitic.setSing3f(verb.getImperfect().getSing3());
        imperfectEnclitic.setPlural1(verb.getImperfect().getPlural1());
        imperfectEnclitic.setPlural2(verb.getImperfect().getPlural2());
        imperfectEnclitic.setPlural3(verb.getImperfect().getPlural3() + "i");

        verb.setImperfectEnclitic(imperfectEnclitic);
    }

    private void setCundizionalEnclitic() {
        VerbDto.PersonalVerbEncliticDto cundizionalEnclitic = new VerbDto.PersonalVerbEncliticDto();

        cundizionalEnclitic.setSing1(verb.getCundiziunal().getSing1() + "iou\n" + verb.getCundiziunal().getSing1() + " jou");
        cundizionalEnclitic.setSing2(verb.getCundiziunal().getSing2());
        cundizionalEnclitic.setSing3m(verb.getCundiziunal().getSing3());
        cundizionalEnclitic.setSing3f(verb.getCundiziunal().getSing3());
        cundizionalEnclitic.setPlural1(verb.getCundiziunal().getPlural1());
        cundizionalEnclitic.setPlural2(verb.getCundiziunal().getPlural2());
        cundizionalEnclitic.setPlural3(verb.getCundiziunal().getPlural3() + "i");

        verb.setCundizionalEnclitic(cundizionalEnclitic);
    }

    private void addPronouns() {

        // PRESCHENT
        verb.getPreschent().setSing1(setPronoun(SutsilvanPronouns.pron_1ps, verb.getPreschent().getSing1()));
        verb.getPreschent().setSing2(setPronoun(SutsilvanPronouns.pron_2ps, verb.getPreschent().getSing2()));
        verb.getPreschent().setSing3(setPronoun(SutsilvanPronouns.pron_3ps, verb.getPreschent().getSing3()));
        verb.getPreschent().setPlural1(setPronoun(SutsilvanPronouns.pron_1pp, verb.getPreschent().getPlural1()));
        verb.getPreschent().setPlural2(setPronoun(SutsilvanPronouns.pron_2pp, verb.getPreschent().getPlural2()));
        verb.getPreschent().setPlural3(setPronoun(SutsilvanPronouns.pron_3pp, verb.getPreschent().getPlural3()));

        // IMPERFECT
        verb.getImperfect().setSing1(setPronoun(SutsilvanPronouns.pron_1ps, verb.getImperfect().getSing1()));
        verb.getImperfect().setSing2(setPronoun(SutsilvanPronouns.pron_2ps, verb.getImperfect().getSing2()));
        verb.getImperfect().setSing3(setPronoun(SutsilvanPronouns.pron_3ps, verb.getImperfect().getSing3()));
        verb.getImperfect().setPlural1(setPronoun(SutsilvanPronouns.pron_1pp, verb.getImperfect().getPlural1()));
        verb.getImperfect().setPlural2(setPronoun(SutsilvanPronouns.pron_2pp, verb.getImperfect().getPlural2()));
        verb.getImperfect().setPlural3(setPronoun(SutsilvanPronouns.pron_3pp, verb.getImperfect().getPlural3()));

        // CONJUNCTIV
        verb.getConjunctiv().setSing1(setPronoun(SutsilvanPronouns.pron_conjunctiv_c + SutsilvanPronouns.pron_1ps, verb.getConjunctiv().getSing1()));
        verb.getConjunctiv().setSing2(setPronoun(SutsilvanPronouns.pron_conjunctiv_c + SutsilvanPronouns.pron_2ps, verb.getConjunctiv().getSing2()));
        verb.getConjunctiv().setSing3(setPronoun(SutsilvanPronouns.pron_conjunctiv_v + SutsilvanPronouns.pron_3ps, verb.getConjunctiv().getSing3()));
        verb.getConjunctiv().setPlural1(setPronoun(SutsilvanPronouns.pron_conjunctiv_c + SutsilvanPronouns.pron_1pp, verb.getConjunctiv().getPlural1()));
        verb.getConjunctiv().setPlural2(setPronoun(SutsilvanPronouns.pron_conjunctiv_c + SutsilvanPronouns.pron_2pp, verb.getConjunctiv().getPlural2()));
        verb.getConjunctiv().setPlural3(setPronoun(SutsilvanPronouns.pron_conjunctiv_v + SutsilvanPronouns.pron_3pp, verb.getConjunctiv().getPlural3()));

        // CONJUNCTIV IMPERFECT
        verb.getConjunctivImperfect().setSing1(setPronoun(SutsilvanPronouns.pron_conjunctiv_c + SutsilvanPronouns.pron_1ps, verb.getConjunctivImperfect().getSing1()));
        verb.getConjunctivImperfect().setSing2(setPronoun(SutsilvanPronouns.pron_conjunctiv_c + SutsilvanPronouns.pron_2ps, verb.getConjunctivImperfect().getSing2()));
        verb.getConjunctivImperfect().setSing3(setPronoun(SutsilvanPronouns.pron_conjunctiv_v + SutsilvanPronouns.pron_3ps, verb.getConjunctivImperfect().getSing3()));
        verb.getConjunctivImperfect().setPlural1(setPronoun(SutsilvanPronouns.pron_conjunctiv_c + SutsilvanPronouns.pron_1pp, verb.getConjunctivImperfect().getPlural1()));
        verb.getConjunctivImperfect().setPlural2(setPronoun(SutsilvanPronouns.pron_conjunctiv_c + SutsilvanPronouns.pron_2pp, verb.getConjunctivImperfect().getPlural2()));
        verb.getConjunctivImperfect().setPlural3(setPronoun(SutsilvanPronouns.pron_conjunctiv_v + SutsilvanPronouns.pron_3pp, verb.getConjunctivImperfect().getPlural3()));

        // CUNDIZIONAL
        verb.getCundiziunal().setSing1(setPronoun(SutsilvanPronouns.pron_1ps, verb.getCundiziunal().getSing1()));
        verb.getCundiziunal().setSing2(setPronoun(SutsilvanPronouns.pron_2ps, verb.getCundiziunal().getSing2()));
        verb.getCundiziunal().setSing3(setPronoun(SutsilvanPronouns.pron_3ps, verb.getCundiziunal().getSing3()));
        verb.getCundiziunal().setPlural1(setPronoun(SutsilvanPronouns.pron_1pp, verb.getCundiziunal().getPlural1()));
        verb.getCundiziunal().setPlural2(setPronoun(SutsilvanPronouns.pron_2pp, verb.getCundiziunal().getPlural2()));
        verb.getCundiziunal().setPlural3(setPronoun(SutsilvanPronouns.pron_3pp, verb.getCundiziunal().getPlural3()));

        // CUNDIZIONAL INDIRECT
        verb.getCundiziunalIndirect().setSing1(setPronoun(SutsilvanPronouns.pron_1ps, verb.getCundiziunalIndirect().getSing1()));
        verb.getCundiziunalIndirect().setSing2(setPronoun(SutsilvanPronouns.pron_2ps, verb.getCundiziunalIndirect().getSing2()));
        verb.getCundiziunalIndirect().setSing3(setPronoun(SutsilvanPronouns.pron_3ps, verb.getCundiziunalIndirect().getSing3()));
        verb.getCundiziunalIndirect().setPlural1(setPronoun(SutsilvanPronouns.pron_1pp, verb.getCundiziunalIndirect().getPlural1()));
        verb.getCundiziunalIndirect().setPlural2(setPronoun(SutsilvanPronouns.pron_2pp, verb.getCundiziunalIndirect().getPlural2()));
        verb.getCundiziunalIndirect().setPlural3(setPronoun(SutsilvanPronouns.pron_3pp, verb.getCundiziunalIndirect().getPlural3()));

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
        verb.getFutur().setSing1(setPronoun(SutsilvanPronouns.pron_1ps, verb.getFutur().getSing1()));
        verb.getFutur().setSing2(setPronoun(SutsilvanPronouns.pron_2ps, verb.getFutur().getSing2()));
        verb.getFutur().setSing3(setPronoun(SutsilvanPronouns.pron_3ps, verb.getFutur().getSing3()));
        verb.getFutur().setPlural1(setPronoun(SutsilvanPronouns.pron_1pp, verb.getFutur().getPlural1()));
        verb.getFutur().setPlural2(setPronoun(SutsilvanPronouns.pron_2pp, verb.getFutur().getPlural2()));
        verb.getFutur().setPlural3(setPronoun(SutsilvanPronouns.pron_3pp, verb.getFutur().getPlural3()));

        // ENCLITIC: PRESCHENT
        verb.getPreschentEnclitic().setSing1(verb.getPreschentEnclitic().getSing1());
        verb.getPreschentEnclitic().setSing2(verb.getPreschentEnclitic().getSing2());
        verb.getPreschentEnclitic().setSing3m(verb.getPreschentEnclitic().getSing3m());
        verb.getPreschentEnclitic().setSing3f(verb.getPreschentEnclitic().getSing3f());
        verb.getPreschentEnclitic().setPlural1(verb.getPreschentEnclitic().getPlural1());
        verb.getPreschentEnclitic().setPlural2(verb.getPreschentEnclitic().getPlural2());
        verb.getPreschentEnclitic().setPlural3(verb.getPreschentEnclitic().getPlural3());

        // ENCLITIC: IMPERFECT
        verb.getImperfectEnclitic().setSing1(verb.getImperfectEnclitic().getSing1());
        verb.getImperfectEnclitic().setSing2(verb.getImperfectEnclitic().getSing2());
        verb.getImperfectEnclitic().setSing3m(verb.getImperfectEnclitic().getSing3m());
        verb.getImperfectEnclitic().setSing3f(verb.getImperfectEnclitic().getSing3f());
        verb.getImperfectEnclitic().setPlural1(verb.getImperfectEnclitic().getPlural1());
        verb.getImperfectEnclitic().setPlural2(verb.getImperfectEnclitic().getPlural2());
        verb.getImperfectEnclitic().setPlural3(verb.getImperfectEnclitic().getPlural3());

        // ENCLITIC: CUNDIZIUNAL
        verb.getCundizionalEnclitic().setSing1(verb.getCundizionalEnclitic().getSing1());
        verb.getCundizionalEnclitic().setSing2(verb.getCundizionalEnclitic().getSing2());
        verb.getCundizionalEnclitic().setSing3m(verb.getCundizionalEnclitic().getSing3m());
        verb.getCundizionalEnclitic().setSing3f(verb.getCundizionalEnclitic().getSing3f());
        verb.getCundizionalEnclitic().setPlural1(verb.getCundizionalEnclitic().getPlural1());
        verb.getCundizionalEnclitic().setPlural2(verb.getCundizionalEnclitic().getPlural2());
        verb.getCundizionalEnclitic().setPlural3(verb.getCundizionalEnclitic().getPlural3());
    }

    private boolean endsOnGl() {
        return (verbClass.id.charAt(0) == '2' && infinitiv.endsWith("gliear"));
    }

    private boolean needsBindingIEnding() {
        return (verbClass.id.startsWith("2") && infinitiv.endsWith("gear") && !infinitiv.endsWith("tgear")) ||
                (verbClass.id.startsWith("3") && infinitiv.endsWith("ger") && !infinitiv.endsWith("tger")) ||
                (verbClass.id.startsWith("4") && infinitiv.endsWith("gir") && !infinitiv.endsWith("tgir"))
                ;
    }

    protected String normalizeString(String query) {
        if (query.charAt(0) == '[') {
            query = query.substring(1, query.length() - 1);
        }
        query = query.replaceAll("^\\s+|\\s+$", "");
        return query;
    }
}
