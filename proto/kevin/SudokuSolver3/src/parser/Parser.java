package parser;

import java.io.IOException;
import java.io.InputStream;

import parser.connector.ParserExtension;

public interface Parser {

    void load(InputStream sudokuConfig) throws IOException;
    String save();
    void addParserExtension(ParserExtension parser);
}
