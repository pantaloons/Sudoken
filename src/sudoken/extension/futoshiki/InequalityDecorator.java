package sudoken.extension.futoshiki;

import sudoken.domain.ConstraintDecorator;
import sudoken.domain.Position;
import sudoken.gui.BoardGraphics;
import sudoken.gui.GapGraphics;

public class InequalityDecorator extends ConstraintDecorator {
	
	private InequalityConstraint constraint;

	public InequalityDecorator(InequalityConstraint c) {
		this.constraint = c;
	}
	
	@Override
	public void decorate(BoardGraphics bg) {
		//TODO: Actually draw a nice graphics arrow here instead of text.
		Position p = BoardGraphics.getPositionBetween(constraint.getPosition(0), constraint.getPosition(1));
		GapGraphics gap = bg.getGap(p, 0);
		gap.setText(inequalityRepresentation());
	}
	
	/**
	 * Get the representation of an inequality,taking into account its direction and the
	 * relative positions of the affected cells.
	 * @return A position dependent textual representation of the InequalityConstraint
	 */
	private String inequalityRepresentation() {
		String ret = "";
		Position p1 = constraint.getPosition(0), p2 = constraint.getPosition(1);
		if (p1.getY() == p2.getY()) {
			if (constraint.isLess() == p1.getX() < p2.getX()) ret = "<";
			else ret = ">";
		}
		else if (p1.getX() == p2.getX()) {
			if (constraint.isLess() == p1.getY() < p2.getY()) ret = "^";
			else ret = "v";
		}
		return ret;
	}
}
