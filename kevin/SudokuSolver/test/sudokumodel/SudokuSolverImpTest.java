package sudokumodel;

import static org.junit.Assert.*;

import org.junit.Test;

import sudokuboard.SudokuBoard;
import sudokuboard.imp.SudokuBoardImp;
import sudokusolver.SudokuSolver;
import sudokusolver.SudokuSolverImp;

public class SudokuSolverImpTest {

    @Test
    public void testSetBoard() {
        fail("Not yet implemented");
    }

    @Test
    public void testSolveBoard() {
        SudokuBoard board = new SudokuBoardImp(3);
        board.setSquareValue(0, 0, 8);
        board.setSquareValue(0, 2, 5);
        board.setSquareValue(0, 3, 7);
        board.setSquareValue(0, 7, 3);
        board.setSquareValue(1, 3, 5);
        board.setSquareValue(1, 5, 8);
        board.setSquareValue(1, 8, 7);
        board.setSquareValue(2, 4, 3);
        board.setSquareValue(2, 5, 4);
        board.setSquareValue(2, 6, 9);
        board.setSquareValue(3, 1, 7);
        board.setSquareValue(3, 6, 1);
        board.setSquareValue(4, 0, 9);
        board.setSquareValue(4, 8, 5);
        board.setSquareValue(5, 2, 6);
        board.setSquareValue(5, 7, 4);
        board.setSquareValue(6, 2, 1);
        board.setSquareValue(6, 3, 3);
        board.setSquareValue(6, 4, 4);
        board.setSquareValue(7, 0, 7);
        board.setSquareValue(7, 3, 2);
        board.setSquareValue(7, 5, 9);
        board.setSquareValue(8, 1, 8);
        board.setSquareValue(8, 5, 6);
        board.setSquareValue(8, 6, 2);
        board.setSquareValue(8, 8, 3);
        
        SudokuSolver solver = new SudokuSolverImp();
        solver.setBoard(board);
        board = solver.solveBoard();
        assertNotNull(board);
    }

}
