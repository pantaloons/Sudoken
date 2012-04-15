package sudoken.gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

import sudoken.domain.Board;
import sudoken.persistence.Parser;
import sudoken.solver.BacktrackingSolver;
import sudoken.solver.Solver;

public class MainWindow implements Runnable {
	
	private void createGridArea(JPanel pane) {
		GridBagLayout gbl = new GridBagLayout();
		pane.setLayout(gbl);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		JLabel label = new JLabel("Hello, world! (This is the board area)");
		gbl.setConstraints(label, gbc);
		pane.add(label);
	}
	
	@Override
	public void run() {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = f.getContentPane();
		GridBagLayout gbl = new GridBagLayout();
		f.setLayout(gbl);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		JPanel board = new JPanel();
		gbl.setConstraints(board, gbc);
		c.add(board);
		
		createGridArea(board);
		
		JButton but = new JButton("Solve!");
		gbc.gridy = 1;
		gbc.weighty = 0;
		gbl.setConstraints(but, gbc);
		c.add(but);
		
		f.pack();
		f.setSize(600, 400);
        f.setVisible(true);
	}
	
	public static void main(String[] args) throws Exception {
		SwingUtilities.invokeLater(new MainWindow());
		
		Class.forName("sudoken.extension.sudoku.Sudoku");
		Class.forName("sudoken.extension.futoshiki.Futoshiki");
		Board b = Parser.load("test/test2.csv");
		Solver s = new BacktrackingSolver();
		s.setSudokuBoard(b);
		s.solve();
		b.print();
	}
}
