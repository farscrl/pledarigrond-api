package ch.pledarigrond.inflection.generation.noun.puter;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.common.data.dictionary.inflection.InflectionType;
import ch.pledarigrond.common.data.dictionary.inflection.NounDto;
import ch.pledarigrond.common.util.PronunciationNormalizer;
import ch.pledarigrond.inflection.generation.noun.LanguageNounGeneration;
import ch.pledarigrond.inflection.model.InflectionSubType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
@EqualsAndHashCode(callSuper = true)
public class PuterNounGenerator extends LanguageNounGeneration {

    private static final Logger logger = LoggerFactory.getLogger(PuterNounGenerator.class);

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
        nounClass = null;
    }

    public InflectionDto generateForms(String nounClassId, String baseForm) {
        reset();

        baseForm = PronunciationNormalizer.normalizePronunciation(baseForm);
        root = getRoot(baseForm, nounClassId);

        InflectionSubType subType = PuterNounClasses.getNounInflectionClass(nounClassId);
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

        baseForm = PronunciationNormalizer.normalizePronunciation(baseForm);
        baseForm = normalizeString(baseForm);
        int length = baseForm.length();
        if (length < 3) {
            return null;
        }

        if (baseForm.matches(".*, -(a|dra|bla|vla|cra|vna|vra|eda|gheda|glieda|ida|glida)$")) {
            String[] parts = baseForm.split(", -");
            baseForm = parts[0];

            switch (parts[1]) {
                case "a":
                    return generateForms("3", baseForm);
                case "dra":
                    return generateForms("4", baseForm);
                case "bla":
                    return generateForms("5", baseForm);
                case "vla":
                    return generateForms("6", baseForm);
                case "cra":
                    return generateForms("7", baseForm);
                case "vna":
                    return generateForms("8", baseForm);
                case "vra":
                    return generateForms("9", baseForm);
                case "eda":
                    return generateForms("10", baseForm);
                case "gheda":
                    return generateForms("11", baseForm);
                case "glieda":
                    return generateForms("12", baseForm);
                case "ida":
                    return generateForms("13", baseForm);
                case "glida":
                    return generateForms("14", baseForm);
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
            if (lastFiveCharacters.equals("glieu")) {
                return generateForms("14", baseForm);
            }
        }

        // last 4 characters
        if (length > 4) {
            String lastFourCharacters = baseForm.substring(length - 4);
            if (lastFourCharacters.equals("glio")) {
                return generateForms("12", baseForm);
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
        if (lastThreeCharacters.equals("vel")) {
            return generateForms("6", baseForm);
        }
        if (lastThreeCharacters.equals("ker")) {
            return generateForms("7", baseForm);
        }
        if (lastThreeCharacters.equals("ven")) {
            return generateForms("8", baseForm);
        }
        if (lastThreeCharacters.equals("ver")) {
            return generateForms("9", baseForm);
        }
        if (lastThreeCharacters.equals("ieu")) {
            return generateForms("13", baseForm);
        }

        // last 2 characters
        String lastTwoCharacters = baseForm.substring(length - 2);
        if (lastTwoCharacters.equals("go")) {
            return generateForms("11", baseForm);
        }

        // last characters
        if (baseForm.substring(length - 1).equals("o")) {
            return generateForms("10", baseForm);
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
            case "14":
                if (maleSingularForm.length() < 5) {
                    throw new RuntimeException("'" + maleSingularForm + "' is not a valid male singular form. Please enter a male singular form.");
                }
                ending = maleSingularForm.substring(maleSingularForm.length() - 5);
                return maleSingularForm.substring(0, maleSingularForm.length() - 5);

            case "12":
                if (maleSingularForm.length() < 4) {
                    throw new RuntimeException("'" + maleSingularForm + "' is not a valid male singular form. Please enter a male singular form.");
                }
                ending = maleSingularForm.substring(maleSingularForm.length() - 4);
                return maleSingularForm.substring(0, maleSingularForm.length() - 4);

            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "13":
                if (maleSingularForm.length() < 3) {
                    throw new RuntimeException("'" + maleSingularForm + "' is not a valid male singular form. Please enter a male singular form.");
                }
                ending = maleSingularForm.substring(maleSingularForm.length() - 3);
                return maleSingularForm.substring(0, maleSingularForm.length() - 3);

            case "11":
                ending = maleSingularForm.substring(maleSingularForm.length() - 2);
                return maleSingularForm.substring(0, maleSingularForm.length() - 2);

            case "10":
                ending = maleSingularForm.substring(maleSingularForm.length() - 1);
                return maleSingularForm.substring(0, maleSingularForm.length() - 1);

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
                if (nounClass.id.equals("15")) {
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

            case "vel":
                noun.setMSingular(root + "vel");
                noun.setFSingular(root + "vla");
                break;

            case "ker":
                noun.setMSingular(root + "ker");
                noun.setFSingular(root + "cra");
                break;

            case "ven":
                noun.setMSingular(root + "ven");
                noun.setFSingular(root + "vna");
                break;

            case "ver":
                noun.setMSingular(root + "ver");
                noun.setFSingular(root + "vra");
                break;

            case "o":
                noun.setMSingular(root + "o");
                noun.setFSingular(root + "eda");
                break;

            case "go":
                noun.setMSingular(root + "go");
                noun.setFSingular(root + "gheda");
                break;

            case "glio":
                noun.setMSingular(root + "glio");
                noun.setFSingular(root + "glieda");
                break;

            case "ieu":
                noun.setMSingular(root + "ieu");
                noun.setFSingular(root + "ida");
                break;

            case "glieu":
                noun.setMSingular(root + "glieu");
                noun.setFSingular(root + "glida");
                break;
        }
    }

    public void setPlural() {
        // collectiv
        if (nounClass.id.equals("15")) {
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
