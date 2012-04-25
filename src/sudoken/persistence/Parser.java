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
                    width, height, ExtensionManager.getDecorator(ext)));
        }
        return ExtensionManager.getConstructor(type).create(width, height,
                grid, constraints, ExtensionManager.getDecorator(type));
    }
}
