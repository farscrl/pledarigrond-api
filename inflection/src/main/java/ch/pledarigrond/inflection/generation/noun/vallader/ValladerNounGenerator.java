package ch.pledarigrond.inflection.generation.noun.vallader;

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
public class ValladerNounGenerator extends LanguageNounGeneration {

    private static final Logger logger = LoggerFactory.getLogger(ValladerNounGenerator.class);

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

        baseForm = PronunciationNormalizer.normalizePronunciation(baseForm);
        root = getRoot(baseForm, nounClassId);

        InflectionSubType subType = ValladerNounClasses.getNounInflectionClass(nounClassId);
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

        if (baseForm.matches(".*, -(a|dra|bla|vla|cra|vna|vra|ada|üda|ida)$")) {
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
                case "ada":
                    return generateForms("10", baseForm);
                case "üda":
                    return generateForms("11", baseForm);
                case "ida":
                    return generateForms("12", baseForm);
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

        // last characters
        if (baseForm.substring(length - 1).equals("à")) {
            return generateForms("10", baseForm);
        }

        if (baseForm.substring(length - 1).equals("ü")) {
            return generateForms("11", baseForm);
        }

        if (baseForm.substring(length - 1).equals("i")) {
            return generateForms("12", baseForm);
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
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                if (maleSingularForm.length() < 3) {
                    throw new RuntimeException("'" + maleSingularForm + "' is not a valid male singular form. Please enter a male singular form.");
                }
                ending = maleSingularForm.substring(maleSingularForm.length() - 3);
                return maleSingularForm.substring(0, maleSingularForm.length() - 3);

            case "10":
            case "11":
            case "12":
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

        if (l1.equals("à")) {
            return base.substring(0, base.length() - 1) + "ats";
        }

        if (l1.equals("è")) {
            return base.substring(0, base.length() - 1) + "els";
        }

        if (l1.equals("i")) {
            return base.substring(0, base.length() - 1) + "its";
        }

        if (l1.equals("ü")) {
            return base.substring(0, base.length() - 1) + "üts";
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
                if (nounClass.id.equals("13")) {
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

            case "à":
                noun.setMSingular(root + "à");
                noun.setFSingular(root + "ada");
                break;

            case "ü":
                noun.setMSingular(root + "ü");
                noun.setFSingular(root + "üda");
                break;

            case "i":
                noun.setMSingular(root + "i");
                noun.setFSingular(root + "ida");
                break;
        }
    }

    public void setPlural() {
        // collectiv
        if (nounClass.id.equals("13")) {
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
