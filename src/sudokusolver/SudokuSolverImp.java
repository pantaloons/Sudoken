package sudokusolver;

import java.util.Collection;

import sudokuboard.SudokuBoard;

/**
 * Solves sudoku type puzzles by using a backtracking algorithm.
 * 
 * @author Kevin Doran
 *
 */
public class SudokuSolverImp implements SudokuSolver {

    private SudokuBoard sudokuBoard;
    private Collection<ConstraintsExtension> allConstraints;
    private Collection<String> possibleCellValues;

    @Override
    public boolean solve() {
        boolean isSolved = solve(0, 0);
        return isSolved;
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
        if (x == sudokuBoard.getWidth()) {
            x = 0;
            y++;
            if (y == sudokuBoard.getHeight()) {
                // Reached the end of the board.
                return true;
            }
        }

        if (!sudokuBoard.getValue(x, y).equals(SudokuBoard.UNSET)) {
            return solve(x + 1, y);
        }

        for (String value : possibleCellValues) {
            sudokuBoard.setValue(x, y, value);
            boolean legal = true;
            for (ConstraintsExtension constraints : allConstraints) {
                boolean areConstraintsViolated = constraints.isViolated(x, y,
                        sudokuBoard);
                if (areConstraintsViolated) {
                    legal = false;
                    break;
                }
            }

            if (legal) {
                boolean areRemainingCellsSolveable = solve(x + 1, y);
                if (areRemainingCellsSolveable) {
                    return true;
                }
            }
        }
        sudokuBoard.setValue(x, y, SudokuBoard.UNSET);
        return false;
    }

    @Override
    public void setSudokuBoard(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
    }

    @Override
    public void addConstraintsExtension(ConstraintsExtension constraints) {
        allConstraints.add(constraints);
    }
}
