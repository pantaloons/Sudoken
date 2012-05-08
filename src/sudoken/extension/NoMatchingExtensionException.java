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
	 * Serialisation UID
	 */
    private static final long serialVersionUID = -2184147238426267004L;

    /**
     * Create a NoMatchingExtensionException
     */
    public NoMatchingExtensionException() {
        super();
    }

    /**
     * Create a NoMatchingExtensionException with a message
     * @param message error message
     */
    public NoMatchingExtensionException(String message) {
        super(message);
    }
}
