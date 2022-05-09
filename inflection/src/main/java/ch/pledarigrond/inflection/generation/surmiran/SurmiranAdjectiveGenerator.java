package ch.pledarigrond.inflection.generation.surmiran;

import ch.pledarigrond.inflection.generation.generic.LanguageAdjectiveGeneration;
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

@Data
@EqualsAndHashCode(callSuper = true)
public class SurmiranAdjectiveGenerator extends LanguageAdjectiveGeneration {

    private static final Logger logger = LoggerFactory.getLogger(SurmiranAdjectiveGenerator.class);

    private SurmiranAdjectiveStructure as;

    private String root;

    private String ending;

    private ArrayList<HashMap<String, String>> formsList;

    private HashMap<String, String> forms;

    public InflectionResponse generateForms(String adjectiveClass, String baseForm) {

        root = getRoot(baseForm, adjectiveClass);

        InflectionSubType subType = SurmiranAdjectiveClasses.getAdjectiveInflectionClass(adjectiveClass);
        if (subType == null) {
            throw new RuntimeException(adjectiveClass + " is not a valid conjugation class.");
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

        if (baseForm.matches(".*, -(a|ada|dra|vla|bla|cra|vna|eida|eda)$")) {
            String[] parts = baseForm.split(", -");
            baseForm = parts[0];

            switch (parts[1]) {
                case "a":
                    return generateForms("1", baseForm);
                case "dra":
                    return generateForms("2", baseForm);
                case "bla":
                    return generateForms("3", baseForm);
                case "cra":
                    return generateForms("4", baseForm);
                case "vla":
                    return generateForms("5", baseForm);
                case "vna":
                    return generateForms("6", baseForm);
                case "eida":
                    return generateForms("7", baseForm);
                case "ada":
                    return generateForms("8", baseForm);
                case "eda":
                    return generateForms("9", baseForm);
                default:
                    logger.error("unknown ending: {}", parts[1]);
                    return null;
            }
        }

        // do not generate forms if word contains whitespaces
        if (baseForm.matches(".*\\s+.*")) {
            return null;
        }

        // last 4 characters
        if (length > 4) {
            String lastFourCharacters = baseForm.substring(length - 4);
            if (lastFourCharacters.equals("cher")) {
                return generateForms("4", baseForm);
            }
        }

        // last 3 characters
        String lastThreeCharacters = baseForm.substring(length - 3);
        if (lastThreeCharacters.equals("der")) {
            return generateForms("2", baseForm);
        }
        if (lastThreeCharacters.equals("bel")) {
            return generateForms("3", baseForm);
        }
        if (lastThreeCharacters.equals("vel")) {
            return generateForms("5", baseForm);
        }
        if (lastThreeCharacters.equals("ven")) {
            return generateForms("6", baseForm);
        }

        // last 2 characters
        String lastTwoCharacters = baseForm.substring(length - 2);
        if (lastTwoCharacters.equals("ia")) {
            return generateForms("7", baseForm);
        }
        if (lastTwoCharacters.equals("ea")) {
            return generateForms("9", baseForm);
        }
        char secondToTheLastCharacter = baseForm.charAt(length-2);
        char lastCharacter = baseForm.charAt(length-1);
        if (isVocal(secondToTheLastCharacter) && isDuplicatedConsonant(lastCharacter)) {
            return generateForms("10", baseForm);
        }

        // last characters
        if (baseForm.substring(length - 1).equals("o")) {
            return generateForms("8", baseForm);
        }

        return generateForms("1", baseForm);
    }

    public String getRoot(String maleSingularForm, String adjectiveClass) {
        if (maleSingularForm == null) {
            return maleSingularForm;
        }

        maleSingularForm = normalizeString(maleSingularForm);

        if (maleSingularForm.length() < 2) {
            throw new RuntimeException("'" + maleSingularForm + "' is not a valid male singular form. Please enter a male singular form.");
        }

        return extractRoot(maleSingularForm, adjectiveClass);
    }

    public String extractRoot(String maleSingularForm, String adjectiveClass) {
        switch (adjectiveClass) {

            case "4":
                if (maleSingularForm.length() < 4) {
                    throw new RuntimeException("'" + maleSingularForm + "' is not a valid male singular form. Please enter a male singular form.");
                }
                ending = maleSingularForm.substring(maleSingularForm.length() - 4);
                return maleSingularForm.substring(0, maleSingularForm.length() - 4);

            case "2":
            case "3":
            case "5":
            case "6":
                if (maleSingularForm.length() < 3) {
                    throw new RuntimeException("'" + maleSingularForm + "' is not a valid male singular form. Please enter a male singular form.");
                }
                ending = maleSingularForm.substring(maleSingularForm.length() - 3);
                return maleSingularForm.substring(0, maleSingularForm.length() - 3);

            case "7":
            case "9":
                ending = maleSingularForm.substring(maleSingularForm.length() - 2);
                return maleSingularForm.substring(0, maleSingularForm.length() - 2);

            case "8":

                ending = maleSingularForm.substring(maleSingularForm.length() - 1);
                return maleSingularForm.substring(0, maleSingularForm.length() - 1);

            case "10":
                ending = maleSingularForm.substring(maleSingularForm.length() - 1);
                return maleSingularForm;

            case "1":
            default:
                ending = "";
                return maleSingularForm;
        }
    }

    public HashMap<String, String> buildForms(String root, InflectionSubType adjectiveClass) {

        as = new SurmiranAdjectiveStructure();
        as.setInflectionSubType(adjectiveClass);

        as.setRoot(root);
        as.setEnding(getEnding());
        as.setAdjectiveClass(adjectiveClass.id);

        setSingular();
        setPlural();

        return as.getAllFormValues();

    }

    public String buildPlural(String base) {
        String l1 = base.substring(base.length() - 1);

        if (base.contains("-")) {
            String[] s = base.split("-", 2);
            return  buildPlural(s[0]) + "-" + s[1];
        }

        if (l1.equals("s")) {
            return base;
        }

        return base + "s";
    }

    public void setSingular() {
        switch (as.getEnding()) {
            case "":
                as.setMSingular(root);
                as.setFSingular(root + "a");
                break;

            case "der":
                as.setMSingular(root + "der");
                as.setFSingular(root + "dra");
                break;

            case "bel":
                as.setMSingular(root + "bel");
                as.setFSingular(root + "bla");
                break;

            case "cher":
                as.setMSingular(root + "cher");
                as.setFSingular(root + "cra");
                break;

            case "vel":
                as.setMSingular(root + "vel");
                as.setFSingular(root + "vla");
                break;

            case "ven":
                as.setMSingular(root + "ven");
                as.setFSingular(root + "vna");
                break;

            case "ia":
                as.setMSingular(root + "ia");
                as.setFSingular(root + "eida");
                break;

            case "o":
                as.setMSingular(root + "o");
                as.setFSingular(root + "ada");
                break;

            case "ea":
                as.setMSingular(root + "ea");
                as.setFSingular(root + "eda");
                break;

            case "c":
            case "f":
            case "m":
            case "n":
            case "p":
            case "r":
            case "t":
            case "z":
                as.setMSingular(root);
                as.setFSingular(root + ending + "a");
        }
    }

    public void setPlural() {
        as.setMPlural(buildPlural(as.getMSingular()));
        as.setFPlural(buildPlural(as.getFSingular()));
    }


    public void printForms(Map<String, String> forms, String form) throws IOException {

        File file = new File("data/output/" + form + ".txt");

        // creating directory and file if not existing
        file.getParentFile().mkdirs();
        file.createNewFile();

        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));

        out.append(forms.get("root"));
        out.append("\n");
        out.append(forms.get("adjectiveClass"));
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
