package org.sudoken.solver;

import org.sudoken.puzzle.*;

/**
 * Dummy progress handler which wraps another progress handler,
 * allowing us to use services provided by a ProgressHandler
 * whether or not an actual/useful instance is attached or not
 */
public class ProgressHandlerProxy implements ProgressHandler
{
	/* Instance Vars ============================== */
	
	/* wrapped progress handler - the real stuff */
	private ProgressHandler handler;
	
	
	/* Setup  ===================================== */
	
	/* ctor */
	public ProgressHandlerProxy()
	{
		// nothing to do...
	}
	
	/* Wrapper =================================== */
	
	/** Get wrapped progress handler */
	public ProgressHandler getWrapped()
	{
		return handler;
	}
	
	/** Set wrapped progress handler */
	public void setWrapped(ProgressHandler unwrapped)
	{
		handler = unwrapped;
	}
	
	/* ProgressHandler =========================== */
	
	/* Call method on wrapped object if present */
	@Override
	public void boardStateChanged(BoardState bstate)
	{
		if (handler != null)
			handler.boardStateChanged(bstate);
	}
	
	/* Call method on wrapped object if present */
	@Override
	public float overallProgressUpdate()
	{
		if (handler != null)
			return handler.overallProgressUpdate();
		else
			return 0.0f;
	}
}
