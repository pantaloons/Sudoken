package sudoken.extension.latinsquare;

import sudoken.domain.BoardDecorator;
import sudoken.gui.BoardGraphics;

public class LatinSquareDecorator extends BoardDecorator {

	@Override
	public void decorate(BoardGraphics bg) {
		bg.setBorderWidths(1);
		int w = bg.getBoard().getWidth(), h = bg.getBoard().getHeight();
		for(int i = 1; i < w - 1; i++) {
		    bg.getCell(i, 0).setBorderWidth(2, 1, 1, 1);
		    bg.getCell(i, h - 1).setBorderWidth(1, 1, 2, 1);
		}
        for(int i = 1; i < h - 1; i++) {
            bg.getCell(0, i).setBorderWidth(1, 1, 1, 2);
            bg.getCell(w - 1, i).setBorderWidth(1, 2, 1, 1);
        }
        bg.getCell(0, 0).setBorderWidth(2, 1, 1, 2);
        bg.getCell(w - 1, 0).setBorderWidth(2, 2, 1, 1);
        bg.getCell(w - 1, h - 1).setBorderWidth(1, 2, 2, 1);
        bg.getCell(0, h - 1).setBorderWidth(1, 1, 2, 2);
	}

}
