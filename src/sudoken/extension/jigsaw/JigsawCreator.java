package sudoken.extension.jigsaw;

import java.util.Collection;

import sudoken.domain.Board;
import sudoken.domain.BoardCreator;
import sudoken.domain.Constraint;
import sudoken.domain.UniqueConstraint;

public class JigsawCreator implements BoardCreator {
	
	@Override
	public Board create(int width, int height, int[][] grid, Collection<Constraint> constraints) {
		if(width != height) throw new IllegalArgumentException("Width and height must be equal.");
		
		for (int i = 0; i < height; i++) {
			UniqueConstraint rowConstraint = new UniqueConstraint();
			for (int j = 0; j < width; j++) {
				rowConstraint.add(j, i);
			}
			constraints.add(rowConstraint);
		}
		
		for (int i = 0; i < width; i++) {
			UniqueConstraint colConstraint = new UniqueConstraint();
			for (int j = 0; j < height; j++) {
				colConstraint.add(i, j);
			}
			constraints.add(colConstraint);
		}
		
		//TODO: Add jigsaw constraints
		
		return new Board(width, height, grid, width, constraints);
	}
}
