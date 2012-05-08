package sudoken.extension.futoshiki;

import sudoken.domain.Board;
import sudoken.domain.BoardDecorator;
import sudoken.gui.BoardGraphics;

/***
 * FutoshikiDecorator decorates a Board with Futoshiki constraints
 * @author Tim Hobbs
 *
 */
public class FutoshikiDecorator extends BoardDecorator {

	@Override
	public void decorate(BoardGraphics bg) {
		Board board = bg.getBoard();
		
		bg.setBorderWidths(2);
		
		for (int i = 0; i < board.getWidth() - 1; i++) bg.setGapWidth(i, 30);
		for (int i = 0; i < board.getHeight() - 1; i++) bg.setGapHeight(i, 30);
		
		super.decorate(bg);
	}

}
