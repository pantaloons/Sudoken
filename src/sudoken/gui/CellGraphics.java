package sudoken.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

/**
 * CellGraphics is used to render a Cell of a Board
 *
 */
public class CellGraphics extends JPanel {
	//Directions
	public static final int NORTH = 0;
	public static final int WEST = 1;
	public static final int SOUTH = 2;
	public static final int EAST = 3;
	
	/** Serialisation UID */
    private static final long serialVersionUID = 3126607752896973719L;
    
    /** Border widths */
    private int[] borders;
    
    /** Label used to display text */
    private JLabel txt;
    
    /**
     * Create a CellGraphics
     * @param label Initial text contents of the Cell
     */
    public CellGraphics(String label) {
        super(new GridBagLayout());
        
        borders = new int[]{0, 0, 0, 0};
        
        setBackground(Color.WHITE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        setPreferredSize(new Dimension(35, 35));
        gbc.gridy = 5;
        
        txt = new JLabel(label);
        add(txt, gbc);
        
        revalidate();
    }
    
    /**
     * Update the borders of the CellGraphics
     */
    private void updateBorders() {
    	setBorder(BorderFactory.createMatteBorder(borders[0], borders[1], borders[2], borders[3], Color.BLACK));
    }
    
    /**
     * Set the border widths of the CellGraphics
     * @param size new width for every border
     */
    public void setBorderWidths(int size) {
    	for(int i = 0; i < 4; i++) borders[i] = size;
    	updateBorders();
    }
    
    /**
     * Set the width of a certain border
     * @param border Border to set width of
     * @param size new width for the border
     */
    public void setBorderWidth(int border, int size) {
    	borders[border] = size;
    	updateBorders();
    }
    
    /**
     * Set the color of the cell
     * @param c new Color of the cell
     */
    public void setColor(Color c) {
        setBackground(c);
    }
    
    /**
     * Set the text of the cell
     * @param s new text content of the cell     
     */
    public void setText(String s) {
        txt.setText(s);
    }
}