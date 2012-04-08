package sudokusolver.square;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import sudokusolver.square.events.PossibilityReductionEvent;
import sudokusolver.square.events.PossibilityReductionListener;
import sudokusolver.square.events.ValueSetEvent;
import sudokusolver.square.events.ValueSetListener;

public class SquareGroup implements ValueSetListener, PossibilityReductionListener {

    Collection<Square> squares = new ArrayList<>();
    Map<Integer, Collection<Square>> valuesToPossibleSquaresMap = new HashMap<>();
    
    public SquareGroup(int size) {
        for(int i=1; i<=size*size; i++) {
            valuesToPossibleSquaresMap.put(i, new ArrayList<Square>());
        }
    };
    
    public void addSquare(Square square) {
        squares.add(square);
        for(Collection<Square> possibleSquares : valuesToPossibleSquaresMap.values()) {
            possibleSquares.add(square);
        }
        square.addSquareGroup(this);
    }
    
    public boolean isNumberAvailable(int number) {
        Iterator<Square> it = squares.iterator();
        boolean isAvailable = true;
        while(it.hasNext() && isAvailable == true) {
            Square square = it.next();
            if(square!=null && square.getValue()==number) {
                isAvailable = false;
            }
        }
        return isAvailable;
    }
    

    @Override
    public void processValueSetEvent(ValueSetEvent event) {
        for(Collection<Square> squares : valuesToPossibleSquaresMap.values()) {
            squares.remove(event.getSquareSource());
        }
        for(Square square : squares) {
            if(!square.isSet()) {
                square.processValueSetEvent(event);
            }
        }
    }

    @Override
    public void processPossibilityReductionEvent(PossibilityReductionEvent event) {
        Collection<Square> possibleSquares = valuesToPossibleSquaresMap.get(event.getRemovedValue());
        boolean removed  = possibleSquares.remove(event.getSquareSource());
        assert removed : "If the possiblity reduction event is raised, then it must be possible, and therefore in the map.";
        if(possibleSquares.size() == 1) {
            Square onlyPossibleSquare = possibleSquares.iterator().next();
            onlyPossibleSquare.setValue(event.getRemovedValue());
        }
    }   
}
