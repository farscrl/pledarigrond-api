package ch.pledarigrond.inflection.generation.adjective.puter;

import ch.pledarigrond.common.data.dictionary.inflection.AdjectiveDto;
import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.common.data.dictionary.inflection.InflectionType;
import ch.pledarigrond.common.util.PronunciationNormalizer;
import ch.pledarigrond.inflection.generation.adjective.LanguageAdjectiveGeneration;
import ch.pledarigrond.inflection.model.InflectionSubType;
import lombok.EqualsAndHashCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@EqualsAndHashCode(callSuper = true)
public class PuterAdjectiveGenerator extends LanguageAdjectiveGeneration {

    private static final Logger logger = LoggerFactory.getLogger(PuterAdjectiveGenerator.class);

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

        baseForm = PronunciationNormalizer.normalizePronunciation(baseForm);
        root = getRoot(baseForm, adjectiveClassId);

        InflectionSubType subType = PuterAdjectiveClasses.getAdjectiveInflectionClass(adjectiveClassId);
        if (subType == null) {
            throw new RuntimeException(adjectiveClassId + " is not a valid adjective class.");
        } else if (ending == null) {
            throw new RuntimeException(baseForm + " is not a valid male singular form. Please enter a valid form.");
        }
        adjectiveClass = subType;
        adjective.setInflectionSubtype(adjectiveClass.id);
        buildForms();
        return inflection;
    }

    public InflectionDto guessInflection(String baseForm, String genus, String flex) {
        reset();

        baseForm = PronunciationNormalizer.normalizePronunciation(baseForm);
        baseForm = normalizeString(baseForm);
        int length = baseForm.length();
        if (length < 3) {
            return null;
        }

        if (baseForm.matches(".*, -(a|ra|bla|cra|na|ma|gia|glia|eda|ida)$")) {
            String[] parts = baseForm.split(", -");
            baseForm = parts[0];

            switch (parts[1]) {
                case "a":
                    return generateForms("1", baseForm);
                case "ra":
                    return generateForms("2", baseForm);
                case "bla":
                    return generateForms("3", baseForm);
                case "cra":
                    return generateForms("4", baseForm);
                case "na":
                    return generateForms("5", baseForm);
                case "ma":
                    return generateForms("6", baseForm);
                case "gia":
                    return generateForms("7", baseForm);
                case "glia":
                    return generateForms("8", baseForm);
                case "eda":
                    return generateForms("9", baseForm);
                case "ida":
                    return generateForms("10", baseForm);
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
            if (lastFourCharacters.equals("ffan") ||
                lastFourCharacters.equals("ffem") ||
                lastFourCharacters.equals("ffer") ||
                lastFourCharacters.equals("llan") ||
                lastFourCharacters.equals("llem") ||
                lastFourCharacters.equals("ller") ||
                lastFourCharacters.equals("ssan") ||
                lastFourCharacters.equals("ssem") ||
                lastFourCharacters.equals("sser") ||
                lastFourCharacters.equals("zzan") ||
                lastFourCharacters.equals("zzem") ||
                lastFourCharacters.equals("zzer")) {
                return generateForms("11", baseForm);
            }
        }

        // last 3 characters
        String lastThreeCharacters = baseForm.substring(length - 3);
        if (lastThreeCharacters.equals("car")) {
            return generateForms("4", baseForm);
        }
        if (lastThreeCharacters.equals("ieu")) {
            return generateForms("10", baseForm);
        }

        // last 2 characters
        String lastTwoCharacters = baseForm.substring(length - 2);
        if (lastTwoCharacters.equals("er")) {
            return generateForms("2", baseForm);
        }
        if (lastTwoCharacters.equals("el")) {
            return generateForms("3", baseForm);
        }
        if (lastTwoCharacters.equals("en")) {
            return generateForms("5", baseForm);
        }
        if (lastTwoCharacters.equals("em")) {
            return generateForms("6", baseForm);
        }
        if (lastTwoCharacters.equals("gl")) {
            return generateForms("8", baseForm);
        }
        char secondToTheLastCharacter = baseForm.charAt(length-2);
        char lastCharacter = baseForm.charAt(length-1);
        if (isVocal(secondToTheLastCharacter) && isDuplicatedConsonant(lastCharacter) && isSingleSyllable(baseForm)) {
            return generateForms("12", baseForm);
        }

        // last characters
        if (baseForm.substring(length - 1).equals("g")) {
            return generateForms("7", baseForm);
        }
        if (baseForm.substring(length - 1).equals("o")) {
            return generateForms("9", baseForm);
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
            case "10":
            case "11":
                if (maleSingularForm.length() < 3) {
                    throw new RuntimeException("'" + maleSingularForm + "' is not a valid male singular form. Please enter a male singular form.");
                }
                ending = maleSingularForm.substring(maleSingularForm.length() - 3);
                return maleSingularForm.substring(0, maleSingularForm.length() - 3);

            case "2":
            case "3":
            case "5":
            case "6":
            case "8":
                ending = maleSingularForm.substring(maleSingularForm.length() - 2);
                return maleSingularForm.substring(0, maleSingularForm.length() - 2);

            case "7":
            case "9":
                ending = maleSingularForm.substring(maleSingularForm.length() - 1);
                return maleSingularForm.substring(0, maleSingularForm.length() - 1);

            case "12":
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
        String l2 = base.substring(base.length() - 2);

        if (l2.equals("ur")) {
            return base.substring(0, base.length() - 1) + "ors";
        }

        if (l1.equals("è")) {
            return base.substring(0, base.length() - 1) + "els";
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

            case "er":
                adjective.setMSingular(root + "er");
                adjective.setFSingular(root + "ra");
                break;

            case "el":
                adjective.setMSingular(root + "el");
                adjective.setFSingular(root + "la");
                break;

            case "car":
                adjective.setMSingular(root + "car");
                adjective.setFSingular(root + "cra");
                break;

            case "en":
                adjective.setMSingular(root + "en");
                adjective.setFSingular(root + "na");
                break;

            case "em":
                adjective.setMSingular(root + "em");
                adjective.setFSingular(root + "ma");
                break;

            case "g":
                adjective.setMSingular(root + "g");
                adjective.setFSingular(root + "gia");
                break;

            case "gl":
                adjective.setMSingular(root + "gl");
                adjective.setFSingular(root + "glia");
                break;

            case "o":
                adjective.setMSingular(root + "o");
                adjective.setFSingular(root + "eda");
                break;

            case "ieu":
                adjective.setMSingular(root + "ieu");
                adjective.setFSingular(root + "ida");
                break;

            case "fan":
            case "lan":
            case "san":
            case "zan":
                adjective.setMSingular(root + ending);
                adjective.setFSingular(root + "na");
                break;
            case "fem":
            case "lem":
            case "sem":
            case "zem":
                adjective.setMSingular(root + ending);
                adjective.setFSingular(root + "ma");
                break;
            case "fer":
            case "ler":
            case "ser":
            case "zer":
                adjective.setMSingular(root + ending);
                adjective.setFSingular(root + "ra");
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
        if (ending.equals("el") || ending.equals("er") || ending.equals("iv")) {
            adjective.setAdverbialForm(adjective.getMSingular() + "maing");
            return;
        }

        adjective.setAdverbialForm(adjective.getFSingular() + "maing");
    }

    private boolean isDuplicatedConsonant(char ch) {
        return switch (ch) {
            case 'c', 'f', 'm', 'n', 'p', 'r', 't', 'z' -> true;
            default -> false;
        };
    }
}
