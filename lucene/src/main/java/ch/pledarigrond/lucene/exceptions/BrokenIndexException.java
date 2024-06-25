package ch.pledarigrond.lucene.exceptions;

public class BrokenIndexException extends IndexException {

	public BrokenIndexException() { super(); }

	public BrokenIndexException(String message) {
		super(message);
	}

	public BrokenIndexException(Throwable cause) {
		super(cause);
	}

	public BrokenIndexException(String message, Throwable cause) {
		super(message, cause);
	}

}
