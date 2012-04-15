package sudoken.solver;

import sudoken.domain.*;

/**
 * Solves sudoku type puzzles by using a backtracking algorithm.
 * 
 * @author Kevin Doran
 *
 */
public class BacktrackingSolver implements Solver {
    private Board board;

    @Override
    public boolean solve() {
        return solve(0, 0);
    }

    /**
     * Attempts to solve the remainder of the sudoku board starting at the given
     * position. This method is called recursively to solve a sudoku board in a brute
     * force manner. 
     * 
     * @param x the x-position to start solving the sudoku board.
     * @param y the y-position to start solving the sudoku board.
     * @return  {@code true} if the board, starting from the start position, is
     *          solved. {@code false} if it cannot be solved. 
     */
    private boolean solve(int x, int y) {
        if (x == board.getWidth()) {
            x = 0;
            y++;
            if (y == board.getHeight()) {
                // Reached the end of the board.
                return true;
            }
        }

        if (!board.getValue(x, y).equals(Board.UNSET)) {
            return solve(x + 1, y);
        }

        for (String value : board.getCandidates()) {
        	board.setValue(x, y, value);
            boolean legal = true;
            for (Constraint c : board.getConstraints()) {
                if(c.isViolated(x, y, board)) {
                    legal = false;
                    break;
                }
            }

            if(legal && solve(x + 1, y)) {
            	return true;
            }
        }
        board.setValue(x, y, Board.UNSET);
        return false;
    }

    @Override
    public void setSudokuBoard(Board board) {
        this.board = board;
    }
}
