package org.sudoken.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.UIManager.*;

/**
 * Main user interface for Sudoku puzzles solvers
 */
public class SudokenGUI extends JFrame
{	
	/* Instance Variables ========================== */
	
	/* panel for housing the puzzle display */
	private PuzzleBoardPanel puzzle_pane;
	
	/* puzzle-type selector */
	private JComboBox type_selector;
	
	/* status label */
	private JLabel status_label;
	
	/* progressbar */
	private JProgressBar progressbar;
	
	/* Ctor ======================================== */
	
	/* ctor */
	public SudokenGUI()
	{
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setIconImage(new ImageIcon(ClassLoader.getSystemResource("icon.png")).getImage());
		
		/* setup UI */
		setup_theming();
		setup_ui();
		
		/* showtime! */
		setVisible(true);
	}
	
	/* Setup UI ==================================== */
	
	/* setup the theme style (i.e. "look and feel" in Java terms) */
	private void setup_theming()
	{
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		}
		catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look
			// and feel.
		}
	}
	
	/* User interface layout */
	private void setup_ui()
	{
		Container contentPane = getContentPane();
		
		/* window stuff */
		setTitle("Sudoken");
		setSize(new Dimension(640, 520));
		setResizable(false);
		setLocationRelativeTo(null); // centered
		
		/* top panel - controls */
		JPanel ctrlPanel = setup_ctrlPanel();
		contentPane.add(ctrlPanel, BorderLayout.NORTH);
		
		/* center panel - puzzle display area */
		puzzle_pane = new PuzzleBoardPanel();		
		contentPane.add(puzzle_pane, BorderLayout.CENTER);
		
		/* bottom panel - status */
		JPanel statusPanel = setup_statusPanel();
		contentPane.add(statusPanel, BorderLayout.SOUTH);
	}
	
	/* --------------------------------- */
	
	/* Control panel */
	private JPanel setup_ctrlPanel()
	{		
		/* panel settings */
		JPanel pnl = new JPanel();
		pnl.setBorder(BorderFactory.createEmptyBorder(2, 2, 4, 2));
		pnl.setLayout(new BoxLayout(pnl, BoxLayout.X_AXIS));
		pnl.setBackground(new Color(0.3f, 0.3f, 0.3f));
		pnl.setOpaque(true);
		
		/* puzzle-type selector */
		type_selector = new JComboBox();
		pnl.add(type_selector);
		
		/* refresh puzzle-type selector */
		// NOTE: this is really just a placebo
		JButton refreshBut = new JButton("Refresh");
		refreshBut.setToolTipText("Refresh the list of available puzzle types");
		pnl.add(refreshBut);
		
		pnl.add(Box.createHorizontalGlue()); // padding
		
		/* load puzzle */
		JButton loadBut = new JButton("Load Puzzle");
		pnl.add(loadBut);
		
		pnl.add(Box.createHorizontalGlue()); // padding
		pnl.add(Box.createHorizontalGlue()); // padding
		
		/* solve */
		JButton solveBut = new JButton("Solve...");
		pnl.add(solveBut);
		
		//pnl.add(Box.createHorizontalGlue()); // padding
		
		return pnl;
	}
	
	/* --------------------------------- */
	
	/* Status panel */
	private JPanel setup_statusPanel()
	{
		/* panel settings */
		JPanel pnl = new JPanel();
		pnl.setBorder(BorderFactory.createEmptyBorder(4, 2, 2, 2));
		pnl.setLayout(new BoxLayout(pnl, BoxLayout.X_AXIS));
		
		/* progressbar - 75% */
		progressbar = new JProgressBar();
		pnl.add(progressbar);
		
		/* labels - 20% */
		status_label = new JLabel("Load puzzle to begin...");
		status_label.setBorder(BorderFactory.createEmptyBorder(2, 7, 2, 3));
		pnl.add(status_label);
		
		return pnl;
	}
}
