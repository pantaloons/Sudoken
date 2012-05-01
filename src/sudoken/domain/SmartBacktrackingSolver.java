package sudoken.domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Solves sudoku type puzzles by using a "smart" version of the backtracking
 * algorithm.
 * 
 * @author Adam Freeth
 * 
 */
public class SmartBacktrackingSolver extends Solver {
    private boolean stop;

    /**
     * Solve the puzzle intelligently and recursively
     */
	@Override
    public boolean solve() throws InterruptedException {
		stop = false;
        return solve(getNextPositions());
    }

    /**
     * Attempts to solve the remainder of the sudoku board starting at the given
     * position. This method is called recursively to solve a sudoku board,
     * looking for positions that may be better to try first.
     * 
     * @param nextPos
     *            the candidates for the next position to try
     * @return {@code true} if the board, starting from the start position, is
     *         solved. {@code false} if it cannot be solved.
     * @throws InterruptedException 
     */
    private boolean solve(Set<Position> nextPos) throws InterruptedException {
    	Iterator<Position> it = nextPos.iterator();
    	
        //notifyListeners(board);
        if (!it.hasNext()) {
            notifyListeners(board);
            return true;
        }
        
        Position p = it.next();
        nextPos.remove(p);

        for (int value = 1; value <= board.getNumCandidates(); value++) {
        	// Assume the computation time is minimal in comparison to the sleep time. 
            TimeUnit.MILLISECONDS.sleep(getMilisecondDelay());
            board.setValue(p, value);
            boolean legal = true;
            for (Constraint c : board.getConstraints()) {
                if (c.canHandle(p)) {
                    if (c.isViolated(board)) {
                        legal = false;
                        break;
                    }
                }
            }
            
            if (legal) {
            	Set<Position> newPos = getNextPositions();
            	if (newPos.size() == 0)
            		newPos = nextPos;
            	else if (nextPos.size() != 0)
            		nextPos.retainAll(newPos);
            		if (nextPos.size() != 0)
            			newPos = nextPos;
            	if (solve(newPos)) {
            		return true;
            	}
            }
        }
        board.setValue(p, Board.UNSET);
        return false;
    }
    
    /**
     * Get a set of Positions where values can be placed
     * @return A set of Positions where values can be placed
     */
    private Set<Position> getNextPositions() {
    	int minPossibilities = board.getNumCandidates() + 1;
    	Set<Position> nextPos = new HashSet<Position>();
    	for (int row = 0; row < board.getHeight(); row++) {
    		for (int col = 0; col < board.getWidth(); col++) {
    			Position p = new Position(col, row);
    			if (board.getValue(p) == Board.UNSET) {
    				int numPossibilities = 0;
    				for (int v = 1; v <= board.getNumCandidates(); v++) {
    					board.setValue(p, v);
    					boolean legal = true;
    					for (Constraint c : board.getConstraints()) {
    		                if (c.canHandle(p)) {
    		                    if (c.isViolated(board)) {
    		                        legal = false;
    		                        break;
    		                    }
    		                }
    		            }
    					board.setValue(p, Board.UNSET);
    					if (legal)
    						numPossibilities++;
    				}
    				if (numPossibilities < minPossibilities) {
    					minPossibilities = numPossibilities;
    					nextPos = new HashSet<Position>();
    					nextPos.add(p);
    				}
    				else if (numPossibilities == minPossibilities)
    					nextPos.add(p);
    			}
    		}
    	}
    	return nextPos;
    }
    
    /**
     * Stop the solver
     */
	@Override
	public void stop() {
		stop = true;
		
	}
}
