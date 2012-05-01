package sudoken.gui;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import sudoken.domain.Board;
import sudoken.domain.Position;

/**
 * BoardGraphics is used to contain the graphical components of a board and toact as a Decoration target
 * @author Tim Hobbs
 *
 */
public class BoardGraphics extends JPanel {
	/** Serialisation UID */
	private static final long serialVersionUID = -2312057083961933054L;
	
	/** Cell Graphics */
	private CellGraphics[][] cg;
	/**Gap Graphics (inbetween cells) */
	private GapGraphics[][] gg;
	
	/** Board to render */
	private Board b;
	
	/**
	 * Create a BoardGraphics
	 * @param b Board to base this BoardGraphics off
	 */
	public BoardGraphics(Board b) {
		super(new GridBagLayout());
		
		this.b = b;
		
		int width = b.getWidth();
		int height = b.getHeight();
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		cg = new CellGraphics[width][height];
		gg = new GapGraphics[2*width-1][2*height-1];
		
		for(int i = 0; i < width * 2 - 1; i++) {
			for(int j = 0; j < height * 2 - 1; j++) {
				gbc.gridx = i;
				gbc.gridy = j;
				
				if (i % 2 == 0 && j % 2 == 0){ //If a cell
					int val = b.getValue(new Position(i/2, j/2));
					if(val == -1) cg[i/2][j/2] = new CellGraphics("");
					else cg[i/2][j/2] = new CellGraphics(val + "");
					add(cg[i/2][j/2], gbc);
				}
				else { //If a gap
					gg[i][j] = new GapGraphics("");
					add(gg[i][j], gbc);
				}
			}
		}
	}
	
	/**
	 * Get a CellGraphics at a Position
	 * @param p Position of the CellGraphics
	 * @return CellGraphics at Position
	 */
	public CellGraphics getCell(Position p) {
		return cg[p.getX()][p.getY()];
	}
	
	/**
	 * Get a CellGraphics at a Position
	 * @param x x-coordinate of the Position
	 * @param y y-coordinate of the Position
	 * @return CellGraphics at Position
	 */
	public CellGraphics getCell(int x, int y) {
		return cg[x][y];
	}
	
	/**
	 * Get a GapGraphics at a Position
	 * @param p Position of the GapGraphics
	 * @return GapGraphics at Position
	 */
	public GapGraphics getGap(Position p, int border) {
		return gg[p.getX()][p.getY()];
	}
	
	/**
	 * Calculate the position of a gap between cell points. Note that gap
	 * coordinates differ from cell coordinates.
	 * @param p1 Position of first cell
	 * @param p2 Position of second cell
	 * @return Position of Gap between cells
	 */
	public static Position getPositionBetween(Position p1, Position p2){
		Position ret = null;
		if (p1.getX() == p2.getX() && Math.abs(p1.getY() - p2.getY()) == 1) {
			ret = new Position(p1.getX()*2,2*Math.min(p1.getY(), p2.getY())+1);
		}
		else if (p1.getY() == p2.getY() && Math.abs(p1.getX() - p2.getX()) == 1){
			ret = new Position(2*Math.min(p1.getX(), p2.getX())+1,p1.getY()*2);
		}
		return ret;
	}
	
	/**
	 * Set the border width of all cells in this BoardGraphics
	 * @param width new border width
	 */
	public void setBorderWidths(int width) {
        for(int i = 0; i < b.getWidth(); i++) {
            for(int j = 0; j < b.getHeight(); j++) {
                cg[i][j].setBorderWidths(width);
            }
        }
	}
	
	/**
	 * Set the height of gaps in a row
	 * @param row Row to set gaps for
	 * @param gap new gap height
	 */
	public void setGapHeight(int row, int gap) {
		if (row >= 0 && row < b.getHeight()) {
			for (int i = 0; i < b.getWidth()*2 - 1; i++) {
				GapGraphics gapg = gg[i][row*2+1];
				if (gapg != null) {
					Dimension size = gapg.getPreferredSize();
					size.height = gap;
					gapg.setPreferredSize(size);
				}
			}
		}
	}
	
	/**
	 * Set the width of gaps in a column
	 * @param col Column to set gaps for
	 * @param gap new gap width
	 */
	public void setGapWidth(int col, int gap) {
		if (col >= 0 && col < b.getWidth()) {
			for (int i = 0; i < b.getHeight()*2 - 1; i++) {
				GapGraphics gapg = gg[col*2+1][i];
				if (gapg != null) {
					Dimension size = gapg.getPreferredSize();
					size.width = gap;
					gapg.setPreferredSize(size);
				}
			}
		}		
	}
	
	/**
	 * Get the Board used by this BoardGraphics
	 * @return
	 */
	public Board getBoard() {
	    return b;
	}
	
	@Override
	public void paint(Graphics g) {
		for(int i = 0; i < b.getWidth(); i++) {
			for(int j = 0; j < b.getHeight(); j++) {
                int val = b.getValue(new Position(i, j));
                if(val == -1) cg[i][j].setText("");
                else cg[i][j].setText(val + "");
			}
		}
		super.paint(g);
	}
}
