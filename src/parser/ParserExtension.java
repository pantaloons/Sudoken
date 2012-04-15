package parser;

public interface ParserExtension {

    /** 
     * is this needed? There needs to be a way to identify extension sections within the config file.
     * @return
     */
    String getName();

    void load(String sudokuConfig);

    String save();
}
