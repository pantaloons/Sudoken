package sudoken.extension.jigsaw;

import sudoken.domain.BoardDecorator;
import sudoken.extension.ExtensionManager;
import sudoken.gui.BoardGraphics;

/**
 * Jigsaw Decorator decorates a Jigsaw Sudoku puzzle
 *
 */
class JigsawDecorator extends BoardDecorator {

	@Override
	public void decorate(BoardGraphics bg) {
		ExtensionManager.getDecorator("latinsquare").decorate(bg);
		super.decorate(bg);
	}
}
