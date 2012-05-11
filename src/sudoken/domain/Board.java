package sudoken.domain;

import java.util.Collection;

/**
 * A game board.
 * 
 * @author Kevin Doran
 * 
 */
public class Board {
	/* value used to denote empty cells (i.e. those that can get values) */
    public static final int UNSET = -1;
    
    /* dimensions of the board */
    private int height;
    private int width;
    
    /* current values of each cell in the board */
    private int[][] values;
    
    /* number of candidate values that can go in each cell */
    private int numCandidates;
    
    /* constraints on the board solution */
    private Collection<Constraint> constraints;
	
	/* renderer for board */
    private BoardDecorator bd;

    public Board(int width, int height, int[][] values, int numCandidates,
            Collection<Constraint> c, BoardDecorator bd) {
        this.height = height;
        this.width = width;
        this.values = values;
        this.numCandidates = numCandidates;
        this.constraints = c;
        this.bd = bd;
    }
    
    /**
     * Get the width of the Board
     * @return the width of the Board in cells 
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the height of the Board
     * @return the height of the Board in cells 
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * Get the value of a cell at a given position
     * @param x x coordinate of the cell
     * @param y y coordinate of the cell
     * @return value  of the cell at the given position
     */
    public int getValue(int x, int y) {
    	return values[x][y];
    }
    
    /**
     * Get the value of a cell at a given position
     * @param position Position of the cell
     * @return value  of the cell at the given position
     */
    public int getValue(Position position) {
        return values[position.getX()][position.getY()];
    }
    
    /**
     * Set the value of the cell at a given position
     * @param position Position of the cell
     * @param value New cell value
     */
    void setValue(Position position, int value) {
        values[position.getX()][position.getY()] = value;
    }

    /**
     * Get the number of candidate values that can go in each cell
     * @return
     */
    public int getNumCandidates() {
        return numCandidates;
    }

    /**
     * Get the constraints of the board
     * @return
     */
    public Collection<Constraint> getConstraints() {
        return constraints;
    }

    /**
     * Get the decorator of the board
     * @return
     */
	public BoardDecorator getDecorator() {
		return bd;
	}
}
