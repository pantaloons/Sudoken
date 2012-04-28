package sudoken.extension.sudoku;

import sudoken.domain.BoardDecorator;
import sudoken.extension.ExtensionManager;
import sudoken.gui.BoardGraphics;

public class SudokuDecorator extends BoardDecorator {
	@Override
	public void decorate(BoardGraphics bg) {
		ExtensionManager.getDecorator("latinsquare").decorate(bg);
	}
}
