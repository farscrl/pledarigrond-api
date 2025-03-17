package ch.pledarigrond.inflection.generation.adjective.surmiran;

import ch.pledarigrond.common.data.dictionary.inflection.AdjectiveDto;
import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.common.data.dictionary.inflection.InflectionType;
import ch.pledarigrond.inflection.generation.adjective.LanguageAdjectiveGeneration;
import ch.pledarigrond.inflection.model.InflectionSubType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
@EqualsAndHashCode(callSuper = true)
public class SurmiranAdjectiveGenerator extends LanguageAdjectiveGeneration {

    private static final Logger logger = LoggerFactory.getLogger(SurmiranAdjectiveGenerator.class);

    private InflectionDto inflection;
    private AdjectiveDto adjective;

    private String root;
    private String ending;

    public void reset() {
        inflection = new InflectionDto();
        adjective = new AdjectiveDto();
        inflection.setAdjective(adjective);
        inflection.setInflectionType(InflectionType.ADJECTIVE);
        root = "";
        ending = "";
    }

    public InflectionDto generateForms(String adjectiveClass, String baseForm) {
        reset();

        root = getRoot(baseForm, adjectiveClass);

        InflectionSubType subType = SurmiranAdjectiveClasses.getAdjectiveInflectionClass(adjectiveClass);
        if (subType == null) {
            throw new RuntimeException(adjectiveClass + " is not a valid conjugation class.");
        } else if (getEnding() == null) {
            throw new RuntimeException(baseForm + " is not a valid male singular form. Please enter a valid form.");
        }

        buildForms(subType);
        return inflection;
    }

    public InflectionDto guessInflection(String baseForm, String genus, String flex) {
        reset();

        baseForm = normalizeString(baseForm);
        int length = baseForm.length();
        if (length < 3) {
            return null;
        }

        if (baseForm.matches(".*, -(a|ada|dra|vla|bla|cra|vna|eida|eda)$")) {
            String[] parts = baseForm.split(", -");
            baseForm = parts[0];

            switch (parts[1]) {
                case "a":
                    return generateForms("1", baseForm);
                case "dra":
                    return generateForms("2", baseForm);
                case "bla":
                    return generateForms("3", baseForm);
                case "cra":
                    return generateForms("4", baseForm);
                case "vla":
                    return generateForms("5", baseForm);
                case "vna":
                    return generateForms("6", baseForm);
                case "eida":
                    return generateForms("7", baseForm);
                case "ada":
                    return generateForms("8", baseForm);
                case "eda":
                    return generateForms("9", baseForm);
                default:
                    logger.error("unknown ending: {}", parts[1]);
                    return null;
            }
        }

        // do not generate forms if word contains whitespaces
        if (baseForm.matches(".*\\s+.*")) {
            return null;
        }

        // last 4 characters
        if (length > 4) {
            String lastFourCharacters = baseForm.substring(length - 4);
            if (lastFourCharacters.equals("cher")) {
                return generateForms("4", baseForm);
            }
        }

        // last 3 characters
        String lastThreeCharacters = baseForm.substring(length - 3);
        if (lastThreeCharacters.equals("der")) {
            return generateForms("2", baseForm);
        }
        if (lastThreeCharacters.equals("bel")) {
            return generateForms("3", baseForm);
        }
        if (lastThreeCharacters.equals("vel")) {
            return generateForms("5", baseForm);
        }
        if (lastThreeCharacters.equals("ven")) {
            return generateForms("6", baseForm);
        }

        // last 2 characters
        String lastTwoCharacters = baseForm.substring(length - 2);
        if (lastTwoCharacters.equals("ia")) {
            return generateForms("7", baseForm);
        }
        if (lastTwoCharacters.equals("ea")) {
            return generateForms("9", baseForm);
        }
        char secondToTheLastCharacter = baseForm.charAt(length-2);
        char lastCharacter = baseForm.charAt(length-1);
        if (isVocal(secondToTheLastCharacter) && isDuplicatedConsonant(lastCharacter)) {
            return generateForms("10", baseForm);
        }

        // last characters
        if (baseForm.substring(length - 1).equals("o")) {
            return generateForms("8", baseForm);
        }

        return generateForms("1", baseForm);
    }

