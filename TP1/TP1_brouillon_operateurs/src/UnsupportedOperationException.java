/**
 * This exception is thrown when trying to execute an unsupported operation
 *
 * @author Samuel Rondeau, Jennifer Khoury
 * @version 1.0
 */
public class UnsupportedOperationException extends Exception {

   /**
	* Constructs a new UnsupportedOperationException.
	*/
	public UnsupportedOperationException() {
		super();
	}

   /**
	* Constructs a new UnsupportedOperationException with a message.
	* @param message a message
	*/
	public UnsupportedOperationException(final String message) {
		super(message);
	}
}
