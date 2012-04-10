package org.sudoken.loader.text;

import java.io.*;
import java.util.*;

/**
 * Parse a text file (from given filename), and assuming that it 
 * is delimited by whitespace, proceed to provide a list of strings
 * representing the unique tokens on each line
 */
public class TokenStream implements Iterator<List<String>>
{
	/* file that stream is backed to */
	private BufferedReader input_file;
	
	/* last line of text read in from file */
	private String last_line;
	
	
	/* ctor 
	 * < filename: path to a plain text file that we are required to parse 
	 */
	public TokenStream(String filename) 
			throws FileNotFoundException
	{
		/* try to load file, and read a line from it (so that hasNext() works) */
		input_file = new BufferedReader(new FileReader(filename));
		fetch_next_line();
	}
	
	
	/** 
	 * Check if file has another line of values available to be read 
	 */
	@Override
	public boolean hasNext()
	{
		// NOTE: we cannot do a readline here, in case caller just keeps polling this
		// this multiple times before read...
		return last_line != null;
	}
	
	/** 
	 * Get contents of the next line from the file 
	 * - Tries to fetch the next line 
	 */
	@Override
	public List<String> next()
	{
		/* parse the last string read in into a list of tokens */
		List<String> tokens;
		
		if (last_line != null) {
			// split on whitespace only - potential extension point here!
			tokens = Arrays.asList(last_line.split("\\s"));
		}
		else {
			tokens = new ArrayList<String>();
		}
		
		/* advance position in stream... */
		fetch_next_line();
		
		/* return line contents */
		return tokens;
	}
	
	/* dummy method required by Iterator */
	@Override
	public void remove()
	{
		throw new UnsupportedOperationException("TokenStreams are read-only");
	}
	
	
	/* Helper method - try to fetch next line of input from file */
	private void fetch_next_line()
	{
		try {
			last_line = input_file.readLine();
		}
		catch (IOException e) {
			last_line = null; // error occurred - cannot read file
		}
	}
	
	
	/** 
	 * Close stream and/or free resources 
	 * ! To be called when caller is done with this stream
	 */
	public void close()
	{
		/* close file-handle */
		try {
			input_file.close();
		}
		catch (IOException e) {
			// do nothing...
		}
		
		/* no more iteration should take place... */
		last_line = null;
	}
}
