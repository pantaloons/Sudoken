package sudoken.extension.futoshiki;

import sudoken.domain.*;
import sudoken.gui.BoardGraphics;
import sudoken.gui.GapGraphics;

/**
 * InequalityContraint represents an inequality between two cells (eg. one cell must have a value greater than another)
 * 
 * @author Tim Hobbs
 */
public class InequalityConstraint extends Constraint {
    /* cell positions */
    private Position p1, p2;

    /* which way inequality points (1 should be less than 2 if true) */
    private boolean less;

    /**
     * Create an InequalityPosition
     * @param provider name of the  Extension that provided this constraint
     * @param shouldSave Should this constraint be saved when the Board is saved
     * @param position1 Position of the first cell in the equation 
     * @param position2 Position of the second cell in the equation
     * @param isLess is the cell at position 1 less than the cell at position 2
     */
    public InequalityConstraint(String provider, boolean shouldSave, Position position1, Position position2, boolean isLess) {
    	super(provider, shouldSave);
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
        }
        else {
            /*
             * violated: if relationship represents inverse inequality to the
             * valid state
             */
            if (less) {
                return v1 > v2;
            }
            else {
                return v1 < v2;
            }
        }
    }
    
    /**
     * Get the position of an affected cell
     * @param index index of the cell in the equation (0 or 1)
     * @return the Position of the selected cell
     */
    public Position getPosition(int index) {
    	if (index == 0) return p1;
    	else if (index == 1) return p2;
    	else return null;
    }
    
    /**
     * Get the position of the first cell in the equation
     * @return the position of the first cell in the equation
     */
    public Position getFirstPosition() {
    	return p1;
    }
    
    /**
     * Get the position of the second cell in the equation
     * @return the position of the second cell in the equation
     */
    public Position getSecondPosition() {
    	return p2;
    }
    
    /**
     * Should the value of the first cell in the equation be less than the value of the second cell
     * @return True if the first cell should be less than the second, False otherwise
     */
    public boolean isLess() {
    	return less;
    }
    
    /**
     * Save the constraint
     */
    public String save() {
    	String ineq = ">";
    	if (less) ineq = "<";
    	return p1.getX() + " " + p1.getY() + " " + ineq + " " + p2.getX() + " " + p2.getY();
    }
}
