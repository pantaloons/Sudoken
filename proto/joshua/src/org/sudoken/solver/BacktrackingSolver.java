package org.sudoken.solver;

import org.sudoken.puzzle.*;

/**
 * Number puzzle solver which works by performing backtracking
 */
public class BacktrackingSolver implements PuzzleSolver
{
	/* Instance Vars =================================== */
	
	/* Here we use wrapper to facilitate seamless hotswapping.
	 * The outside world doesn't know this, as it will only ever see the wrapped instance.
	 */
	private ProgressHandlerProxy progress_handler;
	
	/* Setup ========================================== */
	
	/* ctor */
	public BacktrackingSolver()
	{
		/* we must always have a progress handler */
		progress_handler = new ProgressHandlerProxy();
	}
	
	/* Set progress handler to use for reporting progress */
	@Override
	public void setProgressHandler(ProgressHandler handler)
	{
		progress_handler.setWrapped(handler);
	}
	
	/* Get progress handler used to report progress */
	@Override
	public ProgressHandler getProgressHandler()
	{
		return progress_handler.getWrapped();
	}
	
	/* Setup ========================================== */
	
	/* Solve given puzzle */
	@Override
	public void solve(Puzzle puzzle)
	{
		// TODO Auto-generated method stub
		
	}
}
