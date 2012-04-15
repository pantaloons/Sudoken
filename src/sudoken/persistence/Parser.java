package sudoken.persistence;

import java.io.*;
import java.util.*;

import sudoken.domain.*;
import sudoken.extension.ExtensionManager;

/**
 * Default parser for a board. Reads in the game type and board information, then delegates
 * to extension specific parsers to read constraints.
 *
 */
public class Parser {
	
	/**
	 * Loads a board from a file.
	 * 
	 * @param fileName The file to load a board file.
	 * @return The loaded board
	 */
    public static Board load(String fileName) throws IOException {
    	Scanner sc = new Scanner(new File(fileName));
    	String type = sc.next();
    	if(!ExtensionManager.hasExtension(type)) throw new IOException("Extension " + type + " not loaded.");
    	
    	int width = sc.nextInt();
    	int height = sc.nextInt();
    	String[][] grid = new String[width][height];
    	for(int i = 0; i < height; i++) {
    		for(int j = 0; j < width; j++) {
    			grid[j][i] = sc.next();
    		}
    	}
    	
    	Collection<Constraint> constraints = new ArrayList<Constraint>();
    	while(sc.hasNextLine()) {
    		String line = sc.nextLine();
    		if(line.trim().equals("")) continue;
    		else if(line.charAt(0) != '.') throw new IOException("Parse error..."); //<-- TODO: fix that
    		
    		String ext = line.substring(1);
    		if(!ExtensionManager.hasExtension(ext)) throw new IOException("Parse error...");
    		
    		String conf = "";
    		boolean flipped = false;
    		while(sc.hasNextLine()) {
    			String cur = sc.nextLine();
    			if(line.trim().equals("")) break;
    			if(flipped) conf += "\n";
    			else flipped = true;
    			conf += cur;
    		}
    		constraints.addAll(ExtensionManager.getParser(ext).load(conf));
    	}
    	
    	return ExtensionManager.getConstructor(type).create(width, height, grid, constraints);
    }
}