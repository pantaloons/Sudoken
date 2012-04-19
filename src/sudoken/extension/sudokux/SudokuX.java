package sudoken.extension.sudokux;

import sudoken.extension.Extension;
import sudoken.extension.ExtensionManager;

public class SudokuX extends Extension {
    static {
        ExtensionManager.register(new SudokuX());
    }

    public SudokuX() {
        super(null, new SudokuXCreator());
    }
    
    @Override
    public boolean hasPrerequisites() {
    	// depends on standard sudoku
    	return ExtensionManager.hasExtension("sudoku");
    }
}
