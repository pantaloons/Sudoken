package sudoken.extension.kenken;

import java.util.List;

import sudoken.domain.Board;
import sudoken.domain.Constraint;
import sudoken.domain.Position;
import sudoken.gui.BoardGraphics;

public class OperatorConstraint extends Constraint {
	/* operator defining behaviour of constraint */
	private Operator operator;
	
	/* target value that needs to be reached */
	private int target;
	
	/* list of cells that constraint is concerned about */
	private List<Position> positions;
	
	public OperatorConstraint(String provider, boolean shouldSave, List<Position> positions, int target, Operator operator) {
		super(provider, shouldSave);
		
		if (positions.size() < 2) {
			throw new IllegalArgumentException("Must have more than two positions.");
		}
		else if (operator.isBinary() && (positions.size() != 2)) {
			throw new IllegalArgumentException(
					"Must only have two positions for subtract or division operators.");
		}
		
		this.target = target;
		this.operator = operator;
		this.positions = positions;
	}
	
	@Override
	public boolean canHandle(Position position) {
		for (Position p : positions) {
			if (position.equals(p))
				return true;
		}
		
		return false;
	}
	
	@Override
	public boolean isViolated(Board board) {
		return operator.isViolated(board, positions, target);
	}
	
	public String save() {
    	String saveStr = target + " ";
    	if (operator == Operator.ADDITION)
    		saveStr += "+ ";
    	else if (operator == Operator.SUBTRACTION)
    		saveStr += "- ";
    	else if (operator == Operator.MULTIPLICATION)
    		saveStr += "* ";
    	else
    		saveStr += "/ ";
    	saveStr += positions.size() + " ";
    	for (Position p : positions)
    		saveStr += p.getX() + " " + p.getY() + " ";
    	return saveStr;
    }

	@Override
	public void decorate(BoardGraphics bg) {
		// TODO Auto-generated method stub
		
	}
}
