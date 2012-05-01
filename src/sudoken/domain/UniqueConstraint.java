package sudoken.domain;

import java.util.*;

import sudoken.gui.BoardGraphics;

/**
 * Ensure that within a set of constraints, each value occurs only once
 * 
 * @author Adam Freeth
 * @author Joshua Leung
 */
public class UniqueConstraint extends Constraint {

    /**
     *  List of cells that constraint is concerned about 
     **/
    protected List<Position> positions;
    
    /**
     * 
     */
    private boolean highlight;

    /**
     * Create a UniqueConstraint
     * @param provider Name of the extension providing the UniqueConstraint
     * @param shouldSave Whether this constraint should be saved when a Board is saved 
     * @param highlight Should the group of unique values be decorated
     */
    public UniqueConstraint(String provider, boolean shouldSave, boolean highlight) {
    	super(provider, shouldSave);
        positions = new ArrayList<Position>();
        this.highlight = highlight;
    }

    /**
     * Add a position to the list of Positions
     * @param newPosition Position to add to the list
     */
    public void add(Position newPosition) {
        positions.add(newPosition);
    }

    @Override
    /**
     * Can this constraint handle a certain Position
     * @param position Position to check if it is handled by this UniqueConstraint
     */
    public boolean canHandle(Position position) {
        return positions.contains(position);
        /*
    	for (Position p : positions) {
            if (position.equals(p))
                return true;
        }

        return false;*/
        
    }

    /**
     * Check if a Board state violates this UniqueConstraint
     * @param board Board to check for violation
     */
    @Override
    public boolean isViolated(Board board) {
        /*
         * since we're only interested if things are all unique, just bung them
         * all into an set and balk if we find duplicates along the way (or if
         * we find undefined cells)
         */
        Set<Integer> cellValues = new HashSet<Integer>();
        boolean valid = true;

        for (Position p : positions) {
            int value = board.getValue(p);

            // NOTE: undefined cells would give false positives, but if we
            // considered
            // these as invalid, the solving process would never get anywhere
            if (value != Board.UNSET) {
                if (cellValues.contains(value)) {
                    /* cell already had item = duplicate = invalid */
                    valid = false;
                    break;
                } 
                else {
                    /* value hasn't been seen yet */
                    cellValues.add(value);
                }
            }
        }

        // constraint is violated if board isn't valid
        return (valid == false);
    }
    
    /**
     * Save the UniqueConstraint
     * @return A string representation of this UniqueConstraint for saving
     */
    @Override
    public String save() {
    	String saveStr = "";
    	for (Position p : positions)
    		saveStr += p.getX() + " " + p.getY() + " ";
    	return saveStr;
    }
    
    /**
     * Decorate a Board with this UniqueConstraint
     * @param bg BoardGraphics to decorate
     */
    @Override
    public void decorate(BoardGraphics bg) {
    	if(!highlight) return;
    	
    	int[] dx = new int[]{0, -1, 0, 1};
    	int[] dy = new int[]{-1, 0, 1, 0};
    	
    	for(int i = 0; i < positions.size(); i++) {
    		Position p = positions.get(i);
    		for(int k = 0; k < 4; k++) {
    			int nx = p.getX() + dx[k];
    			int ny = p.getY() + dy[k];
    			
    			boolean edge = true;
				for(int j = 0; j < positions.size(); j++) {
					if(j == i) continue;
					
					Position p2 = positions.get(j);
					if(p2.getX() == nx && p2.getY() == ny) {
						edge = false;
						break;
					}
				}
    			
    			if(edge) {
    				bg.getCell(p).setBorderWidth(k, 2);
    			}
    		}
    	}
    }
}
