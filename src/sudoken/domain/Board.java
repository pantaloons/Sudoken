package sudoken.domain;

import java.util.Collection;

/**
 * A game board.
 * 
 * @author Kevin Doran
 * 
 */
public class Board {
    public static final int UNSET = -1;

    private int height;
    private int width;
    private int[][] values;
    private int numCandidates;
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

    public void setValue(int x, int y, int value) {
        values[x][y] = value;
    }

    public int getNumCandidates() {
        return numCandidates;
    }

    public Collection<Constraint> getConstraints() {
        return constraints;
    }
}
