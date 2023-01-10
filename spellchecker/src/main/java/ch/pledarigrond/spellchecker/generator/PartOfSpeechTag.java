package ch.pledarigrond.spellchecker.generator;

public enum PartOfSpeechTag {
    ABBR("ABBR"),
    ADJ("ADJ"),
    ADV("ADV"),
    ART("ART"),
    CONJ("CONJ"),
    INTERJ("INTERJ"),
    NUM_CARDINALS("NUM.cardinals"),
    NUM_ORDINALS("NUM.ordinals"),
    PREP("PREP"),
    PRON("PRON"),
    PRON_DEMONSTRATIV("PRON.demonstrativ"),
    PRON_INDEFINIT("PRON.indefinit"),
    PRON_INTERROGATIV("PRON.interrogativ"),
    PRON_PERS("PRON.pers"),
    PRON_RELATIV("PRON.relativ"),
    NOUN("NOUN"),
    VERB("VERB"),
    VERB_DEFECTIV("VERB.defectiv"),
    VERB_IRREGULAR("VERB.irregular"),
    WTF("wtf");

    private final String name;

    PartOfSpeechTag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
