package sudoken.gui;

import javax.swing.*;

import sudoken.extension.ExtensionManager;
import sudoken.gui.util.WrappingFrame;

public class SudokenLauncher
{
	/**
	 * Main entrypoint of application
	 */
	public static void main(String[] args) throws Exception
	{
		ExtensionManager.startLoadingExtensions();
		
		/* Manually register extensions */
		// FIXME: this should all be automatic + dynamic
		/*
		Class.forName("sudoken.extension.sudoku.Sudoku");
		Class.forName("sudoken.extension.futoshiki.Futoshiki");
		Class.forName("sudoken.extension.jigsaw.Jigsaw");*/
		
		/* launch gui */
		
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Put up with the standard look-and-feel.
        }
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() 
			{	
			    new WrappingFrame("Sudoken", new SudokenGUIImp().getPanel()).getFrame().setVisible(true);
			}
		});
	}
}
