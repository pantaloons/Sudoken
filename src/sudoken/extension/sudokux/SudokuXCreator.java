package sudoken.extension.sudokux;

import java.util.Collection;

import sudoken.domain.*;
import sudoken.extension.*;

public class SudokuXCreator implements BoardCreator {
	// XXX: review this
	private BoardCreator getSudokuCreator()
	{
		final String requiredExtension = "sudoku";
		
		if (ExtensionManager.hasExtension(requiredExtension))
			return ExtensionManager.getConstructor(requiredExtension);
		else
			return null;
	}
	
	@Override
	public Board create(int width, int height, int[][] grid, 
			Collection<Constraint> constraints) {
		/* create standard sudoku board */
		BoardCreator sudokuCreator = getSudokuCreator();
		Board board = sudokuCreator.create(width, height, grid, constraints);
		
		/* add our own constraints (diagonals) to this board */
		// XXX: perhaps we'd be better off with "board.addAdditionalConstraint()"?
		Collection<Constraint> boardConstraints = board.getConstraints();
		assert(height == width);
		
		UniqueConstraint forwardConstraint = new UniqueConstraint();
		for (int i = 0; i < width; i++) {
			forwardConstraint.add(i, i);
		}
		boardConstraints.add(forwardConstraint);
		
		UniqueConstraint backwardsConstraint = new UniqueConstraint();
		for (int i = 0, j = height-1;
			 (i < width) && (j >= 0);
			 i++, j--)
		{
			backwardsConstraint.add(j, i);
		}
		boardConstraints.add(backwardsConstraint);
		
		/* return this augmented board */
		return board;
	}
}

