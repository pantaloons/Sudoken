package sudoken.extension.futoshiki;

import sudoken.domain.*;

public class InequalityConstraint implements Constraint {
    /* cell positions */
    private Position p1, p2;

    /* which way inequality points (1 should be less than 2 if true) */
    private boolean less;

    public InequalityConstraint(Position position1, Position position2, boolean isLess) {
        /* cell 1 */
        this.p1 = position1;

        /* cell 2 */
        this.p2 = position2;

        /* inequality direction */
        this.less = isLess;
    }

    @Override
    public boolean canHandle(Position position) {
        /* we're only interested in either on of the two cells we're checking */
        return (position.equals(p1) || position.equals(p2));
    }

    @Override
    public boolean isViolated(Board board) {
        int v1 = board.getValue(p1);
        int v2 = board.getValue(p2);

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
