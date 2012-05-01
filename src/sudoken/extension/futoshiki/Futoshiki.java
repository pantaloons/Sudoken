package sudoken.extension.futoshiki;

import java.util.HashSet;
import java.util.Set;

import sudoken.extension.Extension;

/**
 * Futoshiki provides behaviour for Futoshiki puzzles. Futoshiki puzzles are Sudoku puzzles with
 * added inequality constraints between cells
 */
public class Futoshiki extends Extension {

	/**
	 * Create a Futoshiki Extension
	 */
    public Futoshiki() {
        super(new FutoshikiParser(), new FutoshikiCreator(), new FutoshikiDecorator());
    }
    
    @Override
    public Set<String> getPrerequisites() {
    	Set<String> s = new HashSet<String>();
    	s.add("latinsquare");
    	return s;
    }
}
