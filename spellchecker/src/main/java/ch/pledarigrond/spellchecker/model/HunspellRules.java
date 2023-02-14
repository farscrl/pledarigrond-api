package ch.pledarigrond.spellchecker.model;

public enum HunspellRules {

    // RULES FOR PUTER (according to puter/rm-puter.aff)



    // RULES FOR RUMANTSCH GRISCHUN (according to rumantschgrischun/rm-rumgr.aff)



    // RULES FOR SURMIRAN (according to surmiran/rm-surmiran.aff)
    SURMIRAN_PRONOMS_REFLEXIVS("P"),
    SURMIRAN_PRONOM_CONGIUNT_IMPERATIV("Q"),
    SURMIRAN_SHORT_ADJECTIVE("R"),
    SURMIRAN_PLEDS_APOSTROFAI("T"),


    // RULES FOR SURSILVAN (according to sursilvan/rm-sursilv.aff)




    // RULES FOR SUTSILVAN (according to sutsilvan/rm-sutsilv.aff)




    // RULES FOR VALLADER (according to vallader/rm-vallader.aff)
    ;

    public final String abbr;

    HunspellRules(String abbr) {
        this.abbr = abbr;
    }

    @Override
    public String toString() {
        return abbr;
    }
}
