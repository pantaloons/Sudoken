package parser.connector;

public interface ParserExtension {

    String getName();
    void load(String sudokuConfig);
    String save();
}
