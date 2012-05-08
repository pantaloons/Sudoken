package sudoken.extension.jigsaw;

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
import sudoken.domain.UniqueConstraint;
import sudoken.gui.BorderDecorator;
import sudoken.persistence.SectionParser;

/**
 * JigsawParser reads the constraints for a JigsawPuzzle
 *
 */
class JigsawParser implements SectionParser {
	
	/**
	 * Name of the encompassing Extension
	 */
	private static final String EXTENSION_NAME = "jigsaw";
	
    /**
     * Format: Each line represents a puzzle piece, and is a list of positions
     * that make up that puzzle piece in the format x1 y1 x2 y2...
     * 
     * @return
     * @throws ParseException 
     */
    @Override
    public Collection<Constraint> load(String config, int width, int height, BoardDecorator bd)
            throws ParseException {
        Scanner sc = new Scanner(config);
        List<List<Position>> pieces = new ArrayList<List<Position>>();
        List<Constraint> constraints = new ArrayList<Constraint>();
        for (int i = 0; i < width; i++) {
        	List<Position> piece = new ArrayList<Position>();
        	UniqueConstraint constraint = new UniqueConstraint(EXTENSION_NAME, true);
        	for (int j = 0; j < width; j++) {
        		int x, y;
        		if (sc.hasNextInt())
        			x = sc.nextInt();
        		else
        			throw new ParseException("Integer expected.", 0);
        		if (sc.hasNextInt())
        			y = sc.nextInt();
        		else
        			throw new ParseException("Integer expected.", 0);
        		if (x >= 0 && x < width && y >= 0 && y < height) {
        			Position p = new Position(x, y);
        			piece.add(p);
        			constraint.add(p);
        		}
        		else
        			throw new ParseException("Invalid coordinates specified.", 0);
        	}
        	if (piece.size() != width)
        		throw new ParseException("Jigsaw piece is incorrect size.", 0);
        	if (!arePositionsAdjacent(piece))
        		throw new ParseException("Positions of jigsaw piece " + i + " are not adjacent.", 0);
        	pieces.add(piece);
        	constraints.add(constraint);
        	bd.addConstraintDecorator(new BorderDecorator(constraint.getPositions()));
        }
        return constraints;
    }
    
    /**
     * Check if the Positions in a list are adjacent
     * @param positions list of Positions to check
     * @return True if the Positions in the list are adjacent, False otherwise
     */
    private boolean arePositionsAdjacent(List<Position> positions) {
    	return arePositionsAdjacent(positions, new HashSet<Integer>());
    }
    
    /**
     * Check if the Positions in a list are adjacent
     * @param positions list of Positions to check
     * @param adjIndices set of Position indices which have been checked to be adjacent
     * @return True if the Positions in the list are adjacent, False otherwise
     */
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
    
    /**
     * Check if two positions are adjacent
     * @param p1 First Position
     * @param p2 Second Position
     * @return True if the Positions are adjacent, False otherwise
     */
    private boolean arePositionsAdjacent(Position p1, Position p2) {
    	boolean hAdj = (Math.abs(p1.getX() - p2.getX()) == 1);
    	boolean vAdj = (Math.abs(p1.getY() - p2.getY()) == 1);
    	return (hAdj && p1.getY() == p2.getY()) || (vAdj && p1.getX() == p2.getX());
    }
}
