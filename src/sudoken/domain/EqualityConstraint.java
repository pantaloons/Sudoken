package sudoken.domain;

/**
 * Tests whether two cells have the same value
 * 
 * @author Joshua Leung
 */
public class EqualityConstraint implements Constraint {
    /* cell positions that constraint tests */
    private Position p1;
    private Position p2;

    public EqualityConstraint(Position position1, Position position2) {
    	/* cell 1 */
        this.p1 = position1;
        
        /* cell 2 */
        this.p2 = position2;
    }

    @Override
    public boolean canHandle(Position position) {
        /* we're only interested in either one of the two cells we're checking */
        return (p1.equals(position) || p2.equals(position));
    }

    @Override
    public boolean isViolated(Board board) {
        int v1 = board.getValue(p1);
        int v2 = board.getValue(p2);

        if ((v1 == Board.UNSET) || (v2 == Board.UNSET)) {
            /* not violated: cannot compare undefined */
            return false;
        } else {
            /* violated if value are not equal */
            return v1 != v2;
        }
    }
}
