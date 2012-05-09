package sudoken.extension.latinsquare;

import java.util.HashSet;
import java.util.Set;

import sudoken.extension.Extension;

/**
 * LatinSquare provides behaviour for Latin Square, a basis of many Sudoku puzzles.
 * The values in each row and column of a Latin Square must be unique.
 *
 */
public class LatinSquare extends Extension {
	/**
	 * Create a LatinSquare Extension
	 */
    public LatinSquare() {
        super(null, new LatinSquareCreator(), new LatinSquareDecorator());
    }
    
    @Override
    public Set<String> getPrerequisites() {
    	return new HashSet<String>();
    }
}
