package sudoken.extension.sudoku;

import sudoken.extension.Extension;
import sudoken.extension.ExtensionManager;

public class Sudoku extends Extension {
    public Sudoku() {
        super(null, new SudokuCreator());
    }
    
    @Override
    public boolean hasPrerequisites() {
    	// Depends on latin square
    	return ExtensionManager.hasExtension("latinsquare");
    }
}
