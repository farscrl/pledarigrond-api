package ch.pledarigrond.spellchecker.model;

public enum HunspellRules {

    // RULES FOR PUTER (according to puter/rm-puter.aff)
    PUTER_PRONOMS_REFLEXIVS("P"),
    PUTER_PLEDS_APOSTROFAI("T"),
    PUTER_PRONOMS_OBJECTS_IMPERATIV_AFFIRMATIV("Q"),
    PUTER_PRONOM_REFLEXIV_IMPERATIV_AFFIRMATIV("R"),

    // RULES FOR RUMANTSCH GRISCHUN (according to rumantschgrischun/rm-rumgr.aff)
    RUMANTSCH_GRISCHUN_NEGAZIUN("O"),
    RUMANTSCH_GRISCHUN_PRONOMS_REFLEXIVS("P"),
    RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI("T"),
    RUMANTSCH_GRISCHUN_PRONOMS_IMPRES("Q"),



    // RULES FOR SURMIRAN (according to surmiran/rm-surmiran.aff)
    SURMIRAN_PRONOMS_REFLEXIVS("P"),
    SURMIRAN_PRONOM_CONGIUNT_IMPERATIV("Q"),
    SURMIRAN_SHORT_ADJECTIVE("R"),
    SURMIRAN_PLEDS_APOSTROFAI("T"),


    // RULES FOR SURSILVAN (according to sursilvan/rm-sursilv.aff)




    // RULES FOR SUTSILVAN (according to sutsilvan/rm-sutsilv.aff)
    SUTSILVAN_PRONOMS_REFLEXIVS("P"),
    SUTSILVAN_PRONOMS_CUN_FURMA_INVERSIVA_DAL_PRONOM_IMPERS("Q"),
    SUTSILVAN_PLEDS_APOSTROFAI("T"),



    // RULES FOR VALLADER (according to vallader/rm-vallader.aff)
    VALLADER_PRONOMS_REFLEXIVS("P"),
    VALLADER_PLEDS_APOSTROFAI("T"),

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
