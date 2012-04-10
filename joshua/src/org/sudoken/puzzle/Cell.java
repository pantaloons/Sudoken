package org.sudoken.puzzle;

/**
 * Represents a cell holding some value in a BoardState
 */
public class Cell
{
	/* Class Defines ============ */
	
	/* value for "empty" cells */
	public static final int EMPTY = 0;
	
	/* Instance Vars ============ */
	
	/* the value that this cell holds */
	public int value;
	
	/* Ctors ==================== */
	
	public Cell()
	{
		this(Cell.EMPTY);
	}
	
	/**
	 * ctor
	 * < value: initial value of cell
	 */
	public Cell(int value)
	{
		this.value = value;
	}
	
	/* Methods =================== */
	
	/* Is this cell empty? */
	public boolean isEmpty()
	{
		return this.value == Cell.EMPTY;
	}
	
	/* Get value of cell */
	public int getValue()
	{
		return this.value;
	}
}
