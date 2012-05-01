package sudoken.gui;

import javax.swing.*;
import sudoken.domain.*;

/**
 * A board widget that can be added onto a GUI. The widget
 * wraps a board which may be being solved.
 */
public class BoardWidget extends JPanel {
    private static final long serialVersionUID = -2031869036220290839L;

    /**
     * Create a BoardWidget
     */
    public BoardWidget() {
        super();
        setNoBoard();
    }

    /**
     * Set the Board of the BoardWidget
     * @param b Board to use
     */
    public void setBoard(Board b) {
        BoardGraphics bg = new BoardGraphics(b);
        b.getDecorator().decorate(bg);
        for(Constraint c : b.getConstraints()) {
        	c.decorate(bg);
        }
        
        removeAll();
        add(bg);
        revalidate();
        repaint();
    }
    
    /**
     * Repaint the BoardWidget
     */
    @Override
    public void repaint() {
    	super.repaint();
    }

    /**
     * Set to use no Board
     */
    private void setNoBoard() {
        removeAll();
        
        add(new JLabel("No puzzle to display."));
        
        revalidate();
        repaint();
    }
}
