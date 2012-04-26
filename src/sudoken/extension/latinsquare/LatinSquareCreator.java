package sudoken.extension.latinsquare;

import java.util.Collection;

import sudoken.domain.*;

public class LatinSquareCreator implements BoardCreator {
	
	private static final String EXTENSION_NAME = "latinsquare";

    @Override
    public Board create(int width, int height, int[][] grid,
            Collection<Constraint> constraints) {
        if (width != height)
            throw new IllegalArgumentException(
                    "Width and height must be equal.");

        for (int i = 0; i < height; i++) {
            UniqueConstraint rowConstraint = new UniqueConstraint(EXTENSION_NAME);
            for (int j = 0; j < width; j++) {
                rowConstraint.add(new Position(j, i));
            }
            constraints.add(rowConstraint);
        }

        for (int i = 0; i < width; i++) {
            UniqueConstraint colConstraint = new UniqueConstraint(EXTENSION_NAME);
            for (int j = 0; j < height; j++) {
                colConstraint.add(new Position(i, j));
            }
            constraints.add(colConstraint);
        }

        return new Board(width, height, grid, width, constraints);
    }
}
