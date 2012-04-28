package sudoken.extension.diagonals;

import sudoken.extension.Extension;
import sudoken.extension.ExtensionManager;

public class Diagonals extends Extension {
//    static {
//        ExtensionManager.register(new Diagonals());
//    }

    public Diagonals() {
        super(new DiagonalsParser(), null);
    }
    
    @Override
    public boolean hasPrerequisites() {
    	// No prerequisites.
    	return true;
    }
}