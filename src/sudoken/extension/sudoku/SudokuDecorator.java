package sudoken.extension.sudoku;

import sudoken.domain.BoardDecorator;
import sudoken.extension.ExtensionManager;
import sudoken.gui.BoardGraphics;

/**
 * SudokuDecorator decorates a BoardGraphics with the constraints of a Sudoku
 *
 */
public class SudokuDecorator extends BoardDecorator {
	@Override
	public void decorate(BoardGraphics bg) {
		ExtensionManager.getDecorator("latinsquare").decorate(bg);
		super.decorate(bg);
	}
}