package sudoken.extension.futoshiki;

import java.awt.Color;

import javax.swing.BorderFactory;

import sudoken.domain.Board;
import sudoken.domain.BoardDecorator;
import sudoken.domain.Constraint;
import sudoken.domain.Position;
import sudoken.gui.BoardGraphics;
import sudoken.gui.GapGraphics;

/***
 * 
 * @author Tim Hobbs
 *
 */
public class FutoshikiDecorator extends BoardDecorator {

	@Override
	public void decorate(BoardGraphics bg) {
		// TODO Auto-generated method stub
		System.out.println("Hello");
		Board board = bg.getBoard();
		
		bg.setBorderWidths(2);
		
		//bg.setGapWidth(2, 10);
		//bg.setGapHeight(2, 20);
		
		for (int i = 0; i < board.getWidth() - 1; i++) bg.setGapWidth(i, 30);
		for (int i = 0; i < board.getHeight() - 1; i++) bg.setGapHeight(i, 30);
		
		//Set gaps to constraints
		for (Constraint constraint: board.getConstraints()) {
			if (constraint instanceof InequalityConstraint) {
				InequalityConstraint ic = (InequalityConstraint)constraint;
				Position p = BoardGraphics.getPositionBetween(ic.getPosition(0), ic.getPosition(1));
				System.out.println("INEQ: " + ic.getPosition(0) + (ic.isLess() ? " < " : " > ") + ic.getPosition(1));
				GapGraphics gap = bg.getGap(p, 0);
				gap.setText(inequalityRepresentation(ic));
			}
			
		}
		
		
		
		
	}
	
	private static String inequalityRepresentation(final InequalityConstraint ic) {
		String ret = "";
		Position p1 = ic.getPosition(0), p2 = ic.getPosition(1);
		if (p1.getY() == p2.getY()) {
			if (ic.isLess() == p1.getX() < p2.getX()) ret = "<";
			else ret = ">";
		}
		else if (p1.getX() == p2.getX()) {
			if (ic.isLess() == p1.getY() < p2.getY()) ret = "^";
			else ret = "v";
		}
		
		
		
		
		return ret;
	}

}
