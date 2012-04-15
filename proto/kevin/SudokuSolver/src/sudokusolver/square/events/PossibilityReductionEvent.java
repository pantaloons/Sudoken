package sudokusolver.square.events;

import java.util.Collection;

import sudokusolver.square.Square;
import sudokusolver.square.SquareGroup;

public class PossibilityReductionEvent extends SquareChangeEvent {

    private int removedValue;
    
    public PossibilityReductionEvent(Square squareSource,
            Collection<SquareGroup> squareGroups, int removedValue) {
        super(squareSource, squareGroups);
        this.setRemovedValue(removedValue);
    }

    public int getRemovedValue() {
        return removedValue;
    }

    public void setRemovedValue(int removedValue) {
        this.removedValue = removedValue;
    } 

    
}
