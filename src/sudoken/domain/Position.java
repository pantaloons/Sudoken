package sudoken.domain;

/**
 * Utility class to identify the position of a cell on a 
 * 2D grid.
 * 
 * All position indices are zero-based.
 */
public class Position {
	/**
	 * x-coordinate of the Position
	 */
    private int x;
    /**
     * y-coordinate of the Position
     */
    private int y;
    
    /**
     * Create a Position
     * @param x x-coordinate of the new Position
     * @param y y-coordinate of the new Position
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Get the x-coordinate of the Position
     * @return x-coordinate of the Position
     */
    public int getX() {
        return x;
    }   
    
    /**
     * Get the y-coordinate of the Position
     * @return y-coordinate of the Position
     */
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