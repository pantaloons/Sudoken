package sudoken.gui;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import sudoken.domain.Board;
import sudoken.domain.Position;

public class BoardGraphics extends JPanel {
	private static final long serialVersionUID = -2312057083961933054L;
	
	private CellGraphics[][] cg;
	private GapGraphics[][] gg;
	
	private Board b;
	private int gapHeight[], gapWidth[];
	
	public BoardGraphics(Board b) {
		super(new GridBagLayout());
		
		this.b = b;
		
		int width = b.getWidth();
		int height = b.getHeight();
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		cg = new CellGraphics[width][height];
		gg = new GapGraphics[2*width-1][2*height-1];
		
		
		gapHeight = new int[height - 1];
		gapWidth = new int[width - 1];
		
		
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
		
		
		
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.ipadx = 0;
		gbc.ipady = 0;
		/*
		gg = new GapGraphics[width - 1][height - 1];
		for(int i = 0; i < width - 1; i++) {
			for(int j = 0; j < height - 1; j++) {
				gbc.gridx = i * 2 + 1;
				gbc.gridy = j * 2 + 1;

				gg[i][j] = new GapGraphics();
				add(gg[i][j], gbc);
			}
		}*/
	}
	
	public CellGraphics getCell(Position p) {
		return cg[p.getX()][p.getY()];
	}
	
	public CellGraphics getCell(int x, int y) {
		return cg[x][y];
	}
	
	public GapGraphics getGap(Position p, int border) {
		return gg[p.getX()][p.getY()];
	}
	
	/***
	 * Calculate the position of a gap between cell points. Note that gap
	 * coordinates differ from cell coordinates.
	 * @param p1
	 * @param p2
	 * @return
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
	
	public void setBorderWidths(int width) {
        for(int i = 0; i < b.getWidth(); i++) {
            for(int j = 0; j < b.getHeight(); j++) {
                cg[i][j].setBorderWidth(width, width, width, width);
            }
        }
	}
	
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
