package sudokusolver;

import java.util.Collection;

import sudokuboard.Position;
import sudokuboard.SudokuBoard;
import sudokusolver.connector.ConstraintsExtension;

public interface SudokuSolver {
    SudokuBoard getSudokuBoard();
    void setSudokuBoard(SudokuBoard sudokuBoard);
    boolean solve();
    void addConstraintsExtension(ConstraintsExtension constraints);
}
