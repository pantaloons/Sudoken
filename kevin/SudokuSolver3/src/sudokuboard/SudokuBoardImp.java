package sudokuboard;

import java.util.Collection;

public class SudokuBoardImp implements SudokuBoard {
    
    int height;
    int width;
    Collection<String> possibleCellValues;
    String[][] values;
    
    public SudokuBoardImp(int height, int width, String[][] values) {
        this.values = values;
    }

    
    @Override
    public void setCellValueRange(Collection<String> possibleCellValues) {
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
    

//  @Override
//  public String getValue(Position position) {
//      String value = values[position.getX()][position.getY()];
//      return value;
//  }    

}
