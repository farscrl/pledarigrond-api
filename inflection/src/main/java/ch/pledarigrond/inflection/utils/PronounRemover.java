package ch.pledarigrond.inflection.utils;

import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.inflection.generation.verb.puter.PuterPronouns;
import ch.pledarigrond.inflection.generation.verb.surmiran.SurmiranPronouns;
import ch.pledarigrond.inflection.generation.verb.sursilvan.SursilvanPronouns;
import ch.pledarigrond.inflection.generation.verb.vallader.ValladerPronouns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PronounRemover {

    List<String> surmiranPrefixes = new ArrayList<>();
    List<String> puterPrefixes = new ArrayList<>();
    List<String> valladerPrefixes = new ArrayList<>();
    List<String> sursilvanPrefixes = new ArrayList<>();

    public PronounRemover() {
        setUpSurmiranPrefixes();
        setUpPuterPrefixes();
        setUpValladerPrefixes();
        setUpSursilvanPrefixes();
    }

    public String removePronouns(Language language, String value) {
        if (value == null) {
            return null;
        }

        List<String> prefixes = new ArrayList<>();
        if (language == Language.SURMIRAN) {
            prefixes = surmiranPrefixes;
        }
        if (language == Language.PUTER) {
            prefixes = puterPrefixes;
        }
        if (language == Language.VALLADER) {
            prefixes = valladerPrefixes;
        }
        if (language == Language.SURSILVAN) {
            prefixes = sursilvanPrefixes;
        }

        String[] singleForms = value.split("\\R");
        for (int i = 0; i < singleForms.length; i++) {
            if (singleForms[i].isEmpty()) {
                continue;
            }
            boolean enclosedInBrackets = false;
            if (singleForms[i].charAt(0) == '(' && singleForms[i].charAt(singleForms[i].length()-1) == ')') {
                enclosedInBrackets = true;
                singleForms[i] = singleForms[i].replace("(", "").replace(")", "");
            }
            for(String p: prefixes) {
                if (singleForms[i].startsWith(p)) {
                    singleForms[i] =  singleForms[i].substring(p.length()).trim();
                }
            }
            if (enclosedInBrackets) {
                singleForms[i] = "(" + singleForms[i] + ")";
            }
        }
        return String.join("\n", singleForms);
    }

    private void setUpSurmiranPrefixes() {
        Collections.addAll(surmiranPrefixes,
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
    }

    private void setUpPuterPrefixes() {
        Collections.addAll(puterPrefixes,
                // has to be upfront, as other pronouns may follow
                PuterPronouns.pron_conjunctiv_c,
                PuterPronouns.pron_conjunctiv_v,

                PuterPronouns.pron_1ps,
                PuterPronouns.pron_2ps,
                PuterPronouns.pron_3ps,
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
                PuterPronouns.pron_r_v_3pp,
                PuterPronouns.imperativ_not,
                PuterPronouns.imperativ_not_refl_sg,
                PuterPronouns.imperativ_not_refl_pl,
                PuterPronouns.imperativ_refl_sg,
                PuterPronouns.imperativ_refl_pl,
                PuterPronouns.imperativ_polite_sg,
                PuterPronouns.imperativ_polite_pl
        );
    }

    private void setUpValladerPrefixes() {
        Collections.addAll(valladerPrefixes,
                // has to be upfront, as other pronouns may follow
                ValladerPronouns.pron_conjunctiv_c,
                ValladerPronouns.pron_conjunctiv_v,

                ValladerPronouns.pron_1ps,
                ValladerPronouns.pron_2ps,
                ValladerPronouns.pron_3ps,
                ValladerPronouns.pron_1pp,
                ValladerPronouns.pron_1pp_alt,
                ValladerPronouns.pron_2pp,
                ValladerPronouns.pron_2pp_alt,
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
                ValladerPronouns.imperativ_not,
                ValladerPronouns.imperativ_not_vowel,
                ValladerPronouns.imperativ_not_refl_sg,
                ValladerPronouns.imperativ_not_refl_pl,
                ValladerPronouns.imperativ_refl_sg,
                ValladerPronouns.imperativ_refl_sg_vowel,
                ValladerPronouns.imperativ_refl_pl,
                ValladerPronouns.imperativ_refl_pl_vowel,
                ValladerPronouns.imperativ_polite_sg,
                ValladerPronouns.imperativ_polite_pl
        );
    }

    private void setUpSursilvanPrefixes() {
        Collections.addAll(sursilvanPrefixes,
                // has to be upfront, as other pronouns may follow
                SursilvanPronouns.pron_conjunctiv_c,
                SursilvanPronouns.pron_conjunctiv_v,

                SursilvanPronouns.pron_1ps,
                SursilvanPronouns.pron_2ps,
                SursilvanPronouns.pron_3ps,
                SursilvanPronouns.pron_3ps_impers,
                SursilvanPronouns.pron_1pp,
                SursilvanPronouns.pron_2pp,
                SursilvanPronouns.pron_3pp
        );
    }
}
