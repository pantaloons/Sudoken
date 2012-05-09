package sudoken.extension.kenken;

import java.util.List;

import sudoken.domain.Board;
import sudoken.domain.Constraint;
import sudoken.domain.Position;

/**
 * OperatorConstraint enforces that the values of a cage meet
 * the target value of the cage, when operated on by the Operator
 * of the cage
 *
 */
class OperatorConstraint extends Constraint {
	/** operator defining behaviour of constraint */
	private Operator operator;
	
	/** target value that needs to be reached */
	private int target;
	
	/** list of cells that constraint is concerned about */
	private List<Position> positions;
	
	/**
	 * Create an Operator Constraint
	 * @param provider Extension that provided the constraint
	 * @param shouldSave Should this constraint be saved when the board is saved
	 * @param positions List of Positions of the cells in the cage
	 * @param target Target value of the cage
	 * @param operator Operator used by the cage
	 */
	OperatorConstraint(String provider, boolean shouldSave, List<Position> positions, int target, Operator operator) {
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
		return positions.contains(position);
		/*for (Position p : positions) {
			if (position.equals(p))
				return true;
		}
		
		return false;*/
	}
	
	@Override
	public boolean isViolated(Board board) {
		return operator.isViolated(board, positions, target);
	}
	
	/**
	 * Save the constraint
	 * @return String representation of the constraint
	 */
	public String save() {
    	String saveStr = String.format("%d %s %d ", target, operator.toString(), positions.size());
    	for (Position p : positions)
    		saveStr += p.getX() + " " + p.getY() + " ";
    	return saveStr;
    }
	
	/**
	 * Get a string representing the constraint (position dependent)
	 * @return
	 */
	protected String getRepresentation() {
		return operator.toString() + target;
	}

	protected List<Position> getPositions() {
		return positions;
	}
}
