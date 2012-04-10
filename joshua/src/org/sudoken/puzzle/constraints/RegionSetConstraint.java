package org.sudoken.puzzle.constraints;

import java.util.*;

import org.sudoken.puzzle.*;
import org.sudoken.solver.*;

/**
 * Ensures that the numbers 1 to 9 only occur once in the specified region 
 */
public class RegionSetConstraint implements Constraint
{
	/* (0-based index) indices defining extents of region to check */
	private int xmin, xmax; /* columns */
	private int ymin, ymax; /* rows */
	
	/* ctor */
	public RegionSetConstraint(int xmin, int xmax, int ymin, int ymax)
	{
		this.xmin = xmin;
		this.xmax = xmax;
		this.ymin = ymin;
		this.ymax = ymax;
	}
	
	/* check if constraint holds for the current state of the board */
	@Override
	public boolean validate(BoardState state)
	{
		/* use bitflag vector to do quick value testing */
		List<Cell> cells = state.getRegionCells(xmin, xmax, ymin, ymax);
		int seenVec = 0;
		
		for (Cell c : cells) {
			if (c.isEmpty() == false) {
				int	vflag = 1 << c.getValue();
				//assert((1 <= c.getValue()) && (c.getValue() <= 9));
				
				if ((seenVec & vflag) != 0) {
					/* constraint violated somewhere on board */
					return false;
				}
				else {
					/* catch next-time failure... */
					seenVec |= vflag;
				}
			}
		}
		
		/* no violations found */
		return true;
	}
}
