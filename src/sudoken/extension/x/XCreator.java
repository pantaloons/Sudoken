package sudoken.extension.x;

import java.util.Collection;

import sudoken.domain.*;
import sudoken.extension.*;

public class XCreator implements BoardCreator
{
	/* identifier for extension that we're extending */
	private String baseExtension;
	
	public XCreator(String baseExtension)
	{
		this.baseExtension = baseExtension;
	}
	
	/* Get creator for puzzle type that we're extending with an X */
	private BoardCreator getCreator()
	{
		if (ExtensionManager.hasExtension(baseExtension))
			return ExtensionManager.getConstructor(baseExtension);
		else {
			/* TODO: Throw exception. */
			return null;
		}
	}
	
	@Override
	public Board create(int width, int height, int[][] grid, Collection<Constraint> constraints)
	{
		/* create standard sudoku board */
		Board board = getCreator().create(width, height, grid, constraints);
		
		/* add our own constraints (diagonals) to this board */
		// XXX: perhaps we'd be better off with
		// "board.addAdditionalConstraint()"?
		Collection<Constraint> boardConstraints = board.getConstraints();
		assert (height == width);
		
		UniqueConstraint forwardConstraint = new UniqueConstraint();
		for (int i = 0; i < width; i++) {
			forwardConstraint.add(new Position(i, i));
		}
		boardConstraints.add(forwardConstraint);
		
		UniqueConstraint backwardsConstraint = new UniqueConstraint();
		for (int i = 0, j = height - 1; (i < width) && (j >= 0); i++, j--) {
			backwardsConstraint.add(new Position(j, i));
		}
		boardConstraints.add(backwardsConstraint);
		
		/* return this augmented board */
		return board;
	}
}
