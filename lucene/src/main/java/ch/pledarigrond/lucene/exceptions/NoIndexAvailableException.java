package ch.pledarigrond.lucene.exceptions;

public class NoIndexAvailableException extends IndexException {

	public NoIndexAvailableException() {
		super();
	}

	public NoIndexAvailableException(String message) {
		super(message);
	}

	public NoIndexAvailableException(Throwable cause) {
		super(cause);
	}

	public NoIndexAvailableException(String message, Throwable cause) {
		super(message, cause);
	}
}
