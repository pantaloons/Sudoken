package sudoken.gui;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import sudoken.extension.ExtensionListener;
import sudoken.gui.controller.Controller;
import sudoken.solver.BoardChangeListener;

public interface SudokenGUI extends ExtensionListener, BoardChangeListener {

    public void setIsPuzzleLoaded(boolean isLoaded);
    public void setSolved(boolean isSuccess);
    public void reset();
    public JPanel getPanel();
    public void setController(Controller controller);
}
