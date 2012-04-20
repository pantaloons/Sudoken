package sudoken.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
    
    @Override
    public boolean equals(Object object) {
        if(object == null || object.getClass() != this.getClass()) {
            return false;
        }
        
        Position rhs = (Position) object;
        return (this.getX() == rhs.getX() && this.getY() == rhs.getY());
        // Not sure about the need for EqualsBuilder...
        // Does not run for me anyway.
        // - Adam
        //boolean isEquals = new EqualsBuilder().append(this.getX(), rhs.getX()).append(this.getY(), rhs.getY()).isEquals();
        //return isEquals;
    }
    
    @Override
    public int hashCode() {
        int primeA = 131;
        int primeB = 139;
        int hashCode = new HashCodeBuilder(primeA, primeB).append(getX()).append(getY()).toHashCode();
        return hashCode;
    }
}