package sudoken.domain;

public class EqualityConstraint implements Constraint {
	private int x1, y1, x2, y2;
	
	public EqualityConstraint(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
    @Override
    public boolean isViolated(int x, int y, Board b) {
    	if((x != x1 || y != y1) && (x != x2 || y != y2)) return false;
    	if(b.getValue(x1, y1).equals(Board.UNSET) || b.getValue(x2, y2).equals(Board.UNSET)) return false;
    	return b.getValue(x1, y1).equals(b.getValue(x2, y2));
    }
}