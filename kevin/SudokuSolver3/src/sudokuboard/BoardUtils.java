package sudokuboard;

import java.util.Collection;

public interface BoardUtils {

    public Collection<Cell> getNeighbouringCells(Cell cell, SudokuBoard board);
}
