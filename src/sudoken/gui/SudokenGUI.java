package sudoken.gui;

import javax.swing.JComponent;
import javax.swing.JPanel;

import sudoken.extension.ExtensionListener;
import sudoken.solver.SolverListener;

public interface SudokenGUI extends ExtensionListener, SolverListener {

    public void setIsPuzzleLoaded(boolean isLoaded);
    public void setSolved(boolean isSuccess);
    public void reset();
    public JPanel getPanel();
    public void setController(Controller controller);
}
