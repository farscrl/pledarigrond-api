package ch.pledarigrond.inflection.generation.vallader;

import ch.pledarigrond.common.util.PronunciationNormalizer;
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

@Data
@EqualsAndHashCode(callSuper = true)
public class ValladerNounGenerator extends LanguageNounGeneration {

    private static final Logger logger = LoggerFactory.getLogger(ValladerNounGenerator.class);

    private ValladerNounStructure ns;

    private String baseForm;

    private String root;

    private String ending;

    private ArrayList<HashMap<String, String>> formsList;

    private HashMap<String, String> forms;

    public InflectionResponse generateForms(String nounClass, String baseForm) {
        baseForm = PronunciationNormalizer.normalizePronunciation(baseForm);
        root = getRoot(baseForm, nounClass);

        InflectionSubType subType = ValladerNounClasses.getNounInflectionClass(nounClass);
        if (subType == null) {
            throw new RuntimeException(nounClass + " is not a valid inflection class.");
        } else if (getEnding() == null) {
            throw new RuntimeException(baseForm + " is not a valid male singular form. Please enter a valid form.");
        }

        return new InflectionResponse(subType, buildForms(root, subType));
    }

    public InflectionResponse guessInflection(String baseForm, String genus, String flex) {
        baseForm = PronunciationNormalizer.normalizePronunciation(baseForm);
        baseForm = normalizeString(baseForm);
        int length = baseForm.length();
        if (length < 3) {
            return null;
        }

        if (baseForm.matches(".*, -(a|dra|bla|vla|cra|vna|vra|ada|üda|ida)$")) {
            String[] parts = baseForm.split(", -");
            baseForm = parts[0];

            switch (parts[1]) {
                case "a":
                    return generateForms("3", baseForm);
                case "dra":
                    return generateForms("4", baseForm);
                case "bla":
                    return generateForms("5", baseForm);
                case "vla":
                    return generateForms("6", baseForm);
                case "cra":
                    return generateForms("7", baseForm);
                case "vna":
                    return generateForms("8", baseForm);
                case "vra":
                    return generateForms("9", baseForm);
                case "ada":
                    return generateForms("10", baseForm);
                case "üda":
                    return generateForms("11", baseForm);
                case "ida":
                    return generateForms("12", baseForm);
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

        // last 3 characters
        String lastThreeCharacters = baseForm.substring(length - 3);
        if (lastThreeCharacters.equals("der")) {
            return generateForms("4", baseForm);
        }
        if (lastThreeCharacters.equals("bel")) {
            return generateForms("5", baseForm);
        }
        if (lastThreeCharacters.equals("vel")) {
            return generateForms("6", baseForm);
        }
        if (lastThreeCharacters.equals("ker")) {
            return generateForms("7", baseForm);
        }
        if (lastThreeCharacters.equals("ven")) {
            return generateForms("8", baseForm);
        }
        if (lastThreeCharacters.equals("ver")) {
            return generateForms("9", baseForm);
        }

        // last characters
        if (baseForm.substring(length - 1).equals("à")) {
            return generateForms("10", baseForm);
        }

        if (baseForm.substring(length - 1).equals("ü")) {
            return generateForms("11", baseForm);
        }

        if (baseForm.substring(length - 1).equals("i")) {
            return generateForms("12", baseForm);
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
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                if (maleSingularForm.length() < 3) {
                    throw new RuntimeException("'" + maleSingularForm + "' is not a valid male singular form. Please enter a male singular form.");
                }
                ending = maleSingularForm.substring(maleSingularForm.length() - 3);
                return maleSingularForm.substring(0, maleSingularForm.length() - 3);

            case "10":
            case "11":
            case "12":
                ending = maleSingularForm.substring(maleSingularForm.length() - 1);
                return maleSingularForm.substring(0, maleSingularForm.length() - 1);

            case "1":
            case "2":
            case "3":
            default:
                ending = "";
                return maleSingularForm;
        }
    }

    public HashMap<String, String> buildForms(String root, InflectionSubType nounClass) {

        ns = new ValladerNounStructure();
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

        if (l1.equals("à")) {
            return base.substring(0, base.length() - 1) + "ats";
        }

        if (l1.equals("è")) {
            return base.substring(0, base.length() - 1) + "els";
        }

        if (l1.equals("i")) {
            return base.substring(0, base.length() - 1) + "its";
        }

        if (l1.equals("ü")) {
            return base.substring(0, base.length() - 1) + "üts";
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
                if (ns.getNounClass().equals("13")) {
                    ns.setMSingular(root);
                    ns.setFSingular(null);
                }
                break;

            case "der":
                ns.setMSingular(root + "der");
                ns.setFSingular(root + "dra");
                break;

            case "bel":
                ns.setMSingular(root + "bel");
                ns.setFSingular(root + "bla");
                break;

            case "vel":
                ns.setMSingular(root + "vel");
                ns.setFSingular(root + "vla");
                break;

            case "ker":
                ns.setMSingular(root + "ker");
                ns.setFSingular(root + "cra");
                break;

            case "ven":
                ns.setMSingular(root + "ven");
                ns.setFSingular(root + "vna");
                break;

            case "ver":
                ns.setMSingular(root + "ver");
                ns.setFSingular(root + "vra");
                break;

            case "à":
                ns.setMSingular(root + "à");
                ns.setFSingular(root + "ada");
                break;

            case "ü":
                ns.setMSingular(root + "ü");
                ns.setFSingular(root + "üda");
                break;

            case "i":
                ns.setMSingular(root + "i");
                ns.setFSingular(root + "ida");
                break;
        }
    }

    public void setPlural() {
        // collectiv
        if (ns.getNounClass().equals("13")) {
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
