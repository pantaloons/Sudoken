package org.sudoken.puzzle;

import org.sudoken.puzzle.*;

/**
 * Some restriction on the validity of the board state  
 */
public interface Constraint
{
	/** 
	 * Checks whether the current board state is valid or not 
	 * > returns: True if current board state is valid (i.e. constraint isn't violated)
	 */
	boolean validate(BoardState state);
}
