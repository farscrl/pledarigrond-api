package ch.pledarigrond.lucene.exceptions;

public class InvalidQueryException extends IndexException {

	public InvalidQueryException() { super(); }

	public InvalidQueryException(String message) {
		super(message);
	}

	public InvalidQueryException(Throwable cause) {
		super(cause);
	}

	public InvalidQueryException(String message, Throwable cause) {
		super(message, cause);
	}
}
