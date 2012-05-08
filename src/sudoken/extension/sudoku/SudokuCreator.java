package sudoken.extension.sudoku;

import java.util.Collection;

import sudoken.domain.*;
import sudoken.extension.ExtensionManager;
import sudoken.gui.BorderDecorator;

/**
 * SudokuCreator creates Boards to be used with Sudoku puzzles
 *
 */
public class SudokuCreator implements BoardCreator {
	
	/** Name of base prerequisite extension */
	private static final String BASE_EXTENSION = "latinsquare";
	
	/** Name of encompassing Extension */
	private static final String EXTENSION_NAME = "sudoku";
	
    @Override
    public Board create(int width, int height, int[][] grid,
            Collection<Constraint> constraints, BoardDecorator bd) {
    	
    	BoardCreator creator = null;
    	if (ExtensionManager.hasExtension(BASE_EXTENSION))
    		creator = ExtensionManager.getConstructor(BASE_EXTENSION);
    	else
    		/* TODO: Throw exception. */;
    		
    	Board board = creator.create(width, height, grid, constraints, bd);
    	
        if ((int) Math.sqrt(width) * (int) Math.sqrt(width) != width)
            throw new IllegalArgumentException(
                    "Board dimension must be a square number.");
        
        Collection<Constraint> boardConstraints = board.getConstraints();

        int size = (int) Math.sqrt(width);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                UniqueConstraint boxConstraint = new UniqueConstraint(EXTENSION_NAME, false);
                for (int a = i * size; a < (i + 1) * size; a++) {
                    for (int b = j * size; b < (j + 1) * size; b++) {
                        boxConstraint.add(new Position(a, b));
                    }
                }
                boardConstraints.add(boxConstraint);
                bd.addConstraintDecorator(new BorderDecorator(boxConstraint.getPositions()));
            }
        }

        return board;
    }
}
