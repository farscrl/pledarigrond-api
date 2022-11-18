package ch.pledarigrond.inflection.generation.sutsilvan;

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
public class SutsilvanAdjectiveGenerator extends LanguageAdjectiveGeneration {

    private static final Logger logger = LoggerFactory.getLogger(SutsilvanAdjectiveGenerator.class);

    private SutsilvanAdjectiveStructure as;

    private String baseForm;

    private String root;

    private String ending;

    private ArrayList<HashMap<String, String>> formsList;

    private HashMap<String, String> forms;

    public InflectionResponse generateForms(String adjectiveClass, String baseForm) {

        root = getRoot(baseForm, adjectiveClass);

        InflectionSubType subType = SutsilvanAdjectiveClasses.getAdjectiveInflectionClass(adjectiveClass);
        if (subType == null) {
            throw new RuntimeException(adjectiveClass + " is not a valid adjective class.");
        } else if (getEnding() == null) {
            throw new RuntimeException(baseForm + " is not a valid singular form. Please enter a valid form.");
        }

        return new InflectionResponse(subType, buildForms(root, subType));
    }

    public InflectionResponse guessInflection(String baseForm, String genus, String flex) {
        baseForm = normalizeString(baseForm);
        int length = baseForm.length();
        if (length < 3) {
            return null;
        }

        if (baseForm.matches(".*, -(a|dra|bla|gra|vla|vna|ida|ada|cla|vra|frna)$")) {
            String[] parts = baseForm.split(", -");
            baseForm = parts[0];

            switch (parts[1]) {
                case "a":
                    return generateForms("1", baseForm);
                case "dra":
                    return generateForms("2", baseForm);
                case "bla":
                    return generateForms("3", baseForm);
                case "gra":
                    return generateForms("4", baseForm);
                case "vla":
                    return generateForms("5", baseForm);
                case "vna":
                    return generateForms("6", baseForm);
                case "ida":
                    return generateForms("7", baseForm);
                case "ada":
                    return generateForms("8", baseForm);
                case "cla":
                    return generateForms("9", baseForm);
                case "vra":
                    return generateForms("10", baseForm);
                case "frna":
                    return generateForms("11", baseForm);
                default:
                    logger.error("unknown ending: {}", parts[1]);
                    return null;
            }
        }

        // do not generate forms if word contains whitespaces
        if (baseForm.matches(".*\\s+.*")) {
            return null;
        }

        // last 5 characters
        if (length > 5) {
            String lastFiveCharacters = baseForm.substring(length - 5);
            if (lastFiveCharacters.equals("glieu")) {
                return generateForms("12", baseForm);
            }
            if (lastFiveCharacters.equals("tgieu")) {
                return generateForms("13", baseForm);
            }
        }

        // last 4 characters
        if (length > 4) {
            String lastFourCharacters = baseForm.substring(length - 4);
            if (lastFourCharacters.equals("gher")) {
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
        if (lastThreeCharacters.equals("ida")) {
            return generateForms("7", baseForm);
        }
        if (lastThreeCharacters.equals("cel")) {
            return generateForms("9", baseForm);
        }
        if (lastThreeCharacters.equals("ver")) {
            return generateForms("10", baseForm);
        }
        if (lastThreeCharacters.equals("fen")) {
            return generateForms("11", baseForm);
        }

        // last character
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
        baseForm = maleSingularForm;

        if (maleSingularForm.length() < 2) {
            throw new RuntimeException("'" + maleSingularForm + "' is not a valid singular form. Please enter a male singular form.");
        }

        return extractRoot(maleSingularForm, adjectiveClass);
    }

    public String extractRoot(String maleSingularForm, String adjectiveClass) {
        switch (adjectiveClass) {
            case "12":
            case "13":
                if (maleSingularForm.length() < 5) {
                    throw new RuntimeException("'" + maleSingularForm + "' is not a valid singular form. Please enter a male singular form.");
                }
                ending = maleSingularForm.substring(maleSingularForm.length() - 5);
                return maleSingularForm.substring(0, maleSingularForm.length() - 5);

            case "4":
                if (maleSingularForm.length() < 4) {
                    throw new RuntimeException("'" + maleSingularForm + "' is not a valid singular form. Please enter a male singular form.");
                }
                ending = maleSingularForm.substring(maleSingularForm.length() - 4);
                return maleSingularForm.substring(0, maleSingularForm.length() - 4);

            case "2":
            case "3":
            case "5":
            case "6":
            case "7":
            case "9":
            case "10":
            case "11":
                if (maleSingularForm.length() < 3) {
                    throw new RuntimeException("'" + maleSingularForm + "' is not a valid singular form. Please enter a male singular form.");
                }
                ending = maleSingularForm.substring(maleSingularForm.length() - 3);
                return maleSingularForm.substring(0, maleSingularForm.length() - 3);

            case "8":
                ending = maleSingularForm.substring(maleSingularForm.length() - 1);
                return maleSingularForm.substring(0, maleSingularForm.length() - 1);

            case "1":
            default:
                ending = "";
                return maleSingularForm;
        }
    }

    public HashMap<String, String> buildForms(String root, InflectionSubType adjectiveClass) {

        as = new SutsilvanAdjectiveStructure();
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

        if (l1.equals("i")) {
            return base.substring(0, base.length() - 1) + "eals";
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

            case "gher":
                as.setMSingular(root + "gher");
                as.setFSingular(root + "gra");
                break;

            case "vel":
                as.setMSingular(root + "vel");
                as.setFSingular(root + "vla");
                break;

            case "ven":
                as.setMSingular(root + "ven");
                as.setFSingular(root + "vna");
                break;

            case "ieu":
                as.setMSingular(root + "ieu");
                as.setFSingular(root + "ida");
                break;

            case "o":
                as.setMSingular(root + "o");
                as.setFSingular(root + "ada");
                break;

            case "cel":
                as.setMSingular(root + "cel");
                as.setFSingular(root + "cla");
                break;

            case "ver":
                as.setMSingular(root + "ver");
                as.setFSingular(root + "vra");
                break;

            case "fen":
                as.setMSingular(root + "fen");
                as.setFSingular(root + "fna");
                break;

            case "glieu":
                as.setMSingular(root + "glieu");
                as.setFSingular(root + "glieada");
                break;

            case "tgieu":
                as.setMSingular(root + "tgieu");
                as.setFSingular(root + "tgeada");
                break;
        }
    }

    public void setPlural() {
        as.setMPlural(buildPlural(as.getMSingular()));
        as.setFPlural(buildPlural(as.getFSingular()));
    }

    public void setAdverbialForm() {
        String ending = as.getMSingular().substring(as.getMSingular().length() - 2);
        if (ending.equals("al") || ending.equals("ar")) {
            as.setAdverbialForm(as.getMSingular() + "meing");
            return;
        }

        as.setAdverbialForm(as.getFSingular() + "meing");
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
}
