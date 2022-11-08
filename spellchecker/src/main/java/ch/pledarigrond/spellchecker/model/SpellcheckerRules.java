package ch.pledarigrond.spellchecker.model;

public enum SpellcheckerRules {
    SURMIRAN_PRONOMS_REFLEXIVS("P"),
    SURMIRAN_PRONOM_CONGIUNT_IMPERATIV("Q"),
    SURMIRAN_SHORT_ADJECTIVE("R"),
    SURMIRAN_PLEDS_APOSTROFAI("T"),
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
