package sudoken.gui;

import javax.swing.JFrame;
import javax.swing.UIManager;

import sudoken.extension.ExtensionManager;
import sudoken.gui.controller.ControllerImp;
import sudoken.gui.util.WrappingFrame;
import sudoken.solver.BacktrackingSolver;
import sudoken.solver.Solver;

/**
 * Connects the GUI, controller and the solver together and starts the system.
 * 
 * @author Kevin Doran
 *
 */
public class SudokenFacade {
    private JFrame wrappingFrame;

    public SudokenFacade() {
        // Setting the look and feel should be done as the very first step in an
        // application (see http://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html).
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Put up with the standard look-and-feel.
        }

        ExtensionManager.startLoadingExtensions();
        SudokenGUI gui = new SudokenGUIImp();
        Solver puzzleSolver = new BacktrackingSolver();
        ControllerImp.createControllerImp(puzzleSolver, gui);
        wrappingFrame = new WrappingFrame("Sudoken", gui.getPanel()).getFrame();
        // setIconImage(new ImageIcon(ClassLoader.getSystemResource("icon.png")).getImage());
    }

    public void start() {
        wrappingFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new SudokenFacade().start();
    }

}
