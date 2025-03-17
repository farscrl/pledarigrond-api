package ch.pledarigrond.inflection.generation.verb;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.inflection.LanguageInflectionBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class LanguageConjugation extends LanguageInflectionBase {

    public abstract void reset();

    public abstract InflectionDto guessInflection(String baseForm, String genus, String flex);

    public abstract InflectionDto generateConjugation(String conjugationClass, String infinitiv);


    protected boolean endsWithDoubleConsonant(String root) {
        if (root.length() == 1) {
            return false;
        }
        return root.charAt(root.length() - 1) == root.charAt(root.length() - 2);
    }

    protected boolean startsWithVowel(String root) {
        return switch (root.substring(0, 1)) {
            case "a", "e", "i", "o", "u", "ä", "ö", "ü" -> true;
            default -> false;
        };
    }

    protected String setPronoun(String pronoun, String forms) {
        return setPronoun(pronoun, forms, "");
    }

    protected String setPronoun(String pronoun, String forms, String suffix) {
        String[] singleForms = forms.split("\\R");
        for (int i = 0; i < singleForms.length; i++) {
            singleForms[i] = pronoun + singleForms[i] + suffix;
        }
        return String.join("\n", singleForms);
    }
}
