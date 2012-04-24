package sudoken.extension.latinsquare;

import java.util.HashSet;
import java.util.Set;

import sudoken.extension.Extension;
import sudoken.extension.ExtensionManager;

public class LatinSquare extends Extension {
    static {
        ExtensionManager.register(new LatinSquare());
    }

    public LatinSquare() {
        super(null, new LatinSquareCreator(), null);
    }
    
    @Override
    public Set<String> getPrerequisites() {
    	return new HashSet<String>();
    }
}
