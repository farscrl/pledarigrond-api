package ch.pledarigrond.inflection.generation.sursilvan;

import ch.pledarigrond.inflection.generation.generic.LanguageNounGeneration;
import ch.pledarigrond.inflection.model.InflectionResponse;
import ch.pledarigrond.inflection.model.InflectionSubType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@EqualsAndHashCode(callSuper = true)
public class SursilvanNounGenerator extends LanguageNounGeneration {

    private static final Logger logger = LoggerFactory.getLogger(SursilvanNounGenerator.class);

    private SursilvanNounStructure ns;

    private String baseForm;

    private String root;

    private String ending;

    private ArrayList<HashMap<String, String>> formsList;

    private HashMap<String, String> forms;

    public InflectionResponse generateForms(String nounClass, String baseForm) {

        root = getRoot(baseForm, nounClass);

        InflectionSubType subType = SursilvanNounClasses.getNounInflectionClass(nounClass);
        if (subType == null) {
            throw new RuntimeException(nounClass + " is not a valid inflection class.");
        } else if (getEnding() == null) {
            throw new RuntimeException(baseForm + " is not a valid male singular form. Please enter a valid form.");
        }

        return new InflectionResponse(subType, buildForms(root, subType));
    }

    public InflectionResponse guessInflection(String baseForm, String genus, String flex) {
        baseForm = normalizeString(baseForm);
        int length = baseForm.length();
        if (length < 3) {
            return null;
        }

        if (baseForm.matches(".*, -(bla|alla|astra|ta|atta|ada|edra|na|ra|idra|tra|occa|utta|essa|cra|vla)$")) {
            String[] parts = baseForm.split(", -");
            baseForm = parts[0];

            switch (parts[1]) {
                case "bla":
                    return generateForms("4", baseForm);
                case "astra":
                    return generateForms("6", baseForm);
                case "ta":
                case "atta":
                    return generateForms("7", baseForm);
                case "ada":
                    return generateForms("8", baseForm);
                case "edra":
                    return generateForms("9", baseForm);
                case "na":
                    return generateForms("10", baseForm);
                case "ra":
                    return generateForms("11", baseForm);
                case "idra":
                    return generateForms("13", baseForm);
                case "tra":
                    return generateForms("15", baseForm);
                case "occa":
                    return generateForms("16", baseForm);
                case "utta":
                    return generateForms("17", baseForm);
                case "essa":
                    return generateForms("18", baseForm);
                case "cra":
                    return generateForms("19", baseForm);
                case "vla":
                    return generateForms("20", baseForm);

                default:
                    logger.error("unknown ending: {}", parts[1]);
                    return null;
            }
        }

        // do not generate forms if word contains whitespaces
        if (baseForm.matches(".*\\s+.*")) {
            return null;
        }

        // default, case genus is f
        if (genus != null && genus.equals("f")) {
            return generateForms("2", baseForm);
        }

        // last 5 characters
        if (length > 5) {
            String lastFiveCharacters = baseForm.substring(length - 5);
            if (lastFiveCharacters.equals("aster")) {
                return generateForms("6", baseForm);
            }

        }

        // last 4 characters
        if (length > 4) {
            String lastFourCharacters = baseForm.substring(length - 4);
            if (lastFourCharacters.equals("cher")) {
                return generateForms("19", baseForm);
            }
            if (lastFourCharacters.equals("ider")) {
                return generateForms("13", baseForm);
            }
            if (lastFourCharacters.equals("eder")) {
                return generateForms("9", baseForm);
            }
        }

        // last 3 characters
        String lastThreeCharacters = baseForm.substring(length - 3);
        if (lastThreeCharacters.equals("bel")) {
            return generateForms("4", baseForm);
        }
        if (lastThreeCharacters.equals("ter")) {
            return generateForms("15", baseForm);
        }
        if (lastThreeCharacters.equals("vel")) {
            return generateForms("20", baseForm);
        }

        // last 2 characters
        String lastTwoCharacters = baseForm.substring(length - 2);
        if (lastTwoCharacters.equals("at")) {
            // only for words like tat or mat.
            if (isSingleSyllable(baseForm)) {
                return generateForms("7", baseForm);
            }
            // regular for advocat, advocata
        }
        if (lastTwoCharacters.equals("au")) {
            return generateForms("8", baseForm);
        }
        if (lastTwoCharacters.equals("en")) {
            return generateForms("10", baseForm);
        }
        if (lastTwoCharacters.equals("er")) {
            return generateForms("11", baseForm);
        }
        if (lastTwoCharacters.equals("oc")) {
            return generateForms("16", baseForm);
        }
        if (lastTwoCharacters.equals("ut")) {
            return generateForms("17", baseForm);
        }
        if (lastTwoCharacters.equals("on")) {
            return generateForms("18", baseForm);
        }
        if (lastTwoCharacters.equals("un")) {
            return generateForms("18", baseForm);
        }

        return generateForms("3", baseForm);
    }

