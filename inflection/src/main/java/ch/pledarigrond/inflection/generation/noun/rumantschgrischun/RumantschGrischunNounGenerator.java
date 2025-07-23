package ch.pledarigrond.inflection.generation.noun.rumantschgrischun;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.common.data.dictionary.inflection.InflectionType;
import ch.pledarigrond.common.data.dictionary.inflection.NounDto;
import ch.pledarigrond.inflection.generation.noun.LanguageNounGeneration;
import ch.pledarigrond.inflection.model.InflectionSubType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
@EqualsAndHashCode(callSuper = true)
public class RumantschGrischunNounGenerator extends LanguageNounGeneration {

    private static final Logger logger = LoggerFactory.getLogger(RumantschGrischunNounGenerator.class);

    private InflectionDto inflection;
    private NounDto noun;

    private String root;
    private String ending;
    private InflectionSubType nounClass;

    public void reset() {
        inflection = new InflectionDto();
        noun = new NounDto();
        inflection.setNoun(noun);
        inflection.setInflectionType(InflectionType.NOUN);
        root = "";
        ending = "";
    }

    public InflectionDto generateForms(String nounClassId, String baseForm) {
        reset();

        root = getRoot(baseForm, nounClassId);

        InflectionSubType subType = RumantschGrischunNounClasses.getNounInflectionClass(nounClassId);
        if (subType == null) {
            throw new RuntimeException(nounClassId + " is not a valid inflection class.");
        } else if (getEnding() == null) {
            throw new RuntimeException(baseForm + " is not a valid male singular form. Please enter a valid form.");
        }

        nounClass = subType;
        noun.setInflectionSubtype(nounClass.id);
        buildForms();
        return inflection;
    }

    public InflectionDto guessInflection(String baseForm, String genus, String flex) {
        reset();

        baseForm = normalizeString(baseForm);
        int length = baseForm.length();
        if (length < 3) {
            return null;
        }

        if (baseForm.matches(".*, -(a|ada|dra|bla|cra)$")) {
            String[] parts = baseForm.split(", -");
            baseForm = parts[0];

            switch (parts[1]) {
                case "a":
                    return generateForms("3", baseForm);
                case "dra":
                    return generateForms("4", baseForm);
                case "bla":
                    return generateForms("5", baseForm);
                case "cra":
                    return generateForms("6", baseForm);
                case "ada":
                    return generateForms("7", baseForm);
                default:
                    logger.error("unknown ending: {}", parts[1]);
                    return null;
            }
        }

        // do not generate forms if word contains whitespaces
        if (baseForm.matches(".*\\s+.*")) {
            return null;
        }

        // default, case genus is f
        if (genus != null && genus.equals("f")) {
            return generateForms("2", baseForm);
        }

        // last 4 characters
        if (length > 4) {
            String lastFourCharacters = baseForm.substring(length - 4);
            if (lastFourCharacters.equals("cher")) {
                return generateForms("6", baseForm);
            }
        }

        // last 3 characters
        String lastThreeCharacters = baseForm.substring(length - 3);
        if (lastThreeCharacters.equals("der")) {
            return generateForms("4", baseForm);
        }
        if (lastThreeCharacters.equals("bel")) {
            return generateForms("5", baseForm);
        }

        // last 2 characters
        String lastTwoCharacters = baseForm.substring(length - 2);
        char secondToTheLastCharacter = baseForm.charAt(length-2);
        char lastCharacter = baseForm.charAt(length-1);
        if (isVocal(secondToTheLastCharacter) && isDuplicatedConsonant(lastCharacter) && isSingleSyllable(baseForm)) {
            return generateForms("8", baseForm);
        }

        // last characters
        if (baseForm.substring(length - 1).equals("à")) {
            return generateForms("7", baseForm);
        }

        return generateForms("3", baseForm);
    }

    public String getRoot(String maleSingularForm, String nounClass) {
        if (maleSingularForm == null) {
            return maleSingularForm;
        }

        maleSingularForm = normalizeString(maleSingularForm);
        noun.setBaseForm(maleSingularForm);

        if (maleSingularForm.length() < 2) {
            throw new RuntimeException("'" + maleSingularForm + "' is not a valid male singular form. Please enter a male singular form.");
        }

        return extractRoot(maleSingularForm, nounClass);
    }

