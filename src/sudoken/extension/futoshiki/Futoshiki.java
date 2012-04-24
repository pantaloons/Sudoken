package sudoken.extension.futoshiki;

import sudoken.extension.Extension;
import sudoken.extension.ExtensionManager;

public class Futoshiki extends Extension {
    static {
        ExtensionManager.register(new Futoshiki());
    }

    public Futoshiki() {
        super(new FutoshikiParser(), new FutoshikiCreator());
    }
    
    @Override
    public boolean hasPrerequisites() {
    	// Depends on latin square
    	return ExtensionManager.hasExtension("latinsquare");
    }
}
