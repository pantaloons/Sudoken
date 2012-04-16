package sudoken.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.UIManager.*;

import sudoken.domain.*;

/**
 * Main user interface for Sudoku puzzles solvers
 * 
 * @author Joshua Leung
 */
public class SudokenGUI extends JFrame
{	
	/* Instance Variables ========================== */
	
	/* panel for housing the board display */
	private BoardWidget board_pane;
	
	/* status label */
	private JLabel status_label;
	
	/* progressbar */
	private JProgressBar progressbar;
	
	/* --------------------------------------------- */
	// App state - move to a container class?
	
	/* current board/puzzle */
	private Board board;
	
	/* Ctor ======================================== */
	
	/* ctor */
	// FIXME: this should eventually contain the body of the other again
	public SudokenGUI()
	{
		this(null);
	}
	
	/* ctor */
	public SudokenGUI(Board board)
	{
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setIconImage(new ImageIcon(ClassLoader.getSystemResource("icon.png")).getImage());
		
		// XXX: remove this line
		this.board = board;
		
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
		// NOTE: this needs to be replaced everytime the puzzle changes
		board_pane = new BoardWidget(board);		
		contentPane.add(board_pane, BorderLayout.CENTER);
		
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
		
		/* load puzzle */
		JButton loadBut = new JButton("Load Puzzle");
		pnl.add(loadBut);
		
		pnl.add(Box.createHorizontalGlue()); // padding
		
		/* solve */
		JButton solveBut = new JButton("Solve!");
		pnl.add(solveBut);
		
		pnl.add(Box.createHorizontalGlue()); // padding
		
		/* refresh puzzle-type selector */
		// NOTE: this is really just a placebo
		JButton refreshBut = new JButton("Refresh");
		refreshBut.setToolTipText("Refresh the list of available puzzle types");
		pnl.add(refreshBut);
		
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
