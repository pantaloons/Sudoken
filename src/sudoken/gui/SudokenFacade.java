package sudoken.gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import sudoken.extension.ExtensionManager;
import sudoken.gui.controller.Controller;
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
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Put up with the standard look-and-feel.
        }
        
        SudokenGUI gui = new SudokenGUI();
        Solver puzzleSolver = new BacktrackingSolver();
        Controller.createController(puzzleSolver, gui);
        wrappingFrame = new WrappingFrame("Sudoken", gui.getPanel()).getFrame();
        //wrappingFrame.setJMenuBar(gui.getMenuBar());
        //This only works for the .jar, as we don't really want to import resources into our src directory
        if(getClass().getClassLoader().getResource("icon.png") != null) {
            wrappingFrame.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("icon.png")).getImage());
        }
    }

    public void start() {
        wrappingFrame.setVisible(true);
    }

    public static void main(String[] args) {
        ExtensionManager.startLoadingExtensions();
        
        //GUI needs to be run inside invokeLater, as the dispatch thread is not the main thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SudokenFacade().start();
            }
        });
        
    }

}
