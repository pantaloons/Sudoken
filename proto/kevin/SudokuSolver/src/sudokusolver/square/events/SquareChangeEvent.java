package sudokusolver.square.events;

import java.util.ArrayList;
import java.util.Collection;

import sudokusolver.square.Square;
import sudokusolver.square.SquareGroup;

public class SquareChangeEvent {

    private Square squareSource;
    private Collection<SquareGroup> squareGroups = new ArrayList<SquareGroup>();
    
    
    public SquareChangeEvent(Square squareSource, Collection<SquareGroup> squareGroups) {
        super();
        this.squareSource = squareSource;
        this.squareGroups = squareGroups;
    }
    
    public Object getSquareSource() {
        return squareSource;
    }
    
    public void setSquareSource(Square eventSource) {
        this.squareSource = eventSource;
    }
    
    public Collection<SquareGroup> getSquareGroups() {
        return squareGroups;
    }

    public void setSquareGroups(Collection<SquareGroup> squareGroups) {
        this.squareGroups = squareGroups;
    }
}
