package ch.pledarigrond.spellchecker.generator;

import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.inflection.generation.surmiran.SurmiranPronouns;
import ch.pledarigrond.names.entities.Name;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SurmiranSpellcheckerGenerator extends SpellcheckerGenerator{
    public SurmiranSpellcheckerGenerator(PgEnvironment pgEnvironment, List<Name> names) {
        super(pgEnvironment, names);
    }

    @Override
    protected String removePronouns(Language language, String value) {
        if (value == null) {
            return null;
        }

        List<String> prefixes = new ArrayList<>();
        if (language == Language.SURMIRAN) {
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
        }

        for(String p: prefixes) {
            if (value.startsWith(p)) {
                value =  value.substring(p.length());
            }
        }
        return value;
    }
}
