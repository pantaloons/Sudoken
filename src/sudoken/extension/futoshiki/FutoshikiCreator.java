package sudoken.extension.futoshiki;

import java.util.Collection;

import sudoken.domain.Board;
import sudoken.domain.BoardCreator;
import sudoken.domain.Constraint;
import sudoken.extension.ExtensionManager;

public class FutoshikiCreator implements BoardCreator {
	
	private static final String BASE_EXTENSION = "latinsquare";

    @Override
    public Board create(int width, int height, int[][] grid,
            Collection<Constraint> constraints) {
    	
    	BoardCreator creator = null;
    	if (ExtensionManager.hasExtension(BASE_EXTENSION))
    		creator = ExtensionManager.getConstructor(BASE_EXTENSION);
    	else
    		/* TODO: Throw exception. */;

        return creator.create(width, height, grid, constraints);
    }
}