    public String extractRoot(String maleSingularForm, String nounClass) {
        switch (nounClass) {

            case "6":
                if (maleSingularForm.length() < 4) {
                    throw new RuntimeException("'" + maleSingularForm + "' is not a valid male singular form. Please enter a male singular form.");
                }
                ending = maleSingularForm.substring(maleSingularForm.length() - 4);
                return maleSingularForm.substring(0, maleSingularForm.length() - 4);

            case "4":
            case "5":
                if (maleSingularForm.length() < 3) {
                    throw new RuntimeException("'" + maleSingularForm + "' is not a valid male singular form. Please enter a male singular form.");
                }
                ending = maleSingularForm.substring(maleSingularForm.length() - 3);
                return maleSingularForm.substring(0, maleSingularForm.length() - 3);



            case "7":
                ending = maleSingularForm.substring(maleSingularForm.length() - 1);
                return maleSingularForm.substring(0, maleSingularForm.length() - 1);

            case "8":
                ending = maleSingularForm.substring(maleSingularForm.length() - 1);
                return maleSingularForm;

            case "1":
            case "2":
            case "3":
            default:
                ending = "";
                return maleSingularForm;
        }
    }

    public void buildForms() {
        setSingular();
        setPlural();
    }

    public String buildPlural(String base) {
        String l1 = base.substring(base.length() - 1);
        String l2 = base.substring(base.length() - 2);

        if (base.contains("-")) {
            String[] s = base.split("-", 2);
            return  buildPlural(s[0]) + "-" + s[1];
        }

        if (l1.equals("s")) {
            return base;
        }

        if (l1.equals("à")) {
            return base.substring(0, base.length() - 1) + "ads";
        }

        if (l1.equals("è")) {
            return base.substring(0, base.length() - 1) + "els";
        }

        if (l1.equals("ì")) {
            return base.substring(0, base.length() - 1) + "ids";
        }

        return base + "s";
    }

    public void setSingular() {
        switch (ending) {
            case "":
                if (nounClass.id.equals("1")) {
                    noun.setMSingular(root);
                    noun.setFSingular(null);
                }
                if (nounClass.id.equals("2")) {
                    noun.setMSingular(null);
                    noun.setFSingular(root);
                }
                if (nounClass.id.equals("3")) {
                    noun.setMSingular(root);
                    noun.setFSingular(root + "a");
                }
                if (nounClass.id.equals("9")) { // collectiv
                    noun.setMSingular(root);
                    noun.setFSingular(null);
                }
                break;

            case "der":
                noun.setMSingular(root + "der");
                noun.setFSingular(root + "dra");
                break;

            case "bel":
                noun.setMSingular(root + "bel");
                noun.setFSingular(root + "bla");
                break;

            case "cher":
                noun.setMSingular(root + "cher");
                noun.setFSingular(root + "cra");
                break;

            case "à":
                noun.setMSingular(root + "à");
                noun.setFSingular(root + "ada");
                break;

            case "c":
            case "f":
            case "m":
            case "n":
            case "p":
            case "r":
            case "t":
            case "z":
                noun.setMSingular(root);
                noun.setFSingular(root + ending + "a");
        }
    }

    public void setPlural() {
        // collectiv
        if (nounClass.id.equals("9")) {
            noun.setMPlural(buildPlural(noun.getMSingular()));
            noun.setPluralCollectiv(noun.getMSingular() + "a");
            return;
        }

        if (!nounClass.id.equals("2")) {
            noun.setMPlural(buildPlural(noun.getMSingular()));
        } else {
            noun.setMPlural(null);
        }

        if (!nounClass.id.equals("1")) {
            noun.setFPlural(buildPlural(noun.getFSingular()));
        } else {
            noun.setFPlural(null);
        }
    }

    private boolean isDuplicatedConsonant(char ch) {
        return switch (ch) {
            case 'c', 'f', 'm', 'n', 'p', 'r', 't', 'z' -> true;
            default -> false;
        };
    }
}
