package ch.pledarigrond.inflection.generation.sutsilvan;

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
public class SutsilvanNounGenerator extends LanguageNounGeneration {

    private static final Logger logger = LoggerFactory.getLogger(SutsilvanNounGenerator.class);

    private SutsilvanNounStructure ns;

    private String baseForm;

    private String root;

    private String ending;

    private ArrayList<HashMap<String, String>> formsList;

    private HashMap<String, String> forms;

    public InflectionResponse generateForms(String nounClass, String baseForm) {

        root = getRoot(baseForm, nounClass);

        InflectionSubType subType = SutsilvanNounClasses.getNounInflectionClass(nounClass);
        if (subType == null) {
            throw new RuntimeException(nounClass + " is not a valid inflection class.");
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

        if (baseForm.matches(".*, -(a|dra|bla|cra|vla|vna|vra|ada|ida)$")) {
            String[] parts = baseForm.split(", -");
            baseForm = parts[0];

            switch (parts[1]) {
                case "a":
                    return generateForms("3", baseForm);
                case "dra":
                    return generateForms("4", baseForm);
                case "bla":
                    return generateForms("5", baseForm);
                case "cra":
                    return generateForms("6", baseForm);
                case "vla":
                    return generateForms("7", baseForm);
                case "vna":
                    return generateForms("8", baseForm);
                case "vra":
                    return generateForms("9", baseForm);
                case "ada":
                    return generateForms("10", baseForm);
                case "ida":
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

        // default, case genus is f
        if (genus != null && genus.equals("f")) {
            return generateForms("2", baseForm);
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
            if (lastFourCharacters.equals("iieu")) {
                return generateForms("14", baseForm);
            }
        }

        // last 3 characters
        String lastThreeCharacters = baseForm.substring(length - 3);
        if (lastThreeCharacters.equals("der")) {
            return generateForms("4", baseForm);
        }
        if (lastThreeCharacters.equals("bel")) {
            return generateForms("5", baseForm);
        }
        if (lastThreeCharacters.equals("cer")) {
            return generateForms("6", baseForm);
        }
        if (lastThreeCharacters.equals("vel")) {
            return generateForms("7", baseForm);
        }
        if (lastThreeCharacters.equals("ven")) {
            return generateForms("8", baseForm);
        }
        if (lastThreeCharacters.equals("ver")) {
            return generateForms("9", baseForm);
        }
        if (lastThreeCharacters.equals("ieu")) {
            return generateForms("11", baseForm);
        }

        // last character
        if (baseForm.substring(length - 1).equals("o")) {
            return generateForms("10", baseForm);
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
            throw new RuntimeException("'" + maleSingularForm + "' is not a valid singular form. Please enter a singular form.");
        }

        return extractRoot(maleSingularForm, nounClass);
    }

    public String extractRoot(String maleSingularForm, String nounClass) {
        switch (nounClass) {

            case "12":
            case "13":
                if (maleSingularForm.length() < 5) {
                    throw new RuntimeException("'" + maleSingularForm + "' is not a valid singular form. Please enter a singular form.");
                }
                ending = maleSingularForm.substring(maleSingularForm.length() - 5);
                return maleSingularForm.substring(0, maleSingularForm.length() - 5);

            case "14":
                if (maleSingularForm.length() < 4) {
                    throw new RuntimeException("'" + maleSingularForm + "' is not a valid singular form. Please enter a singular form.");
                }
                ending = maleSingularForm.substring(maleSingularForm.length() - 4);
                return maleSingularForm.substring(0, maleSingularForm.length() - 4);

            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "11":
                if (maleSingularForm.length() < 3) {
                    throw new RuntimeException("'" + maleSingularForm + "' is not a valid singular form. Please enter a singular form.");
                }
                ending = maleSingularForm.substring(maleSingularForm.length() - 3);
                return maleSingularForm.substring(0, maleSingularForm.length() - 3);


            case "10":
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
        ns = new SutsilvanNounStructure();
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

        if (l1.equals("s")) {
            return base;
        }

        if (l1.equals("i")) {
            return base.substring(0, base.length() - 1) + "eals";
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
                if (ns.getNounClass().equals("15")) {
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

            case "cer":
                ns.setMSingular(root + "cer");
                ns.setFSingular(root + "cra");
                break;

            case "vel":
                ns.setMSingular(root + "vel");
                ns.setFSingular(root + "vla");
                break;

            case "ven":
                ns.setMSingular(root + "ven");
                ns.setFSingular(root + "vna");
                break;

            case "ver":
                ns.setMSingular(root + "ver");
                ns.setFSingular(root + "vra");
                break;

            case "o":
                ns.setMSingular(root + "o");
                ns.setFSingular(root + "ada");
                break;

            case "ieu":
                ns.setMSingular(root + "ieu");
                ns.setFSingular(root + "ida");
                break;

            case "glieu":
                ns.setMSingular(root + "glieu");
                ns.setFSingular(root + "glieada");
                break;

            case "tgieu":
                ns.setMSingular(root + "tgieu");
                ns.setFSingular(root + "tgeada");
                break;

            case "iieu":
                ns.setMSingular(root + "iieu");
                ns.setFSingular(root + "ieada");
                break;
        }
    }

    public void setPlural() {
        // collectiv
        if (ns.getNounClass().equals("15")) {
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

    protected String normalizeString(String query) {
        if (query.charAt(0) == '[') {
            query = query.substring(1, query.length() - 1);
        }
        query = query.replaceAll("^\\s+|\\s+$", "");
        if (query.endsWith("(a)")) {
            query = query.substring(0, query.length() - 3);
        }
        return query;
    }
}
