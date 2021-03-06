package sudoken.extension.jigsaw;

import java.util.Collection;

import sudoken.domain.Board;
import sudoken.domain.BoardCreator;
import sudoken.domain.BoardDecorator;
import sudoken.domain.Constraint;
import sudoken.extension.ExtensionManager;

/**
 * JigsawCreator creates Boards to be used by the Jigsaw puzzle
 *
 */
public class JigsawCreator implements BoardCreator {
	
	/**
	 * The name of the encompassing extension
	 */
	private static final String BASE_EXTENSION = "latinsquare";

    @Override
    public Board create(int width, int height, int[][] grid,
            Collection<Constraint> constraints, BoardDecorator bd) {
    	
    	BoardCreator creator = null;
    	if (ExtensionManager.hasExtension(BASE_EXTENSION))
    		creator = ExtensionManager.getConstructor(BASE_EXTENSION);
    	else
    		/* TODO: Throw exception. */;

        return creator.create(width, height, grid, constraints, bd);
    }
}
