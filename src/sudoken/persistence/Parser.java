package sudoken.persistence;

import java.io.*;
import java.text.ParseException;
import java.util.*;

import sudoken.domain.*;
import sudoken.extension.ExtensionManager;
import sudoken.extension.NoMatchingExtensionException;

/**
 * Default parser for a board. Reads in the game type and board information,
 * then delegates to extension specific parsers to read constraints.
 * 
 */
public class Parser {

    /**
     * Loads a board from a file.
     * 
     * @param f
     *            The file to load a board file.
     * @return The loaded board
     * @throws NoMatchingExtensionException
     * @throws ParseException
     */
    public static Board load(File f) throws IOException,
            NoMatchingExtensionException, ParseException {
        Scanner sc = new Scanner(f);
        String type = sc.next();
        if (!ExtensionManager.hasExtension(type)) {
            throw new NoMatchingExtensionException("Extension " + type
                    + " not loaded.");
        }
        ExtensionManager.setCurrentPrimaryExtension(type);

        int width = sc.nextInt();
        int height = sc.nextInt();
        int[][] grid = new int[width][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (sc.hasNextInt()) {
                    int nextInt = sc.nextInt();
                    if (nextInt > 0)
                        grid[j][i] = nextInt;
                    else
                        throw new IOException("Parse error...");
                } else {
                    if (sc.next().equals("-"))
                        grid[j][i] = -1;
                    else
                        throw new ParseException("Parse error...", 0);
                }
            }
        }

        Collection<Constraint> constraints = new ArrayList<Constraint>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.trim().equals(""))
                continue;
            else if (line.charAt(0) != '.')
                throw new ParseException("Parse error...", 0); // <-- TODO: fix
                                                               // that

            String ext = line.substring(1);
            if (!ExtensionManager.hasExtension(ext))
                throw new NoMatchingExtensionException("Parse error...");

            String conf = "";
            boolean flipped = false;
            while (sc.hasNextLine()) {
                String cur = sc.nextLine();
                if (line.trim().equals(""))
                    break;
                if (flipped)
                    conf += "\n";
                else
                    flipped = true;
                conf += cur;
            }
            constraints.addAll(ExtensionManager.getParser(ext).load(conf,
                    width, height));
        }
        return ExtensionManager.getConstructor(type).create(width, height,
                grid, constraints);
    }
    
    public static void save(Board board, File f) throws IOException {
    	// Open a writer for the save file.
    	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(f)));
    	String primaryExt = ExtensionManager.getCurrentPrimaryExtension();
    	
    	// Print out the main board configuration.
    	out.println(primaryExt);
    	out.println(board.getWidth() + " " + board.getHeight());
    	for (int row = 0; row < board.getHeight(); row++) {
    		for (int col = 0; col < board.getWidth(); col++) {
    			int v = board.getValue(new Position(col, row));
    			if (v == Board.UNSET)
    				out.print("- ");
    			else
    				out.print(v + " ");
    		}
    		out.println();
    	}
    	out.println();
    	
    	// Gather the board's constraints by extension.
    	Collection<Constraint> constraints = board.getConstraints();
    	Map<String, Collection<Constraint>> extConstraints = new HashMap<String, Collection<Constraint>>();
    	for (Constraint c : constraints) {
    		String ext = c.getExtensionName();
    		if (extConstraints.containsKey(ext))
    			extConstraints.get(ext).add(c);
    		else {
    			Collection<Constraint> constraintCol = new ArrayList<Constraint>();
    			constraintCol.add(c);
    			extConstraints.put(ext, constraintCol);
    		}
    	}
    	
    	// Get extension save configurations, starting with primary extension if applicable.
    	if (extConstraints.containsKey(primaryExt) && ExtensionManager.hasParser(primaryExt)) {
    		List<String> primaryExtConfig = ExtensionManager.getParser(primaryExt).getConfig(extConstraints.get(primaryExt));
    		if (primaryExtConfig.size() > 0) {
    			out.println("." + primaryExt);
    			for (String line : primaryExtConfig)
    				out.println(line);
    			out.println();
    		}
    		extConstraints.remove(primaryExt);
    	}
    	
    	// Get the rest of the extensions' save configurations.
    	Set<String> extNames = extConstraints.keySet();
    	for (String ext : extNames) {
    		if (ExtensionManager.hasParser(ext)) {
	    		List<String> extConfig = ExtensionManager.getParser(ext).getConfig(extConstraints.get(ext));
	    		if (extConfig.size() > 0) {
	    			for (String line : extConfig)
	    				out.println(line);
	    			out.println();
	    		}
    		}
    	}
    	
    	// Close the output writer.
    	out.close();
    }
}
