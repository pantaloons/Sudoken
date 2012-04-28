package sudoken.gui;

import java.awt.*;
import javax.swing.*;

import net.miginfocom.swing.MigLayout;

import sudoken.domain.*;

/* Widget for rendering board states */
public class BoardWidget extends JPanel {
	private static final long serialVersionUID = -2031869036220290839L;
	
	/* the board that the widget currently shows */
	private Board board;
	
	/* array of labels */
	private JLabel[][] boardLabels;
	
	public BoardWidget(Board board) {
		super(new GridBagLayout());
		this.board = board;
		
		setupUI();
	}
	
	public BoardWidget() {
		super();
		setNoBoard();
	}
	
	public void setBoard(Board b) {
		this.board = b;
		setupUI();
	}
	
	private void setNoBoard() {
		removeAll();
		this.board = null;
		/* display dummy placeholder label */
		add(new JLabel("No puzzle to display."));
		
		/* with a big border around the rest of the panel */
		//setBorder(BorderFactory.createLineBorder(Color.GRAY));
		revalidate();
		repaint();
	}
	
	public void updateUI(final Board b) {
		// The width and height checks are temporary hacks to make sure the GUI board is properly updated
		// when a new puzzle (of different dimensions) has been loaded.
		if(board == null || board.getWidth() != b.getWidth() || board.getHeight() != b.getHeight())
			setBoard(b);
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				//System.out.println("Updating!");
				int width = board.getWidth();
				int height = board.getHeight();
				for (int row = 0; row < height; row++) {
					for (int col = 0; col < width; col++) {
						if (b.getValue(col, row) == -1)
							boardLabels[col][row].setText("");
						else
							boardLabels[col][row].setText(b.getValue(col, row) + "");
					}
				}
				revalidate();
			}
		});
	}
	
	/* setup widgets representing the board */
	private void setupUI() {
		System.out.println("setting up UI");
		removeAll();
		
		/* create an array of labels */
		int width = board.getWidth();
		int height = board.getHeight();
		setLayout(new MigLayout("wrap" + width));
		
		boardLabels = new JLabel[width][height];
		for (int row = 0; row < height; row++) {
			
			for (int col = 0; col < width; col++) {
				int value = board.getValue(col, row);
				String txt = (value == Board.UNSET) ? " " : Integer
						.toString(value);
				
				boardLabels[col][row] = new JLabel(txt);
				add(boardLabels[col][row], "width 20, height 20");
			}
		}
		
		// TODO: facilitate overdrawing the area where these lie
		repaint();
		revalidate();
	}
}
