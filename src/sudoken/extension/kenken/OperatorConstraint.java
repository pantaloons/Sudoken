package sudoken.extension.kenken;

import java.util.List;

import sudoken.domain.Board;
import sudoken.domain.Constraint;
import sudoken.domain.Position;

public class OperatorConstraint extends Constraint {
	/* operator defining behaviour of constraint */
	private Operator operator;
	
	/* target value that needs to be reached */
	private int target;
	
    /* list of cells that constraint is concerned about */
    private List<Position> positions;

    public OperatorConstraint(String ext, List<Position> positions, int target, Operator operator) {
    	super(ext);
    	
    	if (positions.size() < 2)
    		throw new IllegalArgumentException("Must have more than two positions.");
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
    
    List<Position> getPositions() {
    	return positions;
    }
    
    int getTarget() {
    	return target;
    }
    
	Operator getOperator() {
    	return operator;
    }
}
