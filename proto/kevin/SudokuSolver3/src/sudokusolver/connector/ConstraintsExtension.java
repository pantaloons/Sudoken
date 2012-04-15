package sudokusolver.connector;

import sudokuboard.SudokuBoard;

public interface ConstraintsExtension {

    // x and y are added for efficiency purposes. 
    boolean isValid(int x, int y, SudokuBoard sudokuBoard);
}
