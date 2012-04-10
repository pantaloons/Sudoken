package org.sudoken.puzzle;

import java.util.List;

import org.sudoken.solver.*;

/**
 * Defines the state of the board at a particular point in time
 */
public interface BoardState extends Cloneable
{
	/**
	 * Get the cell at the given coordinates
	 * < row: (0-based index) row that the cell occurs in
	 * < col: (0-based index) column that the cell occurs in  
	 */
	Cell getCell(int row, int col);
	
	/**
	 * Get list of the cells in the region defined by the given extents
	 * < xmin, xmax: (0-based index) column numbers defining the range
	 * < ymin, ymax: (0-based index) column numbers defining the range
	 */
	List<Cell> getRegionCells(int xmin, int xmax, int ymin, int ymax);
}
