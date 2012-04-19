package sudoken.extension.futoshiki;

import sudoken.domain.*;

public class InequalityConstraint implements Constraint {
    /* cell positions */
    private int x1, y1;
    private int x2, y2;

    /* which way inequality points (1 should be less than 2 if true) */
    private boolean less;

    public InequalityConstraint(int x1, int y1, int x2, int y2, boolean isLess) {
        /* cell 1 */
        this.x1 = x1;
        this.y1 = y1;

        /* cell 2 */
        this.x2 = x2;
        this.y2 = y2;

        /* inequality direction */
        this.less = isLess;
    }

    @Override
    public boolean canHandle(int x, int y) {
        /* we're only interested in either on of the two cells we're checking */
        return ((x == x1) && (y == y1)) || ((x == x2) && (y == y2));
    }

    @Override
    public boolean isViolated(Board board) {
        int v1 = board.getValue(x1, y1);
        int v2 = board.getValue(x2, y2);

        if ((v1 == Board.UNSET) || (v2 == Board.UNSET)) {
            /* not violated: cannot compare undefined */
            return false;
        } else {
            /*
             * violated: if relationship represents inverse inequality to the
             * valid state
             */
            if (less) {
                return v1 > v2;
            } else {
                return v1 < v2;
            }
        }
    }
}
