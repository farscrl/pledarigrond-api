package ch.pledarigrond.spellchecker.generator;

import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.inflection.generation.surmiran.SurmiranPronouns;
import ch.pledarigrond.names.entities.Name;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class WordListUtils {
    public static String normalizeStringSurmiran(String input) {
        if (input == null) {
            return null;
        }

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
        if (input.endsWith("?")) {
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

    public static String removePronounsSurmiran(String input) {
        if (input == null) {
            return null;
        }

        List<String> prefixes = new ArrayList<>();
        Collections.addAll(prefixes,
                // has to be upfront, as other pronouns may follow
                SurmiranPronouns.pron_conjunctiv_c,
                SurmiranPronouns.pron_conjunctiv_v,

                SurmiranPronouns.pron_1ps,
                SurmiranPronouns.pron_2ps,
                SurmiranPronouns.pron_3ps,
                SurmiranPronouns.pron_1pp,
                SurmiranPronouns.pron_2pp,
                SurmiranPronouns.pron_3pp,
                SurmiranPronouns.pron_r_1ps,
                SurmiranPronouns.pron_r_2ps,
                SurmiranPronouns.pron_r_3ps,
                SurmiranPronouns.pron_r_1pp,
                SurmiranPronouns.pron_r_2pp,
                SurmiranPronouns.pron_r_3pp,
                SurmiranPronouns.pron_r_v_1ps,
                SurmiranPronouns.pron_r_v_2ps,
                SurmiranPronouns.pron_r_v_3ps,
                SurmiranPronouns.pron_r_v_1pp,
                SurmiranPronouns.pron_r_v_2pp,
                SurmiranPronouns.pron_r_v_3pp
        );

        List<String> suffixes = new ArrayList<>();
        Collections.addAll(suffixes,
                "'l",
                "'la"
        );

        for(String p: prefixes) {
            if (input.startsWith(p)) {
                input =  input.substring(p.length());
            }
        }
        for(String s: suffixes) {
            if (input.endsWith(s)) {
                input = input.substring(0, input.length() - s.length());
            }
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

    public static String normalizeWordListEntry(String word) {
        if (word == null) {
            return null;
        }

        if (word.equals("") || word.equals(" ")) {
            return null;
        }

        // Ignore ( ) ...
        if (word.contains("(") || word.contains(")") || word.contains("...")) {
            return null;
        }

        if (word.endsWith("!")) {
            word = word.substring(0, word.length() - 1);
        }

        if (word.endsWith("?")) {
            word = word.substring(0, word.length() - 1);
        }

        if (word.endsWith(",")) {
            word = word.substring(0, word.length() - 1);
        }

        return word;
    }
}
