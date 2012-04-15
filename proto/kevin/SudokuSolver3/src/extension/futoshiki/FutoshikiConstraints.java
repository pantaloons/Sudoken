package extension.futoshiki;

import java.util.ArrayList;
import java.util.Collection;

import sudokuboard.BoardUtils;
import sudokuboard.Cell;
import sudokuboard.Position;
import sudokuboard.SudokuBoard;
import sudokusolver.connector.ConstraintsExtension;

public class FutoshikiConstraints implements ConstraintsExtension {

    private Collection<CellInequality> cellInequalities;
    private BoardUtils boardUtils;
    
    public FutoshikiConstraints(Collection<CellInequality> cellInequalities) {
        this.cellInequalities = cellInequalities;
    }
    
    @Override
    public boolean isValid(int x, int y, SudokuBoard sudokuBoard) {
        Position cellPosition = new Position(x, y);
        int value = Integer.parseInt(sudokuBoard.getValue(x, y));
        Cell cell = new Cell(cellPosition, value);
       
        Collection<Cell> neighbouringCells = boardUtils.getNeighbouringCells(cell, sudokuBoard);
        boolean isValid = true;
        for(Cell neighbouringCell : neighbouringCells) {
            for(CellInequality cellInequality : cellInequalities) {
                if(cellInequality.isViolated(cell, neighbouringCell)) {
                    isValid = false;
                    break;
                }
            }
        }
        
        return isValid;
    }
}
