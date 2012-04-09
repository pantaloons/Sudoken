package extension;

import parser.connector.ParserExtension;
import sudokusolver.connector.ConstraintsExtension;

public interface SudokuExtension {
    
    ParserExtension getParser();
    ConstraintsExtension getConstraints();
    //gui
    
}
