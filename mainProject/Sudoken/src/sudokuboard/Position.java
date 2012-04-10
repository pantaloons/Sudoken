package sudokuboard;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Represents a two dimensional integer position. 
 * 
 * @author Kevin Doran
 *
 */
public class Position {

    private int x;
    private int y;

    public Position(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Two positions are equal if both their x and y coordinates are the same.
     */
    @Override
    public boolean equals(Object object) {
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }

        Position rhs = (Position) object;
        boolean isEquals = new EqualsBuilder().append(this.getX(), rhs.getX())
                .append(this.getY(), rhs.getY()).isEquals();
        return isEquals;
    }

    @Override
    public int hashCode() {
        int primeA = 131;
        int primeB = 139;
        int hashCode = new HashCodeBuilder(primeA, primeB).append(getX())
                .append(getY()).toHashCode();
        return hashCode;
    }
}
