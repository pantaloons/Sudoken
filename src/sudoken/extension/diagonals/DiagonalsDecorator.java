package sudoken.extension.diagonals;

import java.awt.Color;

import sudoken.domain.BoardDecorator;
import sudoken.domain.Position;
import sudoken.gui.BoardGraphics;

/**
 * DiagonalsDecorator decorates a Board with Unique Diagonal constraints highlighted
 *
 */
class DiagonalsDecorator extends BoardDecorator {
	@Override
	public void decorate(BoardGraphics bg) {
		for(int i = 0; i < bg.getWidth(); i++) {
			bg.getCell(new Position(i, i)).setColor(Color.green);
			bg.getCell(new Position(bg.getWidth() - 1 - i, bg.getWidth() - 1 - i)).setColor(Color.green);
		}
	}
}