    public String getRoot(String maleSingularForm, String nounClass) {
        if (maleSingularForm == null) {
            return maleSingularForm;
        }

        maleSingularForm = normalizeString(maleSingularForm);
        baseForm = maleSingularForm;

        if (maleSingularForm.length() < 2) {
            throw new RuntimeException("'" + maleSingularForm + "' is not a valid male singular form. Please enter a male singular form.");
        }

        return extractRoot(maleSingularForm, nounClass);
    }

    public String extractRoot(String maleSingularForm, String nounClass) {
        switch (nounClass) {

            case "6":
                if (maleSingularForm.length() < 5) {
                    throw new RuntimeException("'" + maleSingularForm + "' is not a valid male singular form. Please enter a male singular form.");
                }
                ending = maleSingularForm.substring(maleSingularForm.length() - 5);
                return maleSingularForm.substring(0, maleSingularForm.length() - 5);

            case "9":
            case "13":
            case "19":
                if (maleSingularForm.length() < 4) {
                    throw new RuntimeException("'" + maleSingularForm + "' is not a valid male singular form. Please enter a male singular form.");
                }
                ending = maleSingularForm.substring(maleSingularForm.length() - 4);
                return maleSingularForm.substring(0, maleSingularForm.length() - 4);

            case "4":
            case "15":
            case "20":
                if (maleSingularForm.length() < 3) {
                    throw new RuntimeException("'" + maleSingularForm + "' is not a valid male singular form. Please enter a male singular form.");
                }
                ending = maleSingularForm.substring(maleSingularForm.length() - 3);
                return maleSingularForm.substring(0, maleSingularForm.length() - 3);

            case "7":
            case "8":
            case "10":
            case "11":
            case "16":
            case "17":
                ending = maleSingularForm.substring(maleSingularForm.length() - 2);
                return maleSingularForm.substring(0, maleSingularForm.length() - 2);

            case "18":
                if (maleSingularForm.endsWith("t")) {
                    ending = maleSingularForm.substring(maleSingularForm.length() - 1);
                    return maleSingularForm;
                }
                ending = maleSingularForm.substring(maleSingularForm.length() - 2);
                return maleSingularForm;

            case "1":
            case "2":
            case "3":
            case "21":
            default:
                ending = "";
                return maleSingularForm;
        }
    }

    public HashMap<String, String> buildForms(String root, InflectionSubType nounClass) {

        ns = new SursilvanNounStructure();
        ns.setBaseForm(baseForm);
        ns.setInflectionSubType(nounClass);

        ns.setRoot(root);
        ns.setEnding(getEnding());
        ns.setNounClass(nounClass.id);

        setSingular();
        setPlural();

        return ns.getAllFormValues();
    }

    public String buildPlural(String base) {
        String l1 = base.substring(base.length() - 1);

        if (base.contains("-")) {
            String[] s = base.split("-", 2);
            return  buildPlural(s[0]) + "-" + s[1];
        }

        if (base.endsWith("schi") || base.endsWith("gni")) {
            return base.substring(0, base.length() - 1) + "als";
        }

        if (base.endsWith("i")) {
            return base + "als";
        }

        if (base.endsWith("gliel")) {
            return base.substring(0, base.length() - 2) + "euls";
        }

        if (base.endsWith("iel")) {
            return base.substring(0, base.length() - 3) + "euls";
        }

        if (isSingleSyllable(base)) {
            // for single syllable fiep -> fops
            Pattern pattern = Pattern.compile(".*ie([b-df-hj-np-tv-z]+)$");
            Matcher matcher = pattern.matcher(base);
            if (matcher.matches()) {
                String consonant = matcher.group(1);
                if (base.endsWith("schie" + consonant) || base.endsWith("gnie" + consonant)) {
                    return base.substring(0, base.length() - 2 - consonant.length()) + "a" + consonant + "s";
                }
                return base.substring(0, base.length() - 2 - consonant.length()) + "o" + consonant + "s";
            }
        } else {
            // for multi syllable paliet -> paliats
            Pattern pattern = Pattern.compile(".*ie([b-df-hj-np-tv-z]+)$");
            Matcher matcher = pattern.matcher(base);
            if (matcher.matches()) {
                String consonant = matcher.group(1);
                return base.substring(0, base.length() - 2 - consonant.length()) + "ia" + consonant + "s";
            }
        }


        if (l1.equals("s")) {
            return base;
        }

        return base + "s";
    }

