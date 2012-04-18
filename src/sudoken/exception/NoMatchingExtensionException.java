package sudoken.exception;

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
