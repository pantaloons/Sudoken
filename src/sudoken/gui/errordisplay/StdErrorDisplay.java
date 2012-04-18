package sudoken.gui.errordisplay;

import java.awt.Component;

/**
 * An error display class that displays given errors on standard error stream.
 * 
 * @author Kevin Doran
 * 
 */
public class StdErrorDisplay implements ErrorDisplay {
    
    
    public StdErrorDisplay() {
        super();
    }
    
    /**
     * Displays the given message on the standard error stream.
     */
    @Override
    public void showErrorMessage(String message) {
        System.err.println(message);
    }

    /**
     * {@code StdErrorDisplay} doesn't need a parent component. This method does
     * nothing.
     */
    @Override
    public void setParentComponent(Component parentComponent) {
        // StdErrorDisplay doesn't need a parentComponent.
    }

    @Override
    public void showErrorMessage(Component parentComponent, String message) {
        showErrorMessage(message);
    }
}
