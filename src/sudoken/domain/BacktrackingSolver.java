package sudoken.domain;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

/**
 * Solves sudoku type puzzles by using a backtracking algorithm.
 * 
 * @author Kevin Doran
 * 
 */
public class BacktrackingSolver extends Solver {
	private BigInteger checked;
	private BigInteger total;
    
    @Override
    public boolean solve() throws InterruptedException {
    	checked = BigInteger.ZERO;
    	total = BigInteger.valueOf(board.getNumCandidates()).pow(board.getFreeSquares());
        return solve(new Position(0, 0), board.getFreeSquares());
    }
    
    /**
     * Attempts to solve the remainder of the sudoku board starting at the given
     * position. This method is called recursively to solve a sudoku board in a
     * brute force manner.
     * 
     * @param x
     *            the x-position to start solving the sudoku board.
     * @param y
     *            the y-position to start solving the sudoku board.
     * @return {@code true} if the board, starting from the start position, is
     *         solved. {@code false} if it cannot be solved.
     * @throws InterruptedException 
     */
    private boolean solve(Position p, int free) throws InterruptedException {
        
        //notifyListeners(board);
        if (p.getX() == board.getWidth()) {
        	p = new Position(0, p.getY() + 1);
            if (p.getY() == board.getHeight()) {
                // Reached the end of the board.
                notifyListeners(1.0);
                return true;
            }
        }

        if (board.getValue(p) != Board.UNSET) {
            return solve(new Position(p.getX() + 1, p.getY()), free);
        }

        BigInteger each = BigInteger.valueOf(board.getNumCandidates()).pow(free - 1);
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
            
            if(legal && solve(new Position(p.getX() + 1, p.getY()), free - 1)) {
        		return true;
        	}
            checked = checked.add(each);
        }
        board.setValue(p, Board.UNSET);
        return false;
    }

	@Override
	public double getProgress() {
		return checked.multiply(BigInteger.valueOf(2000)).divide(total).doubleValue() / 1000.0;
	}
}
