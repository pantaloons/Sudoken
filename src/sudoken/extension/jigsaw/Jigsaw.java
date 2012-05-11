package sudoken.extension.jigsaw;

import java.util.HashSet;
import java.util.Set;

import sudoken.extension.Extension;

/**
 * Jigsaw is an Extension to provide Jigsaw Sudokus. A Jigsaw Sudoku has the row/column unique constraints
 * of a Latin Square, but has "jigsaw" shapes of 9 cells which must be unique within that shape.
 *
 */
public class Jigsaw extends Extension {

	/**
	 * Create a Jigsaw Extension
	 */
    public Jigsaw() {
        super(new JigsawParser(), new JigsawCreator(), new JigsawDecorator());
    }
    
    @Override
    public Set<String> getPrerequisites() {
    	Set<String> s = new HashSet<String>();
    	s.add("latinsquare");
    	return s;
    }
}
