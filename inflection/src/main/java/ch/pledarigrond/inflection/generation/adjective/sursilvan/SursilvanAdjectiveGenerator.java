package ch.pledarigrond.inflection.generation.adjective.sursilvan;

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
public class SursilvanAdjectiveGenerator extends LanguageAdjectiveGeneration {

    private static final Logger logger = LoggerFactory.getLogger(SursilvanAdjectiveGenerator.class);

    private InflectionDto inflection;
    private AdjectiveDto adjective;

    private String root;
    private String ending;
    private InflectionSubType adjectiveClass;

    public void reset() {
        inflection = new InflectionDto();
        adjective = new AdjectiveDto();
        inflection.setAdjective(adjective);
        inflection.setInflectionType(InflectionType.ADJECTIVE);
        root = "";
        ending = "";
        adjectiveClass = null;
    }

    public InflectionDto generateForms(String adjectiveClassId, String baseForm) {
        reset();

        root = getRoot(baseForm, adjectiveClassId);

        InflectionSubType subType = SursilvanAdjectiveClasses.getAdjectiveInflectionClass(adjectiveClassId);
        if (subType == null) {
            throw new RuntimeException(adjectiveClassId + " is not a valid adjective class.");
        } else if (getEnding() == null) {
            throw new RuntimeException(baseForm + " is not a valid male singular form. Please enter a valid form.");
        }
        adjectiveClass = subType;
        adjective.setInflectionSubtype(adjectiveClass.id);
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

        if (baseForm.matches(".*, -(el|al|en|an|er|iu|au)$")) {
            String[] parts = baseForm.split(", -");
            baseForm = parts[0];

            switch (parts[1]) {
                case "el":
                    return generateForms("2", baseForm);
                case "al":
                    return generateForms("3", baseForm);
                case "en":
                    return generateForms("4", baseForm);
                case "er":
                    return generateForms("6", baseForm);
                case "iu":
                    return generateForms("7", baseForm);
                case "au":
                    return generateForms("8", baseForm);
                default:
                    logger.error("unknown ending: {}", parts[1]);
                    return null;
            }
        }

        // do not generate forms if word contains whitespaces
        if (baseForm.matches(".*\\s+.*")) {
            return null;
        }

        // last 2 characters
        String lastTwoCharacters = baseForm.substring(length - 2);
        if (lastTwoCharacters.equals("el")) {
            return generateForms("2", baseForm);
        }
        if (lastTwoCharacters.equals("al")) {
            return generateForms("3", baseForm);
        }
        if (lastTwoCharacters.equals("en")) {
            return generateForms("4", baseForm);
        }
        if (lastTwoCharacters.equals("er")) {
            return generateForms("6", baseForm);
        }
        if (lastTwoCharacters.equals("iu")) {
            return generateForms("7", baseForm);
        }
        if (lastTwoCharacters.equals("au")) {
            return generateForms("8", baseForm);
        }

        char secondToTheLastCharacter = baseForm.charAt(length-2);
        char lastCharacter = baseForm.charAt(length-1);
        if (isVocal(secondToTheLastCharacter) && isDuplicatedConsonant(lastCharacter)) {
            return generateForms("10", baseForm);
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
            case "2":
            case "3":
            case "4":
            case "6":
            case "7":
            case "8":
                ending = maleSingularForm.substring(maleSingularForm.length() - 2);
                return maleSingularForm.substring(0, maleSingularForm.length() - 2);

            case "10":
                ending = maleSingularForm.substring(maleSingularForm.length() - 1);
                return maleSingularForm;

            case "1":
            default:
                ending = "";
                return maleSingularForm;
        }
    }

    public void buildForms() {
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
                adjective.setPredicative(root + "s");
                adjective.setFSingular(root + "a");
                break;

            case "el":
                adjective.setMSingular(root + "el");
                adjective.setPredicative(root + "els");
                adjective.setFSingular(root + "la");
                break;

            case "al":
                adjective.setMSingular(root + "al");
                adjective.setPredicative(root + "als");
                adjective.setFSingular(root + "la");
                break;

            case "en":
                adjective.setMSingular(root + "en");
                adjective.setPredicative(root + "ens");
                adjective.setFSingular(root + "na");
                break;

            case "er":
                adjective.setMSingular(root + "er");
                adjective.setPredicative(root + "ers");
                adjective.setFSingular(root + "ra");
                break;

            case "iu":
                adjective.setMSingular(root + "iu");
                adjective.setPredicative(root + "ius");
                adjective.setFSingular(root + "ida");
                break;

            case "au":
                adjective.setMSingular(root + "au");
                adjective.setPredicative(root + "aus");
                adjective.setFSingular(root + "ada");
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
                adjective.setPredicative(root + "s");
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
            adjective.setAdverbialForm(adjective.getMSingular() + "mein");
            return;
        }

        adjective.setAdverbialForm(adjective.getFSingular() + "mein");
    }

    private boolean isDuplicatedConsonant(char ch) {
        return switch (ch) {
            case 'c', 'f', 'm', 'n', 'p', 'r', 't', 'z' -> true;
            default -> false;
        };
    }
}
