package sudoken.extension.sudoku;

import java.util.HashSet;
import java.util.Set;

import sudoken.extension.Extension;
import sudoken.extension.ExtensionManager;

public class Sudoku extends Extension {
    static {
        ExtensionManager.register(new Sudoku());
    }

    public Sudoku() {
        super(null, new SudokuCreator(), null);
    }
    
    @Override
    public Set<String> getPrerequisites() {
    	Set<String> s = new HashSet<String>();
    	s.add("latinsquare");
    	return s;
    }
}
