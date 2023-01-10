package ch.pledarigrond.spellchecker.generator;

import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.names.entities.Name;
import ch.pledarigrond.spellchecker.model.SpellcheckerRules;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class WordListUtils {
    public static String normalizeStringSurmiran(String input) {
        // Ignore cf.
        if (input.startsWith("cf. ")) {
            return null;
        }

        if (input.endsWith(" da")) {
            input = input.substring(0, input.length() - 3);
        }
        if (input.endsWith(" a")) {
            input = input.substring(0, input.length() - 2);
        }
        if (input.endsWith("!")) {
            input = input.substring(0, input.length() - 1);
        }
        if (input.startsWith("far ")) {
            input = input.substring(4);
        }
        if (input.startsWith("l'")) {
            input = input.substring(2);
        }
        if (input.startsWith("la ")) {
            input = input.substring(3);
        }
        if (input.startsWith("en ")) {
            input = input.substring(3);
        }
        if (input.startsWith("en'")) {
            input = input.substring(3);
        }
        if (input.startsWith("ena ")) {
            input = input.substring(4);
        }
        if (input.startsWith("egn ")) {
            input = input.substring(4);
        }
        if (input.startsWith("egna ")) {
            input = input.substring(5);
        }
        return input;
    }

    public static String getRomanshNameForLanguage(Language language, Name name) {
        String languageLemma = getLanguageLemma(language, name);
        if (languageLemma != null && !languageLemma.equals("")) {
            return languageLemma;
        } else {
            return name.getNameRumantschGrischun();
        }

    }

    public static String getGermanNameForLanguage(Language language, Name name) {
        if (name.getNameGerman() != null && !name.getNameGerman().equals("")) {
            return name.getNameGerman();
        }

        return null;
    }

    private static String getLanguageLemma(Language language, Name name) {
        return switch (language) {
            case SURSILVAN -> name.getNameSursilvan();
            case SUTSILVAN -> name.getNameSutsilvan();
            case SURMIRAN -> name.getNameSurmiran();
            case PUTER -> name.getNamePuter();
            case VALLADER -> name.getNameVallader();
            case RUMANTSCHGRISCHUN -> name.getNameRumantschGrischun();
        };
    }

    public static void writeFilesToZip(File zipFile, List<File> filesToZip) {
        ZipOutputStream zos = null;
        try {

            zos = new ZipOutputStream(new FileOutputStream(zipFile));

            for(File f: filesToZip) {
                String name = f.getName();
                ZipEntry entry = new ZipEntry(name);
                zos.putNextEntry(entry);

                FileInputStream fis = null;
                fis = new FileInputStream(f);
                byte[] byteBuffer = new byte[1024];
                int bytesRead = -1;
                while ((bytesRead = fis.read(byteBuffer)) != -1) {
                    zos.write(byteBuffer, 0, bytesRead);
                }
                fis.close();
                zos.closeEntry();
                zos.flush();
            }


            zos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
