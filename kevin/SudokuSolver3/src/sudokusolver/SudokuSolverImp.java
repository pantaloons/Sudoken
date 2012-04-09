package sudokusolver;

import java.util.Collection;

import sudokuboard.Position;
import sudokuboard.SudokuBoard;
import sudokusolver.connector.ConstraintsExtension;

public class SudokuSolverImp implements SudokuSolver {

    private SudokuBoard sudokuBoard;

    private Collection<ConstraintsExtension> allConstraints;
    private Collection<String> possibleCellValues;
    

    @Override
    public boolean solve() {
        boolean isSolved = solve(0, 0);
        return isSolved;
    }
    
    private boolean solve(int x, int y) {
        if(x == sudokuBoard.getWidth()) {
            x = 0;
            y++;
            if(y == sudokuBoard.getHeight()) {
                // Reached the end of the board.
                return true;
            }
        }
        
        if(!sudokuBoard.getValue(x, y).equals("")) {
            return solve(x+1, y);
        }
        
        for(String value : possibleCellValues) {
            sudokuBoard.setValue(x, y, value);
            boolean legal = true;
            for(ConstraintsExtension constraints : allConstraints) {
                boolean abidesByConstraints = constraints.isValid(x, y, sudokuBoard);
                if(!abidesByConstraints) {
                    legal = false;
                    break;
                }
            }
            
            if(legal) {
                boolean areRemainingCellsSolveable = solve(x+1, y);
                if(areRemainingCellsSolveable) {
                    return true;
                }
            }            
        }
        sudokuBoard.setValue(x, y, "");
        return false;
    }
    
    
    
    @Override
    public SudokuBoard getSudokuBoard() {
        return sudokuBoard;
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
