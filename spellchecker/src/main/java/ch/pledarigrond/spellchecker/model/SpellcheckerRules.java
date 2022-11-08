package ch.pledarigrond.spellchecker.model;

public enum SpellcheckerRules {
    SURMIRAN_PRONOMS_REFLEXIVS("P"),
    SURMIRAN_PLEDS_APOSTROFAI("T"),
    SURMIRAN_PRONOM_CONGIUNT_IMPERATIV("Q"),
    ;

    public final String abbr;

    SpellcheckerRules(String abbr) {
        this.abbr = abbr;
    }

    @Override
    public String toString() {
        return abbr;
    }
}
