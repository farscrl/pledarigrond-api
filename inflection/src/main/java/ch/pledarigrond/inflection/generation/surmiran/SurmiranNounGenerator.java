package ch.pledarigrond.inflection.generation.surmiran;

import ch.pledarigrond.inflection.generation.generic.LanguageNounGeneration;
import ch.pledarigrond.inflection.model.InflectionResponse;
import ch.pledarigrond.inflection.model.InflectionSubType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class SurmiranNounGenerator extends LanguageNounGeneration {

    private SurmiranNounStructure ns;

    private String base;

    private String root;

    private String ending;

    private ArrayList<HashMap<String, String>> formsList;

    private HashMap<String, String> forms;

    public InflectionResponse generateForms(String nounClass, String baseForm) {

        root = getRoot(baseForm, nounClass);

        InflectionSubType subType = SurmiranNounClasses.getNounInflectionClass(nounClass);
        if (subType == null) {
            throw new RuntimeException(nounClass + " is not a valid conjugation class.");
        } else if (getEnding() == null) {
            throw new RuntimeException(baseForm + " is not a valid male singular form. Please enter a valid form.");
        }

        return new InflectionResponse(subType, buildForms(root, subType));
    }

    public InflectionResponse guessInflection(String baseForm, String genus, String flex) {
        int length = baseForm.length();
        if (length < 3) {
            return null;
        }
        char secondToTheLastCharacter = baseForm.charAt(length-2);
        char lastCharacter = baseForm.charAt(length-2);

        if (isVocal(secondToTheLastCharacter) && isDuplicatedConsonant(lastCharacter)) {
            return generateForms("12", baseForm);
        }

        if (length > 4) {
            String lastFourCharacters = baseForm.substring(length - 4);
            if (lastFourCharacters.equals("cher")) {
                return generateForms("6", baseForm);
            }
        }

        String lastThreeCharacters = baseForm.substring(length - 3);
        if (lastThreeCharacters.equals("der")) {
            return generateForms("4", baseForm);
        }
        if (lastThreeCharacters.equals("bel")) {
            return generateForms("5", baseForm);
        }
        if (lastThreeCharacters.equals("vel")) {
            return generateForms("7", baseForm);
        }
        if (lastThreeCharacters.equals("ven")) {
            return generateForms("8", baseForm);
        }

        String lastTwoCharacters = baseForm.substring(length - 2);
        if (lastTwoCharacters.equals("ia")) {
            return generateForms("9", baseForm);
        }
        if (lastTwoCharacters.equals("ea")) {
            return generateForms("11", baseForm);
        }
        if (baseForm.substring(length - 1).equals("o")) {
            return generateForms("10", baseForm);
        }

        if (genus != null && genus.equals("f")) {
            return generateForms("2", baseForm);
        }

        // TODO: check if 3 or 1 should be default
        return generateForms("3", baseForm);
    }

    public String getRoot(String maleSingularForm, String nounClass) {
        if (maleSingularForm == null) {
            return maleSingularForm;
        }

        maleSingularForm = normalizeString(maleSingularForm);

        if (maleSingularForm.length() < 2) {
            throw new RuntimeException("'" + maleSingularForm + "' is not a valid male singular form. Please enter a male singular form.");
        }

        return extractRoot(maleSingularForm, nounClass);
    }

    public String extractRoot(String maleSingularForm, String nounClass) {
        switch (nounClass) {

            case "6":
                if (maleSingularForm.length() < 4) {
                    throw new RuntimeException("'" + maleSingularForm + "' is not a valid male singular form. Please enter a male singular form.");
                }
                ending = maleSingularForm.substring(maleSingularForm.length() - 4);
                return maleSingularForm.substring(0, maleSingularForm.length() - 4);

            case "4":
            case "5":
            case "7":
            case "8":
                if (maleSingularForm.length() < 3) {
                    throw new RuntimeException("'" + maleSingularForm + "' is not a valid male singular form. Please enter a male singular form.");
                }
                ending = maleSingularForm.substring(maleSingularForm.length() - 3);
                return maleSingularForm.substring(0, maleSingularForm.length() - 3);

            case "9":
            case "11":
                ending = maleSingularForm.substring(maleSingularForm.length() - 2);
                return maleSingularForm.substring(0, maleSingularForm.length() - 2);

            case "10":

                ending = maleSingularForm.substring(maleSingularForm.length() - 1);
                return maleSingularForm.substring(0, maleSingularForm.length() - 1);

            case "12":
                ending = maleSingularForm.substring(maleSingularForm.length() - 1);
                return maleSingularForm;

            case "1":
            case "2":
            case "3":
            default:
                ending = "";
                return maleSingularForm;
        }
    }

    public HashMap<String, String> buildForms(String root, InflectionSubType nounClass) {

        ns = new SurmiranNounStructure();

        ns.setBase(getBase());
        ns.setRoot(root);
        ns.setEnding(getEnding());
        ns.setNounClass(nounClass.id);

        setSingular();
        setPlural();

        return ns.getAllFormValues();

    }

    public String buildPlural(String base) {
        String l1 = base.substring(base.length() - 1);
        String l2 = base.substring(base.length() - 2);

        if (l2.equals("le") || l2.equals("re")) {
            // ignore words like "hardware" or "software"
            if (!base.substring(base.length() - 4).equals("ware")) {
                return base + "is";
            }
        }

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
                break;

            case "der":
                ns.setMSingular(root + "der");
                ns.setFSingular(root + "dra");
                break;

            case "bel":
                ns.setMSingular(root + "bel");
                ns.setFSingular(root + "bla");
                break;

            case "cher":
                ns.setMSingular(root + "cher");
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

            case "ia":
                ns.setMSingular(root + "ia");
                ns.setFSingular(root + "eida");
                break;

            case "o":
                ns.setMSingular(root + "o");
                ns.setFSingular(root + "ada");
                break;

            case "ea":
                ns.setMSingular(root + "ea");
                ns.setFSingular(root + "eda");
                break;

            case "c":
            case "f":
            case "m":
            case "n":
            case "p":
            case "r":
            case "t":
            case "z":
                ns.setMSingular(root);
                ns.setFSingular(root + ending + "a");
        }
    }

    public void setPlural() {
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
