package ch.pledarigrond.spellchecker.generator;

import ch.pledarigrond.inflection.generation.puter.PuterConjugationPronouns;
import ch.pledarigrond.inflection.generation.rumantschgrischun.RumantschGrischunPronouns;
import ch.pledarigrond.inflection.generation.surmiran.SurmiranPronouns;
import ch.pledarigrond.inflection.generation.sutsilvan.SutsilvanPronouns;
import ch.pledarigrond.inflection.generation.vallader.ValladerConjugationPronouns;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class WordListUtils {

    // PUTER
    public static String removePronounsPuter(String input) {
        if (input == null) {
            return null;
        }

        List<String> prefixes = new ArrayList<>();
        Collections.addAll(prefixes,
                // has to be upfront, as other pronouns may follow
                PuterConjugationPronouns.pron_conjunctiv_c,
                PuterConjugationPronouns.pron_conjunctiv_v,

                PuterConjugationPronouns.imperativ_not,
                PuterConjugationPronouns.imperativ_not_vowel,
                PuterConjugationPronouns.imperativ_not_refl_sg,
                PuterConjugationPronouns.imperativ_not_refl_pl,
                PuterConjugationPronouns.imperativ_polite_sg,
                PuterConjugationPronouns.imperativ_polite_pl,

                PuterConjugationPronouns.pron_1ps,
                PuterConjugationPronouns.pron_2ps,
                PuterConjugationPronouns.pron_3ps,
                PuterConjugationPronouns.pron_3ps_impers,
                PuterConjugationPronouns.pron_1pp,
                PuterConjugationPronouns.pron_2pp,
                PuterConjugationPronouns.pron_3pp,
                PuterConjugationPronouns.pron_r_1ps,
                PuterConjugationPronouns.pron_r_2ps,
                PuterConjugationPronouns.pron_r_3ps,
                PuterConjugationPronouns.pron_r_1pp,
                PuterConjugationPronouns.pron_r_2pp,
                PuterConjugationPronouns.pron_r_3pp,
                PuterConjugationPronouns.pron_r_v_1ps,
                PuterConjugationPronouns.pron_r_v_2ps,
                PuterConjugationPronouns.pron_r_v_3ps,
                PuterConjugationPronouns.pron_r_v_1pp,
                PuterConjugationPronouns.pron_r_v_2pp,
                PuterConjugationPronouns.pron_r_v_3pp
        );

        List<String> suffixes = new ArrayList<>();
        Collections.addAll(suffixes,
                PuterConjugationPronouns.imperativ_refl_sg,
                PuterConjugationPronouns.imperativ_refl_pl
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


    // RUMANTSCH GRISCHUN
    public static String removePronounsRumantschGrischun(String input) {
        if (input == null) {
            return null;
        }

        List<String> prefixes = new ArrayList<>();
        Collections.addAll(prefixes,
                // has to be upfront, as other pronouns may follow
                RumantschGrischunPronouns.pron_conjunctiv_c,
                RumantschGrischunPronouns.pron_conjunctiv_v,

                RumantschGrischunPronouns.pron_1ps,
                RumantschGrischunPronouns.pron_2ps,
                RumantschGrischunPronouns.pron_3ps,
                RumantschGrischunPronouns.pron_3ps_impers,
                RumantschGrischunPronouns.pron_1pp,
                RumantschGrischunPronouns.pron_2pp,
                RumantschGrischunPronouns.pron_3pp,
                RumantschGrischunPronouns.pron_r_1ps,
                RumantschGrischunPronouns.pron_r_2ps,
                RumantschGrischunPronouns.pron_r_3ps,
                RumantschGrischunPronouns.pron_r_1pp,
                RumantschGrischunPronouns.pron_r_2pp,
                RumantschGrischunPronouns.pron_r_3pp,
                RumantschGrischunPronouns.pron_r_v_1ps,
                RumantschGrischunPronouns.pron_r_v_2ps,
                RumantschGrischunPronouns.pron_r_v_3ps,
                RumantschGrischunPronouns.pron_r_v_1pp,
                RumantschGrischunPronouns.pron_r_v_2pp,
                RumantschGrischunPronouns.pron_r_v_3pp
        );

        List<String> suffixes = new ArrayList<>();
        Collections.addAll(suffixes,
                ""
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

    // SURMIRAN
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
                SurmiranPronouns.impersonal_3ps,
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

    // SURSILVAN


    // SUTSILVAN
    public static String removePronounsSutsilvan(String input) {
        if (input == null) {
            return null;
        }

        List<String> prefixes = new ArrayList<>();
        Collections.addAll(prefixes,
                // has to be upfront, as other pronouns may follow
                SutsilvanPronouns.pron_conjunctiv_c,
                SutsilvanPronouns.pron_conjunctiv_v,

                SutsilvanPronouns.pron_1ps,
                SutsilvanPronouns.pron_2ps,
                SutsilvanPronouns.pron_3ps,
                SutsilvanPronouns.pron_1pp,
                SutsilvanPronouns.pron_2pp,
                SutsilvanPronouns.pron_3pp,
                SutsilvanPronouns.impersonal_3ps,
                SutsilvanPronouns.pron_r_1ps,
                SutsilvanPronouns.pron_r_2ps,
                SutsilvanPronouns.pron_r_3ps,
                SutsilvanPronouns.pron_r_1pp,
                SutsilvanPronouns.pron_r_2pp,
                SutsilvanPronouns.pron_r_3pp,
                SutsilvanPronouns.pron_r_v_1ps,
                SutsilvanPronouns.pron_r_v_2ps,
                SutsilvanPronouns.pron_r_v_3ps,
                SutsilvanPronouns.pron_r_v_1pp,
                SutsilvanPronouns.pron_r_v_2pp,
                SutsilvanPronouns.pron_r_v_3pp,

                SutsilvanPronouns.prefix_futur_1ps,
                SutsilvanPronouns.prefix_futur_1ps_v,
                SutsilvanPronouns.prefix_futur_2ps,
                SutsilvanPronouns.prefix_futur_2ps_v,
                SutsilvanPronouns.prefix_futur_3ps,
                SutsilvanPronouns.prefix_futur_3ps_v,
                SutsilvanPronouns.prefix_futur_1pp,
                SutsilvanPronouns.prefix_futur_1pp_v,
                SutsilvanPronouns.prefix_futur_2pp,
                SutsilvanPronouns.prefix_futur_2pp_v,
                SutsilvanPronouns.prefix_futur_3pp,
                SutsilvanPronouns.prefix_futur_3pp_v
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


    // VALLADER
    public static String removePronounsVallader(String input) {
        if (input == null) {
            return null;
        }

        List<String> prefixes = new ArrayList<>();
        Collections.addAll(prefixes,
                // has to be upfront, as other pronouns may follow
                ValladerConjugationPronouns.pron_conjunctiv_c,
                ValladerConjugationPronouns.pron_conjunctiv_v,

                ValladerConjugationPronouns.imperativ_not,
                ValladerConjugationPronouns.imperativ_not_vowel,
                ValladerConjugationPronouns.imperativ_not_refl_sg,
                ValladerConjugationPronouns.imperativ_not_refl_pl,
                ValladerConjugationPronouns.imperativ_refl_sg,
                ValladerConjugationPronouns.imperativ_refl_sg_vowel,
                ValladerConjugationPronouns.imperativ_refl_pl,
                ValladerConjugationPronouns.imperativ_refl_pl_vowel,
                ValladerConjugationPronouns.imperativ_polite_sg,
                ValladerConjugationPronouns.imperativ_polite_pl,

                ValladerConjugationPronouns.pron_1ps,
                ValladerConjugationPronouns.pron_2ps,
                ValladerConjugationPronouns.pron_3ps,
                ValladerConjugationPronouns.pron_3ps_impers,
                ValladerConjugationPronouns.pron_1pp,
                ValladerConjugationPronouns.pron_2pp,
                ValladerConjugationPronouns.pron_1pp_alt,
                ValladerConjugationPronouns.pron_3pp,
                ValladerConjugationPronouns.pron_r_1ps,
                ValladerConjugationPronouns.pron_r_2ps,
                ValladerConjugationPronouns.pron_r_3ps,
                ValladerConjugationPronouns.pron_r_1pp,
                ValladerConjugationPronouns.pron_r_2pp,
                ValladerConjugationPronouns.pron_r_3pp,
                ValladerConjugationPronouns.pron_r_v_1ps,
                ValladerConjugationPronouns.pron_r_v_2ps,
                ValladerConjugationPronouns.pron_r_v_3ps,
                ValladerConjugationPronouns.pron_r_v_1pp,
                ValladerConjugationPronouns.pron_r_v_2pp,
                ValladerConjugationPronouns.pron_r_v_3pp,
                "el/ella/i",
                "els/ellas/i",
                "ch'el/ella/i",
                "ch'els/ellas/i"
        );

        List<String> suffixes = new ArrayList<>();
        Collections.addAll(suffixes,
                ""
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

        word = word.trim();

        if (word.equals("") || word.equals(" ")) {
            return null;
        }

        // Ignore ( ) ...
        if (word.contains("...")) {
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
