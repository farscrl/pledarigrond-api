package ch.pledarigrond.spellchecker.generator;

import ch.pledarigrond.inflection.generation.verb.puter.PuterPronouns;
import ch.pledarigrond.inflection.generation.verb.rumantschgrischun.RumantschGrischunPronouns;
import ch.pledarigrond.inflection.generation.verb.surmiran.SurmiranPronouns;
import ch.pledarigrond.inflection.generation.verb.sutsilvan.SutsilvanPronouns;
import ch.pledarigrond.inflection.generation.verb.vallader.ValladerPronouns;

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
                PuterPronouns.pron_conjunctiv_c,
                PuterPronouns.pron_conjunctiv_v,

                PuterPronouns.imperativ_not,
                PuterPronouns.imperativ_not_vowel,
                PuterPronouns.imperativ_not_refl_sg,
                PuterPronouns.imperativ_not_refl_pl,
                PuterPronouns.imperativ_polite_sg,
                PuterPronouns.imperativ_polite_pl,

                PuterPronouns.pron_1ps,
                PuterPronouns.pron_2ps,
                PuterPronouns.pron_3ps,
                PuterPronouns.pron_3ps_impers,
                PuterPronouns.pron_1pp,
                PuterPronouns.pron_2pp,
                PuterPronouns.pron_3pp,
                PuterPronouns.pron_r_1ps,
                PuterPronouns.pron_r_2ps,
                PuterPronouns.pron_r_3ps,
                PuterPronouns.pron_r_1pp,
                PuterPronouns.pron_r_2pp,
                PuterPronouns.pron_r_3pp,
                PuterPronouns.pron_r_v_1ps,
                PuterPronouns.pron_r_v_2ps,
                PuterPronouns.pron_r_v_3ps,
                PuterPronouns.pron_r_v_1pp,
                PuterPronouns.pron_r_v_2pp,
                PuterPronouns.pron_r_v_3pp
        );

        List<String> suffixes = new ArrayList<>();
        Collections.addAll(suffixes,
                PuterPronouns.imperativ_refl_sg,
                PuterPronouns.imperativ_refl_pl
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
                ValladerPronouns.pron_conjunctiv_c,
                ValladerPronouns.pron_conjunctiv_v,

                ValladerPronouns.imperativ_not,
                ValladerPronouns.imperativ_not_vowel,
                ValladerPronouns.imperativ_not_refl_sg,
                ValladerPronouns.imperativ_not_refl_pl,
                ValladerPronouns.imperativ_refl_sg,
                ValladerPronouns.imperativ_refl_sg_vowel,
                ValladerPronouns.imperativ_refl_pl,
                ValladerPronouns.imperativ_refl_pl_vowel,
                ValladerPronouns.imperativ_polite_sg,
                ValladerPronouns.imperativ_polite_pl,

                ValladerPronouns.pron_1ps,
                ValladerPronouns.pron_2ps,
                ValladerPronouns.pron_3ps,
                ValladerPronouns.pron_3ps_impers,
                ValladerPronouns.pron_1pp,
                ValladerPronouns.pron_2pp,
                ValladerPronouns.pron_1pp_alt,
                ValladerPronouns.pron_3pp,
                ValladerPronouns.pron_r_1ps,
                ValladerPronouns.pron_r_2ps,
                ValladerPronouns.pron_r_3ps,
                ValladerPronouns.pron_r_1pp,
                ValladerPronouns.pron_r_2pp,
                ValladerPronouns.pron_r_3pp,
                ValladerPronouns.pron_r_v_1ps,
                ValladerPronouns.pron_r_v_2ps,
                ValladerPronouns.pron_r_v_3ps,
                ValladerPronouns.pron_r_v_1pp,
                ValladerPronouns.pron_r_v_2pp,
                ValladerPronouns.pron_r_v_3pp,
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
