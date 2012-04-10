package org.sudoken.ui;

public class SudokenLauncher
{
	/**
	 * Main entrypoint of application
	 */
	public static void main(String[] args)
	{
		/* register extensions manager */
		//ExtensionsManager manager = ExtensionsManager.getInstance();
		
		/* launch gui */
		new SudokenGUI();
	}
}
