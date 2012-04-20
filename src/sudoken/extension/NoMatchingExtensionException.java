package sudoken.extension;

/**
 * Thrown when the ExtensionManager could not find any matching extension to a
 * given puzzle file.
 * 
 * @author Kevin Doran
 * 
 */
public class NoMatchingExtensionException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -2184147238426267004L;

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
