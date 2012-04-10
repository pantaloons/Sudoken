package sudokusolver;

import sudokuboard.SudokuBoard;

/**
 * The main sudoku solver. The sudoku solver solves the given sudoku board while
 * taking into account any added constraints.
 * 
 * @author Kevin Doran
 *
 */
public interface SudokuSolver {
    
    /**
     * Sets the sudoku board that is to be solved.
     * 
     * @param sudokuBoard   the sudoku board to be solved.
     */
    void setSudokuBoard(SudokuBoard sudokuBoard);
    
    /**
     * Solves the sudoku board.
     * 
     * @return {@code true} if the board is solvable, {@code false} if it is not.
     */
    boolean solve();
    
    /**
     * Adds a constraint to the solving process. A board must not violate any 
     * constraints in order to be solved.
     * 
     * @param constraints   constraints that the solved board must not violate.
     */
    void addConstraintsExtension(ConstraintsExtension constraints);
}
