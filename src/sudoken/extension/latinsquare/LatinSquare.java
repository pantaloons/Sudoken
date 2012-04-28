package sudoken.extension.latinsquare;

import sudoken.extension.Extension;

public class LatinSquare extends Extension {
    public LatinSquare() {
        super(null, new LatinSquareCreator());
    }
    
    @Override
    public boolean hasPrerequisites() {
    	// no prerequisites
    	return true;
    }
}
