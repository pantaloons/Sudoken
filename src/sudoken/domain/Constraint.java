package sudoken.domain;

import sudoken.gui.BoardGraphics;

/**
 * The extension point for plugins to add constraints to the solving process.
 * 
 * @author Kevin Doran
 * @author Joshua Leung
 */
public abstract class Constraint {
	private String provider;
	private boolean shouldSave;
	
	public Constraint(String provider, boolean shouldSave) {
		this.provider = provider;
		this.shouldSave = shouldSave;
	}
	
    /**
     * Called by the solver to check if the constraint is interested in the cell
     * at the given position (for finding constraint violations).
     * 
     * @param x
     *            The x-position of the cell that has changed. Zero-based.
     * @param y
     *            The y-position of the cell that has changed. Zero-based.
     * @return {@code true} if constraint is interested in the cell
     */
    public abstract boolean canHandle(Position position);

    /**
     * Called by the solver to check if the current board state violates any
     * constraints defined by the extension. This is called whenever a single
     * cell is changed.
     * 
     * @param board
     *            The current board state that should be checked for violations
     * @return {@code true} if there the board violates constraints, otherwise
     *         {@code false}.
     * 
     * @precondition {@code canHandle()} should have been called to verify that
     *               cell has changed
     */
    public abstract boolean isViolated(Board board);
    
    public String getPluginProvider() {
    	return provider;
    }
    
    public abstract String save();
    
    public boolean shouldSave() {
    	return shouldSave;
    }
    
    public abstract void decorate(BoardGraphics bg);
}
