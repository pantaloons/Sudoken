package sudokusolver.square;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import sudokusolver.square.events.PossibilityReductionEvent;
import sudokusolver.square.events.PossibilityReductionEventSource;
import sudokusolver.square.events.PossibilityReductionListener;
import sudokusolver.square.events.ValueSetEvent;
import sudokusolver.square.events.ValueSetEventSource;
import sudokusolver.square.events.ValueSetListener;

public class Square implements  Serializable, ValueSetListener, ValueSetEventSource, PossibilityReductionEventSource {

    private int x;
    private int y;
    private Collection<Integer> possibleValues = new ArrayList<>();
    private int value;
    private Collection<SquareGroup> squareGroups = new ArrayList<SquareGroup>();
    private Collection<ValueSetListener> setListeners = new ArrayList<>();
    private Collection<PossibilityReductionListener> reductionListeners = new ArrayList<>();
    
    public Square(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        
        for(int i=1; i<=9; i++) {
            possibleValues.add(i);
        }
    }
    
    public Square(int x, int y, int value) {
        this(x,y);
        this.value = value;
    }
    
    public boolean removePossibleValue(int value) {
        boolean wasRemoved = possibleValues.remove(value);
        return wasRemoved;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        notifyValueSetListeners();
    }

    // Bad. Being a subscriber should not be hidden from the subscriber (aka group).
    // This should not be encapsulated. It also creates a dependency on SquareGroup.
    public void addSquareGroup(SquareGroup group) {
        squareGroups.add(group);
        setListeners.add(group);
        reductionListeners.add(group);
    }
    
    @Override
    public void processValueSetEvent(ValueSetEvent event) {
        boolean wasRemoved = possibleValues.remove(event.getValue());
        
        if(wasRemoved) {
            notifyPossibilityReductionListeners(event.getValue());
            
            if(possibleValues.size() == 1) {
                setValue(possibleValues.iterator().next());
                notifyValueSetListeners();
            }
        }
    }

    
    public boolean isSet() {
        boolean isSet;
        if(getValue()==0) {
            isSet = false;
        }
        else {
            isSet = true;
        }
        return isSet;
    }

    @Override
    public void addListener(PossibilityReductionListener listener) {
        reductionListeners.add(listener);
    }

    @Override
    public void removeListener(PossibilityReductionListener listener) {
        reductionListeners.remove(listener);
    }
    
    private void notifyPossibilityReductionListeners(int removedValue) {
        for(PossibilityReductionListener listener : reductionListeners) {
            PossibilityReductionEvent changeEvent = new PossibilityReductionEvent(this, squareGroups, removedValue);
            listener.processPossibilityReductionEvent(changeEvent);
        }
    }

    @Override
    public void addListener(ValueSetListener listener) {
        setListeners.add(listener);
    }

    @Override
    public void removeListener(ValueSetListener listener) {
        setListeners.remove(listener);
    }
    
    private void notifyValueSetListeners() {
        for(ValueSetListener listener : setListeners) {
            ValueSetEvent changeEvent = new ValueSetEvent(this, squareGroups, getValue());
            listener.processValueSetEvent(changeEvent);
        }       
    }
}
