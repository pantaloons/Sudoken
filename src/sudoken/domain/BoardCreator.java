package sudoken.domain;

import java.util.Collection;

/**
 * 
 * A BoardCreator creates boards
 *
 */
public interface BoardCreator {
	/**
	 * Create a Board
	 * @param width Width of the Board
	 * @param height Height of the Board
	 * @param grid Grid to use for the Board
	 * @param constraints Board constraints for solving
	 * @param bd Board Decorator for rendering Board
	 * @return a Board with the given specifications and behaviours
	 */
    public Board create(int width, int height, int[][] grid,
            Collection<Constraint> constraints, BoardDecorator bd);
}
