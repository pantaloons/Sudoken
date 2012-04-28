package sudoken.extension.jigsaw;

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
import sudoken.domain.UniqueConstraint;
import sudoken.persistence.SectionParser;

public class JigsawParser implements SectionParser {
	
	private static final String EXTENSION_NAME = "jigsaw";
	
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
		List<List<Position>> piecesPositions = new ArrayList<List<Position>>();
		for (int i = 0; i < width; i++) {
			pieceConstraints.add(new UniqueConstraint(EXTENSION_NAME));
			piecesPositions.add(new ArrayList<Position>());
		}
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Change from 1-base to 0-base.
				int pieceNum = sc.nextInt() - 1;
				if (pieceNum >= 0 && pieceNum < width) {
					Position p = new Position(j, i);
					pieceConstraints.get(pieceNum).add(p);
					piecesPositions.get(pieceNum).add(p);
				} 
				else {
					throw new IOException("Parse error: Incorrect jigsaw piece number.");
				}
			}
		}
		for (List<Position> piecePositions: piecesPositions) {
			if (piecePositions.size() == width) {
				// Check that positions are adjacent.
				if (!arePositionsAdjacent(piecePositions))
					throw new IOException("Parse error: Nonadjacent jigsaw piece.");
			}
			else {
				throw new IOException("Parse error: Incorrect jigsaw piece size.");
			}
		}
		List<Constraint> c = new ArrayList<Constraint>();
		c.addAll(pieceConstraints);
		return c;
	}
	
	@Override
	public List<String> save(Collection<Constraint> constraints) throws ParseException {
		List<String> lines = new ArrayList<String>();
		
		int size = constraints.size();
		
		Map<Position, Integer> positionPieces = new HashMap<Position, Integer>();
		int i = 0;
		for (Constraint c : constraints) {
			i++;
			// TODO: Getting weird ClassCastExceptions. Alternative to casting?
			UniqueConstraint uc = (UniqueConstraint) c;
			for (Position p : uc.getPositions())
				positionPieces.put(p, i);
		}
		
		for (int row = 0; row < size; row++) {
			String curLine = "";
			for (int col = 0; col < size; col++) {
				Position p = new Position(col, row);
				if (!positionPieces.containsKey(p))
					throw new ParseException("Missing position", 0);
				curLine += positionPieces.get(p) + " ";
			}
			lines.add(curLine);
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
						if (adjIndices.size() == positions.size())
							return true;
						else 
							return arePositionsAdjacent(positions, adjIndices);
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
