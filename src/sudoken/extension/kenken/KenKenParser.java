package sudoken.extension.kenken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import sudoken.domain.Constraint;
import sudoken.domain.Position;
import sudoken.persistence.SectionParser;

public class KenKenParser implements SectionParser {
	
    /**
     * Format: Positive integers in a grid the same size as puzzle, with each
     * number specifying a cell's membership to a cage. After this, the
     * constraints for each cage are mapped in the following format:
     * 
     * cageNum target operator
     * 
     * e.g. (from KenKen puzzle on Wikipedia):
     * 
     *  1  2  2  3  4  4
     *  1  5  5  3  6  4
     *  7  7  8  8  6  4
     *  7  7  9 10 11 11
     * 12 12  9 10 10 13
     * 14 14 14 15 15 13
     * 
     * 1   11 +
     * 2    2 /
     * 3   20 *
     * 4    6 *
     * 5    3 -
     * 6    3 /
     * 7  240 *
     * 8    6 *
     * 9    6 *
     * 10   7 +
     * 11  30 *
     * 12   6 *
     * 13   9 +
     * 14   8 +
     * 15   2 /
     * 
     * @return
     */
    @Override
    public Collection<Constraint> load(String config, int width, int height)
            throws IOException {
        Scanner sc = new Scanner(config);
        Map<Integer, List<Position>> cagesPositions = new HashMap<Integer, List<Position>>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // Change from 1-base to 0-base.
                int cageNum = sc.nextInt() - 1;
                if (!cagesPositions.containsKey(cageNum)) {
                	cagesPositions.put(cageNum, new ArrayList<Position>());
                }
                cagesPositions.get(cageNum).add(new Position(j, i));
            }
        }
        List<Constraint> cageConstraints = new ArrayList<Constraint>();
        while (sc.hasNext()) {
        	int cageNum = sc.nextInt() - 1;
        	int target = sc.nextInt();
        	String opStr = sc.next();
        	int operator;
        	if (opStr.equals("+"))
        		operator = OperatorConstraint.ADDITION;
        	else if (opStr.equals("-"))
        		operator = OperatorConstraint.SUBTRACTION;
        	else if (opStr.equals("*"))
        		operator = OperatorConstraint.MULTIPLICATION;
        	else
        		operator = OperatorConstraint.DIVISION;
        	List<Position> positions = cagesPositions.remove(cageNum);
        	if (positions == null)
        		throw new IOException("Parse error");
        	if (!arePositionsAdjacent(positions))
        		throw new IOException("Parse error");
        	cageConstraints.add(new OperatorConstraint(positions, target, operator));
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