    public String getRoot(String maleSingularForm, String adjectiveClass) {
        if (maleSingularForm == null) {
            return maleSingularForm;
        }

        maleSingularForm = normalizeString(maleSingularForm);
        adjective.setBaseForm(maleSingularForm);

        if (maleSingularForm.length() < 2) {
            throw new RuntimeException("'" + maleSingularForm + "' is not a valid male singular form. Please enter a male singular form.");
        }

        return extractRoot(maleSingularForm, adjectiveClass);
    }

    public String extractRoot(String maleSingularForm, String adjectiveClass) {
        switch (adjectiveClass) {

            case "4":
                if (maleSingularForm.length() < 4) {
                    throw new RuntimeException("'" + maleSingularForm + "' is not a valid male singular form. Please enter a male singular form.");
                }
                ending = maleSingularForm.substring(maleSingularForm.length() - 4);
                return maleSingularForm.substring(0, maleSingularForm.length() - 4);

            case "2":
            case "3":
            case "5":
            case "6":
                if (maleSingularForm.length() < 3) {
                    throw new RuntimeException("'" + maleSingularForm + "' is not a valid male singular form. Please enter a male singular form.");
                }
                ending = maleSingularForm.substring(maleSingularForm.length() - 3);
                return maleSingularForm.substring(0, maleSingularForm.length() - 3);

            case "7":
            case "9":
                ending = maleSingularForm.substring(maleSingularForm.length() - 2);
                return maleSingularForm.substring(0, maleSingularForm.length() - 2);

            case "8":

                ending = maleSingularForm.substring(maleSingularForm.length() - 1);
                return maleSingularForm.substring(0, maleSingularForm.length() - 1);

            case "10":
                ending = maleSingularForm.substring(maleSingularForm.length() - 1);
                return maleSingularForm;

            case "1":
            default:
                ending = "";
                return maleSingularForm;
        }
    }

    public void buildForms(InflectionSubType adjectiveClass) {
        inflection.setInflectionSubtype(adjectiveClass.id);

        setSingular();
        setPlural();
        setAdverbialForm();
    }

    public String buildPlural(String base) {
        String l1 = base.substring(base.length() - 1);

        if (base.contains("-")) {
            String[] s = base.split("-", 2);
            return  buildPlural(s[0]) + "-" + s[1];
        }

        if (l1.equals("s")) {
            return base;
        }

        return base + "s";
    }

    public void setSingular() {
        switch (ending) {
            case "":
                adjective.setMSingular(root);
                adjective.setFSingular(root + "a");
                break;

            case "der":
                adjective.setMSingular(root + "der");
                adjective.setFSingular(root + "dra");
                break;

            case "bel":
                adjective.setMSingular(root + "bel");
                adjective.setFSingular(root + "bla");
                break;

            case "cher":
                adjective.setMSingular(root + "cher");
                adjective.setFSingular(root + "cra");
                break;

            case "vel":
                adjective.setMSingular(root + "vel");
                adjective.setFSingular(root + "vla");
                break;

            case "ven":
                adjective.setMSingular(root + "ven");
                adjective.setFSingular(root + "vna");
                break;

            case "ia":
                adjective.setMSingular(root + "ia");
                adjective.setFSingular(root + "eida");
                break;

            case "o":
                adjective.setMSingular(root + "o");
                adjective.setFSingular(root + "ada");
                break;

            case "ea":
                adjective.setMSingular(root + "ea");
                adjective.setFSingular(root + "eda");
                break;

            case "c":
            case "f":
            case "m":
            case "n":
            case "p":
            case "r":
            case "t":
            case "z":
                adjective.setMSingular(root);
                adjective.setFSingular(root + ending + "a");
        }
    }

    public void setPlural() {
        adjective.setMPlural(buildPlural(adjective.getMSingular()));
        adjective.setFPlural(buildPlural(adjective.getFSingular()));
    }

    public void setAdverbialForm() {
        String ending = adjective.getMSingular().substring(adjective.getMSingular().length() - 2);
        if (ending.equals("al") || ending.equals("ar")) {
            adjective.setAdverbialForm(adjective.getMSingular() + "maintg");
            return;
        }

        adjective.setAdverbialForm(adjective.getFSingular() + "maintg");
    }

    private boolean isDuplicatedConsonant(char ch) {
        return switch (ch) {
            case 'c', 'f', 'm', 'n', 'p', 'r', 't', 'z' -> true;
            default -> false;
        };
    }
}
