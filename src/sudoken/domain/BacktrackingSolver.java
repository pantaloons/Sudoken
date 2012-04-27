package sudoken.domain;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Solves sudoku type puzzles by using a backtracking algorithm.
 * 
 * @author Kevin Doran
 * 
 */
public class BacktrackingSolver extends Solver {
    private static final int MAX_LAG = 2;
    private Semaphore rateControl = new Semaphore(MAX_LAG);
    
    @Override
    public boolean solve() {
        return solve(new Position(0, 0));
    }
    
    private class RateController implements Runnable {

        @Override
        public void run() {
            rateControl.release();
            try {
                TimeUnit.MILLISECONDS.sleep(getMilisecondDelay());
            } catch (InterruptedException e) {
                e.printStackTrace();
                // End thread without action.
            }
        }
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
     */
    private boolean solve(Position p) {
        //notifyListeners(board);
        if (p.getX() == board.getWidth()) {
        	p = new Position(0, p.getY() + 1);
            if (p.getY() == board.getHeight()) {
                // Reached the end of the board.
                //notifyListeners(board);
                return true;
            }
        }

        if (board.getValue(p) != Board.UNSET) {
            return solve(new Position(p.getX() + 1, p.getY()));
        }

        for (int value = 1; value <= board.getNumCandidates(); value++) {
            try {
                rateControl.acquire();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            board.setValue(p, value);
            // board.print();System.out.println(board.getNumCandidates());
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
}
