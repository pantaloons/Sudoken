package sudoken.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sudoken.domain.Board;
import sudoken.domain.Position;

class CellGraphics extends JComponent {
	private static final long serialVersionUID = 3126607752896973719L;
	
	public static final int BORDER_TOP = 0;
    public static final int BORDER_RIGHT = 1;
    public static final int BORDER_BOTTOM = 2;
    public static final int BORDER_LEFT = 3;
    
    private JLabel txt;
    
    public CellGraphics(String label) {
    	super();
    	txt = new JLabel(label);
    	add(txt);
    }
    
	public void setBorderWidth(int border, int width) {
		
	}
	
	public void setColor(Color c) {
		
	}
	
	public void setText(String s) {
		txt.setText(s);
	}
	
	@Override
	public void paint(Graphics g) {
		System.out.println("Painting!\n");
		// TODO Auto-generated method stub
		super.paint(g);
	}
}

class GapGraphics extends JComponent {
	private static final long serialVersionUID = 2654728970861543683L;
	
}

public class BoardGraphics extends JPanel {
	private static final long serialVersionUID = -2312057083961933054L;
	
	private CellGraphics[][] cg;
	private GapGraphics[][] gg;
	
	private Board b;
	
	public BoardGraphics(Board b) {
		super(new GridBagLayout());
		add(new JLabel("Where's the board!"));
		
		this.b = b;
		
		int width = b.getWidth();
		int height = b.getHeight();
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.ipadx = 20;
		gbc.ipady = 20;
		gbc.fill = GridBagConstraints.BOTH;
		cg = new CellGraphics[width][height];
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				gbc.gridx = i * 2;
				gbc.gridy = j * 2;
				cg[i][j] = new CellGraphics(b.getValue(new Position(i, j)) + "");
				add(cg[i][j], gbc);
			}
		}
		
		gbc.weightx = 0;
		gbc.weighty = 0;
		gg = new GapGraphics[width - 1][height - 1];
		for(int i = 0; i < width - 1; i++) {
			for(int j = 0; j < height - 1; j++) {
				gbc.gridx = i * 2 + 1;
				gbc.gridy = j * 2 + 1;

				gg[i][j] = new GapGraphics();
				add(gg[i][j], gbc);
			}
		}
	}
	
	public CellGraphics getCell(Position p) {
		return cg[p.getX()][p.getY()];
	}
	
	public GapGraphics getGap(Position p, int border) {
		return gg[p.getX()][p.getY()];
	}
	
	public void setGapHeight(int row, int gap) {
		
	}
	
	public void setGapWidth(int col, int gap) {
		
	}
	
	@Override
	public void paint(Graphics g) {
		for(int i = 0; i < b.getWidth(); i++) {
			for(int j = 0; j < b.getHeight(); j++) {
				cg[i][j].setText("" + b.getValue(new Position(i, j)));
			}
		}
		super.paint(g);
	}
}
