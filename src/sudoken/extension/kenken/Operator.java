package sudoken.extension.kenken;

import java.util.List;
import sudoken.domain.*;

/**
 * The operation that OperatorConstraint performs.
 * 
 * Refactored to use Strategy/Enum design patterns to take advantage of inheritance.
 * 
 * @author Adam Freeth
 * @author Joshua Leung
 */
public enum Operator {
	/* Operator Defines ------------------ */
	ADDITION("+", false) {
		@Override
		public boolean isViolated(Board board, List<Position> positions, int target) {
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
	},
	SUBTRACTION("-", true) {
		@Override
		public boolean isViolated(Board board, List<Position> positions, int target) {
			int v1 = board.getValue(positions.get(0));
	    	int v2 = board.getValue(positions.get(1));
	    	
	    	if (v1 == Board.UNSET || v2 == Board.UNSET)
	    		return false;
	    	else
	    		return (Math.abs(v1 - v2) != target);
		}
	},
	MULTIPLICATION("*", false) {
		@Override
		public boolean isViolated(Board board, List<Position> positions, int target) {
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
	},
	DIVISION("/", true) {
		@Override
		public boolean isViolated(Board board, List<Position> positions, int target) {
			int v1 = board.getValue(positions.get(0));
	    	int v2 = board.getValue(positions.get(1));
	    	
	    	if (v1 == Board.UNSET || v2 == Board.UNSET)
	    		return false;
	    	else
	    		return (v1 / v2 * v2 != v1 || v1 / v2 != target) && (v2 / v1 * v1 != v2 || v2 / v1 != target);
		}
	};
	
	/* Operator -------------------------- */
	
	/* the symbol that describes the operator in files */
	private final String symbol;
	
	/* if operator is binary, it requires exactly 2 positions to check */
	private final boolean binary;
	
	/* ctor (used internally) */
	private Operator(String sym, boolean isBinary) {
		this.symbol = sym;
		this.binary = isBinary;
	}
	
	/* operator should only be represented via its string representation */
	@Override
	public String toString() {
		return this.symbol;
	}
	
	/* can't use valueOf, as that expects for full names of the enum items, and can't be subclassed */
	public static Operator fromSymbol(String value) {
		for (Operator op : values()) {
			if (value.equals(op.toString()))
				return op;
		}
		
		throw new IllegalArgumentException("Unknown operator");
	}
	
	/* returns whether operator requires exactly 2 positions (to be valid) */
	public boolean isBinary() {
		return this.binary;
	}
	
	/* checks if the board position violates the constraint imposed by this operator */
	public abstract boolean isViolated(Board board, List<Position> positions, int target);
}
