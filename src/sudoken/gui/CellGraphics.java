package sudoken.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

public class CellGraphics extends JPanel {
	public static final int NORTH = 0;
	public static final int WEST = 1;
	public static final int SOUTH = 2;
	public static final int EAST = 3;
	
    private static final long serialVersionUID = 3126607752896973719L;
    private int[] borders;
    
    private JLabel txt;
    
    public CellGraphics(String label) {
        super(new GridBagLayout());
        
        borders = new int[]{0, 0, 0, 0};
        
        setBackground(Color.WHITE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        setPreferredSize(new Dimension(35, 35));
        
        txt = new JLabel(label);
        add(txt, gbc);
        
        revalidate();
    }
    
    private void updateBorders() {
    	setBorder(BorderFactory.createMatteBorder(borders[0], borders[1], borders[2], borders[3], Color.BLACK));
    }
    
    public void setBorderWidths(int size) {
    	for(int i = 0; i < 4; i++) borders[i] = size;
    	updateBorders();
    }
    
    public void setBorderWidth(int border, int size) {
    	borders[border] = size;
    	updateBorders();
    }
    
    public void setColor(Color c) {
        setBackground(c);
    }
    
    public void setText(String s) {
        txt.setText(s);
    }
}