package sudokuboard;


public class Cell {
    
    private Position position;
    private int value;
    
    public Cell(Position position) {
        super();
        this.position = position;
    }
    
    public Cell(Position position, int value) {
        this(position);
        this.value = value;
    }
    
    public Position getPosition() {
        return this.position;
    }
    
    public int getValue() {
        return value;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
}
