package sudoken.extension.sudoku;

import java.util.HashSet;
import java.util.Set;

import sudoken.extension.Extension;

public class Sudoku extends Extension {
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
