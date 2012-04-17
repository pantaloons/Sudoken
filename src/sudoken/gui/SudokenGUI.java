package sudoken.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.UIManager.*;
import javax.swing.filechooser.FileFilter;

import sudoken.domain.*;
import sudoken.persistence.Parser;
import sudoken.solver.BacktrackingSolver;
import sudoken.solver.Solver;

/**
 * Main user interface for Sudoku puzzles solvers
 * 
 * @author Joshua Leung
 */
public class SudokenGUI extends JFrame
{	
	/* Instance Variables ========================== */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4755160282006524528L;

	private Solver s;

	private BoardWidget board_pane;
	
	/* status label */
	private JLabel status_label;
	
	/* progressbar */
	private JProgressBar progressbar;
	
	/* Ctor ======================================== */
	
	/* ctor */
	// FIXME: this should eventually contain the body of the other again
	public SudokenGUI()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/* setup UI */
		//setIconImage(new ImageIcon(ClassLoader.getSystemResource("icon.png")).getImage());
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
		
		board_pane = new BoardWidget(null);
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
		loadBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser(".");
				fc.setFileFilter(new FileFilter() {
					public String getDescription() {
						return "Sudoken (.sudoken)";
					}
					public boolean accept(File f) {
						if(f.isDirectory()) return true;
						int i = f.getName().lastIndexOf('.');
						if(i < 0) return false;
						else return f.getName().substring(i + 1).equals("sudoken");
					}
				});
				if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					try {
						Board b = Parser.load(f);
						s = new BacktrackingSolver();
						s.setSudokuBoard(b);
						board_pane.setBoard(b);
					}
					catch(IOException x) {
						System.out.println("Error...");
					}
				}
			}
		});
		pnl.add(loadBut);
		
		pnl.add(Box.createHorizontalGlue()); // padding
		
		/* solve */
		JButton solveBut = new JButton("Solve!");
		solveBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s.solve();
				board_pane.setupUI();
			}
		});
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
