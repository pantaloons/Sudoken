package extension.standard;

import sudokuboard.SudokuBoard;
import sudokusolver.connector.ConstraintsExtension;

public class StandardConstraints implements ConstraintsExtension {

    @Override
    public boolean isValid(int x, int y, SudokuBoard sudokuBoard) {       
        boolean isValid = true;
        
        // Rows
        for(int r = 0; r < sudokuBoard.getWidth(); r++) {
            if(r==x) {
                continue;
            }
            if(sudokuBoard.getValue(r, y).equals(sudokuBoard.getValue(x, y))) {
                isValid = false;
            }
        }
        
        // Columns
        for(int c = 0; c < sudokuBoard.getHeight(); c++) {
            if(c == y) {
                continue;
            }
            if(sudokuBoard.getValue(x, c).equals(sudokuBoard.getValue(x, y))) {
                isValid = false;
            }
        }
        
        // Blocks //TODO Irregular blocks
        double blockSizeDouble = Math.sqrt(sudokuBoard.getHeight());
        if(blockSizeDouble != Math.round(blockSizeDouble)) {
            throw new IllegalStateException("The sudokuBoard must be a square.");
        }
        int blockSize = (int) blockSizeDouble;
        
        int rowBlockOffset = (x * blockSize) / blockSize;
        int columnBlockOffset = (y * blockSize) / blockSize;
        
        for(int r = rowBlockOffset; r < rowBlockOffset + blockSize; r++) {
            for(int c = columnBlockOffset; c < blockSize; c++) {
                if(r == x && c == y) {
                    continue;
                }
                if(sudokuBoard.getValue(r, c).equals(sudokuBoard.getValue(x, y))) {
                    isValid = false;
                }
            }
        }
        
        return isValid;
    }
}