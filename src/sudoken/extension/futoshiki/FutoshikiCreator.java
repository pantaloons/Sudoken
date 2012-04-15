package sudoken.extension.futoshiki;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import sudoken.domain.Board;
import sudoken.domain.BoardCreator;
import sudoken.domain.Constraint;
import sudoken.domain.EqualityConstraint;

public class FutoshikiCreator implements BoardCreator {

	@Override
	public Board create(int width, int height, String[][] grid, Collection<Constraint> constraints) {
		if(width != height) throw new IllegalArgumentException("Width and height must be equal.");
		
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
