package sudokusolver;

import sudokuboard.SudokuBoard;

public interface SudokuSolver {

    void setBoard(SudokuBoard board);
    
    SudokuBoard solveBoard();
    
    SudokuBoard getSolvedBoard();
}