    public void setSingular() {
        switch (ns.getEnding()) {
            case "":
                if (ns.getNounClass().equals("1")) {
                    ns.setMSingular(root);
                    ns.setFSingular(null);
                }
                if (ns.getNounClass().equals("2")) {
                    ns.setMSingular(null);
                    ns.setFSingular(root);
                }
                if (ns.getNounClass().equals("3")) {
                    ns.setMSingular(root);
                    ns.setFSingular(root + "a");
                }
                if (ns.getNounClass().equals("21")) {
                    ns.setMSingular(root);
                    ns.setFSingular(null);
                }
                break;

            case "bel":
                ns.setMSingular(root + "bel");
                ns.setFSingular(root + "bla");
                break;

            case "aster":
                ns.setMSingular(root + "aster");
                ns.setFSingular(root + "astra");
                break;

            case "at":
                ns.setMSingular(root + "at");
                ns.setFSingular(root + "atta");
                break;

            case "au":
                ns.setMSingular(root + "au");
                ns.setFSingular(root + "ada");
                break;

            case "eder":
                ns.setMSingular(root + "eder");
                ns.setFSingular(root + "edra");
                break;

            case "en":
                ns.setMSingular(root + "en");
                ns.setFSingular(root + "na");
                break;

            case "er":
                ns.setMSingular(root + "er");
                ns.setFSingular(root + "ra");
                break;

            case "ider":
                ns.setMSingular(root + "ider");
                ns.setFSingular(root + "idra");
                break;

            case "ter":
                ns.setMSingular(root + "ter");
                ns.setFSingular(root + "tra");
                break;

            case "oc":
                ns.setMSingular(root + "oc");
                ns.setFSingular(root + "occa");
                break;

            case "on":
            case "un":
            case "t":
                ns.setMSingular(root);
                ns.setFSingular(root + "essa");
                break;

            case "cher":
                ns.setMSingular(root + "cher");
                ns.setFSingular(root + "cra");
                break;

            case "vel":
                ns.setMSingular(root + "vel");
                ns.setFSingular(root + "vla");
                break;

            case "ut":
                ns.setMSingular(root + "ut");
                ns.setFSingular(root + "utta");
                break;
        }
    }

    public void setPlural() {
        // collectiv
        if (ns.getNounClass().equals("21")) {
            ns.setMPlural(buildPlural(ns.getMSingular()));
            ns.setPluralCollectiv(ns.getMSingular() + "a");
            return;
        }

        if (!ns.getNounClass().equals("2")) {
            ns.setMPlural(buildPlural(ns.getMSingular()));
        } else {
            ns.setMPlural(null);
        }

        if (!ns.getNounClass().equals("1")) {
            ns.setFPlural(buildPlural(ns.getFSingular()));
        } else {
            ns.setFPlural(null);
        }
    }


    public void printForms(Map<String, String> forms, String form) throws IOException {

        File file = new File("data/output/" + form + ".txt");

        // creating directory and file if not existing
        file.getParentFile().mkdirs();
        file.createNewFile();

        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));

        out.append(forms.get("root"));
        out.append("\n");
        out.append(forms.get("nounClass"));
        out.append("\n");
        out.append("\n");

        out.append("MS");
        out.append("\n");
        out.append(forms.get("mSingular"));
        out.append("\n");
        out.append("\n");

        out.append("FS");
        out.append("\n");
        out.append(forms.get("fSingular"));
        out.append("\n");
        out.append("\n");

        out.append("MP");
        out.append("\n");
        out.append(forms.get("mPlural"));
        out.append("\n");
        out.append("\n");

        out.append("FP");
        out.append("\n");
        out.append(forms.get("fPlural"));
        out.append("\n");
        out.append("\n");

        out.close();
    }

    public <K, V> File printMap(Map<K, V> map, String destPath, String fileName) throws IOException {

        File file = new File(destPath + fileName + ".txt");
        Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), StandardCharsets.UTF_8));

        for (Map.Entry<K, V> entry : map.entrySet()) {
            out
                    .append(String.valueOf(entry.getKey()))
                    .append(" : ")
                    .append(String.valueOf(entry.getValue()))
                    .append("\n");
        }

        out.flush();
        out.close();

        return file;
    }

    public <T> File printSet(Set<T> set, String destPath, String filename) throws IOException {

        File file = new File(destPath + filename + ".txt");

        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));

        for (Object o : set) {
            writer
                    .append(String.valueOf(o))
                    .append("\n");
        }

        writer.flush();
        writer.close();

        return file;
    }

    private boolean isDuplicatedConsonant(char ch) {
        return switch (ch) {
            case 'c', 'f', 'm', 'n', 'p', 'r', 't', 'z' -> true;
            default -> false;
        };
    }
}
