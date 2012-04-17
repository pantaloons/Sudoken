package sudoken.gui;

import javax.swing.*;

import sudoken.extension.ExtensionManager;

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
		SwingUtilities.invokeLater(new Runnable() {
			public void run() 
			{	
				new SudokenGUI();
			}
		});
	}
}
