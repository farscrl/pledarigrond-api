package ch.pledarigrond.inflection.generation.puter;

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
public class PuterAdjectiveGenerator extends LanguageAdjectiveGeneration {

    private static final Logger logger = LoggerFactory.getLogger(PuterAdjectiveGenerator.class);

    private PuterAdjectiveStructure as;

    private String baseForm;

    private String root;

    private String ending;

    private ArrayList<HashMap<String, String>> formsList;

    private HashMap<String, String> forms;

    public InflectionResponse generateForms(String adjectiveClass, String baseForm) {

        root = getRoot(baseForm, adjectiveClass);

        InflectionSubType subType = PuterAdjectiveClasses.getAdjectiveInflectionClass(adjectiveClass);
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

        if (baseForm.matches(".*, -(a|ra|bla|cra|na|ma|gia|glia|eda|ida)$")) {
            String[] parts = baseForm.split(", -");
            baseForm = parts[0];

            switch (parts[1]) {
                case "a":
                    return generateForms("1", baseForm);
                case "ra":
                    return generateForms("2", baseForm);
                case "bla":
                    return generateForms("3", baseForm);
                case "cra":
                    return generateForms("4", baseForm);
                case "na":
                    return generateForms("5", baseForm);
                case "ma":
                    return generateForms("6", baseForm);
                case "gia":
                    return generateForms("7", baseForm);
                case "glia":
                    return generateForms("8", baseForm);
                case "eda":
                    return generateForms("9", baseForm);
                case "ida":
                    return generateForms("10", baseForm);
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
            if (lastFourCharacters.equals("ffan") ||
                lastFourCharacters.equals("ffem") ||
                lastFourCharacters.equals("ffer") ||
                lastFourCharacters.equals("llan") ||
                lastFourCharacters.equals("llem") ||
                lastFourCharacters.equals("ller") ||
                lastFourCharacters.equals("ssan") ||
                lastFourCharacters.equals("ssem") ||
                lastFourCharacters.equals("sser") ||
                lastFourCharacters.equals("zzan") ||
                lastFourCharacters.equals("zzem") ||
                lastFourCharacters.equals("zzer")) {
                return generateForms("11", baseForm);
            }
        }

        // last 3 characters
        String lastThreeCharacters = baseForm.substring(length - 3);
        if (lastThreeCharacters.equals("car")) {
            return generateForms("4", baseForm);
        }
        if (lastThreeCharacters.equals("ieu")) {
            return generateForms("10", baseForm);
        }

        // last 2 characters
        String lastTwoCharacters = baseForm.substring(length - 2);
        if (lastTwoCharacters.equals("er")) {
            return generateForms("2", baseForm);
        }
        if (lastTwoCharacters.equals("el")) {
            return generateForms("3", baseForm);
        }
        if (lastTwoCharacters.equals("en")) {
            return generateForms("5", baseForm);
        }
        if (lastTwoCharacters.equals("em")) {
            return generateForms("6", baseForm);
        }
        if (lastTwoCharacters.equals("gl")) {
            return generateForms("8", baseForm);
        }
        char secondToTheLastCharacter = baseForm.charAt(length-2);
        char lastCharacter = baseForm.charAt(length-1);
        if (isVocal(secondToTheLastCharacter) && isDuplicatedConsonant(lastCharacter)) {
            return generateForms("12", baseForm);
        }

        // last characters
        if (baseForm.substring(length - 1).equals("g")) {
            return generateForms("7", baseForm);
        }
        if (baseForm.substring(length - 1).equals("o")) {
            return generateForms("9", baseForm);
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


            case "4":
            case "10":
            case "11":
                if (maleSingularForm.length() < 3) {
                    throw new RuntimeException("'" + maleSingularForm + "' is not a valid male singular form. Please enter a male singular form.");
                }
                ending = maleSingularForm.substring(maleSingularForm.length() - 3);
                return maleSingularForm.substring(0, maleSingularForm.length() - 3);

            case "2":
            case "3":
            case "5":
            case "6":
            case "8":
                ending = maleSingularForm.substring(maleSingularForm.length() - 2);
                return maleSingularForm.substring(0, maleSingularForm.length() - 2);

            case "7":
            case "9":
                ending = maleSingularForm.substring(maleSingularForm.length() - 1);
                return maleSingularForm.substring(0, maleSingularForm.length() - 1);

            case "12":
                ending = maleSingularForm.substring(maleSingularForm.length() - 1);
                return maleSingularForm;

            case "1":
            default:
                ending = "";
                return maleSingularForm;
        }
    }

    public HashMap<String, String> buildForms(String root, InflectionSubType adjectiveClass) {

        as = new PuterAdjectiveStructure();
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
        String l2 = base.substring(base.length() - 2);

        if (l2.equals("ur")) {
            return base.substring(0, base.length() - 1) + "ors";
        }

        if (l1.equals("è")) {
            return base.substring(0, base.length() - 1) + "els";
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

            case "er":
                as.setMSingular(root + "er");
                as.setFSingular(root + "ra");
                break;

            case "el":
                as.setMSingular(root + "el");
                as.setFSingular(root + "la");
                break;

            case "car":
                as.setMSingular(root + "car");
                as.setFSingular(root + "cra");
                break;

            case "en":
                as.setMSingular(root + "en");
                as.setFSingular(root + "na");
                break;

            case "em":
                as.setMSingular(root + "em");
                as.setFSingular(root + "ma");
                break;

            case "g":
                as.setMSingular(root + "g");
                as.setFSingular(root + "gia");
                break;

            case "gl":
                as.setMSingular(root + "gl");
                as.setFSingular(root + "glia");
                break;

            case "o":
                as.setMSingular(root + "o");
                as.setFSingular(root + "eda");
                break;

            case "ieu":
                as.setMSingular(root + "ieu");
                as.setFSingular(root + "ida");
                break;

            case "fan":
            case "lan":
            case "san":
            case "zan":
                as.setMSingular(root + ending);
                as.setFSingular(root + "na");
                break;
            case "fem":
            case "lem":
            case "sem":
            case "zem":
                as.setMSingular(root + ending);
                as.setFSingular(root + "ma");
                break;
            case "fer":
            case "ler":
            case "ser":
            case "zer":
                as.setMSingular(root + ending);
                as.setFSingular(root + "ra");
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

    public void setAdverbialForm() {
        String ending = as.getMSingular().substring(as.getMSingular().length() - 2);
        if (ending.equals("el") || ending.equals("er") || ending.equals("iv")) {
            as.setAdverbialForm(as.getMSingular() + "maing");
            return;
        }

        as.setAdverbialForm(as.getFSingular() + "maing");
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
