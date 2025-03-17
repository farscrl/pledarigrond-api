package ch.pledarigrond.inflection.generation.noun.sursilvan;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.common.data.dictionary.inflection.InflectionType;
import ch.pledarigrond.common.data.dictionary.inflection.NounDto;
import ch.pledarigrond.inflection.generation.noun.LanguageNounGeneration;
import ch.pledarigrond.inflection.model.InflectionSubType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@EqualsAndHashCode(callSuper = true)
public class SursilvanNounGenerator extends LanguageNounGeneration {

    private static final Logger logger = LoggerFactory.getLogger(SursilvanNounGenerator.class);

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

        InflectionSubType subType = SursilvanNounClasses.getNounInflectionClass(nounClassId);
        if (subType == null) {
            throw new RuntimeException(nounClassId + " is not a valid inflection class.");
        } else if (getEnding() == null) {
            throw new RuntimeException(baseForm + " is not a valid male singular form. Please enter a valid form.");
        }

        nounClass = subType;
        inflection.setInflectionSubtype(nounClass.id);
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

        if (baseForm.matches(".*, -(bla|alla|astra|ta|atta|ada|edra|na|ra|idra|tra|occa|utta|essa|cra|vla)$")) {
            String[] parts = baseForm.split(", -");
            baseForm = parts[0];

            switch (parts[1]) {
                case "bla":
                    return generateForms("4", baseForm);
                case "astra":
                    return generateForms("6", baseForm);
                case "ta":
                case "atta":
                    return generateForms("7", baseForm);
                case "ada":
                    return generateForms("8", baseForm);
                case "edra":
                    return generateForms("9", baseForm);
                case "na":
                    return generateForms("10", baseForm);
                case "ra":
                    return generateForms("11", baseForm);
                case "idra":
                    return generateForms("13", baseForm);
                case "tra":
                    return generateForms("15", baseForm);
                case "occa":
                    return generateForms("16", baseForm);
                case "utta":
                    return generateForms("17", baseForm);
                case "essa":
                    return generateForms("18", baseForm);
                case "cra":
                    return generateForms("19", baseForm);
                case "vla":
                    return generateForms("20", baseForm);

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

        // last 5 characters
        if (length > 5) {
            String lastFiveCharacters = baseForm.substring(length - 5);
            if (lastFiveCharacters.equals("aster")) {
                return generateForms("6", baseForm);
            }

        }

        // last 4 characters
        if (length > 4) {
            String lastFourCharacters = baseForm.substring(length - 4);
            if (lastFourCharacters.equals("cher")) {
                return generateForms("19", baseForm);
            }
            if (lastFourCharacters.equals("ider")) {
                return generateForms("13", baseForm);
            }
            if (lastFourCharacters.equals("eder")) {
                return generateForms("9", baseForm);
            }
        }

        // last 3 characters
        String lastThreeCharacters = baseForm.substring(length - 3);
        if (lastThreeCharacters.equals("bel")) {
            return generateForms("4", baseForm);
        }
        if (lastThreeCharacters.equals("ter")) {
            return generateForms("15", baseForm);
        }
        if (lastThreeCharacters.equals("vel")) {
            return generateForms("20", baseForm);
        }

        // last 2 characters
        String lastTwoCharacters = baseForm.substring(length - 2);
        if (lastTwoCharacters.equals("at")) {
            // only for words like tat or mat.
            if (isSingleSyllable(baseForm)) {
                return generateForms("7", baseForm);
            }
            // regular for advocat, advocata
        }
        if (lastTwoCharacters.equals("au")) {
            return generateForms("8", baseForm);
        }
        if (lastTwoCharacters.equals("en")) {
            return generateForms("10", baseForm);
        }
        if (lastTwoCharacters.equals("er")) {
            return generateForms("11", baseForm);
        }
        if (lastTwoCharacters.equals("oc")) {
            return generateForms("16", baseForm);
        }
        if (lastTwoCharacters.equals("ut")) {
            return generateForms("17", baseForm);
        }
        if (lastTwoCharacters.equals("on")) {
            return generateForms("18", baseForm);
        }
        if (lastTwoCharacters.equals("un")) {
            return generateForms("18", baseForm);
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
                if (maleSingularForm.length() < 5) {
                    throw new RuntimeException("'" + maleSingularForm + "' is not a valid male singular form. Please enter a male singular form.");
                }
                ending = maleSingularForm.substring(maleSingularForm.length() - 5);
                return maleSingularForm.substring(0, maleSingularForm.length() - 5);

            case "9":
            case "13":
            case "19":
                if (maleSingularForm.length() < 4) {
                    throw new RuntimeException("'" + maleSingularForm + "' is not a valid male singular form. Please enter a male singular form.");
                }
                ending = maleSingularForm.substring(maleSingularForm.length() - 4);
                return maleSingularForm.substring(0, maleSingularForm.length() - 4);

            case "4":
            case "15":
            case "20":
                if (maleSingularForm.length() < 3) {
                    throw new RuntimeException("'" + maleSingularForm + "' is not a valid male singular form. Please enter a male singular form.");
                }
                ending = maleSingularForm.substring(maleSingularForm.length() - 3);
                return maleSingularForm.substring(0, maleSingularForm.length() - 3);

            case "7":
            case "8":
            case "10":
            case "11":
            case "16":
            case "17":
                ending = maleSingularForm.substring(maleSingularForm.length() - 2);
                return maleSingularForm.substring(0, maleSingularForm.length() - 2);

            case "18":
                if (maleSingularForm.endsWith("t")) {
                    ending = maleSingularForm.substring(maleSingularForm.length() - 1);
                    return maleSingularForm;
                }
                ending = maleSingularForm.substring(maleSingularForm.length() - 2);
                return maleSingularForm;

            case "1":
            case "2":
            case "3":
            case "21":
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

        if (base.contains("-")) {
            String[] s = base.split("-", 2);
            return  buildPlural(s[0]) + "-" + s[1];
        }

        if (base.endsWith("schi") || base.endsWith("gni")) {
            return base.substring(0, base.length() - 1) + "als";
        }

        if (base.endsWith("i")) {
            return base + "als";
        }

        if (base.endsWith("gliel")) {
            return base.substring(0, base.length() - 2) + "euls";
        }

        if (base.endsWith("iel")) {
            return base.substring(0, base.length() - 3) + "euls";
        }

        if (isSingleSyllable(base)) {
            // for single syllable fiep -> fops
            Pattern pattern = Pattern.compile(".*ie([b-df-hj-np-tv-z]+)$");
            Matcher matcher = pattern.matcher(base);
            if (matcher.matches()) {
                String consonant = matcher.group(1);
                if (base.endsWith("schie" + consonant) || base.endsWith("gnie" + consonant)) {
                    return base.substring(0, base.length() - 2 - consonant.length()) + "a" + consonant + "s";
                }
                return base.substring(0, base.length() - 2 - consonant.length()) + "o" + consonant + "s";
            }
        } else {
            // for multi syllable paliet -> paliats
            Pattern pattern = Pattern.compile(".*ie([b-df-hj-np-tv-z]+)$");
            Matcher matcher = pattern.matcher(base);
            if (matcher.matches()) {
                String consonant = matcher.group(1);
                return base.substring(0, base.length() - 2 - consonant.length()) + "ia" + consonant + "s";
            }
        }


        if (l1.equals("s")) {
            return base;
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
                if (nounClass.id.equals("21")) {
                    noun.setMSingular(root);
                    noun.setFSingular(null);
                }
                break;

            case "bel":
                noun.setMSingular(root + "bel");
                noun.setFSingular(root + "bla");
                break;

            case "aster":
                noun.setMSingular(root + "aster");
                noun.setFSingular(root + "astra");
                break;

            case "at":
                noun.setMSingular(root + "at");
                noun.setFSingular(root + "atta");
                break;

            case "au":
                noun.setMSingular(root + "au");
                noun.setFSingular(root + "ada");
                break;

            case "eder":
                noun.setMSingular(root + "eder");
                noun.setFSingular(root + "edra");
                break;

            case "en":
                noun.setMSingular(root + "en");
                noun.setFSingular(root + "na");
                break;

            case "er":
                noun.setMSingular(root + "er");
                noun.setFSingular(root + "ra");
                break;

            case "ider":
                noun.setMSingular(root + "ider");
                noun.setFSingular(root + "idra");
                break;

            case "ter":
                noun.setMSingular(root + "ter");
                noun.setFSingular(root + "tra");
                break;

            case "oc":
                noun.setMSingular(root + "oc");
                noun.setFSingular(root + "occa");
                break;

            case "on":
            case "un":
            case "t":
                noun.setMSingular(root);
                noun.setFSingular(root + "essa");
                break;

            case "cher":
                noun.setMSingular(root + "cher");
                noun.setFSingular(root + "cra");
                break;

            case "vel":
                noun.setMSingular(root + "vel");
                noun.setFSingular(root + "vla");
                break;

            case "ut":
                noun.setMSingular(root + "ut");
                noun.setFSingular(root + "utta");
                break;
        }
    }

    public void setPlural() {
        // collectiv
        if (nounClass.id.equals("21")) {
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
}
