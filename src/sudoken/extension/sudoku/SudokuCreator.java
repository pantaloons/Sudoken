package sudoken.extension.sudoku;

import java.util.Collection;

import sudoken.domain.*;

public class SudokuCreator implements BoardCreator {

    @Override
    public Board create(int width, int height, int[][] grid,
            Collection<Constraint> constraints) {
        if (width != height)
            throw new IllegalArgumentException(
                    "Width and height must be equal.");
        if ((int) Math.sqrt(width) * (int) Math.sqrt(width) != width)
            throw new IllegalArgumentException(
                    "Board dimension must be a square number.");

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

        int size = (int) Math.sqrt(width);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                UniqueConstraint boxConstraint = new UniqueConstraint();
                for (int a = i * size; a < (i + 1) * size; a++) {
                    for (int b = j * size; b < (j + 1) * size; b++) {
                        boxConstraint.add(a, b);
                    }
                }
                constraints.add(boxConstraint);
            }
        }

        return new Board(width, height, grid, width, constraints);
    }
}
