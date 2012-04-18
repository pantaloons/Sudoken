package sudoken.gui.errordisplay;

import java.awt.Component;

/**
 * Defines the functionality of an error displaying class.
 * 
 * @author Kevin Doran
 */
public interface ErrorDisplay {
    /**
     * Sets the swing component over which to display errors.
     * 
     * @param parentComponent
     *            the swing component over which to display errors.
     */
    void setParentComponent(Component parentComponent);

    /**
     * Instructs the message displayer to display the given error message.
     * 
     * @param message
     *            the message to display.
     */
    void showErrorMessage(String message);

    void showErrorMessage(Component parentComponent, String message);
}
