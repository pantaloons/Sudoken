package sudoken.extension.sudoku;

import sudoken.domain.BoardDecorator;
import sudoken.domain.Position;
import sudoken.extension.ExtensionManager;
import sudoken.gui.BoardGraphics;

public class SudokuDecorator extends BoardDecorator {
	@Override
	public void decorate(BoardGraphics bg) {
		ExtensionManager.getDecorator("latinsquare").decorate(bg);
		
		int x = (int)Math.sqrt(bg.getBoard().getWidth());
		for(int a = 0; a < x; a++) {
			for(int b = 0; b < x; b++) {
				for(int i = 1; i < x - 1; i++) {
				    bg.getCell(new Position(i + x * a, b * x)).setBorderWidth(2, 1, 1, 1);
				    bg.getCell(new Position(i + x * a, (b + 1) * x - 1)).setBorderWidth(1, 1, 2, 1);
		            bg.getCell(new Position(x * a, i + x * b)).setBorderWidth(1, 1, 1, 2);
		            bg.getCell(new Position((a + 1) * x - 1, i + x * b)).setBorderWidth(1, 2, 1, 1);
		        }
		        bg.getCell(new Position(x * a, x * b)).setBorderWidth(2, 1, 1, 2);
		        bg.getCell(new Position(x * (a + 1) - 1, x * b)).setBorderWidth(2, 2, 1, 1);
		        bg.getCell(new Position(x * (a + 1) - 1, x * (b + 1) - 1)).setBorderWidth(1, 2, 2, 1);
		        bg.getCell(new Position(x * a, x * (b + 1) - 1)).setBorderWidth(1, 1, 2, 2);
			}
		}
	}
}
