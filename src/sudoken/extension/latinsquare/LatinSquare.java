package sudoken.extension.latinsquare;

import sudoken.extension.Extension;
import sudoken.extension.ExtensionManager;

public class LatinSquare extends Extension {
    static {
        ExtensionManager.register(new LatinSquare());
    }

    public LatinSquare() {
        super(null, new LatinSquareCreator());
    }
    
    @Override
    public boolean hasPrerequisites() {
    	// no prerequisites
    	return true;
    }
}
