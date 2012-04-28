package sudoken.extension.kenken;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import sudoken.domain.BoardDecorator;
import sudoken.domain.Constraint;
import sudoken.domain.Position;
import sudoken.persistence.SectionParser;

import sudoken.extension.kenken.OperatorConstraint;
import sudoken.extension.kenken.Operator;

public class KenKenParser implements SectionParser {
	private static final String EXTENSION_NAME = "kenken";
	
	/* Format: Each line represents a "cage", and consists of the target, then the
	 * operator, then number of positions, then the x and y coordinates of the
	 * positions themselves in the format x1 y1 x2 y2...
	 * 
	 * E.g.: 12 * 2 0 0 0 1
	 * 
	 * I.e., positions (0, 0) and (0, 1) must multiply to 12.
	 * 
	 */

    @Override
    public Collection<Constraint> load(String config, int width, int height, BoardDecorator bd)
            throws ParseException {
        Scanner sc = new Scanner(config);
        List<Constraint> cageConstraints = new ArrayList<Constraint>();
        while (sc.hasNext()) {
        	int target = sc.nextInt();
        	String opStr = sc.next();
        	Operator operator;
			try {
				operator = Operator.fromSymbol(opStr);
			}
			catch (IllegalArgumentException iae) {
				throw new ParseException("Unknown operator.", 0);
			}
        	int numPositions = sc.nextInt();
        	List<Position> positions = new ArrayList<Position>();
        	for (int i = 0; i < numPositions; i++)
        		positions.add(new Position(sc.nextInt(), sc.nextInt()));
        	if (!arePositionsAdjacent(positions))
        		throw new ParseException("Cage positions nonadjacent.", 0);
        	cageConstraints.add(new OperatorConstraint(EXTENSION_NAME, true, positions, target, operator));
        }
        return cageConstraints;
    }
    
    /* Returns true if positions are fully adjacent. */
    private boolean arePositionsAdjacent(List<Position> positions) {
    	return arePositionsAdjacent(positions, new HashSet<Integer>());
    }
    
    private boolean arePositionsAdjacent(List<Position> positions, Set<Integer> adjIndices) {
    	if (adjIndices.isEmpty()) adjIndices.add(0);
    	// Find a position adjacent to ones we've found (in adjIndices).
    	for (int i = 0; i < positions.size(); i++) {
    		if (!adjIndices.contains(i)) {
    			// Check current position against found ones.
    			for (int adjIndex : adjIndices) {
    				if (arePositionsAdjacent(positions.get(i), positions.get(adjIndex))) {
    					adjIndices.add(i);
    					if (adjIndices.size() == positions.size()) return true;
    					else return arePositionsAdjacent(positions, adjIndices);
    				}
    			}
    		}
    	}
    	return false;
    }
    
    /* Returns true if two positions are vertically or horizontally adjacent to each other. */
    private boolean arePositionsAdjacent(Position p1, Position p2) {
    	boolean hAdj = (Math.abs(p1.getX() - p2.getX()) == 1);
    	boolean vAdj = (Math.abs(p1.getY() - p2.getY()) == 1);
    	return (hAdj && p1.getY() == p2.getY()) || (vAdj && p1.getX() == p2.getX());
    }
}
