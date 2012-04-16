package sudoken.extension.futoshiki;

import sudoken.domain.*;

public class InequalityConstraint implements Constraint {
    private int x1, y1, x2, y2;
    private boolean less;
    
    public InequalityConstraint(int x1, int y1, int x2, int y2, boolean isLess) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.less = isLess;
    }

    @Override
    public boolean isViolated(int x, int y, Board b) {
    	if(b.getValue(x1, y1) == Board.UNSET || b.getValue(x2, y2) == Board.UNSET) return false;
        if((x == this.x1 && y == this.y1) || (x == this.x2 && y == this.y2)) {
        	if(less) {
        		return b.getValue(x1, y1) > b.getValue(x2, y2);
        	}
        	else {
        		return b.getValue(x1, y1) < b.getValue(x2, y2);
        	}
        }
        
        return false;
    }
}
