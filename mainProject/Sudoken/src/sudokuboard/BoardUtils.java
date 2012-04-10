package sudokuboard;

import java.util.Collection;

/**
 * Convienience methods for working with sudoku boards.
 * 
 * @author Kevin Doran
 *
 */
public interface BoardUtils {
    
    /**
     * Returns the neighbouring cells of the given sudoku cell [top, right, left and right]. There can
     * be 2-4 cells returned depending on where on the board the given cell is located.
     * 
     * @param cell  the cell whose neighbours are to be found.
     * @param board the sudoku board which contains the cell.
     * @return the neighbouring cells of the given cell.
     */
    public Collection<Cell> getNeighbouringCells(Cell cell, SudokuBoard board);
}
