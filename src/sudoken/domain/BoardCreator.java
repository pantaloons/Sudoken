package sudoken.domain;

import java.util.Collection;

public interface BoardCreator {
	public Board create(int width, int height, String[][] grid, Collection<Constraint> constraints);
}
