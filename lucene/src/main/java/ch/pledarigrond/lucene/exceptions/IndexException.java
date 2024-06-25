package ch.pledarigrond.lucene.exceptions;

public class IndexException extends Exception {

	public IndexException() { super(); }

	public IndexException(String message) {
		super(message);
	}

	public IndexException(Throwable cause) {
		super(cause);
	}

	public IndexException(String message, Throwable cause) {
		super(message, cause);
	}
}
