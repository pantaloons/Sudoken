package sudokusolver;

import sudokuboard.SudokuBoard;

/**
 * The extension point for plugins to add constraints to the solving process. 
 *  
 * @author Kevin Doran
 *
 */
public interface ConstraintsExtension {

    /**
     * Called by the solver to check if the current board state violates any
     * constraints defined by the extension. This is called whenever a single 
     * cell is changed. The position of the changed cell is given to make it
     * easier to find any violations. 
     * 
     * @param x     the x-position of the cell that has changed. 
     * @param y     the y-position of the cell that has changed.
     * @param sudokuBoard the whole sudoku board.
     * @return      {@code true} if there the board violates constraints, otherwise {@code false}.
     */
    boolean isViolated(int x, int y, SudokuBoard sudokuBoard);
}
