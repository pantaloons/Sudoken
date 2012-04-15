package sudokuboard;


/**
 * 
 * @author Kevin Doran
 * 
 * Design prototype.
 *
 */
public class SudokuBoardImp implements SudokuBoard {

    private int size;
    private int[][] numberMatrix;
   
    
    public SudokuBoardImp(int size) {
        this.size = size;
        numberMatrix = new int[size*size][size*size];
    }

    @Override
    public void setSquareValue(int x, int y, int value) {
        numberMatrix[x][y] = value;
    }

    @Override
    public int getSquareValue(int x, int y) {
        return numberMatrix[x][y];
    }

    @Override
    public int getSize() {
        return size;
    }
}
