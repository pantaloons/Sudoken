package sudokuboard;

public interface SudokuBoard {

    void setSquareValue(int x, int y, int value);
    
    int getSquareValue(int x, int y);
    
    int getSize(); 
}
