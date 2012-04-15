package sudoken.extension.sudoku;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import sudoken.domain.*;

public class SudokuCreator implements BoardCreator {

	@Override
	public Board create(int width, int height, String[][] grid, Collection<Constraint> constraints) {
		if(width != height) throw new IllegalArgumentException("Width and height must be equal.");
		if((int)Math.sqrt(width) * (int)Math.sqrt(width) != width)
			throw new IllegalArgumentException("Board dimension must be a square number.");
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				for(int k = j + 1; k < width; k++) {
					constraints.add(new EqualityConstraint(j, i, k, i));
				}
			}
		}
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				for(int k = j + 1; k < height; k++) {
					constraints.add(new EqualityConstraint(i, j, i, k));
				}
			}
		}
		
		int size = (int)Math.sqrt(width);
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				for(int a = i * size; a < (i + 1) * size; a++) {
					for(int b = j * size; b < (j + 1) * size; b++) {
						for(int c = i * size; c < (i + 1) * size; c++) {
							for(int d = j * size; d < (j + 1) * size; d++) {
								if(c < a || (c == a && d <= b)) continue;
								constraints.add(new EqualityConstraint(a, b, c, d));
							}
						}
					}
				}
			}
		}
		
		Set<String> cands = new TreeSet<String>();
		if(size <= 3) {
			for(int i = 1; i <= size * size; i++) cands.add(i + "");
		}
		else {
			for(int i = 0; i < size * size; i++) {
				String s = "";
				do {
					s += 'A' + (i % 26);
					i /= 26;
				} while(i != 0);
				cands.add(s);
			}
		}
		return new Board(width, height, grid, cands, constraints);
	}

}
