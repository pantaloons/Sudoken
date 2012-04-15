package parser;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.ProviderNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import java.util.Set;

import parser.connector.ParserExtension;

import extension.standard.BasicLayoutParser;

public class ParserImp implements Parser {

    Collection<ParserExtension> parsers = new ArrayList<>();
    
    public ParserImp() {
        parsers.add(new BasicLayoutParser());
    }
    @Override
    public void load(InputStream sudokuPropertiesStream) throws IOException {
        Properties sudokuProperties = new Properties();
        sudokuProperties.load(sudokuPropertiesStream);
        sudokuPropertiesStream.close();
        Set<String> extensionNames = sudokuProperties.stringPropertyNames();
        for(String extensionName : extensionNames) {
            ParserExtension correspondingParser = null;
            for(ParserExtension parser : parsers) {
                if(parser.getName().equals(extensionName)) {
                    correspondingParser = parser;
                }
            }
            if(correspondingParser == null) {
                throw new ProviderNotFoundException("A parser could not be found to parse the config file section: " + extensionName + ".");
            }
            correspondingParser.load(sudokuProperties.getProperty(extensionName));
        }
    }

    @Override
    public String save() {
        String config = "";
        for(ParserExtension parser : parsers) {
            config += parser.save();
        }
        return config;
    }
    
    @Override
    public void addParserExtension(ParserExtension newParserExtension) {
        parsers.add(newParserExtension);
    }

}
