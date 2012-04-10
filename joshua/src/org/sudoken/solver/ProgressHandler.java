package org.sudoken.solver;

import org.sudoken.puzzle.*;

/**
 * Handler for progress updates from PuzzleSolver
 */
// TODO: how to force abortion of solving process?
public interface ProgressHandler
{
	/**
	 * Called by solver when a new board state is available
	 */
	void boardStateChanged(BoardState bstate);
	
	/**
	 * Called by solver to report estimated overall solving progress
	 */
	// FIXME: this may be the wrong format...
	float overallProgressUpdate();
}
