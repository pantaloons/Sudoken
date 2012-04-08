package sudokusolver.square.events;

import java.util.Collection;

import sudokusolver.imp.Square;
import sudokusolver.imp.SquareGroup;

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
