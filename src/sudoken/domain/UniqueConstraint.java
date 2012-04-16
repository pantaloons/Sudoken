package sudoken.domain;

public class UniqueConstraint implements Constraint {
	private int[] xValues, yValues;
	
	public UniqueConstraint(int[] xValues, int[] yValues) {
		if (xValues.length != yValues.length) {
			throw new IllegalArgumentException("UniqueConstraint must have an equal number of x and y values.");
		}
		this.xValues = xValues;
		this.yValues = yValues;
	}
	
	@Override
	public boolean isViolated(int x, int y, Board b) {
		boolean isPresent = false;
		for (int i = 0; i < xValues.length; i++) {
			if (x == xValues[i] && y == yValues[i]) {
				isPresent = true;
				break;
			}
		}
		if (!isPresent) {
			return false;
		}
		for (int i = 0; i < xValues.length; i++) {
			if ((x != xValues[i] || y != yValues[i]) && !(b.getValue(xValues[i], yValues[i]) == Board.UNSET)) {
				if (b.getValue(x, y) == b.getValue(xValues[i], yValues[i])) {
					return true;
				}
			}
		}
		return false;
	}
}
