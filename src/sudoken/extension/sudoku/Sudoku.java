package sudoken.extension.sudoku;

import sudoken.extension.Extension;
import sudoken.extension.ExtensionManager;

public class Sudoku extends Extension {
    static {
        ExtensionManager.register(new Sudoku());
    }

    public Sudoku() {
        super(null, new SudokuCreator());
    }
}
