package sudoken.gui;


import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

import sudoken.domain.Board;
import sudoken.domain.Position;


class GapGraphics extends JPanel {
	private static final long serialVersionUID = 2654728970861543683L;
	
}

public class BoardGraphics extends JPanel {
	private static final long serialVersionUID = -2312057083961933054L;
	
	private CellGraphics[][] cg;
	private GapGraphics[][] gg;
	
	private Board b;
	
	public BoardGraphics(Board b) {
		super(new GridBagLayout());
		
		this.b = b;
		
		int width = b.getWidth();
		int height = b.getHeight();
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		cg = new CellGraphics[width][height];
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				gbc.gridx = i;
				gbc.gridy = j;
				int val = b.getValue(new Position(i, j));
				if(val == -1) cg[i][j] = new CellGraphics("");
				else cg[i][j] = new CellGraphics(val + "");
				add(cg[i][j], gbc);
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
	
	public GapGraphics getGap(Position p, int border) {
		return null;
	}
	
	public void setBorderWidths(int width) {
        for(int i = 0; i < b.getWidth(); i++) {
            for(int j = 0; j < b.getHeight(); j++) {
                cg[i][j].setBorderWidth(width, width, width, width);
            }
        }
	}
	
	public void setGapHeight(int row, int gap) {
		
	}
	
	public void setGapWidth(int col, int gap) {
		
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
