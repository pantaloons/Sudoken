package sudoken.extension.latinsquare;

import java.util.HashSet;
import java.util.Set;

import sudoken.extension.Extension;

public class LatinSquare extends Extension {
    public LatinSquare() {
        super(null, new LatinSquareCreator(), new LatinSquareDecorator());
    }
    
    @Override
    public Set<String> getPrerequisites() {
    	return new HashSet<String>();
    }
}
