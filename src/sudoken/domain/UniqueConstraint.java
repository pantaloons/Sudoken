package sudoken.domain;

import java.util.*;

/**
 * Ensure that within a set of constraints, each value occurs only once 
 * 
 * @author Adam Freeth
 * @author Joshua Leung
 */
public class UniqueConstraint implements Constraint 
{
	/* Little data-class to represent cell coordinates */
	// TODO: maybe we should have this as a proper class again, complete with equality tests
	private class Position 
	{
		int x;
		int y;
		
		Position(int x, int y) 
		{
			this.x = x;
			this.y = y;
		}
	}
	
	/* --------------------------- */
	
	/* list of cells that constraint is concerned about */
	private List<Position> positions;
	
	public UniqueConstraint() 
	{
		positions = new ArrayList<Position>();
	}
	
	public void add(int x, int y) 
	{
		positions.add(new Position(x, y));
	}
	
	@Override
	public boolean canHandle(int x, int y)
	{
		for (Position p : positions) {
			if ((p.x == x) && (p.y == y))
				return true;
		}
		
		return false;
	}

	@Override
	public boolean isViolated(Board board)
	{
		/* since we're only interested if things are all unique, 
		 * just bung them all into an set and balk if we find duplicates
		 * along the way (or if we find undefined cells)
		 */
		Set<Integer> cellValues = new HashSet<Integer>();
		boolean valid = true;
		
		for (Position p : positions) {
			int value = board.getValue(p.x, p.y);
			
			// NOTE: undefined cells would give false positives, but if we considered
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
}
