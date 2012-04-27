package sudoken.extension.kenken;

import java.util.List;

import sudoken.domain.Board;
import sudoken.domain.Constraint;
import sudoken.domain.Position;

public class OperatorConstraint extends Constraint {
	public static final int       ADDITION = 0;
	public static final int    SUBTRACTION = 1;
	public static final int MULTIPLICATION = 2;
	public static final int       DIVISION = 3;
	
	private int target;
	private int operator;
	
    /* list of cells that constraint is concerned about */
    private List<Position> positions;

    public OperatorConstraint(String ext, List<Position> positions, int target, int operator) {
    	super(ext);
    	if (operator < ADDITION && operator > DIVISION)
    		throw new IllegalArgumentException("Unknown operator.");
    	if (positions.size() < 2)
    		throw new IllegalArgumentException("Must have more than two positions.");
    	if (operator == SUBTRACTION || operator == DIVISION) {
    		if (positions.size() != 2)
    			throw new IllegalArgumentException(
    					"Must only have two positions for subtraction or division operators.");
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
    	if (operator == ADDITION)
    		return isAdditionViolated(board);
    	if (operator == SUBTRACTION)
    		return isSubtractionViolated(board);
    	if (operator == MULTIPLICATION)
    		return isMultiplicationViolated(board);
    	else
    		return isDivisionViolated(board);
    }
    
    private boolean isAdditionViolated(Board board) {
    	boolean isComplete = true;
    	int currentSolution = 0;
    	
    	for (Position p : positions) {
    		int v = board.getValue(p);
    		if (v == Board.UNSET)
    			isComplete = false;
    		else
    			currentSolution += v;
    	}
    	if (isComplete)
    		return (currentSolution != target);
    	else
    		return (currentSolution >= target);
    }
    
    private boolean isSubtractionViolated(Board board) {
    	int v1 = board.getValue(positions.get(0));
    	int v2 = board.getValue(positions.get(1));
    	
    	if (v1 == Board.UNSET || v2 == Board.UNSET)
    		return false;
    	else
    		return (Math.abs(v1 - v2) != target);
    }
    
    private boolean isMultiplicationViolated(Board board) {
    	boolean isComplete = true;
    	int currentSolution = 1;
    	
    	for (Position p : positions) {
    		int v = board.getValue(p);
    		if (v == Board.UNSET)
    			isComplete = false;
    		else
    			currentSolution *= v;
    	}
    	if (isComplete)
    		return currentSolution != target;
    	else
    		return currentSolution > target;
    }
    
    private boolean isDivisionViolated(Board board) {
    	int v1 = board.getValue(positions.get(0));
    	int v2 = board.getValue(positions.get(1));
    	
    	if (v1 == Board.UNSET || v2 == Board.UNSET)
    		return false;
    	else
    		return (v1 / v2 * v2 != v1 || v1 / v2 != target) && (v2 / v1 * v1 != v2 || v2 / v1 != target);
    }
    
    List<Position> getPositions() {
    	return positions;
    }
    
    int getTarget() {
    	return target;
    }
    
    int getOperator() {
    	return operator;
    }
}
