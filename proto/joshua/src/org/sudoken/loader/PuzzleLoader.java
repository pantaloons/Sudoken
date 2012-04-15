package org.sudoken.loader;

import java.io.*;

import org.sudoken.puzzle.Puzzle;

/**
 * Load puzzle definition from a file, given puzzle type info 
 */
public interface PuzzleLoader
{
	/**
	 * Load puzzle from file located at given path
	 * @throws FileNotFoundException 
	 */
	Puzzle load(String filename) throws FileNotFoundException;
}
