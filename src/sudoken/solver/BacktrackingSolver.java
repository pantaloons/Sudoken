package sudoken.solver;

import java.util.ArrayList;
import java.util.Collection;

import sudoken.domain.*;

/**
 * Solves sudoku type puzzles by using a backtracking algorithm.
 * 
 * @author Kevin Doran
 *
 */
public class BacktrackingSolver implements Solver {
    private Board board;
    private Collection<BoardChangeListener> listeners = new ArrayList<BoardChangeListener>();

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
                notifyListeners(board);
                return true;
            }
        }

        if (board.getValue(x, y) != Board.UNSET) {
            return solve(x + 1, y);
        }

        for (int value = 1; value <= board.getNumCandidates(); value++) {
        	board.setValue(x, y, value);
        	//board.print();System.out.println(board.getNumCandidates());
            boolean legal = true;
            for (Constraint c : board.getConstraints()) {
                if (c.canHandle(x, y)) {
	            	if (c.isViolated(board)) {
	                    legal = false;
	                    break;
	                }
                }
            }

            if (legal && solve(x + 1, y)) {
            	return true;
            }
        }
        board.setValue(x, y, Board.UNSET);
        return false;
    }

    @Override
    public void setSudokuBoard(Board board) {
        this.board = board;
        notifyListeners(board);
    }

    @Override
    public boolean addListener(BoardChangeListener listener) {
        boolean added = listeners.add(listener);
        if(board != null) {
            // Inform listeners of the current board.
            listener.processSolvedBoard(board);
        }
        return added;
    }
    
    private void notifyListeners(Board solvedBoard) {
        for(BoardChangeListener listener : listeners) {
            listener.processSolvedBoard(solvedBoard);
        }
    }
}
