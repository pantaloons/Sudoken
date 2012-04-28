package sudoken.domain;

import java.util.concurrent.TimeUnit;

/**
 * Solves sudoku type puzzles by using a backtracking algorithm.
 * 
 * @author Kevin Doran
 * 
 */
public class BacktrackingSolver extends Solver {
    
	private boolean stop = false;
	
    @Override
    public boolean solve() throws InterruptedException {
    	stop = false;
        return solve(new Position(0, 0));
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
    private boolean solve(Position p) throws InterruptedException {
        if (stop) return false;
        //notifyListeners(board);
        if (p.getX() == board.getWidth()) {
        	p = new Position(0, p.getY() + 1);
            if (p.getY() == board.getHeight()) {
                // Reached the end of the board.
                notifyListeners(board);
                return true;
            }
        }

        if (board.getValue(p) != Board.UNSET) {
            return solve(new Position(p.getX() + 1, p.getY()));
        }

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

            if (legal && solve(new Position(p.getX() + 1, p.getY()))) {
                return true;
            }
        }
        board.setValue(p, Board.UNSET);
        return false;
    }

	@Override
	public void stop() {
		stop = true;
		
	}
}
