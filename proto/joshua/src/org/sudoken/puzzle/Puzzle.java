package org.sudoken.puzzle;

import java.util.*;

/**
 * Representation of some grid-based number puzzle 
 */
public class Puzzle
{
	/* Instance Vars ============================ */
	
	/* path (including file name and extension) of file that puzzle came from */
	String filename;
	
	/* initial board state - given values */
	BoardState initialState;
	
	/* constraints that must be satisfied */
	// TODO: there are perhaps two kinds here - per-puzzle and per-puzzle-type...
	List<Constraint> constraints;
	
	/* ctor ===================================== */
	
	/* Methods ================================== */
	
	/** Get path to current puzzle */
	public String getFilename()
	{
		return filename;
	}
	
	/** Get readonly list of constraints */
	public List<Constraint> getConstraints()
	{
		return Collections.unmodifiableList(constraints);
	}
}
