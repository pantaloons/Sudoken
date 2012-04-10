package sudokuboard;

import java.util.Collection;

/**
 * Represents the sudoku board without any constraints. Contains the dimensions
 * and the contents of the sudoku board, as well as the set of values that
 * a cell on the board can take.
 * 
 * @author Kevin Doran
 *
 */
public interface SudokuBoard {

    /**
     * The value representing unset/empty.
     */
    static String UNSET = "-";
   
    /**
     * @return  the width of the sudoku board.
     */
    int getWidth();

    /**
     * @return  the height of the sudoku board.
     */
    int getHeight();

    /**
     * Returns the value of the cell at the given position.
     * 
     * @param x the x-position of the cell.
     * @param y the y-position of the cell.
     * @return  the value of the cell at the given position.
     */
    String getValue(int x, int y);

    /**
     * Sets the value of the cell at the given position.
     * 
     * @param x the x-position of the cell.
     * @param y the y-position of the cell.
     * @param value the value to set the given cell.
     */
    void setValue(int x, int y, String value);

    /**
     * Sets the range of values that are valid for the sudoku board.
     * 
     * @param rangeOfCellValues the range of valid cell values.
     */
    void setValueRange(Collection<String> rangeOfCellValues);
    
    /**
     * @return  the range of valid cell values.
     */
    Collection<String> getValueRange();
}
