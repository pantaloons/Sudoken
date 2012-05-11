package sudoken.extension.diagonals;

import java.util.HashSet;
import java.util.Set;

import sudoken.extension.Extension;

/**
 * Extension to provide Diagonals Sudoku puzzle functionality. A Diagonals puzzle adds the constraint
 * that cells on the two diagonals of the Board must be Unique.
 *
 */
public class Diagonals extends Extension {

	/**
	 * Create a Diagonals Extension
	 */
    public Diagonals() {
        super(new DiagonalsParser(), null, new DiagonalsDecorator());
    }
    
    @Override
    public Set<String> getPrerequisites() {
    	return new HashSet<String>();
    }
}