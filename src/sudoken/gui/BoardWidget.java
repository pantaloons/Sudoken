package sudoken.gui;

import java.awt.*;
import javax.swing.*;

import sudoken.domain.*;

/* Widget for rendering board states */
public class BoardWidget extends JPanel
{
	/* the board that the widget currently shows */
	private Board board;
	
	/* array of labels */
	private JLabel[][] boardLabels;
	
	public BoardWidget(Board board)
	{
		super(new GridBagLayout());
		this.board = board;
		
		setupUI();
	}
	
	/* setup widgets representing the board */
	public void setupUI()
	{
		GridBagConstraints c = new GridBagConstraints();
		
		if (board == null) {
			/* display dummy placeholder label */
			add(new JLabel("<No puzzle to display>"));
			
			/* with a big border around the rest of the panel */
			setBorder(BorderFactory.createLineBorder(Color.GRAY));
		}
		else {
			/* create an array of labels */
			int width = board.getWidth();
			int height = board.getHeight();
			
			// to get larger cells...
			c.ipadx = 20;
			c.ipady = 10;
			
			boardLabels = new JLabel[width][height]; 
			for (int row = 0; row < height; row++) {
				c.gridy = row;
				
				for (int col = 0; col < width; col++) {
					c.gridx = col;
					
					int value = board.getValue(col, row);
					String txt = (value == Board.UNSET)? " " : Integer.toString(value);
					
					boardLabels[col][row] = new JLabel(txt);
					add(boardLabels[col][row], c);
				}
			}
			
			// TODO: facilitate overdrawing the area where these lie
		}
	}
}
