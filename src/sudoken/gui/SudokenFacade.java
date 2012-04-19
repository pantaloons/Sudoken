package sudoken.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import sudoken.domain.Board;
import sudoken.extension.Extension;
import sudoken.extension.ExtensionManager;
import sudoken.solver.BacktrackingSolver;
import sudoken.solver.Solver;

public class SudokenFacade {
    private JFrame wrappingFrame;
    
    public SudokenFacade() {
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
    }
    
    public void start() {
        wrappingFrame.setVisible(true);
    }
    
    public static void main(String[] args) {
        new SudokenFacade().start();
    }
    
}
