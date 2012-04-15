package sudokusolver.square.events;

import java.util.Collection;

import sudokusolver.square.Square;
import sudokusolver.square.SquareGroup;

public class ValueSetEvent extends SquareChangeEvent {

    private int value;

    public ValueSetEvent(Square squareSource, Collection<SquareGroup> squareGroups, int value) {
        super(squareSource, squareGroups);
        this.value = value;
    }
    
    
    public int getValue() {
        return value;
    }
    
    public void setValue(int value) {
        this.value = value;
    }

}
