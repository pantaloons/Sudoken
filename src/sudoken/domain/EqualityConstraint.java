package sudoken.domain;

/**
 * Tests whether two cells have the same value
 * 
 * @author Joshua Leung
 */
public class EqualityConstraint implements Constraint {
    /* cell positions that constraint tests */
    private int x1, y1;
    private int x2, y2;

    public EqualityConstraint(int x1, int y1, int x2, int y2) {
        /* cell 1 */
        this.x1 = x1;
        this.y1 = y1;

        /* cell 2 */
        this.x2 = x2;
        this.y2 = y2;
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
            /* violated if value are not equal */
            return v1 != v2;
        }
    }
}
