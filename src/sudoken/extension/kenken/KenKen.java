package sudoken.extension.kenken;

import java.util.HashSet;
import java.util.Set;

import sudoken.domain.BoardDecorator;
import sudoken.extension.Extension;

/**
 * KenKen is an Extension to add KenKen puzzle behaviour. KenKen 
 * puzzles are Sudoku puzzles with a Latin Square constraint,
 * but with cells divided into cages. Each cage has an operator
 * and a target, when combined using the operator the  contents
 * of a cage must match its target. 
 *
 */
public class KenKen extends Extension {

	/**
	 * Create a KenKen extension
	 */
    public KenKen() {
        super(new KenKenParser(), null, null);
    }
    
    @Override
    public Set<String> getPrerequisites() {
    	return new HashSet<String>();
    }
}
