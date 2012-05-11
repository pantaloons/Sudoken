package sudoken.extension.sudoku;

import java.util.HashSet;
import java.util.Set;

import sudoken.extension.Extension;

/**
 * Sudoku is an Extension to add behaviour for Sudoku Puzzles
 *
 */
public class Sudoku extends Extension {
	/**
	 * Create a Sudoku Extension
	 */
    public Sudoku() {
        super(null, new SudokuCreator(), new SudokuDecorator());
    }
    
    @Override
    public Set<String> getPrerequisites() {
    	Set<String> s = new HashSet<String>();
    	s.add("latinsquare");
    	return s;
    }
}
