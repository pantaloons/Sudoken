package sudokuboard;

import java.util.Collection;

import sudokusolver.connector.ConstraintsExtension;

public interface SudokuBoard {
    
    int getWidth();
    int getHeight();
    String getValue(int x, int y);
    void setValue(int x, int y, String value);
    void setCellValueRange(Collection<String> rangeOfCellValues);
}
