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
        BoardDecorator decorator = ExtensionManager.getDecorator(type);

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
                        throw new IOException("Invalid grid found in .sudoku file.");
                }
                else {
                    if (sc.next().equals("-"))
                        grid[j][i] = -1;
                    else
                        throw new ParseException("Invalid grid found in .sudoku file.", 0);
                }
            }
        }

        Collection<Constraint> constraints = new ArrayList<Constraint>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.trim().equals("")) {
            	continue;
            }
            else if (line.charAt(0) != '.') {
                throw new ParseException("Invalid metadata section found in .sudoku file.", 0);
            }

            String ext = line.substring(1);
            if (!ExtensionManager.hasExtension(ext))
                throw new NoMatchingExtensionException("Sudoken puzzle depends on extension which is not loaded.");

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
                    width, height, decorator));
        }
        return ExtensionManager.getConstructor(type).create(width, height,
                grid, constraints, decorator);
    }
    
    /**
     * Save a  Board to a file
     * @param board Board to save
     * @param f File to save to
     * @throws IOException
     * @throws ParseException
     */
    public static void save(Board board, File f) throws IOException, ParseException {
    	// Open a writer for the save file.
    	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(f)));
    	String primaryExt = ExtensionManager.getCurrentPrimaryExtension();
    	
    	// Print out the main board configuration.
    	out.println(primaryExt);
    	out.println(board.getWidth() + " " + board.getHeight());
    	
    	int formatWidth = 1 + (int) Math.floor(Math.log10(board.getNumCandidates()));
    	for (int row = 0; row < board.getHeight(); row++) {
    		for (int col = 0; col < board.getWidth(); col++) {
    			int v = board.getValue(col, row);
    			if (v == Board.UNSET)
    				out.print(String.format("%" + formatWidth + "s ", "-"));
    			else
    				out.print(String.format("%" + formatWidth + "d ", v));
    		}
    		out.println();
    	}
    	out.println();
    	
    	// Gather the board's constraints by extension.
    	Collection<Constraint> constraints = board.getConstraints();
    	Map<String, Collection<Constraint>> extConstraints = new HashMap<String, Collection<Constraint>>();
    	// Extensions that go in parser section, but have no constraints to specify.
    	Set<String> otherExtensions = new HashSet<String>();
    	for (Constraint c : constraints) {
    		if (c.shouldSave()) {
	    		String ext = c.getPluginProvider();
	    		if (extConstraints.containsKey(ext))
	    			extConstraints.get(ext).add(c);
	    		else {
	    			Collection<Constraint> constraintCol = new ArrayList<Constraint>();
	    			constraintCol.add(c);
	    			extConstraints.put(ext, constraintCol);
	    		}
    		}
    		else if (ExtensionManager.hasParser(c.getPluginProvider()))
    			otherExtensions.add(c.getPluginProvider());
    	}
    	
    	// Get extension save configurations, starting with primary extension if applicable.
    	if (extConstraints.containsKey(primaryExt)) {
    		Collection<Constraint> primaryConstraints = extConstraints.get(primaryExt);
    		if (primaryConstraints.size() > 0) {
    			out.println("." + primaryExt);
    			for (Constraint c : primaryConstraints)
    				out.println(c.save());
    			out.println();
    		}
    		extConstraints.remove(primaryExt);
    	}
    	
    	// Get save configurations of rest of extensions with constraints specified.
    	Set<String> extNames = extConstraints.keySet();
    	for (String ext : extNames) {
    		Collection<Constraint> extC = extConstraints.get(ext);
			out.println("." + ext);
    		if (extC.size() > 0) {
    			for (Constraint c : extC)
    				out.println(c.save());
    			out.println();
    		}
    	}
    	
    	// Save remaining extensions.
    	for (String ext : otherExtensions) {
    		out.println("." + ext);
    		out.println();
    	}
    	
    	// Close the output writer.
    	out.close();
    }
}
