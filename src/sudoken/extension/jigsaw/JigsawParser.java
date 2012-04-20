package sudoken.extension.jigsaw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import sudoken.domain.Constraint;
import sudoken.domain.UniqueConstraint;
import sudoken.persistence.SectionParser;

public class JigsawParser implements SectionParser {
	// TODO: Make Position universal? Code here gets a bit silly without it.
    private class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
	
    /**
     * Format: Positive integers in a grid the same size as puzzle, with each
     * number specifying a cell's membership to a jigsaw piece.
     * 
     * @return
     */
    @Override
    public Collection<Constraint> load(String config, int width, int height)
            throws IOException {
        Scanner sc = new Scanner(config);
        List<UniqueConstraint> pieceConstraints = new ArrayList<UniqueConstraint>();
        // Could probably also do with a Position class.
        List<List<Position>> piecesPositions = new ArrayList<List<Position>>();
        for (int i = 0; i < width; i++) {
            pieceConstraints.add(new UniqueConstraint());
            piecesPositions.add(new ArrayList<Position>());
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // Change from 1-base to 0-base.
                int pieceNum = sc.nextInt() - 1;
                if (pieceNum >= 0 && pieceNum < width) {
                    pieceConstraints.get(pieceNum).add(j, i);
                    piecesPositions.get(pieceNum).add(new Position(j, i));
                } else
                    throw new IOException("Parse error: Incorrect jigsaw piece number.");
            }
        }
        for (List<Position> piecePositions: piecesPositions) {
        	if (piecePositions.size() == width) {
        		// Check that positions are adjacent.
        		if (!arePiecesAdjacent(piecePositions, new HashSet<Integer>()))
        			throw new IOException("Parse error: Nonadjacent jigsaw piece.");
        	} else
        		throw new IOException("Parse error: Incorrect jigsaw piece size.");
        }
        List<Constraint> c = new ArrayList<Constraint>();
        c.addAll(pieceConstraints);
        return c;
    }
    
    private boolean arePiecesAdjacent(List<Position> positions, Set<Integer> adjIndices) {
    	if (adjIndices.isEmpty()) adjIndices.add(0);
    	// Find a position adjacent to ones we've found (in adjPositionIndices).
    	for (int i = 0; i < positions.size(); i++) {
    		if (!adjIndices.contains(i)) {
    			// Check current position against found ones.
    			for (int adjIndex : adjIndices) {
    				if (arePositionsAdjacent(positions.get(i), positions.get(adjIndex))) {
    					adjIndices.add(i);
    					if (adjIndices.size() == positions.size()) return true;
    					else return arePiecesAdjacent(positions, adjIndices);
    				}
    			}
    		}
    	}
    	return false;
    }
    
    private boolean arePositionsAdjacent(Position first, Position second) {
    	boolean hAdj = (Math.abs(first.x - second.x) == 1);
    	boolean vAdj = (Math.abs(first.y - second.y) == 1);
    	return (hAdj && first.y == second.y) || (vAdj && first.x == second.x);
    }
}
