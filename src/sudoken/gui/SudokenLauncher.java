package sudoken.gui;

import javax.swing.*;

import sudoken.domain.*;
import sudoken.persistence.*;
import sudoken.solver.*;

public class SudokenLauncher
{
	/**
	 * Main entrypoint of application
	 */
	public static void main(String[] args) throws Exception
	{
		/* Manually register extensions */
		// FIXME: this should all be automatic + dynamic
		Class.forName("sudoken.extension.sudoku.Sudoku");
		Class.forName("sudoken.extension.futoshiki.Futoshiki");
		
		// FIXME: we should be solving in realtime
		final Board board = Parser.load("test/test2.csv");
		Solver s = new BacktrackingSolver();
		s.setSudokuBoard(board);
		s.solve();
		board.print();
		
		/* launch gui */
		SwingUtilities.invokeLater(new Runnable() {
			public void run() 
			{	
				SudokenGUI gui = new SudokenGUI(board);
			}
		});
	}
}
