package sudoken.domain;

import java.util.ArrayList;
import java.util.List;

public class UniqueConstraint implements Constraint {
	private List<Integer> xValues, yValues;
	
	public UniqueConstraint() {
		xValues = new ArrayList<Integer>();
		yValues = new ArrayList<Integer>();
	}
	
	public void add(int x, int y) {
		xValues.add(x);
		yValues.add(y);
	}
	
	@Override
	public boolean isViolated(int x, int y, Board b) {
		boolean isPresent = false;
		for (int i = 0; i < xValues.size(); i++) {
			if (x == xValues.get(i) && y == yValues.get(i)) {
				isPresent = true;
				break;
			}
		}
		if (!isPresent) {
			return false;
		}
		for (int i = 0; i < xValues.size(); i++) {
			if ((x != xValues.get(i) || y != yValues.get(i)) && !(b.getValue(xValues.get(i), yValues.get(i)) == Board.UNSET)) {
				if (b.getValue(x, y) == b.getValue(xValues.get(i), yValues.get(i))) {
					return true;
				}
			}
		}
		return false;
	}
}
