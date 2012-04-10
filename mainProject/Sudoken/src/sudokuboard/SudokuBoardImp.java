package sudokuboard;

import java.util.Collection;

/**
 * A sudoku board.
 * 
 * @author Kevin Doran
 *
 */
public class SudokuBoardImp implements SudokuBoard {

    private int height;
    private int width;
    private Collection<String> possibleCellValues;
    private String[][] values;

    public SudokuBoardImp(int height, int width, String[][] values) {
        this.values = values;
    }

    @Override
    public void setValueRange(Collection<String> possibleCellValues) {
        this.possibleCellValues = possibleCellValues;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public String getValue(int x, int y) {
        return values[x][y];
    }

    @Override
    public void setValue(int x, int y, String value) {
        values[x][y] = value;
    }

    @Override
    public Collection<String> getValueRange() {
        return possibleCellValues;
    }
}
