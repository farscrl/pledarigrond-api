package ch.pledarigrond.inflection.utils;

import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.inflection.generation.puter.PuterConjugationPronouns;
import ch.pledarigrond.inflection.generation.surmiran.SurmiranPronouns;
import ch.pledarigrond.inflection.generation.vallader.ValladerConjugationPronouns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PronounRemover {

    List<String> surmiranPrefixes = new ArrayList<>();
    List<String> puterPrefixes = new ArrayList<>();
    List<String> valladerPrefixes = new ArrayList<>();

    public PronounRemover() {
        setUpSurmiranPrefixes();
        setUpPuterPrefixes();
        setUpValladerPrefixes();
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

        String[] singleForms = value.split("\\R");
        for (int i = 0; i < singleForms.length; i++) {
            for(String p: prefixes) {
                if (singleForms[i].startsWith(p)) {
                    singleForms[i] =  singleForms[i].substring(p.length()).trim();
                }
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
                PuterConjugationPronouns.pron_conjunctiv_c,
                PuterConjugationPronouns.pron_conjunctiv_v,

                PuterConjugationPronouns.pron_1ps,
                PuterConjugationPronouns.pron_2ps,
                PuterConjugationPronouns.pron_3ps,
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
    }

    private void setUpValladerPrefixes() {
        Collections.addAll(valladerPrefixes,
                // has to be upfront, as other pronouns may follow
                ValladerConjugationPronouns.pron_conjunctiv_c,
                ValladerConjugationPronouns.pron_conjunctiv_v,

                ValladerConjugationPronouns.pron_1ps,
                ValladerConjugationPronouns.pron_2ps,
                ValladerConjugationPronouns.pron_3ps,
                ValladerConjugationPronouns.pron_1pp,
                ValladerConjugationPronouns.pron_2pp,
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
                ValladerConjugationPronouns.pron_r_v_3pp
        );
    }
}
