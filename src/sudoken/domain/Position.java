package sudoken.domain;

/**
 * Utility class to identify the position of a cell on a 
 * 2D grid.
 * 
 * All position indices are zero-based.
 */
public class Position {
    private int x, y;
    
    public Position(int x, int y) {
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
        if (object == null) return false;
        if (object == this) return true;
        if (object.getClass() != this.getClass()) return false;
        
        Position p = (Position)object;
        return p.getX() == x && p.getY() == y;
    }
    
    @Override
    public int hashCode() {
        return 131 * x + 139 * y;
    }
    
    @Override
    public String toString() {
    	return String.format("(%d, %d)", x, y);
    }
}