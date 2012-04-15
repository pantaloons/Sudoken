package sudoken.domain;

import java.util.Collection;
import java.util.Set;

/**
 * A game board.
 * 
 * @author Kevin Doran
 *
 */
public class Board {
	public static final String UNSET = "-";
	
    private int height;
    private int width;
    private String[][] values;
    private Set<String> candidates;
    private Collection<Constraint> constraints;

    public Board(int width, int height, String[][] values, Set<String> candidates, Collection<Constraint> c) {
        this.height = height;
        this.width = width;
        this.values = values;
        this.candidates = candidates;
        this.constraints = c;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getValue(int x, int y) {
        return values[x][y];
    }

    public void setValue(int x, int y, String value) {
        values[x][y] = value;
    }
    
    public Set<String> getCandidates() {
    	return candidates;
    }
    
    public Collection<Constraint> getConstraints() {
    	return constraints;
    }
    
    //TODO: DEBUG, REMOVE
    public void print() {
    	for(int i = 0; i < height; i++) {
    		for(int j = 0; j < width; j++) {
    			System.out.print(values[j][i] + " ");
    		}
    		System.out.println();
    	}
    	System.out.println();
    }
}
