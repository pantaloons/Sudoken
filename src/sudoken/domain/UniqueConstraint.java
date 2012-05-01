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

    /* list of cells that constraint is concerned about */
    protected List<Position> positions;
    private boolean highlight;

    public UniqueConstraint(String provider, boolean shouldSave, boolean highlight) {
    	super(provider, shouldSave);
        positions = new ArrayList<Position>();
        this.highlight = highlight;
    }

    public void add(Position newPosition) {
        positions.add(newPosition);
    }

    @Override
    public boolean canHandle(Position position) {
        for (Position p : positions) {
            if (position.equals(p))
                return true;
        }

        return false;
    }

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
    
    @Override
    public String save() {
    	String saveStr = "";
    	for (Position p : positions)
    		saveStr += p.getX() + " " + p.getY() + " ";
    	return saveStr;
    }
    
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
