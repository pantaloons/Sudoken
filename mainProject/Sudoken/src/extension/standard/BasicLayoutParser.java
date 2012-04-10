package extension.standard;

import parser.ParserExtension;

public class BasicLayoutParser implements ParserExtension {

    private String name = "basicLayout";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void load(String sudokuConfig) {
        // TODO Auto-generated method stub

    }

    @Override
    public String save() {
        // TODO Auto-generated method stub
        return null;
    }

}
