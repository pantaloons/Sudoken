package sudoken.solver;

import sudoken.domain.*;

/**
 * The main sudoku solver. The sudoku solver solves the given sudoku board while
 * taking into account any added constraints.
 * 
 * @author Kevin Doran
 * 
 */
public interface Solver {

    /**
     * Sets the sudoku board that is to be solved.
     * 
     * @param sudokuBoard
     *            the sudoku board to be solved.
     */
    void setSudokuBoard(Board sudokuBoard);

    /**
     * Solves the sudoku board.
     * 
     * @return {@code true} if the board is solvable, {@code false} if it is
     *         not.
     */
    boolean solve();

    /**
     * Subscribes a SolverListener to this solver. The BoardChangeListener will
     * now have its {@link BoardChangeListener#processSolvedBoard(Board)} method
     * called when a board is loaded or is changed/solved.
     * 
     * @param listener
     *            the BoardChangeListener to subscribe.
     * @return {@code true} if the listener was successfully subscribed,
     *         {@code false} otherwise. A potential reason for having
     *         {@code false} returned is that the listener was already added
     *         previously.
     */
    boolean addListener(BoardChangeListener listener);
}
