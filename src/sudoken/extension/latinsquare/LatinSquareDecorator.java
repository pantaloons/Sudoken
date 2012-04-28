package sudoken.extension.latinsquare;

import sudoken.domain.BoardDecorator;
import sudoken.gui.BoardGraphics;
import sudoken.gui.CellGraphics;

public class LatinSquareDecorator extends BoardDecorator {

	@Override
	public void decorate(BoardGraphics bg) {
		bg.setBorderWidths(1);
		int w = bg.getBoard().getWidth(), h = bg.getBoard().getHeight();
		
		for(int i = 1; i < w - 1; i++) {
		    bg.getCell(i, 0).setBorderWidth(CellGraphics.NORTH, 2);
		    bg.getCell(i, h - 1).setBorderWidth(CellGraphics.SOUTH, 2);
		}
        for(int i = 1; i < h - 1; i++) {
            bg.getCell(0, i).setBorderWidth(CellGraphics.WEST, 2);
            bg.getCell(w - 1, i).setBorderWidth(CellGraphics.EAST, 2);
        }
        
        bg.getCell(0, 0).setBorderWidth(CellGraphics.WEST, 2);
        bg.getCell(0, 0).setBorderWidth(CellGraphics.NORTH, 2);
        
        bg.getCell(w - 1, 0).setBorderWidth(CellGraphics.NORTH, 2);
        bg.getCell(w - 1, 0).setBorderWidth(CellGraphics.EAST, 2);
        
        bg.getCell(w - 1, h - 1).setBorderWidth(CellGraphics.EAST, 2);
        bg.getCell(w - 1, h - 1).setBorderWidth(CellGraphics.SOUTH, 2);
        
        bg.getCell(0, h - 1).setBorderWidth(CellGraphics.SOUTH, 2);
        bg.getCell(0, h - 1).setBorderWidth(CellGraphics.WEST, 2);
	}

}
