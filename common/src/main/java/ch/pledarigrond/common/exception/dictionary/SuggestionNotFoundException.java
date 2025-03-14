package ch.pledarigrond.common.exception.dictionary;

public class SuggestionNotFoundException extends Exception {
    public SuggestionNotFoundException(String suggestionId) {
        super("Suggestion with id " + suggestionId + " not found");
    }
}
