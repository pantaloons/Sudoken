package sudoken.extension.futoshiki;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import sudoken.domain.Board;
import sudoken.domain.BoardCreator;
import sudoken.domain.Constraint;
import sudoken.domain.UniqueConstraint;

public class FutoshikiCreator implements BoardCreator {

	@Override
	public Board create(int width, int height, String[][] grid, Collection<Constraint> constraints) {
		if(width != height) throw new IllegalArgumentException("Width and height must be equal.");
		
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
		
		Set<String> cands = new TreeSet<String>();
		if(width <= 9) {
			for(int i = 1; i <= width; i++) cands.add(i + "");
		}
		else {
			for(int i = 0; i < width; i++) {
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
