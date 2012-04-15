package org.sudoken.ui;

import javax.swing.*;

import org.sudoken.puzzle.*;

/**
 * Panel for drawing puzzle boards
 */
public class PuzzleBoardPanel extends JPanel
{
	/* puzzle currently being shown */
	Puzzle puzzle;
	
	/* board drawing extension */
	// xxx
	
	/* Setup ================================================ */
	
	/* ctor */
	public PuzzleBoardPanel()
	{
		super();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
	}
	
	/* Change the puzzle being used */
	public void setPuzzle(Puzzle puzzle)
	{
		/* change puzzle */
		this.puzzle = puzzle;
		
		/* queue refresh */
		updateWidgets();
	}
	
	/* Puzzle type changed */
	public void changePuzzleType()
	{
		// clear all widgets
		// initBoardWidgets();
	}
	
	/* Extension Points ==================================== */
	
	/**
	 * Setup widgets for drawing board
	 */
	public void setup_ui()
	{
		//extension.init_board_widgets()
	}
	
	/**
	 * Update board widgets - in response to some update or whatever
	 */
	public void updateWidgets()
	{
		
	}
}
