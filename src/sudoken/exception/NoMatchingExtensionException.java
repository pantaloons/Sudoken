package sudoken.exception;

/**
 * Thrown when the ExtensionManager could not find any matching extension to a
 * given puzzle file.
 * 
 * @author Kevin Doran
 * 
 */
public class NoMatchingExtensionException extends Exception {

    public NoMatchingExtensionException() {
        super();
    }

    public NoMatchingExtensionException(String message) {
        super(message);
    }

    public NoMatchingExtensionException(String message, Throwable cause) {
        super(message, cause);
    }
}
