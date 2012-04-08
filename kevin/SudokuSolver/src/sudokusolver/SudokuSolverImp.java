package sudokusolver;

import java.util.ArrayList;
import java.util.Collection;

import sudokuboard.SudokuBoard;
import sudokuboard.imp.SudokuBoardImp;
import sudokusolver.imp.Square;
import sudokusolver.imp.SquareGroup;

public class SudokuSolverImp implements SudokuSolver {

    private SudokuBoard board;
    private Collection<SquareGroup> squareGroups = new ArrayList<>();
    private Square[][] squares;
    
    @Override
    public void setBoard(SudokuBoard board) {
        this.board = board;
    }

    @Override
    public SudokuBoard solveBoard() {
        
        // Create squares.
        int totalSize = board.getSize()*board.getSize();
        squares = new Square[totalSize][totalSize];
        for(int i = 0; i < totalSize; i++) {
            for(int j = 0; j < totalSize; j++){
               Square square = new Square(i, j); 
               squares[i][j] = square;
            }
        }
        
        // Organise into line groups.
        for(int i = 0; i < squares.length; i++) {
            SquareGroup verticalGroup = new SquareGroup(board.getSize());
            SquareGroup horizontalGroup = new SquareGroup(board.getSize());
            for(int j = 0; j < squares.length; j++) {
                verticalGroup.addSquare(squares[i][j]);
                horizontalGroup.addSquare(squares[j][i]);
            }
            squareGroups.add(verticalGroup);
            squareGroups.add(horizontalGroup);
        }
        
        // Organise into square groups. hmmm...very deep nesting...
        for(int x = 0; x < board.getSize(); x++) {
            for(int y =0; y < board.getSize(); y++) {
                SquareGroup squareGroup = new SquareGroup(board.getSize());
                for(int innerX = 0; innerX < board.getSize(); innerX++) {
                    for(int innerY = 0; innerY<board.getSize(); innerY++) {
                        int xpos = x * board.getSize() + innerX;
                        int ypos = y * board.getSize() + innerY;
                        squareGroup.addSquare(squares[xpos][ypos]);
                    }
                }
                squareGroups.add(squareGroup);
            }
        }
        
        for(int x = 0; x < totalSize; x++) {
            for(int y = 0; y < totalSize; y++) {
                int squareValue = board.getSquareValue(x, y);
                if(squareValue != 0) {
                    squares[x][y].setValue(squareValue);
                }
            }
        }
        
        SudokuBoard solvedBoard = new SudokuBoardImp(board.getSize());
        for(int x = 0; x < totalSize; x++) {
            for(int y = 0; y < totalSize; y++) {
                solvedBoard.setSquareValue(x, y, squares[x][y].getValue());
            }
        }
        return solvedBoard;
    }

    @Override
    public SudokuBoard getSolvedBoard() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
