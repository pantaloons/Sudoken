package org.sudoken.loader;

import org.sudoken.loader.text.*;
import org.sudoken.puzzle.*;

/**
 * Custom PuzzleLoader extension point for parsing custom 
 * puzzle-type specific info from the input files
 */
public interface PuzzleLoaderExtension
{
	/**
	 * Get Puzzle instance that is suitable for the given Puzzle type
	 */
	Puzzle init_puzzle();
	
	/**
	 * Entry-point for performing continuing to parse
	 * the "custom" sections at the end of the input
	 * file, after the standard parsing has taken
	 * place
	 */
	// FIXME: TokenStream is at wrong level!
	void parse(Puzzle puzzle, TokenStream fstream);
	
	/**
	 * Operations to perform to given Puzzle after
	 * parsing takes place
	 */
	void postLoading(Puzzle puzzle);
}
