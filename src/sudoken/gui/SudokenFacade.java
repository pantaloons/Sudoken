package sudoken.gui;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import sudoken.domain.*;
import sudoken.extension.ExtensionManager;

/**
 * Connects the GUI, controller and the solver together and starts the system.
 * 
 * @author Kevin Doran
 *
 */
class SudokenFacade {
	/** Frame to wrap the Facade components in */
	private JFrame wrappingFrame;
	
	/**
	 * Create a SudokenFacade, initialising the GUI, Solver and Controller
	 */
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
		} 
		catch (Exception e) {
			// Put up with the standard look-and-feel.
		}
		
		SudokenGUI gui = new SudokenGUI();
		Solver puzzleSolver = new BacktrackingSolver();
		//Solver puzzleSolver = new SmartBacktrackingSolver();
		Controller.createController(puzzleSolver, gui);
		wrappingFrame = new WrappingFrame("Sudoken", gui.getPanel()).getFrame();
		
		//This only works for the .jar, as we don't really want to import resources into our src directory
		URL iconUrl = getClass().getClassLoader().getResource("icon.png");
		if (iconUrl != null) {
			wrappingFrame.setIconImage(new ImageIcon(iconUrl).getImage());
		}
	}
	
	/**
	 * Start the Facade, making it visible
	 */
	private void start() {
		wrappingFrame.setVisible(true);
	}
	
	/**
	 * Main entry point of Sudoken.
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		ExtensionManager.startLoadingExtensions();
		
		// GUI needs to be run inside invokeLater, as the dispatch thread is not the main thread
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new SudokenFacade().start();
			}
		});
	}
}
