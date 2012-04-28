package sudoken.gui;

import javax.swing.*;
import sudoken.domain.*;

/**
 * A board widget that can be added onto a GUI. The widget
 * wraps a board which may be being solved.
 */
public class BoardWidget extends JPanel {
    private static final long serialVersionUID = -2031869036220290839L;

    public BoardWidget() {
        super();
        setNoBoard();
    }

    public void setBoard(Board b) {
        BoardGraphics bg = new BoardGraphics(b);
        b.getDecorator().decorate(bg);
        
        removeAll();
        add(bg);
        revalidate();
        repaint();
    }
    
    @Override
    public void repaint() {
    	super.repaint();
    }

    private void setNoBoard() {
        removeAll();
        
        add(new JLabel("No puzzle to display."));

        revalidate();
        repaint();
    }
}