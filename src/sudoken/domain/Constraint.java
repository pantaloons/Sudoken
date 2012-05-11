package sudoken.domain;

/**
 * The extension point for plugins to add constraints to the solving process.
 * 
 * @author Kevin Doran
 * @author Joshua Leung
 */
public abstract class Constraint {
	/**
	 * Name of the extension that created this constraint
	 */
	private String provider;
	/**
	 * Should this constraint be saved when a Board is saved 
	 */
	private boolean shouldSave;
	
	/**
	 * Create a constraint
	 * @param provider Name of the extension providing the Constraint
	 * @param shouldSave Whether this constraint should be saved when a Board is saved 
	 */
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
    
    /**
     * Get the name of the Extension that provided this constraint
     * @return The name of the extension that provided this constraint
     */
    public String getPluginProvider() {
    	return provider;
    }
    
    /**
     * Save the constraint
     * @return A string representation of the constraint
     */
    public abstract String save();
    
    /**
     * Get if the constraint should be saved when a Board is saved
     * @return True if the constraint should be saved, False otherwise
     */
    public boolean shouldSave() {
    	return shouldSave;
    }
}
