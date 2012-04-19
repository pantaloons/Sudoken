package sudoken.gui;

import java.awt.*;
import javax.swing.*;

import net.miginfocom.swing.MigLayout;

import sudoken.domain.*;

/* Widget for rendering board states */
public class BoardWidget extends JPanel {

    /**
	 * 
	 */
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
        /* display dummy placeholder label */
        add(new JLabel("<No puzzle to display>"));

        /* with a big border around the rest of the panel */
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        revalidate();
        repaint();
    }

    /* setup widgets representing the board */
    public void setupUI() {
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

    // I'm really sorry for swapping the layouts, but for some reason, I
    // couldn't get
    // GridBagLayout and MigLayout to work together: the grid of numbers was
    // being displayed
    // on one line. I'll try get back to this, as I don't want impose MigLayout
    // on
    // people.

    // /* setup widgets representing the board */
    // public void setupUI()
    // {
    // System.out.println("setting up UI");
    //
    // GridBagConstraints c = new GridBagConstraints();
    //
    // removeAll();
    //
    // /* create an array of labels */
    // int width = board.getWidth();
    // int height = board.getHeight();
    //
    // // to get larger cells...
    // c.ipadx = 20;
    // c.ipady = 10;
    //
    // boardLabels = new JLabel[width][height];
    // for (int row = 0; row < height; row++) {
    // c.gridy = row;
    //
    // for (int col = 0; col < width; col++) {
    // c.gridx = col;
    //
    // int value = board.getValue(col, row);
    // String txt = (value == Board.UNSET)? " " : Integer.toString(value);
    //
    // boardLabels[col][row] = new JLabel(txt);
    // add(boardLabels[col][row], c);
    // }
    // }
    //
    // // TODO: facilitate overdrawing the area where these lie
    // repaint();
    // revalidate();
    // }
}
