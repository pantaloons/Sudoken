package parser;

import java.io.IOException;
import java.io.InputStream;

/**
 * Parses a sudoku config file. Any extension sections within the config file are
 * passed on to the extension parser.
 * 
 * @author Kevin Doran
 *
 */
public interface Parser {
    
    /**
     * Loads a sudoku puzzle from the given input stream. 
     * 
     * @param sudokuConfig 
     * @throws IOException
     */
    void load(InputStream sudokuConfig) throws IOException;

    String save();

    /**
     * 
     * @param parser
     */
    void addParserExtension(ParserExtension parser);
}
