package ch.pledarigrond.common.data.common;

public enum AutomaticChangesType {
    NOUNS("NOUNS"),
    ADJECTIVES("ADJECTIVES"),
    VERBS("VERBS"),
    ALL("ALL");

    private String type;

    AutomaticChangesType(String type) {
        this.type = type;
    }



    public String getType() {
        return type;
    }
}
