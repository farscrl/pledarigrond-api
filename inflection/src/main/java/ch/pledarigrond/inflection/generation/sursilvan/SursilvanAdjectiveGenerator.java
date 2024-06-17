package ch.pledarigrond.inflection.generation.sursilvan;

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
public class SursilvanAdjectiveGenerator extends LanguageAdjectiveGeneration {

    private static final Logger logger = LoggerFactory.getLogger(SursilvanAdjectiveGenerator.class);

    private SursilvanAdjectiveStructure as;

    private String baseForm;

    private String root;

    private String ending;

    private ArrayList<HashMap<String, String>> formsList;

    private HashMap<String, String> forms;

    public InflectionResponse generateForms(String adjectiveClass, String baseForm) {

        root = getRoot(baseForm, adjectiveClass);

        InflectionSubType subType = SursilvanAdjectiveClasses.getAdjectiveInflectionClass(adjectiveClass);
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

        if (baseForm.matches(".*, -(el|al|en|an|er|iu|au)$")) {
            String[] parts = baseForm.split(", -");
            baseForm = parts[0];

            switch (parts[1]) {
                case "el":
                    return generateForms("2", baseForm);
                case "al":
                    return generateForms("3", baseForm);
                case "en":
                    return generateForms("4", baseForm);
                case "an":
                    return generateForms("5", baseForm);
                case "er":
                    return generateForms("6", baseForm);
                case "iu":
                    return generateForms("7", baseForm);
                case "au":
                    return generateForms("8", baseForm);
                default:
                    logger.error("unknown ending: {}", parts[1]);
                    return null;
            }
        }

        // do not generate forms if word contains whitespaces
        if (baseForm.matches(".*\\s+.*")) {
            return null;
        }

        // last 2 characters
        String lastTwoCharacters = baseForm.substring(length - 2);
        if (lastTwoCharacters.equals("el")) {
            return generateForms("2", baseForm);
        }
        if (lastTwoCharacters.equals("al")) {
            return generateForms("3", baseForm);
        }
        if (lastTwoCharacters.equals("en")) {
            return generateForms("4", baseForm);
        }
        if (lastTwoCharacters.equals("an")) {
            return generateForms("5", baseForm);
        }
        if (lastTwoCharacters.equals("er")) {
            return generateForms("6", baseForm);
        }
        if (lastTwoCharacters.equals("iu")) {
            return generateForms("7", baseForm);
        }
        if (lastTwoCharacters.equals("au")) {
            return generateForms("8", baseForm);
        }

        char secondToTheLastCharacter = baseForm.charAt(length-2);
        char lastCharacter = baseForm.charAt(length-1);
        if (isVocal(secondToTheLastCharacter) && isDuplicatedConsonant(lastCharacter)) {
            return generateForms("10", baseForm);
        }

        return generateForms("1", baseForm);
    }

    public String getRoot(String maleSingularForm, String adjectiveClass) {
        if (maleSingularForm == null) {
            return maleSingularForm;
        }

        maleSingularForm = normalizeString(maleSingularForm);
        baseForm = maleSingularForm;

        if (maleSingularForm.length() < 2) {
            throw new RuntimeException("'" + maleSingularForm + "' is not a valid male singular form. Please enter a male singular form.");
        }

        return extractRoot(maleSingularForm, adjectiveClass);
    }

    public String extractRoot(String maleSingularForm, String adjectiveClass) {
        switch (adjectiveClass) {
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
                ending = maleSingularForm.substring(maleSingularForm.length() - 2);
                return maleSingularForm.substring(0, maleSingularForm.length() - 2);

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

        as = new SursilvanAdjectiveStructure();
        as.setBaseForm(baseForm);
        as.setInflectionSubType(adjectiveClass);

        as.setRoot(root);
        as.setEnding(getEnding());
        as.setAdjectiveClass(adjectiveClass.id);

        setSingular();
        setPlural();
        setAdverbialForm();

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
                as.setPredicative(root + "s");
                as.setFSingular(root + "a");
                break;

            case "el":
                as.setMSingular(root + "el");
                as.setPredicative(root + "els");
                as.setFSingular(root + "la");
                break;

            case "al":
                as.setMSingular(root + "al");
                as.setPredicative(root + "als");
                as.setFSingular(root + "la");
                break;

            case "en":
                as.setMSingular(root + "en");
                as.setPredicative(root + "ens");
                as.setFSingular(root + "na");
                break;

            case "an":
                as.setMSingular(root + "an");
                as.setPredicative(root + "ans");
                as.setFSingular(root + "na");
                break;

            case "er":
                as.setMSingular(root + "er");
                as.setPredicative(root + "ers");
                as.setFSingular(root + "ra");
                break;

            case "iu":
                as.setMSingular(root + "iu");
                as.setPredicative(root + "ius");
                as.setFSingular(root + "ida");
                break;

            case "au":
                as.setMSingular(root + "au");
                as.setPredicative(root + "aus");
                as.setFSingular(root + "ada");
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
                as.setPredicative(root + "s");
                as.setFSingular(root + ending + "a");
        }
    }

    public void setPlural() {
        as.setMPlural(buildPlural(as.getMSingular()));
        as.setFPlural(buildPlural(as.getFSingular()));
    }

    public void setAdverbialForm() {
        String ending = as.getMSingular().substring(as.getMSingular().length() - 2);
        if (ending.equals("al") || ending.equals("ar")) {
            as.setAdverbialForm(as.getMSingular() + "mein");
            return;
        }

        as.setAdverbialForm(as.getFSingular() + "mein");
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
