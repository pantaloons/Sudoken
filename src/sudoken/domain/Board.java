package sudoken.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

	public List<BoardDecorator> getDecorators() {
		// TODO Auto-generated method stub
		return new ArrayList<BoardDecorator>();
	}
}
