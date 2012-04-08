package sudokusolver.square.events;

import java.util.Collection;

import sudokusolver.imp.Square;
import sudokusolver.imp.SquareGroup;

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
