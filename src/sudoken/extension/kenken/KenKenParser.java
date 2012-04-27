package sudoken.extension.kenken;

import java.io.IOException;
import java.text.ParseException;
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
	
	private static final String EXTENSION_NAME = "kenken";
	
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
        	else if (opStr.equals("/"))
        		operator = OperatorConstraint.DIVISION;
        	else
        		throw new IOException("Parse error: Unknown operator.");
        	List<Position> positions = cagesPositions.remove(cageNum);
        	if (positions == null)
        		throw new IOException("Parse error: Unknown cage number.");
        	if (!arePositionsAdjacent(positions))
        		throw new IOException("Parse error: Cage positions nonadjacent.");
        	cageConstraints.add(new OperatorConstraint(EXTENSION_NAME, positions, target, operator));
        }
        if (cagesPositions.size() > 0)
        	throw new IOException("Parse error: All cages must have constraints specified.");
        return cageConstraints;
    }

    @Override
    public List<String> save(Collection<Constraint> constraints) throws ParseException {
    	List<String> lines = new ArrayList<String>();
    	
    	Map<Position, Integer> positionCages = new HashMap<Position, Integer>();
    	List<Integer> cageTargets = new ArrayList<Integer>();
    	List<Integer> cageOperators = new ArrayList<Integer>();
    	int width = 0;
    	int height = 0;
    	int j = 0;
    	for (Constraint c : constraints) {
    		j++;
    		// TODO: Getting weird ClassCastExceptions. Alternative to casting?
    		OperatorConstraint oc = (OperatorConstraint) c;
    		cageTargets.add(oc.getTarget());
    		cageOperators.add(oc.getOperator());
    		for (Position p : oc.getPositions()) {
    			positionCages.put(p, j);
    			if (p.getX() >= width)
    				width = p.getX() + 1;
    			if (p.getY() >= height)
    				height = p.getY() + 1;
    		}
    	}
    	
    	int formatWidth = 1 + (int) Math.floor(Math.log10(constraints.size()));
    	
    	for (int row = 0; row < height; row++) {
    		String curLine = "";
    		for (int col = 0; col < width; col++) {
    			Position p = new Position(col, row);
    			if (!positionCages.containsKey(p))
    				throw new ParseException("Missing position", 0);
    			curLine += String.format("%" + formatWidth + "s", positionCages.get(p)) + " ";
    		}
    		lines.add(curLine);
    	}
    	
    	int maxTarget = 0;
    	for (int target : cageTargets)
    		if (target > maxTarget)
    			maxTarget = target;
    	int cageNumFormatWidth = 1 + (int) Math.floor(Math.log10(cageTargets.size()));
    	int targetFormatWidth = 1 + (int) Math.floor(Math.log10(maxTarget));
    	
    	for (int i = 0; i < cageTargets.size(); i++) {
    		String op = "+";
    		if (cageOperators.get(i) == OperatorConstraint.SUBTRACTION)
    			op = "-";
    		else if (cageOperators.get(i) == OperatorConstraint.MULTIPLICATION)
    			op = "*";
    		else if (cageOperators.get(i) == OperatorConstraint.DIVISION)
    			op = "/";
    		lines.add(String.format("%-" + cageNumFormatWidth + "d %" + targetFormatWidth + "d %s",
    				(i+1), cageTargets.get(i), op));
    	}
    	
    	return lines;
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
