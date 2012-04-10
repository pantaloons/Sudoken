package org.sudoken.solver;

import org.sudoken.puzzle.*;

/** 
 * Defines an algorithm for solving a grid-based number puzzle 
 */
public interface PuzzleSolver
{
	/** 
	 * Main entrypoint - Try to find a solution to the puzzle
	 * > returns: the first solution to the problem if a solution can be found
	 */
	// TODO: what about multiple solutions?
	//PuzzleSolution solve(Puzzle puzzle);
	void solve(Puzzle puzzle);
	
	
	/**
	 * Register the progress updates handler which we use to report progress 
	 */
	void setProgressHandler(ProgressHandler handler);
	
	/** 
	 * Get the progress updates handler currently in use 
	 */
	ProgressHandler getProgressHandler();
}
