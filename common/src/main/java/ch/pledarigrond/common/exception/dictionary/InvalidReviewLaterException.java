package ch.pledarigrond.common.exception.dictionary;

public class InvalidReviewLaterException extends Exception {
    public InvalidReviewLaterException(String versionId) {
        super("Entry version with id " + versionId + " has no inflection and cannot be reviewed later.");
    }
}
