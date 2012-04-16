package sudoken.extension.sudoku;

import java.util.Collection;

import sudoken.domain.*;

public class SudokuCreator implements BoardCreator {

	@Override
	public Board create(int width, int height, int[][] grid, Collection<Constraint> constraints) {
		if(width != height) throw new IllegalArgumentException("Width and height must be equal.");
		if((int)Math.sqrt(width) * (int)Math.sqrt(width) != width)
			throw new IllegalArgumentException("Board dimension must be a square number.");
		
		for (int i = 0; i < height; i++) {
			int[] xValues = new int[width];
			int[] yValues = new int[width];
			for (int j = 0; j < width; j++) {
				xValues[j] = j;
				yValues[j] = i;
			}
			constraints.add(new UniqueConstraint(xValues, yValues));
		}
		
		for (int i = 0; i < width; i++) {
			int[] xValues = new int[height];
			int[] yValues = new int[height];
			for (int j = 0; j < height; j++) {
				xValues[j] = i;
				yValues[j] = j;
			}
			constraints.add(new UniqueConstraint(xValues, yValues));
		}
		
		int size = (int) Math.sqrt(width);
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				int[] xValues = new int[width];
				int[] yValues = new int[width];
				int k = 0;
				for(int a = i * size; a < (i + 1) * size; a++) {
					for(int b = j * size; b < (j + 1) * size; b++, k++) {
						xValues[k] = a;
						yValues[k] = b;
					}
				}
				constraints.add(new UniqueConstraint(xValues, yValues));
			}
		}
		
		return new Board(width, height, grid, width, constraints);
	}
}
