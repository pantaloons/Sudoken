package sudoken.gui;

import javax.swing.JPanel;

import sudoken.extension.ExtensionListener;
import sudoken.gui.controller.Controller;
import sudoken.solver.BoardChangeListener;

/**
 * Creates a GUI to control and display the sudoku puzzle solver.
 * 
 * @author Kevin Doran
 * 
 */
public interface SudokenGUI extends ExtensionListener, BoardChangeListener {

    /**
     * Puts the GUI in the puzzle loaded or puzzle not-loaded state.
     * 
     * @param isLoaded
     *            the state to put the GUI in. {@code true}: the loaded state;
     *            {@code false}: the not-loaded state.
     */
    public void setIsPuzzleLoaded(boolean isLoaded);

    /**
     * Puts the GUI in the puzzle solved or puzzle not-solved state.
     * 
     * @param isSuccess
     *            the state to put the GUI in. {@code true}: the solved state;
     *            {@code false}: the not-solved state.
     */
    public void setSolved(boolean isSuccess);

    /**
     * Resets the GUI. A reset GUI acts as if it has just started up.
     */
    public void reset();

    /**
     * @return the JPanel containing all the GUI components.
     */
    public JPanel getPanel();

    /**
     * Sets the controller that the GUI will use to control the solver.
     * 
     * @param controller
     *            a controller for the GUI to use.
     */
    public void setController(Controller controller);
}
