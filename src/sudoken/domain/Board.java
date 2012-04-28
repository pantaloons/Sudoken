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
    
    
    public Board(int width, int height, int[][] values, int numCandidates,
            Collection<Constraint> c) {
        this.height = height;
        this.width = width;
        this.values = values;
        this.numCandidates = numCandidates;
        this.constraints = c;
    }
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public int getValue(int x, int y) {
    	return values[x][y];
    }
    
    public int getValue(Position position) {
        return values[position.getX()][position.getY()];
    }
    
    public void setValue(Position position, int value) {
        values[position.getX()][position.getY()] = value;
    }

    public int getNumCandidates() {
        return numCandidates;
    }

    public Collection<Constraint> getConstraints() {
        return constraints;
    }
}
