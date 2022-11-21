package ch.pledarigrond.inflection.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class ConjugationPrinter {
    public static void print(HashMap<String, String> inflectionValues) {

        File file = new File("data/output/" + getValue(inflectionValues, "infinitiv") + ".txt");

        // creating directory and file if not existing
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();

            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));

            out
                    .append(getValue(inflectionValues, "infinitiv")).append(" (")
                    .append(getValue(inflectionValues, "RInflectionSubtype")).append(")\n")
                    .append("\n");

            out
                    .append("Preschent").append("\n")
                    .append(getValue(inflectionValues, "preschentsing1")).append("\n")
                    .append(getValue(inflectionValues, "preschentsing2")).append("\n")
                    .append(getValue(inflectionValues, "preschentsing3")).append("\n")
                    .append(getValue(inflectionValues, "preschentplural1")).append("\n")
                    .append(getValue(inflectionValues, "preschentplural2")).append("\n")
                    .append(getValue(inflectionValues, "preschentplural3")).append("\n")
                    .append("\n");

            out
                    .append("Imperfect").append("\n")
                    .append(getValue(inflectionValues, "imperfectsing1")).append("\n")
                    .append(getValue(inflectionValues, "imperfectsing2")).append("\n")
                    .append(getValue(inflectionValues, "imperfectsing3")).append("\n")
                    .append(getValue(inflectionValues, "imperfectplural1")).append("\n")
                    .append(getValue(inflectionValues, "imperfectplural2")).append("\n")
                    .append(getValue(inflectionValues, "imperfectplural3")).append("\n")
                    .append("\n");

            out
                    .append("Conjunctiv").append("\n")
                    .append(getValue(inflectionValues, "conjunctivsing1")).append("\n")
                    .append(getValue(inflectionValues, "conjunctivsing2")).append("\n")
                    .append(getValue(inflectionValues, "conjunctivsing3")).append("\n")
                    .append(getValue(inflectionValues, "imperfectplural1")).append("\n")
                    .append(getValue(inflectionValues, "conjunctivplural1")).append("\n")
                    .append(getValue(inflectionValues, "conjunctivplural3")).append("\n")
                    .append("\n");

            out
                    .append("Conjunctiv imperfect").append("\n")
                    .append(getValue(inflectionValues, "conjunctivimperfectsing1")).append("\n")
                    .append(getValue(inflectionValues, "conjunctivimperfectsing2")).append("\n")
                    .append(getValue(inflectionValues, "conjunctivimperfectsing3")).append("\n")
                    .append(getValue(inflectionValues, "conjunctivimperfectplural1")).append("\n")
                    .append(getValue(inflectionValues, "conjunctivimperfectplural2")).append("\n")
                    .append(getValue(inflectionValues, "conjunctivimperfectplural3")).append("\n")
                    .append("\n");

            out
                    .append("Cundiziunal").append("\n")
                    .append(getValue(inflectionValues, "cundizionalsing1")).append("\n")
                    .append(getValue(inflectionValues, "cundizionalsing2")).append("\n")
                    .append(getValue(inflectionValues, "cundizionalsing3")).append("\n")
                    .append(getValue(inflectionValues, "cundizionalplural1")).append("\n")
                    .append(getValue(inflectionValues, "cundizionalplural2")).append("\n")
                    .append(getValue(inflectionValues, "cundizionalplural3")).append("\n")
                    .append("\n");

            out
                    .append("Cundiziunal indirect").append("\n")
                    .append(getValue(inflectionValues, "cundizionalindirectsing1")).append("\n")
                    .append(getValue(inflectionValues, "cundizionalindirectsing2")).append("\n")
                    .append(getValue(inflectionValues, "cundizionalindirectsing3")).append("\n")
                    .append(getValue(inflectionValues, "cundizionalindirectplural1")).append("\n")
                    .append(getValue(inflectionValues, "cundizionalindirectplural2")).append("\n")
                    .append(getValue(inflectionValues, "cundizionalindirectplural3")).append("\n")
                    .append("\n");

            out
                    .append("Futur").append("\n")
                    .append(getValue(inflectionValues, "futursing1")).append("\n")
                    .append(getValue(inflectionValues, "futursing2")).append("\n")
                    .append(getValue(inflectionValues, "futursing3")).append("\n")
                    .append(getValue(inflectionValues, "futurplural1")).append("\n")
                    .append(getValue(inflectionValues, "futurplural2")).append("\n")
                    .append(getValue(inflectionValues, "futurplural3")).append("\n")
                    .append("\n");

            out
                    .append("Enclitic: preschent").append("\n")
                    .append(getValue(inflectionValues, "preschentsing1enclitic")).append("\n")
                    .append(getValue(inflectionValues, "preschentsing2enclitic")).append("\n")
                    .append(getValue(inflectionValues, "preschentsing3encliticm")).append("\n")
                    .append(getValue(inflectionValues, "preschentsing3encliticf")).append("\n")
                    .append(getValue(inflectionValues, "preschentplural1enclitic")).append("\n")
                    .append(getValue(inflectionValues, "preschentplural2enclitic")).append("\n")
                    .append(getValue(inflectionValues, "preschentplural3enclitic")).append("\n")
                    .append("\n");

            out
                    .append("Enclitic: imperfect").append("\n")
                    .append(getValue(inflectionValues, "imperfectsing1enclitic")).append("\n")
                    .append(getValue(inflectionValues, "imperfectsing2enclitic")).append("\n")
                    .append(getValue(inflectionValues, "imperfectsing3encliticm")).append("\n")
                    .append(getValue(inflectionValues, "imperfectsing3encliticf")).append("\n")
                    .append(getValue(inflectionValues, "imperfectplural1enclitic")).append("\n")
                    .append(getValue(inflectionValues, "imperfectplural2enclitic")).append("\n")
                    .append(getValue(inflectionValues, "imperfectplural3enclitic")).append("\n")
                    .append("\n");

            out
                    .append("Enclitic: Cundiziunal").append("\n")
                    .append(getValue(inflectionValues, "cundizionalsing1enclitic")).append("\n")
                    .append(getValue(inflectionValues, "cundizionalsing2enclitic")).append("\n")
                    .append(getValue(inflectionValues, "cundizionalsing3encliticm")).append("\n")
                    .append(getValue(inflectionValues, "cundizionalsing3encliticf")).append("\n")
                    .append(getValue(inflectionValues, "cundizionalplural1enclitic")).append("\n")
                    .append(getValue(inflectionValues, "cundizionalplural2enclitic")).append("\n")
                    .append(getValue(inflectionValues, "cundizionalplural3enclitic")).append("\n")
                    .append("\n");

            out
                    .append("Particips").append("\n")
                    .append(getValue(inflectionValues, "participperfectms")).append("\n")
                    .append(getValue(inflectionValues, "participperfectfs")).append("\n")
                    .append(getValue(inflectionValues, "participperfectmp")).append("\n")
                    .append(getValue(inflectionValues, "participperfectfp")).append("\n")
                    .append("\n");

            out
                    .append("Imperativ").append("\n")
                    .append(getValue(inflectionValues, "imperativ1")).append("\n")
                    .append(getValue(inflectionValues, "imperativ2")).append("\n")
                    .append("\n");

            out
                    .append("Gerundi").append("\n")
                    .append(getValue(inflectionValues, "gerundium")).append("\n")
                    .append(getValue(inflectionValues, "imperativ2")).append("\n")
                    .append("\n");

            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    private static String getValue(HashMap<String, String> inflectionValues, String name) {
        String s = inflectionValues.get(name);
        s = s.replaceAll("\n", "\\\\n");
        return s;
    }
}
