package sudoken.domain;

import java.io.File;
import java.io.IOException;

import sudoken.extension.ExtensionManager;
import sudoken.persistence.Parser;

/**
 * Solves sudoku type puzzles by using a backtracking algorithm.
 * 
 * @author Kevin Doran
 * 
 */
public class BacktrackingSolver extends Solver {
    @Override
    public boolean solve() {
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
     */
    private boolean solve(Position p) {
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
