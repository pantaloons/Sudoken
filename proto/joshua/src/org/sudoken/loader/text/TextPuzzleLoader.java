package org.sudoken.loader.text;

import java.awt.Dimension;
import java.io.*;
import java.util.*;

import org.sudoken.loader.*;
import org.sudoken.puzzle.*;

/**
 * Load grid-based number puzzle from text file
 */
public class TextPuzzleLoader implements PuzzleLoader
{
	/* Extension points registered... */
	private PuzzleLoaderExtension ext;
	
	
	/* Parse given filename */
	@Override
	public Puzzle load(String filename) throws FileNotFoundException
	{
		/* get file stream */
		TokenStream stream = new TokenStream(filename); // throws exception
		
		/* try to read file and create a puzzle */
		Puzzle puzzle;
		
		if (ext != null)
			puzzle = ext.init_puzzle();
		else
			puzzle = new Puzzle();
		
		try {
			/* 1) read dimensions of puzzle */
			Dimension dim = parse_dimensions(stream);
			
			/* 2) read initial board state */
			BoardState state = parse_initial_board(stream, dim);
			
			/* 3) extension hooks */
			if (ext != null) {
				/* 3a) parse rest of input? */
				if (stream.hasNext())
					ext.parse(puzzle, stream);
				
				/* 3b) post-parsing cleanup */
				ext.postLoading(puzzle);
			}
		}
		catch (Exception e) {
			System.err.println("Error occurred while trying to load file... ");
			e.printStackTrace();
			puzzle = null; // can't load the puzzle
		}
		
		/* cleanup */
		stream.close();
		return puzzle;
	}
	
	/* Parse dimensions from file */
	private Dimension parse_dimensions(TokenStream stream)
	{
		Dimension dim = null;
		
		/* try to read first line */
		if (stream.hasNext()) {
			List<String> tokens = stream.next();
			
			if (tokens.size() == 2) {
				int rows = Integer.parseInt(tokens.get(0));
				int cols = Integer.parseInt(tokens.get(1));
				
				dim = new Dimension(cols, rows);
			}
			else {
				System.err.format("PuzzleLoader.parse_dimensions(): " +
								  "Expected 2 values for dimensions, but got %d", 
								  tokens.size());
			}
		}
		
		return dim;
	}
	
	/* Parse initial board state */
	private BoardState parse_initial_board(TokenStream stream, Dimension dim)
	{
		BoardState state = null;
		
		for (int row_index=0; (row_index < dim.height) && stream.hasNext(); row_index++) {
			List<String> tokens = stream.next();
			
			/* ensure that we have expected number of columns */
			if (tokens.size() == dim.width) {
				
			}
			else {
				//throw new Exception("Incomplete grid encountered");
				break;
			}
		}
		
		return state;
	}
}
